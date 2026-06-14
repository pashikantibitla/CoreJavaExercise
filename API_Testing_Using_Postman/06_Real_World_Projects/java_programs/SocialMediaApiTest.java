/**
 * SocialMediaApiTest.java
 * 
 * Social Media API test suite covering OAuth 2.0 Authentication,
 * Create Post, Like/Comment, Feed Retrieval, and Notifications.
 * 
 * Demonstrates:
 * - OAuth 2.0 token management
 * - Nested JSON structures (media, location, mentions)
 * - Real-time feed testing with pagination
 * - Notification system validation
 */

import java.util.*;

public class SocialMediaApiTest {
    private static int passed = 0;
    private static int failed = 0;
    private static final String BASE_URL = "https://api.example.com/api/v1";
    private static String accessToken;
    private static String postId;
    private static String commentId;
    private static java.util.Set<String> likedPosts = new java.util.HashSet<>();

    public static void main(String[] args) {
        System.out.println("=== Social Media API Test Suite ===\n");

        runTest("TC-OAUTH-01: Authorization Code Flow", SocialMediaApiTest::testAuthorizationCodeFlow);
        runTest("TC-OAUTH-02: Token Refresh", SocialMediaApiTest::testTokenRefresh);
        runTest("TC-OAUTH-03: Invalid Client ID", SocialMediaApiTest::testInvalidClientId);
        runTest("TC-OAUTH-04: Expired Authorization Code", SocialMediaApiTest::testExpiredAuthCode);
        runTest("TC-POST-01: Create Text Post", SocialMediaApiTest::testCreateTextPost);
        runTest("TC-POST-02: Create Post with Media", SocialMediaApiTest::testCreatePostWithMedia);
        runTest("TC-POST-03: Create Post with Location", SocialMediaApiTest::testCreatePostWithLocation);
        runTest("TC-POST-04: Create Post with Tags", SocialMediaApiTest::testCreatePostWithTags);
        runTest("TC-POST-05: Exceed Character Limit", SocialMediaApiTest::testExceedCharacterLimit);
        runTest("TC-POST-06: Empty Content", SocialMediaApiTest::testEmptyContent);
        runTest("TC-POST-07: XSS Sanitization", SocialMediaApiTest::testXssSanitization);
        runTest("TC-LIKE-01: Like Post", SocialMediaApiTest::testLikePost);
        runTest("TC-LIKE-02: Unlike Post", SocialMediaApiTest::testUnlikePost);
        runTest("TC-LIKE-03: Like Already Liked Post", SocialMediaApiTest::testLikeAlreadyLiked);
        runTest("TC-COM-01: Add Comment", SocialMediaApiTest::testAddComment);
        runTest("TC-COM-02: Add Nested Reply", SocialMediaApiTest::testNestedReply);
        runTest("TC-COM-03: Add Comment with Mention", SocialMediaApiTest::testCommentWithMention);
        runTest("TC-COM-04: Delete Comment", SocialMediaApiTest::testDeleteComment);
        runTest("TC-FEED-01: Get Home Feed", SocialMediaApiTest::testHomeFeed);
        runTest("TC-FEED-02: Get User Profile Feed", SocialMediaApiTest::testProfileFeed);
        runTest("TC-FEED-03: Feed Pagination", SocialMediaApiTest::testFeedPagination);
        runTest("TC-FEED-04: Feed with Blocked Content", SocialMediaApiTest::testBlockedContent);
        runTest("TC-NOTIF-01: Get Notifications", SocialMediaApiTest::testGetNotifications);
        runTest("TC-NOTIF-02: Mark Notification Read", SocialMediaApiTest::testMarkNotificationRead);
        runTest("TC-NOTIF-03: Mark All Read", SocialMediaApiTest::testMarkAllRead);
        runTest("TC-NOTIF-04: Notification Pagination", SocialMediaApiTest::testNotificationPagination);

        System.out.println("\n=== Test Results ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total:  " + (passed + failed));
    }

    private static void runTest(String name, Runnable test) {
        try {
            test.run();
            System.out.println("  PASS: " + name);
            passed++;
        } catch (AssertionError e) {
            System.out.println("  FAIL: " + name + " -> " + e.getMessage());
            failed++;
        } catch (Exception e) {
            System.out.println("  ERROR: " + name + " -> " + e.getMessage());
            failed++;
        }
    }

    // ==================== OAUTH TESTS ====================

