import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.List;
import java.util.Map;

/**
 * JsonUtils.java
 *
 * JSON manipulation utility class.
 * Provides common operations for JSON serialization, deserialization,
 * parsing, validation, and modification using Jackson and JsonPath.
 *
 * All methods are static for easy access across the framework.
 *
 * Prerequisites:
 * - com.fasterxml.jackson.core:jackson-databind
 * - com.jayway.jsonpath:json-path
 */
public class JsonUtils {

    // Reusable ObjectMapper with common configurations
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Do not include null values in serialized output
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Do not fail on unknown properties during deserialization
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Enable pretty printing for readability
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Serialize a Java object to a JSON string.
     *
     * @param object the object to serialize
     * @return JSON string representation
     * @throws RuntimeException if serialization fails
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    /**
     * Deserialize a JSON string to a Java object of the specified class.
     *
     * @param json the JSON string
     * @param clazz the target class
     * @param <T> the type of the target class
     * @return the deserialized object
     * @throws RuntimeException if deserialization fails
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to " + clazz.getSimpleName(), e);
        }
    }

    /**
     * Deserialize a JSON string to a complex type using TypeReference.
     * Useful for generic types like List<User> or Map<String, Object>.
     *
     * @param json the JSON string
     * @param typeReference the TypeReference for the target type
     * @param <T> the type
     * @return the deserialized object
     * @throws RuntimeException if deserialization fails
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }

    /**
     * Parse a JSON string into a JsonNode tree.
     *
     * @param json the JSON string
     * @return the root JsonNode
     * @throws RuntimeException if parsing fails
     */
    public static JsonNode parseJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON string", e);
        }
    }

    /**
     * Extract a value from JSON using a JsonPath expression.
     *
     * @param json the JSON string
     * @param path the JsonPath expression
     * @param <T> the expected type of the extracted value
     * @return the extracted value
     */
    public static <T> T getJsonValue(String json, String path) {
        return JsonPath.read(json, path);
    }

    /**
     * Extract a list of values from JSON using a JsonPath expression.
     *
     * @param json the JSON string
     * @param path the JsonPath expression
     * @param <T> the type of list items
     * @return the list of extracted values
     */
    public static <T> List<T> getJsonList(String json, String path) {
        return JsonPath.read(json, path);
    }

    /**
     * Extract a Map from JSON using a JsonPath expression.
     *
     * @param json the JSON string
     * @param path the JsonPath expression
     * @return the extracted map
     */
    public static Map<String, Object> getJsonMap(String json, String path) {
        return JsonPath.read(json, path);
    }

    /**
     * Validate if a string is valid JSON.
     *
     * @param json the string to validate
     * @return true if valid JSON, false otherwise
     */
    public static boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    /**
     * Pretty print a JSON string with indentation.
     *
     * @param json the JSON string to format
     * @return the formatted JSON string
     */
    public static String prettyPrint(String json) {
        try {
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            return json; // Return original if parsing fails
        }
    }

    /**
     * Update a value in a JSON string using JsonPath.
     *
     * @param json the original JSON string
     * @param path the JsonPath to the value to update
     * @param value the new value
     * @return the updated JSON string
     */
    public static String updateJsonValue(String json, String path, Object value) {
        DocumentContext context = JsonPath.parse(json);
        context.set(path, value);
        return context.jsonString();
    }

    /**
     * Merge two JSON objects. The override JSON's fields take precedence.
     *
     * @param baseJson the base JSON string
     * @param overrideJson the override JSON string
     * @return the merged JSON string
     * @throws RuntimeException if merge fails
     */
    public static String mergeJson(String baseJson, String overrideJson) {
        try {
            JsonNode baseNode = objectMapper.readTree(baseJson);
            JsonNode overrideNode = objectMapper.readTree(overrideJson);
            JsonNode merged = mergeNodes(baseNode, overrideNode);
            return objectMapper.writeValueAsString(merged);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to merge JSON objects", e);
        }
    }

    /**
     * Recursively merge two JsonNode objects.
     *
     * @param baseNode the base node
     * @param overrideNode the override node
     * @return the merged node
     */
    private static JsonNode mergeNodes(JsonNode baseNode, JsonNode overrideNode) {
        if (baseNode.isObject() && overrideNode.isObject()) {
            com.fasterxml.jackson.databind.node.ObjectNode merged =
                ((com.fasterxml.jackson.databind.node.ObjectNode) baseNode).deepCopy();
            overrideNode.fields().forEachRemaining(field -> {
                merged.set(field.getKey(), field.getValue());
            });
            return merged;
        }
        return overrideNode;
    }

    /**
     * Convert a JSON array string to a List of the specified type.
     *
     * @param jsonArray the JSON array string
     * @param clazz the element class
     * @param <T> the element type
     * @return the list of elements
     */
    public static <T> List<T> toList(String jsonArray, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON array to List", e);
        }
    }

    /**
     * Convert a JSON object string to a Map.
     *
     * @param jsonObject the JSON object string
     * @return the map representation
     */
    public static Map<String, Object> toMap(String jsonObject) {
        try {
            return objectMapper.readValue(jsonObject, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to Map", e);
        }
    }

    /**
     * Get a specific field from a JsonNode.
     *
     * @param node the JsonNode
     * @param fieldName the field name
     * @return the field value as string, or null if not found
     */
    public static String getFieldAsString(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return fieldNode != null ? fieldNode.asText() : null;
    }

    /**
     * Get a specific field from a JsonNode as int.
     *
     * @param node the JsonNode
     * @param fieldName the field name
     * @return the field value as int, or -1 if not found
     */
    public static int getFieldAsInt(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return fieldNode != null ? fieldNode.asInt() : -1;
    }

    /**
     * Get the shared ObjectMapper instance.
     * Useful for custom configurations outside this utility.
     *
     * @return the configured ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
