import java.util.Arrays;

/** Lab 5, Exercise 1: overloading is selected using argument types. */
public class MethodOverloading {
    static class Printer {
        void print(int value) { System.out.println("int: " + value); }
        void print(double value) { System.out.println("double: " + value); }
        void print(String value) { System.out.println("String: " + value); }
        void print(int[] values) { System.out.println("int[]: " + Arrays.toString(values)); }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print(4);
        printer.print(2.5);
        printer.print("Java");
        printer.print(new int[]{1, 2, 3});
    }
}
