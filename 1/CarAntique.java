import java.time.Year;

/** Lab 1, Exercise 2: determine whether a car is more than 25 years old. */
public class CarAntique {
    static class Car { 
        String brand;
        int year;
        double mileage;

        Car(String brand, int year, double mileage) {
            this.brand = brand;
            this.year = year;
            this.mileage = mileage;
        }

        void display() {
            System.out.printf("%s (%d), %.1f km%n", brand, year, mileage);
        }

        boolean isAntique() {
            // EXPLAIN: exactly 25 years old does not satisfy "more than 25".
            return Year.now().getValue() - year > 25;
        }
    }

    public static void main(String[] args) {
        int currentYear = Year.now().getValue();
        Car[] cars = {
            new Car("Ford", currentYear - 26, 140_000),
            new Car("Toyota", currentYear - 25, 90_000),
            new Car("Mazda", currentYear - 5, 18_000)
        };
        for (Car car : cars) {
            car.display();
            System.out.println("Antique? " + car.isAntique());
        }
    }
}
