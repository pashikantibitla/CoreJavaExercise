# 12 — Collections Framework

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
10. [Interview Questions](#10-interview-questions)
11. [Quick Reference](#11-quick-reference)
12. [Key Takeaways](#12-key-takeaways)

---

## 1. Introduction to Collections Framework

### What is Collections Framework?

The Java Collections Framework is a unified architecture for representing and manipulating collections, allowing them to be manipulated independently of implementation details.

```
┌──────────────────────────────────────────────────────────────┐
│                    COLLECTIONS FRAMEWORK OVERVIEW                │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │ Core Components:                                          │ │
│  │ 1. Interfaces — Abstract data types (List, Set, Map, etc) │ │
│  │ 2. Implementations — Concrete classes (ArrayList, HashSet)│ │
│  │ 3. Algorithms — Sorting, searching, etc. (Collections,   │ │
│  │    Arrays)                                                │ │
│  └─────────────────────────────────────────────────────────┘ │
│                                                                  │
│  Benefits:                                                       │
│  - Reduces programming effort                                    │
│  - Increases performance                                         │
│  - Provides interoperability between unrelated APIs              │
│  - Reduces effort to learn and use new APIs                      │
│  - Reduces effort to design and implement new APIs               │
│  - Fosters software reuse                                        │
└──────────────────────────────────────────────────────────────┘
```

### Hierarchy Diagram

```
┌──────────────────────────────────────────────────────────────┐
│                    COLLECTIONS HIERARCHY                         │
│                                                                  │
│                         Iterable                                 │
│                           │                                      │
│                     ┌─────┴─────┐                                │
│                     │           │                                │
│                   Collection   Map                               │
│                     │                                            │
│         ┌─────┬─────┴─────┬─────┐                                │
│         │     │           │     │                                │
│       List   Set       Queue  Deque                              │
│         │     │           │                                      │
│    ┌────┼────┐│      ┌────┴────┐                                 │
│    │    │    ││      │         │                                 │
│ ArrayList │ SortedSet │ PriorityQueue                              │
│ LinkedList│     │     │ BlockingQueue                             │
│ Vector   HashSet TreeSet    │                                    │
│ Stack  LinkedHashSet     ArrayBlockingQueue                      │
│                                                                  │
│                         Map                                      │
│                          │                                       │
│              ┌───────────┼───────────┐                           │
│              │           │           │                           │
│           HashMap    SortedMap   Hashtable                      │
│              │          │                                       │
│         LinkedHashMap TreeMap                                    │
│                           │                                      │
│                       NavigableMap                               │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. 9 Key Interfaces

### Overview of Key Interfaces

```
┌──────────────────────────────────────────────────────────────────────┐
│                    9 KEY INTERFACES                                   │
│  ┌──────────────┬────────────────────────────────────────────────────┐│
│  │ Interface    │ Description                                        ││
│  ├──────────────┼────────────────────────────────────────────────────┤│
│  │ Collection   │ Root interface for all collections (except Map)  ││
│  │ List         │ Ordered collection, allows duplicates, index-based ││
│  │ Set          │ Unordered collection, no duplicates                ││
│  │ SortedSet    │ Set that maintains elements in sorted order      ││
│  │ NavigableSet │ SortedSet with navigation methods                  ││
│  │ Queue        │ FIFO collection, designed for holding elements   ││
│  │              │ prior to processing                                ││
│  │ Map          │ Key-value pairs, no duplicate keys                 ││
│  │ SortedMap    │ Map that maintains keys in sorted order            ││
│  │ NavigableMap │ SortedMap with navigation methods                  ││
│  └──────────────┴────────────────────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────────────┘
```

### Collection Interface Methods

```java
import java.util.Collection;
import java.util.ArrayList;

public class CollectionInterfaceDemo {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        
        // Basic operations
        collection.add("Apple");           // Add element
        collection.add("Banana");
        collection.add("Cherry");
        
        System.out.println("Size: " + collection.size());         // 3
        System.out.println("Contains Apple? " + collection.contains("Apple"));  // true
        
        collection.remove("Banana");       // Remove element
        System.out.println("After remove: " + collection);
        
        // Bulk operations
        Collection<String> other = new ArrayList<>();
        other.add("Apple");
        other.add("Date");
        
        collection.retainAll(other);       // Keep only elements in other
        System.out.println("After retainAll: " + collection);
        
        collection.clear();                // Remove all elements
        System.out.println("Is empty? " + collection.isEmpty());  // true
    }
}
```

---

## 3. List Interface

### List Characteristics

```
┌──────────────────────────────────────────────────────────────┐
│                    LIST INTERFACE                                │
│                                                                  │
│  - Ordered collection (maintains insertion order)              │
│  - Allows duplicate elements                                     │
│  - Index-based access (0 to size-1)                            │
│  - Allows null elements                                        │
│                                                                  │
│  Implementations:                                                │
│  ┌────────────────┬─────────────────────────────────────────┐  │
│  │ Class          │ Characteristics                         │  │
│  ├────────────────┼─────────────────────────────────────────┤  │
│  │ ArrayList      │ Dynamic array, fast random access,      │  │
│  │                │ slow insertion/deletion in middle       │  │
│  │ LinkedList     │ Doubly linked list, fast              │  │
│  │                │ insertion/deletion, slow random access  │  │
│  │ Vector         │ Synchronized (legacy), dynamic array    │  │
│  │ Stack          │ LIFO, extends Vector (legacy)           │  │
│  └────────────────┴─────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### ArrayList

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        
        // Adding elements
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add(1, "Blueberry");  // Insert at index
        
        System.out.println("List: " + list);
        
        // Accessing elements
        System.out.println("First: " + list.get(0));
        System.out.println("Last: " + list.get(list.size() - 1));
        
        // Updating
        list.set(2, "Cranberry");
        System.out.println("After update: " + list);
        
        // Removing
        list.remove("Banana");        // By object
        list.remove(0);              // By index
        System.out.println("After remove: " + list);
        
        // Searching
        System.out.println("Index of Cherry: " + list.indexOf("Cherry"));
        System.out.println("Contains Apple? " + list.contains("Apple"));
        
        // Iteration
        for (String fruit : list) {
            System.out.println(fruit);
        }
        
        // Other methods
        System.out.println("Is empty? " + list.isEmpty());
        list.clear();
        System.out.println("Size after clear: " + list.size());
    }
}
```

### LinkedList

```java
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        
        // Adding at both ends
        list.addFirst("First");
        list.addLast("Last");
        list.add("Middle");
        
        System.out.println("LinkedList: " + list);
        
        // Accessing ends
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        
        // Removing from ends
        list.removeFirst();
        list.removeLast();
        System.out.println("After removes: " + list);
        
        // Queue operations (FIFO)
        list.offer("A");
        list.offer("B");
        System.out.println("Queue peek: " + list.peek());  // View first
        System.out.println("Queue poll: " + list.poll());  // Remove first
        System.out.println("After poll: " + list);
        
        // Stack operations (LIFO)
        list.push("X");  // addFirst
        list.push("Y");  // addFirst
        System.out.println("Stack pop: " + list.pop());   // removeFirst
        System.out.println("After pop: " + list);
    }
}
```

### Vector and Stack

```java
import java.util.Stack;
import java.util.Vector;

