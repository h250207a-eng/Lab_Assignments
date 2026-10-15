import java.util.Arrays;

/** Lab 8, Exercise 5: sort comparable reference types with a generic method. */
public class GenericBubbleSort {
    static <T extends Comparable<T>> void bubbleSort(T[] values) {
        for (int pass = 0; pass < values.length - 1; pass++) {
            boolean swapped = false;
            for (int index = 0; index < values.length - 1 - pass; index++) {
                if (values[index].compareTo(values[index + 1]) > 0) {
                    T temporary = values[index];
                    values[index] = values[index + 1];
                    values[index + 1] = temporary;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {3, 1, 2, 1};
        String[] words = {"Zebra", "Apple", "Mango"};
        bubbleSort(numbers);
        bubbleSort(words);
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(words));
        System.out.println("int[] cannot bind to T[]: generics require reference types; use Integer[].");
    }
}
