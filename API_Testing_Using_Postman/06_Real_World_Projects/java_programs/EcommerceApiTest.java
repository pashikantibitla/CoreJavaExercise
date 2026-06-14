/**
 * EcommerceApiTest.java
 * 
 * Complete E-Commerce API test suite demonstrating the full user flow:
 * Register -> Login -> Browse Products -> Add to Cart -> Checkout -> Pay -> Order History.
 * 
 * Uses standard Java assertions (no external dependencies).
 * In production, use TestNG or JUnit with REST Assured.
 */

public class EcommerceApiTest {
    private static EcommerceController controller;
    private static String userId;
    private static String productId = "prod_12345";
    private static String cartId;
    private static String orderId;
    private static String transactionId;
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        controller = new EcommerceController();
        System.out.println("=== E-Commerce API Test Suite ===\n");

        runTest("TC-REG-01: Valid User Registration", EcommerceApiTest::testValidRegistration);
        runTest("TC-LOG-01: Valid User Login", EcommerceApiTest::testValidLogin);
        runTest("TC-PROD-01: Get Products Catalog", EcommerceApiTest::testGetProducts);
        runTest("TC-CART-01: Add Item to Cart", EcommerceApiTest::testAddToCart);
        runTest("TC-VIEW-01: View Cart", EcommerceApiTest::testViewCart);
        runTest("TC-UPD-01: Update Cart Quantity", EcommerceApiTest::testUpdateCartItem);
        runTest("TC-CHK-01: Checkout Process", EcommerceApiTest::testCheckout);
        runTest("TC-PAY-01: Process Payment", EcommerceApiTest::testPayment);
        runTest("TC-ORD-01: Order History", EcommerceApiTest::testOrderHistory);
        runTest("TC-NEG-01: Duplicate Registration", EcommerceApiTest::testDuplicateRegistration);
        runTest("TC-NEG-02: Invalid Login", EcommerceApiTest::testInvalidLogin);
        runTest("TC-NEG-03: Unauthorized Cart Access", EcommerceApiTest::testUnauthorizedCartAccess);
        runTest("TC-NEG-04: Invalid Product ID", EcommerceApiTest::testInvalidProductId);
        runTest("TC-NEG-05: Payment Amount Mismatch", EcommerceApiTest::testPaymentAmountMismatch);
        runTest("TC-NEG-06: SQL Injection in Registration", EcommerceApiTest::testSqlInjectionInRegistration);

