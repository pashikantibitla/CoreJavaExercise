# 21 — Java 8 Features

> **Topics:** Introduction, Lambda, Functional Interfaces, Method References, Streams API, Optional, Default/Static Methods, New Date/Time API, Nashorn, Base64, Interview FAQs

---

## Table of Contents

1. [Introduction to Java 8](#1-introduction-to-java-8)
2. [Lambda Expressions](#2-lambda-expressions)
3. [Functional Interfaces](#3-functional-interfaces)
4. [Method References](#4-method-references)
5. [Streams API](#5-streams-api)
6. [Optional Class](#6-optional-class)
7. [Default and Static Methods in Interfaces](#7-default-and-static-methods-in-interfaces)
8. [New Date/Time API](#8-new-datetime-api)
9. [Nashorn JavaScript Engine](#9-nashorn-javascript-engine)
10. [Base64 Encoding/Decoding](#10-base64-encodingdecoding)
11. [Interview FAQs](#11-interview-faqs)
12. [Quick Reference](#12-quick-reference)
13. [Key Takeaways](#13-key-takeaways)

---

## 1. Introduction to Java 8

### Overview

Java 8 was released in **March 2014** and is one of the most significant releases in Java history.

### Major Features

```
┌─────────────────────────────────────────────────────────────┐
│                    JAVA 8 MAJOR FEATURES                       │
├─────────────────────────────────────────────────────────────┤
│ 1. Lambda Expressions                                        │
│ 2. Functional Interfaces                                     │
│ 3. Method References                                         │
│ 4. Streams API                                               │
│ 5. Optional Class                                            │
│ 6. Default and Static Methods in Interfaces                  │
│ 7. New Date/Time API (java.time)                             │
│ 8. Nashorn JavaScript Engine                                 │
│ 9. Base64 Encoding/Decoding                                  │
│ 10. Parallel Array Sorting                                     │
│ 11. Type Annotations and Repeatable Annotations              │
│ 12. Method Parameter Reflection                              │
└─────────────────────────────────────────────────────────────┘
```

### Why Java 8 Matters

- Enables **functional programming** in Java
- Reduces boilerplate code significantly
- Introduces powerful APIs for collections and dates
- Allows interfaces to evolve without breaking existing code

---

## 2. Lambda Expressions

### What is a Lambda?

A **lambda expression** is an anonymous function with:
- No name
- No return type declaration
- No modifier

### Syntax

```java
(parameters) -> { body }
```

### Examples

```java
// No parameters
() -> System.out.println("Hello");

// One parameter (parentheses optional)
(x) -> x * x;
x -> x * x;

// Multiple parameters
(int a, int b) -> a + b;

// Block body
(int a, int b) -> {
    int sum = a + b;
    return sum;
};
```

### Functional Interface Requirement

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

// Traditional anonymous class
Calculator add = new Calculator() {
    public int operate(int a, int b) {
        return a + b;
    }
};

// Lambda equivalent
Calculator add = (a, b) -> a + b;
```

### Common Use Cases

```java
// Runnable
Runnable r = () -> System.out.println("Running");

// Comparator
Comparator<String> c = (s1, s2) -> s1.length() - s2.length();

// ActionListener
button.addActionListener(e -> System.out.println("Clicked"));

// Thread
new Thread(() -> System.out.println("Thread running")).start();
```

### Variable Capture

```java
int factor = 2;
Multiplier m = (x) -> x * factor; // factor is effectively final
```

---

## 3. Functional Interfaces

### Definition

An interface with **exactly one abstract method**.

```java
@FunctionalInterface
public interface MyFunc {
    void doWork(); // single abstract method
}
```

### Built-in Functional Interfaces

```
┌─────────────────────────────────────────────────────────────┐
│              BUILT-IN FUNCTIONAL INTERFACES (java.util.function) │
├──────────────────┬──────────────────────┬──────────────────┤
│ Interface        │ Method Signature      │ Use Case          │
├──────────────────┼──────────────────────┼──────────────────┤
│ Predicate<T>     │ boolean test(T t)    │ Filter, condition │
│ Function<T,R>    │ R apply(T t)          │ Transform         │
│ Consumer<T>      │ void accept(T t)      │ Side effect       │
│ Supplier<T>      │ T get()               │ Generate value    │
│ BiPredicate<T,U> │ boolean test(T,U)    │ Two-arg condition │
│ BiFunction<T,U,R>│ R apply(T,U)          │ Two-arg transform │
│ BiConsumer<T,U>  │ void accept(T,U)      │ Two-arg side effect│
│ UnaryOperator<T> │ T apply(T t)          │ Same type transform│
│ BinaryOperator<T>│ T apply(T,T)          │ Same type combine  │
└──────────────────┴──────────────────────┴──────────────────┘
```

### Predicate

```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4)); // true
```

### Function

```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("hello")); // 5
```

### Consumer

```java
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello");
```

### Supplier

```java
Supplier<Double> random = () -> Math.random();
System.out.println(random.get());
```

### Chaining

```java
// Predicate chaining
Predicate<Integer> isPositive = n -> n > 0;
Predicate<Integer> isLessThan100 = n -> n < 100;
Predicate<Integer> combined = isPositive.and(isLessThan100);

// Function chaining
Function<Integer, Integer> multiply = x -> x * 2;
Function<Integer, Integer> add = x -> x + 3;
Function<Integer, Integer> combined = multiply.andThen(add); // (x*2)+3
Function<Integer, Integer> combined2 = multiply.compose(add); // (x+3)*2
```

---

## 4. Method References

### Types

```
┌─────────────────────────────────────────────────────────────┐
│                    METHOD REFERENCE TYPES                      │
├────────────────────┬────────────────────────────────────────┤
│ Type               │ Syntax                                 │
├────────────────────┼────────────────────────────────────────┤
│ Static method      │ ClassName::staticMethod                │
│ Instance method    │ object::instanceMethod                 │
│ Arbitrary object   │ ClassName::instanceMethod              │
│ Constructor        │ ClassName::new                         │
└────────────────────┴────────────────────────────────────────┘
```

### Static Method Reference

```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
numbers.forEach(System.out::println);
```

### Instance Method Reference

```java
String str = "hello";
Supplier<Integer> length = str::length;
```

### Arbitrary Object Reference

```java
List<String> names = Arrays.asList("Alice", "Bob");
names.sort(String::compareToIgnoreCase);
```

### Constructor Reference

```java
Supplier<List<String>> listSupplier = ArrayList::new;
Function<Integer, String[]> arrayCreator = String[]::new;
```

---

## 5. Streams API

### What is a Stream?

A **Stream** is a sequence of elements supporting sequential and parallel aggregate operations.

```
┌─────────────────────────────────────────────────────────────┐
│                    STREAM PIPELINE                               │
│  Source → Intermediate Operations → Terminal Operation        │
│  (List, Set, etc.)   (filter, map, sorted)   (collect, forEach)│
└─────────────────────────────────────────────────────────────┘
```

### Creating Streams

```java
// From collection
List<Integer> list = Arrays.asList(1, 2, 3);
Stream<Integer> stream = list.stream();

// From array
Stream<String> stream = Arrays.stream(new String[]{"a", "b"});

// From values
Stream<Integer> stream = Stream.of(1, 2, 3);

// Generate
Stream<Integer> infinite = Stream.iterate(0, n -> n + 2);
Stream<Double> random = Stream.generate(Math::random);

// Range
IntStream range = IntStream.range(1, 10); // 1 to 9
IntStream closed = IntStream.rangeClosed(1, 10); // 1 to 10
```

### Intermediate Operations

```java
// filter
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

// map
List<Integer> squares = numbers.stream()
    .map(n -> n * n)
    .collect(Collectors.toList());

// flatMap
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);
List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());

// sorted
List<Integer> sorted = numbers.stream()
    .sorted()
    .collect(Collectors.toList());

List<String> sortedDesc = names.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());

// distinct
List<Integer> unique = numbers.stream()
    .distinct()
    .collect(Collectors.toList());

// limit and skip
List<Integer> first5 = numbers.stream().limit(5).collect(Collectors.toList());
List<Integer> rest = numbers.stream().skip(5).collect(Collectors.toList());

// peek (for debugging)
List<Integer> result = numbers.stream()
    .peek(n -> System.out.println("Processing: " + n))
    .map(n -> n * 2)
    .collect(Collectors.toList());
```

### Terminal Operations

```java
// forEach
numbers.stream().forEach(System.out::println);

// collect
List<Integer> list = numbers.stream().collect(Collectors.toList());
Set<Integer> set = numbers.stream().collect(Collectors.toSet());

// reduce
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
Optional<Integer> max = numbers.stream().reduce(Integer::max);

// min/max
Optional<Integer> min = numbers.stream().min(Integer::compareTo);
Optional<Integer> max = numbers.stream().max(Integer::compareTo);

// count
long count = numbers.stream().count();

// anyMatch, allMatch, noneMatch
boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
boolean allPositive = numbers.stream().allMatch(n -> n > 0);
boolean noNegative = numbers.stream().noneMatch(n -> n < 0);

// findFirst, findAny
Optional<Integer> first = numbers.stream().findFirst();
Optional<Integer> any = numbers.stream().findAny();

// toArray
Integer[] arr = numbers.stream().toArray(Integer[]::new);
```

### Collectors

```java
// toList, toSet, toMap
Map<String, Integer> map = people.stream()
    .collect(Collectors.toMap(Person::getName, Person::getAge));

// joining
String joined = names.stream()
    .collect(Collectors.joining(", "));

// groupingBy
Map<String, List<Person>> byCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity));

// partitioningBy
Map<Boolean, List<Integer>> partitioned = numbers.stream()
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));

// counting, summing, averaging
Map<String, Long> countByCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));

Map<String, Double> avgAgeByCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity,
        Collectors.averagingInt(Person::getAge)));

