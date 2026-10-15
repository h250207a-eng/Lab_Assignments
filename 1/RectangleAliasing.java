/** Lab 1, Exercise 4: assigning a reference creates an alias. */
public class RectangleAliasing {
    static class Rectangle {
        double width;
        double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        System.out.println("Prediction: r1.width will be 9.0.");
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;
        // EXPLAIN: both variables point to the same object on the heap.
        r2.width = 9;
        System.out.println("r1.width = " + r1.width);
        System.out.println("Same object? " + (r1 == r2));
        System.out.println("Changing the object through r2 is visible through r1.");
    }
}
