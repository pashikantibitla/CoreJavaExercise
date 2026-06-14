class Outer {
    private int outerVar = 10;
    
    void methodWithLocalClass() {
        int localVar = 20; // Effectively final
        final int finalVar = 30;
        
        // Method local inner class
        class LocalInner {
            void display() {
                System.out.println("Outer variable: " + outerVar);
                System.out.println("Local variable (effectively final): " + localVar);
                System.out.println("Final variable: " + finalVar);
            }
        }
        
        LocalInner local = new LocalInner();
        local.display();
        
        // localVar = 25; // Error: would make localVar not effectively final
    }
    
    void methodWithLocalClassInLoop() {
        for (int i = 0; i < 3; i++) {
            final int index = i; // Must be final or effectively final
            
            class LoopLocal {
                void show() {
                    System.out.println("Loop iteration: " + index);
                }
            }
            
            new LoopLocal().show();
        }
    }
    
    void methodWithConditionalLocalClass(boolean flag) {
        if (flag) {
            class IfLocal {
                void show() {
                    System.out.println("Inside if block local class");
                }
            }
            new IfLocal().show();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Basic Method Local Inner Class ===");
        Outer outer = new Outer();
        outer.methodWithLocalClass();
        
        System.out.println("\n=== Method Local in Loop ===");
        outer.methodWithLocalClassInLoop();
        
        System.out.println("\n=== Method Local in If Block ===");
        outer.methodWithConditionalLocalClass(true);
    }
}
