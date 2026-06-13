import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            new Thread(() -> {
                System.out.println("Task " + taskId + " started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                System.out.println("Task " + taskId + " completed");
                latch.countDown();
            }).start();
        }
        
        latch.await();
        System.out.println("All tasks completed");
    }
}
