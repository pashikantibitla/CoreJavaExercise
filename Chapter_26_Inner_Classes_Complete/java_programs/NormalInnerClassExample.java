class Outer {
    private int outerVar = 100;
    static int staticOuterVar = 200;
    
    // Normal inner class
    class Inner {
        private int innerVar = 50;
        
        void display() {
            System.out.println("Inner class can access outer private variable: " + outerVar);
            System.out.println("Inner class can access outer static variable: " + staticOuterVar);
            System.out.println("Inner class own variable: " + innerVar);
        }
        
        void showOuterVar() {
            System.out.println("Outer var from inner: " + outerVar);
        }
    }
    
    void createInner() {
        Inner inner = new Inner();
        inner.display();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Creating Inner Class ===");
        
        // Method 1: Create outer, then inner
        Outer outer = new Outer();
        Outer.Inner inner1 = outer.new Inner();
        inner1.display();
        
        System.out.println("\n=== Creating Inner in one line ===");
        Outer.Inner inner2 = new Outer().new Inner();
        inner2.showOuterVar();
        
        System.out.println("\n=== Inner class from outer method ===");
        outer.createInner();
        
        System.out.println("\n=== Outer accessing inner private member ===");
        System.out.println("Outer can access inner private var: " + inner1.innerVar);
    }
}
