class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barking");
    }
    
    void fetch() {
        System.out.println("Dog fetching");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meowing");
    }
    
    void climb() {
        System.out.println("Cat climbing");
    }
}

public class AnimalCasting {
    public static void main(String[] args) {
        // Upcasting (implicit)
        Animal animal = new Dog();
        animal.makeSound();  // Dog barking
        
        // Downcasting (explicit)
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.fetch();  // Dog fetching
        }
        
        // Invalid downcasting
        Animal cat = new Cat();
        // Dog dog = (Dog) cat;  // ❌ ClassCastException
    }
}
