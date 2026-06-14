# 13 — Concurrent Collections

> **Topics:** Need for Concurrent Collections, ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentMap Interface, Interview FAQs

---

## Table of Contents

1. [Need for Concurrent Collections](#1-need-for-concurrent-collections)
2. [Traditional vs Concurrent Collections](#2-traditional-vs-concurrent-collections)
3. [ConcurrentMap Interface](#3-concurrentmap-interface)
4. [ConcurrentHashMap](#4-concurrenthashmap)
5. [HashMap vs ConcurrentHashMap vs synchronizedMap vs Hashtable](#5-hashmap-vs-concurrenthashmap-vs-synchronizedmap-vs-hashtable)
6. [CopyOnWriteArrayList](#6-copyonwritearraylist)
7. [ArrayList vs CopyOnWriteArrayList vs synchronizedList vs Vector](#7-arraylist-vs-copyonwritearraylist-vs-synchronizedlist-vs-vector)
8. [CopyOnWriteArraySet](#8-copyonwritearrayset)
9. [CopyOnWriteArraySet vs synchronizedSet](#9-copyonwritearrayset-vs-synchronizedset)
10. [Interview Questions](#10-interview-questions)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Need for Concurrent Collections

### What is the Problem with Traditional Collections?

```java
import java.util.*;

public class ProblemDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Thread 1: Iterate
        Thread t1 = new Thread(() -> {
            for (String s : list) {
                System.out.println("Reading: " + s);
                try { Thread.sleep(10); } catch (Exception e) { }
            }
        });
        
        // Thread 2: Modify
        Thread t2 = new Thread(() -> {
            list.add("D");  // ConcurrentModificationException!
        });
        
        t1.start();
        t2.start();
    }
}

// Output: ConcurrentModificationException
```

### Why Not Synchronized Wrappers?

```java
// Collections.synchronizedList() — coarse-grained lock
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Problems:
// 1. Entire list is locked for every operation
// 2. Iterators are NOT thread-safe (must manually synchronize)
// 3. Poor performance under high concurrency
// 4. Only one thread can read/write at a time
```

### Need for Concurrent Collections

```
┌─────────────────────────────────────────────────────────────────────┐
│                    NEED FOR CONCURRENT COLLECTIONS                    │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Traditional Collections                                   │   │
│  │    - NOT thread-safe                                         │   │
│  │    - ConcurrentModificationException during iteration        │   │
│  │                                                             │   │
│  │ 2. Synchronized Wrappers (Collections.synchronizedXxx)        │   │
│  │    - Thread-safe but coarse-grained locking                │   │
│  │    - Only one thread can access at a time                   │   │
│  │    - Iterators still fail-fast                             │   │
│  │                                                             │   │
│  │ 3. Concurrent Collections                                  │   │
│  │    - Thread-safe with fine-grained locking                  │   │
│  │    - Multiple threads can read/write concurrently           │   │
│  │    - Better performance and scalability                     │   │
│  │    - Iterators are fail-safe or snapshot-based             │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Traditional vs Concurrent Collections

### Difference Between Traditional and Concurrent Collections

| Feature | Traditional Collections | Concurrent Collections |
|---------|------------------------|------------------------|
| Thread-safe | No | Yes |
| Locking | No locking | Fine-grained locking |
| Iterators | Fail-fast | Fail-safe / Snapshot |
| ConcurrentModificationException | Yes | No |
| Performance (single-thread) | Better | Slightly overhead |
| Performance (multi-thread) | Poor | Better |
| Null support | Varies | Varies (CHM allows nulls? No) |

### Examples

```java
// Traditional (NOT thread-safe)
List<String> arrayList = new ArrayList<>();
Map<String, Integer> hashMap = new HashMap<>();
Set<String> hashSet = new HashSet<>();

// Concurrent (thread-safe)
List<String> cowList = new CopyOnWriteArrayList<>();
Map<String, Integer> chm = new ConcurrentHashMap<>();
Set<String> cowSet = new CopyOnWriteArraySet<>();
```

---

## 3. ConcurrentMap Interface

### What is ConcurrentMap?

```java
// ConcurrentMap extends Map
// Provides additional atomic operations

public interface ConcurrentMap<K, V> extends Map<K, V> {
    // Atomic put-if-absent
    V putIfAbsent(K key, V value);
    
    // Atomic remove only if value matches
    boolean remove(Object key, Object value);
    
    // Atomic replace only if value matches
    boolean replace(K key, V oldValue, V newValue);
    
    // Atomic replace (always if key exists)
    V replace(K key, V value);
    
    // Java 8+ default methods
    V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);
    V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);
    V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);
    V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);
}
```

### Methods with Examples

```java
import java.util.concurrent.*;

public class ConcurrentMapMethodsDemo {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // 1. putIfAbsent
        map.putIfAbsent("A", 1);  // Puts (A,1)
        map.putIfAbsent("A", 2);  // Ignored, returns 1
        
        // 2. remove(key, value)
        map.remove("A", 2);       // false (value is 1)
        map.remove("A", 1);       // true (removed)
        
        // 3. replace(key, oldValue, newValue)
        map.put("B", 10);
        map.replace("B", 10, 20); // true (replaced)
        map.replace("B", 10, 30); // false (already 20)
        
        // 4. replace(key, value)
        map.replace("B", 100);    // Always replaces if key exists
        
        // 5. computeIfAbsent
        map.computeIfAbsent("C", k -> k.length());  // Puts (C,1)
        
        // 6. computeIfPresent
        map.computeIfPresent("C", (k, v) -> v + 10);  // C becomes 11
        
        // 7. compute
        map.compute("C", (k, v) -> v == null ? 0 : v * 2);  // C becomes 22
        
        // 8. merge
        map.merge("D", 1, Integer::sum);  // Puts (D,1)
        map.merge("D", 5, Integer::sum);  // D becomes 6
        
        System.out.println(map);
    }
}
```

---

## 4. ConcurrentHashMap

### What is ConcurrentHashMap?

```java
// Thread-safe HashMap implementation
// Fine-grained locking (bucket-level in Java 8+)
// No ConcurrentModificationException during iteration

import java.util.concurrent.*;

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
```

### Internal Structure

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CONCURRENTHASHMAP INTERNALS                         │
│                                                                      │
│  Java 7: Segment-based locking                                       │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  [ Segment 0 ] [ Segment 1 ] [ Segment 2 ] [ Segment 3 ]     │   │
│  │   ├── Bucket 0   ├── Bucket 0   ├── Bucket 0   ├── Bucket 0   │   │
│  │   ├── Bucket 1   ├── Bucket 1   ├── Bucket 1   ├── Bucket 1   │   │
│  │   └── ...        └── ...        └── ...        └── ...        │   │
│  │   Each segment has its own ReentrantLock                      │   │
│  │   Default: 16 segments, concurrency level = 16               │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  Java 8+: CAS + synchronized on first node                           │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  [ Bucket 0 ] [ Bucket 1 ] [ Bucket 2 ] [ Bucket 3 ] ...      │   │
│  │  ├── Node 0     ├── Node 1     ├── Node 2     ├── Node 3      │   │
│  │  │   next       │   next       │   next       │   next        │   │
│  │  └── Node       └── Node       └── Node       └── Node        │   │
│  │                                                              │   │
│  │  - Lock on first node of bucket (synchronized)               │   │
│  │  - CAS for head updates (no lock)                            │   │
│  │  - Tree bins for large chains (Red-Black tree)              │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Constructors

```java
// 1. Default
ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap<>();

// 2. Initial capacity
ConcurrentHashMap<String, Integer> map2 = new ConcurrentHashMap<>(32);

// 3. Capacity + load factor
ConcurrentHashMap<String, Integer> map3 = new ConcurrentHashMap<>(32, 0.75f);

// 4. Capacity + load factor + concurrency level
ConcurrentHashMap<String, Integer> map4 = new ConcurrentHashMap<>(32, 0.75f, 16);

// 5. From another map
ConcurrentHashMap<String, Integer> map5 = new ConcurrentHashMap<>(Map.of("A", 1, "B", 2));
```

### Basic Operations

```java
import java.util.concurrent.*;

public class ConcurrentHashMapBasic {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // put, get, remove
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.get("A"));        // 1
        System.out.println(map.remove("B"));     // 2
        
        // putIfAbsent
        map.putIfAbsent("A", 100);  // Ignored (A already exists)
        System.out.println(map.get("A"));  // 1
        
        // containsKey, containsValue
        System.out.println(map.containsKey("A"));  // true
        System.out.println(map.containsValue(1));  // true
        
        // size, isEmpty
        System.out.println(map.size());   // 1
        System.out.println(map.isEmpty()); // false
        
        // forEach
        map.put("C", 3);
        map.forEach((k, v) -> System.out.println(k + "=" + v));
        
        // keySet, values, entrySet
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());
    }
}
```

### Thread-Safety Demo

```java
import java.util.concurrent.*;

public class ConcurrentHashMapThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // Multiple threads writing concurrently
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 100; i++) {
            final int value = i;
            executor.submit(() -> {
                map.put("Key" + value, value);
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        
        System.out.println("Map size: " + map.size());  // 100
        
        // Iterator does NOT throw ConcurrentModificationException
        for (String key : map.keySet()) {
            if (key.equals("Key50")) {
                map.put("NewKey", 999);  // No exception!
            }
        }
        
        System.out.println("Map size after iteration: " + map.size());  // 101
    }
}
```

### ConcurrentHashMap with Atomic Methods

```java
import java.util.concurrent.*;

public class ConcurrentHashMapAtomic {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();
        
        // Atomic computeIfAbsent
        counter.computeIfAbsent("visits", k -> 0);
        counter.compute("visits", (k, v) -> v + 1);
        System.out.println(counter);  // {visits=1}
        
        // Atomic merge
        counter.merge("visits", 5, Integer::sum);
        System.out.println(counter);  // {visits=6}
        
        // Atomic replace
        boolean replaced = counter.replace("visits", 6, 10);
        System.out.println("Replaced: " + replaced);  // true
        System.out.println(counter);  // {visits=10}
    }
}
```

### ConcurrentHashMap Iterators

```java
import java.util.concurrent.*;
import java.util.*;

public class ConcurrentHashMapIterator {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        // Iterator is weakly consistent (does not throw CME)
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
            map.put("D", 4);  // No ConcurrentModificationException!
        }
        
        // forEach (Java 8+)
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
        
        // search
        String result = map.search(1, (k, v) -> v > 2 ? k : null);
        System.out.println("First key with value > 2: " + result);
        
        // reduce
        Integer sum = map.reduceValues(1, (v1, v2) -> v1 + v2);
        System.out.println("Sum of values: " + sum);
    }
}
```

---

## 5. HashMap vs ConcurrentHashMap vs synchronizedMap vs Hashtable

### Comparison Table

| Feature | HashMap | ConcurrentHashMap | Collections.synchronizedMap | Hashtable |
|---------|---------|-------------------|----------------------------|-----------|
| Thread-safe | No | Yes | Yes | Yes |
| Locking | None | Fine-grained (bucket) | Coarse-grained (entire map) | Coarse-grained (entire map) |
| Null key | 1 null key | No null key | 1 null key | No null key |
| Null value | Many null values | No null values | Many null values | No null values |
| Iterator | Fail-fast | Weakly consistent | Fail-fast | Fail-fast |
| Concurrent reads | Yes | Yes | No (single thread) | No (single thread) |
| Concurrent writes | No | Yes (different buckets) | No | No |
| Performance | Fast (single-thread) | Fast (multi-thread) | Slow (multi-thread) | Slow (multi-thread) |
| Introduced | Java 1.2 | Java 5 | Java 1.2 | Java 1.0 |
| Preferred | Single-thread | Multi-thread | Rarely | Never (legacy) |

### Code Comparison

```java
import java.util.*;
import java.util.concurrent.*;

