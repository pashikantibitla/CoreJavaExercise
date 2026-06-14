import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsSortComparator {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Cherry");
        
        Collections.sort(fruits);
        System.out.println("Natural order: " + fruits);
        
        Collections.sort(fruits, Collections.reverseOrder());
        System.out.println("Reverse order: " + fruits);
        
        Collections.sort(fruits, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("By length: " + fruits);
        
        fruits.sort(Comparator.naturalOrder());
        System.out.println("Using List.sort(): " + fruits);
    }
}
