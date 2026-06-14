# Chapter 11 — Generics

> **Source:** YouTube Playlist — Core Java with OCJP/SCJP by Durga Sir
> **Channel:** Durga Software Solutions
> **Topics:** Type Safety, Generic Classes, Generic Methods, Wildcards, Bounded Types, Type Erasure, Generic Interfaces

---

## Table of Contents

1. [Introduction to Generics](#1-introduction-to-generics)
2. [Type Safety and Type Casting](#2-type-safety-and-type-casting)
3. [Generic Classes](#3-generic-classes)
4. [Generic Methods](#4-generic-methods)
5. [Wildcard Characters](#5-wildcard-characters)
6. [Bounded Types](#6-bounded-types)
7. [Type Erasure](#7-type-erasure)
8. [Generic Interfaces](#8-generic-interfaces)
9. [Interview Questions](#9-interview-questions)
10. [Quick Reference](#10-quick-reference)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Introduction to Generics

### What are Generics?

Generics in Java allow you to write type-safe, reusable code by parameterizing types. Introduced in Java 5, generics enable classes, interfaces, and methods to operate on various types while providing compile-time type checking.

### Why Generics?

```
┌──────────────────────────────────────────────────────────────┐
│                    PROBLEMS WITHOUT GENERICS                     │
│                                                                  │
│  1. Type Safety Issues — No compile-time checking               │
│  2. Explicit Type Casting — Required at runtime                 │
│  3. ClassCastException — Runtime errors                       │
│  4. Code Reusability — Separate classes for each type           │
│                                                                  │
│  Before Generics:                                                │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ ArrayList list = new ArrayList();                       │   │
│  │ list.add("Hello");                                      │   │
│  │ list.add(10);  // No error!                             │   │
│  │ String s = (String) list.get(0); // OK                  │   │
│  │ String s = (String) list.get(1); // ClassCastException  │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                  │
│  After Generics:                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ ArrayList<String> list = new ArrayList<>();             │   │
│  │ list.add("Hello");                                      │   │
│  │ list.add(10);  // COMPILE-TIME ERROR!                   │   │
│  │ String s = list.get(0); // No casting needed            │   │
│  └─────────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
```

### Syntax of Generics

```java
// Generic Class
class ClassName<T> { }

// Generic Interface
interface InterfaceName<T> { }

// Generic Method
<T> returnType methodName(T parameter) { }

// Multiple Type Parameters
class ClassName<T, U> { }
```

### Naming Conventions for Type Parameters

```
┌──────────────────────────────────────────────────────────────┐
│                    TYPE PARAMETER NAMING                        │
│  ┌─────────┬─────────────────────────────────────────────────┐ │
│  │ Letter  │ Used For                                        │ │
│  ├─────────┼─────────────────────────────────────────────────┤ │
│  │ T       │ Type (most common)                              │ │
│  │ E       │ Element (Collections)                           │ │
│  │ K       │ Key (Maps)                                      │ │
│  │ V       │ Value (Maps)                                    │ │
│  │ N       │ Number                                          │ │
│  │ S, U, V │ 2nd, 3rd, 4th types                            │ │
│  └─────────┴─────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. Type Safety and Type Casting

### Type Safety Without Generics

```java
import java.util.ArrayList;

public class WithoutGenerics {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();  // Raw type
        
        list.add("John");
        list.add(10);        // Integer added — no compile error
        list.add(true);      // Boolean added — no compile error
        
        // Must cast explicitly
        String name = (String) list.get(0);  // OK
        // String num = (String) list.get(1);  // ClassCastException at runtime!
        
        System.out.println(name);
    }
}
```

### Type Safety With Generics

```java
import java.util.ArrayList;

public class WithGenerics {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();  // Type-safe
        
        list.add("John");
        // list.add(10);     // COMPILE-TIME ERROR: incompatible types
        // list.add(true);   // COMPILE-TIME ERROR: incompatible types
        
        // No casting needed
        String name = list.get(0);  // Safe
        
        System.out.println(name);
    }
}
```

### Polymorphism with Generics

```java
import java.util.ArrayList;
import java.util.List;

public class GenericsPolymorphism {
    public static void main(String[] args) {
        // Valid: ArrayList IS-A List
        List<String> list = new ArrayList<>();
        
        // INVALID: Generic types must match exactly
        // ArrayList<Object> list = new ArrayList<String>();  // ERROR
        
        // The type parameter must be the same on both sides
        // (before Java 7, you had to write both sides)
        List<String> list2 = new ArrayList<String>();  // Pre-Java 7
        List<String> list3 = new ArrayList<>();         // Java 7+ (diamond operator)
    }
}
```

### Raw Types vs Parameterized Types

```java
import java.util.ArrayList;

public class RawTypeDemo {
    public static void main(String[] args) {
        // Raw type (no generics) — backward compatibility
        ArrayList rawList = new ArrayList();
        rawList.add("Hello");
        rawList.add(10);
        
        // Parameterized type
        ArrayList<String> genericList = new ArrayList<>();
        genericList.add("Hello");
        // genericList.add(10);  // ERROR
        
        // Mixing raw and generic (NOT recommended)
        ArrayList<String> list = new ArrayList();  // Warning: unchecked conversion
        list.add("Test");
        
        // Parameterized type assigned to raw type
        ArrayList raw = genericList;  // Allowed but dangerous
        raw.add(10);  // No compile error — but breaks type safety!
        
        // String s = genericList.get(1);  // ClassCastException at runtime!
    }
}
```

---

## 3. Generic Classes

### Defining a Generic Class

```java
// Generic class with single type parameter
public class Box<T> {
    private T item;
    
    public void setItem(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
    
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello");
        String s = stringBox.getItem();  // No casting needed
        
        Box<Integer> intBox = new Box<>();
        intBox.setItem(100);
        Integer i = intBox.getItem();
        
        System.out.println(s + " " + i);
    }
}
```

### Generic Class with Multiple Type Parameters

```java
// Generic class with two type parameters
public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    
    public static void main(String[] args) {
        Pair<String, Integer> person = new Pair<>("Age", 25);
        System.out.println(person.getKey() + ": " + person.getValue());
        
        Pair<Integer, String> code = new Pair<>(404, "Not Found");
        System.out.println(code.getKey() + " — " + code.getValue());
    }
}
```

### Generic Class with Array

```java
public class GenericArray<T> {
    private Object[] arr;
    private int size = 0;
    
    public GenericArray(int capacity) {
        arr = new Object[capacity];  // Cannot create T[] directly
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) arr[index];
    }
    
    public void set(int index, T value) {
        arr[index] = value;
        size++;
    }
    
    public int size() { return size; }
    
    public static void main(String[] args) {
        GenericArray<String> ga = new GenericArray<>(5);
        ga.set(0, "A");
        ga.set(1, "B");
        
        System.out.println(ga.get(0));  // A
        System.out.println(ga.get(1));  // B
    }
}
```

### Important: Cannot Use Primitives as Type Parameters

```java
public class PrimitiveRestriction {
    public static void main(String[] args) {
        // INVALID: Cannot use primitive types
        // Box<int> intBox = new Box<>();  // ERROR
        
        // Use wrapper classes instead
        Box<Integer> intBox = new Box<>();
        Box<Double> doubleBox = new Box<>();
        Box<Character> charBox = new Box<>();
        Box<Boolean> boolBox = new Box<>();
        
        // Auto-boxing and unboxing work automatically
        intBox.setItem(10);       // autoboxing: int -> Integer
        int value = intBox.getItem();  // unboxing: Integer -> int
        
        System.out.println(value);
    }
}

class Box<T> {
    private T item;
    public void setItem(T item) { this.item = item; }
    public T getItem() { return item; }
}
```

### Static Context and Generics

```java
public class StaticGeneric<T> {
    private T instanceVar;  // OK
    
    // static T staticVar;  // ERROR: Cannot use type parameter in static context
    
    public void instanceMethod(T param) {  // OK
        System.out.println(param);
    }
    
    // public static void staticMethod(T param) {  // ERROR
    //     System.out.println(param);
    // }
    
    // Generic static method is OK (declares its own type parameter)
    public static <U> void staticGenericMethod(U param) {
        System.out.println(param);
    }
    
    public static void main(String[] args) {
        StaticGeneric.<String>staticGenericMethod("Hello");
        StaticGeneric.staticGenericMethod(100);  // Type inference
    }
}
```

---

## 4. Generic Methods

### Defining Generic Methods

```java
public class GenericMethodDemo {
    
    // Generic method with single type parameter
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    
    // Generic method that returns value
    public <T> T getFirst(T[] array) {
        return array[0];
    }
    
    // Generic method with multiple parameters
    public <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }
    
    // Generic method with bounded type parameter
    public <T extends Number> double sum(T[] numbers) {
        double total = 0;
        for (T num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }
    
    public static void main(String[] args) {
        GenericMethodDemo demo = new GenericMethodDemo();
        
        String[] strArr = {"A", "B", "C"};
        demo.printArray(strArr);
        
        Integer[] intArr = {1, 2, 3};
        demo.printArray(intArr);
        
        System.out.println("First: " + demo.getFirst(intArr));
        
        demo.printPair("Hello", 100);
        
        Double[] doubles = {1.5, 2.5, 3.5};
        System.out.println("Sum: " + demo.sum(doubles));
    }
}
```

### Generic Method vs Generic Class

```java
public class GenericMethodVsClass {
    
    // Non-generic class with generic method
    public <T> void print(T item) {
        System.out.println(item);
    }
    
    public static void main(String[] args) {
        GenericMethodVsClass obj = new GenericMethodVsClass();
        
        // Same object, different types
        obj.print("String");    // T inferred as String
        obj.print(100);         // T inferred as Integer
        obj.print(3.14);        // T inferred as Double
    }
}

// Generic class — type is fixed at object creation
class Printer<T> {
    public void print(T item) {
        System.out.println(item);
    }
}
```

### Calling Generic Methods with Explicit Type Arguments

```java
public class ExplicitTypeArguments {
    
    public <T> T genericMethod(T param) {
        return param;
    }
    
    public static void main(String[] args) {
        ExplicitTypeArguments obj = new ExplicitTypeArguments();
        
        // Implicit type inference
        String s = obj.genericMethod("Hello");
        
        // Explicit type argument (rarely needed)
        String s2 = obj.<String>genericMethod("Hello");
        
        // Explicit type argument needed when compiler cannot infer
        // obj.<String>genericMethod(10);  // ERROR: 10 is not String
        
        System.out.println(s + " " + s2);
    }
}
```

---

## 5. Wildcard Characters

### Types of Wildcards

```
┌──────────────────────────────────────────────────────────────┐
│                    GENERIC WILDCARDS                             │
│                                                                  │
│  ┌────────────────┬─────────────────────────────────────────┐ │
│  │ Symbol         │ Meaning                                 │ │
│  ├────────────────┼─────────────────────────────────────────┤ │
│  │ ?              │ Unbounded wildcard — any type           │ │
│  │ ? extends T    │ Upper bounded — T or subclasses of T    │ │
│  │ ? super T      │ Lower bounded — T or superclasses of T  │ │
│  └────────────────┴─────────────────────────────────────────┘ │
│                                                                  │
│  Purpose: Increase API flexibility while maintaining type safety │
└──────────────────────────────────────────────────────────────┘
```

### Unbounded Wildcard (?)

```java
import java.util.ArrayList;
import java.util.List;

public class UnboundedWildcard {
    
    // Accepts List of any type
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
    
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("A");
        strList.add("B");
        
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        
        printList(strList);  // OK
        printList(intList);  // OK
        
        // Cannot add elements to List<?>
        // list.add("X");  // ERROR: cannot add to unbounded wildcard list
        // list.add(null); // OK — null is allowed
    }
}
```

### Upper Bounded Wildcard (? extends T)

```java
import java.util.ArrayList;
import java.util.List;

public class UpperBoundedWildcard {
    
    // Accepts List of Number or any subclass
    public static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number num : list) {
            total += num.doubleValue();
        }
        return total;
    }
    
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(2.5);
        
        List<Number> numList = new ArrayList<>();
        numList.add(10);
        numList.add(3.14);
        
        System.out.println(sum(intList));    // 30.0
        System.out.println(sum(doubleList)); // 4.0
        System.out.println(sum(numList));    // 13.14
        
        // Cannot add elements (except null)
        // intList.add(30);  // OK — on original list
        // sumList.add(10);  // ERROR if List<? extends Number>
    }
}
```

### Lower Bounded Wildcard (? super T)

```java
import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcard {
    
    // Accepts List of Integer or any superclass
    public static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
        // Can add Integer (or subclass) but reading returns Object
    }
    
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        addIntegers(intList);
        System.out.println(intList);  // [10, 20, 30]
        
        List<Number> numList = new ArrayList<>();
        addIntegers(numList);
        System.out.println(numList);  // [10, 20, 30]
        
        List<Object> objList = new ArrayList<>();
        addIntegers(objList);
        System.out.println(objList);  // [10, 20, 30]
        
        // Cannot pass List<Double> — Double is not superclass of Integer
        // List<Double> doubleList = new ArrayList<>();
        // addIntegers(doubleList);  // ERROR
    }
}
```

### PECS Principle (Producer Extends, Consumer Super)

```java
import java.util.ArrayList;
import java.util.List;

