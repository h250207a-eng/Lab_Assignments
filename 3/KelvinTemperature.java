/** Lab 3, Exercise 5: hide the storage unit behind a stable public interface. */
public class KelvinTemperature {
    static final class Temperature {
        private final double kelvin;

        private Temperature(double kelvin) {
            if (!Double.isFinite(kelvin) || kelvin < 0) {
                throw new IllegalArgumentException("Temperature cannot be below absolute zero or non-finite");
            }
            this.kelvin = kelvin;
        }

        public static Temperature fromCelsius(double celsius) {
            return new Temperature(celsius + 273.15);
        }

        public static Temperature fromFahrenheit(double fahrenheit) {
            return fromCelsius((fahrenheit - 32) * 5 / 9);
        }

        public double getCelsius() {
            return kelvin - 273.15;
        }

        public double getFahrenheit() {
            return getCelsius() * 9 / 5 + 32;
        }
    }

    public static void main(String[] args) {
        Temperature temperature = Temperature.fromCelsius(25);
        System.out.printf("%.2f C = %.2f F%n",
                temperature.getCelsius(), temperature.getFahrenheit());
        try {
            Temperature.fromCelsius(-274);
        } catch (IllegalArgumentException exception) {
            System.out.println("Rejected: " + exception.getMessage());
        }
        System.out.println("Callers use the same factories and getters as Lab 2 despite kelvin storage.");
    }
}
