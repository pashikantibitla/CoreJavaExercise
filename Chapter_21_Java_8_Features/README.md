# Chapter 21 — Java 8 Features

> **Topics:** Lambda Expressions, Functional Interfaces, Method References, Streams API, Optional, Default/Static Methods, New Date/Time API, Nashorn, Base64, Interview FAQs

---

## Table of Contents

1. [Introduction to Java 8](#1-introduction-to-java-8)
2. [Lambda Expressions](#2-lambda-expressions)
3. [Functional Interfaces](#3-functional-interfaces)
4. [Method References](#4-method-references)
5. [Streams API](#5-streams-api)
6. [Optional Class](#6-optional-class)
7. [Default and Static Methods in Interfaces](#7-default-and-static-methods-in-interfaces)
8. [New Date/Time API](#8-new-datetime-api)
9. [Nashorn JavaScript Engine](#9-nashorn-javascript-engine)
10. [Base64 Encoding/Decoding](#10-base64-encodingdecoding)
11. [Interview FAQs](#11-interview-faqs)
12. [Quick Reference](#12-quick-reference)
13. [Key Takeaways](#13-key-takeaways)

---

## 1. Introduction to Java 8

**File:** `21_Java_8_Features.md` — Sections 1–10

- Released in March 2014
- Major release with functional programming support
- Key features: Lambda, Streams, Functional Interfaces, Optional, New Date/Time API
- Enables cleaner, more concise code

---

## 2. Lambda Expressions

- Anonymous functions with no name, no return type, no modifier
- Syntax: `(parameters) -> { body }`
- Enables functional programming in Java

---

## 3. Functional Interfaces

- Interfaces with exactly one abstract method
- `@FunctionalInterface` annotation
- Built-in: `Predicate`, `Function`, `Consumer`, `Supplier`

---

## 4. Method References

- Shorthand for lambda expressions
- Types: Static, Instance, Constructor, Arbitrary object

---

## 5. Streams API

- Sequence of elements supporting sequential and parallel aggregate operations
- Operations: `filter`, `map`, `flatMap`, `reduce`, `collect`, `forEach`, `sorted`, `distinct`, `limit`, `skip`

---

## 6. Optional Class

- Container object to avoid `NullPointerException`
- Methods: `of`, `ofNullable`, `empty`, `isPresent`, `ifPresent`, `orElse`, `orElseGet`, `orElseThrow`, `map`, `flatMap`

---

## 7. Default and Static Methods in Interfaces

- `default` methods provide implementation in interfaces
- `static` methods belong to the interface, not instances
- Solves diamond problem with multiple inheritance

---

## 8. New Date/Time API

- Package: `java.time`
- Immutable and thread-safe
- Classes: `LocalDate`, `LocalTime`, `LocalDateTime`, `Period`, `Duration`, `Instant`
- Formatting with `DateTimeFormatter`

---

## 9. Nashorn JavaScript Engine

- JavaScript engine bundled with JDK 8
- Execute JS code from Java
- Replaced in later JDKs but important for Java 8

---

## 10. Base64 Encoding/Decoding

- Package: `java.util.Base64`
- Basic, URL, MIME encoding schemes

---

## 📁 Additional Files

- **java_programs/** — 12+ .java files covering each topic
- **21_Java_8_Features.md** — Full theory and notes

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Lambda Expressions ──→ Syntax, usage, functional interfaces
    │
    ├── Functional Interfaces ──→ Predicate, Function, Consumer, Supplier
    │
    ├── Method References ──→ Static, instance, constructor references
    │
    ├── Streams API ──→ filter, map, reduce, collect, grouping
    │
    ├── Optional ──→ Null-safe programming
    │
    ├── Default/Static Methods ──→ Interface evolution
    │
    ├── New Date/Time API ──→ LocalDate, LocalTime, formatting
    │
    ├── Nashorn & Base64 ──→ Misc utilities
    │
    └── Interview FAQs ──→ Real-world questions
```

---

## 📋 Key Takeaways

1. **Java 8** introduced functional programming paradigms.
2. **Lambda** reduces boilerplate code for anonymous inner classes.
3. **Functional Interfaces** are the backbone of lambda usage.
4. **Streams** enable declarative data processing.
5. **Optional** prevents NullPointerException elegantly.
6. **Default/Static methods** allow interfaces to evolve without breaking existing implementations.
7. **New Date/Time API** is immutable and thread-safe, replacing `Date`/`Calendar`.
8. **Base64** is a built-in utility for encoding/decoding.
9. **Nashorn** allows embedding JavaScript in Java.
10. **Interview focus** is heavily on Streams, Lambdas, and Optional.

---

**Happy coding! 🚀**

*Master Java 8, and you master modern Java development.*
