public class StringBuilderOperations {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("Initial: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        System.out.println("Length: " + sb.length());

        sb.append(" World");
        System.out.println("\nAfter append: " + sb);

        sb.insert(5, " Java");
        System.out.println("After insert(5, \" Java\"): " + sb);

        sb.reverse();
        System.out.println("After reverse: " + sb);

        sb.reverse(); // back
        sb.delete(5, 10);
        System.out.println("After delete(5, 10): " + sb);

        sb.replace(0, 5, "Hi");
        System.out.println("After replace(0, 5, \"Hi\"): " + sb);

        sb.setCharAt(0, 'H');
        System.out.println("After setCharAt(0, 'H'): " + sb);

        sb.ensureCapacity(100);
        System.out.println("After ensureCapacity(100): " + sb.capacity());

        sb.trimToSize();
        System.out.println("After trimToSize(): " + sb.capacity());
    }
}
