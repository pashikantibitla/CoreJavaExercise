public class ArmstrongNumber {
    public static boolean isArmstrong(int num) {
        int original = num;
        int sum = 0;
        int digits = String.valueOf(num).length();
        
        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, digits);
            num /= 10;
        }
        
        return original == sum;
    }
    
    public static void main(String[] args) {
        System.out.println(isArmstrong(153));  // true (1^3 + 5^3 + 3^3 = 153)
        System.out.println(isArmstrong(9474)); // true (4-digit)
    }
}
