/** Lab 2, Exercise 2: one static counter is shared by every instance. */
public class CircleCounter {
    static class Circle {
        private static int count;
        private final double radius;

        Circle() {
            this(1.0);
        }

        Circle(double radius) {
            if (!Double.isFinite(radius) || radius <= 0) {
                throw new IllegalArgumentException("Radius must be finite and positive");
            }
            this.radius = radius;
            // EXPLAIN: constructor chaining increments once per successful creation.
            count++;
        }

        static int getCount() {
            return count;
        }

        double getRadius() {
            return radius;
        }
    }

    public static void main(String[] args) {
        System.out.println("Before construction: " + Circle.getCount());
        Circle[] circles = {new Circle(), new Circle(2), new Circle(3)};
        for (Circle circle : circles) {
            System.out.println("Radius: " + circle.getRadius());
        }
        System.out.println("Circles created: " + Circle.getCount());
        // Counts successful constructions, not garbage-collector liveness.
    }
}
