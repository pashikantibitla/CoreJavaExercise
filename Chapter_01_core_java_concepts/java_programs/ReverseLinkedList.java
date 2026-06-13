public class ReverseLinkedList {
    private static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
        }
    }
    
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;  // Store next
            current.next = prev;   // Reverse pointer
            prev = current;        // Move prev
            current = next;        // Move current
        }
        
        return prev;  // New head
    }
    
    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        
        print(head);  // 1 2 3 4
        head = reverse(head);
        print(head);  // 4 3 2 1
    }
}
