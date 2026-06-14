# Test Script Examples

# This file contains sample test scripts for Postman.
# Copy these scripts into the Tests tab of your Postman requests.

# ─────────────────────────────────────────────────────────────────────────────
# Example 1: Basic Status Code Test
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 2: Status Code is One of Expected Values
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Status code is 200 or 201", function () {
    pm.expect(pm.response.code).to.be.oneOf([200, 201]);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 3: Response Time Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response time is less than 500ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(500);
});

pm.test("Response time is less than 1000ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(1000);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 4: JSON Body Structure Validation
# ─────────────────────────────────────────────────────────────────────────────

var jsonData = pm.response.json();

pm.test("Response has correct structure", function () {
    pm.expect(jsonData).to.have.property("id");
    pm.expect(jsonData).to.have.property("name");
    pm.expect(jsonData).to.have.property("email");
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 5: Data Value Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("User email is correct", function () {
    pm.expect(jsonData.email).to.eql("Sincere@april.biz");
});

pm.test("User ID is positive integer", function () {
    pm.expect(jsonData.id).to.be.above(0);
    pm.expect(jsonData.id).to.be.a('number');
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 6: Array Response Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response is an array with 10 items", function () {
    pm.expect(jsonData).to.be.an('array').that.has.lengthOf(10);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 7: Nested Object Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Address contains city and zipcode", function () {
    pm.expect(jsonData.address).to.have.property("city");
    pm.expect(jsonData.address).to.have.property("zipcode");
    pm.expect(jsonData.address.city).to.be.a('string');
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 8: Header Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Content-Type header is present", function () {
    pm.response.to.have.header("Content-Type");
});

pm.test("Content-Type is application/json", function () {
    pm.expect(pm.response.headers.get("Content-Type")).to.include("application/json");
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 9: Cookie Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Session cookie is present", function () {
    pm.expect(pm.cookies.has("session_id")).to.be.true;
});

# Get cookie value
var sessionCookie = pm.cookies.get("session_id");
console.log("Session ID: " + sessionCookie);

# ─────────────────────────────────────────────────────────────────────────────
# Example 10: Email Format Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Email format is valid", function () {
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    pm.expect(jsonData.email).to.match(emailPattern);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 11: Token Extraction and Environment Storage
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response contains auth token", function () {
    pm.expect(jsonData).to.have.property("token");
    pm.expect(jsonData.token).to.not.be.empty;
});

# Extract and save token
var authToken = jsonData.token;
var refreshToken = jsonData.refresh_token;
var userId = jsonData.user.id;

pm.environment.set("auth_token", authToken);
pm.environment.set("refresh_token", refreshToken);
pm.environment.set("user_id", userId);

console.log("Token saved to environment.");

# ─────────────────────────────────────────────────────────────────────────────
# Example 12: Conditional Tests Based on Status Code
# ─────────────────────────────────────────────────────────────────────────────

if (pm.response.code === 200) {
    pm.test("Success response validation", function () {
        pm.expect(jsonData).to.have.property("data");
    });
} else if (pm.response.code === 404) {
    pm.test("Not found error validation", function () {
        pm.expect(jsonData).to.have.property("error");
        pm.expect(jsonData.error).to.include("not found");
    });
} else {
    pm.test("Unexpected status code", function () {
        pm.expect.fail("Unexpected status: " + pm.response.code);
    });
}

# ─────────────────────────────────────────────────────────────────────────────
# Example 13: Data-Driven Test with Iteration Data
# ─────────────────────────────────────────────────────────────────────────────

var expectedStatus = pm.iterationData.get("expected_status");
var testCase = pm.iterationData.get("test_case");

pm.test("[" + testCase + "] Status is " + expectedStatus, function () {
    pm.response.to.have.status(parseInt(expectedStatus));
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 14: Response Time Ranges
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response time is within acceptable range", function () {
    pm.expect(pm.response.responseTime).to.be.within(100, 2000);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 15: String Inclusion Tests
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response contains expected message", function () {
    pm.expect(pm.response.text()).to.include("success");
});

pm.test("Response does not contain error", function () {
    pm.expect(pm.response.text()).to.not.include("error");
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 16: Schema Validation with tv4
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response matches expected schema", function () {
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

# ─────────────────────────────────────────────────────────────────────────────
# Example 17: Extract from Array and Save
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Response is an array with items", function () {
    pm.expect(jsonData).to.be.an('array');
    pm.expect(jsonData.length).to.be.above(0);
});

# Extract first item ID
var firstId = jsonData[0].id;
pm.environment.set("first_item_id", firstId);

# Extract all IDs
var allIds = jsonData.map(function(item) {
    return item.id;
});
pm.environment.set("all_item_ids", JSON.stringify(allIds));

# ─────────────────────────────────────────────────────────────────────────────
# Example 18: Date Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Created date is valid", function () {
    var createdDate = new Date(jsonData.created_at);
    pm.expect(createdDate.toString()).to.not.eql("Invalid Date");
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 19: Authorization Error Tests
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Unauthorized access returns 401", function () {
    pm.response.to.have.status(401);
});

