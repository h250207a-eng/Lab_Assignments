/** Lab 2, Exercise 3: overloaded constructors, unique IDs and copying. */
public class EmployeeConstructors {
    static class Employee {
        private static int nextId = 1000;
        private final int employeeId;
        private final String name;
        private final double salary;

        Employee() {
            this("Unknown", 0);
        }

        Employee(String name) {
            this(name, 0);
        }

        Employee(String name, double salary) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name is required");
            }
            if (!Double.isFinite(salary) || salary < 0) {
                throw new IllegalArgumentException("Salary must be finite and nonnegative");
            }
            this.employeeId = nextId++;
            this.name = name;
            this.salary = salary;
        }

        Employee(Employee other) {
            // EXPLAIN: same data, new object and new employee identity.
            this(other.name, other.salary);
        }

        @Override
        public String toString() {
            return String.format("%d: %s ($%.2f)", employeeId, name, salary);
        }
    }

    public static void main(String[] args) {
        Employee unnamed = new Employee();
        Employee named = new Employee("Ada");
        Employee original = new Employee("Tendai", 1200);
        Employee copy = new Employee(original);
        System.out.println(unnamed);
        System.out.println(named);
        System.out.println(original);
        System.out.println(copy);
        System.out.println("Same object? " + (original == copy));
    }
}