public class MapComparison {
    public static void main(String[] args) {
        // 1. HashMap (NOT thread-safe)
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null, 0);        // OK
        hashMap.put("A", null);      // OK
        
        // 2. ConcurrentHashMap (thread-safe, no nulls)
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        // chm.put(null, 0);          // ❌ NullPointerException
        // chm.put("A", null);        // ❌ NullPointerException
        chm.put("A", 1);              // ✅
        
        // 3. synchronizedMap (thread-safe, coarse lock)
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put(null, 0);         // OK
        syncMap.put("A", null);       // OK
        // Iterator must be manually synchronized
        synchronized (syncMap) {
            Iterator<String> it = syncMap.keySet().iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
        
        // 4. Hashtable (legacy, thread-safe, no nulls)
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        // hashtable.put(null, 0);    // ❌ NullPointerException
        // hashtable.put("A", null);  // ❌ NullPointerException
        hashtable.put("A", 1);        // ✅
    }
}
```

---

## 6. CopyOnWriteArrayList

### What is CopyOnWriteArrayList?

```java
// Thread-safe ArrayList implementation
// Creates a new copy of underlying array on every write operation
// Iterators are snapshot-based (never throw ConcurrentModificationException)

import java.util.concurrent.*;
import java.util.*;

CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
```

### Properties

```
┌─────────────────────────────────────────────────────────────────────┐
│                    COPYONWRITEARRAYLIST PROPERTIES                    │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Thread-safe: Yes, all operations are thread-safe          │   │
│  │ 2. Fail-safe iterator: Snapshot at iterator creation time    │   │
│  │ 3. Copy-on-write: New array created for every write           │   │
│  │ 4. No ConcurrentModificationException                         │   │
│  │ 5. Read-heavy: Excellent for read-intensive scenarios          │   │
│  │ 6. Write-expensive: Slow for frequent write operations         │   │
│  │ 7. Memory overhead: Temporary copies exist during writes      │   │
│  │ 8. No null restriction: Can contain null elements             │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Constructors

