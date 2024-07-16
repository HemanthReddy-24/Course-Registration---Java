package CourseRegistrationSystem;

public class Registration {
    private Student student;
    private Course course;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "student=" + student +
                ", course=" + course +
                '}';
    }
}
