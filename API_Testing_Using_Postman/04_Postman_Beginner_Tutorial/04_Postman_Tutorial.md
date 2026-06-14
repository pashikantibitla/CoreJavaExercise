# Postman Beginner Tutorial — Complete Guide

> **Source:** YouTube Playlist — [Postman Beginner Tutorial by Raghav Pal](https://www.youtube.com/playlist?list=PLhW3qG5bs-L-oT0GenwPLcJAPD_SiFK3C)  
> **Channel:** Automation Step by Step  
> **Total Videos:** 26  
> **Topics:** API Testing, Postman Fundamentals, Collections, Variables, Scripting, Newman, Jenkins, Data-Driven Testing, Authorization, Mini-Projects

---

## Table of Contents

1. [Postman Introduction and UI Components](#1-postman-introduction-and-ui-components)
2. [Creating API Requests](#2-creating-api-requests)
3. [Collections and Folder Organization](#3-collections-and-folder-organization)
4. [Collection Runner](#4-collection-runner)
5. [Variables — Global, Collection, Environment, Data, Local](#5-variables)
6. [Scripting — Pre-request and Test Scripts](#6-scripting)
7. [Environments](#7-environments)
8. [Writing Tests](#8-writing-tests)
9. [Debugging](#9-debugging)
10. [Newman — Command Line Runner](#10-newman)
11. [Jenkins Integration](#11-jenkins-integration)
12. [Workspaces](#12-workspaces)
13. [Data-Driven Testing](#13-data-driven-testing)
14. [Running Collections Remotely](#14-running-collections-remotely)
15. [SOAP Requests](#15-soap-requests)
16. [Response Extraction and Chaining](#16-response-extraction-and-chaining)
17. [API Authorization](#17-api-authorization)
18. [Crash Course Summary](#18-crash-course-summary)
19. [Mini-Project 1: Login API Flow](#19-mini-project-1-login-api-flow)
20. [Mini-Project 2: E-Commerce Add to Cart](#20-mini-project-2-e-commerce-add-to-cart)
21. [Monitoring and Mocking](#21-monitoring-and-mocking)
22. [API Documentation](#22-api-documentation)
23. [Postman Flows](#23-postman-flows)
24. [Interview FAQs](#24-interview-faqs)
25. [Quick Reference](#25-quick-reference)
26. [Key Takeaways](#26-key-takeaways)

---

## 1. Postman Introduction and UI Components

### 1.1 What is Postman?

Postman is an API (Application Programming Interface) platform for building and using APIs. It simplifies each step of the API lifecycle and streamlines collaboration so you can create better APIs faster.

```
┌──────────────────────────────────────────────────────────────┐
│                    POSTMAN CAPABILITIES                         │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Feature            │ Purpose                              ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ API Client         │ Send requests, inspect responses       ││
│  │ Collections        │ Organize requests into folders         ││
│  │ Environments       │ Manage variables for different setups  ││
│  │ Tests              │ Automate validation with scripts     ││
│  │ Newman             │ Run collections from command line      ││
│  │ CI/CD Integration  │ Jenkins, GitHub Actions, etc.        ││
│  │ Mock Servers       │ Simulate API endpoints                 ││
│  │ Monitoring         │ Schedule and run collection checks     ││
│  │ Documentation      │ Auto-generate API docs                 ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 1.2 Postman UI Components

When you open Postman, the interface is divided into several sections:

**Step-by-Step: Understanding the UI**

1. **Top Navigation Bar**
   - New (+ button): Create requests, collections, environments
   - Import: Import collections, environments, or OpenAPI specs
   - Runner: Open the Collection Runner
   - Settings: Configure Postman preferences

2. **Left Sidebar**
   - **Collections**: List of all your request collections
   - **APIs**: API definitions and schemas
   - **Environments**: List of environment configurations
   - **History**: Recent requests sent
   - **Workspaces**: Switch between personal/team workspaces

3. **Request Builder (Center)**
   - **Request Method Dropdown**: GET, POST, PUT, DELETE, PATCH, etc.
   - **URL Bar**: Enter the API endpoint
   - **Tabs**: Params, Authorization, Headers, Body, Pre-request Script, Tests

4. **Response Viewer (Bottom Right)**
   - **Body**: View response in Pretty, Raw, Preview, Visualize modes
   - **Cookies**: View cookies returned by the server
   - **Headers**: View response headers
   - **Test Results**: View results of test scripts
   - **Network Info**: Status, Time, Size

5. **Bottom Status Bar**
   - Cookies, Console, Runner, Trash, Settings shortcuts

```
┌──────────────────────────────────────────────────────────────┐
│  New  Import  Runner  Settings                               │
├──────────────────────────────────────────────────────────────┤
│  ┌─────────────┐  ┌─────────────────────────────────────┐     │
│  │ Collections │  │  GET  https://api.example.com/users │     │
│  │  - Users    │  │  [Params] [Auth] [Headers] [Body]   │     │
│  │    - GET    │  │                                      │     │
│  │    - POST   │  │  [Pre-request Script] [Tests]        │     │
│  │  - Orders   │  │                                      │     │
│  │             │  │  ┌──────────────────────────────────┐ │     │
│  │ APIs        │  │  │ Response Body                    │ │     │
│  │ Environments│  │  │ Status: 200 OK | Time: 234ms    │ │     │
│  │ History     │  │  │                                  │ │     │
│  └─────────────┘  │  │ {                                │ │     │
│                   │  │   "id": 1,                       │ │     │
│                   │  │   "name": "John"                 │ │     │
│                   │  │ }                                │ │     │
│                   │  │                                  │ │     │
│                   │  │ [Test Results] [Cookies] [Headers]│ │    │
│                   │  └──────────────────────────────────┘ │     │
│                   └─────────────────────────────────────┘     │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. Creating API Requests

### 2.1 HTTP Methods Overview

```
┌──────────────────────────────────────────────────────────────┐
│                    HTTP METHODS REFERENCE                      │
│  ┌────────┬────────────────────────────────────────────────┐  │
│  │ Method │ Purpose                                        │  │
│  ├────────┼────────────────────────────────────────────────┤  │
│  │ GET    │ Retrieve data from server                      │  │
│  │ POST   │ Create a new resource on server                │  │
│  │ PUT    │ Update/Replace an existing resource            │  │
│  │ PATCH  │ Partial update of an existing resource         │  │
│  │ DELETE │ Remove a resource from server                  │  │
│  │ HEAD   │ Get headers only (no body)                     │  │
│  │ OPTIONS│ Get supported operations for a resource        │  │
│  └────────┴────────────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### 2.2 Step-by-Step: Creating Your First GET Request

1. **Open Postman** and click the **New** button → **HTTP Request**
2. **Select Method**: Choose `GET` from the dropdown
3. **Enter URL**: `https://jsonplaceholder.typicode.com/users`
4. **Click Send** (Ctrl+Enter / Cmd+Enter)
5. **View Response** in the bottom panel

**Expected Response:**
```json
[
  {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  }
]
```

### 2.3 Step-by-Step: Creating a POST Request

1. **Create a new request** tab
2. **Select Method**: `POST`
3. **Enter URL**: `https://jsonplaceholder.typicode.com/users`
4. **Go to Body Tab** → Select `raw` → Choose `JSON` from the dropdown
5. **Enter JSON Body**:
```json
{
  "name": "John Doe",
  "username": "johndoe",
  "email": "john@example.com",
  "phone": "123-456-7890",
  "website": "johndoe.com"
}
```
6. **Click Send**
7. **Verify Response**: Status `201 Created` with the newly created user object including an assigned `id`

### 2.4 Step-by-Step: Creating a PUT Request

1. **Create a new request** tab
2. **Select Method**: `PUT`
3. **Enter URL**: `https://jsonplaceholder.typicode.com/users/1`
4. **Go to Body Tab** → `raw` → `JSON`
5. **Enter JSON Body**:
```json
{
  "id": 1,
  "name": "Leanne Graham Updated",
  "username": "Bret",
  "email": "Sincere@april.biz"
}
```
6. **Click Send**
7. **Verify Response**: Status `200 OK` with updated data

### 2.5 Step-by-Step: Creating a DELETE Request

1. **Create a new request** tab
2. **Select Method**: `DELETE`
3. **Enter URL**: `https://jsonplaceholder.typicode.com/users/1`
4. **Click Send**
5. **Verify Response**: Status `200 OK` or `204 No Content`

### 2.6 Adding Query Parameters

In the **Params** tab, add key-value pairs:

| Key        | Value | Description               |
|------------|-------|---------------------------|
| `userId`   | `1`   | Filter by user ID         |
| `postId`   | `5`   | Filter by post ID         |
| `page`     | `1`   | Pagination: page number   |
| `limit`    | `10`  | Pagination: items per page|

Postman automatically appends them to the URL:
```
https://jsonplaceholder.typicode.com/posts?userId=1&postId=5
```

### 2.7 Adding Headers

In the **Headers** tab, add key-value pairs:

| Key             | Value              | Description           |
|-----------------|--------------------|-----------------------|
| `Content-Type`  | `application/json` | Request body format   |
| `Accept`        | `application/json` | Expected response format |
| `Authorization` | `Bearer {{token}}` | Auth token (using variable) |
| `X-API-Key`     | `my-secret-key`    | Custom API key header |

---

## 3. Collections and Folder Organization

### 3.1 What is a Collection?

A Collection in Postman is a group of API requests that can be organized into folders. It allows you to:
- Group related requests together
- Share APIs with team members
- Run multiple requests together
- Add documentation and tests
- Export and import collections

```
┌──────────────────────────────────────────────────────────────┐
│                    COLLECTION STRUCTURE                         │
│                                                                  │
│  📁 E-Commerce API Collection                                   │
│  ├── 📁 User Management                                          │
│  │   ├── GET  Get All Users                                     │
│  │   ├── GET  Get User by ID                                    │
│  │   ├── POST Create User                                       │
│  │   ├── PUT  Update User                                       │
│  │   └── DELETE Delete User                                    │
│  ├── 📁 Product Catalog                                         │
│  │   ├── GET  Get All Products                                  │
│  │   ├── GET  Get Product by ID                                 │
│  │   └── POST Add Product                                       │
│  ├── 📁 Order Management                                        │
│  │   ├── POST Create Order                                      │
│  │   ├── GET  Get Order by ID                                  │
│  │   └── PUT  Update Order Status                              │
│  └── 📁 Authentication                                          │
│      ├── POST Login                                             │
│      ├── POST Register                                          │
│      └── POST Forgot Password                                  │
└──────────────────────────────────────────────────────────────┘
```

### 3.2 Step-by-Step: Creating a Collection

1. **Click New** → **Collection** (or click the `+` next to Collections in the sidebar)
2. **Name the Collection**: `E-Commerce API Tests`
3. **Add a Description**: `API tests for e-commerce application`
4. **Click Create**

### 3.3 Adding Folders to a Collection

1. **Right-click** on the collection name
2. **Select** `Add Folder`
3. **Name the Folder**: `User Management`
4. **Add Description** (optional)

### 3.4 Adding Requests to a Collection

**Method 1: Save Request Directly**
1. Create a new request in the request builder
2. Click **Save** (Ctrl+S)
3. Select the target Collection and Folder
4. Name the request: `GET All Users`
5. Click **Save to E-Commerce API Tests**

**Method 2: Add Request to Existing Collection**
1. Right-click on a collection or folder
2. Select **Add Request**
3. Configure the request and save

### 3.5 Collection Options

Right-click on a collection to see options:
- **Edit**: Rename, add description
- **Add Folder**: Create a new folder
- **Duplicate**: Copy the entire collection
- **Export**: Export as JSON (v2.1 recommended)
- **Share**: Share via link or workspace
- **Run**: Open Collection Runner
- **Add Request**: Add a new request directly

---

## 4. Collection Runner

### 4.1 What is Collection Runner?

The Collection Runner allows you to execute all requests in a collection or folder in a specified order. It is useful for:
- Running regression test suites
- Executing multiple requests sequentially
- Performing data-driven testing with iterations
- Generating summary reports

### 4.2 Step-by-Step: Running a Collection

1. **Select a Collection** in the left sidebar
2. **Click the Run button** (▶️) next to the collection name, or go to the **Runner** tab
3. **Configure Runner Options**:
   - **Environment**: Select an environment (or No Environment)
   - **Iterations**: Number of times to run the collection (default: 1)
   - **Delay**: Time in milliseconds between requests (default: 0)
   - **Data**: Select a data file (CSV or JSON) for data-driven testing
   - **Save responses**: Enable to save response bodies
   - **Run collection without using stored cookies**: Optional

4. **Click Run E-Commerce API Tests**
5. **View Results**:
   - **Pass/Fail counts** for each request
   - **Test results** for each iteration
   - **Response summary** (status, time, size)
   - **Export results** as JSON

### 4.3 Runner Configuration Details

```
┌──────────────────────────────────────────────────────────────┐
│                    COLLECTION RUNNER OPTIONS                    │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Option             │ Description                            ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ Environment        │ Select active environment variables    ││
│  │ Iterations         │ How many times to run the collection  ││
│  │ Delay              │ Pause between requests (milliseconds)   ││
│  │ Data File          │ CSV or JSON file for data-driven tests  ││
│  │ Save Responses     │ Store response data for inspection    ││
│  │ Run without cookies│ Ignore saved cookies for fresh state  ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 4.4 Running a Folder Only

1. **Select a Folder** inside a collection
2. **Click the Run button** next to the folder name
3. **Only requests in that folder** will execute

### 4.5 Iteration Data

When running with iterations, you can access the current iteration number in scripts:
```javascript
// Access iteration information in test scripts
console.log("Iteration: " + pm.info.iteration);
console.log("Total Iterations: " + pm.info.iterationCount);
console.log("Request Name: " + pm.info.requestName);
```

---

## 5. Variables

### 5.1 Variable Types and Scope

Postman supports multiple types of variables with different scopes. The scope determines where a variable is accessible.

```
┌──────────────────────────────────────────────────────────────┐
│                    VARIABLE SCOPE (OUTER → INNER)               │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  Global        │  Accessible across all workspaces       │   │
│  │  Collection    │  Accessible within a specific collection │   │
│  │  Environment   │  Accessible when environment is active │   │
│  │  Data          │  From data file during iteration runs   │   │
│  │  Local         │  Temporary, within a single request scope │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                  │
│  Scope Precedence: Data > Environment > Collection > Global     │
│  (Inner scope overrides outer scope)                             │
└──────────────────────────────────────────────────────────────┘
```

### 5.2 Global Variables

Global variables are accessible across all collections, environments, and requests in a workspace.

**Step-by-Step: Creating Global Variables**

1. Click the **gear icon** (⚙️) in the top-right → **Globals**
2. Or go to **Environments** → **Globals**
3. Click **Add Variable**:
   - Variable: `base_url`
   - Initial Value: `https://jsonplaceholder.typicode.com`
   - Current Value: `https://jsonplaceholder.typicode.com`
4. Click **Save**

**Using Global Variables in Requests:**
```
URL: {{base_url}}/users
```

**Setting Global Variables in Scripts:**
```javascript
// Set a global variable
pm.globals.set("user_id", "123");

// Get a global variable
var userId = pm.globals.get("user_id");

// Unset (remove) a global variable
pm.globals.unset("user_id");
```

### 5.3 Collection Variables

Collection variables are accessible only within the collection where they are defined.

**Step-by-Step: Creating Collection Variables**

1. **Right-click** on a collection → **Edit**
2. Go to the **Variables** tab
3. Click **Add Variable**:
   - Variable: `api_version`
   - Initial Value: `v1`
   - Current Value: `v1`
4. Click **Update**

**Using Collection Variables:**
```
URL: {{base_url}}/{{api_version}}/users
```

**Setting Collection Variables in Scripts:**
```javascript
// Set a collection variable
pm.collectionVariables.set("auth_token", "abc123");

// Get a collection variable
var token = pm.collectionVariables.get("auth_token");

// Unset a collection variable
pm.collectionVariables.unset("auth_token");
```

### 5.4 Environment Variables

Environment variables are tied to a specific environment and are accessible when that environment is selected.

**Step-by-Step: Creating an Environment**

1. Click the **Environment dropdown** (top-right) → **Create Environment**
2. **Name**: `Development`
3. Add variables:
   - `base_url`: `https://dev-api.example.com`
   - `api_key`: `dev-secret-key-123`
4. **Save**

**Setting Environment Variables in Scripts:**
```javascript
// Set an environment variable
pm.environment.set("auth_token", "Bearer xyz789");

// Get an environment variable
var token = pm.environment.get("auth_token");

// Unset an environment variable
pm.environment.unset("auth_token");
```

### 5.5 Data Variables (Data-Driven)

Data variables come from a data file (CSV or JSON) when running a collection via the Collection Runner or Newman.

**Example CSV Data File:**
```csv
username,password,expected_status
admin,admin123,200
user1,wrongpass,401
user2,password456,200
```

**Accessing Data Variables in Scripts:**
```javascript
// Get data variable from CSV/JSON iteration data
var username = pm.iterationData.get("username");
var password = pm.iterationData.get("password");
var expectedStatus = pm.iterationData.get("expected_status");

// Use in request body
console.log("Testing with user: " + username);
```

### 5.6 Local Variables

Local variables are temporary and only accessible within the request's pre-request or test script. They are not persisted.

```javascript
// Set a local variable (temporary, request-scoped)
pm.variables.set("temp_value", "123");

// Get a local variable
var temp = pm.variables.get("temp_value");

// Note: Local variables override all other scopes
```

### 5.7 Variable Scope Precedence

When variables with the same name exist in multiple scopes, the narrowest scope wins:

```
Priority: Data > Local > Environment > Collection > Global

Example:
- Global: base_url = https://global.com
- Collection: base_url = https://collection.com
- Environment: base_url = https://env.com
- Data: base_url = https://data.com

Result: {{base_url}} resolves to https://data.com (highest priority)
```

### 5.8 Dynamic Variables

Postman provides built-in dynamic variables for generating random data:

```
{{$guid}}          → UUID v4 (e.g., 123e4567-e89b-12d3-a456-426614174000)
{{$timestamp}}     → Unix timestamp in seconds
{{$randomInt}}     → Random integer between 0 and 1000
{{$randomFullName}}→ Random full name
{{$randomEmail}}   → Random email address
{{$randomPassword}}→ Random password string
{{$randomUUID}}    → Random UUID
```

**Usage in Request Body:**
```json
{
  "id": "{{$guid}}",
  "name": "{{$randomFullName}}",
  "email": "{{$randomEmail}}",
  "createdAt": {{$timestamp}}
}
```

---

## 6. Scripting

### 6.1 Types of Scripts in Postman

Postman supports two types of scripts that run in the Postman Sandbox (a JavaScript execution environment):

```
┌──────────────────────────────────────────────────────────────┐
│                    SCRIPT EXECUTION FLOW                        │
│                                                                  │
│  1. Pre-request Script (Runs BEFORE the request is sent)      │
│     ├── Set variables                                           │
│     ├── Compute values                                          │
│     ├── Generate dynamic data                                   │
│     └── Prepare request state                                   │
│                                                                  │
│  2. Request is sent to the server                               │
│                                                                  │
│  3. Test Script (Runs AFTER the response is received)            │
│     ├── Validate response status                                │
│     ├── Parse response data                                    │
│     ├── Extract values for chaining                            │
│     ├── Set variables for next requests                        │
│     └── Assert business logic                                  │
└──────────────────────────────────────────────────────────────┘
```

### 6.2 Pre-request Scripts

Pre-request scripts run before the request is sent. They are useful for:
- Setting up variables
- Generating timestamps or signatures
- Computing HMAC hashes
- Preparing request data

**Example: Setting Timestamp in Pre-request Script**
```javascript
// Pre-request Script: Add timestamp to request
var timestamp = new Date().toISOString();
pm.environment.set("request_timestamp", timestamp);

// Generate a random order ID
var orderId = "ORD-" + Math.floor(Math.random() * 100000);
pm.environment.set("order_id", orderId);

// Compute a hash (example)
var CryptoJS = require('crypto-js');
var message = "secret-message";
var hash = CryptoJS.MD5(message).toString();
pm.environment.set("message_hash", hash);
```

**Example: Preparing Request with Dynamic Data**
```javascript
// Pre-request Script: Prepare login credentials
var username = pm.environment.get("username") || "testuser";
var password = pm.environment.get("password") || "Test@123";

// Encode credentials if needed
var encodedCredentials = btoa(username + ":" + password);
pm.environment.set("encoded_credentials", encodedCredentials);

// Set a request header dynamically (via Headers tab)
// In Headers tab: Authorization: Basic {{encoded_credentials}}
```

**Example: Conditional Pre-request Logic**
```javascript
// Only run setup if a flag is set
var runSetup = pm.environment.get("run_setup");

if (runSetup === "true") {
    pm.environment.set("setup_complete", "yes");
    console.log("Setup completed");
} else {
    console.log("Skipping setup");
}
```

### 6.3 Test Scripts

Test scripts run after the response is received. They are used to validate the API response.

**Example: Basic Status Code Test**
```javascript
// Test: Status code is 200
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test: Status code is one of the expected values
pm.test("Status code is 200 or 201", function () {
    pm.expect(pm.response.code).to.be.oneOf([200, 201]);
});
```

**Example: Response Time Test**
```javascript
// Test: Response time is less than 500ms
pm.test("Response time is less than 500ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(500);
});

// Test: Response time is less than 1 second
pm.test("Response time is less than 1000ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(1000);
});
```

**Example: JSON Body Validation**
```javascript
// Parse JSON response
var jsonData = pm.response.json();

// Test: Response contains expected user ID
pm.test("Response contains user ID 1", function () {
    pm.expect(jsonData.id).to.eql(1);
});

// Test: Response contains name field
pm.test("Response contains name field", function () {
    pm.expect(jsonData).to.have.property("name");
    pm.expect(jsonData.name).to.not.be.empty;
});

// Test: Verify email format
pm.test("Email format is valid", function () {
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    pm.expect(jsonData.email).to.match(emailPattern);
});

// Test: Array response length
pm.test("Response contains 10 users", function () {
    pm.expect(jsonData).to.be.an('array').that.has.lengthOf(10);
});
```

**Example: Header Validation**
```javascript
// Test: Content-Type header is present
pm.test("Content-Type header is present", function () {
    pm.response.to.have.header("Content-Type");
});

// Test: Content-Type is application/json
pm.test("Content-Type is application/json", function () {
    pm.expect(pm.response.headers.get("Content-Type")).to.include("application/json");
});
```

**Example: Cookie Validation**
```javascript
// Test: Session cookie is set
pm.test("Session cookie is present", function () {
    pm.expect(pm.cookies.has("session_id")).to.be.true;
});

// Get cookie value
var sessionCookie = pm.cookies.get("session_id");
console.log("Session ID: " + sessionCookie);
```

### 6.4 Chai Assertion Library

Postman uses Chai BDD assertions for writing tests.

```javascript
// Common Chai Assertions

// Equality
pm.expect(actual).to.eql(expected);           // deep equality
pm.expect(actual).to.equal(expected);          // strict equality
pm.expect(actual).to.deep.equal(expected);     // deep equality

// Type checking
pm.expect(value).to.be.a('string');
pm.expect(value).to.be.a('number');
pm.expect(value).to.be.an('array');
pm.expect(value).to.be.an('object');
pm.expect(value).to.be.a('boolean');

// Existence
pm.expect(value).to.exist;
pm.expect(value).to.not.be.undefined;
pm.expect(value).to.not.be.null;

// Truthiness
pm.expect(value).to.be.true;
pm.expect(value).to.be.false;
pm.expect(value).to.not.be.ok;  // falsy

// Inclusion
pm.expect(string).to.include(substring);
pm.expect(array).to.include(member);
pm.expect(object).to.have.property('key');

// Length
pm.expect(array).to.have.lengthOf(5);
pm.expect(string).to.have.lengthOf(10);

// Range
pm.expect(value).to.be.above(10);
pm.expect(value).to.be.below(100);
pm.expect(value).to.be.within(10, 100);

// Matching
pm.expect(string).to.match(/regex/);
pm.expect(string).to.contain('substring');

// One of
pm.expect(value).to.be.oneOf([1, 2, 3]);
```

### 6.5 pm Object Reference

The `pm` object is the global object in Postman scripts providing access to request/response data and utilities.

```javascript
// Request Information
pm.request.url            // Request URL object
pm.request.method         // HTTP method
pm.request.headers        // Request headers list
pm.request.body           // Request body

// Response Information
pm.response.code          // Status code
pm.response.status        // Status text
pm.response.headers       // Response headers
pm.response.responseTime  // Response time in ms
pm.response.size()        // Response size
pm.response.text()        // Response body as text
pm.response.json()        // Response body as JSON

// Environment and Globals
pm.environment.set(key, value)
pm.environment.get(key)
pm.environment.unset(key)
pm.globals.set(key, value)
pm.globals.get(key)
pm.globals.unset(key)
pm.collectionVariables.set(key, value)
pm.collectionVariables.get(key)
pm.variables.set(key, value)
pm.variables.get(key)

// Iteration Data (for data-driven tests)
pm.iterationData.get(key)

// Info
pm.info.iteration         // Current iteration number
pm.info.iterationCount    // Total iterations
pm.info.requestName       // Current request name
pm.info.requestId         // Current request ID

// Sending Requests (from test scripts)
pm.sendRequest("https://postman-echo.com/get", function (err, response) {
    console.log(response.json());
});

// Tests
pm.test("Test Name", function () {
    // assertions
});

// Visualizer
pm.visualizer.set(template, data);
```

---

## 7. Environments

### 7.1 What are Environments?

Environments in Postman are sets of key-value pairs (variables) that allow you to switch between different setups (e.g., Development, Staging, Production) without changing your requests.

```
┌──────────────────────────────────────────────────────────────┐
│                    ENVIRONMENT EXAMPLES                         │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Environment        │ Variables                             ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ Development        │ base_url: http://localhost:8080     ││
│  │                    │ api_key: dev-key-123                 ││
│  │                    │ db_name: dev_db                      ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ Staging            │ base_url: https://staging.api.com    ││
│  │                    │ api_key: staging-key-456             ││
│  │                    │ db_name: staging_db                  ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ Production         │ base_url: https://api.example.com    ││
│  │                    │ api_key: prod-key-789                ││
│  │                    │ db_name: prod_db                     ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 7.2 Step-by-Step: Creating an Environment

1. Click the **Environment dropdown** (top-right, usually shows "No Environment")
2. Select **Create Environment**
3. **Name**: `Development`
4. Add variables:
   | Variable    | Initial Value              | Current Value              |
   |-------------|----------------------------|----------------------------|
   | `base_url`  | `http://localhost:8080/api` | `http://localhost:8080/api` |
   | `api_key`   | `dev-key-12345`            | `dev-key-12345`            |
   | `username`  | `dev_user`                 | `dev_user`                 |
5. Click **Save**

### 7.3 Creating Multiple Environments

Create additional environments for Staging and Production:

**Staging Environment:**
- `base_url`: `https://staging-api.example.com`
- `api_key`: `staging-key-67890`
- `username`: `staging_user`

**Production Environment:**
- `base_url`: `https://api.example.com`
- `api_key`: `prod-key-abcde`
- `username`: `prod_user`

### 7.4 Switching Environments

1. Click the **Environment dropdown** (top-right)
2. Select the desired environment
3. All requests using `{{variable}}` will automatically use the values from the selected environment

### 7.5 Environment Quick Look

Click the **eye icon** (👁️) next to the environment dropdown to see all variables in the current environment.

Click the **Edit** link in the Quick Look popup to modify variables without opening the full environment editor.

### 7.6 Using Environment Variables in Scripts

```javascript
// Pre-request Script: Set environment variable based on condition
var env = pm.environment.get("env_name");

if (env === "production") {
    pm.environment.set("timeout", 5000);
} else {
    pm.environment.set("timeout", 1000);
}

// Test Script: Verify environment-specific behavior
var expectedBaseUrl = pm.environment.get("base_url");
pm.test("Request URL contains expected base", function () {
    pm.expect(pm.request.url.toString()).to.include(expectedBaseUrl);
});
```

---

## 8. Writing Tests

### 8.1 Test Structure

Tests in Postman are written using the `pm.test()` function. Each test has a name and a function containing assertions.

```javascript
pm.test("Test Name", function () {
    // Write assertions here
});
```

### 8.2 Common Test Examples

**Test 1: Status Code Validation**
```javascript
pm.test("Status code is 200 OK", function () {
    pm.response.to.have.status(200);
});
```

**Test 2: Response Time Validation**
```javascript
pm.test("Response time is less than 500ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(500);
});
```

**Test 3: JSON Body Structure**
```javascript
pm.test("Response has correct structure", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("id");
    pm.expect(jsonData).to.have.property("name");
    pm.expect(jsonData).to.have.property("email");
});
```

**Test 4: Data Value Validation**
```javascript
pm.test("User email is correct", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.email).to.eql("Sincere@april.biz");
});
```

**Test 5: Array Response**
```javascript
pm.test("Response is an array with 10 items", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.be.an('array').that.has.lengthOf(10);
});
```

**Test 6: Nested Object Validation**
```javascript
pm.test("Address contains city and zipcode", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.address).to.have.property("city");
    pm.expect(jsonData.address).to.have.property("zipcode");
    pm.expect(jsonData.address.city).to.be.a('string');
});
```

**Test 7: Header Validation**
```javascript
pm.test("Content-Type header is application/json", function () {
    pm.expect(pm.response.headers.get("Content-Type")).to.include("application/json");
});
```

**Test 8: Cookie Validation**
```javascript
pm.test("Session cookie is set", function () {
    pm.expect(pm.cookies.has("session_id")).to.be.true;
});
```

**Test 9: Schema Validation**
```javascript
pm.test("Response matches expected schema", function () {
    var jsonData = pm.response.json();
    var schema = {
        "type": "object",
        "properties": {
            "id": { "type": "integer" },
            "name": { "type": "string" },
            "email": { "type": "string" }
        },
        "required": ["id", "name", "email"]
    };
    pm.expect(tv4.validate(jsonData, schema)).to.be.true;
});
```

### 8.3 Multiple Tests in One Script

```javascript
// Test Suite: User API Validation
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response time is acceptable", function () {
    pm.expect(pm.response.responseTime).to.be.below(1000);
});

pm.test("Content-Type is JSON", function () {
    pm.response.to.have.header("Content-Type");
    pm.expect(pm.response.headers.get("Content-Type")).to.include("application/json");
});

pm.test("Response contains user data", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("id");
    pm.expect(jsonData).to.have.property("name");
    pm.expect(jsonData).to.have.property("email");
});

pm.test("User ID is positive integer", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.id).to.be.above(0);
    pm.expect(jsonData.id).to.be.a('number');
});
```

### 8.4 Conditional Tests

```javascript
// Run tests based on status code
if (pm.response.code === 200) {
    pm.test("Success response validation", function () {
        var jsonData = pm.response.json();
        pm.expect(jsonData).to.have.property("data");
    });
} else if (pm.response.code === 404) {
    pm.test("Not found error validation", function () {
        var jsonData = pm.response.json();
        pm.expect(jsonData).to.have.property("error");
        pm.expect(jsonData.error).to.include("not found");
    });
} else {
    pm.test("Unexpected status code", function () {
        pm.expect.fail("Unexpected status: " + pm.response.code);
    });
}
```

---

## 9. Debugging

### 9.1 Postman Console

The Postman Console is the primary debugging tool. It shows:
- Request and response details
- Console.log output from scripts
- Network errors
- Raw request sent to server

**Opening the Console:**
- **Keyboard shortcut**: Ctrl+Alt+C (Windows) or Cmd+Option+C (Mac)
- **Menu**: View → Show Postman Console
- **Bottom bar**: Click "Console" in the status bar

### 9.2 Using console.log

```javascript
// Pre-request Script: Log variable values
console.log("Starting request: " + pm.info.requestName);
console.log("Environment: " + pm.environment.get("base_url"));

// Test Script: Log response data
var jsonData = pm.response.json();
console.log("Response ID: " + jsonData.id);
console.log("Response Name: " + jsonData.name);
console.log("Full Response: " + JSON.stringify(jsonData, null, 2));

// Log different types
console.log("String:", "hello");
console.log("Number:", 123);
console.log("Object:", { key: "value" });
console.log("Array:", [1, 2, 3]);
```

### 9.3 Debugging Request/Response

**Inspect Raw Request:**
In the Console, click on a request to see:
- Request headers
- Request body
- Response headers
- Response body
- Network timing

**Inspect Network Information:**
1. Send a request
2. Look at the response section
3. Hover over the status, time, or size indicators for details
4. Click the **Network** icon (🌐) to see timing breakdown:
   - DNS lookup
   - TCP connection
   - SSL handshake
   - Request sent
   - Waiting for response
   - Response received

### 9.4 Common Debugging Scenarios

**Scenario 1: Variable Not Resolving**
```javascript
// Check if variable exists
var value = pm.environment.get("missing_var");
console.log("Value:", value);  // null or undefined

// Check all variables
console.log("Environment variables:", pm.environment.toObject());
console.log("Global variables:", pm.globals.toObject());
console.log("Collection variables:", pm.collectionVariables.toObject());
```

**Scenario 2: JSON Parsing Error**
```javascript
// Check if response is valid JSON
try {
    var jsonData = pm.response.json();
    console.log("Parsed JSON successfully:", jsonData);
} catch (e) {
    console.log("JSON parse error:", e.message);
    console.log("Raw response:", pm.response.text());
}
```

**Scenario 3: Header Issues**
```javascript
// Log all request headers
console.log("Request Headers:", pm.request.headers.toJSON());

// Log all response headers
console.log("Response Headers:", pm.response.headers.toJSON());

// Check specific header
var contentType = pm.response.headers.get("Content-Type");
console.log("Content-Type:", contentType);
```

### 9.5 Using Postman Console Filters

The Console supports filtering:
- **All**: Show everything
- **Log**: Show console.log output
- **Info**: Show informational messages
- **Warn**: Show warnings
- **Error**: Show errors

Use the search box to filter by keyword.

---

## 10. Newman — Command Line Runner

### 10.1 What is Newman?

Newman is a command-line Collection Runner for Postman. It allows you to run and test a Postman Collection directly from the command line.

```
┌──────────────────────────────────────────────────────────────┐
│                    NEWMAN BENEFITS                              │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Benefit            │ Description                            ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ CI/CD Integration  │ Run tests in Jenkins, GitHub Actions   ││
│  │ Automation         │ Schedule via cron jobs                 ││
│  │ Reporting          │ Generate HTML, JUnit, JSON reports     ││
│  │ No GUI Required    │ Run on headless servers                ││
│  │ Multiple Formats   │ Export in various formats              ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 10.2 Installing Newman

**Prerequisite:** Node.js installed (v14 or higher recommended)

**Step-by-Step Installation:**

1. **Install Node.js** from https://nodejs.org
2. **Verify installation**:
```bash
node --version
npm --version
```
3. **Install Newman globally**:
```bash
npm install -g newman
```
4. **Verify Newman installation**:
```bash
newman --version
```

**Install HTML Reporter (optional but recommended):**
```bash
npm install -g newman-reporter-html
npm install -g newman-reporter-htmlextra
```

### 10.3 Basic Newman Commands

**Run a Collection from URL:**
```bash
newman run https://www.getpostman.com/collections/your-collection-id
```

**Run a Collection from File:**
```bash
newman run MyCollection.postman_collection.json
```

**Run with Environment:**
```bash
newman run MyCollection.json -e MyEnvironment.postman_environment.json
```

**Run with Global Variables:**
```bash
newman run MyCollection.json -g globals.json
```

**Run with Data File (CSV):**
```bash
newman run MyCollection.json -d data.csv
```

**Run with Data File (JSON):**
```bash
newman run MyCollection.json -d data.json
```

**Run with Specific Number of Iterations:**
```bash
newman run MyCollection.json -n 5
```

**Run with Delay Between Requests:**
```bash
newman run MyCollection.json --delay-request 500
```

### 10.4 Newman Options Reference

```
┌──────────────────────────────────────────────────────────────┐
│                    NEWMAN CLI OPTIONS                           │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Option             │ Description                            ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ run <collection>   │ Run a collection (required)            ││
│  │ -e, --environment  │ Specify environment file               ││
│  │ -g, --globals      │ Specify globals file                   ││
│  │ -d, --iteration-data│ Specify data file (CSV/JSON)           ││
│  │ -n, --iteration-count│ Number of iterations                 ││
│  │ --delay-request    │ Delay between requests (ms)            ││
│  │ --timeout-request  │ Request timeout (ms)                 ││
│  │ --bail             │ Stop on first error                    ││
│  │ --suppress-exit-code│ Exit with 0 even if tests fail        ││
│  │ -r, --reporters    │ Specify reporters (cli, json, html, etc.)││
│  │ --reporter-json-export│ Export JSON report path             ││
│  │ --reporter-html-export│ Export HTML report path             ││
│  │ --verbose          │ Show detailed request/response info    ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 10.5 Generating HTML Reports

**Using HTML Reporter:**
```bash
newman run MyCollection.json -r html --reporter-html-export report.html
```

**Using HTMLExtra Reporter (Recommended):**
```bash
newman run MyCollection.json -r htmlextra --reporter-htmlextra-export report.html
```

**Multiple Reporters:**
```bash
newman run MyCollection.json -r cli,json,htmlextra \
  --reporter-json-export report.json \
  --reporter-htmlextra-export report.html
```

### 10.6 Newman Example with Full Options

```bash
newman run "E-Commerce API Tests.postman_collection.json" \
  -e "Development.postman_environment.json" \
  -g "globals.json" \
  -d "test_data.csv" \
  -n 3 \
  --delay-request 1000 \
  --timeout-request 30000 \
  --bail false \
  -r cli,htmlextra,json \
  --reporter-htmlextra-export "newman-report.html" \
  --reporter-json-export "newman-report.json" \
  --verbose
```

### 10.7 Newman in a Node.js Script

```javascript
// newman_script.js
const newman = require('newman');

newman.run({
    collection: require('./MyCollection.postman_collection.json'),
    environment: require('./Development.postman_environment.json'),
    globals: require('./globals.json'),
    iterationData: require('./data.csv'),
    reporters: ['cli', 'htmlextra', 'json'],
    reporter: {
        htmlextra: {
            export: './reports/report.html'
        },
        json: {
            export: './reports/report.json'
        }
    },
    delayRequest: 500,
    timeoutRequest: 30000,
    bail: false
}, function (err, summary) {
    if (err) {
        console.error('Collection run error:', err);
    }
    
    console.log('Run complete!');
    console.log('Total requests:', summary.run.stats.requests.total);
    console.log('Failed tests:', summary.run.failures.length);
    
    // Exit with error code if tests failed
    if (summary.run.failures.length > 0) {
        process.exit(1);
    }
});
```

Run with: `node newman_script.js`

---

## 11. Jenkins Integration

### 11.1 Why Jenkins for API Testing?

Jenkins integration allows you to:
- Run API tests on every code commit
- Schedule nightly test runs
- Generate and archive reports
- Integrate with CI/CD pipelines
- Send notifications on failures

### 11.2 Prerequisites

- Jenkins installed and running
- Node.js installed on Jenkins server
- Newman installed globally on Jenkins server
- Git repository with Postman collection files

### 11.3 Step-by-Step: Jenkins Setup

**Step 1: Install Node.js Plugin in Jenkins**
1. Go to **Manage Jenkins** → **Manage Plugins**
2. Search for **NodeJS Plugin** and install it
3. Restart Jenkins if required

**Step 2: Configure Node.js in Jenkins**
1. Go to **Manage Jenkins** → **Global Tool Configuration**
2. Scroll to **NodeJS** section
3. Click **Add NodeJS**
4. Name: `NodeJS_18`
5. Install automatically: Check and select version 18.x.x
6. Save

**Step 3: Create a New Jenkins Job**
1. Click **New Item**
2. Enter name: `Postman-API-Tests`
3. Select **Freestyle project**
4. Click **OK**

**Step 4: Configure Source Code Management**
1. Go to **Source Code Management** tab
2. Select **Git**
3. Repository URL: `https://github.com/your-repo/postman-tests.git`
4. Credentials: Add if needed
5. Branch: `*/main`

**Step 5: Configure Build Environment**
1. Go to **Build Environment** tab
2. Check **Provide Node & npm bin/ folder to PATH**
3. Select NodeJS installation: `NodeJS_18`

**Step 6: Add Build Steps**
1. Go to **Build** tab
2. Click **Add build step** → **Execute Windows batch command** (or Shell for Linux/Mac)
3. Enter commands:

```bash
# Install Newman if not already installed
npm install -g newman
npm install -g newman-reporter-htmlextra

# Run the collection
newman run collections/E-Commerce_API_Tests.postman_collection.json \
  -e environments/Development.postman_environment.json \
  -r cli,htmlextra,json \
  --reporter-htmlextra-export "newman-report.html" \
  --reporter-json-export "newman-report.json"
```

**Step 7: Configure Post-build Actions**
1. Go to **Post-build Actions** tab
2. Click **Add post-build action** → **Publish HTML reports**
3. HTML directory to archive: `.` (or workspace directory)
4. Index page: `newman-report.html`
5. Report title: `API Test Report`
6. Also add **Archive the artifacts**:
   - Files to archive: `newman-report.*`

**Step 8: Save and Run**
1. Click **Save**
2. Click **Build Now**
3. View the **Console Output** for execution details
4. View the **HTML Report** after build completion

### 11.4 Jenkins Pipeline (Jenkinsfile)

Create a `Jenkinsfile` in your repository:

```groovy
pipeline {
    agent any
    
    tools {
        nodejs 'NodeJS_18'
    }
    
    environment {
        COLLECTION_FILE = 'collections/E-Commerce_API_Tests.postman_collection.json'
        ENVIRONMENT_FILE = 'environments/Development.postman_environment.json'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Install Dependencies') {
            steps {
                sh 'npm install -g newman'
                sh 'npm install -g newman-reporter-htmlextra'
            }
        }
        
        stage('Run API Tests') {
            steps {
                sh """
                    newman run ${COLLECTION_FILE} \
                        -e ${ENVIRONMENT_FILE} \
                        -r cli,htmlextra,json \
                        --reporter-htmlextra-export "newman-report.html" \
                        --reporter-json-export "newman-report.json" \
                        --verbose
                """
            }
        }
        
        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'newman-report.*', allowEmptyArchive: true
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: '.',
                    reportFiles: 'newman-report.html',
                    reportName: 'Postman API Test Report'
                ])
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline completed'
        }
        failure {
            echo 'Tests failed!'
            // Send email notification
            mail to: 'team@example.com',
                 subject: "API Tests Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                 body: "Check console output at ${env.BUILD_URL}"
        }
        success {
            echo 'All tests passed!'
        }
    }
}
```

### 11.5 Jenkins Pipeline with Parameters

```groovy
pipeline {
    agent any
    
    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['Development', 'Staging', 'Production'],
            description: 'Select environment to run tests against'
        )
        string(
            name: 'ITERATIONS',
            defaultValue: '1',
            description: 'Number of iterations to run'
        )
        booleanParam(
            name: 'ENABLE_VERBOSE',
            defaultValue: false,
            description: 'Enable verbose logging'
        )
    }
    
    stages {
        stage('Run Tests') {
            steps {
                script {
                    def envFile = "environments/${params.ENVIRONMENT}.postman_environment.json"
                    def verboseFlag = params.ENABLE_VERBOSE ? '--verbose' : ''
                    
                    sh """
                        newman run collections/MyCollection.json \
                            -e ${envFile} \
                            -n ${params.ITERATIONS} \
                            -r cli,htmlextra \
                            --reporter-htmlextra-export "report.html" \
                            ${verboseFlag}
                    """
                }
            }
        }
    }
}
```

---

## 12. Workspaces

### 12.1 What are Workspaces?

Workspaces are collaborative spaces in Postman where you can organize your API work and collaborate with team members.

```
┌──────────────────────────────────────────────────────────────┐
│                    WORKSPACE TYPES                              │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Type               │ Description                            ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ Personal           │ Private to you, default workspace      ││
│  │ Team               │ Shared with team members               ││
│  │ Public             │ Visible to entire Postman community   ││
│  │ Partner            │ Shared with external partners          ││
│  │ Private            │ Restricted access within team          ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 12.2 Creating a Workspace

**Step-by-Step:**
1. Click the **Workspace dropdown** (top-left)
2. Select **Create Workspace**
3. Choose workspace type:
   - **Personal**: For individual work
   - **Team**: For collaboration
   - **Public**: For open-source or community sharing
4. **Name**: `API Testing Team`
5. **Description**: `Workspace for QA team API testing`
6. **Visibility**: Select appropriate visibility
7. Click **Create Workspace**

### 12.3 Managing Workspace Members

**Adding Members:**
1. Go to the workspace
2. Click **Invite** (top-right)
3. Enter email addresses or usernames
4. Set roles:
   - **Viewer**: Can view but not edit
   - **Editor**: Can edit collections and environments
   - **Admin**: Can manage workspace settings and members
5. Click **Send Invites**

### 12.4 Moving Collections Between Workspaces

1. **Right-click** on a collection
2. Select **Share Collection**
3. Choose the **target workspace**
4. Click **Move and Share**

### 12.5 Workspace Use Cases

**Personal Workspace:**
- Individual learning and experimentation
- Draft collections before sharing

**Team Workspace:**
- Shared API development
- Collaborative testing
- Version control for collections

**Public Workspace:**
- Publish API documentation
- Share examples with community
- Open-source project APIs

---

## 13. Data-Driven Testing

### 13.1 What is Data-Driven Testing?

Data-driven testing is a methodology where test data is separated from test scripts. The same test script runs multiple times with different input data from an external file (CSV or JSON).

```
┌──────────────────────────────────────────────────────────────┐
│                    DATA-DRIVEN TESTING FLOW                     │
│                                                                  │
│  ┌─────────────┐    ┌─────────────┐    ┌──────────────────┐   │
│  │  Data File  │───→│  Collection │───→│  Iteration 1     │   │
│  │  (CSV/JSON) │    │  Runner     │    │  Iteration 2     │   │
│  │             │    │             │    │  Iteration 3     │   │
│  │ username,   │    │             │    │  ...             │   │
│  │ password    │    │             │    │  Iteration N     │   │
│  └─────────────┘    └─────────────┘    └──────────────────┘   │
│                                                                  │
│  Benefits:                                                       │
│  - Test multiple scenarios with one script                       │
│  - Easy to add/modify test cases                                  │
│  - Separate test logic from test data                             │
│  - Run regression with comprehensive data                         │
└──────────────────────────────────────────────────────────────┘
```

### 13.2 CSV Data File Format

Create a CSV file with headers matching the variable names you want to use:

**`test_data.csv`:**
```csv
username,password,expected_status,expected_message
admin,admin123,200,success
user1,wrongpass,401,unauthorized
user2,pass456,200,success
invalid_user,anypass,404,not found
locked_user,locked123,403,account locked
```

### 13.3 JSON Data File Format

Create a JSON file with an array of objects:

**`test_data.json`:**
```json
[
  {
    "username": "admin",
    "password": "admin123",
    "expected_status": 200,
    "expected_message": "success"
  },
  {
    "username": "user1",
    "password": "wrongpass",
    "expected_status": 401,
    "expected_message": "unauthorized"
  },
  {
    "username": "user2",
    "password": "pass456",
    "expected_status": 200,
    "expected_message": "success"
  },
  {
    "username": "invalid_user",
    "password": "anypass",
    "expected_status": 404,
    "expected_message": "not found"
  },
  {
    "username": "locked_user",
    "password": "locked123",
    "expected_status": 403,
    "expected_message": "account locked"
  }
]
```

### 13.4 Using Data Variables in Requests

In your request, use the data variables with `{{variable}}` syntax:

**URL:**
```
https://api.example.com/login
```

**Body (raw JSON):**
```json
{
  "username": "{{username}}",
  "password": "{{password}}"
}
```

### 13.5 Using Data Variables in Test Scripts

```javascript
// Get iteration data
var username = pm.iterationData.get("username");
var password = pm.iterationData.get("password");
var expectedStatus = pm.iterationData.get("expected_status");
var expectedMessage = pm.iterationData.get("expected_message");

// Log current iteration data
console.log("Testing with user: " + username);
console.log("Expected status: " + expectedStatus);

// Validate status code
pm.test("Status code matches expected for " + username, function () {
    pm.response.to.have.status(parseInt(expectedStatus));
});

// Validate response message
pm.test("Response message matches expected for " + username, function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.message).to.include(expectedMessage);
});
```

### 13.6 Running Data-Driven Tests in Collection Runner

1. **Select your collection** in the Collection Runner
2. **Configure settings**:
   - Environment: Select appropriate environment
   - Iterations: Set to match number of data rows (or leave as auto)
   - Delay: 500ms
3. **Data File**: Click **Select File** and choose your CSV or JSON file
4. **Preview Data**: Verify the data is parsed correctly
5. **Run the Collection**
6. **Review Results**: Each iteration will show pass/fail status

### 13.7 Running Data-Driven Tests with Newman

**With CSV:**
```bash
newman run Login_API_Collection.json -d test_data.csv -n 5
```

**With JSON:**
```bash
newman run Login_API_Collection.json -d test_data.json -n 5
```

**With full options:**
```bash
newman run Login_API_Collection.json \
  -e Development.json \
  -d test_data.csv \
  -n 5 \
  --delay-request 500 \
  -r cli,htmlextra \
  --reporter-htmlextra-export "data-driven-report.html"
```

### 13.8 Advanced Data-Driven Testing

**Using Data Variables in Pre-request Scripts:**
```javascript
// Pre-request Script: Set up dynamic data
var username = pm.iterationData.get("username");
var password = pm.iterationData.get("password");

// Generate encoded credentials
var encoded = btoa(username + ":" + password);
pm.environment.set("encoded_credentials", encoded);

// Set dynamic header
// In Headers tab: Authorization: Basic {{encoded_credentials}}
```

**Conditional Testing Based on Data:**
```javascript
var testType = pm.iterationData.get("test_type");

if (testType === "positive") {
    pm.test("Positive test: Status is 200", function () {
        pm.response.to.have.status(200);
    });
} else if (testType === "negative") {
    pm.test("Negative test: Status is 400 or 401", function () {
        pm.expect(pm.response.code).to.be.oneOf([400, 401]);
    });
}
```

---

## 14. Running Collections Remotely

### 14.1 Postman API

Postman provides a public API that allows you to programmatically access data stored in your Postman account, including collections, environments, and run results.

**Postman API Base URL:**
```
https://api.getpostman.com
```

### 14.2 Getting a Postman API Key

1. Go to **Postman Web** or **Postman App**
2. Click your **Profile** → **Settings**
3. Go to **Postman API Keys** tab
4. Click **Generate API Key**
5. Name: `Remote Runner Key`
6. Copy the key and save it securely

### 14.3 Running Collections via Postman API

**Step 1: Get Collection UID**
```bash
# List all collections
curl -X GET https://api.getpostman.com/collections \
  -H "X-Api-Key: your-api-key"
```

**Step 2: Run Collection via API**
```bash
# Create a monitored run
curl -X POST https://api.getpostman.com/monitors \
  -H "X-Api-Key: your-api-key" \
  -H "Content-Type: application/json" \
  -d '{
    "collection": "your-collection-uid",
    "environment": "your-environment-uid",
    "name": "Remote Run",
    "schedule": {
      "cron": "0 0 * * *",
      "timezone": "UTC"
    }
  }'
```

### 14.4 Using Postman Monitors

Postman Monitors allow you to schedule collection runs from the cloud.

**Step-by-Step: Creating a Monitor**

1. Go to the **Collection** you want to monitor
2. Click the **...** (more actions) menu
3. Select **Monitor Collection**
4. **Name**: `Daily Health Check`
5. **Frequency**: Every hour / Every day / Custom
6. **Select Region**: Choose closest region
7. **Add Email Alerts**: Enter notification emails
8. Click **Create Monitor**

**Viewing Monitor Results:**
1. Go to **Monitors** in the left sidebar
2. Click on your monitor
3. View run history, success rates, and response times
4. Click on individual runs to see detailed results

### 14.5 Sharing Collection Run Links

You can generate a public link to run your collection:

1. Go to the **Collection**
2. Click **...** → **Share**
3. Go to **Run in Postman** tab
4. Copy the **Web Link** or **Embed code**

Anyone with the link can run the collection directly in their Postman app.

### 14.6 Running Collections via URL (Newman)

```bash
# Run collection from Postman cloud URL
newman run "https://api.getpostman.com/collections/{collection_uid}?apikey={your_api_key}"

# Run with environment from URL
newman run "https://api.getpostman.com/collections/{uid}?apikey={key}" \
  -e "https://api.getpostman.com/environments/{env_uid}?apikey={key}"
```

---

## 15. SOAP Requests

### 15.1 What is SOAP?

SOAP (Simple Object Access Protocol) is a protocol for exchanging structured information in web services. It uses XML format and typically operates over HTTP.

```
┌──────────────────────────────────────────────────────────────┐
│                    SOAP vs REST COMPARISON                      │
│  ┌────────────────────┬─────────────────┬──────────────────┐│
│  │ Feature            │ SOAP            │ REST              ││
│  ├────────────────────┼─────────────────┼──────────────────┤│
│  │ Format             │ XML only        │ XML, JSON, etc.   ││
│  │ Protocol           │ HTTP, SMTP, etc.│ HTTP only         ││
│  │ Standards          │ Strict (WSDL)   │ Flexible          ││
│  │ Security           │ Built-in (WS)   │ HTTPS + OAuth     ││
│  │ Caching            │ Not supported   │ Supported         ││
│  │ Verbosity          │ Verbose         │ Lightweight       ││
│  └────────────────────┴─────────────────┴──────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 15.2 Creating a SOAP Request in Postman

**Step-by-Step:**

1. **Create a new request** tab
2. **Select Method**: `POST` (SOAP typically uses POST)
3. **Enter URL**: `http://www.dneonline.com/calculator.asmx`
   (Example SOAP calculator service)

4. **Set Headers**:
   | Key             | Value              |
   |-----------------|--------------------|
   | `Content-Type`  | `text/xml`         |
   | `SOAPAction`    | `"http://tempuri.org/Add"` |

5. **Go to Body Tab** → Select `raw` → Choose `XML` from the dropdown

6. **Enter SOAP XML Body**:
```xml
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:xsd="http://www.w3.org/2001/XMLSchema"
               xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <Add xmlns="http://tempuri.org/">
      <intA>5</intA>
      <intB>10</intB>
    </Add>
  </soap:Body>
</soap:Envelope>
```

7. **Click Send**
8. **View Response** (XML format)

**Expected Response:**
```xml
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:xsd="http://www.w3.org/2001/XMLSchema">
  <soap:Body>
    <AddResponse xmlns="http://tempuri.org/">
      <AddResult>15</AddResult>
    </AddResponse>
  </soap:Body>
</soap:Envelope>
```

### 15.3 SOAP Request with WSDL

WSDL (Web Services Description Language) describes the SOAP service.

**Importing WSDL:**
1. Click **Import** in Postman
2. Enter WSDL URL: `http://www.dneonline.com/calculator.asmx?WSDL`
3. Postman will generate a collection based on the WSDL

### 15.4 Testing SOAP Responses

```javascript
// Test: Status code is 200
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test: Content-Type is text/xml
pm.test("Content-Type is text/xml", function () {
    pm.expect(pm.response.headers.get("Content-Type")).to.include("text/xml");
});

// Test: Response contains expected result
pm.test("Response contains AddResult", function () {
    pm.expect(pm.response.text()).to.include("<AddResult>15</AddResult>");
});

// Test: XML is valid (using xml2Json)
pm.test("Valid XML response", function () {
    var jsonObject = xml2Json(pm.response.text());
    pm.expect(jsonObject).to.not.be.undefined;
});
```

### 15.5 Common SOAP Headers

| Header          | Description                              |
|-----------------|------------------------------------------|
| `Content-Type`  | `text/xml` or `application/soap+xml`     |
| `SOAPAction`    | Identifies the operation being called      |
| `Authorization` | Basic Auth or Bearer token if required    |
| `Accept`        | `text/xml`                               |

---

## 16. Response Extraction and Chaining

### 16.1 Extracting Values from Response

API chaining is the process of extracting data from one response and using it in subsequent requests. This is essential for workflows like login → get token → use token.

```
┌──────────────────────────────────────────────────────────────┐
│                    API CHAINING FLOW                            │
│                                                                  │
│  ┌─────────────┐     ┌─────────────┐     ┌──────────────────┐ │
│  │  Request 1  │────→│  Extract    │────→│  Request 2       │ │
│  │  Login API  │     │  auth_token │     │  Use token in    │ │
│  │             │     │  from JSON  │     │  Authorization   │ │
│  │             │     │  response   │     │  header          │ │
│  └─────────────┘     └─────────────┘     └──────────────────┘ │
│                                                                  │
│  Example:                                                        │
│  1. POST /login → { token: "abc123" }                            │
│  2. Extract token → pm.environment.set("token", "abc123")       │
│  3. GET /profile → Header: Authorization: Bearer {{token}}       │
└──────────────────────────────────────────────────────────────┘
```

### 16.2 Extracting JSON Values

**Example: Extracting a Token from Login Response**

Response:
```json
{
  "status": "success",
  "data": {
    "user_id": 123,
    "auth_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "refresh_token": "dGhpcyBpcyBhIHJlZnJlc2g..."
  }
}
```

Test Script:
```javascript
// Parse JSON response
var jsonData = pm.response.json();

// Extract auth token
var authToken = jsonData.data.auth_token;
console.log("Extracted Token: " + authToken);

// Save to environment variable for chaining
pm.environment.set("auth_token", authToken);

// Also save refresh token
pm.environment.set("refresh_token", jsonData.data.refresh_token);

// Save user ID
pm.environment.set("user_id", jsonData.data.user_id);
```

### 16.3 Extracting from Arrays

**Example: Extract First User ID from List**

Response:
```json
[
  {
    "id": 1,
    "name": "Leanne Graham"
  },
  {
    "id": 2,
    "name": "Ervin Howell"
  }
]
```

Test Script:
```javascript
var jsonData = pm.response.json();

// Extract first user's ID
var firstUserId = jsonData[0].id;
pm.environment.set("first_user_id", firstUserId);

// Extract all user IDs
var userIds = jsonData.map(function(user) {
    return user.id;
});
pm.environment.set("all_user_ids", JSON.stringify(userIds));
```

### 16.4 Extracting from Headers

```javascript
// Extract a header value
var requestId = pm.response.headers.get("X-Request-Id");
pm.environment.set("request_id", requestId);

// Extract all headers
var headers = pm.response.headers.toJSON();
console.log("Response Headers:", JSON.stringify(headers, null, 2));
```

### 16.5 Extracting from Cookies

```javascript
// Extract session cookie
var sessionCookie = pm.cookies.get("session_id");
pm.environment.set("session_id", sessionCookie);

// Extract all cookies
var allCookies = pm.cookies.toObject();
console.log("All Cookies:", JSON.stringify(allCookies, null, 2));
```

### 16.6 Using Extracted Values in Next Request

**Request 1: Login (POST)**
- Test Script extracts `auth_token` and saves to environment

**Request 2: Get Profile (GET)**
- URL: `{{base_url}}/profile`
- Headers:
  - `Authorization`: `Bearer {{auth_token}}`
  - `X-User-ID`: `{{user_id}}`

**Request 3: Update Profile (PUT)**
- URL: `{{base_url}}/profile/{{user_id}}`
- Headers:
  - `Authorization`: `Bearer {{auth_token}}`
- Body:
```json
{
  "user_id": {{user_id}},
  "name": "Updated Name"
}
```

### 16.7 Chaining Multiple Requests

```javascript
// Request 1: Create Order (POST /orders)
// Test Script:
var jsonData = pm.response.json();
var orderId = jsonData.order_id;
pm.environment.set("order_id", orderId);
console.log("Created Order ID: " + orderId);

// ──────────────────────────────────────────────

// Request 2: Get Order (GET /orders/{{order_id}})
// URL uses {{order_id}} variable
// Test Script:
pm.test("Order ID matches", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.order_id).to.eql(pm.environment.get("order_id"));
});

// ──────────────────────────────────────────────

// Request 3: Update Order (PUT /orders/{{order_id}})
// URL uses {{order_id}} variable

// ──────────────────────────────────────────────

// Request 4: Delete Order (DELETE /orders/{{order_id}})
// URL uses {{order_id}} variable
// Test Script:
pm.test("Order deleted successfully", function () {
    pm.response.to.have.status(204);
});
```

### 16.8 Handling Nested JSON Extraction

```javascript
// Complex nested JSON
var jsonData = pm.response.json();

// Extract nested values
var street = jsonData.address.street;
var geoLat = jsonData.address.geo.lat;
var companyName = jsonData.company.name;

// Save to environment
pm.environment.set("user_street", street);
pm.environment.set("user_latitude", geoLat);
pm.environment.set("user_company", companyName);

// Extract with fallback (default value)
var phone = jsonData.phone || "N/A";
pm.environment.set("user_phone", phone);

// Extract using optional chaining pattern
var suite = jsonData.address && jsonData.address.suite ? jsonData.address.suite : "N/A";
pm.environment.set("user_suite", suite);
```

### 16.9 Using JSONPath (with lodash)

```javascript
// Postman includes lodash (_) for advanced operations
var jsonData = pm.response.json();

// Find user by name
var user = _.find(jsonData, { name: "Leanne Graham" });
if (user) {
    pm.environment.set("found_user_id", user.id);
}

// Filter users by city
var usersInCity = _.filter(jsonData, function(u) {
    return u.address.city === "Gwenborough";
});
console.log("Users in Gwenborough: " + usersInCity.length);

// Map to extract specific fields
var emails = _.map(jsonData, "email");
pm.environment.set("all_emails", JSON.stringify(emails));
```

---

## 17. API Authorization

### 17.1 Authorization Types in Postman

```
┌──────────────────────────────────────────────────────────────┐
│                    AUTHORIZATION TYPES                          │
│  ┌────────────────────┬──────────────────────────────────────┐│
│  │ Type               │ Use Case                             ││
│  ├────────────────────┼──────────────────────────────────────┤│
│  │ Inherit from Parent│ Use collection/folder auth settings  ││
│  │ No Auth            │ Public APIs, no authentication       ││
│  │ Basic Auth         │ Username + Password (Base64 encoded) ││
│  │ Bearer Token       │ OAuth 2.0 / JWT tokens               ││
│  │ Digest Auth        │ Username + Password (hashed)         ││
│  │ OAuth 1.0          │ Legacy OAuth (Twitter, etc.)         ││
│  │ OAuth 2.0          │ Modern OAuth (Google, Facebook)     ││
│  │ API Key            │ Simple key in header or query        ││
│  │ AWS Signature      │ AWS API authentication              ││
│  │ NTLM               │ Windows authentication               ││
│  │ Hawk Auth          │ Hawk authentication protocol         ││
│  └────────────────────┴──────────────────────────────────────┘│
└──────────────────────────────────────────────────────────────┘
```

### 17.2 Basic Authentication

**Step-by-Step:**
1. Go to the **Authorization** tab
2. Select type: **Basic Auth**
3. Enter:
   - **Username**: `admin`
   - **Password**: `admin123`
4. Postman automatically encodes them as `Base64(username:password)`
5. The header `Authorization: Basic YWRtaW46YWRtaW4xMjM=` is sent

**Test Script for Basic Auth:**
```javascript
pm.test("Basic Auth successful", function () {
    pm.response.to.have.status(200);
});
```

### 17.3 Bearer Token Authentication

**Step-by-Step:**
1. Go to the **Authorization** tab
2. Select type: **Bearer Token**
3. Enter Token: `{{auth_token}}` (or paste the actual token)
4. Postman adds header: `Authorization: Bearer <token>`

**Dynamic Token from Previous Request:**
```javascript
// In Login request's Test script
var jsonData = pm.response.json();
pm.environment.set("auth_token", jsonData.token);
```

**Using in Subsequent Request:**
- Authorization tab → Bearer Token → Token: `{{auth_token}}`

### 17.4 API Key Authentication

**Step-by-Step:**
1. Go to the **Authorization** tab
2. Select type: **API Key**
3. Configure:
   - **Key**: `X-API-Key`
   - **Value**: `your-api-key-here`
   - **Add to**: `Header` (or Query Params)
4. Postman adds header: `X-API-Key: your-api-key-here`

**Alternative: Manual Header**
- Headers tab: `X-API-Key`: `{{api_key}}`

### 17.5 OAuth 2.0 Authentication

**Step-by-Step:**
1. Go to the **Authorization** tab
2. Select type: **OAuth 2.0**
3. Configure:
   - **Grant Type**: `Authorization Code` (or `Client Credentials`, `Password Credentials`, etc.)
   - **Callback URL**: `https://oauth.pstmn.io/v1/callback` (Postman callback)
   - **Auth URL**: `https://accounts.google.com/o/oauth2/auth`
   - **Access Token URL**: `https://oauth2.googleapis.com/token`
   - **Client ID**: Your OAuth client ID
   - **Client Secret**: Your OAuth client secret
   - **Scope**: `email profile`
   - **State**: Optional state parameter
4. Click **Get New Access Token**
5. Authenticate in the browser popup
6. Token is saved and can be used in requests

**Token Expiration and Refresh:**
```javascript
// Test Script: Check if token is expired
var jsonData = pm.response.json();
if (jsonData.error === "token_expired") {
    console.log("Token expired! Need to refresh.");
    // Trigger refresh token request or mark for re-auth
}
```

### 17.6 Authorization at Collection Level

Instead of setting auth for each request, set it at the collection level:

1. **Right-click** on the collection → **Edit**
2. Go to the **Authorization** tab
3. Select type: **Bearer Token**
4. Enter: `{{auth_token}}`
5. Click **Update**

All requests in the collection will inherit this auth unless overridden.

### 17.7 Handling Auth in Test Scripts

```javascript
// Test: Check if auth was successful
pm.test("Authentication successful", function () {
    pm.response.to.have.status(200);
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("token");
});

// Test: Check for unauthorized access
pm.test("Unauthorized access returns 401", function () {
    pm.response.to.have.status(401);
});

// Test: Check for forbidden access
pm.test("Forbidden access returns 403", function () {
    pm.response.to.have.status(403);
});
```

---

## 18. Crash Course Summary

### 18.1 Complete Workflow Overview

This section summarizes all the concepts from Videos 20-23 into a comprehensive workflow.

```
┌──────────────────────────────────────────────────────────────┐
│                    COMPLETE API TESTING WORKFLOW                │
│                                                                  │
│  1. SETUP                                                        │
│     ├── Install Postman                                          │
│     ├── Create Workspace (Team / Personal)                      │
│     └── Install Newman (npm install -g newman)                  │
│                                                                  │
│  2. ORGANIZATION                                                 │
│     ├── Create Collection (e.g., "E-Commerce API")               │
│     ├── Create Folders (Auth, Users, Products, Orders)          │
│     └── Add Requests to Folders                                  │
│                                                                  │
│  3. ENVIRONMENT SETUP                                            │
│     ├── Create Environments (Dev, Staging, Prod)                │
│     ├── Set Variables (base_url, api_key, timeout)              │
│     └── Switch environments as needed                            │
│                                                                  │
│  4. REQUEST BUILDING                                             │
│     ├── Configure URL: {{base_url}}/users                        │
│     ├── Set Method: GET, POST, PUT, DELETE                      │
│     ├── Add Headers: Content-Type, Authorization                │
│     ├── Add Body (for POST/PUT): raw JSON                       │
│     └── Add Pre-request Script: Set dynamic values               │
│                                                                  │
│  5. TESTING                                                      │
│     ├── Write Tests: Status, Time, Body, Headers                │
│     ├── Extract Data: pm.response.json(), save to env           │
│     └── Chain Requests: Use extracted values in next requests   │
│                                                                  │
│  6. EXECUTION                                                    │
│     ├── Run Individual Requests                                  │
│     ├── Run Collection Runner (with data files)                  │
│     ├── Run with Newman (CLI)                                    │
│     └── Run in Jenkins (CI/CD)                                  │
│                                                                  │
│  7. REPORTING                                                    │
│     ├── View Postman Test Results                                │
│     ├── Generate HTML Reports (newman-reporter-htmlextra)     │
│     ├── Generate JSON Reports                                    │
│     └── Integrate with Jenkins Dashboard                         │
│                                                                  │
│  8. MONITORING                                                   │
│     ├── Create Postman Monitors                                  │
│     ├── Schedule Periodic Runs                                   │
│     └── Set Up Alerts (Email)                                    │
└──────────────────────────────────────────────────────────────┘
```

### 18.2 Quick Setup Checklist

**For a New Project:**
- [ ] Create a new Workspace for the team
- [ ] Create a Collection for the API
- [ ] Create folders for each resource (Users, Products, Orders)
- [ ] Create Environments (Dev, Staging, Production)
- [ ] Add base_url and api_key variables to environments
- [ ] Create requests for each endpoint
- [ ] Add Authorization (Bearer Token or API Key) at collection level
- [ ] Write pre-request scripts for dynamic data
- [ ] Write tests for status code, response time, and body validation
- [ ] Extract tokens/IDs and save to environment variables
- [ ] Chain requests using extracted variables
- [ ] Create a CSV data file for data-driven tests
- [ ] Run Collection Runner with the data file
- [ ] Export collection and environment for Newman
- [ ] Set up Jenkins pipeline for automated execution
- [ ] Configure HTML reporting in Jenkins

### 18.3 Common Patterns

**Pattern 1: CRUD Testing Flow**
```
POST /users     → Create user → Extract user_id
GET /users/{id} → Verify user exists
PUT /users/{id} → Update user → Verify changes
DELETE /users/{id} → Remove user → Verify deletion
GET /users/{id} → Verify 404
```

**Pattern 2: Authentication Flow**
```
POST /login     → Login → Extract token
GET /profile    → Use token → Verify access
POST /logout    → Logout
GET /profile    → Verify 401 (unauthorized)
```

**Pattern 3: E-Commerce Flow**
```
POST /login          → Extract token
GET /products        → Extract product_id
POST /cart           → Add product → Extract cart_id
GET /cart/{id}       → Verify cart
POST /checkout       → Checkout → Extract order_id
GET /orders/{id}     → Verify order
```

---

## 19. Mini-Project 1: Login API Flow

### 19.1 Project Overview

This mini-project demonstrates a complete authentication flow:
1. Login with credentials
2. Extract authentication token
3. Use token to access protected endpoints
4. Test token expiration and refresh

```
┌──────────────────────────────────────────────────────────────┐
│                    LOGIN API FLOW                               │
│                                                                  │
│  ┌─────────────┐    ┌─────────────┐    ┌──────────────────┐   │
│  │ 1. Login    │───→│ 2. Extract  │───→│ 3. Access        │   │
│  │    POST     │    │    Token    │    │    Protected     │   │
│  │    /login   │    │             │    │    Endpoints     │   │
│  └─────────────┘    └─────────────┘    └──────────────────┘   │
│         │                  │                      │             │
│         ↓                  ↓                      ↓             │
│  Request:             Test Script:            Headers:         │
│  {                    var jsonData =          Authorization:   │
│    "username": "...",  pm.response.json();   Bearer {{token}}│
│    "password": "..."  pm.environment.set(                     │
│  }                      "token",                             │
│                         jsonData.token);                      │
└──────────────────────────────────────────────────────────────┘
```

### 19.2 Collection Structure

```
📁 Login API Mini-Project
├── 📁 1. Authentication
│   ├── POST Login (Valid Credentials)
│   ├── POST Login (Invalid Credentials)
│   └── POST Login (Locked Account)
├── 📁 2. Token Operations
│   ├── POST Refresh Token
│   ├── POST Logout
│   └── GET Verify Token
├── 📁 3. Protected Endpoints
│   ├── GET User Profile
│   ├── PUT Update Profile
│   └── GET User Dashboard
└── 📁 4. Negative Tests
    ├── GET Profile (No Token)
    ├── GET Profile (Expired Token)
    └── GET Profile (Invalid Token)
```

### 19.3 Request 1: Login (Valid Credentials)

**Method**: POST  
**URL**: `{{base_url}}/auth/login`  
**Headers**:
- `Content-Type`: `application/json`

**Body (raw JSON)**:
```json
{
  "username": "{{username}}",
  "password": "{{password}}"
}
```

**Pre-request Script**:
```javascript
// Log login attempt
console.log("Attempting login for user: " + pm.environment.get("username"));
```

**Test Script**:
```javascript
// Test 1: Status code is 200
pm.test("Login successful - Status 200", function () {
    pm.response.to.have.status(200);
});

// Test 2: Response time is acceptable
pm.test("Response time is less than 2000ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(2000);
});

// Test 3: Response contains token
pm.test("Response contains auth token", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("token");
    pm.expect(jsonData.token).to.not.be.empty;
});

// Test 4: Response contains user data
pm.test("Response contains user data", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("user");
    pm.expect(jsonData.user).to.have.property("id");
    pm.expect(jsonData.user).to.have.property("username");
});

// Test 5: Token is valid JWT format
pm.test("Token is valid JWT format", function () {
    var jsonData = pm.response.json();
    var tokenParts = jsonData.token.split('.');
    pm.expect(tokenParts).to.have.lengthOf(3);
});

// Extract and save token to environment
var jsonData = pm.response.json();
var authToken = jsonData.token;
var refreshToken = jsonData.refresh_token;
var userId = jsonData.user.id;

pm.environment.set("auth_token", authToken);
pm.environment.set("refresh_token", refreshToken);
pm.environment.set("user_id", userId);

console.log("Login successful! Token saved to environment.");
console.log("User ID: " + userId);
```

### 19.4 Request 2: Login (Invalid Credentials)

**Method**: POST  
**URL**: `{{base_url}}/auth/login`  
**Body**:
```json
{
  "username": "invalid_user",
  "password": "wrong_password"
}
```

**Test Script**:
```javascript
// Test 1: Status code is 401
pm.test("Invalid credentials returns 401", function () {
    pm.response.to.have.status(401);
});

// Test 2: Error message is present
pm.test("Error message indicates invalid credentials", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("error");
    pm.expect(jsonData.error).to.include("invalid");
});

// Test 3: No token is returned
pm.test("No token returned for invalid credentials", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.not.have.property("token");
});
```

### 19.5 Request 3: Get User Profile (Protected)

**Method**: GET  
**URL**: `{{base_url}}/users/profile`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`
- `Content-Type`: `application/json`

**Test Script**:
```javascript
// Test 1: Status code is 200
pm.test("Profile access successful - Status 200", function () {
    pm.response.to.have.status(200);
});

// Test 2: Response contains user profile
pm.test("Response contains profile data", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("id");
    pm.expect(jsonData).to.have.property("username");
    pm.expect(jsonData).to.have.property("email");
});

// Test 3: User ID matches logged in user
pm.test("Profile belongs to logged in user", function () {
    var jsonData = pm.response.json();
    var expectedUserId = pm.environment.get("user_id");
    pm.expect(jsonData.id.toString()).to.eql(expectedUserId);
});
```

### 19.6 Request 4: Logout

**Method**: POST  
**URL**: `{{base_url}}/auth/logout`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`

**Test Script**:
```javascript
// Test 1: Status code is 200
pm.test("Logout successful - Status 200", function () {
    pm.response.to.have.status(200);
});

// Test 2: Clear tokens from environment
pm.environment.unset("auth_token");
pm.environment.unset("refresh_token");
pm.environment.unset("user_id");

console.log("Tokens cleared from environment.");
```

### 19.7 Data-Driven Test for Login

**`login_test_data.csv`:**
```csv
username,password,expected_status,test_case
admin,admin123,200,valid_admin
user1,user1pass,200,valid_user
invalid,wrongpass,401,invalid_credentials
locked,lockedpass,403,locked_account
empty,empty,400,empty_username
```

**Test Script (in Login request):**
```javascript
var expectedStatus = pm.iterationData.get("expected_status");
var testCase = pm.iterationData.get("test_case");

pm.test("[" + testCase + "] Status is " + expectedStatus, function () {
    pm.response.to.have.status(parseInt(expectedStatus));
});

// Only extract token for successful logins
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("auth_token", jsonData.token);
    pm.environment.set("user_id", jsonData.user.id);
}
```

---

## 20. Mini-Project 2: E-Commerce Add to Cart

### 20.1 Project Overview

This mini-project demonstrates an e-commerce flow:
1. Login and get token
2. Browse products and select one
3. Add product to cart
4. View cart
5. Proceed to checkout

```
┌──────────────────────────────────────────────────────────────┐
│                    E-COMMERCE FLOW                              │
│                                                                  │
│  1. Login      → Get token                                     │
│  2. Get Products → Extract product_id                           │
│  3. Add to Cart → POST cart with product_id and token           │
│  4. View Cart  → GET cart with cart_id                          │
│  5. Checkout   → POST checkout with cart_id                     │
│  6. Verify Order → GET order with order_id                      │
└──────────────────────────────────────────────────────────────┘
```

### 20.2 Collection Structure

```
📁 E-Commerce API Mini-Project
├── 📁 1. Authentication
│   ├── POST Login
│   └── POST Refresh Token
├── 📁 2. Product Catalog
│   ├── GET All Products
│   ├── GET Product by ID
│   └── GET Product Search
├── 📁 3. Cart Operations
│   ├── POST Create Cart
│   ├── POST Add to Cart
│   ├── GET View Cart
│   └── PUT Update Cart Quantity
├── 📁 4. Checkout
│   ├── POST Checkout
│   ├── GET Order Confirmation
│   └── GET Order Status
└── 📁 5. Cleanup
    ├── DELETE Remove from Cart
    └── DELETE Cancel Order
```

### 20.3 Request 1: Login

**Method**: POST  
**URL**: `{{base_url}}/auth/login`  
**Body**:
```json
{
  "username": "{{username}}",
  "password": "{{password}}"
}
```

**Test Script**:
```javascript
pm.test("Login successful", function () {
    pm.response.to.have.status(200);
});

var jsonData = pm.response.json();
pm.environment.set("auth_token", jsonData.token);
pm.environment.set("user_id", jsonData.user.id);
```

### 20.4 Request 2: Get All Products

**Method**: GET  
**URL**: `{{base_url}}/products`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`

**Test Script**:
```javascript
pm.test("Products retrieved successfully", function () {
    pm.response.to.have.status(200);
});

var jsonData = pm.response.json();

pm.test("Response is an array of products", function () {
    pm.expect(jsonData).to.be.an('array');
    pm.expect(jsonData.length).to.be.above(0);
});

// Extract first product ID for testing
var firstProduct = jsonData[0];
pm.environment.set("product_id", firstProduct.id);
pm.environment.set("product_price", firstProduct.price);

console.log("Selected Product ID: " + firstProduct.id);
console.log("Product Price: " + firstProduct.price);
```

### 20.5 Request 3: Create Cart

**Method**: POST  
**URL**: `{{base_url}}/carts`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`
- `Content-Type`: `application/json`

**Body**:
```json
{
  "user_id": {{user_id}}
}
```

**Test Script**:
```javascript
pm.test("Cart created successfully", function () {
    pm.response.to.have.status(201);
});

var jsonData = pm.response.json();
pm.environment.set("cart_id", jsonData.cart_id);

console.log("Cart ID: " + jsonData.cart_id);
```

### 20.6 Request 4: Add to Cart

**Method**: POST  
**URL**: `{{base_url}}/carts/{{cart_id}}/items`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`
- `Content-Type`: `application/json`

**Body**:
```json
{
  "product_id": {{product_id}},
  "quantity": 2
}
```

**Pre-request Script**:
```javascript
// Verify required variables exist
var cartId = pm.environment.get("cart_id");
var productId = pm.environment.get("product_id");

if (!cartId || !productId) {
    console.error("Missing cart_id or product_id!");
    console.error("cartId: " + cartId);
    console.error("productId: " + productId);
}
```

**Test Script**:
```javascript
pm.test("Item added to cart successfully", function () {
    pm.response.to.have.status(200);
});

var jsonData = pm.response.json();

pm.test("Response contains cart item", function () {
    pm.expect(jsonData).to.have.property("item_id");
    pm.expect(jsonData).to.have.property("cart_id");
    pm.expect(jsonData).to.have.property("product_id");
    pm.expect(jsonData).to.have.property("quantity");
});

pm.test("Cart ID matches", function () {
    pm.expect(jsonData.cart_id.toString()).to.eql(pm.environment.get("cart_id"));
});

pm.test("Product ID matches", function () {
    pm.expect(jsonData.product_id.toString()).to.eql(pm.environment.get("product_id"));
});

pm.test("Quantity is correct", function () {
    pm.expect(jsonData.quantity).to.eql(2);
});

pm.environment.set("cart_item_id", jsonData.item_id);
console.log("Cart Item ID: " + jsonData.item_id);
```

### 20.7 Request 5: View Cart

**Method**: GET  
**URL**: `{{base_url}}/carts/{{cart_id}}`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`

**Test Script**:
```javascript
pm.test("Cart retrieved successfully", function () {
    pm.response.to.have.status(200);
});

var jsonData = pm.response.json();

pm.test("Cart contains items", function () {
    pm.expect(jsonData).to.have.property("items");
    pm.expect(jsonData.items).to.be.an('array');
    pm.expect(jsonData.items.length).to.be.above(0);
});

pm.test("Cart contains selected product", function () {
    var productIds = jsonData.items.map(function(item) {
        return item.product_id.toString();
    });
    pm.expect(productIds).to.include(pm.environment.get("product_id"));
});

pm.test("Cart has total amount", function () {
    pm.expect(jsonData).to.have.property("total_amount");
    pm.expect(jsonData.total_amount).to.be.above(0);
});

console.log("Cart Total: " + jsonData.total_amount);
console.log("Cart Items Count: " + jsonData.items.length);
```

### 20.8 Request 6: Checkout

**Method**: POST  
**URL**: `{{base_url}}/checkout`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`
- `Content-Type`: `application/json`

**Body**:
```json
{
  "cart_id": {{cart_id}},
  "shipping_address": {
    "street": "123 Main St",
    "city": "New York",
    "zipcode": "10001",
    "country": "USA"
  },
  "payment_method": "credit_card"
}
```

**Test Script**:
```javascript
pm.test("Checkout successful", function () {
    pm.response.to.have.status(201);
});

var jsonData = pm.response.json();

pm.test("Order created", function () {
    pm.expect(jsonData).to.have.property("order_id");
    pm.expect(jsonData).to.have.property("order_status");
});

pm.test("Order status is pending", function () {
    pm.expect(jsonData.order_status).to.eql("pending");
});

pm.test("Order total is correct", function () {
    pm.expect(jsonData).to.have.property("total_amount");
    pm.expect(jsonData.total_amount).to.be.above(0);
});

pm.environment.set("order_id", jsonData.order_id);
console.log("Order ID: " + jsonData.order_id);
console.log("Order Status: " + jsonData.order_status);
```

### 20.9 Request 7: Verify Order

**Method**: GET  
**URL**: `{{base_url}}/orders/{{order_id}}`  
**Headers**:
- `Authorization`: `Bearer {{auth_token}}`

**Test Script**:
```javascript
pm.test("Order retrieved successfully", function () {
    pm.response.to.have.status(200);
});

var jsonData = pm.response.json();

pm.test("Order ID matches", function () {
    pm.expect(jsonData.order_id.toString()).to.eql(pm.environment.get("order_id"));
});

pm.test("Order contains cart items", function () {
    pm.expect(jsonData).to.have.property("items");
    pm.expect(jsonData.items).to.be.an('array');
});

pm.test("Order has shipping address", function () {
    pm.expect(jsonData).to.have.property("shipping_address");
    pm.expect(jsonData.shipping_address).to.have.property("city");
});

console.log("Order Verification Complete!");
console.log("Order Total: " + jsonData.total_amount);
console.log("Items Count: " + jsonData.items.length);
```

### 20.10 Data-Driven Test for Add to Cart

**`cart_test_data.csv`:**
```csv
product_id,quantity,expected_status
1,1,200
2,5,200
999,1,404
1,0,400
1,100,400
```

**Test Script (in Add to Cart request):**
```javascript
var expectedStatus = pm.iterationData.get("expected_status");

pm.test("Add to Cart - Status " + expectedStatus, function () {
    pm.response.to.have.status(parseInt(expectedStatus));
});

if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("cart_item_id", jsonData.item_id);
}
```

---

## 21. Monitoring and Mocking

### 21.1 Postman Monitors

Monitors allow you to run collections on a schedule and get notified of failures.

**Creating a Monitor:**
1. Select a collection in the sidebar
2. Click **...** (more actions) → **Monitor Collection**
3. Name: `Production Health Check`
4. Schedule: Every hour / Every day / Custom cron
5. Region: Select closest (US East, US West, EU, Asia)
6. Add email recipients for alerts
7. Click **Create**

**Monitor Configuration Options:**
- **Run Frequency**: Every 5 minutes to weekly
- **Region**: Choose where requests originate
- **Environment**: Select environment to use
- **Retry on Failure**: Run again if a request fails
- **Delay Between Requests**: Pause between requests
- **Follow Redirects**: Allow HTTP redirects
- **Request Timeout**: Maximum wait time per request

**Viewing Monitor Results:**
- Go to **Monitors** in the sidebar
- Click on monitor name
- View:
  - Pass/Fail rate over time
  - Average response time
  - Individual run results
  - Request-level details
  - Test failure messages

### 21.2 Postman Mock Servers

Mock servers simulate API endpoints, allowing you to:
- Develop frontends before backend is ready
- Test error scenarios
- Simulate third-party APIs

**Creating a Mock Server:**
1. Select a collection
2. Click **...** → **Mock Collection**
3. Or go to **New** → **Mock Server**
4. Name: `E-Commerce Mock`
5. Select collection to mock
6. Configure:
   - Make mock server private (requires API key)
   - Save URL to environment variable
7. Click **Create Mock Server**

**Adding Mock Responses:**
1. Create a request in the collection
2. Click **Save Response** → **Save as example**
3. Name the example: `Success Response`
4. Edit the example body, headers, and status code
5. Save

**Using the Mock Server:**
```
Mock URL: https://your-mock-id.mock.pstmn.io

Example: GET https://your-mock-id.mock.pstmn.io/users
Returns the saved example response
```

**Mocking Error Responses:**
1. Save another example for the same request
2. Name it: `Error Response`
3. Set status: `500`
4. Set body: `{ "error": "Internal Server Error" }`
5. Save

**Mocking with Query Match:**
Postman returns the example that matches the request path and query parameters.

---

## 22. API Documentation

### 22.1 Auto-Generating Documentation

Postman automatically generates documentation for your collections.

**Viewing Documentation:**
1. Select a collection
2. Click **...** → **View Documentation**
3. Or click the **Documentation** icon (📄) in the right sidebar

**Documentation Features:**
- Auto-generated from request details
- Shows URL, method, headers, body, and examples
- Includes code snippets in multiple languages (cURL, JavaScript, Python, etc.)
- Shows test scripts and pre-request scripts

### 22.2 Enhancing Documentation

**Add Descriptions:**
1. Click on a collection, folder, or request
2. Click **Add a description** or **Edit description**
3. Use Markdown formatting:
```markdown
# User API

This API manages user accounts.

## Endpoints
- GET /users - List all users
- POST /users - Create a new user

## Authentication
All endpoints require Bearer token authentication.
```

**Add Examples:**
1. Send a request
2. Click **Save Response** → **Save as example**
3. Name it: `Success Response`
4. The example appears in documentation

### 22.3 Publishing Documentation

**Public Documentation:**
1. Go to the collection
2. Click **...** → **Publish Docs**
3. Configure:
   - Custom domain (optional)
   - Branding (logo, colors)
   - Default environment
4. Click **Publish**
5. Share the public URL

**Private Documentation:**
- Team members can view via workspace
- Requires authentication

### 22.4 Documentation Code Snippets

Postman generates code snippets in 20+ languages:
- cURL
- JavaScript (Fetch, Axios, jQuery)
- Python (Requests, http.client)
- Java (OkHttp, Unirest)
- C# (RestSharp, HttpClient)
- PHP (cURL, HTTP_Request2)
- Go (Native)
- Ruby (Net::HTTP)

**Example: cURL Snippet**
```bash
curl --location 'https://api.example.com/users' \
--header 'Authorization: Bearer {{auth_token}}' \
--header 'Content-Type: application/json'
```

---

## 23. Postman Flows

### 23.1 What are Postman Flows?

Postman Flows is a visual tool for building API workflows. It allows you to chain requests, add conditional logic, and process data visually without writing code.

**Use Cases:**
- Visual workflow design
- No-code/low-code API automation
- Data transformation pipelines
- Event-driven workflows

### 23.2 Creating a Flow

1. Click **New** → **Flow**
2. Or go to **Flows** in the left sidebar
3. Drag and drop blocks:
   - **Send Request**: Execute a Postman request
   - **If**: Conditional logic
   - **For Each**: Loop through data
   - **Select**: Extract data from response
   - **Assign**: Set variables
   - **Delay**: Pause execution
   - **Function**: Custom JavaScript function
4. Connect blocks with connectors
5. Configure each block by clicking on it
6. Click **Run** to execute the flow

### 23.3 Flow Example: E-Commerce Flow

```
┌──────────────────────────────────────────────────────────────┐
│                    POSTMAN FLOW EXAMPLE                         │
│                                                                  │
│  [Start] ───→ [Send Request: Login] ───→ [Select: token]       │
│                 │                         │                      │
│                 ↓                         ↓                      │
│           [Test: 200 OK]           [Assign: auth_token]        │
│                 │                         │                      │
│                 └────────→ [If: success] ─┘                      │
│                              │                                   │
│                              ↓                                   │
│                    [Send Request: Get Products]                 │
│                              │                                   │
│                              ↓                                   │
│                    [For Each: product]                          │
│                              │                                   │
│                              ↓                                   │
│                    [If: price < 100]                            │
│                              │                                   │
│                              ↓                                   │
│                    [Send Request: Add to Cart]                  │
│                              │                                   │
│                              ↓                                   │
│                    [Send Request: Checkout]                     │
│                              │                                   │
│                              ↓                                   │
│                           [End]                                  │
└──────────────────────────────────────────────────────────────┘
```

---

## 24. Interview FAQs

### 24.1 Postman Basics

**Q1: What is Postman and what is it used for?**  
**A:** Postman is an API platform for building and using APIs. It is used for API testing, documentation, monitoring, and collaboration. It supports REST, SOAP, GraphQL, and other API protocols.

**Q2: What is the difference between Postman and Newman?**  
**A:** Postman is the GUI application for building and testing APIs. Newman is the command-line tool for running Postman collections, enabling CI/CD integration.

**Q3: What are the different HTTP methods supported in Postman?**  
**A:** GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE, CONNECT, and custom methods.

**Q4: What is the difference between the Authorization tab and the Headers tab?**  
**A:** The Authorization tab provides a user-friendly interface for configuring various auth types (Basic, Bearer, OAuth, etc.) and automatically adds the appropriate header. The Headers tab allows manual addition of any header.

**Q5: What are the different tabs available in a Postman request?**  
**A:** Params, Authorization, Headers, Body, Pre-request Script, Tests, and Settings.

### 24.2 Variables and Environments

**Q6: What are the different types of variables in Postman and their scope?**  
**A:** Global (all workspaces), Collection (specific collection), Environment (active environment), Data (from iteration data), and Local (single request). Scope precedence: Data > Local > Environment > Collection > Global.

**Q7: How do you handle environment-specific configurations in Postman?**  
**A:** By creating separate environments (Development, Staging, Production) with variables like base_url, api_key, and switching between them using the environment dropdown.

**Q8: What are dynamic variables in Postman?**  
**A:** Built-in variables that generate random data: `{{$guid}}`, `{{$timestamp}}`, `{{$randomInt}}`, `{{$randomFullName}}`, `{{$randomEmail}}`, etc.

**Q9: How do you extract a value from a response and use it in another request?**  
**A:** In the first request's test script, parse the response (`pm.response.json()`), extract the value, and save it to an environment variable (`pm.environment.set("var", value)`). Then use `{{var}}` in the next request.

**Q10: How do you handle token expiration in Postman?**  
**A:** Store the token in an environment variable. Write a test script to check for expiration errors (401/403). If expired, call a refresh token endpoint or re-login to update the token variable.

### 24.3 Scripting and Testing

**Q11: What is the difference between Pre-request Script and Test Script?**  
**A:** Pre-request scripts run BEFORE the request is sent, used for setup. Test scripts run AFTER the response is received, used for validation and extraction.

**Q12: What assertion library does Postman use for tests?**  
**A:** Postman uses Chai BDD assertion library with `pm.expect()` syntax.

**Q13: How do you write a test to verify JSON response structure?**  
**A:**
```javascript
pm.test("Response has correct structure", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("id");
    pm.expect(jsonData).to.have.property("name");
    pm.expect(jsonData.id).to.be.a('number');
    pm.expect(jsonData.name).to.be.a('string');
});
```

**Q14: How do you send a request from within a test script?**  
**A:** Using `pm.sendRequest()`:
```javascript
pm.sendRequest("https://api.example.com/check", function (err, response) {
    pm.test("Secondary request successful", function () {
        pm.expect(response.code).to.eql(200);
    });
});
```

**Q15: What is `pm.info.iteration` used for?**  
**A:** It returns the current iteration number when running collections with multiple iterations, useful for data-driven testing.

### 24.4 Collections and Runner

**Q16: What is a Collection in Postman?**  
**A:** A collection is a group of API requests organized into folders. It allows sharing, running multiple requests together, and adding documentation.

**Q17: What is the Collection Runner?**  
**A:** The Collection Runner executes all requests in a collection or folder sequentially with options for iterations, delays, and data files.

**Q18: How do you run a collection with different data sets?**  
**A:** Use the Collection Runner with a CSV or JSON data file, or use Newman with the `-d` flag.

**Q19: How do you export and import a collection?**  
**A:** Right-click collection → Export → Choose format (Collection v2.1 recommended). To import, click Import → choose file or paste URL.

**Q20: What is the difference between running a collection and running a folder?**  
**A:** Running a collection executes all requests in the collection. Running a folder executes only the requests within that folder.

### 24.5 Newman and CI/CD

**Q21: How do you install Newman?**  
**A:** `npm install -g newman` (requires Node.js).

**Q22: How do you generate an HTML report using Newman?**  
**A:** Install `newman-reporter-htmlextra` and run: `newman run collection.json -r htmlextra --reporter-htmlextra-export report.html`.

**Q23: How do you integrate Postman tests with Jenkins?**  
**A:** Install Node.js and Newman on Jenkins. Create a Jenkins job that pulls the collection repository, runs Newman with the collection, and publishes HTML reports.

**Q24: What is the `--bail` flag in Newman?**  
**A:** The `--bail` flag stops the collection run immediately if a test fails or a request errors.

**Q25: How do you run collections with environment variables in Newman?**  
**A:** Use the `-e` flag: `newman run collection.json -e environment.json`.

### 24.6 Advanced Topics

**Q26: How do you handle SOAP requests in Postman?**  
**A:** Set method to POST, URL to the SOAP endpoint, set Content-Type header to `text/xml`, add SOAPAction header, and send XML body in the raw body tab.

**Q27: What is the Postman Sandbox?**  
**A:** The Sandbox is the JavaScript execution environment for Postman scripts. It provides access to `pm` object, `console`, and libraries like `lodash`, `CryptoJS`, `moment`.

**Q28: How do you debug scripts in Postman?**  
**A:** Use `console.log()` in scripts and view the Postman Console (Ctrl+Alt+C / Cmd+Option+C).

**Q29: What are Postman Monitors?**  
**A:** Monitors are scheduled cloud runs of collections that check API health and send alerts on failures.

**Q30: How do you mock an API in Postman?**  
**A:** Create a mock server from a collection. Save example responses for requests. The mock server returns these examples based on the request path.

**Q31: What is the difference between `pm.environment.set()` and `pm.variables.set()`?**  
**A:** `pm.environment.set()` persists in the environment. `pm.variables.set()` is local to the request and not persisted.

**Q32: How do you handle file uploads in Postman?**  
**A:** In the Body tab, select `form-data`, choose `File` from the dropdown, and select the file to upload.

**Q33: What is the purpose of the `pm.sendRequest()` function?**  
**A:** It allows sending additional HTTP requests from within pre-request or test scripts, useful for setup or cleanup.

**Q34: How do you handle rate limiting in Postman tests?**  
**A:** Check for 429 status code, add delays between requests using Collection Runner delay or `setTimeout()` in scripts.

**Q35: What is the difference between `pm.expect()` and `pm.test()`?**  
**A:** `pm.test()` creates a named test block that appears in results. `pm.expect()` is the assertion within the test block. You must use `pm.expect()` inside `pm.test()` for assertions to be tracked.

---

## 25. Quick Reference

### 25.1 Common Postman Shortcuts

| Action | Windows/Linux | Mac |
|--------|---------------|-----|
| Send Request | Ctrl+Enter | Cmd+Enter |
| Save Request | Ctrl+S | Cmd+S |
| New Request | Ctrl+T | Cmd+T |
| Close Tab | Ctrl+W | Cmd+W |
| Find | Ctrl+F | Cmd+F |
| Console | Ctrl+Alt+C | Cmd+Option+C |
| Import | Ctrl+O | Cmd+O |
| Environment Quick Look | Ctrl+E | Cmd+E |

### 25.2 Common Status Codes

| Code | Meaning | Test Script |
|------|---------|-------------|
| 200 | OK | `pm.response.to.have.status(200);` |
| 201 | Created | `pm.response.to.have.status(201);` |
| 204 | No Content | `pm.response.to.have.status(204);` |
| 400 | Bad Request | `pm.response.to.have.status(400);` |
| 401 | Unauthorized | `pm.response.to.have.status(401);` |
| 403 | Forbidden | `pm.response.to.have.status(403);` |
| 404 | Not Found | `pm.response.to.have.status(404);` |
| 500 | Server Error | `pm.response.to.have.status(500);` |
| 502 | Bad Gateway | `pm.response.to.have.status(502);` |
| 503 | Service Unavailable | `pm.response.to.have.status(503);` |

### 25.3 Common `pm` Methods

```javascript
// Request/Response
pm.request
pm.response.code
pm.response.status
pm.response.headers
pm.response.responseTime
pm.response.json()
pm.response.text()

// Variables
pm.globals.get/set/unset
pm.environment.get/set/unset
pm.collectionVariables.get/set/unset
pm.variables.get/set
pm.iterationData.get

// Info
pm.info.iteration
pm.info.iterationCount
pm.info.requestName
pm.info.requestId

// Testing
pm.test("name", function() { })
pm.expect(value)

// Utilities
pm.sendRequest(url, callback)
pm.visualizer.set(template, data)
console.log(msg)
```

### 25.4 Common Chai Assertions

```javascript
pm.expect(value).to.equal(expected)
pm.expect(value).to.eql(expected)
pm.expect(value).to.be.a('string')
pm.expect(value).to.be.an('array')
pm.expect(value).to.have.property('key')
pm.expect(value).to.include('substring')
pm.expect(value).to.have.lengthOf(5)
pm.expect(value).to.be.above(10)
pm.expect(value).to.be.below(100)
pm.expect(value).to.match(/regex/)
pm.expect(value).to.be.oneOf([1, 2, 3])
```

### 25.5 Newman Command Reference

```bash
# Basic run
newman run collection.json

# With environment
newman run collection.json -e environment.json

# With data file
newman run collection.json -d data.csv

# With iterations
newman run collection.json -n 5

# With delay
newman run collection.json --delay-request 1000

# With timeout
newman run collection.json --timeout-request 30000

# With reporters
newman run collection.json -r cli,json,htmlextra

# Export reports
newman run collection.json -r html --reporter-html-export report.html
newman run collection.json -r json --reporter-json-export report.json

# With globals
newman run collection.json -g globals.json

# Stop on error
newman run collection.json --bail

# Suppress exit code
newman run collection.json --suppress-exit-code

# Verbose
newman run collection.json --verbose
```

---

## 26. Key Takeaways

1. **Postman is an API Platform** — Not just a REST client, but a complete solution for API development, testing, documentation, and collaboration.

2. **Collections Organize Your Work** — Group related requests into collections and folders for maintainability.

3. **Variables Enable Reusability** — Use global, collection, environment, and data variables to make collections portable across different setups.

4. **Scripts Add Power** — Pre-request scripts prepare requests; test scripts validate responses. JavaScript runs in the Postman Sandbox.

5. **Environments Separate Configurations** — Create separate environments for Dev, Staging, and Production without duplicating requests.

6. **Chai Assertions Validate Responses** — Use `pm.test()` and `pm.expect()` with Chai BDD style for readable, maintainable tests.

7. **Response Extraction Enables Chaining** — Save values from one response into variables for use in subsequent requests.

8. **Data-Driven Testing Scales Coverage** — Use CSV/JSON files with the Collection Runner to test multiple scenarios with one script.

9. **Newman Enables CI/CD** — Run collections from the command line for automated testing in Jenkins, GitHub Actions, Azure DevOps.

10. **Jenkins Integration Automates Execution** — Set up pipelines to run API tests on every commit, with HTML reporting and email alerts.

11. **Monitors Watch Production** — Schedule collection runs in the cloud to check API health and receive alerts on failures.

12. **Mock Servers Speed Development** — Simulate APIs before they are built, enabling parallel frontend and backend development.

13. **Documentation Drives Adoption** — Auto-generate and publish API documentation from collections.

14. **Authorization is Critical** — Test with Basic Auth, Bearer tokens, API Keys, OAuth 2.0, and custom schemes.

15. **SOAP is Supported** — Postman handles XML-based SOAP APIs with proper headers and body formatting.

16. **Debugging with Console** — Use `console.log()` and the Postman Console to troubleshoot request and script issues.

17. **Flows Provide Visual Automation** — Chain requests with conditional logic using Postman Flows for no-code workflows.

18. **Workspaces Enable Collaboration** — Share collections with team members using personal, team, or public workspaces.

19. **Scope Precedence Matters** — Data > Local > Environment > Collection > Global. Inner scopes override outer scopes.

20. **Practice with Mini-Projects** — Build real-world flows like Login + Token + Protected Endpoints and E-Commerce Cart + Checkout to master Postman.

---

**Happy API Testing!**

*Master Postman, and you master the art of API Quality Assurance.*

---

**Appendix: Additional Resources**

- **Postman Learning Center**: https://learning.postman.com
- **Postman API Documentation**: https://www.postman.com/api-platform/api-documentation/
- **Newman GitHub**: https://github.com/postmanlabs/newman
- **Postman Community Forum**: https://community.postman.com
- **Chai Assertion Docs**: https://www.chaijs.com/api/bdd/

---

*End of 04_Postman_Tutorial.md*
