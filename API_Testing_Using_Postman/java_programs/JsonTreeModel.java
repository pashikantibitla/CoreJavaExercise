package java_programs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * JsonTreeModel.java
 * 
 * Purpose: Demonstrates the Tree Model approach for JSON parsing using Jackson.
 *          The Tree Model loads the entire JSON into memory as a tree of JsonNode objects.
 * 
 * Advantages:
 * - Easy to navigate and modify JSON structure
 * - Random access to any field
 * - No need to define POJO classes
 * 
 * Disadvantages:
 * - High memory usage for large JSON
 * - Slower than Streaming Model for huge files
 * 
 * Use cases:
 * - Small to medium JSON payloads
 * - When JSON structure is dynamic or unknown
 * - When you need to modify JSON before sending
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class JsonTreeModel {
    
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        // ============================================================
        // PART 1: Parse JSON string to tree
        // ============================================================
        
        System.out.println("=== TREE MODEL PARSING ===\n");
        
        String json = "{\"id\":1,\"name\":\"John\",\"salary\":50000.0,\"skills\":[\"Java\",\"REST\"],\"address\":{\"city\":\"NYC\",\"zip\":\"10001\"}}";
        
        JsonNode root = mapper.readTree(json);
        
        // ============================================================
        // PART 2: Navigate tree using getters
        // ============================================================
        
        System.out.println("--- Navigate Tree ---\n");
        
        int id = root.get("id").asInt();
        String name = root.get("name").asText();
        double salary = root.get("salary").asDouble();
        
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        
        // Navigate nested object
        JsonNode addressNode = root.get("address");
        String city = addressNode.get("city").asText();
        String zip = addressNode.get("zip").asText();
        System.out.println("City: " + city);
        System.out.println("Zip: " + zip);
        
        // Navigate array
        JsonNode skillsNode = root.get("skills");
        System.out.println("Skills count: " + skillsNode.size());
        for (JsonNode skill : skillsNode) {
            System.out.println("  - " + skill.asText());
        }
        
        // ============================================================
        // PART 3: Check existence and type
        // ============================================================
        
        System.out.println("\n--- Check Existence and Type ---\n");
        
        boolean hasPhone = root.has("phone");
        boolean hasName = root.has("name");
        boolean isNameText = root.get("name").isTextual();
        boolean isIdNumber = root.get("id").isNumber();
        
        System.out.println("Has phone: " + hasPhone);
        System.out.println("Has name: " + hasName);
        System.out.println("Name is text: " + isNameText);
        System.out.println("ID is number: " + isIdNumber);
        
        // ============================================================
        // PART 4: Navigate with path (safe navigation)
        // ============================================================
        
        System.out.println("\n--- Path Navigation ---\n");
        
        String cityPath = root.path("address").path("city").asText("Unknown");
        String countryPath = root.path("address").path("country").asText("Unknown");
        
        System.out.println("City (via path): " + cityPath);
        System.out.println("Country (via path, default): " + countryPath);
        
        // ============================================================
        // PART 5: Modify tree (add, update, remove)
        // ============================================================
        
        System.out.println("\n--- Modify Tree ---\n");
        
        // Cast to ObjectNode for modification
        ObjectNode rootObject = (ObjectNode) root;
        
        // Add new field
        rootObject.put("department", "Engineering");
        
        // Update existing field
        rootObject.put("salary", 55000.0);
        
        // Add nested object
        ObjectNode manager = mapper.createObjectNode();
        manager.put("name", "Sarah");
        manager.put("title", "Director");
        rootObject.set("manager", manager);
        
        // Add array
        ArrayNode projects = mapper.createArrayNode();
        projects.add("Project A");
        projects.add("Project B");
        rootObject.set("projects", projects);
        
        // Remove field
        rootObject.remove("zip"); // Remove from address if needed, but here we remove from root
        
        // Convert back to JSON
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);
        String modifiedJson = mapper.writeValueAsString(rootObject);
        System.out.println("Modified JSON:\n" + modifiedJson);
        
        // ============================================================
        // PART 6: JsonPointer navigation (RFC 6901)
        // ============================================================
        
        System.out.println("\n--- JsonPointer Navigation ---\n");
        
        JsonNode namePtr = root.at("/name");
        JsonNode cityPtr = root.at("/address/city");
        JsonNode firstSkill = root.at("/skills/0");
        
        System.out.println("Name (pointer): " + namePtr.asText());
        System.out.println("City (pointer): " + cityPtr.asText());
        System.out.println("First Skill (pointer): " + firstSkill.asText());
        
        System.out.println("\n=== Tree Model Example Complete ===");
    }
}
