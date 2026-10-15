/** Lab 5, Exercise 3: extend the manual's Employee/Manager/Developer hierarchy. */
public class EmployeePayroll {
    static class Employee {
        private final String name;
        protected final double baseSalary;

        Employee(String name, double baseSalary) {
            if (name == null || name.isBlank() || !Double.isFinite(baseSalary) || baseSalary < 0) {
                throw new IllegalArgumentException("Name and a finite nonnegative salary are required");
            }
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public String getName() { return name; }
        public double calculatePay() { return baseSalary; }
    }

    static class Manager extends Employee {
        private final double bonus;

        Manager(String name, double baseSalary, double bonus) {
            super(name, baseSalary);
            if (!Double.isFinite(bonus) || bonus < 0) throw new IllegalArgumentException("Invalid bonus");
            this.bonus = bonus;
        }

        @Override
        public double calculatePay() { return super.calculatePay() + bonus; }
    }

    static class Developer extends Employee {
        private static final double OVERTIME_RATE = 25;
        private final int overtimeHours;

        Developer(String name, double baseSalary, int overtimeHours) {
            super(name, baseSalary);
            if (overtimeHours < 0) throw new IllegalArgumentException("Invalid overtime hours");
            this.overtimeHours = overtimeHours;
        }

        @Override
        public double calculatePay() { return baseSalary + overtimeHours * OVERTIME_RATE; }
    }

    static class Intern extends Employee {
        Intern(String name, double stipend) { super(name, stipend); }

        @Override
        public double calculatePay() { return baseSalary; }
    }

    static double printPayroll(Employee[] employees) {
        double total = 0;
        for (Employee employee : employees) {
            // EXPLAIN: no instanceof or subclass-specific branches are necessary.
            double pay = employee.calculatePay();
            System.out.printf("%s earns %.2f%n", employee.getName(), pay);
            total += pay;
        }
        return total;
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("Kudzai", 2000), new Manager("Nyasha", 3500, 800),
            new Developer("Tatenda", 3000, 10), new Intern("Rudo", 600)
        };
        System.out.printf("Total payroll: %.2f%n", printPayroll(employees));
        System.out.println("Adding Intern changes the array, not the payroll loop.");
    }
}
