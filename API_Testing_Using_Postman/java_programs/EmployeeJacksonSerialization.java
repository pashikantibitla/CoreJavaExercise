package java_programs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

/**
 * EmployeeJacksonSerialization.java
 * 
 * Purpose: Demonstrates Jackson serialization and deserialization.
 * 
 * Required Maven dependency:
 * <dependency>
 *     <groupId>com.fasterxml.jackson.core</groupId>
 *     <artifactId>jackson-databind</artifactId>
 *     <version>2.15.2</version>
 * </dependency>
 * 
 * Methods demonstrated:
 * - writeValueAsString(): Serialize Java object to JSON string
 * - readValue(String, Class): Deserialize JSON to single object
 * - readValue(String, TypeReference): Deserialize JSON to generic types (List, Map)
 * - enable(SerializationFeature.INDENT_OUTPUT): Pretty print JSON
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class EmployeeJacksonSerialization {
    
    public static void main(String[] args) throws Exception {
        // Create ObjectMapper instance
        // ObjectMapper is expensive to create; reuse it in production code
        ObjectMapper mapper = new ObjectMapper();
        
        // ============================================================
        // PART 1: Serialization (Java Object -> JSON String)
        // ============================================================
        
        System.out.println("=== SERIALIZATION ===\n");
        
        // Create a POJO
        EmployeePOJO emp1 = new EmployeePOJO(1, "Alice", 75000.0, "Engineering", true);
        
        // Serialize to compact JSON string
        String compactJson = mapper.writeValueAsString(emp1);
        System.out.println("Compact JSON: " + compactJson);
        
        // Enable pretty printing for readable output
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String prettyJson = mapper.writeValueAsString(emp1);
        System.out.println("\nPretty JSON:\n" + prettyJson);
        
        // ============================================================
        // PART 2: Deserialization (JSON String -> Java Object)
        // ============================================================
        
        System.out.println("\n=== DESERIALIZATION ===\n");
        
        String json = "{\"id\":2,\"name\":\"Bob\",\"salary\":65000.0,\"department\":\"Sales\",\"active\":false}";
        
        // Deserialize to single object
        EmployeePOJO emp2 = mapper.readValue(json, EmployeePOJO.class);
        System.out.println("Deserialized Employee: " + emp2);
        System.out.println("Name: " + emp2.getName());
        System.out.println("Department: " + emp2.getDepartment());
        
        // ============================================================
        // PART 3: Deserialize to List
        // ============================================================
        
        System.out.println("\n=== DESERIALIZE TO LIST ===\n");
        
        String jsonArray = "[" +
                "{\"id\":1,\"name\":\"Alice\",\"salary\":75000.0,\"department\":\"Engineering\",\"active\":true}," +
                "{\"id\":2,\"name\":\"Bob\",\"salary\":65000.0,\"department\":\"Sales\",\"active\":false}" +
                "]";
        
        // Use TypeReference to preserve generic type information at runtime
        List<EmployeePOJO> employees = mapper.readValue(jsonArray, new TypeReference<List<EmployeePOJO>>() {});
        
        System.out.println("Number of employees: " + employees.size());
        for (EmployeePOJO emp : employees) {
            System.out.println("  - " + emp.getName() + " (" + emp.getDepartment() + ")");
        }
        
        // ============================================================
        // PART 4: Deserialize to Map
        // ============================================================
        
        System.out.println("\n=== DESERIALIZE TO MAP ===\n");
        
        java.util.Map<String, Object> map = mapper.readValue(json, new TypeReference<java.util.Map<String, Object>>() {});
        System.out.println("Map: " + map);
        System.out.println("Name from map: " + map.get("name"));
        
        // ============================================================
        // PART 5: Serialization with null values
        // ============================================================
        
        System.out.println("\n=== NULL HANDLING ===\n");
        
        EmployeePOJO emp3 = new EmployeePOJO();
        emp3.setId(3);
        emp3.setName("Charlie");
        // department and salary are null / 0.0
        
        String nullJson = mapper.writeValueAsString(emp3);
        System.out.println("JSON with nulls: " + nullJson);
        
        System.out.println("\n=== Serialization and Deserialization Complete ===");
    }
}
