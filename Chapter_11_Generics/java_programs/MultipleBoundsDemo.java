public class MultipleBoundsDemo<T extends Number & Comparable<T>> {
    private T value;
    
    public MultipleBoundsDemo(T value) {
        this.value = value;
    }
    
    public boolean isGreaterThan(T other) {
        return value.compareTo(other) > 0;
    }
    
    public static void main(String[] args) {
        MultipleBoundsDemo<Integer> bound = new MultipleBoundsDemo<>(100);
        System.out.println(bound.isGreaterThan(50));
        System.out.println(bound.isGreaterThan(200));
    }
}
