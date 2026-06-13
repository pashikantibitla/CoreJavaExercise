# 07 — Polymorphism

> **Video:** 30–34, 39–40  
> **Topics:** Method Overriding Rules, Polymorphism Summary, 3 Pillars of OOPs

---

## Table of Contents

1. [Method Overriding Rules](#1-method-overriding-rules)
2. [Polymorphism Overview](#2-polymorphism-overview)
3. [Compile-Time Polymorphism](#3-compile-time-polymorphism)
4. [Runtime Polymorphism](#4-runtime-polymorphism)
5. [3 Pillars of OOPs](#5-3-pillars-of-oops)
6. [Interview Questions](#6-interview-questions)
7. [Quick Reference](#7-quick-reference)
8. [Key Takeaways](#8-key-takeaways)

---

## 1. Method Overriding Rules

### Complete Rules for Overriding

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    METHOD OVERRIDING RULES                                │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Rule               │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ 1. Same name       │ Method name must be identical                  │  │
│  │ 2. Same parameters │ Number, type, and order must be identical      │  │
│  │ 3. Return type     │ Same or covariant (subclass of return type)    │  │
│  │ 4. Access modifier │ Cannot be more restrictive                     │  │
│  │ 5. Exceptions      │ Cannot throw new or broader checked exceptions │  │
│  │ 6. static method   │ Cannot override (only hidden)                  │  │
│  │ 7. final method    │ Cannot override                                │  │
│  │ 8. private method  │ Cannot override (not inherited)                │  │
│  │ 9. @Override       │ Recommended annotation                         │  │
│  │ 10. super          │ Can call parent method using super.method()    │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

### Rule Examples

```java
class Parent {
    public Object getValue() { return null; }       // Rule 3: return type
    protected void show() { }                       // Rule 4: access modifier
    public void process() throws IOException { }    // Rule 5: exceptions
    public static void display() { }                 // Rule 6: static
    public final void lock() { }                     // Rule 7: final
    private void secret() { }                        // Rule 8: private
}

class Child extends Parent {
    // Rule 3: Covariant return type
    @Override
    public String getValue() { return "Child"; }  // ✅ String is Object
    
    // Rule 4: Less restrictive
    @Override
    public void show() { }  // ✅ public > protected
    
    // Rule 5: Same or narrower exception
    @Override
    public void process() throws FileNotFoundException { }  // ✅ Subclass
    
    // Rule 6: static
    // public static void display() { }  // ❌ Hiding, not overriding
    
    // Rule 7: final
    // @Override
    // public void lock() { }  // ❌ Compile error
    
    // Rule 8: private
    // private void secret() { }  // Not overriding — new method
}
```

---

## 2. Polymorphism Overview

### What is Polymorphism?

Polymorphism means **"many forms"**. In Java, it allows one interface to be used for a general class of actions, with the specific action determined by the exact nature of the situation.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    POLYMORPHISM TYPES                                      │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  1. Compile-Time Polymorphism (Static)                            │    │
│  │     • Method Overloading                                          │    │
│  │     • Resolved at compile time                                    │    │
│  │     • Faster execution (no runtime lookup)                       │    │
│  │                                                                  │    │
│  │  2. Runtime Polymorphism (Dynamic)                                │    │
│  │     • Method Overriding                                          │    │
│  │     • Resolved at runtime                                        │    │
│  │     • Slightly slower (virtual method lookup)                    │    │
│  │                                                                  │    │
│  │  Visual:                                                          │    │
│  │  ┌─────────────────────────────────────────────────────────┐    │    │
│  │  │  Animal a = new Dog();                                    │    │    │
│  │  │  a.makeSound();  // Runtime: calls Dog.makeSound()      │    │    │
│  │  │                                                          │    │    │
│  │  │  Calculator.add(2, 3);     // Compile-time: int version  │    │    │
│  │  │  Calculator.add(2.5, 3.5); // Compile-time: double version│    │    │
│  │  └─────────────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Compile-Time Polymorphism

### Method Overloading

```java
class Calculator {
    // Compile-time polymorphism
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        // All resolved at compile time
        System.out.println(calc.add(2, 3));        // int version
        System.out.println(calc.add(2.5, 3.5));    // double version
        System.out.println(calc.add("Hello", "World")); // String version
        System.out.println(calc.add(1, 2, 3));     // 3-parameter version
    }
}
```

---

## 4. Runtime Polymorphism

### Method Overriding with Dynamic Dispatch

```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

class Cow extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cow moos");
    }
}

public class Main {
    public static void main(String[] args) {
        // Runtime polymorphism
        Animal animal;
        
        animal = new Dog();
        animal.makeSound();  // "Dog barks" — decided at runtime
        
        animal = new Cat();
        animal.makeSound();  // "Cat meows" — decided at runtime
        
        animal = new Cow();
        animal.makeSound();  // "Cow moos" — decided at runtime
    }
}
```

### Virtual Method Table

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    VIRTUAL METHOD TABLE (VMT)                            │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  Animal VMT: makeSound() → Animal.makeSound()                   │    │
│  │  Dog VMT:    makeSound() → Dog.makeSound()                       │    │
│  │  Cat VMT:    makeSound() → Cat.makeSound()                       │    │
│  │                                                                  │    │
│  │  At runtime:                                                    │    │
│  │  Animal a = new Dog();                                           │    │
│  │  a.makeSound() → JVM looks at Dog's VMT → calls Dog.makeSound()  │    │
│  │                                                                  │    │
│  │  Animal a = new Cat();                                           │    │
│  │  a.makeSound() → JVM looks at Cat's VMT → calls Cat.makeSound()  │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  JVM uses the VMT of the ACTUAL object (not the reference type)        │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 5. 3 Pillars of OOPs

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    3 PILLARS OF OOPs                                    │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  1. Inheritance                                                   │    │
│  │     • IS-A relationship                                          │    │
│  │     • Code reuse                                                 │    │
│  │     • Extends keyword                                            │    │
│  │                                                                  │    │
│  │  2. Polymorphism                                                  │    │
│  │     • Many forms                                                 │    │
│  │     • Overloading + Overriding                                   │    │
│  │     • Compile-time + Runtime                                     │    │
│  │                                                                  │    │
│  │  3. Encapsulation                                                  │    │
│  │     • Data hiding                                                │    │
│  │     • Private fields + getters/setters                           │    │
│  │     • Bundling data + methods                                    │    │
│  │                                                                  │    │
│  │  Relationship:                                                     │    │
│  │  ┌─────────────────────────────────────────────────────────┐    │    │
│  │  │  Inheritance provides the structure                       │    │    │
│  │  │  Polymorphism provides the flexibility                     │    │    │
│  │  │  Encapsulation provides the protection                     │    │    │
│  │  │                                                          │    │    │
│  │  │  Together: Inheritance → IS-A → Polymorphism → Encapsulation│    │    │
│  │  └─────────────────────────────────────────────────────────┘    │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 6. Interview Questions

### Q1. What is the difference between compile-time and runtime polymorphism?

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Feature            │ Compile-Time       │ Runtime            │
├────────────────────┼────────────────────┼────────────────────┤
│ Also called        │ Static/Early binding│ Dynamic/Late binding│
│ Mechanism          │ Method Overloading │ Method Overriding  │
│ Resolution time    │ Compile time       │ Runtime            │
│ Method call        │ Based on reference │ Based on object type│
│ Performance        │ Faster             │ Slightly slower    │
│ Flexibility        │ Less               │ More               │
│ Example            │ Calculator.add()   │ Animal.makeSound() │
└────────────────────┴────────────────────┴────────────────────┘
```

---

### Q2. Can we achieve runtime polymorphism with data members?

```java
class Parent {
    public String name = "Parent";
    public void show() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    public String name = "Child";
    @Override
    public void show() {
        System.out.println("Child method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        
        System.out.println(p.name);   // "Parent" — runtime polymorphism
        p.show();                     // "Child method" — runtime polymorphism
        
        // Data members are NOT overridden
        // They are resolved by reference type (compile-time)
        // Methods ARE overridden and resolved by object type (runtime)
    }
}
```

---

### Q3. Can we override a constructor?

```java
// ❌ NO — constructors are not inherited

class Parent {
    public Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    public Child() {
        // super(); — implicitly called
        System.out.println("Child constructor");
    }
}

// Constructors are not inherited, so they cannot be overridden
// But child can call parent constructor using super()
```

---

### Q4. What is the output of the following code?

```java
class Parent {
    public void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    public void show() {
        System.out.println("Child");
    }
    
    public void display() {
        System.out.println("Child display");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        p.show();      // "Child" — runtime polymorphism
        // p.display(); // ❌ Error — display() not in Parent
        
        // To call display():
        // Child c = (Child) p;
        // c.display();  // ✅ Works
    }
}
```

---

## 7. Quick Reference

### Polymorphism Types

```
Compile-Time (Static)          Runtime (Dynamic)
─────────────────────────      ──────────────────────
Method Overloading             Method Overriding
Resolved by compiler            Resolved by JVM
Faster                          Slightly slower
Less flexible                   More flexible
```

### Overriding Checklist

```
✅ Same method name
✅ Same parameter list
✅ Same or covariant return type
✅ Access modifier: same or less restrictive
✅ Exceptions: same or narrower
✅ static method → hiding (not overriding)
✅ final method → cannot override
✅ private method → not inherited
✅ Use @Override annotation
```

---

## 8. Key Takeaways

1. **Polymorphism = "many forms"** — one interface, multiple implementations.
2. **Compile-time polymorphism** = method overloading — resolved by compiler.
3. **Runtime polymorphism** = method overriding — resolved by JVM at runtime.
4. **JVM uses VMT** (Virtual Method Table) to resolve method calls at runtime.
5. **Data members are NOT overridden** — resolved by reference type.
6. **Methods ARE overridden** — resolved by object type (runtime).
7. **Constructors cannot be overridden** — they are not inherited.
8. **3 Pillars of OOPs:** Inheritance, Polymorphism, Encapsulation.
9. **Inheritance provides structure**, Polymorphism provides flexibility, Encapsulation provides protection.
10. **Parent reference can hold child object** — but can only call parent methods.
11. **Type casting** needed to access child-specific methods from parent reference.
12. **Final and static methods** cannot be overridden — only hidden.

---

**Happy coding! 🚀**

*Polymorphism is the heart of object-oriented design — it makes code flexible and extensible.*
