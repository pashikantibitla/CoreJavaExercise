class ShadowDemo {
    int x = 1;
    static int y = 10;
    
    class Inner {
        int x = 100;
        
        void display() {
            int x = 1000;
            
            System.out.println("Local x: " + x);              // 1000 (local variable)
            System.out.println("Inner x: " + this.x);           // 100 (inner class field)
            System.out.println("Outer x: " + ShadowDemo.this.x); // 1 (outer class field)
            System.out.println("Outer y: " + y);                // 10 (static)
        }
    }
    
    void accessInner() {
        Inner inner = new Inner();
        System.out.println("Inner x from outer: " + inner.x); // OK, outer can access
        inner.display();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Shadowing and OuterClass.this ===");
        new ShadowDemo().accessInner();
    }
}

// Interview question: Can inner class access private members of outer? Yes.
class PrivateAccessDemo {
    private int privateVar = 42;
    private void privateMethod() {
        System.out.println("Private method called from inner class");
    }
    
    class Inner {
        void accessPrivate() {
            System.out.println("Accessing outer private variable: " + privateVar);
            privateMethod();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("\n=== Inner Accessing Outer Private ===");
        new PrivateAccessDemo().new Inner().accessPrivate();
    }
}

// Interview question: Can outer class access private members of inner? Yes.
class OuterAccessInner {
    class Inner {
        private int innerPrivate = 99;
        private void innerPrivateMethod() {
            System.out.println("Inner private method called from outer");
        }
    }
    
    void accessInnerPrivate() {
        Inner inner = new Inner();
        System.out.println("\n=== Outer Accessing Inner Private ===");
        System.out.println("Accessing inner private variable: " + inner.innerPrivate);
        inner.innerPrivateMethod();
    }
    
    public static void main(String[] args) {
        new OuterAccessInner().accessInnerPrivate();
    }
}

// Interview question: Builder pattern using static nested class
class Person {
    private final String name;
    private final int age;
    private final String email;
    
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }
    
    public static class Builder {
        private String name;
        private int age;
        private String email;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Person build() {
            return new Person(this);
        }
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
    
    public static void main(String[] args) {
        System.out.println("\n=== Builder Pattern with Static Nested Class ===");
        Person person = new Person.Builder()
            .name("Alice")
            .age(30)
            .email("alice@example.com")
            .build();
        System.out.println(person);
    }
}

public class InnerClassesInterviewQuestions {
    public static void main(String[] args) {
        System.out.println("=== Inner Classes Interview Questions ===\n");
        
        // Demonstrate shadowing
        new ShadowDemo().accessInner();
        
        // Demonstrate private access
        new PrivateAccessDemo().new Inner().accessPrivate();
        
        // Demonstrate outer access to inner
        new OuterAccessInner().accessInnerPrivate();
        
        // Demonstrate builder
        System.out.println();
        Person.main(args);
    }
}