public class PECSPrinciple {
    
    // Producer — uses extends (read from list)
    public static void printNumbers(List<? extends Number> producer) {
        for (Number n : producer) {
            System.out.println(n);
        }
        // Cannot add elements (except null)
    }
    
    // Consumer — uses super (write to list)
    public static void addNumbers(List<? super Integer> consumer) {
        consumer.add(10);
        consumer.add(20);
        // Can add Integer, but reading returns Object
    }
    
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        
        // Producer extends
        printNumbers(intList);
        
        // Consumer super
        List<Number> numList = new ArrayList<>();
        addNumbers(numList);
        System.out.println(numList);  // [10, 20]
    }
}
```

### Wildcard Restrictions

```java
import java.util.ArrayList;
import java.util.List;

public class WildcardRestrictions {
    public static void main(String[] args) {
        List<?> unbounded = new ArrayList<String>();
        
        // Reading: Allowed, returns Object
        Object obj = unbounded.get(0);
        
        // Writing: NOT allowed (except null)
        // unbounded.add("Hello");  // ERROR
        unbounded.add(null);  // OK
        
        List<? extends Number> upper = new ArrayList<Integer>();
        // upper.add(10);  // ERROR — cannot guarantee safety
        upper.add(null);  // OK
        
        List<? super Integer> lower = new ArrayList<Number>();
        lower.add(10);  // OK
        // Integer i = lower.get(0);  // ERROR — returns Object
        Object o = lower.get(0);  // OK
    }
}
```

---

## 6. Bounded Types

### Upper Bounded Types

```java
// T must be Number or subclass of Number
public class BoundedType<T extends Number> {
    private T value;
    
