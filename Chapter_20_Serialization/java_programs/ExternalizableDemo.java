import java.io.*;

/**
 * ExternalizableDemo.java
 * Demonstrates the Externalizable interface for full serialization control.
 *
 * Topics:
 * - Externalizable vs Serializable
 * - writeExternal() and readExternal()
 * - Required no-arg constructor
 * - Performance benefits
 */
public class ExternalizableDemo implements Externalizable {
    private String name;
    private int age;
    private transient String password;  // Manual control

    // Required: public no-arg constructor
    public ExternalizableDemo() {
        System.out.println("No-arg constructor called (required for Externalizable)");
    }

    public ExternalizableDemo(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal() called");
        out.writeUTF(name);
        out.writeInt(age);
        // Password is intentionally NOT written
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal() called");
        this.name = in.readUTF();
        this.age = in.readInt();
        // Password is not read, remains null
    }

    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "ExternalizablePerson{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }

    public static void main(String[] args) {
        String filename = "externalizable_person.ser";
        ExternalizableDemo person = new ExternalizableDemo("Bob", 25, "topsecret");

        // Serialize
        System.out.println("=== Externalizable Serialization ===");
        System.out.println("Original: " + person);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(person);
            System.out.println("\nSerialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        System.out.println("\n=== Externalizable Deserialization ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ExternalizableDemo restored = (ExternalizableDemo) ois.readObject();
            System.out.println("\nRestored: " + restored);
            System.out.println("\nPassword after deserialization: " + restored.getPassword());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
