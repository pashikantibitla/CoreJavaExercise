/**
 * AssertionInterview.java
 * Interview-level problems and demonstrations for Java assertions.
 *
 * Covers:
 * - Checking if assertions are enabled
 * - Control-flow invariant assertions
 * - Side-effect warning
 * - Assertions vs Exceptions comparison
 * - Bad practices to avoid
 *
 * Run with assertions enabled:
 *   java -ea AssertionInterview
 */
public class AssertionInterview {

    public static void main(String[] args) {
        System.out.println("=== Assertion Interview Problems ===\n");

        // Problem 1: Check if assertions are enabled
        checkAssertionsEnabled();

        // Problem 2: Control-flow invariant
        processDay(3);

        // Problem 3: Side effect warning
        sideEffectDemo();

        // Problem 4: Assertions vs Exceptions
        assertionsVsExceptions();

        // Problem 5: Bad practices
        badPracticesDemo();
    }

    /**
     * Problem 1: How to check if assertions are enabled?
     */
    private static void checkAssertionsEnabled() {
        System.out.println("--- Problem 1: Check Assertions Enabled ---");
        boolean assertsEnabled = false;

        // This trick sets assertsEnabled to true ONLY if assertions are enabled
        assert assertsEnabled = true;

        if (assertsEnabled) {
            System.out.println("Assertions are ENABLED");
        } else {
            System.out.println("Assertions are DISABLED");
        }
        System.out.println();
    }

    /**
     * Problem 2: Control-flow invariant with assert
     */
    private static void processDay(int day) {
        System.out.println("--- Problem 2: Control-Flow Invariant ---");
        String result;
        switch (day) {
            case 1: result = "Monday"; break;
            case 2: result = "Tuesday"; break;
            case 3: result = "Wednesday"; break;
            case 4: result = "Thursday"; break;
            case 5: result = "Friday"; break;
            case 6: result = "Saturday"; break;
            case 7: result = "Sunday"; break;
            default:
                assert false : "Invalid day: " + day;
                result = "Unknown";
        }
        System.out.println("Day " + day + " is " + result);
        System.out.println();
    }

    /**
     * Problem 3: Side effect warning in assertions
     */
    private static void sideEffectDemo() {
        System.out.println("--- Problem 3: Side Effect Warning ---");
        int count = 0;

        // BAD: This has a side effect!
        // When assertions are disabled, count++ is NOT executed
        // assert (count++) > 0 : "Count incremented";

        // GOOD: No side effect
        assert count >= 0 : "Count should be non-negative";
        count++;  // Always executed

        System.out.println("Final count: " + count);
        System.out.println("Never put side effects in assertions!");
        System.out.println();
    }

    /**
     * Problem 4: Demonstrate assertions vs exceptions
     */
    private static void assertionsVsExceptions() {
        System.out.println("--- Problem 4: Assertions vs Exceptions ---");

        // Exception handling: ALWAYS enforced
        String input = null;
        try {
            if (input == null) {
                throw new IllegalArgumentException("Input cannot be null");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Assertion: Only if enabled
        assert input != null : "Input should not be null at this point";
        System.out.println("Assertion passed (if enabled)");
        System.out.println();
    }

    /**
     * Problem 5: Bad practices to avoid
     */
    private static void badPracticesDemo() {
        System.out.println("--- Problem 5: Bad Practices to Avoid ---");

        System.out.println("1. Don't use assertions for public method argument validation");
        System.out.println("   BAD:  assert arg > 0;");
        System.out.println("   GOOD: if (arg <= 0) throw new IllegalArgumentException();");

        System.out.println("2. Don't use assertions for error handling");
        System.out.println("   BAD:  assert file.exists();");
        System.out.println("   GOOD: try-catch with FileNotFoundException");

        System.out.println("3. Don't use assertions for security checks");
        System.out.println("   BAD:  assert user.isAdmin();");
        System.out.println("   GOOD: if (!user.isAdmin()) throw new SecurityException();");

        System.out.println("4. Don't catch AssertionError");
        System.out.println("   BAD:  try { assert false; } catch (AssertionError e) { ... }");
        System.out.println("   AssertionError should NEVER be caught.");

        System.out.println("5. Don't use assertions with side effects");
        System.out.println("   BAD:  assert (count++) > 0;");
        System.out.println("   GOOD: assert count >= 0; count++;");
        System.out.println();
    }
}
