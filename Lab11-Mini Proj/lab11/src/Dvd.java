public class Dvd extends LibraryItem {
    private final int minutes;

    public Dvd(String id, String title, int minutes) {
        super(id, title);
        if (minutes <= 0) throw new IllegalArgumentException("Running time must be positive");
        this.minutes = minutes;
    }

    public int getMinutes() { return minutes; }

    @Override public int loanPeriodDays() { return 7; }
    @Override public String type()  { return "DVD"; }
    @Override public String extra() { return String.valueOf(minutes); }

    @Override
    public String toString() { return super.toString() + ", " + minutes + " min"; }
}
