# Chapter 18 — Assertions

> **Topics:** Introduction, Simple Assert, Augmented Assert, Runtime Flags, Proper Use, Assertions vs Exceptions, Production Code, Interview FAQs

---

## Table of Contents

1. [Assertions Overview](#1-assertions-overview)
2. [Simple Assert vs Augmented Assert](#2-simple-assert-vs-augmented-assert)
3. [Runtime Flags](#3-runtime-flags)
4. [Proper and Improper Use](#4-proper-and-improper-use)
5. [Assertions vs Exceptions](#5-assertions-vs-exceptions)
6. [Assertions in Production](#6-assertions-in-production)
7. [Interview FAQs](#7-interview-faqs)

---

## 1. Assertions Overview

**File:** `18_Assertions.md`  
**Topics:** Introduction to Assertions

- What are assertions?
- Characteristics of assertions
- Assertions vs other error-checking mechanisms
- When to use assertions

---

## 2. Simple Assert vs Augmented Assert

**File:** `18_Assertions.md`  
**Topics:** Syntax and forms

- `assert condition;` — Simple assert
- `assert condition : detailMessage;` — Augmented assert
- Comparison and examples

---

## 3. Runtime Flags

**File:** `18_Assertions.md`  
**Topics:** JVM flags

- `-ea` / `-enableassertions` — Enable assertions
- `-da` / `-disableassertions` — Disable assertions
- `-esa` / `-enablesystemassertions` — System assertions
- Package and class-level flags

---

## 4. Proper and Improper Use

**File:** `18_Assertions.md`  
**Topics:** Best practices

- ✅ Internal invariants
- ✅ Control-flow invariants
- ✅ Post-conditions
- ✅ Class invariants
- ❌ Public method argument checking
- ❌ Error handling
- ❌ External conditions
- ❌ Side effects in assertions

---

## 5. Assertions vs Exceptions

**File:** `18_Assertions.md`  
**Topics:** Differences and use cases

- Assertions are for debugging
- Exceptions are for error handling
- When to use each
- Can they coexist?

---

## 6. Assertions in Production

**File:** `18_Assertions.md`  
**Topics:** Production considerations

- Assertions are disabled by default
- Do not rely on assertions for correctness
- Zero overhead when disabled
- Side effects warning

---

## 7. Interview FAQs

**File:** `18_Assertions.md`  
**Topics:** Common interview questions

- Syntax and forms
- AssertionError handling
- Runtime flags
- Proper use cases
- Production code best practices

---

## 📁 Additional Files

- **java_programs/** — Individual .java files demonstrating:
  - Simple assert
  - Augmented assert
  - Assertions with methods
  - Interview-level problems

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Introduction to Assertions ──→ What, why, and when
    │
    ├── Simple vs Augmented Assert ──→ Syntax and usage
    │
    ├── Runtime Flags ──→ -ea, -da, -esa, -dsa
    │
    ├── Proper vs Improper Use ──→ Best practices and pitfalls
    │
    ├── Assertions vs Exceptions ──→ Clear distinction
    │
    ├── Assertions in Production ──→ Real-world considerations
    │
    └── Interview FAQs ──→ Common questions and answers
```

---

## 📋 Key Takeaways

1. **Assertions are for debugging** — they verify assumptions during development.
2. **Two forms** — `assert condition;` and `assert condition : message;`.
3. **Disabled by default** — must enable with `-ea` flag.
4. **Never use assertions** for public API validation, error handling, or security checks.
5. **Use assertions** for internal invariants, control-flow checks, and post-conditions.
6. **AssertionError** extends Error — should never be caught.
7. **No side effects** — assertion expressions must not modify state.
8. **Zero overhead** — assertions have no performance impact when disabled.
9. **Assertions are not exceptions** — they complement but do not replace exceptions.
10. **Production code** — should never rely on assertions for correctness.

---

**Happy coding!**

*Assertions are a powerful tool for detecting bugs early — use them wisely!*
