import java.util.concurrent.*;

/**
 * Demonstrates thread safety of ConcurrentHashMap.
 * Multiple threads write concurrently without synchronization.
 * Topics: multi-threading, thread-safe writes, size consistency
 */
public class ConcurrentHashMapThreadSafety {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        int threadCount = 5;
        int iterations = 1000;
        
        Thread[] threads = new Thread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < iterations; j++) {
                    int key = threadId * iterations + j;
                    map.put(key, key);
                }
            });
            threads[i].start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        System.out.println("Expected size: " + (threadCount * iterations));
        System.out.println("Actual size: " + map.size());
        
        if (map.size() == threadCount * iterations) {
            System.out.println("✅ ConcurrentHashMap is thread-safe!");
        } else {
            System.out.println("❌ Size mismatch!");
        }
    }
}