public class VectorStackDemo {
    public static void main(String[] args) {
        // Vector (legacy, synchronized)
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        System.out.println("Vector: " + vector);
        System.out.println("Capacity: " + vector.capacity());
        System.out.println("Element at 1: " + vector.elementAt(1));
        
        // Stack (extends Vector, LIFO)
        Stack<String> stack = new Stack<>();
        
        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        System.out.println("Stack: " + stack);
        
        // Peek (view top)
        System.out.println("Top: " + stack.peek());
        
        // Pop (remove top)
        System.out.println("Popped: " + stack.pop());
        System.out.println("After pop: " + stack);
        
        // Search
        System.out.println("Position of First: " + stack.search("First"));
        System.out.println("Is empty? " + stack.empty());
    }
}
```

### List Comparison Table

```
┌─────────────────────────────────────────────────────────────────────┐
│                    LIST IMPLEMENTATION COMPARISON                    │
│  ┌────────────┬────────────┬────────────┬────────────┬────────────┐  │
│  │ Feature    │ ArrayList  │ LinkedList │ Vector     │ Stack      │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Underlying │ Dynamic    │ Doubly     │ Dynamic    │ Vector     │  │
│  │ Structure  │ Array      │ Linked     │ Array      │ (LIFO)     │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Insert/    │ O(n)       │ O(1)       │ O(n)       │ O(1)       │  │
│  │ Delete     │ (middle)   │ (ends)     │ (middle)   │ (top)      │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Random     │ O(1)       │ O(n)       │ O(1)       │ O(1)       │  │
│  │ Access     │            │            │            │ (top)      │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Search     │ O(n)       │ O(n)       │ O(n)       │ O(n)       │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Thread-    │ No         │ No         │ Yes        │ Yes        │  │
│  │ Safe       │            │            │            │            │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Memory     │ Less       │ More       │ Less       │ Less       │  │
│  │ Overhead   │            │ (pointers) │            │            │  │
│  └────────────┴────────────┴────────────┴────────────┴────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 4. Set Interface

### Set Characteristics

