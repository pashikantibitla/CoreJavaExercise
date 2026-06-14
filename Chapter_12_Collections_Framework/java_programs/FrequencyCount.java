import java.util.HashMap;
import java.util.Map;

public class FrequencyCount {
    public static void main(String[] args) {
        String[] fruits = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        
        Map<String, Integer> frequency = new HashMap<>();
        
        for (String fruit : fruits) {
            frequency.put(fruit, frequency.getOrDefault(fruit, 0) + 1);
        }
        
        System.out.println("Frequency map: " + frequency);
        
        String mostFrequent = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        System.out.println("Most frequent: " + mostFrequent + " (" + maxCount + " times)");
    }
}
