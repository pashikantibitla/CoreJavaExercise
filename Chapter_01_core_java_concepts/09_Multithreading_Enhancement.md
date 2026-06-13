# 09 — Multithreading Enhancement

> **Videos:** 95–99  
> **Topics:** Thread Group, java.util.concurrent Package, Locks, Thread Pools, Thread Local

---

## Table of Contents

1. [Thread Group](#1-thread-group)
2. [java.util.concurrent Package](#2-javautilconcurrent-package)
3. [java.util.concurrent.locks](#3-javautilconcurrentlocks)
4. [Java Thread Pools](#4-java-thread-pools)
5. [Java Thread Local](#5-java-thread-local)
6. [Interview Questions](#6-interview-questions)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. Thread Group

### What is Thread Group?

```java
// ThreadGroup: Group of threads
// Can manage multiple threads together

ThreadGroup group = new ThreadGroup("MyGroup");

Thread t1 = new Thread(group, () -> {
    System.out.println("Thread 1");
});
Thread t2 = new Thread(group, () -> {
    System.out.println("Thread 2");
});

// Start all threads
// group.start();  // ❌ No such method

// Interrupt all threads
// group.interrupt();  // ✅

// Set max priority for group
// group.setMaxPriority(8);

// Get active count
// int count = group.activeCount();

// Parent-child hierarchy
ThreadGroup parent = new ThreadGroup("Parent");
ThreadGroup child = new ThreadGroup(parent, "Child");

// System thread groups:
// system (root)
//   └── main
//         └── main thread
```

### ThreadGroup Methods

```java
ThreadGroup group = new ThreadGroup("MyGroup");

// Methods:
group.activeCount();          // Number of active threads
group.activeGroupCount();     // Number of active subgroups
group.interrupt();            // Interrupt all threads
group.setMaxPriority(8);      // Set max priority
group.setDaemon(true);        // Set daemon status

// Destroy group (if no threads)
// group.destroy();

// Get parent
group.getParent();

// Check access
// group.checkAccess();
```

---

## 2. java.util.concurrent Package

### Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                    java.util.concurrent PACKAGE                       │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Interfaces:                                                   │   │
│  │   Executor, ExecutorService, Callable, Future, BlockingQueue,  │   │
│  │   Lock, Condition, ReadWriteLock                              │   │
│  │                                                               │   │
│  │ Classes:                                                      │   │
│  │   Executors, ThreadPoolExecutor, ScheduledThreadPoolExecutor, │   │
│  │   FutureTask, CompletableFuture, CountDownLatch, CyclicBarrier│   │
│  │   Semaphore, ReentrantLock, ReentrantReadWriteLock,         │   │
│  │   ConcurrentHashMap, CopyOnWriteArrayList, AtomicInteger,   │   │
│  │   AtomicLong, AtomicBoolean                                   │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Executor Framework

```java
import java.util.concurrent.*;

// Executor: Submit tasks for execution
Executor executor = Executors.newSingleThreadExecutor();
executor.execute(() -> {
    System.out.println("Task executed");
});

// ExecutorService: More control
ExecutorService service = Executors.newFixedThreadPool(5);

// Submit Runnable
Future<?> future = service.submit(() -> {
    System.out.println("Runnable task");
});

// Submit Callable
Future<Integer> result = service.submit(() -> {
    return 42;
});

// Get result
Integer value = result.get();  // 42

// Shutdown
service.shutdown();
```

### Callable vs Runnable

```java
// Runnable: No return, no exception
Runnable runnable = () -> {
    System.out.println("Running");
};

// Callable: Returns value, can throw exception
Callable<Integer> callable = () -> {
    return 42;
};

// Submit to ExecutorService
Future<?> future1 = service.submit(runnable);
Future<Integer> future2 = service.submit(callable);

// Get result
// future.get() blocks until result is available
// future.get(timeout, TimeUnit) with timeout
```

---

## 3. java.util.concurrent.locks

### Lock Interface

```java
import java.util.concurrent.locks.*;

// ReentrantLock: Explicit lock
Lock lock = new ReentrantLock();

// Lock
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock();  // Always unlock in finally
}

// TryLock (non-blocking)
if (lock.tryLock()) {
    try {
        // Critical section
    } finally {
        lock.unlock();
    }
} else {
    // Do something else
}

// TryLock with timeout
if (lock.tryLock(5, TimeUnit.SECONDS)) {
    try {
        // Critical section
    } finally {
        lock.unlock();
    }
}
```

### ReentrantLock Features

```java
// ReentrantLock: Same thread can lock multiple times
Lock lock = new ReentrantLock();

// Reentrant
lock.lock();
lock.lock();  // Same thread, OK

// Unlock same number of times
lock.unlock();
lock.unlock();

// Fair lock (first-come-first-served)
Lock fairLock = new ReentrantLock(true);

// Check lock status
ReentrantLock rlock = (ReentrantLock) lock;
rlock.isLocked();         // Is locked?
rlock.isHeldByCurrentThread();  // Held by current thread?
rlock.getHoldCount();     // Number of holds
rlock.getQueueLength();   // Threads waiting for lock
```

### ReadWriteLock

```java
// ReadWriteLock: Separate locks for read and write
ReadWriteLock rwLock = new ReentrantReadWriteLock();

Lock readLock = rwLock.readLock();
Lock writeLock = rwLock.writeLock();

// Multiple readers, single writer
readLock.lock();
try {
    // Read (multiple threads allowed)
} finally {
    readLock.unlock();
}

writeLock.lock();
try {
    // Write (exclusive)
} finally {
    writeLock.unlock();
}

// Use case: Read-heavy data structures
```

---

## 4. Java Thread Pools

### ExecutorService Types

```java
import java.util.concurrent.*;

// 1. Fixed Thread Pool
ExecutorService fixed = Executors.newFixedThreadPool(5);
// Reuses fixed number of threads

// 2. Cached Thread Pool
ExecutorService cached = Executors.newCachedThreadPool();
// Creates new threads as needed, reuses idle threads

// 3. Single Thread Executor
ExecutorService single = Executors.newSingleThreadExecutor();
// One thread, tasks queued

// 4. Scheduled Thread Pool
ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(5);
// Schedule tasks with delay or periodically

// 5. Work Stealing Pool
ExecutorService workStealing = Executors.newWorkStealingPool();
// ForkJoinPool, uses multiple queues
```

### ScheduledExecutorService

```java
ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(5);

// Schedule once after delay
scheduled.schedule(() -> {
    System.out.println("Delayed task");
}, 5, TimeUnit.SECONDS);

// Schedule at fixed rate
scheduled.scheduleAtFixedRate(() -> {
    System.out.println("Fixed rate");
}, 0, 10, TimeUnit.SECONDS);

// Schedule with fixed delay
scheduled.scheduleWithFixedDelay(() -> {
    System.out.println("Fixed delay");
}, 0, 10, TimeUnit.SECONDS);

// Shutdown
scheduled.shutdown();
```

### ThreadPoolExecutor

```java
// Custom thread pool
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    5,                      // Core pool size
    10,                     // Maximum pool size
    60L,                    // Keep alive time
    TimeUnit.SECONDS,       // Time unit
    new LinkedBlockingQueue<>(100)  // Work queue
);

// Submit tasks
for (int i = 0; i < 100; i++) {
    final int taskId = i;
    executor.submit(() -> {
        System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
    });
}

// Shutdown
executor.shutdown();
```

---

## 5. Java Thread Local

### ThreadLocal

```java
// ThreadLocal: Thread-specific variable
// Each thread has its own copy

ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

// Thread 1
Thread t1 = new Thread(() -> {
    threadLocal.set(10);
    System.out.println("Thread 1: " + threadLocal.get());  // 10
});

// Thread 2
Thread t2 = new Thread(() -> {
    threadLocal.set(20);
    System.out.println("Thread 2: " + threadLocal.get());  // 20
});

// Main thread
threadLocal.set(30);
System.out.println("Main: " + threadLocal.get());  // 30

t1.start();
t2.start();

// Output: Main: 30
//         Thread 1: 10
//         Thread 2: 20

// Each thread sees its own value
// ThreadLocal is NOT shared
```

### ThreadLocal with Initial Value

```java
// With initial value
ThreadLocal<Integer> threadLocal = new ThreadLocal<>() {
    @Override
    protected Integer initialValue() {
        return 0;  // Default value for each thread
    }
};

// Java 8+ with lambda
ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

// Use case: User session, database connection, transaction
```

### InheritableThreadLocal

```java
// InheritableThreadLocal: Child thread inherits parent's value
InheritableThreadLocal<Integer> inheritable = new InheritableThreadLocal<>();

inheritable.set(10);

Thread child = new Thread(() -> {
    System.out.println("Child: " + inheritable.get());  // 10 (inherited)
});

child.start();
```

---

## 6. Interview Questions

### Q1. What is the difference between `synchronized` and `ReentrantLock`?

```java
// synchronized:
// - Built-in, automatic
// - Cannot interrupt
// - No timeout
// - No fairness

// ReentrantLock:
// - Explicit, manual
// - Can interrupt (lockInterruptibly)
// - Can timeout (tryLock)
// - Can be fair (first-come-first-served)
// - Can check lock status
// - Must unlock in finally
```

### Q2. What is `ThreadPoolExecutor`?

```java
// ThreadPoolExecutor: Custom thread pool
// Core pool size, max pool size, keep alive, queue

ThreadPoolExecutor executor = new ThreadPoolExecutor(
    5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100)
);

// Submit tasks
// Reuses threads, reduces creation overhead
```

### Q3. What is `Callable` vs `Runnable`?

```java
// Runnable: void run() — no return, no exception
// Callable: T call() — returns value, throws exception

// Use Callable when you need result
// Use Runnable when you don't need result

// Future: Represents result of asynchronous computation
Future<Integer> future = executor.submit(() -> 42);
Integer result = future.get();  // Blocks until result
```

### Q4. What is `CompletableFuture`?

```java
// CompletableFuture: Async programming, non-blocking
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return "Hello";
});

future.thenApply(s -> s + " World")
      .thenAccept(System.out::println);

// Chaining, combining, error handling
// Non-blocking async operations
```

### Q5. What is `CountDownLatch`?

```java
// CountDownLatch: Wait for multiple threads to complete
CountDownLatch latch = new CountDownLatch(3);

for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        System.out.println("Task done");
        latch.countDown();  // Decrement counter
    }).start();
}

latch.await();  // Wait until count reaches 0
System.out.println("All tasks completed");

// Cannot reuse — one-time use
```

### Q6. What is `CyclicBarrier`?

```java
// CyclicBarrier: Threads wait for each other at barrier
CyclicBarrier barrier = new CyclicBarrier(3);

for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        System.out.println("Before barrier");
        try {
            barrier.await();  // Wait for all threads
        } catch (Exception e) { }
        System.out.println("After barrier");
    }).start();
}

// All threads reach barrier, then continue together
// Can reuse (unlike CountDownLatch)
```

### Q7. What is `Semaphore`?

```java
// Semaphore: Control access to limited resources
Semaphore semaphore = new Semaphore(3);  // 3 permits

for (int i = 0; i < 10; i++) {
    new Thread(() -> {
        try {
            semaphore.acquire();  // Get permit
            System.out.println("Accessing resource");
            Thread.sleep(1000);
            semaphore.release();  // Release permit
        } catch (Exception e) { }
    }).start();
}

// Max 3 threads access resource simultaneously
```

### Q8. What is `ThreadLocal`?

```java
// ThreadLocal: Thread-specific variable
// Each thread has its own copy

ThreadLocal<Integer> local = new ThreadLocal<>();
local.set(10);  // Only current thread sees this

// Use cases:
// - User session in web app
// - Database connection
// - Transaction context
// - SimpleDateFormat (not thread-safe)

// Remember to remove() when done to avoid memory leak
local.remove();
```

### Q9. What is `ForkJoinPool`?

```java
// ForkJoinPool: Divide and conquer
// ForkJoinTask: RecursiveTask (returns result) or RecursiveAction (no result)

ForkJoinPool pool = new ForkJoinPool();

// ForkJoinTask: splits task into smaller subtasks
// Work-stealing: idle threads steal work from busy threads

// Use case: Parallel sorting, searching, processing
```

### Q10. What is `BlockingQueue`?

```java
// BlockingQueue: Thread-safe queue with blocking operations
// Producer-Consumer pattern

BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

// Producer
queue.put(10);  // Blocks if queue is full

// Consumer
Integer value = queue.take();  // Blocks if queue is empty

// Implementations:
// ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue
// DelayQueue, SynchronousQueue
```

---

## 7. Key Takeaways

1. **ThreadGroup**: Manage multiple threads together.
2. **java.util.concurrent**: Modern concurrency framework.
3. **Executor**: Submit tasks without managing threads.
4. **ExecutorService**: Lifecycle management of threads.
5. **Callable**: Returns value, throws exception; Runnable does not.
6. **Future**: Represents result of asynchronous computation.
7. **ReentrantLock**: Explicit lock with more features than synchronized.
8. **ReadWriteLock**: Separate locks for reading and writing.
9. **ThreadPool**: Reuse threads, reduce creation overhead.
10. **ScheduledExecutorService**: Schedule tasks with delay/period.
11. **ThreadLocal**: Thread-specific variable; each thread has own copy.
12. **CountDownLatch**: Wait for multiple threads; one-time use.
13. **CyclicBarrier**: Threads wait for each other; reusable.
14. **Semaphore**: Control access to limited resources.
15. **CompletableFuture**: Async programming, non-blocking.
16. **ForkJoinPool**: Divide and conquer, work-stealing.
17. **BlockingQueue**: Thread-safe queue for Producer-Consumer.
18. **ConcurrentHashMap**: Thread-safe map, better than synchronized HashMap.
19. **Atomic classes**: Lock-free thread-safe operations.
20. **Always shutdown** ExecutorService to prevent memory leaks.

---

**Happy coding! 🚀**

*java.util.concurrent is the modern way to handle concurrency — prefer it over raw threads.*
