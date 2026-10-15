/** Lab 7, Exercise 2: catch an invalid array index at the access point. */
public class SafeArrayAccess {
    static void printElement(int[] values, int index) {
        try {
            System.out.println("Element at " + index + ": " + values[index]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Index " + index + " is outside 0 to " + (values.length - 1));
        }
    }

    public static void main(String[] args) {
        int[] values = {10, 20, 30};
        printElement(values, 1);
        printElement(values, -1);
        printElement(values, values.length);
    }
}
