// Nested interface inside a class
class Container {
    // Nested interface (implicitly static)
    interface NestedInterface {
        void nestedMethod();
    }
    
    // Private nested interface
    private interface PrivateNestedInterface {
        void privateMethod();
    }
    
    // Implementing nested interface inside outer class
    class InnerImpl implements NestedInterface {
        @Override
        public void nestedMethod() {
            System.out.println("Nested interface implemented in inner class");
        }
    }
    
    // Implementing private nested interface
    class PrivateImpl implements PrivateNestedInterface {
        @Override
        public void privateMethod() {
            System.out.println("Private nested interface implementation");
        }
    }
    
    public void test() {
        new InnerImpl().nestedMethod();
        new PrivateImpl().privateMethod();
    }
}

// Implementing nested interface from outside
class ExternalImpl implements Container.NestedInterface {
    @Override
    public void nestedMethod() {
        System.out.println("Nested interface implemented externally");
    }
}

// Interface containing another interface
interface OuterInterface {
    void outerMethod();
    
    // Implicitly public and static
    interface InnerInterface {
        void innerMethod();
    }
}

class OuterInterfaceImpl implements OuterInterface.InnerInterface {
    @Override
    public void innerMethod() {
        System.out.println("Inner interface method implemented");
    }
}

public class NestedInterfaceExample {
    public static void main(String[] args) {
        System.out.println("=== Nested Interface in Class ===");
        Container container = new Container();
        container.test();
        
        System.out.println("\n=== External Implementation of Nested Interface ===");
        Container.NestedInterface ext = new ExternalImpl();
        ext.nestedMethod();
        
        System.out.println("\n=== Nested Interface in Interface ===");
        OuterInterface.InnerInterface inner = new OuterInterfaceImpl();
        inner.innerMethod();
    }
}
