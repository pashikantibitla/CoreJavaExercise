public class Power {
    public static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    // Fast power (O(log n))
    public static double fastPower(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) return 1 / fastPower(base, -exponent);
        
        double half = fastPower(base, exponent / 2);
        if (exponent % 2 == 0) {
            return half * half;
        } else {
            return base * half * half;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(power(2, 10));      // 1024.0
        System.out.println(fastPower(2, 10));  // 1024.0
    }
}
