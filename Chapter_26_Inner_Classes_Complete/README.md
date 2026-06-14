# Chapter 26 — Inner Classes (Complete)

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
8. [Interview Questions](#8-interview-questions)
9. [Key Takeaways](#9-key-takeaways)

---

## 1. Introduction to Inner Classes

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** Types, syntax, advantages, limitations

- An inner class is a class defined inside another class.
- Provides better encapsulation and logical grouping.
- Types: Normal/Regular, Method Local, Anonymous, Static Nested.

---

## 2. Normal/Regular Inner Class

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** Declaration, instantiation, accessing outer members

- Declared inside a class but outside any method.
- Can access all members of the outer class (including private).
- Cannot have static declarations (except static final constants).

---

## 3. Method Local Inner Class

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** Scope, access rules, final/effectively final variables

- Declared inside a method or block.
- Scope is limited to that method/block.
- Can access final or effectively final local variables.

---

## 4. Anonymous Inner Class

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** Syntax, use with interfaces and classes, lambda alternatives

- A class without a name, declared and instantiated at the same time.
- Commonly used with interfaces (Runnable, ActionListener, Comparator).
- Can be replaced with lambdas for functional interfaces.

---

## 5. Static Nested Class

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** Declaration, static members, access rules

- Declared with `static` inside a class.
- Can have static members.
- Can only access static members of the outer class directly.
- Does not require an instance of the outer class.

---

## 6. Nested Interfaces

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** Interface inside class, interface inside interface

- An interface declared inside a class or another interface.
- Can be static or non-static (inside class, static by default).
- Inside interface, nested interfaces are implicitly public and static.

---

## 7. Accessing Outer Class Members

**File:** `26_Inner_Classes_Complete.md`  
**Topics:** `OuterClass.this`, shadowing, access rules

- Inner classes can access all outer class members.
- `OuterClass.this` refers to the outer class instance.
- Shadowing occurs when inner and outer have same variable name.

---

## 📁 Additional Files

- **26_Inner_Classes_Complete.md** — Comprehensive theory, code snippets, and interview FAQs
- **java_programs/** — Individual `.java` files demonstrating inner class concepts

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Introduction ──→ What are inner classes, why use them
    │
    ├── Normal Inner Class ──→ Regular inner class, instantiation, member access
    │
    ├── Method Local Inner Class ──→ Class inside method, scope, local variable access
    │
    ├── Anonymous Inner Class ──→ No-name class, interface implementation, event handling
    │
    ├── Static Nested Class ──→ Static inner class, no outer instance needed
    │
    ├── Nested Interfaces ──→ Interface inside class/interface
    │
    └── Accessing Outer Members ──→ OuterClass.this, shadowing, access rules
```

---

## 📋 Key Takeaways

1. **Inner classes** provide better encapsulation and logical grouping of related classes.
2. **Normal/Regular Inner Class** is a member of the outer class and can access all outer members.
3. **Method Local Inner Class** is declared inside a method and has method-level scope.
4. **Anonymous Inner Class** has no name and is instantiated at declaration; ideal for one-time use.
5. **Static Nested Class** can have static members and does not require an outer class instance.
6. **Nested Interfaces** are implicitly static inside interfaces and can be public/private inside classes.
7. **Access rules:** Inner classes can access private outer members. Static nested classes can only access static outer members directly.
8. **Shadowing:** Use `OuterClass.this.variable` to access outer class variable when shadowed.
9. **Compiling:** Inner classes produce `.class` files named `OuterClass$InnerClass.class`.
10. **Lambdas:** For functional interfaces, prefer lambdas over anonymous inner classes (Java 8+).

---

**Happy coding! 🚀**

*Inner classes are the secret weapon of Java encapsulation — use them wisely.*
