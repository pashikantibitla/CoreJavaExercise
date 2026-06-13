public class PerfectNumber {
    public static boolean isPerfect(int num) {
        int sum = 0;
        
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        
        return sum == num;
    }
    
    public static void main(String[] args) {
        System.out.println(isPerfect(6));   // true (1 + 2 + 3 = 6)
        System.out.println(isPerfect(28));  // true (1 + 2 + 4 + 7 + 14 = 28)
    }
}
