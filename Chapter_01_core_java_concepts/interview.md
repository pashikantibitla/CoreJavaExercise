# Core Java Concepts — Interview Questions

> **Source:** YouTube Playlist — [Core Java with OCJP/SCJP by Durga Sir](https://www.youtube.com/playlist?list=PLd3UqWTnYXOmx_J1774ukG_rvrpyWczm0)  
> **Level:** Low to Highly Severe  
> **Coverage:** All 100+ videos, every concept

---

## Table of Contents

1. [Low Level (Basic)](#1-low-level-basic)
2. [Medium Level](#2-medium-level)
3. [High Level (Advanced)](#3-high-level-advanced)
4. [Highly Severe Level (Expert)](#4-highly-severe-level-expert)

---

## 1. Low Level (Basic)

### Q1. What is Java?

**Answer:** Java is a high-level, object-oriented, platform-independent programming language. It follows "Write Once, Run Anywhere" (WORA) principle through JVM (Java Virtual Machine). Key features: platform-independent, object-oriented, robust, secure, multithreaded, and automatic memory management.

### Q2. What is the difference between JDK, JRE, and JVM?

**Answer:**
- **JDK (Java Development Kit)**: Contains JRE + development tools (compiler, debugger, etc.). Used for developing Java applications.
- **JRE (Java Runtime Environment)**: Contains JVM + core libraries. Used for running Java applications.
- **JVM (Java Virtual Machine)**: Executes Java bytecode. Platform-dependent, converts bytecode to machine code.

```
JDK = JRE + Development Tools
JRE = JVM + Core Libraries
JVM = Runtime Engine
```

### Q3. What are the main features of Java?

**Answer:**
1. Platform-independent (JVM)
2. Object-oriented
3. Simple (no pointers, no operator overloading)
4. Robust (strong memory management, exception handling)
5. Secure (no direct memory access, bytecode verifier)
6. Multithreaded
7. Distributed (RMI, EJB, CORBA)
8. Dynamic (runtime class loading)
9. Architectural-neutral
10. Portable

### Q4. What is a class?

**Answer:** A class is a blueprint/template for creating objects. It defines properties (fields/variables) and behaviors (methods). Example: `Student` class defines `name`, `age`, and `study()` method.

### Q5. What is an object?

**Answer:** An object is an instance of a class. It occupies memory and has state and behavior. Example: `Student s = new Student();` creates a Student object.

### Q6. What are the differences between `==` and `.equals()`?

**Answer:**
- `==` compares references (memory addresses) for objects, values for primitives.
- `.equals()` compares content (can be overridden).

```java
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2);      // false (different objects)
System.out.println(s1.equals(s2));  // true (same content)

String s3 = "Hello";
String s4 = "Hello";
System.out.println(s3 == s4);      // true (same pool reference)
```

### Q7. What are Java data types?

**Answer:**
- **Primitive (8)**: byte, short, int, long, float, double, char, boolean
- **Reference**: String, Array, Class, Interface, Enum, etc.

| Type | Size | Range |
|------|------|-------|
| byte | 1 byte | -128 to 127 |
| short | 2 bytes | -32768 to 32767 |
| int | 4 bytes | -2^31 to 2^31-1 |
| long | 8 bytes | -2^63 to 2^63-1 |
| float | 4 bytes | ±3.4028e+38 |
| double | 8 bytes | ±1.7977e+308 |
| char | 2 bytes | 0 to 65535 |
| boolean | 1 bit | true/false |

### Q8. What is the difference between `break` and `continue`?

**Answer:**
- `break`: Exit the loop/switch immediately.
- `continue`: Skip the current iteration and continue with the next.

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) break;      // Exit loop (prints 1, 2)
    if (i == 3) continue;   // Skip 3 (prints 1, 2, 4, 5)
    System.out.println(i);
}
```

### Q9. What is the difference between `while` and `do-while`?

**Answer:**
- `while`: Condition checked before execution. Minimum 0 executions.
- `do-while`: Condition checked after execution. Minimum 1 execution.

```java
while (false) { }      // Never executes
do { } while (false); // Executes once
```

### Q10. What is an array?

**Answer:** An array is a fixed-size, indexed collection of homogeneous elements. Arrays are objects in Java.

```java
int[] arr = new int[5];           // Declaration
int[] arr = {1, 2, 3, 4, 5};    // Initialization
arr.length;                       // Length property
```

### Q11. What is the difference between `length` and `length()`?

**Answer:**
- `length`: Property of array (not a method).
- `length()`: Method of String.
- `size()`: Method of Collection.

```java
int[] arr = new int[5];
System.out.println(arr.length);      // 5

String s = "Hello";
System.out.println(s.length());       // 5

List<Integer> list = new ArrayList<>();
System.out.println(list.size());      // 0
```

### Q12. What is method overloading?

**Answer:** Method overloading: same method name, different parameters (number, type, or order). Compile-time polymorphism.

```java
void add(int a, int b) { }
void add(int a, int b, int c) { }
void add(double a, double b) { }
```

### Q13. What is method overriding?

**Answer:** Method overriding: same method signature in parent and child. Child provides specific implementation. Runtime polymorphism.

```java
class Parent {
    void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    @Override
    void show() { System.out.println("Child"); }
}
```

### Q14. What is inheritance?

**Answer:** Inheritance: child class inherits properties and methods from parent class. Promotes code reuse. `extends` keyword.

```java
class Animal { }
class Dog extends Animal { }  // Dog IS-A Animal
```

### Q15. What is the difference between `this` and `super`?

**Answer:**
- `this`: Refers to current object. Access current class members, call current constructor.
- `super`: Refers to parent object. Access parent class members, call parent constructor.

```java
class Parent {
    int x = 10;
}
class Child extends Parent {
    int x = 20;
    void display() {
        System.out.println(this.x);   // 20
        System.out.println(super.x);  // 10
    }
}
```

### Q16. What is encapsulation?

**Answer:** Encapsulation: binding data and methods together, hiding internal details. Achieved through private variables + public getters/setters.

```java
public class Account {
    private double balance;  // Hidden
    
    public double getBalance() { return balance; }
    public void deposit(double amount) { balance += amount; }
}
```

### Q17. What is abstraction?

**Answer:** Abstraction: hiding implementation details, showing only essential features. Achieved through abstract classes and interfaces.

```java
abstract class Vehicle {
    abstract void start();  // Abstract (no body)
    void stop() { }         // Concrete (has body)
}
```

### Q18. What is polymorphism?

**Answer:** Polymorphism: "many forms". Same method behaves differently.
- **Compile-time**: Method overloading.
- **Runtime**: Method overriding.

```java
// Runtime polymorphism
Animal a = new Dog();
a.makeSound();  // "Dog barking" (decided at runtime)
```

### Q19. What is an abstract class?

**Answer:** Abstract class: cannot be instantiated, may have abstract methods. Must be extended.

```java
abstract class Animal {
    abstract void makeSound();  // No body
    void sleep() { }             // Has body
}
// Animal a = new Animal();  // ❌ ERROR
```

### Q20. What is an interface?

**Answer:** Interface: pure abstraction (all methods abstract until Java 7). Defines contract. Implemented by classes.

```java
interface Drawable {
    void draw();  // public abstract by default
}
class Circle implements Drawable {
    public void draw() { System.out.println("Drawing circle"); }
}
```

### Q21. What is the difference between abstract class and interface?

**Answer:**

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Methods | Abstract + Concrete | Abstract (Java 7), Default/Static (Java 8+) |
| Variables | Any | public static final only |
| Constructor | Yes | No |
| Multiple inheritance | No | Yes (type) |
| extends/implements | extends | implements |
| Use case | Partial implementation | Full contract |

### Q22. What is the `main` method signature?

**Answer:** `public static void main(String[] args)`
- `public`: JVM can access from anywhere.
- `static`: JVM can call without creating object.
- `void`: JVM doesn't expect return value.
- `main`: Name configured in JVM.
- `String[] args`: Command line arguments.

### Q23. What is `static`?

**Answer:** `static` belongs to class, not object. Shared by all instances.

```java
class Counter {
    static int count = 0;  // Shared
    Counter() { count++; }
}
// Counter.count = 3 (after 3 objects)
```

### Q24. What is `final`?

**Answer:** `final`: cannot be changed.
- `final` variable: cannot be reassigned.
- `final` method: cannot be overridden.
- `final` class: cannot be subclassed.

### Q25. What is the difference between `final`, `finally`, and `finalize`?

**Answer:**
- `final`: keyword (variable, method, class).
- `finally`: block (always executes after try-catch).
- `finalize()`: method (called by GC before object destruction).

---

## 2. Medium Level

### Q26. What is the difference between `ArrayList` and `LinkedList`?

**Answer:**

| Feature | ArrayList | LinkedList |
|---------|-----------|------------|
| Internal | Dynamic array | Doubly linked list |
| Access | O(1) random access | O(n) sequential access |
| Insertion/Deletion | O(n) (shifting) | O(1) (if node known) |
| Memory | Less overhead | More overhead (pointers) |
| Use case | Frequent access | Frequent insertion/deletion |

### Q27. What is the difference between `HashMap` and `Hashtable`?

**Answer:**

| Feature | HashMap | Hashtable |
|---------|---------|-----------|
| Thread-safe | No | Yes (synchronized) |
| Null key/value | One null key, many null values | No null key or value |
| Performance | Faster | Slower (synchronization) |
| Iterator | Fail-fast | Fail-safe |
| Introduced | Java 1.2 | Java 1.0 |

### Q28. What is `String` immutability?

**Answer:** String objects are immutable (cannot be modified after creation). Any modification creates a new String object.

```java
String s = "Hello";
s = s + " World";  // Creates new object, s points to new
// Original "Hello" still in string pool

// Benefits:
// 1. Security (cannot change)
// 2. Thread-safe (no synchronization needed)
// 3. String pool (reuse)
// 4. HashCode caching
```

### Q29. What is `StringBuilder` vs `StringBuffer`?

**Answer:**

| Feature | StringBuilder | StringBuffer |
|---------|---------------|--------------|
| Mutable | Yes | Yes |
| Thread-safe | No | Yes (synchronized) |
| Performance | Faster | Slower |
| Introduced | Java 5 | Java 1.0 |

Use `StringBuilder` for single-threaded, `StringBuffer` for multi-threaded.

### Q30. What is the difference between `throw` and `throws`?

**Answer:**
- `throw`: Manually throw exception. Inside method body.
- `throws`: Declare exception. In method signature.

```java
void method() throws IOException {  // Declare
    throw new IOException();         // Throw
}
```

### Q31. What is the difference between checked and unchecked exceptions?

**Answer:**
- **Checked**: Must handle. Compile-time check. `extends Exception` (not RuntimeException).
- **Unchecked**: Optional. Runtime check. `extends RuntimeException`.

```java
// Checked: IOException, SQLException, ClassNotFoundException
// Unchecked: NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException
```

### Q32. What is `try-with-resources`?

**Answer:** Auto-closes resources that implement `AutoCloseable`. Java 7+.

```java
// Old way:
try {
    BufferedReader br = new BufferedReader(new FileReader("file.txt"));
    String line = br.readLine();
    br.close();
} catch (IOException e) { }

// New way:
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
} catch (IOException e) { }
// br.close() called automatically
```

### Q33. What is `synchronized`?

**Answer:** `synchronized` ensures thread-safe access. Only one thread can execute synchronized block at a time.

```java
// Synchronized method
public synchronized void method() { }

// Synchronized block
synchronized (this) { }

// Static synchronized
public static synchronized void method() { }
```

### Q34. What is `volatile`?

**Answer:** `volatile` ensures visibility. Variable is read from main memory, not thread cache.

```java
volatile boolean flag = false;

// Thread 1:
flag = true;  // Visible to all threads immediately

// Thread 2:
while (!flag) { }  // Sees updated value

// Does NOT guarantee atomicity (use AtomicInteger or synchronized)
```

### Q35. What is the difference between `sleep()` and `wait()`?

**Answer:**

| Feature | sleep() | wait() |
|---------|---------|--------|
| Class | Thread | Object |
| Lock | Does not release | Releases |
| Wake up | After time | notify()/notifyAll() |
| Usage | Anywhere | Synchronized block |
| Exception | InterruptedException | InterruptedException |

### Q36. What is `join()`?

**Answer:** `join()` waits for thread to complete.

```java
Thread t = new Thread(() -> {
    // Long task
});
t.start();
t.join();  // Main thread waits for t to finish
```

### Q37. What is a daemon thread?

**Answer:** Background thread. JVM exits when only daemon threads remain.

```java
Thread t = new Thread(() -> { });
t.setDaemon(true);  // Before start()
t.start();

// Examples: GC, finalizer, reference handler
```

### Q38. What is `ThreadLocal`?

**Answer:** Thread-specific variable. Each thread has its own copy.

```java
ThreadLocal<Integer> local = new ThreadLocal<>();
local.set(10);  // Only current thread sees this

// Use cases: User session, database connection, transaction context
```

### Q39. What is `hashCode()` and `equals()` contract?

**Answer:**
1. If `equals()` returns true, `hashCode()` must be same.
2. If `hashCode()` is same, `equals()` may or may not be true.
3. If `equals()` is overridden, `hashCode()` must also be overridden.

```java
// Contract violation example:
class Person {
    String name;
    
    @Override
    public boolean equals(Object obj) {
        return ((Person)obj).name.equals(this.name);
    }
    // ❌ hashCode() not overridden → contract violation
}
```

### Q40. What is `Comparator` vs `Comparable`?

**Answer:**
- `Comparable`: Natural ordering. `compareTo()`. Class implements it.
- `Comparator`: Custom ordering. `compare()`. External class.

```java
// Comparable
class Student implements Comparable<Student> {
    int id;
    public int compareTo(Student s) {
        return this.id - s.id;
    }
}

// Comparator
Comparator<Student> byName = (s1, s2) -> s1.name.compareTo(s2.name);
Collections.sort(students, byName);
```

---

## 3. High Level (Advanced)

### Q41. What is `instanceof` operator?

**Answer:** Checks if object is of specific type. Returns true/false.

```java
String s = "Hello";
System.out.println(s instanceof String);   // true
System.out.println(s instanceof Object);   // true
System.out.println(null instanceof String); // false (always)

// Cannot use with incompatible types:
// System.out.println(s instanceof Integer);  // ❌ Compilation error
```

### Q42. What is `strictfp`?

**Answer:** Ensures consistent floating point results across platforms.

```java
public strictfp class StrictFP {
    public strictfp void method() {
        double d = 10.0 / 3.0;
    }
}
```

### Q43. What is the difference between `&` and `&&`?

**Answer:**
- `&`: Bitwise AND. Always evaluates both sides. Can be used with boolean.
- `&&`: Short-circuit AND. Evaluates second only if first is true.

```java
if (x != 0 && 10/x > 5) { }  // Safe
if (x != 0 & 10/x > 5) { }   // ❌ ArithmeticException if x=0
```

### Q44. What is `native` keyword?

**Answer:** `native` method is implemented in another language (C/C++). No body in Java.

```java
public native void nativeMethod();

// Used for:
// 1. System-level operations
// 2. Performance-critical code
// 3. Accessing hardware

// Requires JNI (Java Native Interface)
```

### Q45. What is `transient`?

**Answer:** `transient` variable is not serialized.

```java
public class User implements Serializable {
    String username;           // Serialized
    transient String password; // NOT serialized
}

// After deserialization: password = null
```

### Q46. What is `clone()`?

**Answer:** Creates a copy of an object.

```java
// Shallow copy: Copies reference (not object)
// Deep copy: Creates new objects

class Student implements Cloneable {
    int id;
    String name;
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();  // Shallow copy
    }
}
```

### Q47. What is the difference between shallow copy and deep copy?

**Answer:**
- **Shallow copy**: Copies reference. Changes reflect in both.
- **Deep copy**: Creates new objects. Independent copies.

```java
// Shallow copy:
Student s1 = new Student();
Student s2 = s1;  // Same reference

// Deep copy:
Student s2 = (Student) s1.clone();  // New object
```

### Q48. What is `finalize()`?

**Answer:** Called by GC before object is destroyed. Cleanup code.

```java
protected void finalize() throws Throwable {
    // Cleanup: close resources, release connections
    super.finalize();
}

// Not guaranteed to run:
// 1. JVM may not run GC
// 2. finalize() may not be called
// 3. Object may be resurrected
// Use try-with-resources or cleanup methods instead
```

### Q49. What is `System.gc()`?

**Answer:** Suggests JVM to run Garbage Collector. Not guaranteed.

```java
System.gc();  // Suggestion only

// GC runs automatically when:
// 1. Heap is low
// 2. JVM decides
// 3. Explicit request (System.gc() or Runtime.gc())
```

### Q50. What is `Class.forName()`?

**Answer:** Loads and initializes class dynamically.

```java
Class<?> c = Class.forName("java.util.ArrayList");
ArrayList<?> list = (ArrayList<?>) c.newInstance();

// Use cases:
// 1. JDBC driver loading
// 2. Dynamic class loading
// 3. Reflection
```

### Q51. What is reflection?

**Answer:** Reflection allows inspecting and modifying classes, methods, fields at runtime.

```java
Class<?> c = Class.forName("java.util.ArrayList");
Method[] methods = c.getDeclaredMethods();
Field[] fields = c.getDeclaredFields();
Constructor<?>[] constructors = c.getDeclaredConstructors();

// Can invoke private methods, create objects, modify fields
```

### Q52. What is `ClassNotFoundException` vs `NoClassDefFoundError`?

**Answer:**
- `ClassNotFoundException`: Checked exception. Class not found at runtime (e.g., `Class.forName()`).
- `NoClassDefFoundError`: Error. Class was available at compile time but not at runtime.

### Q53. What is `OutOfMemoryError`?

**Answer:** JVM cannot allocate object. Heap is full.

```java
// Causes:
// 1. Memory leak
// 2. Too many objects
// 3. Large objects

// Solutions:
// 1. Increase heap size (-Xmx)
// 2. Fix memory leaks
// 3. Use weak references
```

### Q54. What is `StackOverflowError`?

**Answer:** Stack overflow. Usually caused by infinite recursion.

```java
void infinite() {
    infinite();  // Infinite recursion
}

// Solutions:
// 1. Fix recursion (base case)
// 2. Increase stack size (-Xss)
```

### Q55. What is `Serializable`?

**Answer:** Marker interface for object serialization. Converts object to byte stream.

```java
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    transient String password;  // Not serialized
}

