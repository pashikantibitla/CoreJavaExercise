import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// Custom annotation for validation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotNull {
    String message() default "Field cannot be null";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MinLength {
    int value();
    String message() default "Field length is below minimum";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
    String message() default "Field length exceeds maximum";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
    String message() default "Value out of range";
}

// User class with validation annotations
class User {
    @NotNull(message = "Username cannot be null")
    @MinLength(value = 3, message = "Username must be at least 3 characters")
    @MaxLength(value = 20, message = "Username must be at most 20 characters")
    private String username;
    
    @NotNull(message = "Email cannot be null")
    @MaxLength(value = 50, message = "Email must be at most 50 characters")
    private String email;
    
    @Range(min = 18, max = 100, message = "Age must be between 18 and 100")
    private int age;
    
    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}

// Validator using reflection
class Validator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            try {
                Object value = field.get(obj);
                
                // Check @NotNull
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                        NotNull ann = field.getAnnotation(NotNull.class);
                        errors.add(field.getName() + ": " + ann.message());
                    }
                }
                
                // Check @MinLength
                if (field.isAnnotationPresent(MinLength.class) && value instanceof String) {
                    MinLength ann = field.getAnnotation(MinLength.class);
                    String str = (String) value;
                    if (str != null && str.length() < ann.value()) {
                        errors.add(field.getName() + ": " + ann.message() + " (required: " + ann.value() + ")");
                    }
                }
                
                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class) && value instanceof String) {
                    MaxLength ann = field.getAnnotation(MaxLength.class);
                    String str = (String) value;
                    if (str != null && str.length() > ann.value()) {
                        errors.add(field.getName() + ": " + ann.message() + " (required: " + ann.value() + ")");
                    }
                }
                
                // Check @Range
                if (field.isAnnotationPresent(Range.class) && value instanceof Number) {
                    Range ann = field.getAnnotation(Range.class);
                    int num = ((Number) value).intValue();
                    if (num < ann.min() || num > ann.max()) {
                        errors.add(field.getName() + ": " + ann.message() + " (allowed: " + ann.min() + "-" + ann.max() + ")");
                    }
                }
                
            } catch (IllegalAccessException e) {
                errors.add("Error accessing field: " + field.getName());
            }
        }
        
        return errors;
    }
}

// Interview-level: Build a simple dependency injection container
class DIContainer {
    private Map<Class<?>, Object> instances = new HashMap<>();
    
    public void register(Class<?> clazz) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        instances.put(clazz, instance);
    }
    
    public void register(Class<?> clazz, Object instance) {
        instances.put(clazz, instance);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> clazz) {
        return (T) instances.get(clazz);
    }
    
    public void injectDependencies(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dependency = instances.get(field.getType());
                if (dependency != null) {
                    field.set(obj, dependency);
                }
            }
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {}

// Example classes for DI
class DatabaseService {
    public void connect() {
        System.out.println("DatabaseService: Connected to database");
    }
}

class UserService {
    @Inject
    private DatabaseService databaseService;
    
    public void processUser() {
        databaseService.connect();
        System.out.println("UserService: Processing user");
    }
}

public class DevelopmentInterviewQuestions {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Q1: Custom Validation with Reflection ===\n");
        
        User user1 = new User("Al", "alice@example.com", 25);
        User user2 = new User(null, "bob@example.com", 15);
        User user3 = new User("validUser", "charlie@example.com", 30);
        
        List<User> users = Arrays.asList(user1, user2, user3);
        for (User user : users) {
            List<String> errors = Validator.validate(user);
            System.out.println("User: " + user.getClass().getSimpleName());
            if (errors.isEmpty()) {
                System.out.println("  ✓ Valid");
            } else {
                for (String error : errors) {
                    System.out.println("  ✗ " + error);
                }
            }
            System.out.println();
        }
        
        System.out.println("=== Q2: Simple Dependency Injection Container ===\n");
        
        DIContainer container = new DIContainer();
        container.register(DatabaseService.class);
        
        UserService userService = new UserService();
        container.injectDependencies(userService);
        userService.processUser();
        
        System.out.println("\n=== Q3: Reading Class Metadata at Runtime ===\n");
        
        Class<User> userClass = User.class;
        System.out.println("Class: " + userClass.getName());
        System.out.println("Annotations on fields:");
        for (Field field : userClass.getDeclaredFields()) {
            System.out.println("  " + field.getName() + ":");
            for (Annotation ann : field.getAnnotations()) {
                System.out.println("    - " + ann.annotationType().getSimpleName());
            }
        }
        
        System.out.println("\n=== Q4: Method Inspection ===\n");
        
        Class<DatabaseService> dbClass = DatabaseService.class;
        for (Method method : dbClass.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
            System.out.println("  Return type: " + method.getReturnType().getSimpleName());
            System.out.println("  Parameters: " + method.getParameterCount());
            System.out.println("  Declaring class: " + method.getDeclaringClass().getSimpleName());
        }
    }
}
