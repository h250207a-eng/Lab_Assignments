import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Lab 6, Exercise 4: natural ordering and an alternative Comparator. */
public class ComparableStudents {
    record Student(String name, int marks) implements Comparable<Student> {
        Student {
            if (name == null || name.isBlank() || marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Name and marks from 0 to 100 are required");
            }
        }

        @Override
        public int compareTo(Student other) {
            return name.compareTo(other.name);
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(List.of(
                new Student("Zoe", 80), new Student("Ada", 90), new Student("Ben", 70)));
        Collections.sort(students);
        System.out.println("By name: " + students);
        students.sort(Comparator.comparingInt(Student::marks).thenComparing(Student::name));
        System.out.println("By marks ascending: " + students);
    }
}