// serialVersionUID: version control for serialization
```

---

## 4. Highly Severe Level (Expert)

### Q56. What is the `synchronized` keyword internals?

**Answer:** `synchronized` uses monitor locks (intrinsic locks). Each object has a monitor. Thread acquires monitor before entering synchronized block.

```java
// Object-level lock: one thread per object
public synchronized void method() { }

// Class-level lock: one thread per class
public static synchronized void method() { }

// Monitor operations:
// 1. monitorenter: acquire lock
// 2. monitorexit: release lock

// JVM maintains:
// - Entry set: threads waiting for lock
// - Wait set: threads in wait()
```

### Q57. What is `ReentrantLock` vs `synchronized`?

**Answer:**

| Feature | synchronized | ReentrantLock |
|---------|--------------|---------------|
| Automatic | Yes | No (manual) |
| Interruptible | No | Yes (lockInterruptibly) |
| Timeout | No | Yes (tryLock) |
| Fair | No | Yes (constructor) |
| Condition | One (implicit) | Multiple (newCondition) |
| Performance | Good | Better for high contention |

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock();  // Must unlock
}
```

### Q58. What is `ReadWriteLock`?

**Answer:** Allows multiple readers or one writer.

```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();
Lock readLock = rwLock.readLock();
Lock writeLock = rwLock.writeLock();

// Multiple threads can read simultaneously
readLock.lock();
try {
    // Read
} finally {
    readLock.unlock();
}

// Only one thread can write
writeLock.lock();
try {
    // Write
} finally {
    writeLock.unlock();
}
```

