import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * RestAssuredDataDrivenTest.java
 *
 * Demonstrates data-driven testing using TestNG's @DataProvider.
 * Shows inline data, dynamic data generation, and CSV-based data.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - org.testng:testng
 */
public class RestAssuredDataDrivenTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    /**
     * DataProvider 1: Inline hardcoded data.
     * Provides sets of user data for creating users.
     */
    @DataProvider(name = "userDataProvider")
    public Object[][] provideUserData() {
        return new Object[][] {
            {"John", "Developer", 201},
            {"Jane", "Manager", 201},
            {"Bob", "Tester", 201},
            {"Alice", "DevOps", 201}
        };
    }

    /**
     * Test 1: Create users using inline DataProvider.
     * Runs the same test method with multiple sets of data.
     */
    @Test(dataProvider = "userDataProvider")
    public void testCreateUserWithDataProvider(String name, String job, int expectedStatus) {
        String requestBody = String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);

        given()
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .post("/users")
        .then()
            .statusCode(expectedStatus)
            .body("name", equalTo(name))
            .body("job", equalTo(job))
            .body("id", notNullValue())
            .log().ifValidationFails();
    }

    /**
     * DataProvider 2: Dynamic data generation.
     * Generates data programmatically instead of hardcoding.
     */
    @DataProvider(name = "dynamicUserDataProvider")
    public Object[][] provideDynamicUserData() {
        Object[][] data = new Object[5][2];
        for (int i = 0; i < 5; i++) {
            data[i][0] = "User" + (i + 1);
            data[i][1] = "Job" + (i + 1);
        }
        return data;
    }

    /**
     * Test 2: Create users using dynamically generated data.
     */
    @Test(dataProvider = "dynamicUserDataProvider")
    public void testCreateUserWithDynamicData(String name, String job) {
        String requestBody = String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);

        given()
            .body(requestBody)
            .contentType(ContentType.JSON)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo(name))
            .body("job", equalTo(job))
            .log().ifValidationFails();
    }

    /**
     * DataProvider 3: Parallel execution.
     * Setting parallel = true allows TestNG to run tests concurrently.
     */
    @DataProvider(name = "parallelUserDataProvider", parallel = true)
    public Object[][] provideParallelUserData() {
        return new Object[][] {
            {"Parallel1", "Role1"},
            {"Parallel2", "Role2"},
            {"Parallel3", "Role3"},
            {"Parallel4", "Role4"}
        };
    }

    /**
     * Test 3: Parallel user creation.
     * Note: threadPoolSize in @Test is deprecated; use parallel in DataProvider.
     */
    @Test(dataProvider = "parallelUserDataProvider")
    public void testCreateUserParallel(String name, String job) {
        String requestBody = String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);

        given()
            .body(requestBody)
            .contentType(ContentType.JSON)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .log().ifValidationFails();
    }

    /**
     * DataProvider 4: Data with expected status codes including negative cases.
     */
    @DataProvider(name = "userDataWithStatusCodes")
    public Object[][] provideUserDataWithStatus() {
        return new Object[][] {
            {"ValidUser", "Developer", 201},           // Valid - should create
            {"AnotherUser", "Manager", 201},           // Valid - should create
            {"", "", 201}                               // Edge case (reqres.in accepts empty)
        };
    }

    /**
     * Test 4: Create users with expected status validation.
     */
    @Test(dataProvider = "userDataWithStatusCodes")
    public void testCreateUserWithExpectedStatus(String name, String job, int expectedStatus) {
        String requestBody = String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);

        given()
            .body(requestBody)
            .contentType(ContentType.JSON)
        .when()
            .post("/users")
        .then()
            .statusCode(expectedStatus)
            .log().ifValidationFails();
    }

    /**
     * DataProvider 5: CSV-style data (simulated as array of strings).
     * In real scenarios, use OpenCSV to read from a .csv file.
     */
    @DataProvider(name = "csvStyleDataProvider")
    public Object[][] provideCsvStyleData() {
        return new Object[][] {
            {"1", "John", "Developer", "john@example.com"},
            {"2", "Jane", "Manager", "jane@example.com"},
            {"3", "Bob", "Tester", "bob@example.com"},
            {"4", "Alice", "DevOps", "alice@example.com"}
        };
    }

    /**
     * Test 5: Simulate CSV-driven test.
     */
    @Test(dataProvider = "csvStyleDataProvider")
    public void testCreateUserFromCsvStyleData(String id, String name, String job, String email) {
        String requestBody = String.format(
            "{\"name\":\"%s\",\"job\":\"%s\",\"email\":\"%s\"}", name, job, email);

        given()
            .body(requestBody)
            .contentType(ContentType.JSON)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo(name))
            .body("job", equalTo(job))
            .log().ifValidationFails();

        System.out.println("Processed record ID: " + id);
    }

    /**
     * DataProvider 6: Simulated JSON data source.
     * In real scenarios, read JSON file using Jackson and convert to Object[][].
     */
    @DataProvider(name = "jsonStyleDataProvider")
    public Object[][] provideJsonStyleData() {
        return new Object[][] {
            // Each row represents a JSON object
            {"{\"name\":\"JsonUser1\",\"job\":\"Dev\"}"},
            {"{\"name\":\"JsonUser2\",\"job\":\"QA\"}"},
            {"{\"name\":\"JsonUser3\",\"job\":\"Ops\"}"}
        };
    }

    /**
     * Test 6: Simulate JSON-driven test.
     */
    @Test(dataProvider = "jsonStyleDataProvider")
    public void testCreateUserFromJsonStyleData(String jsonBody) {
        given()
            .body(jsonBody)
            .contentType(ContentType.JSON)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .log().ifValidationFails();
    }
}
