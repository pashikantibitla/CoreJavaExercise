interface Vehicle {
    void start();
    void stop();
    
    default void honk() {
        System.out.println("Beep beep!");
    }
    
    static void showInfo() {
        System.out.println("This is a vehicle");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    @Override
    public void stop() {
        System.out.println("Car stopped");
    }
}

public class VehicleInterface {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.honk();  // Default method
        car.stop();
        
        Vehicle.showInfo();  // Static method
    }
}
