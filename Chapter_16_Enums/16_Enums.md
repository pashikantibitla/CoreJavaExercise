# 16 — Enums

> **Topics:** Introduction, Enum vs Inheritance, Constructors, Methods, Abstract Methods, Interfaces, EnumSet, EnumMap, Interview FAQs

---

## Table of Contents

1. [Introduction to Enums](#1-introduction-to-enums)
2. [Enum vs Inheritance](#2-enum-vs-inheritance)
3. [Enum with Constructor](#3-enum-with-constructor)
4. [Enum with Methods](#4-enum-with-methods)
5. [Enum with Abstract Methods](#5-enum-with-abstract-methods)
6. [Enum Implementing Interfaces](#6-enum-implementing-interfaces)
7. [EnumSet and EnumMap](#7-enumset-and-enummap)
8. [Interview FAQs](#8-interview-faqs)
9. [Key Takeaways](#9-key-takeaways)

---

## 1. Introduction to Enums

### What is an Enum?

An **enum** (enumeration) is a special data type that enables a variable to be a set of predefined constants. Enums are used when we know all possible values at compile time.

```java
// Basic enum declaration
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
```

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUM CHARACTERISTICS                               │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Type-safe          │ Compiler ensures only valid constants    │  │
│  │ Fixed set          │ Cannot add new constants at runtime      │  │
│  │ Singleton          │ Each constant is a singleton instance    │  │
│  │ Comparable         │ Implements Comparable (natural order)    │  │
│  │ Serializable       │ Serializable by default                  │  │
│  │ Enum base class    │ Implicitly extends java.lang.Enum        │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Basic Enum Usage

```java
public class BasicEnumDemo {
    enum Color {
        RED, GREEN, BLUE
    }
    
    public static void main(String[] args) {
        Color c = Color.RED;
        System.out.println(c); // RED
        
        // Enum methods
        System.out.println(c.name());          // RED
        System.out.println(c.ordinal());       // 0
        System.out.println(Color.values().length); // 3
        
        // Iteration
        for (Color color : Color.values()) {
            System.out.println(color);
        }
    }
}
```

### Enum Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUM METHODS                                       │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Method             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ name()             │ Returns the name of the constant         │  │
│  │ ordinal()          │ Returns the position (0-based)           │  │
│  │ values()           │ Returns array of all constants           │  │
│  │ valueOf(String)    │ Returns the enum constant by name        │  │
│  │ compareTo(E)       │ Compares based on ordinal                │  │
│  │ toString()         │ Returns the name (can be overridden)       │  │
│  │ getDeclaringClass()│ Returns the Class object of enum         │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Enum vs Inheritance

### Enum Inheritance Rules

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUM INHERITANCE RULES                             │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Rule               │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Extends Enum       │ Implicitly extends java.lang.Enum        │  │
│  │ Cannot extend class│ Cannot extend any other class (final)    │  │
│  │ Can implement      │ Can implement any number of interfaces   │  │
│  │ Cannot be abstract │ Enum class cannot be abstract            │  │
│  │ Can have abstract  │ Individual constants can override        │  │
│  │   methods          │ abstract methods in the enum body        │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
// This is NOT allowed:
// enum Color extends SomeClass { }  // ❌ ERROR

// This is allowed:
enum Color implements Runnable, Comparable<Color> {
    RED, GREEN, BLUE;
    
    public void run() {
        System.out.println("Running " + this);
    }
}
```

### Why Enums Cannot Extend Classes

```java
// java.lang.Enum is declared as:
// public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable

// Since Java does not support multiple inheritance, an enum cannot extend
// another class because it already extends java.lang.Enum.
// This ensures consistency and type safety for enum constants.
```

---

## 3. Enum with Constructor

### Enum Constructors

Enums can have constructors to initialize constant-specific data. The constructor is implicitly **private**.

```java
public enum Status {
    PENDING(1, "Waiting for approval"),
    APPROVED(2, "Request approved"),
    REJECTED(3, "Request rejected");
    
    private final int code;
    private final String description;
    
    // Constructor is implicitly private
    Status(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() { return code; }
    public String getDescription() { return description; }
}
```

```java
public class EnumConstructorDemo {
    public static void main(String[] args) {
        Status s = Status.APPROVED;
        System.out.println(s.name() + " -> Code: " + s.getCode() + 
                           ", Description: " + s.getDescription());
    }
}
```

### Constructor Rules

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUM CONSTRUCTOR RULES                             │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Rule               │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Implicitly private │ Constructor access is always private     │  │
│  │ Cannot be public   │ ❌ public constructor is not allowed      │  │
│  │ Called per constant│ Constructor runs once for each constant  │  │
│  │ Overloaded allowed │ Multiple constructors are allowed        │  │
│  │ Default constructor│ If no constructor, a default is provided │  │
│  │ Constant-specific  │ Each constant can have its own args      │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6);
    
    private final double mass;   // in kilograms
    private final double radius; // in meters
    
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    public double surfaceGravity() {
        return 6.67300E-11 * mass / (radius * radius);
    }
    
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}
```

---

## 4. Enum with Methods

### Instance and Static Methods

Enums can have both instance methods and static methods.

```java
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;
    
    // Instance method
    public double apply(double x, double y) {
        switch (this) {
            case PLUS:   return x + y;
            case MINUS:  return x - y;
            case TIMES:  return x * y;
            case DIVIDE: return x / y;
            default: throw new AssertionError("Unknown operation: " + this);
        }
    }
    
    // Static method
    public static Operation fromString(String symbol) {
        switch (symbol) {
            case "+": return PLUS;
            case "-": return MINUS;
            case "*": return TIMES;
            case "/": return DIVIDE;
            default: throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }
    }
}
```

```java
public class EnumMethodDemo {
    public static void main(String[] args) {
        double result = Operation.PLUS.apply(5, 3);
        System.out.println("5 + 3 = " + result);
        
        Operation op = Operation.fromString("*");
        System.out.println("5 * 3 = " + op.apply(5, 3));
    }
}
```

### Overriding toString()

```java
public enum Color {
    RED {
        public String toString() {
            return "red color";
        }
    },
    GREEN {
        public String toString() {
            return "green color";
        }
    },
    BLUE {
        public String toString() {
            return "blue color";
        }
    };
}
```

---

## 5. Enum with Abstract Methods

### Constant-Specific Method Implementations

An enum can declare abstract methods, and each constant must provide its own implementation.

```java
public enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
    
    private final PayType payType;
    
    PayrollDay(PayType payType) {
        this.payType = payType;
    }
    
    PayrollDay() {
        this(PayType.WEEKDAY);
    }
    
    public double pay(double hoursWorked, double payRate) {
        return payType.pay(hoursWorked, payRate);
    }
    
    // Nested enum with abstract method
    private enum PayType {
        WEEKDAY {
            double overtimePay(double hours, double payRate) {
                return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            double overtimePay(double hours, double payRate) {
                return hours * payRate / 2;
            }
        };
        
        private static final int HOURS_PER_SHIFT = 8;
        
        abstract double overtimePay(double hours, double payRate);
        
        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }
}
```

```java
public class EnumAbstractMethodDemo {
    public static void main(String[] args) {
        double weekdayPay = PayrollDay.MONDAY.pay(10, 50);
        System.out.println("Monday pay: " + weekdayPay);
        
        double weekendPay = PayrollDay.SATURDAY.pay(10, 50);
        System.out.println("Saturday pay: " + weekendPay);
    }
}
```

---

## 6. Enum Implementing Interfaces

### Enum Can Implement Interfaces

Since enums cannot extend classes but can implement interfaces, they can be used polymorphically.

```java
// Interface definition
public interface Printable {
    void print();
}

public interface Command {
    void execute();
}
```

```java
public enum Action implements Printable, Command {
    START {
        public void print() { System.out.println("Starting..."); }
        public void execute() { System.out.println("Execute start"); }
    },
    STOP {
        public void print() { System.out.println("Stopping..."); }
        public void execute() { System.out.println("Execute stop"); }
    },
    PAUSE {
        public void print() { System.out.println("Pausing..."); }
        public void execute() { System.out.println("Execute pause"); }
    };
}
```

```java
public class EnumInterfaceDemo {
    public static void main(String[] args) {
        Printable p = Action.START;
        p.print(); // Starting...
        
        Command c = Action.STOP;
        c.execute(); // Execute stop
        
        // All enums can be treated as their interface type
        for (Action action : Action.values()) {
            action.print();
            action.execute();
        }
    }
}
```

---

## 7. EnumSet and EnumMap

### EnumSet

`EnumSet` is a specialized Set implementation for use with enum types. It is more efficient than `HashSet` for enums.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUMSET FEATURES                                   │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Type-safe          │ Only accepts enum type                   │  │
│  │ Compact            │ Backed by bit vector (very efficient)    │  │
│  │ Fast operations    │ O(1) for add, remove, contains           │  │
│  │ Null not allowed   │ NullPointerException on null insertion  │  │
│  │ Iteration order    │ Natural order of enum constants        │  │
│  │ Not synchronized   │ Not thread-safe by default             │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
import java.util.EnumSet;

public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumSetDemo {
    public static void main(String[] args) {
        // Empty enum set
        EnumSet<Day> none = EnumSet.noneOf(Day.class);
        
        // All days
        EnumSet<Day> all = EnumSet.allOf(Day.class);
        
        // Specific days
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        
        // Range
        EnumSet<Day> workdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        
        // Operations
        EnumSet<Day> complement = EnumSet.complementOf(workdays);
        
        System.out.println("All: " + all);
        System.out.println("Weekend: " + weekend);
        System.out.println("Workdays: " + workdays);
        System.out.println("Complement of workdays: " + complement);
        
        // Add/remove
        none.add(Day.MONDAY);
        System.out.println("After add: " + none);
    }
}
```

### EnumMap

`EnumMap` is a specialized Map implementation for use with enum keys. It is more efficient than `HashMap` for enum keys.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUMMAP FEATURES                                     │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Key type           │ Enum keys only                           │  │
│  │ Compact            │ Backed by array indexed by ordinal       │  │
│  │ Fast operations    │ O(1) for put, get, remove                │  │
│  │ Null keys not      │ NullPointerException on null key        │  │
│  │   allowed          │                                          │  │
│  │ Iteration order    │ Natural order of enum keys               │  │
│  │ Not synchronized   │ Not thread-safe by default             │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
import java.util.EnumMap;

public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumMapDemo {
    public static void main(String[] args) {
        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);
        
        schedule.put(Day.MONDAY, "Team Meeting");
        schedule.put(Day.TUESDAY, "Code Review");
        schedule.put(Day.WEDNESDAY, "Development");
        schedule.put(Day.THURSDAY, "Testing");
        schedule.put(Day.FRIDAY, "Deployment");
        
        System.out.println("Schedule: " + schedule);
        System.out.println("Monday task: " + schedule.get(Day.MONDAY));
        
        // Iteration in natural order
        for (Day day : schedule.keySet()) {
            System.out.println(day + " -> " + schedule.get(day));
        }
        
        // Check contains
        System.out.println("Contains Saturday? " + schedule.containsKey(Day.SATURDAY));
    }
}
```

---

## 8. Interview FAQs

### Q1. What is an enum in Java?

```java
// An enum is a special class that represents a fixed set of constants.
// It implicitly extends java.lang.Enum and is type-safe.

public enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}
```

### Q2. Can an enum extend a class?

```java
// ❌ NO — enums implicitly extend java.lang.Enum
// and Java does not support multiple inheritance.

// enum Color extends SomeClass { }  // ❌ COMPILE ERROR

// ✅ But enums CAN implement interfaces:
enum Color implements Runnable {
    RED, GREEN, BLUE;
    public void run() { }
}
```

### Q3. Can an enum have a constructor?

```java
// ✅ YES — constructors are implicitly private.
public enum Status {
    ACTIVE(1), INACTIVE(0);
    
    private final int code;
    
    Status(int code) {
        this.code = code;
    }
    
    public int getCode() { return code; }
}
```

### Q4. Can an enum have abstract methods?

```java
// ✅ YES — each constant must provide its own implementation.
public enum Operation {
    PLUS {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS {
        public double apply(double x, double y) { return x - y; }
    };
    
    public abstract double apply(double x, double y);
}
```

### Q5. What is the difference between EnumSet and HashSet?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUMSET vs HASHSET                                 │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ EnumSet            │ HashSet            │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Type-safe          │ Yes (enum only)    │ No (any object)    │  │
│  │ Performance        │ Faster (bit vector)│ Slower (hash table)│  │
│  │ Memory             │ Compact            │ More overhead      │  │
│  │ Null allowed       │ No                 │ Yes (one null)     │  │
│  │ Iteration order    │ Natural enum order │ Unordered          │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q6. What is the difference between EnumMap and HashMap?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ENUMMAP vs HASHMAP                                 │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ EnumMap            │ HashMap            │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Key type           │ Enum only          │ Any object         │  │
│  │ Performance        │ Faster (array)     │ Slower (hash)      │  │
│  │ Memory             │ Compact            │ More overhead      │  │
│  │ Null keys          │ Not allowed        │ Not allowed        │  │
│  │ Iteration order    │ Natural enum order │ Unordered          │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q7. Can you compare enums using `==` and `equals()`?

```java
// Both == and equals() work for enums because each constant is a singleton.
Day d1 = Day.MONDAY;
Day d2 = Day.MONDAY;

System.out.println(d1 == d2);      // true
System.out.println(d1.equals(d2)); // true

// Recommendation: use == for enums (null-safe, faster)
```

### Q8. How do you convert a String to an enum?

```java
Day day = Day.valueOf("MONDAY"); // Returns Day.MONDAY

// Case-sensitive: Day.valueOf("monday") throws IllegalArgumentException

// Safe conversion with try-catch:
try {
    Day d = Day.valueOf("SUNDAY");
} catch (IllegalArgumentException e) {
    System.out.println("Invalid day name");
}
```

### Q9. Can enums be used in switch statements?

```java
// ✅ YES — enums are ideal for switch statements.
public void process(Day day) {
    switch (day) {
        case MONDAY:
        case TUESDAY:
        case WEDNESDAY:
        case THURSDAY:
        case FRIDAY:
            System.out.println("Weekday");
            break;
        case SATURDAY:
        case SUNDAY:
            System.out.println("Weekend");
            break;
    }
}
```

### Q10. Are enums thread-safe?

```java
// ✅ YES — enum constants are singletons and are created during class loading.
// They are inherently thread-safe and can be used for singleton pattern.

public enum Singleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Singleton action");
    }
}
```

---

## 9. Key Takeaways

1. **Enum** is a type-safe class representing a fixed set of constants.
2. Enums **implicitly extend `java.lang.Enum`** and cannot extend other classes.
3. Enums can have **constructors** (implicitly private), **methods**, and **abstract methods**.
4. Enums can **implement interfaces** for polymorphic behavior.
5. **EnumSet** is a high-performance Set for enums (backed by bit vectors).
6. **EnumMap** is a high-performance Map with enum keys (backed by arrays).
7. Enums are **singleton**, **comparable**, **serializable**, and **thread-safe**.
8. Use `==` for enum comparison (null-safe and faster than `equals()`).
9. Enums are perfect for **switch statements**, **state machines**, and **singleton patterns**.

---

**Happy coding! 🚀**
