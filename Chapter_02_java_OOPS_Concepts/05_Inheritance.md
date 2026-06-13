# 05 — Inheritance

> **Video:** 17–21  
> **Topics:** Introduction, Importance, Types, Multiple Inheritance, Cyclic Inheritance

---

## Table of Contents

1. [Introduction to Inheritance](#1-introduction-to-inheritance)
2. [Importance of Inheritance](#2-importance-of-inheritance)
3. [Types of Inheritance](#3-types-of-inheritance)
4. [Multiple Inheritance](#4-multiple-inheritance)
5. [Cyclic Inheritance](#5-cyclic-inheritance)
6. [Interview Questions](#6-interview-questions)
7. [Quick Reference](#7-quick-reference)
8. [Key Takeaways](#8-key-takeaways)

---

## 1. Introduction to Inheritance

### What is Inheritance?

Inheritance is the mechanism by which one class (child/subclass) acquires the properties and behaviors of another class (parent/superclass). It represents the **IS-A relationship**.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    INHERITANCE VISUAL                                     │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                        Parent Class (Superclass)                  │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │  • Fields                                              │  │    │
│  │  │  • Methods                                           │  │    │
│  │  │  • Constructors                                      │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  │                              │                                   │    │
│  │                              │ extends (IS-A relationship)     │    │
│  │                              ▼                                   │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │                    Child Class (Subclass)                   │  │    │
│  │  │  ┌─────────────────────────────────────────────────────┐ │  │    │
│  │  │  │ Inherited:                                        │ │  │    │
│  │  │  │ • Fields from parent                              │ │  │    │
│  │  │  │ • Methods from parent                             │ │  │    │
│  │  │  │                                                   │ │  │    │
│  │  │  │ Own:                                              │ │  │    │
│  │  │  │ • New fields                                      │ │  │    │
│  │  │  │ • New methods                                     │ │  │    │
│  │  │  │ • Overridden methods                              │ │  │    │
│  │  │  └─────────────────────────────────────────────────────┘ │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  Example: Dog IS-A Animal, Car IS-A Vehicle, Manager IS-A Employee       │
└─────────────────────────────────────────────────────────────────────────┘
```

### Syntax

```java
// Parent class
class Animal {
    protected String name;
    
    public void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class
class Dog extends Animal {
    public void bark() {
        System.out.println("Dog is barking");
    }
    
    // Can use inherited methods
    public void display() {
        eat();        // Inherited method
        bark();       // Own method
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();   // "Animal is eating" — inherited
        dog.bark();  // "Dog is barking" — own
    }
}
```

---

## 2. Importance of Inheritance

### Why Use Inheritance?

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    IMPORTANCE OF INHERITANCE                            │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Benefit            │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ Code Reuse         │ Common code in parent, reused in children       │  │
│  │ Extensibility      │ Add new features without modifying existing code│  │
│  │ Polymorphism       │ Parent reference can hold child objects          │  │
│  │ Method Overriding  │ Child can customize parent behavior             │  │
│  │ Hierarchy          │ Organizes classes logically                    │  │
│  │ Maintenance        │ Changes in parent propagate to children         │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example: Code Reuse

```java
class Vehicle {
    protected String brand;
    protected int year;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    public void start() {
        System.out.println("Vehicle starting");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}

class Car extends Vehicle {
    private int trunkCapacity;
    
    public Car(String brand, int year, int trunkCapacity) {
        super(brand, year);  // Call parent constructor
        this.trunkCapacity = trunkCapacity;
    }
    
    public void openTrunk() {
        System.out.println("Trunk opened");
    }
}

class Bike extends Vehicle {
    private boolean hasGear;
    
    public Bike(String brand, int year, boolean hasGear) {
        super(brand, year);
        this.hasGear = hasGear;
    }
    
    public void wheelie() {
        System.out.println("Doing a wheelie!");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2023, 500);
        Bike bike = new Bike("Yamaha", 2022, true);
        
        // Inherited methods
        car.displayInfo();   // "Brand: Toyota, Year: 2023"
        bike.displayInfo();  // "Brand: Yamaha, Year: 2022"
        
        // Own methods
        car.openTrunk();
        bike.wheelie();
    }
}
```

---

## 3. Types of Inheritance

### Classification

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    TYPES OF INHERITANCE                                   │
│                                                                          │
│  1. Single Inheritance                                                  │
│  2. Multilevel Inheritance                                              │
│  3. Hierarchical Inheritance                                            │
│  4. Multiple Inheritance (NOT supported in Java via classes)             │
│  5. Hybrid Inheritance (combination)                                    │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  Single         │  Multilevel        │  Hierarchical             │    │
│  ├─────────────────────────────────────────────────────────────────┤    │
│  │     A           │      A             │       A                   │    │
│  │     │           │      │             │      / \\                  │    │
│  │     B           │      B             │     B   C                 │    │
│  │                 │      │             │    /                       │    │
│  │                 │      C             │   D                        │    │
│  │  One parent,    │  Chain of          │  One parent,             │    │
│  │  one child      │  inheritance       │  multiple children      │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  Multiple (NOT supported via classes)                           │    │
│  │     A     B                                                     │    │
│  │      \\   /                                                       │    │
│  │       C                                                          │    │
│  │  C extends both A and B — NOT allowed in Java with classes      │    │
│  │  ✅ Supported via interfaces: class C implements A, B          │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
// Single Inheritance
class Animal { }
class Dog extends Animal { }

// Multilevel Inheritance
class Animal { }
class Mammal extends Animal { }
class Dog extends Mammal { }

// Hierarchical Inheritance
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
class Bird extends Animal { }

// Multiple Inheritance (NOT supported via classes)
// class A { }
// class B { }
// class C extends A, B { }  // ❌ Syntax error in Java

// ✅ Supported via interfaces
interface Flyable { void fly(); }
interface Swimmable { void swim(); }
class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Flying"); }
    public void swim() { System.out.println("Swimming"); }
}
```

---

## 4. Multiple Inheritance

### Why Java Doesn't Support Multiple Inheritance via Classes

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    THE DIAMOND PROBLEM                                     │
│                                                                          │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                        Parent Class A                              │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │  public void display() {                                  │  │    │
│  │  │    System.out.println("A");                                │  │    │
│  │  │  }                                                        │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  │                    /           \\                                  │    │
│  │                   /             \\                                 │    │
│  │  ┌─────────────────────┐   ┌─────────────────────┐          │    │
│  │  │  Class B extends A   │   │  Class C extends A   │          │    │
│  │  │  // Inherits display()│   │  // Inherits display()│          │    │
│  │  └─────────────────────┘   └─────────────────────┘          │    │
│  │                    \\           /                                  │    │
│  │                     \\         /                                   │    │
│  │  ┌─────────────────────────────────────────────────────────┐  │    │
│  │  │  Class D extends B, C  ← NOT ALLOWED IN JAVA             │  │    │
│  │  │  Problem: Which display() does D inherit? B's or C's? │  │    │
│  │  │  This is the Diamond Problem — ambiguity!               │  │    │
│  │  └─────────────────────────────────────────────────────────┘  │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  Java avoids this by NOT allowing multiple class inheritance.            │
│  But allows multiple interface inheritance.                              │
└─────────────────────────────────────────────────────────────────────────┘
```

### Solution: Multiple Inheritance via Interfaces

```java
interface Printable {
    void print();
}

interface Showable {
    void show();
}

class Document implements Printable, Showable {
    @Override
    public void print() {
        System.out.println("Printing document");
    }
    
    @Override
    public void show() {
        System.out.println("Showing document");
    }
}

// If both interfaces have same method:
interface Interface1 {
    void display();
}

interface Interface2 {
    void display();
}

class MyClass implements Interface1, Interface2 {
    @Override
    public void display() {
        // Only one implementation needed
        // No ambiguity because both require same method
        System.out.println("Display");
    }
}
```

---

## 5. Cyclic Inheritance

### What is Cyclic Inheritance?

Cyclic inheritance occurs when a class inherits from itself, directly or indirectly. It is **not allowed** in Java.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    CYCLIC INHERITANCE (NOT ALLOWED)                     │
│                                                                          │
│  Direct Cyclic:                                                          │
│  ┌─────────────────────┐                                                │
│  │  class A extends A  │  ← ❌ A cannot extend itself                  │
│  └─────────────────────┘                                                │
│                                                                          │
│  Indirect Cyclic:                                                        │
│  ┌─────────────────────┐                                                │
│  │  class A extends B│                                                │
│  │  class B extends C│                                                │
│  │  class C extends A│  ← ❌ Cycle: A → B → C → A                     │
│  └─────────────────────┘                                                │
│                                                                          │
│  Java compiler detects this and throws an error:                        │
│  "Cyclic inheritance involving A"                                        │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
// ❌ Direct cyclic inheritance
class A extends A {  // Compile error: "Cyclic inheritance involving A"
    public void display() {
        System.out.println("A");
    }
}

// ❌ Indirect cyclic inheritance
class X extends Y { }
class Y extends Z { }
class Z extends X { }  // Compile error: "Cyclic inheritance involving X"
```

---

## 6. Interview Questions

### Q1. Can a child class access private members of parent class?

```java
class Parent {
    private int privateField = 10;
    public int publicField = 20;
    
    public int getPrivateField() {
        return privateField;
    }
}

class Child extends Parent {
    public void display() {
        // System.out.println(privateField); // ❌ Error — private
        System.out.println(publicField);      // ✅ Works
        System.out.println(getPrivateField()); // ✅ Works via getter
    }
}

// Private members are NOT inherited
// But can be accessed indirectly via public/protected methods
```

---

### Q2. Can a parent class reference hold a child class object?

```java
class Animal {
    public void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
    
    public void fetch() {
        System.out.println("Fetching");
    }
}

public class Main {
    public static void main(String[] args) {
        // ✅ Parent reference can hold child object
        Animal animal = new Dog();
        animal.makeSound();  // "Bark" — dynamic method dispatch
        
        // animal.fetch();   // ❌ Error — fetch() not in Animal
        // Can only call methods defined in Animal
    }
}
```

---

### Q3. What is `super` keyword?

```java
class Vehicle {
    protected String brand;
    
    public Vehicle(String brand) {
        this.brand = brand;
    }
    
    public void display() {
        System.out.println("Brand: " + brand);
    }
}

class Car extends Vehicle {
    private int horsepower;
    
    public Car(String brand, int horsepower) {
        super(brand);  // Call parent constructor
        this.horsepower = horsepower;
    }
    
    @Override
    public void display() {
        super.display();  // Call parent method
        System.out.println("Horsepower: " + horsepower);
    }
}

// super() — calls parent constructor
// super.method() — calls parent method
// super.field — accesses parent field
```

---

### Q4. Can a class extend itself?

```java
// ❌ NO — cyclic inheritance
class A extends A { }  // Compile error

// This is logically impossible:
// A is already fully defined, so extending itself adds nothing
// And creates infinite recursion in class hierarchy
```

---

## 7. Quick Reference

### Inheritance Keywords

```
extends     — Class inherits from another class
implements  — Class implements an interface
super       — Refers to parent class
this        — Refers to current class
final       — Prevents inheritance (class) or overriding (method)
```

### Inheritance Rules

```
✅ Class extends one class only
✅ Class implements multiple interfaces
✅ Interface extends multiple interfaces
✅ Constructor is NOT inherited
✅ Private members are NOT inherited
✅ Static methods are NOT overridden (they are hidden)
✅ Final methods cannot be overridden
✅ Abstract class can be parent
```

---

## 8. Key Takeaways

1. **Inheritance = IS-A relationship** — Dog IS-A Animal.
2. **`extends` keyword** — used for class inheritance.
3. **Java does NOT support multiple inheritance** with classes.
4. **Multiple inheritance IS supported** with interfaces.
5. **Diamond problem** — reason why multiple class inheritance is banned.
6. **Cyclic inheritance** — not allowed (class A cannot extend A, or A→B→A).
7. **Private members are NOT inherited** — but accessible via getters.
8. **Constructors are NOT inherited** — but child can call `super()`.
9. **Method overriding** — child provides different implementation.
10. **`super` keyword** — accesses parent class members.
11. **Parent reference can hold child object** — polymorphism.
12. **Code reuse** — biggest benefit of inheritance.

---

**Happy coding! 🚀**

*Inheritance promotes code reuse — but favor composition over inheritance when possible.*
