import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

/** Plain-Java test runner (no frameworks needed). Run: java -cp out LibraryTests */
public class LibraryTests {
    static int passed = 0, failed = 0;

    static void check(String name, boolean ok) {
        if (ok) { passed++; System.out.println("PASS  " + name); }
        else    { failed++; System.out.println("FAIL  " + name); }
    }

    interface Thrower { void run() throws Exception; }
    static boolean throwsType(Class<? extends Throwable> t, Thrower r) {
        try { r.run(); return false; } catch (Throwable e) { return t.isInstance(e); }
    }

    static Library sample() {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java Concurrency", "Brian Goetz"));
        lib.addItem(new Book("B4", "Hello, World: A \"Quoted\" Title", "A. Author"));
        lib.addItem(new Dvd("D1", "The Matrix", 136));
        lib.registerMember(new Member("M1", "Edric"));
        lib.registerMember(new Member("M2", "Tariro"));
        return lib;
    }

    public static void main(String[] args) throws Exception {
        LocalDate day = LocalDate.of(2026, 10, 1);

        // --- validation ---
        check("blank title rejected", throwsType(IllegalArgumentException.class, () -> new Book("X", " ", "a")));
        check("blank member name rejected", throwsType(IllegalArgumentException.class, () -> new Member("M", "")));
        check("non-positive DVD time rejected", throwsType(IllegalArgumentException.class, () -> new Dvd("X", "t", 0)));

        // --- polymorphism ---
        List<LibraryItem> mixed = List.of(new Book("B", "b", "a"), new Dvd("D", "d", 90));
        check("Book loan 21 days, Dvd 7 days via LibraryItem reference",
                mixed.get(0).loanPeriodDays() == 21 && mixed.get(1).loanPeriodDays() == 7);

        // --- borrowing ---
        Library lib = sample();
        Loan l1 = lib.borrow("M1", "B1", day);
        check("book due date = loan date + 21", l1.getDueDate().equals(day.plusDays(21)));
        check("item becomes unavailable", !lib.findItem("B1").isAvailable());
        Loan ld = lib.borrow("M2", "D1", day);
        check("dvd due date = loan date + 7", ld.getDueDate().equals(day.plusDays(7)));

        check("borrowing an item already on loan -> ItemNotAvailableException",
                throwsType(ItemNotAvailableException.class, () -> lib.borrow("M2", "B1", day)));
        check("unknown member -> IllegalArgumentException",
                throwsType(IllegalArgumentException.class, () -> lib.borrow("NOPE", "B2", day)));
        check("unknown item -> IllegalArgumentException",
                throwsType(IllegalArgumentException.class, () -> lib.borrow("M1", "NOPE", day)));

        lib.borrow("M1", "B2", day);
        lib.borrow("M1", "B3", day);
        check("member holds exactly 3 loans", lib.findMember("M1").getLoans().size() == 3);
        check("4th loan -> MemberLimitExceededException",
                throwsType(MemberLimitExceededException.class, () -> lib.borrow("M1", "B4", day)));
        check("failed borrow leaves item available (no half-finished state)", lib.findItem("B4").isAvailable());
        check("duplicate item id rejected",
                throwsType(IllegalArgumentException.class, () -> lib.addItem(new Book("B1", "x", "y"))));

        // --- returning ---
        lib.giveBack("B3");
        check("returned item available again", lib.findItem("B3").isAvailable());
        check("member back to 2 loans", lib.findMember("M1").getLoans().size() == 2);
        check("returning an item not on loan -> IllegalArgumentException",
                throwsType(IllegalArgumentException.class, () -> lib.giveBack("B3")));
        lib.borrow("M1", "B4", day);   // slot freed, now allowed
        check("can borrow again after returning", lib.findMember("M1").getLoans().size() == 3);

        // --- overdue ---
        // D1 due day+7; books due day+21
        check("nothing overdue on the due date itself", lib.overdueLoans(day.plusDays(7)).isEmpty());
        check("dvd overdue day after due date", lib.overdueLoans(day.plusDays(8)).size() == 1);
        check("all 4 loans overdue after 22 days", lib.overdueLoans(day.plusDays(22)).size() == 4);

        // --- search (streams) ---
        List<LibraryItem> r = lib.searchByTitle("JAVA");
        check("search is case-insensitive and sorted by title",
                r.size() == 2 && r.get(0).getTitle().equals("Effective Java")
                        && r.get(1).getTitle().equals("Java Concurrency"));
        check("search with no match returns empty list", lib.searchByTitle("zzz").isEmpty());

        // --- persistence round trip ---
        Path tmp = Files.createTempDirectory("libtest");
        LibraryStorage storage = new LibraryStorage(tmp);
        storage.save(lib);
        List<String> warnings = new ArrayList<>();
        Library reloaded = storage.load(warnings);
        check("reload produces no warnings", warnings.isEmpty());
        check("items survive reload", reloaded.getItems().size() == 5);
        check("members survive reload", reloaded.getMembers().size() == 2);
        check("tricky title (comma + quotes) survives reload",
                reloaded.findItem("B4").getTitle().equals("Hello, World: A \"Quoted\" Title"));
        check("Dvd subclass restored with correct type", reloaded.findItem("D1") instanceof Dvd d && d.getMinutes() == 136);
        check("loans survive reload with same due dates",
                reloaded.allLoans().size() == 4
                        && reloaded.allLoans().stream().anyMatch(l -> l.getItem().getId().equals("D1")
                        && l.getDueDate().equals(day.plusDays(7))));
        check("loaned items still unavailable after reload", !reloaded.findItem("B1").isAvailable());
        check("limit still enforced after reload",
                throwsType(MemberLimitExceededException.class, () -> reloaded.borrow("M1", "B3", day)));

        // --- corrupt file handling ---
        Path bad = Files.createTempDirectory("badlib");
        Files.writeString(bad.resolve("items.csv"),
                "type,id,title,extra\nBOOK,B1,Good,Auth\nDVD,D9,Bad,notanumber\nTAPE,T1,x,y\nBOOK,B1,Dup,Auth\n");
        warnings.clear();
        Library partial = new LibraryStorage(bad).load(warnings);
        check("corrupt lines skipped with warnings, good line kept",
                partial.getItems().size() == 1 && warnings.size() == 3);

        // --- first run (no files) ---
        Library empty = new LibraryStorage(Files.createTempDirectory("emptylib")).load(new ArrayList<>());
        check("first run with no files gives empty library", empty.getItems().isEmpty());

        System.out.println("\n" + passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }
}
