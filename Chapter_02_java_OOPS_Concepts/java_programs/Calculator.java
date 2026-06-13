public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 10));          // 15
        System.out.println(calc.add(5.5, 10.5));    // 16.0
        System.out.println(calc.add(1, 2, 3));        // 6
        System.out.println(calc.add("Hello", "World"));  // HelloWorld
    }
}
