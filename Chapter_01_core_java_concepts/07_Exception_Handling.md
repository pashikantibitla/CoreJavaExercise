# 07 — Exception Handling

> **Videos:** 70–79  
> **Topics:** Introduction, Default Exception Handling, Checked vs Unchecked, try-catch, finally, throw, throws, Customized Exceptions, Top-10 Exceptions, try-with-resources

---

## Table of Contents

1. [Introduction to Exceptions](#1-introduction-to-exceptions)
2. [Default Exception Handling](#2-default-exception-handling)
3. [Checked vs Unchecked Exceptions](#3-checked-vs-unchecked-exceptions)
4. [try-catch Blocks](#4-try-catch-blocks)
5. [finally Block](#5-finally-block)
6. [throw and throws](#6-throw-and-throws)
7. [Customized Exceptions](#7-customized-exceptions)
8. [Top-10 Exceptions in Java](#8-top-10-exceptions-in-java)
9. [try-with-resources and Multi-catch](#9-try-with-resources-and-multi-catch)
10. [Interview Questions](#10-interview-questions)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Introduction to Exceptions

### What is an Exception?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    EXCEPTION HIERARCHY                                │
│                                                                  │
│                        Throwable                                    │
│                           │                                         │
│              ┌────────────┴────────────┐                              │
│              │                         │                              │
│           Exception                    Error                          │
│              │                         │                              │
│     ┌──────┴──────┐            ┌──────┴──────┐                      │
│     │             │            │             │                      │
│  Checked      Unchecked    OutOfMemory   StackOverflow            │
│  (Compile)   (Runtime)    (Error)        (Error)                   │
│     │             │                                                  │
│  IOException  NullPointerException                                   │
│  SQLException  ArrayIndexOutOfBoundsException                       │
│  ClassNotFoundException  ArithmeticException                       │
│  FileNotFoundException  ClassCastException                         │
│  InterruptedException  IllegalArgumentException                    │
│  NumberFormatException                                              │
│                                                                  │
│  Checked: Must handle (compile-time check)                          │
│  Unchecked: Optional handling (runtime check)                       │
│  Error: Serious problems, cannot be handled                       │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Default Exception Handling

### What Happens When Exception Occurs?

```java
// Default exception handling:
// 1. Exception object is created by JVM
// 2. Search for catch block in current method
// 3. If not found, propagate to caller method
// 4. If not found in main(), program terminates abnormally
// 5. Print stack trace to console

public class DefaultHandling {
    public static void main(String[] args) {
        method1();  // Exception propagates to main
    }
    
    static void method1() {
        method2();  // Exception propagates to method1
    }
    
    static void method2() {
        System.out.println(10 / 0);  // ArithmeticException
    }
}

// Output:
// Exception in thread "main" java.lang.ArithmeticException: / by zero
//     at DefaultHandling.method2(DefaultHandling.java:16)
//     at DefaultHandling.method1(DefaultHandling.java:12)
//     at DefaultHandling.main(DefaultHandling.java:8)

// Stack trace shows: exception name, description, method hierarchy
```

---

## 3. Checked vs Unchecked Exceptions

### Comparison

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CHECKED vs UNCHECKED EXCEPTIONS                    │
│  ┌────────────────────┬──────────────────────────────────────────┐   │
│  │ Feature            │ Checked Exception    │ Unchecked Exception │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Also called        │ Compile-time          │ Runtime            │   │
│  │                    │ exception             │ exception          │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Detection          │ At compile time       │ At runtime         │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Handling           │ Mandatory (try-catch│ Optional           │   │
│  │                    │ or throws)            │                    │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Inheritance        │ extends Exception     │ extends            │   │
│  │                    │ (not RuntimeException)│ RuntimeException   │   │
│  ├────────────────────┼──────────────────────────────────────────┤   │
│  │ Examples           │ IOException,          │ NullPointerException│   │
│  │                    │ SQLException,        │ ArithmeticException│   │
│  │                    │ ClassNotFoundException│ ArrayIndexOutOfBounds│   │
│  │                    │ FileNotFoundException │ ClassCastException │   │
│  │                    │ InterruptedException  │ NumberFormatException│   │
│  │                    │                       │ IllegalArgument     │   │
│  └────────────────────┴──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 4. try-catch Blocks

### Basic Syntax

```java
// try-catch: Handle exception
public class TryCatchDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
        System.out.println("Program continues...");
    }
}

// Output:
// Cannot divide by zero
// Program continues...
```

### Multiple catch Blocks

```java
// Multiple catch: Handle different exceptions differently
public class MultipleCatch {
    public static void main(String[] args) {
        try {
            String s = null;
            System.out.println(s.length());  // NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error");
        } catch (NullPointerException e) {
            System.out.println("Null reference");
        } catch (Exception e) {
            System.out.println("General error");
        }
    }
}

// Order: Child classes first, Parent classes last
// catch (Exception e) { }  // Must be last
// catch (NullPointerException e) { }  // Must be before Exception
```

### Nested try-catch

```java
// Nested try-catch
public class NestedTryCatch {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try");
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Outer catch");
        }
    }
}

// Output:
// Outer try
// Inner catch: / by zero
```

---

## 5. finally Block

### What is finally?

```java
// finally: Always executes (whether exception occurs or not)
// Use: Cleanup code (close resources, release locks)

public class FinallyDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Try block");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Catch block");
        } finally {
            System.out.println("Finally block");
        }
        System.out.println("Program continues");
    }
}

// Output:
// Try block
// Catch block
// Finally block
// Program continues
```

### finally Does NOT Execute When

```java
// 1. System.exit()
try {
    System.out.println("Try");
    System.exit(0);  // JVM terminates
} finally {
    System.out.println("Finally");  // ❌ Does NOT execute
}

// 2. Fatal error (JVM crash)
// 3. Infinite loop in try
// 4. Power failure / OS kill

// But finally executes even if:
// - Exception occurs in catch
// - return statement in try/catch
// - break/continue in try/catch
```

### return in finally

```java
public class FinallyReturn {
    public static void main(String[] args) {
        System.out.println(test());  // 30
    }
    
    static int test() {
        try {
            return 10;
        } catch (Exception e) {
            return 20;
        } finally {
            return 30;  // Overrides all previous returns
        }
    }
}

// finally return overrides try/catch return
// But bad practice — avoid return in finally
```

---

## 6. throw and throws

### throw

```java
// throw: Manually throw an exception
public class ThrowDemo {
    public static void main(String[] args) {
        checkAge(15);
    }
    
    static void checkAge(int age) {
        if (age < 18) {
            throw new ArithmeticException("Not eligible to vote");
        }
        System.out.println("Eligible to vote");
    }
}

// throw new ExceptionType("message");
// Creates exception object and throws it
```

### throws

```java
// throws: Declare exceptions that method might throw
// Caller must handle or declare

public class ThrowsDemo {
    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
    
    // Declare checked exception
    static void readFile() throws IOException, FileNotFoundException {
        // Method that might throw IOException
        throw new IOException("File not found");
    }
}

// Rules:
// 1. Only checked exceptions need to be declared
// 2. Can declare multiple exceptions
// 3. Caller must handle or declare again
// 4. Subclass exceptions can be caught by parent class
```

---

## 7. Customized Exceptions

### Creating Custom Exception

```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Custom unchecked exception
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Usage
public class CustomExceptionDemo {
    public static void main(String[] args) {
        try {
            withdraw(100, 200);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    static void withdraw(int balance, int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Balance: " + balance + ", Requested: " + amount);
        }
        System.out.println("Withdrawal successful");
    }
}

// Best practices:
// 1. Extend Exception for checked, RuntimeException for unchecked
// 2. Provide meaningful message
// 3. Provide constructors with message and cause
// 4. Use standard exception names (end with "Exception")
```

---

## 8. Top-10 Exceptions in Java

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TOP-10 EXCEPTIONS IN JAVA                          │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. NullPointerException                                      │   │
│  │    Accessing null reference: null.toString(), null.length()  │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 2. ArrayIndexOutOfBoundsException                            │   │
│  │    Array index out of range: arr[10] when arr.length = 5    │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 3. StringIndexOutOfBoundsException                           │   │
│  │    String index out of range: s.charAt(10) when s.length=5 │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 4. ArithmeticException                                        │   │
│  │    Mathematical error: 10/0, 10%0                            │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 5. NumberFormatException                                      │   │
│  │    Invalid number format: Integer.parseInt("abc")            │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 6. ClassCastException                                         │   │
│  │    Invalid type casting: (String) new Integer(10)            │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 7. IllegalArgumentException                                   │   │
│  │    Invalid argument: Thread.setPriority(100)                 │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 8. ClassNotFoundException                                     │   │
│  │    Class not found: Class.forName("com.xyz.Foo")            │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 9. IOException                                                │   │
│  │    I/O error: File not found, network error                  │   │
│  ├─────────────────────────────────────────────────────────────┤   │
│  │ 10. InterruptedException                                       │   │
│  │    Thread interrupted: thread.sleep() interrupted            │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 9. try-with-resources and Multi-catch

### try-with-resources (Java 7+)

```java
// try-with-resources: Auto-close resources
// Resource must implement AutoCloseable

// Old way (verbose):
public void oldWay() {
    BufferedReader br = null;
    try {
        br = new BufferedReader(new FileReader("file.txt"));
        String line = br.readLine();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (br != null) br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// New way (concise):
public void newWay() {
    try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
        String line = br.readLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
    // br.close() called automatically
}

// Multiple resources
public void multipleResources() {
    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
         BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
        String line = br.readLine();
        bw.write(line);
    } catch (IOException e) {
        e.printStackTrace();
    }
    // Both br and bw closed automatically in reverse order
}
```

### Multi-catch (Java 7+)

```java
// Multi-catch: Catch multiple exceptions in one block
public class MultiCatch {
    public static void main(String[] args) {
        try {
            // Code that might throw multiple exceptions
        } catch (IOException | SQLException e) {
            // Handle both exceptions
            System.out.println("I/O or SQL error: " + e.getMessage());
        }
    }
}

// Rules:
// 1. Cannot catch parent and child together
// catch (Exception | IOException e) { }  // ❌ ERROR (IOException IS-A Exception)
// 2. Variable is effectively final
// catch (IOException | SQLException e) { e = new IOException(); }  // ❌
// 3. e is of type of the common parent (Exception)
```

---

## 10. Interview Questions

### Q1. What is the difference between `throw` and `throws`?

```java
// throw: Manually create and throw exception
throw new RuntimeException("Error");

// throws: Declare exception in method signature
void method() throws IOException { }

// throw: inside method body
// throws: in method declaration
// throw: creates exception object
// throws: delegates responsibility to caller
```

### Q2. Can we write multiple catch blocks?

```java
// ✅ YES
// Order: Child first, Parent last

try {
    // code
} catch (NullPointerException e) {    // Child first
    System.out.println("Null");
} catch (RuntimeException e) {         // Parent later
    System.out.println("Runtime");
} catch (Exception e) {              // Parent last
    System.out.println("Exception");
}

// catch (Exception e) { }  // ❌ If first, other catches unreachable
// catch (NullPointerException e) { }  // ❌ Unreachable
```

### Q3. Can `finally` block exist without `catch`?

```java
// ✅ YES
// try-finally: No catch needed

try {
    System.out.println("Try");
} finally {
    System.out.println("Finally");
}

// Also valid: try-catch-finally, try-catch, try-catch-catch-finally
// finally always executes (except System.exit())
```

### Q4. What is the difference between `final`, `finally`, and `finalize`?

```java
// final: keyword
final int x = 10;     // Variable cannot change
final void method() { }  // Method cannot override
final class A { }     // Class cannot inherit

// finally: block
// Always executes after try-catch

// finalize(): method
// Called by GC before object destruction
protected void finalize() {
    // cleanup
}
```

### Q5. Can we catch `Error`?

```java
// ✅ YES (but not recommended)
try {
    // Code that causes OutOfMemoryError
} catch (OutOfMemoryError e) {
    System.out.println("Out of memory");
}

// Error indicates serious problem
// Should not be caught — let JVM handle
// Catch Exception, not Error
```

### Q6. What is the difference between checked and unchecked exceptions?

```java
// Checked: Must handle (compile-time check)
// Unchecked: Optional (runtime check)

// Checked: extends Exception (not RuntimeException)
// Unchecked: extends RuntimeException

// Examples:
// Checked: IOException, SQLException, ClassNotFoundException
// Unchecked: NullPointerException, ArithmeticException, NumberFormatException

// Checked: Recoverable, expected conditions
// Unchecked: Programming errors, bugs
```

### Q7. What is try-with-resources?

```java
// Auto-closes resources (implements AutoCloseable)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
// br.close() called automatically

// Multiple resources
// Closed in reverse order of creation
```

### Q8. Can we have `return` in `finally`?

```java
// ✅ YES (but bad practice)
static int test() {
    try {
        return 10;
    } finally {
        return 20;  // Overrides try return
    }
}
// Returns 20, not 10

// Avoid return in finally
// finally should only have cleanup code
```

### Q9. What is exception propagation?

```java
// Exception propagates up the call stack
// If not caught, JVM terminates program

class A {
    void method() { throw new RuntimeException(); }
}
class B {
    void method() { new A().method(); }  // Propagates to B
}
class C {
    void method() { new B().method(); }  // Propagates to C
}

// If not caught in C, propagates to main, then JVM
```

### Q10. How to create custom exception?

```java
// Checked custom exception
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

// Unchecked custom exception
class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String message) {
        super(message);
    }
}

// Usage
throw new MyException("Custom error");
throw new MyRuntimeException("Custom runtime error");
```

---

## 11. Key Takeaways

1. **Exception**: Unwanted event that disrupts normal program flow.
2. **Throwable**: Parent of Exception and Error.
3. **Exception**: Recoverable, should be handled.
4. **Error**: Serious, cannot be handled (OutOfMemoryError).
5. **Checked**: Must handle (compile-time check).
6. **Unchecked**: Optional (RuntimeException subclasses).
7. **try**: Code that might throw exception.
8. **catch**: Handle exception.
9. **finally**: Always executes (cleanup code).
10. **throw**: Manually throw exception.
11. **throws**: Declare exception in method signature.
12. **Stack trace**: Method hierarchy where exception occurred.
13. **Multi-catch**: Catch multiple exceptions (Java 7+).
14. **try-with-resources**: Auto-close resources (Java 7+).
15. **Custom exception**: Extend Exception (checked) or RuntimeException (unchecked).
16. **finally return**: Overrides try/catch return (bad practice).
17. **Exception propagation**: Moves up call stack until caught.
18. **Instanceof with exception**: Check exception type before casting.
19. **getMessage()**: Returns exception description.
20. **printStackTrace()**: Prints complete stack trace.

---

**Happy coding! 🚀**

*Exception handling makes your program robust and user-friendly — never ignore exceptions.*
