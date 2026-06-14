public class RuntimeClassDemo {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();

        // Memory info
        long freeMemory = rt.freeMemory();
        long totalMemory = rt.totalMemory();
        long maxMemory = rt.maxMemory();

        System.out.println("Free memory: " + freeMemory / (1024 * 1024) + " MB");
        System.out.println("Total memory: " + totalMemory / (1024 * 1024) + " MB");
        System.out.println("Max memory: " + maxMemory / (1024 * 1024) + " MB");

        // Available processors
        System.out.println("Available processors: " + rt.availableProcessors());

        // Suggest GC
        rt.gc();
        System.out.println("GC suggested.");

        // Execute command (platform dependent)
        // Uncomment below on appropriate OS
        // try {
        //     Process p = rt.exec("notepad.exe");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
