# Chapter 03 — POJO Classes and JSON

> **Topics:** POJO Fundamentals, JavaBean Conventions, Serialization/Deserialization, Jackson Library, Gson Library, Lombok Annotations, Nested POJOs, Memory Allocation, GC Impact, JSON Structure, JSON Parsing, Tree vs Streaming Model, JSON Schema Validation, Interview FAQs

---

## Table of Contents

1. [What are POJO Classes](#1-what-are-pojo-classes)
2. [JavaBean Conventions for POJOs](#2-javabean-conventions-for-pojos)
3. [Serialization and Deserialization Concepts](#3-serialization-and-deserialization-concepts)
4. [Jackson Library — ObjectMapper](#4-jackson-library--objectmapper)
5. [Gson Library Usage](#5-gson-library-usage)
6. [Lombok Annotations for POJOs](#6-lombok-annotations-for-pojos)
7. [Nested POJOs and Complex JSON Structures](#7-nested-pojos-and-complex-json-structures)
8. [Memory Allocation for POJO Objects](#8-memory-allocation-for-pojo-objects)
9. [Garbage Collection Impact During API Testing](#9-garbage-collection-impact-during-api-testing)
10. [Methods and Functions in POJO Classes](#10-methods-and-functions-in-pojo-classes)
11. [Data Processing Approaches — Tree Model vs Streaming Model](#11-data-processing-approaches--tree-model-vs-streaming-model)
12. [JSON Structure and Data Types](#12-json-structure-and-data-types)
13. [JSON Parsing Techniques and Manipulation](#13-json-parsing-techniques-and-manipulation)
14. [JSON Schema Validation](#14-json-schema-validation)
15. [Interview FAQs (20+ Questions)](#15-interview-faqs-20-questions)
16. [Key Takeaways](#16-key-takeaways)

---

## 1. What are POJO Classes

### Definition

**POJO** stands for **Plain Old Java Object**. It is a simple Java class that does not extend any specific class, implement any specific interface, or use any annotations from external frameworks. POJOs are used to represent data in object-oriented programs.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    POJO DEFINITION                                     │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ A POJO is a Java object that:                                │   │
│  │ 1. Does NOT extend pre-specified classes (e.g., EJB Entity) │   │
│  │ 2. Does NOT implement pre-specified interfaces               │   │
│  │ 3. Does NOT contain pre-specified annotations               │   │
│  │ 4. Contains only fields and methods for data access        │   │
│  │ 5. Follows JavaBean conventions (getters/setters)            │   │
│  │ 6. Has a public no-arg constructor                           │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Why POJOs Matter in API Testing

```java
// POJOs are the foundation of API testing because:
//
// 1. JSON Mapping: REST APIs send/receive JSON. POJOs map JSON fields to Java objects.
// 2. Type Safety: POJOs provide compile-time type checking instead of raw strings.
// 3. Code Readability: `employee.getName()` is cleaner than `json.get("name")`.
// 4. Reusability: The same POJO can be used for serialization, deserialization, and validation.
// 5. Framework Integration: Jackson, Gson, REST Assured, and Spring all use POJOs.
// 6. Maintainability: Changing a field name in one place updates all usages.
// 7. Test Data Generation: POJOs can be used to build request payloads programmatically.
//
// Example: Without POJO
String json = "{\"name\":\"John\",\"age\":30}";  // String manipulation — error-prone
//
// Example: With POJO
Employee emp = new Employee("John", 30);  // Type-safe, object-oriented
String json = objectMapper.writeValueAsString(emp);  // Automatic JSON conversion
```

### Simple POJO Example

```java
// Basic POJO class
public class Employee {
    
    // Private fields (data hiding)
    private int id;
    private String name;
    private double salary;
    
    // Public no-arg constructor (required for JSON deserialization)
    public Employee() {
    }
    
    // Parameterized constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    
    // Getter methods
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Setter methods
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}
```

---

## 2. JavaBean Conventions for POJOs

### JavaBean Rules

```java
// A JavaBean is a special kind of POJO that follows strict conventions:
//
// 1. The class must be public.
// 2. The class must have a public no-arg constructor.
// 3. All fields must be private.
// 4. For each field, provide public getter and setter methods.
// 5. Getter naming: get<FieldName>() for non-boolean, is<FieldName>() for boolean.
// 6. Setter naming: set<FieldName>(<Type> value).
// 7. The class should implement Serializable (optional but recommended).
// 8. The class should override toString(), equals(), and hashCode().
```

### JavaBean Naming Convention

```java
public class JavaBeanExample implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String firstName;       // getter: getFirstName()     setter: setFirstName()
    private int age;                // getter: getAge()           setter: setAge()
    private boolean active;         // getter: isActive()         setter: setActive()
    private boolean isDeleted;      // getter: isDeleted()        setter: setDeleted()
    
    // Boolean field: getter starts with 'is' if field starts with lowercase
    // Boolean field: getter starts with 'get' if field starts with 'is'
    
    private String URL;             // getter: getURL()           setter: setURL()
    // Exception: If field is all uppercase, getter keeps the field name
    
    // No-arg constructor
    public JavaBeanExample() {
    }
    
    // Getters and setters
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }
    
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }
    
    public String getURL() {
        return URL;
    }
    
    public void setURL(String URL) {
        this.URL = URL;
    }
}
```

### Why JavaBean Conventions Matter

```java
// Frameworks like Jackson, Gson, and Spring use JavaBean conventions
// to automatically map JSON/XML to Java objects.
//
// Jackson reads a JSON field "firstName" and looks for:
//   - setFirstName() method to set the value
//   - getFirstName() method to read the value
//
// If the naming convention is wrong, deserialization will fail.
//
// Example: Wrong naming
public class BadNaming {
    private String name;
    
    public String getname() {        // WRONG: should be getName()
        return name;
    }
    
    public void setname(String name) { // WRONG: should be setName()
        this.name = name;
    }
}
// Result: Jackson cannot map JSON field "name" to this field.
```

---

## 3. Serialization and Deserialization Concepts

### Definitions

```
┌─────────────────────────────────────────────────────────────────────┐
│            SERIALIZATION vs DESERIALIZATION IN API TESTING             │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ SERIALIZATION:                                               │   │
│  │   Java Object → JSON String → HTTP Request Body             │   │
│  │                                                             │   │
│  │ DESERIALIZATION:                                             │   │
│  │   HTTP Response Body → JSON String → Java Object           │   │
│  │                                                             │   │
│  │ In API Testing:                                              │   │
│  │   - Serialize POJO to create request payloads                │   │
│  │   - Deserialize response JSON to validate data               │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Serialization Process

```java
// Serialization: Converting a Java object to a JSON string
//
// Example with Jackson:
// Employee emp = new Employee(1, "John", 50000.0);
// String json = objectMapper.writeValueAsString(emp);
//
// Output:
// {"id":1,"name":"John","salary":50000.0}
//
// Steps in Serialization:
// 1. Jackson reads the POJO class metadata (fields, getters).
// 2. For each field, it calls the getter method.
// 3. It converts the field value to the corresponding JSON type.
// 4. It constructs the JSON string with field names and values.
// 5. The JSON string is sent as the HTTP request body.
```

### Deserialization Process

```java
// Deserialization: Converting a JSON string to a Java object
//
// Example with Jackson:
// String json = "{\"id\":1,\"name\":\"John\",\"salary\":50000.0}";
// Employee emp = objectMapper.readValue(json, Employee.class);
//
// Steps in Deserialization:
// 1. Jackson parses the JSON string into a tree structure.
// 2. It creates a new instance of the target class using the no-arg constructor.
// 3. For each JSON field, it matches the field name to the setter method.
// 4. It converts the JSON value to the Java type expected by the setter.
// 5. It calls the setter method to populate the field.
// 6. The fully populated object is returned.
```

### Serialization in REST API Testing

```java
// Example: Creating a POST request payload
public class ApiTest {
    public static void main(String[] args) throws Exception {
        // Create POJO
        Employee emp = new Employee(1, "John Doe", 75000.0);
        
        // Serialize to JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(emp);
        
        // Send as HTTP POST body
        // RestAssured.given()
        //     .contentType("application/json")
        //     .body(jsonPayload)
        //     .post("/api/employees");
        
        System.out.println("JSON Payload: " + jsonPayload);
    }
}
```

### Deserialization in REST API Testing

```java
// Example: Validating a GET response
public class ApiResponseTest {
    public static void main(String[] args) throws Exception {
        String jsonResponse = "{\"id\":1,\"name\":\"John Doe\",\"salary\":75000.0}";
        
        // Deserialize JSON to POJO
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = mapper.readValue(jsonResponse, Employee.class);
        
        // Validate fields using POJO getters
        assert emp.getId() == 1;
        assert "John Doe".equals(emp.getName());
        assert emp.getSalary() == 75000.0;
        
        System.out.println("Employee: " + emp);
    }
}
```

---

## 4. Jackson Library — ObjectMapper

### Introduction to Jackson

**Jackson** is the most popular JSON library for Java. It provides the `ObjectMapper` class for serialization and deserialization, plus extensive configuration options for customization.

```java
// Maven dependency:
// <dependency>
//     <groupId>com.fasterxml.jackson.core</groupId>
//     <artifactId>jackson-databind</artifactId>
//     <version>2.15.2</version>
// </dependency>
//
// Key classes:
// - ObjectMapper: Main class for JSON serialization/deserialization
// - ObjectWriter: Pre-configured writer for serialization
// - ObjectReader: Pre-configured reader for deserialization
// - JsonNode: Tree model representation of JSON
```

### ObjectMapper Configuration

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class JacksonConfig {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        
        // Serialization Features
        mapper.enable(SerializationFeature.INDENT_OUTPUT);           // Pretty print JSON
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);     // Allow empty classes
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Skip null fields
        
        // Deserialization Features
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Ignore unknown JSON fields
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY); // "a" -> ["a"]
        
        // Date Handling
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Write dates as strings
        
        // Custom Naming Strategy
        // mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        // Maps: firstName -> first_name in JSON
    }
}
```

### Jackson Serialization

```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonSerialization {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        Employee emp = new Employee(1, "Alice", 60000.0);
        
        // Serialize to JSON string
        String json = mapper.writeValueAsString(emp);
        System.out.println("Compact JSON: " + json);
        
        // Serialize with pretty print
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String prettyJson = mapper.writeValueAsString(emp);
        System.out.println("Pretty JSON:\n" + prettyJson);
        
        // Serialize to byte array
        byte[] jsonBytes = mapper.writeValueAsBytes(emp);
        
        // Serialize to File
        // mapper.writeValue(new File("employee.json"), emp);
    }
}
```

### Jackson Deserialization

```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDeserialization {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        String json = "{\"id\":1,\"name\":\"Bob\",\"salary\":55000.0}";
        
        // Deserialize to single object
        Employee emp = mapper.readValue(json, Employee.class);
        System.out.println("Employee: " + emp.getName());
        
        // Deserialize to List
        String jsonArray = "[{\"id\":1,\"name\":\"A\"},{\"id\":2,\"name\":\"B\"}]";
        List<Employee> employees = mapper.readValue(jsonArray, 
            new TypeReference<List<Employee>>() {});
        System.out.println("Count: " + employees.size());
        
        // Deserialize to Map
        Map<String, Object> map = mapper.readValue(json, 
            new TypeReference<Map<String, Object>>() {});
        System.out.println("Map: " + map);
    }
}
```

### Jackson Annotations

```java
import com.fasterxml.jackson.annotation.*;

public class AnnotatedEmployee {
    
    @JsonProperty("employee_id")  // Custom JSON field name
    private int id;
    
    @JsonIgnore                    // Exclude this field from JSON
    private String password;
    
    @JsonIgnoreProperties({"salary"})  // Ignore 'salary' during deserialization
    private double salary;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date joinDate;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)  // Skip if null
    private String department;
    
    // @JsonSetter("emp_name") — Alternative to @JsonProperty for deserialization
    // @JsonGetter("emp_name") — Alternative to @JsonProperty for serialization
    // @JsonAnySetter — Catch unknown properties into a map
    // @JsonAnyGetter — Serialize extra properties from a map
    // @JsonRootName("employee") — Wrap object with root name
}
```

### Custom Serializers with Jackson

```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// Custom serializer for phone numbers
public class PhoneNumberSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String phone, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        // Mask phone number: 1234567890 -> ***-***-7890
        String masked = "***-***-" + phone.substring(phone.length() - 4);
        gen.writeString(masked);
    }
}

// Usage
public class EmployeeWithCustomSerializer {
    private String name;
    
    @JsonSerialize(using = PhoneNumberSerializer.class)
    private String phoneNumber;
    
    // getters and setters
}
```

---

## 5. Gson Library Usage

### Introduction to Gson

**Gson** is Google's JSON library for Java. It is simpler to configure than Jackson and provides a `Gson` class for serialization and deserialization.

```java
// Maven dependency:
// <dependency>
//     <groupId>com.google.code.gson</groupId>
//     <artifactId>gson</artifactId>
//     <version>2.10.1</version>
// </dependency>
//
// Key class: Gson
// Key method: toJson() and fromJson()
```

### Gson Serialization

```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSerialization {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        Employee emp = new Employee(1, "Charlie", 70000.0);
        
        // Serialize to JSON
        String json = gson.toJson(emp);
        System.out.println("JSON: " + json);
        
        // Pretty print with GsonBuilder
        Gson prettyGson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        String prettyJson = prettyGson.toJson(emp);
        System.out.println("Pretty JSON:\n" + prettyJson);
    }
}
```

### Gson Deserialization

```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonDeserialization {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        String json = "{\"id\":1,\"name\":\"Diana\",\"salary\":80000.0}";
        
        // Deserialize to single object
        Employee emp = gson.fromJson(json, Employee.class);
        System.out.println("Employee: " + emp.getName());
        
        // Deserialize to List
        String jsonArray = "[{\"id\":1,\"name\":\"A\"},{\"id\":2,\"name\":\"B\"}]";
        List<Employee> employees = gson.fromJson(jsonArray, 
            new TypeToken<List<Employee>>(){}.getType());
        System.out.println("Count: " + employees.size());
    }
}
```

### Gson Annotations

```java
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class GsonAnnotatedEmployee {
    
    @SerializedName("employee_id")  // Custom JSON field name
    private int id;
    
    @Expose(serialize = false, deserialize = false)  // Exclude from both
    private String password;
    
    @Expose(serialize = true, deserialize = false)   // Serialize only
    private String temporaryToken;
    
    private String name;  // No annotation: included in both
}
```

### Gson vs Jackson Comparison

```java
// Gson vs Jackson:
//
// Feature              | Gson               | Jackson
// -------------------- | ------------------ | ------------------
// Popularity           | High (Google)      | Very High (Spring)
// Performance          | Moderate           | High
// Configuration        | Simple             | Extensive
// Annotations          | Fewer              | Rich
// Streaming API        | Yes                | Yes
// Tree Model           | JsonElement        | JsonNode
// Custom Serializers   | Yes                | Yes
// Null Handling        | Includes nulls     | Configurable
// Unknown Fields       | Ignores by default | FAIL by default (configurable)
//
// Recommendation:
// - Use Jackson for Spring Boot and enterprise applications.
// - Use Gson for simple Android or lightweight projects.
```

---

## 6. Lombok Annotations for POJOs

### Introduction to Lombok

**Lombok** is a Java annotation processor that generates boilerplate code at compile time. It eliminates the need to write getters, setters, constructors, toString, equals, and hashCode manually.

```java
// Maven dependency:
// <dependency>
//     <groupId>org.projectlombok</groupId>
//     <artifactId>lombok</artifactId>
//     <version>1.18.28</version>
//     <scope>provided</scope>
// </dependency>
//
// IDE Plugin: Install Lombok plugin in IntelliJ IDEA or Eclipse
```

### Lombok Annotations

```java
import lombok.*;

// @Data: Generates getters, setters, toString, equals, hashCode
@Data
public class EmployeeLombok {
    private int id;
    private String name;
    private double salary;
    
    // Generated at compile time:
    // public int getId() { return id; }
    // public void setId(int id) { this.id = id; }
    // public String getName() { return name; }
    // public void setName(String name) { this.name = name; }
    // public double getSalary() { return salary; }
    // public void setSalary(double salary) { this.salary = salary; }
    // public String toString() { ... }
    // public boolean equals(Object o) { ... }
    // public int hashCode() { ... }
}
```

### Individual Lombok Annotations

```java
import lombok.*;

// @Getter and @Setter: Generate getters/setters for all fields
@Getter
@Setter
public class EmployeeGetterSetter {
    private int id;
    private String name;
}

// @NoArgsConstructor: Generate public no-arg constructor
@NoArgsConstructor
public class EmployeeNoArgs {
    private int id;
}

// @AllArgsConstructor: Generate constructor with all fields
@AllArgsConstructor
public class EmployeeAllArgs {
    private int id;
    private String name;
}

// @RequiredArgsConstructor: Generate constructor for final fields
@RequiredArgsConstructor
public class EmployeeRequiredArgs {
    private final int id;    // Included in constructor
    private String name;     // Not included (not final)
}

// @ToString: Generate toString()
@ToString
public class EmployeeToString {
    private int id;
    private String name;
}

// @EqualsAndHashCode: Generate equals() and hashCode()
@EqualsAndHashCode
public class EmployeeEqualsHashCode {
    private int id;
    private String name;
}

// @Builder: Generate builder pattern
@Builder
public class EmployeeBuilder {
    private int id;
    private String name;
    private double salary;
}

// Usage with @Builder:
// EmployeeBuilder emp = EmployeeBuilder.builder()
//     .id(1)
//     .name("John")
//     .salary(50000.0)
//     .build();
```

### Lombok @Data with @Builder

```java
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeComplete {
    private int id;
    private String name;
    private double salary;
    private String department;
}

// Usage:
// EmployeeComplete emp = EmployeeComplete.builder()
//     .id(1)
//     .name("John")
//     .salary(50000.0)
//     .department("Engineering")
//     .build();
```

### Lombok in API Testing

```java
// Lombok dramatically reduces boilerplate in API testing:
//
// Without Lombok (40 lines):
// public class Employee {
//     private int id;
//     private String name;
//     public Employee() {}
//     public int getId() { return id; }
//     public void setId(int id) { this.id = id; }
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
//     @Override public String toString() { ... }
//     @Override public boolean equals(Object o) { ... }
//     @Override public int hashCode() { ... }
// }
//
// With Lombok (4 lines):
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Employee {
//     private int id;
//     private String name;
// }
//
// This makes test codebases with many POJOs much more maintainable.
```

---

## 7. Nested POJOs and Complex JSON Structures

### Nested POJO Example

```java
// Address POJO
public class Address {
    private String street;
    private String city;
    private String zipCode;
    private String country;
    
    public Address() {}
    public Address(String street, String city, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
    
    // getters and setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}

// Employee with nested Address
public class EmployeeWithAddress {
    private int id;
    private String name;
    private Address address;  // Nested POJO
    
    public EmployeeWithAddress() {}
    
    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
```

### Complex JSON Mapping

```java
// JSON with nested objects:
// {
//   "id": 1,
//   "name": "John",
//   "address": {
//     "street": "123 Main St",
//     "city": "New York",
//     "zipCode": "10001",
//     "country": "USA"
//   }
// }

// Jackson automatically maps nested JSON to nested POJOs:
public class NestedJsonTest {
    public static void main(String[] args) throws Exception {
        String json = "{\"id\":1,\"name\":\"John\",\"address\":{\"street\":\"123 Main St\",\"city\":\"New York\",\"zipCode\":\"10001\",\"country\":\"USA\"}}";
        
        ObjectMapper mapper = new ObjectMapper();
        EmployeeWithAddress emp = mapper.readValue(json, EmployeeWithAddress.class);
        
        System.out.println("City: " + emp.getAddress().getCity());
    }
}
```

### List of Nested Objects

```java
// Department with list of employees
public class Department {
    private String name;
    private List<Employee> employees;  // List of nested POJOs
    
    public Department() {}
    
    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}

// JSON:
// {
//   "name": "Engineering",
//   "employees": [
//     {"id": 1, "name": "Alice", "salary": 60000},
//     {"id": 2, "name": "Bob", "salary": 70000}
//   ]
// }
```

### Map of Nested Objects

```java
// Company with departments mapped by name
public class Company {
    private String name;
    private Map<String, Department> departments;  // Map of nested POJOs
    
    public Company() {}
    
    // getters and setters
}

// JSON:
// {
//   "name": "TechCorp",
//   "departments": {
//     "engineering": {
//       "name": "Engineering",
//       "employees": [...]
//     },
//     "sales": {
//       "name": "Sales",
//       "employees": [...]
//     }
//   }
// }
```

---

## 8. Memory Allocation for POJO Objects

### JVM Memory Areas

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JVM MEMORY AREAS — POJO OBJECT ALLOCATION             │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  STACK (per thread)                                        │   │
│  │  ┌─────────────────────────────────────────────────────┐   │   │
│  │  │  Local Variables (references)                        │   │   │
│  │  │  - emp (reference to Employee object)                │   │   │
│  │  │  - primitive values (int, double)                    │   │   │
│  │  │  - method parameters                                 │   │   │
│  │  └─────────────────────────────────────────────────────┘   │   │
│  │  - Fast allocation and deallocation (LIFO)             │   │
│  │  - Automatically cleaned when method exits               │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  HEAP (shared across all threads)                            │   │
│  │  ┌─────────────────────────────────────────────────────┐   │   │
│  │  │  YOUNG GENERATION (1/3)                              │   │   │
│  │  │  ┌─────────────────────────────────────────────┐    │   │   │
│  │  │  │ Eden Space: New POJOs created here           │    │   │   │
│  │  │  │ Employee emp = new Employee();               │    │   │   │
│  │  │  │ (object allocated in Eden)                   │    │   │   │
│  │  │  └─────────────────────────────────────────────┘    │   │   │
│  │  │  ┌─────────────────────────────────────────────┐    │   │   │
│  │  │  │ Survivor 0 (S0): Objects surviving GC        │    │   │   │
│  │  │  └─────────────────────────────────────────────┘    │   │   │
│  │  │  ┌─────────────────────────────────────────────┐    │   │   │
│  │  │  │ Survivor 1 (S1): Objects surviving S0 GC     │    │   │   │
│  │  │  └─────────────────────────────────────────────┘    │   │   │
│  │  └─────────────────────────────────────────────────────┘   │   │
│  │  ┌─────────────────────────────────────────────────────┐   │   │
│  │  │  OLD GENERATION (Tenured) (2/3)                      │   │   │
│  │  │  - Long-lived POJOs promoted from Survivor         │   │   │
│  │  │  - POJOs cached in static collections              │   │   │
│  │  │  - POJOs referenced by singletons                  │   │   │
│  │  └─────────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  METASPACE (Java 8+) / PERMGEN (Java 7)                      │   │
│  │  - Class metadata (Employee.class, Address.class)           │   │
│  │  - Method metadata (getters, setters, constructors)         │   │
│  │  - Static variables (if any in POJO class)                  │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  STRING POOL (in Heap, but special area)                   │   │
│  │  - String literals: "John", "Engineering"                  │   │
│  │  - Created via intern() method                             │   │
│  │  - Reused across the application                           │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### POJO Object Creation Memory Flow

```
┌─────────────────────────────────────────────────────────────────────┐
│            POJO OBJECT CREATION — MEMORY ALLOCATION FLOW               │
│                                                                      │
│  Code:                                                               │
│  Employee emp = new Employee();                                      │
│  emp.setName("John");                                                │
│  emp.setSalary(50000.0);                                             │
│                                                                      │
│  Step 1: Class Loading (Metaspace)                                   │
│  ─────────────────────────────────                                   │
│  - Employee.class metadata loaded into Metaspace                     │
│  - Method metadata: getName(), setName(), getSalary(), setSalary()  │
│  - Field metadata: name (String), salary (double)                    │
│                                                                      │
│  Step 2: Object Creation (Heap — Eden)                             │
│  ─────────────────────────────────────                               │
│  - new Employee() allocates memory in Eden space                     │
│  - Object header: 12 bytes (mark word + class pointer)               │
│  - Fields: name (4 bytes reference) + salary (8 bytes double)        │
│  - Alignment padding: 4 bytes (to 8-byte boundary)                    │
│  - Total object size: ~24 bytes (on 64-bit JVM with compressed OOPs)│
│                                                                      │
│  Step 3: Reference Stored (Stack)                                    │
│  ─────────────────────────────────                                   │
│  - emp (local variable) stored in stack frame                        │
│  - emp holds the memory address (reference) of the heap object       │
│                                                                      │
│  Step 4: String Assignment (Heap — String Pool)                      │
│  ─────────────────────────────────────                               │
│  - "John" is a String literal                                      │
│  - If not already in pool, created in String Pool (heap)              │
│  - name field in Employee object points to "John" in pool          │
│                                                                      │
│  Step 5: Primitive Assignment (Heap)                               │
│  ─────────────────────────────────                                   │
│  - salary field (double) stores 50000.0 directly in the object     │
│  - Primitives are stored inside the object, not referenced         │
│                                                                      │
│  Memory Layout:                                                      │
│  Stack: emp -> [reference: 0x12345678]                              │
│  Heap:   0x12345678 -> [Employee object]                           │
│          - name: reference -> "John" (in String Pool)                │
│          - salary: 50000.0 (primitive, stored inline)               │
└─────────────────────────────────────────────────────────────────────┘
```

### Object Reference vs Primitive in POJO

```java
public class MemoryLayoutDemo {
    public static void main(String[] args) {
        // Primitive fields are stored INSIDE the object
        // Reference fields store a pointer to another object
        
        Employee emp = new Employee();
        // emp (reference) is on Stack
        // Employee object is on Heap
        
        emp.setId(1);        // int: stored directly in object (4 bytes)
        emp.setName("John"); // String: reference stored in object (4/8 bytes)
                             // "John" String object is in Heap (String Pool)
        
        // If we create another object:
        Employee emp2 = new Employee();
        emp2.setName("John"); // "John" is reused from String Pool (same object)
        
        System.out.println(emp.getName() == emp2.getName()); // true (same reference)
    }
}
```

### Nested Object Memory Allocation

```java
public class NestedMemoryDemo {
    public static void main(String[] args) {
        // Nested objects create a reference chain in heap
        
        EmployeeWithAddress emp = new EmployeeWithAddress();
        emp.setId(1);
        emp.setName("John");
        
        Address addr = new Address();
        addr.setCity("New York");
        emp.setAddress(addr);
        
        // Memory layout:
        // Stack: emp -> [reference to EmployeeWithAddress]
        // Heap:  EmployeeWithAddress object
        //        - id: 1 (primitive)
        //        - name: reference -> "John" (String Pool)
        //        - address: reference -> Address object
        //
        // Heap:  Address object
        //        - street: null
        //        - city: reference -> "New York" (String Pool)
        //        - zipCode: null
        //        - country: null
    }
}
```

---

## 9. Garbage Collection Impact During API Testing

### GC and API Testing

```java
// API Testing creates many short-lived objects:
// - POJOs for request/response payloads
// - JSON strings during serialization/deserialization
// - HttpResponse objects
// - Assertion objects
// - Test report objects
//
// These objects are typically created and discarded within a single test method.
// They are allocated in Eden space and quickly become eligible for GC.
//
// GC Impact:
// 1. Minor GC runs frequently during large test suites.
// 2. Object creation overhead can slow down test execution.
// 3. Memory pressure can cause test failures (OutOfMemoryError).
// 4. GC pauses (stop-the-world) can affect performance test accuracy.
```

### Minimizing GC Impact in API Tests

```java
// Strategy 1: Reuse ObjectMapper
// ObjectMapper is expensive to create. Reuse it across tests.
public class ApiTestBase {
    protected static final ObjectMapper mapper = new ObjectMapper();
    // Create once, reuse everywhere
}

// Strategy 2: Use Streaming API for Large JSON
// For large JSON payloads, use Jackson Streaming API or JsonParser
// instead of creating full POJOs in memory.

// Strategy 3: Clear Test Data
// Explicitly nullify references after large tests to help GC.
public void testLargeData() {
    List<Employee> employees = loadHugeList();
    // ... perform test ...
    employees = null; // Help GC reclaim memory
}

// Strategy 4: Increase Heap for Load Tests
// java -Xmx4g -jar test-suite.jar

// Strategy 5: Use Off-Heap or File-Based Processing
// For extremely large payloads, process JSON as a stream and write to temp files.
```

### GC Logs During API Testing

```bash
# Enable GC logging to monitor GC behavior during tests:
# Java 8:
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log -jar test.jar

# Java 9+:
java -Xlog:gc*:file=gc.log:time,uptime,level,tags:filecount=5,filesize=10m -jar test.jar

# Analyze GC logs to find:
# - Frequency of Minor GC
# - Promotion rate to Old Generation
# - Full GC events (should be rare in API tests)
```

---

## 10. Methods and Functions in POJO Classes

### Standard POJO Methods

```java
public class EmployeeMethods {
    private int id;
    private String name;
    private double salary;
    
    // 1. No-arg constructor (required for deserialization)
    public EmployeeMethods() {
    }
    
    // 2. Parameterized constructor (convenience)
    public EmployeeMethods(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    
    // 3. Getter methods (read field values)
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // 4. Setter methods (modify field values)
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    // 5. toString() — human-readable representation
    @Override
    public String toString() {
        return "EmployeeMethods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
    
    // 6. equals() — logical equality comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeMethods that = (EmployeeMethods) o;
        return id == that.id &&
                Double.compare(that.salary, salary) == 0 &&
                Objects.equals(name, that.name);
    }
    
    // 7. hashCode() — consistent hash for collections
    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary);
    }
    
    // 8. Custom business methods (optional but useful)
    public double getAnnualSalary() {
        return salary * 12;
    }
    
    public void giveRaise(double percentage) {
        this.salary += this.salary * (percentage / 100);
    }
}
```

### equals() and hashCode() Contract

```java
// The equals() and hashCode() contract:
// 1. If two objects are equal (equals() returns true), they must have the same hashCode().
// 2. If two objects have the same hashCode(), they may or may not be equal.
// 3. hashCode() must remain consistent across multiple invocations (if object unchanged).
//
// Why it matters for POJOs:
// - POJOs are often stored in HashMap, HashSet, and used as keys.
// - If hashCode() is not overridden, default (memory address) is used.
// - Two logically equal objects may be treated as different in HashMap.
//
// Example: Without equals() and hashCode()
Employee e1 = new Employee(1, "John", 50000);
Employee e2 = new Employee(1, "John", 50000);
Set<Employee> set = new HashSet<>();
set.add(e1);
set.add(e2);
System.out.println(set.size()); // 2 (WRONG — logically same employee)
//
// With equals() and hashCode():
// System.out.println(set.size()); // 1 (CORRECT)
```

### toString() for Debugging

```java
// toString() is critical for debugging API tests:
//
// Without toString():
// System.out.println(emp); // Output: Employee@6d06d69c (not useful)
//
// With toString():
// System.out.println(emp); // Output: Employee{id=1, name='John', salary=50000.0}
//
// In test failure logs, toString() output helps identify the exact data.
```

---

## 11. Data Processing Approaches — Tree Model vs Streaming Model

### Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│            JSON DATA PROCESSING APPROACHES IN JAVA                     │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │  APPROACH 1: TREE MODEL (DOM-like)                           │   │
│  │  - Load entire JSON into memory as a tree                   │   │
│  │  - Navigate using methods (get, path, findValue)          │   │
│  │  - Jackson: JsonNode, Gson: JsonElement                   │   │
│  │  - Pros: Easy to use, random access, mutable               │   │
│  │  - Cons: High memory usage, slower for large JSON           │   │
│  │                                                             │   │
│  │  APPROACH 2: STREAMING MODEL (SAX-like)                      │   │
│  │  - Read JSON token by token without loading all in memory   │   │
│  │  - Jackson: JsonParser/JsonGenerator, Gson: JsonReader      │   │
│  │  - Pros: Low memory, fast, handles huge files               │   │
│  │  - Cons: Complex, forward-only, no random access            │   │
│  │                                                             │   │
│  │  APPROACH 3: POJO BINDING (Object Mapping)                   │   │
│  │  - Map JSON directly to Java objects                        │   │
│  │  - Jackson: ObjectMapper, Gson: Gson.fromJson()             │   │
│  │  - Pros: Type-safe, clean code, IDE support                 │   │
│  │  - Cons: Requires POJO classes, memory overhead              │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Tree Model with Jackson (JsonNode)

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class TreeModelExample {
    public static void main(String[] args) throws Exception {
        String json = "{\"id\":1,\"name\":\"John\",\"address\":{\"city\":\"NYC\"}}";
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        
        // Access fields like a tree
        int id = root.get("id").asInt();
        String name = root.get("name").asText();
        String city = root.get("address").get("city").asText();
        
        // Check if field exists
        boolean hasPhone = root.has("phone");
        
        // Navigate with path
        String cityPath = root.path("address").path("city").asText();
        
        // Modify the tree
        ((com.fasterxml.jackson.databind.node.ObjectNode) root).put("age", 30);
        
        // Convert back to JSON string
        String modifiedJson = mapper.writeValueAsString(root);
        System.out.println(modifiedJson);
    }
}
```

### Tree Model with Gson (JsonElement)

```java
import com.google.gson.*;

public class GsonTreeModel {
    public static void main(String[] args) {
        String json = "{\"id\":1,\"name\":\"John\"}";
        
        Gson gson = new Gson();
        JsonElement element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();
        
        int id = object.get("id").getAsInt();
        String name = object.get("name").getAsString();
        
        // Add new field
        object.addProperty("age", 30);
        
        String modified = gson.toJson(object);
        System.out.println(modified);
    }
}
```

### Streaming Model with Jackson (JsonParser)

```java
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class StreamingModelExample {
    public static void main(String[] args) throws Exception {
        String json = "{\"id\":1,\"name\":\"John\",\"salary\":50000.0}";
        
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);
        
        String name = null;
        double salary = 0;
        
        // Token-by-token parsing
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            if ("name".equals(fieldName)) {
                parser.nextToken(); // move to value
                name = parser.getText();
            } else if ("salary".equals(fieldName)) {
                parser.nextToken(); // move to value
                salary = parser.getDoubleValue();
            }
        }
        parser.close();
        
        System.out.println("Name: " + name + ", Salary: " + salary);
    }
}
```

### Streaming Model with Gson (JsonReader)

```java
import com.google.gson.stream.JsonReader;
import java.io.StringReader;

public class GsonStreamingModel {
    public static void main(String[] args) throws Exception {
        String json = "{\"id\":1,\"name\":\"John\",\"salary\":50000.0}";
        
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.beginObject();
        
        String name = null;
        double salary = 0;
        
        while (reader.hasNext()) {
            String field = reader.nextName();
            if ("name".equals(field)) {
                name = reader.nextString();
            } else if ("salary".equals(field)) {
                salary = reader.nextDouble();
            } else {
                reader.skipValue(); // skip unknown fields
            }
        }
        reader.endObject();
        reader.close();
        
        System.out.println("Name: " + name + ", Salary: " + salary);
    }
}
```

### When to Use Each Approach

```java
// Tree Model: Use when...
// 1. JSON is small to medium size (< 10 MB)
// 2. You need random access to fields
// 3. You need to modify the JSON structure
// 4. You want simple, readable code
// 5. The JSON structure is dynamic or unknown

// Streaming Model: Use when...
// 1. JSON is very large (> 10 MB, possibly GBs)
// 2. Memory is limited
// 3. You only need a few fields from a huge JSON
// 4. You are processing JSON in real-time (streaming APIs)
// 5. Performance is critical

// POJO Binding: Use when...
// 1. JSON structure is known and stable
// 2. You want type-safe code
// 3. You are building/maintaining API tests
// 4. Code readability is important
// 5. The JSON maps cleanly to domain objects
```

---

## 12. JSON Structure and Data Types

### JSON Fundamentals

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JSON DATA TYPES AND STRUCTURE                       │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. OBJECT   — { }  — Collection of key-value pairs           │   │
│  │ 2. ARRAY    — [ ]  — Ordered list of values                 │   │
│  │ 3. STRING   — "text" — Unicode text                         │   │
│  │ 4. NUMBER   — 42, 3.14 — Integer or floating-point           │   │
│  │ 5. BOOLEAN  — true, false                                    │   │
│  │ 6. NULL     — null                                          │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### JSON Structure Examples

```json
// JSON Object
{
  "id": 1,
  "name": "John Doe",
  "active": true,
  "salary": 50000.50,
  "manager": null
}

// JSON Array
[
  "Java",
  "Python",
  "JavaScript"
]

// Nested Object
{
  "employee": {
    "id": 1,
    "name": "John"
  }
}

// Array of Objects
{
  "employees": [
    {"id": 1, "name": "John"},
    {"id": 2, "name": "Jane"}
  ]
}

// Mixed Array
{
  "data": [1, "text", true, null, {"key": "value"}]
}
```

### JSON to Java Type Mapping

```java
// JSON Type    -> Java Type (Jackson/Gson)
// -----------------------------------------
// object       -> POJO class, Map<String, Object>
// array        -> List<T>, Set<T>, Array[]
// string       -> String
// number(int)  -> Integer, int, Long, long, BigInteger
// number(float)-> Double, double, Float, float, BigDecimal
// boolean      -> Boolean, boolean
// null         -> null, Optional.empty()

// Example mapping:
// JSON: {"id": 1, "name": "John", "active": true, "salary": 50000.0}
// Java: Employee{id=1, name="John", active=true, salary=50000.0}
```

---

## 13. JSON Parsing Techniques and Manipulation

### Reading JSON with Jackson

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonParsingTechniques {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"id\":1,\"name\":\"John\",\"skills\":[\"Java\",\"Python\"]}";
        
        // Technique 1: POJO binding
        Employee emp = mapper.readValue(json, Employee.class);
        
        // Technique 2: Tree model
        JsonNode tree = mapper.readTree(json);
        JsonNode skillsNode = tree.get("skills");
        for (JsonNode skill : skillsNode) {
            System.out.println(skill.asText());
        }
        
        // Technique 3: Map binding
        Map<String, Object> map = mapper.readValue(json, Map.class);
        List<String> skills = (List<String>) map.get("skills");
        
        // Technique 4: JsonPointer (RFC 6901)
        JsonNode nameNode = tree.at("/name");
        JsonNode firstSkill = tree.at("/skills/0");
    }
}
```

### Modifying JSON

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonManipulation {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"id\":1,\"name\":\"John\"}";
        
        // Parse to mutable tree
        ObjectNode root = (ObjectNode) mapper.readTree(json);
        
        // Add field
        root.put("salary", 50000.0);
        root.put("active", true);
        
        // Add nested object
        ObjectNode address = mapper.createObjectNode();
        address.put("city", "NYC");
        root.set("address", address);
        
        // Add array
        ArrayNode skills = mapper.createArrayNode();
        skills.add("Java");
        skills.add("Python");
        root.set("skills", skills);
        
        // Remove field
        root.remove("active");
        
        // Convert back to string
        String modified = mapper.writeValueAsString(root);
        System.out.println(modified);
    }
}
```

### Merging JSON Objects

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMerge {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        String json1 = "{\"id\":1,\"name\":\"John\"}";
        String json2 = "{\"salary\":50000,\"department\":\"Engineering\"}";
        
        ObjectNode node1 = (ObjectNode) mapper.readTree(json1);
        ObjectNode node2 = (ObjectNode) mapper.readTree(json2);
        
        // Merge node2 into node1
        node1.setAll(node2);
        
        System.out.println(mapper.writeValueAsString(node1));
        // Output: {"id":1,"name":"John","salary":50000,"department":"Engineering"}
    }
}
```

### Filtering JSON with Jackson

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonFilter {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"id\":1,\"name\":\"John\",\"password\":\"secret\",\"salary\":50000}";
        
        JsonNode root = mapper.readTree(json);
        ObjectNode filtered = mapper.createObjectNode();
        
        // Copy only allowed fields
        filtered.set("id", root.get("id"));
        filtered.set("name", root.get("name"));
        
        // Exclude sensitive fields (password, salary)
        
        System.out.println(mapper.writeValueAsString(filtered));
        // Output: {"id":1,"name":"John"}
    }
}
```

---

## 14. JSON Schema Validation

### What is JSON Schema?

**JSON Schema** is a declarative language that describes the structure and constraints of a JSON document. It is used to validate that a JSON document conforms to expected rules.

```java
// JSON Schema validation ensures:
// 1. Required fields are present
// 2. Field types are correct (string, number, boolean, etc.)
// 3. String length, number range, and pattern constraints
// 4. Array length and item types
// 5. Nested object structure
```

### JSON Schema Example

```json
// employee-schema.json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "required": ["id", "name"],
  "properties": {
    "id": {
      "type": "integer",
      "minimum": 1
    },
    "name": {
      "type": "string",
      "minLength": 1,
      "maxLength": 100
    },
    "email": {
      "type": "string",
      "format": "email"
    },
    "salary": {
      "type": "number",
      "minimum": 0
    },
    "active": {
      "type": "boolean"
    },
    "address": {
      "type": "object",
      "properties": {
        "city": { "type": "string" },
        "zipCode": { "type": "string", "pattern": "^[0-9]{5}$" }
      }
    },
    "skills": {
      "type": "array",
      "items": { "type": "string" },
      "minItems": 1
    }
  }
}
```

### JSON Schema Validation with Java

```java
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

public class JsonSchemaValidation {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        // JSON to validate
        String json = "{\"id\":1,\"name\":\"John\",\"salary\":50000,\"email\":\"john@example.com\"}";
        JsonNode jsonNode = mapper.readTree(json);
        
        // Load schema
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = factory.getSchema(
            JsonSchemaValidation.class.getResourceAsStream("/employee-schema.json")
        );
        
        // Validate
        Set<ValidationMessage> errors = schema.validate(jsonNode);
        
        if (errors.isEmpty()) {
            System.out.println("JSON is valid!");
        } else {
            System.out.println("Validation errors:");
            for (ValidationMessage error : errors) {
                System.out.println("  " + error.getMessage());
            }
        }
    }
}
```

### Schema Validation Use Cases

```java
// 1. API Contract Testing:
//    Validate that API response matches the expected schema.
//
// 2. Request Validation:
//    Validate incoming JSON payload before processing.
//
// 3. Documentation:
//    JSON Schema serves as living documentation for API contracts.
//
// 4. Code Generation:
//    Generate POJOs from JSON Schema (and vice versa).
//
// 5. Configuration Validation:
//    Validate application configuration files.
```

---

## 15. Interview FAQs (20+ Questions)

### Q1. What is a POJO class?

**Answer:** POJO stands for Plain Old Java Object. It is a simple Java class that does not extend any specific class, implement any specific interface, or use external annotations. It contains private fields and public getter/setter methods. POJOs are used to represent data and are the foundation of JSON mapping in API testing.

### Q2. What is the difference between POJO and JavaBean?

**Answer:** All JavaBeans are POJOs, but not all POJOs are JavaBeans. A JavaBean is a POJO that follows strict conventions:
- Must have a public no-arg constructor.
- All fields must be private.
- Must have public getters and setters for all fields.
- Should implement Serializable.
- Should override toString(), equals(), and hashCode().

### Q3. What is serialization and deserialization in the context of API testing?

**Answer:** Serialization is the process of converting a Java object (POJO) into a JSON string to send as an HTTP request body. Deserialization is the process of converting a JSON string from an HTTP response into a Java object (POJO). These processes are handled by libraries like Jackson or Gson.

### Q4. What is the difference between Jackson and Gson?

**Answer:** Jackson is more feature-rich and performant, widely used in Spring Boot, and supports streaming, tree model, and extensive annotations. Gson is simpler to use, developed by Google, and is often preferred for Android or lightweight projects. Jackson has better performance for large JSON, while Gson is easier for quick tasks.

### Q5. What is ObjectMapper in Jackson?

**Answer:** ObjectMapper is the central class in the Jackson library. It provides methods like `writeValueAsString()` for serialization and `readValue()` for deserialization. It can be configured with features like pretty printing, ignoring unknown properties, custom date formats, and naming strategies.

### Q6. What is Lombok and why is it useful for POJOs?

**Answer:** Lombok is a Java annotation processor that generates boilerplate code at compile time. It provides annotations like `@Data`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, and `@Builder`. It reduces boilerplate code (getters, setters, constructors, toString, equals, hashCode) from hundreds of lines to a few annotations, making POJOs more maintainable.

### Q7. What is the purpose of a no-arg constructor in a POJO?

**Answer:** JSON deserialization libraries (Jackson, Gson) create object instances using reflection. They require a public no-arg constructor to instantiate the object before calling setter methods or setting fields directly. Without a no-arg constructor, deserialization will fail with an exception.

### Q8. What is the difference between the Tree Model and Streaming Model for JSON parsing?

**Answer:** The Tree Model loads the entire JSON into memory as a tree structure (e.g., JsonNode in Jackson, JsonElement in Gson), allowing random access and easy modification. The Streaming Model reads JSON token by token without loading the entire document into memory, making it suitable for very large JSON files with minimal memory usage.

### Q9. How does memory allocation work when creating a POJO object?

**Answer:** When a POJO is created:
- The class metadata is loaded into Metaspace.
- The object is allocated in the Heap (usually Eden space).
- The reference variable is stored in the Stack.
- Primitive fields are stored inside the object.
- Reference fields store pointers to other objects (e.g., String in String Pool).
- If the object survives garbage collection, it is promoted to Survivor and eventually Old Generation.

### Q10. What is the String Pool and how does it relate to POJOs?

**Answer:** The String Pool is a special area in the Heap where Java stores String literals. When a POJO has String fields, the String values may be stored in the String Pool if they are literals. This saves memory because multiple POJOs can share the same String instance. The String Pool is managed by the JVM and is garbage collected when no references exist.

### Q11. How does garbage collection impact API testing?

**Answer:** API testing creates many short-lived objects (POJOs, JSON strings, response objects). These objects are allocated in Eden space and quickly become eligible for GC. Frequent GC can slow down test execution, cause OutOfMemoryError in large test suites, and introduce stop-the-world pauses that affect performance test accuracy. Best practices include reusing ObjectMapper, using streaming APIs for large payloads, and increasing heap size for load tests.

### Q12. Why should we override equals() and hashCode() in POJOs?

**Answer:** The default equals() compares memory addresses (identity), which means two logically equal objects with the same data are considered different. Overriding equals() and hashCode() ensures logical equality. This is critical when POJOs are used in collections like HashMap, HashSet, or when comparing expected vs actual results in tests.

### Q13. What is the difference between GET and POST in terms of serialization?

**Answer:** GET requests typically do not have a request body, so serialization is not needed for the request. However, query parameters may be mapped from POJOs. POST, PUT, and PATCH requests have a request body, so the POJO must be serialized to JSON (or XML) before sending. The response body for any method may be deserialized to a POJO.

### Q14. How do you handle nested JSON objects with POJOs?

**Answer:** Create separate POJO classes for each nested object. For example, an Employee POJO can have an Address field, where Address is another POJO. Jackson and Gson automatically map nested JSON objects to nested POJOs as long as the field names match (or annotations are used to map custom names). Lists and Maps of nested objects are also supported.

### Q15. What is JSON Schema validation and why is it used?

**Answer:** JSON Schema is a declarative language that describes the structure and constraints of a JSON document. It validates that required fields exist, types are correct, strings match patterns, numbers are within ranges, and arrays have minimum items. It is used for API contract testing, request validation, and documentation.

### Q16. What is the `@JsonIgnore` annotation in Jackson?

**Answer:** `@JsonIgnore` tells Jackson to exclude a field from both serialization and deserialization. It is commonly used for sensitive fields like passwords or tokens that should not be included in JSON output. The related `@JsonIgnoreProperties` annotation can ignore multiple fields at the class level.

### Q17. What is the `@SerializedName` annotation in Gson?

**Answer:** `@SerializedName` maps a Java field to a JSON field with a different name. For example, `@SerializedName("employee_id") private int id;` maps the JSON field `employee_id` to the Java field `id`. This is useful when the JSON field names use snake_case or other naming conventions that differ from Java camelCase.

### Q18. What is the Builder pattern and how does Lombok support it?

**Answer:** The Builder pattern is a creational pattern that constructs complex objects step by step. It is useful for POJOs with many optional fields. Lombok provides the `@Builder` annotation, which automatically generates a builder class. Usage: `Employee.builder().id(1).name("John").salary(50000).build();`.

### Q19. What is the difference between `@Data` and `@Value` in Lombok?

**Answer:** `@Data` generates mutable POJOs with getters, setters, toString, equals, and hashCode. `@Value` generates immutable POJOs: all fields are private and final, no setters are generated, and the class is made final. `@Value` is useful for creating immutable data transfer objects (DTOs).

### Q20. How do you handle null values in JSON serialization?

**Answer:** Jackson includes null fields by default. To exclude them, use `@JsonInclude(JsonInclude.Include.NON_NULL)` on the class or field. Gson also includes nulls by default; use `@Expose(serialize = false, deserialize = false)` or register a custom exclusion strategy. Excluding nulls reduces JSON payload size and avoids sending unnecessary fields.

### Q21. What is the difference between `TypeReference` and `Class` in Jackson deserialization?

**Answer:** `Class` is used for simple deserialization (e.g., `Employee.class`). `TypeReference` is used for generic types like `List<Employee>` or `Map<String, Employee>` because Java type erasure removes generic type information at runtime. `TypeReference` preserves the generic type information for Jackson to use.

### Q22. How do you customize JSON field names without changing Java field names?

**Answer:** Use Jackson's `@JsonProperty("custom_name")` or Gson's `@SerializedName("custom_name")` on the field. Alternatively, configure a naming strategy globally in ObjectMapper: `mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)` converts `firstName` to `first_name` automatically.

### Q23. What is the Streaming API and when should it be used?

**Answer:** The Streaming API (JsonParser in Jackson, JsonReader in Gson) reads JSON token by token without creating a full object tree in memory. It should be used when processing very large JSON files (hundreds of MB or GB), when memory is limited, or when only a few fields are needed from a large JSON document. It is faster and more memory-efficient than the Tree Model or POJO binding.

### Q24. What is the difference between `Map<String, Object>` and POJO for JSON parsing?

**Answer:** `Map<String, Object>` is flexible and requires no class definition, but it is not type-safe. You must cast values manually, which is error-prone and reduces IDE support. POJOs provide type safety, compile-time checking, code completion, and cleaner code. Use POJOs for known structures and Maps for dynamic or unknown structures.

### Q25. What are common mistakes when creating POJOs for JSON mapping?

**Answer:**
1. Missing no-arg constructor (deserialization fails).
2. Incorrect getter/setter naming (Jackson/Gson cannot map fields).
3. Using different field names in JSON and Java without annotations.
4. Forgetting to override equals() and hashCode() for test assertions.
5. Not handling null values properly (NullPointerException in tests).
6. Using primitive types for fields that can be null in JSON (use wrapper types like Integer).
7. Not marking sensitive fields with `@JsonIgnore`.
8. Creating deeply nested POJOs without proper separation of concerns.

### Q26. What is the purpose of `serialVersionUID` in a POJO?

**Answer:** While `serialVersionUID` is primarily for Java Serialization (`java.io.Serializable`), it is good practice to include it in POJOs that implement Serializable. It ensures version compatibility when objects are serialized and deserialized. If the class structure changes, the same `serialVersionUID` ensures that old serialized objects can still be read.

### Q27. How do you validate JSON against a schema in Java?

**Answer:** Use a JSON Schema validator library like `networknt/json-schema-validator` or `everit-org/json-schema`. Load the JSON Schema file, parse the JSON to validate, and call the validate method. The validator returns a set of validation messages describing any errors (missing fields, wrong types, pattern violations, etc.).

---

## 16. Key Takeaways

1. **POJO** is a simple Java class with private fields and getters/setters. It is the foundation of JSON mapping in API testing.
2. **JavaBean conventions** require a public no-arg constructor, private fields, public getters/setters, and ideally `Serializable`.
3. **Serialization** converts Java objects to JSON strings. **Deserialization** converts JSON strings to Java objects.
4. **Jackson** is the most popular JSON library for Java. The `ObjectMapper` class handles serialization and deserialization with extensive configuration.
5. **Gson** is Google's simpler JSON library. It is easier to use but less configurable than Jackson.
6. **Lombok** eliminates boilerplate code with annotations like `@Data`, `@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, and `@AllArgsConstructor`.
7. **Nested POJOs** are created by defining separate classes for nested objects and using them as fields in parent POJOs.
8. **Memory allocation** for POJOs: Stack stores references, Heap stores objects, Metaspace stores class metadata, String Pool stores String literals.
9. **Garbage Collection** runs frequently during API testing due to many short-lived objects. Reuse `ObjectMapper` and use streaming APIs for large JSON.
10. **Standard methods** in POJOs: getters, setters, constructors, `toString()`, `equals()`, and `hashCode()`.
11. **Tree Model** loads JSON into memory as a tree. Easy to use but memory-intensive.
12. **Streaming Model** reads JSON token by token. Memory-efficient and fast but complex to use.
13. **JSON data types**: object, array, string, number, boolean, null.
14. **JSON parsing techniques**: POJO binding, Tree Model, Map binding, JsonPointer, and Streaming API.
15. **JSON Schema** validates JSON structure, types, constraints, and patterns. It is essential for API contract testing.
16. **Always include a no-arg constructor** in POJOs used for deserialization.
17. **Override `equals()` and `hashCode()`** for logical comparison and collection usage.
18. **Use `@JsonIgnore`** or `@Expose` to exclude sensitive fields from JSON.
19. **Use `TypeReference`** for deserializing generic types like `List<Employee>`.
20. **Use Lombok `@Builder`** for constructing POJOs with many optional fields.

---

**Happy coding!**

*Master POJOs and JSON to build robust, type-safe, and maintainable API test suites!*
