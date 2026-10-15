/** Lab 9, Exercise 2: records generate accessors and value-based Object methods. */
public class StudentRecord {
    record Student(String name, int age) { }

    public static void main(String[] args) {
        Student first = new Student("Rudo", 20);
        Student second = new Student("Rudo", 20);
        System.out.println(first);
        System.out.println("Equal values? " + first.equals(second));
        System.out.println("Same object? " + (first == second));
        System.out.println("Name component: " + first.name());
        System.out.println("Age component: " + first.age());
    }
}
