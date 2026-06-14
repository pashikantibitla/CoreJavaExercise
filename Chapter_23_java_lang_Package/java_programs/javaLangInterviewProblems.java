public class javaLangInterviewProblems {
    public static void main(String[] args) {
        // Problem 1: Why equals and hashCode must be overridden together
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.hashCode() == p2.hashCode(): " + (p1.hashCode() == p2.hashCode()));

        // Problem 2: Deep copy vs shallow copy
        Address addr = new Address("NY");
        Student s1 = new Student(1, "Bob", addr);
        try {
            Student s2 = (Student) s1.clone();
            System.out.println("Shallow copy: s1.address == s2.address? " + (s1.address == s2.address));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // Problem 3: Wrapper caching trap
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("127 == 127? " + (a == b));
        System.out.println("128 == 128? " + (c == d) + " (use equals!)");

        // Problem 4: Performance pitfall with auto-boxing
        long start = System.currentTimeMillis();
        long sumPrimitive = 0L;
        for (long i = 0; i < 10000000; i++) {
            sumPrimitive += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("Primitive sum time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        Long sumWrapper = 0L;
        for (long i = 0; i < 10000000; i++) {
            sumWrapper += i; // auto-boxing on each iteration!
        }
        end = System.currentTimeMillis();
        System.out.println("Wrapper sum time: " + (end - start) + " ms (slower due to boxing)");

        // Problem 5: System.nanoTime vs currentTimeMillis
        long nano = System.nanoTime();
        long millis = System.currentTimeMillis();
        System.out.println("nanoTime is for elapsed: " + nano);
        System.out.println("currentTimeMillis is wall-clock: " + millis);
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person p = (Person) o;
        return age == p.age && java.util.Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, age);
    }
}

class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Student implements Cloneable {
    int id;
    String name;
    Address address;

    Student(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
