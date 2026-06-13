import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            return "Hello";
        });
        
        future.thenApply(s -> s + " World")
              .thenAccept(System.out::println);
        
        // Wait for completion
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { }
    }
}
