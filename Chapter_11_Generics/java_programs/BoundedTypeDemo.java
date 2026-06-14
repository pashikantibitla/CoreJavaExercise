public class BoundedTypeDemo<T extends Number> {
    private T value;
    
    public BoundedTypeDemo(T value) {
        this.value = value;
    }
    
    public double getDoubleValue() {
        return value.doubleValue();
    }
    
    public static void main(String[] args) {
        BoundedTypeDemo<Integer> intVal = new BoundedTypeDemo<>(100);
        System.out.println(intVal.getDoubleValue());
        
        BoundedTypeDemo<Double> doubleVal = new BoundedTypeDemo<>(3.14);
        System.out.println(doubleVal.getDoubleValue());
        
        // BoundedTypeDemo<String> strVal = new BoundedTypeDemo<>("Hello"); // ERROR
    }
}
