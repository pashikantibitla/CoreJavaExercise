import java.lang.ref.*;

/**
 * Demonstrates PhantomReference.
 * Topics: phantom reference, ReferenceQueue, cleanup tracking.
 */
public class PhantomReferenceExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PhantomReference Demo ===");
        
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object obj = new Object();
        
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, queue);
        
        System.out.println("PhantomReference.get() always returns null: " + phantomRef.get());
        System.out.println("Object is still alive: " + obj);
        
        obj = null;  // Make object eligible for GC
        System.out.println("Strong reference removed.");
        
        System.gc();  // Suggest GC
        
        // Wait for reference to be enqueued
        Reference<?> ref = queue.poll();
        if (ref == phantomRef) {
            System.out.println("PhantomReference enqueued! Object is finalized.");
            System.out.println("Can perform cleanup action now.");
        } else {
            System.out.println("Reference not yet enqueued (GC may not have run).");
        }
        
        System.out.println("\nPhantomReference is used for:");
        System.out.println("  - Tracking when object is finalized");
        System.out.println("  - Performing cleanup after object destruction");
        System.out.println("  - Must be used with ReferenceQueue");
    }
}
