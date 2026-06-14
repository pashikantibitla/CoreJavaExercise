import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8InterviewProblems {
    public static void main(String[] args) {
        // Problem 1: Find second highest number
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        Integer secondHighest = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("Second highest: " + secondHighest);

        // Problem 2: Find first non-repeating character
        String input = "swiss";
        Character firstNonRepeat = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
                .findFirst()
                .orElse(null);
        System.out.println("First non-repeating: " + firstNonRepeat);

        // Problem 3: Count occurrence of each word
        String sentence = "java java python java python";
        Map<String, Long> wordCount = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("Word count: " + wordCount);

        // Problem 4: Check if list contains only odd numbers
        List<Integer> oddList = Arrays.asList(1, 3, 5, 7);
        boolean allOdd = oddList.stream().allMatch(n -> n % 2 != 0);
        System.out.println("All odd? " + allOdd);

        // Problem 5: Sum of first 10 natural numbers
        int sum = IntStream.rangeClosed(1, 10).sum();
        System.out.println("Sum 1-10: " + sum);

        // Problem 6: Flatten list of lists
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened: " + flat);

        // Problem 7: Find duplicates
        List<Integer> dupList = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> duplicates = dupList.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Duplicates: " + duplicates);
    }
}
