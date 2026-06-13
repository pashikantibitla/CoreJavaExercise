# 03 — Flow Control

> **Videos:** 24–29  
> **Topics:** if-else, switch, while, do-while, for, for-each, break, continue

---

## Table of Contents

1. [Introduction to Flow Control](#1-introduction-to-flow-control)
2. [Selection Statements](#2-selection-statements)
3. [Iterative Statements](#3-iterative-statements)
4. [Transfer Statements](#4-transfer-statements)
5. [Interview Questions](#5-interview-questions)
6. [Quick Reference](#6-quick-reference)
7. [Key Takeaways](#7-key-takeaways)

---

## 1. Introduction to Flow Control

### Types of Flow Control

```
┌─────────────────────────────────────────────────────────────────────┐
│                    FLOW CONTROL IN JAVA                               │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │ 1. Sequential (Default)                                     │  │
│  │    Statements execute in order: top to bottom              │  │
│  │                                                             │  │
│  │ 2. Conditional / Selection                                │  │
│  │    if, if-else, if-else-if, switch                         │  │
│  │                                                             │  │
│  │ 3. Iterative / Looping                                    │  │
│  │    while, do-while, for, for-each                          │  │
│  │                                                             │  │
│  │ 4. Transfer / Jump                                        │  │
│  │    break, continue, return, System.exit()                  │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. Selection Statements

### if Statement

```java
// Simple if
if (condition) {
    // statement(s)
}

// if-else
if (condition) {
    // true block
} else {
    // false block
}

// if-else-if ladder
if (condition1) {
    // block 1
} else if (condition2) {
    // block 2
} else if (condition3) {
    // block 3
} else {
    // default block
}

// Nested if
if (condition1) {
    if (condition2) {
        // block
    }
}
```

### if Statement Examples

```java
// Example 1: Basic if
int age = 20;
if (age >= 18) {
    System.out.println("Adult");
}

// Example 2: if-else
int marks = 75;
if (marks >= 40) {
    System.out.println("Pass");
} else {
    System.out.println("Fail");
}

// Example 3: if-else-if ladder
int score = 85;
if (score >= 90) {
    System.out.println("Grade A");
} else if (score >= 80) {
    System.out.println("Grade B");
} else if (score >= 70) {
    System.out.println("Grade C");
} else if (score >= 60) {
    System.out.println("Grade D");
} else {
    System.out.println("Grade F");
}

// Example 4: Curly braces are optional for single statement
if (true)
    System.out.println("True");  // ✅ Valid

// But dangerous — dangling else problem
if (true)
    if (false)
        System.out.println("A");
    else
        System.out.println("B");  // Prints B (else belongs to nearest if)

// Example 5: Condition must be boolean
// if (10) { }  // ❌ ERROR: int cannot be converted to boolean
// if (0) { }   // ❌ ERROR: int cannot be converted to boolean
if (true) { }  // ✅
if (10 > 5) { } // ✅
```

### switch Statement

```java
// Syntax
switch (expression) {
    case value1:
        // statements
        break;
    case value2:
        // statements
        break;
    default:
        // statements
}
```

### switch Statement Rules

```java
// 1. Allowed types: byte, short, int, char, String, enum
// NOT allowed: long, float, double, boolean

int day = 3;
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    case 4:
        System.out.println("Thursday");
        break;
    case 5:
        System.out.println("Friday");
        break;
    case 6:
        System.out.println("Saturday");
        break;
    case 7:
        System.out.println("Sunday");
        break;
    default:
        System.out.println("Invalid day");
}

// 2. String switch (Java 7+)
String color = "red";
switch (color) {
    case "red":
        System.out.println("Stop");
        break;
    case "yellow":
        System.out.println("Caution");
        break;
    case "green":
        System.out.println("Go");
        break;
    default:
        System.out.println("Invalid");
}

// 3. Enum switch
enum Day { MONDAY, TUESDAY, WEDNESDAY }
Day today = Day.MONDAY;
switch (today) {
    case MONDAY:
        System.out.println("Start of week");
        break;
    case TUESDAY:
        System.out.println("Second day");
        break;
    default:
        System.out.println("Other day");
}

// 4. case values must be compile-time constants
int x = 10;
final int y = 20;
switch (x) {
    case 10:        // ✅ Literal
        break;
    case y:         // ✅ Final constant
        break;
    // case z:      // ❌ ERROR: z must be final
    //     break;
}

// 5. Duplicate case values not allowed
switch (x) {
    case 10:
        break;
    // case 10:    // ❌ ERROR: duplicate case label
    //     break;
}

// 6. Fall-through behavior (without break)
int x = 2;
switch (x) {
    case 1:
        System.out.println("One");
    case 2:
        System.out.println("Two");    // Prints
    case 3:
        System.out.println("Three");  // Also prints
    default:
        System.out.println("Default"); // Also prints
}
// Output: Two
//         Three
//         Default

// 7. Multiple cases without break (intentional fall-through)
int month = 3;
switch (month) {
    case 3:
    case 4:
    case 5:
        System.out.println("Spring");
        break;
    case 6:
    case 7:
    case 8:
        System.out.println("Summer");
        break;
    default:
        System.out.println("Other");
}

// 8. Default case can be anywhere
switch (x) {
    default:
        System.out.println("Default");
        break;
    case 10:
        System.out.println("Ten");
        break;
}

// 9. Switch expression (Java 12+)
int day = 3;
String dayName = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    default -> "Unknown";
};
// No break needed, uses arrow syntax
```

### if vs switch

```
┌─────────────────────────────────────────────────────────────────────┐
│                    if vs switch COMPARISON                            │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ if                        │ switch         │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Condition type     │ Any boolean expression    │ Equality only │  │
│  │ Number of values   │ Any range                 │ Limited cases │  │
│  │ Types supported    │ All types                 │ byte, short,  │  │
│  │                    │                           │ int, char,     │  │
│  │                    │                           │ String, enum   │  │
│  │ Performance        │ Multiple comparisons      │ Jump table    │  │
│  │ Readability        │ Complex for many cases    │ Better for    │  │
│  │                    │                           │ many cases     │  │
│  │ Nested             │ Easy to nest              │ Can be nested │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 3. Iterative Statements

### while Loop

```java
// Syntax
while (condition) {
    // statements
}

// Example 1: Print 1 to 5
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}

// Example 2: Infinite loop
while (true) {
    // Infinite loop
}

// Example 3: Condition checked first
int i = 10;
while (i < 5) {
    System.out.println(i);  // Never executes
}

// Example 4: Curly braces optional for single statement
while (true)
    System.out.println("Infinite");  // ✅ Valid
```

### do-while Loop

```java
// Syntax
do {
    // statements
} while (condition);

// Example 1: Print 1 to 5
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);

// Example 2: Executes at least once
int i = 10;
do {
    System.out.println(i);  // Prints 10
    i++;
} while (i < 5);

// Example 3: Semicolon is mandatory
// do {} while (true)  // ❌ ERROR: ; expected
```

### while vs do-while

```
┌─────────────────────────────────────────────────────────────────────┐
│                    while vs do-while COMPARISON                       │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Feature            │ while                      │ do-while     │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Condition check    │ Before execution           │ After execution│  │
│  │ Minimum executions │ 0 times                    │ 1 time       │  │
│  │ Syntax             │ while (cond) { }           │ do { } while (cond);│  │
│  │ Use case           │ When unsure if execution   │ When at least │  │
│  │                    │ is needed                  │ 1 execution   │  │
│  │ Semicolon          │ No                         │ Mandatory after while│  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### for Loop

```java
// Syntax
for (initialization; condition; update) {
    // statements
}

// Example 1: Print 1 to 5
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}

// Example 2: Multiple initialization
for (int i = 0, j = 10; i < j; i++, j--) {
    System.out.println(i + " " + j);
}

// Example 3: Different update types
for (int i = 0; i < 10; i += 2) {  // Increment by 2
    System.out.println(i);
}

// Example 4: No initialization
int i = 0;
for (; i < 5; i++) {
    System.out.println(i);
}

// Example 5: No condition (infinite loop)
for (int i = 0; ; i++) {
    System.out.println(i);
    if (i >= 10) break;
}

// Example 6: No update
for (int i = 0; i < 5; ) {
    System.out.println(i);
    i++;  // Update inside loop
}

// Example 7: All parts optional
for (;;) {
    // Infinite loop
    break;
}

// Example 8: Nested loops
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.println(i + " " + j);
    }
}

// Example 9: Loop variable scope
for (int i = 0; i < 5; i++) {
    // i is accessible here
}
// System.out.println(i);  // ❌ ERROR: i is out of scope
```

### for-each Loop (Enhanced for)

```java
// Syntax
for (dataType variable : collection/array) {
    // statements
}

// Example 1: Array
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    System.out.println(num);
}

// Example 2: List
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}

