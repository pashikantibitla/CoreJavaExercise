# 22 — String, StringBuffer, StringBuilder

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

### Overview

- `java.lang.String` is a **final** class.
- Represents an **immutable** sequence of characters.
- Implements `Serializable`, `Comparable<String>`, `CharSequence`.

### String Creation

```java
// Literal — goes to String Constant Pool
String s1 = "hello";

// new — goes to Heap
String s2 = new String("hello");

// intern
String s3 = s2.intern(); // moves to SCP if not present
```

### Immutability

```java
String s = "hello";
s = s + " world"; // creates new object, s now points to "hello world"
// original "hello" is unchanged
```

### Memory Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    STRING MEMORY LAYOUT                        │
│  Stack                Heap              String Constant Pool │
│  ┌──────┐            ┌──────┐            ┌──────┐            │
│  │ s1 ──┼───────────→│ "hello" │            │ "hello" │            │
│  │ s2 ──┼───────────→│ "hello" │ (new)    │ "world" │            │
│  │ s3 ──┼───────────────────────────────→│ "hello" │            │
│  └──────┘            └──────┘            └──────┘            │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. String Methods

### Common Methods

```java
String s = "  Hello World  ";

// charAt
char c = s.charAt(1); // ' ' (space)

// length
int len = s.length(); // 15

// substring
String sub = s.substring(2, 7); // "Hello"

// toLowerCase / toUpperCase
String lower = s.toLowerCase(); // "  hello world  "
String upper = s.toUpperCase(); // "  HELLO WORLD  "

// trim
String trimmed = s.trim(); // "Hello World"

// replace
String replaced = s.replace('l', 'x'); // "  Hexxo Worxd  "

// contains
boolean has = s.contains("World"); // true

// indexOf / lastIndexOf
int first = s.indexOf('l'); // 4
int last = s.lastIndexOf('l'); // 9

// startsWith / endsWith
boolean sw = s.trim().startsWith("Hello"); // true

// equals / equalsIgnoreCase
boolean eq = s.trim().equals("Hello World"); // true
boolean eqi = s.trim().equalsIgnoreCase("hello world"); // true

// split
String[] parts = s.trim().split(" "); // ["Hello", "World"]

// concat
String cat = "Hello".concat(" World"); // "Hello World"

// isEmpty / isBlank (Java 11+)
boolean empty = "".isEmpty(); // true

// valueOf
String num = String.valueOf(123); // "123"

// compareTo
int cmp = "apple".compareTo("banana"); // negative
```

### Method Summary Table

```
┌─────────────────────────────────────────────────────────────┐
│                    STRING METHODS REFERENCE                    │
├────────────────────┬────────────────────────────────────────┤
│ Method             │ Description                            │
├────────────────────┼────────────────────────────────────────┤
│ charAt(int)        │ Character at index                     │
│ length()           │ Number of characters                   │
│ substring(int)     │ Substring from index to end            │
│ substring(int,int) │ Substring from start to end-1          │
│ toLowerCase()      │ Lowercase version                      │
│ toUpperCase()      │ Uppercase version                      │
│ trim()             │ Remove leading/trailing spaces         │
│ replace(char,char) │ Replace all occurrences                │
│ replace(Char,Char) │ Replace all occurrences                │
│ contains(CharSeq)  │ Check if contains substring            │
│ indexOf(char)      │ First index of char                    │
│ lastIndexOf(char)  │ Last index of char                     │
│ startsWith(String) │ Starts with prefix?                    │
│ endsWith(String)   │ Ends with suffix?                      │
│ equals(Object)     │ Content equality (case-sensitive)    │
│ equalsIgnoreCase   │ Content equality (case-insensitive)    │
│ split(String)      │ Split by regex                         │
│ concat(String)     │ Concatenate strings                    │
│ isEmpty()          │ Length == 0?                           │
│ valueOf(primitive) │ Convert to String                      │
│ compareTo(String)  │ Lexicographic comparison               │
│ intern()           │ Add to SCP and return reference        │
│ toCharArray()      │ Convert to char array                  │
│ getBytes()         │ Convert to byte array                  │
│ matches(String)    │ Regex match                            │
│ replaceFirst       │ Replace first regex match              │
│ replaceAll         │ Replace all regex matches              │
└────────────────────┴────────────────────────────────────────┘
```

