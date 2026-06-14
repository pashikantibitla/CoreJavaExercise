public class GenericClassDemo<T> {
    private T value;
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
    
    public static void main(String[] args) {
        GenericClassDemo<String> strBox = new GenericClassDemo<>();
        strBox.setValue("Hello Generics");
        System.out.println(strBox.getValue());
        
        GenericClassDemo<Integer> intBox = new GenericClassDemo<>();
        intBox.setValue(100);
        System.out.println(intBox.getValue());
        
        GenericClassDemo<Double> doubleBox = new GenericClassDemo<>();
        doubleBox.setValue(99.99);
        System.out.println(doubleBox.getValue());
    }
}
