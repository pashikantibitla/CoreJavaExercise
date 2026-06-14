import java.util.*;
import java.util.concurrent.*;

/**
 * Interview-level problem: Concurrent cache using ConcurrentHashMap.
 * Demonstrates computeIfAbsent for lazy loading.
 */
public class ConcurrentCacheDemo {
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    
    public String getData(String key) {
        return cache.computeIfAbsent(key, this::loadFromDatabase);
    }
    
    private String loadFromDatabase(String key) {
        System.out.println("Loading from DB for key: " + key);
        try {
            Thread.sleep(100);  // Simulate DB delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Value-of-" + key;
    }
    
    public static void main(String[] args) throws InterruptedException {
        ConcurrentCacheDemo demo = new ConcurrentCacheDemo();
        
        // Multiple threads request same key
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                String value = demo.getData("user-1");
                System.out.println("Thread " + Thread.currentThread().getName() + " got: " + value);
            });
            threads[i].start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        System.out.println("\nCache contents: " + demo.cache);
        System.out.println("Note: Database loaded only once due to atomic computeIfAbsent!");
    }
}
