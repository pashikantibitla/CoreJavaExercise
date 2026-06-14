# Chapter 20 — Serialization

> **Topics:** Introduction, ObjectOutputStream, ObjectInputStream, Serializable, transient, serialVersionUID, Custom Serialization, Externalizable, Inheritance, Interview FAQs

---

## Table of Contents

1. [Introduction to Serialization](#1-introduction-to-serialization)
2. [ObjectOutputStream and ObjectInputStream](#2-objectoutputstream-and-objectinputstream)
3. [Serializable Interface](#3-serializable-interface)
4. [The transient Keyword](#4-the-transient-keyword)
5. [serialVersionUID](#5-serialversionuid)
6. [Custom Serialization](#6-custom-serialization)
7. [Externalizable Interface](#7-externalizable-interface)
8. [Serialization with Inheritance](#8-serialization-with-inheritance)
9. [Interview Questions](#9-interview-questions)
10. [Quick Reference](#10-quick-reference)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Introduction to Serialization

### What is Serialization?

Serialization is the process of converting an object's state into a byte stream, so it can be saved to a file, sent over a network, or stored in a database. Deserialization is the reverse process.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SERIALIZATION OVERVIEW                              │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Serialization:                                               │   │
│  │  Object → Byte Stream → File / Network / Database           │   │
│  │                                                             │   │
│  │ Deserialization:                                             │   │
│  │  File / Network / Database → Byte Stream → Object           │   │
│  │                                                             │   │
│  │ Java Package: java.io                                        │   │
│  │ Key Classes: ObjectOutputStream, ObjectInputStream          │   │
│  │ Key Interfaces: Serializable, Externalizable               │   │
│  │                                                             │   │
│  │ Use Cases:                                                   │   │
│  │ 1. Save object state to a file (persistence)               │   │
│  │ 2. Send objects over network (RMI, sockets)                │   │
│  │ 3. Cache objects in memory or disk                          │   │
│  │ 4. Deep copy of objects                                     │   │
│  │ 5. Session management in web applications                   │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Serialization Requirements

```java
// To be serializable, a class must:
// 1. Implement java.io.Serializable interface (marker interface)
// 2. All non-transient fields must be serializable
// 3. If a field is not serializable, it must be marked transient

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    // All fields are serializable types
}

// Serializable is a marker interface (no methods)
// It signals to the JVM that this class can be serialized
```

---

## 2. ObjectOutputStream and ObjectInputStream

### ObjectOutputStream (Serialization)

```java
import java.io.*;

public class SerializeDemo {
    public static void main(String[] args) {
        Student student = new Student(101, "John");
        
        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("student.ser"))) {
            
            oos.writeObject(student);  // Serialize object to file
            System.out.println("Object serialized successfully");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// ObjectOutputStream methods:
// writeObject(Object obj)     — Serialize an object
// writeInt(int val)           — Write an int
// writeDouble(double val)     — Write a double
// writeUTF(String str)        — Write a String
// flush()                     — Flush the stream
// close()                     — Close the stream
```

### ObjectInputStream (Deserialization)

```java
import java.io.*;

public class DeserializeDemo {
    public static void main(String[] args) {
        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("student.ser"))) {
            
            Student student = (Student) ois.readObject();  // Deserialize
            System.out.println("Deserialized: " + student);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// ObjectInputStream methods:
// readObject()                — Deserialize an object
// readInt()                   — Read an int
// readDouble()                — Read a double
// readUTF()                   — Read a String
// close()                     — Close the stream
```

### Complete Example

```java
import java.io.*;

public class Student implements Serializable {
    private int id;
    private String name;
    
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
    
    public static void main(String[] args) {
        Student s1 = new Student(101, "Alice");
        String filename = "student.ser";
        
        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(s1);
            System.out.println("Serialized: " + s1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("Deserialized: " + s2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 3. Serializable Interface

### Marker Interface

```java
// Serializable is a marker interface — it has no methods
public interface Serializable {
    // Empty interface
}

// Purpose: Signals to the JVM that objects of this class
// can be serialized using ObjectOutputStream
```

### Serializable Rules

```java
import java.io.*;

// ✅ Correct: Class implements Serializable
public class Employee implements Serializable {
    private int id;
    private String name;
    private double salary;
}

// ❌ Incorrect: Class does not implement Serializable
public class Address {
    private String city;
    private String zip;
}

// If Address is used in Employee:
public class Employee implements Serializable {
    private int id;
    private String name;
    private Address address;  // ❌ ERROR: Address is not Serializable!
}

// ✅ Solution: Make Address serializable
public class Address implements Serializable {
    private String city;
    private String zip;
}

// ✅ Or mark non-serializable field as transient
public class Employee implements Serializable {
    private int id;
    private String name;
    private transient Address address;  // Will be skipped during serialization
}
```

### Object Graph Serialization

```java
import java.io.*;

public class Department implements Serializable {
    private String name;
    private Employee manager;  // Employee must also be Serializable
    
    // If Department is serialized, manager is also serialized
    // This is called "object graph serialization"
}

public class Employee implements Serializable {
    private String name;
    private Department dept;  // Circular reference is handled by JVM
}

// JVM handles circular references automatically
// Same object is not serialized twice
```

---

## 4. The transient Keyword

### What is transient?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TRANSIENT KEYWORD                                   │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Definition: transient prevents a field from being serialized │   │
│  │                                                             │   │
│  │ Use cases:                                                   │   │
│  │ 1. Sensitive data (passwords, keys, tokens)                │   │
│  │ 2. Derived/calculated fields                               │   │
│  │ 3. Non-serializable objects                                 │   │
│  │ 4. Temporary data (caches, session info)                    │   │
│  │ 5. Thread-related data                                       │   │
│  │                                                             │   │
│  │ During deserialization:                                     │   │
│  │ transient fields are set to default values                 │   │
│  │   - Object references → null                                │   │
│  │   - Primitives → 0, false, '\u0000'                         │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### transient Example

```java
import java.io.*;

public class User implements Serializable {
    private int id;
    private String username;
    private transient String password;      // Won't be serialized
    private transient int age;             // Won't be serialized
    private String email;
    
    public User(int id, String username, String password, int age, String email) {
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
    
    public static void main(String[] args) throws Exception {
        User user = new User(1, "john", "secret123", 25, "john@example.com");
        String file = "user.ser";
        
        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(user);
            System.out.println("Serialized: " + user);
        }
        
        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User restored = (User) ois.readObject();
            System.out.println("Deserialized: " + restored);
            // password will be null
            // age will be 0
        }
    }
}

// Output:
// Serialized: User{id=1, username='john', password='secret123', age=25, email='john@example.com'}
// Deserialized: User{id=1, username='john', password='null', age=0, email='john@example.com'}
```

---

## 5. serialVersionUID

### What is serialVersionUID?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SERIALVERSIONUID                                    │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Definition: A unique identifier for a Serializable class.  │   │
│  │                                                             │   │
│  │ Purpose: Ensures compatibility between serialized and       │   │
│  │ deserialized versions of a class.                          │   │
│  │                                                             │   │
│  │ If serialVersionUID does not match during deserialization: │   │
│  │   → InvalidClassException is thrown                        │   │
│  │                                                             │   │
│  │ Generated if not declared:                                │   │
│  │   Based on class structure (fields, methods, etc.)        │   │
│  │                                                             │   │
│  │ Best Practice: Always declare explicitly                  │   │
│  │   private static final long serialVersionUID = 1L;        │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### serialVersionUID Example

```java
import java.io.*;

public class Product implements Serializable {
    // Explicitly declare serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private double price;
    
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
    
    public static void main(String[] args) throws Exception {
        // Scenario 1: Same serialVersionUID → Compatible
        Product p1 = new Product(1, "Laptop", 999.99);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("product.ser"))) {
            oos.writeObject(p1);
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("product.ser"))) {
            Product p2 = (Product) ois.readObject();
            System.out.println(p2);
        }
    }
}

// If you change the class (add/remove fields) but keep the same serialVersionUID,
// deserialization will still work (with some limitations).
// If serialVersionUID is auto-generated and class changes, it will mismatch.
```

### Compatibility Rules

```java
import java.io.*;

public class VersionDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Compatible changes (same serialVersionUID):
    // ✅ Add new fields → new fields get default values
    // ✅ Remove fields → ignored during deserialization
    // ✅ Change access modifiers → allowed
    // ✅ Add classes to inheritance hierarchy → allowed
    
    // Incompatible changes (will cause InvalidClassException):
    // ❌ Change class name
    // ❌ Change field types
    // ❌ Change superclass (if superclass was not Serializable)
    // ❌ Remove Serializable interface
    // ❌ Change from enum to class (or vice versa)
}
```

---

## 6. Custom Serialization

### writeObject and readObject

```java
import java.io.*;

public class CustomSerialization implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private transient String password;  // sensitive data
    private transient int age;       // derived field
    
    // Custom serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  // Serialize normal fields
        
        // Custom handling for transient fields
        String encryptedPassword = encrypt(password);
        out.writeObject(encryptedPassword);  // Write encrypted password
        
        out.writeInt(age);  // Write age explicitly
    }
    
    // Custom deserialization
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();  // Deserialize normal fields
        
        // Custom handling for transient fields
        String encryptedPassword = (String) in.readObject();
        this.password = decrypt(encryptedPassword);  // Decrypt password
        
        this.age = in.readInt();  // Read age explicitly
    }
    
    private String encrypt(String password) {
        // Simple encryption (for demo only)
        StringBuilder sb = new StringBuilder();
        for (char c : password.toCharArray()) {
            sb.append((char)(c + 1));
        }
        return sb.toString();
    }
    
    private String decrypt(String encrypted) {
        StringBuilder sb = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            sb.append((char)(c - 1));
        }
        return sb.toString();
    }
    
    // ... constructor, getters, toString
}
```

### Custom Serialization Example

```java
import java.io.*;

public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String accountNumber;
    private transient double balance;  // Don't serialize raw balance
    
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    // Custom serialization: encrypt balance
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        // Store balance in encrypted form or as a string
        oos.writeDouble(balance * 100);  // Store as cents to avoid floating point issues
    }
    
    // Custom deserialization: restore balance
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.balance = ois.readDouble() / 100.0;
    }
    
    @Override
    public String toString() {
        return "BankAccount{accountNumber='" + accountNumber + "', balance=" + balance + "}";
    }
    
    public static void main(String[] args) throws Exception {
        BankAccount account = new BankAccount("123456", 1000.50);
        String file = "account.ser";
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(account);
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            BankAccount restored = (BankAccount) ois.readObject();
            System.out.println(restored);
        }
    }
}
```

### readResolve and writeReplace

```java
import java.io.*;

