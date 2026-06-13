class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Employee extends Person {
    protected String employeeId;
    protected double salary;
    
    public Employee(String name, int age, String employeeId, double salary) {
        super(name, age);
        this.employeeId = employeeId;
        this.salary = salary;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Employee ID: " + employeeId + ", Salary: $" + salary);
    }
}

class Manager extends Employee {
    private String department;
    private int teamSize;
    
    public Manager(String name, int age, String employeeId, double salary, String department, int teamSize) {
        super(name, age, employeeId, salary);
        this.department = department;
        this.teamSize = teamSize;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Department: " + department + ", Team Size: " + teamSize);
    }
}

public class EmployeeHierarchy {
    public static void main(String[] args) {
        Manager manager = new Manager("John", 35, "E001", 80000, "IT", 10);
        manager.display();
    }
}
