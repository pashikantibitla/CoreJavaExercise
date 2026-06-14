import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/**
 * UserController.java
 *
 * Controller pattern for User API endpoints.
 * This class encapsulates direct HTTP interaction with the User API,
 * abstracting request building and response handling.
 *
 * The Controller layer is the lowest layer of the framework and is responsible
 * for making raw API calls. It does not contain business logic.
 *
 * Prerequisites:
 * - io.rest-assured:rest-assured
 */
public class UserController {

    private static final String USERS_ENDPOINT = "/users";
    private static final String SINGLE_USER_ENDPOINT = "/users/{id}";

    /**
     * GET all users with pagination.
     *
     * @param page the page number to retrieve
     * @return Response object containing the list of users
     */
    public Response getUsers(int page) {
        return given()
            .queryParam("page", page)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .get(USERS_ENDPOINT);
    }

    /**
     * GET a single user by ID.
     *
     * @param userId the user ID to retrieve
     * @return Response object containing user data
     */
    public Response getUserById(int userId) {
        return given()
            .pathParam("id", userId)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .get(SINGLE_USER_ENDPOINT);
    }

    /**
     * POST a new user.
     *
     * @param requestBody the request body as a JSON string or POJO
     * @return Response object containing created user data
     */
    public Response createUser(Object requestBody) {
        return given()
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .post(USERS_ENDPOINT);
    }

    /**
     * PUT (update) an existing user.
     *
     * @param userId the user ID to update
     * @param requestBody the updated user data
     * @return Response object containing updated user data
     */
    public Response updateUser(int userId, Object requestBody) {
        return given()
            .pathParam("id", userId)
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .put(SINGLE_USER_ENDPOINT);
    }

    /**
     * PATCH (partial update) an existing user.
     *
     * @param userId the user ID to patch
     * @param requestBody the partial update data
     * @return Response object containing updated user data
     */
    public Response patchUser(int userId, Object requestBody) {
        return given()
            .pathParam("id", userId)
            .body(requestBody)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .patch(SINGLE_USER_ENDPOINT);
    }

    /**
     * DELETE a user.
     *
     * @param userId the user ID to delete
     * @return Response object (usually 204 No Content)
     */
    public Response deleteUser(int userId) {
        return given()
            .pathParam("id", userId)
            .log().ifValidationFails()
        .when()
            .delete(SINGLE_USER_ENDPOINT);
    }

    /**
     * GET users with custom query parameters.
     *
     * @param page the page number
     * @param perPage number of items per page
     * @return Response object containing filtered results
     */
    public Response getUsersWithParams(int page, int perPage) {
        return given()
            .queryParam("page", page)
            .queryParam("per_page", perPage)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .get(USERS_ENDPOINT);
    }

    /**
     * GET users with custom headers.
     *
     * @param customHeaderName custom header name
     * @param customHeaderValue custom header value
     * @return Response object
     */
    public Response getUsersWithCustomHeader(String customHeaderName, String customHeaderValue) {
        return given()
            .header(customHeaderName, customHeaderValue)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .get(USERS_ENDPOINT);
    }

    /**
     * POST a new user with authorization token.
     *
     * @param requestBody the request body
     * @param token Bearer token for authentication
     * @return Response object
     */
    public Response createUserWithAuth(Object requestBody, String token) {
        return given()
            .body(requestBody)
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + token)
            .log().ifValidationFails()
        .when()
            .post(USERS_ENDPOINT);
    }

    /**
     * GET a user with authorization token.
     *
     * @param userId the user ID
     * @param token Bearer token for authentication
     * @return Response object
     */
    public Response getUserByIdWithAuth(int userId, String token) {
        return given()
            .pathParam("id", userId)
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .log().ifValidationFails()
        .when()
            .get(SINGLE_USER_ENDPOINT);
    }
}