// summingInt
int totalAge = people.stream()
    .collect(Collectors.summingInt(Person::getAge));

// mapping
Map<String, List<String>> namesByCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity,
        Collectors.mapping(Person::getName, Collectors.toList())));
```

### Parallel Streams

```java
List<Integer> result = numbers.parallelStream()
    .map(n -> n * n)
    .collect(Collectors.toList());
```

---

## 6. Optional Class

### Overview

`Optional<T>` is a container that may or may not contain a non-null value.

```java
Optional<String> empty = Optional.empty();
Optional<String> present = Optional.of("hello");
Optional<String> nullable = Optional.ofNullable(getString()); // may be null
```

### Methods

```java
// isPresent
if (opt.isPresent()) {
    System.out.println(opt.get());
}

// ifPresent
opt.ifPresent(System.out::println);

// orElse
String value = opt.orElse("default");

// orElseGet
String value = opt.orElseGet(() -> getDefaultValue());

// orElseThrow
String value = opt.orElseThrow(() -> new NoSuchElementException());

// map
Optional<Integer> length = opt.map(String::length);

// flatMap
Optional<String> upper = opt.flatMap(s -> Optional.of(s.toUpperCase()));

// filter
Optional<String> filtered = opt.filter(s -> s.length() > 3);
```

### Best Practices

```java
// ✅ Good: Return Optional from methods that may return null
public Optional<User> findById(int id) { ... }

