import java.util.*;

/**
 * Demonstrates a memory leak scenario.
 * Topics: static collection growing indefinitely, unremoved listeners.
 * Note: This is a demonstration. Do NOT do this in production.
 */
public class MemoryLeakExample {
    // Static collection never shrinks - memory leak!
    private static final List<String> leakyCache = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("=== Memory Leak Demo ===");
        System.out.println("Static collection grows and never shrinks.");
        
        Runtime runtime = Runtime.getRuntime();
        
        for (int i = 0; i < 100000; i++) {
            addToCache("Data" + i);
        }
        
        System.out.println("Cache size: " + leakyCache.size());
        System.out.println("Used memory: " + ((runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024) + " MB");
        
        System.out.println("\n=== How to Fix ===");
        System.out.println("1. Use WeakHashMap instead of ArrayList for caches");
        System.out.println("2. Remove entries when no longer needed");
        System.out.println("3. Use LRU cache with bounded size");
        System.out.println("4. Unregister listeners when done");
        System.out.println("5. Close resources with try-with-resources");
    }
    
    static void addToCache(String data) {
        leakyCache.add(data);  // Never removed! Memory leak!
    }
}
