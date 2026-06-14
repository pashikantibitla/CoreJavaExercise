# Chapter 19 — Regular Expressions

> **Topics:** Introduction, Pattern and Matcher, Character Classes, Quantifiers, Boundary Matchers, Groups and Capturing, String Regex Methods, Common Patterns, Interview FAQs

---

## Table of Contents

1. [Introduction to Regular Expressions](#1-introduction-to-regular-expressions)
2. [Pattern and Matcher Classes](#2-pattern-and-matcher-classes)
3. [Character Classes](#3-character-classes)
4. [Quantifiers](#4-quantifiers)
5. [Boundary Matchers](#5-boundary-matchers)
6. [Groups and Capturing](#6-groups-and-capturing)
7. [String Regex Methods](#7-string-regex-methods)
8. [Common Regex Patterns](#8-common-regex-patterns)
9. [Interview FAQs](#9-interview-faqs)

---

## 1. Introduction to Regular Expressions

**File:** `19_Regular_Expressions.md`  
**Topics:** Introduction

- What is a regular expression?
- Java regex package: `java.util.regex`
- Pattern and Matcher classes
- Common use cases

---

## 2. Pattern and Matcher Classes

**File:** `19_Regular_Expressions.md`  
**Topics:** Core classes

- `Pattern.compile()`
- `Matcher.find()`, `matches()`, `lookingAt()`
- `Matcher.group()`, `start()`, `end()`
- Pattern flags

---

## 3. Character Classes

**File:** `19_Regular_Expressions.md`  
**Topics:** Character matching

- Simple classes: `[abc]`, `[^abc]`, `[a-z]`
- Predefined classes: `\d`, `\D`, `\s`, `\S`, `\w`, `\W`
- Union, intersection, subtraction

---

## 4. Quantifiers

**File:** `19_Regular_Expressions.md`  
**Topics:** Repetition

- `?`, `*`, `+`, `{n}`, `{n,}`, `{n,m}`
- Greedy, reluctant, and possessive modes
- Examples and use cases

---

## 5. Boundary Matchers

**File:** `19_Regular_Expressions.md`  
**Topics:** Position matching

- `^`, `$`, `\b`, `\B`
- `\A`, `\Z`, `\z`, `\G`
- Examples

---

## 6. Groups and Capturing

**File:** `19_Regular_Expressions.md`  
**Topics:** Grouping

- Capturing groups `(regex)`
- Non-capturing groups `(?:regex)`
- Named groups `(?<name>regex)`
- Backreferences `\n`

---

## 7. String Regex Methods

**File:** `19_Regular_Expressions.md`  
**Topics:** String class convenience

- `matches(regex)`
- `replaceAll(regex, replacement)`
- `replaceFirst(regex, replacement)`
- `split(regex)` and `split(regex, limit)`

---

## 8. Common Regex Patterns

**File:** `19_Regular_Expressions.md`  
**Topics:** Real-world patterns

- Email validation
- Phone number validation
- Password validation
- URL validation
- IP address validation
- Date validation
- Hex color validation

---

## 9. Interview FAQs

**File:** `19_Regular_Expressions.md`  
**Topics:** Interview questions

- `matches()` vs `find()`
- Case-insensitive regex
- Capturing groups
- `split()` with limit
- Greedy vs reluctant
- Backreferences
- `replaceAll()` vs `replace()`
- Validation patterns

---

## 📁 Additional Files

- **java_programs/** — Individual .java files demonstrating:
  - Pattern and Matcher basics
  - Character classes
  - Quantifiers
  - Groups and capturing
  - String regex methods
  - Interview-level problems (email, phone, etc.)

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Introduction to Regex ──→ What is regex and why use it
    │
    ├── Pattern and Matcher ──→ Core classes and methods
    │
    ├── Character Classes ──→ Matching character sets
    │
    ├── Quantifiers ──→ Repetition and matching modes
    │
    ├── Boundary Matchers ──→ Position-based matching
    │
    ├── Groups and Capturing ──→ Extracting matched data
    │
    ├── String Regex Methods ──→ Convenient string operations
    │
    ├── Common Patterns ──→ Real-world validation patterns
    │
    └── Interview FAQs ──→ Common questions and answers
```

---

## 📋 Key Takeaways

1. **Regex is a pattern language** for matching, searching, and manipulating text.
2. **Pattern and Matcher** are the core classes in `java.util.regex`.
3. **Character classes** define sets of characters to match.
4. **Quantifiers** control how many times a pattern should match.
5. **Boundary matchers** check positions rather than characters.
6. **Capturing groups** store matched text for later use.
7. **String methods** (`matches`, `replaceAll`, `split`) provide convenient regex operations.
8. **Greedy quantifiers** match as much as possible; **reluctant** matches as little as possible.
9. **Assertions** (`(?=...)`, `(?!...)`) are used for lookahead checks.
10. **Regex is powerful but complex** — test patterns thoroughly before production use.

---

**Happy coding!**

*Master regex, and you master text processing in Java!*
