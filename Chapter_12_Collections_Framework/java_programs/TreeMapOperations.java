import java.util.Map;
import java.util.TreeMap;

public class TreeMapOperations {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("Banana", 2);
        map.put("Apple", 1);
        map.put("Cherry", 3);
        
        System.out.println("TreeMap (sorted by key): " + map);
        
        TreeMap<String, Integer> treeMap = new TreeMap<>(map);
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("HeadMap: " + treeMap.headMap("Cherry"));
        System.out.println("TailMap: " + treeMap.tailMap("Cherry"));
        
        TreeMap<String, Integer> descMap = new TreeMap<>((a, b) -> b.compareTo(a));
        descMap.putAll(map);
        System.out.println("Descending: " + descMap);
    }
}
