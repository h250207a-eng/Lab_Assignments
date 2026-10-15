import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Console menu. All rules live in Library; this class only reads input and prints results. */
public class LibraryApp {
    public static void main(String[] args) throws IOException {
        LibraryStorage storage = new LibraryStorage(Path.of(args.length > 0 ? args[0] : "data"));
        List<String> warnings = new ArrayList<>();
        Library library = storage.load(warnings);
        warnings.forEach(w -> System.out.println("Warning: " + w));
        System.out.println("Loaded " + library.getItems().size() + " items, "
                + library.getMembers().size() + " members, " + library.allLoans().size() + " active loans.");

        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.hasNextLine() ? in.nextLine().trim() : "0";
            try {
                switch (choice) {
                    case "1" -> addItem(library, in);
                    case "2" -> registerMember(library, in);
                    case "3" -> borrow(library, in);
                    case "4" -> giveBack(library, in);
                    case "5" -> listOverdue(library);
                    case "6" -> search(library, in);
                    case "7" -> library.getItems().stream()
                            .sorted(java.util.Comparator.comparing(LibraryItem::getId))
                            .forEach(System.out::println);
                    case "0" -> running = false;
                    default  -> System.out.println("Please choose a number from the menu.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot borrow: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        storage.save(library);                       // persist on exit
        System.out.println("Data saved. Goodbye.");
    }

    private static void printMenu() {
        System.out.println("""

                === Library Menu ===
                1) Add item        2) Register member
                3) Borrow          4) Return
                5) List overdue    6) Search by title
                7) List all items  0) Save and exit""");
        System.out.print("> ");
    }

    private static String ask(Scanner in, String prompt) {
        System.out.print(prompt + ": ");
        return in.hasNextLine() ? in.nextLine().trim() : "";
    }

    private static void addItem(Library lib, Scanner in) {
        String type = ask(in, "Type (book/dvd)").toLowerCase();
        String id = ask(in, "Item id");
        String title = ask(in, "Title");
        LibraryItem item;
        if (type.equals("book")) {
            item = new Book(id, title, ask(in, "Author"));
        } else if (type.equals("dvd")) {
            String text = ask(in, "Running time (minutes)");
            try {
                item = new Dvd(id, title, Integer.parseInt(text));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("'" + text + "' is not a whole number");
            }
        } else {
            throw new IllegalArgumentException("Type must be 'book' or 'dvd'");
        }
        lib.addItem(item);
        System.out.println("Added: " + item);
    }

    private static void registerMember(Library lib, Scanner in) {
        Member m = new Member(ask(in, "Member id"), ask(in, "Name"));
        lib.registerMember(m);
        System.out.println("Registered: " + m);
    }

    private static void borrow(Library lib, Scanner in)
            throws ItemNotAvailableException, MemberLimitExceededException {
        Loan loan = lib.borrow(ask(in, "Member id"), ask(in, "Item id"));
        System.out.println("Loan created: " + loan);
    }

    private static void giveBack(Library lib, Scanner in) {
        Loan loan = lib.giveBack(ask(in, "Item id"));
        long late = java.time.temporal.ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
        System.out.println("Returned: " + loan.getItem().getTitle()
                + (late > 0 ? " (" + late + " days late)" : " (on time)"));
    }

    private static void listOverdue(Library lib) {
        List<Loan> overdue = lib.overdueLoans(LocalDate.now());
        if (overdue.isEmpty()) System.out.println("No overdue items.");
        else overdue.forEach(l -> System.out.println("OVERDUE: " + l));
    }

    private static void search(Library lib, Scanner in) {
        List<LibraryItem> results = lib.searchByTitle(ask(in, "Search text"));
        if (results.isEmpty()) System.out.println("No matches.");
        else results.forEach(System.out::println);
    }
}