public class SingletonSerialization implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final SingletonSerialization INSTANCE = new SingletonSerialization();
    
    private SingletonSerialization() {}
    
    public static SingletonSerialization getInstance() {
        return INSTANCE;
    }
    
    // Called after readObject to ensure singleton
    private Object readResolve() {
        return INSTANCE;  // Return the singleton instance
    }
    
    // Called before writeObject to replace object
    private Object writeReplace() {
        return INSTANCE;  // Replace with singleton instance
    }
}

// readResolve ensures that after deserialization, you get the singleton
// instead of a new object.
```

---

## 7. Externalizable Interface

### Externalizable vs Serializable

```
┌─────────────────────────────────────────────────────────────────────┐
│                    EXTERNALIZABLE vs SERIALIZABLE                      │
│  ┌────────────────┬─────────────────────┬────────────────────────┐  │
│  │ Feature        │ Serializable        │ Externalizable         │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Interface      │ Marker (no methods) │ Two methods: writeExternal,│
│  │                │                     │ readExternal           │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Control        │ JVM handles all     │ Programmer controls   │  │
│  │                │ serialization     │ serialization logic    │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Performance    │ Slower (reflection) │ Faster (no reflection) │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Flexibility    │ Less flexible       │ More flexible          │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ transient      │ Supported           │ Not needed (manual)    │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ serialVersionUID│ Supported          │ Not needed             │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Inheritance    │ Default behavior    │ Must call parent       │  │
│  │                │                     │ writeExternal/readExternal│  │
│  └────────────────┴─────────────────────┴────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Externalizable Example

