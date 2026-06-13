# Java OOP Concepts — Interview Questions

> **Source:** YouTube Playlist — [Java Tutorials || Java OOPS](https://www.youtube.com/playlist?list=PLd3UqWTnYXOkwluXExifmJWKkvGa1ywWp)  
> **Level:** Low to Highly Severe  
> **Coverage:** All 44 videos, every concept

---

## Table of Contents

1. [Low Level (Basic)](#1-low-level-basic)
2. [Medium Level](#2-medium-level)
3. [High Level (Advanced)](#3-high-level-advanced)
4. [Highly Severe Level (Expert)](#4-highly-severe-level-expert)

---

## 1. Low Level (Basic)

### Q1. What are the 4 pillars of OOP?

**Answer:**
1. **Abstraction** — Hiding implementation details, showing only essential features.
2. **Encapsulation** — Binding data and methods together, hiding internal state.
3. **Inheritance** — Acquiring properties of parent class (IS-A relationship).
4. **Polymorphism** — One interface, many implementations (overloading + overriding).

### Q2. What is the difference between class and object?

**Answer:**
- **Class**: Blueprint/template. Defines structure and behavior. Logical entity.
- **Object**: Instance of class. Occupies memory. Physical entity.

```java
class Student { }           // Class
Student s = new Student();  // Object
```

### Q3. What is encapsulation?

**Answer:** Encapsulation is bundling data (fields) and methods that operate on that data into a single unit (class), and restricting direct access to some components.

```java
public class Employee {
    private String name;           // Hidden
    private double salary;         // Hidden
    
    public String getName() {       // Controlled access
        return name;
    }
    
    public void setSalary(double salary) {  // Validation
        if (salary > 0) {
            this.salary = salary;
        }
    }
}
```

### Q4. What is abstraction?

**Answer:** Abstraction hides implementation complexity and shows only essential features. Achieved through abstract classes and interfaces.

```java
abstract class Vehicle {
    abstract void start();  // What to do (not how)
    
    void stop() {           // Common implementation
        System.out.println("Vehicle stopped");
    }
}
```

### Q5. What is inheritance?

**Answer:** Inheritance allows a class to inherit properties and methods from another class. Promotes code reuse.

```java
class Animal {                    // Parent
    void eat() { }
}

class Dog extends Animal {       // Child
    void bark() { }
}

// Dog IS-A Animal
// Dog inherits eat() from Animal
```

### Q6. What is polymorphism?

**Answer:** Polymorphism means "many forms". Same method behaves differently.

```java
// Compile-time: Overloading
class Math {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}

// Runtime: Overriding
class Animal {
    void makeSound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    void makeSound() { System.out.println("Dog barking"); }
}
```

### Q7. What is method overloading?

**Answer:** Same method name, different parameters (number, type, or order). Compile-time polymorphism.

```java
void display(int a) { }
void display(String a) { }
void display(int a, int b) { }
void display(int a, String b) { }
```

### Q8. What is method overriding?

**Answer:** Same method signature in parent and child. Child provides specific implementation. Runtime polymorphism.

```java
class Parent {
    void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    @Override
    void show() { System.out.println("Child"); }
}
```

### Q9. What is the difference between `public`, `protected`, `default`, and `private`?

**Answer:**

| Modifier | Same Class | Same Package | Subclass (any package) | Other Packages |
|----------|-----------|-------------|----------------------|----------------|
| public | ✅ | ✅ | ✅ | ✅ |
| protected | ✅ | ✅ | ✅ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| private | ✅ | ❌ | ❌ | ❌ |

### Q10. What is `super` keyword?

**Answer:** `super` refers to parent class object.
- Access parent class methods/fields.
- Call parent class constructor.

```java
class Parent {
    int x = 10;
    Parent() { System.out.println("Parent constructor"); }
}
class Child extends Parent {
    int x = 20;
    Child() {
        super();           // Call parent constructor
        System.out.println(super.x);  // 10
        System.out.println(this.x);   // 20
    }
}
```

### Q11. What is `this` keyword?

**Answer:** `this` refers to current object.
- Differentiate instance variables from local variables.
- Call current class constructor.
- Pass current object as parameter.

```java
class Student {
    String name;
    Student(String name) {
        this.name = name;  // this.name = instance, name = parameter
    }
}
```

### Q12. What is constructor?

**Answer:** Constructor is a special method that initializes an object. Same name as class, no return type.

```java
class Student {
    Student() { }                    // Default constructor
    Student(String name) { }         // Parameterized constructor
    Student(Student s) { }            // Copy constructor
}
```

### Q13. What is the difference between constructor and method?

**Answer:**

| Feature | Constructor | Method |
|---------|-------------|--------|
| Name | Same as class | Any valid name |
| Return type | No return type | Has return type (or void) |
| Purpose | Initialize object | Perform operation |
| Called | Automatically when creating object | Explicitly called |
| Inheritance | Not inherited | Inherited |

### Q14. What is abstract class?

**Answer:** Abstract class cannot be instantiated. Can have abstract methods (no body) and concrete methods (with body).

```java
abstract class Animal {
    abstract void makeSound();  // Abstract method
    void sleep() { }             // Concrete method
}
```

### Q15. What is interface?

**Answer:** Interface is a contract that defines methods a class must implement. All methods are abstract (until Java 8). All variables are public static final.

```java
interface Drawable {
    void draw();           // public abstract
    int MAX = 100;         // public static final
}
```

### Q16. What is the difference between abstract class and interface?

**Answer:**

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Methods | Abstract + Concrete | Abstract (Java 7), Default/Static (Java 8+) |
| Variables | Any | public static final only |
| Constructor | Yes | No |
| Multiple inheritance | No | Yes (type) |
| extends/implements | extends | implements |

### Q17. What is `final` keyword?

**Answer:** `final` means cannot be changed.
- `final` variable: Constant.
- `final` method: Cannot be overridden.
- `final` class: Cannot be inherited.

### Q18. What is `static` keyword?

**Answer:** `static` belongs to class, not object.
- `static` variable: Shared by all objects.
- `static` method: Can be called without object.
- `static` block: Executes when class is loaded.

```java
class Counter {
    static int count = 0;  // Shared
    Counter() { count++; }
}
```

### Q19. What is IS-A vs HAS-A relationship?

**Answer:**
- **IS-A**: Inheritance (`extends`). Dog IS-A Animal.
- **HAS-A**: Composition (object reference). Car HAS-A Engine.

```java
// IS-A
class Dog extends Animal { }

// HAS-A
class Car {
    Engine engine = new Engine();  // Car HAS-A Engine
}
```

### Q20. What is `instanceof` operator?

**Answer:** Checks if an object is of a specific type. Returns true/false.

```java
Animal a = new Dog();
System.out.println(a instanceof Dog);     // true
System.out.println(a instanceof Animal);  // true
System.out.println(a instanceof Cat);     // false
```

---

## 2. Medium Level

### Q21. What is the difference between method overloading and overriding?

**Answer:**

| Feature | Overloading | Overriding |
|---------|-------------|------------|
| Class | Same class | Parent + Child |
| Signature | Different parameters | Same parameters |
| Return type | Can be different | Same or covariant |
| Polymorphism | Compile-time | Runtime |
| Static | Can be static | Cannot be static |

### Q22. Can we overload static methods?

**Answer:** Yes, static methods can be overloaded.

```java
class Math {
    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
}
```

### Q23. Can we override static methods?

**Answer:** No, static methods are hidden, not overridden.

```java
class Parent {
    static void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    static void show() { System.out.println("Child"); }  // Hiding
}

Parent p = new Child();
p.show();  // "Parent" (resolved by reference type)
```

### Q24. What is method hiding?

**Answer:** When child class defines a static method with the same signature as parent's static method. Not overriding.

```java
class Parent {
    static void method() { }
}
class Child extends Parent {
    static void method() { }  // Hiding
}
```

### Q25. What is covariant return type?

**Answer:** Overridden method can return a subtype of parent method's return type.

```java
class Parent {
    Object get() { return null; }
}
class Child extends Parent {
    @Override
    String get() { return ""; }  // String IS-A Object
}
```

### Q26. What is the difference between `==` and `.equals()` for objects?

**Answer:**
- `==` compares references (memory addresses).
- `.equals()` compares content (can be overridden).

```java
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2);      // false
System.out.println(s1.equals(s2));  // true
```

### Q27. What is `hashCode()` and `equals()` contract?

**Answer:**
1. If `equals()` returns true, `hashCode()` must be same.
2. If `hashCode()` is same, `equals()` may or may not return true.
3. If `equals()` is overridden, `hashCode()` must also be overridden.

### Q28. What is a tightly encapsulated class?

**Answer:** All fields are private. Access is only through getters/setters.

```java
public class TightlyEncapsulated {
    private int x;           // All private
    private String y;
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
}
```

### Q29. What is the difference between association, aggregation, and composition?

**Answer:**

| Relationship | Description | Example |
|-------------|-------------|---------|
| Association | General relationship | Teacher teaches Student |
| Aggregation | HAS-A, weak (can exist independently) | Department HAS-A Professor |
| Composition | HAS-A, strong (cannot exist independently) | Car HAS-A Engine |

### Q30. What is diamond problem in Java?

**Answer:** Diamond problem occurs when a class inherits from two classes that have the same method. Java does not support multiple inheritance with classes to avoid this.

```java
// Java avoids this by not allowing:
// class A extends B, C { }  // ❌ ERROR

// Solution: Use interfaces
class A implements B, C { }  // ✅
```

### Q31. Can a class extend itself?

**Answer:** No, cyclic inheritance is not allowed.

```java
class A extends A { }  // ❌ ERROR: cyclic inheritance

class A extends B { }
class B extends A { }  // ❌ ERROR: cyclic inheritance
```

### Q32. What is `Object` class?

**Answer:** `Object` is the root class of all Java classes. Every class implicitly extends `Object`.

```java
// Methods in Object:
// equals(), hashCode(), toString(), clone(), finalize()
// getClass(), wait(), notify(), notifyAll()
```

### Q33. What is `toString()` method?

**Answer:** Returns string representation of an object. Should be overridden.

```java
class Student {
    int id;
    String name;
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name + "}";
    }
}
```

### Q34. What is `clone()` method?

**Answer:** Creates a copy of an object. Object must implement `Cloneable`.

```java
class Student implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();  // Shallow copy
    }
}
```

### Q35. What is the difference between shallow copy and deep copy?

**Answer:**
- **Shallow copy**: Copies reference. Changes reflect in both.
- **Deep copy**: Creates new objects. Independent copies.

### Q36. What is constructor chaining?

**Answer:** Calling one constructor from another constructor.

```java
class Student {
    String name;
    int age;
    
    Student() {
        this("Unknown", 0);  // Call parameterized constructor
    }
    
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### Q37. What is the difference between `this()` and `super()`?

**Answer:**
- `this()`: Call another constructor in same class.
- `super()`: Call parent class constructor.
- Both must be first statement in constructor.
- Cannot have both in same constructor.

### Q38. What is `final` class and can it be abstract?

**Answer:** `final` class cannot be extended. `final` and `abstract` cannot be used together (contradiction).

```java
// final abstract class A { }  // ❌ ERROR
```

### Q39. What is interface with default methods (Java 8+)?

**Answer:** Java 8 allows interfaces to have methods with default implementation.

```java
interface Drawable {
    void draw();                           // Abstract
    default void print() {                // Default method
        System.out.println("Printing");
    }
    static void show() {                  // Static method
        System.out.println("Showing");
    }
}
```

### Q40. What is functional interface?

**Answer:** Interface with exactly one abstract method. Can be used with lambda.

```java
@FunctionalInterface
interface Runnable {
    void run();  // Only one abstract method
}

// Lambda:
Runnable r = () -> System.out.println("Running");
```

---

## 3. High Level (Advanced)

### Q41. What is the difference between composition and inheritance?

**Answer:**
- **Inheritance**: IS-A relationship. Strong coupling. Parent changes affect child.
- **Composition**: HAS-A relationship. Loose coupling. Can change behavior at runtime.

Prefer composition over inheritance when:
- No true IS-A relationship.
- Need flexibility.
- Want to avoid fragile base class problem.

### Q42. What is Liskov Substitution Principle (LSP)?

**Answer:** Objects of parent class should be replaceable with objects of child class without breaking the program.

```java
// Violation:
class Rectangle {
    void setWidth(int w) { }
    void setHeight(int h) { }
}
class Square extends Rectangle {
    // Square violates LSP because setWidth and setHeight
    // must maintain width == height, breaking Rectangle behavior
}
```

### Q43. What is SOLID principles?

**Answer:**
1. **S**ingle Responsibility Principle: One class, one responsibility.
2. **O**pen/Closed Principle: Open for extension, closed for modification.
3. **L**iskov Substitution Principle: Child can substitute parent.
4. **I**nterface Segregation Principle: Small, focused interfaces.
5. **D**ependency Inversion Principle: Depend on abstractions.

### Q44. What is the difference between compile-time and runtime polymorphism?

**Answer:**

| Feature | Compile-time | Runtime |
|---------|-------------|---------|
| Also called | Static/Early binding | Dynamic/Late binding |
| Mechanism | Overloading | Overriding |
| Decision | By compiler | By JVM |
| Performance | Faster | Slower (virtual method table) |
| Example | Method overloading | Method overriding |

### Q45. What is Virtual Method Table (VMT)?

**Answer:** VMT is a table of function pointers used by JVM to resolve method calls at runtime. Each class has its own VMT.

```
Animal VMT: [makeSound() → Animal.makeSound()]
Dog VMT:    [makeSound() → Dog.makeSound()]
Cat VMT:    [makeSound() → Cat.makeSound()]

// At runtime, JVM looks up VMT of actual object type
```

### Q46. What is object type casting?

**Answer:** Converting one object type to another.

```java
// Upcasting (implicit)
Animal a = new Dog();  // ✅

// Downcasting (explicit)
Dog d = (Dog) a;      // ✅

// Invalid downcasting
Animal a2 = new Animal();
// Dog d2 = (Dog) a2;  // ❌ ClassCastException

// instanceof check
if (a instanceof Dog) {
    Dog d3 = (Dog) a;  // ✅ Safe
}
```

### Q47. What are the 3 mantras of object type casting?

**Answer:**
1. **B IS-A A** must be true for casting B to A.
2. **Compiler** checks reference type compatibility.
3. **JVM** checks object type compatibility at runtime.

### Q48. What is the difference between `Class.forName()` and `new`?

**Answer:**
- `new`: Compile-time type checking, creates object directly.
- `Class.forName()`: Runtime type loading, dynamic object creation.

```java
// Compile-time
Student s = new Student();

// Runtime
Class<?> c = Class.forName("Student");
Student s = (Student) c.newInstance();
```

### Q49. What is reflection?

**Answer:** Reflection allows inspecting and modifying classes, methods, fields at runtime.

```java
Class<?> c = Class.forName("java.util.ArrayList");
Method[] methods = c.getDeclaredMethods();
Field[] fields = c.getDeclaredFields();
```

### Q50. What is dynamic polymorphism?

**Answer:** Method resolution happens at runtime based on actual object type.

```java
Animal a = new Dog();
a.makeSound();  // "Dog barking" — resolved at runtime

// JVM uses VMT to determine actual method
```

### Q51. What is the difference between abstract class and interface in Java 8+?

**Answer:**

| Feature | Abstract Class | Interface (Java 8+) |
|---------|---------------|-------------------|
| Default methods | Yes | Yes |
| Static methods | Yes | Yes |
| Private methods | Yes | Yes (Java 9+) |
| Constructor | Yes | No |
| Instance variables | Yes | No (constants only) |
| Multiple inheritance | No | Yes |

### Q52. What is marker interface?

**Answer:** Interface with no methods. Used to mark classes for special behavior.

```java
// Examples:
Serializable, Cloneable, Remote, EventListener

// Purpose: JVM provides special behavior to classes implementing these
```

### Q53. What is the difference between `Comparator` and `Comparable`?

**Answer:**
- `Comparable`: Natural ordering. `compareTo()`. Class implements it.
- `Comparator`: Custom ordering. `compare()`. External class.

```java
// Comparable
class Student implements Comparable<Student> {
    public int compareTo(Student s) {
        return this.id - s.id;
    }
}

// Comparator
Comparator<Student> byName = (s1, s2) -> s1.name.compareTo(s2.name);
```

### Q54. What is inner class?

**Answer:** Class defined inside another class.

```java
class Outer {
    class Inner { }          // Member inner class
    static class Static { }   // Static inner class
    void method() {
        class Local { }       // Local inner class
    }
}

// Anonymous inner class
Runnable r = new Runnable() {
    public void run() { }
};
```

### Q55. What is the difference between inner class and nested class?

**Answer:**
- **Inner class**: Non-static. Belongs to instance. Accesses instance members.
- **Nested class**: Static. Belongs to class. Accesses only static members.

---

## 4. Highly Severe Level (Expert)

### Q56. What is the difference between `Class.forName()` and `ClassLoader.loadClass()`?

**Answer:**
- `Class.forName()`: Loads and **initializes** class.
- `ClassLoader.loadClass()`: Loads class but **does not initialize**.

```java
// Class.forName() — initialization happens
Class.forName("com.mysql.jdbc.Driver");  // Driver registers itself

// loadClass() — no initialization
ClassLoader.getSystemClassLoader().loadClass("com.example.Foo");
```

### Q57. What is the `invokedynamic` instruction?

**Answer:** JVM instruction introduced in Java 7 for dynamic method invocation. Used by lambda expressions and dynamic languages.

```java
// Lambda uses invokedynamic
Runnable r = () -> System.out.println("Hello");

// Instead of generating anonymous class, JVM uses invokedynamic
// to link lambda at runtime
```

### Q58. What is the difference between lambda and anonymous class?

**Answer:**

| Feature | Lambda | Anonymous Class |
|---------|--------|-----------------|
| Syntax | Concise | Verbose |
| Class file | No (invokedynamic) | Yes (Outer$1.class) |
| Scope | No `this` ambiguity | `this` refers to anonymous class |
| Performance | Better | Slower |
| Serialization | No | Yes |

### Q59. What is method reference?

**Answer:** Shorthand for lambda that calls an existing method.

```java
// Lambda
list.forEach(s -> System.out.println(s));

// Method reference
list.forEach(System.out::println);

// Types:
// Static method: ClassName::methodName
// Instance method: object::methodName
// Constructor: ClassName::new
```

### Q60. What is `var` (Java 10+)?

**Answer:** Local variable type inference. Compiler infers type from initializer.

```java
var list = new ArrayList<String>();  // ArrayList<String>
var num = 10;                       // int
var name = "Hello";                 // String

// Rules:
// 1. Must initialize immediately
// 2. Cannot be null without type
// 3. Cannot use with method parameters
// 4. Cannot use with instance variables
```

### Q61. What is `record` (Java 16+)?

**Answer:** Compact class for immutable data. Automatically generates constructor, getters, equals, hashCode, toString.

```java
record Person(String name, int age) { }

// Usage:
Person p = new Person("Alice", 30);
System.out.println(p.name());  // "Alice"
System.out.println(p.age());   // 30

// Automatically generates:
// - Constructor
// - Getters (name(), age())
// - equals(), hashCode(), toString()
```

### Q62. What is `sealed` class (Java 17+)?

**Answer:** Restricts which classes can extend it.

```java
public abstract sealed class Shape
    permits Circle, Rectangle, Square { }

public final class Circle extends Shape { }
public final class Rectangle extends Shape { }
public final class Square extends Shape { }

// Only Circle, Rectangle, Square can extend Shape
```

### Q63. What is pattern matching for instanceof (Java 16+)?

**Answer:** Combines instanceof and casting in one step.

```java
// Old way:
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// New way:
if (obj instanceof String s) {
    System.out.println(s.length());  // s is already cast
}
```

### Q64. What is switch expression (Java 14+)?

**Answer:** Switch that returns a value.

```java
// Old way:
String day;
switch (num) {
    case 1: day = "Monday"; break;
    case 2: day = "Tuesday"; break;
    default: day = "Unknown";
}

// New way:
String day = switch (num) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    default -> "Unknown";
};

// With yield:
String day = switch (num) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    default -> {
        System.out.println("Unknown");
        yield "Unknown";
    }
};
```

### Q65. What is text block (Java 15+)?

**Answer:** Multi-line string literal.

```java
String json = """
    {
        "name": "John",
        "age": 30
    }
    """;

// No need for \n or string concatenation
// Automatically strips indentation
```

### Q66. What is the difference between `var` and `dynamic` typing?

**Answer:**
- `var`: Compile-time type inference. Type is known at compile time.
- `dynamic` (other languages): Runtime type resolution. Type can change.

```java
var x = 10;     // x is int (fixed)
// x = "Hello";  // ❌ ERROR

// Java is statically typed — var is just syntactic sugar
```

### Q67. What is `null` in Java?

**Answer:** `null` is a special value that means "no object reference".

```java
String s = null;     // No object
// s.length();       // ❌ NullPointerException

// null is not an object
// null instanceof String → false (always)
// null == null → true
```

### Q68. What is `Optional` (Java 8+)?

**Answer:** Container for a value that may or may not be present. Avoids null checks.

```java
Optional<String> optional = Optional.of("Hello");

// Methods:
optional.isPresent();      // true
optional.get();            // "Hello"
optional.orElse("Default"); // "Hello"
optional.ifPresent(System.out::println);

// Usage:
Optional<String> result = findById(1);
result.ifPresent(s -> System.out.println(s));
```

### Q69. What is `Stream` API (Java 8+)?

**Answer:** Functional-style operations on collections.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Filter and map
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .collect(Collectors.toList());

// result: [4, 8]

// Operations: filter, map, flatMap, reduce, collect, forEach, sorted, distinct
```

### Q70. What is `Collectors`?

**Answer:** Utility class for collecting stream results.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Collect to list
List<String> list = names.stream().collect(Collectors.toList());

// Collect to set
Set<String> set = names.stream().collect(Collectors.toSet());

// Collect to map
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(n -> n, String::length));

// Joining
String joined = names.stream().collect(Collectors.joining(", "));

// Grouping
Map<Integer, List<String>> grouped = names.stream()
    .collect(Collectors.groupingBy(String::length));
```

---

**Happy coding! 🚀**

*Master these OOP interview questions to excel in any Java interview.*
