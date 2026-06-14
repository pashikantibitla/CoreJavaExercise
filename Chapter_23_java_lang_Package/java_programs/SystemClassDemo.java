public class SystemClassDemo {
    public static void main(String[] args) {
        // Current time
        long millis = System.currentTimeMillis();
        long nano = System.nanoTime();
        System.out.println("Current time millis: " + millis);
        System.out.println("Nano time: " + nano);

        // Environment variables
        String path = System.getenv("PATH");
        System.out.println("PATH length: " + (path != null ? path.length() : 0));

        // System properties
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS name: " + System.getProperty("os.name"));
        System.out.println("User name: " + System.getProperty("user.name"));
        System.out.println("Working dir: " + System.getProperty("user.dir"));

        // Array copy
        int[] src = {1, 2, 3, 4, 5};
        int[] dest = new int[5];
        System.arraycopy(src, 0, dest, 0, src.length);
        System.out.println("Copied array: " + java.util.Arrays.toString(dest));

        // Standard I/O
        System.out.println("Standard output");
        System.err.println("Standard error");

        // Suggest GC
        System.gc();
        System.out.println("GC suggested via System.gc()");
    }
}
