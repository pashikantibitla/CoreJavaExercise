import java.lang.ref.Cleaner;

/**
 * Interview-level problem: Modern resource cleanup using java.lang.ref.Cleaner.
 * Demonstrates Java 9+ Cleaner as a replacement for finalize().
 */
public class CleanerDemo {
    private static final Cleaner cleaner = Cleaner.create();
    
    private static class CleanupTask implements Runnable {
        private final String resourceName;
        
        CleanupTask(String resourceName) {
            this.resourceName = resourceName;
        }
        
        @Override
        public void run() {
            System.out.println("Cleaner: Cleaning up resource '" + resourceName + "'");
        }
    }
    
    static class ManagedResource {
        private final Cleaner.Cleanable cleanable;
        
        ManagedResource(String name) {
            this.cleanable = cleaner.register(this, new CleanupTask(name));
        }
        
        public void close() {
            cleanable.clean();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Cleaner Demo (Java 9+) ===");
        
        try (ManagedResource resource = new ManagedResource("FileHandle")) {
            System.out.println("Using resource...");
        }  // close() called automatically
        
        System.out.println("Resource closed.");
        
        System.out.println("\n=== Cleaner vs finalize() ===");
        System.out.println("Cleaner advantages:");
        System.out.println("  - More reliable than finalize()");
        System.out.println("  - No resurrection issues");
        System.out.println("  - Better performance");
        System.out.println("  - Explicit cleanup via close()");
    }
}