```java
import java.io.*;

public class ExternalizablePerson implements Externalizable {
    private String name;
    private int age;
    private transient String password;  // Manual control
    
    // Required: public no-arg constructor
    public ExternalizablePerson() {}
    
    public ExternalizablePerson(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
    
    // Custom serialization logic
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
        // Password is intentionally not written
    }
    
    // Custom deserialization logic
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.age = in.readInt();
        // Password is not read, remains null
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }
    
    public static void main(String[] args) throws Exception {
        ExternalizablePerson person = new ExternalizablePerson("Alice", 30, "secret");
        String file = "person_ext.ser";
        
        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(person);
        }
        
        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ExternalizablePerson restored = (ExternalizablePerson) ois.readObject();
            System.out.println(restored);
            // password will be null
        }
    }
}
```

---

## 8. Serialization with Inheritance

### Serializable Parent

```java
import java.io.*;

// Parent implements Serializable
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String species;
    
    public Animal(String species) {
        this.species = species;
    }
    
    public String getSpecies() { return species; }
}

// Child automatically serializable
public class Dog extends Animal {
    private static final long serialVersionUID = 1L;
    private String name;
    
    public Dog(String species, String name) {
        super(species);
        this.name = name;
    }
    
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "Dog{species='" + getSpecies() + "', name='" + name + "'}";
    }
}
```