    public BoundedType(T value) {
        this.value = value;
    }
    
    public double getDoubleValue() {
        return value.doubleValue();  // Can call Number methods
    }
    
    public static void main(String[] args) {
        BoundedType<Integer> intVal = new BoundedType<>(100);
        System.out.println(intVal.getDoubleValue());  // 100.0
        
        BoundedType<Double> doubleVal = new BoundedType<>(3.14);
        System.out.println(doubleVal.getDoubleValue());  // 3.14
        
        // BoundedType<String> strVal = new BoundedType<>("Hello");  // ERROR
    }
}
```

### Multiple Bounds (Interface + Class)

```java
// T must extend Number AND implement Comparable
public class MultipleBounds<T extends Number & Comparable<T>> {
    private T value;
    
    public MultipleBounds(T value) {
        this.value = value;
    }
    
    public boolean isGreaterThan(T other) {
        return value.compareTo(other) > 0;
    }
    
    public static void main(String[] args) {
        MultipleBounds<Integer> bound = new MultipleBounds<>(100);
        System.out.println(bound.isGreaterThan(50));   // true
        System.out.println(bound.isGreaterThan(200));   // false
        
        // MultipleBounds<Double> d = new MultipleBounds<>(1.5);  // OK too
    }
}
```

### Bounded Type in Generic Methods

```java
public class BoundedMethod {
    