// ✅ Good: Use orElse for default
String name = findById(1).map(User::getName).orElse("Unknown");

// ❌ Bad: Never use Optional.get() without checking
// ❌ Bad: Never use Optional as field or method parameter (generally)
```

---

## 7. Default and Static Methods in Interfaces

### Default Methods

```java
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }
}
```

### Static Methods

```java
interface Utility {
    static void printHello() {
        System.out.println("Hello");
    }
}

// Call directly on interface
Utility.printHello();
```

### Diamond Problem Resolution

```java
interface A {
    default void show() { System.out.println("A"); }
}

interface B {
    default void show() { System.out.println("B"); }
}

class C implements A, B {
    public void show() {
        A.super.show(); // explicit call to A's default
    }
}
```

---

## 8. New Date/Time API

### Overview

- Package: `java.time`
- Immutable and thread-safe
- Replaces `java.util.Date` and `java.util.Calendar`

### Key Classes

```
┌─────────────────────────────────────────────────────────────┐
│                    JAVA 8 DATE/TIME CLASSES                    │
├────────────────────┬────────────────────────────────────────┤
│ Class              │ Description                            │
├────────────────────┼────────────────────────────────────────┤
│ LocalDate          │ Date without time (yyyy-MM-dd)       │
│ LocalTime          │ Time without date (HH:mm:ss)         │
│ LocalDateTime      │ Date and time                        │
│ ZonedDateTime      │ Date/time with timezone              │
│ Instant            │ Timestamp (epoch millis)             │
│ Period             │ Date-based amount (days, months)     │
│ Duration           │ Time-based amount (seconds, nanos)   │
│ DateTimeFormatter  │ Formatting and parsing               │
└────────────────────┴────────────────────────────────────────┘
```

### LocalDate

```java
LocalDate today = LocalDate.now();
LocalDate specific = LocalDate.of(2024, 1, 15);
LocalDate parsed = LocalDate.parse("2024-01-15");

