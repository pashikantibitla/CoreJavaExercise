interface Color {
    void applyColor();
}

class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying red color");
    }
}

class BlueColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying blue color");
    }
}

abstract class Shape {
    protected Color color;
    
    public Shape(Color color) {
        this.color = color;
    }
    
    abstract void applyColor();
}

class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }
    
    @Override
    void applyColor() {
        System.out.print("Circle filled with ");
        color.applyColor();
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }
    
    @Override
    void applyColor() {
        System.out.print("Square filled with ");
        color.applyColor();
    }
}

public class ColorShape {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueSquare = new Square(new BlueColor());
        
        redCircle.applyColor();
        blueSquare.applyColor();
    }
}
