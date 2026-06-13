# 04 — Declarations and Access Modifiers

> **Videos:** 30–43  
> **Topics:** Source File Structure, Import, Static Import, Package, Class Level Modifiers, Member Level Modifiers, final, static, native, transient, Interfaces

---

## Table of Contents

1. [Java Source File Structure](#1-java-source-file-structure)
2. [Import and Static Import](#2-import-and-static-import)
3. [Package Statement](#3-package-statement)
4. [Class Level Modifiers](#4-class-level-modifiers)
5. [Member Level Modifiers](#5-member-level-modifiers)
6. [final Variables](#6-final-variables)
7. [static Modifier](#7-static-modifier)
8. [native, transient, volatile](#8-native-transient-volatile)
9. [Interfaces](#9-interfaces)
10. [Interview Questions](#10-interview-questions)
11. [Quick Reference](#11-quick-reference)
12. [Key Takeaways](#12-key-takeaways)

---

## 1. Java Source File Structure

### Complete Structure

```java
// 1. Package statement (optional, must be first)
package com.durgasoft.core;

// 2. Import statements (optional)
import java.util.Date;
import java.util.ArrayList;
import static java.lang.Math.*;

// 3. Class/Interface/Enum declarations
public class SourceFileDemo {
    // 4. Static variables
    static int staticVar;
    
    // 5. Instance variables
    int instanceVar;
    
    // 6. Static block
    static {
        System.out.println("Static block");
    }
    
    // 7. Instance block
    {
        System.out.println("Instance block");
    }
    
    // 8. Constructors
    public SourceFileDemo() {
        System.out.println("Constructor");
    }
    
    // 9. Methods
    public void method() {
        System.out.println("Method");
    }
    
    // 10. Main method
    public static void main(String[] args) {
        System.out.println("Main method");
    }
}

// 11. Multiple non-public classes
class HelperClass {
    // Helper class
}
```

### Source File Rules

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SOURCE FILE RULES                                  │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Rule               │ Description                              │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Only one public    │ Only one public class/interface per file │   │
│  │ File name          │ Must match public class name + .java     │   │
│  │ Case sensitive     │ ClassName.java (exact match)             │   │
│  │ Multiple classes   │ Any number of non-public classes         │   │
│  │ Package first      │ Package must be first statement            │   │
│  │ Import after pkg   │ Import after package, before class       │   │
│  │ Comments allowed   │ Anywhere in the file                     │   │
│  └────────────────────┴──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Import and Static Import

### Import Statement

```java
// 1. Single class import
import java.util.Date;

// 2. Package import (all classes)
import java.util.*;

// 3. Static import (Java 5+)
import static java.lang.Math.PI;
import static java.lang.Math.*;

// 4. Fully Qualified Name (no import needed)
java.util.Date d = new java.util.Date();

// Important notes:
// - java.lang is automatically imported (no need for import)
// - import is resolved at compile time, no runtime impact
// - Wildcard (*) imports classes only, NOT subpackages
// - import does NOT increase file size
```

### Static Import

```java
// Without static import
System.out.println(Math.PI);
System.out.println(Math.sqrt(16));
System.out.println(Math.max(10, 20));

// With static import
import static java.lang.Math.*;
import static java.lang.System.out;

out.println(PI);           // No Math. prefix
out.println(sqrt(16));     // No Math. prefix
out.println(max(10, 20));  // No Math. prefix

// Static import of all members
import static java.lang.Math.*;

// Static import of specific member
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

// Overuse can cause ambiguity:
import static java.lang.Integer.*;
import static java.lang.Long.*;
// MAX_VALUE;  // ❌ ERROR: ambiguous (Integer.MAX_VALUE or Long.MAX_VALUE)
```

---

## 3. Package Statement

### Package Rules

```java
// 1. Package declaration
package com.durgasoft.core;

// 2. Rules:
// - Must be first statement (except comments)
// - Only one package per file
// - All lowercase convention
// - Reverse domain name: com.company.project

// 3. Package folder structure
// package com.durgasoft.core;
// Folder: com/durgasoft/core/

// 4. Default package
// If no package declaration, class is in default package
// Cannot be imported by classes in named packages

// 5. Subpackages
// java.util.*    → imports java.util classes only
// java.util.regex.* → must import separately
```

---

## 4. Class Level Modifiers

### public

```java
// public class: accessible from anywhere
public class PublicClass {
    public static void main(String[] args) {
        System.out.println("Accessible everywhere");
    }
}

// File name: PublicClass.java
```

### default (package-private)

```java
// No modifier = package-private
// Accessible only within the same package
class DefaultClass {
    // Accessible only in this package
}

// In another package:
// DefaultClass d = new DefaultClass();  // ❌ ERROR: not visible
```

### final

```java
// final class: cannot be extended/inherited
public final class FinalClass {
    // Cannot be subclassed
}

// class SubClass extends FinalClass { }  // ❌ ERROR: cannot inherit from final class

// Use cases:
// 1. Security: String class is final
// 2. Immutability: prevent modification through inheritance
// 3. API stability: prevent breaking changes

// Examples of final classes in Java:
// String, StringBuffer, System, Integer, Double, Math, Scanner
```

### abstract

```java
// abstract class: cannot be instantiated
// Must be extended and implemented
public abstract class AbstractClass {
    // Can have abstract methods and concrete methods
    public abstract void abstractMethod();  // No body
    
    public void concreteMethod() {          // Has body
        System.out.println("Concrete method");
    }
}

// AbstractClass a = new AbstractClass();  // ❌ ERROR: cannot instantiate

// Must extend:
class ConcreteClass extends AbstractClass {
    @Override
    public void abstractMethod() {
        System.out.println("Implementation");
    }
}

// abstract class with only concrete methods: valid but not useful
public abstract class UselessAbstract {
    public void method() { }  // No abstract methods
}
// Can create subclass to instantiate
```

### strictfp

```java
// strictfp: strict floating point
// Ensures same floating point results across all platforms
public strictfp class StrictFPClass {
    public strictfp void method() {
        double result = 10.0 / 3.0;  // Consistent across platforms
    }
}

// strictfp can be applied to:
// 1. Class
// 2. Method
// NOT to: variable, constructor

// Modern JVMs don't need strictfp (IEEE 754 is standard)
```

### Class Modifiers Summary

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CLASS LEVEL MODIFIERS                              │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Modifier           │ Effect                                   │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ public             │ Accessible everywhere                    │   │
│  │ default            │ Accessible only in package               │   │
│  │ final              │ Cannot be subclassed                     │   │
│  │ abstract           │ Cannot be instantiated, may have         │   │
│  │                    │ abstract methods                         │   │
│  │ strictfp           │ Strict floating point behavior           │   │
│  └────────────────────┴──────────────────────────────────────────┘   │
│                                                                  │
│  Valid combinations:                                             │
│  - public + final ✅                                              │
│  - public + abstract ✅                                           │
│  - public + strictfp ✅                                           │
│  - abstract + final ❌ (contradiction)                            │
│  - final + strictfp ✅                                            │
│  - public + abstract + strictfp ✅                                │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. Member Level Modifiers

### public, protected, default, private

```java
public class MemberModifiers {
    // 1. public: accessible everywhere
    public int publicVar;
    
    // 2. protected: accessible in package + subclasses (even in other packages)
    protected int protectedVar;
    
    // 3. default (package-private): accessible only in package
    int defaultVar;
    
    // 4. private: accessible only within class
    private int privateVar;
    
    // Same for methods:
    public void publicMethod() { }
    protected void protectedMethod() { }
    void defaultMethod() { }
    private void privateMethod() { }
}
```

### Access Modifier Summary

```
┌─────────────────────────────────────────────────────────────────────┐
│                    MEMBER ACCESS MODIFIERS SUMMARY                      │
│  ┌────────────────┬──────────┬──────────┬──────────┬──────────┐       │
│  │ Location       │ public   │ protected│ default  │ private  │       │
│  ├────────────────┼──────────┼──────────┼──────────┼──────────┤       │
│  │ Same class     │ ✅       │ ✅       │ ✅       │ ✅       │       │
│  │ Same package   │ ✅       │ ✅       │ ✅       │ ❌       │       │
│  │ Subclass (same │ ✅       │ ✅       │ ✅       │ ❌       │       │
│  │ package)       │          │          │          │          │       │
│  │ Subclass (diff │ ✅       │ ✅       │ ❌       │ ❌       │       │
│  │ package)       │          │          │          │          │       │
│  │ Other packages │ ✅       │ ❌       │ ❌       │ ❌       │       │
│  └────────────────┴──────────┴──────────┴──────────┴──────────┘       │
│                                                                  │
│  Visibility: public > protected > default > private                │
└─────────────────────────────────────────────────────────────────────┘
```

### final Methods

```java
public class Parent {
    // final method: cannot be overridden
    public final void finalMethod() {
        System.out.println("Cannot override");
    }
    
    public void normalMethod() {
        System.out.println("Can override");
    }
}

class Child extends Parent {
    // @Override
    // public void finalMethod() { }  // ❌ ERROR: cannot override final method
    
    @Override
    public void normalMethod() {      // ✅ Can override
        System.out.println("Overridden");
    }
}
```

### abstract Methods

```java
public abstract class AbstractClass {
    // abstract method: no body, must be overridden
    public abstract void abstractMethod();
    
    // abstract method rules:
    // 1. Must be in abstract class
    // 2. No body (no {})
    // 3. Must be overridden by subclass
    // 4. Cannot be private, static, final, synchronized
    // 5. Can be public or protected
}

class Concrete extends AbstractClass {
    @Override
    public void abstractMethod() {    // ✅ Must override
        System.out.println("Implemented");
    }
}
```

### synchronized Methods

```java
public class SyncDemo {
    // synchronized: thread-safe
    // Only one thread can execute at a time
    
    public synchronized void syncMethod() {
        // Thread-safe code
    }
    
    public static synchronized void staticSyncMethod() {
        // Class-level lock
    }
    
    public void syncBlock() {
        synchronized (this) {
            // Object-level lock
        }
    }
}
```

### native Methods

```java
public class NativeDemo {
    // native: implemented in other language (C/C++)
    // No body in Java
    
    public native void nativeMethod();
    
    // Used for:
    // 1. System-level operations
    // 2. Performance-critical code
    // 3. Accessing hardware
    
    // Requires JNI (Java Native Interface)
    
    static {
        System.loadLibrary("nativeLib");  // Load native library
    }
}
```

---

## 6. final Variables

### final Variable Rules

```java
public class FinalVariableDemo {
    // 1. final instance variable: must be initialized
    //    before constructor completes
    final int FINAL_VAR;
    
    // 2. final static variable: must be initialized
    //    at declaration or in static block
    static final int STATIC_FINAL = 100;
    
    // 3. final local variable: must be initialized before use
    public FinalVariableDemo() {
        FINAL_VAR = 10;  // ✅ Initialize in constructor
    }
    
    public void method() {
        // 4. final local variable
        final int LOCAL_FINAL;
        LOCAL_FINAL = 20;  // ✅ Initialize once
        // LOCAL_FINAL = 30;  // ❌ ERROR: cannot reassign
        
        // 5. final reference variable
        final StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");  // ✅ Can modify object
        // sb = new StringBuilder();  // ❌ ERROR: cannot reassign reference
    }
    
    // 6. Blank final variable: declared without initialization
    final int BLANK_FINAL;
    
    {
        BLANK_FINAL = 50;  // ✅ Initialize in instance block
    }
    
    // 7. final parameter
    public void finalParam(final int x) {
        // x = 10;  // ❌ ERROR: cannot modify final parameter
    }
}
```

---

## 7. static Modifier

### static Variables

```java
public class StaticDemo {
    // static variable: belongs to class, not object
    static int count = 0;
    
    public StaticDemo() {
        count++;  // Increment shared counter
    }
    
    public static void main(String[] args) {
        new StaticDemo();
        new StaticDemo();
        new StaticDemo();
        
        System.out.println(StaticDemo.count);  // 3
        // Access via class name (recommended)
    }
}
```

### static Methods

```java
public class StaticMethodDemo {
    // static method: belongs to class
    static void staticMethod() {
        System.out.println("Static method");
        // Cannot access non-static members directly
    }
    
    void instanceMethod() {
        System.out.println("Instance method");
        // Can access static and non-static members
    }
    
    public static void main(String[] args) {
        staticMethod();          // ✅ Direct call
        StaticMethodDemo.staticMethod();  // ✅ Via class name
        
        // staticMethodDemo().instanceMethod();  // ❌ ERROR: need object
        
        StaticMethodDemo obj = new StaticMethodDemo();
        obj.instanceMethod();    // ✅ Via object
    }
}
```

### static Block

```java
public class StaticBlockDemo {
    static int x;
    
    // static block: executes when class is loaded
    static {
        System.out.println("Static block 1");
        x = 10;
    }
    
    static {
        System.out.println("Static block 2");
        x = 20;
    }
    
    public static void main(String[] args) {
        System.out.println("Main method");
        System.out.println(x);  // 20
    }
}
// Output: Static block 1
//         Static block 2
//         Main method
//         20

// Execution order:
// 1. static variables (in order)
// 2. static blocks (in order)
// 3. main method
```

### static Import

```java
import static java.lang.Math.*;
import static java.lang.System.out;

public class StaticImportDemo {
    public static void main(String[] args) {
        out.println(PI);           // No Math.PI
        out.println(sqrt(16));     // No Math.sqrt
        out.println(max(10, 20));  // No Math.max
    }
}
```

---

## 8. native, transient, volatile

### native

```java
// native: method implemented in native code (C/C++)
// No body in Java
public class NativeExample {
    public native void nativeMethod();
    
    static {
        System.loadLibrary("nativeLib");
    }
}

// System.out.println() is native internally
// System.arraycopy() is native
```

### transient

```java
// transient: variable not serialized
// Skip during serialization

import java.io.*;

public class TransientDemo implements Serializable {
    String username;           // Serialized
    transient String password; // NOT serialized
    
    public static void main(String[] args) throws Exception {
        TransientDemo obj = new TransientDemo();
        obj.username = "user";
        obj.password = "pass";
        
        // Serialize
        ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("file.ser"));
        oos.writeObject(obj);
        
        // Deserialize
        ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream("file.ser"));
        TransientDemo obj2 = (TransientDemo) ois.readObject();
        
        System.out.println(obj2.username);  // user
        System.out.println(obj2.password);  // null
    }
}
```

### volatile

```java
// volatile: variable may be modified by multiple threads
// Ensures visibility across threads

public class VolatileDemo {
    // Without volatile: thread may cache the value
    // With volatile: always reads from main memory
    volatile boolean running = true;
    
    public void stop() {
        running = false;
    }
    
    public void doWork() {
        while (running) {
            // Do work
        }
    }
}

// Use cases:
// 1. Stop flag for threads
// 2. State variables
// 3. Double-checked locking (with care)

// volatile does NOT guarantee atomicity
// For atomic operations, use AtomicInteger, synchronized, etc.
```

---

## 9. Interfaces

### Interface Basics

```java
// Interface: pure abstract class (all methods abstract until Java 7)
// Java 8+: default and static methods
// Java 9+: private methods

public interface MyInterface {
    // 1. Abstract methods (implicitly public abstract)
    void method1();                    // public abstract by default
    public abstract void method2();    // Explicit (redundant)
    
    // 2. Constants (implicitly public static final)
    int MAX = 100;                     // public static final by default
    public static final int MIN = 0;   // Explicit (redundant)
    
    // 3. Default methods (Java 8+)
    default void defaultMethod() {
        System.out.println("Default method");
    }
    
    // 4. Static methods (Java 8+)
    static void staticMethod() {
        System.out.println("Static method");
    }
    
    // 5. Private methods (Java 9+)
    private void privateMethod() {
        System.out.println("Private method");
    }
    
    // 6. Private static methods (Java 9+)
    private static void privateStaticMethod() {
        System.out.println("Private static method");
    }
}

// Implementing interface
class MyClass implements MyInterface {
    @Override
    public void method1() {
        System.out.println("Implemented");
    }
    
    @Override
    public void method2() {
        System.out.println("Implemented");
    }
    
    // Optional: override default method
    @Override
    public void defaultMethod() {
        System.out.println("Overridden default");
    }
}
```

### Interface Rules

```java
// 1. Interface methods are implicitly public
interface Test {
    void method();  // public abstract
}

// 2. Cannot reduce visibility in implementation
class Impl implements Test {
    // void method() { }  // ❌ ERROR: cannot reduce visibility
    public void method() { }  // ✅ Must be public
}

// 3. Interface variables are constants
interface Constants {
    int MAX = 100;  // public static final
    // MAX = 200;   // ❌ ERROR: cannot assign a value to final variable
}

// 4. Multiple interfaces
class MyClass implements Interface1, Interface2 {
    // Must implement all abstract methods
}

// 5. Interface extends interface
interface SubInterface extends SuperInterface {
    // Inherits all methods
}

// 6. Multiple inheritance of type
interface A { void method(); }
interface B { void method(); }
class C implements A, B {
    public void method() { }  // ✅ Single implementation satisfies both
}

// 7. Interface with same default methods
interface A { default void method() { } }
interface B { default void method() { } }
class C implements A, B {
    public void method() {  // ✅ Must override to resolve conflict
        A.super.method();   // Call specific default method
    }
}
```

### Interface vs Abstract Class

```
┌─────────────────────────────────────────────────────────────────────┐
│                    INTERFACE vs ABSTRACT CLASS                        │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Interface         │ Abstract Class       │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Methods            │ Abstract (Java 7) │ Abstract + Concrete  │  │
│  │                    │ Default (Java 8+) │                      │  │
│  │                    │ Static (Java 8+)  │                      │  │
│  │                    │ Private (Java 9+)│                      │  │
│  │ Variables          │ public static final│ Any type          │  │
│  │ Constructor        │ No                │ Yes                  │  │
│  │ Instantiation      │ No                │ No                   │  │
│  │ Multiple inheritance│ Yes (type)       │ No                   │  │
│  │ extends            │ interface         │ class                │  │
│  │ implements         │ class             │ N/A                  │  │
│  │ Use case           │ Defines contract  │ Partial implementation│  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 10. Interview Questions

### Q1. What is the difference between `final`, `finally`, and `finalize`?

```java
// final: keyword — applies to class, method, variable
final class A { }           // Cannot be inherited
final void method() { }     // Cannot be overridden
final int x = 10;           // Cannot be reassigned

// finally: block — executes always after try-catch
try {
    // code
} catch (Exception e) {
    // handle
} finally {
    // Always executes
}

// finalize(): method — called by GC before object destruction
public class Test {
    protected void finalize() {
        // Cleanup code
    }
}

// final    → compile-time concept
// finally  → exception handling
// finalize → garbage collection
```

### Q2. Can we declare a class as both `final` and `abstract`?

```java
// ❌ NO — contradiction
final class FinalClass { }      // Cannot be inherited
abstract class AbstractClass { } // Must be inherited

// final abstract class Test { }  // ❌ ERROR: illegal combination

// final: cannot be subclassed
// abstract: must be subclassed
// These are mutually exclusive
```

### Q3. What is the difference between `public` and `protected`?

```java
// public: accessible everywhere
// protected: accessible in package + subclasses (any package)

public class Parent {
    public void publicMethod() { }
    protected void protectedMethod() { }
}

// Same package:
class SamePackage {
    void test() {
        Parent p = new Parent();
        p.publicMethod();      // ✅
        p.protectedMethod();   // ✅
    }
}

// Different package:
package other;
class Child extends Parent {
    void test() {
        publicMethod();        // ✅
        protectedMethod();     // ✅ (inherited)
    }
}

class OtherClass {
    void test() {
        Parent p = new Parent();
        p.publicMethod();      // ✅
        // p.protectedMethod();  // ❌ ERROR: not accessible
    }
}
```

### Q4. Can we override a `static` method?

```java
// ❌ NO — static methods are hidden, not overridden
// This is called method hiding

class Parent {
    public static void staticMethod() {
        System.out.println("Parent static");
    }
    
    public void instanceMethod() {
        System.out.println("Parent instance");
    }
}

class Child extends Parent {
    // Hiding (not overriding)
    public static void staticMethod() {
        System.out.println("Child static");
    }
    
    // Overriding
    @Override
    public void instanceMethod() {
        System.out.println("Child instance");
    }
}

Parent p = new Child();
p.staticMethod();     // "Parent static" (resolved by reference type)
p.instanceMethod();   // "Child instance" (resolved by object type)
```

### Q5. What is the difference between `static` and `instance` variables?

```java
// static: one copy per class
// instance: one copy per object

class Counter {
    static int staticCount = 0;    // Shared
    int instanceCount = 0;         // Per object
    
    Counter() {
        staticCount++;
        instanceCount++;
    }
}

Counter c1 = new Counter();
// staticCount = 1, instanceCount = 1
Counter c2 = new Counter();
// staticCount = 2, instanceCount = 1
Counter c3 = new Counter();
// staticCount = 3, instanceCount = 1

// Access:
Counter.staticCount;   // ✅ Class name
// c1.staticCount;     // ✅ Object (not recommended)
c1.instanceCount;      // ✅ Object
```

### Q6. Can we have multiple `static` blocks?

```java
// ✅ YES — execute in order
class Test {
    static {
        System.out.println("Block 1");
    }
    
    static {
        System.out.println("Block 2");
    }
    
    public static void main(String[] args) {
        System.out.println("Main");
    }
}
// Output: Block 1
//         Block 2
//         Main

// Execution order:
// 1. static variables (in order of declaration)
// 2. static blocks (in order of declaration)
// 3. main method
```

### Q7. What is `transient`?

```java
// transient: skip variable during serialization
// Used for sensitive data (passwords, keys)

public class User implements Serializable {
    String username;           // Serialized
    transient String password; // NOT serialized
    transient int age;       // NOT serialized
}

// After deserialization:
// username = "original value"
// password = null
// age = 0
```

### Q8. What is `volatile`?

```java
// volatile: variable may be modified by multiple threads
// Ensures visibility (read from main memory)

public class VolatileExample {
    volatile boolean flag = false;
    
    public void stop() {
        flag = true;  // Visible to all threads immediately
    }
    
    public void run() {
        while (!flag) {
            // Work
        }
    }
}

// volatile vs synchronized:
// - volatile: visibility only, no atomicity
// - synchronized: visibility + atomicity (mutual exclusion)
```

### Q9. Can we have `abstract` methods in a non-abstract class?

```java
// ❌ NO
class NormalClass {
    // abstract void method();  // ❌ ERROR: abstract method in non-abstract class
}

// If a class has an abstract method, it MUST be declared abstract
abstract class AbstractClass {
    abstract void method();  // ✅
}

// Or implement the method:
class NormalClass {
    void method() {           // ✅ Concrete method
        System.out.println("Implemented");
    }
}
```

### Q10. What is `strictfp`?

```java
// strictfp: strict floating point
// Ensures consistent floating point results across platforms

public strictfp class StrictFP {
    public strictfp void method() {
        double d = 10.0 / 3.0;
        // Same result on all JVMs
    }
}

// Applicable to: class, method
// Not applicable to: variable, constructor

// Modern JVMs: IEEE 754 is standard, so strictfp is rarely needed
```

---

## 11. Quick Reference

### Class Modifiers

```
public class ClassName        { }  // ✅
class ClassName               { }  // ✅
final class ClassName         { }  // ✅
abstract class ClassName      { }  // ✅
strictfp class ClassName      { }  // ✅
public final class ClassName  { }  // ✅
public abstract class ClassName { }  // ✅
public strictfp class ClassName { }  // ✅
final abstract class ClassName{ }  // ❌
```

### Member Modifiers

```
public void method()           { }  // ✅
protected void method()        { }  // ✅
void method()                  { }  // ✅
private void method()          { }  // ✅
final void method()            { }  // ✅
abstract void method()         { }  // ✅
static void method()           { }  // ✅
synchronized void method()     { }  // ✅
native void method()           { }  // ✅
strictfp void method()         { }  // ✅
```

### Access Levels

```
Same class:       public ✅ protected ✅ default ✅ private ✅
Same package:     public ✅ protected ✅ default ✅ private ❌
Subclass (same):  public ✅ protected ✅ default ✅ private ❌
Subclass (diff):  public ✅ protected ✅ default ❌ private ❌
Other packages:   public ✅ protected ❌ default ❌ private ❌
```

---

## 12. Key Takeaways

1. **Only one public class** per file; file name must match.
2. **Multiple non-public classes** can be in the same file.
3. **Package** must be first statement (except comments).
4. **import** is compile-time only; `java.lang` is auto-imported.
5. **Static import** brings static members into scope without class name.
6. **public** class: accessible everywhere; **default**: package-only.
7. **final class**: cannot be subclassed; **abstract class**: must be subclassed.
8. **abstract + final** is illegal combination.
9. **public > protected > default > private** visibility.
10. **final method**: cannot be overridden; **abstract method**: must be overridden.
11. **static**: belongs to class, not object; shared by all instances.
12. **static block**: executes when class is loaded; multiple blocks execute in order.
13. **final variable**: can be assigned only once; must be initialized.
14. **transient**: skip during serialization; **volatile**: ensure thread visibility.
15. **native**: implemented in C/C++; requires JNI.
16. **Interface**: 100% abstract (Java 7); default/static methods (Java 8+); private methods (Java 9+).
17. **Interface variables**: implicitly public static final (constants).
18. **Interface methods**: implicitly public abstract (until default/static added).
19. **Multiple inheritance**: allowed with interfaces (type), not with classes.
20. **strictfp**: ensures consistent floating point across platforms.

---

**Happy coding! 🚀**

*Declarations and access modifiers control the visibility and behavior of your Java code — understand them to write clean, maintainable code.*
