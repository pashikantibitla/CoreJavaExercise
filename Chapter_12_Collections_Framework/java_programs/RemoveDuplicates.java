import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("apple");
        list.add("cherry");
        list.add("banana");
        
        System.out.println("Original list: " + list);
        
        // Using LinkedHashSet to preserve order
        Set<String> set = new LinkedHashSet<>(list);
        List<String> uniqueList = new ArrayList<>(set);
        
        System.out.println("After removing duplicates: " + uniqueList);
        
        // Java 8+ stream approach
        List<String> streamUnique = list.stream().distinct().toList();
        System.out.println("Using stream distinct: " + streamUnique);
    }
}
