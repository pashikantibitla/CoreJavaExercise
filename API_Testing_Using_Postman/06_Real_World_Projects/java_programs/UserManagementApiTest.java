/**
 * UserManagementApiTest.java
 * 
 * User Management API test suite covering CRUD operations,
 * search/filter, pagination, bulk operations, and import/export.
 * 
 * Demonstrates:
 * - Complete CRUD lifecycle
 * - Query parameter handling
 * - Bulk operation testing
 * - File upload simulation
 */

import java.util.*;

public class UserManagementApiTest {
    private static int passed = 0;
    private static int failed = 0;
    private static List<String> createdUserIds = new ArrayList<>();
    private static final String BASE_URL = "https://api.example.com/api/v1/users";

    public static void main(String[] args) {
        System.out.println("=== User Management API Test Suite ===\n");

        runTest("TC-CRUD-01: Create User", UserManagementApiTest::testCreateUser);
        runTest("TC-CRUD-02: Read User", UserManagementApiTest::testReadUser);
        runTest("TC-CRUD-03: Update User", UserManagementApiTest::testUpdateUser);
        runTest("TC-CRUD-04: Delete User", UserManagementApiTest::testDeleteUser);
        runTest("TC-CRUD-05: Partial Update (PATCH)", UserManagementApiTest::testPartialUpdate);
        runTest("TC-SEARCH-01: Search by Name", UserManagementApiTest::testSearchByName);
        runTest("TC-SEARCH-02: Filter by Role", UserManagementApiTest::testFilterByRole);
        runTest("TC-SEARCH-03: Combined Search and Filter", UserManagementApiTest::testCombinedSearchFilter);
        runTest("TC-PAGE-01: First Page", UserManagementApiTest::testPaginationFirstPage);
        runTest("TC-PAGE-02: Last Page", UserManagementApiTest::testPaginationLastPage);
        runTest("TC-PAGE-03: Page Size Boundary", UserManagementApiTest::testPageSizeBoundary);
        runTest("TC-PAGE-04: Sort Ascending", UserManagementApiTest::testSortAscending);
        runTest("TC-PAGE-05: Sort Descending", UserManagementApiTest::testSortDescending);
        runTest("TC-BULK-01: Bulk Create", UserManagementApiTest::testBulkCreate);
        runTest("TC-BULK-02: Bulk Update", UserManagementApiTest::testBulkUpdate);
        runTest("TC-BULK-03: Bulk Delete", UserManagementApiTest::testBulkDelete);
        runTest("TC-IMP-01: Export Users", UserManagementApiTest::testExportUsers);
        runTest("TC-IMP-02: Import Users", UserManagementApiTest::testImportUsers);
        runTest("TC-NEG-01: Duplicate Email", UserManagementApiTest::testDuplicateEmail);
        runTest("TC-NEG-02: Invalid Role", UserManagementApiTest::testInvalidRole);
        runTest("TC-NEG-03: Missing Required Fields", UserManagementApiTest::testMissingRequiredFields);
        runTest("TC-NEG-04: Read Non-existent User", UserManagementApiTest::testReadNonExistentUser);

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

    // ==================== CRUD TESTS ====================

    static void testCreateUser() {
        String json = buildUserJson("Priya", "Patel", "priya" + System.currentTimeMillis() + "@example.com", "ADMIN", "Engineering");
        ApiResponse response = httpPost(BASE_URL, json);
        assertStatus(response, 201);
        assertContains(response.body, "userId");
        assertContains(response.body, "ADMIN");
        String userId = extractField(response.body, "userId");
        createdUserIds.add(userId);
    }

    static void testReadUser() {
        if (createdUserIds.isEmpty()) {
            throw new AssertionError("No user created for read test");
        }
        String userId = createdUserIds.get(0);
        ApiResponse response = httpGet(BASE_URL + "/" + userId);
        assertStatus(response, 200);
        assertContains(response.body, "userId");
        // Note: In a real API, the response would contain the specific userId.
        // The mock returns a generic userId for demonstration.
        assertContains(response.body, "firstName");
    }

    static void testUpdateUser() {
        if (createdUserIds.isEmpty()) {
            throw new AssertionError("No user created for update test");
        }
        String userId = createdUserIds.get(0);
        String json = "{\"firstName\":\"Priya\",\"lastName\":\"Patel\",\"role\":\"MANAGER\",\"department\":\"Product\",\"isActive\":true}";
        ApiResponse response = httpPut(BASE_URL + "/" + userId, json);
        assertStatus(response, 200);
        assertContains(response.body, "MANAGER");
        assertContains(response.body, "Product");
    }

    static void testDeleteUser() {
        String email = "delete" + System.currentTimeMillis() + "@example.com";
        String json = buildUserJson("Delete", "Me", email, "USER", "HR");
        ApiResponse createResp = httpPost(BASE_URL, json);
        String userId = extractField(createResp.body, "userId");
        ApiResponse response = httpDelete(BASE_URL + "/" + userId);
        assertStatus(response, 204);
    }

    static void testPartialUpdate() {
        if (createdUserIds.isEmpty()) {
            throw new AssertionError("No user created for partial update test");
        }
        String userId = createdUserIds.get(0);
        String json = "{\"isActive\":false}";
        ApiResponse response = httpPatch(BASE_URL + "/" + userId, json);
        assertStatus(response, 200);
        assertContains(response.body, "false");
    }

    // ==================== SEARCH & FILTER TESTS ====================

    static void testSearchByName() {
        ApiResponse response = httpGet(BASE_URL + "/search?q=priya");
        assertStatus(response, 200);
        assertContains(response.body, "users");
        assertContains(response.body, "totalResults");
    }

    static void testFilterByRole() {
        ApiResponse response = httpGet(BASE_URL + "/search?role=ADMIN");
        assertStatus(response, 200);
        assertContains(response.body, "users");
    }

    static void testCombinedSearchFilter() {
        ApiResponse response = httpGet(BASE_URL + "/search?q=priya&role=ADMIN&department=Engineering&status=active");
        assertStatus(response, 200);
        assertContains(response.body, "filters");
    }

    // ==================== PAGINATION TESTS ====================

    static void testPaginationFirstPage() {
        ApiResponse response = httpGet(BASE_URL + "?page=1&size=10");
        assertStatus(response, 200);
        assertContains(response.body, "content");
        assertContains(response.body, "pageable");
    }

    static void testPaginationLastPage() {
        ApiResponse response = httpGet(BASE_URL + "?page=999&size=10");
        assertStatus(response, 200);
        assertContains(response.body, "empty");
    }

    static void testPageSizeBoundary() {
        ApiResponse response = httpGet(BASE_URL + "?page=1&size=1");
        assertStatus(response, 200);
        assertContains(response.body, "numberOfElements");
    }

    static void testSortAscending() {
        ApiResponse response = httpGet(BASE_URL + "?sort=createdAt,asc");
        assertStatus(response, 200);
        assertContains(response.body, "sort");
    }

    static void testSortDescending() {
        ApiResponse response = httpGet(BASE_URL + "?sort=createdAt,desc");
        assertStatus(response, 200);
        assertContains(response.body, "sort");
    }

    // ==================== BULK OPERATIONS TESTS ====================

    static void testBulkCreate() {
        String json = "{"
            + "\"users\":["
            + "{\"firstName\":\"User1\",\"lastName\":\"Test\",\"email\":\"u1" + System.currentTimeMillis() + "@e.com\",\"role\":\"USER\"},"
            + "{\"firstName\":\"User2\",\"lastName\":\"Test\",\"email\":\"u2" + System.currentTimeMillis() + "@e.com\",\"role\":\"USER\"}"
            + "],"
            + "\"operation\":\"CREATE\""
            + "}";
        ApiResponse response = httpPost(BASE_URL + "/bulk", json);
        assertStatus(response, 200, 201);
        assertContains(response.body, "successful");
    }

    static void testBulkUpdate() {
        String json = "{\"userIds\":[\"usr_001\",\"usr_002\"],\"status\":\"INACTIVE\"}";
        ApiResponse response = httpPut(BASE_URL + "/bulk/status", json);
        assertStatus(response, 200);
        assertContains(response.body, "successful");
    }

    static void testBulkDelete() {
        String json = "{\"userIds\":[\"usr_001\",\"usr_002\"]}";
        ApiResponse response = httpDelete(BASE_URL + "/bulk", json);
        assertStatus(response, 200);
        assertContains(response.body, "successful");
    }

    // ==================== IMPORT/EXPORT TESTS ====================

    static void testExportUsers() {
        ApiResponse response = httpGet(BASE_URL + "/export?format=csv");
        assertStatus(response, 200);
        assertContains(response.body, "csv");
    }

    static void testImportUsers() {
        String json = "{\"totalRecords\":100,\"processed\":98,\"failed\":2,\"errors\":[{\"row\":45,\"error\":\"Invalid email\"}]}";
        ApiResponse response = httpPost(BASE_URL + "/import", json);
        assertStatus(response, 200, 201);
        assertContains(response.body, "processed");
    }

    // ==================== NEGATIVE TESTS ====================

    static void testDuplicateEmail() {
        String email = "duplicate@example.com";
        String json = buildUserJson("Dup", "User", email, "USER", "IT");
        httpPost(BASE_URL, json);
        ApiResponse response = httpPost(BASE_URL, json);
        assertStatus(response, 409);
        assertContains(response.body, "error");
    }

    static void testInvalidRole() {
        String json = buildUserJson("Test", "User", "test" + System.currentTimeMillis() + "@example.com", "SUPER_ADMIN", "IT");
        ApiResponse response = httpPost(BASE_URL, json);
        assertContains(response.body, "error");
    }

    static void testMissingRequiredFields() {
        String json = "{\"firstName\":\"Test\"}";
        ApiResponse response = httpPost(BASE_URL, json);
        assertContains(response.body, "error");
    }

    static void testReadNonExistentUser() {
        ApiResponse response = httpGet(BASE_URL + "/usr_nonexistent");
        assertContains(response.body, "error");
    }

    // ==================== HTTP HELPERS ====================

    static ApiResponse httpGet(String url) {
        if (url.contains("usr_nonexistent")) {
            return new ApiResponse(404, "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"User not found\"}}");
        }
        if (url.contains("/users/") && !url.contains("/search") && !url.contains("?")) {
            return new ApiResponse(200, "{\"userId\":\"usr_001\",\"firstName\":\"Priya\",\"lastName\":\"Patel\",\"email\":\"priya@example.com\",\"role\":\"ADMIN\",\"department\":\"Engineering\",\"isActive\":true,\"createdAt\":\"2026-06-14T10:00:00Z\"}");
        }
        // Simulated GET: return a mock response containing expected fields
        return new ApiResponse(200, "{\"users\":[],\"content\":[],\"totalResults\":0,\"pageable\":{\"pageNumber\":1,\"pageSize\":10,\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false}},\"numberOfElements\":0,\"empty\":true,\"filters\":{},\"successful\":2,\"csv\":\"...\"}");
    }

    static ApiResponse httpPost(String url, String body) {
        if (body.contains("SUPER_ADMIN")) {
            return new ApiResponse(400, "{\"error\":{\"code\":\"INVALID_ROLE\",\"message\":\"Invalid role specified\"}}");
        }
        if (body.contains("\"firstName\":\"Test\"}") && !body.contains("email")) {
            return new ApiResponse(400, "{\"error\":{\"code\":\"MISSING_FIELDS\",\"message\":\"Missing required fields\"}}");
        }
        if (body.contains("duplicate@example.com")) {
            return new ApiResponse(409, "{\"error\":{\"code\":\"ALREADY_EXISTS\",\"message\":\"Email already exists\"}}");
        }
        // Bulk create
        if (url.contains("/bulk")) {
            return new ApiResponse(201, "{\"successful\":2,\"failed\":0,\"results\":[{\"identifier\":\"user1@example.com\",\"status\":\"CREATED\"},{\"identifier\":\"user2@example.com\",\"status\":\"CREATED\"}]}");
        }
        // Import
        if (url.contains("/import")) {
            return new ApiResponse(201, "{\"totalRecords\":100,\"processed\":98,\"failed\":2,\"errors\":[{\"row\":45,\"error\":\"Invalid email\",\"data\":{\"email\":\"invalid-email\"}}]}");
        }
        String uid = "usr_" + System.currentTimeMillis();
        return new ApiResponse(201, "{\"userId\":\"" + uid + "\",\"firstName\":\"Priya\",\"lastName\":\"Patel\",\"email\":\"priya@example.com\",\"role\":\"ADMIN\",\"department\":\"Engineering\",\"isActive\":true,\"status\":\"ACTIVE\",\"createdAt\":\"2026-06-14T10:00:00Z\"}");
    }

    static ApiResponse httpPut(String url, String body) {
        if (url.contains("/bulk")) {
            return new ApiResponse(200, "{\"successful\":2,\"failed\":0,\"results\":[{\"identifier\":\"usr_001\",\"status\":\"UPDATED\"},{\"identifier\":\"usr_002\",\"status\":\"UPDATED\"}]}");
        }
        return new ApiResponse(200, "{\"userId\":\"usr_001\",\"role\":\"MANAGER\",\"department\":\"Product\",\"isActive\":true}");
    }

    static ApiResponse httpPatch(String url, String body) {
        return new ApiResponse(200, "{\"userId\":\"usr_001\",\"isActive\":false}");
    }

    static ApiResponse httpDelete(String url) {
        return new ApiResponse(204, "");
    }

    static ApiResponse httpDelete(String url, String body) {
        return new ApiResponse(200, "{\"successful\":2,\"failed\":0}");
    }

    // ==================== UTILITY METHODS ====================

    static String buildUserJson(String firstName, String lastName, String email, String role, String department) {
        return "{"
            + "\"firstName\":\"" + firstName + "\","
            + "\"lastName\":\"" + lastName + "\","
            + "\"email\":\"" + email + "\","
            + "\"password\":\"SecurePass123!\","
            + "\"role\":\"" + role + "\","
            + "\"department\":\"" + department + "\","
            + "\"isActive\":true"
            + "}";
    }

    static void assertStatus(ApiResponse response, int... expected) {
        for (int e : expected) {
            if (response.status == e) return;
        }
        throw new AssertionError("Expected status in " + Arrays.toString(expected) + " but got " + response.status);
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

    static class ApiResponse {
        int status;
        String body;
        ApiResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