    // Method accepts array of Comparable elements
    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        Integer[] numbers = {1, 5, 3, 9, 2};
        System.out.println(findMax(numbers));  // 9
        
        String[] words = {"apple", "zebra", "mango"};
        System.out.println(findMax(words));  // zebra
    }
}
```

---

## 7. Type Erasure

### What is Type Erasure?

```
┌──────────────────────────────────────────────────────────────┐
│                    TYPE ERASURE                                  │
│                                                                  │
│  Java generics are implemented via TYPE ERASURE.               │
│  At compile time, type parameters are removed and replaced      │
│  with their bounds (or Object if unbounded).                     │
│                                                                  │
│  Compile-time: List<String> → Runtime: List                     │
│                                                                  │
│  Purpose: Maintain backward compatibility with pre-Java 5 code  │
└──────────────────────────────────────────────────────────────┘
```

### Type Erasure Examples

```java
import java.util.ArrayList;
import java.util.List;

public class TypeErasureDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        // At runtime, both are just ArrayList (raw type)
        System.out.println(stringList.getClass() == intList.getClass());  // true
        
        // Both print "java.util.ArrayList"
        System.out.println(stringList.getClass().getName());
        System.out.println(intList.getClass().getName());
    }
}
```

### Bridge Methods

```java
// After type erasure, compiler generates bridge methods
// to maintain polymorphism

