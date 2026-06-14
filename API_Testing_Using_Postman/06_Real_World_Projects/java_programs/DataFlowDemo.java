/**
 * DataFlowDemo.java
 * 
 * Demonstrates how JSON data flows between requests in an API test suite
 * and shows JVM memory allocation patterns during test execution.
 * 
 * Concepts demonstrated:
 * - String Pool vs Heap allocation
 * - Object lifecycle and garbage collection
 * - Memory usage measurement
 * - JSON payload memory estimation
 * - Optimization strategies
 */

import java.lang.management.*;
import java.util.*;

public class DataFlowDemo {
    private static Runtime runtime = Runtime.getRuntime();
    private static long initialMemory;
    private static List<String> environmentVariables = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Data Flow & Memory Management Demo ===\n");
        
        // Capture baseline memory
        System.gc();
        initialMemory = usedMemory();
        System.out.println("Initial Heap Used: " + formatBytes(initialMemory));
        System.out.println("Max Heap: " + formatBytes(runtime.maxMemory()));
        System.out.println("Available Processors: " + runtime.availableProcessors());
        System.out.println();

        // Step 1: Simulate User Registration (JSON creation)
        System.out.println("Step 1: User Registration - JSON Payload Creation");
        long memBefore = usedMemory();
        String registrationJson = createRegistrationJson();
        long memAfter = usedMemory();
        System.out.println("  JSON Size: " + registrationJson.length() + " chars");
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println("  String Pool: " + (isInterned(registrationJson) ? "Interned" : "Heap"));
        System.out.println();

        // Step 2: Simulate Response parsing (object creation)
        System.out.println("Step 2: Parse Response - Object Creation on Heap");
        memBefore = usedMemory();
        Map<String, Object> userResponse = parseUserResponse();
        memAfter = usedMemory();
        System.out.println("  Objects Created: " + countObjects(userResponse));
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println("  Extracted userId: " + userResponse.get("userId"));
        System.out.println();

        // Step 3: Store in environment variable (String persistence)
        System.out.println("Step 3: Store in Environment Variable - String Persistence");
        memBefore = usedMemory();
        String userId = (String) userResponse.get("userId");
        environmentVariables.add(userId); // Simulate environment variable
        memAfter = usedMemory();
        System.out.println("  Variable stored: userId = " + userId);
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println("  String Pool: " + (isInterned(userId) ? "Interned" : "Heap"));
        System.out.println();

        // Step 4: Create Login Request
        System.out.println("Step 4: Login Request - New JSON Payload");
        memBefore = usedMemory();
        String loginJson = createLoginJson((String) userResponse.get("email"));
        memAfter = usedMemory();
        System.out.println("  JSON Size: " + loginJson.length() + " chars");
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println();

        // Step 5: Extract token and persist
        System.out.println("Step 5: Token Extraction - Long-lived Object");
        memBefore = usedMemory();
        Map<String, Object> authResponse = parseAuthResponse();
        String accessToken = (String) authResponse.get("accessToken");
        environmentVariables.add(accessToken);
        memAfter = usedMemory();
        System.out.println("  Token Length: " + accessToken.length() + " chars");
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println("  Token is long-lived (remains for entire test suite)");
        System.out.println();

        // Step 6: Simulate Product Catalog (large response)
        System.out.println("Step 6: Product Catalog - Large Response Handling");
        memBefore = usedMemory();
        List<Map<String, Object>> products = createProductList(10);
        memAfter = usedMemory();
        System.out.println("  Products Count: " + products.size());
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println("  Per-Product Avg: " + formatBytes((memAfter - memBefore) / products.size()));
        System.out.println();

        // Step 7: Add to Cart (nested objects)
        System.out.println("Step 7: Add to Cart - Nested Object Creation");
        memBefore = usedMemory();
        Map<String, Object> cartItem = createCartItem(products.get(0));
        memAfter = usedMemory();
        System.out.println("  Objects in hierarchy: " + countObjects(cartItem));
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println();

