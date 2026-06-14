import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * RestAssuredAuthTest.java
 *
 * Demonstrates various authentication mechanisms in REST Assured:
 * - Basic Authentication
 * - Bearer Token Authentication
 * - OAuth 2.0
 * - API Key Authentication
 * - Preemptive Basic Authentication
 *
 * Note: Some tests use public test endpoints that simulate authentication.
 * Replace credentials with real values for production APIs.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 * - org.testng:testng
 */
public class RestAssuredAuthTest {

    /**
     * Setup method to configure logging.
     */
    @BeforeClass
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Test 1: Basic Authentication
     * Uses httpbin.org basic-auth endpoint for demonstration.
     * In real scenarios, replace with actual credentials and endpoint.
     */
    @Test
    public void testBasicAuthentication() {
        // httpbin.org provides a test endpoint for basic auth
        // Endpoint: /basic-auth/{user}/{password}
        given()
            .auth().basic("user", "passwd")
            .log().all()
        .when()
            .get("https://httpbin.org/basic-auth/user/passwd")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("user", equalTo("user"))
            .log().all();
    }

    /**
     * Test 2: Preemptive Basic Authentication
     * Sends credentials immediately without waiting for 401 challenge.
     * This reduces network round-trips.
     */
    @Test
    public void testPreemptiveBasicAuthentication() {
        given()
            .auth().preemptive().basic("user", "passwd")
            .log().all()
        .when()
            .get("https://httpbin.org/basic-auth/user/passwd")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .log().all();
    }

    /**
     * Test 3: Bearer Token Authentication
     * Demonstrates how to send a Bearer token in the Authorization header.
     * This is the most common authentication for modern REST APIs.
     */
    @Test
    public void testBearerTokenAuthentication() {
        String bearerToken = "dummy_bearer_token_for_demonstration";

        // Using REST Assured's oauth2 method for Bearer token
        given()
            .auth().oauth2(bearerToken)
            .log().all()
        .when()
            .get("https://httpbin.org/bearer")
        .then()
            // httpbin.org /bearer returns 401 for invalid tokens
            // In real APIs, this would validate the token
            .log().all();
    }

    /**
     * Test 4: Bearer Token as Header (Alternative approach)
     * Shows the explicit header approach for Bearer token.
     */
    @Test
    public void testBearerTokenAsHeader() {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

        given()
            .header("Authorization", "Bearer " + bearerToken)
            .header("Accept", "application/json")
            .log().all()
        .when()
            .get("https://httpbin.org/get")
        .then()
            .statusCode(200)
            .body("headers.Authorization", equalTo("Bearer " + bearerToken))
            .log().all();
    }

    /**
     * Test 5: Digest Authentication
     * Demonstrates Digest authentication scheme.
     * Digest is more secure than Basic as it uses hashing.
     */
    @Test
    public void testDigestAuthentication() {
        // Note: httpbin.org does not support digest auth by default
        // This demonstrates the syntax for actual APIs
        given()
            .auth().digest("username", "password")
            .log().all()
        .when()
            .get("https://httpbin.org/digest-auth/auth/user/passwd")
        .then()
            .log().all();
    }

    /**
     * Test 6: OAuth 2.0 Authentication
     * Demonstrates OAuth2 token usage.
     * In real scenarios, tokens are obtained from an authorization server.
     */
    @Test
    public void testOAuth2Authentication() {
        String accessToken = "sample_oauth2_access_token";

        given()
            .auth().oauth2(accessToken)
            .log().all()
        .when()
            .get("https://httpbin.org/get")
        .then()
            .statusCode(200)
            .body("headers.Authorization", equalTo("Bearer " + accessToken))
            .log().all();
    }

    /**
     * Test 7: API Key Authentication (Query Parameter)
     * Demonstrates API key passed as a query parameter.
     */
    @Test
    public void testApiKeyAsQueryParam() {
        String apiKey = "1234567890abcdef";

        given()
            .queryParam("api_key", apiKey)
            .log().all()
        .when()
            .get("https://httpbin.org/get")
        .then()
            .statusCode(200)
            .body("args.api_key", equalTo(apiKey))
            .log().all();
    }

    /**
     * Test 8: API Key Authentication (Header)
     * Demonstrates API key passed as a custom header.
     */
    @Test
    public void testApiKeyAsHeader() {
        String apiKey = "1234567890abcdef";

        given()
            .header("X-API-Key", apiKey)
            .log().all()
        .when()
            .get("https://httpbin.org/get")
        .then()
            .statusCode(200)
            .body("headers.X-Api-Key", equalTo(apiKey))
            .log().all();
    }

    /**
     * Test 9: Form Authentication (x-www-form-urlencoded)
     * Demonstrates login using form parameters.
     */
    @Test
    public void testFormAuthentication() {
        given()
            .formParam("username", "testuser")
            .formParam("password", "testpass")
            .log().all()
        .when()
            .post("https://httpbin.org/post")
        .then()
            .statusCode(200)
            .body("form.username", equalTo("testuser"))
            .body("form.password", equalTo("testpass"))
            .log().all();
    }

    /**
     * Test 10: Reusable Authentication with Request Specification
     * Demonstrates creating a reusable request specification with authentication.
     */
    @Test
    public void testReusableAuthSpecification() {
        String token = "my_reusable_token";

        io.restassured.specification.RequestSpecification authSpec =
            given()
                .auth().oauth2(token)
                .header("X-Request-Source", "Automation-Framework")
                .contentType("application/json");

        // Use the specification for multiple requests
        given()
            .spec(authSpec)
            .log().all()
        .when()
            .get("https://httpbin.org/get")
        .then()
            .statusCode(200)
            .log().all();

        given()
            .spec(authSpec)
            .body("{\"key\":\"value\"}")
            .log().all()
        .when()
            .post("https://httpbin.org/post")
        .then()
            .statusCode(200)
            .log().all();
    }
}
