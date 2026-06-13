# Core Java Concepts — Coding Problems

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

### Problem 1: Check if a Number is Palindrome

**Logic:** Reverse the number and compare with original.

```java
// File: Palindrome.java
public class Palindrome {
    public static boolean isPalindrome(int num) {
        int original = num;
        int reversed = 0;
        
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        
        return original == reversed;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));  // true
        System.out.println(isPalindrome(123));  // false
    }
}
```

### Problem 2: Check if a Number is Prime

**Logic:** Check divisibility from 2 to square root of number.

```java
// File: PrimeCheck.java
public class PrimeCheck {
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isPrime(17));  // true
        System.out.println(isPrime(18));  // false
    }
}
```

### Problem 3: Factorial of a Number

**Logic:** Multiply all numbers from 1 to n.

```java
// File: Factorial.java
public class Factorial {
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0 || n == 1) return 1;
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(factorial(5));  // 120
    }
}
```

### Problem 4: Fibonacci Series

**Logic:** Each number is sum of two preceding ones.

```java
// File: Fibonacci.java
public class Fibonacci {
    public static void printFibonacci(int n) {
        int a = 0, b = 1;
        
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }
    
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        printFibonacci(10);  // 0 1 1 2 3 5 8 13 21 34
    }
}
```

### Problem 5: Reverse a String

**Logic:** Swap characters from start and end.

```java
// File: ReverseString.java
public class ReverseString {
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        
        return new String(chars);
    }
    
    public static void main(String[] args) {
        System.out.println(reverse("Hello"));  // olleH
    }
}
```

### Problem 6: Check if String is Palindrome

**Logic:** Compare string with its reverse.

```java
// File: StringPalindrome.java
public class StringPalindrome {
    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome("radar"));  // true
        System.out.println(isPalindrome("hello"));  // false
    }
}
```

### Problem 7: Find Maximum Element in Array

**Logic:** Iterate and compare each element.

```java
// File: MaxElement.java
public class MaxElement {
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 9, 1};
        System.out.println(findMax(arr));  // 9
    }
}
```

### Problem 8: Linear Search

**Logic:** Compare each element with target.

```java
// File: LinearSearch.java
public class LinearSearch {
    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;  // Found at index i
            }
        }
        return -1;  // Not found
    }
    
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 2, 9};
        System.out.println(search(arr, 8));  // 2
    }
}
```

### Problem 9: Binary Search

**Logic:** Divide array in half, compare with middle.

```java
// File: BinarySearch.java
public class BinarySearch {
    public static int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        System.out.println(search(arr, 7));  // 3
    }
}
```

### Problem 10: Bubble Sort

**Logic:** Repeatedly swap adjacent elements if in wrong order.

```java
// File: BubbleSort.java
public class BubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        sort(arr);
        System.out.println(java.util.Arrays.toString(arr));
        // [11, 12, 22, 25, 34, 64, 90]
    }
}
```

---

## 2. Medium Level

### Problem 11: Selection Sort

**Logic:** Find minimum and swap with first unsorted element.

```java
// File: SelectionSort.java
public class SelectionSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        sort(arr);
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

### Problem 12: Insertion Sort

**Logic:** Insert each element into its correct position in sorted part.

```java
// File: InsertionSort.java
public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        sort(arr);
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

### Problem 13: Merge Sort

**Logic:** Divide and conquer. Divide array, sort, merge.

```java
// File: MergeSort.java
public class MergeSort {
    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        sort(arr, 0, arr.length - 1);
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

### Problem 14: Quick Sort

**Logic:** Divide and conquer. Pick pivot, partition, sort.

```java
// File: QuickSort.java
public class QuickSort {
    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        sort(arr, 0, arr.length - 1);
        System.out.println(java.util.Arrays.toString(arr));
    }
}
```

### Problem 15: Find GCD (Greatest Common Divisor)

**Logic:** Euclidean algorithm.

```java
// File: GCD.java
public class GCD {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // Recursive
    public static int gcdRecursive(int a, int b) {
        if (b == 0) return a;
        return gcdRecursive(b, a % b);
    }
    
