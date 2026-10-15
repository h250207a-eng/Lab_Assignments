/** Lab 2, Exercise 1: overloaded constructors and this(...). */
public class CircleConstructors {
    static class Circle {
        private final double radius;

        Circle() {
            this(1.0);
        }

        Circle(double radius) {
            if (!Double.isFinite(radius) || radius <= 0) {
                throw new IllegalArgumentException("Radius must be finite and positive");
            }
            this.radius = radius;
        }

        double getRadius() {
            return radius;
        }

        double area() {
            return Math.PI * radius * radius;
        }
    }

    public static void main(String[] args) {
        Circle defaultCircle = new Circle();
        Circle customCircle = new Circle(2.5);
        System.out.println("Default radius: " + defaultCircle.getRadius());
        System.out.printf("Custom radius: %.1f, area: %.2f%n",
                customCircle.getRadius(), customCircle.area());
    }
}
