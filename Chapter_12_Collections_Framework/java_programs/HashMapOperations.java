import java.util.HashMap;
import java.util.Map;

public class HashMapOperations {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);
        map.put("Alice", 26);
        
        System.out.println("Map: " + map);
        System.out.println("Alice's age: " + map.get("Alice"));
        System.out.println("Contains key 'Bob'? " + map.containsKey("Bob"));
        System.out.println("Contains value 30? " + map.containsValue(30));
        
        map.remove("Bob");
        System.out.println("After remove: " + map);
        
        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        map.forEach((k, v) -> System.out.println(k + " is " + v + " years old"));
        
        map.clear();
        System.out.println("Is empty? " + map.isEmpty());
    }
}