---

## 3. StringBuffer

### Overview

- **Mutable** — contents can be changed.
- **Synchronized** — thread-safe.
- Slower than StringBuilder due to synchronization overhead.

### Constructors

```java
StringBuffer sb1 = new StringBuffer();        // capacity 16
StringBuffer sb2 = new StringBuffer(50);      // capacity 50
StringBuffer sb3 = new StringBuffer("hello"); // capacity 16 + 5 = 21
```

### Common Methods

```java
StringBuffer sb = new StringBuffer("Hello");

// append
sb.append(" World"); // "Hello World"

// insert
sb.insert(5, " Java"); // "Hello Java World"

// reverse
sb.reverse(); // "dlroW avaJ olleH"

// delete
sb.delete(5, 10); // remove chars from index 5 to 9

// deleteCharAt
sb.deleteCharAt(0); // remove first char

// replace
sb.replace(0, 5, "Hi"); // replace chars 0-4 with "Hi"

// capacity
int cap = sb.capacity(); // current capacity

// length
int len = sb.length(); // current length

// setCharAt
sb.setCharAt(0, 'h'); // change char at index

// trimToSize
sb.trimToSize(); // reduce capacity to length

// ensureCapacity
sb.ensureCapacity(100); // ensure minimum capacity
```

---

## 4. StringBuilder

### Overview

- **Mutable** — contents can be changed.
- **Non-synchronized** — not thread-safe.
- **Faster** than StringBuffer.
- Introduced in **Java 1.5**.

### Constructors

```java
StringBuilder sb1 = new StringBuilder();        // capacity 16
StringBuilder sb2 = new StringBuilder(50);        // capacity 50
StringBuilder sb3 = new StringBuilder("hello");   // capacity 21
```

### Common Methods

```java
StringBuilder sb = new StringBuilder("Hello");

// All same methods as StringBuffer
sb.append(" World");
sb.insert(5, " Java");
sb.reverse();
sb.delete(5, 10);
sb.replace(0, 5, "Hi");
int cap = sb.capacity();
int len = sb.length();
```

---

## 5. Difference between String, StringBuffer, StringBuilder

```
┌─────────────────────────────────────────────────────────────┐
│              STRING vs STRINGBUFFER vs STRINGBUILDER           │
├────────────────────┬──────────┬─────────────┬───────────────┤
│ Feature            │ String   │ StringBuffer│ StringBuilder │
├────────────────────┼──────────┼─────────────┼───────────────┤
│ Mutable            │ No       │ Yes         │ Yes           │
│ Thread-safe        │ Yes      │ Yes         │ No            │
│ Synchronized       │ N/A      │ Yes         │ No            │
│ Performance        │ Slow     │ Slow        │ Fast          │
│ Version            │ 1.0      │ 1.0         │ 1.5           │
│ Storage            │ SCP/Heap │ Heap only   │ Heap only     │
│ Use case           │ Read-only│ Multi-thread│ Single-thread │
└────────────────────┴──────────┴─────────────┴───────────────┘
```

---

## 6. String Immutability Benefits

```
┌─────────────────────────────────────────────────────────────┐
│              WHY STRING IS IMMUTABLE?                          │
├─────────────────────────────────────────────────────────────┤
│ 1. Security     → Strings are used in network connections,    │
│                 file paths, class loading. Immutability     │
│                 prevents tampering.                           │
│ 2. Synchronization → Immutable objects are inherently       │
│                 thread-safe. No synchronization needed.       │
│ 3. Caching      → String pool reuses literals.              │
│ 4. HashCode     → HashCode can be cached (used in HashMap). │
│ 5. Class Loading → String used as class name; immutable    │
│                 prevents security issues.                     │
└─────────────────────────────────────────────────────────────┘
```

---

## 7. String Constant Pool

### Overview

- Special memory area in the **Heap** (Java 7+).
- Stores string literals.
- Reuses references when same literal appears.

### intern() Method

```java
String s1 = new String("hello");
String s2 = s1.intern(); // returns SCP reference
String s3 = "hello";

System.out.println(s2 == s3); // true
```

### == vs equals

```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");

System.out.println(s1 == s2);      // true (same SCP reference)
System.out.println(s1 == s3);      // false (different heap objects)
System.out.println(s1.equals(s3)); // true (same content)
```

---

