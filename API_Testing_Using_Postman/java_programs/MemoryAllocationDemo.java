package java_programs;

/**
 * MemoryAllocationDemo.java
 * 
 * Purpose: Demonstrates JVM memory allocation for POJO objects.
 *          Shows how objects are allocated in Heap, references in Stack,
 *          and String literals in the String Pool.
 * 
 * Memory Areas demonstrated:
 * - Stack: Local variables and references (LIFO, fast, per-thread)
 * - Heap: Object instances (Eden -> Survivor -> Old Generation)
 * - Metaspace: Class metadata (EmployeePOJO.class, methods, fields)
 * - String Pool: String literals (reused across application)
 * 
 * ASCII diagrams show the relationship between stack references and heap objects.
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class MemoryAllocationDemo {
    
    public static void main(String[] args) {
        System.out.println("=== JVM MEMORY ALLOCATION FOR POJO OBJECTS ===\n");
        
        // ============================================================
        // PART 1: Stack vs Heap allocation
        // ============================================================
        
        System.out.println("--- Part 1: Stack vs Heap ---\n");
        
        // Local variable 'emp' is stored in STACK (reference)
        // EmployeePOJO object is stored in HEAP (Eden space)
        EmployeePOJO emp = new EmployeePOJO(1, "John", 50000.0, "Engineering", true);
        
        System.out.println("Created EmployeePOJO object:");
        System.out.println("  Stack: emp (reference variable)");
        System.out.println("  Heap:  EmployeePOJO object with id=1, name=John, salary=50000.0");
        System.out.println("  emp.toString(): " + emp);
        
        // ============================================================
        // PART 2: String Pool demonstration
        // ============================================================
        
        System.out.println("\n--- Part 2: String Pool ---\n");
        
        String name1 = "John";      // Created in String Pool
        String name2 = "John";      // Reused from String Pool (same reference)
        String name3 = new String("John"); // Created in Heap (new object)
        
        System.out.println("name1 == name2: " + (name1 == name2) + " (same reference in String Pool)");
        System.out.println("name1 == name3: " + (name1 == name3) + " (different object in Heap)");
        System.out.println("name1.equals(name3): " + name1.equals(name3) + " (same content)");
        
        // ============================================================
        // PART 3: Nested object references
        // ============================================================
        
        System.out.println("\n--- Part 3: Nested Object References ---\n");
        
        EmployeePOJO empA = new EmployeePOJO(1, "Alice", 60000.0, "Engineering", true);
        EmployeePOJO empB = new EmployeePOJO(2, "Bob", 55000.0, "Sales", true);
        EmployeePOJO empC = empA; // empC points to same object as empA
        
        System.out.println("empA == empB: " + (empA == empB) + " (different objects)");
        System.out.println("empA == empC: " + (empA == empC) + " (same object, two references)");
        
        empC.setName("Alice Modified");
        System.out.println("After modifying via empC:");
        System.out.println("  empA.getName(): " + empA.getName() + " (also changed — same object)");
        
        // ============================================================
        // PART 4: Memory layout ASCII diagram
        // ============================================================
        
        System.out.println("\n--- Part 4: Memory Layout Diagram ---\n");
        
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  STACK (Local Variables)                     │");
        System.out.println("│  ┌─────────────────────────────────────┐     │");
        System.out.println("│  │ empA  ->  reference: 0x1000      │     │");
        System.out.println("│  │ empB  ->  reference: 0x2000      │     │");
        System.out.println("│  │ empC  ->  reference: 0x1000      │     │");
        System.out.println("│  │ (same as empA)                    │     │");
        System.out.println("│  └─────────────────────────────────────┘     │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  HEAP (Objects)                            │");
        System.out.println("│  ┌─────────────────────────────────────┐     │");
        System.out.println("│  │ 0x1000: EmployeePOJO              │     │");
        System.out.println("│  │   id: 1                            │     │");
        System.out.println("│  │   name: -> \"Alice Modified\" (String Pool) │");
        System.out.println("│  │   salary: 60000.0 (primitive)      │     │");
        System.out.println("│  │   department: -> \"Engineering\"     │     │");
        System.out.println("│  │   active: true (primitive)         │     │");
        System.out.println("│  └─────────────────────────────────────┘     │");
        System.out.println("│  ┌─────────────────────────────────────┐     │");
        System.out.println("│  │ 0x2000: EmployeePOJO              │     │");
        System.out.println("│  │   id: 2                            │     │");
        System.out.println("│  │   name: -> \"Bob\" (String Pool)    │     │");
        System.out.println("│  │   salary: 55000.0                  │     │");
        System.out.println("│  │   department: -> \"Sales\"           │     │");
        System.out.println("│  │   active: true                     │     │");
        System.out.println("│  └─────────────────────────────────────┘     │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  STRING POOL (in Heap)                     │");
        System.out.println("│  ┌─────────────────────────────────────┐     │");
        System.out.println("│  │ \"Alice Modified\"                    │     │");
        System.out.println("│  │ \"Bob\"                               │     │");
        System.out.println("│  │ \"Engineering\"                       │     │");
        System.out.println("│  │ \"Sales\"                             │     │");
        System.out.println("│  │ \"John\"                              │     │");
        System.out.println("│  └─────────────────────────────────────┘     │");
        System.out.println("└─────────────────────────────────────────────┘");
        
        // ============================================================
        // PART 5: Garbage Collection eligibility
        // ============================================================
        
        System.out.println("\n--- Part 5: Garbage Collection Eligibility ---\n");
        
        EmployeePOJO temp = new EmployeePOJO(99, "Temp", 1000.0, "Temp", false);
        System.out.println("Created temp object: " + temp);
        
        temp = null; // Object at this reference is now eligible for GC
        System.out.println("Set temp = null — object is now eligible for GC");
        System.out.println("(GC will reclaim the memory when it runs next)");
        
        // Suggest GC (not guaranteed to run immediately)
        System.gc();
        System.out.println("System.gc() called — JVM may or may not run GC now");
        
        // ============================================================
        // PART 6: Runtime memory stats
        // ============================================================
        
        System.out.println("\n--- Part 6: Runtime Memory Statistics ---\n");
        
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Max Memory:  " + maxMemory / 1024 / 1024 + " MB");
        System.out.println("Total Memory:" + totalMemory / 1024 / 1024 + " MB");
        System.out.println("Free Memory: " + freeMemory / 1024 / 1024 + " MB");
        System.out.println("Used Memory: " + usedMemory / 1024 / 1024 + " MB");
        
        System.out.println("\n=== Memory Allocation Demo Complete ===");
    }
}
