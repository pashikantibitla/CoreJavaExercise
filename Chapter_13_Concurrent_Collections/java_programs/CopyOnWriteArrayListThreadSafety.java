import java.util.concurrent.*;

/**
 * Demonstrates CopyOnWriteArrayList thread safety.
 * One thread iterates while another modifies.
 * No ConcurrentModificationException.
 */
public class CopyOnWriteArrayListThreadSafety {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Thread 1: Iterate with delay
        Thread reader = new Thread(() -> {
            System.out.println("Reader started.");
            for (String s : list) {
                System.out.println("Reading: " + s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Reader finished.");
        });
        
        // Thread 2: Add elements while reading
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            list.add("D");
            System.out.println("Writer added D.");
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            list.add("E");
            System.out.println("Writer added E.");
        });
        
        reader.start();
        writer.start();
        reader.join();
        writer.join();
        
        System.out.println("Final list: " + list);
        System.out.println("✅ No ConcurrentModificationException!");
    }
}