### Q59. What is `CompletableFuture`?

**Answer:** Asynchronous programming with non-blocking operations.

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return "Hello";
});

future.thenApply(s -> s + " World")
      .thenAccept(System.out::println);

// Chaining, combining, error handling
// Non-blocking, functional style
```

### Q60. What is `ForkJoinPool`?

**Answer:** Implements divide-and-conquer using work-stealing algorithm.

```java
ForkJoinPool pool = new ForkJoinPool();

// ForkJoinTask: RecursiveTask (returns result) or RecursiveAction (no result)
// Work-stealing: idle threads steal tasks from busy threads

// Use case: Parallel sorting, searching, large data processing
```

### Q61. What is `CountDownLatch` vs `CyclicBarrier`?

**Answer:**

| Feature | CountDownLatch | CyclicBarrier |
|---------|---------------|---------------|
| Purpose | Wait for N threads | Threads wait for each other |
| Reuse | One-time | Reusable |
| Counter | Decrements | Resets |
| Action | None | Runnable action |

```java
CountDownLatch latch = new CountDownLatch(3);
// Each thread calls latch.countDown()
// latch.await() waits until count reaches 0

CyclicBarrier barrier = new CyclicBarrier(3);
// Each thread calls barrier.await()
// All threads wait until count reaches 0, then continue together
```

### Q62. What is `Semaphore`?

**Answer:** Controls access to limited resources.

```java
Semaphore semaphore = new Semaphore(3);  // 3 permits

