import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Lab 10, Exercise 2: supply a Predicate to removeIf. */
public class RemoveEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(-2, -1, 0, 1, 2, 3, 4, 5, 6));
        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println("Before: " + numbers);
        numbers.removeIf(isEven);
        System.out.println("Odd numbers: " + numbers);
    }
}
