/**
 * Demonstrates the finalize() method.
 * Topics: finalize() called before GC, object resurrection, deprecation notice.
 * Note: finalize() is deprecated since Java 9.
 */
public class FinalizeMethodDemo {
    static FinalizeMethodDemo obj;
    int id;
    
    FinalizeMethodDemo(int id) {
        this.id = id;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable {
        System.out.println("finalize() called for object id=" + id);
        // Cleanup code would go here
        super.finalize();
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== finalize() Demo ===");
        FinalizeMethodDemo obj1 = new FinalizeMethodDemo(1);
        obj1 = null;  // Eligible for GC
        
        System.gc();  // Request GC
        Thread.sleep(1000);
        System.out.println("After first GC request.");
        
        System.out.println("\n=== Object Resurrection Demo ===");
        FinalizeMethodDemo obj2 = new FinalizeMethodDemo(2);
        obj = obj2;  // Static reference
        obj2 = null; // Now only static reference holds it
        System.gc();
        Thread.sleep(1000);
        System.out.println("After GC with strong static reference: obj = " + obj);
        
        System.out.println("\n=== finalize() Called Only Once ===");
        obj = null;  // Now eligible for GC again
        System.gc();
        Thread.sleep(1000);
        System.out.println("Note: finalize() is called at most once per object.");
    }
}
