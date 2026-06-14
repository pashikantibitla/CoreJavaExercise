import java.util.concurrent.*;

/**
 * Demonstrates CopyOnWriteArraySet thread safety.
 * One thread iterates while another adds elements.
 */
public class CopyOnWriteArraySetThreadSafety {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("A");
        set.add("B");
        
        Thread reader = new Thread(() -> {
            System.out.println("Reader started.");
            for (String s : set) {
                System.out.println("Reading: " + s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Reader finished.");
        });
        
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            set.add("C");
            System.out.println("Writer added C.");
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            set.add("D");
            System.out.println("Writer added D.");
        });
        
        reader.start();
        writer.start();
        reader.join();
        writer.join();
        
        System.out.println("Final set: " + set);
        System.out.println("✅ No ConcurrentModificationException!");
    }
}
