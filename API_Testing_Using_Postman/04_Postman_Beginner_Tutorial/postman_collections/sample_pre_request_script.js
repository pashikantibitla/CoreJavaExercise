# Pre-request Script Examples

# This file contains sample pre-request scripts for Postman.
# Copy these scripts into the Pre-request Script tab of your Postman requests.

# ─────────────────────────────────────────────────────────────────────────────
# Example 1: Set Timestamp and Order ID
# ─────────────────────────────────────────────────────────────────────────────

# Generate current timestamp in ISO format
var timestamp = new Date().toISOString();
pm.environment.set("request_timestamp", timestamp);

# Generate a random order ID
var orderId = "ORD-" + Math.floor(Math.random() * 100000);
pm.environment.set("order_id", orderId);

console.log("Timestamp: " + timestamp);
console.log("Order ID: " + orderId);

# ─────────────────────────────────────────────────────────────────────────────
# Example 2: Prepare Login Credentials
# ─────────────────────────────────────────────────────────────────────────────

# Get credentials from environment or use defaults
var username = pm.environment.get("username") || "testuser";
var password = pm.environment.get("password") || "Test@123";

# Base64 encode credentials for Basic Auth
var encodedCredentials = btoa(username + ":" + password);
pm.environment.set("encoded_credentials", encodedCredentials);

console.log("Username: " + username);
console.log("Encoded Credentials: " + encodedCredentials);

# In the Headers tab, add: Authorization: Basic {{encoded_credentials}}

# ─────────────────────────────────────────────────────────────────────────────
# Example 3: Compute MD5 Hash
# ─────────────────────────────────────────────────────────────────────────────

# Compute MD5 hash of a message
var CryptoJS = require('crypto-js');
var message = "secret-message";
var hash = CryptoJS.MD5(message).toString();

pm.environment.set("message_hash", hash);
console.log("MD5 Hash: " + hash);

# ─────────────────────────────────────────────────────────────────────────────
# Example 4: Conditional Setup
# ─────────────────────────────────────────────────────────────────────────────

# Only run setup if a flag is set
var runSetup = pm.environment.get("run_setup");

if (runSetup === "true") {
    pm.environment.set("setup_complete", "yes");
    console.log("Setup completed successfully.");
} else {
    console.log("Skipping setup (run_setup is not 'true').");
}

# ─────────────────────────────────────────────────────────────────────────────
# Example 5: Set Dynamic Request Header
# ─────────────────────────────────────────────────────────────────────────────

# Generate a unique request ID
var requestId = "req-" + Math.random().toString(36).substring(2, 15);
pm.environment.set("request_id", requestId);

# Set a custom header (use {{request_id}} in the Headers tab)
console.log("Request ID set: " + requestId);

# ─────────────────────────────────────────────────────────────────────────────
# Example 6: Prepare JSON Body with Dynamic Data
# ─────────────────────────────────────────────────────────────────────────────

# Generate dynamic user data
var randomName = "User-" + Math.floor(Math.random() * 1000);
var randomEmail = randomName.toLowerCase() + "@example.com";

pm.environment.set("dynamic_name", randomName);
pm.environment.set("dynamic_email", randomEmail);

# In the Body tab, use:
# {
#   "name": "{{dynamic_name}}",
#   "email": "{{dynamic_email}}"
# }

# ─────────────────────────────────────────────────────────────────────────────
# Example 7: Verify Required Variables Exist
# ─────────────────────────────────────────────────────────────────────────────

# Check if required variables exist before sending request
var cartId = pm.environment.get("cart_id");
var productId = pm.environment.get("product_id");

if (!cartId || !productId) {
    console.error("Missing required variables!");
    console.error("cart_id: " + cartId);
    console.error("product_id: " + productId);
} else {
    console.log("All required variables present.");
}

# ─────────────────────────────────────────────────────────────────────────────
# Example 8: Set Environment-Specific Timeout
# ─────────────────────────────────────────────────────────────────────────────

var env = pm.environment.get("env_name");

if (env === "production") {
    pm.environment.set("timeout", 5000);
    console.log("Production timeout set: 5000ms");
} else {
    pm.environment.set("timeout", 1000);
    console.log("Development timeout set: 1000ms");
}

# ─────────────────────────────────────────────────────────────────────────────
# Example 9: HMAC Signature Generation
# ─────────────────────────────────────────────────────────────────────────────

var CryptoJS = require('crypto-js');

# HMAC SHA256 signature
var secret = pm.environment.get("api_secret") || "default-secret";
var payload = pm.environment.get("payload") || "{}";
var signature = CryptoJS.HmacSHA256(payload, secret).toString();

pm.environment.set("hmac_signature", signature);
console.log("HMAC Signature: " + signature);

# ─────────────────────────────────────────────────────────────────────────────
# Example 10: URL Encoding
# ─────────────────────────────────────────────────────────────────────────────

var queryValue = "Hello World!";
var encodedValue = encodeURIComponent(queryValue);

pm.environment.set("encoded_query", encodedValue);
console.log("Encoded: " + encodedValue);

# ─────────────────────────────────────────────────────────────────────────────
# Example 11: Parse and Set JWT Expiration
# ─────────────────────────────────────────────────────────────────────────────

# Simple JWT payload parsing (for illustration)
var jwt = pm.environment.get("auth_token");
if (jwt) {
    var parts = jwt.split('.');
    if (parts.length === 3) {
        var payload = JSON.parse(atob(parts[1]));
        var expDate = new Date(payload.exp * 1000);
        pm.environment.set("token_expiry", expDate.toISOString());
        console.log("Token expires: " + expDate.toISOString());
    }
}

# ─────────────────────────────────────────────────────────────────────────────
# Example 12: Set Multiple Variables from a List
# ─────────────────────────────────────────────────────────────────────────────

var testUsers = [
    { "username": "admin", "role": "administrator" },
    { "username": "user1", "role": "user" },
    { "username": "user2", "role": "user" }
];

# Pick a random user
var randomIndex = Math.floor(Math.random() * testUsers.length);
var selectedUser = testUsers[randomIndex];

pm.environment.set("test_username", selectedUser.username);
pm.environment.set("test_role", selectedUser.role);

console.log("Selected test user: " + selectedUser.username + " (" + selectedUser.role + ")");

# ─────────────────────────────────────────────────────────────────────────────
# End of Pre-request Script Examples
# ─────────────────────────────────────────────────────────────────────────────
