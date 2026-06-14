interface Greeting {
    void sayHello();
    void sayGoodbye();
}

class Animal {
    void makeSound() {
        System.out.println("Some generic sound");
    }
}

public class AnonymousInnerClassExample {
    public static void main(String[] args) {
        System.out.println("=== Anonymous class implementing interface ===");
        
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from anonymous class!");
            }
            
            @Override
            public void sayGoodbye() {
                System.out.println("Goodbye from anonymous class!");
            }
        };
        
        greeting.sayHello();
        greeting.sayGoodbye();
        
        System.out.println("\n=== Anonymous class extending class ===");
        
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Bark! Bark!");
            }
            
            // Can add new methods but can't call them via Animal reference
            void fetch() {
                System.out.println("Fetching the ball...");
            }
        };
        
        dog.makeSound();
        // dog.fetch(); // Error: cannot find symbol fetch() in Animal
        
        System.out.println("\n=== Anonymous class with constructor args ===");
        
        Animal cat = new Animal() {
            {
                System.out.println("Anonymous cat instance initializer");
            }
            
            @Override
            void makeSound() {
                System.out.println("Meow!");
            }
        };
        cat.makeSound();
        
        System.out.println("\n=== Anonymous class in Thread ===");
        
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in anonymous Runnable");
            }
        });
        thread.start();
        
        System.out.println("\n=== Anonymous class for Comparator ===");
        
        java.util.Comparator<String> lengthComparator = new java.util.Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };
        
        String[] words = {"Banana", "Apple", "Cherry", "Fig"};
        java.util.Arrays.sort(words, lengthComparator);
        System.out.println("Sorted by length: " + java.util.Arrays.toString(words));
    }
}
