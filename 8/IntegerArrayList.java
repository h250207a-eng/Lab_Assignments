import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Lab 8, Exercise 1: store and summarise ten integers in an ArrayList. */
public class IntegerArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(4, 8, 1, 9, 2, 6, 3, 7, 5, 10));
        int sum = 0;
        for (int number : numbers) sum += number;
        System.out.println("Original: " + numbers);
        System.out.println("Sum: " + sum);
        System.out.println("Maximum: " + Collections.max(numbers));
        Collections.reverse(numbers);
        System.out.println("Reverse order: " + numbers);
    }
}
