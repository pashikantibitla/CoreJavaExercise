import java.lang.ref.SoftReference;

/**
 * Demonstrates SoftReference.
 * Topics: soft reference, memory-sensitive cache, GC clears when memory low.
 */
public class SoftReferenceExample {
    public static void main(String[] args) {
        System.out.println("=== SoftReference Demo ===");
        
        SoftReference<byte[]> softRef = new SoftReference<>(new byte[1024 * 1024 * 10]); // 10 MB
        
        System.out.println("Before GC: " + (softRef.get() != null ? "Available" : "Cleared"));
        
        System.gc();  // GC may not clear soft reference if memory is sufficient
        
        System.out.println("After GC (memory sufficient): " + (softRef.get() != null ? "Available" : "Cleared"));
        
        System.out.println("\n=== SoftReference Cache Concept ===");
        System.out.println("SoftReference is ideal for caches:");
        System.out.println("  - Kept as long as memory is sufficient");
        System.out.println("  - Cleared before OutOfMemoryError when memory is low");
        System.out.println("  - Prevents memory leaks while improving performance");
    }
}
