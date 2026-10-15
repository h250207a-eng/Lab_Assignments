import java.util.List;

/** Lab 10, Exercise 4: filter above-average students and sort their names. */
public class AboveAverageStudents {
    record Student(String name, double marks) {
        Student {
            if (name == null || name.isBlank() || !Double.isFinite(marks) || marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Name and marks from 0 to 100 are required");
            }
        }
    }

    public static void main(String[] args) {
        List<Student> students = List.of(new Student("Zoe", 90), new Student("Ada", 85),
                new Student("Ben", 60), new Student("Rudo", 65));
        double average = students.stream().mapToDouble(Student::marks).average().orElse(0);
        System.out.printf("Class average: %.2f%n", average);
        students.stream()
                .filter(student -> student.marks() > average)
                .map(Student::name)
                .sorted()
                .forEach(System.out::println);
    }
}
