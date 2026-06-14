# Chapter 13 — Concurrent Collections

> **Topics:** Need for Concurrent Collections, ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteArraySet, Thread-safe Collections

---

## Table of Contents

1. [Need for Concurrent Collections](#1-need-for-concurrent-collections)
2. [ConcurrentHashMap](#2-concurrenthashmap)
3. [CopyOnWriteArrayList](#3-copyonwritearraylist)
4. [CopyOnWriteArraySet](#4-copyonwritearrayset)
5. [ConcurrentMap Interface](#5-concurrentmap-interface)
6. [Interview Questions](#6-interview-questions)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. Need for Concurrent Collections

### Why Concurrent Collections?

**File:** `13_Concurrent_Collections.md`  
**Topics:** Thread-safe alternatives to traditional collections

- Traditional collections (`ArrayList`, `HashMap`, `HashSet`) are NOT thread-safe.
- Concurrent collections provide thread-safe operations without coarse-grained locking.
- Better performance in multi-threaded environments.

---

## 2. ConcurrentHashMap

**File:** `13_Concurrent_Collections.md`  
**Topics:** Java 7 segment locking, Java 8+ CAS + synchronized on first node

- Thread-safe `HashMap` implementation.
- Multiple threads can read concurrently.
- Multiple threads can write to different buckets concurrently.
- `computeIfAbsent`, `compute`, `merge` atomic methods.

---

## 3. CopyOnWriteArrayList

**File:** `13_Concurrent_Collections.md`  
**Topics:** Snapshot iterators, write-heavy operations

- Thread-safe `ArrayList` implementation.
- Creates a new copy on every write operation.
- Iterators are snapshot-based and never throw `ConcurrentModificationException`.
- Best for read-heavy, write-rare scenarios.

---

## 4. CopyOnWriteArraySet

**File:** `13_Concurrent_Collections.md`  
**Topics:** Thread-safe Set, backed by `CopyOnWriteArrayList`

- Thread-safe `Set` implementation.
- Uses `CopyOnWriteArrayList` internally.
- No duplicates, snapshot iterators.

---

## 5. ConcurrentMap Interface

**File:** `13_Concurrent_Collections.md`  
**Topics:** Additional atomic methods beyond `Map`

- `putIfAbsent`, `remove(key, value)`, `replace(key, oldValue, newValue)`, `compute`, `merge`, `computeIfAbsent`, `computeIfPresent`.

---

## 📁 Additional Files

- **13_Concurrent_Collections.md** — Comprehensive theory, code snippets, and interview FAQs
- **java_programs/** — Individual `.java` files demonstrating concurrent collections

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Need for Concurrent Collections ──→ Why traditional collections fail in multi-threading
    │
    ├── ConcurrentHashMap ──→ Thread-safe HashMap, segment/bucket locking, methods
    │
    ├── CopyOnWriteArrayList ──→ Snapshot iterators, write-copy behavior, read-heavy use
    │
    ├── CopyOnWriteArraySet ──→ Thread-safe Set, internal workings
    │
    └── ConcurrentMap Interface ──→ Atomic methods, interview FAQs
```

---

## 📋 Key Takeaways

1. **ConcurrentHashMap** is the preferred thread-safe `Map` implementation.
2. **CopyOnWriteArrayList** is ideal for read-heavy scenarios with few writes.
3. **CopyOnWriteArraySet** provides thread-safe set semantics with snapshot iterators.
4. **ConcurrentMap** provides atomic compound operations absent in `Map`.
5. **Traditional collections** (`ArrayList`, `HashMap`) are NOT thread-safe.
6. **Synchronized wrappers** (`Collections.synchronizedList`, `synchronizedMap`) use coarse-grained locks and are slower.
7. **Hashtable** is thread-safe but uses coarse-grained synchronization; prefer `ConcurrentHashMap`.
8. **Iterators** of concurrent collections are generally fail-safe or snapshot-based.
9. **Concurrent collections** are designed for high concurrency and scalability.
10. **Choose the right collection** based on read/write ratio and thread safety requirements.

---

**Happy coding! 🚀**

*Concurrent collections are the backbone of high-performance multi-threaded Java applications.*
