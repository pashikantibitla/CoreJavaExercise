public class JVMInterviewProblem {
    public static void main(String[] args) {
        System.out.println("Main start");
        System.out.println("Child.x = " + Child.x);
        System.out.println("Child.y = " + Child.y);
        System.out.println("Main end");
    }
}

class Parent {
    static int x = 10;
    static {
        System.out.println("Parent static block: x = " + x);
        x = 20;
    }
}

class Child extends Parent {
    static int y = 30;
    static {
        System.out.println("Child static block: x = " + x + ", y = " + y);
        y = 40;
    }
}

// Expected Output:
// Main start
// Parent static block: x = 10
// Child static block: x = 20, y = 30
// Child.x = 20
// Child.y = 40
// Main end
//
// Explanation: Accessing Child.y triggers loading of Child class.
// Parent is loaded and initialized first, then Child.
// Static blocks execute in the order they appear in the source.
