import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        // Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));

        // Function
        Function<String, Integer> lengthFunc = s -> s.length();
        System.out.println("Length of 'hello': " + lengthFunc.apply("hello"));

        // Consumer
        Consumer<String> printConsumer = s -> System.out.println("Consumed: " + s);
        printConsumer.accept("Test");

        // Supplier
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random: " + randomSupplier.get());

        // UnaryOperator
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square of 5: " + square.apply(5));

        // BinaryOperator
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println("Multiply 3 and 7: " + multiply.apply(3, 7));

        // Chaining
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isLessThan100 = n -> n < 100;
        Predicate<Integer> combined = isPositive.and(isLessThan100);
        System.out.println("Is 50 positive and <100? " + combined.test(50));

        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add3 = x -> x + 3;
        Function<Integer, Integer> combinedFunc = multiplyBy2.andThen(add3);
        System.out.println("(5 * 2) + 3 = " + combinedFunc.apply(5));
    }
}