public class BridgeMethodDemo {
    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn;
        // n.setData("Hello");  // Compile error, but at runtime...
        // Bridge method ensures type safety
    }
}

class Node<T> {
    private T data;
    public void setData(T data) { this.data = data; }
    public T getData() { return data; }
}

class MyNode extends Node<Integer> {
    public MyNode(Integer data) { setData(data); }
    
    // Compiler generates bridge method:
    // public void setData(Object data) { setData((Integer) data); }
}
```

### Reification vs Erasure

```java
import java.util.ArrayList;
import java.util.List;

public class ReificationDemo {
    public static void main(String[] args) {
        // Cannot create generic array
        // List<String>[] array = new List<String>[10];  // ERROR
        
        // Can create raw type array
        List[] rawArray = new List[10];  // OK
        
        // Cannot use instanceof with generic type
        List<String> list = new ArrayList<>();
        // if (list instanceof ArrayList<String>)  // ERROR
        if (list instanceof ArrayList) {  // OK — raw type
            System.out.println("It's an ArrayList");
        }
        
        // Cannot use primitive types
        // List<int> intList;  // ERROR
        
        // Cannot create instance of type parameter
        // T obj = new T();  // ERROR
        
        // Cannot use static fields with type parameter
        // static T value;  // ERROR
    }
}
```

---

## 8. Generic Interfaces

### Defining Generic Interfaces

```java
// Generic interface
interface Container<T> {
    void add(T item);
    T get(int index);
    int size();
}

// Implementation with specific type
public class StringContainer implements Container<String> {
    private String[] items = new String[10];
    private int count = 0;
    
    @Override
    public void add(String item) {
        items[count++] = item;
    }
    
    @Override
    public String get(int index) {
        return items[index];
    }
    
    @Override
    public int size() {
        return count;
    }
    
