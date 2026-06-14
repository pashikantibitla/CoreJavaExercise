import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericCollectionsDemo {
    public static void main(String[] args) {
        // Generic List
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("Names: " + names);
        
        // Generic Map
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Generic method with collections
        printCollection(names);
    }
    
    public static <T> void printCollection(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}
