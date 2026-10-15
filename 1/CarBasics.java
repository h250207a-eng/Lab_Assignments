/** Lab 1, Exercise 1: state and behaviour belong to each Car object. */
public class CarBasics {
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
    }

    public static void main(String[] args) {
        // EXPLAIN: each new expression creates a separate object.
        Car[] cars = {
            new Car("Toyota", 2020, 42_000),
            new Car("Honda", 2018, 61_000),
            new Car("Mazda", 2022, 18_000)
        };
        for (Car car : cars) {
            car.display();
        }
    }
}
