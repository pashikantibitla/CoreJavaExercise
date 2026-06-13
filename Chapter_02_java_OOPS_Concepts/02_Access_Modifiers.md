# 02 — Access Modifiers

> **Video:** 5, 9–11  
> **Topics:** Class Level Modifiers, Member Level Modifiers, Summary

---

## Table of Contents

1. [Class Level Modifiers](#1-class-level-modifiers)
2. [Member Level Modifiers](#2-member-level-modifiers)
3. [Summary Table](#3-summary-table)
4. [Interview Questions](#4-interview-questions)
5. [Quick Reference](#5-quick-reference)
6. [Key Takeaways](#6-key-takeaways)

---

## 1. Class Level Modifiers

### Overview

At the class level, Java supports only **two** access modifiers:

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    CLASS LEVEL MODIFIERS                                   │
│  ┌────────────────────┬────────────────────┬─────────────────────────┐   │
│  │ Modifier           │ Visibility         │ Example                  │   │
│  ├────────────────────┼────────────────────┼─────────────────────────┤   │
│  │ public             │ Everywhere         │ public class A {}       │   │
│  │ default            │ Same package only  │ class A {}              │   │
│  │ abstract           │ (Non-access)       │ abstract class A {}     │   │
│  │ final              │ (Non-access)       │ final class A {}       │   │
│  │ strictfp           │ (Non-access)       │ strictfp class A {}    │   │
│  └────────────────────┴────────────────────┴─────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────────┘
```

### public Class

```java
// File: A.java
public class A {
    public void display() {
        System.out.println("Class A");
    }
}

// Accessible from ANY package
// Any other class can import and use A
```

### default (package-private) Class

```java
// File: B.java
class B {
    public void display() {
        System.out.println("Class B");
    }
}

// Accessible ONLY within the same package
// Cannot be imported by classes in other packages
```

### Abstract Class

```java
// Cannot be instantiated
// Must be extended
public abstract class Vehicle {
    // Can have abstract methods
    public abstract void start();
    
    // Can have concrete methods
    public void stop() {
        System.out.println("Vehicle stopped");
    }
}
```

### Final Class

```java
// Cannot be extended (inherited)
public final class FinalClass {
    public void display() {
        System.out.println("Cannot be extended");
    }
}

// class SubClass extends FinalClass {}  // ❌ Compile error
```

---

## 2. Member Level Modifiers

### Overview

At the member level (fields, methods, constructors, nested classes), Java supports **four** access modifiers:

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    MEMBER LEVEL MODIFIERS                                │
│  ┌────────────────────┬────────────────────────────────────────────────┐  │
│  │ Modifier           │ Visibility                                     │  │
│  ├────────────────────┼────────────────────────────────────────────────┤  │
│  │ public             │ Everywhere — same package, other packages      │  │
│  │ protected          │ Same package + subclasses (even in other pkg) │  │
│  │ default            │ Same package only                              │  │
│  │ private            │ Same class only                                │  │
│  └────────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

### public Member

```java
public class Person {
    public String name;           // Visible everywhere
    public void display() { }     // Visible everywhere
}
```

### protected Member

```java
public class Animal {
    protected String species;     // Same package + subclasses
    protected void eat() { }      // Same package + subclasses
}

// In same package: ✅ Accessible
// In subclass (different package): ✅ Accessible
// In non-subclass (different package): ❌ Not accessible
```

### default (package-private) Member

```java
class Student {
    int id;                       // Same package only
    void display() { }            // Same package only
}

// In same package: ✅ Accessible
// In different package: ❌ Not accessible (even in subclass)
```

### private Member

```java
public class Employee {
    private int salary;           // Same class only
    private void calculateTax() { } // Same class only
    
    public void showSalary() {
        // Can access private within the same class
        System.out.println(salary);  // ✅ Allowed
    }
}

// Outside the class: ❌ Not accessible
// In subclass: ❌ Not accessible
```

---

## 3. Summary Table

### Class Visibility

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Modifier           │ Same Package       │ Different Package  │
├────────────────────┼────────────────────┼────────────────────┤
│ public             │ ✅ Yes             │ ✅ Yes             │
│ default            │ ✅ Yes             │ ❌ No              │
└────────────────────┴────────────────────┴────────────────────┘
```

### Member Visibility

```
┌────────────────────┬────────────────────┬────────────────────┬────────────────────┬────────────────────┐
│ Modifier           │ Same Class         │ Same Package       │ Subclass (diff pkg)│ Different Package  │
│                    │                    │                    │                    │ (non-subclass)     │
├────────────────────┼────────────────────┼────────────────────┼────────────────────┼────────────────────┤
│ public             │ ✅ Yes             │ ✅ Yes             │ ✅ Yes             │ ✅ Yes             │
│ protected          │ ✅ Yes             │ ✅ Yes             │ ✅ Yes             │ ❌ No              │
│ default            │ ✅ Yes             │ ✅ Yes             │ ❌ No              │ ❌ No              │
│ private            │ ✅ Yes             │ ❌ No              │ ❌ No              │ ❌ No              │
└────────────────────┴────────────────────┴────────────────────┴────────────────────┴────────────────────┘
```

### Visual: Access Levels

```
                    ┌─────────────────────────────────────────────────────────┐
                    │                    ACCESS LEVELS                             │
                    │  ┌────────────────────────────────────────────────────┐  │
                    │  │   public                                            │  │
                    │  │   ┌────────────────────────────────────────────────┐  │  │
                    │  │   │   protected                                     │  │  │
                    │  │   │   ┌────────────────────────────────────────┐  │  │  │
                    │  │   │   │   default (package-private)           │  │  │  │
                    │  │   │   │   ┌────────────────────────────────┐  │  │  │  │
                    │  │   │   │   │   private                       │  │  │  │  │
                    │  │   │   │   │   │                            │  │  │  │  │  │
                    │  │   │   │   │   │   Same class only          │  │  │  │  │
                    │  │   │   │   └────────────────────────────────┘  │  │  │  │
                    │  │   │   │   Same package                      │  │  │  │
                    │  │   │   └────────────────────────────────────────┘  │  │  │
                    │  │   │   Same package + subclasses (anywhere)       │  │  │
                    │  │   └────────────────────────────────────────────────┘  │  │
                    │  │   Everywhere                                          │  │
                    │  └────────────────────────────────────────────────────┘  │
                    └─────────────────────────────────────────────────────────┘
```

---

## 4. Interview Questions

### Q1. Can a class be both `abstract` and `final`?

```java
// ❌ NO — abstract and final are mutually exclusive

public abstract final class MyClass { }  // ❌ Compile error

// Why?
// - abstract class: MUST be extended
// - final class: CANNOT be extended
// - Contradiction!

// Similarly:
// ❌ abstract + final on methods
// ❌ abstract + static on methods
// ❌ abstract + private on methods
```

---

### Q2. Can a top-level class be `private` or `protected`?

```java
// ❌ NO — top-level classes can only be public or default

private class MyClass { }     // ❌ Compile error
protected class MyClass { }  // ❌ Compile error

// ✅ Only allowed:
public class MyClass { }      // ✅ Allowed
class MyClass { }             // ✅ Allowed (default)

// private and protected are allowed for INNER classes
public class Outer {
    private class Inner { }   // ✅ Allowed
    protected class Inner2 { } // ✅ Allowed
}
```

---

### Q3. What is the difference between `default` and `protected`?

```java
// package com.example.pkg1;
public class Parent {
    int defaultField;           // default
    protected int protectedField; // protected
}

// package com.example.pkg2;
import com.example.pkg1.Parent;

public class Child extends Parent {
    public void test() {
        // defaultField;       // ❌ Error — not accessible in different package
        protectedField;         // ✅ Allowed — accessible in subclass
    }
}

public class NonChild {
    public void test() {
        Parent p = new Parent();
        // p.defaultField;     // ❌ Error
        // p.protectedField;  // ❌ Error — not accessible in non-subclass
    }
}
```

---

### Q4. Can we reduce visibility when overriding a method?

```java
class Parent {
    public void show() { }      // public
    protected void display() { } // protected
}

class Child extends Parent {
    // public void show() { }   // ✅ Allowed — same visibility
    // protected void show() { } // ❌ Error — cannot reduce visibility
    
    // protected void display() { } // ✅ Allowed — same visibility
    public void display() { }      // ✅ Allowed — can increase visibility
}

// Rule: Visibility can only be INCREASED or kept SAME, never DECREASED
// public > protected > default > private
```

---

### Q5. Can a `private` method be overridden?

```java
class Parent {
    private void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    // This is NOT overriding — it's a NEW method
    private void show() {
        System.out.println("Child");
    }
}

// private methods are NOT inherited
// Therefore, they cannot be overridden
// The child method is completely separate
```

---

## 5. Quick Reference

### Access Modifier Matrix

```
Modifier      │ Same Class │ Same Package │ Subclass │ Different Package
──────────────┼────────────┼──────────────┼──────────┼──────────────────
public        │     ✅     │      ✅      │    ✅    │       ✅
protected     │     ✅     │      ✅      │    ✅    │       ❌
default       │     ✅     │      ✅      │    ❌    │       ❌
private       │     ✅     │      ❌      │    ❌    │       ❌
```

### Class Modifiers

```
Modifier │ Access │ Instantiation │ Inheritance
─────────┼────────┼───────────────┼─────────────
public   │ Any    │ Yes           │ Yes
default  │ Package│ Yes           │ Yes
abstract │ Any    │ No            │ Yes
final    │ Any    │ Yes           │ No
```

---

## 6. Key Takeaways

1. **Class level only has `public` and `default`** — no `protected` or `private` for top-level classes.
2. **Member level has all four:** `public`, `protected`, `default`, `private`.
3. **`public` = most accessible**, `private` = most restrictive.
4. **`protected` = package + subclass** (even in different package).
5. **`default` = package only** — no access outside the package.
6. **`private` = class only** — not even in subclasses.
7. **Cannot reduce visibility** when overriding — only increase or keep same.
8. **`abstract` and `final` are mutually exclusive** for classes.
9. **Private methods are NOT inherited** — therefore cannot be overridden.
10. **Use `private` for fields**, `public` for getters/setters — encapsulation.

---

**Happy coding! 🚀**

*Access modifiers are the gatekeepers of your code — use them wisely.*
