import java.util.regex.*;

/**
 * RegexInterview.java
 * Interview-level regex problems and validations.
 *
 * Covers:
 * - Email validation
 * - Phone number validation
 * - Password validation
 * - URL validation
 * - IPv4 validation
 * - Date validation
 * - Credit card validation
 */
public class RegexInterview {
    public static void main(String[] args) {
        System.out.println("=== Regex Interview-Level Problems ===\n");

        // 1. Email Validation
        System.out.println("--- 1. Email Validation ---");
        validateEmails();
        System.out.println();

        // 2. Phone Number Validation
        System.out.println("--- 2. Phone Number Validation ---");
        validatePhones();
        System.out.println();

        // 3. Password Validation
        System.out.println("--- 3. Password Validation ---");
        validatePasswords();
        System.out.println();

        // 4. URL Validation
        System.out.println("--- 4. URL Validation ---");
        validateUrls();
        System.out.println();

        // 5. IPv4 Validation
        System.out.println("--- 5. IPv4 Validation ---");
        validateIPv4();
        System.out.println();

        // 6. Date Validation
        System.out.println("--- 6. Date Validation ---");
        validateDates();
        System.out.println();

        // 7. Credit Card Validation
        System.out.println("--- 7. Credit Card Validation ---");
        validateCreditCards();
        System.out.println();

        // 8. Extract data from log
        System.out.println("--- 8. Extract Data from Log ---");
        extractLogData();
    }

    private static void validateEmails() {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String[] emails = {
            "test@example.com",
            "user.name@domain.co.in",
            "user+tag@example.com",
            "invalid.email",
            "@nodomain.com",
            "user@domain",
            "user name@example.com",
            "user@.com"
        };
        for (String email : emails) {
            System.out.println("  \"" + email + "\" -> " + email.matches(regex));
        }
    }

    private static void validatePhones() {
        // US phone number formats
        String regex = "^(\\+1[-\\s]?)?\\(?([0-9]{3})\\)?[-\\s\\.]?([0-9]{3})[-\\s\\.]?([0-9]{4})$";
        String[] phones = {
            "(123) 456-7890",
            "123-456-7890",
            "123.456.7890",
            "1234567890",
            "+1 (123) 456-7890",
            "123-45-678",
            "12345678901",
            "abc-def-ghij"
        };
        for (String phone : phones) {
            System.out.println("  \"" + phone + "\" -> " + phone.matches(regex));
        }
    }

    private static void validatePasswords() {
        // At least 8 chars, 1 upper, 1 lower, 1 digit
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        String[] passwords = {
            "Password1",
            "password",
            "PASSWORD1",
            "Pass1",
            "StrongPass123",
            "weak"
        };
        for (String pwd : passwords) {
            System.out.println("  \"" + pwd + "\" -> " + pwd.matches(regex));
        }
    }

    private static void validateUrls() {
        String regex = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";
        String[] urls = {
            "https://www.example.com",
            "http://example.com",
            "www.example.com",
            "example.com",
            "https://example.com/path/page.html",
            "not-a-url"
        };
        for (String url : urls) {
            System.out.println("  \"" + url + "\" -> " + url.matches(regex));
        }
    }

    private static void validateIPv4() {
        String regex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        String[] ips = {
            "192.168.1.1",
            "255.255.255.255",
            "0.0.0.0",
            "256.1.1.1",
            "192.168.1",
            "192.168.1.1.1"
        };
        for (String ip : ips) {
            System.out.println("  \"" + ip + "\" -> " + ip.matches(regex));
        }
    }

    private static void validateDates() {
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        String[] dates = {
            "2024-03-15",
            "2024-12-31",
            "2024-01-01",
            "2024-13-01",
            "2024-03-32",
            "2024-03-15T10:30:00"
        };
        for (String date : dates) {
            System.out.println("  \"" + date + "\" -> " + date.matches(regex));
        }
    }

    private static void validateCreditCards() {
        String regex = "^\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}$";
        String[] cards = {
            "1234-5678-9012-3456",
            "1234 5678 9012 3456",
            "1234567890123456",
            "1234-5678-9012",
            "1234-5678-9012-3456-7890"
        };
        for (String card : cards) {
            System.out.println("  \"" + card + "\" -> " + card.matches(regex));
        }
    }

    private static void extractLogData() {
        String logLine = "[2024-03-15 10:30:45] INFO: User 'john_doe' logged in from IP 192.168.1.100";
        String regex = "\\[(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\]\\s+(\\w+):\\s+.*User\\s+'(\\w+)'.*IP\\s+(\\d+\\.\\d+\\.\\d+\\.\\d+)";

        Matcher m = Pattern.compile(regex).matcher(logLine);
        if (m.matches()) {
            System.out.println("  Timestamp: " + m.group(1));
            System.out.println("  Level: " + m.group(2));
            System.out.println("  User: " + m.group(3));
            System.out.println("  IP: " + m.group(4));
        } else {
            System.out.println("  No match found");
        }
    }
}
