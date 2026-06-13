interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }
}

class ShapeFactory {
    public static Shape createShape(String type) {
        switch (type.toLowerCase()) {
            case "circle": return new Circle();
            case "square": return new Square();
            case "triangle": return new Triangle();
            default: throw new IllegalArgumentException("Unknown shape: " + type);
        }
    }
}

public class ShapeFactoryDemo {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("circle");
        Shape square = ShapeFactory.createShape("square");
        
        circle.draw();   // Drawing Circle
        square.draw();   // Drawing Square
    }
}
