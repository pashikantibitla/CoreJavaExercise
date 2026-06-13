# 01 — Source File Structure

> **Video:** 1–4  
> **Topics:** Package Statements, Import Statements, Source File Organization

---

## Table of Contents

1. [Package Statement](#1-package-statement)
2. [Import Statement](#2-import-statement)
3. [Source File Rules](#3-source-file-rules)
4. [Interview Questions](#4-interview-questions)
5. [Quick Reference](#5-quick-reference)
6. [Key Takeaways](#6-key-takeaways)

---

## 1. Package Statement

### What is a Package?

A package is a namespace that organizes a set of related classes and interfaces. It is like a folder in a file system.

```
┌─────────────────────────────────────────────────────────┐
│                    PACKAGE HIERARCHY                       │
│                                                          │
│  com.company.project                                     │
│  ├── module1                                             │
│  │   ├── ClassA.java                                     │
│  │   └── ClassB.java                                     │
│  ├── module2                                             │
│  │   ├── ClassC.java                                     │
│  │   └── ClassD.java                                     │
│  └── util                                                │
│      └── Helper.java                                     │
│                                                          │
│  Folder structure: com/company/project/module1/ClassA.java│
└─────────────────────────────────────────────────────────┘
```

### Syntax

```java
// Package declaration (must be the first line in the file)
package com.company.project;

public class MyClass {
    // class body
}
```

### Rules for Package Statement

```
┌─────────────────────────────────────────────────────────────┐
│                    PACKAGE RULES                             │
│  ┌────────────────────┬──────────────────────────────────┐│
│  │ Rule               │ Description                        ││
│  ├────────────────────┼──────────────────────────────────┤│
│  │ First line         │ Must be the first line (except     ││
│  │                    │ comments)                          ││
│  │ One per file       │ Only one package statement per file││
│  │ Naming convention  │ All lowercase, reverse domain      ││
│  │ Subpackages        │ Use dot (.) to separate levels     ││
│  │ Folder structure   │ Must match package hierarchy       ││
│  │ Default package    │ No package = default package       ││
│  └────────────────────┴──────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
```

### Example

```java
// File: com/durgasoft/oop/Student.java
package com.durgasoft.oop;

public class Student {
    private int id;
    private String name;
    
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

// File: com/durgasoft/oop/Main.java
package com.durgasoft.oop;

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.display();
    }
}
```

---

## 2. Import Statement

### What is Import?

The `import` statement tells the compiler where to find classes that are referenced in the code but not defined in the same file.

```
┌─────────────────────────────────────────────────────────────┐
│                    IMPORT TYPES                              │
│  ┌────────────────────┬──────────────────────────────────┐│
│  │ Import Type          │ Example                          ││
│  ├────────────────────┼──────────────────────────────────┤│
│  │ Single class         │ import java.util.Date;           ││
│  │ Entire package       │ import java.util.*;              ││
│  │ Static import        │ import static java.lang.Math.*;  ││
│  │ Static method        │ import static java.lang.Math.PI; ││
│  └────────────────────┴──────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
```

### Important Points About Import

```java
// 1. import is not needed for java.lang package (automatically imported)
// System, String, Object, etc. are automatically available

// 2. import is not needed for classes in the same package
// If two classes are in the same package, no import needed

// 3. import does NOT make the class file bigger
// It is only for compile-time resolution

// 4. import is resolved at compile time, not runtime
// No performance impact at runtime

// 5. Wildcard import (*) only imports classes, NOT subpackages
import java.util.*;  // Imports all classes in java.util
// Does NOT import java.util.regex.* or java.util.concurrent.*

// 6. Fully qualified name (FQN) avoids import
java.util.Date d = new java.util.Date();  // No import needed

// 7. Static import brings static members into scope
import static java.lang.Math.*;
System.out.println(PI);  // No need for Math.PI
System.out.println(sqrt(16));  // No need for Math.sqrt
```

### Example: Import vs Fully Qualified Name

```java
// Method 1: Import
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Date d = new Date();  // Simple
    }
}

// Method 2: Fully Qualified Name
public class Demo {
    public static void main(String[] args) {
        java.util.Date d = new java.util.Date();  // Verbose
    }
}
```

---

## 3. Source File Rules

### Complete Source File Structure

```java
// 1. Package statement (optional, but must be first)
package com.durgasoft.oop;

// 2. Import statements (optional, after package)
import java.util.Date;
import java.util.ArrayList;
import static java.lang.Math.*;

// 3. Comments (can appear anywhere)
/* This is a class that demonstrates source file structure */

// 4. Class/interface/enum declarations
public class SourceFileDemo {
    // 5. Fields (instance variables)
    private int id;
    private String name;
    
    // 6. Constructors
    public SourceFileDemo(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // 7. Methods
    public void display() {
        System.out.println("ID: " + id);
    }
    
    // 8. Main method (optional)
    public static void main(String[] args) {
        SourceFileDemo obj = new SourceFileDemo(1, "Test");
        obj.display();
    }
}

// 9. Multiple classes (only one public class per file)
class HelperClass {
    // This class is NOT public
    // Can have multiple non-public classes in one file
}
```

### Rules for Source File

```
┌─────────────────────────────────────────────────────────────┐
│                    SOURCE FILE RULES                         │
│  ┌────────────────────┬──────────────────────────────────┐  │
│  │ Rule               │ Description                        │  │
│  ├────────────────────┼──────────────────────────────────┤  │
│  │ Only one public    │ Only one public class per file     │  │
│  │ class per file     │                                    │  │
│  │ File name          │ Must match the public class name   │  │
│  │ Case sensitive     │ File name = ClassName.java         │  │
│  │ No public class    │ File name can be anything.java     │  │
│  │ Multiple classes   │ Any number of non-public classes    │  │
│  │ Package first      │ Package must be first statement   │  │
│  │ Import after pkg   │ Import after package, before class│  │
│  └────────────────────┴──────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

### Example: Multiple Classes in One File

```java
// File: OuterClass.java

public class OuterClass {
    public static void main(String[] args) {
        System.out.println("Main class");
    }
}

// Non-public class — can be in the same file
class InnerClass1 {
    void display() {
        System.out.println("InnerClass1");
    }
}

// Another non-public class
class InnerClass2 {
    void display() {
        System.out.println("InnerClass2");
    }
}

// After compilation, 3 .class files are generated:
// OuterClass.class, InnerClass1.class, InnerClass2.class
```

---

## 4. Interview Questions

### Q1. Can a Java file have more than one public class?

```java
// ❌ NO — Only one public class per file
// This will cause a compilation error

public class ClassA {
    public static void main(String[] args) {
        System.out.println("Class A");
    }
}

public class ClassB {  // ❌ ERROR: ClassB is public, should be declared in a file named ClassB.java
    public static void main(String[] args) {
        System.out.println("Class B");
    }
}

// ✅ Solution: Make one public and others default (package-private)
public class ClassA { }
class ClassB { }  // ✅ Allowed
```

---

### Q2. What is the difference between `import java.util.Date` and `import java.util.*`?

```java
// Single class import
import java.util.Date;     // Only Date is imported
Date d = new Date();       // ✅ Works
ArrayList list = new ArrayList(); // ❌ Error — ArrayList not imported

// Wildcard import
import java.util.*;        // All classes in java.util are imported
Date d = new Date();       // ✅ Works
ArrayList list = new ArrayList(); // ✅ Works

// Note: There is NO performance difference at runtime
// Both are resolved at compile time
// Wildcard import does NOT import subpackages
```

---

### Q3. Is `import java.lang.*` required?

```java
// NO — java.lang is automatically imported by the compiler
// The following classes are always available without import:

// Classes:
String, System, Object, Integer, Double, Math, Thread, Exception, Runtime

// Interfaces:
Runnable, Comparable, Iterable

// Examples:
String s = "Hello";          // ✅ Works without import
System.out.println("Hi");    // ✅ Works without import
Object obj = new Object();   // ✅ Works without import

// The compiler automatically adds: import java.lang.*;
```

---

### Q4. What happens if a class is not in any package?

```java
// If no package statement, the class is in the DEFAULT package

// File: Hello.java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

// This class is in the default package
// Cannot be imported by classes in named packages
// Best practice: ALWAYS use a package
```

---

### Q5. Can we import a package without importing its subpackages?

```java
// Yes — wildcard import only imports classes, not subpackages

import java.util.*;        // Imports classes in java.util
// Does NOT import java.util.regex.*
// Does NOT import java.util.concurrent.*

// To use Pattern (in java.util.regex):
import java.util.regex.Pattern;  // Must import separately

// Or fully qualified:
java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-z]+");
```

---

## 5. Quick Reference

### Source File Order

```
1. Package statement (optional, must be first)
2. Import statements (optional)
3. Comments (anywhere)
4. Class/Interface/Enum declarations
   - Only one public class per file
   - File name must match public class name
```

### Import Types

```
import java.util.Date;        // Single class
import java.util.*;          // All classes in package
import static java.lang.Math.PI;  // Static field
import static java.lang.Math.*;   // All static members
```

---

## 6. Key Takeaways

1. **Package statement must be the first line** (except comments).
2. **Only one public class per file**, and file name must match.
3. **Any number of non-public classes** can be in the same file.
4. **Import is for compile-time only** — no runtime performance impact.
5. **`java.lang` is automatically imported** — no need for explicit import.
6. **Wildcard `*` imports classes only**, not subpackages.
7. **Fully qualified name** avoids import but is verbose.
8. **Static import** brings static members into scope.
9. **Default package** (no package) is for quick tests only — not for production.
10. **Reverse domain naming** for packages: `com.company.project`.

---

**Happy coding! 🚀**

*Source file structure is the foundation of organized Java code.*
