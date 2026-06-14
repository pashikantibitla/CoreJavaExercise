import java.util.regex.*;

/**
 * Quantifiers.java
 * Demonstrates quantifiers in Java regex.
 *
 * Topics:
 * - ?, *, +, {n}, {n,}, {n,m}
 * - Greedy vs Reluctant vs Possessive modes
 */
public class Quantifiers {
    public static void main(String[] args) {
        System.out.println("=== Quantifiers Demo ===\n");

        // 1. ? — Zero or one
        System.out.println("--- 1. ? (Zero or One) ---");
        System.out.println("colou?r matches 'color': " + Pattern.matches("colou?r", "color"));
        System.out.println("colou?r matches 'colour': " + Pattern.matches("colou?r", "colour"));
        System.out.println("colou?r matches 'colouur': " + Pattern.matches("colou?r", "colouur"));
        System.out.println();

        // 2. * — Zero or more
        System.out.println("--- 2. * (Zero or More) ---");
        System.out.println("a* matches '': " + Pattern.matches("a*", ""));
        System.out.println("a* matches 'a': " + Pattern.matches("a*", "a"));
        System.out.println("a* matches 'aaa': " + Pattern.matches("a*", "aaa"));
        System.out.println("a* matches 'b': " + Pattern.matches("a*", "b"));
        System.out.println();

        // 3. + — One or more
        System.out.println("--- 3. + (One or More) ---");
        System.out.println("a+ matches '': " + Pattern.matches("a+", ""));
        System.out.println("a+ matches 'a': " + Pattern.matches("a+", "a"));
        System.out.println("a+ matches 'aaa': " + Pattern.matches("a+", "aaa"));
        System.out.println("a+ matches 'b': " + Pattern.matches("a+", "b"));
        System.out.println();

        // 4. {n} — Exactly n times
        System.out.println("--- 4. {n} (Exactly n times) ---");
        System.out.println("a{3} matches 'aaa': " + Pattern.matches("a{3}", "aaa"));
        System.out.println("a{3} matches 'aa': " + Pattern.matches("a{3}", "aa"));
        System.out.println("\\d{4} matches '1234': " + Pattern.matches("\\d{4}", "1234"));
        System.out.println("\\d{4} matches '12345': " + Pattern.matches("\\d{4}", "12345"));
        System.out.println();

        // 5. {n,} — At least n times
        System.out.println("--- 5. {n,} (At least n times) ---");
        System.out.println("a{2,} matches 'aa': " + Pattern.matches("a{2,}", "aa"));
        System.out.println("a{2,} matches 'aaaa': " + Pattern.matches("a{2,}", "aaaa"));
        System.out.println("a{2,} matches 'a': " + Pattern.matches("a{2,}", "a"));
        System.out.println();

        // 6. {n,m} — Between n and m times
        System.out.println("--- 6. {n,m} (Between n and m) ---");
        System.out.println("a{2,4} matches 'aa': " + Pattern.matches("a{2,4}", "aa"));
        System.out.println("a{2,4} matches 'aaaa': " + Pattern.matches("a{2,4}", "aaaa"));
        System.out.println("a{2,4} matches 'aaaaa': " + Pattern.matches("a{2,4}", "aaaaa"));
        System.out.println();

        // 7. Greedy vs Reluctant
        System.out.println("--- 7. Greedy vs Reluctant ---");
        String text = "aaaa";

        Matcher greedy = Pattern.compile("a+").matcher(text);
        if (greedy.find()) {
            System.out.println("Greedy a+ on \"aaaa\": \"" + greedy.group() + "\" (matches max)");
        }

        Matcher reluctant = Pattern.compile("a+?").matcher(text);
        if (reluctant.find()) {
            System.out.println("Reluctant a+? on \"aaaa\": \"" + reluctant.group() + "\" (matches min)");
        }
        System.out.println();

        // 8. Possessive
        System.out.println("--- 8. Possessive Quantifier ---");
        String text2 = "aaaaab";
        Matcher possessive = Pattern.compile("a++b").matcher(text2);
        System.out.println("Possessive a++b on \"aaaaab\": " + possessive.find());
        System.out.println("  (a++ consumes all 'a's, no backtracking for 'b')");

        Matcher greedy2 = Pattern.compile("a+b").matcher(text2);
        System.out.println("Greedy a+b on \"aaaaab\": " + greedy2.find());
        System.out.println("  (a+ backtracks to allow 'b' to match)");
        System.out.println();

        // 9. Practical: US zip code
        System.out.println("--- 9. Practical Example: US Zip Code ---");
        String zipPattern = "\\d{5}(-\\d{4})?";
        System.out.println("12345 matches zip: " + Pattern.matches(zipPattern, "12345"));
        System.out.println("12345-6789 matches zip: " + Pattern.matches(zipPattern, "12345-6789"));
        System.out.println("1234 matches zip: " + Pattern.matches(zipPattern, "1234"));
    }
}
