import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapOperations {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Zebra", 1);
        map.put("Apple", 2);
        map.put("Mango", 3);
        
        System.out.println("Insertion order: " + map);
        
        Map<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("A", 1);
        accessOrderMap.put("B", 2);
        accessOrderMap.put("C", 3);
        
        accessOrderMap.get("A");
        accessOrderMap.get("B");
        
        System.out.println("Access order: " + accessOrderMap);
    }
}
