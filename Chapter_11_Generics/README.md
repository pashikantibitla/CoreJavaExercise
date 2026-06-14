# Chapter 11 — Generics

> **Topics:** Type Safety, Generic Classes, Generic Methods, Wildcards, Bounded Types, Type Erasure, Generic Interfaces

---

## Table of Contents

1. [Introduction to Generics](#1-introduction-to-generics)
2. [Type Safety and Type Casting](#2-type-safety-and-type-casting)
3. [Generic Classes](#3-generic-classes)
4. [Generic Methods](#4-generic-methods)
5. [Wildcard Characters](#5-wildcard-characters)
6. [Bounded Types](#6-bounded-types)
7. [Type Erasure](#7-type-erasure)
8. [Generic Interfaces](#8-generic-interfaces)
9. [Interview FAQs](#9-interview-faqs)
10. [Java Programs](#10-java-programs)

---

## 1. Introduction to Generics

**File:** `11_Generics.md`

- Introduction to Generics
- Why Generics are needed
- Type parameter naming conventions
- Syntax and basic usage

---

## 2. Type Safety and Type Casting

**File:** `11_Generics.md`

- Problems without Generics (ClassCastException)
- Type safety with Generics
- Polymorphism with Generics
- Raw types vs Parameterized types
- Diamond operator

---

## 3. Generic Classes

**File:** `11_Generics.md`

- Single type parameter
- Multiple type parameters
- Generic class with arrays
- Primitive restrictions (autoboxing)
- Static context rules

---

## 4. Generic Methods

**File:** `11_Generics.md`

- Defining generic methods
- Generic method vs Generic class
- Explicit type arguments
- Calling generic methods

---

## 5. Wildcard Characters

**File:** `11_Generics.md`

- Unbounded wildcard `?`
- Upper bounded wildcard `? extends T`
- Lower bounded wildcard `? super T`
- PECS principle (Producer Extends, Consumer Super)
- Wildcard restrictions

---

## 6. Bounded Types

**File:** `11_Generics.md`

- Upper bounded types (`<T extends Number>`)
- Multiple bounds (`<T extends Number & Comparable<T>>`)
- Bounded types in generic methods

---

## 7. Type Erasure

**File:** `11_Generics.md`

- What is type erasure
- Compile-time vs Runtime behavior
- Bridge methods
- Reification restrictions

---

## 8. Generic Interfaces

**File:** `11_Generics.md`

- Defining generic interfaces
- Implementing with specific type
- Generic implementation
- Multiple type parameters

---

## 9. Interview FAQs

**File:** `11_Generics.md`

- 15+ interview questions covering:
  - Type safety and erasure
  - Wildcards and bounds
  - PECS principle
  - Raw types
  - Bridge methods
  - Diamond operator

---

## 10. Java Programs

**Directory:** `java_programs/`

| # | File | Concept |
|---|------|---------|
| 1 | `GenericClassDemo.java` | Generic class with single type parameter |
| 2 | `GenericMethodDemo.java` | Generic methods with multiple types |
| 3 | `BoundedTypeDemo.java` | Bounded types with Numbers |
| 4 | `MultipleBoundsDemo.java` | Multiple bounds (class + interface) |
| 5 | `WildcardUpperDemo.java` | Upper bounded wildcard `? extends` |
| 6 | `WildcardLowerDemo.java` | Lower bounded wildcard `? super` |
| 7 | `WildcardUnboundedDemo.java` | Unbounded wildcard `?` |
| 8 | `TypeSafetyDemo.java` | Type safety demonstration |
| 9 | `GenericInterfaceDemo.java` | Generic interface implementation |
| 10 | `GenericCollectionsDemo.java` | Generics with Collections |
| 11 | `GenericPairDemo.java` | Generic class with multiple parameters |
| 12 | `TypeErasureDemo.java` | Type erasure demonstration |
| 13 | `GenericStackDemo.java` | Generic stack implementation |
| 14 | `GenericMaxFinder.java` | Interview-level bounded method |
| 15 | `GenericSwapDemo.java` | Interview-level generic swap |

---

## 📁 Additional Files

- **11_Generics.md** — Comprehensive theory with examples and diagrams
- **java_programs/** — Individual .java files for each concept

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Type Safety ──→ Why generics, raw types, type casting
    │
    ├── Generic Classes ──→ Single parameter, multiple parameters, arrays
    │
    ├── Generic Methods ──→ Method-level type parameters
    │
    ├── Wildcards ──→ ?, extends, super, PECS
    │
    ├── Bounded Types ──→ extends, multiple bounds
    │
    ├── Type Erasure ──→ Compile-time vs runtime
    │
    └── Generic Interfaces ──→ Interface design with generics
```

---

## 📋 Key Takeaways

1. **Type safety** — Catch errors at compile time, not runtime.
2. **Type erasure** — Generic info removed at runtime for backward compatibility.
3. **Wildcards** — `? extends T` for reading, `? super T` for writing.
4. **Bounded types** — Restrict type parameters to specific hierarchies.
5. **No primitives** — Use wrapper classes with autoboxing.
6. **No generic arrays** — Due to type erasure.
7. **Static methods** — Cannot use class type params but can declare their own.
8. **Diamond operator** — `<>` reduces boilerplate in declarations.
9. **Generic interfaces** — Enable type-safe API design.
10. **Interview focus** — PECS, erasure, bounds, and raw types are common questions.

---

**Happy coding!**

*Master Generics, and you unlock type-safe, reusable code in Java.*
