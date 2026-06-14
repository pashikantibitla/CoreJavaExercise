package java_programs;

/**
 * EmployeePOJO.java
 * 
 * Purpose: Demonstrates a basic POJO class with all standard methods.
 *          This follows JavaBean conventions required for JSON serialization.
 * 
 * Methods included:
 * - Private fields (data hiding)
 * - Public no-arg constructor (required for JSON deserialization)
 * - Parameterized constructor (convenience)
 * - Getter methods (access field values)
 * - Setter methods (modify field values)
 * - toString() (human-readable representation)
 * - equals() (logical equality comparison)
 * - hashCode() (consistent hash for collections)
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class EmployeePOJO implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Private fields — data hiding
    private int id;
    private String name;
    private double salary;
    private String department;
    private boolean active;
    
    /**
     * No-argument constructor.
     * REQUIRED for JSON deserialization by Jackson and Gson.
     * Frameworks use reflection to instantiate objects via this constructor.
     */
    public EmployeePOJO() {
    }
    
    /**
     * Parameterized constructor for convenient object creation.
     * 
     * @param id         the employee ID
     * @param name       the employee name
     * @param salary     the employee salary
     * @param department the department name
     * @param active     whether the employee is active
     */
    public EmployeePOJO(int id, String name, double salary, String department, boolean active) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.active = active;
    }
    
    // Getter methods — read field values
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public boolean isActive() {
        return active;
    }
    
    // Setter methods — modify field values
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * toString() provides a human-readable representation of the object.
     * Essential for debugging and logging in API tests.
     */
    @Override
    public String toString() {
        return "EmployeePOJO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", active=" + active +
                '}';
    }
    
    /**
     * equals() checks logical equality (same data) rather than identity (same memory address).
     * Required for correct comparison in test assertions and collections.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePOJO that = (EmployeePOJO) o;
        return id == that.id &&
                Double.compare(that.salary, salary) == 0 &&
                active == that.active &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(department, that.department);
    }
    
    /**
     * hashCode() returns a consistent hash code for objects that are equal.
     * Required for correct behavior in HashMap, HashSet, and other hash-based collections.
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, salary, department, active);
    }
    
    /**
     * Main method demonstrating POJO creation and usage.
     */
    public static void main(String[] args) {
        // Create object using parameterized constructor
        EmployeePOJO emp1 = new EmployeePOJO(1, "Alice", 75000.0, "Engineering", true);
        
        // Create object using no-arg constructor and setters
        EmployeePOJO emp2 = new EmployeePOJO();
        emp2.setId(2);
        emp2.setName("Bob");
        emp2.setSalary(65000.0);
        emp2.setDepartment("Sales");
        emp2.setActive(false);
        
        // Demonstrate toString()
        System.out.println("Employee 1: " + emp1);
        System.out.println("Employee 2: " + emp2);
        
        // Demonstrate equals()
        EmployeePOJO emp3 = new EmployeePOJO(1, "Alice", 75000.0, "Engineering", true);
        System.out.println("emp1 equals emp3: " + emp1.equals(emp3)); // true
        System.out.println("emp1 equals emp2: " + emp1.equals(emp2)); // false
        
        // Demonstrate hashCode()
        System.out.println("emp1 hashCode: " + emp1.hashCode());
        System.out.println("emp3 hashCode: " + emp3.hashCode());
        
        // Demonstrate use in HashSet
        java.util.Set<EmployeePOJO> set = new java.util.HashSet<>();
        set.add(emp1);
        set.add(emp3); // duplicate, won't be added
        System.out.println("Set size: " + set.size()); // 1
    }
}
