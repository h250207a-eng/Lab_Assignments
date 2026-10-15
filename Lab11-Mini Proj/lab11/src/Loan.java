import java.time.LocalDate;

public class Loan {
    private final Member member;
    private final LibraryItem item;
    private final LocalDate loanDate;
    private final LocalDate dueDate;

    public Loan(Member member, LibraryItem item, LocalDate loanDate) {
        if (member == null || item == null || loanDate == null)
            throw new IllegalArgumentException("Loan needs a member, an item and a date");
        this.member = member;
        this.item = item;
        this.loanDate = loanDate;
        this.dueDate = loanDate.plusDays(item.loanPeriodDays());   // polymorphic call
    }

    public Member getMember()      { return member; }
    public LibraryItem getItem()   { return item; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate()  { return dueDate; }

    public boolean isOverdue(LocalDate today) { return today.isAfter(dueDate); }

    @Override
    public String toString() {
        return item.getTitle() + " -> " + member.getName() + " (due " + dueDate + ")";
    }
}
