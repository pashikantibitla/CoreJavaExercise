package java_programs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * EmployeeGsonSerialization.java
 * 
 * Purpose: Demonstrates Gson serialization and deserialization.
 * 
 * Required Maven dependency:
 * <dependency>
 *     <groupId>com.google.code.gson</groupId>
 *     <artifactId>gson</artifactId>
 *     <version>2.10.1</version>
 * </dependency>
 * 
 * Methods demonstrated:
 * - toJson(): Serialize Java object to JSON string
 * - fromJson(String, Class): Deserialize JSON to single object
 * - fromJson(String, TypeToken): Deserialize JSON to generic types
 * - GsonBuilder.setPrettyPrinting(): Pretty print JSON
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class EmployeeGsonSerialization {
    
    public static void main(String[] args) {
        // Create Gson instance
        Gson gson = new Gson();
        
        // ============================================================
        // PART 1: Serialization (Java Object -> JSON String)
        // ============================================================
        
        System.out.println("=== SERIALIZATION ===\n");
        
        // Create a POJO
        EmployeePOJO emp1 = new EmployeePOJO(1, "Alice", 75000.0, "Engineering", true);
        
        // Serialize to JSON string
        String json = gson.toJson(emp1);
        System.out.println("JSON: " + json);
        
        // Pretty print with GsonBuilder
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(emp1);
        System.out.println("\nPretty JSON:\n" + prettyJson);
        
        // ============================================================
        // PART 2: Deserialization (JSON String -> Java Object)
        // ============================================================
        
        System.out.println("\n=== DESERIALIZATION ===\n");
        
        String jsonString = "{\"id\":2,\"name\":\"Bob\",\"salary\":65000.0,\"department\":\"Sales\",\"active\":false}";
        
        // Deserialize to single object
        EmployeePOJO emp2 = gson.fromJson(jsonString, EmployeePOJO.class);
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
        
        // Use TypeToken for generic types
        List<EmployeePOJO> employees = gson.fromJson(jsonArray, new TypeToken<List<EmployeePOJO>>(){}.getType());
        
        System.out.println("Number of employees: " + employees.size());
        for (EmployeePOJO emp : employees) {
            System.out.println("  - " + emp.getName() + " (" + emp.getDepartment() + ")");
        }
        
        // ============================================================
        // PART 4: Deserialize to Map
        // ============================================================
        
        System.out.println("\n=== DESERIALIZE TO MAP ===\n");
        
        java.util.Map<String, Object> map = gson.fromJson(jsonString, new TypeToken<java.util.Map<String, Object>>(){}.getType());
        System.out.println("Map: " + map);
        System.out.println("Name from map: " + map.get("name"));
        
        // ============================================================
        // PART 5: Gson null handling
        // ============================================================
        
        System.out.println("\n=== NULL HANDLING ===\n");
        
        EmployeePOJO emp3 = new EmployeePOJO();
        emp3.setId(3);
        emp3.setName("Charlie");
        
        String nullJson = gson.toJson(emp3);
        System.out.println("JSON with nulls: " + nullJson);
        
        System.out.println("\n=== Gson Serialization and Deserialization Complete ===");
    }
}
