import java.util.*;

class Course {
    private String courseCode;
    private String courseName;
    private List<Student> students;
    
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void displayStudents() {
        System.out.println("Course: " + courseName);
        for (Student student : students) {
            System.out.println("  - " + student.getName());
        }
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> courses;
    
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }
    
    public void enroll(Course course) {
        courses.add(course);
        course.addStudent(this);
    }
    
    public String getName() {
        return name;
    }
    
    public void displayCourses() {
        System.out.println("Student: " + name);
        for (Course course : courses) {
            System.out.println("  - " + course.courseName);
        }
    }
}

public class StudentCourse {
    public static void main(String[] args) {
        Course java = new Course("CS101", "Java Programming");
        Course python = new Course("CS102", "Python Programming");
        
        Student alice = new Student("S001", "Alice");
        Student bob = new Student("S002", "Bob");
        
        alice.enroll(java);
        alice.enroll(python);
        bob.enroll(java);
        
        java.displayStudents();
        alice.displayCourses();
    }
}
