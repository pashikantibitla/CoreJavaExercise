/**
 * EcommerceController.java
 * 
 * Controller layer for E-Commerce API testing.
 * Simulates REST API calls using HttpURLConnection (standard Java).
 * In production, this would use REST Assured or similar HTTP client libraries.
 * 
 * Demonstrates:
 * - HTTP GET/POST/PUT/DELETE operations
 * - Header management (Authorization, Content-Type)
 * - JSON serialization/deserialization
 * - Error handling
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EcommerceController {
    private static final String BASE_URL = "https://api.example.com";
    private String authToken;
    private String userId;
    private String cartId;
    private String orderId;

    /**
     * Generic HTTP GET request with optional auth token.
     */
    public ApiResponse get(String endpoint, boolean requiresAuth) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(BASE_URL + endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (requiresAuth && authToken != null) {
                conn.setRequestProperty("Authorization", "Bearer " + authToken);
            }
            ApiResponse response = readResponse(conn);
            // Return mock data for demo when network is unavailable
            if (response.status == 0) return mockGetResponse(endpoint);
            return response;
        } catch (IOException e) {
            return mockGetResponse(endpoint);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * Generic HTTP POST request with JSON body.
     */
    public ApiResponse post(String endpoint, String jsonBody, boolean requiresAuth) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(BASE_URL + endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            if (requiresAuth && authToken != null) {
                conn.setRequestProperty("Authorization", "Bearer " + authToken);
            }
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            ApiResponse response = readResponse(conn);
            if (response.status == 0) return mockPostResponse(endpoint, jsonBody);
            return response;
        } catch (IOException e) {
            return mockPostResponse(endpoint, jsonBody);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * Generic HTTP PUT request with JSON body.
     */
    public ApiResponse put(String endpoint, String jsonBody, boolean requiresAuth) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(BASE_URL + endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            if (requiresAuth && authToken != null) {
                conn.setRequestProperty("Authorization", "Bearer " + authToken);
            }
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            ApiResponse response = readResponse(conn);
            if (response.status == 0) return mockPutResponse(endpoint, jsonBody);
            return response;
        } catch (IOException e) {
            return mockPutResponse(endpoint, jsonBody);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * Generic HTTP DELETE request.
     */
    public ApiResponse delete(String endpoint, boolean requiresAuth) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(BASE_URL + endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (requiresAuth && authToken != null) {
                conn.setRequestProperty("Authorization", "Bearer " + authToken);
            }
            ApiResponse response = readResponse(conn);
            if (response.status == 0) return mockDeleteResponse(endpoint);
            return response;
        } catch (IOException e) {
            return mockDeleteResponse(endpoint);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * Reads the HTTP response and returns an ApiResponse object.
     */
    private ApiResponse readResponse(HttpURLConnection conn) throws IOException {
        int status = conn.getResponseCode();
        InputStream stream = (status >= 200 && status < 300)
            ? conn.getInputStream()
            : conn.getErrorStream();

        StringBuilder response = new StringBuilder();
        if (stream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
            }
        }
        return new ApiResponse(status, response.toString());
    }

    // ==================== E-COMMERCE SPECIFIC METHODS ====================

    /**
     * Step 1: Register a new user.
     */
    public ApiResponse registerUser(String firstName, String lastName, String email,
                                      String password, String phone, String city) {
        String json = "{"
            + "\"firstName\":\"" + firstName + "\","
            + "\"lastName\":\"" + lastName + "\","
            + "\"email\":\"" + email + "\","
            + "\"password\":\"" + password + "\","
            + "\"phone\":\"" + phone + "\","
            + "\"address\":{\"street\":\"42, MG Road\",\"city\":\"" + city + "\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\"}"
            + "}";
        return post("/api/v1/users/register", json, false);
    }

    /**
     * Step 2: Login and extract token.
     */
    public ApiResponse login(String email, String password) {
        String json = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        ApiResponse response = post("/api/v1/auth/login", json, false);
        if (response.status == 200) {
            // In real implementation, parse JSON to extract token
            authToken = extractToken(response.body);
        }
        return response;
    }

    /**
     * Step 3: Get product catalog.
     */
    public ApiResponse getProducts(String category, int page, int limit) {
        String endpoint = "/api/v1/products?category=" + category + "&page=" + page + "&limit=" + limit;
        return get(endpoint, true);
    }

    /**
     * Step 4: Add item to cart.
     */
    public ApiResponse addToCart(String productId, int quantity, String color, String size) {
        String json = "{"
            + "\"productId\":\"" + productId + "\","
            + "\"quantity\":" + quantity + ","
            + "\"variant\":{\"color\":\"" + color + "\",\"size\":\"" + size + "\"}"
            + "}";
        ApiResponse response = post("/api/v1/cart/items", json, true);
        if (response.status == 200) {
            cartId = extractField(response.body, "cartId");
        }
        return response;
    }

    /**
     * Step 5: View cart.
     */
    public ApiResponse viewCart() {
        return get("/api/v1/cart", true);
    }

    /**
     * Step 6: Update cart item.
     */
    public ApiResponse updateCartItem(String cartItemId, int quantity) {
        String json = "{\"quantity\":" + quantity + "}";
        return put("/api/v1/cart/items/" + cartItemId, json, true);
    }

    /**
     * Step 7: Checkout and create order.
     */
    public ApiResponse checkout(String couponCode, String paymentMethod) {
        String json = "{"
            + "\"shippingAddress\":{\"street\":\"42, MG Road\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\",\"phone\":\"+91-9876543210\"},"
            + "\"billingAddress\":{\"street\":\"42, MG Road\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\"},"
            + "\"paymentMethod\":\"" + paymentMethod + "\","
            + "\"couponCode\":\"" + couponCode + "\","
            + "\"notes\":\"Please deliver after 6 PM\""
            + "}";
        ApiResponse response = post("/api/v1/orders", json, true);
        if (response.status == 201) {
            orderId = extractField(response.body, "orderId");
        }
        return response;
    }

    /**
     * Step 8: Get order history.
     */
    public ApiResponse getOrderHistory(String status, int page, int limit) {
        String endpoint = "/api/v1/orders?status=" + status + "&page=" + page + "&limit=" + limit;
        return get(endpoint, true);
    }

    /**
     * Step 9: Process payment.
     */
    public ApiResponse processPayment(String orderId, double amount, String currency) {
        String json = "{"
            + "\"orderId\":\"" + orderId + "\","
            + "\"paymentMethod\":\"CREDIT_CARD\","
            + "\"cardDetails\":{\"cardNumber\":\"4111111111111111\",\"cardHolder\":\"Rahul Sharma\",\"expiryMonth\":\"12\",\"expiryYear\":\"2028\",\"cvv\":\"123\"},"
            + "\"amount\":" + amount + ","
            + "\"currency\":\"" + currency + "\""
            + "}";
        return post("/api/v1/payments/process", json, true);
    }

    // ==================== HELPER METHODS ====================

    /**
     * Simulates token extraction from JSON response.
     * In real code, use a JSON parser like Jackson or Gson.
     */
    private String extractToken(String json) {
        // Simplified extraction - real code uses JSON parser
        int start = json.indexOf("\"accessToken\":\"");
        if (start == -1) return null;
        start += 15;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    private String extractField(String json, String fieldName) {
        String search = "\"" + fieldName + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return null;
        start += search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    public String getAuthToken() { return authToken; }
    public String getUserId() { return userId; }
    public String getCartId() { return cartId; }
    public String getOrderId() { return orderId; }
    public void setAuthToken(String token) { this.authToken = token; }

    // ==================== MOCK RESPONSES (FOR DEMO) ====================
    // These methods return realistic JSON when the network is unavailable,
    // allowing the test suite to run without a live API server.

    private ApiResponse mockGetResponse(String endpoint) {
        if (endpoint.contains("/products")) {
            return new ApiResponse(200, "{\"data\":[{\"productId\":\"prod_12345\",\"name\":\"Wireless Headphones\",\"price\":2499.00,\"category\":\"ELECTRONICS\",\"stockQuantity\":150}],\"pagination\":{\"currentPage\":1,\"totalPages\":5,\"totalItems\":48,\"itemsPerPage\":10,\"hasNextPage\":true,\"hasPreviousPage\":false}}");
        }
        if (endpoint.contains("/cart") && !endpoint.contains("/items")) {
            // Simulate unauthorized access when token is missing
            if (authToken == null) {
                return new ApiResponse(401, "{\"error\":{\"code\":\"AUTH_REQUIRED\",\"message\":\"Authorization token is required\"}}");
            }
            return new ApiResponse(200, "{\"cartId\":\"cart_98765\",\"userId\":\"usr_8f3a9b2c\",\"items\":[{\"cartItemId\":\"ci_001\",\"productId\":\"prod_12345\",\"productName\":\"Wireless Headphones\",\"quantity\":2,\"unitPrice\":2499.00,\"totalPrice\":4998.00,\"variant\":{\"color\":\"Black\",\"size\":\"Standard\"},\"addedAt\":\"2026-06-14T11:00:00Z\"}],\"summary\":{\"subtotal\":4998.00,\"tax\":899.64,\"shipping\":0.00,\"discount\":0.00,\"total\":5897.64,\"currency\":\"INR\"},\"itemCount\":2,\"updatedAt\":\"2026-06-14T11:00:00Z\"}");
        }
        if (endpoint.contains("/orders")) {
            return new ApiResponse(200, "{\"orders\":[{\"orderId\":\"ord_20260614_001\",\"status\":\"PENDING_PAYMENT\",\"total\":8346.46,\"currency\":\"INR\",\"itemCount\":3,\"createdAt\":\"2026-06-14T11:15:00Z\"}],\"pagination\":{\"currentPage\":1,\"totalPages\":1,\"totalItems\":1,\"itemsPerPage\":10}}");
        }
        return new ApiResponse(404, "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"Resource not found\"}}");
    }

    private ApiResponse mockPostResponse(String endpoint, String jsonBody) {
        if (endpoint.contains("/register")) {
            // Simulate duplicate email detection
            if (jsonBody.contains("duplicate@example.com")) {
                return new ApiResponse(409, "{\"error\":{\"code\":\"ALREADY_EXISTS\",\"message\":\"Email already registered\"}}");
            }
            String uid = "usr_" + System.currentTimeMillis();
            return new ApiResponse(201, "{\"userId\":\"" + uid + "\",\"firstName\":\"Rahul\",\"lastName\":\"Sharma\",\"email\":\"rahul@example.com\",\"phone\":\"+91-9876543210\",\"address\":{\"street\":\"42, MG Road\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\"},\"createdAt\":\"2026-06-14T10:30:00Z\",\"status\":\"ACTIVE\"}");
        }
        if (endpoint.contains("/login")) {
            // Simulate invalid credentials
            if (jsonBody.contains("wrong@example.com") || jsonBody.contains("wrongpassword")) {
                return new ApiResponse(401, "{\"error\":{\"code\":\"INVALID_CREDENTIALS\",\"message\":\"Email or password is incorrect\"}}");
            }
            authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.mock_token";
            return new ApiResponse(200, "{\"accessToken\":\"" + authToken + "\",\"refreshToken\":\"refresh_token\",\"tokenType\":\"Bearer\",\"expiresIn\":3600,\"user\":{\"userId\":\"usr_8f3a9b2c\",\"email\":\"rahul@example.com\",\"role\":\"CUSTOMER\"}}");
        }
        if (endpoint.contains("/cart/items")) {
            // Simulate invalid product
            if (jsonBody.contains("invalid_id")) {
                return new ApiResponse(404, "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"Product not found\"}}");
            }
            cartId = "cart_98765";
            return new ApiResponse(200, "{\"cartId\":\"cart_98765\",\"userId\":\"usr_8f3a9b2c\",\"items\":[{\"cartItemId\":\"ci_001\",\"productId\":\"prod_12345\",\"productName\":\"Wireless Headphones\",\"quantity\":2,\"unitPrice\":2499.00,\"totalPrice\":4998.00,\"variant\":{\"color\":\"Black\",\"size\":\"Standard\"},\"addedAt\":\"2026-06-14T11:00:00Z\"}],\"summary\":{\"subtotal\":4998.00,\"tax\":899.64,\"shipping\":0.00,\"discount\":0.00,\"total\":5897.64,\"currency\":\"INR\"},\"itemCount\":2,\"updatedAt\":\"2026-06-14T11:00:00Z\"}");
        }
        if (endpoint.contains("/orders")) {
            orderId = "ord_20260614_001";
            return new ApiResponse(201, "{\"orderId\":\"ord_20260614_001\",\"userId\":\"usr_8f3a9b2c\",\"status\":\"PENDING_PAYMENT\",\"items\":[{\"productId\":\"prod_12345\",\"productName\":\"Wireless Headphones\",\"quantity\":3,\"unitPrice\":2499.00,\"totalPrice\":7497.00,\"variant\":{\"color\":\"Black\",\"size\":\"Standard\"}}],\"shippingAddress\":{\"street\":\"42, MG Road\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\",\"phone\":\"+91-9876543210\"},\"billingAddress\":{\"street\":\"42, MG Road\",\"city\":\"Bangalore\",\"state\":\"Karnataka\",\"zipCode\":\"560001\",\"country\":\"India\"},\"pricing\":{\"subtotal\":7497.00,\"tax\":1349.46,\"shipping\":0.00,\"discount\":500.00,\"total\":8346.46,\"currency\":\"INR\"},\"payment\":{\"method\":\"CREDIT_CARD\",\"status\":\"PENDING\",\"transactionId\":null},\"createdAt\":\"2026-06-14T11:15:00Z\",\"estimatedDelivery\":\"2026-06-17T18:00:00Z\"}");
        }
        if (endpoint.contains("/payments")) {
            // Simulate amount mismatch
            if (jsonBody.contains("9999.99")) {
                return new ApiResponse(400, "{\"error\":{\"code\":\"AMOUNT_MISMATCH\",\"message\":\"Payment amount does not match order total\"}}");
            }
            return new ApiResponse(200, "{\"transactionId\":\"txn_abc123xyz\",\"orderId\":\"ord_20260614_001\",\"status\":\"SUCCESS\",\"amount\":8346.46,\"currency\":\"INR\",\"paymentMethod\":\"CREDIT_CARD\",\"timestamp\":\"2026-06-14T11:20:00Z\",\"receiptUrl\":\"https://api.example.com/receipts/txn_abc123xyz.pdf\"}");
        }
        return new ApiResponse(400, "{\"error\":{\"code\":\"BAD_REQUEST\",\"message\":\"Invalid request\"}}");
    }

    private ApiResponse mockPutResponse(String endpoint, String jsonBody) {
        if (endpoint.contains("/cart/items")) {
            return new ApiResponse(200, "{\"cartId\":\"cart_98765\",\"userId\":\"usr_8f3a9b2c\",\"items\":[{\"cartItemId\":\"ci_001\",\"productId\":\"prod_12345\",\"productName\":\"Wireless Headphones\",\"quantity\":3,\"unitPrice\":2499.00,\"totalPrice\":7497.00,\"variant\":{\"color\":\"Black\",\"size\":\"Standard\"},\"addedAt\":\"2026-06-14T11:00:00Z\"}],\"summary\":{\"subtotal\":7497.00,\"tax\":1349.46,\"shipping\":0.00,\"discount\":0.00,\"total\":8846.46,\"currency\":\"INR\"},\"itemCount\":3,\"updatedAt\":\"2026-06-14T11:05:00Z\"}");
        }
        return new ApiResponse(200, "{\"updated\":true}");
    }

    private ApiResponse mockDeleteResponse(String endpoint) {
        return new ApiResponse(204, "");
    }

    // ==================== INNER API RESPONSE CLASS ====================

    static class ApiResponse {
        int status;
        String body;

        public ApiResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        @Override
        public String toString() {
            return "ApiResponse{status=" + status + ", body='" + body + "'}";
        }
    }

    // ==================== MAIN DEMO ====================

    public static void main(String[] args) {
        EcommerceController controller = new EcommerceController();

        System.out.println("=== E-Commerce Controller Demo ===\n");
        System.out.println("Note: This demo simulates API calls. In a real test, the base URL would be a live API server.\n");

        // 1. Register
        System.out.println("Step 1: User Registration");
        String email = "rahul" + System.currentTimeMillis() + "@example.com";
        ApiResponse reg = controller.registerUser("Rahul", "Sharma", email,
            "SecurePass123!", "+91-9876543210", "Bangalore");
        System.out.println("Register Response: " + reg.status + " " + reg.body.substring(0, Math.min(100, reg.body.length())));
        System.out.println();

        // 2. Login
        System.out.println("Step 2: User Login");
        ApiResponse login = controller.login(email, "SecurePass123!");
        System.out.println("Login Response: " + login.status + " " + login.body.substring(0, Math.min(100, login.body.length())));
        System.out.println("Token: " + (controller.getAuthToken() != null ? "Extracted" : "Not found (demo)"));
        System.out.println();

        // 3. Get Products
        System.out.println("Step 3: Product Catalog");
        ApiResponse products = controller.getProducts("ELECTRONICS", 1, 10);
        System.out.println("Products Response: " + products.status + " " + products.body.substring(0, Math.min(100, products.body.length())));
        System.out.println();

        // 4. Add to Cart
        System.out.println("Step 4: Add to Cart");
        ApiResponse cart = controller.addToCart("prod_12345", 2, "Black", "Standard");
        System.out.println("Cart Response: " + cart.status + " " + cart.body.substring(0, Math.min(100, cart.body.length())));
        System.out.println();

        // 5. Checkout
        System.out.println("Step 5: Checkout");
        ApiResponse order = controller.checkout("SUMMER2026", "CREDIT_CARD");
        System.out.println("Order Response: " + order.status + " " + order.body.substring(0, Math.min(100, order.body.length())));
        System.out.println();

        // 6. Payment
        System.out.println("Step 6: Payment");
        ApiResponse payment = controller.processPayment("ord_20260614_001", 8346.46, "INR");
        System.out.println("Payment Response: " + payment.status + " " + payment.body.substring(0, Math.min(100, payment.body.length())));
        System.out.println();

        System.out.println("=== E-Commerce Flow Complete ===");
        System.out.println("In real tests, use REST Assured with proper assertions on each response.");
    }
}
