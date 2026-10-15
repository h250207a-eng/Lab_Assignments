import java.time.LocalDate;
import java.util.*;

/** Core service: owns the collections and enforces the borrowing rules. */
public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();

    // ---------- adding ----------
    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId()))
            throw new IllegalArgumentException("An item with id " + item.getId() + " already exists");
        items.put(item.getId(), item);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getId()))
            throw new IllegalArgumentException("A member with id " + member.getId() + " already exists");
        members.put(member.getId(), member);
    }

    // ---------- lookups ----------
    public LibraryItem findItem(String id) {
        LibraryItem item = items.get(id);
        if (item == null) throw new IllegalArgumentException("No item with id " + id);
        return item;
    }

    public Member findMember(String id) {
        Member m = members.get(id);
        if (m == null) throw new IllegalArgumentException("No member with id " + id);
        return m;
    }

    public Collection<LibraryItem> getItems()   { return Collections.unmodifiableCollection(items.values()); }
    public Collection<Member> getMembers()      { return Collections.unmodifiableCollection(members.values()); }

    // ---------- borrowing ----------
    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        return borrow(memberId, itemId, LocalDate.now());
    }

    /** Date is a parameter so the rules can be tested without waiting for real time to pass. */
    public Loan borrow(String memberId, String itemId, LocalDate today)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item (IllegalArgumentException if missing)
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);

        // 2. check the member's limit and the item's availability BEFORE changing anything,
        //    so a failed borrow leaves no half-finished state behind
        if (member.atLimit()) throw new MemberLimitExceededException(
                member.getName() + " already has the maximum of " + Member.MAX_LOANS + " loans");
        item.borrow(member);                       // throws ItemNotAvailableException if on loan

        // 3. create the Loan, item is now unavailable, record the loan on the member
        Loan loan = new Loan(member, item, today);
        member.addLoan(loan);                      // cannot fail: limit already checked
        return loan;
    }

    public Loan giveBack(String itemId) {
        LibraryItem item = findItem(itemId);
        for (Member m : members.values()) {
            for (Loan loan : m.getLoans()) {
                if (loan.getItem() == item) {
                    item.giveBack();
                    m.removeLoan(loan);
                    return loan;
                }
            }
        }
        throw new IllegalArgumentException("'" + item.getTitle() + "' is not currently on loan");
    }

    // ---------- reports and search (streams) ----------
    public List<Loan> allLoans() {
        return members.values().stream()
                .flatMap(m -> m.getLoans().stream())
                .sorted(Comparator.comparing(Loan::getDueDate))
                .toList();
    }

    public List<Loan> overdueLoans(LocalDate today) {
        return allLoans().stream().filter(l -> l.isOverdue(today)).toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        String needle = text.toLowerCase();
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(needle))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    // ---------- used by the storage class when reloading ----------
    void restoreLoan(String memberId, String itemId, LocalDate loanDate) {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);
        try {
            member.addLoan(new Loan(member, item, loanDate));
            item.setAvailable(false);
        } catch (MemberLimitExceededException e) {
            throw new IllegalStateException("Corrupt data: " + e.getMessage(), e);
        }
    }
}
