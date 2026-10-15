/** Lab 3, Exercise 1: private state is changed through validated setters. */
public class ValidatedStudent {
    static class Student {
        private String name;
        private int marks;

        Student(String name, int marks) {
            setName(name);
            setMarks(marks);
        }

        public void setName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name is required");
            }
            this.name = name.trim();
        }

        public void setMarks(int marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100");
            }
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Rudo", 82);
        System.out.println(student.getName() + ": " + student.getMarks());
        try {
            student.setMarks(120);
        } catch (IllegalArgumentException exception) {
            System.out.println("Rejected: " + exception.getMessage());
        }
        System.out.println("Marks after rejection: " + student.getMarks());
    }
}
