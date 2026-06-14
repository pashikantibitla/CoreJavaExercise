import java.util.LinkedList;

public class LinkedListOperations {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        
        list.addFirst("First");
        list.addLast("Last");
        list.add("Middle");
        
        System.out.println("LinkedList: " + list);
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        
        list.removeFirst();
        list.removeLast();
        System.out.println("After removes: " + list);
        
        list.offer("A");
        list.offer("B");
        System.out.println("Queue peek: " + list.peek());
        System.out.println("Queue poll: " + list.poll());
        
        list.push("X");
        list.push("Y");
        System.out.println("Stack pop: " + list.pop());
    }
}
