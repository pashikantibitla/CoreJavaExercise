import java.util.ArrayList;
import java.util.List;

public class WildcardUpperDemo {
    
    public static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number num : list) {
            total += num.doubleValue();
        }
        return total;
    }
    
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(2.5);
        
        System.out.println("Sum of Integers: " + sum(intList));
        System.out.println("Sum of Doubles: " + sum(doubleList));
    }
}
