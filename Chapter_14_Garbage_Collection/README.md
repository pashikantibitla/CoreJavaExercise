# Chapter 14 — Garbage Collection

> **Topics:** Object Eligibility, finalize(), System.gc(), GC Types, GC Algorithms, Heap Structure, JVM Options, Reference Types, Interview FAQs

---

## Table of Contents

1. [Introduction to Garbage Collection](#1-introduction-to-garbage-collection)
2. [Ways to Make Object Eligible for GC](#2-ways-to-make-object-eligible-for-gc)
3. [Methods for Requesting JVM to Run GC](#3-methods-for-requesting-jvm-to-run-gc)
4. [finalize() Method](#4-finalize-method)
5. [Memory Leaks and GC](#5-memory-leaks-and-gc)
6. [Types of GC](#6-types-of-gc)
7. [GC Algorithms](#7-gc-algorithms)
8. [Heap Memory Structure](#8-heap-memory-structure)
9. [GC Tuning and JVM Options](#9-gc-tuning-and-jvm-options)
10. [System.gc() and Runtime.gc()](#10-systemgc-and-runtimegc)
11. [Reference Types](#11-reference-types)
12. [java.lang.ref Package](#12-javalangref-package)
13. [Interview Questions](#13-interview-questions)

---

## 1. Introduction to Garbage Collection

**File:** `14_Garbage_Collection.md`  
**Topics:** Automatic memory management, GC roots, unreachable objects

- JVM automatically manages memory.
- Objects without references are eligible for GC.
- GC roots: local variables, active threads, static fields, JNI references.

---

## 2. Ways to Make Object Eligible for GC

**File:** `14_Garbage_Collection.md`  
**Topics:** Nullifying reference, reassigning reference, island of isolation

- Set reference to `null`.
- Reassign reference to another object.
- Objects referencing each other but no external reference (island of isolation).

---

## 3. Methods for Requesting JVM to Run GC

**File:** `14_Garbage_Collection.md`  
**Topics:** `System.gc()`, `Runtime.gc()`, not guaranteed

- `System.gc()` and `Runtime.gc()` are suggestions to JVM.
- JVM may ignore the request.

---

## 4. finalize() Method

**File:** `14_Garbage_Collection.md`  
**Topics:** `Object.finalize()`, cleanup, resurrection, deprecated

- Called by GC before object is destroyed.
- Can be used for cleanup (close resources).
- Object can be resurrected in `finalize()`.
- Deprecated since Java 9 (use `try-with-resources` or `Cleaner`).

---

## 5. Memory Leaks and GC

**File:** `14_Garbage_Collection.md`  
**Topics:** Static collections, listeners, unclosed resources, caches

- Memory leaks occur when objects are not released unintentionally.
- Common causes: static collections, unregistered listeners, unclosed streams.

---

## 6. Types of GC

**File:** `14_Garbage_Collection.md`  
**Topics:** Serial, Parallel, CMS, G1, ZGC, Shenandoah

- **Serial GC**: Single thread, simple.
- **Parallel GC**: Multiple threads, throughput.
- **CMS**: Low pause, concurrent.
- **G1 GC**: Balanced, default (Java 9+).
- **ZGC**: Ultra-low latency (Java 11+).
- **Shenandoah**: Low pause (Java 12+).

---

## 7. GC Algorithms

**File:** `14_Garbage_Collection.md`  
**Topics:** Mark-Sweep, Copy, Mark-Compact

- **Mark-Sweep**: Mark live objects, sweep dead objects.
- **Copy**: Copy live objects to new space, clear old space.
- **Mark-Compact**: Mark live objects, compact them to one end.

---

## 8. Heap Memory Structure

**File:** `14_Garbage_Collection.md`  
**Topics:** Young Generation (Eden, S0, S1), Old Generation (Tenured), PermGen/Metaspace

- **Young Generation**: New objects. Eden, Survivor 0 (S0), Survivor 1 (S1).
- **Old Generation**: Long-lived objects.
- **Metaspace**: Class metadata (replaced PermGen in Java 8).

---

## 9. GC Tuning and JVM Options

**File:** `14_Garbage_Collection.md`  
**Topics:** `-Xms`, `-Xmx`, `-XX:+UseG1GC`, `-XX:MaxGCPauseMillis`

- Heap size tuning: `-Xms`, `-Xmx`.
- GC selection: `-XX:+UseG1GC`, `-XX:+UseSerialGC`, etc.
- Pause time goals: `-XX:MaxGCPauseMillis`.

---

## 10. System.gc() and Runtime.gc()

**File:** `14_Garbage_Collection.md`  
**Topics:** Suggestion, not command, performance impact

- Both are suggestions to JVM.
- `Runtime.getRuntime().gc()` is the same as `System.gc()`.
- Usually better to let JVM decide.

---

## 11. Reference Types

**File:** `14_Garbage_Collection.md`  
**Topics:** Strong, Soft, Weak, Phantom

- **Strong**: Normal reference, never GC'd.
- **Soft**: GC'd when memory is low.
- **Weak**: GC'd when GC runs.
- **Phantom**: After object is finalized, used for cleanup tracking.

---

## 12. java.lang.ref Package

**File:** `14_Garbage_Collection.md`  
**Topics:** `ReferenceQueue`, `SoftReference`, `WeakReference`, `PhantomReference`

- `ReferenceQueue`: Queue to receive notification when object is GC'd.
- `SoftReference`: For caches.
- `WeakReference`: For canonicalized mappings.
- `PhantomReference`: For cleanup tracking.

---

## 📁 Additional Files

- **14_Garbage_Collection.md** — Comprehensive theory covering all 13 parts
- **java_programs/** — Individual `.java` files demonstrating GC concepts

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Introduction to GC ──→ Automatic memory management, GC roots
    │
    ├── Object Eligibility ──→ Nullifying, reassigning, island of isolation
    │
    ├── Requesting GC ──→ System.gc(), Runtime.gc() (suggestion only)
    │
    ├── finalize() ──→ Cleanup, resurrection, deprecated status
    │
    ├── Memory Leaks ──→ Static collections, listeners, unclosed resources
    │
    ├── Types of GC ──→ Serial, Parallel, CMS, G1, ZGC, Shenandoah
    │
    ├── GC Algorithms ──→ Mark-Sweep, Copy, Mark-Compact
    │
    ├── Heap Structure ──→ Young/Old generation, Eden, Survivor, Metaspace
    │
    ├── GC Tuning ──→ JVM options, heap sizing, GC selection
    │
    ├── Reference Types ──→ Strong, Soft, Weak, Phantom
    │
    ├── java.lang.ref ──→ ReferenceQueue, WeakHashMap, Cleaner
    │
    └── Interview FAQs ──→ Common GC interview questions
```

---

## 📋 Key Takeaways

1. **Garbage Collection** is automatic memory management in Java.
2. **Object eligibility** occurs when no references point to the object.
3. **`System.gc()`** is a suggestion, not a command.
4. **`finalize()`** is deprecated; use `try-with-resources` or `Cleaner` instead.
5. **Memory leaks** happen when objects are unintentionally retained.
6. **Serial GC** is single-threaded; **Parallel GC** is multi-threaded.
7. **CMS** aims for low pause; **G1 GC** is the default (Java 9+).
8. **Heap** is divided into Young (Eden, S0, S1) and Old (Tenured) generations.
9. **Mark-Sweep** may cause fragmentation; **Mark-Compact** avoids it.
10. **Soft references** are good for caches; **Weak references** for canonical mappings.
11. **Phantom references** are used for cleanup tracking after finalization.
12. **`ReferenceQueue`** receives notifications when referenced objects are GC'd.
13. **JVM tuning** options: `-Xms`, `-Xmx`, `-XX:+UseG1GC`, `-XX:MaxGCPauseMillis`.
14. **WeakHashMap** uses weak keys and is useful for temporary caches.
15. **Always close resources** to avoid memory leaks, rather than relying on `finalize()`.

---

**Happy coding! 🚀**

*Understanding Garbage Collection is essential for writing memory-efficient, high-performance Java applications.*
