import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Saves and loads the library as three CSV files: items.csv, members.csv, loans.csv. */
public class LibraryStorage {
    private final Path dir;

    public LibraryStorage(Path dir) { this.dir = dir; }

    public void save(Library lib) throws IOException {
        Files.createDirectories(dir);

        List<String> itemLines = new ArrayList<>();
        itemLines.add("type,id,title,extra");
        for (LibraryItem i : lib.getItems())
            itemLines.add(CsvUtil.join(i.type(), i.getId(), i.getTitle(), i.extra()));
        Files.write(dir.resolve("items.csv"), itemLines);

        List<String> memberLines = new ArrayList<>();
        memberLines.add("id,name");
        for (Member m : lib.getMembers())
            memberLines.add(CsvUtil.join(m.getId(), m.getName()));
        Files.write(dir.resolve("members.csv"), memberLines);

        List<String> loanLines = new ArrayList<>();
        loanLines.add("memberId,itemId,loanDate");
        for (Loan l : lib.allLoans())
            loanLines.add(CsvUtil.join(l.getMember().getId(), l.getItem().getId(), l.getLoanDate().toString()));
        Files.write(dir.resolve("loans.csv"), loanLines);
    }

    /** Returns an empty library on a first run; reports bad lines instead of crashing. */
    public Library load(List<String> warnings) throws IOException {
        Library lib = new Library();
        Path itemsFile = dir.resolve("items.csv");
        Path membersFile = dir.resolve("members.csv");
        Path loansFile = dir.resolve("loans.csv");

        if (Files.exists(itemsFile)) {
            int n = 1;
            for (String line : Files.readAllLines(itemsFile).stream().skip(1).toList()) {
                n++;
                if (line.isBlank()) continue;
                try {
                    List<String> f = CsvUtil.split(line);
                    lib.addItem(switch (f.get(0)) {
                        case "BOOK" -> new Book(f.get(1), f.get(2), f.get(3));
                        case "DVD"  -> new Dvd(f.get(1), f.get(2), Integer.parseInt(f.get(3)));
                        default -> throw new IllegalArgumentException("unknown item type " + f.get(0));
                    });
                } catch (RuntimeException e) {
                    warnings.add("items.csv line " + n + " skipped: " + e.getMessage());
                }
            }
        }
        if (Files.exists(membersFile)) {
            int n = 1;
            for (String line : Files.readAllLines(membersFile).stream().skip(1).toList()) {
                n++;
                if (line.isBlank()) continue;
                try {
                    List<String> f = CsvUtil.split(line);
                    lib.registerMember(new Member(f.get(0), f.get(1)));
                } catch (RuntimeException e) {
                    warnings.add("members.csv line " + n + " skipped: " + e.getMessage());
                }
            }
        }
        if (Files.exists(loansFile)) {
            int n = 1;
            for (String line : Files.readAllLines(loansFile).stream().skip(1).toList()) {
                n++;
                if (line.isBlank()) continue;
                try {
                    List<String> f = CsvUtil.split(line);
                    lib.restoreLoan(f.get(0), f.get(1), LocalDate.parse(f.get(2)));
                } catch (RuntimeException e) {
                    warnings.add("loans.csv line " + n + " skipped: " + e.getMessage());
                }
            }
        }
        return lib;
    }
}
