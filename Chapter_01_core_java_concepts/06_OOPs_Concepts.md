# 06 — OOPs Concepts

> **Videos:** 51–67  
> **Topics:** Introduction, Data Hiding, Inheritance (IS-A, HAS-A), Overloading, Overriding, Coupling, Type Casting, Static Control Flow, Instance Control Flow, Constructors, Singleton Class

---

## Table of Contents

1. [Introduction and Data Hiding](#1-introduction-and-data-hiding)
2. [Inheritance (IS-A Relationship)](#2-inheritance-is-a-relationship)
3. [Inheritance (HAS-A Relationship)](#3-inheritance-has-a-relationship)
4. [Method Overloading](#4-method-overloading)
5. [Method Overriding](#5-method-overriding)
6. [Coupling](#6-coupling)
7. [Type Casting](#7-type-casting)
8. [Static Control Flow](#8-static-control-flow)
9. [Instance Control Flow](#9-instance-control-flow)
10. [Constructors](#10-constructors)
11. [Singleton Class](#11-singleton-class)
12. [Interview Questions](#12-interview-questions)
13. [Key Takeaways](#13-key-takeaways)

---

## 1. Introduction and Data Hiding

### What is OOP?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    OOP (Object Oriented Programming)                  │
│                                                                  │
│  4 Pillars of OOP:                                               │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Abstraction     │ Hide implementation details             │   │
│  │ 2. Encapsulation  │ Bind data + methods together            │   │
│  │ 3. Inheritance    │ Reuse code (IS-A, HAS-A)                │   │
│  │ 4. Polymorphism   │ One name, many forms                    │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                  │
│  Benefits:                                                       │
│  - Reusability, Maintainability, Extensibility, Modularity       │
└─────────────────────────────────────────────────────────────────────┘
```

### Data Hiding

```java
// Data Hiding: Restricting direct access to object data
// Implementation: private variables + public getters/setters

public class Account {
    private double balance;  // Hidden from outside
    
    // Controlled access via methods
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

// Benefits:
// 1. Validation before modifying data
// 2. Internal implementation can change without affecting users
// 3. Read-only or write-only fields possible
// 4. Security and integrity
```

---

## 2. Inheritance (IS-A Relationship)

### What is IS-A?

```java
// IS-A: Inheritance (extends)
// Child IS-A Parent

class Animal {                    // Parent / Super / Base
    void eat() {
        System.out.println("Animal eating");
    }
}

class Dog extends Animal {        // Child / Sub / Derived
    void bark() {
        System.out.println("Dog barking");
    }
}

// Dog IS-A Animal
Dog d = new Dog();
d.eat();   // ✅ Inherited method
d.bark();  // ✅ Own method

// Reference types:
Animal a = new Dog();  // ✅ Dog IS-A Animal
a.eat();               // ✅
// a.bark();           // ❌ ERROR: Animal reference doesn't have bark()

// Types of inheritance:
// 1. Single: A extends B
// 2. Multilevel: A extends B extends C
// 3. Hierarchical: A extends B, C extends B
// 4. Multiple: A extends B, C (NOT supported in Java with classes)
// 5. Hybrid: combination (NOT supported)
```

### Inheritance Memory Model

```
┌─────────────────────────────────────────────────────────────────────┐
│                    INHERITANCE MEMORY MODEL                           │
│                                                                  │
│  Dog d = new Dog();                                               │
│                                                                  │
│  Heap:                                                             │
│  ┌─────────────────────────────────────────────┐                   │
│  │ Dog Object                                  │                   │
│  │ ┌─────────────────────────────────────────┐ │                   │
│  │ │ Animal Part (parent)                   │ │                   │
│  │ │   eat() → "Animal eating"              │ │                   │
│  │ ├─────────────────────────────────────────┤ │                   │
│  │ │ Dog Part (child)                       │ │                   │
│  │ │   bark() → "Dog barking"               │ │                   │
│  │ └─────────────────────────────────────────┘ │                   │
│  └─────────────────────────────────────────────┘                   │
│                                                                  │
│  Stack:                                                            │
│  ┌─────┐                                                          │
│  │  d  │───ref──→ Dog Object                                      │
│  └─────┘                                                          │
│                                                                  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 3. Inheritance (HAS-A Relationship)

### What is HAS-A?

```java
// HAS-A: Composition (class contains object of another class)
// Engine HAS-A Car (or Car HAS-A Engine)

class Engine {
    void start() {
        System.out.println("Engine starting");
    }
}

class Car {
    // Car HAS-A Engine
    Engine engine = new Engine();  // Composition
    
    void startCar() {
        engine.start();  // Delegation
        System.out.println("Car started");
    }
}

// Usage:
Car car = new Car();
car.startCar();

// HAS-A vs IS-A:
// IS-A: Inheritance (extends) — "Dog IS-A Animal"
// HAS-A: Composition (object) — "Car HAS-A Engine"

// Prefer HAS-A over IS-A when:
// 1. No true IS-A relationship
// 2. Need to reuse code without tight coupling
// 3. Can change behavior at runtime
```

---

## 4. Method Overloading

### What is Overloading?

```java
// Overloading: Same method name, different parameters
// Also known as: Compile-time polymorphism / Early binding

class Calculator {
    // Overloaded methods
    int add(int a, int b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {  // Different number of parameters
        return a + b + c;
    }
    
    double add(double a, double b) {  // Different type
        return a + b;
    }
    
    int add(int a, double b) {  // Different order
        return (int)(a + b);
    }
}

// Valid overloading:
// 1. Different number of parameters
// 2. Different type of parameters
// 3. Different order of parameters

// NOT valid overloading:
// 1. Only return type different (not allowed)
// 2. Only access modifier different (not allowed)
```

### Overloading Rules

```java
class OverloadingRules {
    // Case 1: Exact match
    void method(int a) { }
    void method(String a) { }
    
    // Case 2: Automatic promotion
    void method(int a) { }
    // Calling: method(10); → exact match
    // Calling: method('a'); → promoted to int (97)
    // Calling: method(10L); → promoted to float/double (if available)
    
    // Case 3: Widening (automatic promotion)
    void method(long a) { }
    void method(float a) { }
    // method(10); → int promoted to long → method(long)
    // (long is closer than float)
    
    // Case 4: Ambiguity
    void method(int a, long b) { }
    void method(long a, int b) { }
    // method(10, 20);  // ❌ ERROR: ambiguous (both match with promotion)
    
    // Case 5: Var-arg
    void method(int a) { }
    void method(int... a) { }
    // method(10); → method(int) (exact match preferred)
    // method(10, 20); → method(int...)
    
    // Case 6: null ambiguity
    void method(String a) { }
    void method(StringBuilder a) { }
    // method(null);  // ❌ ERROR: ambiguous (both are reference types)
    
    void method(String a) { }
    void method(Object a) { }
    // method(null); → method(String) (child class preferred)
    
    // Case 7: Widening vs Boxing
    void method(long a) { }        // Widening
    void method(Integer a) { }     // Boxing
    // method(10); → method(long) (widening preferred over boxing)
    
    // Case 8: Boxing vs Var-arg
    void method(Integer a) { }     // Boxing
    void method(int... a) { }      // Var-arg
    // method(10); → method(Integer) (boxing preferred over var-arg)
    
    // Case 9: Widening vs Boxing vs Var-arg
    // Priority: Exact match > Widening > Boxing > Var-arg
}
```

### Overloading Resolution Priority

```
┌─────────────────────────────────────────────────────────────────────┐
│                    OVERLOADING RESOLUTION PRIORITY                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Priority 1: Exact Match                                       │   │
│  │ Priority 2: Widening (byte→short→int→long→float→double)    │   │
│  │ Priority 3: Boxing (int→Integer, char→Character, etc.)       │   │
│  │ Priority 4: Var-arg (int... a)                               │   │
│  │ Priority 5: Combination (widening + boxing)                   │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. Method Overriding

### What is Overriding?

```java
// Overriding: Same method signature in parent and child
// Child provides specific implementation
// Also known as: Runtime polymorphism / Late binding / Dynamic dispatch

class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barking");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meowing");
    }
}

// Usage:
Animal a = new Dog();
a.makeSound();  // "Dog barking" (runtime decision)

Animal a2 = new Cat();
a2.makeSound();  // "Cat meowing" (runtime decision)
```

### Overriding Rules

```java
class Parent {
    public void method1() { }
    protected void method2() { }
    void method3() { }
    public static void method4() { }
    public final void method5() { }
    public Object method6() { return null; }
    public void method7() throws IOException { }
}

class Child extends Parent {
    // Rule 1: Same signature (name + parameters)
    @Override
    public void method1() { }  // ✅
    
    // Rule 2: Cannot reduce visibility
    // @Override
    // void method2() { }  // ❌ ERROR: cannot reduce visibility
    
    @Override
    public void method2() { }  // ✅ Same or wider visibility
    
    // Rule 3: Cannot change return type (except covariant)
    @Override
    public void method3() { }  // ✅
    
    // Rule 4: Covariant return type
    @Override
    public String method6() { return ""; }  // ✅ String IS-A Object
    
    // Rule 5: Cannot override static methods (hiding)
    public static void method4() { }  // Hiding, not overriding
    
    // Rule 6: Cannot override final methods
    // @Override
    // public void method5() { }  // ❌ ERROR: cannot override final
    
    // Rule 7: Exception handling
    @Override
    public void method7() { }  // ✅ No exception (subclass of IOException)
    // @Override
    // public void method7() throws Exception { }  // ❌ Wider exception
    
    @Override
    public void method7() throws FileNotFoundException { }  // ✅ Subclass exception
}
```

---

## 6. Coupling

### What is Coupling?

```java
// Coupling: Degree of dependency between classes
// Goal: Loose coupling (low dependency)

// Tight Coupling (BAD):
class TightCoupling {
    // Direct dependency on concrete class
    OracleDatabase db = new OracleDatabase();  // Hard-coded
    
    void save() {
        db.save();  // Direct dependency
    }
}

// Loose Coupling (GOOD):
interface Database {
    void save();
}

class OracleDatabase implements Database {
    public void save() {
        System.out.println("Oracle save");
    }
}

class MySQLDatabase implements Database {
    public void save() {
        System.out.println("MySQL save");
    }
}

class LooseCoupling {
    Database db;  // Interface reference
    
    LooseCoupling(Database db) {  // Dependency injection
        this.db = db;
    }
    
    void save() {
        db.save();  // Works with any Database
    }
}

// Usage:
LooseCoupling app = new LooseCoupling(new OracleDatabase());
app.save();  // "Oracle save"

app = new LooseCoupling(new MySQLDatabase());
app.save();  // "MySQL save"
```

---

## 7. Type Casting

### Object Type Casting

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

// Upcasting (implicit)
Animal a = new Dog();  // ✅ Dog IS-A Animal

// Downcasting (explicit)
Dog d = (Dog) a;       // ✅ Valid

// Invalid downcasting
Animal a2 = new Animal();
// Dog d2 = (Dog) a2;  // ❌ ClassCastException

// instanceof check
Animal a3 = new Cat();
if (a3 instanceof Dog) {
    Dog d3 = (Dog) a3;  // Not executed
}

// 3 Mantras of Type Casting:
// 1. B b = (B) a;  → B IS-A type of a must be true
// 2. Compiler checks: Reference type must be compatible
// 3. JVM checks: Object type must be compatible
```

### 3 Mantras of Type Casting

```java
// Mantra 1: B IS-A A must be true
class A { }
class B extends A { }
class C { }

// B IS-A A → true
A a = new B();
B b = (B) a;  // ✅

// C IS-A A → false
// A a2 = new C();  // ❌ Compilation error

// Mantra 2: Compiler checks reference type
A a = new B();
// C c = (C) a;  // ❌ Compiler error: C is not A

// Mantra 3: JVM checks object type
A a = new B();
// B b = (B) a;  // ✅ JVM: a's object is B

A a2 = new A();
// B b2 = (B) a2;  // ❌ Compiler allows but JVM throws ClassCastException
// JVM: a2's object is A, not B
```

---

## 8. Static Control Flow

### Static Control Flow Order

```java
class StaticFlow {
    // Step 1: Identify static members
    static int i = 10;        // Static variable
    
    // Step 2: Execute static variables & blocks in order
    static {
        System.out.println("Static block 1");  // i = 10
        m1();                                    // prints 10
    }
    
    static void m1() {
        System.out.println(i);  // 10
    }
    
    static {
        System.out.println("Static block 2");
    }
    
    // Step 3: Execute main method
    public static void main(String[] args) {
        System.out.println("Main method");
    }
}

// Output:
// Static block 1
// 10
// Static block 2
// Main method

// Order of execution:
// 1. Static variables (in order of declaration)
// 2. Static blocks (in order of declaration)
// 3. Main method
```

### Static Control Flow Example

```java
class StaticFlowExample {
    static {
        System.out.println("Block 1");
    }
    
    static int x = 10;
    
    static {
        System.out.println("Block 2: x = " + x);
        // y = 20;  // ✅ Forward reference allowed
    }
    
    static int y = 20;
    
    static {
        System.out.println("Block 3: y = " + y);
    }
    
    public static void main(String[] args) {
        System.out.println("Main: x = " + x + ", y = " + y);
    }
}

// Output:
// Block 1
// Block 2: x = 10
// Block 3: y = 20
// Main: x = 10, y = 20

// Note: Forward reference allowed in static blocks
// But read before declaration: default value
```

---

## 9. Instance Control Flow

### Instance Control Flow Order

```java
class InstanceFlow {
    // Step 1: Instance variable initialization
    int i = 10;
    
    // Step 2: Instance block
    {
        System.out.println("Instance block 1: i = " + i);
    }
    
    // Step 3: Constructor
    InstanceFlow() {
        System.out.println("Constructor: i = " + i);
    }
    
    {
        System.out.println("Instance block 2");
    }
    
    public static void main(String[] args) {
        System.out.println("Main");
        new InstanceFlow();
        System.out.println("End");
    }
}

// Output:
// Main
// Instance block 1: i = 10
// Instance block 2
// Constructor: i = 10
// End

// Order of execution for object creation:
// 1. Static control flow (once per class)
// 2. Instance variable initialization
// 3. Instance blocks (in order)
// 4. Constructor
```

### Combined Static + Instance Flow

```java
class CombinedFlow {
    static int s = 10;
    int i = 20;
    
    static {
        System.out.println("Static block: s = " + s);
    }
    
    {
        System.out.println("Instance block: i = " + i);
    }
    
    CombinedFlow() {
        System.out.println("Constructor: s = " + s + ", i = " + i);
    }
    
    public static void main(String[] args) {
        System.out.println("Main");
        new CombinedFlow();
        new CombinedFlow();
    }
}

// Output:
// Static block: s = 10
// Main
// Instance block: i = 20
// Constructor: s = 10, i = 20
// Instance block: i = 20
// Constructor: s = 10, i = 20

// Note: Static executes once, Instance executes every time
```

---

## 10. Constructors

### Constructor Basics

```java
class ConstructorDemo {
    int x;
    
    // Default constructor (no parameters)
    ConstructorDemo() {
        System.out.println("Default constructor");
    }
    
    // Parameterized constructor
    ConstructorDemo(int x) {
        this.x = x;
        System.out.println("Parameterized constructor: x = " + x);
    }
    
    // Constructor overloading
    ConstructorDemo(int x, String s) {
        this(x);  // Call another constructor
        System.out.println("Two-parameter constructor: " + s);
    }
    
    public static void main(String[] args) {
        new ConstructorDemo();           // Default
        new ConstructorDemo(10);           // Parameterized
        new ConstructorDemo(10, "Hello");  // Two-parameter
    }
}
```

### Constructor Rules

```java
class ConstructorRules {
    // Rule 1: Constructor name = class name
    ConstructorRules() { }  // ✅
    // Constructorrules() { }  // ❌ Case sensitive
    
    // Rule 2: No return type (not even void)
    // void ConstructorRules() { }  // ❌ Not a constructor (it's a method)
    
    // Rule 3: Cannot be static, final, abstract
    // static ConstructorRules() { }  // ❌
    // final ConstructorRules() { }   // ❌
    // abstract ConstructorRules() { }  // ❌
    
    // Rule 4: Can have access modifiers
    public ConstructorRules() { }      // ✅
    protected ConstructorRules(int x) { }  // ✅
    private ConstructorRules(String s) { }  // ✅
    
    // Rule 5: Default constructor (if no constructor defined)
    // Compiler provides: public ClassName() { }
    
    // Rule 6: super() or this() must be first statement
    ConstructorRules() {
        // super();  // Implicit
        System.out.println("Constructor");
    }
    
    // Rule 7: Cannot have both super() and this()
    ConstructorRules(int x) {
        this();       // ✅ this()
        // super();    // ❌ Cannot have both
    }
    
    // Rule 8: Constructor can throw exceptions
    ConstructorRules() throws IOException { }  // ✅
}
```

---

## 11. Singleton Class

### What is Singleton?

```java
// Singleton: Class that can have only ONE object
// Use cases: Database connection, Logger, Configuration, Cache

class Singleton {
    // Step 1: Private static instance
    private static Singleton instance;
    
    // Step 2: Private constructor (prevent external instantiation)
    private Singleton() {
        System.out.println("Singleton created");
    }
    
    // Step 3: Public static method to get instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();  // Lazy initialization
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Hello from Singleton");
    }
}

// Usage:
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
System.out.println(s1 == s2);  // true (same object)
```

### Thread-Safe Singleton

```java
// Thread-safe singleton (synchronized)
class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() { }
    
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}

// Double-checked locking (better performance)
class DCLSingleton {
    private static volatile DCLSingleton instance;
    
    private DCLSingleton() { }
    
    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}

// Eager initialization (simplest)
class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() { }
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}

// Enum singleton (best approach)
enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Doing something");
    }
}

// Usage:
EnumSingleton.INSTANCE.doSomething();
```

---

## 12. Interview Questions

### Q1. What is the difference between overloading and overriding?

```java
// Overloading:
// - Same method name, different parameters
// - Compile-time polymorphism
// - Same class
// - Return type can be different
// - Access modifier can be different

// Overriding:
// - Same method name, same parameters
// - Runtime polymorphism
// - Parent and child classes
// - Return type: same or covariant
// - Access modifier: same or wider
// - Cannot override static, final, private
```

### Q2. Can we overload static methods?

```java
// ✅ YES
class Test {
    static void method(int a) { }
    static void method(String a) { }
    static void method(int a, int b) { }
}

// Overloading rules apply to static methods too
// But static methods are hidden, not overridden
```

### Q3. Can we override static methods?

```java
// ❌ NO — static methods are hidden, not overridden
class Parent {
    static void method() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void method() {  // Hiding, not overriding
        System.out.println("Child");
    }
}

Parent p = new Child();
p.method();  // "Parent" (resolved by reference type)
```

### Q4. What is the difference between IS-A and HAS-A?

```java
// IS-A: Inheritance (extends)
class Animal { }
class Dog extends Animal { }  // Dog IS-A Animal

// HAS-A: Composition (object reference)
class Engine { }
class Car {
    Engine engine = new Engine();  // Car HAS-A Engine
}

// Prefer HAS-A when:
// - No true inheritance relationship
// - Need flexibility
// - Need to change behavior at runtime
```

### Q5. What is the order of execution in a class?

```java
// Static flow (once per class):
// 1. Static variables (in order)
// 2. Static blocks (in order)
// 3. Main method

// Instance flow (every object creation):
// 1. Instance variables (in order)
// 2. Instance blocks (in order)
// 3. Constructor

// Combined:
// 1. Static flow (class loading)
// 2. Main method starts
// 3. When new object: Instance flow
// 4. Continue main method
```

### Q6. How to create a Singleton class?

```java
// 1. Private constructor
// 2. Private static instance
// 3. Public static getInstance() method

class Singleton {
    private static Singleton instance;
    private Singleton() { }
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// Best practice: Enum singleton
enum Singleton {
    INSTANCE;
    // Thread-safe, serialization-safe, reflection-safe
}
```

### Q7. What is covariant return type?

```java
class Parent {
    public Object get() { return null; }
}

class Child extends Parent {
    @Override
    public String get() { return ""; }  // ✅ String IS-A Object
}

// Covariant return type: Child's return type is subclass of Parent's
// Allowed from Java 5+
```

### Q8. Can we have constructor in abstract class?

```java
// ✅ YES
abstract class AbstractClass {
    AbstractClass() {
        System.out.println("Abstract constructor");
    }
}

class Concrete extends AbstractClass {
    Concrete() {
        // super() called implicitly
        System.out.println("Concrete constructor");
    }
}

// Purpose: Initialize abstract class fields when child object is created
```

---

## 13. Key Takeaways

1. **OOP 4 Pillars**: Abstraction, Encapsulation, Inheritance, Polymorphism.
2. **Data Hiding**: private fields + public getters/setters.
3. **IS-A**: Inheritance (extends); **HAS-A**: Composition (object reference).
4. **Overloading**: Same name, different parameters (compile-time).
5. **Overriding**: Same name, same parameters (runtime).
6. **Overloading resolution**: Exact > Widening > Boxing > Var-arg.
7. **Overriding rules**: Same signature, same or wider visibility, covariant return type.
8. **Cannot override**: static, final, private methods.
9. **Coupling**: Prefer loose coupling (interface-based) over tight coupling.
10. **Static flow**: Static vars → Static blocks → Main method.
11. **Instance flow**: Instance vars → Instance blocks → Constructor.
12. **Constructor**: No return type, same name as class, can be overloaded.
13. **Singleton**: Only one instance; private constructor + static getInstance().
14. **Enum singleton**: Best practice for thread-safe, serialization-safe singleton.
15. **3 Mantras of Type Casting**: B IS-A A, compiler checks reference, JVM checks object.

---

**Happy coding! 🚀**

*OOP concepts are the foundation of Java — master them to write clean, maintainable, and scalable code.*