// Example 3: String characters
for (char c : "Hello".toCharArray()) {
    System.out.println(c);
}

// Example 4: Modification limitation
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    num = num * 2;  // ❌ Does NOT modify array (num is a copy)
}

// Example 5: Cannot modify collection while iterating
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String s : list) {
    // list.remove(s);  // ❌ ConcurrentModificationException
}

// Example 6: Iterator for safe removal
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("B")) {
        it.remove();  // ✅ Safe removal
    }
}
```

### Loop Comparison

```
┌─────────────────────────────────────────────────────────────────────┐
│                    LOOP COMPARISON TABLE                              │
│  ┌────────────────┬──────────┬──────────┬──────────┬──────────────┐  │
│  │ Feature        │ for      │ while    │ do-while │ for-each     │  │
│  ├────────────────┼──────────┼──────────┼──────────┼──────────────┤  │
│  │ Condition check│ Before   │ Before   │ After    │ Implicit     │  │
│  │ Minimum runs   │ 0        │ 0        │ 1        │ 0 (if empty) │  │
│  │ Use case       │ Known    │ Unknown  │ At least │ Collection/  │  │
│  │                │ iterations│ iterations│ 1 run   │ Array        │  │
│  │ Index access   │ Yes      │ Yes      │ Yes      │ No           │  │
│  │ Modify element │ Yes      │ Yes      │ Yes      │ No (copy)    │  │
│  └────────────────┴──────────┴──────────┴──────────┴──────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 4. Transfer Statements

