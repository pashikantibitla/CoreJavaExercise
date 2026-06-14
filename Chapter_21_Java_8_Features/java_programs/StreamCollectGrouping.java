import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person {
    String name;
    String city;
    int age;

    Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() { return name; }
    public String getCity() { return city; }
    public int getAge() { return age; }
}

public class StreamCollectGrouping {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", "NY", 30),
            new Person("Bob", "LA", 25),
            new Person("Charlie", "NY", 35),
            new Person("David", "LA", 28)
        );

        // Collect to list
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("Names: " + names);

        // Grouping by city
        Map<String, List<Person>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));
        System.out.println("By city: " + byCity);

        // Counting by city
        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println("Count by city: " + countByCity);

        // Average age by city
        Map<String, Double> avgAgeByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity,
                        Collectors.averagingInt(Person::getAge)));
        System.out.println("Avg age by city: " + avgAgeByCity);

        // Partitioning by age > 28
        Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 28));
        System.out.println("Partitioned: " + partitioned);

        // Joining
        String joined = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);
    }
}
