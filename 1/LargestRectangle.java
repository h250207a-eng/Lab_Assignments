/** Lab 1, Exercise 3: compare the areas of five Rectangle objects. */
public class LargestRectangle {
    static class Rectangle {
        double width;
        double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        double area() {
            return width * height;
        }

        double perimeter() {
            return 2 * (width + height);
        }

        @Override
        public String toString() {
            return String.format("%.1f x %.1f, area=%.1f, perimeter=%.1f",
                    width, height, area(), perimeter());
        }
    }

    public static void main(String[] args) {
        Rectangle[] rectangles = {
            new Rectangle(2, 4), new Rectangle(5, 3), new Rectangle(6, 6),
            new Rectangle(8, 2), new Rectangle(4, 4)
        };
        Rectangle largest = rectangles[0];
        for (Rectangle rectangle : rectangles) {
            if (rectangle.area() > largest.area()) {
                largest = rectangle;
            }
        }
        System.out.println("Largest rectangle: " + largest);
    }
}
