class Product {
    protected String name;
    protected double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public void display() {
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
    }
}

class ElectronicProduct extends Product {
    private String brand;
    private int warranty;
    
    public ElectronicProduct(String name, double price, String brand, int warranty) {
        super(name, price);
        this.brand = brand;
        this.warranty = warranty;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warranty + " years");
    }
}

public class EnhancedDisplay {
    public static void main(String[] args) {
        ElectronicProduct laptop = new ElectronicProduct("Laptop", 1200, "Dell", 2);
        laptop.display();
    }
}
