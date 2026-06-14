import java.util.concurrent.*;

/**
 * Demonstrates ConcurrentHashMap Java 8+ parallel methods.
 * Topics: forEach, search, reduce, mappingCount
 */
public class ConcurrentHashMapParallelMethods {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        
        // forEach with parallelism threshold
        System.out.println("=== forEach ===");
        map.forEach(1, (k, v) -> System.out.println(k + "=" + v));
        
        // search
        System.out.println("\n=== search ===");
        String key = map.search(1, (k, v) -> v > 3 ? k : null);
        System.out.println("First key with value > 3: " + key);
        
        // searchValues
        Integer value = map.searchValues(1, v -> v > 3 ? v : null);
        System.out.println("First value > 3: " + value);
        
        // reduce
        System.out.println("\n=== reduce ===");
        Integer sum = map.reduceValues(1, (v1, v2) -> v1 + v2);
        System.out.println("Sum of all values: " + sum);
        
        // reduce with transformer
        Long longSum = map.reduceValuesToLong(1, v -> (long) v, 0, Long::sum);
        System.out.println("Sum as long: " + longSum);
        
        // mappingCount
        System.out.println("\nMapping count: " + map.mappingCount());
    }
}