        // Step 8: Simulate Checkout (complex payload)
        System.out.println("Step 8: Checkout - Complex Payload");
        memBefore = usedMemory();
        Map<String, Object> orderRequest = createOrderRequest();
        memAfter = usedMemory();
        System.out.println("  Objects in hierarchy: " + countObjects(orderRequest));
        System.out.println("  Memory Delta: " + formatBytes(memAfter - memBefore));
        System.out.println();

        // Step 9: Memory cleanup simulation
        System.out.println("Step 9: Cleanup - Dereferencing Objects");
        memBefore = usedMemory();
        products = null; // Dereference large list
        cartItem = null;
        orderRequest = null;
        System.gc(); // Request GC
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        memAfter = usedMemory();
        System.out.println("  Memory Before GC: " + formatBytes(memBefore));
        System.out.println("  Memory After GC:  " + formatBytes(memAfter));
        System.out.println("  Freed: " + formatBytes(memBefore - memAfter));
        System.out.println();

        // Step 10: Environment variables still persist
        System.out.println("Step 10: Environment Variables - Persisted Data");
        System.out.println("  Variables stored: " + environmentVariables.size());
        System.out.println("  Variable 1 (userId): " + environmentVariables.get(0));
        System.out.println("  Variable 2 (token): " + environmentVariables.get(1).substring(0, 20) + "...");
        System.out.println("  These remain in memory until explicitly cleared.");
        System.out.println();

