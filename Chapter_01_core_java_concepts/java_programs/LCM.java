public class LCM {
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static void main(String[] args) {
        System.out.println(lcm(4, 6));  // 12
    }
}
