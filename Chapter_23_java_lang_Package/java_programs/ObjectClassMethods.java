import java.util.Objects;

class Employee implements Cloneable {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee emp = (Employee) o;
        return id == emp.id && Objects.equals(name, emp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing: " + this);
    }
}

public class ObjectClassMethods {
    public static void main(String[] args) throws Exception {
        Employee e1 = new Employee(1, "Alice");
        Employee e2 = new Employee(1, "Alice");
        Employee e3 = new Employee(2, "Bob");

        // equals
        System.out.println("e1.equals(e2): " + e1.equals(e2));
        System.out.println("e1.equals(e3): " + e1.equals(e3));

        // hashCode
        System.out.println("e1.hashCode(): " + e1.hashCode());
        System.out.println("e2.hashCode(): " + e2.hashCode());

        // toString
        System.out.println("e1.toString(): " + e1.toString());

        // getClass
        System.out.println("e1.getClass(): " + e1.getClass().getName());

        // clone
        Employee cloned = (Employee) e1.clone();
        System.out.println("Cloned: " + cloned);
        System.out.println("e1 == cloned: " + (e1 == cloned));

        // notify/notifyAll/wait demo omitted for brevity (requires synchronization)
    }
}
