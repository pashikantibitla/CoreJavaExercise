import java.lang.reflect.*;

class Person {
    private String name;
    private int age;
    public String email;
    
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }
    
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void introduce() {
        System.out.println("Hi, I am " + name + ", " + age + " years old.");
    }
    
    private void secretMethod() {
        System.out.println("This is a private method!");
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Class<Person> clazz = Person.class;
        
        System.out.println("=== Class Information ===");
        System.out.println("Class Name: " + clazz.getName());
        System.out.println("Simple Name: " + clazz.getSimpleName());
        System.out.println("Package: " + clazz.getPackage().getName());
        System.out.println("Superclass: " + clazz.getSuperclass().getName());
        System.out.println("Is Interface: " + clazz.isInterface());
        
        System.out.println("\n=== Constructors ===");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor.getName() + 
                " (Parameter Count: " + constructor.getParameterCount() + ")");
            System.out.println("  Modifiers: " + Modifier.toString(constructor.getModifiers()));
            for (Parameter param : constructor.getParameters()) {
                System.out.println("  Parameter: " + param.getType().getSimpleName() + " " + param.getName());
            }
        }
        
        System.out.println("\n=== Fields ===");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field: " + field.getName() + 
                " (Type: " + field.getType().getSimpleName() + 
                ", Modifiers: " + Modifier.toString(field.getModifiers()) + ")");
        }
        
        System.out.println("\n=== Methods ===");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName() + 
                " (Return: " + method.getReturnType().getSimpleName() + 
                ", Parameters: " + method.getParameterCount() + 
                ", Modifiers: " + Modifier.toString(method.getModifiers()) + ")");
        }
        
        System.out.println("\n=== Creating Instance with Reflection ===");
        // Using public no-arg constructor
        Constructor<Person> publicConstructor = clazz.getDeclaredConstructor();
        Person person1 = publicConstructor.newInstance();
        System.out.println("Created via public constructor: " + person1.getName());
        
        // Using private constructor (set accessible)
        Constructor<Person> privateConstructor = clazz.getDeclaredConstructor(String.class, int.class);
        privateConstructor.setAccessible(true);
        Person person2 = privateConstructor.newInstance("Alice", 30);
        System.out.println("Created via private constructor: " + person2.getName() + ", " + person2.getAge());
        
        System.out.println("\n=== Accessing and Modifying Fields ===");
        // Access public field
        Field emailField = clazz.getDeclaredField("email");
        emailField.set(person1, "alice@example.com");
        System.out.println("Public email field: " + person1.email);
        
        // Access private field
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        System.out.println("Private name (before): " + nameField.get(person1));
        nameField.set(person1, "Bob");
        System.out.println("Private name (after): " + nameField.get(person1));
        
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(person1, 25);
        System.out.println("Private age (after set): " + ageField.get(person1));
        
        System.out.println("\n=== Invoking Methods ===");
        // Invoke public method
        Method introduceMethod = clazz.getDeclaredMethod("introduce");
        introduceMethod.invoke(person1);
        
        // Invoke private method
        Method secretMethod = clazz.getDeclaredMethod("secretMethod");
        secretMethod.setAccessible(true);
        secretMethod.invoke(person1);
        
        // Invoke method with parameters
        Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
        setNameMethod.invoke(person1, "Charlie");
        System.out.println("After setName via reflection: " + person1.getName());
        
        System.out.println("\n=== Practical Use Case: Generic Object Printer ===");
        printObjectInfo(person2);
    }
    
    public static void printObjectInfo(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        System.out.println("Object of class: " + clazz.getSimpleName());
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println("  " + field.getName() + " = " + field.get(obj));
        }
    }
}