```java
// 1. Default
CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();

// 2. From Collection
CopyOnWriteArrayList<String> list2 = new CopyOnWriteArrayList<>(Arrays.asList("A", "B"));

// 3. From Array
CopyOnWriteArrayList<String> list3 = new CopyOnWriteArrayList<>(new String[]{"A", "B"});
```

### Methods

```java
import java.util.concurrent.*;
import java.util.*;

public class CopyOnWriteArrayListMethods {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        
        // add, addAll
        list.add("A");
        list.add("B");
        list.addAll(Arrays.asList("C", "D"));
        System.out.println(list);  // [A, B, C, D]
        
        // addIfAbsent (no duplicate)
        boolean added = list.addIfAbsent("A");  // false
        list.addIfAbsent("E");                   // true
        System.out.println(list);  // [A, B, C, D, E]
        
        // addAllAbsent
        list.addAllAbsent(Arrays.asList("A", "F", "G"));  // Adds F, G
        System.out.println(list);  // [A, B, C, D, E, F, G]
        
        // set
        list.set(0, "AA");
        System.out.println(list);  // [AA, B, C, D, E, F, G]
        
        // remove
        list.remove("B");
        list.remove(0);
        System.out.println(list);  // [C, D, E, F, G]
        
        // get, size, contains, indexOf
        System.out.println(list.get(0));       // C
        System.out.println(list.size());       // 5
        System.out.println(list.contains("D")); // true
        System.out.println(list.indexOf("E"));  // 2
        
        // toArray
        String[] arr = list.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));  // [C, D, E, F, G]
    }
}
```

