public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author must not be blank");
        this.author = author.trim();
    }

    public String getAuthor() { return author; }

    @Override public int loanPeriodDays() { return 21; }
    @Override public String type()  { return "BOOK"; }
    @Override public String extra() { return author; }

    @Override
    public String toString() { return super.toString() + " by " + author; }
}
