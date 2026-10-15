/** Lab 7, Exercise 5: finally executes before a pending return completes. */
public class FinallyReturn {
    static int demonstrateFinally() {
        try {
            return 1;
        } finally {
            // EXPLAIN: cleanup runs after evaluating the return value, before returning.
            System.out.println("finally executes before return");
        }
    }

    public static void main(String[] args) {
        System.out.println("Prediction: the finally message appears first, followed by returned: 1.");
        System.out.println("returned: " + demonstrateFinally());
    }
}
