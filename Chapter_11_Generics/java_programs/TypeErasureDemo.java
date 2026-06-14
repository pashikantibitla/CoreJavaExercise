import java.util.ArrayList;
import java.util.List;

public class TypeErasureDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        // At runtime, both are just ArrayList
        System.out.println("Same class? " + (stringList.getClass() == intList.getClass()));
        System.out.println("Class name: " + stringList.getClass().getName());
        
        // Type info is erased at runtime
        // List<String>[] array = new List<String>[10]; // COMPILE ERROR
        
        // instanceof with raw type only
        if (stringList instanceof ArrayList) {
            System.out.println("It's an ArrayList");
        }
    }
}