### break Statement

```java
// 1. break in loop
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break;  // Exit loop immediately
    }
    System.out.println(i);  // Prints 1, 2, 3, 4
}

// 2. break in switch
switch (x) {
    case 1:
        System.out.println("One");
        break;  // Exit switch
    case 2:
        System.out.println("Two");
        break;
}

// 3. Labeled break
outer: for (int i = 0; i < 3; i++) {
    inner: for (int j = 0; j < 3; j++) {
        if (j == 1) {
            break outer;  // Breaks outer loop
        }
        System.out.println(i + " " + j);
    }
}

// 4. break without label/switch
// break;  // ❌ ERROR: break outside switch or loop
```

### continue Statement

```java
// 1. continue in loop
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue;  // Skip current iteration
    }
    System.out.println(i);  // Prints 1, 2, 4, 5
}

// 2. continue in while
int i = 0;
while (i < 5) {
    i++;
    if (i == 3) {
        continue;
    }
    System.out.println(i);  // Prints 1, 2, 4, 5
}

// 3. Labeled continue
outer: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) {
            continue outer;  // Continue to next iteration of outer loop
        }
        System.out.println(i + " " + j);
    }
}

// 4. continue without loop
// continue;  // ❌ ERROR: continue outside of loop
```

### return Statement

```java
// 1. return with value
public int sum(int a, int b) {
    return a + b;  // Exit method and return value
}

// 2. return without value (void method)
public void printHello() {
    System.out.println("Hello");
    return;  // Optional in void method
}

// 3. Early return
public void checkAge(int age) {
    if (age < 0) {
        return;  // Exit early
    }
    System.out.println("Valid age: " + age);
}

// 4. return in main
public static void main(String[] args) {
    System.out.println("Start");
    return;  // Exit main method
    // System.out.println("End");  // ❌ ERROR: unreachable statement
}
```

### System.exit()

```java
// Terminates JVM completely
System.exit(0);   // Normal termination
System.exit(1);   // Abnormal termination

// 0 = Success, non-zero = Error
```

### Transfer Statements Summary

```
┌─────────────────────────────────────────────────────────────────────┐
│                    TRANSFER STATEMENTS SUMMARY                        │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Statement          │ Effect                                   │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ break              │ Exit current loop/switch                 │  │
│  │ break label        │ Exit labeled block                       │  │
│  │ continue           │ Skip to next iteration                   │  │
│  │ continue label     │ Continue outer loop                      │  │
│  │ return             │ Exit method (with or without value)      │  │
│  │ System.exit()      │ Terminate JVM                            │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. Interview Questions

### Q1. What is the difference between `if` and `switch`?

```java
// if: Any boolean condition
// switch: Equality comparison only

// if can handle ranges:
if (x > 0 && x < 10) { }  // ✅

// switch cannot handle ranges directly:
// switch (x > 0 && x < 10) { }  // ❌ ERROR: switch needs single value

// switch is more efficient for multiple discrete values:
// if (x == 1) { } else if (x == 2) { } ... // Verbose
// switch (x) { case 1: case 2: ... }  // Cleaner

// switch supports: byte, short, int, char, String, enum
// if supports: any boolean expression
```

### Q2. Can we use `switch` with `long` or `double`?

```java
// ❌ NO
long l = 10L;
// switch (l) { }  // ❌ ERROR: incompatible types

double d = 10.5;
// switch (d) { }  // ❌ ERROR: incompatible types

// Allowed: byte, short, int, char, String, enum
// Not allowed: long, float, double, boolean
```

### Q3. What is the difference between `while` and `do-while`?

```java
// while: condition checked BEFORE execution
// do-while: condition checked AFTER execution

// while: minimum 0 executions
// do-while: minimum 1 execution

