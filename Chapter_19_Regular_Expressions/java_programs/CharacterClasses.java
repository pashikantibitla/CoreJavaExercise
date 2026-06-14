import java.util.regex.*;

/**
 * CharacterClasses.java
 * Demonstrates character classes and predefined classes in Java regex.
 *
 * Topics:
 * - Simple character classes [abc], [^abc], [a-z]
 * - Predefined classes \d, \D, \s, \S, \w, \W
 * - Union, intersection, subtraction
 */
public class CharacterClasses {
    public static void main(String[] args) {
        System.out.println("=== Character Classes Demo ===\n");

        // 1. Simple character classes
        System.out.println("--- 1. Simple Character Classes ---");
        System.out.println("[abc] matches 'a': " + Pattern.matches("[abc]", "a"));
        System.out.println("[abc] matches 'd': " + Pattern.matches("[abc]", "d"));
        System.out.println("[abc] matches 'ab': " + Pattern.matches("[abc]", "ab")); // false - matches single char
        System.out.println("[abc]+ matches 'ab': " + Pattern.matches("[abc]+", "ab")); // true
        System.out.println();

        // 2. Negation
        System.out.println("--- 2. Negation [^abc] ---");
        System.out.println("[^abc] matches 'd': " + Pattern.matches("[^abc]", "d"));
        System.out.println("[^abc] matches 'a': " + Pattern.matches("[^abc]", "a"));
        System.out.println();

        // 3. Range
        System.out.println("--- 3. Range [a-z] ---");
        System.out.println("[a-z] matches 'm': " + Pattern.matches("[a-z]", "m"));
        System.out.println("[a-z] matches 'M': " + Pattern.matches("[a-z]", "M"));
        System.out.println("[a-zA-Z] matches 'M': " + Pattern.matches("[a-zA-Z]", "M"));
        System.out.println();

        // 4. Predefined character classes
        System.out.println("--- 4. Predefined Character Classes ---");
        System.out.println("\\d (digit) matches '5': " + Pattern.matches("\\d", "5"));
        System.out.println("\\d (digit) matches 'a': " + Pattern.matches("\\d", "a"));
        System.out.println("\\D (non-digit) matches 'a': " + Pattern.matches("\\D", "a"));
        System.out.println("\\D (non-digit) matches '5': " + Pattern.matches("\\D", "5"));
        System.out.println();

        System.out.println("\\s (whitespace) matches ' ': " + Pattern.matches("\\s", " "));
        System.out.println("\\s (whitespace) matches '\\t': " + Pattern.matches("\\s", "\t"));
        System.out.println("\\S (non-whitespace) matches 'a': " + Pattern.matches("\\S", "a"));
        System.out.println();

        System.out.println("\\w (word) matches '_': " + Pattern.matches("\\w", "_"));
        System.out.println("\\w (word) matches '7': " + Pattern.matches("\\w", "7"));
        System.out.println("\\w (word) matches '@': " + Pattern.matches("\\w", "@"));
        System.out.println("\\W (non-word) matches '@': " + Pattern.matches("\\W", "@"));
        System.out.println();

        // 5. Dot (any character)
        System.out.println("--- 5. Dot (any character) ---");
        System.out.println(". matches 'a': " + Pattern.matches(".", "a"));
        System.out.println(". matches '1': " + Pattern.matches(".", "1"));
        System.out.println(". matches '@': " + Pattern.matches(".", "@"));
        System.out.println(".. matches 'ab': " + Pattern.matches("..", "ab"));
        System.out.println();

        // 6. Union and intersection
        System.out.println("--- 6. Union and Intersection ---");
        System.out.println("[a-z&&[def]] matches 'e': " + Pattern.matches("[a-z&&[def]]", "e"));
        System.out.println("[a-z&&[def]] matches 'a': " + Pattern.matches("[a-z&&[def]]", "a"));
        System.out.println("[a-z&&[^bc]] matches 'a': " + Pattern.matches("[a-z&&[^bc]]", "a"));
        System.out.println("[a-z&&[^bc]] matches 'b': " + Pattern.matches("[a-z&&[^bc]]", "b"));
        System.out.println();

        // 7. Practical examples
        System.out.println("--- 7. Practical Examples ---");
        String hex = "A3F";
        System.out.println("Is \"" + hex + "\" a valid hex number? " + Pattern.matches("[0-9A-Fa-f]+", hex));

        String binary = "1010";
        System.out.println("Is \"" + binary + "\" a valid binary number? " + Pattern.matches("[01]+", binary));

        String vowel = "e";
        System.out.println("Is \"" + vowel + "\" a vowel? " + Pattern.matches("[aeiouAEIOU]", vowel));
    }
}
