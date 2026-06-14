import java.util.ArrayList;
import java.util.List;

public class ArrayListOperations {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add(1, "Blueberry");
        
        System.out.println("List: " + list);
        System.out.println("First: " + list.get(0));
        System.out.println("Last: " + list.get(list.size() - 1));
        
        list.set(2, "Cranberry");
        System.out.println("After update: " + list);
        
        list.remove("Banana");
        list.remove(0);
        System.out.println("After remove: " + list);
        
        System.out.println("Index of Cherry: " + list.indexOf("Cherry"));
        System.out.println("Contains Apple? " + list.contains("Apple"));
        
        list.clear();
        System.out.println("Size after clear: " + list.size());
    }
}
