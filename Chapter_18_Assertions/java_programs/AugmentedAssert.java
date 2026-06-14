import java.util.Scanner;

/**
 * AugmentedAssert.java
 * Demonstrates augmented assertions with custom detail messages.
 *
 * Run with assertions enabled:
 *   java -ea AugmentedAssert
 */
public class AugmentedAssert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive number: ");
        int number = scanner.nextInt();

        // Augmented assert with custom message
        assert number > 0 : "Number must be positive, but was: " + number;
        System.out.println("You entered: " + number);

        // Augmented assert with method call
        String input = "test";
        assert input.length() > 0 : getErrorMessage("input", input);
        System.out.println("String assertion passed: '" + input + "' is not empty");

        // Augmented assert for array bounds
        int[] arr = {10, 20, 30};
        int index = 5;
        assert index >= 0 && index < arr.length
            : "Index out of bounds: " + index + " (length: " + arr.length + ")";
        System.out.println("Element: " + arr[index]);

        scanner.close();
    }

    private static String getErrorMessage(String fieldName, Object value) {
        return "Validation failed for " + fieldName + ": " + value;
    }
}
