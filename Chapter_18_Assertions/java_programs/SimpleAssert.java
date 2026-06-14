import java.util.Scanner;

/**
 * SimpleAssert.java
 * Demonstrates the basic syntax of simple assertions in Java.
 *
 * Run with assertions enabled:
 *   java -ea SimpleAssert
 * Run with assertions disabled:
 *   java SimpleAssert
 */
public class SimpleAssert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive number: ");
        int number = scanner.nextInt();

        // Simple assert: no detail message
        assert number > 0;

        System.out.println("Assertion passed: " + number + " is positive");

        // Another simple assert for demonstration
        int age = 25;
        assert age >= 18;
        System.out.println("Age assertion passed: " + age + " is an adult");

        // Array bounds check
        int[] arr = {10, 20, 30};
        int index = 1;
        assert index >= 0 && index < arr.length;
        System.out.println("Element at index " + index + " is " + arr[index]);

        scanner.close();
    }
}
