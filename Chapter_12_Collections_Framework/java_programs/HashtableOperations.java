import java.util.Hashtable;
import java.util.Map;

public class HashtableOperations {
    public static void main(String[] args) {
        Map<String, Integer> table = new Hashtable<>();
        
        table.put("One", 1);
        table.put("Two", 2);
        table.put("Three", 3);
        
        System.out.println("Hashtable: " + table);
        System.out.println("Size: " + table.size());
        System.out.println("Keys: " + table.keySet());
        
        // table.put(null, 4); // NullPointerException
        // table.put("Four", null); // NullPointerException
    }
}