        System.out.println("\n=== Test Results ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total:  " + (passed + failed));
    }

    private static void runTest(String name, Runnable test) {
        try {
            test.run();
            System.out.println("  PASS: " + name);
            passed++;
        } catch (AssertionError e) {
            System.out.println("  FAIL: " + name + " -> " + e.getMessage());
            failed++;
        } catch (Exception e) {
            System.out.println("  ERROR: " + name + " -> " + e.getMessage());
            failed++;
        }
    }

    // ==================== POSITIVE TEST CASES ====================

    static void testValidRegistration() {
        String email = "rahul" + System.currentTimeMillis() + "@example.com";
        EcommerceController.ApiResponse response = controller.registerUser(
            "Rahul", "Sharma", email, "SecurePass123!", "+91-9876543210", "Bangalore"
        );
        assertStatus(response, 200, 201); // Some APIs return 200 or 201
        assertContains(response.body, "userId");
        assertContains(response.body, "ACTIVE");
        userId = extractField(response.body, "userId");
    }

    static void testValidLogin() {
        EcommerceController.ApiResponse response = controller.login("rahul@example.com", "SecurePass123!");
        assertContains(response.body, "accessToken");
        assertContains(response.body, "Bearer");
    }

    static void testGetProducts() {
        EcommerceController.ApiResponse response = controller.getProducts("ELECTRONICS", 1, 10);
        assertContains(response.body, "data");
        assertContains(response.body, "pagination");
    }

    static void testAddToCart() {
        EcommerceController.ApiResponse response = controller.addToCart(productId, 2, "Black", "Standard");
        assertContains(response.body, "cartId");
        assertContains(response.body, "items");
        cartId = extractField(response.body, "cartId");
    }

    static void testViewCart() {
        EcommerceController.ApiResponse response = controller.viewCart();
        assertContains(response.body, "cartId");
        assertContains(response.body, "summary");
    }

    static void testUpdateCartItem() {
        EcommerceController.ApiResponse response = controller.updateCartItem("ci_001", 3);
        assertContains(response.body, "cartId");
    }

    static void testCheckout() {
        EcommerceController.ApiResponse response = controller.checkout("SUMMER2026", "CREDIT_CARD");
        assertContains(response.body, "orderId");
        assertContains(response.body, "PENDING_PAYMENT");
        orderId = extractField(response.body, "orderId");
    }

    static void testPayment() {
        EcommerceController.ApiResponse response = controller.processPayment("ord_20260614_001", 8346.46, "INR");
        assertContains(response.body, "transactionId");
        assertContains(response.body, "SUCCESS");
        transactionId = extractField(response.body, "transactionId");
    }

    static void testOrderHistory() {
        EcommerceController.ApiResponse response = controller.getOrderHistory("ALL", 1, 10);
        assertContains(response.body, "orders");
        assertContains(response.body, "pagination");
    }

    // ==================== NEGATIVE TEST CASES ====================

    static void testDuplicateRegistration() {
        String email = "duplicate@example.com";
        controller.registerUser("Dup", "User", email, "SecurePass123!", "+91-9876543210", "Bangalore");
        EcommerceController.ApiResponse response = controller.registerUser(
            "Dup", "User", email, "SecurePass123!", "+91-9876543210", "Bangalore"
        );
        assertContains(response.body, "error");
    }

    static void testInvalidLogin() {
        EcommerceController.ApiResponse response = controller.login("wrong@example.com", "wrongpassword");
        assertContains(response.body, "error");
    }

    static void testUnauthorizedCartAccess() {
        // Save current token, clear it, make request, restore token
        String savedToken = controller.getAuthToken();
        controller.setAuthToken(null);
        EcommerceController.ApiResponse response = controller.viewCart();
        assertContains(response.body, "error");
        controller.setAuthToken(savedToken);
    }

    static void testInvalidProductId() {
        EcommerceController.ApiResponse response = controller.addToCart("invalid_id", 1, "Black", "Standard");
        assertContains(response.body, "error");
    }

    static void testPaymentAmountMismatch() {
        EcommerceController.ApiResponse response = controller.processPayment("ord_20260614_001", 9999.99, "INR");
        assertContains(response.body, "error");
    }

    static void testSqlInjectionInRegistration() {
        String maliciousEmail = "test' OR '1'='1" + System.currentTimeMillis() + "@example.com";
        EcommerceController.ApiResponse response = controller.registerUser(
            "Test", "User", maliciousEmail, "Pass123!", "+91-9876543210", "Bangalore"
        );
        // Should not contain SQL errors or success with injection
        if (response.body.contains("userId") && response.body.contains(maliciousEmail.replace("'", ""))) {
            throw new AssertionError("SQL injection may have succeeded - email was not sanitized");
        }
    }

    // ==================== ASSERTION HELPERS ====================

    static void assertStatus(EcommerceController.ApiResponse response, int... expected) {
        for (int e : expected) {
            if (response.status == e) return;
        }
        throw new AssertionError("Expected status in " + java.util.Arrays.toString(expected) + " but got " + response.status);
    }

    static void assertContains(String body, String substring) {
        if (body == null || !body.contains(substring)) {
            throw new AssertionError("Response body does not contain: " + substring);
        }
    }

    static String extractField(String json, String fieldName) {
        String search = "\"" + fieldName + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return null;
        start += search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
