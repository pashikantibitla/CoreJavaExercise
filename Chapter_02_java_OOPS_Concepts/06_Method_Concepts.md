# 06 — Method Concepts

> **Video:** 22–29, 35–38  
> **Topics:** Method Signature, Overloading, Overriding, Hiding

---

## Table of Contents

1. [Method Signature](#1-method-signature)
2. [Method Overloading](#2-method-overloading)
3. [Method Overloading Case Studies](#3-method-overloading-case-studies)
4. [Method Overriding](#4-method-overriding)
5. [Method Overriding Rules](#5-method-overriding-rules)
6. [Method Hiding](#6-method-hiding)
7. [Comparison](#7-comparison)
8. [Interview Questions](#8-interview-questions)
9. [Quick Reference](#9-quick-reference)
10. [Key Takeaways](#10-key-takeaways)

---

## 1. Method Signature

### What is a Method Signature?

A method signature consists of **two** things:
1. **Method name**
2. **Parameter list** (number, type, and order of parameters)

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    METHOD SIGNATURE COMPONENTS                            │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  public void display(int a, String b)                          │    │
│  │  │      │       │           │                                    │    │
│  │  │      │       │           └── Parameter list (type, order)     │    │
│  │  │      │       └────────────── Method name                     │    │
│  │  │      └────────────────────── Return type (NOT in signature)  │    │
│  │  └─────────────────────────────── Access modifier (NOT in signature)│    │
│  │                                                                  │    │
│  │  Method signature = display(int, String)                          │    │
│  │  Return type and access modifier are NOT part of signature!     │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### Example

```java
public class MethodSignatureDemo {
    // These are different methods (different signatures)
    public void display(int a) { }           // display(int)
    public void display(String a) { }        // display(String)
    public void display(int a, String b) { } // display(int, String)
    public void display(String a, int b) { } // display(String, int)
    
    // These are NOT valid (same signature)
    // public int display(int a) { return 0; }  // ❌ Error — same signature
    // Different return type does NOT change signature
}
```

---

## 2. Method Overloading

### What is Method Overloading?

Method overloading is the ability to define multiple methods with the **same name** but **different parameters** in the same class.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    METHOD OVERLOADING                                     │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  class Calculator {                                              │    │
│  │    // Same name, different parameters                             │    │
│  │    public int add(int a, int b) { return a + b; }                │    │
│  │    public double add(double a, double b) { return a + b; }     │    │
│  │    public int add(int a, int b, int c) { return a + b + c; }    │    │
│  │    public String add(String a, String b) { return a + b; }      │    │
│  │  }                                                               │    │
│  │                                                                  │    │
│  │  add(2, 3)        → int version                                  │    │
│  │  add(2.5, 3.5)    → double version                               │    │
│  │  add(1, 2, 3)     → 3-parameter version                          │    │
│  │  add("Hello", "World") → String version                          │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  Compiler decides which method to call based on method signature.      │
│  This is COMPILE-TIME (static) polymorphism.                             │
└─────────────────────────────────────────────────────────────────────────┘
```

### Rules for Overloading

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    OVERLOADING RULES                                       │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Rule               │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ Same name          │ Methods must have same name                     │  │
│  │ Different params   │ Number, type, or order must differ             │  │
│  │ Return type        │ Can be same or different (not enough alone)    │  │
│  │ Access modifier    │ Can be same or different                       │  │
│  │ Exceptions         │ Can be same or different                       │  │
│  │ Same class         │ Must be in same class                          │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Method Overloading Case Studies

### Case Study 1: Automatic Type Promotion

```java
public class OverloadingDemo {
    public void display(int a) {
        System.out.println("int: " + a);
    }
    
    public void display(double a) {
        System.out.println("double: " + a);
    }
    
    public void display(long a) {
        System.out.println("long: " + a);
    }
    
    public static void main(String[] args) {
        OverloadingDemo obj = new OverloadingDemo();
        
        obj.display(5);      // int version
        obj.display(5.5);    // double version
        obj.display(5L);     // long version
        
        // Automatic promotion
        obj.display('A');    // char promoted to int → int version
        obj.display(5.5f);   // float promoted to double → double version
    }
}
```

### Case Study 2: Null Reference Ambiguity

```java
public class NullDemo {
    public void display(String s) {
        System.out.println("String: " + s);
    }
    
    public void display(StringBuilder s) {
        System.out.println("StringBuilder: " + s);
    }
    
    public static void main(String[] args) {
        NullDemo obj = new NullDemo();
        
        // obj.display(null);  // ❌ Ambiguity! Both accept null
        // Compile error: "reference to display is ambiguous"
        
        // Solution: Cast the null
        obj.display((String) null);        // ✅ String version
        obj.display((StringBuilder) null); // ✅ StringBuilder version
    }
}
```

### Case Study 3: Var-Args Overloading

```java
public class VarArgsDemo {
    public void display(int a) {
        System.out.println("Single int: " + a);
    }
    
    public void display(int... a) {
        System.out.println("Var args: " + java.util.Arrays.toString(a));
    }
    
    public static void main(String[] args) {
        VarArgsDemo obj = new VarArgsDemo();
        
        obj.display(5);        // Single int (exact match preferred)
        obj.display(5, 6);     // Var args
        obj.display(5, 6, 7); // Var args
    }
}
```

### Case Study 4: Overloading with Inheritance

```java
class Parent {
    public void display(Object o) {
        System.out.println("Object version");
    }
}

class Child extends Parent {
    public void display(String s) {
        System.out.println("String version");
    }
    
    public static void main(String[] args) {
        Child c = new Child();
        c.display("Hello");   // String version (exact match)
        c.display(5);         // Object version (inherited)
    }
}
```

### Case Study 5: Overloading and Main Method

```java
public class MainOverloading {
    // Standard main method
    public static void main(String[] args) {
        System.out.println("String[] main");
        main(5);  // Calls overloaded main
    }
    
    // Overloaded main method
    public static void main(int a) {
        System.out.println("int main: " + a);
    }
    
    public static void main(String s) {
        System.out.println("String main: " + s);
    }
}

// JVM always calls: public static void main(String[] args)
// Overloaded versions are NOT called by JVM automatically
```

### Case Study 6: Overloading with Wrapper Classes

```java
public class WrapperDemo {
    public void display(int a) {
        System.out.println("Primitive int: " + a);
    }
    
    public void display(Integer a) {
        System.out.println("Integer wrapper: " + a);
    }
    
    public static void main(String[] args) {
        WrapperDemo obj = new WrapperDemo();
        
        obj.display(5);        // Primitive int (exact match)
        obj.display(new Integer(5)); // Integer wrapper (exact match)
    }
}
```

---

## 4. Method Overriding

### What is Method Overriding?

Method overriding occurs when a subclass provides a **specific implementation** for a method that is already defined in its superclass.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    METHOD OVERRIDING                                       │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │  class Animal {                                                  │    │
│  │    public void makeSound() {                                     │    │
│  │      System.out.println("Some sound");                           │    │
│  │    }                                                             │    │
│  │  }                                                               │    │
│  │                                                                  │    │
│  │  class Dog extends Animal {                                      │    │
│  │    @Override                                                     │    │
│  │    public void makeSound() {                                     │    │
│  │      System.out.println("Bark");                                  │    │
│  │    }                                                             │    │
│  │  }                                                               │    │
│  │                                                                  │    │
│  │  Animal a = new Dog();                                           │    │
│  │  a.makeSound();  // "Bark" — calls Dog's version at runtime      │    │
│  │                                                                  │    │
│  │  This is RUNTIME (dynamic) polymorphism.                         │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### Rules for Overriding

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    OVERRIDING RULES                                       │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Rule               │ Description                                    │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ Same name          │ Method name must be identical                  │  │
│  │ Same params        │ Parameter list must be identical               │  │
│  │ Same return type   │ Must be same or covariant (subclass)            │  │
│  │ Access modifier    │ Cannot be more restrictive (can be less)       │  │
│  │ Exceptions         │ Cannot throw broader checked exceptions          │  │
│  │ static method      │ Cannot be overridden (only hidden)               │  │
│  │ final method       │ Cannot be overridden                           │  │
│  │ private method     │ Cannot be overridden (not inherited)             │  │
│  │ @Override          │ Recommended annotation (compile-time check)      │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 5. Method Overriding Rules

### Rule 1: Return Type (Covariant)

```java
class Parent {
    public Object getValue() {
        return "Parent";
    }
}

class Child extends Parent {
    @Override
    public String getValue() {  // ✅ String is subclass of Object (covariant)
        return "Child";
    }
}
```

### Rule 2: Access Modifier

```java
class Parent {
    protected void display() { }
}

class Child extends Parent {
    @Override
    public void display() { }  // ✅ Less restrictive (public > protected)
    
    // @Override
    // private void display() { }  // ❌ Error — more restrictive
}
```

### Rule 3: Exceptions

```java
class Parent {
    public void process() throws IOException { }
}

class Child extends Parent {
    @Override
    public void process() throws FileNotFoundException { }  // ✅ Subclass exception
    
    // @Override
    // public void process() throws Exception { }  // ❌ Error — broader exception
}
```

### Rule 4: static and final

```java
class Parent {
    public static void show() { }    // static
    public final void display() { }   // final
}

class Child extends Parent {
    // @Override
    // public static void show() { }  // ❌ Hiding, not overriding
    
    // @Override
    // public void display() { }     // ❌ Error — final method
}
```

---

## 6. Method Hiding

### What is Method Hiding?

Method hiding occurs when a child class defines a **static method** with the same signature as a static method in the parent class. It is NOT overriding.

```java
class Parent {
    public static void display() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    public static void display() {  // Hiding, NOT overriding
        System.out.println("Child static");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        p.display();  // "Parent static" — resolved by reference type
        
        Child c = new Child();
        c.display();  // "Child static" — resolved by reference type
    }
}

// Overriding: resolved by runtime object type
// Hiding: resolved by compile-time reference type
```

---

## 7. Comparison

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Feature            │ Overloading        │ Overriding         │
├────────────────────┼────────────────────┼────────────────────┤
│ Class              │ Same class         │ Parent + Child     │
│ Method name        │ Same               │ Same               │
│ Parameters         │ Different          │ Same               │
│ Return type        │ Can differ         │ Same or covariant  │
│ Access modifier    │ Can differ         │ Cannot be stricter │
│ static             │ Allowed            │ Not allowed        │
│ final              │ Allowed            │ Not allowed        │
│ Polymorphism       │ Compile-time       │ Runtime            │
│ Inheritance        │ Not required       │ Required           │
└────────────────────┴────────────────────┴────────────────────┘
```

---

## 8. Interview Questions

### Q1. Can we overload a method by changing only the return type?

```java
// ❌ NO — return type is NOT part of method signature

public class Demo {
    public int display() { return 0; }
    // public double display() { return 0.0; }  // ❌ Compile error
    // Same signature: display()
}

// Must change parameter list to overload
```

---

### Q2. Can we override a static method?

```java
// ❌ NO — static methods are hidden, not overridden

class Parent {
    public static void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    public static void show() {  // Hiding
        System.out.println("Child");
    }
}

// Resolved by reference type, not runtime object
```

---

### Q3. Can we override a private method?

```java
// ❌ NO — private methods are NOT inherited

class Parent {
    private void secret() { }
}

class Child extends Parent {
    private void secret() { }  // New method, NOT overriding
}
```

---

## 9. Quick Reference

### Overloading

```
Same class
Same name
Different parameters
Return type doesn't matter
Compile-time polymorphism
```

### Overriding

```
Parent + Child
Same name
Same parameters
Return type: same or covariant
Access: cannot be more restrictive
Runtime polymorphism
```

---

## 10. Key Takeaways

1. **Method signature = name + parameter list** — return type is NOT part of it.
2. **Overloading = same name, different parameters** — compile-time polymorphism.
3. **Overriding = same name, same parameters, different body** — runtime polymorphism.
4. **Static methods are hidden, not overridden** — resolved by reference type.
5. **Final methods cannot be overridden** — locked by parent.
6. **Private methods are not inherited** — therefore cannot be overridden.
7. **Covariant return types** — allowed in overriding (subclass return type).
8. **Cannot reduce visibility** when overriding — can only increase or keep same.
9. **Var-args** — prefer exact match over var-args in overloading.
10. **@Override annotation** — recommended for compile-time safety.

---

**Happy coding! 🚀**

*Overloading is compile-time magic, overriding is runtime magic — master both!*
