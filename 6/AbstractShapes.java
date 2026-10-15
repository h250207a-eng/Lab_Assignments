/** Lab 6, Exercise 1: concrete shapes implement an abstract contract. */
public class AbstractShapes {
    static abstract class Shape {
        public abstract double area();
        public abstract double perimeter();

        protected static double positive(double dimension) {
            if (!Double.isFinite(dimension) || dimension <= 0) {
                throw new IllegalArgumentException("Dimensions must be finite and positive");
            }
            return dimension;
        }
    }

    static class Circle extends Shape {
        private final double radius;
        Circle(double radius) { this.radius = positive(radius); }
        @Override public double area() { return Math.PI * radius * radius; }
        @Override public double perimeter() { return 2 * Math.PI * radius; }
    }

    static class Rectangle extends Shape {
        private final double width;
        private final double height;
        Rectangle(double width, double height) {
            this.width = positive(width);
            this.height = positive(height);
        }
        @Override public double area() { return width * height; }
        @Override public double perimeter() { return 2 * (width + height); }
    }

    static class Square extends Rectangle {
        Square(double side) { super(side, side); }
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(2), new Rectangle(2, 3), new Square(4)};
        for (Shape shape : shapes) {
            System.out.printf("%s: area=%.2f, perimeter=%.2f%n",
                    shape.getClass().getSimpleName(), shape.area(), shape.perimeter());
        }
    }
}
