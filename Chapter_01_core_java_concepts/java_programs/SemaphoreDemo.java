import java.util.concurrent.*;

public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(3);
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Task " + taskId + 
                        " accessing resource by " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    System.out.println("Task " + taskId + " releasing");
                    semaphore.release();
                } catch (InterruptedException e) { }
            }).start();
        }
    }
}
