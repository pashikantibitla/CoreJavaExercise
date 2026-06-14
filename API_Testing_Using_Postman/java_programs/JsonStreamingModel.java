package java_programs;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * JsonStreamingModel.java
 * 
 * Purpose: Demonstrates the Streaming Model approach for JSON parsing using Jackson.
 *          The Streaming Model reads JSON token by token without loading the entire document into memory.
 * 
 * Advantages:
 * - Extremely low memory usage (constant regardless of JSON size)
 * - Very fast for large files
 * - Suitable for real-time processing
 * 
 * Disadvantages:
 * - Complex code (forward-only, no random access)
 * - Hard to modify JSON structure
 * - Manual state management
 * 
 * Use cases:
 * - Very large JSON files (hundreds of MB or GB)
 * - Limited memory environments
 * - When only a few fields are needed from a huge JSON
 * - Streaming APIs and real-time data processing
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class JsonStreamingModel {
    
    public static void main(String[] args) throws Exception {
        
        // ============================================================
        // PART 1: Parse JSON using JsonParser (Streaming API)
        // ============================================================
        
        System.out.println("=== STREAMING MODEL PARSING ===\n");
        
        String json = "{\"id\":1,\"name\":\"John\",\"salary\":50000.0,\"active\":true,\"skills\":[\"Java\",\"REST\",\"SOAP\"]}";
        
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);
        
        // Variables to hold extracted values
        int id = 0;
        String name = null;
        double salary = 0.0;
        boolean active = false;
        java.util.List<String> skills = new java.util.ArrayList<>();
        
        System.out.println("--- Token-by-Token Parsing ---\n");
        
        // Loop through tokens until end of JSON
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            
            // Get current field name
            String fieldName = parser.getCurrentName();
            if (fieldName == null) continue;
            
            System.out.println("Token: " + parser.currentToken() + ", Field: " + fieldName);
            
            // Move to the value token
            parser.nextToken();
            
            switch (fieldName) {
                case "id":
                    id = parser.getIntValue();
                    break;
                case "name":
                    name = parser.getText();
                    break;
                case "salary":
                    salary = parser.getDoubleValue();
                    break;
                case "active":
                    active = parser.getBooleanValue();
                    break;
                case "skills":
                    // Parse array
                    while (parser.nextToken() != JsonToken.END_ARRAY) {
                        skills.add(parser.getText());
                    }
                    break;
                default:
                    // Skip unknown fields
                    parser.skipChildren();
                    break;
            }
        }
        
        parser.close();
        
        // ============================================================
        // PART 2: Display extracted values
        // ============================================================
        
        System.out.println("\n--- Extracted Values ---\n");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Active: " + active);
        System.out.println("Skills: " + skills);
        
        // ============================================================
        // PART 3: Streaming with large JSON simulation
        // ============================================================
        
        System.out.println("\n--- Streaming Large JSON Simulation ---\n");
        
        // Simulate a large JSON with many records
        StringBuilder largeJson = new StringBuilder();
        largeJson.append("{");
        largeJson.append("\"total\":1000,");
        largeJson.append("\"employees\":[");
        for (int i = 1; i <= 1000; i++) {
            if (i > 1) largeJson.append(",");
            largeJson.append("{\"id\":");
            largeJson.append(i);
            largeJson.append(",\"name\":\"Employee");
            largeJson.append(i);
            largeJson.append("\"}");
        }
        largeJson.append("]}");
        
        // Parse only the 'total' field and count records
        // We do NOT load all 1000 employee objects into memory
        JsonParser largeParser = factory.createParser(largeJson.toString());
        
        int total = 0;
        int recordCount = 0;
        
        while (largeParser.nextToken() != null) {
            String field = largeParser.getCurrentName();
            if ("total".equals(field)) {
                largeParser.nextToken();
                total = largeParser.getIntValue();
            } else if ("employees".equals(field)) {
                // Count array elements without loading them
                largeParser.nextToken(); // START_ARRAY
                while (largeParser.nextToken() != JsonToken.END_ARRAY) {
                    if (largeParser.currentToken() == JsonToken.START_OBJECT) {
                        recordCount++;
                    }
                    largeParser.skipChildren();
                }
            }
        }
        
        largeParser.close();
        
        System.out.println("Total from JSON: " + total);
        System.out.println("Records counted (streaming): " + recordCount);
        System.out.println("Memory used: minimal (only two integers stored, not 1000 objects)");
        
        // ============================================================
        // PART 4: Comparison with Tree Model memory
        // ============================================================
        
        System.out.println("\n--- Memory Comparison ---\n");
        
        Runtime runtime = Runtime.getRuntime();
        
        // Measure before tree model
        long beforeTree = runtime.totalMemory() - runtime.freeMemory();
        
        // Load large JSON into tree model
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode tree = mapper.readTree(largeJson.toString());
        
        long afterTree = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Tree Model memory used: ~" + (afterTree - beforeTree) / 1024 + " KB");
        System.out.println("Tree Model node count: " + tree.at("/employees").size());
        
        System.out.println("\nStreaming Model would use: ~0 KB (constant memory)");
        System.out.println("Streaming Model is ideal for large JSON files.");
        
        System.out.println("\n=== Streaming Model Example Complete ===");
    }
}