### Thread-Safety Demo

```java
import java.util.concurrent.*;

public class CopyOnWriteArrayListThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Thread 1: Iterate
        Thread t1 = new Thread(() -> {
            for (String s : list) {
                System.out.println("Reading: " + s);
                try { Thread.sleep(10); } catch (Exception e) { }
            }
        });
        
        // Thread 2: Modify
        Thread t2 = new Thread(() -> {
            list.add("D");
            list.add("E");
            System.out.println("Added D, E");
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Final list: " + list);
    }
}

// Output:
// Reading: A
// Reading: B
// Added D, E
// Reading: C
// Final list: [A, B, C, D, E]
```

### Iterator Snapshot Behavior

```java
import java.util.concurrent.*;
import java.util.*;

public class CopyOnWriteArrayListIterator {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Get iterator (snapshot)
        Iterator<String> it = list.iterator();
        
        // Modify list after iterator creation
        list.add("D");
        list.remove("A");
        
        // Iterator still sees old snapshot
        System.out.println("Iterator sees:");
        while (it.hasNext()) {
            System.out.println(it.next());  // A, B, C (old snapshot)
        }
        
        // List has new elements
        System.out.println("List now: " + list);  // [B, C, D]
        
        // New iterator sees current state
        System.out.println("New iterator sees:");
        for (String s : list) {
            System.out.println(s);  // B, C, D
        }
    }
}
```

---

## 7. ArrayList vs CopyOnWriteArrayList vs synchronizedList vs Vector

### Comparison Table