```
┌──────────────────────────────────────────────────────────────┐
│                    SET INTERFACE                                 │
│                                                                  │
│  - Unordered collection (no index)                               │
│  - No duplicate elements                                         │
│  - Allows at most one null element (usually)                     │
│                                                                  │
│  Implementations:                                                │
│  ┌────────────────┬─────────────────────────────────────────┐  │
│  │ Class          │ Characteristics                         │  │
│  ├────────────────┼─────────────────────────────────────────┤  │
│  │ HashSet        │ Unordered, uses hash table, O(1)      │  │
│  │                │ operations, allows null                 │  │
│  │ LinkedHashSet  │ Maintains insertion order, hash table+  │  │
│  │                │ linked list, slightly slower than HashSet│  │
│  │ TreeSet        │ Sorted order (natural or custom),     │  │
│  │                │ Red-Black tree, O(log n), no null       │  │
│  └────────────────┴─────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### HashSet

```java
import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        
        // Adding elements
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");  // Duplicate — ignored
        
        System.out.println("Set: " + set);  // Order not guaranteed
        System.out.println("Size: " + set.size());  // 3
        
        // Checking membership
        System.out.println("Contains Banana? " + set.contains("Banana"));
        
        // Removing
        set.remove("Banana");
        System.out.println("After remove: " + set);
        
        // Iteration
        for (String fruit : set) {
            System.out.println(fruit);
        }
        
        // Null element
        set.add(null);
        System.out.println("With null: " + set);
        
        // Bulk operations
        Set<String> other = new HashSet<>();
        other.add("Apple");
        other.add("Date");
        
        set.retainAll(other);
        System.out.println("After retainAll: " + set);
    }
}
```

### LinkedHashSet

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        
        // Maintains insertion order
        set.add("Banana");
        set.add("Apple");
        set.add("Cherry");
        set.add("Date");
        
        System.out.println("LinkedHashSet: " + set);
        // Output: [Banana, Apple, Cherry, Date] — insertion order preserved
        
        // No duplicates
        set.add("Apple");
        System.out.println("After duplicate add: " + set);
        
        // Performance: slightly slower than HashSet but predictable iteration
        System.out.println("Size: " + set.size());
    }
}
```

### TreeSet

```java
import java.util.TreeSet;
import java.util.Set;

public class TreeSetDemo {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        
        // Automatically sorted (natural order)
        set.add("Banana");
        set.add("Apple");
        set.add("Cherry");
        set.add("Apricot");
        
        System.out.println("TreeSet (sorted): " + set);
        // Output: [Apple, Apricot, Banana, Cherry]
        
        // TreeSet specific methods
        TreeSet<String> treeSet = new TreeSet<>(set);
        
        System.out.println("First: " + treeSet.first());
        System.out.println("Last: " + treeSet.last());
        System.out.println("HeadSet (< 'Cherry'): " + treeSet.headSet("Cherry"));
        System.out.println("TailSet (>= 'Cherry'): " + treeSet.tailSet("Cherry"));
        System.out.println("SubSet: " + treeSet.subSet("Apple", "Cherry"));
        
        // No null allowed
        // treeSet.add(null);  // NullPointerException
    }
}
```

### Set Comparison Table

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SET IMPLEMENTATION COMPARISON                    │
│  ┌────────────┬────────────┬────────────┬────────────┐              │
│  │ Feature    │ HashSet    │ LinkedHashSet│ TreeSet  │              │
│  ├────────────┼────────────┼────────────┼────────────┤              │
│  │ Ordering   │ Unordered  │ Insertion  │ Sorted     │              │
│  ├────────────┼────────────┼────────────┼────────────┤              │
│  │ Null       │ 1 null     │ 1 null     │ No null    │              │
│  ├────────────┼────────────┼────────────┼────────────┤              │
│  │ Add/Remove │ O(1)       │ O(1)       │ O(log n)   │              │
│  ├────────────┼────────────┼────────────┼────────────┤              │
│  │ Contains   │ O(1)       │ O(1)       │ O(log n)   │              │
│  ├────────────┼────────────┼────────────┼────────────┤              │
│  │ Underlying │ Hash table │ Hash+Link  │ Red-Black  │              │
│  │ Structure  │            │ list       │ Tree       │              │
│  ├────────────┼────────────┼────────────┼────────────┤              │
│  │ Thread-safe│ No         │ No         │ No         │              │
│  └────────────┴────────────┴────────────┴────────────┘              │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. Map Interface

### Map Characteristics

