/** Lab 4, Exercise 2: Student and Teacher reuse a common superclass. */
public class PersonTeacher {
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

        public String getCourse() { return course; }
    }

    static class Teacher extends Person {
        private final String subject;

        Teacher(String name, int age, String subject) {
            super(name, age);
            this.subject = subject;
        }

        public String getSubject() { return subject; }
    }

    public static void main(String[] args) {
        Student student = new Student("Rudo", 20, "Computer Science");
        Teacher teacher = new Teacher("Mr Moyo", 40, "Java");
        Person[] people = {student, teacher};
        for (Person person : people) {
            person.display();
        }
        System.out.println("Course: " + student.getCourse());
        System.out.println("Subject: " + teacher.getSubject());
        System.out.println("Both subclasses use the single display() implementation in Person.");
    }
}
