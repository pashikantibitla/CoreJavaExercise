# 23 — java.lang Package

> **Topics:** Object class, String overview, StringBuffer/StringBuilder, Wrapper classes, Auto-boxing/unboxing, Math, Runtime, System, Thread, Interview FAQs

---

## Table of Contents

1. [Introduction to java.lang Package](#1-introduction-to-java-lang-package)
2. [Object Class](#2-object-class)
3. [String Class Overview](#3-string-class-overview)
4. [StringBuffer and StringBuilder](#4-stringbuffer-and-stringbuilder)
5. [Wrapper Classes](#5-wrapper-classes)
6. [Auto-boxing and Auto-unboxing](#6-auto-boxing-and-auto-unboxing)
7. [Math Class](#7-math-class)
8. [Runtime Class](#8-runtime-class)
9. [System Class](#9-system-class)
10. [Thread Class](#10-thread-class)
11. [Interview FAQs](#11-interview-faqs)
12. [Quick Reference](#12-quick-reference)
13. [Key Takeaways](#13-key-takeaways)

---

## 1. Introduction to java.lang Package

### Overview

- `java.lang` is the **most fundamental package** in Java.
- **Automatically imported** — no explicit `import` statement needed.
- Contains core classes: `Object`, `String`, `Thread`, `Math`, `System`, `Runtime`, and wrapper classes.

### Key Classes

```
┌─────────────────────────────────────────────────────────────┐
│                    java.lang KEY CLASSES                      │
├─────────────────────────────────────────────────────────────┤
│  Object          → Root of all Java classes                 │
│  String          → Immutable character sequence             │
│  StringBuffer    → Mutable, synchronized                  │
│  StringBuilder   → Mutable, non-synchronized                │
│  Integer         → Wrapper for int                          │
│  Long            → Wrapper for long                         │
│  Short           → Wrapper for short                        │
│  Byte            → Wrapper for byte                         │
│  Double          → Wrapper for double                       │
│  Float           → Wrapper for float                        │
│  Character       → Wrapper for char                         │
│  Boolean         → Wrapper for boolean                      │
│  Math            → Mathematical utilities                   │
│  Runtime         → JVM runtime information                  │
│  System          → System-level operations                  │
│  Thread          → Thread of execution                      │
│  Throwable       → Root of exception hierarchy              │
│  Enum            → Base class for all enums                 │
│  Class           → Represents classes at runtime            │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. Object Class

### Overview

- `java.lang.Object` is the **root class** of all Java classes.
- Every class directly or indirectly extends `Object`.

### Methods

```
┌─────────────────────────────────────────────────────────────┐
│                    OBJECT CLASS METHODS                        │
├────────────────────┬────────────────────────────────────────┤
│ Method             │ Description                            │
├────────────────────┼────────────────────────────────────────┤
│ equals(Object)     │ Compare content (default: reference) │
│ hashCode()         │ Return hash code for object            │
│ toString()         │ Return string representation           │
│ clone()            │ Create shallow copy                    │
│ finalize()         │ Called before garbage collection       │
│ getClass()         │ Return Class object                    │
│ wait()             │ Wait until notified                    │
│ notify()           │ Wake up one waiting thread             │
│ notifyAll()        │ Wake up all waiting threads            │
└────────────────────┴────────────────────────────────────────┘
```

### equals() and hashCode()

```java
class Person {
    String name;
    int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```

### toString()

```java
@Override
public String toString() {
    return "Person{name='" + name + "', age=" + age + "}";
}
```

### clone()

```java
class Student implements Cloneable {
    int id;
    String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }
}
```

### wait(), notify(), notifyAll()

```java
synchronized (lock) {
    lock.wait();      // wait until notified
    lock.notify();    // notify one waiting thread
    lock.notifyAll(); // notify all waiting threads
}
```

### Contract

- `equals()` and `hashCode()` must be consistent: if `a.equals(b)` is true, then `a.hashCode() == b.hashCode()`.

---

## 3. String Class Overview

- Immutable, final, stored in String Constant Pool.
- Refer to **Chapter 22** for a deep dive.

---

## 4. StringBuffer and StringBuilder

- Mutable string classes.
- Refer to **Chapter 22** for a deep dive.

---

## 5. Wrapper Classes

### Overview

- Wrapper classes wrap primitive types into objects.
- Allow primitives to be used in collections and generic APIs.

```
┌─────────────────────────────────────────────────────────────┐
│                    WRAPPER CLASSES                             │
├────────────────────┬────────────────────┬────────────────────┤
│ Primitive          │ Wrapper            │ Parsing Method     │
├────────────────────┼────────────────────┼────────────────────┤
│ byte               │ Byte               │ Byte.parseByte()   │
│ short              │ Short              │ Short.parseShort() │
│ int                │ Integer            │ Integer.parseInt() │
│ long               │ Long               │ Long.parseLong()   │
│ float              │ Float              │ Float.parseFloat() │
│ double             │ Double             │ Double.parseDouble│
│ char               │ Character          │ N/A                │
│ boolean            │ Boolean            │ Boolean.parseBoolean│
└────────────────────┴────────────────────┴────────────────────┘
```

### Usage

```java
Integer i = Integer.valueOf(10);
int primitive = i.intValue();

String s = "100";
int num = Integer.parseInt(s);
String str = Integer.toString(100);
```

### Caching

```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b); // true (cached)

Integer c = 128;
Integer d = 128;
System.out.println(c == d); // false (not cached)
```

---

## 6. Auto-boxing and Auto-unboxing

### Definition

- **Auto-boxing**: Automatic conversion of primitive to wrapper.
- **Auto-unboxing**: Automatic conversion of wrapper to primitive.

### Examples

```java
// Auto-boxing
Integer num = 10; // int → Integer

// Auto-unboxing
int n = num; // Integer → int

// In collections
List<Integer> list = new ArrayList<>();
list.add(5); // auto-boxing
int x = list.get(0); // auto-unboxing
```

### Pitfalls

```java
Integer a = null;
int b = a; // NullPointerException (auto-unboxing null)

// Performance overhead in loops
Long sum = 0L;
for (long i = 0; i < Integer.MAX_VALUE; i++) {
    sum += i; // auto-boxing on every iteration!
}
```

---

## 7. Math Class

### Overview

- `java.lang.Math` contains static methods for mathematical operations.
- Cannot be instantiated (private constructor).

### Methods

```java
int abs = Math.abs(-10);           // 10
double ceil = Math.ceil(4.2);      // 5.0
double floor = Math.floor(4.8);    // 4.0
double max = Math.max(10, 20);     // 20
double min = Math.min(10, 20);     // 10
double pow = Math.pow(2, 3);       // 8.0
double sqrt = Math.sqrt(16);       // 4.0
double random = Math.random();     // 0.0 to 1.0
long round = Math.round(4.5);      // 5
double sin = Math.sin(Math.PI / 2); // 1.0
```

---

## 8. Runtime Class

### Overview

- Singleton class representing the JVM runtime.
- Cannot be instantiated directly; use `Runtime.getRuntime()`.

### Methods

```java
Runtime rt = Runtime.getRuntime();

long free = rt.freeMemory();   // free memory in bytes
long total = rt.totalMemory(); // total memory in bytes
long max = rt.maxMemory();     // max memory in bytes

rt.gc(); // suggest garbage collection

// Execute external command
Process p = rt.exec("notepad.exe");
```

---

## 9. System Class

### Overview

- Provides access to system-level resources.
- Contains `in`, `out`, `err` streams.

### Methods

```java
// Current time
long millis = System.currentTimeMillis();
long nano = System.nanoTime();

// Exit
System.exit(0); // terminate JVM

// GC
System.gc(); // suggest garbage collection

// Environment variables
String path = System.getenv("PATH");

// System properties
String javaVersion = System.getProperty("java.version");

// Array copy
int[] src = {1, 2, 3};
int[] dest = new int[3];
System.arraycopy(src, 0, dest, 0, 3);
```

---

## 10. Thread Class

### Overview

- `java.lang.Thread` represents a thread of execution.
- Extends `Object` and implements `Runnable`.

### Constructors

```java
Thread t1 = new Thread();
Thread t2 = new Thread(runnable);
Thread t3 = new Thread(runnable, "MyThread");
```

### Methods

```java
Thread t = new Thread(() -> System.out.println("Running"));

// Start
t.start();

// Sleep
Thread.sleep(1000);

// Join
t.join();

// Yield
Thread.yield();

// Priority
t.setPriority(Thread.MAX_PRIORITY);

// Daemon
t.setDaemon(true);

// Current thread
Thread current = Thread.currentThread();
```

---

## 11. Interview FAQs

### Q1. Why is `java.lang` automatically imported?

> Because it contains fundamental classes that are essential for every Java program. The compiler implicitly imports it.

### Q2. What is the contract between `equals()` and `hashCode()`?

> If two objects are equal via `equals()`, they must have the same `hashCode()`.

### Q3. What is the difference between shallow copy and deep copy?

> Shallow copy copies the object and its reference fields.
> Deep copy recursively copies all referenced objects.

### Q4. What is the purpose of `finalize()`?

> Called by the garbage collector before reclaiming the object. It is deprecated in Java 9+.

### Q5. What is `getClass()` used for?

> Returns the runtime class of the object. Useful for reflection.

### Q6. What are wrapper classes?

> Classes that wrap primitive types into objects so they can be used in collections and generic APIs.

### Q7. What is the difference between `Integer.valueOf()` and `new Integer()`?

> `Integer.valueOf()` uses caching (for -128 to 127).
> `new Integer()` creates a new object every time (deprecated in Java 9+).

### Q8. What is auto-boxing and auto-unboxing?

> Automatic conversion between primitives and their wrapper classes.

### Q9. What is the `NullPointerException` risk with auto-unboxing?

> Unboxing a `null` wrapper object throws `NullPointerException`.

### Q10. What is the caching range for `Integer`?

> -128 to 127 by default. Can be changed with `-XX:AutoBoxCacheMax`.

### Q11. What is the difference between `Math.ceil()` and `Math.floor()`?

> `ceil()` rounds up to the nearest integer.
> `floor()` rounds down to the nearest integer.

### Q12. What is the difference between `System.currentTimeMillis()` and `System.nanoTime()`?

> `currentTimeMillis()` returns wall-clock time in milliseconds.
> `nanoTime()` returns a high-resolution time value for measuring elapsed time.

### Q13. What is the `Runtime` class?

> A singleton class that provides access to JVM runtime information like memory and processor.

### Q14. Can we instantiate `Runtime` directly?

> No. Use `Runtime.getRuntime()`.

### Q15. What is the difference between `System.gc()` and `Runtime.getRuntime().gc()`?

> Both are the same. `System.gc()` internally calls `Runtime.getRuntime().gc()`.

### Q16. What is the difference between `System.exit(0)` and `System.exit(1)`?

> `0` means normal termination.
> `1` (or non-zero) means abnormal termination.

### Q17. What is `System.arraycopy()`?

> A native method for fast array copying.

### Q18. What is the difference between `Thread.start()` and `Thread.run()`?

> `start()` creates a new thread and calls `run()`.
> `run()` executes in the current thread.

### Q19. What is `Thread.yield()`?

> Suggests the scheduler to give other threads a chance to run.

### Q20. What is `Thread.setDaemon(true)`?

> Marks the thread as a daemon thread. JVM exits when only daemon threads remain.

---

## 12. Quick Reference

### Object Methods

```java
equals(Object), hashCode(), toString(), clone(), finalize()
getClass(), wait(), notify(), notifyAll()
```

### Wrapper Classes

```java
Byte, Short, Integer, Long, Float, Double, Character, Boolean
parseXxx(String), valueOf(primitive), xxxValue()
```

### Math

```java
abs, ceil, floor, max, min, pow, sqrt, random, round, sin, cos, tan
```

### Runtime

```java
Runtime.getRuntime(), freeMemory(), totalMemory(), maxMemory(), gc(), exec()
```

### System

```java
currentTimeMillis(), nanoTime(), exit(), gc(), getenv(), getProperty()
arraycopy(), in, out, err
```

### Thread

```java
start(), run(), sleep(), join(), yield(), setPriority(), setDaemon()
currentThread()
```

---

## 13. Key Takeaways

1. **java.lang** is automatically imported in every Java program.
2. **Object** is the root class; every class inherits from it.
3. **Wrapper classes** allow primitives to be treated as objects.
4. **Auto-boxing/unboxing** simplifies conversion but can cause performance overhead.
5. **Math** is a utility class with all static methods.
6. **Runtime** gives access to JVM runtime information.
7. **System** provides standard I/O and system-level operations.
8. **Thread** is the foundation of Java multithreading.
9. **Interview focus** is on Object methods, wrapper caching, and auto-boxing pitfalls.
10. **equals() and hashCode()** must be overridden together for correctness.

---

**Happy coding! 🚀**

*Master java.lang, and you master the core of Java.*
