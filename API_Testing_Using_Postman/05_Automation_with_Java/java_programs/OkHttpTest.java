import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

/**
 * OkHttpTest.java
 *
 * Demonstrates API testing using OkHttp library by Square.
 * Covers GET, POST, and header configuration.
 *
 * Prerequisites:
 * - com.squareup.okhttp3:okhttp
 * - org.testng:testng
 */
public class OkHttpTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Test 1: Basic GET request using OkHttp.
     */
    @Test
    public void testGetRequest() throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URI + "/api/users/2")
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Status Code: " + response.code());
            System.out.println("Content-Type: " + response.header("Content-Type"));

            assertEquals(response.code(), 200);
            assertNotNull(response.body());
            assertNotNull(response.body().string());
        }
    }

    /**
     * Test 2: GET request with custom headers.
     */
    @Test
    public void testGetWithHeaders() throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URI + "/api/users/2")
            .header("Accept", "application/json")
            .header("X-Custom-Header", "OkHttpValue")
            .header("User-Agent", "OkHttp-Test")
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Status Code: " + response.code());
            System.out.println("All Headers received: " + response.headers());

            assertEquals(response.code(), 200);
            assertNotNull(response.header("Content-Type"));
        }
    }

    /**
     * Test 3: POST request with JSON body.
     */
    @Test
    public void testPostRequest() throws IOException {
        String jsonBody = "{\"name\":\"OkHttpUser\",\"job\":\"OkHttpJob\"}";

        RequestBody requestBody = RequestBody.create(
            jsonBody,
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(BASE_URI + "/api/users")
            .post(requestBody)
            .header("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("Status Code: " + response.code());
            System.out.println("Response Body: " + responseBody);

            assertEquals(response.code(), 201);
            assertTrue(responseBody.contains("OkHttpUser"));
            assertTrue(responseBody.contains("OkHttpJob"));
        }
    }

    /**
     * Test 4: GET request with query parameters.
     */
    @Test
    public void testGetWithQueryParams() throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URI + "/api/users")
            .newBuilder()
            .addQueryParameter("page", "2")
            .addQueryParameter("per_page", "3")
            .build();

        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("Status Code: " + response.code());
            System.out.println("Response Body (truncated): " + responseBody.substring(0, Math.min(300, responseBody.length())));

            assertEquals(response.code(), 200);
            assertTrue(responseBody.contains("page"));
            assertTrue(responseBody.contains("data"));
        }
    }

    /**
     * Test 5: PUT request with JSON body.
     */
    @Test
    public void testPutRequest() throws IOException {
        String jsonBody = "{\"name\":\"OkHttp Updated\",\"job\":\"OkHttp Updated Job\"}";

        RequestBody requestBody = RequestBody.create(
            jsonBody,
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(BASE_URI + "/api/users/2")
            .put(requestBody)
            .header("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("Status Code: " + response.code());
            System.out.println("Response Body: " + responseBody);

            assertEquals(response.code(), 200);
            assertTrue(responseBody.contains("OkHttp Updated"));
        }
    }

    /**
     * Test 6: DELETE request.
     */
    @Test
    public void testDeleteRequest() throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URI + "/api/users/2")
            .delete()
            .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Status Code: " + response.code());

            assertEquals(response.code(), 204);
        }
    }

    /**
     * Test 7: Request with authentication header.
     */
    @Test
    public void testWithAuthHeader() throws IOException {
        String token = "dummy_token_for_demo";

        Request request = new Request.Builder()
            .url("https://httpbin.org/get")
            .header("Authorization", "Bearer " + token)
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("Status Code: " + response.code());
            System.out.println("Response Body: " + responseBody);

            assertEquals(response.code(), 200);
            assertTrue(responseBody.contains("Bearer " + token));
        }
    }

    /**
     * Test 8: Form data POST request.
     */
    @Test
    public void testFormDataPost() throws IOException {
        RequestBody requestBody = new FormBody.Builder()
            .add("username", "testuser")
            .add("password", "testpass")
            .build();

        Request request = new Request.Builder()
            .url("https://httpbin.org/post")
            .post(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("Status Code: " + response.code());
            System.out.println("Response Body: " + responseBody);

            assertEquals(response.code(), 200);
            assertTrue(responseBody.contains("testuser"));
            assertTrue(responseBody.contains("testpass"));
        }
    }

    /**
     * Test 9: Response cache check.
     */
    @Test
    public void testCacheControl() throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URI + "/api/users/2")
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Cache-Control: " + response.header("Cache-Control"));
            System.out.println("Is successful: " + response.isSuccessful());
            System.out.println("Is redirect: " + response.isRedirect());

            assertTrue(response.isSuccessful());
        }
    }

    /**
     * Test 10: Synchronous vs Asynchronous comparison note.
     * OkHttp supports async with enqueue().
     */
    public void noteAsyncSupport() {
        // Asynchronous call example (not executed as test):
        // client.newCall(request).enqueue(new Callback() {
        //     @Override
        //     public void onFailure(Call call, IOException e) {
        //         e.printStackTrace();
        //     }
        //     @Override
        //     public void onResponse(Call call, Response response) throws IOException {
        //         System.out.println(response.body().string());
        //     }
        // });
    }
}