// Manipulation
LocalDate tomorrow = today.plusDays(1);
LocalDate lastMonth = today.minusMonths(1);

// Query
int day = today.getDayOfMonth();
DayOfWeek dow = today.getDayOfWeek();
```

### LocalTime

```java
LocalTime now = LocalTime.now();
LocalTime specific = LocalTime.of(14, 30, 0);
LocalTime parsed = LocalTime.parse("14:30:00");
```

### LocalDateTime

```java
LocalDateTime now = LocalDateTime.now();
LocalDateTime specific = LocalDateTime.of(2024, 1, 15, 14, 30);
```

### Period and Duration

```java
Period period = Period.between(startDate, endDate);
Period p = Period.of(1, 2, 3); // 1 year, 2 months, 3 days

Duration duration = Duration.between(startTime, endTime);
Duration d = Duration.ofHours(2);
```

### Instant

```java
Instant now = Instant.now();
Instant later = now.plusSeconds(60);
```

### Formatting

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String formatted = date.format(formatter);
LocalDate parsed = LocalDate.parse("15/01/2024", formatter);
```

---

## 9. Nashorn JavaScript Engine

### Overview

- JavaScript engine bundled with JDK 8
- Replaced Rhino (Java 6/7)
- Can execute JS from Java code

### Usage

```java
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("nashorn");

engine.eval("print('Hello from JavaScript');");

// Invoking functions
engine.eval("function add(a, b) { return a + b; }");
Object result = engine.invokeFunction("add", 10, 20);
```

---

## 10. Base64 Encoding/Decoding

### Overview

- Package: `java.util.Base64`
- Three variants: Basic, URL, MIME

### Usage

```java
import java.util.Base64;

// Basic encoding
String original = "Hello";
String encoded = Base64.getEncoder().encodeToString(original.getBytes());
byte[] decoded = Base64.getDecoder().decode(encoded);

// URL encoding
String urlEncoded = Base64.getUrlEncoder().encodeToString(data);

// MIME encoding
String mimeEncoded = Base64.getMimeEncoder().encodeToString(data);
```

---

## 11. Interview FAQs

### Q1. What is a functional interface?

> An interface with exactly one abstract method. It can have default and static methods.

### Q2. Can we use a lambda without a functional interface?

> No. Lambda expressions can only be assigned to functional interface references.

### Q3. What is the difference between `map` and `flatMap`?

> `map` transforms each element into one element.
> `flatMap` transforms each element into a stream and flattens the result.

### Q4. What is the difference between `findFirst` and `findAny`?

> `findFirst` returns the first element (deterministic).
> `findAny` returns any element (faster in parallel streams).

### Q5. What is a terminal operation?

> An operation that triggers the stream pipeline and produces a result or side effect. Examples: `collect`, `forEach`, `reduce`, `count`.

### Q6. Can we reuse a stream?

> No. Streams are consumed once. Reusing throws `IllegalStateException`.

### Q7. What is the difference between `Stream.of()` and `Arrays.stream()`?

