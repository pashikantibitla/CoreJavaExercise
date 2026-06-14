import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        // Creating Optional
        Optional<String> empty = Optional.empty();
        Optional<String> present = Optional.of("Hello");
        Optional<String> nullable = Optional.ofNullable(null);

        // isPresent
        System.out.println("Present isPresent? " + present.isPresent());
        System.out.println("Nullable isPresent? " + nullable.isPresent());

        // ifPresent
        present.ifPresent(s -> System.out.println("Value: " + s));
        nullable.ifPresent(s -> System.out.println("This won't print"));

        // orElse
        String value1 = nullable.orElse("Default");
        System.out.println("OrElse: " + value1);

        // orElseGet
        String value2 = empty.orElseGet(() -> "Computed Default");
        System.out.println("OrElseGet: " + value2);

        // orElseThrow
        try {
            empty.orElseThrow(() -> new RuntimeException("Value missing"));
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // map
        Optional<Integer> length = present.map(String::length);
        System.out.println("Length: " + length.orElse(0));

        // filter
        Optional<String> filtered = present.filter(s -> s.length() > 3);
        System.out.println("Filtered: " + filtered.orElse("Too short"));
    }
}
