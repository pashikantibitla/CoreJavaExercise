import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        
        ListIterator<String> iterator = list.listIterator();
        
        System.out.println("Forward traversal:");
        while (iterator.hasNext()) {
            System.out.println(iterator.nextIndex() + ": " + iterator.next());
        }
        
        System.out.println("Backward traversal:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previousIndex() + ": " + iterator.previous());
        }
        
        iterator = list.listIterator(2);
        iterator.add("X");
        System.out.println("After add at index 2: " + list);
        
        iterator.next();
        iterator.remove();
        System.out.println("After remove: " + list);
    }
}
