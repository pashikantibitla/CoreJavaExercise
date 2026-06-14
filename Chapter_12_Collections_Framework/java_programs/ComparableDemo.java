import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 22));
        students.add(new Student("Bob", 20));
        students.add(new Student("Charlie", 25));
        
        System.out.println("Before sort: " + students);
        Collections.sort(students);
        System.out.println("After sort (by age): " + students);
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Student other) {
        return this.age - other.age;
    }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