| Feature | ArrayList | CopyOnWriteArrayList | Collections.synchronizedList | Vector |
|---------|-----------|----------------------|---------------------------|--------|
| Thread-safe | No | Yes | Yes | Yes |
| Locking | None | Copy-on-write | Coarse-grained | Coarse-grained |
| Iterator | Fail-fast | Snapshot (fail-safe) | Fail-fast | Fail-fast |
| Concurrent reads | Yes | Yes | No | No |
| Concurrent writes | No | No (copy-based) | No | No |
| Write performance | Fast | Slow (copy entire array) | Slow | Slow |
| Read performance | Fast | Fast | Slow | Slow |
| Memory overhead | Low | High (copies) | Low | Low |
| Null elements | Yes | Yes | Yes | Yes |
| Legacy | No | No | No | Yes (Java 1.0) |
| Preferred | Single-thread | Read-heavy, multi-thread | Rarely | Never |

### Code Comparison

```java
import java.util.*;
import java.util.concurrent.*;

public class ListComparison {
    public static void main(String[] args) {
        // 1. ArrayList (NOT thread-safe)
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(null);  // OK
        
        // 2. CopyOnWriteArrayList (thread-safe, read-heavy)
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add(null);  // OK
        
        // 3. synchronizedList (thread-safe, coarse lock)
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add(null);  // OK
        // Iterator must be manually synchronized
        synchronized (syncList) {
            Iterator<String> it = syncList.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
        
        // 4. Vector (legacy, thread-safe)
        Vector<String> vector = new Vector<>();
        vector.add(null);  // OK
        vector.addElement("A");
        System.out.println(vector);
    }
}
```

---

## 8. CopyOnWriteArraySet

### What is CopyOnWriteArraySet?

```java
// Thread-safe Set implementation
// Backed by CopyOnWriteArrayList internally
// No duplicates, snapshot iterators
// Same properties as CopyOnWriteArrayList

import java.util.concurrent.*;

CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
```

### Constructors

```java
// 1. Default
CopyOnWriteArraySet<String> set1 = new CopyOnWriteArraySet<>();

// 2. From Collection
CopyOnWriteArraySet<String> set2 = new CopyOnWriteArraySet<>(Arrays.asList("A", "B"));
```

### Methods

```java
import java.util.concurrent.*;
import java.util.*;

public class CopyOnWriteArraySetMethods {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        
        // add
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println(set);  // [A, B, C]
        
        // No duplicates
        boolean added = set.add("A");  // false
        System.out.println("Added A again? " + added);  // false
        
        // remove
        set.remove("B");
        System.out.println(set);  // [A, C]
        
        // contains
        System.out.println(set.contains("A"));  // true
        
        // size, isEmpty
        System.out.println(set.size());   // 2
        System.out.println(set.isEmpty()); // false
        
        // toArray
        String[] arr = set.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));  // [A, C]
        
        // iterator
        for (String s : set) {
            System.out.println(s);
        }
    }
}
```

### Thread-Safety Demo

```java
import java.util.concurrent.*;

public class CopyOnWriteArraySetThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("A");
        set.add("B");
        
        // Thread 1: Iterate
        Thread t1 = new Thread(() -> {
            for (String s : set) {
                System.out.println("Reading: " + s);
                try { Thread.sleep(10); } catch (Exception e) { }
            }
        });
        
        // Thread 2: Add
        Thread t2 = new Thread(() -> {
            set.add("C");
            set.add("D");
            System.out.println("Added C, D");
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Final set: " + set);
    }
}
```

---

## 9. CopyOnWriteArraySet vs synchronizedSet

### Comparison Table

| Feature | CopyOnWriteArraySet | Collections.synchronizedSet |
|---------|---------------------|-----------------------------|
| Thread-safe | Yes | Yes |
| Internal | CopyOnWriteArrayList | HashSet (or any Set) |
| Iterator | Snapshot (fail-safe) | Fail-fast |
| Concurrent reads | Yes | No (single thread) |
| Write performance | Slow (copy) | Slow (lock) |
| Read performance | Fast | Slow (lock) |
| Memory overhead | High | Low |
| ConcurrentModificationException | No | Yes |
| Use case | Read-heavy, multi-thread | Rarely |

### Code Comparison

```java
import java.util.*;
import java.util.concurrent.*;

public class SetComparison {
    public static void main(String[] args) {
        // 1. CopyOnWriteArraySet (thread-safe, read-heavy)
        CopyOnWriteArraySet<String> cowSet = new CopyOnWriteArraySet<>();
        cowSet.add("A");
        cowSet.add("B");
        
        // Iterator: no ConcurrentModificationException
        for (String s : cowSet) {
            cowSet.add("C");  // No exception!
        }
        System.out.println(cowSet);  // [A, B, C]
        
        // 2. synchronizedSet (thread-safe, coarse lock)
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        syncSet.add("A");
        syncSet.add("B");
        
        // Iterator must be manually synchronized
        synchronized (syncSet) {
            Iterator<String> it = syncSet.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }
}
```

