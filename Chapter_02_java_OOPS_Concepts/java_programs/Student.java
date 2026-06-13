public class Student {
    private String name;
    private int rollNo;
    
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
    
    public void display() {
        System.out.println("Name: " + name + ", Roll No: " + rollNo);
    }
    
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 101);
        Student s2 = new Student("Bob", 102);
        s1.display();
        s2.display();
    }
}