    public static void main(String[] args) {
        StringContainer sc = new StringContainer();
        sc.add("Hello");
        sc.add("World");
        System.out.println(sc.get(0));  // Hello
        System.out.println(sc.size());   // 2
    }
}
```

### Generic Interface with Generic Implementation

```java
// Generic interface
interface Stack<T> {
    void push(T item);
    T pop();
    boolean isEmpty();
}

// Generic implementation
public class GenericStack<T> implements Stack<T> {
    private Object[] elements = new Object[100];
    private int top = -1;
    
    @Override
    public void push(T item) {
        elements[++top] = item;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return (T) elements[top--];
    }
    
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
    
    public static void main(String[] args) {
        Stack<Integer> intStack = new GenericStack<>();
        intStack.push(10);
        intStack.push(20);
        System.out.println(intStack.pop());  // 20
        System.out.println(intStack.pop());  // 10
        
        Stack<String> strStack = new GenericStack<>();
        strStack.push("A");
        strStack.push("B");
        System.out.println(strStack.pop());  // B
    }
}
```

### Generic Interface with Multiple Parameters

```java
interface Mapper<K, V> {
    V map(K key);
}

public class GenericInterfaceMulti implements Mapper<String, Integer> {
    @Override
    public Integer map(String key) {
        return key.length();
    }
    
    public static void main(String[] args) {
        GenericInterfaceMulti mapper = new GenericInterfaceMulti();
        System.out.println(mapper.map("Hello"));  // 5
    }
}
```

---

## 9. Interview Questions

### Q1. What is the need for Generics in Java?

**Answer:**
1. **Type Safety** — Catch type errors at compile time
2. **Eliminate Type Casting** — No need for explicit casts
3. **Code Reusability** — Write once, use with any type
4. **Detect Errors Early** — Compile-time instead of runtime

### Q2. What is the difference between `List<T>` and `List<?>`?

**Answer:**
- `List<T>` — A list of a specific type T. You can add T elements and get T elements.
- `List<?>` — A list of unknown type. You can only read Object elements and add null.

```java
List<String> list1 = new ArrayList<>();
list1.add("Hello");  // OK
String s = list1.get(0);  // OK

List<?> list2 = new ArrayList<String>();
// list2.add("Hello");  // ERROR
Object o = list2.get(0);  // OK
list2.add(null);  // OK
```

### Q3. What is the difference between `List<Object>` and `List<?>`?

**Answer:**
- `List<Object>` — Can add any Object subclass, but reading requires Object reference.
- `List<?>` — Cannot add any element except null.
- `List<Object>` is not a superclass of `List<String>`.

### Q4. Explain PECS (Producer Extends, Consumer Super)

**Answer:**
- **Producer Extends** — If you read from a generic type, use `? extends T`.
- **Consumer Super** — If you write to a generic type, use `? super T`.
- **If both** — Don't use wildcards.
- **If neither** — Use `? extends T`.

### Q5. Why can't we create generic arrays?

**Answer:**
Because of type erasure. At runtime, `T[]` becomes `Object[]`, which would break type safety if you could create it and assign to a specific array type.

```java
// If this were allowed:
List<String>[] array = new List<String>[10];
Object[] objArray = array;
objArray[0] = new ArrayList<Integer>();  // ArrayStoreException at runtime
```

### Q6. Why can't we use primitive types as type parameters?

**Answer:**
Because of type erasure. At runtime, generics are erased to Object (or bound). Primitives are not objects, so they cannot be used. Use wrapper classes instead (autoboxing handles conversion).

### Q7. What is a raw type? Why should it be avoided?

**Answer:**
A raw type is a generic type without type parameters, e.g., `ArrayList` instead of `ArrayList<String>`. It exists for backward compatibility with pre-Java 5 code. It should be avoided because it loses type safety and requires explicit casting.

### Q8. Can a generic class extend another generic class?

**Answer:** Yes.

```java
class Box<T> { }
class ColoredBox<T> extends Box<T> { }
class StringBox extends Box<String> { }
```

### Q9. What is type erasure? What are its implications?

**Answer:**
Type erasure is the process where generic type parameters are removed at compile time and replaced with their bounds or Object. Implications:
- No generic type information at runtime
- Cannot create instances of T
- Cannot use instanceof with generic types
- Cannot create generic arrays
- Cannot use primitives as type parameters

### Q10. What is the difference between `<T extends Number>` and `List<? extends Number>`?

**Answer:**
- `<T extends Number>` — Type parameter declaration for classes/methods. T is a specific type.
- `List<? extends Number>` — Wildcard for a method parameter. List could be `List<Integer>`, `List<Double>`, etc.

### Q11. Why can't static methods use class type parameters?

**Answer:**
Because type parameters belong to the instance. Static methods are shared across all instances and don't know which type parameter to use. However, static methods can declare their own type parameters.

```java
class Example<T> {
    // static T value;  // ERROR
    static <U> void method(U param) { }  // OK
}
```

### Q12. What is the diamond operator (`<>`)?

**Answer:**
Introduced in Java 7, it allows the compiler to infer type arguments from the context, reducing verbosity.

```java
// Before Java 7
List<String> list = new ArrayList<String>();