// Example:
int x = 10;
while (x < 5) {
    System.out.println("while");  // Never prints
}

do {
    System.out.println("do-while");  // Prints once
} while (x < 5);
```

### Q4. What is the output of the following?

```java
for (int i = 0; i < 5; i++) {
    if (i == 3) {
        continue;
    }
    System.out.println(i);
}
// Output: 0, 1, 2, 4

// continue skips the current iteration (i=3)
// break would exit the loop entirely
```

### Q5. Can we use `break` in `if` without a loop?

```java
// ❌ NO
if (true) {
    break;  // ❌ ERROR: break outside switch or loop
}

// break can only be used inside:
// 1. Loops (for, while, do-while)
// 2. switch statement

// Solution: Use return or System.exit()
if (true) {
    return;  // ✅ Exit method
}
```

### Q6. What is labeled `break` and `continue`?

```java
// Labeled break: exit outer loop
outer: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) {
            break outer;  // Breaks outer loop
        }
    }
}

// Labeled continue: skip to next outer loop iteration
outer: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) {
            continue outer;  // Continue outer loop
        }
    }
}

// Labels can be any valid identifier
label1: for (...) { }
```

### Q7. What is fall-through in `switch`?

```java
int x = 2;
switch (x) {
    case 1:
        System.out.println("One");
    case 2:
        System.out.println("Two");      // Prints
    case 3:
        System.out.println("Three");    // Also prints (fall-through)
    default:
        System.out.println("Default");   // Also prints (fall-through)
}
// Output: Two, Three, Default

// Without break, execution continues to the next case
// break prevents fall-through

// Use fall-through intentionally for multiple cases:
switch (month) {
    case 3: case 4: case 5:
        System.out.println("Spring");
        break;
}
```

### Q8. What is the difference between `return` and `System.exit()`?

```java
// return: exits the current method
// System.exit(): terminates the entire JVM

public void method() {
    return;  // ✅ Exit method, other methods continue
}

public void method() {
    System.exit(0);  // Terminates JVM completely
    // No code executes after this
}

// System.exit(0) = normal termination
// System.exit(1) = abnormal termination
// Non-zero exit code = error
```

### Q9. Can we write `for(;;)`?

```java
// ✅ YES — infinite loop
for (;;) {
    System.out.println("Infinite");
    break;  // Exit after one iteration
}

// All parts of for are optional:
// for (init; condition; update)
// for (;;) = no init, no condition (always true), no update

// Equivalent to:
while (true) { }
```

### Q10. What is the output of nested loops?

```java
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.println(i + " " + j);
    }
}
// Output:
// 1 1
// 1 2
// 1 3
// 2 1
// 2 2
// 2 3
// 3 1
// 3 2
// 3 3

// Outer loop: 3 iterations
// Inner loop: 3 iterations per outer loop
// Total: 3 × 3 = 9 iterations
```

---

## 6. Quick Reference

### Loop Syntax

```
for loop:
for (init; condition; update) { }

while loop:
while (condition) { }

do-while loop:
do { } while (condition);

for-each loop:
for (Type var : collection) { }
```

### switch Syntax

```
switch (expression) {
    case value1:
        // statements
        break;
    case value2:
        // statements
        break;
    default:
        // statements
}

// Java 12+ switch expression:
result = switch (expression) {
    case value1 -> result1;
    case value2 -> result2;
    default -> defaultResult;
};
```

### Transfer Statements

```
break;              // Exit loop/switch
break label;        // Exit labeled block
continue;           // Skip to next iteration
continue label;     // Skip to next outer iteration
return;             // Exit void method
return value;       // Exit method with value
System.exit(0);     // Terminate JVM
```

---

## 7. Key Takeaways

1. **if-else** handles any boolean condition; **switch** handles equality only.
2. **switch** supports: byte, short, int, char, String, enum. NOT: long, float, double, boolean.
3. **case values** must be compile-time constants (final literals).
4. **Fall-through** in switch: without break, execution continues to next case.
5. **while** checks condition BEFORE execution; **do-while** checks AFTER.
6. **for** is best for known iterations; **while** for unknown.
7. **for-each** is for collections/arrays but cannot modify elements or index.
8. **break** exits loop/switch; **continue** skips to next iteration.
9. **Labeled break/continue** control outer loops from inner loops.
10. **return** exits method; **System.exit()** terminates JVM.
11. **All for loop parts** are optional: `for(;;)` creates infinite loop.
12. **Curly braces** are optional for single statements but risky.
13. **Nested loops** complexity: outer × inner iterations.
14. **Switch expression** (Java 12+) uses arrow syntax without break.
15. **do-while** semicolon is mandatory after while.

---

**Happy coding! 🚀**

*Flow control determines the logic of your program — master it to control program execution.*
