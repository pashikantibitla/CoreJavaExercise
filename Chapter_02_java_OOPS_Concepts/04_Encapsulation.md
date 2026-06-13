# 04 — Encapsulation

> **Video:** 13, 15–16  
> **Topics:** Data Hiding, Encapsulation, Tightly Encapsulated Class

---

## Table of Contents

1. [Data Hiding](#1-data-hiding)
2. [Encapsulation](#2-encapsulation)
3. [Tightly Encapsulated Class](#3-tightly-encapsulated-class)
4. [Interview Questions](#4-interview-questions)
5. [Quick Reference](#5-quick-reference)
6. [Key Takeaways](#6-key-takeaways)

---

## 1. Data Hiding

### What is Data Hiding?

Data hiding is the technique of **restricting direct access** to an object's internal data. It is achieved by declaring fields as `private` and providing controlled access through public methods (getters/setters).

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    DATA HIDING CONCEPT                                     │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  WITHOUT Data Hiding              │  WITH Data Hiding              │    │
│  ├─────────────────────────────────────────────────────────────────┤    │
│  │  ┌─────────────────────┐          │  ┌─────────────────────┐        │    │
│  │  │  public class A {   │          │  │  public class A {   │        │    │
│  │  │    public int age; │          │  │    private int age; │        │    │
│  │  │  }                  │          │  │                     │        │    │
│  │  │                     │          │  │    public int getAge()│       │    │
│  │  │  // Direct access:  │          │  │    { return age; }  │        │    │
│  │  │  a.age = -100;      │          │  │                     │        │    │
│  │  │  // No validation!  │          │  │    public void setAge(int age)│    │
│  │  │                     │          │  │    {                │        │    │
│  │  │                     │          │  │      if (age > 0)   │        │    │
│  │  │                     │          │  │        this.age = age;│       │    │
│  │  │                     │          │  │    }                  │        │    │
│  │  └─────────────────────┘          │  └─────────────────────┘        │    │
│  │                                     │                                 │    │
│  │  a.age = -100;  // ❌ Valid but wrong│  a.setAge(-100); // ❌ Blocked    │    │
│  │  a.age = 999;   // ❌ Valid but wrong│  a.setAge(25);   // ✅ Valid      │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  Data hiding prevents direct access and allows validation logic.         │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    private String pin;
    
    // Constructor
    public BankAccount(String accountNumber, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }
    
    // Getter with validation
    public double getBalance() {
        return balance;
    }
    
    // Setter with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid amount");
        }
    }
    
    public void withdraw(double amount, String enteredPin) {
        if (this.pin.equals(enteredPin) && amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal");
        }
    }
    
    // No setter for accountNumber — immutable
    public String getAccountNumber() {
        return accountNumber;
    }
}
```

---

## 2. Encapsulation

### What is Encapsulation?

Encapsulation is the **bundling of data and methods** that operate on that data within a single unit (class). It is a broader concept than data hiding — it includes:
- **Data hiding** (private fields)
- **Getter methods** (read access)
- **Setter methods** (write access with validation)

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    ENCAPSULATION VISUAL                                   │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                        Encapsulated Class                          │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │  Private Fields (data)                                   │  │    │
│  │  │  • name                                                   │  │    │
│  │  │  • age                                                    │  │    │
│  │  │  • salary                                                 │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  │                          │                                    │    │
│  │                          │ Access through                      │    │
│  │                          ▼                                    │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │  Public Methods (getters/setters)                        │  │    │
│  │  │  • getName()     ← read                                  │  │    │
│  │  │  • setAge()      ← write with validation                │  │    │
│  │  │  • getSalary()   ← read-only                            │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  │                          │                                    │    │
│  │                          │ Controlled access                   │    │
│  │                          ▼                                    │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │  External World                                         │  │    │
│  │  │  • Can only access through public methods               │  │    │
│  │  │  • Cannot access private fields directly                │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
public class Student {
    // Private fields — data hiding
    private int id;
    private String name;
    private double marks;
    private String grade;
    
    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public String getGrade() {
        return grade;
    }
    
    // Setter with validation
    public void setMarks(double marks) {
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
            this.grade = calculateGrade(marks);
        } else {
            System.out.println("Invalid marks");
        }
    }
    
    // No setter for id — immutable
    // No setter for grade — calculated automatically
    
    // Private helper method
    private String calculateGrade(double marks) {
        if (marks >= 90) return "A";
        else if (marks >= 80) return "B";
        else if (marks >= 70) return "C";
        else if (marks >= 60) return "D";
        else return "F";
    }
}
```

---

## 3. Tightly Encapsulated Class

### What is a Tightly Encapsulated Class?

A class is **tightly encapsulated** if **all** its fields are `private`. Whether or not there are getters/setters is irrelevant — the key is that all fields are private.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    TIGHTLY ENCAPSULATED CLASS                            │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  Tightly Encapsulated              │  Not Tightly Encapsulated    │    │
│  ├─────────────────────────────────────────────────────────────────┤    │
│  │  ┌─────────────────────┐          │  ┌─────────────────────┐    │    │
│  │  │  class Tight {      │          │  │  class Loose {      │    │    │
│  │  │    private int a;   │          │  │    private int a;   │    │    │
│  │  │    private int b;   │          │  │    public int b;    │    │    │
│  │  │  }                  │          │  │  }                  │    │    │
│  │  │                     │          │  │                     │    │    │
│  │  │  ✅ All fields private│          │  │  ❌ Field b is public │    │    │
│  │  │  ✅ Tightly encapsulated│          │  │  ❌ Not tightly encapsulated│    │
│  │  └─────────────────────┘          │  └─────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  Rule: If any field is non-private, class is NOT tightly encapsulated.  │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
// Tightly encapsulated class
public class Employee {
    private int id;           // private
    private String name;      // private
    private double salary;    // private
    
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    // No setter for id and name — immutable
}

// NOT tightly encapsulated
public class Person {
    private int id;        // private
    public String name;    // public — breaks encapsulation!
}
```

---

## 4. Interview Questions

### Q1. What is the difference between encapsulation and data hiding?

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Feature            │ Data Hiding        │ Encapsulation      │
├────────────────────┼────────────────────┼────────────────────┤
│ Definition         │ Restricting access │ Bundling data +    │
│                    │ to data            │ methods            │
│ Focus              │ Security           │ Organization +     │
│                    │                    │ Security           │
│ Implementation     │ private fields     │ private fields +   │
│                    │                    │ getters/setters    │
│ Scope              │ Narrower concept   │ Broader concept    │
│                    │                    │ (includes data     │
│                    │                    │ hiding)            │
│ Example            │ private int x;    │ class with private │
│                    │                    │ fields + methods   │
└────────────────────┴────────────────────┴────────────────────┘
```

---

### Q2. Can a class be encapsulated without getters and setters?

```java
// ✅ YES — encapsulation is about bundling data + methods
// Getters/setters are optional

public class Logger {
    private String logFile;    // Private field
    
    // No getters or setters
    // But methods that USE the data
    
    public void writeLog(String message) {
        // Write to logFile
        System.out.println("Writing to " + logFile + ": " + message);
    }
    
    public void rotateLog() {
        // Rotate log file
    }
}

// This class is still encapsulated because:
// 1. Data is bundled with methods
// 2. Internal representation is hidden
// 3. Access is through public methods
```

---

### Q3. What is the advantage of encapsulation?

```java
// 1. Data Validation
public void setAge(int age) {
    if (age > 0 && age < 150) {
        this.age = age;
    }
}

// 2. Flexibility to change implementation
// Can change internal data structure without affecting external code

// 3. Security
// Sensitive data cannot be accessed directly

// 4. Read-only or Write-only fields
public class Person {
    private final String id;  // Read-only (no setter)
    private String password;  // Write-only (no getter, only setter)
    
    public String getId() { return id; }
    public void setPassword(String password) { this.password = password; }
}

// 5. Lazy initialization
public class Database {
    private Connection connection;  // Created only when needed
    
    public Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }
}
```

---

### Q4. Can a class be tightly encapsulated without getters?

```java
// ✅ YES — tightly encapsulated means ALL fields are private
// Getters/setters are optional

public class Config {
    private String host;     // private
    private int port;        // private
    
    // No getters/setters
    // But fields are still private
    
    public void connect() {
        // Uses host and port internally
    }
}

// This class IS tightly encapsulated because:
// All fields are private
// Whether getters/setters exist does not matter
```

---

## 5. Quick Reference

### Encapsulation Checklist

```
✅ All fields private
✅ Public getters for read access
✅ Public setters for write access (with validation)
✅ Constructor to initialize fields
✅ No direct field access from outside
```

### Getter/Setter Naming

```
Field: private String name;
Getter: public String getName()
Setter: public void setName(String name)

Field: private boolean active;
Getter: public boolean isActive()
Setter: public void setActive(boolean active)
```

---

## 6. Key Takeaways

1. **Data hiding** = restricting access to internal data using `private`.
2. **Encapsulation** = bundling data + methods + controlling access.
3. **Tightly encapsulated class** = ALL fields are `private`.
4. **Getters and setters** provide controlled access to private fields.
5. **Validation logic** goes in setters — prevents invalid data.
6. **Immutable fields** have no setter — can only be set in constructor.
7. **Encapsulation = security + flexibility + maintainability**.
8. **JavaBean** = class with private fields, no-arg constructor, getters/setters.
9. **POJO** = Plain Old Java Object — simple class with fields and getters/setters.
10. **Data hiding is part of encapsulation** — encapsulation is broader.

---

**Happy coding! 🚀**

*Encapsulation is the shield of your data — keep it private, expose it wisely.*
