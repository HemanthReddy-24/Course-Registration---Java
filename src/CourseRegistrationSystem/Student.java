package CourseRegistrationSystem;

public class Student {
    String studentId;
    String studentName;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + studentName;
    }
}
