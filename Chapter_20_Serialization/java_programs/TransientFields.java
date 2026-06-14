import java.io.*;

/**
 * TransientFields.java
 * Demonstrates the transient keyword in Java serialization.
 *
 * Topics:
 * - transient keyword usage
 * - Sensitive data exclusion
 * - Default values after deserialization
 */
public class TransientFields implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private transient String password;      // Sensitive: won't be serialized
    private transient int age;             // Derived: won't be serialized
    private String email;

    public TransientFields(int id, String username, String password, int age, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', " +
               "password='" + password + "', age=" + age + ", email='" + email + "'}";
    }

    public static void main(String[] args) {
        String filename = "user.ser";
        TransientFields user = new TransientFields(1, "john_doe", "secret123", 25, "john@example.com");

        // Serialization
        System.out.println("=== Serialization ===");
        System.out.println("Original: " + user);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(user);
            System.out.println("Serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        System.out.println("\n=== Deserialization ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            TransientFields restored = (TransientFields) ois.readObject();
            System.out.println("Restored: " + restored);
            System.out.println("\nNotice:");
            System.out.println("  - password is null (was transient)");
            System.out.println("  - age is 0 (was transient, default for int)");
            System.out.println("  - id, username, email are preserved");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
