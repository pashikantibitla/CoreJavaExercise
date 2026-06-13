class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof!");
    }
    
    public void fetch() {
        System.out.println(name + " is fetching the ball");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow!");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Animal a1 = new Dog("Buddy");
        Animal a2 = new Cat("Whiskers");
        
        a1.makeSound();  // Buddy barks: Woof!
        a2.makeSound();  // Whiskers meows: Meow!
    }
}
