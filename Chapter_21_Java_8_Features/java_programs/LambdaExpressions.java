import java.util.Arrays;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        // Lambda with no parameters
        Runnable r = () -> System.out.println("Hello from Runnable");
        r.run();

        // Lambda with one parameter
        java.util.function.Consumer<String> printer = msg -> System.out.println("Message: " + msg);
        printer.accept("Lambda is awesome!");

        // Lambda with multiple parameters
        java.util.function.BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("Sum: " + add.apply(10, 20));

        // Lambda with block body
        java.util.function.BiFunction<Integer, Integer, Integer> max = (a, b) -> {
            if (a > b) return a;
            return b;
        };
        System.out.println("Max: " + max.apply(15, 8));

        // Lambda in collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println(name));

        // Thread with lambda
        new Thread(() -> System.out.println("Thread running with lambda")).start();
    }
}