## 8. Interview FAQs

### Q1. Why is String immutable in Java?

> For security, thread-safety, caching, hashcode caching, and class loading safety.

### Q2. What is the difference between `==` and `equals()` for String?

> `==` compares references (memory addresses).
> `equals()` compares content (character by character).

### Q3. What is the String Constant Pool?

> A special memory region in the heap that stores string literals. It reuses references to save memory.

### Q4. What does `intern()` do?

> Moves the string into the SCP if not already present and returns the SCP reference.

### Q5. Why is StringBuffer synchronized?

> To ensure thread safety when multiple threads modify the same buffer.

### Q6. When should we use StringBuilder over StringBuffer?

> In single-threaded environments where thread safety is not needed. StringBuilder is faster.

### Q7. How does String concatenation work internally?

> `+` operator compiles to `StringBuilder` (or `StringBuffer` in older versions) for efficiency.

### Q8. What is the difference between `replace(char, char)` and `replaceAll(String, String)`?

> `replace(char, char)` replaces all occurrences of a character.
> `replaceAll(String, String)` replaces all regex matches.

### Q9. What is the default capacity of StringBuffer/StringBuilder?

> 16 characters. It doubles when exceeded (old capacity * 2 + 2).

### Q10. How many objects are created by `String s = new String("hello");`?

> Two objects: one in the heap (by `new`) and one in the SCP (if "hello" is not already there).

### Q11. What happens when `String s = "hello" + " world";`?

> At compile time, the concatenation is optimized to a single literal: "hello world". So one object in SCP.

### Q12. What is the difference between `substring(int)` and `substring(int, int)`?

> `substring(int)` returns from start index to end.
> `substring(int, int)` returns from start (inclusive) to end (exclusive).

### Q13. What is the difference between `trim()` and `strip()`?

> `trim()` removes characters <= '\u0020'.
> `strip()` removes all Unicode whitespace (Java 11+).

### Q14. What is the difference between `isEmpty()` and `isBlank()`?

> `isEmpty()` returns true if length == 0.
> `isBlank()` returns true if length == 0 or contains only whitespace (Java 11+).

### Q15. Can we create a mutable String?

> No, String is immutable. Use StringBuilder or StringBuffer for mutability.

### Q16. What is the difference between `StringBuilder` and `StringBuffer`?

> StringBuilder is not synchronized and faster. StringBuffer is synchronized and thread-safe.

### Q17. What is the capacity formula when exceeded?

> newCapacity = (oldCapacity * 2) + 2

### Q18. What is the difference between `length()` and `capacity()`?

> `length()` returns the number of characters currently stored.
> `capacity()` returns the total allocated space.

### Q19. What is the significance of String being final?

> Prevents subclassing, which could break immutability and security contracts.

### Q20. How does `String.valueOf()` differ from `toString()`?

> `String.valueOf(null)` returns "null" string.
> `obj.toString()` on null throws NullPointerException.

---

## 9. Quick Reference

### String Creation

```java
String literal = "hello";       // SCP
String object = new String("hello"); // Heap
String interned = object.intern(); // SCP
```

### String Methods

```java
charAt, length, substring, toLowerCase, toUpperCase, trim
replace, contains, indexOf, lastIndexOf, startsWith, endsWith
equals, equalsIgnoreCase, split, concat, isEmpty, valueOf
compareTo, intern, toCharArray, getBytes, matches
```

### StringBuffer/StringBuilder

```java
append, insert, reverse, delete, deleteCharAt, replace
capacity, length, setCharAt, trimToSize, ensureCapacity
```

---

## 10. Key Takeaways

1. **String is immutable** — any modification creates a new object.
2. **StringBuffer** is thread-safe and mutable.
3. **StringBuilder** is faster but not thread-safe.
4. **String Constant Pool** saves memory by reusing literals.
5. **Use StringBuilder** for single-threaded concatenation.
6. **Use StringBuffer** for multi-threaded string manipulation.
7. **intern()** moves strings to SCP.
8. **== checks reference**, `equals()` checks content.
9. **Capacity** of StringBuffer/StringBuilder defaults to 16 and grows as `(old * 2) + 2`.
10. **Interview focus** is on immutability reasons, pool behavior, and performance comparison.

---

**Happy coding! 🚀**

*Master String internals, and you master Java interview basics.*
