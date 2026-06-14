import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsBinarySearch {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        
        Collections.sort(list);
        
        int target = 30;
        int index = Collections.binarySearch(list, target);
        System.out.println("Index of " + target + ": " + index);
        
        target = 35;
        index = Collections.binarySearch(list, target);
        System.out.println("Index of " + target + ": " + index);
        System.out.println("Would be inserted at: " + (-index - 1));
        
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        
        int idx = Collections.binarySearch(words, "banana", String::compareTo);
        System.out.println("Index of banana: " + idx);
    }
}
