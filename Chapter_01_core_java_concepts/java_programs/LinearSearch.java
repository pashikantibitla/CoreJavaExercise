public class LinearSearch {
    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;  // Found at index i
            }
        }
        return -1;  // Not found
    }
    
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 2, 9};
        System.out.println(search(arr, 8));  // 2
    }
}
