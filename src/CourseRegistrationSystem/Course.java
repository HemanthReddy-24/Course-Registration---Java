package CourseRegistrationSystem;

public class Course {
    String courseId;
    String courseName;
    String instructor;
    int credits;

    public Course(String courseId, String courseName, String instructor, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Name: " + courseName + ", Instructor: " + instructor + ", Credits: " + credits;
    }
}
