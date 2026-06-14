import java.util.concurrent.*;
import java.util.*;

/**
 * Demonstrates CopyOnWriteArrayList basic operations.
 * Topics: add, remove, set, get, contains, iterator
 */
public class CopyOnWriteArrayListOperations {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        
        // add
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("After add: " + list);
        
        // addIfAbsent
        boolean added = list.addIfAbsent("Apple");  // false
        System.out.println("Add 'Apple' again? " + added);
        list.addIfAbsent("Date");
        System.out.println("After addIfAbsent: " + list);
        
        // addAllAbsent
        list.addAllAbsent(Arrays.asList("Apple", "Elderberry", "Fig"));
        System.out.println("After addAllAbsent: " + list);
        
        // set
        list.set(0, "Apricot");
        System.out.println("After set(0, 'Apricot'): " + list);
        
        // remove
        list.remove("Banana");
        list.remove(0);
        System.out.println("After removes: " + list);
        
        // get
        System.out.println("Get(0): " + list.get(0));
        
        // contains, indexOf
        System.out.println("Contains 'Cherry': " + list.contains("Cherry"));
        System.out.println("IndexOf 'Cherry': " + list.indexOf("Cherry"));
        
        // size, isEmpty
        System.out.println("Size: " + list.size());
        System.out.println("IsEmpty: " + list.isEmpty());
        
        // toArray
        String[] arr = list.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(arr));
    }
}
