/** Lab 4, Exercise 1: a Student inherits the Person display method. */
public class PersonStudent {
    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void display() {
            System.out.println(name + ", age " + age);
        }
    }

    static class Student extends Person {
        private final String course;

        Student(String name, int age, String course) {
            super(name, age);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Farai", 35);
        Student student = new Student("Tariro", 20, "Object-Oriented Programming");
        person.display();
        student.display(); // EXPLAIN: display is inherited without being rewritten.
        System.out.println("Student course: " + student.getCourse());
    }
}
