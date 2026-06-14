# 15 — JVM Architecture

> **Topics:** JVM Components, Class Loading, Linking, Initialization, Runtime Data Areas, Execution Engine, Class Loaders, Delegation Model, JVM Tuning

---

## Table of Contents

1. [Part 1: Introduction and Class Loading Subsystem](#part-1-introduction-and-class-loading-subsystem)
2. [Part 2: Linking (Verify, Prepare, Resolve)](#part-2-linking-verify-prepare-resolve)
3. [Part 3: Initialization](#part-3-initialization)
4. [Part 4: Runtime Data Areas](#part-4-runtime-data-areas)
5. [Part 5: Execution Engine](#part-5-execution-engine)
6. [Part 6: Class Loader Types](#part-6-class-loader-types)
7. [Part 7: Types of Class Loaders](#part-7-types-of-class-loaders)
8. [Part 8: How Java Class Loader Works](#part-8-how-java-class-loader-works)
9. [Part 9: JVM Options and Tuning](#part-9-jvm-options-and-tuning)
10. [Interview FAQs](#interview-faqs)
11. [Key Takeaways](#key-takeaways)

---

## Part 1: Introduction and Class Loading Subsystem

### What is the JVM?

The **Java Virtual Machine (JVM)** is an abstract computing machine that enables a computer to run Java programs. It converts Java bytecode into machine-specific instructions and provides the runtime environment.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JVM ARCHITECTURE OVERVIEW                        │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │                 Class Loader Subsystem                        │  │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐                      │  │
│  │  │ Loading │→│ Linking │→│Initialization│                  │  │
│  │  └─────────┘  └─────────┘  └─────────┘                      │  │
│  └─────────────────────────────────────────────────────────────┘  │
│                              │                                    │
│                              ▼                                    │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │                 Runtime Data Areas                            │  │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────┐  ┌───────┐ │  │
│  │  │ Method  │  │  Heap   │  │  Stack  │  │ PC  │  │Native │ │  │
│  │  │  Area   │  │         │  │         │  │ Reg │  │ Stack │ │  │
│  │  └─────────┘  └─────────┘  └─────────┘  └─────┘  └───────┘ │  │
│  └─────────────────────────────────────────────────────────────┘  │
│                              │                                    │
│                              ▼                                    │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │                  Execution Engine                               │  │
│  │  ┌───────────┐  ┌───────────┐  ┌─────────────────────────┐   │  │
│  │  │Interpreter│  │ JIT Compiler│  │    Garbage Collector    │   │  │
│  │  └───────────┘  └───────────┘  └─────────────────────────┘   │  │
│  └─────────────────────────────────────────────────────────────┘  │
│                              │                                    │
│                              ▼                                    │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │              Native Method Interface (JNI)                    │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### JVM Responsibilities

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JVM RESPONSIBILITIES                               │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Responsibility       │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Load Classes         │ Reads .class files and loads them      │  │
│  │ Verify Bytecode      │ Ensures bytecode is valid and safe      │  │
│  │ Execute Bytecode     │ Interprets or compiles to native code   │  │
│  │ Manage Memory        │ Allocates and deallocates memory (GC)   │  │
│  │ Provide Security     │ Sandbox, class loader isolation         │  │
│  │ Thread Management    │ Manages Java threads and synchronization│  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Class Loading Subsystem

The **Class Loader Subsystem** is responsible for loading class files into the JVM. It performs three main functions:

1. **Loading** — Reads the `.class` file and creates a `Class` object.
2. **Linking** — Verifies, prepares, and resolves the class.
3. **Initialization** — Executes static initializers and static variable assignments.

```
┌─────────────────────────────────────────────────────────────────────┐
│                 CLASS LOADING SUBSYSTEM                             │
│                                                                     │
│   ┌─────────┐    ┌─────────────────────────────────────┐           │
│   │ .class  │───→│           LOADING                     │           │
│   │  file   │    │  - Find and read .class file         │           │
│   └─────────┘    │  - Create Class object in Method Area │           │
│                  └─────────────────────────────────────┘           │
│                              │                                      │
│                              ▼                                      │
│                  ┌─────────────────────────────────────┐           │
│                  │           LINKING                     │           │
│                  │  ┌─────────┐ ┌─────────┐ ┌────────┐ │           │
│                  │  │ Verify  │→│ Prepare │→│ Resolve│ │           │
│                  │  └─────────┘ └─────────┘ └────────┘ │           │
│                  └─────────────────────────────────────┘           │
│                              │                                      │
│                              ▼                                      │
│                  ┌─────────────────────────────────────┐           │
│                  │        INITIALIZATION               │           │
│                  │  - Execute static blocks            │           │
│                  │  - Assign static variable values    │           │
│                  └─────────────────────────────────────┘           │
└─────────────────────────────────────────────────────────────────────┘
```

### Loading Phase

```java
// The JVM loads a class when it is first referenced
public class Test {
    public static void main(String[] args) {
        // Triggers loading of String class
        String s = "Hello";
        
        // Triggers loading of Test class itself
        System.out.println("Main executed");
    }
}
```

**Key Points:**
- Loading is triggered by **active use** of a class.
- The Class Loader reads the binary data and creates a `Class` object in the **Method Area**.
- The Class object encapsulates the runtime constant pool, field data, method data, and constructor data.

---

## Part 2: Linking (Verify, Prepare, Resolve)

### Overview of Linking

Linking combines the loaded class into the runtime state of the JVM so it can be executed. It has three phases:

1. **Verification**
2. **Preparation**
3. **Resolution**

```
┌─────────────────────────────────────────────────────────────────────┐
│                    LINKING PHASES                                     │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Phase              │ Purpose                                  │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Verification       │ Ensure bytecode is valid and safe        │  │
│  │ Preparation        │ Allocate memory for static variables     │  │
│  │                    │ and assign default values                │  │
│  │ Resolution         │ Convert symbolic references to direct  │  │
│  │                    │ references                               │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Verification

Verification ensures the loaded class is structurally correct and safe to execute.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    VERIFICATION CHECKS                              │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Check              │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Magic Number       │ 0xCAFEBABE at start of .class file      │  │
│  │ Version Check      │ Compatible major/minor version          │  │
│  │ Bytecode Verifier  │ Validates instruction streams           │  │
│  │ Type Checking      │ Ensures type safety of operations       │  │
│  │ Access Control     │ Validates public/private/protected      │  │
│  │ Stack Overflow     │ Checks stack does not overflow/underflow│  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
// If verification fails, the JVM throws VerifyError
// Example: manually corrupted bytecode or version mismatch
```

### Preparation

Preparation allocates memory for class variables (static fields) and assigns default values.

```java
public class PreparationDemo {
    // During preparation:
    static int a;        // allocated and set to 0
    static long b;       // allocated and set to 0L
    static boolean flag; // allocated and set to false
    static String name;  // allocated and set to null
    
    // Initialization (next phase) assigns explicit values
    static int x = 100;  // during preparation: 0, during initialization: 100
}
```

```
┌─────────────────────────────────────────────────────────────────────┐
│                    DEFAULT VALUES DURING PREPARATION                    │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Data Type          │ Default Value                            │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ byte, short, int   │ 0                                        │  │
│  │ long               │ 0L                                       │  │
│  │ float              │ 0.0f                                     │  │
│  │ double             │ 0.0d                                     │  │
│  │ boolean            │ false                                    │  │
│  │ char               │ '\u0000'                                 │  │
│  │ Reference          │ null                                     │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Resolution

Resolution replaces symbolic references in the constant pool with direct references.

```java
// Symbolic reference: class name, method name, field name
// Direct reference: actual memory address or pointer

public class ResolutionDemo {
    public static void main(String[] args) {
        // Symbolic reference: "java.lang.String"
        // Resolved to direct reference to String class in Method Area
        String s = new String("Hello");
        
        // Symbolic reference: "System.out.println"
        // Resolved to direct reference to PrintStream method
        System.out.println(s);
    }
}
```

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SYMBOLIC vs DIRECT REFERENCES                      │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Symbolic Reference │ Direct Reference                         │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Class name string    │ Pointer to Class object in Method Area   │  │
│  │ Method descriptor    │ Pointer to method code in Method Area    │  │
│  │ Field name           │ Pointer to field offset in object/Class  │  │
│  │ Constant pool entry  │ Actual memory address or index           │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## Part 3: Initialization

### What is Initialization?

Initialization is the process of executing the class's static initializer blocks and static variable assignments. It is the final phase of class loading.

```java
public class InitializationDemo {
    // Static variable initialization
    static int x = 10;
    static String name = "Java";
    
    // Static initializer block
    static {
        System.out.println("Static block executed");
        x = 20;
    }
    
    // Instance initializer (NOT part of class initialization)
    {
        System.out.println("Instance block executed");
    }
    
    public static void main(String[] args) {
        System.out.println("Main executed");
        System.out.println("x = " + x);
    }
}

// Output:
// Static block executed
// Main executed
// x = 20
```

### Order of Initialization

```
┌─────────────────────────────────────────────────────────────────────┐
│                    INITIALIZATION ORDER                             │
│                                                                     │
│  1. Load parent class (if not already loaded)                       │
│  2. Execute static variable declarations and static blocks          │
│     in the order they appear in the source code                     │
│  3. Load child class                                                │
│  4. Execute child static variable declarations and static blocks    │
│                                                                     │
│  Note: Instance initializers and constructors run when object       │
│        is created, NOT during class initialization.                   │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class InitializationOrder {
    static int a = 10;
    
    static {
        System.out.println("Static block 1: a = " + a);
        a = 20;
    }
    
    static int b = 30;
    
    static {
        System.out.println("Static block 2: b = " + b);
    }
    
    public static void main(String[] args) {
        System.out.println("Main: a = " + a + ", b = " + b);
    }
}

// Output:
// Static block 1: a = 10
// Static block 2: b = 30
// Main: a = 20, b = 30
```

### Active Use vs Passive Use

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ACTIVE USE (Triggers Initialization)               │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │ 1. Creating an instance of a class (new)                       │  │
│  │ 2. Accessing a static variable (except final compile-time const) │  │
│  │ 3. Invoking a static method                                    │  │
│  │ 4. Using reflection (Class.forName())                          │  │
│  │ 5. Executing main method of a class                            │  │
│  │ 6. Initializing a subclass (triggers parent initialization)      │  │
│  └───────────────────────────────────────────────────────────────┘  │
│                                                                     │
│                    PASSIVE USE (Does NOT Trigger Initialization)      │
│  ┌───────────────────────────────────────────────────────────────┐  │
│  │ 1. Referencing a static final compile-time constant            │  │
│  │ 2. Creating an array of a class type                           │  │
│  │ 3. Accessing a class via a reference type (e.g., Class c)      │  │
│  └───────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class ActiveUseDemo {
    static {
        System.out.println("ActiveUseDemo class initialized");
    }
    
    static final int CONSTANT = 100; // compile-time constant
    static int variable = 200;
}

public class Test {
    public static void main(String[] args) {
        // Passive use: does NOT trigger initialization
        int x = ActiveUseDemo.CONSTANT;
        ActiveUseDemo[] arr = new ActiveUseDemo[5];
        
        // Active use: triggers initialization
        int y = ActiveUseDemo.variable;
        // Output: ActiveUseDemo class initialized
    }
}
```

---

## Part 4: Runtime Data Areas

### Overview

The JVM defines several runtime data areas that are used during execution. Some are created when the JVM starts, while others are per-thread.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    RUNTIME DATA AREAS                                 │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │  SHARED BY ALL THREADS (Created on JVM start)                 │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │ Method Area                                             │  │  │
│  │  │  - Class structures, static variables, constant pool   │  │  │
│  │  │  - Code for methods and constructors                   │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │ Heap                                                    │  │  │
│  │  │  - All objects and arrays                              │  │  │
│  │  │  - Shared across all threads                           │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  └─────────────────────────────────────────────────────────────┘  │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │  PER THREAD (Created when thread starts)                       │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │ JVM Stack                                               │  │  │
│  │  │  - Frames for each method invocation                   │  │  │
│  │  │  - Local variables, operand stack, return value        │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │ PC Register                                             │  │  │
│  │  │  - Address of current executing instruction            │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  │  ┌─────────────────────────────────────────────────────────┐  │  │
│  │  │ Native Method Stack                                     │  │  │
│  │  │  - Stack for native (C/C++) method calls               │  │  │
│  │  └─────────────────────────────────────────────────────────┘  │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Method Area

The **Method Area** stores class-level data such as the runtime constant pool, field and method data, and the code for methods and constructors.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    METHOD AREA CONTENTS                               │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Content            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Class Name         │ Fully qualified class name               │  │
│  │ Parent Class Name  │ Super class reference                    │  │
│  │ Class Modifiers    │ public, abstract, final, etc.           │  │
│  │ Constant Pool      │ Literals, symbolic references            │  │
│  │ Field Data         │ Field names, types, modifiers            │  │
│  │ Method Data        │ Method names, signatures, bytecode       │  │
│  │ Static Variables   │ Class-level variable data                │  │
│  │ Constructor Data   │ Constructor bytecode and info            │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class MethodAreaDemo {
    // Stored in Method Area
    static int count = 0;
    static final String VERSION = "1.0";
    
    // Method bytecode stored in Method Area
    public static void main(String[] args) {
        count++;
        System.out.println("Count: " + count);
    }
}
```

### Heap

The **Heap** is the runtime data area where all class instances and arrays are allocated. It is shared by all threads.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    HEAP MEMORY STRUCTURE                              │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │                         HEAP                                  │  │
│  │  ┌─────────────────────────────────────────────────────┐   │  │
│  │  │               Young Generation                        │   │  │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐ │   │  │
│  │  │  │   Eden      │  │  Survivor 0 │  │  Survivor 1 │ │   │  │
│  │  │  │  (new objs) │  │  (S0)       │  │  (S1)       │ │   │  │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘ │   │  │
│  │  └─────────────────────────────────────────────────────┘   │  │
│  │  ┌─────────────────────────────────────────────────────┐   │  │
│  │  │               Old Generation                        │   │  │
│  │  │  (long-lived objects promoted from young gen)       │   │  │
│  │  └─────────────────────────────────────────────────────┘   │  │
│  │  ┌─────────────────────────────────────────────────────┐   │  │
│  │  │               Permanent Generation (MetaSpace)      │   │  │
│  │  │  (class metadata, constant pool — moved off-heap in    │   │  │
│  │  │   Java 8+ as Metaspace)                              │   │  │
│  │  └─────────────────────────────────────────────────────┘   │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class HeapDemo {
    public static void main(String[] args) {
        // Get runtime instance
        Runtime runtime = Runtime.getRuntime();
        
        // Memory in bytes
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Total Memory: " + totalMemory / 1024 / 1024 + " MB");
        System.out.println("Free Memory: " + freeMemory / 1024 / 1024 + " MB");
        System.out.println("Used Memory: " + usedMemory / 1024 / 1024 + " MB");
        
        // Create objects to consume heap
        for (int i = 0; i < 100000; i++) {
            new String("Object" + i);
        }
        
        System.gc(); // Request GC (not guaranteed)
        
        long freeAfterGC = runtime.freeMemory();
        System.out.println("Free after GC: " + freeAfterGC / 1024 / 1024 + " MB");
    }
}
```

### JVM Stack

Each thread has a private **JVM Stack** that stores frames. Each frame corresponds to a method invocation.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JVM STACK FRAME STRUCTURE                          │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Component          │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Local Variables    │ Parameters and local variables           │  │
│  │ Operand Stack      │ Stack for intermediate computation       │  │
│  │ Frame Data         │ Return address, exception table          │  │
│  │ Constant Pool Ref  │ Reference to runtime constant pool       │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class StackDemo {
    public static void main(String[] args) {
        methodA();
    }
    
    public static void methodA() {
        int x = 10;
        methodB();
    }
    
    public static void methodB() {
        int y = 20;
        methodC();
    }
    
    public static void methodC() {
        int z = 30;
        // Stack trace shows the stack frames
        Thread.currentThread().getStackTrace();
    }
}

// Stack (bottom to top):
// | main()  |
// | methodA()| x=10
// | methodB()| y=20
// | methodC()| z=30  ← current frame
```

### PC Register

The **Program Counter (PC) Register** holds the address of the currently executing JVM instruction. If the current method is native, the PC value is undefined.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    PC REGISTER                                        │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Per Thread         │ Each thread has its own PC register      │  │
│  │ Address Type       │ Offset into the method's bytecode        │  │
│  │ Native Methods     │ PC is undefined when executing native    │  │
│  │ Size               │ Small (holds one address)                │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Native Method Stack

The **Native Method Stack** is used for executing native (C/C++) methods. It is separate from the JVM Stack.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    NATIVE METHOD STACK                                │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Purpose            │ Stack for native method execution        │  │
│  │ Language           │ C/C++ functions called via JNI           │  │
│  │ Per Thread         │ Separate stack per thread                │  │
│  │ Size               │ Configurable (-Xss)                      │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## Part 5: Execution Engine

### Overview

The **Execution Engine** executes the bytecode instructions. It consists of three main components:

1. **Interpreter**
2. **JIT Compiler**
3. **Garbage Collector**

```
┌─────────────────────────────────────────────────────────────────────┐
│                    EXECUTION ENGINE                                   │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  Bytecode (from Method Area)                                  │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                              │                                      │
│              ┌───────────────┼───────────────┐                    │
│              ▼               ▼               ▼                    │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────────┐   │
│  │ Interpreter │  │ JIT Compiler│  │   Garbage Collector     │   │
│  │             │  │             │  │                         │   │
│  │ - Line by   │  │ - Compiles  │  │ - Reclaims memory       │   │
│  │   line exec │  │   hot code  │  │   from heap             │   │
│  │             │  │   to native │  │ - Runs automatically      │   │
│  │             │  │   code      │  │   or on request           │   │
│  └─────────────┘  └─────────────┘  └─────────────────────────┘   │
│                                                                     │
│  Output: Native machine code for the underlying platform            │
└─────────────────────────────────────────────────────────────────────┘
```

### Interpreter

The interpreter reads bytecode instructions one by one and executes them. It is simple but slower than compiled code.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    INTERPRETER                                        │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Execution          │ Line-by-line bytecode interpretation     │  │
│  │ Speed              │ Slower than compiled native code         │  │
│  │ Memory             │ Low memory overhead                      │  │
│  │ Startup            │ Fast startup (no compilation delay)      │  │
│  │ Use Case           │ Cold code, rarely executed methods       │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### JIT Compiler

The **Just-In-Time (JIT) Compiler** compiles frequently executed bytecode (hot code) into native machine code for better performance.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JIT COMPILER                                       │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Trigger            │ Method is called frequently (hotspot)    │  │
│  │ Compilation Level  │ C1 (client) vs C2 (server) compiler      │  │
│  │ Speed              │ Faster execution after compilation       │  │
│  │ Memory             │ Higher memory usage (compiled code cache)│  │
│  │ Startup            │ Slower initial execution due to compile│  │
│  │ Cache              │ Compiled code stored in code cache       │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
// JIT compiler works automatically; no manual code needed
// Example: loop that runs many times becomes hot code
public class JITDemo {
    public static void main(String[] args) {
        long start = System.nanoTime();
        
        int sum = 0;
        for (int i = 0; i < 10000000; i++) {
            sum += i;
        }
        
        long end = System.nanoTime();
        System.out.println("Sum: " + sum);
        System.out.println("Time: " + (end - start) / 1_000_000 + " ms");
    }
}
```

### Garbage Collector

The **Garbage Collector (GC)** reclaims memory from objects that are no longer reachable.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    GARBAGE COLLECTOR TYPES                            │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ GC Type            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Serial GC          │ Single-threaded, good for small apps     │  │
│  │ Parallel GC        │ Multi-threaded, high throughput          │  │
│  │ CMS (Concurrent    │ Low pause times, older concurrent GC     │  │
│  │   Mark Sweep)      │                                          │  │
│  │ G1 GC              │ Region-based, predictable pause times    │  │
│  │ ZGC                │ Ultra-low latency, scalable (Java 11+)   │  │
│  │ Shenandoah         │ Low pause times, concurrent (Java 12+)   │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class GCDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        
        System.out.println("Total: " + runtime.totalMemory());
        System.out.println("Free before: " + runtime.freeMemory());
        
        // Create garbage
        for (int i = 0; i < 100000; i++) {
            new String("Garbage" + i);
        }
        
        System.gc(); // Suggest GC (not guaranteed)
        
        System.out.println("Free after: " + runtime.freeMemory());
    }
}
```

---

## Part 6: Class Loader Types

### Built-in Class Loaders

Java provides three built-in class loaders:

```
┌─────────────────────────────────────────────────────────────────────┐
│                    BUILT-IN CLASS LOADERS                           │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Class Loader       │ Loads Classes From                       │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Bootstrap          │ `JAVA_HOME/jre/lib/rt.jar` (core Java) │  │
│  │ (Primordial)       │ `java.*`, `javax.*`, `sun.*`, etc.       │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Extension          │ `JAVA_HOME/jre/lib/ext` directory        │  │
│  │                    │ Extension libraries                       │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Application/System │ Classpath (user-defined classes)         │  │
│  │                    │ `-cp` or `CLASSPATH` environment           │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class ClassLoaderTypesDemo {
    public static void main(String[] args) {
        // Bootstrap class loader (returns null)
        ClassLoader bootstrap = String.class.getClassLoader();
        System.out.println("String class loader: " + bootstrap); // null
        
        // Extension class loader
        ClassLoader extension = ClassLoaderTypesDemo.class.getClassLoader().getParent();
        System.out.println("Extension class loader: " + extension);
        
        // Application class loader
        ClassLoader application = ClassLoaderTypesDemo.class.getClassLoader();
        System.out.println("Application class loader: " + application);
    }
}
```

---

## Part 7: Types of Class Loaders

### Classification

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CLASS LOADER CLASSIFICATION                        │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │  Built-in Class Loaders                                      │  │
│  │  ├─ Bootstrap Class Loader (C/C++ native code)               │  │
│  │  ├─ Extension Class Loader (Java class)                    │  │
│  │  └─ Application Class Loader (Java class)                  │  │
│  │                                                              │  │
│  │  User-defined Class Loaders                                  │  │
│  │  ├─ Custom class loaders extending ClassLoader             │  │
│  │  └─ Used for modular loading, security, hot deployment     │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CLASS LOADER HIERARCHY                             │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │                    Bootstrap Class Loader                     │  │
│  │                    (Loaded in native code)                    │  │
│  │                           │                                   │  │
│  │                           ▼                                   │  │
│  │                    Extension Class Loader                     │  │
│  │                    (sun.misc.Launcher$ExtClassLoader)         │  │
│  │                           │                                   │  │
│  │                           ▼                                   │  │
│  │                    Application Class Loader                   │  │
│  │                    (sun.misc.Launcher$AppClassLoader)          │  │
│  │                           │                                   │  │
│  │                           ▼                                   │  │
│  │                    Custom Class Loaders                       │  │
│  │                    (User-defined subclasses)                  │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## Part 8: How Java Class Loader Works

### Delegation Model

The **Parent Delegation Model** ensures that a class loader delegates class loading to its parent before attempting to load the class itself.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    DELEGATION MODEL FLOW                              │
│                                                                     │
│  1. Application Class Loader receives request to load a class       │
│  2. Delegates to Extension Class Loader                             │
│  3. Extension Class Loader delegates to Bootstrap Class Loader      │
│  4. Bootstrap Class Loader attempts to load                         │
│     - If found: returns Class object                                │
│     - If not found: delegates back down to child                    │
│  5. Extension Class Loader attempts to load                         │
│  6. Application Class Loader attempts to load (classpath)             │
│  7. If not found anywhere: ClassNotFoundException                   │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │  Application Class Loader asks Extension Class Loader          │  │
│  │         │                                                   │  │
│  │         ▼                                                   │  │
│  │  Extension Class Loader asks Bootstrap Class Loader          │  │
│  │         │                                                   │  │
│  │         ▼                                                   │  │
│  │  Bootstrap Class Loader checks core libraries                │  │
│  │         │                                                   │  │
│  │         ├─ Found? → Return Class                              │  │
│  │         └─ Not Found? → Return to Extension                  │  │
│  │                   │                                           │  │
│  │                   ▼                                           │  │
│  │  Extension Class Loader checks ext directory                 │  │
│  │         │                                                   │  │
│  │         ├─ Found? → Return Class                              │  │
│  │         └─ Not Found? → Return to Application                │  │
│  │                   │                                           │  │
│  │                   ▼                                           │  │
│  │  Application Class Loader checks classpath                   │  │
│  │         │                                                   │  │
│  │         ├─ Found? → Return Class                              │  │
│  │         └─ Not Found? → ClassNotFoundException               │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Why Delegation Model?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    BENEFITS OF DELEGATION MODEL                       │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Benefit            │ Explanation                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Security           │ Core classes loaded by Bootstrap cannot  │  │
│  │                    │ be overridden by malicious classes     │  │
│  │ Avoid Duplicates   │ Same class loaded only once by parent  │  │
│  │ Consistency        │ Ensures same class version across app    │  │
│  │ Extensibility      │ Custom loaders can extend behavior       │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```java
public class DelegationModelDemo {
    public static void main(String[] args) {
        ClassLoader appClassLoader = DelegationModelDemo.class.getClassLoader();
        System.out.println("Application ClassLoader: " + appClassLoader);
        
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println("Extension ClassLoader: " + extClassLoader);
        
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("Bootstrap ClassLoader: " + bootstrapClassLoader);
        
        // Expected output:
        // Application ClassLoader: sun.misc.Launcher$AppClassLoader@...
        // Extension ClassLoader: sun.misc.Launcher$ExtClassLoader@...
        // Bootstrap ClassLoader: null
    }
}
```

---

## Part 9: JVM Options and Tuning

### Common JVM Options

```
┌─────────────────────────────────────────────────────────────────────┐
│                    COMMON JVM OPTIONS                                 │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Option             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ -Xms<size>         │ Initial heap size (e.g., -Xms512m)      │  │
│  │ -Xmx<size>         │ Maximum heap size (e.g., -Xmx2g)        │  │
│  │ -Xss<size>         │ Thread stack size (e.g., -Xss1m)        │  │
│  │ -Xmn<size>         │ Young generation size                   │  │
│  │ -XX:PermSize       │ Permanent generation initial size       │  │
│  │ -XX:MaxPermSize    │ Permanent generation max size           │  │
│  │ -XX:MetaspaceSize  │ Metaspace initial size (Java 8+)       │  │
│  │ -XX:MaxMetaspaceSize│ Metaspace max size (Java 8+)           │  │
│  │ -XX:+UseG1GC       │ Use G1 Garbage Collector                │  │
│  │ -XX:+UseConcMarkSweepGC │ Use CMS GC (deprecated in Java 9+)   │  │
│  │ -XX:+UseSerialGC   │ Use Serial GC                           │  │
│  │ -XX:+UseParallelGC │ Use Parallel GC                         │  │
│  │ -XX:+PrintGCDetails│ Print detailed GC logs                  │  │
│  │ -XX:+HeapDumpOnOutOfMemoryError │ Dump heap on OOM               │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Example JVM Arguments

```bash
# Basic heap tuning
java -Xms512m -Xmx2g -Xss1m MyApp

# G1GC with logging
java -Xms1g -Xmx4g -XX:+UseG1GC -XX:+PrintGCDetails MyApp

# Heap dump on OOM
java -Xmx2g -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/logs MyApp

# Metaspace tuning (Java 8+)
java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m MyApp
```

### Monitoring Tools

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JVM MONITORING TOOLS                               │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Tool               │ Purpose                                  │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ jconsole           │ GUI for monitoring JVM (memory, threads) │  │
│  │ jvisualvm          │ Advanced profiling and monitoring        │  │
│  │ jstat              │ Command-line statistics (GC, heap)       │  │
│  │ jmap               │ Heap dump and histogram                │  │
│  │ jstack             │ Thread dump                            │  │
│  │ jcmd               │ Diagnostic commands                     │  │
│  │ Java Mission       │ Low-overhead continuous monitoring       │  │
│  │ Control (JMC)      │                                          │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

```bash
# jstat example: GC statistics every 1 second for 5 iterations
jstat -gcutil <pid> 1000 5

# jstack example: thread dump
jstack <pid> > threaddump.txt

# jmap example: heap dump
jmap -dump:format=b,file=heapdump.hprof <pid>
```

---

## Interview FAQs

### Q1. What is the JVM? How does it work?

```java
// The JVM is an abstract machine that:
// 1. Loads .class files via Class Loaders
// 2. Verifies bytecode for safety
// 3. Executes bytecode via interpreter or JIT compiler
// 4. Manages memory via Garbage Collector
// 5. Provides runtime data areas for program execution

// It converts Java bytecode (platform-independent) into native machine code
// for the specific OS and hardware.
```

### Q2. What is the difference between JDK, JRE, and JVM?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JDK vs JRE vs JVM                                  │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Component          │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ JVM                │ Runtime engine, executes bytecode        │  │
│  │ JRE                │ JVM + core libraries (no compiler)       │  │
│  │ JDK                │ JRE + development tools (javac, javadoc) │  │
│  │                    │ + debuggers, profilers                   │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q3. Explain the Class Loader Subsystem.

```java
// The Class Loader Subsystem performs three operations:
// 1. Loading: reads .class file and creates Class object
// 2. Linking: verify, prepare, and resolve
// 3. Initialization: execute static blocks and assign static variables

// Class loaders are hierarchical and follow the delegation model.
```

### Q4. What is the Delegation Model? Why is it used?

```java
// Delegation Model: a class loader delegates to its parent before loading.
// Order: Application → Extension → Bootstrap

// Benefits:
// - Security: core classes cannot be overridden
// - Avoid duplicate loading: parent loads first
// - Consistency: same class version across the application
```

### Q5. What is the difference between the Method Area and Heap?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    METHOD AREA vs HEAP                                │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ Method Area        │ Heap               │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Stores             │ Class metadata,    │ Objects, arrays    │  │
│  │                    │ static variables,  │                    │  │
│  │                    │ method code        │                    │  │
│  │ Shared             │ Yes (all threads)  │ Yes (all threads)  │  │
│  │ Created            │ JVM startup        │ JVM startup        │  │
│  │ GC                 │ Rare (MetaSpace)   │ Frequent (GC runs) │  │
│  │ Java 8+            │ Metaspace (off-heap)│ On-heap           │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q6. What is the JIT Compiler? How does it differ from the interpreter?

```java
// Interpreter: executes bytecode line by line. Slow but fast startup.
// JIT Compiler: compiles frequently used (hot) bytecode to native code.
//   - Faster execution after compilation
//   - Slower initial execution due to compilation overhead
//   - C1 (client) and C2 (server) compilers in HotSpot JVM
```

### Q7. What is the purpose of the Garbage Collector?

```java
// GC reclaims memory occupied by objects that are no longer reachable.
// It runs automatically in the background.
// Types: Serial, Parallel, CMS, G1, ZGC, Shenandoah.
// You can request GC with System.gc() but it is NOT guaranteed.
```

### Q8. What is the difference between the Stack and Heap?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    STACK vs HEAP                                      │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ Stack              │ Heap               │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Stores             │ Local variables,   │ Objects, arrays    │  │
│  │                    │ method frames      │                    │  │
│  │ Thread Safety      │ Per thread (safe)  │ Shared (needs sync)│  │
│  │ Size               │ Small (fixed)      │ Large (configurable)│  │
│  │ Management         │ Automatic (LIFO)   │ Garbage Collected  │  │
│  │ Speed              │ Fast allocation    │ Slower allocation  │  │
│  │ Scope              │ Method execution   │ Object lifetime  │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q9. What is `Class.forName()`? When does it trigger class loading?

```java
// Class.forName() loads and initializes a class dynamically.
// It triggers the full class loading process: load, link, initialize.

public class ClassForNameDemo {
    public static void main(String[] args) throws Exception {
        // Triggers loading, linking, and initialization
        Class<?> clazz = Class.forName("java.util.ArrayList");
        System.out.println("Class loaded: " + clazz.getName());
    }
}
```

### Q10. What are JVM options for tuning heap memory?

```bash
// -Xms: initial heap size
// -Xmx: maximum heap size
// -Xmn: young generation size
// -XX:MetaspaceSize: metaspace size (Java 8+)
// -XX:+UseG1GC: use G1 garbage collector

// Example:
java -Xms1g -Xmx4g -XX:+UseG1GC -XX:+PrintGCDetails MyApp
```

---

## Key Takeaways

1. **JVM** provides a runtime environment for executing Java bytecode.
2. **Class Loading Subsystem** handles loading, linking, and initialization.
3. **Linking** includes verification, preparation, and resolution.
4. **Initialization** executes static blocks and assigns static variables.
5. **Runtime Data Areas** include Method Area, Heap, Stack, PC Register, and Native Method Stack.
6. **Execution Engine** uses an interpreter, JIT compiler, and garbage collector.
7. **Class Loaders** are hierarchical: Bootstrap → Extension → Application.
8. **Delegation Model** ensures security and avoids duplicate class loading.
9. **JVM Tuning** involves heap size, stack size, and GC selection.
10. **Monitoring Tools** like jconsole, jvisualvm, jstat help analyze JVM behavior.

---

**Happy coding! 🚀**
