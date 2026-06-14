import java.util.ArrayList;
import java.util.List;

public class WildcardLowerDemo {
    
    public static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
    }
    
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        addIntegers(intList);
        System.out.println("Integer List: " + intList);
        
        List<Number> numList = new ArrayList<>();
        addIntegers(numList);
        System.out.println("Number List: " + numList);
        
        List<Object> objList = new ArrayList<>();
        addIntegers(objList);
        System.out.println("Object List: " + objList);
    }
}
