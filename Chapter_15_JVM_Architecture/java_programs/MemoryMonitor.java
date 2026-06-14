public class MemoryMonitor {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("=== JVM Memory Info ===");
        System.out.println("Max Memory:    " + format(maxMemory));
        System.out.println("Total Memory:  " + format(totalMemory));
        System.out.println("Free Memory:   " + format(freeMemory));
        System.out.println("Used Memory:   " + format(usedMemory));

        // Allocate some objects
        System.out.println("\nAllocating objects...");
        String[] arr = new String[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new String("Object" + i);
        }

        long usedAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used after allocation: " + format(usedAfter));

        // Request GC
        System.out.println("Requesting GC...");
        runtime.gc();

        long freeAfterGC = runtime.freeMemory();
        System.out.println("Free after GC: " + format(freeAfterGC));
    }

    private static String format(long bytes) {
        return (bytes / 1024 / 1024) + " MB";
    }
}
