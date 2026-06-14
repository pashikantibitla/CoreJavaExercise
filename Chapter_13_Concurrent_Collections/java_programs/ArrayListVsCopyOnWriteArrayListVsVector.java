import java.util.*;
import java.util.concurrent.*;

/**
 * Compares ArrayList, CopyOnWriteArrayList, synchronizedList, and Vector.
 * Topics: thread safety, iterator behavior, performance characteristics
 */
public class ArrayListVsCopyOnWriteArrayListVsVector {
    public static void main(String[] args) throws InterruptedException {
        // 1. ArrayList (NOT thread-safe)
        System.out.println("=== ArrayList (NOT thread-safe) ===");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        System.out.println("ArrayList: " + arrayList);
        
        // 2. CopyOnWriteArrayList (thread-safe, read-heavy)
        System.out.println("\n=== CopyOnWriteArrayList ===");
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("A");
        cowList.add("B");
        cowList.add("C");
        
        Iterator<String> cowIterator = cowList.iterator();
        cowList.add("D");
        System.out.print("Iterator after add: ");
        while (cowIterator.hasNext()) {
            System.out.print(cowIterator.next() + " ");  // Old snapshot: A B C
        }
        System.out.println("\nList after add: " + cowList);
        
        // 3. synchronizedList (coarse lock)
        System.out.println("\n=== Collections.synchronizedList ===");
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("A");
        syncList.add("B");
        System.out.println("synchronizedList: " + syncList);
        
        // 4. Vector (legacy)
        System.out.println("\n=== Vector (legacy) ===");
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.addElement("C");
        System.out.println("Vector: " + vector);
        
        // Thread safety comparison
        System.out.println("\n=== Thread Safety Test ===");
        testThreadSafety(new CopyOnWriteArrayList<>(), "CopyOnWriteArrayList");
        testThreadSafety(new Vector<>(), "Vector");
    }
    
    private static void testThreadSafety(List<String> list, String name) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(name + "-" + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(name + "-" + i);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(name + " size after 2 threads: " + list.size());
    }
}
