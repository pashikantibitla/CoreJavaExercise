import java.util.ArrayList;
import java.util.List;

public class TypeSafetyDemo {
    public static void main(String[] args) {
        // Without generics (raw type)
        List rawList = new ArrayList();
        rawList.add("Hello");
        rawList.add(10);
        
        // With generics (type-safe)
        List<String> genericList = new ArrayList<>();
        genericList.add("Hello");
        // genericList.add(10); // COMPILE-TIME ERROR
        
        String s = genericList.get(0); // No casting needed
        System.out.println("Type-safe value: " + s);
        
        // Runtime class check
        List<String> strList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        System.out.println("Same class? " + (strList.getClass() == intList.getClass()));
    }
}
