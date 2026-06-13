# 08 — Object Type Casting

> **Video:** 41–44  
> **Topics:** Mantras, Case Studies, Internal Mechanics, Scenarios

---

## Table of Contents

1. [3 Mantras of Object Type Casting](#1-3-mantras-of-object-type-casting)
2. [Case Studies](#2-case-studies)
3. [Internal Mechanics](#3-internal-mechanics)
4. [Different Scenarios](#4-different-scenarios)
5. [Interview Questions](#5-interview-questions)
6. [Quick Reference](#6-quick-reference)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. 3 Mantras of Object Type Casting

### The 3 Mantras

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    3 MANTRAS OF OBJECT TYPE CASTING                        │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  MANTRA 1: B IS-A A                                             │    │
│  │  ─────────────────────────────────────────────────────────────  │    │
│  │  If B extends A (or B implements A), then B IS-A A.             │    │
│  │  A a = new B();  // ✅ Valid (implicit upcasting)               │    │
│  │  B b = (B) a;    // ✅ Valid (explicit downcasting)              │    │
│  │                                                                  │    │
│  │  MANTRA 2: Compiler checks reference type                       │    │
│  │  ─────────────────────────────────────────────────────────────  │    │
│  │  The compiler checks if the reference type can hold the object  │    │
│  │  based on the IS-A relationship.                                 │    │
│  │  A a = new B();  // ✅ B IS-A A, so compiler allows              │    │
│  │  B b = new A();  // ❌ A is NOT B, compiler error                │    │
│  │                                                                  │    │
│  │  MANTRA 3: JVM checks object type at runtime                    │    │
│  │  ─────────────────────────────────────────────────────────────  │    │
│  │  If the cast is invalid at runtime, ClassCastException occurs.   │    │
│  │  A a = new B();                                                 │    │
│  │  C c = (C) a;    // ❌ ClassCastException at runtime             │    │
│  │  (a is actually B, not C)                                       │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### Mantra 1: B IS-A A

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

// ✅ Valid: Dog IS-A Animal
Animal a = new Dog();

// ✅ Valid: Cat IS-A Animal
Animal a2 = new Cat();

// ❌ Invalid: Animal is NOT Dog
// Dog d = new Animal();  // Compile error
```

### Mantra 2: Compiler Checks Reference Type

```java
class Parent { }
class Child extends Parent { }
class Sibling extends Parent { }

// Compiler checks: can Child be assigned to Parent?
// Yes, because Child IS-A Parent
Parent p = new Child();  // ✅ Compile-time valid

// Compiler checks: can Child be assigned to Sibling?
// No, because Child IS-NOT Sibling
// Sibling s = new Child();  // ❌ Compile error

// Downcasting: compiler checks if casting is possible
// Sibling s = (Sibling) new Child();  // ❌ Compile error
// Child IS-NOT Sibling, so compiler knows it's invalid
```

### Mantra 3: JVM Checks Object Type at Runtime

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

// Compiler allows: Animal can hold Dog
Animal a = new Dog();

// Compiler allows: Animal can hold Cat
// Cat c = (Cat) a;  // ❌ Compile error? No!
//                   // ✅ Compiler allows because Animal can hold Cat

// But at runtime: a is actually a Dog, not a Cat!
// Cat c = (Cat) a;  // ❌ ClassCastException at runtime!

// JVM checks: what is the actual object type?
// a is Dog, not Cat → Exception!
```

---

## 2. Case Studies

### Case Study 1: Basic Upcasting and Downcasting

```java
class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
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
        // Upcasting (implicit)
        Animal a = new Dog();  // ✅ Dog IS-A Animal
        a.makeSound();         // "Bark" — runtime polymorphism
        // a.fetch();          // ❌ Error — fetch() not in Animal
        
        // Downcasting (explicit)
        Dog d = (Dog) a;       // ✅ Valid because a is actually Dog
        d.fetch();             // ✅ Works
        
        // Invalid downcasting
        Animal a2 = new Animal();
        // Dog d2 = (Dog) a2;  // ❌ ClassCastException at runtime
        // a2 is actually Animal, not Dog
    }
}
```

### Case Study 2: instanceof Operator

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

public class Main {
    public static void main(String[] args) {
        Animal[] animals = { new Dog(), new Cat(), new Dog() };
        
        for (Animal a : animals) {
            // Safe downcasting with instanceof
            if (a instanceof Dog) {
                Dog d = (Dog) a;  // ✅ Safe
                System.out.println("It's a Dog");
            } else if (a instanceof Cat) {
                Cat c = (Cat) a;  // ✅ Safe
                System.out.println("It's a Cat");
            }
        }
    }
}
```

### Case Study 3: Object Casting with Interfaces

```java
interface Drawable {
    void draw();
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    public void calculateArea() {
        System.out.println("Area = πr²");
    }
}

public class Main {
    public static void main(String[] args) {
        // Upcasting to interface
        Drawable d = new Circle();  // ✅ Circle implements Drawable
        d.draw();                    // ✅ Interface method
        // d.calculateArea();        // ❌ Not in Drawable interface
        
        // Downcasting to concrete class
        Circle c = (Circle) d;     // ✅ Valid
        c.calculateArea();            // ✅ Works
    }
}
```

---

## 3. Internal Mechanics

### How JVM Handles Type Casting

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    INTERNAL MECHANICS OF TYPE CASTING                    │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  1. COMPILE-TIME CHECK                                          │    │
│  │     • Compiler checks: can reference type hold the object?      │    │
│  │     • Based on IS-A relationship                                │    │
│  │     • If no: compile error                                      │    │
│  │                                                                  │    │
│  │  2. RUNTIME CHECK                                               │    │
│  │     • JVM checks: what is the actual object type?              │    │
│  │     • Uses instanceof internally                                │    │
│  │     • If cast is invalid: ClassCastException                   │    │
│  │                                                                  │    │
│  │  3. CASTING PROCESS                                             │    │
│  │     • Upcasting: implicit, no runtime check needed             │    │
│  │     • Downcasting: explicit, runtime check required              │    │
│  │                                                                  │    │
│  │  Visual:                                                         │    │
│  │  ┌─────────────────────────────────────────────────────────┐    │    │
│  │  │  Animal a = new Dog();   // Upcasting                  │    │    │
│  │  │  │                                                         │    │    │
│  │  │  │  Stack: a (Animal reference)                              │    │    │
│  │  │  │  Heap:  Dog object                                      │    │    │
│  │  │  │                                                         │    │    │
│  │  │  Dog d = (Dog) a;        // Downcasting                  │    │    │
│  │  │  │                                                         │    │    │
│  │  │  │  Stack: d (Dog reference) ← points to same Dog object   │    │    │
│  │  │  │  Heap:  Dog object (no change)                         │    │    │
│  │  │  │                                                         │    │    │
│  │  │  Cat c = (Cat) a;        // ❌ Runtime error             │    │    │
│  │  │  │  JVM: "a is Dog, not Cat!"                             │    │    │
│  │  │  │  ClassCastException                                    │    │    │
│  │  │  └─────────────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 4. Different Scenarios

### Scenario 1: Upcasting

```java
class Parent { }
class Child extends Parent { }

// Implicit upcasting — always safe
Parent p = new Child();  // ✅ No explicit cast needed

// Child IS-A Parent, so always valid
// No runtime check needed
```

### Scenario 2: Downcasting

```java
class Parent { }
class Child extends Parent { }

Parent p = new Child();

// Explicit downcasting — may fail at runtime
Child c = (Child) p;  // ✅ Valid because p is actually Child

Parent p2 = new Parent();
// Child c2 = (Child) p2;  // ❌ ClassCastException at runtime
```

### Scenario 3: Cross-Casting

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

Animal a = new Dog();

// Compiler allows: Animal can hold Cat
// Cat c = (Cat) a;  // ❌ ClassCastException at runtime

// Why does compiler allow?
// Because Animal CAN hold Cat — compiler doesn't know runtime type
// Only JVM knows actual object type at runtime
```

### Scenario 4: Interface Casting

```java
interface Movable { void move(); }
class Car implements Movable {
    public void move() { System.out.println("Car moving"); }
    public void honk() { System.out.println("Honk"); }
}

Movable m = new Car();     // ✅ Upcasting
m.move();                   // ✅ Interface method

Car c = (Car) m;           // ✅ Downcasting
c.honk();                   // ✅ Now can access Car methods
```

---

## 5. Interview Questions

### Q1. What is the difference between upcasting and downcasting?

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Feature            │ Upcasting          │ Downcasting        │
├────────────────────┼────────────────────┼────────────────────┤
│ Direction          │ Child → Parent     │ Parent → Child     │
│ Syntax             │ Implicit           │ Explicit (cast)    │
│ Safety             │ Always safe        │ May fail at runtime│
│ Runtime check      │ Not needed         │ JVM checks         │
│ Risk               │ None               │ ClassCastException │
│ Example            │ Animal a = new Dog()│ Dog d = (Dog) a;│
│ Compiler           │ Allows (IS-A)      │ Allows (IS-A)      │
└────────────────────┴────────────────────┴────────────────────┘
```

---

### Q2. What is `instanceof` and why is it used?

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        
        // instanceof checks if object is of specified type
        System.out.println(a instanceof Animal); // true
        System.out.println(a instanceof Dog);      // true
        System.out.println(a instanceof Cat);     // false
        
        // Safe casting pattern
        if (a instanceof Dog) {
            Dog d = (Dog) a;  // ✅ Safe
        }
        
        // With null
        Animal a2 = null;
        System.out.println(a2 instanceof Animal); // false
        // null is not an instance of any type
    }
}
```

---

### Q3. What is `ClassCastException`?

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        
        // Compiler allows: Animal can hold Cat
        // But at runtime: a is actually Dog, not Cat!
        Cat c = (Cat) a;  // ❌ ClassCastException!
        
        // Exception message:
        // "Dog cannot be cast to Cat"
    }
}
```

---

### Q4. Can we cast a parent object to a child class?

```java
class Parent { }
class Child extends Parent { }

public class Main {
    public static void main(String[] args) {
        Parent p = new Parent();
        
        // Compiler allows: Parent can hold Child
        Child c = (Child) p;  // ❌ ClassCastException!
        
        // Why? p is actually Parent, not Child!
        // JVM checks at runtime: what is the actual object?
        // p is Parent → cannot cast to Child
        
        // ✅ Valid downcasting only works if parent reference
        //    actually holds a child object
        Parent p2 = new Child();
        Child c2 = (Child) p2;  // ✅ Valid
    }
}
```

---

## 6. Quick Reference

### Type Casting Rules

```
Upcasting:      Parent p = new Child();  // Implicit, always safe
Downcasting:    Child c = (Child) p;     // Explicit, may fail

Safe downcasting:
    if (p instanceof Child) {
        Child c = (Child) p;
    }

Dangerous:
    Child c = (Child) new Parent();  // ❌ ClassCastException
```

### instanceof Operator

```
object instanceof Type

Returns true if:
    - object is of type Type
    - object is of subclass of Type
    - object is null → false

if (a instanceof Dog) {
    Dog d = (Dog) a;  // Safe
}
```

---

## 7. Key Takeaways

1. **Mantra 1: B IS-A A** — if B extends A, then B can be assigned to A.
2. **Mantra 2: Compiler checks reference type** — IS-A relationship at compile time.
3. **Mantra 3: JVM checks object type** — actual object at runtime.
4. **Upcasting** = implicit, always safe, no runtime check.
5. **Downcasting** = explicit, may fail, JVM checks at runtime.
6. **`instanceof`** — checks if object is of specified type before casting.
7. **`ClassCastException`** — occurs when downcasting to incompatible type.
8. **Cross-casting** = compiler allows (IS-A), but JVM may reject (wrong type).
9. **Interface casting** = same rules as class casting.
10. **Null** is not an instance of any type — `null instanceof X` returns false.
11. **Upcasting enables polymorphism** — parent reference holds child object.
12. **Downcasting enables child-specific access** — but must be safe.

---

**Happy coding! 🚀**

*Type casting is the bridge between compile-time types and runtime objects — use it safely with `instanceof`.*
