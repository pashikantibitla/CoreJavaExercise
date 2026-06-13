# Java OOP Concepts — Coding Problems

> **Source:** YouTube Playlist — [Java Tutorials || Java OOPS](https://www.youtube.com/playlist?list=PLd3UqWTnYXOkwluXExifmJWKkvGa1ywWp)  
> **Level:** Low to Highly Severe  
> **Coverage:** All 44 videos, every OOP concept

---

## Table of Contents

1. [Low Level (Basic)](#1-low-level-basic)
2. [Medium Level](#2-medium-level)
3. [High Level (Advanced)](#3-high-level-advanced)
4. [Highly Severe Level (Expert)](#4-highly-severe-level-expert)

---

## 1. Low Level (Basic)

### Problem 1: Simple Class and Object

**Logic:** Create a class with fields and methods, instantiate objects.

```java
// File: Student.java
public class Student {
    private String name;
    private int rollNo;
    
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
    
    public void display() {
        System.out.println("Name: " + name + ", Roll No: " + rollNo);
    }
    
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 101);
        Student s2 = new Student("Bob", 102);
        s1.display();
        s2.display();
    }
}
```

### Problem 2: Encapsulation — Bank Account

**Logic:** Private fields with getters/setters.

```java
// File: BankAccount.java
public class BankAccount {
    private double balance;
    private String accountNumber;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC123", 1000);
        account.deposit(500);
        account.withdraw(200);
        System.out.println("Balance: " + account.getBalance());
    }
}
```

### Problem 3: Inheritance — Animal Hierarchy

**Logic:** Parent class with child classes extending.

```java
// File: AnimalDemo.java
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof!");
    }
    
    public void fetch() {
        System.out.println(name + " is fetching the ball");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow!");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Animal a1 = new Dog("Buddy");
        Animal a2 = new Cat("Whiskers");
        
        a1.makeSound();  // Buddy barks: Woof!
        a2.makeSound();  // Whiskers meows: Meow!
    }
}
```

### Problem 4: Method Overloading — Calculator

**Logic:** Multiple methods with same name, different parameters.

```java
// File: Calculator.java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 10));          // 15
        System.out.println(calc.add(5.5, 10.5));    // 16.0
        System.out.println(calc.add(1, 2, 3));        // 6
        System.out.println(calc.add("Hello", "World"));  // HelloWorld
    }
}
```

### Problem 5: Constructor Overloading — Rectangle

**Logic:** Multiple constructors with different parameters.

```java
// File: Rectangle.java
public class Rectangle {
    private int length;
    private int width;
    
    // Default constructor
    public Rectangle() {
        this(0, 0);
    }
    
    // Single parameter (square)
    public Rectangle(int side) {
        this(side, side);
    }
    
    // Two parameters
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }
    
    public int area() {
        return length * width;
    }
    
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();        // 0 x 0
        Rectangle r2 = new Rectangle(5);         // 5 x 5
        Rectangle r3 = new Rectangle(4, 6);    // 4 x 6
        
        System.out.println("Area 1: " + r1.area());  // 0
        System.out.println("Area 2: " + r2.area());  // 25
        System.out.println("Area 3: " + r3.area());  // 24
    }
}
```

### Problem 6: Static vs Instance — Counter

**Logic:** Demonstrate static variable shared across instances.

```java
// File: Counter.java
public class Counter {
    private static int staticCount = 0;
    private int instanceCount = 0;
    
    public Counter() {
        staticCount++;
        instanceCount++;
    }
    
    public static int getStaticCount() {
        return staticCount;
    }
    
    public int getInstanceCount() {
        return instanceCount;
    }
    
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        
        System.out.println("Static: " + Counter.getStaticCount());    // 3
        System.out.println("Instance c1: " + c1.getInstanceCount());  // 1
        System.out.println("Instance c2: " + c2.getInstanceCount());  // 1
        System.out.println("Instance c3: " + c3.getInstanceCount());  // 1
    }
}
```

### Problem 7: Abstract Class — Shape

**Logic:** Abstract class with abstract and concrete methods.

```java
// File: ShapeDemo.java
abstract class Shape {
    abstract double area();
    abstract double perimeter();
    
    void display() {
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length, width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    double area() {
        return length * width;
    }
    
    @Override
    double perimeter() {
        return 2 * (length + width);
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        
        circle.display();
        rectangle.display();
    }
}
```

### Problem 8: Interface — Drawable

**Logic:** Interface with multiple implementations.

```java
// File: DrawableDemo.java
interface Drawable {
    void draw();
    void resize(double factor);
}

class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing Circle with radius " + radius);
    }
    
    @Override
    public void resize(double factor) {
        radius *= factor;
    }
}

class Rectangle implements Drawable {
    private double length, width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle " + length + " x " + width);
    }
    
    @Override
    public void resize(double factor) {
        length *= factor;
        width *= factor;
    }
}

public class DrawableDemo {
    public static void main(String[] args) {
        Drawable[] shapes = {
            new Circle(5),
            new Rectangle(4, 6)
        };
        
        for (Drawable shape : shapes) {
            shape.draw();
            shape.resize(2);
            shape.draw();
        }
    }
}
```

### Problem 9: Composition — Car and Engine

**Logic:** HAS-A relationship using object composition.

```java
// File: CarEngine.java
class Engine {
    private String type;
    private int horsepower;
    
    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }
    
    public void start() {
        System.out.println(type + " engine starting with " + horsepower + " HP");
    }
    
    public void stop() {
        System.out.println("Engine stopped");
    }
}

class Car {
    private String brand;
    private Engine engine;  // Composition: Car HAS-A Engine
    
    public Car(String brand, String engineType, int horsepower) {
        this.brand = brand;
        this.engine = new Engine(engineType, horsepower);
    }
    
    public void startCar() {
        System.out.println(brand + " car starting...");
        engine.start();
    }
    
    public void stopCar() {
        engine.stop();
        System.out.println(brand + " car stopped");
    }
}

public class CarEngine {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "V6", 300);
        car.startCar();
        car.stopCar();
    }
}
```

### Problem 10: Polymorphism — Payment System

**Logic:** Runtime polymorphism with different payment methods.

```java
// File: PaymentSystem.java
abstract class Payment {
    protected double amount;
    
    public Payment(double amount) {
        this.amount = amount;
    }
    
    abstract void processPayment();
    
    void displayAmount() {
        System.out.println("Amount: $" + amount);
    }
}

class CreditCardPayment extends Payment {
    private String cardNumber;
    
    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
    
    @Override
    void processPayment() {
        System.out.println("Processing credit card payment...");
        System.out.println("Card ending in: " + cardNumber.substring(cardNumber.length() - 4));
        displayAmount();
    }
}

class PayPalPayment extends Payment {
    private String email;
    
    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }
    
    @Override
    void processPayment() {
        System.out.println("Processing PayPal payment...");
        System.out.println("Account: " + email);
        displayAmount();
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Payment[] payments = {
            new CreditCardPayment(100.50, "1234567890123456"),
            new PayPalPayment(75.25, "user@example.com")
        };
        
        for (Payment payment : payments) {
            payment.processPayment();
            System.out.println();
        }
    }
}
```

---

## 2. Medium Level

### Problem 11: Singleton Pattern — Database Connection

**Logic:** Ensure only one instance exists.

```java
// File: DatabaseConnection.java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;
    
    private DatabaseConnection() {
        connectionString = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Database connection created");
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Connected to: " + connectionString);
    }
    
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        db1.connect();
        System.out.println("Same instance? " + (db1 == db2));  // true
    }
}
```

### Problem 12: Factory Pattern — Shape Factory

**Logic:** Create objects without exposing creation logic.

```java
// File: ShapeFactory.java
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }
}

class ShapeFactory {
    public static Shape createShape(String type) {
        switch (type.toLowerCase()) {
            case "circle": return new Circle();
            case "square": return new Square();
            case "triangle": return new Triangle();
            default: throw new IllegalArgumentException("Unknown shape: " + type);
        }
    }
}

public class ShapeFactoryDemo {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("circle");
        Shape square = ShapeFactory.createShape("square");
        
        circle.draw();   // Drawing Circle
        square.draw();   // Drawing Square
    }
}
```

### Problem 13: Builder Pattern — Computer Builder

**Logic:** Step-by-step construction of complex objects.

```java
// File: ComputerBuilder.java
class Computer {
    private String cpu;
    private int ram;
    private int storage;
    private boolean hasGPU;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGPU = builder.hasGPU;
    }
    
    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + "GB, Storage=" + storage + "GB, GPU=" + hasGPU + "]";
    }
    
    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        private boolean hasGPU;
        
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder hasGPU(boolean hasGPU) {
            this.hasGPU = hasGPU;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}

public class ComputerBuilder {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
            .cpu("Intel i9")
            .ram(32)
            .storage(1000)
            .hasGPU(true)
            .build();
        
        System.out.println(gamingPC);
    }
}
```

### Problem 14: Multiple Interfaces — Smart Phone

**Logic:** Implement multiple interfaces.

```java
// File: SmartPhone.java
interface Camera {
    void takePhoto();
    void recordVideo();
}

interface MusicPlayer {
    void playMusic();
    void stopMusic();
}

interface GPS {
    void navigate(String destination);
}

class SmartPhone implements Camera, MusicPlayer, GPS {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo...");
    }
    
    @Override
    public void recordVideo() {
        System.out.println("Recording video...");
    }
    
    @Override
    public void playMusic() {
        System.out.println("Playing music...");
    }
    
    @Override
    public void stopMusic() {
        System.out.println("Music stopped");
    }
    
    @Override
    public void navigate(String destination) {
        System.out.println("Navigating to: " + destination);
    }
}

public class SmartPhoneDemo {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone();
        phone.takePhoto();
        phone.playMusic();
        phone.navigate("New York");
    }
}
```

### Problem 15: Inheritance with Constructor Chaining — Employee Hierarchy

**Logic:** Parent and child constructors with super().

```java
// File: EmployeeHierarchy.java
class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Employee extends Person {
    protected String employeeId;
    protected double salary;
    
    public Employee(String name, int age, String employeeId, double salary) {
        super(name, age);
        this.employeeId = employeeId;
        this.salary = salary;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Employee ID: " + employeeId + ", Salary: $" + salary);
    }
}

class Manager extends Employee {
    private String department;
    private int teamSize;
    
    public Manager(String name, int age, String employeeId, double salary, String department, int teamSize) {
        super(name, age, employeeId, salary);
        this.department = department;
        this.teamSize = teamSize;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Department: " + department + ", Team Size: " + teamSize);
    }
}

public class EmployeeHierarchy {
    public static void main(String[] args) {
        Manager manager = new Manager("John", 35, "E001", 80000, "IT", 10);
        manager.display();
    }
}
```

### Problem 16: Method Overriding with super — Enhanced Display

**Logic:** Override method while calling parent version.

```java
// File: EnhancedDisplay.java
class Product {
    protected String name;
    protected double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public void display() {
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
    }
}

class ElectronicProduct extends Product {
    private String brand;
    private int warranty;
    
    public ElectronicProduct(String name, double price, String brand, int warranty) {
        super(name, price);
        this.brand = brand;
        this.warranty = warranty;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warranty + " years");
    }
}

public class EnhancedDisplay {
    public static void main(String[] args) {
        ElectronicProduct laptop = new ElectronicProduct("Laptop", 1200, "Dell", 2);
        laptop.display();
    }
}
```

### Problem 17: Abstract Class with Template Method — Game Development

**Logic:** Template method pattern with abstract class.

```java
// File: GameTemplate.java
abstract class Game {
    // Template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
    
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
}

class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket game initialized");
    }
    
    @Override
    void startPlay() {
        System.out.println("Cricket game started");
    }
    
    @Override
    void endPlay() {
        System.out.println("Cricket game ended");
    }
}

class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football game initialized");
    }
    
    @Override
    void startPlay() {
        System.out.println("Football game started");
    }
    
    @Override
    void endPlay() {
        System.out.println("Football game ended");
    }
}

public class GameTemplate {
    public static void main(String[] args) {
        Game cricket = new Cricket();
        cricket.play();
        
        System.out.println();
        
        Game football = new Football();
        football.play();
    }
}
```

### Problem 18: Interface with Default Methods — Vehicle Interface

**Logic:** Java 8+ default and static methods in interface.

```java
// File: VehicleInterface.java
interface Vehicle {
    void start();
    void stop();
    
    default void honk() {
        System.out.println("Beep beep!");
    }
    
    static void showInfo() {
        System.out.println("This is a vehicle");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    @Override
    public void stop() {
        System.out.println("Car stopped");
    }
}

public class VehicleInterface {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.honk();  // Default method
        car.stop();
        
        Vehicle.showInfo();  // Static method
    }
}
```

### Problem 19: Object Type Casting — Animal Casting

**Logic:** Demonstrate upcasting and downcasting.

```java
// File: AnimalCasting.java
class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barking");
    }
    
    void fetch() {
        System.out.println("Dog fetching");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meowing");
    }
    
    void climb() {
        System.out.println("Cat climbing");
    }
}

public class AnimalCasting {
    public static void main(String[] args) {
        // Upcasting (implicit)
        Animal animal = new Dog();
        animal.makeSound();  // Dog barking
        
        // Downcasting (explicit)
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.fetch();  // Dog fetching
        }
        
        // Invalid downcasting
        Animal cat = new Cat();
        // Dog dog = (Dog) cat;  // ❌ ClassCastException
    }
}
```

### Problem 20: Association — Student and Course

**Logic:** Many-to-many association.

```java
// File: StudentCourse.java
import java.util.*;

class Course {
    private String courseCode;
    private String courseName;
    private List<Student> students;
    
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void displayStudents() {
        System.out.println("Course: " + courseName);
        for (Student student : students) {
            System.out.println("  - " + student.getName());
        }
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> courses;
    
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }
    
    public void enroll(Course course) {
        courses.add(course);
        course.addStudent(this);
    }
    
    public String getName() {
        return name;
    }
    
    public void displayCourses() {
        System.out.println("Student: " + name);
        for (Course course : courses) {
            System.out.println("  - " + course.courseName);
        }
    }
}

public class StudentCourse {
    public static void main(String[] args) {
        Course java = new Course("CS101", "Java Programming");
        Course python = new Course("CS102", "Python Programming");
        
        Student alice = new Student("S001", "Alice");
        Student bob = new Student("S002", "Bob");
        
        alice.enroll(java);
        alice.enroll(python);
        bob.enroll(java);
        
        java.displayStudents();
        alice.displayCourses();
    }
}
```

---

## 3. High Level (Advanced)

### Problem 21: Strategy Pattern — Sorting Strategy

**Logic:** Define a family of algorithms, encapsulate each one.

```java
// File: SortingStrategy.java
interface SortStrategy {
    void sort(int[] data);
}

class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting using Bubble Sort");
        // Bubble sort implementation
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}

class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting using Quick Sort");
        quickSort(data, 0, data.length - 1);
    }
    
    private void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pi = partition(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }
    
    private int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (data[j] < pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        int temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;
        return i + 1;
    }
}

class Context {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void executeSort(int[] data) {
        strategy.sort(data);
    }
}

public class SortingStrategy {
    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        
        Context context = new Context();
        
        context.setStrategy(new BubbleSortStrategy());
        context.executeSort(data.clone());
        
        context.setStrategy(new QuickSortStrategy());
        context.executeSort(data.clone());
    }
}
```

### Problem 22: Observer Pattern — News Publisher

**Logic:** One-to-many dependency. When one changes, all dependents are notified.

```java
// File: NewsPublisher.java
import java.util.*;

interface Observer {
    void update(String news);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class NewsAgency implements Subject {
    private List<Observer> observers;
    private String latestNews;
    
    public NewsAgency() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }
    
    public void setNews(String news) {
        this.latestNews = news;
        notifyObservers();
    }
}

class NewsChannel implements Observer {
    private String name;
    private String news;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String news) {
        this.news = news;
        System.out.println(name + " received: " + news);
    }
}

public class NewsPublisher {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        
        NewsChannel channel1 = new NewsChannel("CNN");
        NewsChannel channel2 = new NewsChannel("BBC");
        
        agency.registerObserver(channel1);
        agency.registerObserver(channel2);
        
        agency.setNews("Breaking: Java 20 released!");
    }
}
```

### Problem 23: Decorator Pattern — Coffee Shop

**Logic:** Add behavior dynamically without affecting other objects.

```java
// File: CoffeeShop.java
interface Coffee {
    double cost();
    String description();
}

class SimpleCoffee implements Coffee {
    @Override
    public double cost() {
        return 2.0;
    }
    
    @Override
    public String description() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }
    
    @Override
    public String description() {
        return decoratedCoffee.description();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double cost() {
        return super.cost() + 0.5;
    }
    
    @Override
    public String description() {
        return super.description() + ", milk";
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double cost() {
        return super.cost() + 0.2;
    }
    
    @Override
    public String description() {
        return super.description() + ", sugar";
    }
}

public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.description() + " $" + coffee.cost());
        
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.description() + " $" + coffee.cost());
        
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.description() + " $" + coffee.cost());
    }
}
```

### Problem 24: Command Pattern — Remote Control

**Logic:** Encapsulate a request as an object.

```java
// File: RemoteControl.java
interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() {
        System.out.println("Light is ON");
    }
    
    public void off() {
        System.out.println("Light is OFF");
    }
}

class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.on();
    }
    
    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.off();
    }
    
    @Override
    public void undo() {
        light.on();
    }
}

class RemoteControl {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
    }
    
    public void pressUndo() {
        command.undo();
    }
}

public class RemoteControlDemo {
    public static void main(String[] args) {
        Light light = new Light();
        
        LightOnCommand onCommand = new LightOnCommand(light);
        LightOffCommand offCommand = new LightOffCommand(light);
        
        RemoteControl remote = new RemoteControl();
        
        remote.setCommand(onCommand);
        remote.pressButton();
        remote.pressUndo();
        
        remote.setCommand(offCommand);
        remote.pressButton();
        remote.pressUndo();
    }
}
```

### Problem 25: Adapter Pattern — Media Player

**Logic:** Convert interface of a class into another interface.

```java
// File: MediaPlayer.java
interface MediaPlayer {
    void play(String filename);
}

interface AdvancedMediaPlayer {
    void playMp4(String filename);
    void playVlc(String filename);
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String filename) {
        System.out.println("Playing MP4: " + filename);
    }
    
    @Override
    public void playVlc(String filename) {
        // Do nothing
    }
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String filename) {
        // Do nothing
    }
    
    @Override
    public void playVlc(String filename) {
        System.out.println("Playing VLC: " + filename);
    }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        }
    }
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp4")) {
            advancedPlayer.playMp4(filename);
        } else if (filename.endsWith(".vlc")) {
            advancedPlayer.playVlc(filename);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp3")) {
            System.out.println("Playing MP3: " + filename);
        } else {
            mediaAdapter = new MediaAdapter(filename.substring(filename.lastIndexOf(".") + 1));
            mediaAdapter.play(filename);
        }
    }
}

public class MediaPlayerDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("song.mp3");
        player.play("movie.mp4");
        player.play("video.vlc");
    }
}
```

---

## 4. Highly Severe Level (Expert)

### Problem 26: Dependency Injection — Service Locator

**Logic:** Invert control. Dependencies provided externally.

```java
// File: DependencyInjection.java
interface MessageService {
    void sendMessage(String message, String recipient);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Email sent to " + recipient + ": " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("SMS sent to " + recipient + ": " + message);
    }
}

class MessageProcessor {
    private MessageService messageService;
    
    // Constructor injection
    public MessageProcessor(MessageService messageService) {
        this.messageService = messageService;
    }
    
    public void processMessage(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}

public class DependencyInjection {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        MessageProcessor processor = new MessageProcessor(emailService);
        processor.processMessage("Hello!", "user@example.com");
        
        MessageService smsService = new SMSService();
        processor = new MessageProcessor(smsService);
        processor.processMessage("Hello!", "+1234567890");
    }
}
```

### Problem 27: Proxy Pattern — Image Proxy

**Logic:** Provide surrogate or placeholder for another object.

```java
// File: ImageProxy.java
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ImageProxy {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");
        
        // Image not loaded yet
        System.out.println("---");
        
        // Image loaded and displayed
        image.display();
        
        // Image already loaded, just displayed
        image.display();
    }
}
```

### Problem 28: Composite Pattern — Organization Structure

**Logic:** Treat individual objects and compositions uniformly.

```java
// File: OrganizationStructure.java
interface Employee {
    void showEmployeeDetails();
    double getSalary();
}

class Developer implements Employee {
    private String name;
    private double salary;
    
    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public void showEmployeeDetails() {
        System.out.println("Developer: " + name + ", Salary: $" + salary);
    }
    
    @Override
    public double getSalary() {
        return salary;
    }
}

class Manager implements Employee {
    private String name;
    private double salary;
    private List<Employee> employees;
    
    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    
    @Override
    public void showEmployeeDetails() {
        System.out.println("Manager: " + name + ", Salary: $" + salary);
        System.out.println("  Subordinates:");
        for (Employee employee : employees) {
            System.out.print("    ");
            employee.showEmployeeDetails();
        }
    }
    
    @Override
    public double getSalary() {
        double total = salary;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }
}

public class OrganizationStructure {
    public static void main(String[] args) {
        Manager ceo = new Manager("CEO", 100000);
        Manager manager1 = new Manager("Manager1", 50000);
        Manager manager2 = new Manager("Manager2", 50000);
        
        Developer dev1 = new Developer("Dev1", 30000);
        Developer dev2 = new Developer("Dev2", 30000);
        Developer dev3 = new Developer("Dev3", 30000);
        
        manager1.addEmployee(dev1);
        manager1.addEmployee(dev2);
        manager2.addEmployee(dev3);
        
        ceo.addEmployee(manager1);
        ceo.addEmployee(manager2);
        
        ceo.showEmployeeDetails();
        System.out.println("Total salary: $" + ceo.getSalary());
    }
}
```

### Problem 29: Bridge Pattern — Color and Shape

**Logic:** Decouple abstraction from implementation.

```java
// File: ColorShape.java
interface Color {
    void applyColor();
}

class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying red color");
    }
}

class BlueColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying blue color");
    }
}

abstract class Shape {
    protected Color color;
    
    public Shape(Color color) {
        this.color = color;
    }
    
    abstract void applyColor();
}

class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }
    
    @Override
    void applyColor() {
        System.out.print("Circle filled with ");
        color.applyColor();
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }
    
    @Override
    void applyColor() {
        System.out.print("Square filled with ");
        color.applyColor();
    }
}

public class ColorShape {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueSquare = new Square(new BlueColor());
        
        redCircle.applyColor();
        blueSquare.applyColor();
    }
}
```

### Problem 30: Visitor Pattern — Document Visitor

**Logic:** Separate algorithm from object structure.

```java
// File: DocumentVisitor.java
interface Visitor {
    void visit(TextDocument document);
    void visit(ImageDocument document);
}

interface Document {
    void accept(Visitor visitor);
}

class TextDocument implements Document {
    private String content;
    
    public TextDocument(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class ImageDocument implements Document {
    private String filename;
    private int width;
    private int height;
    
    public ImageDocument(String filename, int width, int height) {
        this.filename = filename;
        this.width = width;
        this.height = height;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public int getSize() {
        return width * height;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class WordCountVisitor implements Visitor {
    private int wordCount = 0;
    
    @Override
    public void visit(TextDocument document) {
        wordCount += document.getContent().split("\\s+").length;
    }
    
    @Override
    public void visit(ImageDocument document) {
        // Images don't have words
    }
    
    public int getWordCount() {
        return wordCount;
    }
}

class RenderVisitor implements Visitor {
    @Override
    public void visit(TextDocument document) {
        System.out.println("Rendering text: " + document.getContent());
    }
    
    @Override
    public void visit(ImageDocument document) {
        System.out.println("Rendering image: " + document.getFilename());
    }
}

public class DocumentVisitor {
    public static void main(String[] args) {
        Document[] documents = {
            new TextDocument("Hello world this is a test"),
            new ImageDocument("photo.jpg", 1920, 1080),
            new TextDocument("Another document with more words")
        };
        
        WordCountVisitor wordCounter = new WordCountVisitor();
        RenderVisitor renderer = new RenderVisitor();
        
        for (Document doc : documents) {
            doc.accept(wordCounter);
            doc.accept(renderer);
        }
        
        System.out.println("Total words: " + wordCounter.getWordCount());
    }
}
```

---

**Happy coding! 🚀**

*Practice these OOP coding problems to master design patterns and object-oriented principles.*
