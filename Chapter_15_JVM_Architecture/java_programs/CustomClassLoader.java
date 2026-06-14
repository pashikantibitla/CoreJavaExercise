import java.io.*;

public class CustomClassLoader extends ClassLoader {

    public CustomClassLoader() {
        super();
    }

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("CustomClassLoader.loadClass: " + name);
        // Delegation model: parent loads first
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("CustomClassLoader.findClass: " + name);
        // In a real scenario, load bytes from a custom source
        // Here we just delegate to the parent for simplicity
        return super.findClass(name);
    }

    public static void main(String[] args) {
        CustomClassLoader loader = new CustomClassLoader();
        try {
            Class<?> clazz = loader.loadClass("java.lang.String");
            System.out.println("Loaded: " + clazz.getName() + " via " + clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