---

## 10. Interview Questions

### Q1. Why do we need Concurrent Collections?

**Answer:**
1. Traditional collections (`ArrayList`, `HashMap`) are NOT thread-safe.
2. Synchronized wrappers (`Collections.synchronizedList`) use coarse-grained locking, allowing only one thread to access at a time.
3. Concurrent collections provide fine-grained locking, allowing multiple threads to read/write concurrently.
4. Better performance and scalability in multi-threaded environments.

### Q2. What is the difference between HashMap and ConcurrentHashMap?

**Answer:**

| Feature | HashMap | ConcurrentHashMap |
|---------|---------|-------------------|
| Thread-safe | No | Yes |
| Null key | Yes | No |
| Null value | Yes | No |
| Iterator | Fail-fast | Weakly consistent |
| Locking | None | Bucket-level (Java 8+) |
| Performance | Fast single-thread | Fast multi-thread |

### Q3. What is the internal working of ConcurrentHashMap?

**Answer:**
- **Java 7**: Segment-based locking. Default 16 segments, each with its own `ReentrantLock`.
- **Java 8+**: CAS (Compare-And-Swap) for head updates + `synchronized` on the first node of each bucket. If a bucket grows too large, it converts to a Red-Black tree.

### Q4. Can ConcurrentHashMap have null key or null value?

**Answer:** No. `ConcurrentHashMap` does not allow null keys or null values. Attempting to insert null throws `NullPointerException`. This is because `ConcurrentHashMap` uses `null` internally to indicate a key is not present in some operations.

### Q5. What is CopyOnWriteArrayList?

**Answer:** `CopyOnWriteArrayList` is a thread-safe variant of `ArrayList`. On every write operation (add, set, remove), it creates a new copy of the underlying array. Iterators are snapshot-based and do not throw `ConcurrentModificationException`. Best for read-heavy scenarios with few writes.

### Q6. What is the difference between ArrayList and CopyOnWriteArrayList?

**Answer:**

| Feature | ArrayList | CopyOnWriteArrayList |
|---------|-----------|----------------------|
| Thread-safe | No | Yes |
| Iterator | Fail-fast | Snapshot (fail-safe) |
| Write cost | Low | High (copies entire array) |
| Read cost | Low | Low |
| Use case | Single-thread | Read-heavy, multi-thread |

### Q7. What is the difference between Vector and CopyOnWriteArrayList?

**Answer:**
- **Vector**: Synchronized on every method, coarse-grained locking, slow for both reads and writes.
- **CopyOnWriteArrayList**: Lock-free reads, copy-on-write for writes, much faster for read-heavy scenarios.

### Q8. What is CopyOnWriteArraySet?

**Answer:** `CopyOnWriteArraySet` is a thread-safe `Set` implementation backed by `CopyOnWriteArrayList`. It uses `equals()` to check for duplicates. Like `CopyOnWriteArrayList`, it is best for read-heavy scenarios.

### Q9. What is the difference between CopyOnWriteArraySet and synchronizedSet?

**Answer:**
- **CopyOnWriteArraySet**: Snapshot iterators, no `ConcurrentModificationException`, read-heavy, copy-on-write.
- **synchronizedSet**: Fail-fast iterators, manual synchronization required for iteration, coarse-grained locking.

### Q10. What is ConcurrentMap?

**Answer:** `ConcurrentMap` is an interface that extends `Map` and provides additional atomic operations: `putIfAbsent`, `remove(key, value)`, `replace(key, oldValue, newValue)`, `computeIfAbsent`, `computeIfPresent`, `compute`, `merge`.

### Q11. What is the difference between synchronizedMap and ConcurrentHashMap?

**Answer:**
- **synchronizedMap**: Wraps a `Map` with `synchronized` methods, coarse-grained lock, only one thread can access at a time.
- **ConcurrentHashMap**: Fine-grained locking (bucket-level), multiple threads can read/write concurrently, much better performance.

### Q12. What happens when we iterate over ConcurrentHashMap and another thread modifies it?

