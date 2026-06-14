import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambdaDemo {
    public static void main(String[] args) {
        List<Student2> students = new ArrayList<>();
        students.add(new Student2("Alice", 22));
        students.add(new Student2("Bob", 20));
        students.add(new Student2("Charlie", 25));
        
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Sort by name: " + students);
        
        students.sort(Comparator.comparing(Student2::getAge).reversed());
        System.out.println("Sort by age desc: " + students);
        
        students.sort(Comparator.comparing(Student2::getAge).thenComparing(Student2::getName));
        System.out.println("Chained sort: " + students);
    }
}

class Student2 {
    private String name;
    private int age;
    
    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