semaphore.acquire();   // Get permit (blocks if none)
// Use resource
semaphore.release();   // Release permit

// Max 3 threads access resource simultaneously
```

### Q63. What is `AtomicInteger`?

**Answer:** Thread-safe integer using CAS (Compare-And-Swap). Lock-free.

```java
AtomicInteger counter = new AtomicInteger(0);

// Atomic operations
counter.incrementAndGet();  // ++i
counter.getAndIncrement();  // i++
counter.addAndGet(5);        // i += 5
counter.compareAndSet(0, 1);  // CAS

// Lock-free, thread-safe
```

### Q64. What is `volatile` vs `AtomicInteger`?

**Answer:**
- `volatile`: Visibility guarantee, no atomicity.
- `AtomicInteger`: Visibility + atomicity (CAS).

```java
volatile int count = 0;
// count++;  // NOT atomic (read + increment + write)

AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();  // Atomic
```

### Q65. What is `ConcurrentHashMap`?

**Answer:** Thread-safe HashMap with segment-level locking (Java 7) or bucket-level locking (Java 8+).

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

// Thread-safe operations
map.put("key", 1);
map.computeIfAbsent("key", k -> 2);
map.merge("key", 1, Integer::sum);

// Better performance than synchronized HashMap
// Java 8+: Uses CAS and synchronized on first node
```

