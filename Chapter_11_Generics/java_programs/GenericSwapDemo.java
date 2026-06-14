public class GenericSwapDemo {
    
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Before: ");
        for (int n : numbers) System.out.print(n + " ");
        
        swap(numbers, 1, 3);
        System.out.println("\nAfter: ");
        for (int n : numbers) System.out.print(n + " ");
        
        String[] words = {"apple", "banana", "cherry"};
        System.out.println("\nBefore: ");
        for (String s : words) System.out.print(s + " ");
        
        swap(words, 0, 2);
        System.out.println("\nAfter: ");
        for (String s : words) System.out.print(s + " ");
    }
}
