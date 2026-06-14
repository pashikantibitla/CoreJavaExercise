import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import io.restassured.path.json.JsonPath;
import java.util.List;

/**
 * RestAssuredJsonPathTest.java
 *
 * Demonstrates JSONPath assertions and expressions in REST Assured.
 * JSONPath is a query language for JSON, similar to XPath for XML.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - io.rest-assured:json-path
 * - org.testng:testng
 */
public class RestAssuredJsonPathTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH = "/api";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    /**
     * Test 1: Basic JSONPath - access root element.
     */
    @Test
    public void testBasicJsonPath() {
        given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", notNullValue())
            .body("data.first_name", notNullValue())
            .log().all();
    }

    /**
     * Test 2: Nested JSONPath - access deeply nested elements.
     */
    @Test
    public void testNestedJsonPath() {
        given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@reqres.in"))
            .body("data.first_name", equalTo("Janet"))
            .body("data.last_name", equalTo("Weaver"))
            .body("support.url", notNullValue())
            .body("support.text", notNullValue())
            .log().all();
    }

    /**
     * Test 3: Array indexing - access specific array elements.
     */
    @Test
    public void testArrayIndexing() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data[0].id", equalTo(1))
            .body("data[0].email", notNullValue())
            .body("data[1].id", equalTo(2))
            .body("data[5].id", equalTo(6))
            .log().all();
    }

    /**
     * Test 4: Array size validation.
     */
    @Test
    public void testArraySize() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data", hasSize(6))
            .body("data.size()", equalTo(6))
            .body("data.length()", equalTo(6))
            .log().all();
    }

    /**
     * Test 5: Find all items matching a condition.
     */
    @Test
    public void testFindAll() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.findAll { it.id > 3 }.size()", greaterThan(0))
            .body("data.findAll { it.id > 3 }.id", hasItems(4, 5, 6))
            .log().all();
    }

    /**
     * Test 6: Find single item matching a condition.
     */
    @Test
    public void testFindSingleItem() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.find { it.id == 2 }.first_name", equalTo("Janet"))
            .body("data.find { it.id == 3 }.email", containsString("emma"))
            .log().all();
    }

    /**
     * Test 7: Collection operations - collect, sum, max, min.
     */
    @Test
    public void testCollectionOperations() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.collect { it.id }.sum()", equalTo(21)) // 1+2+3+4+5+6 = 21
            .body("data.collect { it.id }.max()", equalTo(6))
            .body("data.collect { it.id }.min()", equalTo(1))
            .log().all();
    }

    /**
     * Test 8: String operations in JSONPath.
     */
    @Test
    public void testStringOperations() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.findAll { it.email.contains('@reqres.in') }.size()", equalTo(6))
            .body("data.findAll { it.first_name.startsWith('G') }.size()", equalTo(1))
            .body("data.findAll { it.last_name.endsWith('a') }.size()", greaterThan(0))
            .log().all();
    }

    /**
     * Test 9: Extract list using JSONPath.
     */
    @Test
    public void testExtractListWithJsonPath() {
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

        List<String> emails = jsonPath.getList("data.email");
        List<String> firstNames = jsonPath.getList("data.first_name");
        List<Integer> ids = jsonPath.getList("data.id");

        System.out.println("Emails: " + emails);
        System.out.println("First Names: " + firstNames);
        System.out.println("IDs: " + ids);

        assertNotNull(emails);
        assertEquals(emails.size(), 6);
        assertTrue(emails.get(0).contains("@reqres.in"));
    }

    /**
     * Test 10: Filter with complex conditions.
     */
    @Test
    public void testComplexFilterConditions() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.findAll { it.id >= 2 && it.id <= 4 }.size()", equalTo(3))
            .body("data.findAll { it.first_name.length() > 4 }.size()", greaterThan(0))
            .log().all();
    }

    /**
     * Test 11: Extract single value with JSONPath.
     */
    @Test
    public void testExtractSingleValueWithJsonPath() {
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

        System.out.println("Extracted first name: " + firstName);
        assertEquals(firstName, "Janet");
    }

    /**
     * Test 12: Every item matcher.
     */
    @Test
    public void testEveryItemMatcher() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.id", everyItem(greaterThan(0)))
            .body("data.email", everyItem(containsString("@")))
            .body("data.email", everyItem(endsWith("reqres.in")))
            .log().all();
    }

    /**
     * Test 13: Has items matcher.
     */
    @Test
    public void testHasItemsMatcher() {
        given()
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data.id", hasItems(1, 2, 3, 4, 5, 6))
            .body("data.first_name", hasItem("George"))
            .body("data.last_name", hasItem("Bluth"))
            .log().all();
    }

    /**
     * Test 14: Is in matcher.
     */
    @Test
    public void testIsInMatcher() {
        given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", isIn(1, 2, 3, 4, 5, 6))
            .body("data.first_name", isIn("George", "Janet", "Emma", "Eve", "Charles", "Tracey"))
            .log().all();
    }

    /**
     * Test 15: Null and empty assertions.
     */
    @Test
    public void testNullAndEmptyAssertions() {
        given()
            .pathParam("id", 2)
            .log().all()
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", notNullValue())
            .body("data.email", not(emptyOrNullString()))
            .body("data.avatar", not(emptyOrNullString()))
            .body("error", nullValue())
            .log().all();
    }
}
