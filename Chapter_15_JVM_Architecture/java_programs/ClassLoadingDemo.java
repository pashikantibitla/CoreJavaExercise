public class ClassLoadingDemo {
    public static void main(String[] args) {
        // Bootstrap class loader (returns null for core classes)
        ClassLoader bootstrap = String.class.getClassLoader();
        System.out.println("String class loader: " + bootstrap);

        // Application class loader for user classes
        ClassLoader app = ClassLoadingDemo.class.getClassLoader();
        System.out.println("ClassLoadingDemo class loader: " + app);

        // Extension class loader (parent of application)
        ClassLoader ext = app.getParent();
        System.out.println("Parent of app loader (Extension): " + ext);

        // Demonstrate that array class loaders are same as element type
        ClassLoader arrayLoader = int[].class.getClassLoader();
        System.out.println("int[] class loader: " + arrayLoader);

        ClassLoader objArrayLoader = Object[].class.getClassLoader();
        System.out.println("Object[] class loader: " + objArrayLoader);
    }
}
