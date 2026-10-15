/** Abstract base for everything the library lends. Subclasses decide their own loan period. */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank())       throw new IllegalArgumentException("Item id must not be blank");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be blank");
        this.id = id.trim();
        this.title = title.trim();
    }

    /** Abstract: each kind of item has its own loan period (polymorphism). */
    public abstract int loanPeriodDays();

    /** Short label used in listings and CSV files, e.g. "BOOK" or "DVD". */
    public abstract String type();

    /** The one type-specific value stored in the CSV (author or running time). */
    public abstract String extra();

    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException("'" + title + "' (" + id + ") is already on loan");
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }

    /** Package-private: used by Library when reloading saved loans. */
    void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%d-day loan, %s)",
                type(), id, title, loanPeriodDays(), available ? "available" : "on loan");
    }
}
