import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * HttpClientTest.java
 *
 * Demonstrates Java 11's built-in HttpClient for API testing.
 * Covers synchronous GET, POST, and setting headers, timeouts.
 *
 * Prerequisites:
 * - Java 11 or higher (java.net.http.HttpClient)
 */
public class HttpClientTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final HttpClient client;

    static {
        // Create HttpClient with custom configuration
        client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .version(HttpClient.Version.HTTP_2)
            .build();
    }

    public static void main(String[] args) {
        HttpClientTest test = new HttpClientTest();

        try {
            test.testGetRequest();
            test.testGetWithHeaders();
            test.testPostRequest();
            test.testGetWithQueryParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test 1: Basic synchronous GET request.
     */
    public void testGetRequest() throws Exception {
        System.out.println("=== Test 1: Basic GET Request ===");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users/2"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Content Type: " + response.headers().firstValue("Content-Type").orElse("Unknown"));
        System.out.println("Response Body (truncated): " + response.body().substring(0, Math.min(200, response.body().length())));
        System.out.println();
    }

    /**
     * Test 2: GET request with custom headers.
     */
    public void testGetWithHeaders() throws Exception {
        System.out.println("=== Test 2: GET with Headers ===");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users/2"))
            .header("Accept", "application/json")
            .header("X-Custom-Header", "CustomValue")
            .header("User-Agent", "Java-HttpClient-Test")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("All Headers:");
        response.headers().map().forEach((key, value) -> System.out.println("  " + key + " -> " + value));
        System.out.println("Response Body (truncated): " + response.body().substring(0, Math.min(200, response.body().length())));
        System.out.println();
    }

    /**
     * Test 3: POST request with JSON body.
     */
    public void testPostRequest() throws Exception {
        System.out.println("=== Test 3: POST Request ===");

        String jsonBody = "{\"name\":\"JavaHttpClient\",\"job\":\"Tester\"}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users"))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
        System.out.println();
    }

    /**
     * Test 4: GET request with query parameters.
     */
    public void testGetWithQueryParams() throws Exception {
        System.out.println("=== Test 4: GET with Query Parameters ===");

        String uri = BASE_URI + "/api/users?page=2&per_page=3";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body (truncated): " + response.body().substring(0, Math.min(300, response.body().length())));
        System.out.println();
    }

    /**
     * Test 5: PUT request to update a resource.
     */
    public void testPutRequest() throws Exception {
        System.out.println("=== Test 5: PUT Request ===");

        String jsonBody = "{\"name\":\"Updated Name\",\"job\":\"Updated Job\"}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users/2"))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
        System.out.println();
    }

    /**
     * Test 6: DELETE request.
     */
    public void testDeleteRequest() throws Exception {
        System.out.println("=== Test 6: DELETE Request ===");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users/2"))
            .DELETE()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
        System.out.println();
    }

    /**
     * Test 7: Synchronous response with timeout.
     */
    public void testWithTimeout() throws Exception {
        System.out.println("=== Test 7: Request with Timeout ===");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users/2"))
            .timeout(Duration.ofSeconds(5))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println();
    }

    /**
     * Test 8: Response body as InputStream for large responses.
     */
    public void testResponseAsInputStream() throws Exception {
        System.out.println("=== Test 8: Response as InputStream ===");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URI + "/api/users?page=1"))
            .GET()
            .build();

        HttpResponse<java.io.InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("InputStream received for processing large data");
        System.out.println();
    }
}