// Java 7+
List<String> list = new ArrayList<>();
```

### Q13. Can we have multiple bounds on a type parameter?

**Answer:** Yes, but at most one class and multiple interfaces.

```java
<T extends Number & Comparable<T> & Serializable>
```

### Q14. What is the difference between generic methods and generic classes?

**Answer:**
- Generic class: Type parameter is fixed when object is created. All methods use same type.
- Generic method: Type parameter is determined per method call. Different calls can use different types.

### Q15. What is a bridge method?

**Answer:**
A synthetic method generated by the compiler to maintain polymorphism after type erasure. When a class extends a generic class with a specific type, the compiler generates a bridge method that calls the overridden method with proper casting.

---

## 10. Quick Reference

```
┌──────────────────────────────────────────────────────────────┐
│                    GENERICS QUICK REFERENCE                      │
│                                                                  │
│  ┌─────────────────────────────┬─────────────────────────────┐ │
│  │ Syntax                      │ Usage                       │ │
│  ├─────────────────────────────┼─────────────────────────────┤ │
│  │ class Box<T>                │ Generic class               │ │
│  │ interface Container<T>        │ Generic interface           │ │
│  │ <T> void method(T t)        │ Generic method              │ │
│  │ class Box<T extends Number>  │ Bounded type (upper)        │ │
│  │ List<?>                     │ Unbounded wildcard          │ │
│  │ List<? extends Number>       │ Upper bounded wildcard      │ │
│  │ List<? super Integer>        │ Lower bounded wildcard      │ │
│  │ Box<String> box = new Box<>()│ Diamond operator (Java 7+)  │ │
│  └─────────────────────────────┴─────────────────────────────┘ │
│                                                                  │
│  RULES:                                                          │
│  1. Cannot use primitives as type parameters                     │
│  2. Cannot create instances of T: new T()                       │
│  3. Cannot create generic arrays: new T[10]                     │
│  4. Cannot use instanceof with generic types                    │
│  5. Cannot use static fields with class type parameters         │
│  6. Cannot throw or catch generic type exceptions               │
└──────────────────────────────────────────────────────────────┘
```

---

## 11. Key Takeaways

1. **Generics provide type safety** at compile time, eliminating ClassCastException.
2. **Type erasure** removes generic information at runtime for backward compatibility.
3. **Wildcard `?`** increases flexibility: `? extends T` for reading, `? super T` for writing.
4. **Bounded types** restrict type parameters to specific classes or interfaces.
5. **Generic methods** can be declared in non-generic classes and have their own type parameters.
6. **Cannot use primitives** — use wrapper classes with autoboxing.
7. **Cannot create generic arrays** due to type erasure.
8. **Static context** cannot use class type parameters but can declare its own.
9. **PECS principle**: Producer Extends, Consumer Super.
10. **Diamond operator `<>`** (Java 7+) reduces boilerplate in generic declarations.

---

**Happy coding!**

*Master Generics, and you unlock type-safe, reusable code in Java.*