    public static void main(String[] args) {
        System.out.println(gcd(48, 18));  // 6
    }
}
```

### Problem 16: Find LCM (Least Common Multiple)

**Logic:** LCM = (a * b) / GCD.

```java
// File: LCM.java
public class LCM {
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static void main(String[] args) {
        System.out.println(lcm(4, 6));  // 12
    }
}
```

### Problem 17: Armstrong Number

**Logic:** Sum of cubes of digits equals number.

```java
// File: ArmstrongNumber.java
public class ArmstrongNumber {
    public static boolean isArmstrong(int num) {
        int original = num;
        int sum = 0;
        int digits = String.valueOf(num).length();
        
        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, digits);
            num /= 10;
        }
        
        return original == sum;
    }
    
    public static void main(String[] args) {
        System.out.println(isArmstrong(153));  // true (1^3 + 5^3 + 3^3 = 153)
        System.out.println(isArmstrong(9474)); // true (4-digit)
    }
}
```

### Problem 18: Perfect Number

**Logic:** Sum of proper divisors equals number.

```java
// File: PerfectNumber.java
public class PerfectNumber {
    public static boolean isPerfect(int num) {
        int sum = 0;
        
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        
        return sum == num;
    }
    
    public static void main(String[] args) {
        System.out.println(isPerfect(6));   // true (1 + 2 + 3 = 6)
        System.out.println(isPerfect(28));  // true (1 + 2 + 4 + 7 + 14 = 28)
    }
}
```

### Problem 19: Sum of Digits

**Logic:** Extract digits and sum.

```java
// File: SumOfDigits.java
public class SumOfDigits {
    public static int sum(int num) {
        int sum = 0;
        
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println(sum(123));  // 6
    }
}
```

### Problem 20: Power of a Number

**Logic:** Multiply base exponent times.

```java
// File: Power.java
public class Power {
    public static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    // Fast power (O(log n))
    public static double fastPower(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) return 1 / fastPower(base, -exponent);
        
        double half = fastPower(base, exponent / 2);
        if (exponent % 2 == 0) {
            return half * half;
        } else {
            return base * half * half;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(power(2, 10));      // 1024.0
        System.out.println(fastPower(2, 10));  // 1024.0
    }
}
```

---

## 3. High Level (Advanced)

### Problem 21: Singleton Pattern (Thread-Safe)

**Logic:** Ensure only one instance exists. Double-checked locking.

```java
// File: Singleton.java
public class Singleton {
    private static volatile Singleton instance;
    
    private Singleton() {
        System.out.println("Singleton created");
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);  // true
    }
}
```

### Problem 22: Producer-Consumer Problem

**Logic:** Thread communication using wait/notify.

```java
// File: ProducerConsumer.java
public class ProducerConsumer {
    private int data;
    private boolean produced = false;
    
    public synchronized void produce(int value) throws InterruptedException {
        while (produced) {
            wait();
        }
        data = value;
        produced = true;
        System.out.println("Produced: " + value);
        notify();
    }
    
    public synchronized int consume() throws InterruptedException {
        while (!produced) {
            wait();
        }
        produced = false;
        System.out.println("Consumed: " + data);
        notify();
        return data;
    }
    
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    pc.produce(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
        });
        
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    pc.consume();
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
        });
        
        producer.start();
        consumer.start();
    }
}
```

### Problem 23: Deadlock Detection

**Logic:** Two threads waiting for each other.

```java
// File: DeadlockDemo.java
public class DeadlockDemo {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();
    
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
```

### Problem 24: Custom Exception

**Logic:** Create and use custom exception.

```java
// File: CustomException.java
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

class BankAccount {
    private double balance;
    
    public BankAccount(double balance) {
        this.balance = balance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Balance: " + balance + ", Requested: " + amount);
        }
        balance -= amount;
    }
    
    public double getBalance() {
        return balance;
    }
}

