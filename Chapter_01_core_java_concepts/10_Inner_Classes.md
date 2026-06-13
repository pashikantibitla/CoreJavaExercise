# 10 — Inner Classes

> **Videos:** 100+  
> **Topics:** Introduction, Types of Inner Classes, Member Inner Class, Static Inner Class, Local Inner Class, Anonymous Inner Class

---

## Table of Contents

1. [Introduction to Inner Classes](#1-introduction-to-inner-classes)
2. [Member Inner Class](#2-member-inner-class)
3. [Static Inner Class](#3-static-inner-class)
4. [Local Inner Class](#4-local-inner-class)
5. [Anonymous Inner Class](#5-anonymous-inner-class)
6. [Interview Questions](#6-interview-questions)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. Introduction to Inner Classes

### What is an Inner Class?

```java
// Inner Class: Class defined within another class
// Outer class (enclosing) → Inner class (nested)

class Outer {
    // Outer class members
    private int outerVar = 10;
    
    // Inner class
    class Inner {
        // Inner class members
        private int innerVar = 20;
        
        void display() {
            // Access outer class members
            System.out.println("Outer var: " + outerVar);
            System.out.println("Inner var: " + innerVar);
        }
    }
}

// Usage:
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();  // Create inner object
inner.display();

// Class file: Outer.class, Outer$Inner.class
```

### Types of Inner Classes

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TYPES OF INNER CLASSES                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Member Inner Class (Non-static)                           │   │
│  │    - Defined inside class, outside methods                   │   │
│  │    - Has access to all outer class members                  │   │
│  │    - Can be public, private, protected, default             │   │
│  │                                                             │   │
│  │ 2. Static Inner Class (Nested Class)                       │   │
│  │    - Defined with static keyword                            │   │
│  │    - Only access static members of outer class              │   │
│  │    - Can be accessed without outer object                   │   │
│  │                                                             │   │
│  │ 3. Local Inner Class                                        │   │
│  │    - Defined inside method/block                            │   │
│  │    - Only accessible within that method/block               │   │
│  │    - Can access final/effectively final local variables     │   │
│  │                                                             │   │
│  │ 4. Anonymous Inner Class                                    │   │
│  │    - No name, defined at point of use                       │   │
│  │    - Extends class or implements interface                  │   │
│  │    - Single-use class                                       │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Member Inner Class

### Member Inner Class

```java
class Outer {
    private int x = 10;
    private static int y = 20;
    
    // Member Inner Class (non-static)
    public class Inner {
        private int z = 30;
        
        void display() {
            System.out.println(x);  // ✅ Access outer instance
            System.out.println(y);  // ✅ Access outer static
            System.out.println(z);  // ✅ Access own instance
        }
        
        void modifyOuter() {
            x = 100;  // ✅ Can modify outer instance variable
        }
    }
    
    void createInner() {
        Inner inner = new Inner();  // ✅ Inside outer class
        inner.display();
    }
}

// Usage from outside:
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();  // Need outer object
inner.display();

// Without outer object: ❌
// Outer.Inner inner = new Outer.Inner();  // ❌ ERROR
// Outer.Inner inner = new Outer().new Inner();  // ✅
```

### Accessing Outer Members

```java
class Outer {
    private int x = 10;
    
    class Inner {
        private int x = 20;
        
        void display() {
            System.out.println(x);           // 20 (inner)
            System.out.println(this.x);      // 20 (inner)
            System.out.println(Outer.this.x); // 10 (outer)
        }
    }
}

// Outer.this.x → Access outer class member
// this.x → Access inner class member
```

---

## 3. Static Inner Class

### Static Inner Class (Nested Class)

```java
class Outer {
    private int x = 10;
    private static int y = 20;
    
    // Static Inner Class
    public static class Inner {
        private int z = 30;
        
        void display() {
            // System.out.println(x);  // ❌ ERROR: non-static cannot access
            System.out.println(y);    // ✅ Access outer static
            System.out.println(z);    // ✅ Access own instance
        }
        
        static void staticMethod() {
            System.out.println(y);    // ✅ Access outer static
            // System.out.println(z);  // ❌ ERROR: non-static from static
        }
    }
}

// Usage from outside:
// No need for outer object
Outer.Inner inner = new Outer.Inner();  // ✅
inner.display();

// Static inner class can have static members
// Member inner class CANNOT have static members (except constants)
```

---

## 4. Local Inner Class

### Local Inner Class

```java
class Outer {
    private int x = 10;
    
    void method() {
        int localVar = 20;
        final int finalVar = 30;
        
        // Local Inner Class (inside method)
        class LocalInner {
            void display() {
                System.out.println(x);         // ✅ Access outer
                System.out.println(finalVar);   // ✅ Access final local
                // System.out.println(localVar); // ❌ Before Java 8
                // From Java 8: effectively final (no modification)
            }
        }
        
        LocalInner local = new LocalInner();
        local.display();
    }
    
    void block() {
        if (true) {
            // Local Inner Class inside block
            class BlockInner {
                void display() {
                    System.out.println("Block inner");
                }
            }
            
            BlockInner inner = new BlockInner();
            inner.display();
        }
        // inner.display();  // ❌ Out of scope
    }
}

// Local inner class is only accessible within the method/block
```

---

## 5. Anonymous Inner Class

### Anonymous Inner Class

```java
// Anonymous Inner Class: No name, defined at point of use

// 1. Extending a class
class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Test {
    public static void main(String[] args) {
        // Anonymous class extending Animal
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Dog barking");
            }
            
            void fetch() {  // Can add new methods
                System.out.println("Fetching");
            }
        };
        
        dog.makeSound();  // "Dog barking"
        // dog.fetch();   // ❌ ERROR: Animal reference doesn't have fetch()
    }
}

// 2. Implementing an interface
interface Runnable {
    void run();
}

Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running");
    }
};

// 3. With arguments
class Person {
    String name;
    Person(String name) {
        this.name = name;
    }
    void greet() {
        System.out.println("Hello");
    }
}

Person person = new Person("Alice") {
    @Override
    void greet() {
        System.out.println("Hello, " + name);
    }
};

// 4. Lambda expression (Java 8+)
Runnable r = () -> System.out.println("Running");
```

### Anonymous Inner Class with Abstract Class

```java
abstract class Vehicle {
    abstract void drive();
    
    void start() {
        System.out.println("Starting");
    }
}

Vehicle car = new Vehicle() {
    @Override
    void drive() {
        System.out.println("Driving car");
    }
};

car.start();   // "Starting"
car.drive();   // "Driving car"

// Class file: Test$1.class, Test$2.class (anonymous classes)
```

---

## 6. Interview Questions

### Q1. What is the difference between inner class and nested class?

```java
// Inner class: Non-static, belongs to instance
// Nested class: Static, belongs to class

// Inner class: Outer.Inner inner = outer.new Inner();
// Nested class: Outer.Inner inner = new Outer.Inner();

// Inner class can access instance members
// Nested class can only access static members

// Terminology:
// Inner class = Member inner class + Local inner class + Anonymous inner class
// Nested class = Static inner class (also called nested class)
```

### Q2. Can inner class have static members?

```java
// Member inner class: NO static members (except constants)
class Outer {
    class Inner {
        // static int x = 10;  // ❌ ERROR (Java 7)
        static final int CONSTANT = 10;  // ✅ Constant allowed
    }
}

// Static inner class: YES static members
class Outer {
    static class Inner {
        static int x = 10;  // ✅
        static void method() { }  // ✅
    }
}

// Java 16+: Member inner class can have static members
```

### Q3. How to access outer class variable from inner class?

```java
class Outer {
    int x = 10;
    
    class Inner {
        int x = 20;
        
        void display() {
            System.out.println(x);           // 20 (inner)
            System.out.println(this.x);      // 20 (inner)
            System.out.println(Outer.this.x); // 10 (outer)
        }
    }
}
```

### Q4. What is the difference between local inner class and anonymous inner class?

```java
// Local inner class:
// - Has a name
// - Can have constructor
// - Can implement multiple methods
// - Can be reused within the method

// Anonymous inner class:
// - No name
// - No constructor
// - Can only implement one interface or extend one class
// - Single use

// Use local when: multiple methods, need constructor
// Use anonymous when: single method, simple implementation
```

### Q5. Can we declare inner class as private?

```java
// ✅ YES
class Outer {
    private class Inner {
        void display() {
            System.out.println("Private inner");
        }
    }
    
    void useInner() {
        Inner inner = new Inner();  // ✅ Accessible within outer
        inner.display();
    }
}

// Inner class can be: public, private, protected, default
// Outer class can only be: public or default
```

### Q6. What is the class file name for inner class?

```java
// Outer.java
class Outer {
    class Inner { }
    static class StaticInner { }
}

// Compilation:
// Outer.class
// Outer$Inner.class
// Outer$StaticInner.class

// Anonymous classes:
// Outer$1.class, Outer$2.class, etc.
```

### Q7. Can we have inner interface?

```java
// ✅ YES
class Outer {
    interface InnerInterface {
        void method();
    }
    
    class Inner implements InnerInterface {
        public void method() {
            System.out.println("Implemented");
        }
    }
}

// Interface inside interface:
interface OuterInterface {
    interface InnerInterface {
        void method();
    }
}

// Class file: Outer$InnerInterface.class
```

### Q8. Can inner class extend outer class?

```java
// ✅ YES
class Outer {
    class Inner extends Outer {
        // Inner extends Outer
        // Inner IS-A Outer
        // Also Inner is inside Outer
    }
}

// Not common but valid
// Can cause confusion, avoid in practice
```

---

## 7. Key Takeaways

1. **Inner class**: Defined inside another class; has access to outer members.
2. **Member inner class**: Non-static; needs outer object to create.
3. **Static inner class**: Like top-level class; only access static outer members.
4. **Local inner class**: Inside method/block; only accessible within scope.
5. **Anonymous inner class**: No name; single use; extends class or implements interface.
6. **Class file**: Outer.class, Outer$Inner.class, Outer$1.class (anonymous).
7. **Inner class** can access all outer members (private too).
8. **Static inner class** can only access static outer members.
9. **Local inner class** can access final/effectively final local variables.
10. **Anonymous inner class** cannot have constructor (no name).
11. **Inner class** can be private, protected, public, default.
12. **Outer class** can only be public or default.
13. **Inner interface** is valid; can be implemented by inner class.
14. **Member inner class** cannot have static members (except constants before Java 16).
15. **Lambda** (Java 8+) can replace anonymous inner class with single method.

---

**Happy coding! 🚀**

*Inner classes provide better encapsulation and logical grouping — use them when a class is only useful to its outer class.*
