import java.util.concurrent.*;

/**
 * Demonstrates ConcurrentHashMap atomic methods.
 * Topics: computeIfAbsent, computeIfPresent, compute, merge, replace
 */
public class ConcurrentHashMapAtomicMethods {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // computeIfAbsent
        map.computeIfAbsent("A", k -> k.length());
        System.out.println("After computeIfAbsent 'A': " + map);
        
        map.computeIfAbsent("A", k -> 100);  // Ignored because 'A' exists
        System.out.println("After computeIfAbsent 'A' again: " + map);
        
        // computeIfPresent
        map.computeIfPresent("A", (k, v) -> v + 10);
        System.out.println("After computeIfPresent 'A': " + map);
        
        map.computeIfPresent("B", (k, v) -> v + 10);  // Ignored because 'B' absent
        System.out.println("After computeIfPresent 'B' (absent): " + map);
        
        // compute
        map.compute("A", (k, v) -> v == null ? 0 : v * 2);
        System.out.println("After compute 'A' (double): " + map);
        
        map.compute("B", (k, v) -> v == null ? 0 : v * 2);  // Adds B with 0
        System.out.println("After compute 'B' (null case): " + map);
        
        // merge
        map.merge("A", 5, Integer::sum);
        System.out.println("After merge 'A' (+5): " + map);
        
        map.merge("C", 10, Integer::sum);  // Adds C with 10
        System.out.println("After merge 'C' (new): " + map);
        
        // replace
        boolean replaced = map.replace("A", 10, 100);  // false (A is not 10)
        System.out.println("Replace A(10->100): " + replaced);
        
        replaced = map.replace("A", 15, 100);  // true (A is 15)
        System.out.println("Replace A(15->100): " + replaced);
        System.out.println("Final map: " + map);
    }
}
