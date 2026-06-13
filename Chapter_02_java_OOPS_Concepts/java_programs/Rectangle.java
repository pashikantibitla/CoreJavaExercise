public class Rectangle {
    private int length;
    private int width;
    
    public Rectangle() {
        this(0, 0);
    }
    
    public Rectangle(int side) {
        this(side, side);
    }
    
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }
    
    public int area() {
        return length * width;
    }
    
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();        // 0 x 0
        Rectangle r2 = new Rectangle(5);         // 5 x 5
        Rectangle r3 = new Rectangle(4, 6);    // 4 x 6
        
        System.out.println("Area 1: " + r1.area());  // 0
        System.out.println("Area 2: " + r2.area());  // 25
        System.out.println("Area 3: " + r3.area());  // 24
    }
}
