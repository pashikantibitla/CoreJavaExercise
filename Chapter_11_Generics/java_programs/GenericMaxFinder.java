public class GenericMaxFinder {
    
    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        Integer[] numbers = {1, 5, 3, 9, 2};
        System.out.println("Max Integer: " + findMax(numbers));
        
        String[] words = {"apple", "zebra", "mango"};
        System.out.println("Max String: " + findMax(words));
    }
}