**Answer:** `ConcurrentHashMap` iterator is **weakly consistent**. It may reflect some or all of the changes made by other threads after the iterator was created, but it will never throw `ConcurrentModificationException`.

### Q13. What is the difference between fail-fast and fail-safe iterators?

**Answer:**
- **Fail-fast**: Throws `ConcurrentModificationException` if collection is modified during iteration (e.g., `ArrayList`, `HashMap`).
- **Fail-safe**: Works on a snapshot or clone, does not throw exception (e.g., `CopyOnWriteArrayList`, `ConcurrentHashMap`).

### Q14. When should we use CopyOnWriteArrayList?

**Answer:** Use `CopyOnWriteArrayList` when:
1. Multiple threads read frequently.
2. Writes are rare.
3. You need snapshot iterators without `ConcurrentModificationException`.
4. Read performance is more critical than write performance.

### Q15. What are the disadvantages of CopyOnWriteArrayList?

**Answer:**
1. **Write overhead**: Every write creates a new copy of the array.
2. **Memory overhead**: Multiple copies can exist simultaneously.
3. **Data consistency**: Iterators see a snapshot, not real-time data.
4. **Not suitable for write-heavy scenarios**.

### Q16. How does ConcurrentHashMap achieve thread-safety without locking the entire map?

**Answer:**
- **Java 7**: Divides map into segments (default 16). Each segment has its own lock. Threads can operate on different segments concurrently.
- **Java 8+**: Uses CAS for head updates and `synchronized` on the first node of each bucket. Different buckets can be locked independently.

### Q17. What is the difference between Hashtable and ConcurrentHashMap?

**Answer:**

| Feature | Hashtable | ConcurrentHashMap |
|---------|-----------|-------------------|
| Locking | Entire table | Segment/bucket level |
| Null key/value | No | No |
| Performance | Slow | Fast |
| Iterator | Fail-fast | Weakly consistent |
| Legacy | Yes (Java 1.0) | No (Java 5) |
| Preferred | Never | Always |

### Q18. What is `computeIfAbsent` in ConcurrentHashMap?

**Answer:** `computeIfAbsent` atomically computes the value for a given key if the key is not already associated with a value (or is mapped to null). It is useful for lazy initialization.

```java
map.computeIfAbsent("key", k -> expensiveComputation());
```

### Q19. What is `merge` in ConcurrentHashMap?

**Answer:** `merge` atomically combines the existing value with a new value using a provided remapping function. If the key does not exist, it inserts the new value directly.

```java
map.merge("key", 1, Integer::sum);  // Increments value by 1
```

### Q20. Can we use ConcurrentHashMap in a single-threaded application?

**Answer:** Yes, but there is a slight overhead compared to `HashMap`. For single-threaded applications, prefer `HashMap` unless you need the additional atomic methods (`putIfAbsent`, `compute`, etc.).

---

## 11. Key Takeaways

1. **ConcurrentHashMap** is the preferred thread-safe `Map` with fine-grained locking.
2. **CopyOnWriteArrayList** is ideal for read-heavy, write-rare scenarios with snapshot iterators.
3. **CopyOnWriteArraySet** provides thread-safe `Set` semantics backed by `CopyOnWriteArrayList`.
4. **ConcurrentMap** interface provides atomic compound operations (`putIfAbsent`, `compute`, `merge`).
5. **Traditional collections** fail in multi-threaded environments with `ConcurrentModificationException`.
6. **Synchronized wrappers** are slower due to coarse-grained locking.
7. **Hashtable** is legacy and should be replaced with `ConcurrentHashMap`.
8. **Fail-fast iterators** throw exceptions on concurrent modification; **fail-safe** do not.
9. **ConcurrentHashMap** does NOT allow null keys or null values.
10. **CopyOnWriteArrayList** creates a new copy on every write, making writes expensive.
11. **Iterators** of `ConcurrentHashMap` are weakly consistent; `CopyOnWriteArrayList` iterators are snapshot-based.
12. **Choose** the collection based on your read/write ratio and concurrency requirements.
13. **Concurrent collections** are essential for high-performance, scalable multi-threaded applications.
14. **Java 8+** `ConcurrentHashMap` uses CAS and `synchronized` on nodes for better performance.
15. **Vector** and `Hashtable` are legacy classes and should be avoided in new code.

---

**Happy coding! 🚀**

*Concurrent collections are the foundation of scalable, thread-safe Java applications — master them to build robust multi-threaded systems.*
