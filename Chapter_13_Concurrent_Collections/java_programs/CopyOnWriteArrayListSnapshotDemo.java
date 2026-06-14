import java.util.*;
import java.util.concurrent.*;

/**
 * Interview-level problem: Read-Write problem with CopyOnWriteArrayList.
 * Demonstrates snapshot iterator behavior vs real-time list state.
 */
public class CopyOnWriteArrayListSnapshotDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Create iterator (snapshot)
        Iterator<String> it = list.iterator();
        
        // Modify list after iterator creation
        list.add("D");
        list.remove("A");
        
        System.out.println("Iterator sees old snapshot:");
        while (it.hasNext()) {
            System.out.println("  " + it.next());  // A, B, C
        }
        
        System.out.println("List current state: " + list);  // [B, C, D]
        
        // New iterator sees current state
        System.out.println("New iterator sees current state:");
        for (String s : list) {
            System.out.println("  " + s);  // B, C, D
        }
    }
}
