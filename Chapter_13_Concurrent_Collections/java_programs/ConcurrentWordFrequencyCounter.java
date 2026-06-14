import java.util.concurrent.*;

/**
 * Interview-level problem: Word frequency counter using ConcurrentHashMap.merge().
 * Multiple threads count words concurrently using atomic merge.
 */
public class ConcurrentWordFrequencyCounter {
    private final ConcurrentHashMap<String, Integer> frequencyMap = new ConcurrentHashMap<>();
    
    public void addWord(String word) {
        frequencyMap.merge(word, 1, Integer::sum);
    }
    
    public void printFrequencies() {
        frequencyMap.forEach((word, count) -> 
            System.out.println(word + " => " + count));
    }
    
    public static void main(String[] args) throws InterruptedException {
        ConcurrentWordFrequencyCounter counter = new ConcurrentWordFrequencyCounter();
        String[] words = {
            "java", "python", "java", "c++", "java",
            "python", "java", "go", "java", "python",
            "rust", "java", "c++", "python", "java"
        };
        
        Thread[] threads = new Thread[words.length];
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            threads[i] = new Thread(() -> counter.addWord(word));
            threads[i].start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        System.out.println("Word frequencies:");
        counter.printFrequencies();
        
        System.out.println("\nExpected: java=7, python=4, c++=2, go=1, rust=1");
    }
}
