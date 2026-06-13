public class Counter {
    private static int staticCount = 0;
    private int instanceCount = 0;
    
    public Counter() {
        staticCount++;
        instanceCount++;
    }
    
    public static int getStaticCount() {
        return staticCount;
    }
    
    public int getInstanceCount() {
        return instanceCount;
    }
    
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        
        System.out.println("Static: " + Counter.getStaticCount());    // 3
        System.out.println("Instance c1: " + c1.getInstanceCount());  // 1
        System.out.println("Instance c2: " + c2.getInstanceCount());  // 1
        System.out.println("Instance c3: " + c3.getInstanceCount());  // 1
    }
}