### Q66. What is `CopyOnWriteArrayList`?

**Answer:** Thread-safe ArrayList. Copy on write operation.

```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

list.add("A");  // Creates new copy, replaces reference
list.add("B");

// Iterators: snapshot of list at creation time
// No ConcurrentModificationException
// Use case: Read-heavy, write-rare scenarios
```

### Q67. What is `BlockingQueue`?

**Answer:** Thread-safe queue with blocking operations.

```java
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

// Producer
queue.put(10);  // Blocks if queue is full

// Consumer
Integer value = queue.take();  // Blocks if queue is empty

// Implementations: ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue, DelayQueue, SynchronousQueue
```

### Q68. What is `ThreadPoolExecutor`?

**Answer:** Custom thread pool with configurable parameters.

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    5,                      // Core pool size
    10,                     // Maximum pool size
    60L,                    // Keep alive time
    TimeUnit.SECONDS,       // Time unit
    new LinkedBlockingQueue<>(100)  // Work queue
);

// Core threads: always alive
// Max threads: created when queue is full
// Keep alive: idle non-core threads terminate after this time
// Queue: holds tasks waiting for execution
```

### Q69. What is `Future`?

**Answer:** Represents result of asynchronous computation.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
Future<Integer> future = executor.submit(() -> {
    return 42;
});

Integer result = future.get();  // Blocks until result available
// future.get(5, TimeUnit.SECONDS);  // With timeout
// future.cancel(true);  // Cancel task
// future.isDone();      // Check if completed
```

