import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

/**
 * RestAssuredPojoTest.java
 *
 * Demonstrates using POJOs (Plain Old Java Objects) for API request and response handling.
 * Shows automatic serialization (Object -> JSON) and deserialization (JSON -> Object)
 * using Jackson ObjectMapper.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - com.fasterxml.jackson.core:jackson-databind
 * - org.testng:testng
 */
public class RestAssuredPojoTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH = "/api";

    private ObjectMapper objectMapper;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;

        // Configure ObjectMapper
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Test 1: Create user using POJO for request body.
     * REST Assured automatically serializes the POJO to JSON.
     */
    @Test
    public void testCreateUserWithPojo() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("John Doe");
        request.setJob("Software Engineer");
        request.setEmail("john.doe@example.com");

        given()
            .body(request)
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("job", equalTo("Software Engineer"))
            .body("id", notNullValue())
            .log().all();
    }

    /**
     * Test 2: Deserialize response into a POJO.
     * REST Assured extracts the response and converts it to a Java object.
     */
    @Test
    public void testGetUserAndDeserialize() {
        UserResponse userResponse = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .as(UserResponse.class);

        // Assert on deserialized object
        assertNotNull(userResponse);
        assertNotNull(userResponse.getData());
        assertEquals(userResponse.getData().getId(), 2);
        assertNotNull(userResponse.getData().getEmail());
        assertTrue(userResponse.getData().getEmail().contains("@reqres.in"));

        System.out.println("User: " + userResponse.getData().getFirst_name() + " " + userResponse.getData().getLast_name());
    }

    /**
     * Test 3: Manual serialization using Jackson.
     * Convert POJO to JSON string manually for inspection or logging.
     */
    @Test
    public void testManualSerialization() throws JsonProcessingException {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Jane Smith");
        request.setJob("QA Engineer");
        request.setEmail("jane.smith@example.com");

        String jsonString = objectMapper.writeValueAsString(request);
        System.out.println("Serialized JSON: " + jsonString);

        assertTrue(jsonString.contains("Jane Smith"));
        assertTrue(jsonString.contains("QA Engineer"));
    }

    /**
     * Test 4: Manual deserialization using Jackson.
     * Convert JSON string to POJO manually.
     */
    @Test
    public void testManualDeserialization() throws JsonProcessingException {
        String json = "{\"name\":\"Alice\",\"job\":\"Manager\",\"email\":\"alice@example.com\"}";

        CreateUserRequest request = objectMapper.readValue(json, CreateUserRequest.class);

        assertEquals(request.getName(), "Alice");
        assertEquals(request.getJob(), "Manager");
        assertEquals(request.getEmail(), "alice@example.com");
    }

    /**
     * Test 5: Nested POJO deserialization.
     * Demonstrates handling nested objects in the response.
     */
    @Test
    public void testNestedPojoDeserialization() {
        UserResponse response = given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .log().all()
            .extract()
            .as(UserResponse.class);

        // Nested data assertions
        assertNotNull(response.getData());
        assertNotNull(response.getSupport());
        assertNotNull(response.getSupport().getUrl());
        assertNotNull(response.getSupport().getText());

        System.out.println("Support URL: " + response.getSupport().getUrl());
        System.out.println("Support Text: " + response.getSupport().getText());
    }

    // ==================== POJO CLASSES ====================

    /**
     * Request POJO for creating a user.
     */
    public static class CreateUserRequest {
        private String name;
        private String job;
        private String email;

        public CreateUserRequest() {}

        public CreateUserRequest(String name, String job) {
            this.name = name;
            this.job = job;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getJob() { return job; }
        public void setJob(String job) { this.job = job; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    /**
     * Response POJO for the /users/{id} endpoint.
     */
    public static class UserResponse {
        private UserData data;
        private Support support;

        public UserData getData() { return data; }
        public void setData(UserData data) { this.data = data; }

        public Support getSupport() { return support; }
        public void setSupport(Support support) { this.support = support; }
    }

    /**
     * Nested POJO representing user data.
     */
    public static class UserData {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getFirst_name() { return first_name; }
        public void setFirst_name(String first_name) { this.first_name = first_name; }

        public String getLast_name() { return last_name; }
        public void setLast_name(String last_name) { this.last_name = last_name; }

        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
    }

    /**
     * Nested POJO representing support information.
     */
    public static class Support {
        private String url;
        private String text;

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
