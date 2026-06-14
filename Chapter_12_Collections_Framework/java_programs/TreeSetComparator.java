import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetComparator {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("Banana");
        set.add("Apple");
        set.add("Cherry");
        System.out.println("Natural order: " + set);
        
        TreeSet<String> reverseSet = new TreeSet<>(Comparator.reverseOrder());
        reverseSet.add("Banana");
        reverseSet.add("Apple");
        reverseSet.add("Cherry");
        System.out.println("Reverse order: " + reverseSet);
        
        TreeSet<String> lengthSet = new TreeSet<>(Comparator.comparingInt(String::length));
        lengthSet.add("Banana");
        lengthSet.add("Apple");
        lengthSet.add("Cherry");
        lengthSet.add("Kiwi");
        System.out.println("By length: " + lengthSet);
    }
}
