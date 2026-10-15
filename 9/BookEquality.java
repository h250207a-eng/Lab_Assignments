import java.util.HashSet;
import java.util.Set;

/** Lab 9, Exercise 3: ISBN determines Book equality and hash codes. */
public class BookEquality {
    static final class Book {
        private final String title;
        private final String isbn;

        Book(String title, String isbn) {
            if (title == null || title.isBlank() || isbn == null || isbn.isBlank()) {
                throw new IllegalArgumentException("Title and ISBN are required");
            }
            this.title = title;
            this.isbn = isbn;
        }

        @Override
        public boolean equals(Object other) {
            return this == other || other instanceof Book book && isbn.equals(book.isbn);
        }

        @Override
        public int hashCode() {
            // EXPLAIN: equal ISBNs must give equal hash codes for HashSet lookup.
            return isbn.hashCode();
        }

        @Override
        public String toString() { return title + " [ISBN " + isbn + "]"; }
    }

    public static void main(String[] args) {
        Book first = new Book("Java", "978-0000000001");
        Book duplicate = new Book("Another edition", "978-0000000001");
        Set<Book> books = new HashSet<>();
        System.out.println("First added? " + books.add(first));
        System.out.println("Duplicate added? " + books.add(duplicate));
        System.out.println("Equal? " + first.equals(duplicate));
        System.out.println("Equal hashes? " + (first.hashCode() == duplicate.hashCode()));
        System.out.println("Books stored: " + books.size());
        System.out.println(first);
    }
}
