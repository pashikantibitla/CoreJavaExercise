import java.util.concurrent.locks.*;

public class ReentrantLockDemo {
    private static final Lock lock = new ReentrantLock();
    private static int counter = 0;
    
    public static void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println("Counter: " + counter + 
                " by " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    increment();
                }
            }).start();
        }
    }
}
