# 26 — Inner Classes (Complete Guide)

> **Topics:** Normal Inner Class, Method Local Inner Class, Anonymous Inner Class, Static Nested Class, Nested Interfaces, Interview FAQs

---

## Table of Contents

1. [Introduction to Inner Classes](#1-introduction-to-inner-classes)
2. [Normal/Regular Inner Class](#2-normalregular-inner-class)
3. [Method Local Inner Class](#3-method-local-inner-class)
4. [Anonymous Inner Class](#4-anonymous-inner-class)
5. [Static Nested Class](#5-static-nested-class)
6. [Nested Interfaces](#6-nested-interfaces)
7. [Accessing Outer Class Members](#7-accessing-outer-class-members)
8. [Interview FAQs](#8-interview-faqs)
9. [Key Takeaways](#9-key-takeaways)

---

## 1. Introduction to Inner Classes

### What are Inner Classes?

An inner class is a class that is defined inside another class. Inner classes are a feature of Java that provides a way to logically group classes that are only used in one place, thereby increasing encapsulation and readability.

```
┌──────────────────────────────────────────────────────────────┐
│              INNER CLASS TYPES IN JAVA                       │
│                                                               │
│   ┌─────────────────────────────────────────────────────────┐│
│   │  Outer Class                                             ││
│   │  ┌─────────────────────────────────────────────────────┐  ││
│   │  │  1. Normal/Regular Inner Class                     │  ││
│   │  │     (declared directly inside outer class)         │  ││
│   │  ├─────────────────────────────────────────────────────┤  ││
│   │  │  2. Method Local Inner Class                       │  ││
│   │  │     (declared inside a method)                       │  ││
│   │  ├─────────────────────────────────────────────────────┤  ││
│   │  │  3. Anonymous Inner Class                          │  ││
│   │  │     (no name, declared & instantiated together)    │  ││
│   │  ├─────────────────────────────────────────────────────┤  ││
│   │  │  4. Static Nested Class                            │  ││
│   │  │     (declared with static inside outer class)      │  ││
│   │  └─────────────────────────────────────────────────────┘  ││
│   └─────────────────────────────────────────────────────────┘│
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Why Use Inner Classes?

1. **Encapsulation:** Inner classes can access private members of the outer class, and vice versa.
2. **Logical Grouping:** Classes that are only used in one place can be defined inside the class that uses them.
3. **Code Organization:** Keeps related code together, reducing the number of top-level classes.
4. **Callback Mechanisms:** Anonymous inner classes are perfect for event handling and callbacks.
5. **Readability:** The relationship between outer and inner class is clear from the code structure.

### Compiling Inner Classes

When you compile a Java file containing inner classes, the compiler generates separate `.class` files:

```
OuterClass.java
  → OuterClass.class
  → OuterClass$InnerClass.class
  → OuterClass$1.class (anonymous inner class)
  → OuterClass$1LocalClass.class (method local inner class)
```

---

## 2. Normal/Regular Inner Class

### What is a Normal Inner Class?

A normal (or regular) inner class is a non-static class declared inside another class at the member level. It acts like a member of the outer class and has access to all members (including private) of the outer class.

```
┌──────────────────────────────────────────────────────────────┐
│              NORMAL INNER CLASS CHARACTERISTICS              │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Feature            │ Description                          │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ Declaration        │ Inside outer class, outside methods    │  │
│  │ Access modifier    │ Can be public, private, protected,   │  │
│  │                    │ or default                           │  │
│  │ Static members     │ Cannot declare static members (except│  │
│  │                    │ static final constants)              │  │
│  │ Outer access       │ Can access all outer class members   │  │
│  │ Instantiation      │ Requires outer class instance        │  │
│  │                    │ Outer.Inner obj = outer.new Inner(); │  │
│  │ .class file        │ OuterClass$InnerClass.class          │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Basic Syntax

```java
class Outer {
    private int x = 10;
    
    // Normal inner class
    class Inner {
        void display() {
            System.out.println("Outer x = " + x); // Access private outer member
        }
    }
    
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner(); // Instantiation syntax
        inner.display(); // Outer x = 10
    }
}
```

### Accessing Outer Class Members

```java
class Outer {
    private int x = 10;
    static int y = 20;
    
    class Inner {
        private int x = 100; // Shadowing outer x
        
        void display() {
            System.out.println("Inner x = " + x);           // 100
            System.out.println("Outer x = " + Outer.this.x);  // 10
            System.out.println("Outer y = " + y);             // 20
        }
    }
    
    public static void main(String[] args) {
        Outer.Inner inner = new Outer().new Inner();
        inner.display();
    }
}
```

### Inner Class with Access Modifiers

```java
class Outer {
    public class PublicInner {
        void show() { System.out.println("Public Inner"); }
    }
    
    private class PrivateInner {
        void show() { System.out.println("Private Inner"); }
    }
    
    protected class ProtectedInner {
        void show() { System.out.println("Protected Inner"); }
    }
    
    class DefaultInner {
        void show() { System.out.println("Default Inner"); }
    }
    
    public void accessPrivateInner() {
        PrivateInner pi = new PrivateInner();
        pi.show(); // Allowed inside outer class
    }
}
```

### Inner Class Extending Outer Class

```java
class Outer {
    void outerMethod() {
        System.out.println("Outer method");
    }
    
    class Inner extends Outer {
        void innerMethod() {
            System.out.println("Inner method extending Outer");
            outerMethod(); // Inherited from Outer
        }
    }
    
    public static void main(String[] args) {
        Outer.Inner inner = new Outer().new Inner();
        inner.innerMethod();
    }
}
```

---

## 3. Method Local Inner Class

### What is a Method Local Inner Class?

A method local inner class is a class declared inside a method (or block, constructor, or initializer). Its scope is limited to that block, and it cannot be accessed outside of it.

```
┌──────────────────────────────────────────────────────────────┐
│              METHOD LOCAL INNER CLASS RULES                    │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Rule               │ Description                          │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ Scope              │ Only inside the declaring method/block │  │
│  │ Access modifier    │ Cannot have access modifiers           │  │
│  │ Static members     │ Cannot declare static members (except│  │
│  │                    │ static final constants)              │  │
│  │ Local variables    │ Can access final or effectively final│  │
│  │                    │ local variables only                 │  │
│  │ Outer members      │ Can access all outer class members   │  │
│  │ Instantiation      │ Can only be instantiated inside the  │  │
│  │                    │ method/block                         │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Basic Syntax

```java
class Outer {
    private int x = 10;
    
    void method() {
        int localVar = 20; // Effectively final
        
        // Method local inner class
        class LocalInner {
            void display() {
                System.out.println("Outer x = " + x);
                System.out.println("Local variable = " + localVar);
            }
        }
        
        LocalInner local = new LocalInner();
        local.display();
    }
    
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.method();
    }
}
```

### Method Local Inner Class in Different Blocks

```java
class Outer {
    private int x = 10;
    
    // Inside constructor
    Outer() {
        class ConstructorLocal {
            void show() {
                System.out.println("Constructor local, x = " + x);
            }
        }
        new ConstructorLocal().show();
    }
    
    // Inside instance initializer block
    {
        class InitializerLocal {
            void show() {
                System.out.println("Initializer local, x = " + x);
            }
        }
        new InitializerLocal().show();
    }
    
    // Inside static block
    static {
        class StaticLocal {
            void show() {
                System.out.println("Static local (no access to instance x)");
            }
        }
        new StaticLocal().show();
    }
    
    // Inside if block
    void conditionalMethod(boolean flag) {
        if (flag) {
            class IfLocal {
                void show() {
                    System.out.println("If block local");
                }
            }
            new IfLocal().show();
        }
    }
    
    // Inside for loop
    void loopMethod() {
        for (int i = 0; i < 3; i++) {
            class LoopLocal {
                void show(int val) {
                    System.out.println("Loop local, val = " + val);
                }
            }
            new LoopLocal().show(i);
        }
    }
    
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.conditionalMethod(true);
        outer.loopMethod();
    }
}
```

### Effectively Final Variables

```java
class Outer {
    void method() {
        final int finalVar = 10;
        int effectivelyFinal = 20; // Never modified after initialization
        // effectivelyFinal = 30; // ERROR: would make it not effectively final
        
        class LocalInner {
            void display() {
                System.out.println("Final var: " + finalVar);
                System.out.println("Effectively final: " + effectivelyFinal);
            }
        }
        
        new LocalInner().display();
    }
    
    public static void main(String[] args) {
        new Outer().method();
    }
}
```

---

## 4. Anonymous Inner Class

### What is an Anonymous Inner Class?

An anonymous inner class is a class without a name. It is declared and instantiated in a single expression. It is typically used when you need to override methods of a class or implement an interface for a one-time use.

```
┌──────────────────────────────────────────────────────────────┐
│              ANONYMOUS INNER CLASS RULES                       │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Feature            │ Description                          │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ Name               │ No name (compiler generates $1, $2) │  │
│  │ Declaration        │ Declared and instantiated together   │  │
│  │ Constructor        │ Cannot define constructor            │  │
│  │ Parent type        │ Extends a class OR implements an     │  │
│  │                    │ interface (single parent only)       │  │
│  │ Access modifier    │ No access modifier (local to block)  │  │
│  │ Static members     │ Cannot declare static members (except│  │
│  │                    │ static final constants)              │  │
│  │ Outer access       │ Can access all outer members         │  │
│  │ Lambda alternative │ Can be replaced with lambda for      │  │
│  │                    │ functional interfaces (Java 8+)      │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Anonymous Class Extending a Class

```java
class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class AnonymousClassDemo {
    public static void main(String[] args) {
        // Anonymous class extending Animal
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Bark!");
            }
            
            void fetch() {
                System.out.println("Fetching...");
            }
        };
        
        dog.makeSound(); // Bark!
        // dog.fetch(); // ERROR: cannot call new method via Animal reference
        
        // Anonymous class with different behavior
        Animal cat = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Meow!");
            }
        };
        
        cat.makeSound(); // Meow!
    }
}
```

### Anonymous Class Implementing an Interface

```java
interface Runnable {
    void run();
}

class InterfaceAnonymousDemo {
    public static void main(String[] args) {
        // Anonymous class implementing Runnable
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in anonymous class");
            }
        };
        
        runner.run();
        
        // Common use: Thread creation
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread running");
            }
        });
        t.start();
        
        // Java 8+ lambda equivalent
        Thread t2 = new Thread(() -> System.out.println("Thread with lambda"));
        t2.start();
    }
}
```

### Anonymous Class with Arguments

```java
class Message {
    String text;
    Message(String text) {
        this.text = text;
    }
    void display() {
        System.out.println(text);
    }
}

class AnonymousWithArgs {
    public static void main(String[] args) {
        // Anonymous class with constructor argument
        Message greeting = new Message("Hello") {
            @Override
            void display() {
                System.out.println("Greeting: " + text);
            }
        };
        greeting.display(); // Greeting: Hello
    }
}
```

### Anonymous Class in Method Arguments

```java
import java.util.Arrays;
import java.util.Comparator;

class AnonymousInMethod {
    public static void main(String[] args) {
        String[] fruits = {"Banana", "Apple", "Orange", "Mango"};
        
        // Anonymous Comparator
        Arrays.sort(fruits, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        System.out.println(Arrays.toString(fruits)); // [Apple, Mango, Banana, Orange]
        
        // Lambda equivalent (Java 8+)
        Arrays.sort(fruits, (s1, s2) -> s1.length() - s2.length());
    }
}
```

### Anonymous Class with Multiple Interfaces (Not Allowed)

```java
interface A { void methodA(); }
interface B { void methodB(); }

// ERROR: Anonymous class can only implement one interface
// Object obj = new A, B() { ... }; // NOT ALLOWED

// But can extend class and implement interface if the class already implements one
class AnonymousLimitations {
    public static void main(String[] args) {
        // This is allowed: extending a class that implements an interface
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Anonymous Thread");
            }
        };
    }
}
```

---

## 5. Static Nested Class

### What is a Static Nested Class?

A static nested class is a class declared inside another class with the `static` modifier. It is essentially a member of the outer class, similar to a static method or variable. It does not have an implicit reference to an instance of the outer class.

```
┌──────────────────────────────────────────────────────────────┐
│              STATIC NESTED CLASS RULES                       │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Feature            │ Description                          │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ Keyword            │ Declared with static                 │  │
│  │ Outer instance     │ NOT required for instantiation       │  │
│  │ Instantiation      │ Outer.Inner obj = new Outer.Inner(); │  │
│  │ Outer access       │ Can only access static members of    │  │
│  │                    │ outer class directly                 │  │
│  │ Static members     │ CAN declare static members           │  │
│  │ Access modifier    │ Can be public, private, protected,   │  │
│  │                    │ or default                           │  │
│  │ .class file        │ OuterClass$NestedClass.class         │  │
│  │ Main method        │ Can have main() and run independently│  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Basic Syntax

```java
class Outer {
    static int staticX = 10;
    int instanceY = 20;
    
    static class StaticNested {
        void display() {
            System.out.println("Static x = " + staticX); // OK
            // System.out.println("Instance y = " + instanceY); // ERROR
            
            // To access instance members, create outer instance
            Outer outer = new Outer();
            System.out.println("Instance y = " + outer.instanceY); // OK
        }
        
        static void staticMethod() {
            System.out.println("Static nested static method");
        }
    }
    
    public static void main(String[] args) {
        // No outer instance needed
        Outer.StaticNested nested = new Outer.StaticNested();
        nested.display();
        
        // Calling static method
        Outer.StaticNested.staticMethod();
    }
}
```

### Static Nested Class with Main Method

```java
class Outer {
    static class StaticNested {
        public static void main(String[] args) {
            System.out.println("Main inside static nested class!");
            System.out.println("Can run with: java Outer$StaticNested");
        }
    }
}

// Run: java Outer$StaticNested
```

### Static Nested Class for Builder Pattern

```java
class Computer {
    private String cpu;
    private int ram;
    private int storage;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }
    
    // Static nested Builder class
    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
    
    @Override
    public String toString() {
        return "Computer [cpu=" + cpu + ", ram=" + ram + ", storage=" + storage + "]";
    }
    
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
            .cpu("Intel i9")
            .ram(32)
            .storage(1000)
            .build();
        
        System.out.println(computer);
    }
}
```

---

## 6. Nested Interfaces

### Interface Inside a Class

An interface declared inside a class is called a nested interface. It can be static or non-static (but by default, interfaces inside classes are static).

```java
class Outer {
    // Nested interface (static by default)
    interface NestedInterface {
        void nestedMethod();
    }
    
    // Implementing nested interface inside outer class
    class Inner implements NestedInterface {
        @Override
        public void nestedMethod() {
            System.out.println("Nested interface method implemented");
        }
    }
    
    public static void main(String[] args) {
        Outer.Inner inner = new Outer().new Inner();
        inner.nestedMethod();
    }
}
```

### Interface Inside an Interface

An interface declared inside another interface is implicitly public and static.

```java
interface OuterInterface {
    void outerMethod();
    
    // Implicitly public and static
    interface InnerInterface {
        void innerMethod();
    }
}

class NestedInterfaceImpl implements OuterInterface.InnerInterface {
    @Override
    public void innerMethod() {
        System.out.println("Inner interface method");
    }
    
    public static void main(String[] args) {
        OuterInterface.InnerInterface obj = new NestedInterfaceImpl();
        obj.innerMethod();
    }
}
```

### Nested Interface in Class with Access Modifiers

```java
class Container {
    // Public nested interface
    public interface PublicNested {
        void publicMethod();
    }
    
    // Private nested interface (only accessible within Container)
    private interface PrivateNested {
        void privateMethod();
    }
    
    // Protected nested interface
    protected interface ProtectedNested {
        void protectedMethod();
    }
    
    // Default nested interface
    interface DefaultNested {
        void defaultMethod();
    }
    
    // Implementing private interface within outer class
    class PrivateImpl implements PrivateNested {
        @Override
        public void privateMethod() {
            System.out.println("Private nested interface implementation");
        }
    }
}

// Implementing public nested interface from outside
class PublicImpl implements Container.PublicNested {
    @Override
    public void publicMethod() {
        System.out.println("Public nested interface implementation");
    }
}
```

---

## 7. Accessing Outer Class Members

### Accessing Outer Class from Inner Class

```java
class Outer {
    private int x = 10;
    static int y = 20;
    
    class Inner {
        private int x = 100;
        
        void display() {
            System.out.println("Inner x: " + x);          // 100
            System.out.println("Outer x: " + Outer.this.x); // 10
            System.out.println("Outer y: " + y);            // 20
        }
    }
    
    void show() {
        Inner inner = new Inner();
        System.out.println("Inner x from outer: " + inner.x); // Outer can access private inner members!
    }
    
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.new Inner().display();
        outer.show();
    }
}
```

### Shadowing and Resolution

```java
class ShadowDemo {
    int x = 1;
    
    class Inner {
        int x = 2;
        
        void method() {
            int x = 3;
            
            System.out.println("Local x: " + x);              // 3
            System.out.println("Inner x: " + this.x);           // 2
            System.out.println("Outer x: " + ShadowDemo.this.x); // 1
        }
    }
    
    public static void main(String[] args) {
        new ShadowDemo().new Inner().method();
    }
}
```

### Accessing Members of Outer Class from Static Nested Class

```java
class StaticAccessDemo {
    static int staticVar = 10;
    int instanceVar = 20;
    
    static class StaticNested {
        void show() {
            System.out.println("Static var: " + staticVar); // OK
            // System.out.println("Instance var: " + instanceVar); // ERROR
            
            // Access via instance
            StaticAccessDemo outer = new StaticAccessDemo();
            System.out.println("Instance var: " + outer.instanceVar); // OK
        }
    }
    
    public static void main(String[] args) {
        new StaticNested().show();
    }
}
```

---

## 8. Interview FAQs

### Q1. What is the difference between an inner class and a static nested class?

```
┌──────────────────────────────────────────────────────────────┐
│              INNER CLASS vs STATIC NESTED CLASS               │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Feature            │ Inner Class  │ Static Nested Class  │  │
│  ├────────────────────┼──────────────┼──────────────────────┤  │
│  │ static keyword     │ No           │ Yes                  │  │
│  │ Outer instance     │ Required     │ Not required         │  │
│  │ Static members     │ Not allowed  │ Allowed              │  │
│  │ Outer static access│ Yes          │ Yes                  │  │
│  │ Outer instance     │ Yes (direct) │ Yes (via instance)   │  │
│  │ .class file        │ Outer$Inner  │ Outer$Nested         │  │
│  └────────────────────┴──────────────┴──────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Q2. Can a Java class have multiple inner classes?

**Answer:** Yes, a class can have any number of inner classes, including normal inner classes, method local classes, anonymous classes, and static nested classes. Each must have a unique name (except anonymous classes which are auto-named).

### Q3. Can we declare a static method inside a normal inner class?

**Answer:** No, a normal (non-static) inner class cannot declare static methods or static variables. The only exception is static final constants (compile-time constants). If you need static members, use a static nested class instead.

### Q4. What is the purpose of `OuterClass.this`?

**Answer:** `OuterClass.this` is a reference to the instance of the outer class from within an inner class. It is used when there is variable shadowing (the inner class has a variable with the same name as the outer class). It explicitly refers to the outer class's instance members.

### Q5. Can we override methods in an anonymous inner class?

**Answer:** Yes, anonymous inner classes are commonly used to override methods of a class or implement an interface. You can override any accessible methods of the parent class or interface methods. You can also add new methods, but they cannot be called via the parent reference.

### Q6. What is the difference between an anonymous inner class and a lambda expression?

**Answer:**
- **Anonymous inner class:** Can extend a class or implement an interface with multiple methods. Creates a new class file. Can have state (instance variables).
- **Lambda expression:** Can only implement functional interfaces (single abstract method). Does not create a new class file. More concise. Cannot have state (unless using closures).

```java
// Anonymous inner class
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Anonymous");
    }
};

// Lambda equivalent
Runnable r2 = () -> System.out.println("Lambda");
```

### Q7. Can an inner class be declared inside an interface?

**Answer:** Yes, an inner class can be declared inside an interface. It is implicitly `public static` (even if not declared so). It can be instantiated without an interface implementation.

```java
interface MyInterface {
    class InnerClass {
        void show() { System.out.println("Inner class in interface"); }
    }
}
```

### Q8. Can an inner class have a main method?

**Answer:** Yes, a static nested class can have a main method and can be executed independently using:
```bash
java OuterClass$StaticNestedClass
```
Normal inner classes cannot have a main method because they cannot have static members.

### Q9. What are the restrictions of method local inner classes?

**Answer:**
1. Cannot have access modifiers.
2. Cannot declare static members (except static final constants).
3. Can only access final or effectively final local variables.
4. Scope is limited to the declaring block.
5. Cannot be instantiated outside the declaring block.

### Q10. Can an inner class extend its outer class?

**Answer:** Yes, an inner class can extend its outer class. It inherits all accessible members of the outer class. This is sometimes used for specialized implementations that need access to private members.

```java
class Outer {
    void method() { }
    class Inner extends Outer {
        // Inner now has access to all Outer methods
    }
}
```

---

## 9. Key Takeaways

1. **Inner classes** are classes defined inside another class. They provide better encapsulation and logical grouping.
2. **Normal/Regular Inner Class** is a member of the outer class and requires an outer instance to be created. It cannot have static members.
3. **Method Local Inner Class** is defined inside a method. Its scope is limited to that method. It can access final or effectively final local variables.
4. **Anonymous Inner Class** has no name and is declared and instantiated at the same time. It can either extend a class or implement an interface. For functional interfaces, prefer lambdas.
5. **Static Nested Class** is declared with `static`. It does not require an outer instance and can have static members. It can only access static outer members directly.
6. **Nested Interfaces** can be declared inside classes or interfaces. Inside interfaces, they are implicitly `public static`.
7. **Variable shadowing** can be resolved using `OuterClass.this.variableName`.
8. The outer class can access all private members of its inner classes, and vice versa.
9. **Compilation:** Each inner class produces a `.class` file named `Outer$Inner.class` or `Outer$1.class` for anonymous classes.
10. **Builder pattern** often uses static nested classes to implement fluent APIs.

---

**Happy coding! 🚀**

*Inner classes are the hidden gem of Java encapsulation — master them to write cleaner, more maintainable code.*