### Q70. What is `Callable` vs `Runnable`?

**Answer:**

| Feature | Runnable | Callable |
|---------|----------|----------|
| Return | void | T |
| Exception | Cannot throw checked | Can throw checked |
| Method | run() | call() |
| Future | Future<?> | Future<T> |

```java
// Runnable
Runnable r = () -> System.out.println("Running");

// Callable
Callable<Integer> c = () -> {
    return 42;
};
```

### Q71. What is `ThreadGroup`?

**Answer:** Group of threads for management.

```java
ThreadGroup group = new ThreadGroup("MyGroup");
Thread t = new Thread(group, () -> { });

// Methods:
group.interrupt();        // Interrupt all threads
group.setMaxPriority(8);  // Set max priority
group.activeCount();      // Active threads count

// Hierarchy: system (root) → main → custom groups
```

### Q72. What is `InheritableThreadLocal`?

**Answer:** Child thread inherits parent's value.

```java
InheritableThreadLocal<Integer> inheritable = new InheritableThreadLocal<>();
inheritable.set(10);

Thread child = new Thread(() -> {
    System.out.println(inheritable.get());  // 10 (inherited)
});
```

### Q73. What is `WeakReference`, `SoftReference`, `PhantomReference`?

**Answer:**

| Reference | GC Behavior | Use case |
|-----------|-------------|----------|
| Strong | Not collected | Normal objects |
| Weak | Collected when GC runs | Caches (WeakHashMap) |
| Soft | Collected when memory low | Caches |
| Phantom | After object is finalized | Cleanup tracking |

