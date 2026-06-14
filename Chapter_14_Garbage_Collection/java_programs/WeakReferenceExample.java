import java.lang.ref.WeakReference;

/**
 * Demonstrates WeakReference.
 * Topics: weak reference, GC clears weak reference, WeakHashMap concept.
 */
public class WeakReferenceExample {
    public static void main(String[] args) {
        System.out.println("=== WeakReference Demo ===");
        
        String strongRef = new String("Strong");
        WeakReference<String> weakRef = new WeakReference<>(new String("Weak"));
        
        System.out.println("Before GC:");
        System.out.println("  Strong ref: " + strongRef);
        System.out.println("  Weak ref: " + weakRef.get());
        
        System.gc();  // Suggest GC
        
        System.out.println("\nAfter GC:");
        System.out.println("  Strong ref: " + strongRef);  // Still alive
        System.out.println("  Weak ref: " + weakRef.get());  // Likely null
        
        System.out.println("\n=== WeakHashMap Concept ===");
        java.util.WeakHashMap<String, String> map = new java.util.WeakHashMap<>();
        String key = new String("TempKey");
        map.put(key, "TempValue");
        
        System.out.println("Before GC: " + map);
        
        key = null;  // Remove strong reference
        System.gc();
        
        System.out.println("After GC (key may be GC'd, entry removed): " + map);
    }
}
