import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

/**
 * RestAssuredChainingTest.java
 *
 * Demonstrates response extraction and request chaining.
 * Shows how to extract data from one response and use it in subsequent requests.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - org.testng:testng
 */
public class RestAssuredChainingTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    /**
     * Test 1: Extract a single value from response and use in assertion.
     */
    @Test
    public void testExtractSingleValue() {
        String userEmail = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .path("data.email");

        System.out.println("Extracted email: " + userEmail);
        assertTrue(userEmail.contains("@reqres.in"));
    }

    /**
     * Test 2: Extract multiple values into a list.
     */
    @Test
    public void testExtractListValues() {
        List<String> emails = given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .path("data.email");

        System.out.println("Extracted emails: " + emails);
        assertNotNull(emails);
        assertFalse(emails.isEmpty());

        for (String email : emails) {
            assertTrue(email.contains("@reqres.in"));
        }
    }

    /**
     * Test 3: Extract response as Response object for flexible processing.
     */
    @Test
    public void testExtractResponseObject() {
        Response response = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .response();

        // Extract different fields from the same response object
        int id = response.path("data.id");
        String email = response.path("data.email");
        String firstName = response.path("data.first_name");
        String lastName = response.path("data.last_name");

        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
        System.out.println("Name: " + firstName + " " + lastName);

        assertEquals(id, 2);
        assertNotNull(email);
        assertNotNull(firstName);
    }

    /**
     * Test 4: Extract using JsonPath for complex queries.
     */
    @Test
    public void testExtractWithJsonPath() {
        JsonPath jsonPath = given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .jsonPath();

        // Extract using JsonPath expressions
        int page = jsonPath.getInt("page");
        int perPage = jsonPath.getInt("per_page");
        int total = jsonPath.getInt("total");
        List<Map<String, Object>> users = jsonPath.getList("data");

        System.out.println("Page: " + page);
        System.out.println("Per Page: " + perPage);
        System.out.println("Total: " + total);
        System.out.println("Users count: " + users.size());

        assertEquals(page, 1);
        assertEquals(perPage, 6); // reqres.in default
        assertNotNull(users);
    }

    /**
     * Test 5: Request chaining - Create user then retrieve it.
     * Demonstrates extracting an ID from POST and using it in GET.
     */
    @Test
    public void testCreateAndRetrieveUser() {
        // Step 1: Create a user
        String createdId = given()
            .body("{\"name\":\"Chained User\",\"job\":\"Chained Job\"}")
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("Chained User"))
            .log().all()
            .extract()
            .path("id");

        System.out.println("Created user ID: " + createdId);
        assertNotNull(createdId);

        // Step 2: Retrieve the created user (simulated with existing ID)
        // Note: reqres.in is a mock API, so created users are not persistent.
        // In a real API, you would use the createdId.
        // For demonstration, we use a known existing ID.
        String retrievedEmail = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .path("data.email");

        System.out.println("Retrieved email: " + retrievedEmail);
        assertNotNull(retrievedEmail);
    }

    /**
     * Test 6: Extract and use in next request body.
     */
    @Test
    public void testExtractAndReuseInBody() {
        // Step 1: Get user details
        String firstName = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .path("data.first_name");

        // Step 2: Use extracted name in a new request
        String updateBody = String.format("{\"name\":\"%s Updated\",\"job\":\"Updated Role\"}", firstName);

        given()
            .pathParam("id", 2)
            .body(updateBody)
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .put("/users/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo(firstName + " Updated"))
            .log().all();
    }

    /**
     * Test 7: Extract headers and use in subsequent requests.
     */
    @Test
    public void testExtractHeaders() {
        Response response = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}");

        // Extract headers
        String contentType = response.getHeader("Content-Type");
        int statusCode = response.getStatusCode();
        Map<String, String> allHeaders = response.getHeaders().asMap();

        System.out.println("Content-Type: " + contentType);
        System.out.println("Status Code: " + statusCode);
        System.out.println("All Headers: " + allHeaders);

        assertNotNull(contentType);
        assertTrue(contentType.contains("application/json"));
        assertEquals(statusCode, 200);
    }

    /**
     * Test 8: Extract as Map for dynamic field access.
     */
    @Test
    public void testExtractAsMap() {
        Map<String, Object> userData = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .path("data");

        System.out.println("User data map: " + userData);

        // Access fields dynamically
        assertTrue(userData.containsKey("id"));
        assertTrue(userData.containsKey("email"));
        assertTrue(userData.containsKey("first_name"));
        assertTrue(userData.containsKey("last_name"));
        assertTrue(userData.containsKey("avatar"));

        int id = (Integer) userData.get("id");
        assertEquals(id, 2);
    }

    /**
     * Test 9: Extract and filter list items.
     */
    @Test
    public void testExtractAndFilterList() {
        List<Map<String, Object>> allUsers = given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .path("data");

        // Filter users with id > 3
        for (Map<String, Object> user : allUsers) {
            int id = (Integer) user.get("id");
            if (id > 3) {
                System.out.println("User with ID > 3: " + user.get("first_name") + " (ID: " + id + ")");
            }
        }

        assertFalse(allUsers.isEmpty());
    }

    /**
     * Test 10: Extract response as String for custom parsing.
     */
    @Test
    public void testExtractAsString() {
        String responseBody = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .asString();

        System.out.println("Raw response: " + responseBody);

        assertTrue(responseBody.contains("data"));
        assertTrue(responseBody.contains("id"));
        assertTrue(responseBody.contains("email"));
    }
}
