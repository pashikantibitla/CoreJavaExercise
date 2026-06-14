import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

/**
 * ApiFrameworkBaseTest.java
 *
 * Framework base test class that provides common setup and teardown
 * for all API test classes. Includes:
 * - Global REST Assured configuration
 * - Reusable RequestSpecification
 * - Test lifecycle logging
 * - Basic reporting hooks
 *
 * Test classes should extend this class to inherit common behavior.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - org.testng:testng
 */
public class ApiFrameworkBaseTest {

    // Reusable request specification for all tests
    protected static RequestSpecification requestSpec;

    // Global configuration values
    protected static final String BASE_URI = "https://reqres.in";
    protected static final String BASE_PATH = "/api";
    protected static final int DEFAULT_TIMEOUT_MS = 5000;

    /**
     * BeforeSuite: Runs once before all tests in the suite.
     * Configures global REST Assured settings and reusable request specification.
     */
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("=== Starting API Test Suite ===");

        // Set base URI and path for all requests
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;

        // Enable automatic logging when validation fails
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Set default timeout for requests
        RestAssured.config = RestAssured.config()
            .httpClient(RestAssured.config().getHttpClientConfig()
                .setParam("http.connection.timeout", DEFAULT_TIMEOUT_MS)
                .setParam("http.socket.timeout", DEFAULT_TIMEOUT_MS));

        // Build reusable request specification with common headers
        requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("X-Request-Source", "API-Automation-Framework")
            .addHeader("X-Test-Framework", "TestNG-RestAssured")
            .build();

        System.out.println("Base URI configured: " + BASE_URI);
        System.out.println("Base Path configured: " + BASE_PATH);
        System.out.println("Request Specification initialized");
    }

    /**
     * BeforeMethod: Runs before each test method.
     * Logs test method name and resets any per-test state.
     */
    @BeforeMethod
    public void beforeMethod(Method method) {
        System.out.println("--- Starting Test: " + method.getDeclaringClass().getSimpleName() + "." + method.getName() + " ---");
    }

    /**
     * AfterMethod: Runs after each test method.
     * Logs test result (PASS, FAIL, SKIP) and any exceptions.
     */
    @AfterMethod
    public void afterMethod(ITestResult result) {
        String status;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "PASS";
                break;
            case ITestResult.FAILURE:
                status = "FAIL";
                break;
            case ITestResult.SKIP:
                status = "SKIP";
                break;
            default:
                status = "UNKNOWN";
        }

        System.out.println("--- Test Result: " + status + " ---");

        if (result.getStatus() == ITestResult.FAILURE && result.getThrowable() != null) {
            System.out.println("Exception: " + result.getThrowable().getMessage());
        }
    }

    /**
     * AfterSuite: Runs once after all tests in the suite.
     * Performs cleanup and final logging.
     */
    @AfterSuite
    public void afterSuite() {
        System.out.println("=== API Test Suite Completed ===");
    }

    /**
     * Helper method: Returns a pre-configured given() with the common specification.
     * Child test classes can call this to start with common settings.
     */
    protected static io.restassured.specification.RequestSpecification givenRequest() {
        return RestAssured.given()
            .spec(requestSpec)
            .log().all();
    }

    /**
     * Helper method: Returns a pre-configured given() with the common specification
     * but without logging (for performance or bulk tests).
     */
    protected static io.restassured.specification.RequestSpecification givenRequestSilent() {
        return RestAssured.given()
            .spec(requestSpec);
    }

    /**
     * Helper method: Returns a pre-configured given() with the common specification
     * and an additional authorization token.
     */
    protected static io.restassured.specification.RequestSpecification givenRequestWithAuth(String token) {
        return RestAssured.given()
            .spec(requestSpec)
            .header("Authorization", "Bearer " + token)
            .log().all();
    }

    /**
     * Helper method: Returns a pre-configured given() with the common specification
     * and a custom header.
     */
    protected static io.restassured.specification.RequestSpecification givenRequestWithHeader(String headerName, String headerValue) {
        return RestAssured.given()
            .spec(requestSpec)
            .header(headerName, headerValue)
            .log().all();
    }

    /**
     * Helper method: Waits for a specified duration.
     * Useful for tests that need to simulate delays or polling.
     */
    protected static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrupted");
        }
    }

    /**
     * Helper method: Formats a test data object for logging.
     */
    protected static String formatForLog(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }
}
