import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeating {
    public static void main(String[] args) {
        String str = "swiss";
        
        Map<Character, Integer> count = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        Character firstNonRepeating = null;
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                firstNonRepeating = entry.getKey();
                break;
            }
        }
        
        System.out.println("First non-repeating character in '" + str + "': " + firstNonRepeating);
    }
}