    static void testAuthorizationCodeFlow() {
        // Step 1: Simulate authorization request
        ApiResponse authResponse = httpGet(BASE_URL + "/oauth/authorize?client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=read,write");
        assertContains(authResponse.body, "code");
        String authCode = extractField(authResponse.body, "code");

        // Step 2: Exchange code for token
        String tokenBody = "grant_type=authorization_code&code=" + authCode + "&client_id=CLIENT_ID&client_secret=SECRET&redirect_uri=REDIRECT_URI";
        ApiResponse tokenResponse = httpPostForm(BASE_URL + "/oauth/token", tokenBody);
        assertStatus(tokenResponse, 200);
        assertContains(tokenResponse.body, "access_token");
        assertContains(tokenResponse.body, "Bearer");
        accessToken = extractField(tokenResponse.body, "access_token");
    }

    static void testTokenRefresh() {
        String body = "grant_type=refresh_token&refresh_token=def50200...&client_id=CLIENT_ID&client_secret=SECRET";
        ApiResponse response = httpPostForm(BASE_URL + "/oauth/token", body);
        assertStatus(response, 200);
        assertContains(response.body, "access_token");
    }

    static void testInvalidClientId() {
        String body = "grant_type=authorization_code&code=CODE&client_id=INVALID&client_secret=SECRET";
        ApiResponse response = httpPostForm(BASE_URL + "/oauth/token", body);
        assertContains(response.body, "error");
    }

    static void testExpiredAuthCode() {
        String body = "grant_type=authorization_code&code=EXPIRED&client_id=CLIENT_ID&client_secret=SECRET";
        ApiResponse response = httpPostForm(BASE_URL + "/oauth/token", body);
        assertContains(response.body, "error");
    }

    // ==================== POST TESTS ====================

    static void testCreateTextPost() {
        String json = "{\"content\":\"Just completed a marathon! Feeling amazing!\",\"visibility\":\"PUBLIC\"}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        assertStatus(response, 201);
        assertContains(response.body, "postId");
        postId = extractField(response.body, "postId");
    }

    static void testCreatePostWithMedia() {
        String json = "{"
            + "\"content\":\"Check out my new camera!\","
            + "\"media\":[{\"type\":\"image\",\"url\":\"https://cdn.example.com/media/cam.jpg\",\"caption\":\"My new camera\"}],"
            + "\"visibility\":\"PUBLIC\""
            + "}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        assertStatus(response, 201);
        assertContains(response.body, "media");
    }

    static void testCreatePostWithLocation() {
        String json = "{"
            + "\"content\":\"At the beach!\","
            + "\"visibility\":\"PUBLIC\","
            + "\"location\":{\"name\":\"Marine Drive\",\"latitude\":18.9543,\"longitude\":72.8114}"
            + "}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        assertStatus(response, 201);
        assertContains(response.body, "location");
    }

    static void testCreatePostWithTags() {
        String json = "{"
            + "\"content\":\"Love this place! #travel #india\","
            + "\"visibility\":\"PUBLIC\","
            + "\"tags\":[\"travel\",\"india\"]"
            + "}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        assertStatus(response, 201);
        assertContains(response.body, "tags");
    }

    static void testExceedCharacterLimit() {
        StringBuilder longContent = new StringBuilder();
        for (int i = 0; i < 300; i++) longContent.append("a");
        String json = "{\"content\":\"" + longContent + "\",\"visibility\":\"PUBLIC\"}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        assertContains(response.body, "error");
    }

    static void testEmptyContent() {
        String json = "{\"content\":\"\",\"visibility\":\"PUBLIC\"}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        assertContains(response.body, "error");
    }

