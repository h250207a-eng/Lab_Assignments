import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Lab 8, Exercise 2: a Set stores each distinct name once. */
public class UniqueNames {
    public static void main(String[] args) {
        List<String> names = List.of("Ana", "Ben", "Ana", "Cleo", "Ben");
        // EXPLAIN: LinkedHashSet also preserves first-occurrence order.
        Set<String> uniqueNames = new LinkedHashSet<>(names);
        System.out.println("Original: " + names);
        System.out.println("Without duplicates: " + uniqueNames);
    }
}
