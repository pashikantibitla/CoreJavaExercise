interface Drawable {
    void draw();
    void resize(double factor);
}

class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing Circle with radius " + radius);
    }
    
    @Override
    public void resize(double factor) {
        radius *= factor;
    }
}

class Rectangle implements Drawable {
    private double length, width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle " + length + " x " + width);
    }
    
    @Override
    public void resize(double factor) {
        length *= factor;
        width *= factor;
    }
}

public class DrawableDemo {
    public static void main(String[] args) {
        Drawable[] shapes = {
            new Circle(5),
            new Rectangle(4, 6)
        };
        
        for (Drawable shape : shapes) {
            shape.draw();
            shape.resize(2);
            shape.draw();
        }
    }
}