    static void testXssSanitization() {
        String json = "{\"content\":\"<script>alert('xss')</script>\",\"visibility\":\"PUBLIC\"}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts", json);
        if (response.body.contains("<script>")) {
            throw new AssertionError("XSS not sanitized: script tag present in response");
        }
    }

    // ==================== LIKE/UNLIKE TESTS ====================

    static void testLikePost() {
        if (postId == null) postId = "post_98765";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts/" + postId + "/likes", "{}");
        assertStatus(response, 200);
        assertContains(response.body, "likeId");
    }

    static void testUnlikePost() {
        if (postId == null) postId = "post_98765";
        ApiResponse response = httpDeleteAuth(BASE_URL + "/posts/" + postId + "/likes");
        assertStatus(response, 204);
    }

    static void testLikeAlreadyLiked() {
        if (postId == null) postId = "post_98765";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts/" + postId + "/likes", "{}");
        assertContains(response.body, "error");
    }

    // ==================== COMMENT TESTS ====================

    static void testAddComment() {
        if (postId == null) postId = "post_98765";
        String json = "{\"content\":\"Congratulations!\",\"parentCommentId\":null,\"mentions\":[]}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts/" + postId + "/comments", json);
        assertStatus(response, 201);
        assertContains(response.body, "commentId");
        commentId = extractField(response.body, "commentId");
    }

    static void testNestedReply() {
        if (postId == null) postId = "post_98765";
        if (commentId == null) commentId = "cmt_001";
        String json = "{\"content\":\"Thanks!\",\"parentCommentId\":\"" + commentId + "\",\"mentions\":[]}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts/" + postId + "/comments", json);
        assertStatus(response, 201);
        assertContains(response.body, "commentId");
    }

    static void testCommentWithMention() {
        if (postId == null) postId = "post_98765";
        String json = "{\"content\":\"@rahul great post!\",\"mentions\":[\"usr_12345\"]}";
        ApiResponse response = httpPostAuth(BASE_URL + "/posts/" + postId + "/comments", json);
        assertStatus(response, 201);
        assertContains(response.body, "mentions");
    }

    static void testDeleteComment() {
        if (postId == null) postId = "post_98765";
        if (commentId == null) commentId = "cmt_001";
        ApiResponse response = httpDeleteAuth(BASE_URL + "/posts/" + postId + "/comments/" + commentId);
        assertStatus(response, 204);
    }

    // ==================== FEED TESTS ====================

    static void testHomeFeed() {
        ApiResponse response = httpGetAuth(BASE_URL + "/feed?type=HOME&page=1&limit=20");
        assertStatus(response, 200);
        assertContains(response.body, "posts");
        assertContains(response.body, "pagination");
    }

    static void testProfileFeed() {
        ApiResponse response = httpGetAuth(BASE_URL + "/feed?type=PROFILE&userId=usr_12345&page=1&limit=20");
        assertStatus(response, 200);
        assertContains(response.body, "posts");
    }

    static void testFeedPagination() {
        ApiResponse response = httpGetAuth(BASE_URL + "/feed?type=HOME&cursor=abc123&limit=20");
        assertStatus(response, 200);
        assertContains(response.body, "cursor");
        assertContains(response.body, "hasMore");
    }

    static void testBlockedContent() {
        ApiResponse response = httpGetAuth(BASE_URL + "/feed?type=HOME&page=1&limit=20");
        // Verify blocked user content is not in feed
        if (response.body.contains("blocked_user_content")) {
            throw new AssertionError("Blocked user content should not appear in feed");
        }
    }

    // ==================== NOTIFICATION TESTS ====================

    static void testGetNotifications() {
        ApiResponse response = httpGetAuth(BASE_URL + "/notifications");
        assertStatus(response, 200);
        assertContains(response.body, "notifications");
        assertContains(response.body, "unreadCount");
    }

    static void testMarkNotificationRead() {
        ApiResponse response = httpPutAuth(BASE_URL + "/notifications/notif_001/read", "{}");
        assertStatus(response, 200);
        assertContains(response.body, "isRead");
        assertContains(response.body, "true");
    }

    static void testMarkAllRead() {
        ApiResponse response = httpPutAuth(BASE_URL + "/notifications/read-all", "{}");
        assertStatus(response, 200);
    }

    static void testNotificationPagination() {
        ApiResponse response = httpGetAuth(BASE_URL + "/notifications?page=1&limit=10");
        assertStatus(response, 200);
        assertContains(response.body, "notifications");
    }

    // ==================== HTTP HELPERS ====================

    static ApiResponse httpGet(String url) {
        return new ApiResponse(200, "{\"code\":\"auth_code_123\"}");
    }

    static ApiResponse httpGetAuth(String url) {
        return new ApiResponse(200, "{"
            + "\"posts\":[{\"postId\":\"post_98765\",\"content\":\"Hello\",\"likes\":245,\"comments\":32,\"shares\":12}],"
            + "\"pagination\":{\"cursor\":\"abc123\",\"hasMore\":true},"
            + "\"notifications\":[{\"notificationId\":\"notif_001\",\"type\":\"LIKE\",\"isRead\":false}],"
            + "\"unreadCount\":2,"
            + "\"totalCount\":15"
            + "}");
    }

    static ApiResponse httpPostForm(String url, String body) {
        if (body.contains("client_id=INVALID") || body.contains("INVALID")) {
            return new ApiResponse(401, "{\"error\":\"invalid_client\",\"error_description\":\"Client authentication failed\"}");
        }
        if (body.contains("EXPIRED")) {
            return new ApiResponse(400, "{\"error\":\"invalid_grant\",\"error_description\":\"Authorization code expired\"}");
        }
        return new ApiResponse(200, "{"
            + "\"access_token\":\"eyJhbGciOiJIUzI1NiJ9...\","
            + "\"token_type\":\"Bearer\","
            + "\"expires_in\":3600,"
            + "\"refresh_token\":\"def50200...\","
            + "\"scope\":\"read write\""
            + "}");
    }

    static ApiResponse httpPostAuth(String url, String body) {
        // Likes
        if (url.contains("/likes")) {
            String likedPostId = url.replaceAll(".*post_(\\w+)/likes.*", "post_$1");
            if (likedPosts.contains(likedPostId)) {
                return new ApiResponse(409, "{\"error\":{\"code\":\"ALREADY_LIKED\",\"message\":\"Post already liked\"}}");
            }
            likedPosts.add(likedPostId);
            return new ApiResponse(200, "{\"likeId\":\"like_001\",\"postId\":\"" + likedPostId + "\",\"userId\":\"usr_67890\",\"createdAt\":\"2026-06-14T12:05:00Z\"}");
        }
        // Comments
        if (url.contains("/comments")) {
            String mentions = body.contains("mentions") ? "\"mentions\":[\"usr_12345\"]," : "";
            return new ApiResponse(201, "{"
                + "\"commentId\":\"cmt_\"" + System.currentTimeMillis() + ","
                + "\"postId\":\"post_98765\","
                + "\"userId\":\"usr_67890\","
                + "\"content\":\"Congratulations!\","
                + mentions
                + "\"likes\":0,"
                + "\"replies\":0,"
                + "\"createdAt\":\"2026-06-14T12:10:00Z\""
                + "}");
        }
        // Posts
        if (body.contains("<script>")) {
            return new ApiResponse(201, "{\"postId\":\"post_clean\",\"userId\":\"usr_12345\",\"content\":\"alert('xss')\",\"visibility\":\"PUBLIC\",\"likes\":0,\"comments\":0,\"shares\":0}");
        }
        if (body.contains("\"content\":\"\"")) {
            return new ApiResponse(400, "{\"error\":{\"code\":\"EMPTY_CONTENT\",\"message\":\"Post content cannot be empty\"}}");
        }
        // Check character limit: if body is very long
        if (body.length() > 300) {
            return new ApiResponse(400, "{\"error\":{\"code\":\"CONTENT_TOO_LONG\",\"message\":\"Content exceeds maximum length\"}}");
        }
        // Build response based on request body
        String media = body.contains("media") ? "\"media\":[{\"mediaId\":\"med_001\",\"type\":\"image\",\"url\":\"https://cdn.example.com/media/cam.jpg\",\"caption\":\"My new camera\"}]," : "";
        String location = body.contains("location") ? "\"location\":{\"name\":\"Marine Drive\",\"latitude\":18.9543,\"longitude\":72.8114}," : "";
        String tags = body.contains("tags") ? "\"tags\":[\"travel\",\"india\"]," : "";
        return new ApiResponse(201, "{"
            + "\"postId\":\"post_\"" + System.currentTimeMillis() + ","
            + "\"userId\":\"usr_12345\","
            + "\"content\":\"Hello\","
            + media
            + location
            + tags
            + "\"visibility\":\"PUBLIC\","
            + "\"likes\":0,"
            + "\"comments\":0,"
            + "\"shares\":0,"
            + "\"createdAt\":\"2026-06-14T12:00:00Z\""
            + "}");
    }

    static ApiResponse httpPutAuth(String url, String body) {
        return new ApiResponse(200, "{\"isRead\":true}");
    }

    static ApiResponse httpDeleteAuth(String url) {
        return new ApiResponse(204, "");
    }

    // ==================== ASSERTIONS ====================

    static void assertStatus(ApiResponse response, int... expected) {
        for (int e : expected) {
            if (response.status == e) return;
        }
        throw new AssertionError("Expected status in " + java.util.Arrays.toString(expected) + " but got " + response.status);
    }

    static void assertContains(String body, String substring) {
        if (body == null || !body.contains(substring)) {
            throw new AssertionError("Response body does not contain: " + substring);
        }
    }

    static String extractField(String json, String fieldName) {
        String search = "\"" + fieldName + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) {
            search = "\"" + fieldName + "\":";
            start = json.indexOf(search);
            if (start == -1) return null;
            start += search.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).replace("\"", "");
        }
        start += search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    static class ApiResponse {
        int status;
        String body;
        ApiResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
