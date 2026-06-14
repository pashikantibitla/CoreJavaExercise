import java.util.*;
import java.util.concurrent.*;

/**
 * Compares HashMap, ConcurrentHashMap, synchronizedMap, and Hashtable.
 * Topics: thread safety, null support, iterator behavior, performance
 */
public class HashMapVsConcurrentHashMapComparison {
    public static void main(String[] args) {
        System.out.println("=== 1. HashMap (NOT thread-safe) ===");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null, 0);
        hashMap.put("A", null);
        System.out.println("HashMap allows null key and value: " + hashMap);
        
        System.out.println("\n=== 2. ConcurrentHashMap (thread-safe) ===");
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        try {
            chm.put(null, 0);
        } catch (NullPointerException e) {
            System.out.println("ConcurrentHashMap does NOT allow null key: NullPointerException");
        }
        try {
            chm.put("A", null);
        } catch (NullPointerException e) {
            System.out.println("ConcurrentHashMap does NOT allow null value: NullPointerException");
        }
        chm.put("A", 1);
        System.out.println("ConcurrentHashMap: " + chm);
        
        System.out.println("\n=== 3. Collections.synchronizedMap ===");
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put(null, 0);
        syncMap.put("A", null);
        System.out.println("synchronizedMap allows null key and value: " + syncMap);
        
        System.out.println("\n=== 4. Hashtable (legacy, thread-safe) ===");
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        try {
            hashtable.put(null, 0);
        } catch (NullPointerException e) {
            System.out.println("Hashtable does NOT allow null key: NullPointerException");
        }
        try {
            hashtable.put("A", null);
        } catch (NullPointerException e) {
            System.out.println("Hashtable does NOT allow null value: NullPointerException");
        }
        hashtable.put("A", 1);
        System.out.println("Hashtable: " + hashtable);
        
        System.out.println("\n=== Iterator Behavior ===");
        ConcurrentHashMap<String, Integer> chm2 = new ConcurrentHashMap<>();
        chm2.put("A", 1);
        chm2.put("B", 2);
        
        for (String key : chm2.keySet()) {
            System.out.println("Iterating: " + key);
            chm2.put("C", 3);  // No ConcurrentModificationException!
        }
        System.out.println("ConcurrentHashMap iterator is weakly consistent (no CME)");
    }
}
