import java.util.*;
import java.util.concurrent.*;

/**
 * Interview-level problem: Concurrent HashMap vs synchronizedMap performance test.
 * Measures time for concurrent reads and writes.
 */
public class ConcurrentMapPerformanceTest {
    private static final int OPERATIONS = 100_000;
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ConcurrentHashMap vs synchronizedMap Performance Test");
        System.out.println("Operations: " + OPERATIONS);
        
        Map<String, Integer> chm = new ConcurrentHashMap<>();
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        
        long chmTime = testMap(chm, "ConcurrentHashMap");
        long syncTime = testMap(syncMap, "synchronizedMap");
        
        System.out.println("\nConcurrentHashMap time: " + chmTime + "ms");
        System.out.println("synchronizedMap time: " + syncTime + "ms");
        System.out.println("Winner: " + (chmTime < syncTime ? "ConcurrentHashMap" : "synchronizedMap"));
    }
    
    private static long testMap(Map<String, Integer> map, String name) throws InterruptedException {
        long start = System.currentTimeMillis();
        
        Thread writer = new Thread(() -> {
            for (int i = 0; i < OPERATIONS; i++) {
                map.put("key" + i, i);
            }
        });
        
        Thread reader = new Thread(() -> {
            for (int i = 0; i < OPERATIONS; i++) {
                map.get("key" + i);
            }
        });
        
        writer.start();
        reader.start();
        writer.join();
        reader.join();
        
        long end = System.currentTimeMillis();
        return end - start;
    }
}
