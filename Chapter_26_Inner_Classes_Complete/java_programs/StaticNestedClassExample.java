class Outer {
    static int staticVar = 100;
    int instanceVar = 200;
    
    // Static nested class
    static class StaticNested {
        static int nestedStaticVar = 300;
        int nestedInstanceVar = 400;
        
        void show() {
            System.out.println("Static nested accessing outer static: " + staticVar);
            // System.out.println(instanceVar); // Error: cannot access non-static directly
            
            // Can access instance via outer instance
            Outer outer = new Outer();
            System.out.println("Via outer instance: " + outer.instanceVar);
        }
        
        static void staticShow() {
            System.out.println("Static method in static nested class");
            System.out.println("Accessing outer static: " + staticVar);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Creating Static Nested Class ===");
        
        // No outer instance needed
        Outer.StaticNested nested1 = new Outer.StaticNested();
        nested1.show();
        
        System.out.println("\n=== Static nested static method ===");
        Outer.StaticNested.staticShow();
        
        System.out.println("\n=== Static nested class main method ===");
        // Can run: java Outer$StaticNestedMain
        // StaticNestedMain.main(args);
    }
}

// Static nested class with its own main method
class OuterWithMain {
    static class StaticNestedMain {
        public static void main(String[] args) {
            System.out.println("Main method inside static nested class!");
            System.out.println("Run this with: java OuterWithMain$StaticNestedMain");
        }
    }
}
