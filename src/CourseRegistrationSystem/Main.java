package CourseRegistrationSystem;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CourseManagementSystem system = new CourseManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. Add Course");
            System.out.println("2. View Courses");
            System.out.println("3. Search Course by ID");
            System.out.println("4. Search Course by Name");
            System.out.println("5. Search Course by Instructor");
            System.out.println("6. Register Student for Course");
            System.out.println("7. View Registrations");
            System.out.println("8. Sort Courses by Name");
            System.out.println("9. Sort Courses by ID");
            System.out.println("10. Sort Courses by Credits");
            System.out.println("11. Add Student");
            System.out.println("12. Undo Last Registration");
            System.out.println("13. Prioritize Registrations by Course Credits");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");
            int choice = getValidIntInput(scanner);

            switch (choice) {
                case 1:
                    addCourse(system, scanner);
                    break;
                case 2:
                    system.viewCourses();
                    break;
                case 3:
                    searchCourseById(system, scanner);
                    break;
                case 4:
                    searchCourseByName(system, scanner);
                    break;
                case 5:
                    searchCourseByInstructor(system, scanner);
                    break;
                case 6:
                    registerStudentForCourse(system, scanner);
                    break;
                case 7:
                    system.viewRegistrations();
                    break;
                case 8:
                    system.sortCoursesByName();
                    System.out.println("Courses sorted by name.");
                    system.viewCourses();
                    break;
                case 9:
                    system.sortCoursesById();
                    System.out.println("Courses sorted by ID.");
                    system.viewCourses();
                    break;
                case 10:
                    system.sortCoursesByCredits();
                    System.out.println("Courses sorted by credits.");
                    system.viewCourses();
                    break;
                case 11:
                    addStudent(system, scanner);
                    break;
                case 12:
                    system.undoLastRegistration();
                    break;
                case 13:
                    system.prioritizeRegistrations();
                    break;
                case 14:
                	System.out.println("Exiting the Application! \n Have a Good Day!");
                    scanner.close();
                    return;
                default:
                    System.out.println("You have entered wrong input. Please try again.");
                    break;
            }
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("You have entered wrong input. Please enter a valid number:");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    private static void addCourse(CourseManagementSystem system, Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter instructor: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = getValidIntInput(scanner);
        system.addCourse(new Course(courseId, courseName, instructor, credits));
    }

    private static void searchCourseById(CourseManagementSystem system, Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        Course course = system.searchCourseById(courseId);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void searchCourseByName(CourseManagementSystem system, Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        List<Course> coursesByName = system.searchCourseByName(courseName);
        if (coursesByName.isEmpty()) {
            System.out.println("No courses found with the name " + courseName);
        } else {
            for (Course c : coursesByName) {
                System.out.println(c);
            }
        }
    }

    private static void searchCourseByInstructor(CourseManagementSystem system, Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        List<Course> coursesByInstructor = system.searchCourseByInstructor(instructor);
        if (coursesByInstructor.isEmpty()) {
            System.out.println("No courses found with the instructor " + instructor);
        } else {
            for (Course c : coursesByInstructor) {
                System.out.println(c);
            }
        }
    }

    private static void registerStudentForCourse(CourseManagementSystem system, Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = system.searchStudentById(studentId);
        if (student == null) {
            System.out.print("Student not found. Enter new student name to register: ");
            String studentName = scanner.nextLine();
            student = new Student(studentId, studentName);
            system.addStudent(student);
        }
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        Course course = system.searchCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        system.registerStudentForCourse(student, course);
    }

    private static void addStudent(CourseManagementSystem system, Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        system.addStudent(new Student(studentId, studentName));
    }
}
