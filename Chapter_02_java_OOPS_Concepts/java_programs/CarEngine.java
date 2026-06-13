class Engine {
    private String type;
    private int horsepower;
    
    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }
    
    public void start() {
        System.out.println(type + " engine starting with " + horsepower + " HP");
    }
    
    public void stop() {
        System.out.println("Engine stopped");
    }
}

class Car {
    private String brand;
    private Engine engine;
    
    public Car(String brand, String engineType, int horsepower) {
        this.brand = brand;
        this.engine = new Engine(engineType, horsepower);
    }
    
    public void startCar() {
        System.out.println(brand + " car starting...");
        engine.start();
    }
    
    public void stopCar() {
        engine.stop();
        System.out.println(brand + " car stopped");
    }
}

public class CarEngine {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "V6", 300);
        car.startCar();
        car.stopCar();
    }
}