pm.test("Forbidden access returns 403", function () {
    pm.response.to.have.status(403);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 20: Send Secondary Request from Test Script
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Primary request successful", function () {
    pm.response.to.have.status(200);
});

# Send a follow-up request
pm.sendRequest("https://postman-echo.com/get", function (err, response) {
    if (err) {
        console.log("Secondary request error: " + err);
    } else {
        pm.test("Secondary request successful", function () {
            pm.expect(response.code).to.eql(200);
        });
    }
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 21: JSONPath-like Extraction with lodash
# ─────────────────────────────────────────────────────────────────────────────

# Find specific user by name
var user = _.find(jsonData, { name: "Leanne Graham" });
if (user) {
    pm.environment.set("found_user_id", user.id);
    console.log("Found user ID: " + user.id);
}

# Filter by city
var usersInCity = _.filter(jsonData, function(u) {
    return u.address.city === "Gwenborough";
});
console.log("Users in Gwenborough: " + usersInCity.length);

# Map to extract emails
var emails = _.map(jsonData, "email");
pm.environment.set("all_emails", JSON.stringify(emails));

# ─────────────────────────────────────────────────────────────────────────────
# Example 22: Check for Empty or Null Values
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Name is not empty", function () {
    pm.expect(jsonData.name).to.not.be.empty;
    pm.expect(jsonData.name).to.not.be.null;
    pm.expect(jsonData.name).to.not.be.undefined;
});

pm.test("Description may be null or string", function () {
    if (jsonData.description !== null && jsonData.description !== undefined) {
        pm.expect(jsonData.description).to.be.a('string');
    }
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 23: Numeric Range Validation
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Price is within valid range", function () {
    pm.expect(jsonData.price).to.be.above(0);
    pm.expect(jsonData.price).to.be.below(10000);
});

pm.test("Rating is between 0 and 5", function () {
    pm.expect(jsonData.rating).to.be.within(0, 5);
});

# ─────────────────────────────────────────────────────────────────────────────
# Example 24: Collection of Assertions for Login Response
# ─────────────────────────────────────────────────────────────────────────────

pm.test("Login response is valid", function () {
    var data = pm.response.json();
    
    pm.expect(data).to.have.property("status");
    pm.expect(data.status).to.eql("success");
    
    pm.expect(data).to.have.property("data");
    pm.expect(data.data).to.have.property("token");
    pm.expect(data.data.token).to.be.a('string');
    pm.expect(data.data.token).to.have.lengthOf.above(10);
    
    pm.expect(data.data).to.have.property("user");
    pm.expect(data.data.user).to.have.property("id");
    pm.expect(data.data.user).to.have.property("username");
});

# ─────────────────────────────────────────────────────────────────────────────
# End of Test Script Examples
# ─────────────────────────────────────────────────────────────────────────────
