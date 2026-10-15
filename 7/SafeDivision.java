import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Lab 7, Exercise 1: handle invalid integer input and division by zero. */
public class SafeDivision {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter two integers: ");
            int numerator = scanner.nextInt();
            int denominator = scanner.nextInt();
            System.out.println("Result: " + (numerator / denominator));
        } catch (InputMismatchException exception) {
            System.out.println("Input must contain two valid integers.");
        } catch (NoSuchElementException exception) {
            System.out.println("Input ended before two integers were supplied.");
        } catch (ArithmeticException exception) {
            System.out.println("Cannot divide by zero.");
        }
    }
}
