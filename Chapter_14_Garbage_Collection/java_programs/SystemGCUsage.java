/**
 * Demonstrates System.gc() and Runtime.gc() usage.
 * Topics: GC request, memory monitoring, Runtime.getRuntime().freeMemory()
 */
public class SystemGCUsage {
    public static void main(String[] args) {
        System.out.println("=== System.gc() Demo ===");
        
        Runtime runtime = Runtime.getRuntime();
        
        System.out.println("Max Memory: " + format(runtime.maxMemory()));
        System.out.println("Total Memory: " + format(runtime.totalMemory()));
        System.out.println("Free Memory (before): " + format(runtime.freeMemory()));
        
        // Create many objects
        for (int i = 0; i < 100000; i++) {
            new String("Object" + i);
        }
        
        System.out.println("Free Memory (after creation): " + format(runtime.freeMemory()));
        
        // Request GC
        System.gc();
        
        System.out.println("Free Memory (after System.gc()): " + format(runtime.freeMemory()));
        
        System.out.println("\n=== Runtime.gc() Demo ===");
        runtime.gc();  // Same as System.gc()
        System.out.println("Runtime.gc() called. JVM may or may not run GC immediately.");
    }
    
    static String format(long bytes) {
        return (bytes / 1024 / 1024) + " MB";
    }
}
