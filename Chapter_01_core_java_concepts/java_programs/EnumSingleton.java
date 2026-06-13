public enum EnumSingleton {
    INSTANCE;
    
    private int value;
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public void doSomething() {
        System.out.println("Doing something: " + value);
    }
    
    public static void main(String[] args) {
        EnumSingleton.INSTANCE.setValue(42);
        EnumSingleton.INSTANCE.doSomething();  // Doing something: 42
        
        // Thread-safe, serialization-safe, reflection-safe
        System.out.println(EnumSingleton.INSTANCE == EnumSingleton.INSTANCE);  // true
    }
}
