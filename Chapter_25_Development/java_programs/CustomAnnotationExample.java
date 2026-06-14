import java.lang.annotation.*;
import java.lang.reflect.*;

// Step 1: Define the custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface MyCustomAnnotation {
    String value() default "";
    int priority() default 0;
    String description() default "No description";
    String[] tags() default {};
}

// Step 2: Apply the annotation to a class, methods, fields, and constructor
@MyCustomAnnotation(
    value = "EmployeeEntity",
    priority = 1,
    description = "Represents an employee in the system",
    tags = {"entity", "core", "hr"}
)
class Employee {
    
    @MyCustomAnnotation(
        value = "employeeId",
        priority = 2,
        description = "Unique identifier for the employee"
    )
    private int id;
    
    @MyCustomAnnotation(
        value = "employeeName",
        priority = 3,
        description = "Full name of the employee"
    )
    private String name;
    
    @MyCustomAnnotation(
        value = "Constructor",
        priority = 1,
        description = "Creates a new Employee instance"
    )
    public Employee(@MyCustomAnnotation(value = "idParam", priority = 2) int id,
                   @MyCustomAnnotation(value = "nameParam", priority = 3) String name) {
        this.id = id;
        this.name = name;
    }
    
    @MyCustomAnnotation(
        value = "getDetails",
        priority = 2,
        description = "Returns employee details as string",
        tags = {"getter", "display"}
    )
    public String getDetails() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
    
    @MyCustomAnnotation(
        value = "calculateSalary",
        priority = 5,
        description = "Calculates monthly salary"
    )
    public double calculateSalary(double baseSalary, double bonus) {
        return baseSalary + bonus;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

// Step 3: Demonstrate reading annotations using reflection
public class CustomAnnotationExample {
    
    public static void main(String[] args) {
        // Read class-level annotation
        Class<Employee> clazz = Employee.class;
        
        System.out.println("=== Class Annotations ===");
        if (clazz.isAnnotationPresent(MyCustomAnnotation.class)) {
            MyCustomAnnotation ann = clazz.getAnnotation(MyCustomAnnotation.class);
            printAnnotation(ann);
        }
        
        // Read field annotations
        System.out.println("\n=== Field Annotations ===");
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyCustomAnnotation.class)) {
                MyCustomAnnotation ann = field.getAnnotation(MyCustomAnnotation.class);
                System.out.println("Field: " + field.getName());
                printAnnotation(ann);
            }
        }
        
        // Read constructor annotations
        System.out.println("\n=== Constructor Annotations ===");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(MyCustomAnnotation.class)) {
                MyCustomAnnotation ann = constructor.getAnnotation(MyCustomAnnotation.class);
                System.out.println("Constructor: " + constructor.getName());
                printAnnotation(ann);
            }
            // Read parameter annotations
            for (Parameter param : constructor.getParameters()) {
                if (param.isAnnotationPresent(MyCustomAnnotation.class)) {
                    MyCustomAnnotation ann = param.getAnnotation(MyCustomAnnotation.class);
                    System.out.println("  Parameter: " + param.getName());
                    printAnnotation(ann);
                }
            }
        }
        
        // Read method annotations
        System.out.println("\n=== Method Annotations ===");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyCustomAnnotation.class)) {
                MyCustomAnnotation ann = method.getAnnotation(MyCustomAnnotation.class);
                System.out.println("Method: " + method.getName());
                printAnnotation(ann);
            }
        }
    }
    
    private static void printAnnotation(MyCustomAnnotation ann) {
        System.out.println("  Value: " + ann.value());
        System.out.println("  Priority: " + ann.priority());
        System.out.println("  Description: " + ann.description());
        System.out.println("  Tags: " + String.join(", ", ann.tags()));
        System.out.println();
    }
}
