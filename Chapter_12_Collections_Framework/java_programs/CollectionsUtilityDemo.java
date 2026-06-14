import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsUtilityDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(10);
        list.add(50);
        list.add(20);
        list.add(40);
        
        Collections.sort(list);
        System.out.println("Sorted: " + list);
        
        Collections.reverse(list);
        System.out.println("Reversed: " + list);
        
        Collections.shuffle(list);
        System.out.println("Shuffled: " + list);
        
        Collections.sort(list);
        int index = Collections.binarySearch(list, 30);
        System.out.println("Index of 30: " + index);
        
        System.out.println("Max: " + Collections.max(list));
        System.out.println("Min: " + Collections.min(list));
        
        list.add(10);
        list.add(10);
        System.out.println("Frequency of 10: " + Collections.frequency(list, 10));
        
        Collections.fill(list, 0);
        System.out.println("After fill: " + list);
        
        List<String> single = Collections.singletonList("Only");
        System.out.println("Singleton: " + single);
    }
}
