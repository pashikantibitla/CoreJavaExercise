import java.util.*;

interface Employee {
    void showEmployeeDetails();
    double getSalary();
}

class Developer implements Employee {
    private String name;
    private double salary;
    
    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public void showEmployeeDetails() {
        System.out.println("Developer: " + name + ", Salary: $" + salary);
    }
    
    @Override
    public double getSalary() {
        return salary;
    }
}

class Manager implements Employee {
    private String name;
    private double salary;
    private List<Employee> employees;
    
    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    
    @Override
    public void showEmployeeDetails() {
        System.out.println("Manager: " + name + ", Salary: $" + salary);
        System.out.println("  Subordinates:");
        for (Employee employee : employees) {
            System.out.print("    ");
            employee.showEmployeeDetails();
        }
    }
    
    @Override
    public double getSalary() {
        double total = salary;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }
}

public class OrganizationStructure {
    public static void main(String[] args) {
        Manager ceo = new Manager("CEO", 100000);
        Manager manager1 = new Manager("Manager1", 50000);
        Manager manager2 = new Manager("Manager2", 50000);
        
        Developer dev1 = new Developer("Dev1", 30000);
        Developer dev2 = new Developer("Dev2", 30000);
        Developer dev3 = new Developer("Dev3", 30000);
        
        manager1.addEmployee(dev1);
        manager1.addEmployee(dev2);
        manager2.addEmployee(dev3);
        
        ceo.addEmployee(manager1);
        ceo.addEmployee(manager2);
        
        ceo.showEmployeeDetails();
        System.out.println("Total salary: $" + ceo.getSalary());
    }
}
