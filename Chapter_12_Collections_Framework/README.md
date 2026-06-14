# Chapter 12 — Collections Framework

> **Topics:** 9 Key Interfaces, List, Set, Map, Queue, Comparator, Comparable, Utility Classes, Searching, Sorting

---

## Table of Contents

1. [Introduction to Collections Framework](#1-introduction-to-collections-framework)
2. [9 Key Interfaces](#2-9-key-interfaces)
3. [List Interface](#3-list-interface)
4. [Set Interface](#4-set-interface)
5. [Map Interface](#5-map-interface)
6. [Queue Interface](#6-queue-interface)
7. [Comparator and Comparable](#7-comparator-and-comparable)
8. [Utility Classes](#8-utility-classes)
9. [Searching and Sorting](#9-searching-and-sorting)
10. [Interview FAQs](#10-interview-faqs)
11. [Java Programs](#11-java-programs)

---

## 1. Introduction to Collections Framework

**File:** `12_Collections_Framework.md`

- Unified architecture for collections
- Interfaces, implementations, and algorithms
- Benefits and hierarchy

---

## 2. 9 Key Interfaces

**File:** `12_Collections_Framework.md`

- Collection, List, Set, SortedSet, NavigableSet
- Queue, Map, SortedMap, NavigableMap
- Methods and characteristics

---

## 3. List Interface

**File:** `12_Collections_Framework.md`

- ArrayList, LinkedList, Vector, Stack
- Operations, comparison, use cases

---

## 4. Set Interface

**File:** `12_Collections_Framework.md`

- HashSet, LinkedHashSet, TreeSet
- Operations, comparison, use cases

---

## 5. Map Interface

**File:** `12_Collections_Framework.md`

- HashMap, LinkedHashMap, TreeMap, Hashtable, Properties
- Operations, comparison, use cases

---

## 6. Queue Interface

**File:** `12_Collections_Framework.md`

- PriorityQueue, ArrayDeque, LinkedList
- FIFO, LIFO, priority ordering

---

## 7. Comparator and Comparable

**File:** `12_Collections_Framework.md`

- Comparable (natural ordering)
- Comparator (custom ordering)
- Lambda comparators, chaining

---

## 8. Utility Classes

**File:** `12_Collections_Framework.md`

- Collections class (sort, reverse, shuffle, binarySearch)
- Arrays class (sort, binarySearch, copy, fill)

---

## 9. Searching and Sorting

**File:** `12_Collections_Framework.md`

- Collections.sort() and List.sort()
- Collections.binarySearch()
- Arrays.sort()

---

## 10. Interview FAQs

**File:** `12_Collections_Framework.md`

- 20+ interview questions covering:
  - List vs Set vs Map
  - Internal working of HashMap
  - Comparable vs Comparator
  - Thread-safe collections
  - Fail-fast vs fail-safe
  - Sorting and searching algorithms

---

## 11. Java Programs

**Directory:** `java_programs/`

| # | File | Concept |
|---|------|---------|
| 1 | `ArrayListOperations.java` | ArrayList CRUD operations |
| 2 | `LinkedListOperations.java` | LinkedList operations |
| 3 | `HashSetOperations.java` | HashSet operations |
| 4 | `TreeSetComparator.java` | TreeSet with custom Comparator |
| 5 | `HashMapOperations.java` | HashMap operations |
| 6 | `TreeMapOperations.java` | TreeMap operations |
| 7 | `LinkedHashMapOperations.java` | LinkedHashMap operations |
| 8 | `HashtableOperations.java` | Hashtable operations |
| 9 | `PriorityQueueDemo.java` | PriorityQueue operations |
| 10 | `CollectionsSortComparator.java` | Collections.sort() with Comparator |
| 11 | `CollectionsBinarySearch.java` | Collections.binarySearch() |
| 12 | `ArraysSortDemo.java` | Arrays.sort() |
| 13 | `ComparableDemo.java` | Comparable implementation |
| 14 | `ComparatorLambdaDemo.java` | Comparator with lambdas |
| 15 | `MapSortingByValue.java` | Interview: Sort map by value |
| 16 | `ListIteratorDemo.java` | ListIterator operations |
| 17 | `FrequencyCount.java` | Interview: Frequency count using Map |
| 18 | `RemoveDuplicates.java` | Interview: Remove duplicates from list |
| 19 | `FirstNonRepeating.java` | Interview: First non-repeating character |
| 20 | `CollectionsUtilityDemo.java` | Collections utility methods |

---

## 📁 Additional Files

- **12_Collections_Framework.md** — Comprehensive theory with examples and diagrams
- **java_programs/** — Individual .java files for each concept

---

## 🎯 Learning Path

```
Start Here
    │
    ├── 9 Key Interfaces ──→ Understand the hierarchy
    │
    ├── List ──→ ArrayList, LinkedList, Vector, Stack
    │
    ├── Set ──→ HashSet, LinkedHashSet, TreeSet
    │
    ├── Map ──→ HashMap, LinkedHashMap, TreeMap, Hashtable
    │
    ├── Queue ──→ PriorityQueue, ArrayDeque
    │
    ├── Sorting ──→ Comparable, Comparator
    │
    ├── Utilities ──→ Collections, Arrays
    │
    └── Interview Problems ──→ Real-world problems
```

---

## 📋 Key Takeaways

1. **List** — Ordered, allows duplicates. ArrayList for access, LinkedList for modifications.
2. **Set** — No duplicates. HashSet for speed, TreeSet for sorted, LinkedHashSet for insertion order.
3. **Map** — Key-value pairs. HashMap for general use, TreeMap for sorted keys.
4. **Queue** — FIFO processing. PriorityQueue for priority, ArrayDeque for general use.
5. **Comparable** — Natural ordering (compareTo). **Comparator** — Custom ordering (compare).
6. **Collections** utility — sort, reverse, shuffle, binarySearch, max, min.
7. **Arrays** utility — sort, binarySearch, copy, fill, toString, equals.
8. **HashMap internals** — Array of buckets with linked list/tree chaining.
9. **Thread safety** — Vector, Hashtable are synchronized. Prefer concurrent collections.
10. **Interview focus** — HashMap working, fail-fast vs fail-safe, comparable vs comparator.

---

**Happy coding!**

*Master Collections, and you unlock efficient data manipulation in Java.*
