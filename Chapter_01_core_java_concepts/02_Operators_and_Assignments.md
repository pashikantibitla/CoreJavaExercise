# 02 — Operators and Assignments

> **Videos:** 17–23  
> **Topics:** Increment/Decrement, Arithmetic, Concatenation, Relational, Equality, instanceof, Bitwise, Short-circuit, Type Cast, Assignment, Conditional, new, Precedence, FAQs

---

## Table of Contents

1. [Increment and Decrement Operators](#1-increment-and-decrement-operators)
2. [Arithmetic Operators](#2-arithmetic-operators)
3. [Concatenation Operator](#3-concatenation-operator)
4. [Relational and Equality Operators](#4-relational-and-equality-operators)
5. [instanceof Operator](#5-instanceof-operator)
6. [Bitwise Operators](#6-bitwise-operators)
7. [Short-circuit Operators](#7-short-circuit-operators)
8. [Type Cast Operators](#8-type-cast-operators)
9. [Assignment, Conditional, and new Operators](#9-assignment-conditional-and-new-operators)
10. [Operator and Operand Precedence](#10-operator-and-operand-precedence)
11. [Interview FAQs](#11-interview-faqs)
12. [Quick Reference](#12-quick-reference)
13. [Key Takeaways](#13-key-takeaways)

---

## 1. Increment and Decrement Operators

### Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    INCREMENT/DECREMENT OPERATORS                      │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Operator           │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ ++x (pre-increment)│ Increment first, then use               │  │
│  │ x++ (post-increment)│ Use first, then increment               │  │
│  │ --x (pre-decrement)│ Decrement first, then use               │  │
│  │ x-- (post-decrement)│ Use first, then decrement               │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Pre vs Post Increment

```java
// Pre-increment (++x): Increment first, then use
int x = 10;
int y = ++x;    // x becomes 11, then y = 11
System.out.println(x);  // 11
System.out.println(y);  // 11

// Post-increment (x++): Use first, then increment
int a = 10;
int b = a++;    // b = 10, then a becomes 11
System.out.println(a);  // 11
System.out.println(b);  // 10

// Pre-decrement (--x): Decrement first, then use
int m = 10;
int n = --m;    // m becomes 9, then n = 9
System.out.println(m);  // 9
System.out.println(n);  // 9

// Post-decrement (x--): Use first, then decrement
int p = 10;
int q = p--;    // q = 10, then p becomes 9
System.out.println(p);  // 9
System.out.println(q);  // 10
```

### Complex Examples

```java
// Example 1: Multiple increments
int x = 10;
System.out.println(x++);   // 10 (prints 10, x becomes 11)
System.out.println(++x);   // 12 (x becomes 12, prints 12)
System.out.println(x--);   // 12 (prints 12, x becomes 11)
System.out.println(--x);   // 10 (x becomes 10, prints 10)

// Example 2: Expression with multiple increments
int a = 10;
int b = 10;
System.out.println(a++ + ++a);  // 10 + 12 = 22
// a++ → a=10, then a becomes 11
// ++a → a becomes 12, then 12
// Result: 10 + 12 = 22

// Example 3: Final variable
final int x = 10;
// x++;  // ❌ ERROR: cannot assign a value to final variable x

// Example 4: Cannot apply to boolean
boolean flag = true;
// flag++;  // ❌ ERROR: bad operand type boolean for unary operator '++'

// Example 5: Nested
int x = 10;
// int y = x++++;  // ❌ ERROR: unexpected token ++ (cannot chain)
// int y = ++x++;  // ❌ ERROR: unexpected token ++

// Example 6: Applied to byte, short, char
byte b = 10;
b++;    // ✅ b becomes 11 (byte)
// b = b + 1;  // ❌ ERROR: int cannot be assigned to byte
// But b++ is fine because internal casting is done

char c = 'a';
c++;    // ✅ c becomes 'b'
System.out.println(c);  // b
```

---

## 2. Arithmetic Operators

### Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ARITHMETIC OPERATORS                             │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Operator           │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ +                  │ Addition                                 │  │
│  │ -                  │ Subtraction                              │  │
│  │ *                  │ Multiplication                           │  │
│  │ /                  │ Division                                 │  │
│  │ %                  │ Modulo (Remainder)                       │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
// Basic operations
int a = 10, b = 3;
System.out.println(a + b);  // 13
System.out.println(a - b);  // 7
System.out.println(a * b);  // 30
System.out.println(a / b);  // 3 (integer division)
System.out.println(a % b);  // 1

// Division by zero
System.out.println(10 / 0);     // ❌ ArithmeticException: / by zero
System.out.println(10.0 / 0);     // ✅ Infinity
System.out.println(10.0 / 0.0);   // ✅ Infinity
System.out.println(-10.0 / 0);    // ✅ -Infinity
System.out.println(0.0 / 0.0);    // ✅ NaN

// Infinity and NaN
System.out.println(10 / 0.0);     // Infinity
System.out.println(-10 / 0.0);    // -Infinity

// NaN comparisons
System.out.println(Double.NaN == Double.NaN);  // false
System.out.println(Double.NaN != Double.NaN);  // true

// Modulo with negative numbers
System.out.println(10 % 3);    // 1
System.out.println(-10 % 3);   // -1
System.out.println(10 % -3);   // 1
System.out.println(-10 % -3);  // -1

// Modulo with floating point
System.out.println(10.5 % 3.2);  // 3.7
System.out.println(10.5 % 0);    // ❌ ArithmeticException
System.out.println(10.5 % 0.0);  // NaN
```

---

## 3. Concatenation Operator

### String Concatenation

```java
// + operator is overloaded for string concatenation

// 1. String + primitive
String s = "Hello" + 10;    // "Hello10"
String s2 = 10 + "Hello";   // "10Hello"

// 2. Left to right evaluation
System.out.println("a" + "b" + "c");     // "abc"
System.out.println("a" + 10 + 20);       // "a1020" (String + int + int)
System.out.println(10 + 20 + "a");       // "30a" (int + int + String)
System.out.println("a" + (10 + 20));     // "a30" (parentheses first)

// 3. char + int = int
System.out.println('a' + 'b');           // 195 (97 + 98)
System.out.println('a' + 10);            // 107
System.out.println('a' + "b");           // "ab"

// 4. null concatenation
String s = null + "Hello";   // "nullHello"
String s2 = "Hello" + null;  // "Hellonull"

// 5. Concatenation with objects
System.out.println("Hello" + new Student());  // "Hello" + toString()
```

---

## 4. Relational and Equality Operators

### Relational Operators

```
┌─────────────────────────────────────────────────────────────────────┐
│                    RELATIONAL OPERATORS                               │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Operator           │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ <                  │ Less than                                │  │
│  │ <=                 │ Less than or equal to                    │  │
│  │ >                  │ Greater than                             │  │
│  │ >=                 │ Greater than or equal to                 │  │
│  │ ==                 │ Equal to (reference or value)            │  │
│  │ !=                 │ Not equal to                             │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
// 1. Basic comparison
int a = 10, b = 20;
System.out.println(a < b);   // true
System.out.println(a > b);   // false
System.out.println(a <= b);  // true
System.out.println(a >= b);  // false

// 2. Equality with primitives
System.out.println(a == b);  // false
System.out.println(a != b);  // true

// 3. Cannot compare incompatible types
System.out.println(true > false);  // ❌ ERROR: bad operand types
System.out.println("a" > "b");      // ❌ ERROR: bad operand types
// Relational operators work only on numeric types

// 4. == vs .equals()
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2);      // false (reference comparison)
System.out.println(s1.equals(s2));  // true (content comparison)

// 5. == with null
String s3 = null;
System.out.println(s3 == null);    // true
System.out.println(null == null);    // true

// 6. Comparing char
System.out.println('a' > 'b');     // false (97 > 98)
System.out.println('a' > 50);      // true (97 > 50)
```

---

## 5. instanceof Operator

### What is instanceof?

```java
// instanceof checks if an object is of a specific type
// Returns true or false

// Syntax: objectReference instanceof Type

// Example 1:
String s = "Hello";
System.out.println(s instanceof String);       // true
System.out.println(s instanceof Object);         // true
// System.out.println(s instanceof Integer);     // ❌ ERROR: incompatible types

// Example 2: With null
System.out.println(null instanceof String);      // false (always)
System.out.println(null instanceof Object);      // false (always)

// Example 3: With inheritance
class Animal {}
class Dog extends Animal {}

Animal a = new Dog();
System.out.println(a instanceof Dog);    // true
System.out.println(a instanceof Animal); // true
System.out.println(a instanceof Object);   // true

// Example 4: Interface
Runnable r = new Thread();
System.out.println(r instanceof Runnable);  // true
System.out.println(r instanceof Thread);    // true

// Example 5: Compilation error
String s = "Hello";
// System.out.println(s instanceof Integer);  // ❌ ERROR: incompatible types
// System.out.println(s instanceof int);      // ❌ ERROR: unexpected type

// Example 6: With null reference
// null instanceof AnyClass → always false, no compile error
Object obj = null;
System.out.println(obj instanceof String);  // false
```

---

## 6. Bitwise Operators

### Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    BITWISE OPERATORS                                  │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Operator           │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ &                  │ AND (bitwise)                            │  │
│  │ |                  │ OR (bitwise)                             │  │
│  │ ^                  │ XOR (bitwise)                            │  │
│  │ ~                  │ NOT (complement)                         │  │
│  │ <<                 │ Left shift                               │  │
│  │ >>                 │ Right shift (signed)                     │  │
│  │ >>>                │ Right shift (unsigned)                   │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Bitwise Operations

```java
// Bitwise AND (&)
// 1 & 1 = 1, 1 & 0 = 0, 0 & 1 = 0, 0 & 0 = 0
System.out.println(4 & 5);   // 4
// 4 = 100
// 5 = 101
// & = 100 = 4

// Bitwise OR (|)
// 1 | 1 = 1, 1 | 0 = 1, 0 | 1 = 1, 0 | 0 = 0
System.out.println(4 | 5);   // 5
// 4 = 100
// 5 = 101
// | = 101 = 5

// Bitwise XOR (^)
// 1 ^ 1 = 0, 1 ^ 0 = 1, 0 ^ 1 = 1, 0 ^ 0 = 0
System.out.println(4 ^ 5);   // 1
// 4 = 100
// 5 = 101
// ^ = 001 = 1

// Bitwise NOT (~)
// ~x = -(x + 1)
System.out.println(~4);      // -5
// 4 = 0000...0100
// ~4 = 1111...1011 = -5 (two's complement)

// Left Shift (<<)
// x << n = x * 2^n
System.out.println(4 << 2);  // 16 (4 * 4)
System.out.println(4 << 3);  // 32 (4 * 8)

// Right Shift (>>)
// x >> n = x / 2^n
System.out.println(16 >> 2);  // 4 (16 / 4)
System.out.println(-16 >> 2); // -4 (preserves sign)

// Unsigned Right Shift (>>>)
// Always fills with 0
System.out.println(-16 >>> 2);  // 1073741820 (fills with 0)

// Bitwise with boolean (logical AND/OR)
System.out.println(true & false);  // false
System.out.println(true | false);  // true
System.out.println(true ^ false);  // true
System.out.println(true ^ true);   // false
// ~true;  // ❌ ERROR: bad operand type boolean for unary ~
```

---

## 7. Short-circuit Operators

### Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SHORT-CIRCUIT OPERATORS                            │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Operator           │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ && (AND)           │ If first is false, second is NOT evaluated│  │
│  │ || (OR)            │ If first is true, second is NOT evaluated │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Short-circuit vs Non-short-circuit

```java
// Non-short-circuit: & and | (always evaluate both sides)
// Short-circuit: && and || (evaluate only if necessary)

// Example 1: && (AND)
int x = 10;
if (x < 5 && ++x > 10) {
    // First condition false, so ++x is NOT executed
}
System.out.println(x);  // 10 (not incremented)

// Example 2: & (bitwise AND)
int y = 10;
if (y < 5 & ++y > 10) {
    // Both conditions evaluated regardless
}
System.out.println(y);  // 11 (incremented)

// Example 3: || (OR)
int a = 10;
if (a > 5 || ++a > 10) {
    // First condition true, so ++a is NOT executed
}
System.out.println(a);  // 10 (not incremented)

// Example 4: | (bitwise OR)
int b = 10;
if (b > 5 | ++b > 10) {
    // Both conditions evaluated regardless
}
System.out.println(b);  // 11 (incremented)

// Example 5: Division by zero prevention
int x = 0;
if (x != 0 && 10/x > 5) {
    // Safe — second condition not evaluated
}

// if (x != 0 & 10/x > 5) {  // ❌ ArithmeticException

// Example 6: Method call prevention
public boolean isValid() {
    System.out.println("isValid called");
    return true;
}

if (false && isValid()) {  // isValid() NOT called
}

if (false & isValid()) {   // isValid() IS called
}
```

---

## 8. Type Cast Operators

### Types of Casting

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TYPE CASTING IN JAVA                               │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Casting Type       │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Implicit (Widening)│ Smaller → Larger type (automatic)       │  │
│  │ Explicit (Narrowing)│ Larger → Smaller type (manual)           │  │
│  │ Reference Casting   │ Up/Down casting (requires IS-A relation) │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Implicit Casting (Widening)

```java
// Automatic — no explicit cast needed
// byte → short → int → long → float → double
// char → int → long → float → double

byte b = 10;
short s = b;        // ✅ byte → short
int i = s;          // ✅ short → int
long l = i;         // ✅ int → long
float f = l;        // ✅ long → float
double d = f;       // ✅ float → double

char c = 'a';
int x = c;          // ✅ char → int (ASCII value)
```

### Explicit Casting (Narrowing)

```java
// Manual — explicit cast required
// double → float → long → int → short → byte

double d = 10.5;
float f = (float) d;    // ✅
long l = (long) f;      // ✅
int i = (int) l;        // ✅
short s = (short) i;    // ✅
byte b = (byte) s;      // ✅

// Data loss in narrowing
int x = 130;
byte y = (byte) x;      // ✅ -126 (130 - 256 = -126)
System.out.println(y);  // -126

// Truncation
double d = 10.99;
int i = (int) d;        // ✅ 10 (decimal part truncated)
System.out.println(i);  // 10

// Overflow
int big = 100000;
byte small = (byte) big;  // ✅ 96 (data loss)
```

### Reference Type Casting

```java
// Up-casting (implicit)
class Animal {}
class Dog extends Animal {}

Dog d = new Dog();
Animal a = d;          // ✅ Implicit up-casting

// Down-casting (explicit)
Animal a = new Dog();
Dog d = (Dog) a;       // ✅ Valid down-casting

// Invalid down-casting
Animal a = new Animal();
// Dog d = (Dog) a;    // ❌ ClassCastException at runtime

// instanceof check before casting
Animal a = getAnimal();
if (a instanceof Dog) {
    Dog d = (Dog) a;   // ✅ Safe
}
```

---

## 9. Assignment, Conditional, and new Operators

### Assignment Operators

```java
// Simple assignment
int a = 10;

// Compound assignment
a += 5;     // a = a + 5;  // 15
a -= 5;     // a = a - 5;  // 10
a *= 2;     // a = a * 2;  // 20
a /= 2;     // a = a / 2;  // 10
a %= 3;     // a = a % 3;  // 1

// Bitwise compound
a &= 5;     // a = a & 5;
a |= 5;     // a = a | 5;
a ^= 5;     // a = a ^ 5;
a <<= 2;    // a = a << 2;
a >>= 2;    // a = a >> 2;
a >>>= 2;   // a = a >>> 2;

// Chained assignment
int a, b, c;
a = b = c = 10;     // ✅ Right-to-left: c=10, b=c, a=b

// Assignment with expressions
int x = 10;
x += 5 * 2;    // x = x + (5 * 2) = 20

// Type casting in compound assignment
byte b = 10;
// b = b + 1;   // ❌ ERROR: int cannot be assigned to byte
b += 1;         // ✅ Implicit cast: b = (byte)(b + 1);
```

### Conditional (Ternary) Operator

```java
// Syntax: condition ? value_if_true : value_if_false

// Example 1:
int a = 10, b = 20;
int max = (a > b) ? a : b;    // max = 20

// Example 2: Nested ternary
int grade = 85;
String result = (grade >= 90) ? "A" : (grade >= 80) ? "B" : "C";
// result = "B"

// Example 3: Different types
Object obj = (true) ? new Integer(10) : new Double(10.0);
// Result is Double (10.0) — type promotion to common type

// Example 4: Null handling
String s = null;
String result = (s != null) ? s : "default";
// result = "default"

// Example 5: With method calls
int result = (isValid()) ? getValue() : getDefault();

// Example 6: Cannot have void methods
// (true) ? System.out.println("A") : System.out.println("B");
// ❌ ERROR: void cannot be converted to boolean
```

### new Operator

```java
// new creates objects

// 1. Object creation
Student s = new Student();          // Default constructor
Student s2 = new Student(10);       // Parameterized constructor

// 2. Array creation
int[] arr = new int[5];             // Array of 5 ints
int[][] arr2 = new int[3][4];       // 2D array

// 3. Anonymous object
new Student().display();            // Created, used, eligible for GC

// 4. Anonymous class
Runnable r = new Runnable() {
    public void run() {}
};

// 5. new vs newInstance()
Student s = new Student();           // Compile-time type checking
Student s2 = (Student) Class.forName("Student").newInstance();  // Runtime
```

---

## 10. Operator and Operand Precedence

### Precedence Table

```
┌─────────────────────────────────────────────────────────────────────┐
│                    OPERATOR PRECEDENCE (High to Low)                │
│  ┌──────┬─────────────────────────────────────────────────────┐   │
│  │ Priority │ Operators                                      │   │
│  ├──────┼─────────────────────────────────────────────────────┤   │
│  │ 1    │ () [] .                                          │   │
│  │ 2    │ ++ -- ~ ! (unary)                                │   │
│  │ 3    │ new (type)                                       │   │
│  │ 4    │ * / %                                            │   │
│  │ 5    │ + -                                              │   │
│  │ 6    │ << >> >>>                                        │   │
│  │ 7    │ < <= > >= instanceof                           │   │
│  │ 8    │ == !=                                            │   │
│  │ 9    │ &                                                │   │
│  │ 10   │ ^                                                │   │
│  │ 11   │ |                                                │   │
│  │ 12   │ &&                                               │   │
│  │ 13   │ ||                                               │   │
│  │ 14   │ ?: (ternary)                                     │   │
│  │ 15   │ = += -= *= /= %= &= ^= |= <<= >>= >>>=          │   │
│  └──────┴─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Precedence Examples

```java
// Example 1: * before +
int result = 10 + 5 * 2;    // 10 + 10 = 20 (not 30)

// Example 2: Parentheses override
int result = (10 + 5) * 2;  // 15 * 2 = 30

// Example 3: Postfix before unary
int x = 10;
int y = x++ + ++x;  // 10 + 12 = 22

// Example 4: && before ||
boolean result = true || false && false;  // true || false = true

// Example 5: Assignment right-to-left
int a, b, c;
a = b = c = 10;  // c=10, b=10, a=10

// Example 6: Complex expression
int a = 10, b = 5, c = 2;
int result = a + b * c - a / b + c;
// 10 + 10 - 2 + 2 = 20

// Example 7: Associativity
int x = 10 - 5 - 2;   // (10 - 5) - 2 = 3 (left to right)
int y = 10 / 5 * 2;   // (10 / 5) * 2 = 4 (left to right)
int z = 2 << 3 >> 2;  // (2 << 3) >> 2 = 2 (left to right)

// Example 8: Right associative
int a = b = c = 10;   // Right to left
int x = 2 * 3 / 4;    // (2 * 3) / 4 = 1 (left to right)
```

---

## 11. Interview FAQs

### Q1. What is the output of the following code?

```java
int a = 10;
int b = ++a + a++ + a++;
System.out.println(a);  // 13
System.out.println(b);  // 34

// Explanation:
// ++a: a becomes 11, value = 11
// a++: value = 11, a becomes 12
// a++: value = 12, a becomes 13
// b = 11 + 11 + 12 = 34
```

### Q2. What is the difference between `==` and `.equals()`?

```java
// == compares references (memory addresses)
// .equals() compares content (can be overridden)

String s1 = new String("Hello");
String s2 = new String("Hello");

System.out.println(s1 == s2);      // false (different objects)
System.out.println(s1.equals(s2));  // true (same content)

// String literal pool
String s3 = "Hello";
String s4 = "Hello";
System.out.println(s3 == s4);      // true (same reference from pool)
System.out.println(s3.equals(s4)); // true (same content)

// Object class
Object obj1 = new Object();
Object obj2 = new Object();
System.out.println(obj1 == obj2);      // false
System.out.println(obj1.equals(obj2));  // false (Object class uses ==)
```

### Q3. What is `>>>` vs `>>`?

```java
// >>  : Signed right shift (fills with sign bit)
// >>> : Unsigned right shift (fills with 0)

int x = -8;      // 11111111 11111111 11111111 11111000
System.out.println(x >> 2);   // -2 (fills with 1)
System.out.println(x >>> 2);  // 1073741822 (fills with 0)

// Positive numbers: same result
int y = 8;
System.out.println(y >> 2);   // 2
System.out.println(y >>> 2);  // 2
```

### Q4. What is the output of `10/0` and `10.0/0`?

```java
System.out.println(10 / 0);      // ❌ ArithmeticException: / by zero
System.out.println(10.0 / 0);    // Infinity
System.out.println(-10.0 / 0);   // -Infinity
System.out.println(0.0 / 0);     // NaN
System.out.println(10 / 0.0);    // Infinity

// Why? Integer division by zero is undefined → Exception
// Floating point division by zero is defined in IEEE 754 → Infinity/NaN
```

### Q5. Can we apply `++` to `byte` and `char`?

```java
byte b = 10;
b++;    // ✅ Works — implicit type casting

char c = 'a';
c++;    // ✅ Works — c becomes 'b'

// But explicit arithmetic may fail:
// b = b + 1;   // ❌ ERROR: int cannot be assigned to byte
// Must use: b = (byte)(b + 1);
```

### Q6. What is the difference between `&` and `&&`?

```java
// & : Bitwise AND / Logical AND (always evaluates both sides)
// && : Short-circuit AND (evaluates second only if first is true)

// Performance:
if (x != 0 && 10/x > 5) {  // Safe
}
if (x != 0 & 10/x > 5) {    // ❌ ArithmeticException if x=0
}

// Method call:
if (false && expensiveMethod()) {  // Method NOT called
}
if (false & expensiveMethod()) {   // Method IS called
}
```

### Q7. What is the output of `System.out.println(1 + 2 + "3" + 4 + 5)`?

```java
System.out.println(1 + 2 + "3" + 4 + 5);
// 1 + 2 = 3 (int)
// 3 + "3" = "33" (String)
// "33" + 4 = "334" (String)
// "334" + 5 = "3345" (String)
// Output: 3345

System.out.println("1" + 2 + 3 + 4 + 5);
// "1" + 2 = "12"
// "12" + 3 = "123"
// ... Output: 12345

System.out.println(1 + 2 + 3 + 4 + "5");
// 1 + 2 + 3 + 4 = 10
// 10 + "5" = "105"
// Output: 105
```

### Q8. What is the output of `System.out.println('a' + 'b')`?

```java
System.out.println('a' + 'b');   // 195
// 'a' = 97, 'b' = 98
// 97 + 98 = 195

System.out.println('a' + 1);     // 98
System.out.println('a' + "b");   // "ab"
System.out.println("a" + 'b');   // "ab"
System.out.println("a" + "b");   // "ab"
```

### Q9. What is the output of `System.out.println(10 < 20 < 30)`?

```java
System.out.println(10 < 20 < 30);
// ❌ ERROR: bad operand types for binary operator '<'
// 10 < 20 → true (boolean)
// true < 30 → ❌ boolean cannot be compared with int

// Correct way:
System.out.println(10 < 20 && 20 < 30);  // true
```

### Q10. What is the output of `System.out.println(10 == 10 == 10)`?

```java
System.out.println(10 == 10 == 10);
// ❌ ERROR: bad operand types for binary operator '=='
// 10 == 10 → true
// true == 10 → ❌ boolean cannot be compared with int

// Correct way:
System.out.println(10 == 10 && 10 == 10);  // true
```

### Q11. What is the output of the following?

```java
int a = 10;
a += a++ + ++a;
System.out.println(a);
// a += a++ + ++a
// a = a + (a++ + ++a)
// a = 10 + (10 + 12)
// a = 10 + 22 = 32

// But wait: a++ uses old value (10), then a becomes 11
// ++a increments a to 12, then uses 12
// a++ + ++a = 10 + 12 = 22
// a = 10 + 22 = 32
// Output: 32
```

### Q12. What is the output of `System.out.println(~10)`?

```java
System.out.println(~10);   // -11
// ~x = -(x + 1)
// ~10 = -(10 + 1) = -11

System.out.println(~-10);  // 9
// ~-10 = -(-10 + 1) = 9

System.out.println(~0);    // -1
System.out.println(~1);    // -2
```

---

## 12. Quick Reference

### All Java Operators

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ALL JAVA OPERATORS (44 TYPES)                      │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Category           │ Operators                                │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Unary (6)          │ ++, --, +, -, ~, !                      │  │
│  │ Arithmetic (5)     │ +, -, *, /, %                           │  │
│  │ Shift (3)          │ <<, >>, >>>                             │  │
│  │ Relational (6)     │ <, <=, >, >=, instanceof, ==, !=        │  │
│  │ Bitwise (4)        │ &, |, ^, ~                              │  │
│  │ Logical (2)        │ &&, ||                                  │  │
│  │ Ternary (1)        │ ?:                                      │  │
│  │ Assignment (12)    │ =, +=, -=, *=, /=, %=, &=, |=, ^=,      │  │
│  │                    │ <<=, >>=, >>>=                          │  │
│  │ new (1)            │ new                                     │  │
│  │ [] (1)             │ [] (array access)                       │  │
│  │ . (1)              │ . (member access)                       │  │
│  │ () (1)             │ () (method call, expression)            │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Operator Precedence Summary

```
Priority 1:  () [] . (dot)
Priority 2:  ++ -- ~ ! (unary)
Priority 3:  new (type)
Priority 4:  * / %
Priority 5:  + -
Priority 6:  << >> >>>
Priority 7:  < <= > >= instanceof
Priority 8:  == !=
Priority 9:  &
Priority 10: ^
Priority 11: |
Priority 12: &&
Priority 13: ||
Priority 14: ?:
Priority 15: = += -= *= /= %= &= ^= |= <<= >>= >>>=
```

---

## 13. Key Takeaways

1. **Pre-increment (++x)** → increment first, then use.
2. **Post-increment (x++)** → use first, then increment.
3. **Cannot apply ++/-- to boolean** or final variables.
4. **Integer division by zero** → ArithmeticException.
5. **Floating division by zero** → Infinity or NaN (IEEE 754).
6. **NaN == NaN** → false (always).
7. **+ operator** is overloaded for both addition and string concatenation.
8. **'a' + 'b'** → 195 (char promoted to int).
9. **"a" + "b"** → "ab" (string concatenation).
10. **instanceof** → returns false for null, compilation error for incompatible types.
11. **& and |** → always evaluate both sides (bitwise + logical).
12. **&& and ||** → short-circuit evaluation (only evaluate if needed).
13. **Widening** → automatic (byte → short → int → long → float → double).
14. **Narrowing** → explicit cast required, may lose data.
15. **~x** → bitwise complement: -(x + 1).
16. **x << n** → x * 2^n.
17. **x >> n** → x / 2^n (preserves sign).
18. **x >>> n** → unsigned right shift (fills with 0).
19. **Ternary operator** → only operator that takes 3 operands.
20. **new operator** → creates objects and arrays in heap.
21. **Compound assignment** → implicit cast: `b += 1` is `b = (byte)(b + 1)`.
22. **Parentheses** → override precedence and improve readability.

---

**Happy coding! 🚀**

*Operators are the building blocks of expressions — master them to master Java logic.*
