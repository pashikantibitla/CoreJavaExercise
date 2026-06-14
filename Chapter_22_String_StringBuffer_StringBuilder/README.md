# Chapter 22 — String, StringBuffer, StringBuilder

> **Topics:** String Immutability, String Pool, String Methods, StringBuffer, StringBuilder, Performance, Interview FAQs

---

## Table of Contents

1. [String Class](#1-string-class)
2. [String Methods](#2-string-methods)
3. [StringBuffer](#3-stringbuffer)
4. [StringBuilder](#4-stringbuilder)
5. [Difference between String, StringBuffer, StringBuilder](#5-difference-between-string-stringbuffer-stringbuilder)
6. [String Immutability Benefits](#6-string-immutability-benefits)
7. [String Constant Pool](#7-string-constant-pool)
8. [Interview FAQs](#8-interview-faqs)
9. [Quick Reference](#9-quick-reference)
10. [Key Takeaways](#10-key-takeaways)

---

## 1. String Class

**File:** `22_String_StringBuffer_StringBuilder.md` — Sections 1–7

- Immutable sequence of characters
- Stored in String Constant Pool (SCP)
- `java.lang.String` is final class

---

## 2. String Methods

- `charAt`, `concat`, `contains`, `equals`, `equalsIgnoreCase`
- `indexOf`, `lastIndexOf`, `length`, `replace`, `split`
- `substring`, `toLowerCase`, `toUpperCase`, `trim`, `valueOf`

---

## 3. StringBuffer

- Mutable, synchronized, thread-safe
- Methods: `append`, `insert`, `reverse`, `delete`, `replace`, `capacity`, `length`

---

## 4. StringBuilder

- Mutable, non-synchronized, faster
- Same methods as StringBuffer but not thread-safe

---

## 5. Difference between String, StringBuffer, StringBuilder

| Feature | String | StringBuffer | StringBuilder |
|---------|--------|--------------|---------------|
| Mutable | No | Yes | Yes |
| Thread-safe | Yes (immutable) | Yes (synchronized) | No |
| Performance | Slow (new object) | Slow (synchronized) | Fast |
| Version | 1.0 | 1.0 | 1.5 |

---

## 6. String Immutability Benefits

- Security, synchronization, caching, hashcode caching
- String pool optimization

---

## 7. String Constant Pool

- Special memory region in heap (Java 7+)
- Reuses string literals
- `intern()` method

---

## 📁 Additional Files

- **java_programs/** — 6+ .java files demonstrating all concepts
- **22_String_StringBuffer_StringBuilder.md** — Full theory and notes

---

## 🎯 Learning Path

```
Start Here
    │
    ├── String Basics ──→ Immutability, constant pool, creation
    │
    ├── String Methods ──→ charAt, substring, replace, split, etc.
    │
    ├── StringBuffer ──→ Mutable, synchronized operations
    │
    ├── StringBuilder ──→ Mutable, faster, non-synchronized
    │
    ├── Performance ──→ Comparison of String vs Buffer vs Builder
    │
    └── Interview FAQs ──→ Real-world tricky questions
```

---

## 📋 Key Takeaways

1. **String is immutable** — any modification creates a new object.
2. **StringBuffer** is thread-safe and mutable.
3. **StringBuilder** is faster but not thread-safe.
4. **String Constant Pool** saves memory by reusing literals.
5. **Use StringBuilder** for single-threaded string concatenation.
6. **Use StringBuffer** for multi-threaded string manipulation.
7. **Interview focus** is heavily on immutability reasons and pool behavior.

---

**Happy coding! 🚀**

*Master String internals, and you master Java interview basics.*
