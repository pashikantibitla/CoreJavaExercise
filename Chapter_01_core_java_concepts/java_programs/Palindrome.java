public class Palindrome {
    public static boolean isPalindrome(int num) {
        int original = num;
        int reversed = 0;
        
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        
        return original == reversed;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));  // true
        System.out.println(isPalindrome(123));  // false
    }
}
