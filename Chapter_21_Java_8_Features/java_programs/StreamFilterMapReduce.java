import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterMapReduce {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter: even numbers
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Evens: " + evens);

        // map: squares
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        // reduce: sum
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);

        // reduce: product
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);

        // filter + map + reduce combined
        int sumOfEvenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println("Sum of even squares: " + sumOfEvenSquares);
    }
}
