# 08 — Multi Threading

> **Videos:** 81–94  
> **Topics:** Introduction, Defining Threads, Priorities, yield, join, sleep, Synchronization, Synchronized Block, Inter Thread Communication, DeadLock, Daemon Threads

---

## Table of Contents

1. [Introduction to Threads](#1-introduction-to-threads)
2. [Defining Threads](#2-defining-threads)
3. [Thread Priorities](#3-thread-priorities)
4. [yield, join, sleep](#4-yield-join-sleep)
5. [Synchronization](#5-synchronization)
6. [Synchronized Block](#6-synchronized-block)
7. [Inter Thread Communication](#7-inter-thread-communication)
8. [DeadLock and Starvation](#8-deadlock-and-starvation)
9. [Daemon Threads](#9-daemon-threads)
10. [Interview Questions](#10-interview-questions)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Introduction to Threads

### What is a Thread?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    THREAD vs PROCESS                                    │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Feature            │ Process                  │ Thread          │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Definition         │ Running program          │ Lightweight    │   │
│  │                    │                          │ process         │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Memory             │ Separate memory space    │ Shared memory   │   │
│  │                    │                          │ (same process)  │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Communication      │ Inter-process            │ Direct (shared  │   │
│  │                    │ communication (IPC)     │ memory)         │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Overhead           │ High                     │ Low             │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Creation           │ Expensive                │ Cheap           │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Context switch     │ Expensive                │ Cheap           │   │
│  └────────────────────┴──────────────────────────────────────────┘   │
│                                                                  │
│  Java Thread: Instance of java.lang.Thread                         │
│  Every Java program has at least one thread (main thread)         │
└─────────────────────────────────────────────────────────────────────┘
```

### Thread Lifecycle

```
┌─────────────────────────────────────────────────────────────────────┐
│                    THREAD LIFECYCLE                                   │
│                                                                  │
│  New ────start()──→ Runnable ───run()──→ Running ───sleep()──→  │
│    │               (ready queue)   │   │     │    │               │
│    │                 ↑____________│   │     │    │               │
│    │                    yield()        │     │    │               │
│    │                 ←___wait()__      │     │    │               │
│    │                      │           │     │    │               │
│    │                    Waiting       │     │    │               │
│    │                    (blocked)    │     │    │               │
│    │                    notify()      │     │    │               │
│    │                    notifyAll()   │     │    │               │
│    │                                    │     │    │               │
│    │─────────────────────synchronized────│     │    │               │
│    │                      │              │     │    │               │
│    │                    Blocked          │     │    │               │
│    │                      │              │     │    │               │
│    │                      │              │     │    │               │
│    └──────────────────────┴──────────────┴─────┴────┘               │
│                      Dead (terminated)                                │
│                                                                  │
│  States: New → Runnable → Running → Waiting/Blocked → Dead        │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Defining Threads

### Way 1: extends Thread

```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Child thread: " + i);
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();  // Start thread (calls run() internally)
        
        // Main thread
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
```

### Way 2: implements Runnable

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Child thread: " + i);
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
        
        // Main thread
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
```

### Way 3: Lambda (Java 8+)

```java
// Using lambda
public class LambdaThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Child thread: " + i);
            }
        });
        t.start();
    }
}
```

### Thread vs Runnable

```
┌─────────────────────────────────────────────────────────────────────┐
│                    extends Thread vs implements Runnable            │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Feature            │ extends Thread       │ implements Runnable│   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Inheritance        │ Cannot extend another│ Can extend another │   │
│  │                    │ class (single inherit)│ class             │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Code sharing       │ Each thread has     │ Multiple threads  │   │
│  │                    │ separate run()       │ share same Runnable│   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Recommended        │ Less flexible        │ More flexible      │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Use case           │ Simple cases         │ Complex cases,    │   │
│  │                    │                      │ need to extend class│   │
│  └────────────────────┴──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 3. Thread Priorities

### Priority Constants

```java
// Thread priorities: 1 (MIN) to 10 (MAX)
// Default: 5 (NORM_PRIORITY)

Thread t = new Thread();
t.setPriority(8);  // Set priority

// Constants:
Thread.MIN_PRIORITY = 1
Thread.NORM_PRIORITY = 5
Thread.MAX_PRIORITY = 10

// Get priority
System.out.println(t.getPriority());  // 8

// Rules:
// 1. Priority is a hint to scheduler, not a guarantee
// 2. Platform dependent (OS may ignore)
// 3. Child inherits parent's priority
// 4. Range: 1-10 (IllegalArgumentException if outside)
```

---

## 4. yield, join, sleep

### yield()

```java
// yield(): Hint to scheduler that current thread is willing to pause
// Current thread goes to Runnable state

class YieldDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Child: " + i);
                Thread.yield();  // Suggest to pause
            }
        });
        t.start();
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main: " + i);
        }
    }
}

// Note: yield() is a hint, not a command
// Scheduler may or may not honor it
```

### join()

```java
// join(): Wait for thread to complete

class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Child: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) { }
            }
        });
        t.start();
        t.join();  // Main thread waits for t to complete
        
        System.out.println("Main: Thread completed");
    }
}

// Variations:
// t.join();        // Wait indefinitely
// t.join(1000);    // Wait max 1000 ms
// t.join(1000, 500);  // Wait 1000 ms + 500 ns
```

### sleep()

```java
// sleep(): Pause execution for specified time

class SleepDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start");
        Thread.sleep(2000);  // Sleep 2 seconds
        System.out.println("After 2 seconds");
        
        // TimeUnit (Java 5+)
        TimeUnit.SECONDS.sleep(2);  // Sleep 2 seconds
        TimeUnit.MILLISECONDS.sleep(500);  // Sleep 500 ms
    }
}

// Note: sleep() throws InterruptedException (checked)
// Thread does not lose monitor lock while sleeping
```

---

## 5. Synchronization

### What is Synchronization?

```java
// Synchronization: Thread-safe access to shared resources
// Prevents race conditions

class Counter {
    int count = 0;
    
    // synchronized method: only one thread can execute at a time
    public synchronized void increment() {
        count++;  // Thread-safe
    }
    
    public int getCount() {
        return count;
    }
}

// Without synchronization:
// Thread 1: read count (10)
// Thread 2: read count (10)
// Thread 1: write count (11)
// Thread 2: write count (11)
// Expected: 12, Actual: 11 (lost update)

// With synchronization:
// Thread 1: lock → read → write → unlock
// Thread 2: wait → lock → read → write → unlock
// Expected: 12, Actual: 12
```

### Synchronized Method

```java
class BankAccount {
    private int balance = 0;
    
    // synchronized method
    public synchronized void deposit(int amount) {
        balance += amount;
    }
    
    public synchronized void withdraw(int amount) {
        balance -= amount;
    }
    
    public synchronized int getBalance() {
        return balance;
    }
}

// Lock: Object-level lock (this)
// Each object has its own monitor lock
```

### Static Synchronized

```java
class StaticSync {
    static int count = 0;
    
    // Class-level lock (StaticSync.class)
    public static synchronized void increment() {
        count++;
    }
}

// Object-level lock vs Class-level lock:
// synchronized method: object lock
// static synchronized method: class lock
// Both can run simultaneously (different locks)
```

---

## 6. Synchronized Block

### Synchronized Block

```java
class SyncBlock {
    private final Object lock = new Object();
    private int count = 0;
    
    void increment() {
        // Non-synchronized code
        System.out.println("Before");
        
        // Synchronized block
        synchronized (lock) {
            count++;  // Thread-safe
        }
        
        // Non-synchronized code
        System.out.println("After");
    }
    
    // Synchronized on 'this'
    void decrement() {
        synchronized (this) {
            count--;
        }
    }
    
    // Synchronized on class
    static void staticMethod() {
        synchronized (SyncBlock.class) {
            // Thread-safe
        }
    }
}

// Benefits over synchronized method:
// 1. Granular locking (only critical section)
// 2. Multiple locks
// 3. Better performance
```

---

## 7. Inter Thread Communication

### wait, notify, notifyAll

```java
class SharedResource {
    boolean flag = false;
    
    synchronized void waitForFlag() throws InterruptedException {
        while (!flag) {
            wait();  // Release lock and wait
        }
        System.out.println("Flag is true");
    }
    
    synchronized void setFlag() {
        flag = true;
        notify();  // Wake up one waiting thread
        // notifyAll();  // Wake up all waiting threads
    }
}

// Rules:
// 1. Must be in synchronized block/method
// 2. wait() releases lock
// 3. notify() wakes one waiting thread
// 4. notifyAll() wakes all waiting threads
// 5. wait() throws InterruptedException
```

### Producer-Consumer Problem

```java
class Buffer {
    int data;
    boolean produced = false;
    
    synchronized void produce(int value) throws InterruptedException {
        while (produced) {
            wait();  // Wait until consumed
        }
        data = value;
        produced = true;
        System.out.println("Produced: " + value);
        notify();  // Notify consumer
    }
    
    synchronized int consume() throws InterruptedException {
        while (!produced) {
            wait();  // Wait until produced
        }
        produced = false;
        System.out.println("Consumed: " + data);
        notify();  // Notify producer
        return data;
    }
}
```

---

## 8. DeadLock and Starvation

### DeadLock

```java
// DeadLock: Two or more threads waiting for each other forever

class DeadLockDemo {
    static final Object resource1 = new Object();
    static final Object resource2 = new Object();
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");
                try { Thread.sleep(100); } catch (InterruptedException e) { }
                
                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");
                try { Thread.sleep(100); } catch (InterruptedException e) { }
                
                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });
        
        t1.start();
        t2.start();
    }
}

// DeadLock conditions (Coffman conditions):
// 1. Mutual exclusion
// 2. Hold and wait
// 3. No preemption
// 4. Circular wait

// Prevention:
// 1. Lock ordering (always lock in same order)
// 2. Lock timeout
// 3. TryLock (ReentrantLock)
```

### Starvation

```java
// Starvation: Thread cannot access resource for long time
// Low priority thread never gets CPU

Thread t = new Thread(() -> {
    while (true) {
        System.out.println("Low priority thread");
    }
});
t.setPriority(Thread.MIN_PRIORITY);
t.start();

// High priority threads consume all CPU
// Low priority thread starves

// Solution: Fair locks (ReentrantLock(true))
```

---

## 9. Daemon Threads

### What is Daemon Thread?

```java
// Daemon thread: Background thread (low priority)
// JVM exits when only daemon threads remain
// Examples: GC, finalizer, reference handler

class DaemonDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("Daemon running");
                try { Thread.sleep(1000); } catch (InterruptedException e) { }
            }
        });
        
        t.setDaemon(true);  // Set as daemon BEFORE start
        t.start();
        
        System.out.println("Main ending");
        // JVM exits immediately (daemon thread killed)
    }
}

// Rules:
// 1. setDaemon(true) before start()
// 2. t.setDaemon(true) after start() → IllegalThreadStateException
// 3. Child inherits daemon status from parent
```

---

## 10. Interview Questions

### Q1. What is the difference between Thread and Runnable?

```java
// Thread: class
// Runnable: interface

// extends Thread: cannot extend another class
// implements Runnable: can extend another class

// Multiple threads can share same Runnable
// Each Thread has separate run()

// Recommended: implements Runnable (more flexible)
```

### Q2. What is the difference between `start()` and `run()`?

```java
// start(): Creates new thread, calls run() internally
// run(): Normal method call (no new thread)

Thread t = new Thread(() -> {
    System.out.println("Thread: " + Thread.currentThread().getName());
});

t.start();  // New thread: "Thread-0"
t.run();    // Main thread: "main"

// Never call run() directly — defeats purpose
```

### Q3. What is the difference between `sleep()` and `wait()`?

```java
// sleep(): Thread.sleep(1000)
// wait(): object.wait()

// sleep():
// - Does not release lock
// - Static method
// - Can be called from anywhere
// - InterruptedException

// wait():
// - Releases lock
// - Instance method
// - Must be in synchronized block
// - InterruptedException
// - Needs notify() to wake up
```

### Q4. What is `synchronized`?

```java
// synchronized: Thread-safe access to shared resources
// Lock: object-level or class-level

// synchronized method
public synchronized void method() { }

// synchronized block
synchronized (lock) { }

// static synchronized
public static synchronized void method() { }

// Object lock: one thread per object
// Class lock: one thread per class
```

### Q5. What is `volatile`?

```java
// volatile: Visibility guarantee
// Ensures variable is read from main memory

volatile boolean flag = false;

// Thread 1:
flag = true;  // Visible to all threads immediately

// Thread 2:
while (!flag) {  // Sees updated value
}

// volatile does NOT guarantee atomicity
// For atomicity, use AtomicInteger or synchronized
```

### Q6. What is DeadLock?

```java
// DeadLock: Threads waiting for each other forever
// Thread 1: Lock A → Lock B
// Thread 2: Lock B → Lock A

// Prevention:
// 1. Lock ordering
// 2. Timeout
// 3. TryLock

// Detection:
// ThreadMXBean.findDeadlockedThreads()
```

### Q7. What is `join()`?

```java
// join(): Wait for thread to complete

Thread t = new Thread(() -> {
    // Long running task
});
t.start();
t.join();  // Wait for t to finish

// t.join(1000);  // Wait max 1000ms
// t.join(1000, 500);  // 1000ms + 500ns
```

### Q8. What is Daemon thread?

```java
// Daemon: Background thread
// JVM exits when only daemon threads remain

Thread t = new Thread(() -> { });
t.setDaemon(true);
t.start();

// Examples: GC, finalizer
// Must set daemon before start()
// Child inherits daemon status
```

### Q9. What is `ThreadLocal`?

```java
// ThreadLocal: Thread-specific variable
// Each thread has its own copy

ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

// Thread 1:
threadLocal.set(10);

// Thread 2:
threadLocal.set(20);

// Each thread sees its own value
// Use cases: User session, database connection, transaction
```

### Q10. What is `notify()` vs `notifyAll()`?

```java
// notify(): Wake up ONE waiting thread
// notifyAll(): Wake up ALL waiting threads

// Use notifyAll() when:
// - Multiple threads waiting
// - All may be able to proceed
// - Avoid lost wakeups

// Use notify() when:
// - Only one thread can proceed
// - All threads are equivalent

// Always use while loop with wait():
while (!condition) {
    wait();
}
```

---

## 11. Key Takeaways

1. **Thread**: Lightweight process; shares memory with other threads.
2. **Process**: Heavyweight; separate memory space.
3. **Thread states**: New → Runnable → Running → Waiting/Blocked → Dead.
4. **Two ways** to create thread: extends Thread, implements Runnable.
5. **Recommended**: implements Runnable (more flexible).
6. **start()** creates new thread; **run()** is normal method call.
7. **Priority**: 1-10; hint to scheduler, not guaranteed.
8. **yield()**: Suggest to pause; not guaranteed.
9. **sleep()**: Pause, don't release lock; throws InterruptedException.
10. **wait()**: Release lock and wait; must be in synchronized block.
11. **notify()**: Wake up one waiting thread.
12. **notifyAll()**: Wake up all waiting threads.
13. **join()**: Wait for thread to complete.
14. **synchronized**: Thread-safe; object lock or class lock.
15. **synchronized block**: Granular locking; better performance.
16. **DeadLock**: Threads wait forever; prevent with lock ordering.
17. **Starvation**: Thread never gets resource; use fair locks.
18. **Daemon thread**: Background thread; JVM exits when only daemons remain.
19. **volatile**: Visibility guarantee; not atomic.
20. **ThreadLocal**: Thread-specific variable; each thread has its own copy.

---

**Happy coding! 🚀**

*Multi-threading unlocks the true power of modern CPUs — master it for high-performance applications.*
