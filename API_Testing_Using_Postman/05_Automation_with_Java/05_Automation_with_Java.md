# 05 — Automation with Java for API Testing

> **Source:** Comprehensive API Testing with Java — REST Assured, TestNG, Maven, CI/CD
> **Topics:** RestAssured, HttpClient, OkHttp, POJOs, Data-Driven Testing, Framework Architecture, Reporting, CI/CD, Interview FAQs

---

## Table of Contents

1. [API Testing Libraries Overview](#1-api-testing-libraries-overview)
2. [RestAssured Deep Dive](#2-restassured-deep-dive)
3. [POJO-based Automation](#3-pojo-based-automation)
4. [Data-Driven Testing with Java](#4-data-driven-testing-with-java)
5. [Framework Architecture](#5-framework-architecture)
6. [Reporting](#6-reporting)
7. [Maven Configuration](#7-maven-configuration)
8. [CI/CD Integration](#8-cicd-integration)
9. [Interview FAQs](#9-interview-faqs)
10. [Quick Reference](#10-quick-reference)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. API Testing Libraries Overview

### What is API Testing?

API testing involves verifying application programming interfaces directly to validate functionality, reliability, performance, and security. Unlike UI testing, API testing focuses on the business logic layer of the software architecture.

```
┌──────────────────────────────────────────────────────────────┐
│                    LAYERS OF SOFTWARE TESTING                     │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  PRESENTATION LAYER (UI)                                 │   │
│  │  - Selenium, Cypress, Playwright                        │   │
│  │  - Visual validation, user interactions                 │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ calls                                │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  BUSINESS LOGIC LAYER (API)                              │   │
│  │  - REST Assured, HttpClient, Postman                    │   │
│  │  - Data validation, business rules                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ queries                              │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  DATABASE LAYER                                          │   │
│  │  - JDBC, Hibernate, SQL queries                         │   │
│  │  - Data integrity, constraints                          │   │
│  └─────────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
```

### Why Java for API Testing?

- **Strong typing** ensures compile-time safety for request/response contracts
- **Rich ecosystem** with mature libraries (REST Assured, Jackson, TestNG)
- **Enterprise integration** with Spring Boot, Maven, Gradle
- **CI/CD ready** with Jenkins, GitHub Actions, Azure DevOps
- **Object-oriented** design enables reusable, maintainable frameworks

### Popular Java API Testing Libraries

#### 1. RestAssured

REST Assured is a Java DSL (Domain Specific Language) for simplifying testing of REST APIs. It provides a behavior-driven development (BDD) style syntax and supports JSON and XML request/response bodies.

**Key Features:**
- BDD-style Given-When-Then syntax
- Static imports for readability
- Built-in JSON and XML parsing
- Request/Response specification reuse
- Authentication support (Basic, OAuth, Bearer)
- Session handling and cookies
- Logging and filtering capabilities
- Hamcrest matcher integration

```java
// Static imports used in REST Assured
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// Basic GET request with assertions
given()
    .baseUri("https://api.example.com")
.when()
    .get("/users")
.then()
    .statusCode(200)
    .body("data.size()", greaterThan(0));
```

#### 2. HttpClient (Java 11 Built-in)

Java 11 introduced a new, modern HTTP client as part of the standard library (`java.net.http.HttpClient`). It supports HTTP/1.1 and HTTP/2, synchronous and asynchronous programming models, and WebSocket.

**Key Features:**
- No external dependencies (built into JDK 11+)
- Synchronous and asynchronous APIs
- HTTP/2 support
- WebSocket support
- Reactive streams for response body handling
- CompletableFuture-based async operations

```java
// Java 11 HttpClient synchronous GET
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .GET()
    .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.statusCode());
System.out.println(response.body());
```

#### 3. OkHttp

OkHttp is an efficient HTTP client for Android and Java applications developed by Square. It supports HTTP/2, connection pooling, transparent GZIP, and response caching.

**Key Features:**
- HTTP/2 support with multiplexing
- Connection pooling reduces request latency
- Transparent GZIP compression
- Response caching to avoid network requests
- Simple API for common operations
- Interceptors for logging, authentication, and retry logic

```java
// OkHttp GET request
OkHttpClient client = new OkHttpClient();
Request request = new Request.Builder()
    .url("https://api.example.com/users")
    .build();

try (Response response = client.newCall(request).execute()) {
    System.out.println(response.code());
    System.out.println(response.body().string());
}
```

#### 4. JSONPath Library

JSONPath is a query language for JSON, similar to XPath for XML. It allows you to extract and filter data from JSON documents using path expressions.

**Key Features:**
- XPath-like expressions for JSON
- Nested property extraction
- Array filtering and slicing
- Aggregate functions (min, max, avg, sum)
- Filter expressions with conditions
- Integration with REST Assured for assertions

```java
// JSONPath example
String json = "{\"users\":[{\"name\":\"John\",\"age\":30},{\"name\":\"Jane\",\"age\":25}]}";
List<String> names = JsonPath.read(json, "$.users[*].name");
// Result: ["John", "Jane"]

// Filter with condition
List<String> adults = JsonPath.read(json, "$.users[?(@.age > 25)].name");
// Result: ["John"]
```

### Comparison Table of Libraries

| Feature | REST Assured | HttpClient | OkHttp | JSONPath |
|---------|-------------|------------|--------|----------|
| **External Dependency** | Yes | No (JDK 11+) | Yes | Yes (included in REST Assured) |
| **BDD Syntax** | Yes | No | No | No |
| **HTTP/2 Support** | Yes | Yes | Yes | N/A |
| **Async Support** | Limited | Yes | Yes | N/A |
| **Authentication Built-in** | Yes | Basic | Yes | N/A |
| **JSON Parsing** | Yes | Manual | Manual | Yes |
| **XML Parsing** | Yes | Manual | Manual | No |
| **Logging** | Built-in | Manual | Interceptors | N/A |
| **Best For** | API Test Automation | Simple HTTP calls | Mobile/High Performance | JSON extraction |
| **Learning Curve** | Low | Medium | Low | Low |

### When to Choose Which Library?

```
┌──────────────────────────────────────────────────────────────┐
│              LIBRARY SELECTION DECISION TREE                      │
│                                                                  │
│  Need BDD-style test automation?                                 │
│       ├─ YES → REST Assured                                      │
│       └─ NO → Continue...                                        │
│                                                                  │
│  Need zero external dependencies?                                  │
│       ├─ YES → Java HttpClient (JDK 11+)                         │
│       └─ NO → Continue...                                        │
│                                                                  │
│  Need HTTP/2 + Connection pooling + Performance?                 │
│       ├─ YES → OkHttp                                            │
│       └─ NO → Continue...                                        │
│                                                                  │
│  Need simple JSON extraction only?                                 │
│       ├─ YES → JSONPath standalone                               │
│       └─ NO → REST Assured (most comprehensive)                  │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. RestAssured Deep Dive

### Given-When-Then Syntax

REST Assured follows BDD-style syntax that makes tests readable and self-documenting:

```
┌──────────────────────────────────────────────────────────────┐
│              REST ASSURED BDD SYNTAX STRUCTURE                   │
│                                                                  │
│  given()  ──► Setup preconditions                                │
│    ├── baseUri / basePath                                        │
│    ├── headers, cookies, parameters                              │
│    ├── request body, authentication                              │
│    └── content type, logging                                     │
│                                                                  │
│  when()   ──► Perform the action                                 │
│    ├── HTTP methods: GET, POST, PUT, DELETE, PATCH               │
│    └── endpoint path                                             │
│                                                                  │
│  then()   ──► Validate the outcome                               │
│    ├── status code, status line                                  │
│    ├── body assertions, header assertions                        │
│    ├── response time, cookies                                    │
│    └── extract values for chaining                               │
└──────────────────────────────────────────────────────────────┘
```

```java
// Complete Given-When-Then example
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredSyntaxTest {
    
    @Test
    public void testCompleteFlow() {
        given()
            // Setup preconditions
            .baseUri("https://reqres.in")
            .basePath("/api")
            .header("Content-Type", "application/json")
            .log().all()
        .when()
            // Perform action
            .get("/users/2")
        .then()
            // Validate outcome
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@reqres.in"))
            .body("data.first_name", notNullValue())
            .header("Content-Type", containsString("application/json"))
            .time(lessThan(2000L))
            .log().all();
    }
}
```

### Request Specification

REST Assured provides multiple ways to configure requests:

#### Headers and Content Type

```java
// Setting headers
.given()
    .header("Content-Type", "application/json")
    .header("Authorization", "Bearer token123")
    .header("X-Request-Id", UUID.randomUUID().toString())

// Alternative header methods
.given()
    .headers("Content-Type", "application/json", 
             "Authorization", "Bearer token123")

// Using Map for headers
Map<String, String> headers = new HashMap<>();
headers.put("Content-Type", "application/json");
headers.put("Accept", "application/json");

given().headers(headers)

// Content type shorthand
.given()
    .contentType(ContentType.JSON)
    .accept(ContentType.JSON)
```

#### Query Parameters and Path Parameters

```java
// Query Parameters (appended to URL: ?page=2&per_page=10)
given()
    .queryParam("page", 2)
    .queryParam("per_page", 10)
.when()
    .get("/users")

// Multiple query params
.given()
    .queryParams("page", 2, "per_page", 10)

// Path Parameters (replaced in URL: /users/{id})
given()
    .pathParam("id", 2)
.when()
    .get("/users/{id}")

// Multiple path params
.given()
    .pathParams("userId", 2, "orderId", 101)
.when()
    .get("/users/{userId}/orders/{orderId}")
```

#### Request Body

```java
// String body
String jsonBody = "{\"name\":\"John\",\"job\":\"Developer\"}";

given()
    .body(jsonBody)
    .contentType(ContentType.JSON)
.when()
    .post("/users")

// JSON Object body
JSONObject json = new JSONObject();
json.put("name", "John");
json.put("job", "Developer");

given()
    .body(json.toString())
.when()
    .post("/users")

// POJO body (automatically serialized with Jackson)
UserRequest user = new UserRequest("John", "Developer");

given()
    .body(user)
.when()
    .post("/users")

// Form parameters (application/x-www-form-urlencoded)
given()
    .formParam("username", "john")
    .formParam("password", "secret")
.when()
    .post("/login")

// Multipart form data (file upload)
given()
    .multiPart("file", new File("data.json"), "application/json")
    .multiPart("description", "Test data file")
.when()
    .post("/upload")
```

#### Authentication

```java
// Basic Authentication
given()
    .auth().basic("username", "password")
    .auth().preemptive().basic("username", "password")

// Bearer Token (OAuth 2.0)
given()
    .auth().oauth2("your_access_token")
    // OR simply as header
    .header("Authorization", "Bearer " + token)

// OAuth 1.0
given()
    .auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret)

// API Key
given()
    .queryParam("api_key", "your_api_key")
    // OR as header
    .header("X-API-Key", "your_api_key")

// Digest Authentication
given()
    .auth().digest("username", "password")

// Certificate Authentication
given()
    .auth().certificate("path/to/keystore", "password")
```

#### Request Specification Reuse

```java
// Create reusable request specification
RequestSpecification requestSpec = new RequestSpecBuilder()
    .setBaseUri("https://reqres.in")
    .setBasePath("/api")
    .setContentType(ContentType.JSON)
    .addHeader("Accept", "application/json")
    .log(LogDetail.ALL)
    .build();

// Use in multiple tests
given()
    .spec(requestSpec)
.when()
    .get("/users/2")
.then()
    .statusCode(200);

given()
    .spec(requestSpec)
    .body(user)
.when()
    .post("/users")
.then()
    .statusCode(201);
```

### Response Validation

#### Status Code and Line Validation

```java
// Status code assertions
.then()
    .statusCode(200)
    .statusCode(anyOf(is(200), is(201), is(202)))
    .statusCode(allOf(greaterThanOrEqualTo(200), lessThan(300)))

// Status line
.then()
    .statusLine("HTTP/1.1 200 OK")
    .statusLine(containsString("OK"))
    .statusLine(matchesPattern("HTTP/1.1 \\d{3} .*"))
```

#### Body Validation

```java
// Basic body assertions
.then()
    .body("data.id", equalTo(2))
    .body("data.email", containsString("@reqres.in"))
    .body("data.first_name", notNullValue())
    .body("data.last_name", isEmptyOrNullString())
    .body("data.avatar", startsWith("https://"))

// Numeric assertions
.then()
    .body("data.id", greaterThan(0))
    .body("data.id", lessThan(100))
    .body("data.id", greaterThanOrEqualTo(1))
    .body("data.id", lessThanOrEqualTo(12))

// Collection assertions
.then()
    .body("data", hasSize(6))
    .body("data.id", hasItems(1, 2, 3))
    .body("data.email", everyItem(containsString("@")))

// Boolean assertions
.then()
    .body("success", is(true))
    .body("deleted", is(false))

// Null and empty assertions
.then()
    .body("error", nullValue())
    .body("data", not(empty()))
    .body("data[0].name", notNullValue())
```

#### Header Validation

```java
.then()
    .header("Content-Type", "application/json; charset=utf-8")
    .header("Content-Type", containsString("application/json"))
    .header("Content-Length", Integer::parseInt, greaterThan(0))
    .headers("Content-Type", "application/json", 
             "Connection", "keep-alive")
    .header("X-Request-Id", notNullValue())

// Get all headers
Map<String, String> headers = response.getHeaders();
```

#### Response Time Validation

```java
.then()
    .time(lessThan(2000L)) // milliseconds
    .time(lessThanOrEqualTo(3000L))
    .time(greaterThan(100L))
    .time(between(100L, 5000L))
```

### JSONPath Assertions

JSONPath expressions in REST Assured allow powerful navigation and assertions:

```java
// Root element access
.body("data.id", equalTo(2))

// Nested element access
.body("data.address.city", equalTo("New York"))

// Array element access
.body("data[0].name", equalTo("John"))
.body("data[-1].name", equalTo("Last User")) // Last element

// Array size
.body("data", hasSize(6))
.body("data.size()", equalTo(6))

// Array filtering
.body("data.findAll { it.age > 25 }.size()", equalTo(3))
.body("data.find { it.name == 'John' }.age", equalTo(30))
.body("data.findAll { it.name.startsWith('J') }.name", 
      hasItems("John", "Jane"))

// Collection operations
.body("data.collect { it.age }.sum()", equalTo(150))
.body("data.collect { it.age }.max()", equalTo(40))
.body("data.collect { it.age }.min()", equalTo(20))
.body("data.collect { it.age }.avg()", equalTo(30.0f))

// Complex nested navigation
.body("data.accounts[0].transactions[0].amount", equalTo(100))

// Wildcard
.body("data.*.name", hasItem("John"))
```

### XML Assertions

REST Assured also supports XML response validation:

```java
// XML path assertions
.when()
    .get("/soap-endpoint")
.then()
    .contentType(ContentType.XML)
    .body("user.id", equalTo("2"))
    .body("user.email", containsString("@example.com"))
    .body("users.user.size()", equalTo(6))
    .body("users.user[0].name", equalTo("John"))

// XML namespace handling
.given()
    .config(RestAssured.config()
        .xmlConfig(xmlConfig()
            .declareNamespace("ns", "http://example.com/schema")))
.when()
    .get("/soap-endpoint")
.then()
    .body("ns:user.ns:id", equalTo("2"));
```

### Response Extraction and Chaining

```java
// Extract single value
int userId = given()
    .get("/users/2")
.then()
    .statusCode(200)
    .extract()
    .path("data.id");

// Extract multiple values
Response response = given()
    .get("/users/2")
.then()
    .extract()
    .response();

String email = response.path("data.email");
String firstName = response.path("data.first_name");
List<String> avatarUrls = response.path("data.avatar");

// Extract into POJO
User user = given()
    .get("/users/2")
.then()
    .extract()
    .as(User.class);

// Extract and use in next request (chaining)
String createdId = given()
    .body(newUser)
.when()
    .post("/users")
.then()
    .statusCode(201)
    .extract()
    .path("id");

// Use extracted value in next request
given()
    .pathParam("id", createdId)
.when()
    .get("/users/{id}")
.then()
    .statusCode(200);

// Extract with JSONPath
JsonPath jsonPath = given()
    .get("/users")
.then()
    .extract()
    .jsonPath();

List<String> names = jsonPath.getList("data.name");
```

### Logging Configuration

```java
// Log all request details
.given()
    .log().all()

// Log specific request parts
.given()
    .log().parameters()
    .log().headers()
    .log().body()
    .log().cookies()
    .log().method()
    .log().uri()

// Log all response details
.then()
    .log().all()

// Log on condition (e.g., only on error)
.then()
    .log().ifError()
    .log().ifStatusCodeIsEqualTo(500)
    .log().ifValidationFails()

// Global logging configuration
RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

// Filter-based logging
 given()
    .filter(new RequestLoggingFilter())
    .filter(new ResponseLoggingFilter())
    .filter(new ErrorLoggingFilter())
```

### Handling Authentication

#### Basic Authentication

```java
// Basic Auth
@Test
public void testBasicAuth() {
    given()
        .auth().basic("username", "password")
        .baseUri("https://httpbin.org")
    .when()
        .get("/basic-auth/username/password")
    .then()
        .statusCode(200)
        .body("authenticated", equalTo(true));
}

// Preemptive Basic Auth (sends credentials immediately)
@Test
public void testPreemptiveBasicAuth() {
    given()
        .auth().preemptive().basic("username", "password")
    .when()
        .get("/basic-auth/username/password")
    .then()
        .statusCode(200);
}
```

#### Bearer Token Authentication

```java
@Test
public void testBearerToken() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
    
    given()
        .auth().oauth2(token)
        // OR: .header("Authorization", "Bearer " + token)
        .baseUri("https://api.example.com")
    .when()
        .get("/protected-resource")
    .then()
        .statusCode(200);
}
```

#### OAuth 2.0 Flow

```java
@Test
public void testOAuth2() {
    // Step 1: Obtain access token (simplified)
    String accessToken = given()
        .formParam("grant_type", "password")
        .formParam("username", "testuser")
        .formParam("password", "testpass")
        .formParam("client_id", "my-client")
        .formParam("client_secret", "secret")
        .post("https://auth-server.com/oauth/token")
        .then()
        .extract()
        .path("access_token");
    
    // Step 2: Use token in subsequent request
    given()
        .auth().oauth2(accessToken)
    .when()
        .get("https://api.example.com/resource")
    .then()
        .statusCode(200);
}
```

#### API Key Authentication

```java
@Test
public void testApiKey() {
    given()
        .queryParam("api_key", "1234567890abcdef")
    .when()
        .get("https://api.example.com/data")
    .then()
        .statusCode(200);
}
```

---

## 3. POJO-based Automation

### Why Use POJOs?

```
┌──────────────────────────────────────────────────────────────┐
│              POJO vs STRING-BASED APPROACH                       │
│                                                                  │
│  STRING-BASED:                                                   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ String json = "{\"name\":\"John\",\"job\":\"Developer\"}";   │   │
│  │ - Prone to syntax errors                                      │   │
│  │ - No compile-time validation                                │   │
│  │ - Hard to maintain and update                               │   │
│  │ - No IDE support (autocomplete, refactoring)                │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                  │
│  POJO-BASED:                                                     │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ UserRequest user = new UserRequest();                       │   │
│  │ user.setName("John");                                       │   │
│  │ user.setJob("Developer");                                   │   │
│  │ - Type-safe and compile-time validated                       │   │
│  │ - IDE support with autocomplete                             │   │
│  │ - Easy to refactor and maintain                             │   │
│  │ - Reusable across the framework                             │   │
│  └─────────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
```

### Creating Request/Response POJOs

```java
// Request POJO
public class CreateUserRequest {
    private String name;
    private String job;
    private String email;
    private List<String> skills;
    
    // Constructors
    public CreateUserRequest() {}
    
    public CreateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}
```

```java
// Response POJO
public class CreateUserResponse {
    private String id;
    private String name;
    private String job;
    private String email;
    private String createdAt;
    
    // Constructors
    public CreateUserResponse() {}
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    
    @Override
    public String toString() {
        return "CreateUserResponse{id='" + id + "', name='" + name + 
               "', job='" + job + "', email='" + email + "', createdAt='" + createdAt + "'}";
    }
}
```

### Serialization with Jackson for Requests

```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationTest {
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    public void testSerialization() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("John Doe");
        request.setJob("Software Engineer");
        request.setEmail("john@example.com");
        request.setSkills(Arrays.asList("Java", "REST API", "Selenium"));
        
        // Serialize to JSON string
        String jsonString = objectMapper.writeValueAsString(request);
        System.out.println("JSON: " + jsonString);
        
        // Output:
        // {"name":"John Doe","job":"Software Engineer",
        //  "email":"john@example.com","skills":["Java","REST API","Selenium"]}
        
        // REST Assured automatically serializes POJOs when you pass them
        given()
            .body(request) // Automatic serialization
            .contentType(ContentType.JSON)
        .when()
            .post("/api/users")
        .then()
            .statusCode(201);
    }
}
```

### Deserialization with Jackson for Responses

```java
public class DeserializationTest {
    
    @Test
    public void testDeserialization() {
        // REST Assured automatically deserializes responses
        CreateUserResponse response = given()
            .body(new CreateUserRequest("John", "Developer"))
            .contentType(ContentType.JSON)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .extract()
            .as(CreateUserResponse.class);
        
        System.out.println("Created ID: " + response.getId());
        System.out.println("Created At: " + response.getCreatedAt());
        
        // Assertions on deserialized object
        assertNotNull(response.getId());
        assertEquals("John", response.getName());
        assertEquals("Developer", response.getJob());
    }
    
    @Test
    public void testManualDeserialization() throws Exception {
        String jsonResponse = given()
            .get("https://reqres.in/api/users/2")
        .then()
            .extract()
            .asString();
        
        ObjectMapper mapper = new ObjectMapper();
        UserResponse user = mapper.readValue(jsonResponse, UserResponse.class);
        
        assertEquals(2, user.getData().getId());
    }
}
```

### Nested POJO Handling

```java
// Nested POJO for complex responses
public class UserResponse {
    private UserData data;
    private Support support;
    
    // Getters and Setters
    public UserData getData() { return data; }
    public void setData(UserData data) { this.data = data; }
    
    public Support getSupport() { return support; }
    public void setSupport(Support support) { this.support = support; }
}

public class UserData {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    
    // Getters and Setters
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

public class Support {
    private String url;
    private String text;
    
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
```

### Lombok Integration

Lombok reduces boilerplate code by generating getters, setters, constructors, and more at compile time.

```java
// Add Lombok dependency in pom.xml
// <dependency>
//     <groupId>org.projectlombok</groupId>
//     <artifactId>lombok</artifactId>
//     <version>1.18.30</version>
//     <scope>provided</scope>
// </dependency>

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data                    // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor       // Generates no-arg constructor
@AllArgsConstructor      // Generates all-arg constructor
@Builder                 // Generates builder pattern
public class UserRequest {
    private String name;
    private String job;
    private String email;
    private List<String> skills;
}

// Usage with Builder
UserRequest user = UserRequest.builder()
    .name("John")
    .job("Developer")
    .email("john@example.com")
    .skills(Arrays.asList("Java", "API Testing"))
    .build();

// REST Assured automatically serializes Lombok POJOs
given()
    .body(user)
    .contentType(ContentType.JSON)
.when()
    .post("/api/users")
.then()
    .statusCode(201);
```

### Memory Allocation During Serialization

```
┌──────────────────────────────────────────────────────────────┐
│              SERIALIZATION MEMORY FLOW                           │
│                                                                  │
│  HEAP MEMORY:                                                    │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  POJO Object (UserRequest)                               │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐      │   │
│  │  │ name: "John" │  │ job: "Dev"   │  │ skills: List │      │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           │                                      │
│                           │ ObjectMapper.writeValueAsString()   │
│                           ▼                                      │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  JSON String (new String object in heap)                 │   │
│  │  "{\"name\":\"John\",\"job\":\"Dev\"...}"                    │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           │                                      │
│                           │ HTTP Request Body                     │
│                           ▼                                      │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  Network Buffer (TCP/IP stack)                             │   │
│  │  [ bytes sent over network ]                               │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                  │
│  KEY POINTS:                                                     │
│  1. POJO stays in heap until GC                                  │
│  2. JSON String is a new object (can be large)                  │
│  3. Use -Xmx to control heap size for large tests               │
│  4. Reuse ObjectMapper (thread-safe after configuration)        │
│  5. Stream large responses instead of loading into memory       │
└──────────────────────────────────────────────────────────────┘
```

```java
// Efficient ObjectMapper usage
public class ObjectMapperFactory {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}

// For large payloads, use InputStream
@Test
public void testLargeResponse() {
    InputStream stream = given()
        .get("/large-data")
    .then()
        .extract()
        .asInputStream();
    
    // Stream processing instead of loading into memory
    ObjectMapper mapper = ObjectMapperFactory.getMapper();
    try (stream) {
        JsonNode root = mapper.readTree(stream);
        // Process nodes incrementally
    }
}
```

---

## 4. Data-Driven Testing with Java

### TestNG DataProvider

TestNG DataProvider enables running the same test with multiple sets of data, improving test coverage and reducing code duplication.

```java
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {
    
    // Inline DataProvider
    @DataProvider(name = "userData")
    public Object[][] provideUserData() {
        return new Object[][] {
            {"John", "Developer", 201},
            {"Jane", "Manager", 201},
            {"Bob", "Tester", 201},
            {"", "Invalid", 400}    // Negative case
        };
    }
    
    @Test(dataProvider = "userData")
    public void testCreateUser(String name, String job, int expectedStatus) {
        CreateUserRequest request = new CreateUserRequest(name, job);
        
        given()
            .body(request)
            .contentType(ContentType.JSON)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(expectedStatus);
    }
    
    // DataProvider from another class
    @Test(dataProvider = "externalData", dataProviderClass = ExternalDataProvider.class)
    public void testWithExternalData(String name, String job) {
        // Test implementation
    }
    
    // Dynamic DataProvider
    @DataProvider(name = "dynamicData")
    public Object[][] provideDynamicData() {
        List<Object[]> data = new ArrayList<>();
        
        // Generate data programmatically
        for (int i = 1; i <= 5; i++) {
            data.add(new Object[]{"User" + i, "Job" + i});
        }
        
        return data.toArray(new Object[0][]);
    }
    
    // Parallel execution with DataProvider
    @DataProvider(name = "parallelData", parallel = true)
    public Object[][] provideParallelData() {
        return new Object[][] {
            {"data1"}, {"data2"}, {"data3"}, {"data4"}
        };
    }
    
    @Test(dataProvider = "parallelData", threadPoolSize = 4)
    public void testParallel(String data) {
        // Runs in parallel
    }
}
```

### Reading CSV Files

```java
import com.opencsv.CSVReader;
import java.io.FileReader;

public class CsvDataProvider {
    
    @DataProvider(name = "csvData")
    public Object[][] readCsvData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        
        try (CSVReader reader = new CSVReader(new FileReader("test-data.csv"))) {
            String[] line;
            boolean skipHeader = true;
            
            while ((line = reader.readNext()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                data.add(line);
            }
        }
        
        return data.toArray(new Object[0][]);
    }
}
```

### Reading JSON Files

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonDataProvider {
    
    @DataProvider(name = "jsonData")
    public Object[][] readJsonData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        List<UserTestData> users = mapper.readValue(
            new File("test-data/users.json"),
            new TypeReference<List<UserTestData>>() {}
        );
        
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        
        return data;
    }
}

// JSON file structure (users.json)
// [
//   {"name": "John", "job": "Developer", "expectedStatus": 201},
//   {"name": "Jane", "job": "Manager", "expectedStatus": 201}
// ]
```

### Reading Excel Files

```java
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
    
    @DataProvider(name = "excelData")
    public Object[][] readExcelData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream("test-data.xlsx"))) {
            Sheet sheet = workbook.getSheetAt(0);
            
            // Skip header row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Object[] rowData = new Object[row.getLastCellNum()];
                
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = getCellValue(cell);
                }
                
                data.add(rowData);
            }
        }
        
        return data.toArray(new Object[0][]);
    }
    
    private Object getCellValue(Cell cell) {
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }
}
```

### Parameterized Tests

```java
import org.testng.annotations.Parameters;

public class ParameterizedTest {
    
    @Parameters({"baseUri", "apiKey"})
    @Test
    public void testWithParameters(String baseUri, String apiKey) {
        given()
            .baseUri(baseUri)
            .queryParam("api_key", apiKey)
        .when()
            .get("/data")
        .then()
            .statusCode(200);
    }
}

// testng.xml configuration
// <suite name="API Test Suite">
//     <parameter name="baseUri" value="https://api.example.com"/>
//     <parameter name="apiKey" value="123456789"/>
//     <test name="Regression">
//         <classes>
//             <class name="ParameterizedTest"/>
//         </classes>
//     </test>
// </suite>
```

---

## 5. Framework Architecture

### Layered Architecture

```
┌──────────────────────────────────────────────────────────────┐
│              LAYERED API TEST FRAMEWORK ARCHITECTURE             │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  TEST LAYER                                              │   │
│  │  - TestNG Test Classes                                    │   │
│  │  - Test methods using Given-When-Then                     │   │
│  │  - Assertions and validations                             │   │
│  │  - DataProviders for test data                            │   │
│  │  ┌─────────────────────────────────────────────────┐   │   │
│  │  │  UserTest.java, OrderTest.java, ProductTest.java  │   │   │
│  │  └─────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ uses                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  SERVICE LAYER                                           │   │
│  │  - Business logic operations                              │   │
│  │  - Orchestration of multiple API calls                     │   │
│  │  - Data transformation and validation                     │   │
│  │  ┌─────────────────────────────────────────────────┐   │   │
│  │  │  UserService.java, OrderService.java              │   │   │
│  │  └─────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ uses                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  CONTROLLER LAYER                                        │   │
│  │  - Direct API interaction (HTTP methods)                  │   │
│  │  - Request building and response handling                 │   │
│  │  - Authentication and session management                  │   │
│  │  ┌─────────────────────────────────────────────────┐   │   │
│  │  │  UserController.java, OrderController.java          │   │   │
│  │  └─────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ uses                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  UTILITIES LAYER                                         │   │
│  │  - ConfigReader, JsonUtils, TestDataGenerator             │   │
│  │  - ReportManager, Logger, ExtentReports                  │   │
│  │  - Common assertions and helpers                         │   │
│  │  ┌─────────────────────────────────────────────────┐   │   │
│  │  │  ConfigReader.java, JsonUtils.java                  │   │   │
│  │  │  ExtentReportManager.java, TestDataGenerator.java   │   │   │
│  │  └─────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ uses                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  POJO / DTO LAYER                                        │   │
│  │  - Request/Response data models                           │   │
│  │  - Serialization/Deserialization contracts                │   │
│  │  ┌─────────────────────────────────────────────────┐   │   │
│  │  │  UserRequest.java, UserResponse.java                │   │   │
│  │  │  OrderRequest.java, OrderResponse.java              │   │   │
│  │  └─────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           ▲                                      │
│                           │ uses                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  CONFIGURATION LAYER                                     │   │
│  │  - config.properties, environment.properties              │   │
│  │  - TestNG XML suites, Maven profiles                       │   │
│  │  ┌─────────────────────────────────────────────────┐   │   │
│  │  │  config.properties, testng.xml, pom.xml            │   │   │
│  │  └─────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
```

### BaseTest Class Setup

```java
public class BaseTest {
    
    protected static RequestSpecification requestSpec;
    protected static ExtentTest extentTest;
    protected static ExtentReports extentReports;
    
    @BeforeSuite
    public void beforeSuite() {
        // Initialize reporting
        extentReports = ExtentReportManager.getInstance();
        
        // Configure global REST Assured settings
        RestAssured.baseURI = ConfigReader.getProperty("base.uri");
        RestAssured.basePath = ConfigReader.getProperty("base.path", "/api");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        // Create reusable request specification
        requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("X-Request-Source", "Automation-Framework")
            .build();
    }
    
    @BeforeMethod
    public void beforeMethod(Method method) {
        // Create test entry in report
        extentTest = extentReports.createTest(method.getName());
        
        // Log test start
        System.out.println("Starting test: " + method.getName());
    }
    
    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Log test result
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test passed successfully");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test skipped");
        }
    }
    
    @AfterSuite
    public void afterSuite() {
        // Flush reports
        if (extentReports != null) {
            extentReports.flush();
        }
    }
    
    // Helper method to create request with common spec
    protected RequestSpecification givenRequest() {
        return given()
            .spec(requestSpec)
            .log().all();
    }
    
    // Helper for assertions
    protected ValidatableResponse thenAssert(Response response) {
        return response.then().log().ifValidationFails();
    }
}
```

### Configuration Management

```java
public class ConfigReader {
    
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            
            if (input != null) {
                properties.load(input);
            } else {
                System.out.println("Config file not found: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Override with environment variables
        overrideWithEnvironmentVariables();
    }
    
    private static void overrideWithEnvironmentVariables() {
        Map<String, String> envVars = System.getenv();
        
        for (String key : envVars.keySet()) {
            String normalizedKey = key.toLowerCase().replace("_", ".");
            if (properties.containsKey(normalizedKey)) {
                properties.setProperty(normalizedKey, envVars.get(key));
            }
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
    
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
```

**config.properties:**
```properties
# API Configuration
base.uri=https://reqres.in
base.path=/api
timeout.milliseconds=5000

# Authentication
auth.type=bearer
auth.token=your_token_here

# Environment
environment=qa
version=v1

# Reporting
report.name=API Test Report
report.title=Automation Test Execution
```

### Test Data Generation

```java
public class TestDataGenerator {
    
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    
    public static String generateRandomName() {
        return faker.name().fullName();
    }
    
    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }
    
    public static String generateRandomJob() {
        return faker.job().title();
    }
    
    public static String generateRandomPassword() {
        return faker.internet().password(8, 16, true, true);
    }
    
    public static String generateRandomString(int length) {
        return faker.lorem().characters(length);
    }
    
    public static int generateRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }
    
    public static String generateRandomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }
    
    public static String generateRandomAddress() {
        return faker.address().fullAddress();
    }
    
    public static UserRequest generateRandomUser() {
        return UserRequest.builder()
            .name(generateRandomName())
            .job(generateRandomJob())
            .email(generateRandomEmail())
            .skills(Arrays.asList("Java", "API Testing", "Selenium"))
            .build();
    }
    
    public static List<UserRequest> generateRandomUsers(int count) {
        List<UserRequest> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(generateRandomUser());
        }
        return users;
    }
    
    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
    
    public static String generateFutureDate(int days) {
        LocalDate futureDate = LocalDate.now().plusDays(days);
        return futureDate.toString();
    }
    
    public static String generatePastDate(int days) {
        LocalDate pastDate = LocalDate.now().minusDays(days);
        return pastDate.toString();
    }
}
```

### Utility Classes

```java
public class JsonUtils {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }
    
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
    }
    
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }
    
    public static JsonNode parseJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
    
    public static String getJsonValue(String json, String path) {
        return JsonPath.read(json, path);
    }
    
    public static boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
    
    public static String prettyPrint(String json) {
        try {
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            return json;
        }
    }
    
    public static String updateJsonValue(String json, String path, Object value) {
        DocumentContext context = JsonPath.parse(json);
        context.set(path, value);
        return context.jsonString();
    }
    
    public static String mergeJson(String baseJson, String overrideJson) {
        try {
            JsonNode baseNode = objectMapper.readTree(baseJson);
            JsonNode overrideNode = objectMapper.readTree(overrideJson);
            JsonNode merged = mergeNodes(baseNode, overrideNode);
            return objectMapper.writeValueAsString(merged);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to merge JSON", e);
        }
    }
    
    private static JsonNode mergeNodes(JsonNode baseNode, JsonNode overrideNode) {
        if (baseNode.isObject() && overrideNode.isObject()) {
            ObjectNode merged = ((ObjectNode) baseNode).deepCopy();
            overrideNode.fields().forEachRemaining(field -> {
                merged.set(field.getKey(), field.getValue());
            });
            return merged;
        }
        return overrideNode;
    }
}
```

---

## 6. Reporting

### Extent Reports

Extent Reports is a powerful, customizable reporting library for Java and .NET.

```java
public class ExtentReportManager {
    
    private static ExtentReports extentReports;
    private static final String REPORT_PATH = "reports/extent-report.html";
    
    public static ExtentReports getInstance() {
        if (extentReports == null) {
            createInstance();
        }
        return extentReports;
    }
    
    private static void createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
        
        // Configure report appearance
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("API Test Automation Report");
        sparkReporter.config().setReportName("REST API Test Results");
        sparkReporter.config().setEncoding("utf-8");
        
        // Add custom CSS
        sparkReporter.config().setCss(".badge { font-size: 100%; }");
        
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        
        // Add system information
        extentReports.setSystemInfo("Environment", ConfigReader.getProperty("environment", "QA"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Base URI", ConfigReader.getProperty("base.uri"));
        
        // Add category (tag) filter
        extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);
    }
    
    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}

// Usage in tests
@Test
public void testWithExtentReport() {
    ExtentTest test = ExtentReportManager.getInstance().createTest("Create User Test");
    
    try {
        test.info("Starting test execution");
        
        Response response = given()
            .body(newUser)
        .when()
            .post("/users");
        
        test.info("Request sent with body: " + JsonUtils.toJson(newUser));
        test.info("Response received: " + response.asString());
        
        response.then().statusCode(201);
        
        test.pass("User created successfully with ID: " + response.path("id"));
    } catch (AssertionError e) {
        test.fail("Test assertion failed: " + e.getMessage());
        throw e;
    } catch (Exception e) {
        test.fail("Test failed with exception: " + e.getMessage());
        throw e;
    }
}
```

### Allure Reports

Allure is a flexible, lightweight multi-language test report tool.

```java
// Add Allure dependencies to pom.xml
// <dependency>
//     <groupId>io.qameta.allure</groupId>
//     <artifactId>allure-testng</artifactId>
//     <version>2.24.0</version>
// </dependency>

import io.qameta.allure.*;

@Epic("User Management")
@Feature("User CRUD Operations")
public class AllureReportTest {
    
    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating a new user with valid data")
    @Test
    public void testCreateUserWithAllure() {
        CreateUserRequest request = TestDataGenerator.generateRandomUser();
        
        // Attach request data to report
        Allure.addAttachment("Request Body", JsonUtils.toJson(request));
        
        Response response = given()
            .body(request)
        .when()
            .post("/users");
        
        // Attach response to report
        Allure.addAttachment("Response", response.asString());
        
        // Add step
        Allure.step("Verify status code is 201");
        response.then().statusCode(201);
        
        Allure.step("Verify response contains ID");
        assertNotNull(response.path("id"));
        
        // Add parameters
        Allure.parameter("User Name", request.getName());
        Allure.parameter("User Job", request.getJob());
    }
    
    @Step("Send POST request to create user")
    private Response createUser(CreateUserRequest request) {
        return given().body(request).post("/users");
    }
    
    @Attachment(value = "Response", type = "application/json")
    private String attachResponse(String response) {
        return response;
    }
}

// Generate Allure report
// Run: mvn allure:serve
// Or: allure serve target/allure-results
```

### TestNG Reports

TestNG generates built-in HTML reports automatically.

```
┌──────────────────────────────────────────────────────────────┐
│              TESTNG REPORT STRUCTURE                              │
│                                                                  │
│  test-output/                                                    │
│  ├── index.html              ──► Main report dashboard          │
│  ├── emailable-report.html   ──► Email-friendly report         │
│  ├── testng-results.xml      ──► XML for CI integration         │
│  ├── old/                                                          │
│  │   └── index.html          ──► Previous run reports         │
│  └── SuiteName/                                                    │
│      └── testng-failed.xml   ──► Failed tests for rerun         │
│                                                                  │
│  CUSTOMIZATION:                                                  │
│  - Use ITestListener for custom events                           │
│  - Use IReporter for custom report generation                    │
│  - Use TestNG XML for suite configuration                         │
└──────────────────────────────────────────────────────────────┘
```

```java
// Custom TestNG Reporter
public class CustomReporter implements IReporter {
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, 
                               List<ISuite> suites, 
                               String outputDirectory) {
        
        for (ISuite suite : suites) {
            System.out.println("Suite: " + suite.getName());
            
            for (Map.Entry<String, ISuiteResult> entry : suite.getResults().entrySet()) {
                ITestContext context = entry.getValue().getTestContext();
                
                System.out.println("Passed: " + context.getPassedTests().size());
                System.out.println("Failed: " + context.getFailedTests().size());
                System.out.println("Skipped: " + context.getSkippedTests().size());
            }
        }
    }
}

// Add to testng.xml
// <listeners>
//     <listener class-name="com.api.framework.listeners.CustomReporter"/>
// </listeners>
```

---

## 7. Maven Configuration

### Complete pom.xml for API Testing

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.api.testing</groupId>
    <artifactId>api-automation-framework</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>API Automation Framework</name>
    <description>Comprehensive API Testing Framework with Java</description>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        
        <!-- Dependency Versions -->
        <restassured.version>5.3.2</restassured.version>
        <testng.version>7.8.1</testng.version>
        <jackson.version>2.15.2</jackson.version>
        <lombok.version>1.18.30</lombok.version>
        <extentreports.version>5.1.1</extentreports.version>
        <allure.version>2.24.0</allure.version>
        <faker.version>1.0.2</faker.version>
        <opencsv.version>5.8</opencsv.version>
        <poi.version>5.2.3</poi.version>
        <assertj.version>3.24.2</assertj.version>
        <log4j.version>2.20.0</log4j.version>
        <maven.surefire.version>3.1.2</maven.surefire.version>
    </properties>

    <dependencies>
        <!-- REST Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${restassured.version}</version>
        </dependency>
        
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>${restassured.version}</version>
        </dependency>
        
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>xml-path</artifactId>
            <version>${restassured.version}</version>
        </dependency>
        
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>${restassured.version}</version>
        </dependency>

        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
        </dependency>

        <!-- Jackson for JSON Serialization/Deserialization -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- Extent Reports -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>${extentreports.version}</version>
        </dependency>

        <!-- Allure Reports -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-testng</artifactId>
            <version>${allure.version}</version>
        </dependency>
        
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-rest-assured</artifactId>
            <version>${allure.version}</version>
        </dependency>

        <!-- Test Data Generation -->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>${faker.version}</version>
        </dependency>

        <!-- CSV Processing -->
        <dependency>
            <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>${opencsv.version}</version>
        </dependency>

        <!-- Excel Processing -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>${poi.version}</version>
        </dependency>
        
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>${poi.version}</version>
        </dependency>

        <!-- AssertJ for fluent assertions -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>${assertj.version}</version>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>${log4j.version}</version>
        </dependency>

        <!-- OkHttp (optional) -->
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>4.11.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Maven Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>

            <!-- Maven Surefire Plugin for TestNG -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.version}</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <properties>
                        <property>
                            <name>usedefaultlisteners</name>
                            <value>false</value>
                        </property>
                    </properties>
                    <reportsDirectory>${project.build.directory}/test-output</reportsDirectory>
                </configuration>
            </plugin>

            <!-- Allure Maven Plugin -->
            <plugin>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-maven</artifactId>
                <version>2.12.0</version>
                <configuration>
                    <reportVersion>${allure.version}</reportVersion>
                    <resultsDirectory>${project.build.directory}/allure-results</resultsDirectory>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <!-- Profiles for different environments -->
    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <env>dev</env>
            </properties>
        </profile>
        <profile>
            <id>qa</id>
            <properties>
                <env>qa</env>
            </properties>
        </profile>
        <profile>
            <id>prod</id>
            <properties>
                <env>prod</env>
            </properties>
        </profile>
    </profiles>
</project>
```

### Maven Commands

```bash
# Run all tests
mvn clean test

# Run with specific TestNG XML
mvn clean test -DsuiteXmlFile=regression.xml

# Run with profile
mvn clean test -Pqa

# Run specific test class
mvn clean test -Dtest=UserTest

# Run specific test method
mvn clean test -Dtest=UserTest#testCreateUser

# Skip tests
mvn clean install -DskipTests

# Generate Allure report
mvn allure:serve

# Compile only
mvn clean compile
```

---

## 8. CI/CD Integration

### Jenkins Pipeline

```groovy
// Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-11'
    }
    
    environment {
        BASE_URI = 'https://reqres.in'
        ENVIRONMENT = 'qa'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/your-org/api-automation.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Pqa'
            }
        }
        
        stage('Generate Reports') {
            steps {
                // TestNG Reports
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/test-output',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
                ])
                
                // Extent Reports
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'extent-report.html',
                    reportName: 'Extent Report'
                ])
                
                // Allure Reports
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
    
    post {
        always {
            // Archive test results
            junit 'target/test-output/*.xml'
            
            // Clean workspace
            cleanWs()
        }
        
        success {
            echo 'All tests passed!'
        }
        
        failure {
            echo 'Tests failed!'
            // Send notification
            mail to: 'team@example.com',
                 subject: "API Tests Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                 body: "Check console output at ${env.BUILD_URL}"
        }
    }
}
```

### GitHub Actions Workflow

```yaml
# .github/workflows/api-tests.yml
name: API Test Automation

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  schedule:
    # Run every day at 2 AM
    - cron: '0 2 * * *'
  workflow_dispatch:
    # Manual trigger

jobs:
  api-tests:
    runs-on: ubuntu-latest
    
    strategy:
      matrix:
        java-version: [11, 17]
    
    steps:
      - name: Checkout Code
        uses: actions/checkout@v4
      
      - name: Set up JDK ${{ matrix.java-version }}
        uses: actions/setup-java@v4
        with:
          java-version: ${{ matrix.java-version }}
          distribution: 'temurin'
          cache: maven
      
      - name: Cache Maven Dependencies
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2
      
      - name: Run API Tests
        run: mvn clean test -Pqa
        env:
          BASE_URI: ${{ secrets.BASE_URI }}
          API_KEY: ${{ secrets.API_KEY }}
      
      - name: Upload TestNG Reports
        uses: actions/upload-artifact@v4
        if: always()
        with:
          name: testng-reports-java-${{ matrix.java-version }}
          path: target/test-output/
          retention-days: 30
      
      - name: Upload Extent Reports
        uses: actions/upload-artifact@v4
        if: always()
        with:
          name: extent-reports-java-${{ matrix.java-version }}
          path: reports/
          retention-days: 30
      
      - name: Generate Allure Report
        uses: simple-elf/allure-report-action@master
        if: always()
        with:
          allure_results: target/allure-results
          allure_history: allure-history
      
      - name: Deploy Allure Report to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        if: always()
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_branch: gh-pages
          publish_dir: allure-history

  notify:
    needs: api-tests
    runs-on: ubuntu-latest
    if: failure()
    
    steps:
      - name: Send Slack Notification
        uses: 8398a7/action-slack@v3
        with:
          status: ${{ job.status }}
          text: 'API Tests Failed!'
          webhook_url: ${{ secrets.SLACK_WEBHOOK }}
```

### Running with Maven in CI

```bash
# Docker-based execution for CI
# Dockerfile.test
FROM maven:3.9-eclipse-temurin-11

WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY testng.xml .
COPY config.properties .

RUN mvn dependency:go-offline

CMD ["mvn", "clean", "test", "-Pqa"]

# Build and run
# docker build -f Dockerfile.test -t api-tests .
# docker run -e BASE_URI=https://api.example.com api-tests
```

---

## 9. Interview FAQs

### Beginner Level

**Q1: What is REST Assured and why is it used?**

REST Assured is a Java DSL (Domain Specific Language) for simplifying testing of REST APIs. It provides a BDD-style syntax (Given-When-Then) that makes API tests readable and maintainable. It is used because it handles HTTP requests/responses, JSON/XML parsing, authentication, and assertions with minimal boilerplate code.

**Q2: What is the difference between REST and SOAP?**

| Feature | REST | SOAP |
|---------|------|------|
| Protocol | HTTP/HTTPS | Any (HTTP, SMTP, TCP) |
| Format | JSON, XML, HTML, plain text | XML only |
| Style | Architectural style | Protocol/Standard |
| State | Stateless | Stateless |
| Security | HTTPS, OAuth, API Keys | WS-Security |
| Performance | Lightweight, faster | Heavier, slower |
| Caching | Can be cached | Cannot be cached |
| Learning | Simpler | More complex |

**Q3: What are the HTTP methods used in API testing?**

- **GET**: Retrieve data from server
- **POST**: Create new resource
- **PUT**: Update existing resource (full replacement)
- **PATCH**: Partial update to existing resource
- **DELETE**: Remove resource
- **HEAD**: Retrieve headers only
- **OPTIONS**: Get supported operations
- **TRACE**: Echo back request (for debugging)

**Q4: What are the common HTTP status codes?**

- **1xx (Informational)**: 100 Continue
- **2xx (Success)**: 200 OK, 201 Created, 204 No Content
- **3xx (Redirection)**: 301 Moved Permanently, 302 Found, 304 Not Modified
- **4xx (Client Error)**: 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found, 409 Conflict
- **5xx (Server Error)**: 500 Internal Server Error, 502 Bad Gateway, 503 Service Unavailable

**Q5: What is JSONPath and how is it used?**

JSONPath is a query language for JSON similar to XPath for XML. It allows extracting and filtering data from JSON documents using expressions like `$.users[0].name` or `$.users[?(@.age > 25)]`. In REST Assured, it is used for response body assertions.

### Intermediate Level

**Q6: What is the difference between path parameters and query parameters?**

Path parameters are part of the URL path (e.g., `/users/{id}`) and identify specific resources. Query parameters are appended after `?` (e.g., `/users?page=2&size=10`) and are used for filtering, sorting, or pagination.

**Q7: How do you handle authentication in REST Assured?**

REST Assured supports multiple authentication mechanisms:
- Basic Auth: `.auth().basic("user", "pass")`
- Preemptive Basic Auth: `.auth().preemptive().basic("user", "pass")`
- Bearer Token: `.auth().oauth2("token")` or `.header("Authorization", "Bearer " + token)`
- OAuth 1.0: `.auth().oauth(...)`
- Digest Auth: `.auth().digest("user", "pass")`
- API Key: `.queryParam("api_key", key)` or `.header("X-API-Key", key)`

**Q8: What is RequestSpecification and why use it?**

`RequestSpecification` allows creating reusable request configurations. It is used to set common headers, base URI, authentication, and content type once, then reuse across multiple tests. This reduces duplication and makes maintenance easier.

**Q9: How do you perform data-driven testing in REST Assured?**

Data-driven testing is implemented using TestNG's `@DataProvider` annotation. The DataProvider can supply data from:
- Inline arrays
- CSV files (using OpenCSV)
- Excel files (using Apache POI)
- JSON files (using Jackson)
- External databases or APIs

**Q10: How do you handle dynamic data in API tests?**

Dynamic data is handled using:
- Test data generators (JavaFaker)
- UUID generation for unique identifiers
- Timestamp-based values
- Response extraction and chaining for dependent API calls
- Random number/string generation

**Q11: What is serialization and deserialization in API testing?**

Serialization is converting a Java object (POJO) into a JSON/XML string for the request body. Deserialization is converting a JSON/XML response into a Java object. Jackson library handles this automatically in REST Assured.

**Q12: How do you validate JSON schema in REST Assured?**

JSON schema validation is done using the `json-schema-validator` module:
```java
.then()
    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"))
```

**Q13: What is the difference between PUT and PATCH?**

PUT replaces the entire resource with the request payload. PATCH applies partial modifications to the resource. PATCH is more efficient for large resources when only a few fields need updating.

**Q14: How do you handle file uploads in REST Assured?**

File uploads use multipart form data:
```java
given()
    .multiPart("file", new File("data.json"), "application/json")
    .multiPart("description", "Test data")
.when()
    .post("/upload")
```

### Advanced Level

**Q15: How do you build a maintainable API automation framework?**

A maintainable framework uses:
- Layered architecture (Controller, Service, POJO, Utils, Test)
- BaseTest class for common setup/teardown
- Configuration management (properties, environment variables)
- POJO-based request/response handling
- Data-driven testing with TestNG DataProviders
- Comprehensive reporting (Extent, Allure, TestNG)
- CI/CD integration (Jenkins, GitHub Actions)
- Logging and debugging capabilities
- Error handling and retry mechanisms

**Q16: What is the difference between RestAssured and HttpClient?**

REST Assured is a DSL specifically for API testing with BDD syntax, built-in assertions, and JSON/XML support. HttpClient is a general-purpose HTTP client built into JDK 11+ for making HTTP requests. REST Assured is better for testing; HttpClient is better for general HTTP operations.

**Q17: How do you handle dependent API calls in tests?**

Dependent API calls use response extraction and chaining:
1. Call API A and extract response data
2. Use extracted data in API B's request
3. Use TestNG's `@DependsOnMethods` for test dependency

**Q18: How do you handle pagination in API tests?**

Pagination is tested by:
- Iterating through pages using query parameters
- Validating response metadata (total, page, per_page)
- Verifying page size consistency
- Testing edge cases (first page, last page, invalid page)
- Verifying total count vs actual returned items

**Q19: What are interceptors in OkHttp and how do they differ from REST Assured filters?**

OkHttp interceptors modify or observe requests/responses at the network layer. They can retry requests, add headers, or log. REST Assured filters are similar but operate at the test framework level. Both are used for cross-cutting concerns like logging and authentication.

**Q20: How do you ensure API tests are idempotent?**

Idempotent tests produce the same result regardless of how many times they run. This is achieved by:
- Using unique identifiers for each test run
- Cleaning up created resources in teardown
- Using setup methods to reset state
- Avoiding hardcoded data that might conflict
- Using transactions or rollback mechanisms

**Q21: How do you handle asynchronous API operations?**

Asynchronous operations are handled by:
- Polling the status endpoint with wait conditions
- Using TestNG's `Thread.sleep()` or better: `Awaitility` library
- Implementing retry logic with exponential backoff
- Setting appropriate timeouts
- Using WebHooks or callbacks when available

**Q22: What is the purpose of `.spec()` and `.log()` in REST Assured?**

`.spec()` applies a reusable `RequestSpecification` or `ResponseSpecification`. `.log()` enables request/response logging for debugging. `.log().all()` logs everything; `.log().ifValidationFails()` logs only on failure.

**Q23: How do you test rate limiting in APIs?**

Rate limiting is tested by:
- Sending requests exceeding the limit
- Validating 429 Too Many Requests status
- Checking `Retry-After` header
- Measuring request reset time
- Testing with burst traffic patterns
- Validating rate limit headers (`X-RateLimit-Limit`, `X-RateLimit-Remaining`)

**Q24: How do you implement parallel test execution in API automation?**

Parallel execution is configured in TestNG XML:
```xml
<suite name="Parallel Suite" parallel="methods" thread-count="4">
```
Or use DataProvider's `parallel = true` attribute. Ensure thread-safe state management and avoid shared mutable resources.

**Q25: How do you handle versioning in API testing?**

API versioning is handled by:
- Including version in URL path (`/v1/users`, `/v2/users`)
- Using `Accept-Version` header
- Using content negotiation (`Accept: application/vnd.api.v1+json`)
- Maintaining separate test suites for each version
- Parameterizing version in configuration files

**Q26: What are the key metrics you track in API performance testing?**

Key metrics include:
- Response time (average, median, p95, p99)
- Throughput (requests per second)
- Error rate
- CPU and memory utilization
- Network latency
- Database query performance
- Connection pool metrics

**Q27: How do you mock external APIs in your tests?**

External APIs are mocked using:
- WireMock for stubbing HTTP services
- MockServer for complex expectations
- Mockito for unit-level mocking
- Testcontainers for integration testing
- JSON server for simple REST mocks

**Q28: How do you handle API contract changes?**

API contract changes are handled by:
- Using JSON Schema validation for backward compatibility
- Implementing consumer-driven contract testing (Pact)
- Versioning APIs
- Automated regression testing on CI/CD
- Monitoring API changes with OpenAPI/Swagger

**Q29: What is the difference between `@DataProvider` and `@Parameters` in TestNG?**

`@DataProvider` provides multiple sets of data to a single test method, enabling multiple iterations. `@Parameters` reads values from TestNG XML suite configuration and provides single values to test methods.

**Q30: How do you implement CI/CD for API testing with Maven?**

CI/CD for API testing is implemented by:
- Configuring Maven profiles for different environments
- Using `maven-surefire-plugin` to run TestNG suites
- Integrating with Jenkins (Pipeline DSL) or GitHub Actions
- Publishing reports (HTML, Allure, Extent) as artifacts
- Configuring notifications for failures
- Running tests on every commit or scheduled basis

---

## 10. Quick Reference

### REST Assured Cheat Sheet

```java
// Imports
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// Basic GET
Response response = get("/endpoint");

// GET with assertions
get("/endpoint").then().statusCode(200);

// POST with body
post("/endpoint", body).then().statusCode(201);

// PUT with body
put("/endpoint", body).then().statusCode(200);

// DELETE
delete("/endpoint/123").then().statusCode(204);

// PATCH
patch("/endpoint/123", body).then().statusCode(200);

// Headers
.header("X-Custom", "value")
.headers("h1", "v1", "h2", "v2")

// Parameters
.queryParam("key", "value")
.pathParam("id", 123)
.formParam("field", "value")

// Authentication
.auth().basic("user", "pass")
.auth().oauth2("token")

// Response Extraction
String value = response.path("data.name");
List<String> list = response.path("data.items.name");

// Logging
.log().all()
.log().ifValidationFails()
.log().ifError()

// JSON Schema
.then().body(matchesJsonSchemaInClasspath("schema.json"))
```

### Common Matchers

| Matcher | Description | Example |
|---------|-------------|---------|
| `equalTo()` | Exact match | `.body("id", equalTo(2))` |
| `not()` | Negation | `.body("error", notNullValue())` |
| `containsString()` | Substring match | `.body("email", containsString("@"))` |
| `startsWith()` | Prefix match | `.body("url", startsWith("https"))` |
| `hasSize()` | Collection size | `.body("data", hasSize(5))` |
| `hasItem()` | Contains item | `.body("names", hasItem("John"))` |
| `greaterThan()` | Numeric comparison | `.body("count", greaterThan(0))` |
| `lessThan()` | Numeric comparison | `.body("time", lessThan(1000))` |
| `isEmptyOrNullString()` | Empty or null | `.body("middleName", isEmptyOrNullString())` |
| `everyItem()` | All items match | `.body("ages", everyItem(greaterThan(18)))` |

---

## 11. Key Takeaways

```
┌──────────────────────────────────────────────────────────────┐
│                    KEY TAKEAWAYS                                  │
│                                                                  │
│  1. LIBRARY CHOICE                                               │
│     - REST Assured for BDD-style test automation                │
│     - HttpClient for zero-dependency HTTP operations            │
│     - OkHttp for performance-critical applications              │
│     - JSONPath for JSON data extraction                         │
│                                                                  │
│  2. REST ASSURED FUNDAMENTALS                                    │
│     - Given-When-Then syntax for readable tests                 │
│     - Static imports for clean code                             │
│     - Request/Response specifications for reusability             │
│     - Multiple authentication mechanisms supported              │
│                                                                  │
│  3. POJO-BASED AUTOMATION                                        │
│     - Type-safe request/response handling                       │
│     - Jackson for serialization/deserialization                 │
│     - Lombok for reducing boilerplate code                       │
│     - Nested POJOs for complex data structures                 │
│                                                                  │
│  4. DATA-DRIVEN TESTING                                          │
│     - TestNG DataProvider for multiple test iterations           │
│     - CSV, JSON, Excel data sources                             │
│     - Programmatic data generation                              │
│     - Parallel execution support                                │
│                                                                  │
│  5. FRAMEWORK ARCHITECTURE                                       │
│     - Layered design (Controller, Service, POJO, Utils)          │
│     - BaseTest for common setup and teardown                    │
│     - Configuration management with properties and env vars     │
│     - Utility classes for common operations                     │
│                                                                  │
│  6. REPORTING                                                    │
│     - Extent Reports for rich HTML dashboards                   │
│     - Allure Reports for modern test reporting                   │
│     - TestNG built-in reports for quick reference               │
│                                                                  │
│  7. CI/CD INTEGRATION                                            │
│     - Maven for build and dependency management                 │
│     - Jenkins Pipeline for enterprise CI/CD                      │
│     - GitHub Actions for cloud-native CI/CD                     │
│     - Docker for containerized test execution                   │
│                                                                  │
│  8. BEST PRACTICES                                               │
│     - Use POJOs instead of raw strings for payloads             │
│     - Implement reusable request/response specifications         │
│     - Maintain separate test data for each environment            │
│     - Clean up test data after execution                        │
│     - Use appropriate logging for debugging                     │
│     - Implement proper error handling and retries               │
│     - Keep tests independent and idempotent                     │
│     - Use meaningful assertions with clear messages             │
│     - Version control your test code and configurations          │
└──────────────────────────────────────────────────────────────┘
```

---

**Happy API Testing!**

*Master API automation, and you master the gateway to modern application testing.*

---

**Last Updated:** June 2026
