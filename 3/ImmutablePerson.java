/** Lab 3, Exercise 4: an immutable object cannot change after construction. */
// EXPLAIN: final prevents a subclass from adding mutable behaviour.
public final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120");
        }
        this.name = name.trim();
        this.age = age;
    }

    // EXPLAIN: private final fields are assigned once; getters expose values only.
    public String getName() { return name; }
    public int getAge() { return age; }
    // String is immutable and int is a value, so defensive copies are unnecessary.

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("Farai", 21);
        System.out.println(person);
        System.out.println("Final class, private final fields and no setters prevent mutation.");
        System.out.println("Getters return an immutable String and an int value.");
    }
}
