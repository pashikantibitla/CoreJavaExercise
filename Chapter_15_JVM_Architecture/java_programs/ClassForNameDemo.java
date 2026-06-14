public class ClassForNameDemo {
    static {
        System.out.println("ClassForNameDemo class initialized!");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Before Class.forName()");

        // Triggers loading, linking, and initialization
        Class<?> clazz = Class.forName("ClassForNameDemo");
        System.out.println("After Class.forName()");
        System.out.println("Class name: " + clazz.getName());

        // Class.forName() with initialize=false
        Class<?> clazz2 = Class.forName("java.util.ArrayList", false,
                ClassLoader.getSystemClassLoader());
        System.out.println("ArrayList loaded (no init): " + clazz2.getName());
    }
}
