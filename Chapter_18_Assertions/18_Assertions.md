# Chapter 18 — Assertions

> **Topics:** Introduction, Simple Assert, Augmented Assert, Runtime Flags, Proper Use, Assertions vs Exceptions, Production Code, Interview FAQs

---

## Table of Contents

1. [Introduction to Assertions](#1-introduction-to-assertions)
2. [Simple Assert vs Augmented Assert](#2-simple-assert-vs-augmented-assert)
3. [Runtime Flags](#3-runtime-flags)
4. [Proper and Improper Use of Assertions](#4-proper-and-improper-use-of-assertions)
5. [Assertions vs Exceptions](#5-assertions-vs-exceptions)
6. [Assertions in Production Code](#6-assertions-in-production-code)
7. [Interview Questions](#7-interview-questions)
8. [Quick Reference](#8-quick-reference)
9. [Key Takeaways](#9-key-takeaways)

---

## 1. Introduction to Assertions

### What are Assertions?

An assertion is a statement in Java that allows you to test your assumptions about the program. It is used to verify that something is true at a certain point in the code.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ASSERTION IN JAVA                                  │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Definition:                                                 │   │
│  │ An assertion is a boolean expression that the programmer  │   │
│  │ believes to be true at a specific point in the program.  │   │
│  │                                                             │   │
│  │ Purpose:                                                    │   │
│  │ 1. Detect bugs during development and testing               │   │
│  │ 2. Document assumptions in the code                         │   │
│  │ 3. Catch logic errors early                                  │   │
│  │                                                             │   │
│  │ Syntax:                                                     │   │
│  │ assert condition;                                          │   │
│  │ assert condition : detailMessage;                            │   │
│  │                                                             │   │
│  │ Since: Java 1.4 (introduced)                                 │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Why Use Assertions?

```java
// Without assertion:
public void calculateDiscount(int age) {
    // Programmer assumes age is always >= 0
    double discount = age * 0.5;
    // If age is negative, discount becomes negative
    // Bug goes unnoticed
}

// With assertion:
public void calculateDiscount(int age) {
    assert age >= 0 : "Age cannot be negative: " + age;
    double discount = age * 0.5;
    // If age is negative, AssertionError is thrown immediately
    // Bug is caught during development
}
```

### Important Characteristics

```java
// 1. Assertions are disabled by default
//    They only run if explicitly enabled with -ea flag

// 2. Assertions are for debugging, not error handling
//    They should NOT be used to handle runtime errors

// 3. Assertions throw AssertionError (extends Error, not Exception)
//    AssertionError is unchecked and should NOT be caught

// 4. AssertionError should never be caught
    try {
        assert false;
    } catch (AssertionError e) {  // ❌ BAD PRACTICE
        System.out.println("Caught assertion error");
    }
```

---

## 2. Simple Assert vs Augmented Assert

### Simple Assert (Version 1)

```java
// Syntax: assert condition;

// Example:
public class SimpleAssertDemo {
    public static void main(String[] args) {
        int x = 10;
        assert x > 0;  // ✅ Simple assert
        System.out.println("Assertion passed: x is positive");
    }
}

// If assertion fails: throws AssertionError with no message
```

### Augmented Assert (Version 2)

```java
// Syntax: assert condition : detailMessage;

// Example:
public class AugmentedAssertDemo {
    public static void main(String[] args) {
        int x = -5;
        assert x > 0 : "x must be positive, but was: " + x;  // ✅ Augmented assert
        System.out.println("Assertion passed");
    }
}

// If assertion fails: throws AssertionError with message:
// "x must be positive, but was: -5"
```

### Comparison

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SIMPLE ASSERT vs AUGMENTED ASSERT                   │
│  ┌────────────────┬─────────────────────┬────────────────────────────┐  │
│  │ Feature        │ Simple Assert       │ Augmented Assert          │  │
│  ├────────────────┼─────────────────────┼────────────────────────────┤  │
│  │ Syntax         │ assert condition;   │ assert condition : msg;    │  │
│  ├────────────────┼─────────────────────┼────────────────────────────┤  │
│  │ Error message  │ No detail message   │ Custom detail message    │  │
│  ├────────────────┼─────────────────────┼────────────────────────────┤  │
│  │ Error thrown   │ AssertionError      │ AssertionError with msg  │  │
│  ├────────────────┼─────────────────────┼────────────────────────────┤  │
│  │ Use case       │ Quick check         │ Debug with context      │  │
│  └────────────────┴─────────────────────┴────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples of Both Forms

```java
public class AssertFormsDemo {
    public static void main(String[] args) {
        // 1. Simple assert
        int age = 20;
        assert age >= 18;
        System.out.println("Adult verified");
        
        // 2. Augmented assert with string message
        String name = "John";
        assert name != null : "Name cannot be null";
        System.out.println("Name verified: " + name);
        
        // 3. Augmented assert with expression
        int[] arr = {1, 2, 3};
        int index = 2;
        assert index >= 0 && index < arr.length 
            : "Index out of bounds: " + index + " (length: " + arr.length + ")";
        System.out.println("Element: " + arr[index]);
        
        // 4. Augmented assert with method call
        int value = 100;
        assert value > 0 : generateErrorMessage(value);
        System.out.println("Value verified: " + value);
    }
    
    private static String generateErrorMessage(int value) {
        return "Invalid value: " + value + " at " + new java.util.Date();
    }
}
```

---

## 3. Runtime Flags

### Assertion Flags

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ASSERTION RUNTIME FLAGS                             │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Flag           │ Description                                    │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ -ea            │ Enable assertions (default package)           │  │
│  │ -enableassertions │ Same as -ea                                 │  │
│  │ -da            │ Disable assertions (default package)           │  │
│  │ -disableassertions │ Same as -da                                 │  │
│  │ -esa           │ Enable assertions in system classes            │  │
│  │ -enablesystemassertions │ Same as -esa                         │  │
│  │ -dsa           │ Disable assertions in system classes           │  │
│  │ -disablesystemassertions │ Same as -dsa                        │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Usage Examples

```bash
# 1. Enable assertions for all classes
java -ea MyProgram

# 2. Enable assertions for specific class
java -ea:com.example.MyClass MyProgram

# 3. Enable assertions for specific package
java -ea:com.example... MyProgram

# 4. Enable assertions for default package
java -ea:... MyProgram

# 5. Disable assertions for all classes
java -da MyProgram

# 6. Disable assertions for specific class
java -da:com.example.MyClass MyProgram

# 7. Enable assertions for package but disable for specific class
java -ea:com.example... -da:com.example.Test MyProgram

# 8. Enable assertions in system classes
java -esa MyProgram

# 9. Enable assertions in both user and system classes
java -ea -esa MyProgram

# 10. Enable assertions in default package
java -ea:... MyProgram
```

### Programmatic Check

```java
public class CheckAssertionsEnabled {
    public static void main(String[] args) {
        boolean assertsEnabled = false;
        
        // Trick to check if assertions are enabled
        assert assertsEnabled = true;
        
        if (assertsEnabled) {
            System.out.println("Assertions are ENABLED");
        } else {
            System.out.println("Assertions are DISABLED");
        }
    }
}

// Output without -ea: Assertions are DISABLED
// Output with -ea: Assertions are ENABLED
```

---

## 4. Proper and Improper Use of Assertions

### Proper Use Cases

```java
// ✅ 1. Internal Invariants
//    Check conditions that should always be true
public void process(int x) {
    assert x >= 0 : "x should be non-negative";
    // ... process x
}

// ✅ 2. Control Flow Invariants
//    Check that certain code is unreachable
public void processDay(int day) {
    switch (day) {
        case 1: processMonday(); break;
        case 2: processTuesday(); break;
        case 3: processWednesday(); break;
        case 4: processThursday(); break;
        case 5: processFriday(); break;
        case 6: processSaturday(); break;
        case 7: processSunday(); break;
        default:
            assert false : "Invalid day: " + day;  // Should never reach here
    }
}

// ✅ 3. Post-conditions
//    Check that method produces expected results
public int divide(int a, int b) {
    assert b != 0 : "Divisor cannot be zero";
    int result = a / b;
    assert result * b == a : "Math error in division";
    return result;
}

// ✅ 4. Class Invariants
//    Check object state is consistent
public class BankAccount {
    private double balance;
    
    public void withdraw(double amount) {
        assert amount > 0 : "Withdrawal amount must be positive";
        balance -= amount;
        assert balance >= 0 : "Balance cannot be negative after withdrawal";
    }
}

// ✅ 5. Documentation
//    Assert to document assumptions
public void sort(int[] arr) {
    assert arr != null : "Array must not be null";
    assert arr.length > 1 : "Array must have at least 2 elements to sort";
    // ... sorting logic
}
```

### Improper Use Cases

```java
// ❌ 1. Do NOT use assertions for argument checking in public methods
public void setAge(int age) {
    assert age > 0 : "Age must be positive";  // ❌ BAD
    // This check is skipped if assertions are disabled!
    this.age = age;
}

// ✅ Instead, use exception handling:
public void setAge(int age) {
    if (age <= 0) {
        throw new IllegalArgumentException("Age must be positive");
    }
    this.age = age;
}

// ❌ 2. Do NOT use assertions for error handling
public void readFile(String path) {
    assert path != null : "Path is null";  // ❌ BAD
    // FileNotFoundException might still occur
}

// ✅ Instead, use try-catch:
try {
    FileReader reader = new FileReader(path);
} catch (FileNotFoundException e) {
    // Handle error properly
}

// ❌ 3. Do NOT use assertions for checking external conditions
public void connectToDatabase() {
    assert database.isAvailable() : "Database not available";  // ❌ BAD
    // External conditions are not under programmer control
}

// ✅ Instead, use proper error handling:
if (!database.isAvailable()) {
    throw new DatabaseUnavailableException("Database is down");
}

// ❌ 4. Do NOT use assertions that have side effects
public void process(int x) {
    assert (x = 5) > 0;  // ❌ BAD: assignment has side effect!
    // This modifies x even when assertions are disabled!
}

// ✅ Use pure boolean expressions:
assert x > 0 : "x must be positive";

// ❌ 5. Do NOT use assertions in production for critical checks
public void transferMoney(double amount) {
    assert amount > 0;  // ❌ BAD: skipped if assertions disabled
    // Critical validation should always run
}

// ✅ Always validate critical business logic:
if (amount <= 0) {
    throw new IllegalArgumentException("Transfer amount must be positive");
}
```

### Summary: Proper vs Improper Use

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ASSERTION USE CASES SUMMARY                         │
│  ┌────────────────────────────┬────────────────────────────────┐  │
│  │ ✅ Proper Use              │ ❌ Improper Use                 │  │
│  ├────────────────────────────┼────────────────────────────────┤  │
│  │ Internal invariants        │ Public method argument checking │  │
│  │ Control flow invariants    │ External condition checking    │  │
│  │ Post-conditions             │ Error handling / user input    │  │
│  │ Class invariants            │ Production-critical validation │  │
│  │ Documentation              │ Side-effect expressions        │  │
│  │ Private method preconditions│ Command-line argument checking │  │
│  └────────────────────────────┴────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. Assertions vs Exceptions

### Key Differences

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ASSERTIONS vs EXCEPTIONS                            │
│  ┌────────────────┬─────────────────────┬────────────────────────┐  │
│  │ Feature        │ Assertions           │ Exceptions           │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Purpose        │ Debug assumptions   │ Handle runtime errors│  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Default state  │ Disabled            │ Always enabled        │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Throwable type│ Error (AssertionError)│ Exception/Error      │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Recovery       │ No recovery expected │ Can recover           │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ User input     │ Should NOT be used  │ Should be used        │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Production     │ Optional, may be off│ Must always be on   │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Catching       │ Should NOT be caught│ Can be caught         │  │
│  ├────────────────┼─────────────────────┼────────────────────────┤  │
│  │ Performance    │ Zero overhead when  │ Always has overhead   │  │
│  │                │ disabled            │                       │  │
│  └────────────────┴─────────────────────┴────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### When to Use What

```java
// Use EXCEPTIONS for:
// 1. Invalid user input
public void registerUser(String email) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Invalid email format");
    }
}

// 2. External resource failures
try {
    FileInputStream fis = new FileInputStream("file.txt");
} catch (FileNotFoundException e) {
    // Handle missing file
}

// 3. Business logic violations
public void withdraw(double amount) {
    if (amount > balance) {
        throw new InsufficientFundsException("Balance too low");
    }
}

// 4. Public API argument validation
public void process(int[] data) {
    if (data == null) {
        throw new NullPointerException("Data cannot be null");
    }
}

// Use ASSERTIONS for:
// 1. Internal consistency checks
public void sort(int[] arr) {
    assert arr != null;  // Internal: caller should never pass null
    // ... sort
    assert isSorted(arr);  // Post-condition
}

// 2. Unreachable code assertions
public void processColor(Color c) {
    switch (c) {
        case RED:    // handle red
        case GREEN:  // handle green
        case BLUE:   // handle blue
        default:
            assert false : "Unknown color: " + c;
    }
}

// 3. Private method preconditions
private void helper(int x) {
    assert x > 0 : "x must be positive in helper";
    // ... logic
}
```

---

## 6. Assertions in Production Code

### Best Practices for Production

```
┌─────────────────────────────────────────────────────────────────────┐
│                    ASSERTIONS IN PRODUCTION                              │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Rule 1: NEVER rely on assertions for program correctness  │   │
│  │         Assertions may be disabled in production.         │   │
│  │                                                             │   │
│  │ Rule 2: Do NOT use assertions for security checks         │   │
│  │         Security must work even when assertions are off.  │   │
│  │                                                             │   │
│  │ Rule 3: Do NOT use assertions for business logic          │   │
│  │         Business rules must always be enforced.           │   │
│  │                                                             │   │
│  │ Rule 4: Assertions are OK for internal debugging          │   │
│  │         Helpful during development, harmless when off.    │   │
│  │                                                             │   │
│  │ Rule 5: Keep assertions simple and fast                   │   │
│  │         Avoid complex expressions in assertions.          │   │
│  │                                                             │   │
│  │ Rule 6: Document assertion usage in code reviews          │   │
│  │         Ensure team knows assertions are present.         │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Production Code Example

```java
public class BankService {
    private double balance;
    
    // ✅ Production validation: ALWAYS enforced
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Balance too low");
        }
        
        double oldBalance = balance;
        balance -= amount;
        
        // ✅ Debug assertion: verifies internal consistency
        // Safe to disable in production, but helps during development
        assert balance >= 0 : "Balance became negative after withdrawal";
        assert balance == oldBalance - amount : "Balance calculation error";
    }
    
    // ✅ Private method: assertion acceptable for internal logic
    private double calculateInterest(double rate) {
        assert rate > 0 && rate < 1 : "Rate must be between 0 and 1";
        return balance * rate;
    }
}
```

### Side Effects Warning

```java
public class SideEffectWarning {
    private int count = 0;
    
    // ❌ BAD: assertion has side effect
    public void process(int x) {
        assert (count++) > 0 : "Count incremented";  // DON'T DO THIS
        // count++ is skipped when assertions are disabled!
        // This causes inconsistent behavior.
    }
    
    // ✅ GOOD: assertion has no side effect
    public void processGood(int x) {
        assert count > 0 : "Count should be positive";
        count++;  // Count is always incremented
    }
}
```

---

## 7. Interview Questions

### Q1. What is the syntax of assert in Java?

```java
// There are two forms:

// 1. Simple assert
assert condition;

// 2. Augmented assert
assert condition : detailMessage;

// Example:
public class AssertSyntax {
    public static void main(String[] args) {
        int x = 10;
        assert x > 0;  // Simple
        assert x > 0 : "x is not positive";  // Augmented
    }
}

// detailMessage can be any expression that returns a value
// (not void). It is converted to String.
```

### Q2. What happens when an assertion fails?

```java
// When an assertion fails, Java throws an AssertionError.

// 1. Simple assert fails: AssertionError with no message
assert false;  // Throws: java.lang.AssertionError

// 2. Augmented assert fails: AssertionError with message
assert false : "Something went wrong";  // Throws: java.lang.AssertionError: Something went wrong

// AssertionError extends Error, which extends Throwable.
// It is an unchecked error and should NOT be caught.
```

### Q3. Are assertions enabled by default?

```java
// NO — assertions are DISABLED by default.

// To enable assertions:
// java -ea MyProgram
// java -enableassertions MyProgram

// To enable for specific package:
// java -ea:com.example... MyProgram

// To disable assertions:
// java -da MyProgram

// Assertions were introduced in Java 1.4.
// Before Java 1.4, assert was not a keyword.
```

### Q4. Can we catch AssertionError?

```java
// Yes, technically we can catch AssertionError because it is a Throwable.

public class CatchAssertionError {
    public static void main(String[] args) {
        try {
            assert false : "Test assertion";
        } catch (AssertionError e) {
            System.out.println("Caught: " + e.getMessage());
        }
        System.out.println("Program continues...");
    }
}

// Output with -ea:
// Caught: Test assertion
// Program continues...

// ❌ BUT this is BAD PRACTICE.
// AssertionError should never be caught.
// Assertions are meant to detect bugs, not to be handled.
// Catching AssertionError defeats the purpose of assertions.
```

### Q5. What is the difference between assert and if-else?

```java
// assert is NOT a replacement for if-else.

// if-else: Runtime logic that MUST always execute
if (userInput > 0) {
    process(userInput);
} else {
    throw new IllegalArgumentException("Invalid input");
}

// assert: Debug check that MAY be skipped
assert userInput > 0 : "Invalid input";  // Only if -ea enabled

// Key differences:
// 1. if-else always runs; assert runs only if enabled
// 2. if-else is for normal program flow; assert is for debugging
// 3. if-else handles user input; assert checks internal logic
// 4. if-else can recover; assert should crash the program
```

### Q6. Can we use assert as an identifier?

```java
// In Java 1.3 and earlier: assert was NOT a keyword.
// In Java 1.4 and later: assert is a keyword.

// Code compiled with -source 1.3:
// int assert = 10;  // ✅ Valid in Java 1.3

// Code compiled with -source 1.4 or later:
// int assert = 10;  // ❌ ERROR: assert is a keyword

// Modern Java (1.5+): assert is always a keyword.
```

### Q7. Where should assertions be used?

```java
// ✅ Use assertions for:
// 1. Internal invariants
assert size >= 0 : "Size cannot be negative";

// 2. Control-flow invariants
assert false : "Should never reach here";

// 3. Post-conditions
assert result != null : "Result should not be null";

// 4. Class invariants
assert balance >= 0 : "Balance cannot be negative";

// 5. Private method preconditions
private void helper(int x) {
    assert x > 0 : "x must be positive";
}

// ❌ Do NOT use assertions for:
// 1. Public method argument checking
// 2. Error handling
// 3. External condition checking
// 4. User input validation
// 5. Security checks
// 6. Business logic enforcement
```

### Q8. What is the difference between -ea and -esa?

```bash
# -ea or -enableassertions
#    Enables assertions in user classes (your code)

# -esa or -enablesystemassertions
#    Enables assertions in system classes (Java API classes)

# Example:
java -ea MyProgram       # Assertions enabled in user classes
java -esa MyProgram      # Assertions enabled in system classes
java -ea -esa MyProgram  # Assertions enabled in both

// Note: System class assertions are rarely used.
// Most developers only use -ea.
```

### Q9. What is a side effect in assertions? Why should it be avoided?

```java
// Side effect: assertion expression changes program state.

public class SideEffectDemo {
    static int counter = 0;
    
    public static void main(String[] args) {
        // ❌ BAD: side effect in assertion
        assert (counter++ > 0) : "Counter incremented";
        // counter++ is SKIPPED when assertions are disabled!
        // Program behaves differently with/without -ea.
        
        // ✅ GOOD: no side effect
        assert counter > 0 : "Counter should be positive";
        counter++;  // Always executes
    }
}

// Assertions should be PURE boolean expressions.
// They should NOT modify any state.
```

### Q10. Can assertions be used with exception handling?

```java
// Assertions and exceptions serve different purposes.
// They can coexist in the same program.

public class AssertWithException {
    public static void main(String[] args) {
        try {
            processData(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Handled: " + e.getMessage());
        }
    }
    
    public static void processData(String data) {
        // Exception: always enforced, handles user input
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        // Assertion: debug check, may be disabled
        assert data.length() > 0 : "Data should not be empty after null check";
        
        System.out.println("Processing: " + data);
    }
}

// Best practice:
// 1. Use exceptions for error handling and user input
// 2. Use assertions for internal debugging and invariants
```

---

## 8. Quick Reference

### Assertion Syntax

```java
assert condition;                    // Simple
assert condition : detailMessage;     // Augmented
```

### Runtime Flags

```bash
-ea                     # Enable assertions (user classes)
-da                     # Disable assertions (user classes)
-esa                    # Enable assertions (system classes)
-dsa                    # Disable assertions (system classes)
-ea:package...          # Enable for package
-da:class               # Disable for class
```

### AssertionError Hierarchy

```
java.lang.Object
    └── java.lang.Throwable
        └── java.lang.Error
            └── java.lang.AssertionError
```

---

## 9. Key Takeaways

1. **Assertions are for debugging** — they verify assumptions during development.
2. **Two forms** — `assert condition;` and `assert condition : message;`.
3. **Disabled by default** — must enable with `-ea` flag.
4. **Never use assertions** for public API validation, error handling, or security checks.
5. **Use assertions** for internal invariants, control-flow checks, and post-conditions.
6. **AssertionError** extends Error — should never be caught.
7. **No side effects** — assertion expressions must not modify state.
8. **Zero overhead** — assertions have no performance impact when disabled.
9. **Assertions are not exceptions** — they complement but do not replace exceptions.
10. **Production code** — should never rely on assertions for correctness.

---

**Happy coding!**

*Assertions are a powerful tool for detecting bugs early — use them wisely!*
