import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferences {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Static method reference
        names.forEach(System.out::println);

        // Instance method reference (arbitrary object)
        names.sort(String::compareToIgnoreCase);
        System.out.println("Sorted: " + names);

        // Instance method reference (specific object)
        String prefix = "Mr. ";
        names.forEach(prefix::concat); // not printed, just demo

        // Constructor reference
        Supplier<List<String>> listSupplier = java.util.ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("Tom");
        System.out.println("New list: " + newList);

        // Method reference in custom interface
        java.util.function.Function<String, String> toUpper = String::toUpperCase;
        System.out.println(toUpper.apply("java"));
    }
}