public class CustomExceptionDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);
        
        try {
            account.withdraw(200);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Problem 25: Thread Pool

**Logic:** Use ExecutorService for thread pool.

```java
// File: ThreadPoolDemo.java
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + 
                    " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) { }
    }
}
```

### Problem 26: CountDownLatch

**Logic:** Wait for multiple threads to complete.

```java
// File: CountDownLatchDemo.java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            new Thread(() -> {
                System.out.println("Task " + taskId + " started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                System.out.println("Task " + taskId + " completed");
                latch.countDown();
            }).start();
        }
        
        latch.await();
        System.out.println("All tasks completed");
    }
}
```

### Problem 27: CompletableFuture

**Logic:** Asynchronous programming.

```java
// File: CompletableFutureDemo.java
import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            return "Hello";
        });
        
        future.thenApply(s -> s + " World")
              .thenAccept(System.out::println);
        
        // Wait for completion
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { }
    }
}
```

### Problem 28: ReentrantLock

**Logic:** Explicit lock with more control.

```java
// File: ReentrantLockDemo.java
import java.util.concurrent.locks.*;

public class ReentrantLockDemo {
    private static final Lock lock = new ReentrantLock();
    private static int counter = 0;
    
    public static void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println("Counter: " + counter + 
                " by " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    increment();
                }
            }).start();
        }
    }
}
```

### Problem 29: ReadWriteLock

**Logic:** Separate locks for reading and writing.

```java
// File: ReadWriteLockDemo.java
import java.util.concurrent.locks.*;

public class ReadWriteLockDemo {
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Lock readLock = rwLock.readLock();
    private static final Lock writeLock = rwLock.writeLock();
    private static int data = 0;
    
    public static void read() {
        readLock.lock();
        try {
            System.out.println("Read: " + data + 
                " by " + Thread.currentThread().getName());
        } finally {
            readLock.unlock();
        }
    }
    
    public static void write(int value) {
        writeLock.lock();
        try {
            data = value;
            System.out.println("Write: " + data + 
                " by " + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> read()).start();
        }
        new Thread(() -> write(100)).start();
    }
}
```

### Problem 30: Semaphore

**Logic:** Control access to limited resources.

```java
// File: SemaphoreDemo.java
import java.util.concurrent.*;

public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(3);
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Task " + taskId + 
                        " accessing resource by " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    System.out.println("Task " + taskId + " releasing");
                    semaphore.release();
                } catch (InterruptedException e) { }
            }).start();
        }
    }
}
```

---

## 4. Highly Severe Level (Expert)

### Problem 31: LRU Cache

**Logic:** Least Recently Used cache using LinkedHashMap.

```java
// File: LRUCache.java
import java.util.*;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
    
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache);  // {1=A, 2=B, 3=C}
        
        cache.get(1);  // Access 1
        cache.put(4, "D");  // Evicts 2 (LRU)
        System.out.println(cache);  // {3=C, 1=A, 4=D}
    }
}
```

### Problem 32: Thread-Safe Singleton (Enum)

**Logic:** Enum-based singleton (best approach).

```java
// File: EnumSingleton.java
public enum EnumSingleton {
    INSTANCE;
    
    private int value;
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public void doSomething() {
        System.out.println("Doing something: " + value);
    }
    
    public static void main(String[] args) {
        EnumSingleton.INSTANCE.setValue(42);
        EnumSingleton.INSTANCE.doSomething();  // Doing something: 42
        
        // Thread-safe, serialization-safe, reflection-safe
        System.out.println(EnumSingleton.INSTANCE == EnumSingleton.INSTANCE);  // true
    }
}
```

### Problem 33: Custom HashMap

**Logic:** Simple HashMap implementation using arrays and linked list.

```java
// File: CustomHashMap.java
public class CustomHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Entry<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;
    
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Entry[capacity];
    }
    
    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
    
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> head = buckets[index];
        
        // Check if key exists
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        
        // Insert at head
        size++;
        head = buckets[index];
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        buckets[index] = newEntry;
    }
    
    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> head = buckets[index];
        
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    
    public int size() {
        return size;
    }
    
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println(map.get("two"));   // 2
        System.out.println(map.get("four"));  // null
        System.out.println(map.size());       // 3
    }
}
```

### Problem 34: Custom ArrayList

**Logic:** Dynamic array implementation.

```java
// File: CustomArrayList.java
public class CustomArrayList<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }
    
    public int size() {
        return size;
    }
    
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }
    
    public static void main(String[] args) {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        System.out.println(list.get(0));  // A
        System.out.println(list.get(1));  // B
        System.out.println(list.size());  // 3
    }
}
```

### Problem 35: Custom Stack

**Logic:** LIFO using array.

```java
// File: CustomStack.java
public class CustomStack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CustomStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    public void push(T element) {
        ensureCapacity();
        elements[size++] = element;
    }
    
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T element = (T) elements[--size];
        elements[size] = null;  // Prevent memory leak
        return element;
    }
    
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (T) elements[size - 1];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }
    
    public static void main(String[] args) {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println(stack.peek());  // 30
        System.out.println(stack.pop());   // 30
        System.out.println(stack.pop());   // 20
        System.out.println(stack.size());  // 1
    }
}
```

### Problem 36: Custom Queue

**Logic:** FIFO using circular array.

```java
// File: CustomQueue.java
public class CustomQueue<T> {
    private Object[] elements;
    private int front = 0;
    private int rear = 0;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CustomQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    public void enqueue(T element) {
        ensureCapacity();
        elements[rear] = element;
        rear = (rear + 1) % elements.length;
        size++;
    }
    
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T element = (T) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(front + i) % elements.length];
            }
            front = 0;
            rear = size;
            elements = newElements;
        }
    }
    
    public static void main(String[] args) {
        CustomQueue<String> queue = new CustomQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        
        System.out.println(queue.dequeue());  // A
        System.out.println(queue.dequeue());  // B
        System.out.println(queue.size());     // 1
    }
}
```

### Problem 37: Binary Search Tree

**Logic:** Tree where left < parent < right.

```java
// File: BinarySearchTree.java
public class BinarySearchTree {
    private static class Node {
        int data;
        Node left, right;
        
        Node(int data) {
            this.data = data;
        }
    }
    
    private Node root;
    
    public void insert(int data) {
        root = insert(root, data);
    }
    
    private Node insert(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data) node.left = insert(node.left, data);
        else if (data > node.data) node.right = insert(node.right, data);
        return node;
    }
    
    public boolean search(int data) {
        return search(root, data);
    }
    
    private boolean search(Node node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return search(node.left, data);
        return search(node.right, data);
    }
    
    public void inorder() {
        inorder(root);
        System.out.println();
    }
    
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }
    
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        
        bst.inorder();  // 20 30 40 50 70
        System.out.println(bst.search(40));  // true
        System.out.println(bst.search(100)); // false
    }
}
```

### Problem 38: Linked List

**Logic:** Linear collection of nodes.

```java
// File: LinkedList.java
public class LinkedList<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data) {
            this.data = data;
        }
    }
    
    private Node<T> head;
    private int size = 0;
    
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }
    
    public int size() {
        return size;
    }
    
    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        
        list.print();  // 10 20 30
        System.out.println(list.get(1));  // 20
        list.remove(1);
        list.print();  // 10 30
    }
}
```

### Problem 39: Reverse Linked List

**Logic:** Reverse pointers.

```java
// File: ReverseLinkedList.java
public class ReverseLinkedList {
    private static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
        }
    }
    
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;  // Store next
            current.next = prev;   // Reverse pointer
            prev = current;        // Move prev
            current = next;        // Move current
        }
        
        return prev;  // New head
    }
    
    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        
        print(head);  // 1 2 3 4
        head = reverse(head);
        print(head);  // 4 3 2 1
    }
}
```

### Problem 40: Merge Two Sorted Arrays

**Logic:** Two pointers technique.

```java
// File: MergeSortedArrays.java
public class MergeSortedArrays {
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        
        int[] merged = merge(arr1, arr2);
        System.out.println(java.util.Arrays.toString(merged));
        // [1, 2, 3, 4, 5, 6, 7, 8]
    }
}
```

---

**Happy coding! 🚀**

*Practice these coding problems from low to highly severe levels to master Core Java programming.*
