# 03 — Abstraction

> **Video:** 6–8, 12, 14  
> **Topics:** Abstract Methods, Abstract Classes, Interfaces, Abstraction

---

## Table of Contents

1. [Abstract Method](#1-abstract-method)
2. [Abstract Class](#2-abstract-class)
3. [Abstract Class vs Abstract Method](#3-abstract-class-vs-abstract-method)
4. [Interface](#4-interface)
5. [Interview Questions](#5-interview-questions)
6. [Quick Reference](#6-quick-reference)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. Abstract Method

### What is an Abstract Method?

An abstract method is a method that has **only a declaration** but **no implementation** (body). It must be declared inside an abstract class or interface.

```java
// Abstract method declaration
public abstract void draw();  // No body — just semicolon

// ❌ Abstract method cannot have a body
// public abstract void draw() { }  // Compile error
```

### Characteristics

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    ABSTRACT METHOD CHARACTERISTICS                        │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Feature            │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ Declaration only   │ No body, ends with semicolon                  │  │
│  │ abstract keyword   │ Must be declared with abstract                  │  │
│  │ Must be overridden │ Child class must provide implementation         │  │
│  │ Cannot be private  │ Private methods cannot be overridden           │  │
│  │ Cannot be static   │ Static methods cannot be overridden           │  │
│  │ Cannot be final    │ Final methods cannot be overridden           │  │
│  │ Must be in abstract│ Enclosing class must be abstract               │  │
│  │   class/interface  │                                                │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
abstract class Shape {
    // Abstract method
    public abstract void draw();
    
    // Concrete method
    public void display() {
        System.out.println("Displaying shape");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}

class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Circle();
        Shape s2 = new Rectangle();
        
        s1.draw();  // "Drawing circle"
        s2.draw();  // "Drawing rectangle"
    }
}
```

---

## 2. Abstract Class

### What is an Abstract Class?

An abstract class is a class that **cannot be instantiated** and may contain both abstract and concrete methods. It is meant to be extended by subclasses.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    ABSTRACT CLASS CHARACTERISTICS                          │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Feature            │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ Cannot instantiate │ new Shape() — ❌ Compile error                 │  │
│  │ Can have abstract  │ methods with no body                         │  │
│  │ Can have concrete  │ methods with body                              │  │
│  │ Can have fields    │ instance and static variables                  │  │
│  │ Can have constructor│ Yes, called when subclass is instantiated     │  │
│  │ Must be extended   │ Child class must override all abstract methods  │  │
│  │ Can extend another │ abstract class or concrete class              │  │
│  │ class              │                                                │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
abstract class Vehicle {
    // Fields
    private String brand;
    private int year;
    
    // Constructor
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    // Abstract method
    public abstract void start();
    
    // Concrete method
    public void stop() {
        System.out.println("Vehicle stopped");
    }
    
    // Getter
    public String getBrand() {
        return brand;
    }
}

class Car extends Vehicle {
    public Car(String brand, int year) {
        super(brand, year);
    }
    
    @Override
    public void start() {
        System.out.println("Car starting with key ignition");
    }
}

class Bike extends Vehicle {
    public Bike(String brand, int year) {
        super(brand, year);
    }
    
    @Override
    public void start() {
        System.out.println("Bike starting with kick");
    }
}

public class Main {
    public static void main(String[] args) {
        // Vehicle v = new Vehicle(); // ❌ Error
        Vehicle car = new Car("Toyota", 2023);
        Vehicle bike = new Bike("Yamaha", 2022);
        
        car.start();  // "Car starting with key ignition"
        bike.start(); // "Bike starting with kick"
    }
}
```

---

## 3. Abstract Class vs Abstract Method

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    ABSTRACT CLASS vs ABSTRACT METHOD                     │
│  ┌────────────────────┬────────────────────┬─────────────────────────┐  │
│  │ Feature            │ Abstract Class     │ Abstract Method           │  │
│  ├────────────────────┼────────────────────┼─────────────────────────┤  │
│  │ Declaration        │ abstract class A {}│ public abstract void m();│  │
│  │ Can have body?     │ Yes (concrete methods)│ No (just declaration) │  │
│  │ Can be instantiated│ No                 │ N/A                       │  │
│  │ Must be overridden │ No (only abstract │ Yes (by child class)       │  │
│  │                    │   methods must be) │                           │  │
│  │ Can be final?      │ No                 │ No                        │  │
│  │ Can be static?     │ N/A                │ No                        │  │
│  │ Can be private?    │ N/A                │ No                        │  │
│  └────────────────────┴────────────────────┴─────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 4. Interface

### What is an Interface?

An interface is a **100% abstract class** (before Java 8) that defines a contract for what a class can do. It specifies methods that implementing classes must provide.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    INTERFACE CHARACTERISTICS                             │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Feature            │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ Methods (Java 8+)  │ Default, static, abstract                       │  │
│  │ Fields             │ public static final (constants)                │  │
│  │ Cannot instantiate │ No constructors                                │  │
│  │ Multiple inheritance│ A class can implement multiple interfaces    │  │
│  │ IS-A relationship  │ class implements Interface                     │  │
│  │ 100% abstraction   │ Before Java 8 (now partial with default)       │  │
│  │ Public methods     │ All methods are implicitly public               │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
// Interface definition
interface Drawable {
    // Implicitly: public static final
    int MAX_SIZE = 1000;
    
    // Abstract method (implicitly public)
    void draw();
    
    // Default method (Java 8+)
    default void print() {
        System.out.println("Printing...");
    }
    
    // Static method (Java 8+)
    static void showInfo() {
        System.out.println("Drawable interface");
    }
}

// Implementation
class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}

class Square implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing square");
    }
}

public class Main {
    public static void main(String[] args) {
        Drawable d1 = new Circle();
        Drawable d2 = new Square();
        
        d1.draw();  // "Drawing circle"
        d2.draw();  // "Drawing square"
        
        d1.print(); // "Printing..." (default method)
        Drawable.showInfo(); // "Drawable interface"
    }
}
```

### Interface with Multiple Inheritance

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

// Multiple inheritance through interfaces
class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }
}
```

---

## 5. Interview Questions

### Q1. Can an abstract class have a constructor?

```java
// ✅ YES — abstract class can have a constructor

abstract class Animal {
    private String name;
    
    // Constructor
    public Animal(String name) {
        this.name = name;
    }
    
    public abstract void makeSound();
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);  // Calls abstract class constructor
    }
    
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

// Note: Constructor is called when child is instantiated
// Even though abstract class cannot be instantiated directly
```

---

### Q2. Can an abstract class be final?

```java
// ❌ NO — abstract and final are mutually exclusive

public abstract final class MyClass { }  // ❌ Compile error

// abstract = must be extended
// final = cannot be extended
// Contradiction!
```

---

### Q3. Can an abstract class have only concrete methods?

```java
// ✅ YES — abstract class can have zero abstract methods

abstract class Base {
    public void method1() {
        System.out.println("Concrete method 1");
    }
    
    public void method2() {
        System.out.println("Concrete method 2");
    }
}

// This class cannot be instantiated directly
// But can be extended
class Child extends Base {
    // Can override or use as-is
}

// Use case: Prevent direct instantiation of a utility-like class
```

---

### Q4. Can we create an object of an abstract class?

```java
// ❌ NO — abstract class cannot be instantiated

abstract class Animal {
    public abstract void makeSound();
}

public class Main {
    public static void main(String[] args) {
        // Animal a = new Animal(); // ❌ Compile error
        // "Animal is abstract; cannot be instantiated"
        
        // ✅ But can have reference type
        Animal a = new Dog();  // ✅ Polymorphism
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}
```

---

### Q5. What is the difference between abstract class and interface?

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Feature            │ Abstract Class     │ Interface          │
├────────────────────┼────────────────────┼────────────────────┤
│ Methods            │ Abstract + Concrete│ Abstract + Default │
│                    │                    │ + Static           │
│ Fields             │ Any type           │ public static final│
│ Constructor        │ Yes                │ No                 │
│ Multiple inheritance│ No (extends one)  │ Yes (implements    │
│                    │                    │ many)              │
│ Access modifiers   │ Any                │ public (implicit)  │
│ Use case           │ IS-A with shared   │ IS-A with contract│
│                    │ code               │ only               │
│ Keyword            │ extends            │ implements         │
│ Java version       │ Since Java 1.0     │ Since Java 1.0     │
│ 100% abstraction   │ No (can have       │ Yes (before Java 8)│
│                    │ concrete methods)  │ Partial (Java 8+)  │
└────────────────────┴────────────────────┴────────────────────┘
```

---

## 6. Quick Reference

### Abstract Class Syntax

```java
abstract class ClassName {
    // Fields
    // Concrete methods
    // Abstract methods
    // Constructors
}

class Child extends ClassName {
    // Must override all abstract methods
}
```

### Interface Syntax

```java
interface InterfaceName {
    // Constants (public static final)
    // Abstract methods (public)
    // Default methods (Java 8+)
    // Static methods (Java 8+)
}

class Implementer implements InterfaceName {
    // Must implement all abstract methods
}
```

---

## 7. Key Takeaways

1. **Abstract method** = declaration only, no body, must be overridden.
2. **Abstract class** = cannot be instantiated, may have abstract + concrete methods.
3. **Interface** = contract for behavior, class implements it, supports multiple inheritance.
4. **Child class MUST override all abstract methods** from parent abstract class.
5. **Abstract class can have constructor** — called when child is instantiated.
6. **Interface cannot have constructors** — no instantiation.
7. **Interface fields are implicitly `public static final`** — constants only.
8. **Java 8+ allows default and static methods** in interfaces.
9. **Abstract class = IS-A with shared code**, Interface = IS-A with shared contract.
10. **Multiple inheritance** is achieved through interfaces, not classes.

---

**Happy coding! 🚀**

*Abstraction hides complexity — use abstract classes and interfaces to build flexible systems.*