```
┌──────────────────────────────────────────────────────────────┐
│                    MAP INTERFACE                                 │
│                                                                  │
│  - Key-value pairs (each key maps to exactly one value)         │
│  - Keys are unique (no duplicates)                              │
│  - Values can be duplicated                                     │
│  - Does NOT extend Collection interface                         │
│                                                                  │
│  Implementations:                                                │
│  ┌────────────────┬─────────────────────────────────────────┐  │
│  │ Class          │ Characteristics                         │  │
│  ├────────────────┼─────────────────────────────────────────┤  │
│  │ HashMap        │ Unordered, allows 1 null key,           │  │
│  │                │ multiple null values, O(1) operations   │  │
│  │ LinkedHashMap  │ Maintains insertion order or access     │  │
│  │                │ order, slightly slower than HashMap     │  │
│  │ TreeMap        │ Sorted by keys, Red-Black tree,         │  │
│  │                │ O(log n), no null key                   │  │
│  │ Hashtable      │ Legacy, synchronized, no null         │  │
│  │                │ key or values                           │  │
│  │ Properties     │ Extends Hashtable, String key-value,    │  │
│  │                │ used for configuration                  │  │
│  └────────────────┴─────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### HashMap

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        // Adding entries
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);
        map.put("Alice", 26);  // Overwrites existing value
        
        System.out.println("Map: " + map);
        
        // Accessing
        System.out.println("Alice's age: " + map.get("Alice"));
        System.out.println("Contains key 'Bob'? " + map.containsKey("Bob"));
        System.out.println("Contains value 30? " + map.containsValue(30));
        
        // Removing
        map.remove("Bob");
        System.out.println("After remove: " + map);
        
        // Iterating over keys
        System.out.println("Keys: " + map.keySet());
        
        // Iterating over values
        System.out.println("Values: " + map.values());
        
        // Iterating over entries
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Java 8+ forEach
        map.forEach((k, v) -> System.out.println(k + " is " + v + " years old"));
        
        // Size and clear
        System.out.println("Size: " + map.size());
        map.clear();
        System.out.println("Is empty? " + map.isEmpty());
    }
}
```

### LinkedHashMap

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // Insertion order preserved
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Zebra", 1);
        map.put("Apple", 2);
        map.put("Mango", 3);
        
        System.out.println("Insertion order: " + map);
        // Output: {Zebra=1, Apple=2, Mango=3}
        
        // Access order (LRU cache)
        Map<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("A", 1);
        accessOrderMap.put("B", 2);
        accessOrderMap.put("C", 3);
        
        accessOrderMap.get("A");  // Access A
        accessOrderMap.get("B");  // Access B
        
        System.out.println("Access order: " + accessOrderMap);
        // C is least recently accessed, A and B moved to end
    }
}
```

### TreeMap

```java
import java.util.TreeMap;
import java.util.Map;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        
        // Automatically sorted by key
        map.put("Banana", 2);
        map.put("Apple", 1);
        map.put("Cherry", 3);
        
        System.out.println("TreeMap (sorted by key): " + map);
        // Output: {Apple=1, Banana=2, Cherry=3}
        
        // TreeMap specific methods
        TreeMap<String, Integer> treeMap = new TreeMap<>(map);
        
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("HeadMap: " + treeMap.headMap("Cherry"));
        System.out.println("TailMap: " + treeMap.tailMap("Cherry"));
        
        // Reverse order
        TreeMap<String, Integer> descMap = new TreeMap<>((a, b) -> b.compareTo(a));
        descMap.putAll(map);
        System.out.println("Descending: " + descMap);
    }
}
```

### Hashtable

```java
import java.util.Hashtable;
import java.util.Map;

public class HashtableDemo {
    public static void main(String[] args) {
        // Synchronized, no null key or value
        Map<String, Integer> table = new Hashtable<>();
        
        table.put("One", 1);
        table.put("Two", 2);
        table.put("Three", 3);
        
        System.out.println("Hashtable: " + table);
        
        // Null not allowed
        // table.put(null, 4);      // NullPointerException
        // table.put("Four", null); // NullPointerException
        
        System.out.println("Size: " + table.size());
        System.out.println("Keys: " + table.keySet());
        
        // Enumeration (legacy way)
        java.util.Enumeration<String> keys = ((Hashtable<String, Integer>) table).keys();
        while (keys.hasMoreElements()) {
            System.out.println("Key: " + keys.nextElement());
        }
    }
}
```

### Properties

```java
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        
        // Set properties
        props.setProperty("db.url", "jdbc:mysql://localhost:3306/test");
        props.setProperty("db.username", "root");
        props.setProperty("db.password", "secret");
        
        // Get properties
        System.out.println("URL: " + props.getProperty("db.url"));
        System.out.println("Username: " + props.getProperty("db.username"));
        
        // Default value if key not found
        System.out.println("Port: " + props.getProperty("db.port", "3306"));
        
        // List all properties
        props.list(System.out);
        
        // Store and load (file operations)
        // props.store(new FileWriter("config.properties"), "Application Config");
        // props.load(new FileReader("config.properties"));
    }
}
```

### Map Comparison Table

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    MAP IMPLEMENTATION COMPARISON                           │
│  ┌────────────┬────────────┬────────────┬────────────┬────────────┐  │
│  │ Feature    │ HashMap    │LinkedHashMap│ TreeMap    │ Hashtable  │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Ordering   │ Unordered  │ Insertion/ │ Sorted by  │ Unordered  │  │
│  │            │            │ Access       │ key        │            │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Null key   │ 1 null     │ 1 null     │ No null    │ No null    │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Null value │ Yes        │ Yes        │ Yes        │ No null    │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Get/Put    │ O(1)       │ O(1)       │ O(log n)   │ O(1)       │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Thread-    │ No         │ No         │ No         │ Yes        │  │
│  │ Safe       │            │            │            │            │  │
│  ├────────────┼────────────┼────────────┼────────────┼────────────┤  │
│  │ Since      │ Java 1.2   │ Java 1.4   │ Java 1.2   │ Java 1.0   │  │
│  └────────────┴────────────┴────────────┴────────────┴────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 6. Queue Interface

### Queue Characteristics

```
┌──────────────────────────────────────────────────────────────┐
│                    QUEUE INTERFACE                               │
│                                                                  │
│  - Designed for holding elements prior to processing             │
│  - FIFO (First-In-First-Out) order by default                    │
│  - Two sets of methods: throwing vs returning special value      │
│                                                                  │
│  ┌────────────────┬─────────────┬─────────────────────────────┐   │
│  │ Operation      │ Throws      │ Returns Special Value     │   │
│  ├────────────────┼─────────────┼─────────────────────────────┤   │
│  │ Insert         │ add(e)      │ offer(e)                  │   │
│  │ Remove         │ remove()    │ poll()                    │   │
│  │ Examine        │ element()   │ peek()                    │   │
│  └────────────────┴─────────────┴─────────────────────────────┘   │
│                                                                  │
│  Implementations:                                                │
│  ┌────────────────┬─────────────────────────────────────────┐  │
│  │ Class          │ Characteristics                         │  │
│  ├────────────────┼─────────────────────────────────────────┤  │
│  │ PriorityQueue  │ Natural or custom ordering, heap-based, │  │
│  │                │ O(log n) for add/remove, no null       │  │
│  │ ArrayDeque     │ Resizable array, no null, faster than │  │
│  │                │ LinkedList, stack and queue operations  │  │
│  │ LinkedList     │ Also implements Queue/Deque           │  │
│  └────────────────┴─────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### PriorityQueue

