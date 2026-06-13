public class MaxElement {
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 9, 1};
        System.out.println(findMax(arr));  // 9
    }
}
