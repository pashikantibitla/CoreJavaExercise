public class StringImmutability {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = s1;

        System.out.println("Before modification:");
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s1 == s2: " + (s1 == s2));

        // Modifying s1 creates a new object
        s1 = s1 + " world";

        System.out.println("\nAfter modification:");
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s1 == s2: " + (s1 == s2));

        // String literal pool
        String a = "java";
        String b = "java";
        System.out.println("\nLiteral pool:");
        System.out.println("a == b: " + (a == b));

        // new keyword creates separate object
        String c = new String("java");
        System.out.println("a == c: " + (a == c));
        System.out.println("a.equals(c): " + a.equals(c));

        // intern()
        String d = c.intern();
        System.out.println("a == d (interned): " + (a == d));
    }
}
