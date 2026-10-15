/** Lab 3, Exercise 2: calculate a grade from marks instead of storing it. */
public class StudentGrades {
    static class Student {
        private final String name;
        private int marks;

        Student(String name, int marks) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name is required");
            }
            this.name = name;
            setMarks(marks);
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100");
            }
            this.marks = marks;
        }

        public String getGrade() {
            // Assumption: A >= 75, B >= 60, C >= 50, otherwise F.
            if (marks >= 75) return "A";
            if (marks >= 60) return "B";
            if (marks >= 50) return "C";
            return "F";
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Rudo", 0);
        for (int marks : new int[]{0, 49, 50, 59, 60, 74, 75, 100}) {
            student.setMarks(marks);
            System.out.println(student.getName() + ": " + student.getMarks()
                    + " -> " + student.getGrade());
        }
    }
}
