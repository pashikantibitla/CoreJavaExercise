# 05 — Interface and Abstract Class Loopholes

> **Videos:** 44–50  
> **Topics:** new vs Constructor, Child Object vs Parent Constructor, Need of Abstract Class Constructor, Abstract Class vs Interface wrt Constructor, Abstract Class vs Interface

---

## Table of Contents

1. [new vs Constructor](#1-new-vs-constructor)
2. [Child Object vs Parent Constructor](#2-child-object-vs-parent-constructor)
3. [Need of Abstract Class Constructor](#3-need-of-abstract-class-constructor)
4. [Abstract Class vs Interface wrt Constructor](#4-abstract-class-vs-interface-wrt-constructor)
5. [Abstract Class vs Interface](#5-abstract-class-vs-interface)
6. [Interview Questions](#6-interview-questions)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. new vs Constructor

### What does `new` do?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    new vs CONSTRUCTOR                                 │
│                                                                  │
│  new operator:                                                   │
│  1. Allocates memory in heap                                     │
│  2. Creates object                                               │
│  3. Calls constructor to initialize                              │
│  4. Returns reference to the object                              │
│                                                                  │
│  Constructor:                                                    │
│  1. Initializes the object (sets initial values)               │
│  2. Executes instance blocks                                     │
│  3. Cannot return any value (not even void)                      │
│  4. Name must be same as class name                              │
│                                                                  │
│  new + Constructor = Object Creation + Initialization            │
└─────────────────────────────────────────────────────────────────────┘
```

### new creates object, constructor initializes

```java
// new creates the object, constructor initializes it
Student s = new Student();  // new creates object, Student() initializes

// What happens internally:
// 1. new allocates memory for Student object
// 2. Calls Student() constructor
// 3. Constructor initializes variables
// 4. Returns reference

// Constructor is NOT creating the object
// Constructor is ONLY initializing the object

// Proof: Constructor cannot return anything
public class Student {
    public Student() {
        // No return statement
        // If you add return; it's allowed but returns nothing
        return;  // ✅ Allowed in constructor (returns void)
    }
    
    // public Student(int x) { return 10; }  // ❌ ERROR: cannot return a value
}
```

---

## 2. Child Object vs Parent Constructor

### Constructor Chaining

```java
class Parent {
    Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {
    Child() {
        // super();  // Implicitly inserted by compiler
        System.out.println("Child Constructor");
    }
}

// When creating Child object:
Child c = new Child();
// Output:
// Parent Constructor
// Child Constructor

// Why Parent constructor runs first?
// 1. Child IS-A Parent (complete parent must exist first)
// 2. super() is first line of every constructor (implicit or explicit)
// 3. Parent must be fully initialized before child
```

### Object Creation Flow

```
┌─────────────────────────────────────────────────────────────────────┐
│                    OBJECT CREATION FLOW                                 │
│                                                                  │
│  Child c = new Child();                                            │
│                                                                  │
│  Step 1: new allocates memory for Child object                    │
│  Step 2: super() calls Parent constructor (implicit)              │
│  Step 3: Parent constructor executes:                             │
│          - Instance variables: default values                    │
│          - Instance blocks: execute in order                       │
│          - Constructor body: executes                              │
│  Step 4: Child constructor executes:                              │
│          - Instance variables: default values                    │
│          - Instance blocks: execute in order                       │
│          - Constructor body: executes                              │
│  Step 5: Reference assigned to variable c                          │
│                                                                  │
│  Note: Parent constructor runs before Child constructor             │
│  But: Child object is created (not Parent object)                  │
│  Only ONE object is created: Child object                         │
│  Parent constructor is called to initialize parent part           │
└─────────────────────────────────────────────────────────────────────┘
```

### Parent Class Object Not Created

```java
class Parent {
    Parent() {
        System.out.println(this);  // Prints Child object reference
        System.out.println(this.getClass().getName());  // Child
    }
}

class Child extends Parent {
    Child() {
        System.out.println(this);  // Same Child object reference
        System.out.println(this.getClass().getName());  // Child
    }
    
    public static void main(String[] args) {
        Child c = new Child();
    }
}

// Output:
// Child@hashcode
// Child
// Child@hashcode (same)
// Child

// Conclusion: Only Child object is created, not Parent object
// Parent constructor runs but creates no new object
```

### super() vs this()

```java
class Parent {
    Parent() {
        System.out.println("Parent no-arg");
    }
    Parent(int x) {
        System.out.println("Parent int-arg");
    }
}

class Child extends Parent {
    Child() {
        // super();  // Default: calls Parent no-arg constructor
        System.out.println("Child no-arg");
    }
    
    Child(int x) {
        super(x);  // Explicit: calls Parent int-arg constructor
        System.out.println("Child int-arg");
    }
    
    Child(String s) {
        this();     // Calls Child no-arg constructor
        System.out.println("Child String-arg");
    }
}

// Rules:
// 1. super() or this() must be first statement
// 2. Cannot have both super() and this() in same constructor
// 3. super() is implicit if not present
// 4. this() must call another constructor in same class
```

---

## 3. Need of Abstract Class Constructor

### Why Abstract Class Has Constructor?

```java
abstract class Vehicle {
    String brand;
    
    // Constructor in abstract class
    Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle constructor: " + brand);
    }
    
    abstract void drive();
}

class Car extends Vehicle {
    Car(String brand) {
        super(brand);  // Must call parent constructor
        System.out.println("Car constructor");
    }
    
    @Override
    void drive() {
        System.out.println("Driving car");
    }
}

// Why abstract class needs constructor:
// 1. Initialize abstract class variables (instance variables)
// 2. Execute abstract class instance blocks
// 3. Called when child object is created
// 4. Abstract class variables need initialization

// Usage:
Vehicle v = new Car("Toyota");
// Output: Vehicle constructor: Toyota
//         Car constructor
```

### Constructor Use in Abstract Class

```java
abstract class Person {
    String name;
    int age;
    
    // Constructor to initialize common fields
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    abstract void work();
}

class Employee extends Person {
    int salary;
    
    Employee(String name, int age, int salary) {
        super(name, age);  // Initialize parent fields
        this.salary = salary;
    }
    
    @Override
    void work() {
        System.out.println(name + " is working");
    }
}

// Without abstract constructor:
// - Child must initialize all parent fields
// - Redundant code in every child
// - Abstract constructor provides common initialization
```

---

## 4. Abstract Class vs Interface wrt Constructor

### Comparison

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ABSTRACT CLASS vs INTERFACE (Constructor)          │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Feature            │ Abstract Class       │ Interface         │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Constructor        │ ✅ YES              │ ❌ NO              │   │
│  │ Can be instantiated│ ❌ NO               │ ❌ NO              │   │
│  │ Purpose of ctor    │ Initialize fields   │ N/A               │   │
│  │ Called when        │ Child object created│ N/A               │   │
│  │ Child must call    │ super()             │ N/A               │   │
│  │ Instance variables │ ✅ YES              │ ❌ NO (constants) │   │
│  │ Instance blocks    │ ✅ YES              │ ❌ NO              │   │
│  └────────────────────┴──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Why Interface Has No Constructor?

```java
// Interface has no constructor because:
// 1. No instance variables (only constants)
// 2. No instance blocks
// 3. No object creation (interface is not a class)
// 4. All methods are abstract (until default)

interface MyInterface {
    // Variables are constants
    int MAX = 100;  // public static final
    
    // No constructor allowed
    // MyInterface() { }  // ❌ ERROR
    
    // No instance blocks
    // { }  // ❌ ERROR
    
    // Methods are abstract (no body)
    void method();
}

// Interface is a contract, not an implementation
// No need to initialize anything (no state)
```

---

## 5. Abstract Class vs Interface

### Detailed Comparison

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ABSTRACT CLASS vs INTERFACE                        │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Feature            │ Abstract Class       │ Interface         │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Methods            │ Abstract + Concrete  │ Abstract (Java 7)│   │
│  │                    │                      │ Default (Java 8+) │   │
│  │                    │                      │ Static (Java 8+)  │   │
│  │                    │                      │ Private (Java 9+) │   │
│  │ Variables          │ Instance + Static    │ public static final│   │
│  │ Constructor        │ ✅ YES              │ ❌ NO              │   │
│  │ Instance blocks    │ ✅ YES              │ ❌ NO              │   │
│  │ Multiple inheritance│ ❌ NO (single class)│ ✅ YES (multiple)  │   │
│  │ Use case           │ Partial implementation│ Full abstraction │   │
│  │ extends/implements │ extends              │ implements        │   │
│  │ Performance        │ Faster (fewer indirection)│ Slower (method │   │
│  │                    │                      │ lookup)           │   │
│  │ Evolution          │ Easy to add methods │ Breaks implementations│   │
│  │                    │                      │ (unless default)  │   │
│  │ Java version       │ All versions        │ Java 8+ enhanced  │   │
│  └────────────────────┴──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### When to Use What

```java
// Use Abstract Class when:
// 1. Partial implementation needed
// 2. Common state (instance variables) needed
// 3. Constructor needed for initialization
// 4. Non-public members needed
// 5. Single inheritance is sufficient

abstract class Animal {
    String name;  // Instance variable
    
    Animal(String name) {  // Constructor
        this.name = name;
    }
    
    void eat() {  // Concrete method
        System.out.println(name + " is eating");
    }
    
    abstract void makeSound();  // Abstract method
}

// Use Interface when:
// 1. Pure contract/behavior definition
// 2. Multiple inheritance needed
// 3. No state/fields needed
// 4. API evolution (default methods)
// 5. Cross-cutting concerns

interface Flyable {
    void fly();  // Abstract
    
    default void land() {  // Default method (Java 8+)
        System.out.println("Landing");
    }
}

interface Swimmable {
    void swim();
}

// Multiple interface implementation:
class Duck extends Animal implements Flyable, Swimmable {
    Duck(String name) {
        super(name);
    }
    
    @Override
    void makeSound() {
        System.out.println("Quack");
    }
    
    @Override
    public void fly() {
        System.out.println("Duck flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck swimming");
    }
}
```

---

## 6. Interview Questions

### Q1. Can we create an object of abstract class?

```java
// ❌ NO
abstract class AbstractClass {
    void method() { }
}

// AbstractClass a = new AbstractClass();  // ❌ ERROR

// Must create subclass:
class Concrete extends AbstractClass { }

AbstractClass a = new Concrete();  // ✅ (reference to concrete object)
Concrete c = new Concrete();       // ✅
```

### Q2. Can we create an object of interface?

```java
// ❌ NO
interface MyInterface {
    void method();
}

// MyInterface i = new MyInterface();  // ❌ ERROR

// Must implement interface:
class Implementation implements MyInterface {
    public void method() { }
}

MyInterface i = new Implementation();  // ✅
```

### Q3. Why does abstract class have constructor if we can't instantiate it?

```java
// Abstract class constructor is called when child object is created
abstract class Parent {
    int x = 10;
    
    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    Child() {
        // super() called implicitly
        System.out.println("Child constructor");
    }
}

// Parent constructor initializes parent part of child object
// Child object = parent part + child part
// Parent constructor initializes parent part
```

### Q4. Can we have `final` methods in abstract class?

```java
// ✅ YES
abstract class Parent {
    // final method: cannot be overridden
    public final void finalMethod() {
        System.out.println("Cannot override");
    }
    
    abstract void abstractMethod();
}

class Child extends Parent {
    // @Override
    // public void finalMethod() { }  // ❌ ERROR
    
    @Override
    void abstractMethod() { }  // ✅
}
```

### Q5. What happens if child class constructor doesn't call `super()`?

```java
class Parent {
    Parent() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    Child() {
        // super() inserted implicitly by compiler
        System.out.println("Child");
    }
}

// If parent has no default constructor:
class Parent {
    Parent(int x) { }  // Parameterized constructor only
}

class Child extends Parent {
    Child() {
        // super();  // ❌ ERROR: Parent() does not exist
        super(10);   // ✅ Must call explicitly
    }
}
```

### Q6. Can interface have `static` methods?

```java
// ✅ YES (Java 8+)
interface MyInterface {
    static void staticMethod() {
        System.out.println("Static method");
    }
    
    default void defaultMethod() {
        System.out.println("Default method");
    }
}

// Usage:
MyInterface.staticMethod();  // ✅ Via interface name

// Cannot override static method in implementation
class Impl implements MyInterface {
    // Can hide static method
    static void staticMethod() {  // Hiding, not overriding
        System.out.println("Hidden");
    }
}
```

### Q7. What is the difference between `implements` and `extends`?

```java
// extends: IS-A relationship (class inheritance)
// implements: CAN-DO relationship (interface implementation)

class Animal { }                    // Parent class
class Dog extends Animal { }        // Dog IS-A Animal

interface Flyable { }               // Interface
class Bird implements Flyable { }   // Bird CAN-DO flying

// Class extends ONE class, implements MANY interfaces
class Eagle extends Bird implements Flyable, Runnable { }
```

### Q8. Can we have `main()` method in abstract class?

```java
// ✅ YES
abstract class AbstractWithMain {
    public static void main(String[] args) {
        System.out.println("Hello from abstract class");
    }
    
    abstract void method();
}

// Run: java AbstractWithMain
// Output: Hello from abstract class

// Main method is static, doesn't need object
// Abstract class can have static members
```

---

## 7. Key Takeaways

1. **new** creates object; **constructor** initializes object.
2. **Child object** creation calls parent constructor (super() implicit).
3. **Only ONE object** is created (child), not parent object.
4. **Parent constructor** initializes parent part of child object.
5. **Abstract class** has constructor to initialize its fields.
6. **Interface** has NO constructor (no state, no instance variables).
7. **super()** must be first statement; **this()** also first statement.
8. **Cannot have both** super() and this() in same constructor.
9. **Constructor chaining** happens automatically via super().
10. **Abstract class**: partial implementation; **Interface**: full contract.
11. **Java 8+**: interfaces can have default and static methods.
12. **Java 9+**: interfaces can have private methods.
13. **Abstract class** can have final methods (cannot override).
14. **Multiple inheritance** allowed with interfaces, not with classes.
15. **Main method** can be in abstract class (static).

---

**Happy coding! 🚀**

*Understanding constructors in abstract classes and interfaces is key to mastering object creation in Java.*
