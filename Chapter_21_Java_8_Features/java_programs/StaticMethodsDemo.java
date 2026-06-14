interface Utility {
    static void printHello() {
        System.out.println("Hello from static method!");
    }

    static int add(int a, int b) {
        return a + b;
    }

    static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}

public class StaticMethodsDemo {
    public static void main(String[] args) {
        // Call static methods directly on interface
        Utility.printHello();
        System.out.println("Sum: " + Utility.add(5, 3));
        System.out.println("Is empty? " + Utility.isEmpty(""));
        System.out.println("Is empty? " + Utility.isEmpty("test"));
    }
}