```java
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // Min-heap by default (natural ordering)
        Queue<Integer> pq = new PriorityQueue<>();
        
        pq.offer(30);
        pq.offer(10);
        pq.offer(50);
        pq.offer(20);
        
        System.out.println("PriorityQueue: " + pq);
        // Not necessarily sorted, but head is smallest
        
        // Poll removes in priority order
        System.out.println("Poll: " + pq.poll());  // 10
        System.out.println("Poll: " + pq.poll());  // 20
        System.out.println("Poll: " + pq.poll());  // 30
        System.out.println("Poll: " + pq.poll());  // 50
        
        // Max-heap (reverse order)
        Queue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);
        maxPq.offer(30);
        maxPq.offer(10);
        maxPq.offer(50);
        
        System.out.println("Max Poll: " + maxPq.poll());  // 50
    }
}
```

### ArrayDeque

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        
        // Queue operations (FIFO)
        deque.offer("First");
        deque.offer("Second");
        deque.offer("Third");
        
        System.out.println("Queue peek: " + deque.peek());  // First
        System.out.println("Queue poll: " + deque.poll());   // First
        
        // Stack operations (LIFO)
        deque.push("A");
        deque.push("B");
        deque.push("C");
        
        System.out.println("Stack pop: " + deque.pop());   // C
        
        // Deque operations
        deque.addFirst("Front");
        deque.addLast("Back");
        
        System.out.println("First: " + deque.getFirst());
        System.out.println("Last: " + deque.getLast());
        System.out.println("Deque: " + deque);
    }
}
```

---

## 7. Comparator and Comparable

### Comparable

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 22));
        students.add(new Student("Bob", 20));
        students.add(new Student("Charlie", 25));
        
        System.out.println("Before sort: " + students);
        Collections.sort(students);
        System.out.println("After sort (by age): " + students);
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Student other) {
        return this.age - other.age;  // Sort by age ascending
    }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
```

### Comparator

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Student2> students = new ArrayList<>();
        students.add(new Student2("Alice", 22));
        students.add(new Student2("Bob", 20));
        students.add(new Student2("Charlie", 25));
        
        // Sort by name (Comparator)
        Collections.sort(students, new NameComparator());
        System.out.println("Sort by name: " + students);
        
        // Sort by age descending (Comparator)
        Collections.sort(students, new AgeDescendingComparator());
        System.out.println("Sort by age desc: " + students);
        
        // Lambda comparator (Java 8+)
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Lambda sort: " + students);
        
        // Comparator chaining
        students.sort(Comparator
            .comparing(Student2::getAge)
            .thenComparing(Student2::getName));
        System.out.println("Chained sort: " + students);
    }
}

