interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped (default)");
    }

    default void honk() {
        System.out.println("Beep beep!");
    }
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }

    // Override default method
    public void stop() {
        System.out.println("Car stopped with brakes");
    }
}

public class DefaultMethodsDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.stop();
        car.honk(); // uses default method
    }
}