> `Stream.of()` creates a stream of the given values.
> `Arrays.stream()` creates a stream from an array.

### Q8. What is lazy evaluation in streams?

> Intermediate operations are not executed until a terminal operation is invoked.

### Q9. How does `Optional` prevent NullPointerException?

> By wrapping values and providing methods to handle absence explicitly.

### Q10. What are the rules for default methods in interfaces?

> - A class can override a default method.
> - If two interfaces have the same default method, the class must override it.
> - Use `InterfaceName.super.method()` to call a specific default method.

### Q11. What is the difference between `orElse` and `orElseGet`?

> `orElse` evaluates the default value always.
> `orElseGet` evaluates the default value lazily (only when absent).

### Q12. What is the difference between `Collection.stream()` and `Collection.parallelStream()`?

> `stream()` creates a sequential stream.
> `parallelStream()` creates a parallel stream that uses multiple threads.

### Q13. What is the difference between `Comparator` and `Comparable`?

> `Comparable` is for natural ordering (implemented by class).
> `Comparator` is for custom ordering (passed as argument).

### Q14. What is the `collect` method?

> It is a terminal operation that accumulates stream elements into a collection or other mutable result.

### Q15. What is the difference between `reduce` and `collect`?

> `reduce` is for immutable reduction (returns one value).
> `collect` is for mutable reduction (returns a collection or complex object).

### Q16. What is `groupingBy`?

> A collector that groups elements by a classifier function, returning a `Map<K, List<T>>`.

### Q17. What is `partitioningBy`?

> A special case of `groupingBy` that partitions elements into two groups based on a predicate.

### Q18. What is the difference between `Date` and `LocalDate`?

> `Date` is mutable and not thread-safe.
> `LocalDate` is immutable and thread-safe.

### Q19. How do you format a `LocalDate`?

> Use `DateTimeFormatter` with patterns like `dd/MM/yyyy`.

### Q20. What is the `Instant` class?

> It represents a point on the time-line (epoch milliseconds) and is useful for timestamps.

---

## 12. Quick Reference

### Lambda Syntax

```java
// Zero params
() -> expr

// One param
x -> expr

// Multiple params
(x, y) -> expr

// Block
(x, y) -> { stmt; return expr; }
```

### Stream Operations

```java
// filter
stream.filter(predicate)

// map
stream.map(function)

// flatMap
stream.flatMap(funcReturningStream)

// reduce
stream.reduce(identity, binaryOp)

// collect
stream.collect(Collectors.toList())

// forEach
stream.forEach(consumer)

// sorted
stream.sorted()
stream.sorted(comparator)

// distinct
stream.distinct()

// limit
stream.limit(n)

// skip
stream.skip(n)
```

### Optional

```java
Optional.of(value)
Optional.ofNullable(value)
Optional.empty()
opt.isPresent()
opt.ifPresent(consumer)
opt.orElse(default)
opt.orElseGet(supplier)
opt.orElseThrow(supplier)
opt.map(function)
opt.flatMap(function)
opt.filter(predicate)
```

### Date/Time

```java
LocalDate.now()
LocalTime.now()
LocalDateTime.now()
Instant.now()
Period.between(start, end)
Duration.between(start, end)
date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
```

---

## 13. Key Takeaways

1. **Java 8** is the foundation of modern Java programming.
2. **Lambdas** reduce boilerplate and enable functional style.
3. **Functional interfaces** are the contract for lambdas.
4. **Method references** are concise lambda shorthands.
5. **Streams** provide declarative, parallel-ready data processing.
6. **Optional** eliminates null checks and NullPointerException.
7. **Default methods** allow interfaces to evolve.
8. **Static methods** in interfaces provide utility methods.
9. **New Date/Time API** is immutable, thread-safe, and intuitive.
10. **Base64** is a built-in utility for encoding/decoding.
11. **Nashorn** enables JavaScript execution within Java.
12. **Interview focus** is heavily on Streams, Lambdas, Optional, and Date/Time API.

---

**Happy coding! 🚀**

*Master Java 8, and you master modern Java.*