        // Summary
        printSummary();
        printOptimizationTips();
        printGCInfo();
    }

    // ==================== JSON CREATION METHODS ====================

    static String createRegistrationJson() {
        return "{"
            + "\"firstName\":\"Rahul\","
            + "\"lastName\":\"Sharma\","
            + "\"email\":\"rahul@example.com\","
            + "\"password\":\"SecurePass123!\","
            + "\"phone\":\"+91-9876543210\","
            + "\"address\":{\"street\":\"42, MG Road\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\"}"
            + "}";
    }

    static String createLoginJson(String email) {
        return "{\"email\":\"" + email + "\",\"password\":\"SecurePass123!\"}";
    }

    static Map<String, Object> parseUserResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", "usr_8f3a9b2c");
        response.put("firstName", "Rahul");
        response.put("lastName", "Sharma");
        response.put("email", "rahul@example.com");
        response.put("status", "ACTIVE");
        response.put("createdAt", "2026-06-14T10:30:00Z");
        
        Map<String, String> address = new HashMap<>();
        address.put("street", "42, MG Road");
        address.put("city", "Bangalore");
        address.put("state", "Karnataka");
        address.put("zipCode", "560001");
        address.put("country", "India");
        response.put("address", address);
        
        return response;
    }

    static Map<String, Object> parseAuthResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3JfOGYzYTliMmMiLCJleHAiOjE3NTAwMDAwMDB9.mock_signature");
        response.put("refreshToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3JfOGYzYTliMmMiLCJleHAiOjE3NTI1OTIwMDB9.mock_signature");
        response.put("tokenType", "Bearer");
        response.put("expiresIn", 3600);
        
        Map<String, String> user = new HashMap<>();
        user.put("userId", "usr_8f3a9b2c");
        user.put("email", "rahul@example.com");
        user.put("role", "CUSTOMER");
        response.put("user", user);
        
        return response;
    }

    static List<Map<String, Object>> createProductList(int count) {
        List<Map<String, Object>> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> product = new HashMap<>();
            product.put("productId", "prod_" + (12345 + i));
            product.put("name", "Product " + i);
            product.put("description", "This is a detailed description for product " + i);
            product.put("category", "ELECTRONICS");
            product.put("price", 2499.00 + (i * 100));
            product.put("currency", "INR");
            product.put("stockQuantity", 100 + i);
            product.put("rating", 4.0 + (i * 0.1));
            product.put("images", Arrays.asList("https://cdn.example.com/img" + i + "_1.jpg", "https://cdn.example.com/img" + i + "_2.jpg"));
            
            Map<String, String> specs = new HashMap<>();
            specs.put("batteryLife", "30 hours");
            specs.put("weight", "250g");
            specs.put("connectivity", "Bluetooth 5.0");
            product.put("specifications", specs);
            
            products.add(product);
        }
        return products;
    }

    static Map<String, Object> createCartItem(Map<String, Object> product) {
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("productId", product.get("productId"));
        cartItem.put("quantity", 2);
        
        Map<String, String> variant = new HashMap<>();
        variant.put("color", "Midnight Black");
        variant.put("size", "Standard");
        cartItem.put("variant", variant);
        
        return cartItem;
    }

    static Map<String, Object> createOrderRequest() {
        Map<String, Object> order = new HashMap<>();
        
        Map<String, String> shipping = new HashMap<>();
        shipping.put("street", "42, MG Road");
        shipping.put("city", "Bangalore");
        shipping.put("state", "Karnataka");
        shipping.put("zipCode", "560001");
        shipping.put("country", "India");
        shipping.put("phone", "+91-9876543210");
        order.put("shippingAddress", shipping);
        
        Map<String, String> billing = new HashMap<>();
        billing.put("street", "42, MG Road");
        billing.put("city", "Bangalore");
        billing.put("state", "Karnataka");
        billing.put("zipCode", "560001");
        billing.put("country", "India");
        order.put("billingAddress", billing);
        
        order.put("paymentMethod", "CREDIT_CARD");
        order.put("couponCode", "SUMMER2026");
        order.put("notes", "Please deliver after 6 PM");
        
        return order;
    }

    // ==================== MEMORY UTILITIES ====================

    static long usedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }

    static boolean isInterned(String s) {
        return s == s.intern();
    }

    static int countObjects(Object obj) {
        if (obj == null) return 0;
        if (obj instanceof Map) {
            int count = 1;
            for (Object value : ((Map<?, ?>) obj).values()) {
                count += countObjects(value);
            }
            return count;
        }
        if (obj instanceof Collection) {
            int count = 1;
            for (Object item : (Collection<?>) obj) {
                count += countObjects(item);
            }
            return count;
        }
        return 1;
    }

    // ==================== SUMMARY ====================

    static void printSummary() {
        System.out.println("=== Memory Summary ===");
        long finalMemory = usedMemory();
        System.out.println("Initial Memory:    " + formatBytes(initialMemory));
        System.out.println("Final Memory:      " + formatBytes(finalMemory));
        System.out.println("Net Change:        " + formatBytes(finalMemory - initialMemory));
        System.out.println("Environment Vars:  " + environmentVariables.size() + " (persisted)");
        System.out.println();
    }

    static void printOptimizationTips() {
        System.out.println("=== Optimization Strategies ===");
        System.out.println("1. Response Size: Use field selection (?fields=id,name) to reduce payload size");
        System.out.println("2. Streaming: For large responses, use streaming parsers instead of full object mapping");
        System.out.println("3. Connection Pool: Reuse HTTP connections to reduce native memory usage");
        System.out.println("4. Variable Cleanup: Clear environment variables after test suite completion");
        System.out.println("5. String Pool: Avoid concatenating large strings; use StringBuilder");
        System.out.println("6. GC Tuning: Use -Xms512m -Xmx2g -XX:+UseG1GC for API testing");
        System.out.println("7. Parallel Tests: Limit thread count to CPU cores * 2 to prevent memory bloat");
        System.out.println();
    }

    static void printGCInfo() {
        System.out.println("=== Garbage Collection Info ===");
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("GC Name: " + gcBean.getName());
            System.out.println("  Collection Count: " + gcBean.getCollectionCount());
            System.out.println("  Collection Time:  " + gcBean.getCollectionTime() + " ms");
        }
        
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("Heap Usage:");
        System.out.println("  Init:     " + formatBytes(heapUsage.getInit()));
        System.out.println("  Used:     " + formatBytes(heapUsage.getUsed()));
        System.out.println("  Committed:" + formatBytes(heapUsage.getCommitted()));
        System.out.println("  Max:      " + formatBytes(heapUsage.getMax()));
        System.out.println();
    }
}