### Non-Serializable Parent

```java
import java.io.*;

// Parent does NOT implement Serializable
public class Vehicle {
    private String type;
    
    public Vehicle(String type) {
        this.type = type;
    }
    
    public String getType() { return type; }
}

// Child implements Serializable
public class Car extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String model;
    
    public Car(String type, String model) {
        super(type);
        this.model = model;
    }
    
    // Must handle parent fields manually
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  // Serialize child fields
        out.writeUTF(getType());    // Serialize parent field manually
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();  // Deserialize child fields
        // Cannot call super constructor, must reconstruct parent manually
        // This is tricky — parent class must have a no-arg constructor
        // or you need to set fields via reflection (not recommended)
    }
    
    // Alternative: Use Externalizable
    // Or ensure parent has a no-arg constructor and use reflection
}

// Best practice: If parent is not Serializable, use Externalizable
// or redesign the class hierarchy.
```

### Inheritance Summary

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SERIALIZATION WITH INHERITANCE                      │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Scenario       │ Behavior                                       │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ Parent serializable│ Child automatically serializable            │  │
│  │                │ Parent fields are saved automatically          │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ Parent NOT serializable│ Child must implement Serializable     │  │
│  │                │ Child must manually serialize parent fields    │  │
│  │                │ Parent MUST have no-arg constructor           │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ Child serializable │ If parent is not, parent constructor     │  │
│  │ Parent NOT        │ is called during deserialization (no-arg)  │  │
│  │                │ Parent fields may be lost if not handled      │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 9. Interview Questions

### Q1. What is serialization in Java?

```java
// Serialization is the process of converting an object into a byte stream.
// Deserialization is the reverse process.

// Requirements:
// 1. Class must implement java.io.Serializable
// 2. All non-transient fields must be serializable

import java.io.*;

public class SerializationAnswer {
    public static void main(String[] args) throws Exception {
        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.ser"))) {
            oos.writeObject(new Student("John"));
        }
        
        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.ser"))) {
            Student s = (Student) ois.readObject();
        }
    }
}

class Student implements Serializable {
    private String name;
    public Student(String name) { this.name = name; }
}
```

### Q2. What is the difference between Serializable and Externalizable?

```java
import java.io.*;

// Serializable: Marker interface, JVM handles serialization
public class SerializableClass implements Serializable {
    private int id;
    private String name;
    // JVM serializes all non-transient fields automatically
}

// Externalizable: Programmer controls serialization
public class ExternalizableClass implements Externalizable {
    private int id;
    private String name;
    
    public ExternalizableClass() {}  // Required no-arg constructor
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = in.readInt();
        this.name = in.readUTF();
    }
}

// Key differences:
// Serializable: Automatic, reflection-based, slower
// Externalizable: Manual, faster, more control
```

### Q3. What is the purpose of the transient keyword?

```java
import java.io.*;

public class TransientDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private transient String password;  // Won't be serialized
    private transient int tempValue;  // Won't be serialized
    
    // During deserialization:
    // username → original value
    // password → null
    // tempValue → 0
}

// Use transient for:
// 1. Sensitive data (passwords, keys)
// 2. Derived/calculated fields
// 3. Non-serializable objects
// 4. Temporary/cached data
```

### Q4. What is serialVersionUID? Why is it important?

```java
import java.io.*;

public class SerialVersionUIDDemo implements Serializable {
    // Explicitly declared serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    
    // If you don't declare serialVersionUID:
    // JVM generates one based on class structure (fields, methods, etc.)
    
    // If class changes after serialization:
    // - With explicit serialVersionUID: May still be compatible
    // - Without explicit serialVersionUID: InvalidClassException
    
    // Best practice: Always declare serialVersionUID explicitly
}
```

### Q5. Can you serialize a static field?

```java
import java.io.*;

public class StaticFieldDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;           // ✅ Serialized
    private static int count;  // ❌ NOT serialized (static belongs to class, not object)
    
    // Static fields are not part of object state
    // They belong to the class, not to any instance
    // Therefore, they are not serialized
}
```

### Q6. What happens if a class implements Serializable but its superclass does not?

