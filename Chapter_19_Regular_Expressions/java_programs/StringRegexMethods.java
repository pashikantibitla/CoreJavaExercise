import java.util.Arrays;

/**
 * StringRegexMethods.java
 * Demonstrates String class regex methods.
 *
 * Topics:
 * - matches(regex)
 * - replaceAll(regex, replacement)
 * - replaceFirst(regex, replacement)
 * - split(regex)
 * - split(regex, limit)
 */
public class StringRegexMethods {
    public static void main(String[] args) {
        System.out.println("=== String Regex Methods Demo ===\n");

        // 1. matches()
        System.out.println("--- 1. matches() ---");
        String email = "test@example.com";
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        System.out.println("\"" + email + "\" matches email regex: " + email.matches(emailRegex));

        String invalidEmail = "not-an-email";
        System.out.println("\"" + invalidEmail + "\" matches email regex: " + invalidEmail.matches(emailRegex));
        System.out.println();

        // 2. replaceAll()
        System.out.println("--- 2. replaceAll() ---");
        String text = "The price is $100 and $200";
        String replaced = text.replaceAll("\\$\\d+", "$$X");
        System.out.println("Original: " + text);
        System.out.println("Replaced: " + replaced);
        System.out.println();

        // 3. replaceFirst()
        System.out.println("--- 3. replaceFirst() ---");
        String firstReplaced = text.replaceFirst("\\$\\d+", "$$X");
        System.out.println("Original: " + text);
        System.out.println("First replaced: " + firstReplaced);
        System.out.println();

        // 4. split()
        System.out.println("--- 4. split() ---");
        String csv = "apple,banana,orange,grape";
        String[] fruits = csv.split(",");
        System.out.println("CSV: " + csv);
        System.out.println("Split by comma: " + Arrays.toString(fruits));
        System.out.println();

        // 5. split() with regex
        System.out.println("--- 5. split() with Regex ---");
        String data = "John  30    Engineer";
        String[] parts = data.split("\\s+");
        System.out.println("Data: \"" + data + "\"");
        System.out.println("Split by \\s+: " + Arrays.toString(parts));
        System.out.println();

        // 6. split() with limit
        System.out.println("--- 6. split() with Limit ---");
        String numbers = "1,2,3,4,5";
        String[] limited = numbers.split(",", 3);
        System.out.println("Numbers: " + numbers);
        System.out.println("Split with limit=3: " + Arrays.toString(limited));
        System.out.println();

        // 7. $0 and $1 in replacement
        System.out.println("--- 7. $0 and $1 in Replacement ---");
        String sentence = "Java is great";
        String bracketed = sentence.replaceAll("\\w+", "[$0]");
        System.out.println("Original: " + sentence);
        System.out.println("Bracketed: " + bracketed);

        String swap = "John Doe";
        String swapped = swap.replaceAll("(\\w+)\\s+(\\w+)", "$2, $1");
        System.out.println("Original: " + swap);
        System.out.println("Swapped: " + swapped);
        System.out.println();

        // 8. replaceAll vs replace
        System.out.println("--- 8. replaceAll() vs replace() ---");
        String text2 = "a1b2c3";
        System.out.println("Original: " + text2);
        System.out.println("replaceAll(\"\\d\", \"X\"): " + text2.replaceAll("\\d", "X"));
        System.out.println("replace(\"\\d\", \"X\"): " + text2.replace("\\d", "X"));
        System.out.println("replace('1', 'X'): " + text2.replace('1', 'X'));
    }
}
