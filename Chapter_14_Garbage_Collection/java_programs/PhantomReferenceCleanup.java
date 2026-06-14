import java.lang.ref.*;

/**
 * Interview-level problem: Resource cleanup using PhantomReference.
 * Demonstrates tracking object destruction and performing cleanup.
 */
public class PhantomReferenceCleanup {
    private static final ReferenceQueue<Resource> queue = new ReferenceQueue<>();
    
    static class Resource {
        private final String name;
        Resource(String name) { this.name = name; }
        public String getName() { return name; }
    }
    
    static class CleanupTask extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Reference<?> ref = queue.remove();
                    System.out.println("Cleanup: Resource was finalized, performing cleanup action.");
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PhantomReference Cleanup Demo ===");
        
        CleanupTask cleanupTask = new CleanupTask();
        cleanupTask.setDaemon(true);
        cleanupTask.start();
        
        Resource res = new Resource("DatabaseConnection");
        PhantomReference<Resource> phantom = new PhantomReference<>(res, queue);
        
        System.out.println("Created resource: " + res.getName());
        
        res = null;  // Remove strong reference
        System.out.println("Strong reference removed.");
        
        System.gc();  // Suggest GC
        
        Thread.sleep(1000);
        System.out.println("Cleanup task should have detected finalization.");
    }
}