```java
WeakReference<Object> weak = new WeakReference<>(new Object());
System.gc();
Object obj = weak.get();  // May be null
```

### Q74. What is `ClassLoader`?

**Answer:** Loads class files into JVM.

```java
// Types:
// 1. Bootstrap ClassLoader (jre/lib)
// 2. Extension ClassLoader (jre/lib/ext)
// 3. Application ClassLoader (classpath)
// 4. Custom ClassLoader (user-defined)

// Delegation model:
// Application → Extension → Bootstrap
// If not found, parent loads; if not found, child loads

ClassLoader loader = Thread.currentThread().getContextClassLoader();
Class<?> c = loader.loadClass("java.util.ArrayList");
```

### Q75. What is `ServiceLoader`?

**Answer:** Service provider loading mechanism (SPI). Loads implementations of service interface.

```java
// Service interface
public interface Logger {
    void log(String message);
}

// Service provider configuration file:
// META-INF/services/Logger
// com.example.FileLogger
// com.example.ConsoleLogger

// Load providers:
ServiceLoader<Logger> loaders = ServiceLoader.load(Logger.class);
for (Logger logger : loaders) {
    logger.log("Hello");
}
```

### Q76. What is `Instrumentation`?

**Answer:** API for modifying bytecode at runtime. Used for profiling, monitoring, AOP.

```java
// Java agent: premain() or agentmain()
// Can modify class bytecode using ASM, ByteBuddy, etc.

public class MyAgent {
    public static void premain(String args, Instrumentation inst) {
        // Transform bytecode
    }
}
```

### Q77. What is `Java Agent`?

**Answer:** Instrumentation tool that runs before main().

```java
// Java agent with premain()
public class MyAgent {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Agent started");
    }
}

// Run with: java -javaagent:agent.jar -jar app.jar
```

### Q78. What is `JVM` memory model?

**Answer:**

```
JVM Memory:
┌─────────────────────────────────────┐
│ Heap (all objects)                  │
│   ├─ Young Generation (Eden, S0, S1)│
│   └─ Old Generation (Tenured)       │
├─────────────────────────────────────┤
│ Method Area (Class metadata)         │
│   ├─ Constant pool                  │
│   ├─ Field/method data              │
│   └─ Static variables               │
├─────────────────────────────────────┤
│ Stack (method calls, local vars)     │
│   ├─ Each thread has its own stack   │
│   └─ Stack frames                     │
├─────────────────────────────────────┤
│ PC Register (program counter)         │
│ Native Method Stack                   │
│ Direct Memory (NIO)                    │
└─────────────────────────────────────┘
```

### Q79. What is `Garbage Collection`?

**Answer:** Automatic memory management. Removes unreachable objects.

```
GC Algorithms:
1. Serial GC: Single thread, simple
2. Parallel GC: Multiple threads, throughput
3. CMS (Concurrent Mark Sweep): Low pause
4. G1 (Garbage First): Balanced, default (Java 9+)
5. ZGC: Ultra-low latency (Java 11+)
6. Shenandoah: Low pause (Java 12+)

GC Roots:
- Local variables
- Active threads
- Static variables
- JNI references
```

### Q80. What is ` finalize()` vs `Cleaner` vs `PhantomReference`?

**Answer:**

| Mechanism | When | Use case |
|-----------|------|----------|
| finalize() | Before GC | Deprecated (Java 9) |
| Cleaner | Explicit | Native resource cleanup |
| PhantomReference | After finalize | Track object destruction |

```java
// Cleaner (Java 9+)
Cleaner cleaner = Cleaner.create();
cleaner.register(object, () -> {
    // Cleanup action
});

// PhantomReference
ReferenceQueue<Object> queue = new ReferenceQueue<>();
PhantomReference<Object> phantom = new PhantomReference<>(obj, queue);
// When obj is GC'd, phantom is added to queue
```

---

**Happy coding! 🚀**

*Practice these interview questions from low to highly severe levels to master Core Java for any interview.*
