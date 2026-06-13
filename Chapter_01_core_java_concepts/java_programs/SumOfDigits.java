public class SumOfDigits {
    public static int sum(int num) {
        int sum = 0;
        
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println(sum(123));  // 6
    }
}
