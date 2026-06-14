package java_programs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

/**
 * JsonSchemaValidation.java
 * 
 * Purpose: Demonstrates JSON Schema validation using the networknt/json-schema-validator library.
 *          JSON Schema ensures that JSON payloads conform to expected structure, types, and constraints.
 * 
 * Required Maven dependency:
 * <dependency>
 *     <groupId>com.networknt</groupId>
 *     <artifactId>json-schema-validator</artifactId>
 *     <version>1.0.86</version>
 * </dependency>
 * 
 * Validation demonstrated:
 * - Required fields
 * - Data types (string, integer, number, boolean)
 * - String length and pattern constraints
 * - Numeric range constraints
 * - Array minimum items
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class JsonSchemaValidation {
    
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        // ============================================================
        // PART 1: Define JSON Schema
        // ============================================================
        
        System.out.println("=== JSON SCHEMA VALIDATION ===\n");
        
        String schemaJson = "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"required\": [\"id\", \"name\", \"email\"],\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"integer\",\n" +
                "      \"minimum\": 1\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"minLength\": 1,\n" +
                "      \"maxLength\": 100\n" +
                "    },\n" +
                "    \"email\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"format\": \"email\"\n" +
                "    },\n" +
                "    \"salary\": {\n" +
                "      \"type\": \"number\",\n" +
                "      \"minimum\": 0\n" +
                "    },\n" +
                "    \"active\": {\n" +
                "      \"type\": \"boolean\"\n" +
                "    },\n" +
                "    \"skills\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": { \"type\": \"string\" },\n" +
                "      \"minItems\": 1\n" +
                "    }\n" +
                "  }\n" +
                "}";
        
        // ============================================================
        // PART 2: Create schema validator
        // ============================================================
        
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = factory.getSchema(schemaJson);
        
        // ============================================================
        // PART 3: Validate valid JSON
        // ============================================================
        
        System.out.println("--- Test 1: Valid JSON ---\n");
        
        String validJson = "{\"id\":1,\"name\":\"John\",\"email\":\"john@example.com\",\"salary\":50000,\"active\":true,\"skills\":[\"Java\"]}";
        JsonNode validNode = mapper.readTree(validJson);
        
        Set<ValidationMessage> errors = schema.validate(validNode);
        
        if (errors.isEmpty()) {
            System.out.println("Result: VALID ✓");
        } else {
            System.out.println("Result: INVALID ✗");
            for (ValidationMessage error : errors) {
                System.out.println("  Error: " + error.getMessage());
            }
        }
        
        // ============================================================
        // PART 4: Validate JSON with missing required field
        // ============================================================
        
        System.out.println("\n--- Test 2: Missing Required Field ---\n");
        
        String missingRequired = "{\"id\":1,\"name\":\"John\",\"salary\":50000}"; // email is missing
        JsonNode missingNode = mapper.readTree(missingRequired);
        
        errors = schema.validate(missingNode);
        
        if (errors.isEmpty()) {
            System.out.println("Result: VALID ✓");
        } else {
            System.out.println("Result: INVALID ✗");
            for (ValidationMessage error : errors) {
                System.out.println("  Error: " + error.getMessage());
            }
        }
        
        // ============================================================
        // PART 5: Validate JSON with wrong type
        // ============================================================
        
        System.out.println("\n--- Test 3: Wrong Data Type ---\n");
        
        String wrongType = "{\"id\":\"abc\",\"name\":\"John\",\"email\":\"john@example.com\"}"; // id should be integer
        JsonNode wrongTypeNode = mapper.readTree(wrongType);
        
        errors = schema.validate(wrongTypeNode);
        
        if (errors.isEmpty()) {
            System.out.println("Result: VALID ✓");
        } else {
            System.out.println("Result: INVALID ✗");
            for (ValidationMessage error : errors) {
                System.out.println("  Error: " + error.getMessage());
            }
        }
        
        // ============================================================
        // PART 6: Validate JSON with constraint violation
        // ============================================================
        
        System.out.println("\n--- Test 4: Constraint Violation ---\n");
        
        String constraintViolation = "{\"id\":0,\"name\":\"\",\"email\":\"invalid-email\",\"salary\":-1000,\"skills\":[]}"; // multiple violations
        JsonNode constraintNode = mapper.readTree(constraintViolation);
        
        errors = schema.validate(constraintNode);
        
        if (errors.isEmpty()) {
            System.out.println("Result: VALID ✓");
        } else {
            System.out.println("Result: INVALID ✗");
            for (ValidationMessage error : errors) {
                System.out.println("  Error: " + error.getMessage());
            }
        }
        
        // ============================================================
        // PART 7: Validate JSON with valid email format
        // ============================================================
        
        System.out.println("\n--- Test 5: Valid Email Format ---\n");
        
        String validEmail = "{\"id\":2,\"name\":\"Jane\",\"email\":\"jane.doe@company.co.uk\"}";
        JsonNode validEmailNode = mapper.readTree(validEmail);
        
        errors = schema.validate(validEmailNode);
        
        if (errors.isEmpty()) {
            System.out.println("Result: VALID ✓");
        } else {
            System.out.println("Result: INVALID ✗");
            for (ValidationMessage error : errors) {
                System.out.println("  Error: " + error.getMessage());
            }
        }
        
        // ============================================================
        // PART 8: API Testing Context
        // ============================================================
        
        System.out.println("\n--- API Testing Context ---\n");
        
        System.out.println("JSON Schema validation is used in API testing for:");
        System.out.println("  1. Contract Testing — ensure response matches expected schema");
        System.out.println("  2. Request Validation — validate incoming payloads");
        System.out.println("  3. Documentation — schema serves as living API documentation");
        System.out.println("  4. Code Generation — generate POJOs from schema (and vice versa)");
        System.out.println("  5. Configuration Validation — validate config files");
        
        System.out.println("\n=== JSON Schema Validation Example Complete ===");
    }
}
