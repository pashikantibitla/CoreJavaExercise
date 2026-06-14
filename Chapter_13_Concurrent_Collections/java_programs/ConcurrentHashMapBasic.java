import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasic {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        // put, get, remove
        map.put("A", 1);
        map.put("B", 2);
        System.out.println("Get A: " + map.get("A"));
        System.out.println("Remove B: " + map.remove("B"));
        
        // putIfAbsent
        map.putIfAbsent("A", 100);
        System.out.println("After putIfAbsent A=100: " + map.get("A"));
        
        // containsKey, containsValue
        System.out.println("Contains A: " + map.containsKey("A"));
        System.out.println("Contains value 1: " + map.containsValue(1));
        
        // forEach
        map.put("C", 3);
        map.forEach((k, v) -> System.out.println(k + "=" + v));
        
        // keySet, values, entrySet
        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());
        System.out.println("Entries: " + map.entrySet());
    }
}
