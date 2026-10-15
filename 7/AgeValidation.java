/** Lab 7, Exercise 3: a checked exception must be caught or declared. */
public class AgeValidation {
    static class InvalidAgeException extends Exception {
        private static final long serialVersionUID = 1L;

        InvalidAgeException(int age) {
            super("Invalid age: " + age + "; expected 0 to 120");
        }
    }

    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) throw new InvalidAgeException(age);
    }

    public static void main(String[] args) {
        for (int age : new int[]{-1, 0, 20, 120, 121}) {
            try {
                validateAge(age);
                System.out.println(age + " is valid");
            } catch (InvalidAgeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
