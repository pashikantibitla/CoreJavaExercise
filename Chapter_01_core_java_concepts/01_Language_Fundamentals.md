# 01 — Language Fundamentals

> **Videos:** 1–16  
> **Topics:** Identifiers, Data Types, Literals, Arrays, Variables, Var-arg, Main Method, Coding Standards

---

## Table of Contents

1. [Java Identifiers and Reserved Words](#1-java-identifiers-and-reserved-words)
2. [Data Types](#2-data-types)
3. [Literals](#3-literals)
4. [Arrays](#4-arrays)
5. [Types of Variables](#5-types-of-variables)
6. [Var-arg Methods](#6-var-arg-methods)
7. [Main Method](#7-main-method)
8. [Command Line Arguments](#8-command-line-arguments)
9. [Java Coding Standards](#9-java-coding-standards)
10. [Interview Questions](#10-interview-questions)
11. [Quick Reference](#11-quick-reference)
12. [Key Takeaways](#12-key-takeaways)

---

## 1. Java Identifiers and Reserved Words

### What are Identifiers?

An identifier is a name given to a programming element — class, method, variable, interface, enum, etc.

```
┌──────────────────────────────────────────────────────────────┐
│                    IDENTIFIER RULES                             │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Rule               │ Description                          │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ Allowed chars      │ a-z, A-Z, 0-9, _, $                 │  │
│  │ Cannot start with  │ Digit (0-9)                          │  │
│  │ Case sensitive     │ `total` and `Total` are different    │  │
│  │ No length limit    │ But keep it readable                 │  │
│  │ Cannot use         │ Reserved words (keywords)            │  │
│  │ No spaces          │ `student name` is invalid           │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Valid vs Invalid Identifiers

```java
// ✅ Valid Identifiers
int total;           // letters
int TOTAL;           // case sensitive
int _value;          // underscore
int $price;          // dollar sign
int studentName;     // camelCase
int student_name;    // snake_case
int i123;            // starts with letter, contains digits
int _;               // single underscore (Java 8+)

// ❌ Invalid Identifiers
int 123abc;          // ERROR: cannot start with digit
int student name;    // ERROR: space not allowed
int student-name;    // ERROR: hyphen not allowed
int class;           // ERROR: reserved word
int public;          // ERROR: reserved word
int student@name;    // ERROR: @ not allowed
```

### Reserved Words (Keywords)

```
┌──────────────────────────────────────────────────────────────┐
│                    JAVA RESERVED WORDS (53)                      │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Used keywords (50):                                     │   │
│  │                                                         │   │
│  │ abstract  assert   boolean  break    byte      case     │   │
│  │ catch     char     class    const    continue   default │   │
│  │ do        double   else      enum     extends    final   │   │
│  │ finally   float    for       goto     if         implements│  │
│  │ import    instanceof int      interface long      native │   │
│  │ new       package  private   protected public     return │   │
│  │ short     static   strictfp  super    switch     synchronized││
│  │ this      throw    throws    transient try       void    │   │
│  │ volatile  while                                           │   │
│  │                                                         │   │
│  │ Unused reserved words (2): goto, const                  │   │
│  │                                                         │   │
│  │ Literals (3): true, false, null                          │   │
│  │ (Note: true/false/null are not keywords but reserved)   │   │
│  └─────────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
```

### Classification of Reserved Words

```
┌──────────────────────────────────────────────────────────────┐
│              CLASSIFICATION OF RESERVED WORDS                   │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Category           │ Words                                │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ Data Types (8)     │ byte, short, int, long, float,       │  │
│  │                    │ double, char, boolean                  │  │
│  │ Flow Control (11)  │ if, else, switch, case, default,     │  │
│  │                    │ while, do, for, break, continue,      │  │
│  │                    │ return                                 │  │
│  │ Modifiers (11)     │ public, private, protected, static,    │  │
│  │                    │ final, abstract, synchronized,         │  │
│  │                    │ native, strictfp, transient, volatile  │  │
│  │ Exception Handling │ try, catch, finally, throw, throws   │  │
│  │ (5)                │                                        │  │
│  │ Class Related (6)  │ class, extends, implements, package, │  │
│  │                    │ import, enum                           │  │
│  │ Object Related (4) │ new, this, super, instanceof           │  │
│  │ Return Type (1)    │ void                                   │  │
│  │ Unused (2)         │ goto, const                            │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. Data Types

### Classification of Data Types

```
┌──────────────────────────────────────────────────────────────┐
│                    JAVA DATA TYPES                               │
│                                                                  │
│  ┌────────────────────┐    ┌─────────────────────────────────┐  │
│  │  Primitive (8)     │    │  Reference (Non-Primitive)      │  │
│  │                    │    │                                 │  │
│  │  ┌──────────────┐  │    │  ┌─────────────────────────┐   │  │
│  │  │ Numeric      │  │    │  │ Class                   │   │  │
│  │  │ ┌──────────┐ │  │    │  │ Interface               │   │  │
│  │  │ │ Integral │ │  │    │  │ Array                   │   │  │
│  │  │ │ byte     │ │  │    │  │ Enum                    │   │  │
│  │  │ │ short    │ │  │    │  │ String                  │   │  │
│  │  │ │ int      │ │  │    │  │ Annotation              │   │  │
│  │  │ │ long     │ │  │    │  │ etc.                    │   │  │
│  │  │ └──────────┘ │  │    │  └─────────────────────────┘   │  │
│  │  │ ┌──────────┐ │  │    │                                 │  │
│  │  │ │ Floating │ │  │    │  String is a class in Java      │  │
│  │  │ │ float    │ │  │    │  │  (not a primitive)           │  │
│  │  │ │ double   │ │  │    │  │                                 │  │
│  │  │ └──────────┘ │  │    │                                 │  │
│  │  │ char         │  │    │                                 │  │
│  │  │ boolean      │  │    │                                 │  │
│  │  └──────────────┘  │    │                                 │  │
│  └────────────────────┘    └─────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Primitive Data Types Details

```
┌─────────────────────────────────────────────────────────────────────┐
│                    PRIMITIVE DATA TYPES DETAILS                       │
│  ┌─────────┬─────────┬──────────────┬────────────────────────────────┐│
│  │ Type    │ Size    │ Range        │ Default Value                ││
│  ├─────────┼─────────┼──────────────┼────────────────────────────────┤│
│  │ byte    │ 1 byte  │ -128 to 127  │ 0                            ││
│  │ short   │ 2 bytes │ -32768 to    │ 0                            ││
│  │         │         │  32767       │                              ││
│  │ int     │ 4 bytes │ -2^31 to     │ 0                            ││
│  │         │         │  2^31-1      │                              ││
│  │ long    │ 8 bytes │ -2^63 to     │ 0L                           ││
│  │         │         │  2^63-1      │                              ││
│  │ float   │ 4 bytes │ ±3.4028e+38  │ 0.0f                         ││
│  │ double  │ 8 bytes │ ±1.7977e+308 │ 0.0d                         ││
│  │ char    │ 2 bytes │ 0 to 65535   │ '\u0000' (null char)         ││
│  │         │         │ (Unicode)    │                              ││
│  │ boolean │ 1 bit   │ true/false   │ false                        ││
│  └─────────┴─────────┴──────────────┴────────────────────────────────┘│
└─────────────────────────────────────────────────────────────────────┘
```

### Important Notes on Data Types

```java
// 1. byte is the smallest numeric type
byte b = 10;        // ✅
byte b = 128;       // ❌ ERROR: 128 is out of range (-128 to 127)

// 2. No decimal points in integer types
int i = 10.5;       // ❌ ERROR: incompatible types

// 3. char size in Java: 2 bytes (Unicode)
char c = 'a';       // ✅
char c = 97;        // ✅ (ASCII value of 'a')
char c = 65535;     // ✅ (max value)
char c = 65536;     // ❌ ERROR: out of range

// 4. boolean is NOT numeric
boolean flag = true;    // ✅
boolean flag = 0;       // ❌ ERROR: incompatible types
boolean flag = 1;       // ❌ ERROR: incompatible types

// 5. Default data types for literals
int x = 10;         // 10 is int by default
long x = 10L;       // 10L is long
float f = 10.5f;    // 10.5 is double by default, need 'f' suffix
double d = 10.5;    // 10.5 is double by default

// 6. Underscore in numeric literals (Java 7+)
int million = 1_000_000;   // ✅
long creditCard = 1234_5678_9012_3456L;  // ✅
```

---

## 3. Literals

### What are Literals?

A literal is a constant value that can be assigned to a variable.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TYPES OF LITERALS IN JAVA                          │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Literal Type       │ Examples                                 │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Integer Literal    │ 0, 9, 108, 0b1010, 0777, 0xFF         │  │
│  │ Floating Literal   │ 0.0, 3.14, 1.2e10, 1.2E10, 1.0f, 1.0d│  │
│  │ Boolean Literal    │ true, false                              │  │
│  │ Character Literal  │ 'a', '1', '$', '\n', '\t', '\u0000'     │  │
│  │ String Literal     │ "Hello", "123", "", "A"                 │  │
│  │ Null Literal       │ null                                     │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Integer Literals

```java
// 1. Decimal (Base 10)
int a = 10;         // ✅

// 2. Octal (Base 8) — starts with 0
int b = 010;        // 8 in decimal ✅
int c = 077;        // 63 in decimal ✅
// int d = 078;     // ❌ ERROR: 8 is not valid in octal

// 3. Hexadecimal (Base 16) — starts with 0x or 0X
int e = 0x10;       // 16 in decimal ✅
int f = 0xFF;       // 255 in decimal ✅
int g = 0xabc;      // 2748 in decimal ✅

// 4. Binary (Base 2) — starts with 0b or 0B (Java 7+)
int h = 0b1010;     // 10 in decimal ✅
int i = 0B1111;     // 15 in decimal ✅

// 5. Long literal — suffix L or l
long j = 10L;       // ✅
long k = 10l;       // ✅ (but avoid lowercase 'l' — looks like 1)
```

### Floating Point Literals

```java
// 1. double is default
double d = 10.5;    // ✅

// 2. float requires 'f' or 'F' suffix
float f = 10.5f;    // ✅
float f2 = 10.5F;   // ✅
// float f3 = 10.5; // ❌ ERROR: double cannot be assigned to float

// 3. Scientific notation
double d2 = 1.2e3;  // 1.2 × 10³ = 1200.0 ✅
double d3 = 1.2E3;  // same ✅
double d4 = 1.2e-3; // 1.2 × 10⁻³ = 0.0012 ✅

// 4. Special floating point values
double posInf = 10.0 / 0.0;   // Infinity
double negInf = -10.0 / 0.0;  // -Infinity
double nan = 0.0 / 0.0;       // NaN (Not a Number)
```

### Character and String Literals

```java
// Character literals
char c1 = 'a';      // ✅
char c2 = '1';      // ✅
char c3 = '$';      // ✅
// char c4 = 'ab';  // ❌ ERROR: too many characters

// Escape sequences
char newline = '\n';     // New line
char tab = '\t';         // Tab
char backspace = '\b';   // Backspace
char carriage = '\r';    // Carriage return
char formFeed = '\f';    // Form feed
char backslash = '\\';   // Backslash
char singleQuote = '\''; // Single quote
char doubleQuote = '\"'; // Double quote

// Unicode characters
char unicode = '\u0041';  // 'A' (Unicode for A)

// String literals
String s1 = "Hello";    // ✅
String s2 = "";         // ✅ (empty string)
String s3 = "A";        // ✅ (single char string)
```

### Boolean and Null Literals

```java
// Boolean literals
boolean flag = true;    // ✅
boolean flag2 = false; // ✅
// boolean flag3 = 0;   // ❌ ERROR: int cannot be converted to boolean
// boolean flag4 = 1;   // ❌ ERROR: int cannot be converted to boolean

// Null literal
String str = null;      // ✅ (reference types only)
// int x = null;        // ❌ ERROR: int is primitive
Object obj = null;      // ✅
```

---

## 4. Arrays

### What is an Array?

An array is an indexed collection of fixed number of homogeneous data elements.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ARRAY CHARACTERISTICS                              │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Fixed size         │ Size cannot be changed after creation    │  │
│  │ Homogeneous        │ All elements must be same type           │  │
│  │ Indexed            │ 0-based index                            │  │
│  │ Contiguous memory  │ Elements stored in contiguous memory    │  │
│  │ Reference type     │ Array is an object in Java               │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### 1D Array Declaration

```java
// 3 ways to declare 1D array
int[] a;            // ✅ Recommended (type is int[])
int []a;            // ✅ Space is optional
int a[];            // ✅ C-style (allowed for C/C++ programmers)

// All are valid and equivalent
int[] a, b, c;      // a, b, c are all int[]
int a[], b, c;      // a is int[], b and c are int
int[] a[], b[], c;  // a is int[][], b is int[][], c is int[]
```

### 1D Array Creation

```java
// 1. Declaration + Creation
int[] a = new int[5];   // 5 elements, all initialized to 0

// 2. Declaration + Initialization
int[] a = {10, 20, 30, 40, 50};   // ✅

// 3. Declaration + Creation + Initialization
int[] a = new int[] {10, 20, 30, 40, 50};   // ✅

// 4. Dynamic size
int size = 5;
int[] a = new int[size];   // ✅

// 5. Cannot specify size and values together
// int[] a = new int[5] {10, 20, 30, 40, 50};  // ❌ ERROR
```

### 2D Array (Multi-Dimensional Arrays)

```java
// 1. 2D Array Declaration
int[][] a;          // ✅ Recommended
int [][]a;          // ✅
int a[][];          // ✅
int[] a[];          // ✅ (confusing but valid)
int[][][] a;        // ✅ 3D array

// 2. 2D Array Creation
int[][] a = new int[3][4];   // 3 rows, 4 columns

// 3. 2D Array Initialization
int[][] a = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

// 4. Jagged Array (Ragged Array)
// Each row can have different number of columns
int[][] a = new int[3][];
a[0] = new int[4];   // Row 0: 4 columns
a[1] = new int[2];   // Row 1: 2 columns
a[2] = new int[5];   // Row 2: 5 columns

// 5. Jagged Array with initialization
int[][] a = {
    {1, 2, 3},
    {4, 5},
    {6, 7, 8, 9}
};
```

### Anonymous Arrays

```java
// Anonymous array — no reference variable
// Used when passing array to method directly

// Example 1:
public static void main(String[] args) {
    sum(new int[] {10, 20, 30});   // Anonymous array
}

public static void sum(int[] a) {
    int total = 0;
    for (int x : a) total += x;
    System.out.println(total);
}

// Example 2: Cannot be reused
new int[] {10, 20, 30};   // Created but immediately eligible for GC

// Example 3: 2D Anonymous array
new int[][] {{1,2}, {3,4,5}};
```

### Array Length and Default Values

```java
// 1. Array length
int[] a = new int[5];
System.out.println(a.length);   // 5 (not a method, it's a property)

// 2. Default values
int[] a = new int[5];       // {0, 0, 0, 0, 0}
String[] s = new String[5]; // {null, null, null, null, null}
boolean[] b = new boolean[5]; // {false, false, false, false, false}
double[] d = new double[5];  // {0.0, 0.0, 0.0, 0.0, 0.0}

// 3. Array of Objects
Student[] students = new Student[5];  // 5 null references
students[0] = new Student();          // Assign object
```

### Array Internals

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ARRAY MEMORY MODEL                                 │
│                                                                  │
│  int[] a = new int[3];  // a = {10, 20, 30}                      │
│                                                                  │
│  Stack                        Heap                                  │
│  ┌─────┐                     ┌─────────────────────────────┐       │
│  │  a  │ ───────ref────────→ │ Array Object               │       │
│  │ 100 │                     │ ┌─────┬─────┬─────┐        │       │
│  └─────┘                     │ │  0  │  1  │  2  │        │       │
│                              │ │ 10  │ 20  │ 30  │        │       │
│                              │ └─────┴─────┴─────┘        │       │
│                              │ length = 3                   │       │
│                              └─────────────────────────────┘       │
│                                                                  │
│  int[][] a = new int[2][3];                                        │
│                                                                  │
│  Stack                        Heap                                │
│  ┌─────┐                     ┌─────────┐                           │
│  │  a  │ ───────ref────────→│ [ref0]  │──ref──→ [0,0,0]          │
│  │ 200 │                     │ [ref1]  │──ref──→ [0,0,0]          │
│  └─────┘                     └─────────┘                           │
│                                                                  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. Types of Variables

### Variable Classification

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TYPES OF VARIABLES IN JAVA                         │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │ Based on Position:                                          │  │
│  │                                                             │  │
│  │  1. Instance Variables (Non-static)                         │  │
│  │     - Declared inside class, outside methods                 │  │
│  │     - Created when object is created                         │  │
│  │     - Destroyed when object is destroyed                     │  │
│  │     - Stored in heap memory                                  │  │
│  │                                                             │  │
│  │  2. Static Variables (Class Variables)                      │  │
│  │     - Declared with static keyword                           │  │
│  │     - Created when class is loaded                           │  │
│  │     - Destroyed when class is unloaded                       │  │
│  │     - Stored in method area (static pool)                      │  │
│  │     - Shared by all objects                                  │  │
│  │                                                             │  │
│  │  3. Local Variables                                         │  │
│  │     - Declared inside methods, blocks, constructors          │  │
│  │     - Created when method/block is entered                   │  │
│  │     - Destroyed when method/block is exited                  │  │
│  │     - Stored in stack memory                                 │  │
│  │     - Must be initialized before use                         │  │
│  │                                                             │  │
│  └─────────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │ Based on Data Type:                                         │  │
│  │                                                             │  │
│  │  1. Primitive Variables  → int, char, float, etc.           │  │
│  │  2. Reference Variables  → String, Student, Array, etc.   │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Instance Variables

```java
public class Student {
    // Instance Variables
    int id;           // default: 0
    String name;      // default: null
    
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        
        s1.id = 101;
        s2.id = 102;
        
        System.out.println(s1.id);   // 101
        System.out.println(s2.id);   // 102
        // Each object has its own copy
    }
}
```

### Static Variables

```java
public class Counter {
    static int count = 0;   // Static variable
    
    Counter() {
        count++;            // Increment shared counter
    }
    
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        
        System.out.println(c1.count);  // 3
        System.out.println(c2.count);  // 3
        System.out.println(c3.count);  // 3
        // All objects share same count
        System.out.println(Counter.count);  // 3 (best practice)
    }
}
```

### Local Variables

```java
public class Test {
    public static void main(String[] args) {
        int x;           // Local variable
        // System.out.println(x);  // ❌ ERROR: not initialized
        
        x = 10;          // Initialize before use
        System.out.println(x);   // ✅ 10
        
        if (true) {
            int y = 20;    // Local to if block
            System.out.println(y);  // ✅
        }
        // System.out.println(y);  // ❌ ERROR: out of scope
        
        for (int i = 0; i < 5; i++) {
            System.out.println(i);  // ✅
        }
        // System.out.println(i);  // ❌ ERROR: out of scope
    }
}
```

### Variable Comparison Table

```
┌─────────────────────────────────────────────────────────────────────┐
│                    VARIABLE COMPARISON TABLE                          │
│  ┌────────────────┬──────────────┬──────────────┬──────────────┐   │
│  │ Feature        │ Instance     │ Static       │ Local        │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Declaration    │ Inside class │ Inside class │ Inside method│   │
│  │                │ outside method│ outside method│ block       │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ static keyword │ No           │ Yes          │ No           │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Scope          │ Object level │ Class level  │ Method level │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Storage        │ Heap         │ Method area  │ Stack        │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Default value  │ Yes          │ Yes          │ No           │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Access modifiers│ Yes          │ Yes          │ No           │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Creation       │ Object creation│ Class loading│ Method entry │   │
│  ├────────────────┼──────────────┼──────────────┼──────────────┤   │
│  │ Destruction    │ Object GC    │ Class unload │ Method exit  │   │
│  └────────────────┴──────────────┴──────────────┴──────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 6. Var-arg Methods

### What is Var-arg?

Variable number of arguments (varargs) allows a method to accept any number of arguments of the same type.

```java
// Syntax: dataType... variableName
public static void sum(int... a) {
    int total = 0;
    for (int x : a) {
        total += x;
    }
    System.out.println("Sum: " + total);
}

// Usage
sum();           // Sum: 0
sum(10);         // Sum: 10
sum(10, 20);     // Sum: 30
sum(10, 20, 30); // Sum: 60
```

### Var-arg Rules

```java
// 1. Only ONE var-arg parameter per method
public void method(int... a, String... b) {}  // ❌ ERROR

// 2. Var-arg must be the LAST parameter
public void method(int... a, int b) {}        // ❌ ERROR
public void method(int b, int... a) {}        // ✅

// 3. Var-arg parameter is treated as an array internally
public void method(int... a) {
    System.out.println(a.length);  // a is int[]
    System.out.println(a[0]);    // array access
}

// 4. Var-arg vs Array — both are valid
public void method1(int[] a) {}    // Array parameter
public void method2(int... a) {}   // Var-arg parameter

// 5. Method overloading with var-arg
public void method(int a) {}           // Exact match preferred
public void method(int... a) {}       // Var-arg match

method(10);        // Calls method(int a) — exact match
method(10, 20);    // Calls method(int... a) — var-arg

// 6. Var-arg vs null
public void method(int... a) {
    System.out.println(a.length);  // If called with null: NullPointerException
}

method(null);      // ❌ NullPointerException
method();          // ✅ length = 0
method(10);        // ✅ length = 1
```

### Var-arg Method Resolution

```java
public class Test {
    public static void method(int a) {
        System.out.println("int");
    }
    
    public static void method(int... a) {
        System.out.println("int...");
    }
    
    public static void method(int[] a) {
        System.out.println("int[]");
    }
    
    public static void main(String[] args) {
        method(10);        // int — exact match
        method(10, 20);    // int... — var-arg
        // method(new int[]{10});  // int[] — exact match
    }
}
```

---

## 7. Main Method

### Main Method Signature

```java
public static void main(String[] args)
```

### Main Method Components

```
┌─────────────────────────────────────────────────────────────────────┐
│                    MAIN METHOD COMPONENTS                             │
│  ┌────────────────┬────────────────────────────────────────────┐   │
│  │ Component      │ Purpose                                    │   │
│  ├────────────────┼────────────────────────────────────────────┤   │
│  │ public         │ JVM can call from anywhere                   │   │
│  │ static         │ JVM can call without creating object         │   │
│  │ void           │ Main method doesn't return anything to JVM  │   │
│  │ main           │ Name configured in JVM                      │   │
│  │ String[] args  │ Command line arguments                      │   │
│  └────────────────┴────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Valid Main Method Variations

```java
// 1. Standard form
public static void main(String[] args) {}

// 2. Different parameter name
public static void main(String[] arguments) {}

// 3. Var-arg form (Java 5+)
public static void main(String... args) {}

// 4. Different array syntax
public static void main(String []args) {}
public static void main(String args[]) {}

// 5. Order of modifiers
static public void main(String[] args) {}  // ✅ (public and static can swap)

// 6. Final parameter
public static void main(final String[] args) {}  // ✅

// 7. Strictfp
public static strictfp void main(String[] args) {}  // ✅

// 8. Synchronized
public static synchronized void main(String[] args) {}  // ✅

// ❌ Invalid main methods
static void main(String[] args) {}           // ❌ Not public
public void main(String[] args) {}          // ❌ Not static
public static void main(int[] args) {}      // ❌ Wrong parameter type
public static int main(String[] args) {}   // ❌ Return type not void
void main(String[] args) {}                 // ❌ Missing public static
public static void Main(String[] args) {}    // ❌ Case sensitive — 'Main' not 'main'
```

### Overloading Main Method

```java
public class Test {
    // JVM always looks for: public static void main(String[] args)
    public static void main(String[] args) {
        System.out.println("String[] main");
        main(10);    // Call overloaded version
    }
    
    // Overloaded main — valid but not entry point
    public static void main(int a) {
        System.out.println("int main: " + a);
    }
    
    public static void main(String args) {
        System.out.println("String main: " + args);
    }
}

// Output:
// String[] main
// int main: 10
```

---

## 8. Command Line Arguments

### What are Command Line Arguments?

Arguments passed to the main method when running the program from the command line.

```java
public class Test {
    public static void main(String[] args) {
        // Print all arguments
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
    }
}
```

### Running with Command Line Arguments

```bash
# Command:
java Test Hello World 123

# Output:
# args[0] = Hello
# args[1] = World
# args[2] = 123

# All arguments are Strings
# To use as numbers:
java Test 10 20

// In code:
int a = Integer.parseInt(args[0]);  // 10
int b = Integer.parseInt(args[1]);  // 20
int sum = a + b;                    // 30
```

### Important Points

```java
// 1. No arguments: args.length = 0
public class Test {
    public static void main(String[] args) {
        System.out.println(args.length);  // 0
        // args[0];  // ArrayIndexOutOfBoundsException
    }
}

// 2. Space separated arguments
java Test "Hello World" 123
// args[0] = "Hello World"  (with quotes: treated as single argument)
// args[1] = 123

// 3. Without quotes: space is separator
java Test Hello World 123
// args[0] = Hello
// args[1] = World
// args[2] = 123

// 4. Common use case
public class Calculator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Calculator <num1> <num2>");
            return;
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println("Sum: " + (a + b));
    }
}
```

---

## 9. Java Coding Standards

### Naming Conventions

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JAVA CODING STANDARDS                            │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Element            │ Convention                             │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Classes            │ PascalCase: Student, EmployeeDetails    │  │
│  │ Interfaces         │ PascalCase: Runnable, Comparable        │  │
│  │ Methods            │ camelCase: getName(), calculateTotal() │  │
│  │ Variables          │ camelCase: firstName, totalAmount       │  │
│  │ Constants          │ UPPER_CASE: MAX_VALUE, PI             │  │
│  │ Packages           │ lowercase: com.company.project        │  │
│  │ Enums              │ PascalCase: Day, Status               │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
// Class name: PascalCase
public class StudentDetails {
    // Constants: UPPER_SNAKE_CASE
    public static final int MAX_STUDENTS = 100;
    public static final double PI = 3.14159;
    
    // Instance variables: camelCase
    private String firstName;
    private String lastName;
    private int age;
    
    // Methods: camelCase
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void calculateAverage() {
        // Method implementation
    }
}

// Package: lowercase
package com.company.studentmanagement;

// Interface: PascalCase
public interface StudentService {
    void saveStudent(Student student);
    Student getStudent(int id);
}

// Enum: PascalCase
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
```

### Coding Best Practices

```java
// 1. Meaningful names
int a;              // ❌ Bad
int age;            // ✅ Good
int studentAge;     // ✅ Better

// 2. Avoid single letter names except in loops
for (int i = 0; i < 10; i++) {}  // ✅ i is acceptable

// 3. One declaration per line
int a, b, c;        // ❌ Avoid
int a;              // ✅ Good
int b;
int c;

// 4. Initialize variables
int count;          // ❌ Not initialized
int count = 0;      // ✅ Initialized

// 5. Use final for constants
final int MAX = 100;  // ✅

// 6. Braces style
public class Example {  // ✅ Opening brace on same line
    public void method() {
        if (condition) {
            // code
        }
    }
}

// 7. Indentation: 4 spaces or tab
public class Example {
    public void method() {
        if (true) {
            System.out.println("Indented");
        }
    }
}
```

---

## 10. Interview Questions

### Q1. How many reserved words are there in Java?

```java
// Answer: 53
// 50 used keywords + 2 unused (goto, const) + 3 literals (true, false, null)

// true, false, null are reserved words but NOT keywords
// They are literals (values)

// Keywords: 50
// Reserved Literals: 3 (true, false, null)
// Total Reserved Words: 53
```

### Q2. Can we use `true`, `false`, `null` as identifiers?

```java
// ❌ NO — they are reserved words
int true = 10;      // ❌ ERROR
boolean false = true;  // ❌ ERROR
String null = "test";  // ❌ ERROR

// ✅ But we can use them as method names (Java allows)
public void true() {}    // ✅ Valid method name
public void false() {}   // ✅ Valid method name
public void null() {}    // ✅ Valid method name

// ✅ As variable names: NO
// int true = 10;        // ❌ ERROR
```

### Q3. What is the difference between `int[] a` and `int a[]`?

```java
// Both are valid in Java
int[] a;    // ✅ Type is int[] — recommended (Java style)
int a[];    // ✅ Type is int — C-style (allowed for compatibility)

// Important difference in multiple declarations:
int[] a, b, c;   // All three are int[]
int a[], b, c;    // a is int[], b and c are int

// Recommendation: Always use int[] a for clarity
```

### Q4. What is the default value of a local variable?

```java
public class Test {
    public static void main(String[] args) {
        int x;
        // System.out.println(x);  // ❌ ERROR: variable x might not have been initialized
        
        // Local variables are NOT initialized automatically
        // Instance and static variables are initialized to default values
    }
}

// Instance/Static default values:
// int → 0
// double → 0.0
// boolean → false
// String → null
// Object → null
```

### Q5. Can we change the size of an array after creation?

```java
// ❌ NO — Arrays are fixed size
int[] a = new int[5];
// a.length = 10;  // ❌ ERROR: length is final

// ✅ Solution: Create new array and copy
int[] a = new int[5];
int[] b = new int[10];
System.arraycopy(a, 0, b, 0, a.length);
a = b;  // Now a points to larger array

// Or use ArrayList for dynamic size
ArrayList<Integer> list = new ArrayList<>();
list.add(10);    // ✅ Dynamic size
```

### Q6. What is the difference between `length` and `length()`?

```java
// length → property of array (not a method)
int[] a = new int[5];
System.out.println(a.length);    // ✅ 5

// length() → method of String
String s = "Hello";
System.out.println(s.length());  // ✅ 5

// size() → method of Collection
ArrayList<Integer> list = new ArrayList<>();
System.out.println(list.size());  // ✅ 0
```

### Q7. Can we declare multiple main methods in a class?

```java
// ✅ YES — overloading is allowed
// But JVM only calls: public static void main(String[] args)

public class Test {
    public static void main(String[] args) {
        System.out.println("String[] main");
        main(10);
    }
    
    public static void main(int a) {
        System.out.println("int main: " + a);
    }
}

// Output: String[] main
//         int main: 10
```

### Q8. Why is main method `public static void`?

```java
// public → JVM can access from anywhere
// static → JVM can call without creating object
// void → JVM doesn't expect any return value

// If main is not public:
// JVM cannot access it → Error: Main method not found

// If main is not static:
// JVM needs to create object to call it
// But constructor might have parameters → JVM doesn't know

// If main returns int:
// JVM doesn't know what to do with return value
```

### Q9. What happens if we call `main()` method without arguments?

```java
public class Test {
    public static void main(String[] args) {
        // main();  // ❌ ERROR: no suitable method found
    }
}

// main() method with String[] is required
// Without arguments, we need to call an overloaded version
```

### Q10. Explain `var-arg` vs `array` parameter.

```java
// Both are valid but var-arg is more flexible

// Array parameter: must pass array
public void method1(int[] a) {
    // a is int[]
}

// Var-arg parameter: can pass 0, 1, or many values
public void method2(int... a) {
    // a is int[] internally
}

// Calling:
method1(new int[] {10, 20});    // ✅ Only way
method2(10, 20, 30);             // ✅ Flexible
method2(new int[] {10, 20});     // ✅ Also works
```

---

## 11. Quick Reference

### Data Type Ranges

```
┌─────────┬─────────────┬─────────────────────────────────────────┐
│ Type    │ Size        │ Range                                   │
├─────────┼─────────────┼─────────────────────────────────────────┤
│ byte    │ 1 byte      │ -128 to 127                             │
│ short   │ 2 bytes     │ -32,768 to 32,767                       │
│ int     │ 4 bytes     │ -2,147,483,648 to 2,147,483,647         │
│ long    │ 8 bytes     │ -9,223,372,036,854,775,808 to ...       │
│ float   │ 4 bytes     │ ±3.40282347E+38                         │
│ double  │ 8 bytes     │ ±1.79769313486231570E+308               │
│ char    │ 2 bytes     │ 0 to 65,535 (Unicode)                   │
│ boolean │ 1 bit       │ true or false                           │
└─────────┴─────────────┴─────────────────────────────────────────┘
```

### Default Values

```
┌─────────────┬─────────────┐
│ Data Type   │ Default     │
├─────────────┼─────────────┤
│ byte        │ 0           │
│ short       │ 0           │
│ int         │ 0           │
│ long        │ 0L          │
│ float       │ 0.0f        │
│ double      │ 0.0d        │
│ char        │ '\u0000'    │
│ boolean     │ false       │
│ String      │ null        │
│ Object      │ null        │
└─────────────┴─────────────┘
```

### Main Method Variations

```
✅ Valid:
public static void main(String[] args)
public static void main(String[] arguments)
public static void main(String... args)
static public void main(String[] args)
public static void main(String []args)
public static void main(String args[])

❌ Invalid:
static void main(String[] args)           // Not public
public void main(String[] args)           // Not static
public static int main(String[] args)     // Not void
public static void main(int[] args)       // Wrong type
void main(String[] args)                  // Missing public static
public static void Main(String[] args)    // Wrong case
```

---

## 12. Key Takeaways

1. **53 reserved words** in Java: 50 keywords + 2 unused (goto, const) + 3 literals (true, false, null).
2. **true/false/null are NOT keywords** — they are reserved literals.
3. **8 primitive data types**: byte, short, int, long, float, double, char, boolean.
4. **char is 2 bytes** in Java (Unicode) vs 1 byte in C/C++ (ASCII).
5. **boolean is NOT numeric** — cannot use 0/1, cannot do arithmetic.
6. **Arrays are fixed size** — cannot resize after creation.
7. **Arrays are objects** — stored in heap, have `.length` property.
8. **3 types of variables**: Local (stack), Instance (heap), Static (method area).
9. **Local variables must be initialized** before use — no default values.
10. **Instance and static variables** get default values automatically.
11. **Var-arg** is syntactic sugar for array — treated as array internally.
12. **Only one var-arg parameter** per method, and it must be last.
13. **Main method**: `public static void main(String[] args)` — JVM entry point.
14. **Main method can be overloaded** — but JVM only calls String[] version.
15. **Command line arguments** are always String — use `Integer.parseInt()` for numbers.
16. **Coding standards**: PascalCase for classes, camelCase for methods/variables, UPPER_CASE for constants.
17. **Package names**: all lowercase, reverse domain: `com.company.project`.
18. **Anonymous arrays**: created without reference — used for one-time method calls.
19. **Jagged arrays**: 2D arrays where each row has different number of columns.
20. **No unsigned types** in Java — all numeric types are signed.

---

**Happy coding! 🚀**

*Language fundamentals are the foundation of Java mastery.*
