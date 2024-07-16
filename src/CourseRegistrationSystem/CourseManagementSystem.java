package CourseRegistrationSystem;
import java.util.*;

public class CourseManagementSystem {
    private List<Course> courses = new ArrayList<>();  // ArrayList used to store Course objects
    private List<Student> students = new ArrayList<>();  // ArrayList used to store Student objects
    private Queue<Registration> registrations = new LinkedList<>();  // LinkedList used as a Queue to store Registration objects
    private Stack<Registration> registrationHistory = new Stack<>();  // Stack used to store Registration objects for undo functionality
    private PriorityQueue<Registration> prioritizedRegistrations = new PriorityQueue<>(new RegistrationComparator());  // PriorityQueue to prioritize registrations based on credits

    // Add new course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // View all courses
    public void viewCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // Search for course by ID
    public Course searchCourseById(String courseId) {
        for (Course course : courses) {
            if (course.courseId.equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    // Search for courses by name
    public List<Course> searchCourseByName(String courseName) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.courseName.equalsIgnoreCase(courseName)) {
                result.add(course);
            }
        }
        return result;
    }

    // Search for courses by instructor
    public List<Course> searchCourseByInstructor(String instructor) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.instructor.equalsIgnoreCase(instructor)) {
                result.add(course);
            }
        }
        return result;
    }

    // Register student for course
    public void registerStudentForCourse(Student student, Course course) {
        Registration registration = new Registration(student, course);
        registrations.add(registration);  // Add to the Queue
        registrationHistory.push(registration);  // Add to the Stack for undo functionality
    }

    // View all registrations
    public void viewRegistrations() {
        for (Registration registration : registrations) {
            System.out.println(registration);
        }
    }

    // Sort courses by name
    public void sortCoursesByName() {
        courses.sort((c1, c2) -> c1.courseName.compareToIgnoreCase(c2.courseName));  
    }

    // Sort courses by ID
    public void sortCoursesById() {
        courses.sort((c1, c2) -> c1.courseId.compareTo(c2.courseId));  
    }

    // Sort courses by credits
    public void sortCoursesByCredits() {
        courses.sort((c1, c2) -> Integer.compare(c1.credits, c2.credits));  
    }

    // Add new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Search for student by ID
    public Student searchStudentById(String studentId) {
        for (Student student : students) {
            if (student.studentId.equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Undo last registration
    public void undoLastRegistration() {
        if (!registrationHistory.isEmpty()) {
            Registration lastRegistration = registrationHistory.pop();  // Remove from the Stack
            registrations.remove(lastRegistration);  // Remove from the Queue
            System.out.println("Undo successful. Removed registration: " + lastRegistration);
        } else {
            System.out.println("No registration to undo.");
        }
    }

    // Prioritize registrations by course credits
    public void prioritizeRegistrations() {
        prioritizedRegistrations.addAll(registrations);
        System.out.println("Registrations prioritized by course credits:");
        while (!prioritizedRegistrations.isEmpty()) {
            System.out.println(prioritizedRegistrations.poll());
        }
    }
}

// Comparator for prioritizing registrations by course credits
class RegistrationComparator implements Comparator<Registration> {
    @Override
    public int compare(Registration r1, Registration r2) {
        return Integer.compare(r2.getCourse().credits, r1.getCourse().credits);
    }
}
