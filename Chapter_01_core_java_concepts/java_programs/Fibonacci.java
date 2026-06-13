public class Fibonacci {
    public static void printFibonacci(int n) {
        int a = 0, b = 1;
        
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }
    
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        printFibonacci(10);  // 0 1 1 2 3 5 8 13 21 34
    }
}
