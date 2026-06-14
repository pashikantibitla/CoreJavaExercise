import java.util.regex.*;

/**
 * GroupsAndCapturing.java
 * Demonstrates groups, capturing, and backreferences in Java regex.
 *
 * Topics:
 * - Capturing groups (regex)
 * - Non-capturing groups (?:regex)
 * - Named groups (?<name>regex)
 * - Backreferences \n
 */
public class GroupsAndCapturing {
    public static void main(String[] args) {
        System.out.println("=== Groups and Capturing Demo ===\n");

        // 1. Capturing groups
        System.out.println("--- 1. Capturing Groups ---");
        String text = "John Doe, 30, Engineer";
        String regex = "(\\w+)\\s+(\\w+),\\s+(\\d+),\\s+(\\w+)";
        Matcher m = Pattern.compile(regex).matcher(text);

        if (m.matches()) {
            System.out.println("Full match (group 0): " + m.group(0));
            System.out.println("First name (group 1): " + m.group(1));
            System.out.println("Last name (group 2): " + m.group(2));
            System.out.println("Age (group 3): " + m.group(3));
            System.out.println("Job (group 4): " + m.group(4));
            System.out.println("Total groups: " + m.groupCount());
        }
        System.out.println();

        // 2. Non-capturing group
        System.out.println("--- 2. Non-Capturing Group ---");
        String text2 = "Mr. Smith";
        String regex2 = "(?:Mr\\.|Mrs\\.|Ms\\.)\\s+(\\w+)";
        Matcher m2 = Pattern.compile(regex2).matcher(text2);
        if (m2.matches()) {
            System.out.println("Full match: " + m2.group(0));
            System.out.println("Captured group 1: " + m2.group(1));
            System.out.println("Total groups: " + m2.groupCount());
            System.out.println("(Title is not captured because of (?:...))");
        }
        System.out.println();

        // 3. Named groups (Java 7+)
        System.out.println("--- 3. Named Groups ---");
        String date = "2024-03-15";
        String dateRegex = "(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})";
        Matcher m3 = Pattern.compile(dateRegex).matcher(date);
        if (m3.matches()) {
            System.out.println("Year: " + m3.group("year"));
            System.out.println("Month: " + m3.group("month"));
            System.out.println("Day: " + m3.group("day"));
        }
        System.out.println();

        // 4. Backreferences
        System.out.println("--- 4. Backreferences ---");
        String repeated = "hello hello world";
        String repeatRegex = "\\b(\\w+)\\s+\\1\\b";
        Matcher m4 = Pattern.compile(repeatRegex).matcher(repeated);
        if (m4.find()) {
            System.out.println("Found repeated word: " + m4.group());
        }

        // Same prefix and suffix
        System.out.println("\"abcabc\" matches (\\p{Alpha}{3})\\1: " + Pattern.matches("(\\p{Alpha}{3})\\1", "abcabc"));

        // Palindrome of 3 chars
        System.out.println("\"aba\" is palindrome (3 chars): " + Pattern.matches("(.)(.)(\\1)", "aba"));
        System.out.println("\"abc\" is palindrome (3 chars): " + Pattern.matches("(.)(.)(\\1)", "abc"));
        System.out.println();

        // 5. Nested groups
        System.out.println("--- 5. Nested Groups ---");
        String nested = "a1b2c3";
        String nestRegex = "((a)(\\d))(b)(\\d)(c)(\\d)";
        Matcher m5 = Pattern.compile(nestRegex).matcher(nested);
        if (m5.matches()) {
            System.out.println("Total groups: " + m5.groupCount());
            for (int i = 0; i <= m5.groupCount(); i++) {
                System.out.println("Group " + i + ": " + m5.group(i));
            }
        }
        System.out.println();

        // 6. Practical: Extracting domain from email
        System.out.println("--- 6. Practical: Extract Email Domain ---");
        String email = "user@example.com";
        Matcher emailMatcher = Pattern.compile("([^@]+)@(.+)").matcher(email);
        if (emailMatcher.matches()) {
            System.out.println("Username: " + emailMatcher.group(1));
            System.out.println("Domain: " + emailMatcher.group(2));
        }
    }
}
