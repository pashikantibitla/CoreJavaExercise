# Chapter 19 — Regular Expressions

> **Topics:** Introduction, Pattern and Matcher, Character Classes, Quantifiers, Boundary Matchers, Groups and Capturing, String Regex Methods, Common Patterns, Interview FAQs

---

## Table of Contents

1. [Introduction to Regular Expressions](#1-introduction-to-regular-expressions)
2. [Pattern and Matcher Classes](#2-pattern-and-matcher-classes)
3. [Character Classes](#3-character-classes)
4. [Quantifiers](#4-quantifiers)
5. [Boundary Matchers](#5-boundary-matchers)
6. [Groups and Capturing](#6-groups-and-capturing)
7. [String Class Regex Methods](#7-string-class-regex-methods)
8. [Common Regex Patterns](#8-common-regex-patterns)
9. [Interview Questions](#9-interview-questions)
10. [Quick Reference](#10-quick-reference)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Introduction to Regular Expressions

### What is a Regular Expression?

A Regular Expression (regex) is a sequence of characters that defines a search pattern. It is used to match, locate, and manage text.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    REGULAR EXPRESSION OVERVIEW                          │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ Definition:                                                 │   │
│  │ A regular expression is a pattern describing a set of      │   │
│  │ strings, used for matching, searching, and manipulating    │   │
│  │ text.                                                      │   │
│  │                                                             │   │
│  │ Java Package: java.util.regex                              │   │
│  │ Main Classes: Pattern, Matcher, PatternSyntaxException    │   │
│  │                                                             │   │
│  │ Use Cases:                                                  │   │
│  │ 1. Validation (email, phone, password)                    │   │
│  │ 2. Search and replace                                      │   │
│  │ 3. Text parsing and extraction                            │   │
│  │ 4. Data extraction from logs/files                        │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Basic Example

```java
import java.util.regex.*;

public class RegexIntro {
    public static void main(String[] args) {
        String text = "The price is $100";
        String pattern = "\\d+";  // Matches one or more digits
        
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        
        if (m.find()) {
            System.out.println("Found: " + m.group());  // Found: 100
        }
    }
}
```

---

## 2. Pattern and Matcher Classes

### Pattern Class

```java
import java.util.regex.Pattern;

// 1. Compile a pattern
Pattern pattern = Pattern.compile("[a-z]+");

// 2. Compile with flags
Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);

// 3. Common flags
Pattern.CASE_INSENSITIVE   // (?i) — ignore case
Pattern.MULTILINE          // (?m) — ^ and $ match line boundaries
Pattern.DOTALL             // (?s) — . matches newline
Pattern.UNICODE_CASE       // (?u) — Unicode-aware case matching
Pattern.COMMENTS           // (?x) — ignore whitespace and comments

// 4. Pattern matches (static method)
boolean isMatch = Pattern.matches("[a-z]+", "hello");  // true

// 5. Split using pattern
Pattern p = Pattern.compile("[,\\s]+");
String[] parts = p.split("apple, banana  orange");  // ["apple", "banana", "orange"]
```

### Matcher Class

```java
import java.util.regex.Matcher;

Matcher matcher = pattern.matcher("input text");

// Matching methods
boolean matches()      // Attempts to match entire input
boolean find()       // Finds next subsequence that matches
boolean find(int start) // Finds from specified index
boolean lookingAt()  // Matches from beginning (but not entire input)

// Group methods
String group()         // Returns matched string
String group(int n)    // Returns nth capturing group
int groupCount()       // Returns number of capturing groups
int start()            // Start index of match
int end()              // End index of match
int start(int n)       // Start of nth group
int end(int n)         // End of nth group

// Replacement methods
String replaceAll(String replacement)   // Replace all matches
String replaceFirst(String replacement) // Replace first match
Matcher appendReplacement(StringBuffer sb, String replacement)
Matcher appendTail(StringBuffer sb)
```

### Pattern and Matcher Example

```java
import java.util.regex.*;

public class PatternMatcherDemo {
    public static void main(String[] args) {
        String text = "Java 8, Java 11, Java 17, and Java 21 are LTS versions.";
        String regex = "Java\\s+\\d+";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        // find() — finds all matches
        System.out.println("All matches:");
        while (matcher.find()) {
            System.out.println("Found: " + matcher.group() + 
                " at [" + matcher.start() + "-" + matcher.end() + "]");
        }
        
        // matches() — entire input must match
        String input = "Java 17";
        Matcher m2 = Pattern.compile("Java\\s+\\d+").matcher(input);
        System.out.println("matches(): " + m2.matches());  // true
        
        // lookingAt() — must match from beginning
        String input2 = "Java 17 is great";
        Matcher m3 = Pattern.compile("Java\\s+\\d+").matcher(input2);
        System.out.println("lookingAt(): " + m3.lookingAt());  // true
    }
}

// Output:
// All matches:
// Found: Java 8 at [0-7]
// Found: Java 11 at [9-16]
// Found: Java 17 at [18-25]
// Found: Java 21 at [31-38]
// matches(): true
// lookingAt(): true
```

---

## 3. Character Classes

### Character Class Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CHARACTER CLASSES                                   │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Class          │ Description                                    │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ [abc]          │ a, b, or c (simple class)                      │  │
│  │ [^abc]         │ Any character except a, b, or c (negation)    │  │
│  │ [a-z]          │ a through z (range)                            │  │
│  │ [a-zA-Z]       │ a through z or A through Z (union)            │  │
│  │ [a-z&&[def]]   │ d, e, or f (intersection)                      │  │
│  │ [a-z&&[^bc]]   │ a through z, except b and c (subtraction)     │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Predefined Character Classes

```
┌─────────────────────────────────────────────────────────────────────┐
│                    PREDEFINED CHARACTER CLASSES                        │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Class          │ Description                                    │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ .              │ Any character (may or may not match line     │  │
│  │                │ terminators)                                   │  │
│  │ \\d            │ Any digit: [0-9]                              │  │
│  │ \\D            │ Any non-digit: [^0-9]                         │  │
│  │ \\s            │ Any whitespace: [ \\t\\n\\x0B\\f\\r]            │  │
│  │ \\S            │ Any non-whitespace: [^\\s]                     │  │
│  │ \\w            │ Any word character: [a-zA-Z_0-9]              │  │
│  │ \\W            │ Any non-word character: [^\\w]                 │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
import java.util.regex.*;

public class CharacterClassesDemo {
    public static void main(String[] args) {
        // [abc] — simple class
        System.out.println(Pattern.matches("[abc]", "a"));    // true
        System.out.println(Pattern.matches("[abc]", "d"));    // false
        
        // [^abc] — negation
        System.out.println(Pattern.matches("[^abc]", "d"));    // true
        System.out.println(Pattern.matches("[^abc]", "a"));    // false
        
        // [a-z] — range
        System.out.println(Pattern.matches("[a-z]", "m"));    // true
        System.out.println(Pattern.matches("[a-z]", "M"));    // false
        
        // [a-zA-Z] — union
        System.out.println(Pattern.matches("[a-zA-Z]", "M"));  // true
        
        // \\d — digit
        System.out.println(Pattern.matches("\\d", "5"));       // true
        System.out.println(Pattern.matches("\\d", "a"));       // false
        
        // \\D — non-digit
        System.out.println(Pattern.matches("\\D", "a"));       // true
        
        // \\s — whitespace
        System.out.println(Pattern.matches("\\s", " "));      // true
        System.out.println(Pattern.matches("\\s", "a"));      // false
        
        // \\S — non-whitespace
        System.out.println(Pattern.matches("\\S", "a"));       // true
        
        // \\w — word character
        System.out.println(Pattern.matches("\\w", "_"));       // true
        System.out.println(Pattern.matches("\\w", "@"));       // false
        
        // \\W — non-word character
        System.out.println(Pattern.matches("\\W", "@"));       // true
        
        // . — any character
        System.out.println(Pattern.matches(".", "a"));        // true
        System.out.println(Pattern.matches(".", "1"));        // true
        System.out.println(Pattern.matches(".", "@"));        // true
    }
}
```

---

## 4. Quantifiers

### Quantifier Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    QUANTIFIERS IN JAVA REGEX                           │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Quantifier     │ Description                                    │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ ?              │ Zero or one (optional)                         │  │
│  │ *              │ Zero or more                                   │  │
│  │ +              │ One or more                                    │  │
│  │ {n}            │ Exactly n times                                │  │
│  │ {n,}           │ At least n times                               │  │
│  │ {n,m}          │ At least n but not more than m times          │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
```

### Greedy, Reluctant, and Possessive Quantifiers

```
┌─────────────────────────────────────────────────────────────────────┐
│                    QUANTIFIER MODES                                    │
│  ┌────────────────┬──────────┬──────────┬──────────────────────────┐  │
│  │ Type           │ Greedy   │ Reluctant│ Possessive               │  │
│  ├────────────────┼──────────┼──────────┼──────────────────────────┤  │
│  │ Zero or one    │ ?        │ ??       │ ?+                       │  │
│  ├────────────────┼──────────┼──────────┼──────────────────────────┤  │
│  │ Zero or more   │ *        │ *?       │ *+                       │  │
│  ├────────────────┼──────────┼──────────┼──────────────────────────┤  │
│  │ One or more    │ +        │ +?       │ ++                       │  │
│  ├────────────────┼──────────┼──────────┼──────────────────────────┤  │
│  │ Exactly n      │ {n}      │ {n}?     │ {n}+                     │  │
│  ├────────────────┼──────────┼──────────┼──────────────────────────┤  │
│  │ At least n     │ {n,}     │ {n,}?    │ {n,}+                    │  │
│  ├────────────────┼──────────┼──────────┼──────────────────────────┤  │
│  │ n to m         │ {n,m}    │ {n,m}?   │ {n,m}+                   │  │
│  └────────────────┴──────────┴──────────┴──────────────────────────┘  │
│                                                                      │
│  Greedy:     Matches as much as possible.                          │
│  Reluctant:  Matches as little as possible.                        │
│  Possessive: Matches as much as possible without backtracking.    │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
import java.util.regex.*;

public class QuantifiersDemo {
    public static void main(String[] args) {
        String text = "aaa";
        
        // ? — zero or one
        System.out.println(Pattern.matches("a?", ""));     // true
        System.out.println(Pattern.matches("a?", "a"));    // true
        System.out.println(Pattern.matches("a?", "aa"));  // false
        
        // * — zero or more
        System.out.println(Pattern.matches("a*", ""));     // true
        System.out.println(Pattern.matches("a*", "a"));    // true
        System.out.println(Pattern.matches("a*", "aaa"));  // true
        
        // + — one or more
        System.out.println(Pattern.matches("a+", ""));     // false
        System.out.println(Pattern.matches("a+", "a"));    // true
        System.out.println(Pattern.matches("a+", "aaa"));  // true
        
        // {n} — exactly n
        System.out.println(Pattern.matches("a{3}", "aaa"));   // true
        System.out.println(Pattern.matches("a{3}", "aa"));    // false
        
        // {n,} — at least n
        System.out.println(Pattern.matches("a{2,}", "aa"));   // true
        System.out.println(Pattern.matches("a{2,}", "a"));   // false
        
        // {n,m} — between n and m
        System.out.println(Pattern.matches("a{2,4}", "aaa"));  // true
        System.out.println(Pattern.matches("a{2,4}", "aaaaa")); // false
        
        // Greedy vs Reluctant
        String text2 = "aaa";
        Matcher greedy = Pattern.compile("a+").matcher(text2);
        Matcher reluctant = Pattern.compile("a+?").matcher(text2);
        
        if (greedy.find()) System.out.println("Greedy: " + greedy.group());     // aaa
        if (reluctant.find()) System.out.println("Reluctant: " + reluctant.group()); // a
    }
}
```

---

## 5. Boundary Matchers

### Boundary Matcher Types

```
┌─────────────────────────────────────────────────────────────────────┐
│                    BOUNDARY MATCHERS                                   │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Matcher        │ Description                                    │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ ^              │ Beginning of line                              │  │
│  │ $              │ End of line                                    │  │
│  │ \\b            │ Word boundary                                  │  │
│  │ \\B            │ Non-word boundary                              │  │
│  │ \\A            │ Beginning of input                             │  │
│  │ \\Z            │ End of input (before final line terminator)   │  │
│  │ \\z            │ End of input                                   │  │
│  │ \\G            │ End of previous match                          │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
import java.util.regex.*;

public class BoundaryMatchersDemo {
    public static void main(String[] args) {
        // ^ — beginning of line
        System.out.println(Pattern.matches("^Hello", "Hello World")); // true
        System.out.println(Pattern.matches("^Hello", "Say Hello"));   // false
        
        // $ — end of line
        System.out.println(Pattern.matches("World$", "Hello World")); // true
        System.out.println(Pattern.matches("Hello$", "Hello World")); // false
        
        // \\b — word boundary
        String text = "Hello World";
        Matcher m = Pattern.compile("\\bWorld\\b").matcher(text);
        System.out.println(m.find());  // true
        
        // \\B — non-word boundary
        Matcher m2 = Pattern.compile("\\Bor\\B").matcher("word");
        System.out.println(m2.find());  // true (or inside word)
        
        // \\A — beginning of input
        System.out.println(Pattern.matches("\\AHello", "Hello"));  // true
        
        // \\z — end of input
        System.out.println(Pattern.matches("Hello\\z", "Hello"));  // true
        
        // \\Z — end of input (allows final newline)
        System.out.println(Pattern.matches("Hello\\Z", "Hello\n")); // true
        
        // \\G — end of previous match
        String numbers = "1, 2, 3, 4";
        Matcher m3 = Pattern.compile("\\G,\\s*(\\d+)").matcher(numbers);
        m3.find();  // skip first number
        while (m3.find()) {
            System.out.println("Found: " + m3.group(1));  // 2, 3, 4
        }
    }
}
```

---

## 6. Groups and Capturing

### Grouping

```
┌─────────────────────────────────────────────────────────────────────┐
│                    GROUPS AND CAPTURING                                │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Group Type     │ Syntax                                         │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ Capturing      │ (regex) — stores match for later use          │  │
│  │ Non-capturing  │ (?:regex) — groups but does not capture       │  │
│  │ Named group    │ (?<name>regex) — capture with name (Java 7+)  │  │
│  │ Backreference  │ \\n — refers to nth capturing group               │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
import java.util.regex.*;

public class GroupsAndCapturingDemo {
    public static void main(String[] args) {
        // 1. Capturing groups
        String text = "John Doe, 30, Engineer";
        String regex = "(\\w+)\\s+(\\w+),\\s+(\\d+),\\s+(\\w+)";
        
        Matcher m = Pattern.compile(regex).matcher(text);
        if (m.matches()) {
            System.out.println("Full match: " + m.group(0));      // John Doe, 30, Engineer
            System.out.println("First name: " + m.group(1));      // John
            System.out.println("Last name: " + m.group(2));       // Doe
            System.out.println("Age: " + m.group(3));             // 30
            System.out.println("Job: " + m.group(4));             // Engineer
        }
        
        // 2. Non-capturing group
        String text2 = "color";
        // Match "color" or "colour" (non-capturing group)
        System.out.println(Pattern.matches("colou(?:r|re)", text2)); // false
        System.out.println(Pattern.matches("colou?r", "color"));   // true
        
        // 3. Named groups (Java 7+)
        String date = "2024-03-15";
        String dateRegex = "(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})";
        Matcher m2 = Pattern.compile(dateRegex).matcher(date);
        if (m2.matches()) {
            System.out.println("Year: " + m2.group("year"));      // 2024
            System.out.println("Month: " + m2.group("month"));    // 03
            System.out.println("Day: " + m2.group("day"));      // 15
        }
        
        // 4. Backreference
        String repeated = "abcabc";
        // Match 3 letters followed by same 3 letters
        System.out.println(Pattern.matches("(\\p{Alpha}{3})\\1", repeated)); // true
        
        // 5. Nested groups
        String nested = "a1b2c3";
        String nestRegex = "((a)(\\d))(b)(\\d)(c)(\\d)";
        Matcher m3 = Pattern.compile(nestRegex).matcher(nested);
        if (m3.matches()) {
            System.out.println("Groups: " + m3.groupCount());  // 7
            for (int i = 0; i <= m3.groupCount(); i++) {
                System.out.println("Group " + i + ": " + m3.group(i));
            }
        }
    }
}
```

---

## 7. String Class Regex Methods

### String Regex Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    STRING CLASS REGEX METHODS                          │
│  ┌────────────────┬────────────────────────────────────────────────┐  │
│  │ Method         │ Description                                    │  │
│  ├────────────────┼────────────────────────────────────────────────┤  │
│  │ matches(regex) │ Returns true if entire string matches         │  │
│  │ replaceAll(regex, replacement) │ Replaces all matches          │  │
│  │ replaceFirst(regex, replacement) │ Replaces first match        │  │
│  │ split(regex)   │ Splits string around matches                  │  │
│  │ split(regex, limit) │ Splits with max parts limit                │  │
│  └────────────────┴────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Examples

```java
public class StringRegexMethodsDemo {
    public static void main(String[] args) {
        // 1. matches()
        String email = "test@example.com";
        boolean isEmail = email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        System.out.println("Is email: " + isEmail);  // true
        
        // 2. replaceAll()
        String text = "The price is $100 and $200";
        String replaced = text.replaceAll("\\$\\d+", "$$X");
        System.out.println(replaced);  // The price is $X and $X
        
        // 3. replaceFirst()
        String firstReplaced = text.replaceFirst("\\$\\d+", "$$X");
        System.out.println(firstReplaced);  // The price is $X and $200
        
        // 4. split()
        String csv = "apple,banana,orange,grape";
        String[] fruits = csv.split(",");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        // apple
        // banana
        // orange
        // grape
        
        // 5. split() with regex
        String data = "John 30   Engineer";
        String[] parts = data.split("\\s+");  // split on one or more whitespace
        for (String part : parts) {
            System.out.println(part);
        }
        // John
        // 30
        // Engineer
        
        // 6. split() with limit
        String numbers = "1,2,3,4,5";
        String[] limited = numbers.split(",", 3);
        for (String num : limited) {
            System.out.println(num);
        }
        // 1
        // 2
        // 3,4,5
        
        // 7. $ and \\ in replacement
        String sentence = "Java is great";
        // $0 refers to entire match
        String upper = sentence.replaceAll("\\w+", "[$0]");
        System.out.println(upper);  // [Java] [is] [great]
        
        // $1 refers to first group
        String swap = "John Doe";
        String swapped = swap.replaceAll("(\\w+)\\s+(\\w+)", "$2, $1");
        System.out.println(swapped);  // Doe, John
    }
}
```

---

## 8. Common Regex Patterns

### Validation Patterns

```java
public class CommonRegexPatterns {
    public static void main(String[] args) {
        // 1. Email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        System.out.println("test@example.com".matches(emailRegex));      // true
        System.out.println("invalid.email".matches(emailRegex));           // false
        
        // 2. Phone number (US format)
        String phoneRegex = "^(\\+1[-\\s]?)?\\(?([0-9]{3})\\)?[-\\s]?([0-9]{3})[-\\s]?([0-9]{4})$";
        System.out.println("(123) 456-7890".matches(phoneRegex));  // true
        System.out.println("123-456-7890".matches(phoneRegex));    // true
        
        // 3. Password (at least 8 chars, 1 upper, 1 lower, 1 digit)
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        System.out.println("Password1".matches(passwordRegex));  // true
        System.out.println("password".matches(passwordRegex));  // false
        
        // 4. URL
        String urlRegex = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";
        System.out.println("https://www.example.com".matches(urlRegex));  // true
        
        // 5. IPv4 address
        String ipv4Regex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        System.out.println("192.168.1.1".matches(ipv4Regex));  // true
        System.out.println("256.1.1.1".matches(ipv4Regex));  // false
        
        // 6. Date (YYYY-MM-DD)
        String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        System.out.println("2024-03-15".matches(dateRegex));  // true
        
        // 7. Hex color
        String hexColorRegex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        System.out.println("#FF5733".matches(hexColorRegex));  // true
        System.out.println("#F53".matches(hexColorRegex));     // true
        
        // 8. Credit card (simplified)
        String ccRegex = "^\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}$";
        System.out.println("1234-5678-9012-3456".matches(ccRegex));  // true
    }
}
```

---

## 9. Interview Questions

### Q1. What is the difference between `matches()` and `find()`?

```java
import java.util.regex.*;

public class MatchesVsFind {
    public static void main(String[] args) {
        String text = "Java is fun";
        Pattern p = Pattern.compile("Java");
        Matcher m = p.matcher(text);
        
        // matches() — matches entire input
        System.out.println(m.matches());  // false ("Java" != "Java is fun")
        
        // find() — finds anywhere in input
        m.reset();  // Reset matcher
        System.out.println(m.find());     // true ("Java" found in text)
        
        // lookingAt() — matches from beginning
        m.reset();
        System.out.println(m.lookingAt()); // true ("Java" at start)
    }
}

// matches() = entire input must match
// find() = any subsequence can match
// lookingAt() = must match from beginning but not necessarily entire input
```

### Q2. How do you compile a case-insensitive regex?

```java
import java.util.regex.*;

public class CaseInsensitiveRegex {
    public static void main(String[] args) {
        // Method 1: Using Pattern flag
        Pattern p = Pattern.compile("hello", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("HELLO");
        System.out.println(m.matches());  // true
        
        // Method 2: Using inline flag (?i)
        Pattern p2 = Pattern.compile("(?i)hello");
        Matcher m2 = p2.matcher("HeLLo");
        System.out.println(m2.matches());  // true
        
        // Method 3: String.matches() with inline flag
        System.out.println("HELLO".matches("(?i)hello"));  // true
    }
}
```

### Q3. What is a capturing group? How to use it?

```java
import java.util.regex.*;

public class CapturingGroupDemo {
    public static void main(String[] args) {
        String text = "John Doe";
        String regex = "(\\w+)\\s+(\\w+)";
        
        Matcher m = Pattern.compile(regex).matcher(text);
        if (m.matches()) {
            System.out.println("Group 0 (full): " + m.group(0));  // John Doe
            System.out.println("Group 1: " + m.group(1));        // John
            System.out.println("Group 2: " + m.group(2));        // Doe
        }
        
        // Non-capturing group: (?:...)
        String regex2 = "(?:Mr\\.|Mrs\\.|Ms\\.)\\s+(\\w+)";
        Matcher m2 = Pattern.compile(regex2).matcher("Mr. Smith");
        if (m2.matches()) {
            System.out.println("Group 1: " + m2.group(1));  // Smith
            // Group 0: Mr. Smith
            // No group for (?:Mr\.|Mrs\.|Ms\.) because it's non-capturing
        }
    }
}
```

### Q4. What is the difference between `split()` and `split(regex, limit)`?

```java
public class SplitLimitDemo {
    public static void main(String[] args) {
        String text = "a,b,c,d,e";
        
        // split(regex) — no limit
        String[] parts1 = text.split(",");
        System.out.println(parts1.length);  // 5
        
        // split(regex, limit) — with limit
        String[] parts2 = text.split(",", 3);
        System.out.println(parts2.length);  // 3
        // parts2[0] = "a"
        // parts2[1] = "b"
        // parts2[2] = "c,d,e" (remaining string)
        
        // limit = 0 (default behavior, trailing empty strings removed)
        String text2 = "a,b,c,,";
        String[] parts3 = text2.split(",", 0);
        System.out.println(parts3.length);  // 3
        
        // limit = -1 (trailing empty strings included)
        String[] parts4 = text2.split(",", -1);
        System.out.println(parts4.length);  // 5
    }
}
```

### Q5. Write a regex to validate an email address.

```java
public class EmailValidation {
    public static void main(String[] args) {
        // Basic email regex
        String basicRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        // More strict regex
        String strictRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        
        String[] emails = {
            "test@example.com",
            "user.name@domain.co.in",
            "invalid.email",
            "@nodomain.com",
            "user@domain"
        };
        
        for (String email : emails) {
            System.out.println(email + " -> " + email.matches(basicRegex));
        }
    }
}
```

### Q6. What is the difference between greedy, reluctant, and possessive quantifiers?

```java
import java.util.regex.*;

public class QuantifierModes {
    public static void main(String[] args) {
        String text = "aaaa";
        
        // Greedy: matches as much as possible
        Matcher greedy = Pattern.compile("a+").matcher(text);
        if (greedy.find()) {
            System.out.println("Greedy: " + greedy.group());  // aaaa
        }
        
        // Reluctant: matches as little as possible
        Matcher reluctant = Pattern.compile("a+?").matcher(text);
        if (reluctant.find()) {
            System.out.println("Reluctant: " + reluctant.group());  // a
        }
        
        // Possessive: matches as much as possible, no backtracking
        String text2 = "aaaaab";
        Matcher possessive = Pattern.compile("a++b").matcher(text2);
        System.out.println("Possessive match: " + possessive.find());  // false
        // "a++" consumes all 'a's and doesn't backtrack, so 'b' has no match
        
        // With greedy: "a+" matches "aaaaa", then backtracks to match "b"
        Matcher greedy2 = Pattern.compile("a+b").matcher(text2);
        System.out.println("Greedy match: " + greedy2.find());  // true
    }
}
```

### Q7. How do you use backreferences in regex?

```java
public class BackreferenceDemo {
    public static void main(String[] args) {
        // Match repeated words
        String text = "hello hello world";
        String regex = "\\b(\\w+)\\s+\\1\\b";
        Matcher m = Pattern.compile(regex).matcher(text);
        System.out.println(m.find());  // true
        System.out.println(m.group());  // "hello hello"
        
        // Match same 3-letter prefix and suffix
        System.out.println("abcabc".matches("(\\p{Alpha}{3})\\1"));  // true
        
        // Match palindrome of 3 characters (aba, cdc, etc.)
        System.out.println("aba".matches("(.)(.)(\\1)"));  // true
        System.out.println("abc".matches("(.)(.)(\\1)"));  // false
    }
}
```

### Q8. What are the differences between `\s`, `\S`, `\d`, `\D`, `\w`, `\W`?

```java
public class PredefinedClasses {
    public static void main(String[] args) {
        // \\d = digit [0-9]
        System.out.println("5".matches("\\d"));   // true
        System.out.println("a".matches("\\d"));   // false
        
        // \\D = non-digit [^0-9]
        System.out.println("a".matches("\\D"));   // true
        System.out.println("5".matches("\\D"));   // false
        
        // \\s = whitespace [ \t\n\x0B\f\r]
        System.out.println(" ".matches("\\s"));   // true
        System.out.println("a".matches("\\s"));   // false
        
        // \\S = non-whitespace
        System.out.println("a".matches("\\S"));   // true
        System.out.println(" ".matches("\\S"));   // false
        
        // \\w = word character [a-zA-Z_0-9]
        System.out.println("_".matches("\\w"));   // true
        System.out.println("@".matches("\\w"));   // false
        
        // \\W = non-word character
        System.out.println("@".matches("\\W"));   // true
        System.out.println("_".matches("\\W"));   // false
    }
}
```

### Q9. What is the difference between `replaceAll()` and `replace()` in String?

```java
public class ReplaceVsReplaceAll {
    public static void main(String[] args) {
        // replaceAll() — uses regex
        String text1 = "a1b2c3";
        String result1 = text1.replaceAll("\\d", "X");
        System.out.println(result1);  // aXbXcX
        
        // replace() — uses literal string
        String text2 = "a1b2c3";
        String result2 = text2.replace("\\d", "X");
        System.out.println(result2);  // a1b2c3 (no match for literal \\d)
        
        // replace() with char
        String text3 = "hello";
        String result3 = text3.replace('l', 'x');
        System.out.println(result3);  // hexxo
        
        // replaceAll() treats backslashes specially
        String text4 = "abc";
        System.out.println(text4.replaceAll("b", "\\\\"));  // a\c
        // In replacement: \\ becomes \\ in regex replacement
    }
}
```

### Q10. How do you validate a phone number using regex?

```java
public class PhoneValidation {
    public static void main(String[] args) {
        // US phone number formats
        String[] phones = {
            "(123) 456-7890",
            "123-456-7890",
            "123.456.7890",
            "1234567890",
            "+1 (123) 456-7890",
            "123-45-678"  // invalid
        };
        
        // Regex for various formats
        String regex = "^(\\+1[-\\s]?)?\\(?([0-9]{3})\\)?[-\\s\\.]?([0-9]{3})[-\\s\\.]?([0-9]{4})$";
        
        for (String phone : phones) {
            System.out.println(phone + " -> " + phone.matches(regex));
        }
    }
}
```

---

## 10. Quick Reference

### Regex Syntax Summary

```
Character Classes:
  [abc]       a, b, or c
  [^abc]      Any character except a, b, or c
  [a-z]       a through z
  .           Any character
  \\d         Digit [0-9]
  \\D         Non-digit
  \\s         Whitespace
  \\S         Non-whitespace
  \\w         Word character [a-zA-Z_0-9]
  \\W         Non-word character

Quantifiers:
  ?           Zero or one
  *           Zero or more
  +           One or more
  {n}         Exactly n
  {n,}        At least n
  {n,m}       Between n and m

Boundary Matchers:
  ^           Beginning of line
  $           End of line
  \\b         Word boundary
  \\B         Non-word boundary
  \\A         Beginning of input
  \\Z         End of input (before final terminator)
  \\z         End of input

Groups:
  (regex)     Capturing group
  (?:regex)   Non-capturing group
  (?<name>...) Named group
  \\n         Backreference

String Methods:
  matches(regex)
  replaceAll(regex, replacement)
  replaceFirst(regex, replacement)
  split(regex)
  split(regex, limit)
```

---

## 11. Key Takeaways

1. **Regex is a pattern language** for matching, searching, and manipulating text.
2. **Pattern and Matcher** are the core classes in `java.util.regex`.
3. **Character classes** define sets of characters to match.
4. **Quantifiers** control how many times a pattern should match.
5. **Boundary matchers** check positions rather than characters.
6. **Capturing groups** store matched text for later use.
7. **String methods** (`matches`, `replaceAll`, `split`) provide convenient regex operations.
8. **Greedy quantifiers** match as much as possible; **reluctant** matches as little as possible.
9. **Assertions** (`(?=...)`, `(?!...)`) are used for lookahead checks.
10. **Regex is powerful but complex** — test patterns thoroughly before production use.

---

**Happy coding!**

*Master regex, and you master text processing in Java!*
