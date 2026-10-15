/** Lab 1, Exercise 5: composition, chained access and null. */
public class PersonAddress {
    static class Address {
        String city;

        Address(String city) {
            this.city = city;
        }
    }

    static class Person {
        String name;
        Address address;

        Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Rudo", new Address("Harare"));
        // EXPLAIN: person -> address object -> city field.
        System.out.println(person.name + " lives in " + person.address.city);

        Person noAddress = new Person("Tariro", null);
        try {
            // Deliberately demonstrate the failure requested by the exercise.
            System.out.println(noAddress.address.city);
        } catch (NullPointerException exception) {
            System.out.println("NullPointerException: address does not refer to an object.");
        }
        // EXPLAIN: guard a nullable reference before using it in normal code.
        if (noAddress.address == null) {
            System.out.println(noAddress.name + " has no address recorded.");
        }
    }
}
