import java.util.concurrent.*;
import java.util.*;

/**
 * Demonstrates CopyOnWriteArraySet basic operations.
 * Topics: add, remove, contains, no duplicates, iterator
 */
public class CopyOnWriteArraySetOperations {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        
        // add
        set.add("Java");
        set.add("Python");
        set.add("C++");
        System.out.println("After add: " + set);
        
        // No duplicates
        boolean added = set.add("Java");
        System.out.println("Add 'Java' again? " + added);
        System.out.println("Set after duplicate add: " + set);
        
        // remove
        set.remove("C++");
        System.out.println("After remove 'C++': " + set);
        
        // contains
        System.out.println("Contains 'Java': " + set.contains("Java"));
        System.out.println("Contains 'C++': " + set.contains("C++"));
        
        // size, isEmpty
        System.out.println("Size: " + set.size());
        System.out.println("IsEmpty: " + set.isEmpty());
        
        // toArray
        String[] arr = set.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(arr));
        
        // iterator
        System.out.println("Iterator:");
        for (String s : set) {
            System.out.println("  " + s);
        }
    }
}
