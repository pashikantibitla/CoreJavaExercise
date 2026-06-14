import java.util.Arrays;

public class ArraysSortDemo {
    public static void main(String[] args) {
        int[] numbers = {30, 10, 50, 20, 40};
        Arrays.sort(numbers);
        System.out.println("Sorted ints: " + Arrays.toString(numbers));
        
        String[] words = {"banana", "apple", "cherry"};
        Arrays.sort(words);
        System.out.println("Sorted strings: " + Arrays.toString(words));
        
        Employee[] employees = {
            new Employee("Bob", 50000),
            new Employee("Alice", 60000),
            new Employee("Charlie", 45000)
        };
        
        Arrays.sort(employees);
        System.out.println("Sorted by salary: " + Arrays.toString(employees));
        
        int[] partial = {5, 2, 8, 1, 9, 3};
        Arrays.sort(partial, 1, 4);
        System.out.println("Partial sort (1 to 4): " + Arrays.toString(partial));
    }
}

class Employee implements Comparable<Employee> {
    private String name;
    private int salary;
    
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public int compareTo(Employee other) {
        return this.salary - other.salary;
    }
    
    @Override
    public String toString() {
        return name + "($" + salary + ")";
    }
}
