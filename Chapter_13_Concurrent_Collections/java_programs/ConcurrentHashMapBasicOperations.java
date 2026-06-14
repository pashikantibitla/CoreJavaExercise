import java.util.concurrent.*;

/**
 * Demonstrates basic CRUD operations on ConcurrentHashMap.
 * Topics: put, get, remove, containsKey, size, forEach
 */
public class ConcurrentHashMapBasicOperations {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // put
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        System.out.println("After put: " + map);
        
        // get
        System.out.println("Get 'One': " + map.get("One"));
        System.out.println("Get 'Unknown': " + map.get("Unknown"));
        
        // remove
        map.remove("Two");
        System.out.println("After remove 'Two': " + map);
        
        // containsKey / containsValue
        System.out.println("Contains 'One': " + map.containsKey("One"));
        System.out.println("Contains value 3: " + map.containsValue(3));
        
        // size, isEmpty
        System.out.println("Size: " + map.size());
        System.out.println("IsEmpty: " + map.isEmpty());
        
        // putIfAbsent
        map.putIfAbsent("One", 100);  // Ignored
        map.putIfAbsent("Four", 4);   // Added
        System.out.println("After putIfAbsent: " + map);
        
        // forEach
        map.forEach((k, v) -> System.out.println("Key=" + k + ", Value=" + v));
    }
}
