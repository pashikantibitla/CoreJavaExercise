public class StringMethodsDemo {
    public static void main(String[] args) {
        String s = "  Hello World  ";

        System.out.println("Original: '" + s + "'");
        System.out.println("charAt(1): '" + s.charAt(1) + "'");
        System.out.println("length(): " + s.length());
        System.out.println("substring(2, 7): '" + s.substring(2, 7) + "'");
        System.out.println("toLowerCase(): '" + s.toLowerCase() + "'");
        System.out.println("toUpperCase(): '" + s.toUpperCase() + "'");
        System.out.println("trim(): '" + s.trim() + "'");
        System.out.println("replace('l', 'x'): '" + s.replace('l', 'x') + "'");
        System.out.println("contains(\"World\"): " + s.contains("World"));
        System.out.println("indexOf('l'): " + s.indexOf('l'));
        System.out.println("lastIndexOf('l'): " + s.lastIndexOf('l'));
        System.out.println("startsWith(\"Hello\"): " + s.trim().startsWith("Hello"));
        System.out.println("endsWith(\"World\"): " + s.trim().endsWith("World"));
        System.out.println("equals(\"Hello World\"): " + s.trim().equals("Hello World"));
        System.out.println("equalsIgnoreCase(\"hello world\"): " + s.trim().equalsIgnoreCase("hello world"));

        String[] parts = s.trim().split(" ");
        System.out.println("split(\" \"): " + java.util.Arrays.toString(parts));

        System.out.println("concat(\"!\"): '" + s.trim().concat("!") + "'");
        System.out.println("isEmpty(): " + "".isEmpty());
        System.out.println("valueOf(123): '" + String.valueOf(123) + "'");
        System.out.println("compareTo(\"apple\"): " + "banana".compareTo("apple"));
    }
}
