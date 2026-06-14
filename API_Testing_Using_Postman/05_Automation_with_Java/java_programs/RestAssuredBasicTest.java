import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

/**
 * RestAssuredBasicTest.java
 *
 * Demonstrates basic HTTP operations using REST Assured:
 * GET, POST, PUT, DELETE, PATCH
 *
 * Uses https://reqres.in as a free fake API for testing.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - org.testng:testng
 */
public class RestAssuredBasicTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH = "/api";

    /**
     * Setup method to configure base URI and path before running tests.
     */
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    /**
     * Test 1: GET request to retrieve a list of users.
     * Validates status code 200 and response body contains data.
     */
    @Test
    public void testGetUsersList() {
        given()
            .queryParam("page", 2)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data", is(notNullValue()))
            .body("data.size()", greaterThan(0))
            .log().all();
    }

    /**
     * Test 2: GET request to retrieve a single user by ID.
     * Validates status code 200 and specific user data fields.
     */
    @Test
    public void testGetSingleUser() {
        given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@reqres.in"))
            .body("data.first_name", notNullValue())
            .body("data.last_name", notNullValue())
            .log().all();
    }

    /**
     * Test 3: POST request to create a new user.
     * Validates status code 201 and response contains generated id.
     */
    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\":\"John Doe\",\"job\":\"Software Engineer\"}";

        given()
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("job", equalTo("Software Engineer"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue())
            .log().all();
    }

    /**
     * Test 4: POST request to create a user and capture the response.
     * Demonstrates extracting values from the response for further use.
     */
    @Test
    public void testCreateUserAndExtractId() {
        String requestBody = "{\"name\":\"Jane Smith\",\"job\":\"QA Engineer\"}";

        String userId = given()
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .extract()
            .path("id");

        System.out.println("Created user ID: " + userId);
        assertNotNull(userId, "User ID should not be null");
    }

    /**
     * Test 5: PUT request to update an existing user.
     * Validates status code 200 and updated fields.
     */
    @Test
    public void testUpdateUser() {
        String requestBody = "{\"name\":\"John Updated\",\"job\":\"Senior Engineer\"}";

        given()
            .pathParam("id", 2)
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .put("/users/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo("John Updated"))
            .body("job", equalTo("Senior Engineer"))
            .body("updatedAt", notNullValue())
            .log().all();
    }

    /**
     * Test 6: PATCH request to partially update a user.
     * Validates status code 200 and updated field.
     */
    @Test
    public void testPatchUser() {
        String requestBody = "{\"job\":\"Lead Developer\"}";

        given()
            .pathParam("id", 2)
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .patch("/users/{id}")
        .then()
            .statusCode(200)
            .body("job", equalTo("Lead Developer"))
            .log().all();
    }

    /**
     * Test 7: DELETE request to remove a user.
     * Validates status code 204 (No Content) on successful deletion.
     */
    @Test
    public void testDeleteUser() {
        given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .delete("/users/{id}")
        .then()
            .statusCode(204)
            .log().all();
    }

    /**
     * Test 8: GET request for a non-existent user.
     * Validates status code 404 and error structure.
     */
    @Test
    public void testGetUserNotFound() {
        given()
            .pathParam("id", 999)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(404)
            .body("", isEmptyOrNullString())
            .log().all();
    }

    /**
     * Test 9: GET request with response time validation.
     * Validates that the API responds within 2 seconds.
     */
    @Test
    public void testResponseTimeValidation() {
        given()
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .time(lessThan(2000L))
            .log().all();
    }

    /**
     * Test 10: GET request extracting response as a String.
     * Demonstrates manual response extraction for custom parsing.
     */
    @Test
    public void testExtractResponseAsString() {
        Response response = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}");

        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        String contentType = response.getContentType();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Content Type: " + contentType);
        System.out.println("Response Body: " + responseBody);

        assertEquals(statusCode, 200);
        assertTrue(contentType.contains("application/json"));
        assertTrue(responseBody.contains(""data""));
    }
}