class Student2 {
    private String name;
    private int age;
    
    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

class NameComparator implements Comparator<Student2> {
    @Override
    public int compare(Student2 s1, Student2 s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

class AgeDescendingComparator implements Comparator<Student2> {
    @Override
    public int compare(Student2 s1, Student2 s2) {
        return s2.getAge() - s1.getAge();
    }
}
```

### Comparable vs Comparator

```
┌─────────────────────────────────────────────────────────────────────┐
│                    COMPARABLE VS COMPARATOR                          │
│  ┌────────────────┬────────────────────┬────────────────────┐      │
│  │ Feature        │ Comparable         │ Comparator         │      │
│  ├────────────────┼────────────────────┼────────────────────┤      │
│  │ Package        │ java.lang          │ java.util          │      │
│  ├────────────────┼────────────────────┼────────────────────┤      │
│  │ Method         │ compareTo()        │ compare()          │      │
│  ├────────────────┼────────────────────┼────────────────────┤      │
│  │ Definition     │ In class itself    │ Separate class     │      │
│  ├────────────────┼────────────────────┼────────────────────┤      │
│  │ Sorting logic  │ Single (natural)   │ Multiple (custom)   │      │
│  ├────────────────┼────────────────────┼────────────────────┤      │
│  │ Use case       │ Default sorting    │ Custom sorting     │      │
│  ├────────────────┼────────────────────┼────────────────────┤      │
│  │ Java 8+        │ Can use            │ Lambda expressions   │      │
│  └────────────────┴────────────────────┴────────────────────┘      │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 8. Utility Classes

### Collections Utility Class

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsUtilityDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(10);
        list.add(50);
        list.add(20);
        list.add(40);
        
        // Sorting
        Collections.sort(list);
        System.out.println("Sorted: " + list);
        
        // Reverse
        Collections.reverse(list);
        System.out.println("Reversed: " + list);
        
        // Shuffle
        Collections.shuffle(list);
        System.out.println("Shuffled: " + list);
        
        // Binary search (requires sorted list)
        Collections.sort(list);
        int index = Collections.binarySearch(list, 30);
        System.out.println("Index of 30: " + index);
        
        // Max and Min
        System.out.println("Max: " + Collections.max(list));
        System.out.println("Min: " + Collections.min(list));
        
        // Frequency
        list.add(10);
        list.add(10);
        System.out.println("Frequency of 10: " + Collections.frequency(list, 10));
        
        // Fill
        Collections.fill(list, 0);
        System.out.println("After fill: " + list);
        
        // Unmodifiable list
        List<Integer> unmodifiable = Collections.unmodifiableList(list);
        // unmodifiable.add(1);  // UnsupportedOperationException
        
        // Singleton list
        List<String> single = Collections.singletonList("Only");
        System.out.println("Singleton: " + single);
    }
}
```

### Arrays Utility Class

```java
import java.util.Arrays;
import java.util.List;

public class ArraysUtilityDemo {
    public static void main(String[] args) {
        int[] arr = {30, 10, 50, 20, 40};
        
        // Sorting
        Arrays.sort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
        
        // Binary search
        int index = Arrays.binarySearch(arr, 30);
        System.out.println("Index of 30: " + index);
        
        // Fill
        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("Filled: " + Arrays.toString(filled));
        
        // Copy
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copy: " + Arrays.toString(copy));
        
        // Copy with range
        int[] range = Arrays.copyOfRange(arr, 1, 4);
        System.out.println("Range copy: " + Arrays.toString(range));
        
        // Equals
        System.out.println("Arrays equal? " + Arrays.equals(arr, copy));
        
        // Deep equals (for nested arrays)
        int[][] deep1 = {{1, 2}, {3, 4}};
        int[][] deep2 = {{1, 2}, {3, 4}};
        System.out.println("Deep equals? " + Arrays.deepEquals(deep1, deep2));
        
        // Convert to List
        String[] strArr = {"A", "B", "C"};
        List<String> list = Arrays.asList(strArr);
        System.out.println("As list: " + list);
        
        // Parallel sort (Java 8+)
        int[] large = {5, 2, 8, 1, 9};
        Arrays.parallelSort(large);
        System.out.println("Parallel sorted: " + Arrays.toString(large));
    }
}
```

---

## 9. Searching and Sorting

### Collections.sort()

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsSortDemo {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Cherry");
        
        // Natural order
        Collections.sort(fruits);
        System.out.println("Natural order: " + fruits);
        
        // Reverse order
        Collections.sort(fruits, Collections.reverseOrder());
        System.out.println("Reverse order: " + fruits);
        
        // Custom comparator
        Collections.sort(fruits, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("By length: " + fruits);
        
        // List.sort() (Java 8+)
        fruits.sort(Comparator.naturalOrder());
        System.out.println("Using List.sort(): " + fruits);
    }
}
```

### Collections.binarySearch()

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        
        // List must be sorted
        Collections.sort(list);
        
        int target = 30;
        int index = Collections.binarySearch(list, target);
        System.out.println("Index of " + target + ": " + index);
        
        // Not found
        target = 35;
        index = Collections.binarySearch(list, target);
        System.out.println("Index of " + target + ": " + index);
        // Returns (-insertionPoint - 1)
        System.out.println("Would be inserted at: " + (-index - 1));
        
        // With comparator
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        
        int idx = Collections.binarySearch(words, "banana", String::compareTo);
        System.out.println("Index of banana: " + idx);
    }
}
```

### Arrays.sort()

```java
import java.util.Arrays;

