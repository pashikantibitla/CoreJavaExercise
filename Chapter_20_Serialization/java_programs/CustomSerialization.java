import java.io.*;

/**
 * CustomSerialization.java
 * Demonstrates custom serialization with writeObject and readObject.
 *
 * Topics:
 * - writeObject(ObjectOutputStream)
 * - readObject(ObjectInputStream)
 * - Encrypting/decrypting sensitive data
 * - Custom post-condition restoration
 */
public class CustomSerialization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password;  // sensitive
    private transient int age;         // derived

    public CustomSerialization(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    // Custom serialization: encrypt password before saving
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  // Serialize normal fields
        String encrypted = encrypt(password);
        out.writeObject(encrypted);  // Write encrypted password
        out.writeInt(age);          // Write age explicitly
    }

    // Custom deserialization: decrypt password after reading
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();  // Deserialize normal fields
        String encrypted = (String) in.readObject();
        this.password = decrypt(encrypted);  // Decrypt password
        this.age = in.readInt();           // Read age explicitly
    }

    private String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append((char) (c + 1));
        }
        return sb.toString();
    }

    private String decrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append((char) (c - 1));
        }
        return sb.toString();
    }

    public String getPassword() { return password; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "CustomUser{username='" + username + "', password='" + password + "', age=" + age + "}";
    }

    public static void main(String[] args) {
        String filename = "custom_user.ser";
        CustomSerialization user = new CustomSerialization("alice", "secret123", 30);

        // Serialize
        System.out.println("=== Custom Serialization ===");
        System.out.println("Original: " + user);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(user);
            System.out.println("Serialized to " + filename);
            System.out.println("(Password was encrypted before saving)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        System.out.println("\n=== Custom Deserialization ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            CustomSerialization restored = (CustomSerialization) ois.readObject();
            System.out.println("Restored: " + restored);
            System.out.println("\nPassword restored: " + restored.getPassword());
            System.out.println("Age restored: " + restored.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
