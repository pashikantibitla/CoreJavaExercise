import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private transient String password;
    
    public Employee(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', password='" + password + "'}";
    }
}

public class ObjectSerializationDemo {
    public static void main(String[] args) {
        String file = "employee.ser";
        
        // Serialize
        Employee emp = new Employee(101, "Alice", "secret123");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(emp);
            System.out.println("Serialized: " + emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Employee restored = (Employee) ois.readObject();
            System.out.println("Deserialized: " + restored);
            // Note: password is null because it is transient
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