public class ArraysSortDemo {
    public static void main(String[] args) {
        // Primitive array
        int[] numbers = {30, 10, 50, 20, 40};
        Arrays.sort(numbers);
        System.out.println("Sorted ints: " + Arrays.toString(numbers));
        
        // Object array
        String[] words = {"banana", "apple", "cherry"};
        Arrays.sort(words);
        System.out.println("Sorted strings: " + Arrays.toString(words));
        
        // Custom object array
        Employee[] employees = {
            new Employee("Bob", 50000),
            new Employee("Alice", 60000),
            new Employee("Charlie", 45000)
        };
        
        Arrays.sort(employees);
        System.out.println("Sorted by salary: " + Arrays.toString(employees));
        
        // Partial sort
        int[] partial = {5, 2, 8, 1, 9, 3};
        Arrays.sort(partial, 1, 4);
        System.out.println("Partial sort (1 to 4): " + Arrays.toString(partial));
    }
}

class Employee implements Comparable<Employee> {
    private String name;
    private int salary;
    
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public int compareTo(Employee other) {
        return this.salary - other.salary;
    }
    
    @Override
    public String toString() {
        return name + "($" + salary + ")";
    }
}
```

---

## 10. Interview Questions

### Q1. What is the difference between ArrayList and LinkedList?

**Answer:**
- **ArrayList**: Uses dynamic array. Fast random access (O(1)), slow insertion/deletion in middle (O(n)). Better for frequent access.
- **LinkedList**: Uses doubly linked list. Fast insertion/deletion at ends (O(1)), slow random access (O(n)). Better for frequent modifications.

### Q2. What is the difference between HashSet and TreeSet?

**Answer:**
- **HashSet**: Unordered, uses hash table, O(1) operations, allows one null.
- **TreeSet**: Sorted order, uses Red-Black tree, O(log n), no null elements.

### Q3. What is the difference between HashMap and Hashtable?

**Answer:**
| Feature | HashMap | Hashtable |
|---------|---------|-----------|
| Thread-safe | No | Yes |
| Null key | 1 | No |
| Null value | Yes | No |
| Performance | Faster | Slower |
| Introduced | Java 1.2 | Java 1.0 |

### Q4. What is the difference between Comparable and Comparator?

**Answer:**
- **Comparable**: Single sorting sequence, `compareTo()`, defined in the class itself.
- **Comparator**: Multiple sorting sequences, `compare()`, defined in separate class.

### Q5. Why does Map not extend Collection?

**Answer:**
Map stores key-value pairs, not individual elements. The Collection interface is designed for single-element collections. Map has its own hierarchy with methods like `put()`, `get()`, `keySet()`, `values()` that don't fit the Collection interface contract.

### Q6. What is the difference between List and Set?

**Answer:**
- **List**: Ordered, allows duplicates, index-based access.
- **Set**: Unordered, no duplicates, no index.

### Q7. How does HashMap work internally?

**Answer:**
HashMap uses an array of buckets (Node<K,V>[]). Each bucket is a linked list (or tree for Java 8+). Hash of key determines bucket index. In case of collision (same hash), entries are chained. Java 8+ converts long chains to Red-Black trees for O(log n) lookup.

### Q8. What is the load factor in HashMap?

**Answer:**
Default load factor is 0.75. It determines when to resize the hash table. When size > capacity * loadFactor, the table is doubled and entries are rehashed.

### Q9. What is the difference between Iterator and ListIterator?

**Answer:**
- **Iterator**: Forward traversal only, available for all collections.
- **ListIterator**: Bidirectional traversal, only for List implementations. Can also modify list during iteration.

### Q10. What is ConcurrentModificationException?

**Answer:**
Thrown when a collection is modified concurrently during iteration (except through the iterator's own methods). Solutions: use `Iterator.remove()`, `CopyOnWriteArrayList`, or Java 8+ `removeIf()`.

### Q11. What is the difference between poll() and remove() in Queue?

**Answer:**
- **poll()**: Returns null if queue is empty.
- **remove()**: Throws NoSuchElementException if queue is empty.

### Q12. How to make a collection synchronized?

**Answer:**
```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
```
Or use concurrent collections from `java.util.concurrent` package.

### Q13. What is the difference between Arrays.sort() and Collections.sort()?

**Answer:**
- **Arrays.sort()**: For arrays (primitive and object).
- **Collections.sort()**: For List implementations. Internally uses `List.sort()` which uses TimSort (hybrid of merge sort and insertion sort).

### Q14. What is the difference between peek() and element() in Queue?

**Answer:**
- **peek()**: Returns null if empty.
- **element()**: Throws NoSuchElementException if empty.

### Q15. What is the difference between HashMap and LinkedHashMap?

**Answer:**
- **HashMap**: Unordered, no guaranteed iteration order.
- **LinkedHashMap**: Maintains insertion order (or access order with `true` parameter). Slightly slower than HashMap.

### Q16. What is the difference between fail-fast and fail-safe iterators?

**Answer:**
- **Fail-fast**: Throw ConcurrentModificationException immediately (ArrayList, HashMap).
- **Fail-safe**: Work on a copy of the collection, don't throw exception (CopyOnWriteArrayList, ConcurrentHashMap).

### Q17. What is the difference between Collection and Collections?

**Answer:**
- **Collection**: Interface (root of collection hierarchy).
- **Collections**: Utility class with static methods for collection operations (sort, search, reverse, etc.).

### Q18. What is the difference between Stack and Queue?

**Answer:**
- **Stack**: LIFO (Last-In-First-Out). Operations: push, pop, peek.
- **Queue**: FIFO (First-In-First-Out). Operations: offer, poll, peek.

### Q19. What is the difference between Deque and Queue?

**Answer:**
- **Queue**: Single-ended, FIFO.
- **Deque**: Double-ended, supports insertion and removal at both ends. Can be used as both Stack and Queue.

### Q20. How to sort a Map by values?

**Answer:**
```java
Map<String, Integer> map = new HashMap<>();
// ... populate map

