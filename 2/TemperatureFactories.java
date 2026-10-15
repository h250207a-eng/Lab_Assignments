/** Lab 2, Exercise 5: named factories make the input unit explicit. */
public class TemperatureFactories {
    static final class Temperature {
        private final double celsius;

        private Temperature(double celsius) {
            if (!Double.isFinite(celsius) || celsius < -273.15) {
                throw new IllegalArgumentException("Temperature must be finite and at least -273.15 C");
            }
            this.celsius = celsius;
        }

        static Temperature fromCelsius(double celsius) {
            return new Temperature(celsius);
        }

        static Temperature fromFahrenheit(double fahrenheit) {
            return new Temperature((fahrenheit - 32) * 5 / 9);
        }

        double getCelsius() {
            return celsius;
        }

        double getFahrenheit() {
            return celsius * 9 / 5 + 32;
        }
    }

    public static void main(String[] args) {
        Temperature warm = Temperature.fromCelsius(25);
        Temperature freezing = Temperature.fromFahrenheit(32);
        System.out.printf("%.2f C = %.2f F%n", warm.getCelsius(), warm.getFahrenheit());
        System.out.printf("32.00 F = %.2f C%n", freezing.getCelsius());
        System.out.println("Factory names state the unit; two Temperature(double) constructors cannot coexist.");
    }
}
