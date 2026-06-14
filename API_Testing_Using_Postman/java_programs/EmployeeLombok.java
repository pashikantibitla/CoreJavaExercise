package java_programs;

/**
 * EmployeeLombok.java
 * 
 * Purpose: Demonstrates a POJO class using Lombok annotations.
 *          Lombok eliminates boilerplate code by generating it at compile time.
 * 
 * Required Maven dependency:
 * <dependency>
 *     <groupId>org.projectlombok</groupId>
 *     <artifactId>lombok</artifactId>
 *     <version>1.18.28</version>
 *     <scope>provided</scope>
 * </dependency>
 * 
 * Annotations used:
 * - @Data: Generates getters, setters, toString, equals, hashCode
 * - @NoArgsConstructor: Generates public no-arg constructor
 * - @AllArgsConstructor: Generates constructor with all fields
 * - @Builder: Generates builder pattern for constructing objects
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */

// Lombok annotations — uncomment after adding Lombok to classpath
// import lombok.*;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
public class EmployeeLombok {
    
    // Without Lombok, we must write all boilerplate manually.
    // With Lombok, the annotations above generate everything at compile time.
    
    private int id;
    private String name;
    private double salary;
    private String department;
    private boolean active;
    
    // Manual boilerplate (only needed if Lombok is not on classpath)
    // In a real project with Lombok, delete everything below this line.
    
    public EmployeeLombok() {
    }
    
    public EmployeeLombok(int id, String name, double salary, String department, boolean active) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.active = active;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    @Override
    public String toString() {
        return "EmployeeLombok{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", active=" + active +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeLombok that = (EmployeeLombok) o;
        return id == that.id && Double.compare(that.salary, salary) == 0 && active == that.active && java.util.Objects.equals(name, that.name) && java.util.Objects.equals(department, that.department);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, salary, department, active);
    }
    
    // Simulated @Builder pattern
    public static EmployeeLombokBuilder builder() {
        return new EmployeeLombokBuilder();
    }
    
    public static class EmployeeLombokBuilder {
        private int id;
        private String name;
        private double salary;
        private String department;
        private boolean active;
        
        public EmployeeLombokBuilder id(int id) {
            this.id = id;
            return this;
        }
        
        public EmployeeLombokBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public EmployeeLombokBuilder salary(double salary) {
            this.salary = salary;
            return this;
        }
        
        public EmployeeLombokBuilder department(String department) {
            this.department = department;
            return this;
        }
        
        public EmployeeLombokBuilder active(boolean active) {
            this.active = active;
            return this;
        }
        
        public EmployeeLombok build() {
            return new EmployeeLombok(id, name, salary, department, active);
        }
    }
    
    /**
     * Main method demonstrating Lombok-style POJO usage.
     */
    public static void main(String[] args) {
        // Create object using builder pattern (simulating Lombok @Builder)
        EmployeeLombok emp1 = EmployeeLombok.builder()
                .id(1)
                .name("Alice")
                .salary(75000.0)
                .department("Engineering")
                .active(true)
                .build();
        
        // Create object using all-args constructor
        EmployeeLombok emp2 = new EmployeeLombok(2, "Bob", 65000.0, "Sales", false);
        
        // Demonstrate toString()
        System.out.println("Employee 1: " + emp1);
        System.out.println("Employee 2: " + emp2);
        
        // Demonstrate equals()
        EmployeeLombok emp3 = EmployeeLombok.builder()
                .id(1)
                .name("Alice")
                .salary(75000.0)
                .department("Engineering")
                .active(true)
                .build();
        System.out.println("emp1 equals emp3: " + emp1.equals(emp3)); // true
        
        // Demonstrate use in HashSet
        java.util.Set<EmployeeLombok> set = new java.util.HashSet<>();
        set.add(emp1);
        set.add(emp3);
        System.out.println("Set size: " + set.size()); // 1
        
        System.out.println("\n--- With Lombok, the entire class would be just 5 lines ---");
        System.out.println("@Data");
        System.out.println("@NoArgsConstructor");
        System.out.println("@AllArgsConstructor");
        System.out.println("@Builder");
        System.out.println("public class EmployeeLombok { ... }");
    }
}
