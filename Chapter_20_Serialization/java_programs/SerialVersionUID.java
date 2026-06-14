import java.io.*;

/**
 * SerialVersionUID.java
 * Demonstrates serialVersionUID usage and version compatibility.
 *
 * Topics:
 * - Explicit serialVersionUID declaration
 * - Compatible changes (add/remove fields)
 * - Incompatible changes (InvalidClassException)
 */
public class SerialVersionUID implements Serializable {
    // Explicit serialVersionUID ensures compatibility
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    // Try adding this field after serializing, then deserialize
    // private String department;

    public SerialVersionUID(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "'}";
    }

    public static void main(String[] args) {
        String filename = "product.ser";
        SerialVersionUID product = new SerialVersionUID(1, "Laptop");

        // Serialize
        System.out.println("=== Serialization ===");
        System.out.println("Original: " + product);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(product);
            System.out.println("Serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        System.out.println("\n=== Deserialization ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            SerialVersionUID restored = (SerialVersionUID) ois.readObject();
            System.out.println("Restored: " + restored);
            System.out.println("\nWith explicit serialVersionUID, you can add/remove fields");
            System.out.println("and still deserialize old objects (with default values).");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Demonstration of what happens without serialVersionUID
        System.out.println("\n=== Without serialVersionUID ===");
        System.out.println("If serialVersionUID is not declared, JVM auto-generates it");
        System.out.println("based on class structure. Any class change will cause");
        System.out.println("InvalidClassException during deserialization!");
    }
}
