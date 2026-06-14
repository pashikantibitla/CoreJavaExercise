# 14 — Garbage Collection

> **Topics:** Introduction, Object Eligibility, finalize(), GC Types, GC Algorithms, Heap Structure, JVM Tuning, Reference Types, Interview FAQs

---

## Table of Contents

1. [Part 1: Introduction to Garbage Collection](#part-1-introduction-to-garbage-collection)
2. [Part 2: Ways to Make Object Eligible for GC](#part-2-ways-to-make-object-eligible-for-gc)
3. [Part 3: Methods for Requesting JVM to Run GC](#part-3-methods-for-requesting-jvm-to-run-gc)
4. [Part 4: finalize() Method](#part-4-finalize-method)
5. [Part 5: Memory Leaks and GC](#part-5-memory-leaks-and-gc)
6. [Part 6: Types of GC](#part-6-types-of-gc)
7. [Part 7: GC Algorithms](#part-7-gc-algorithms)
8. [Part 8: Heap Memory Structure](#part-8-heap-memory-structure)
9. [Part 9: GC Tuning and JVM Options](#part-9-gc-tuning-and-jvm-options)
10. [Part 10: System.gc() and Runtime.gc()](#part-10-systemgc-and-runtimegc)
11. [Part 11: Reference Types](#part-11-reference-types)
12. [Part 12: java.lang.ref Package](#part-12-javalangref-package)
13. [Part 13: Interview FAQs on GC](#part-13-interview-faqs-on-gc)
14. [Key Takeaways](#key-takeaways)

---

## Part 1: Introduction to Garbage Collection

### What is Garbage Collection?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    GARBAGE COLLECTION (GC)                            │
│                                                                      │
│  Automatic memory management in Java.                                │
│  JVM reclaims memory from objects that are no longer reachable.      │
│                                                                      │
│  Process:                                                            │
│  1. Identify objects that are no longer needed.                      │
│  2. Reclaim the heap memory used by those objects.                   │
│  3. Compact the memory (optional, depending on GC).                   │
│                                                                      │
│  Developer responsibility: Create objects.                          │
│  JVM responsibility: Destroy unreachable objects.                      │
└─────────────────────────────────────────────────────────────────────┘
```

### Advantages of GC

```java
// 1. No manual memory management (unlike C/C++)
// 2. No dangling pointers (object freed but reference still used)
// 3. No memory leaks (in theory, but leaks still possible in Java)
// 4. Increased productivity for developers
```

### GC Roots

```java
// Objects reachable from GC Roots are NOT eligible for GC.
// GC Roots include:

1. Local variables in stack frames of active threads
2. Active Java threads
3. Static variables
4. JNI references (native code references)
5. Objects held by system classloader
6. Monitor objects (synchronized locks)
```

### Example

```java
public class GCDemo {
    public static void main(String[] args) {
        String s1 = new String("Hello");  // s1 is a GC root (local variable)
        s1 = null;  // "Hello" object is now eligible for GC
        
        // At this point, "Hello" has no reference
        // GC may reclaim it (not guaranteed to run immediately)
    }
}
```

---

## Part 2: Ways to Make Object Eligible for GC

### 1. Nullifying the Reference

```java
public class NullifyReference {
    public static void main(String[] args) {
        String s = new String("Hello");
        s = null;  // "Hello" object is eligible for GC
    }
}
```

### 2. Reassigning the Reference

```java
public class ReassignReference {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("World");
        s1 = s2;  // "Hello" object is eligible for GC
                  // "World" object is now referenced by s1 and s2
    }
}
```

### 3. Object Created Inside a Method

```java
public class ObjectInMethod {
    public void method() {
        String s = new String("Local");  // Local variable
        // When method ends, 's' goes out of scope
        // "Local" object becomes eligible for GC
    }
    
    public static void main(String[] args) {
        new ObjectInMethod().method();
        // After method call, "Local" is eligible for GC
    }
}
```

### 4. Island of Isolation

```java
class Node {
    Node next;
    int data;
    Node(int data) { this.data = data; }
}

public class IslandOfIsolation {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;  // Circular reference
        
        n1 = null;
        n2 = null;
        n3 = null;
        
        // Even though n1, n2, n3 reference each other,
        // no external GC root points to them.
        // All three objects are eligible for GC.
    }
}
```

### Summary

```
┌─────────────────────────────────────────────────────────────────────┐
│              WAYS TO MAKE OBJECT ELIGIBLE FOR GC                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Nullify reference      → s = null;                        │   │
│  │ 2. Reassign reference     → s1 = s2;                         │   │
│  │ 3. Object out of scope  → local variable in method         │   │
│  │ 4. Island of isolation    → circular references, no root     │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## Part 3: Methods for Requesting JVM to Run GC

### System.gc()

```java
public class RequestGC {
    public static void main(String[] args) {
        String s = new String("Hello");
        s = null;
        
        // Request GC (suggestion, not command)
        System.gc();
        
        // JVM may or may not run GC immediately
    }
}
```

### Runtime.gc()

```java
public class RuntimeGC {
    public static void main(String[] args) {
        String s = new String("Hello");
        s = null;
        
        // Runtime.gc() is equivalent to System.gc()
        Runtime.getRuntime().gc();
    }
}
```

### Important Notes

```java
// 1. System.gc() internally calls Runtime.getRuntime().gc()
// 2. JVM may ignore the request
// 3. GC runs automatically when:
//    - Heap is low
//    - JVM decides it's optimal
//    - Explicit request (System.gc() or Runtime.gc())

// 4. Performance impact:
//    - Full GC is expensive
//    - Calling System.gc() too often can hurt performance
//    - Modern JVMs are smart; let them decide

// 5. Best practice: Do NOT call System.gc() in production code
```

---

## Part 4: finalize() Method

### What is finalize()?

```java
// finalize() is called by GC before object is destroyed
// Defined in Object class
// Can be overridden for cleanup

public class FinalizeDemo {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize() called for object: " + this);
        // Cleanup code: close files, release connections, etc.
        super.finalize();  // Call parent's finalize
    }
    
    public static void main(String[] args) {
        FinalizeDemo obj = new FinalizeDemo();
        obj = null;  // Object eligible for GC
        System.gc();  // Request GC
        
        // May see "finalize() called..." in output
    }
}
```

### Object Resurrection in finalize()

```java
public class ResurrectionDemo {
    static ResurrectionDemo obj;
    
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize() called");
        obj = this;  // Resurrect the object!
        // Object is no longer eligible for GC
    }
    
    public static void main(String[] args) throws InterruptedException {
        obj = new ResurrectionDemo();
        obj = null;  // Eligible for GC
        System.gc();
        Thread.sleep(1000);
        
        System.out.println("Obj after resurrection: " + obj);
        // Obj is alive again!
        
        obj = null;  // Eligible for GC again
        System.gc();
        Thread.sleep(1000);
        
        // finalize() is called only ONCE per object
        // Even if object is resurrected and later GC'd again
    }
}
```

### finalize() is Deprecated

```java
// Java 9+: finalize() is deprecated
// Reasons:
// 1. Uncertain timing (when will GC run?)
// 2. Performance overhead (GC must queue objects with finalize)
// 3. Uncertain execution (finalize may not be called)
// 4. Resource leaks (if finalize throws exception)

// Alternatives:
// 1. try-with-resources (AutoCloseable)
// 2. Cleaner class (Java 9+)
// 3. Explicit cleanup methods
```

### Cleaner (Java 9+)

```java
import java.lang.ref.Cleaner;

public class CleanerDemo {
    private static final Cleaner cleaner = Cleaner.create();
    
    private static class CleanupTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Cleanup task executed!");
        }
    }
    
    public CleanerDemo() {
        cleaner.register(this, new CleanupTask());
    }
    
    public static void main(String[] args) {
        CleanerDemo obj = new CleanerDemo();
        obj = null;
        System.gc();
    }
}
```

---

## Part 5: Memory Leaks and GC

### What is a Memory Leak?

```java
// Memory leak: Object is no longer needed but still referenced
// GC cannot reclaim it, causing memory to grow over time
```

### Common Causes

```java
// 1. Static Collections
public class StaticCollectionLeak {
    private static final List<String> cache = new ArrayList<>();
    
    public void add(String data) {
        cache.add(data);  // Never removed! Memory leak!
    }
}

// 2. Unregistered Listeners
public class ListenerLeak {
    private final List<EventListener> listeners = new ArrayList<>();
    
    public void addListener(EventListener listener) {
        listeners.add(listener);  // Never removed! Memory leak!
    }
}

// 3. Unclosed Resources
public class ResourceLeak {
    public void readFile() {
        // Old way: FileInputStream not closed!
        FileInputStream fis = new FileInputStream("file.txt");
        // If exception occurs, fis is never closed
        // Use try-with-resources instead!
    }
}

// 4. Incorrect equals() and hashCode() in HashMap keys
public class BadKey {
    // If hashCode() changes after object is put in HashMap,
    // the object cannot be retrieved or removed!
    // Memory leak!
}

// 5. ThreadLocal without remove()
public class ThreadLocalLeak {
    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    
    public void use() {
        threadLocal.set(new Object());
        // If thread is from a pool, value persists forever!
        // Must call threadLocal.remove() when done!
    }
}
```

### Detecting Memory Leaks

```java
// 1. Heap dumps
//    -XX:+HeapDumpOnOutOfMemoryError
//    -XX:HeapDumpPath=/path/to/dump

// 2. Profiling tools
//    - VisualVM
//    - JProfiler
//    - YourKit
//    - Eclipse MAT

// 3. Monitoring
//    - jstat
//    - jconsole
//    - jvisualvm
```

---

## Part 6: Types of GC

### Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TYPES OF GARBAGE COLLECTORS                        │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Serial GC                                               │   │
│  │    - Single thread for GC                                  │   │
│  │    - Best for small heaps, single-core machines            │   │
│  │    - JVM option: -XX:+UseSerialGC                           │   │
│  │                                                             │   │
│  │ 2. Parallel GC (Throughput GC)                             │   │
│  │    - Multiple threads for GC                               │   │
│  │    - Best for multi-core, high throughput                  │   │
│  │    - Default for Java 8 (server mode)                      │   │
│  │    - JVM option: -XX:+UseParallelGC                       │   │
│  │                                                             │   │
│  │ 3. CMS (Concurrent Mark Sweep)                             │   │
│  │    - Low pause time                                        │   │
│  │    - GC runs concurrently with application                 │   │
│  │    - Deprecated in Java 9, removed in Java 14              │   │
│  │    - JVM option: -XX:+UseConcMarkSweepGC                  │   │
│  │                                                             │   │
│  │ 4. G1 GC (Garbage First)                                   │   │
│  │    - Default from Java 9+                                  │   │
│  │    - Balanced throughput and low pause                     │   │
│  │    - Divides heap into regions                             │   │
│  │    - JVM option: -XX:+UseG1GC                             │   │
│  │                                                             │   │
│  │ 5. ZGC (Java 11+)                                         │   │
│  │    - Ultra-low latency (< 10ms pause)                      │   │
│  │    - Scalable to multi-terabyte heaps                      │   │
│  │    - JVM option: -XX:+UseZGC                              │   │
│  │                                                             │   │
│  │ 6. Shenandoah (Java 12+)                                   │   │
│  │    - Low pause time                                        │   │
│  │    - Concurrent compaction                                 │   │
│  │    - JVM option: -XX:+UseShenandoahGC                     │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Comparison Table

| GC | Threads | Pause Time | Use Case | Status |
|----|---------|-----------|----------|--------|
| Serial | 1 | High | Small heaps, single-core | Available |
| Parallel | Multiple | Medium | High throughput, multi-core | Available |
| CMS | Multiple | Low | Low latency (legacy) | Removed (Java 14) |
| G1 | Multiple | Low | Balanced, large heaps | Default (Java 9+) |
| ZGC | Multiple | Ultra-low | Huge heaps, low latency | Java 11+ |
| Shenandoah | Multiple | Ultra-low | Low latency, concurrent | Java 12+ |

---

## Part 7: GC Algorithms

### 1. Mark-Sweep

```
┌─────────────────────────────────────────────────────────────────────┐
│                    MARK-SWEEP ALGORITHM                               │
│                                                                      │
│  Phase 1: MARK                                                         │
│  - Traverse object graph from GC roots                               │
│  - Mark all reachable objects as "live"                              │
│                                                                      │
│  Phase 2: SWEEP                                                      │
│  - Scan entire heap                                                  │
│  - Remove unmarked (dead) objects                                    │
│  - Add freed memory to free list                                     │
│                                                                      │
│  Pros: Simple, no object movement                                    │
│  Cons: Fragmentation, two passes (slow)                              │
└─────────────────────────────────────────────────────────────────────┘
```

### 2. Copy (Mark-Copy)

```
┌─────────────────────────────────────────────────────────────────────┐
│                    COPY ALGORITHM                                     │
│                                                                      │
│  - Divide heap into two spaces: From and To                          │
│  - When From space is full:                                          │
│    1. Mark live objects in From space                                │
│    2. Copy live objects to To space (compact)                        │
│    3. Clear entire From space                                        │
│    4. Swap From and To spaces                                        │
│                                                                      │
│  Pros: No fragmentation, fast allocation                             │
│  Cons: Double memory usage, copy cost                                │
│  Used in: Young generation (Eden → Survivor)                        │
└─────────────────────────────────────────────────────────────────────┘
```

### 3. Mark-Compact

```
┌─────────────────────────────────────────────────────────────────────┐
│                    MARK-COMPACT ALGORITHM                             │
│                                                                      │
│  Phase 1: MARK                                                       │
│  - Traverse from GC roots, mark live objects                       │
│                                                                      │
│  Phase 2: COMPACT                                                    │
│  - Move all live objects to one end of heap                        │
│  - Update all references                                             │
│  - Free space is contiguous                                          │
│                                                                      │
│  Pros: No fragmentation, no double memory                          │
│  Cons: Object movement cost, reference updates                       │
│  Used in: Old generation (Tenured)                                  │
└─────────────────────────────────────────────────────────────────────┘
```

### Summary

| Algorithm | Fragmentation | Memory Overhead | Speed | Used In |
|-----------|-------------|-----------------|-------|---------|
| Mark-Sweep | Yes | Low | Slow | Old CMS |
| Copy | No | High (50%) | Fast | Young generation |
| Mark-Compact | No | Low | Medium | Old generation |

---

## Part 8: Heap Memory Structure

### Heap Structure

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JVM HEAP MEMORY STRUCTURE                          │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  HEAP (all objects)                                          │   │
│  │  ┌─────────────────────────────────────────────────────┐   │   │
│  │  │  Young Generation (1/3 of heap)                      │   │   │
│  │  │  ┌─────────────────────────────────────────────┐    │   │   │
│  │  │  │  Eden Space (8/10 of young)                  │    │   │   │
│  │  │  │  - New objects allocated here                 │    │   │   │
│  │  │  │  - Most objects die here (minor GC)           │    │   │   │
│  │  │  └─────────────────────────────────────────────┘    │   │   │
│  │  │  ┌─────────────────────────────────────────────┐    │   │   │
│  │  │  │  Survivor 0 (S0) (1/10 of young)            │    │   │   │
│  │  │  │  - Objects surviving Eden GC                  │    │   │   │
│  │  │  └─────────────────────────────────────────────┘    │   │   │
│  │  │  ┌─────────────────────────────────────────────┐    │   │   │
│  │  │  │  Survivor 1 (S1) (1/10 of young)            │    │   │   │
│  │  │  │  - Objects surviving S0 GC                      │    │   │   │
│  │  │  └─────────────────────────────────────────────┘    │   │   │
│  │  └─────────────────────────────────────────────────────┘   │   │
│  │  ┌─────────────────────────────────────────────────────┐   │   │
│  │  │  Old Generation (Tenured) (2/3 of heap)              │   │   │
│  │  │  - Long-lived objects promoted from survivor       │   │   │
│  │  │  - Major GC (Full GC) occurs here                    │   │   │
│  │  └─────────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  (Outside Heap)                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  Metaspace (Java 8+) / PermGen (Java 7 and earlier)         │   │
│  │  - Class metadata, methods, static variables                  │   │
│  │  - Metaspace uses native memory (auto-expanding)             │   │
│  │  - PermGen is fixed size, caused OutOfMemoryError            │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Object Promotion Flow

```
New Object → Eden Space → Minor GC → Survivor 0 → Minor GC → Survivor 1
                                                                  ↓
                                                            Minor GC (age threshold)
                                                                  ↓
                                                          Old Generation (Tenured)
                                                                  ↓
                                                            Major GC / Full GC
```

### GC Event Types

| GC Event | Area | Trigger | Description |
|----------|------|---------|-------------|
| Minor GC | Young Generation | Eden full | Fast, frequent, stop-the-world |
| Major GC | Old Generation | Old full | Slower, less frequent |
| Full GC | Entire heap | Explicit or heap full | Slowest, stops all threads |

---

## Part 9: GC Tuning and JVM Options

### Heap Size Options

```bash
# Initial heap size
java -Xms512m MyApp

# Maximum heap size
java -Xmx2g MyApp

# Both together
java -Xms1g -Xmx1g MyApp  # Fixed heap size (avoids resizing)

# Young generation size
java -Xmn256m MyApp

# Ratio of young to old (default 2)
java -XX:NewRatio=2 MyApp  # Old:Young = 2:1

# Survivor ratio (default 8)
java -XX:SurvivorRatio=8 MyApp  # Eden:S0:S1 = 8:1:1
```

### GC Selection

```bash
# Serial GC
java -XX:+UseSerialGC MyApp

# Parallel GC
java -XX:+UseParallelGC MyApp

# G1 GC (default Java 9+)
java -XX:+UseG1GC MyApp

# ZGC (Java 11+)
java -XX:+UseZGC MyApp

# Shenandoah (Java 12+)
java -XX:+UseShenandoahGC MyApp
```

### GC Tuning Options

```bash
# Max GC pause time target
java -XX:MaxGCPauseMillis=200 MyApp

# Throughput goal (time spent in GC)
java -XX:GCTimeRatio=19 MyApp  # 5% of time in GC

# Heap dump on OutOfMemoryError
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/dump.hprof MyApp

# Print GC details
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps MyApp

# Log GC to file
java -Xloggc:/var/log/gc.log MyApp

# Enable GC log rotation (Java 9+)
java -Xlog:gc*:file=/var/log/gc.log:time,uptime,level,tags:filecount=5,filesize=10m MyApp
```

### G1 GC Tuning

```bash
# Region size
java -XX:G1HeapRegionSize=16m MyApp

# Max pause time
java -XX:MaxGCPauseMillis=200 MyApp

# Initiating heap occupancy percentage
java -XX:InitiatingHeapOccupancyPercent=45 MyApp
```

---

## Part 10: System.gc() and Runtime.gc()

### System.gc()

```java
public class SystemGCDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new String("Object" + i);  // Create objects
        }
        
        System.out.println("Before GC: ");
        printMemory();
        
        System.gc();  // Suggest GC
        
        System.out.println("After GC request: ");
        printMemory();
    }
    
    static void printMemory() {
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;
        System.out.println("  Used: " + used / 1024 / 1024 + " MB");
    }
}
```

### Runtime.gc()

```java
public class RuntimeGCDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        
        // Runtime.gc() is the underlying method
        // System.gc() calls this internally
        runtime.gc();
        
        // Memory info
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        
        System.out.println("Max Memory: " + maxMemory / 1024 / 1024 + " MB");
        System.out.println("Total Memory: " + totalMemory / 1024 / 1024 + " MB");
        System.out.println("Free Memory: " + freeMemory / 1024 / 1024 + " MB");
    }
}
```

### Important Points

```java
// 1. System.gc() and Runtime.gc() are SUGGESTIONS
// 2. JVM may ignore them
// 3. Calling them frequently hurts performance
// 4. Modern JVMs have adaptive GC; let them decide
// 5. Do NOT call in production code
// 6. Can be disabled with: -XX:+DisableExplicitGC
```

---

## Part 11: Reference Types

### Types of References

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TYPES OF REFERENCES IN JAVA                        │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Strong Reference                                          │   │
│  │    - Normal reference: Object obj = new Object();            │   │
│  │    - NOT eligible for GC as long as reference exists         │   │
│  │    - Default reference type                                  │   │
│  │                                                             │   │
│  │ 2. Soft Reference                                            │   │
│  │    - Eligible for GC when memory is LOW                     │   │
│  │    - Good for caches                                        │   │
│  │    - Cleared before OutOfMemoryError                        │   │
│  │                                                             │   │
│  │ 3. Weak Reference                                            │   │
│  │    - Eligible for GC when GC runs (regardless of memory)    │   │
│  │    - Good for canonicalized mappings (WeakHashMap)          │   │
│  │    - Cleared on next GC cycle                               │   │
│  │                                                             │   │
│  │ 4. Phantom Reference                                         │   │
│  │    - Always returns null from get()                         │   │
│  │    - Used for cleanup tracking after finalization           │   │
│  │    - Must be used with ReferenceQueue                       │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Strong Reference

```java
// Strong reference (default)
String s = new String("Hello");  // Strong reference

// NOT eligible for GC until 's' is null or out of scope
s = null;  // Now eligible for GC
```

### Soft Reference

```java
import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) {
        // Soft reference
        SoftReference<String> softRef = new SoftReference<>(new String("Soft"));
        
        System.out.println("Before GC: " + softRef.get());
        
        System.gc();
        
        // Soft reference may still be available if memory is sufficient
        System.out.println("After GC: " + softRef.get());
        
        // Soft references are cleared when memory is low
        // Good for implementing caches
    }
}
```

### Weak Reference

```java
import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        WeakReference<String> weakRef = new WeakReference<>(new String("Weak"));
        
        System.out.println("Before GC: " + weakRef.get());
        
        System.gc();  // Suggest GC
        
        // Weak reference is likely cleared
        System.out.println("After GC: " + weakRef.get());
    }
}
```

### Phantom Reference

```java
import java.lang.ref.*;

public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object obj = new Object();
        
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, queue);
        
        System.out.println("Phantom get(): " + phantomRef.get());  // Always null
        
        obj = null;  // Eligible for GC
        System.gc();
        
        // Wait for reference to be enqueued
        Reference<?> ref = queue.remove(1000);
        if (ref == phantomRef) {
            System.out.println("Phantom reference enqueued!");
            // Object is finalized, can perform cleanup
        }
    }
}
```

---

## Part 12: java.lang.ref Package

### ReferenceQueue

```java
import java.lang.ref.*;

public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        
        WeakReference<String> weakRef = new WeakReference<>(new String("Weak"), queue);
        
        System.out.println("Before GC: " + weakRef.get());
        
        System.gc();
        
        // Poll the queue
        Reference<?> ref = queue.poll();
        if (ref != null) {
            System.out.println("Reference enqueued!");
        }
    }
}
```

### WeakHashMap

```java
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<String, String> map = new WeakHashMap<>();
        
        String key = new String("Key");
        map.put(key, "Value");
        
        System.out.println("Before GC: " + map);
        
        key = null;  // Remove strong reference to key
        System.gc();  // Key may be GC'd, entry removed from map
        
        System.out.println("After GC: " + map);
    }
}
```

### SoftReference Cache Example

```java
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftReferenceCache {
    private Map<String, SoftReference<byte[]>> cache = new HashMap<>();
    
    public byte[] getData(String key) {
        SoftReference<byte[]> ref = cache.get(key);
        if (ref != null) {
            byte[] data = ref.get();
            if (data != null) {
                return data;  // Cache hit
            }
        }
        // Cache miss or GC'd
        byte[] data = loadData(key);
        cache.put(key, new SoftReference<>(data));
        return data;
    }
    
    private byte[] loadData(String key) {
        return new byte[1024 * 1024];  // 1 MB
    }
    
    public static void main(String[] args) {
        SoftReferenceCache cache = new SoftReferenceCache();
        byte[] data = cache.getData("file1");
        System.out.println("Loaded data size: " + data.length);
    }
}
```

### Cleaner (Java 9+)

```java
import java.lang.ref.Cleaner;

public class CleanerResource implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;
    
    private static class CleanupTask implements Runnable {
        private final String resourceName;
        
        CleanupTask(String resourceName) {
            this.resourceName = resourceName;
        }
        
        @Override
        public void run() {
            System.out.println("Cleaning up: " + resourceName);
        }
    }
    
    public CleanerResource(String name) {
        this.cleanable = cleaner.register(this, new CleanupTask(name));
    }
    
    @Override
    public void close() {
        cleanable.clean();
    }
    
    public static void main(String[] args) {
        try (CleanerResource resource = new CleanerResource("MyResource")) {
            System.out.println("Using resource");
        }
        System.out.println("Resource closed");
    }
}
```

---

## Part 13: Interview FAQs on GC

### Q1. What is Garbage Collection?

**Answer:** Garbage Collection is automatic memory management in Java. The JVM automatically identifies and removes objects that are no longer reachable, reclaiming memory without explicit programmer intervention.

### Q2. How does JVM determine if an object is eligible for GC?

**Answer:** An object is eligible for GC if there are no references to it from any GC root. GC roots include:
- Local variables in active stack frames
- Active threads
- Static variables
- JNI references

### Q3. What are the ways to make an object eligible for GC?

**Answer:**
1. Set all references to `null`.
2. Reassign the reference to another object.
3. Let the object go out of scope (local variable in method).
4. Create an island of isolation (circular references with no external root).

### Q4. What is the difference between `System.gc()` and `Runtime.gc()`?

**Answer:** There is no functional difference. `System.gc()` internally calls `Runtime.getRuntime().gc()`. Both are suggestions to the JVM to run GC, but the JVM may ignore them.

### Q5. What is `finalize()`?

**Answer:** `finalize()` is a method in `Object` class called by GC before an object is destroyed. It can be overridden for cleanup. However, it is **deprecated since Java 9** because:
- Uncertain timing and execution
- Performance overhead
- Object can be resurrected
- Better alternatives: `try-with-resources`, `Cleaner`

### Q6. Can an object be resurrected in `finalize()`?

**Answer:** Yes. In `finalize()`, you can assign `this` to a static reference, making the object reachable again. However, `finalize()` will be called only once per object, so if the object becomes eligible again, `finalize()` will NOT be called a second time.

### Q7. What is the difference between Minor GC, Major GC, and Full GC?

**Answer:**
- **Minor GC**: Collects Young Generation (Eden, Survivor). Fast and frequent.
- **Major GC**: Collects Old Generation. Slower and less frequent.
- **Full GC**: Collects entire heap (Young + Old). Slowest, stops all threads.

### Q8. What is the structure of the Java heap?

**Answer:**
- **Young Generation** (1/3 of heap):
  - Eden Space (new objects)
  - Survivor 0 (S0)
  - Survivor 1 (S1)
- **Old Generation** (Tenured) (2/3 of heap): Long-lived objects
- **Metaspace** (Java 8+): Class metadata (replaced PermGen)

### Q9. What are the different types of Garbage Collectors?

**Answer:**
| GC | Characteristics |
|----|-----------------|
| Serial | Single thread, small heaps |
| Parallel | Multi-thread, throughput |
| CMS | Low pause, concurrent (removed) |
| G1 | Balanced, default (Java 9+) |
| ZGC | Ultra-low latency, huge heaps |
| Shenandoah | Low pause, concurrent |

### Q10. What is the Mark-Sweep algorithm?

**Answer:** Mark-Sweep is a GC algorithm with two phases:
1. **Mark**: Traverse from GC roots and mark all reachable objects.
2. **Sweep**: Scan heap and remove unmarked (dead) objects.
Pros: Simple. Cons: Fragmentation, requires two passes.

### Q11. What is the difference between Mark-Sweep and Mark-Compact?

**Answer:**
- **Mark-Sweep**: Removes dead objects. May leave fragmentation.
- **Mark-Compact**: Removes dead objects AND moves live objects to one end of heap. No fragmentation but requires object movement.

### Q12. What is a memory leak in Java?

**Answer:** A memory leak occurs when objects are no longer needed but are still referenced, preventing GC from reclaiming them. Common causes:
- Static collections that grow indefinitely
- Unregistered event listeners
- Unclosed resources
- ThreadLocal without `remove()`

### Q13. What is the difference between Strong, Soft, Weak, and Phantom references?

**Answer:**

| Reference | GC Behavior | Use Case |
|-----------|-------------|----------|
| Strong | Never GC'd | Normal objects |
| Soft | GC'd when memory low | Caches |
| Weak | GC'd on next GC | WeakHashMap, canonical mappings |
| Phantom | After finalization | Cleanup tracking |

### Q14. What is `WeakHashMap`?

**Answer:** `WeakHashMap` uses weak references for keys. When a key is no longer strongly referenced, the entry is automatically removed from the map. Useful for temporary caches where entries should be GC'd when not needed.

### Q15. What is `ReferenceQueue`?

**Answer:** `ReferenceQueue` is a queue that receives notifications when objects referenced by `SoftReference`, `WeakReference`, or `PhantomReference` are garbage collected. Used for cleanup actions.

### Q16. What is the difference between `-Xms` and `-Xmx`?

**Answer:**
- `-Xms`: Initial heap size.
- `-Xmx`: Maximum heap size.
Setting them equal (`-Xms1g -Xmx1g`) avoids heap resizing overhead.

### Q17. What is the default GC in Java?

**Answer:**
- Java 8: Parallel GC (server mode), Serial GC (client mode)
- Java 9+: G1 GC

### Q18. When should we use G1 GC?

**Answer:** G1 GC is suitable for:
- Large heaps (6GB+)
- Predictable pause times
- Mixed workloads (not purely throughput or latency)
- Default choice for Java 9+

### Q19. What is a `OutOfMemoryError`?

**Answer:** `OutOfMemoryError` occurs when the JVM cannot allocate an object because there is insufficient heap space. Common causes:
- Memory leak
- Heap size too small
- Too many large objects

### Q20. How do you analyze a memory leak?

**Answer:**
1. Enable heap dump on OOM: `-XX:+HeapDumpOnOutOfMemoryError`
2. Analyze heap dump with tools like Eclipse MAT, VisualVM, JProfiler.
3. Look for objects with unexpectedly high retention.
4. Check for unclosed resources, static collections, listeners.

### Q21. What is `java.lang.ref.Cleaner`?

**Answer:** `Cleaner` (Java 9+) is a modern replacement for `finalize()`. It allows registering cleanup actions that run when an object becomes phantom reachable. More reliable and performant than `finalize()`.

### Q22. What is the difference between `SoftReference` and `WeakReference`?

**Answer:**
- **SoftReference**: Object is GC'd only when memory is low. Good for caches.
- **WeakReference**: Object is GC'd on the next GC cycle, regardless of memory. Good for canonicalized mappings.

### Q23. Can we force garbage collection in Java?

**Answer:** No. `System.gc()` and `Runtime.gc()` are only suggestions. The JVM decides whether and when to run GC based on its internal heuristics.

### Q24. What is the Eden space?

**Answer:** Eden space is part of the Young Generation where new objects are allocated. Most objects die here quickly (minor GC). Surviving objects are moved to Survivor spaces.

### Q25. What is the Survivor space?

**Answer:** Survivor spaces (S0 and S1) are part of the Young Generation. Objects that survive minor GC in Eden are moved to S0. After another minor GC, they move to S1. After a threshold of survivals, they are promoted to Old Generation.

### Q26. What is promotion in GC?

**Answer:** Promotion is the process of moving objects from Young Generation to Old Generation. Objects that survive multiple GC cycles in Young Generation are promoted to Old Generation.

### Q27. What is the PermGen vs Metaspace?

**Answer:**
- **PermGen** (Java 7 and earlier): Fixed-size area for class metadata. Could cause `OutOfMemoryError: PermGen space`.
- **Metaspace** (Java 8+): Uses native memory, auto-expanding. Replaced PermGen. More flexible but can still run out of native memory.

### Q28. What is a stop-the-world event?

**Answer:** A stop-the-world event occurs when all application threads are paused while GC runs. All GC algorithms have some stop-the-world phase, but concurrent collectors (CMS, G1, ZGC) minimize it.

### Q29. What is `jstat`?

**Answer:** `jstat` is a JVM command-line tool for monitoring GC and heap statistics. Example:
```bash
jstat -gc <pid> 1000
```
Shows GC statistics every 1000ms.

### Q30. What is `jmap`?

**Answer:** `jmap` is a JVM tool for generating heap dumps. Example:
```bash
jmap -dump:format=b,file=heap.hprof <pid>
```

---

## Key Takeaways

1. **Garbage Collection** is automatic memory management in Java.
2. **Object eligibility** for GC occurs when no references exist.
3. **`System.gc()`** is a suggestion, not a command.
4. **`finalize()`** is deprecated; use `try-with-resources` or `Cleaner`.
5. **Memory leaks** happen when objects are unintentionally retained.
6. **Serial GC** is single-threaded; **Parallel GC** is multi-threaded.
7. **G1 GC** is the default from Java 9+.
8. **Heap** is divided into Young (Eden, S0, S1) and Old generations.
9. **Mark-Sweep** may fragment; **Mark-Compact** avoids fragmentation.
10. **Soft references** are for caches; **Weak references** for temporary mappings.
11. **Phantom references** are for cleanup tracking after finalization.
12. **`ReferenceQueue`** receives notifications when references are cleared.
13. **JVM tuning** options: `-Xms`, `-Xmx`, `-XX:+UseG1GC`.
14. **WeakHashMap** uses weak keys and auto-removes entries.
15. **Always close resources** explicitly rather than relying on `finalize()`.
16. **Island of isolation**: circular references with no external root are GC-eligible.
17. **Object promotion**: surviving objects move from Young to Old generation.
18. **Stop-the-world**: all threads pause during GC phases.
19. **Heap dumps** and profiling tools help diagnose memory leaks.
20. **Modern Java** prefers `Cleaner` and `try-with-resources` over `finalize()`.

---

**Happy coding! 🚀**

*Understanding Garbage Collection is crucial for writing efficient, memory-safe Java applications — master it to avoid leaks and optimize performance.*
