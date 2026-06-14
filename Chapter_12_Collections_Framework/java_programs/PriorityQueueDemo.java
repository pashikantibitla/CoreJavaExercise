import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        Queue<Integer> pq = new PriorityQueue<>();
        
        pq.offer(30);
        pq.offer(10);
        pq.offer(50);
        pq.offer(20);
        
        System.out.println("PriorityQueue: " + pq);
        
        System.out.println("Poll: " + pq.poll());
        System.out.println("Poll: " + pq.poll());
        System.out.println("Poll: " + pq.poll());
        System.out.println("Poll: " + pq.poll());
        
        Queue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);
        maxPq.offer(30);
        maxPq.offer(10);
        maxPq.offer(50);
        
        System.out.println("Max Poll: " + maxPq.poll());
    }
}
