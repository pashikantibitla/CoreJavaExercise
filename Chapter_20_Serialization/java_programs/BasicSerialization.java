import java.io.*;

/**
 * BasicSerialization.java
 * Demonstrates basic serialization and deserialization in Java.
 *
 * Topics:
 * - Serializable interface
 * - ObjectOutputStream.writeObject()
 * - ObjectInputStream.readObject()
 * - File-based persistence
 */
public class BasicSerialization implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double salary;

    public BasicSerialization(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }

    public static void main(String[] args) {
        String filename = "employee.ser";
        BasicSerialization emp = new BasicSerialization(101, "Alice", 75000.0);

        // Serialization
        System.out.println("=== Serialization ===");
        System.out.println("Original object: " + emp);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(emp);
            System.out.println("Object serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        System.out.println("\n=== Deserialization ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            BasicSerialization restored = (BasicSerialization) ois.readObject();
            System.out.println("Restored object: " + restored);
            System.out.println("Are they equal? " + emp.toString().equals(restored.toString()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
