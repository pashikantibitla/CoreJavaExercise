import java.util.HashSet;
import java.util.Set;

public class HashSetOperations {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");
        
        System.out.println("Set: " + set);
        System.out.println("Size: " + set.size());
        System.out.println("Contains Banana? " + set.contains("Banana"));
        
        set.remove("Banana");
        System.out.println("After remove: " + set);
        
        set.add(null);
        System.out.println("With null: " + set);
        
        Set<String> other = new HashSet<>();
        other.add("Apple");
        other.add("Date");
        set.retainAll(other);
        System.out.println("After retainAll: " + set);
    }
}
