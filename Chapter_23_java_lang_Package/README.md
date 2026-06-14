# Chapter 23 — java.lang Package

> **Topics:** Object class, String, StringBuffer, StringBuilder, Wrapper classes, Auto-boxing/unboxing, Math, Runtime, System, Thread, Interview FAQs

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

**File:** `23_java_lang_Package.md` — Sections 1–10

- Automatically imported, no need for explicit import
- Contains core classes: Object, String, Thread, Math, System, Runtime, Wrapper classes

---

## 2. Object Class

- Root of all Java classes
- Methods: `equals`, `hashCode`, `toString`, `clone`, `finalize`, `getClass`, `wait`, `notify`, `notifyAll`

---

## 3. String Class Overview

- Immutable, final, in String Constant Pool
- Refer to Chapter 22 for deep dive

---

## 4. StringBuffer and StringBuilder

- Mutable string classes
- Refer to Chapter 22 for deep dive

---

## 5. Wrapper Classes

- `Integer`, `Long`, `Short`, `Byte`, `Double`, `Float`, `Character`, `Boolean`
- Convert primitives to objects and vice versa

---

## 6. Auto-boxing and Auto-unboxing

- Automatic conversion between primitives and wrappers
- Introduced in Java 5
- Performance implications in loops

---

## 7. Math Class

- Utility methods: `abs`, `ceil`, `floor`, `max`, `min`, `pow`, `sqrt`, `random`, `round`

---

## 8. Runtime Class

- Singleton representing JVM runtime
- Methods: `getRuntime()`, `exec()`, `freeMemory()`, `totalMemory()`, `maxMemory()`, `gc()`

---

## 9. System Class

- Standard input/output/error streams
- Methods: `currentTimeMillis()`, `nanoTime()`, `exit()`, `gc()`, `getenv()`, `getProperties()`

---

## 10. Thread Class

- Represents a thread of execution
- Methods: `start()`, `run()`, `sleep()`, `join()`, `yield()`, `setDaemon()`, `setPriority()`

---

## 📁 Additional Files

- **java_programs/** — 6+ .java files covering Object, wrappers, Math, Runtime, System, and interview problems
- **23_java_lang_Package.md** — Full theory and notes

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Object Class ──→ equals, hashCode, toString, clone, wait/notify
    │
    ├── Wrapper Classes ──→ Integer, Double, Boolean, etc.
    │
    ├── Auto-boxing/Unboxing ──→ Automatic conversions, caveats
    │
    ├── Math Class ──→ Utility math methods
    │
    ├── Runtime Class ──→ JVM memory, exec, gc
    │
    ├── System Class ──→ I/O, time, properties
    │
    └── Interview FAQs ──→ Tricky questions on java.lang
```

---

## 📋 Key Takeaways

1. **java.lang** is automatically imported in every Java program.
2. **Object** is the root class; every class inherits from it.
3. **Wrapper classes** allow primitives to be treated as objects.
4. **Auto-boxing/unboxing** simplifies conversion but can cause performance overhead.
5. **Math** is a utility class with all static methods.
6. **Runtime** gives access to JVM runtime information.
7. **System** provides standard I/O and system-level operations.
8. **Thread** is the foundation of Java multithreading.
9. **Interview focus** is on Object methods, wrapper caching, and auto-boxing pitfalls.

---

**Happy coding! 🚀**

*Master java.lang, and you master the core of Java.*