map.entrySet()
    .stream()
    .sorted(Map.Entry.comparingByValue())
    .forEach(System.out::println);
```

---

## 11. Quick Reference

```
┌──────────────────────────────────────────────────────────────┐
│                    COLLECTIONS QUICK REFERENCE                   │
│                                                                  │
│  LIST: Ordered, duplicates allowed, index-based                  │
│  ┌────────────┬────────────────────────────────────────────┐  │
│  │ ArrayList  │ Fast access, slow middle insert/delete     │  │
│  │ LinkedList │ Fast insert/delete, slow access            │  │
│  │ Vector     │ Synchronized (legacy)                      │  │
│  │ Stack      │ LIFO (extends Vector)                      │  │
│  └────────────┴────────────────────────────────────────────┘  │
│                                                                  │
│  SET: Unordered, no duplicates                                     │
│  ┌────────────┬────────────────────────────────────────────┐  │
│  │ HashSet    │ Unordered, O(1), 1 null                   │  │
│  │ LinkedHashSet│ Insertion order, O(1)                    │  │
│  │ TreeSet    │ Sorted, O(log n), no null                 │  │
│  └────────────┴────────────────────────────────────────────┘  │
│                                                                  │
│  MAP: Key-value pairs, unique keys                               │
│  ┌────────────┬────────────────────────────────────────────┐  │
│  │ HashMap    │ Unordered, 1 null key, O(1)               │  │
│  │ LinkedHashMap│ Insertion/access order                    │  │
│  │ TreeMap    │ Sorted keys, O(log n), no null key        │  │
│  │ Hashtable  │ Synchronized, no null                     │  │
│  └────────────┴────────────────────────────────────────────┘  │
│                                                                  │
│  QUEUE: FIFO, prior to processing                                │
│  ┌────────────┬────────────────────────────────────────────┐  │
│  │ PriorityQueue│ Ordered by priority, heap-based          │  │
│  │ ArrayDeque   │ Resizable array, no null, fast           │  │
│  │ LinkedList   │ Also implements Queue                    │  │
│  └────────────┴────────────────────────────────────────────┘  │
│                                                                  │
│  SORTING:                                                          │
│  - Comparable: Natural order (compareTo)                         │
│  - Comparator: Custom order (compare)                            │
│  - Collections.sort(list) — for Lists                            │
│  - Arrays.sort(array) — for arrays                                 │
└──────────────────────────────────────────────────────────────┘
```

---

## 12. Key Takeaways

1. **Collections Framework** provides unified architecture for storing and manipulating groups of objects.
2. **List** maintains order and allows duplicates. Choose ArrayList for access, LinkedList for frequent insert/delete.
3. **Set** does not allow duplicates. HashSet for speed, TreeSet for sorted order, LinkedHashSet for insertion order.
4. **Map** stores key-value pairs. HashMap for general use, TreeMap for sorted keys, LinkedHashMap for predictable order.
5. **Queue** is designed for holding elements prior to processing. PriorityQueue for priority ordering, ArrayDeque for general queue/stack operations.
6. **Comparable** provides natural ordering; **Comparator** provides custom/multiple orderings.
7. **Collections** utility class provides sorting, searching, reversing, shuffling, etc.
8. **Arrays** utility class provides sorting, searching, copying, filling for arrays.
9. **Thread safety**: Vector, Hashtable, Stack are synchronized. Use `Collections.synchronizedXxx()` or `java.util.concurrent` for modern concurrent collections.
10. **Fail-fast iterators** detect concurrent modification; **fail-safe** iterators work on copies.
11. **HashMap internals**: Array of buckets with linked list/tree chaining for collision resolution.
12. **Java 8+ enhancements**: Lambda comparators, `forEach`, `removeIf`, `compute`, `merge`, `replaceAll`.

---

**Happy coding!**

*Master Collections, and you unlock efficient data manipulation in Java.*
