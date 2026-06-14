public class GenericMethodDemo {
    
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    
    public <T> T getFirst(T[] array) {
        return array[0];
    }
    
    public <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }
    
    public <T extends Number> double sum(T[] numbers) {
        double total = 0;
        for (T num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }
    
    public static void main(String[] args) {
        GenericMethodDemo demo = new GenericMethodDemo();
        
        String[] strArr = {"A", "B", "C"};
        demo.printArray(strArr);
        
        Integer[] intArr = {1, 2, 3};
        demo.printArray(intArr);
        
        System.out.println("First: " + demo.getFirst(intArr));
        
        demo.printPair("Hello", 100);
        
        Double[] doubles = {1.5, 2.5, 3.5};
        System.out.println("Sum: " + demo.sum(doubles));
    }
}
