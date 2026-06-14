import java.io.*;

/**
 * SerializationInterview.java
 * Interview-level problems and demonstrations for Java serialization.
 *
 * Covers:
 * - Serializable vs Externalizable
 * - Static fields and serialization
 * - Inheritance with non-serializable parent
 * - Singleton serialization with readResolve
 * - Object graph serialization
 */
public class SerializationInterview {
    public static void main(String[] args) {
        System.out.println("=== Serialization Interview Problems ===\n");

        problem1StaticFields();
        problem2SingletonSerialization();
        problem3InheritanceSerialization();
        problem4ObjectGraph();
    }

    /**
     * Problem 1: Static fields are NOT serialized
     */
    private static void problem1StaticFields() {
        System.out.println("--- Problem 1: Static Fields Not Serialized ---");
        String filename = "static_demo.ser";

        StaticDemo obj1 = new StaticDemo(1);
        StaticDemo.staticValue = 100;
        System.out.println("Before serialization: " + obj1);
        System.out.println("Static value before: " + StaticDemo.staticValue);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Change static value before deserialization
        StaticDemo.staticValue = 200;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            StaticDemo obj2 = (StaticDemo) ois.readObject();
            System.out.println("After deserialization: " + obj2);
            System.out.println("Static value after: " + StaticDemo.staticValue);
            System.out.println("(Static value is 200, not 100, because static fields are NOT serialized)\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Problem 2: Singleton serialization with readResolve
     */
    private static void problem2SingletonSerialization() {
        System.out.println("--- Problem 2: Singleton Serialization ---");
        String filename = "singleton.ser";

        Singleton original = Singleton.getInstance();
        System.out.println("Original singleton: " + original);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(original);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Singleton restored = (Singleton) ois.readObject();
            System.out.println("Restored singleton: " + restored);
            System.out.println("Are they the same object? " + (original == restored));
            System.out.println("(readResolve ensures singleton property is preserved)\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Problem 3: Inheritance with non-serializable parent
     */
    private static void problem3InheritanceSerialization() {
        System.out.println("--- Problem 3: Inheritance Serialization ---");
        String filename = "inheritance.ser";

        Child child = new Child("Vehicle", "Tesla", "Model 3");
        System.out.println("Original: " + child);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(child);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Child restored = (Child) ois.readObject();
            System.out.println("Restored: " + restored);
            System.out.println("(Non-serializable parent fields may be lost or have default values)\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Problem 4: Object graph serialization
     */
    private static void problem4ObjectGraph() {
        System.out.println("--- Problem 4: Object Graph Serialization ---");
        String filename = "graph.ser";

        Person p1 = new Person("Alice");
        Person p2 = new Person("Bob");
        p1.friend = p2;
        p2.friend = p1;  // Circular reference!

        System.out.println("Alice's friend: " + p1.friend.name);
        System.out.println("Bob's friend: " + p2.friend.name);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(p1);
            System.out.println("\nSerialized with circular reference");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Person restored = (Person) ois.readObject();
            System.out.println("Restored Alice's friend: " + restored.friend.name);
            System.out.println("Restored Bob's friend: " + restored.friend.friend.name);
            System.out.println("(Circular reference preserved: " + (restored.friend.friend == restored) + ")\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// Supporting classes for Problem 1
class StaticDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static int staticValue;
    private int instanceValue;

    public StaticDemo(int instanceValue) {
        this.instanceValue = instanceValue;
    }

    @Override
    public String toString() {
        return "StaticDemo{instanceValue=" + instanceValue + ", staticValue=" + staticValue + "}";
    }
}

// Supporting class for Problem 2
class Singleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }

    // Called after readObject to ensure singleton
    private Object readResolve() {
        return INSTANCE;
    }
}

// Supporting classes for Problem 3
class Parent {
    String type;
    public Parent() {}  // Required no-arg constructor
    public Parent(String type) { this.type = type; }
}

class Child extends Parent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String brand;
    private String model;

    public Child(String type, String brand, String model) {
        super(type);
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Child{type='" + type + "', brand='" + brand + "', model='" + model + "'}";
    }
}

// Supporting class for Problem 4
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    Person friend;

    public Person(String name) {
        this.name = name;
    }
}
