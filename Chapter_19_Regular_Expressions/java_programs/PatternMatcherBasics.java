import java.util.regex.*;

/**
 * PatternMatcherBasics.java
 * Demonstrates Pattern and Matcher class basics in Java.
 *
 * Topics:
 * - Pattern.compile()
 * - Matcher.find(), matches(), lookingAt()
 * - Matcher.group(), start(), end()
 * - Pattern flags
 */
public class PatternMatcherBasics {
    public static void main(String[] args) {
        String text = "The Java version is Java 17, and Java 21 is LTS.";

        // 1. Basic Pattern and Matcher
        System.out.println("=== 1. Basic Pattern and Matcher ===");
        Pattern pattern = Pattern.compile("Java\\s+\\d+");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Text: " + text);
        System.out.println("Pattern: Java\\s+\\d+");
        System.out.println();

        // 2. find() - finds all occurrences
        System.out.println("=== 2. find() - Find All Matches ===");
        while (matcher.find()) {
            System.out.println("Found: '" + matcher.group() + "' at position [" + matcher.start() + "-" + matcher.end() + "]");
        }
        System.out.println();

        // 3. matches() - entire input must match
        System.out.println("=== 3. matches() - Full Match ===");
        String input1 = "Java 17";
        Matcher m1 = Pattern.compile("Java\\s+\\d+").matcher(input1);
        System.out.println("Input: \"" + input1 + "\" matches \"Java\\s+\\d+\": " + m1.matches());

        String input2 = "The Java 17";
        Matcher m2 = Pattern.compile("Java\\s+\\d+").matcher(input2);
        System.out.println("Input: \"" + input2 + "\" matches \"Java\\s+\\d+\": " + m2.matches());
        System.out.println();

        // 4. lookingAt() - match from beginning
        System.out.println("=== 4. lookingAt() - Match From Start ===");
        String input3 = "Java 17 is great";
        Matcher m3 = Pattern.compile("Java\\s+\\d+").matcher(input3);
        System.out.println("Input: \"" + input3 + "\" lookingAt \"Java\\s+\\d+\": " + m3.lookingAt());
        System.out.println();

        // 5. Pattern flags - CASE_INSENSITIVE
        System.out.println("=== 5. Pattern Flags - CASE_INSENSITIVE ===");
        String caseText = "Hello World";
        Pattern casePattern = Pattern.compile("hello", Pattern.CASE_INSENSITIVE);
        Matcher caseMatcher = casePattern.matcher(caseText);
        System.out.println("CASE_INSENSITIVE match: " + caseMatcher.find());
        System.out.println();

        // 6. Pattern.matches() - static method
        System.out.println("=== 6. Pattern.matches() Static Method ===");
        boolean isMatch = Pattern.matches("[a-zA-Z]+", "Hello");
        System.out.println("\"Hello\" matches \"[a-zA-Z]+\": " + isMatch);
        System.out.println();

        // 7. Split using Pattern
        System.out.println("=== 7. Pattern.split() ===");
        Pattern splitPattern = Pattern.compile("[,\\s]+");
        String[] parts = splitPattern.split("apple, banana  orange");
        System.out.println("Split result:");
        for (String part : parts) {
            System.out.println("  " + part);
        }
        System.out.println();

        // 8. Reset and reuse matcher
        System.out.println("=== 8. Reset Matcher ===");
        matcher.reset();
        System.out.println("After reset, find() again:");
        while (matcher.find()) {
            System.out.println("  Found: " + matcher.group());
        }
    }
}