```java
import java.io.*;

// Superclass does NOT implement Serializable
class Parent {
    private String parentField;
    
    public Parent() {}  // Required: no-arg constructor
    public Parent(String parentField) { this.parentField = parentField; }
    public String getParentField() { return parentField; }
}

// Subclass implements Serializable
class Child extends Parent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String childField;
    
    public Child(String parentField, String childField) {
        super(parentField);
        this.childField = childField;
    }
    
    // During deserialization:
    // 1. Child fields are restored from stream
    // 2. Parent's no-arg constructor is called
    // 3. Parent fields may be lost unless custom logic handles it
    
    // Solution: Use writeObject/readObject or Externalizable
}

// Answer: Parent fields are not automatically saved.
// Parent's no-arg constructor is called during deserialization.
// Parent fields may need to be saved/restored manually.
```

### Q7. How can you customize serialization?

```java
import java.io.*;

public class CustomSerializationAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String data;
    private transient String sensitiveData;
    
    // Method 1: writeObject and readObject
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  // Serialize normal fields
        out.writeObject(encrypt(sensitiveData));  // Custom serialization
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();  // Deserialize normal fields
        this.sensitiveData = decrypt((String) in.readObject());  // Custom deserialization
    }
    
    // Method 2: writeReplace and readResolve
    private Object writeReplace() {
        // Return a different object to be serialized
        return this;
    }
    
    private Object readResolve() {
        // Return a different object after deserialization
        return this;
    }
    
    private String encrypt(String data) { return data; }
    private String decrypt(String data) { return data; }
}
```

### Q8. What is the difference between writeObject and readObject?

```java
// writeObject(ObjectOutputStream out):
// - Called during serialization
// - Allows custom serialization logic
// - Must call out.defaultWriteObject() first

// readObject(ObjectInputStream in):
// - Called during deserialization
// - Allows custom deserialization logic
// - Must call in.defaultReadObject() first

// Both methods are private and called by JVM via reflection.
// They must be declared exactly with this signature.
```

### Q9. How does Java handle object graphs during serialization?

```java
import java.io.*;

public class ObjectGraphDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private ObjectGraphDemo friend;  // Circular reference possible
    
    // Java handles object graphs automatically:
    // 1. Same object is not serialized twice
    // 2. Object references are preserved
    // 3. Circular references are handled correctly
    
    // JVM uses ObjectOutputStream's handle mechanism
    // Each object gets a unique handle
    // When the same object is referenced again, only the handle is written
}
```

### Q10. What exceptions can occur during serialization/deserialization?

```java
import java.io.*;

public class SerializationExceptions {
    public static void main(String[] args) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.ser"));
            oos.writeObject(new Object());  // ❌ NotSerializableException
        } catch (NotSerializableException e) {
            // Object does not implement Serializable
        } catch (IOException e) {
            // I/O error
        }
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"));
            Object obj = ois.readObject();
        } catch (InvalidClassException e) {
            // serialVersionUID mismatch or incompatible class
        } catch (ClassNotFoundException e) {
            // Class of serialized object not found
        } catch (StreamCorruptedException e) {
            // Stream header is incorrect
        } catch (IOException e) {
            // I/O error
        }
    }
}
```

---

## 10. Quick Reference

### Serialization Classes

```java
// Serializable — Marker interface
public interface Serializable {}

// Externalizable — With methods
public interface Externalizable extends Serializable {
    void writeExternal(ObjectOutput out) throws IOException;
    void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
}

// ObjectOutputStream — Serialization
public class ObjectOutputStream extends OutputStream implements ObjectOutput {
    void writeObject(Object obj);
    void defaultWriteObject();
    void writeInt(int val);
    void writeUTF(String str);
}

// ObjectInputStream — Deserialization
public class ObjectInputStream extends InputStream implements ObjectInput {
    Object readObject();
    void defaultReadObject();
    int readInt();
    String readUTF();
}
```

### Custom Serialization Methods

```java
private void writeObject(ObjectOutputStream out) throws IOException;
private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;
private Object writeReplace();
private Object readResolve();
```

---

## 11. Key Takeaways

1. **Serialization converts objects to byte streams** for persistence or network transfer.
2. **Serializable** is a marker interface — no methods to implement.
3. **ObjectOutputStream** serializes; **ObjectInputStream** deserializes.
4. **transient** fields are skipped during serialization.
5. **serialVersionUID** ensures version compatibility — always declare it.
6. **Custom serialization** via `writeObject`/`readObject` for special handling.
7. **Externalizable** gives full control over serialization logic.
8. **Non-serializable parent** requires manual handling or a no-arg constructor.
9. **Static fields** are not serialized (they belong to the class, not instance).
10. **Object graphs** are handled automatically — circular references are safe.

---

**Happy coding!**

*Serialization is the bridge between objects and bytes — master it for robust persistence and communication!*
