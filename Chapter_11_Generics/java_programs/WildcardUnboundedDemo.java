import java.util.ArrayList;
import java.util.List;

public class WildcardUnboundedDemo {
    
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
    
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("World");
        
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        
        System.out.println("String List:");
        printList(strList);
        
        System.out.println("Integer List:");
        printList(intList);
    }
}
