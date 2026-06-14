/**
 * Demonstrates object eligibility for Garbage Collection.
 * Topics: nullifying reference, reassigning reference, island of isolation
 */
public class ObjectEligibilityForGC {
    public static void main(String[] args) {
        System.out.println("=== 1. Nullifying Reference ===");
        String s1 = new String("Hello");
        System.out.println("s1 = " + s1);
        s1 = null;  // "Hello" object is now eligible for GC
        System.out.println("s1 set to null. Object is eligible for GC.");
        
        System.out.println("\n=== 2. Reassigning Reference ===");
        String s2 = new String("World");
        String s3 = new String("Java");
        s2 = s3;  // "World" object is now eligible for GC
        System.out.println("s2 reassigned to s3. 'World' object is eligible for GC.");
        
        System.out.println("\n=== 3. Object Out of Scope ===");
        createObject();
        System.out.println("After method returns, local object is eligible for GC.");
        
        System.out.println("\n=== 4. Island of Isolation ===");
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;  // Circular reference
        
        n1 = null;
        n2 = null;
        n3 = null;
        System.out.println("All external references removed.");
        System.out.println("Even though objects reference each other, they form an island of isolation.");
        System.out.println("All three objects are eligible for GC.");
    }
    
    static void createObject() {
        String local = new String("Local Object");
        System.out.println("Created local object: " + local);
        // When method ends, 'local' goes out of scope
    }
    
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }
}
