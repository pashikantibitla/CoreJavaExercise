# 06 — Real-World API Testing Projects

> **Goal:** Apply API testing concepts to complete, production-like projects using Postman, REST Assured, and Java.
> **Projects:** E-Commerce, User Management, Social Media, Banking
> **Topics:** Authentication, CRUD, Pagination, OAuth 2.0, Security, Data Flow, Memory Management

---

## Table of Contents

1. [Project 1: E-Commerce API Testing (Complete Flow)](#1-project-1-e-commerce-api-testing-complete-flow)
2. [Project 2: User Management API Testing](#2-project-2-user-management-api-testing)
3. [Project 3: Social Media API Testing](#3-project-3-social-media-api-testing)
4. [Project 4: Banking API Testing](#4-project-4-banking-api-testing)
5. [Data Flow and Memory Management in Projects](#5-data-flow-and-memory-management-in-projects)
6. [Step-by-Step Implementation Guide](#6-step-by-step-implementation-guide)
7. [JSON Data Structures for Each Project](#7-json-data-structures-for-each-project)
8. [Interview FAQs (15+ Questions)](#8-interview-faqs-15-questions)

---

## 1. Project 1: E-Commerce API Testing (Complete Flow)

### 1.1 Overview

An E-Commerce application follows a typical user journey:

```
Register → Login → Browse Products → Add to Cart → View Cart → Update Cart → Checkout → Pay → Order History
```

Each step requires API calls with proper headers, tokens, and JSON payloads.

---

### 1.2 User Registration

**API Endpoint:** `POST /api/v1/users/register`

**Request Headers:**
```
Content-Type: application/json
Accept: application/json
```

**Request Body:**
```json
{
  "firstName": "Rahul",
  "lastName": "Sharma",
  "email": "rahul.sharma@example.com",
  "password": "SecurePass123!",
  "phone": "+91-9876543210",
  "address": {
    "street": "42, MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "zipCode": "560001",
    "country": "India"
  }
}
```

**Response Body (201 Created):**
```json
{
  "userId": "usr_8f3a9b2c",
  "firstName": "Rahul",
  "lastName": "Sharma",
  "email": "rahul.sharma@example.com",
  "phone": "+91-9876543210",
  "address": {
    "street": "42, MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "zipCode": "560001",
    "country": "India"
  },
  "createdAt": "2026-06-14T10:30:00Z",
  "status": "ACTIVE"
}
```

**Test Cases:**
- TC-REG-01: Valid registration with complete data
- TC-REG-02: Duplicate email rejection (409 Conflict)
- TC-REG-03: Missing mandatory fields (400 Bad Request)
- TC-REG-04: Invalid email format validation
- TC-REG-05: Weak password rejection
- TC-REG-06: SQL injection attempt in name field
- TC-REG-07: XSS attempt in address field
- TC-REG-08: Phone number format validation

**Postman Test Script:**
```javascript
pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});

pm.test("Response contains userId", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("userId");
    pm.environment.set("userId", jsonData.userId);
});

pm.test("Email matches request", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.email).to.eql(pm.environment.get("email"));
});

pm.test("Response time is less than 2000ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(2000);
});
```

---

### 1.3 User Login (Token Extraction)

**API Endpoint:** `POST /api/v1/auth/login`

**Request Body:**
```json
{
  "email": "rahul.sharma@example.com",
  "password": "SecurePass123!"
}
```

**Response Body (200 OK):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600,
  "user": {
    "userId": "usr_8f3a9b2c",
    "email": "rahul.sharma@example.com",
    "role": "CUSTOMER"
  }
}
```

**Postman Test Script (Token Extraction):**
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Access token is present", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("accessToken");
    pm.environment.set("accessToken", jsonData.accessToken);
    pm.environment.set("refreshToken", jsonData.refreshToken);
});

pm.test("Token type is Bearer", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.tokenType).to.eql("Bearer");
});

pm.test("Token expires in 1 hour", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.expiresIn).to.eql(3600);
});
```

**Negative Test Cases:**
- TC-LOG-01: Invalid credentials (401 Unauthorized)
- TC-LOG-02: Empty email field
- TC-LOG-03: SQL injection in password field
- TC-LOG-04: Brute force protection (429 Too Many Requests)
- TC-LOG-05: Expired token usage (401 Unauthorized)

---

### 1.4 Product Catalog (GET Products)

**API Endpoint:** `GET /api/v1/products`

**Query Parameters:**
```
?category=ELECTRONICS&sort=price_asc&page=1&limit=10&minPrice=1000&maxPrice=50000
```

**Request Headers:**
```
Authorization: Bearer {{accessToken}}
Accept: application/json
```

**Response Body (200 OK):**
```json
{
  "data": [
    {
      "productId": "prod_12345",
      "name": "Wireless Headphones",
      "description": "Noise cancelling over-ear headphones",
      "category": "ELECTRONICS",
      "price": 2499.00,
      "currency": "INR",
      "stockQuantity": 150,
      "rating": 4.5,
      "images": [
        "https://cdn.example.com/images/headphones_1.jpg",
        "https://cdn.example.com/images/headphones_2.jpg"
      ],
      "specifications": {
        "batteryLife": "30 hours",
        "weight": "250g",
        "connectivity": "Bluetooth 5.0"
      }
    },
    {
      "productId": "prod_12346",
      "name": "Smart Watch",
      "description": "Fitness tracking smartwatch",
      "category": "ELECTRONICS",
      "price": 3999.00,
      "currency": "INR",
      "stockQuantity": 75,
      "rating": 4.3,
      "images": [
        "https://cdn.example.com/images/watch_1.jpg"
      ],
      "specifications": {
        "display": "1.4 inch AMOLED",
        "waterResistance": "5ATM",
        "batteryLife": "7 days"
      }
    }
  ],
  "pagination": {
    "currentPage": 1,
    "totalPages": 5,
    "totalItems": 48,
    "itemsPerPage": 10,
    "hasNextPage": true,
    "hasPreviousPage": false
  },
  "filters": {
    "category": "ELECTRONICS",
    "minPrice": 1000,
    "maxPrice": 50000,
    "sort": "price_asc"
  }
}
```

**Test Cases:**
- TC-PROD-01: Get all products without filters
- TC-PROD-02: Filter by category
- TC-PROD-03: Sort by price ascending
- TC-PROD-04: Sort by price descending
- TC-PROD-05: Pagination - page 1
- TC-PROD-06: Pagination - last page
- TC-PROD-07: Invalid category (empty result)
- TC-PROD-08: Price range filter
- TC-PROD-09: Search by keyword
- TC-PROD-10: Without auth token (401)

**Postman Test Script:**
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response is JSON", function () {
    pm.response.to.be.json;
});

pm.test("Data is an array", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.data).to.be.an('array');
});

pm.test("Each product has required fields", function () {
    var jsonData = pm.response.json();
    jsonData.data.forEach(function(product) {
        pm.expect(product).to.have.property("productId");
        pm.expect(product).to.have.property("name");
        pm.expect(product).to.have.property("price");
        pm.expect(product).to.have.property("stockQuantity");
    });
});

pm.test("Pagination object is present", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.pagination).to.have.property("currentPage");
    pm.expect(jsonData.pagination).to.have.property("totalPages");
    pm.expect(jsonData.pagination).to.have.property("totalItems");
});

pm.test("Price is positive", function () {
    var jsonData = pm.response.json();
    jsonData.data.forEach(function(product) {
        pm.expect(product.price).to.be.above(0);
    });
});
```

---

### 1.5 Add to Cart (POST with Auth Token)

**API Endpoint:** `POST /api/v1/cart/items`

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer {{accessToken}}
```

**Request Body:**
```json
{
  "productId": "prod_12345",
  "quantity": 2,
  "variant": {
    "color": "Midnight Black",
    "size": "Standard"
  }
}
```

**Response Body (200 OK):**
```json
{
  "cartId": "cart_98765",
  "userId": "usr_8f3a9b2c",
  "items": [
    {
      "cartItemId": "ci_001",
      "productId": "prod_12345",
      "productName": "Wireless Headphones",
      "quantity": 2,
      "unitPrice": 2499.00,
      "totalPrice": 4998.00,
      "variant": {
        "color": "Midnight Black",
        "size": "Standard"
      },
      "addedAt": "2026-06-14T11:00:00Z"
    }
  ],
  "summary": {
    "subtotal": 4998.00,
    "tax": 899.64,
    "shipping": 0.00,
    "discount": 0.00,
    "total": 5897.64,
    "currency": "INR"
  },
  "itemCount": 2,
  "updatedAt": "2026-06-14T11:00:00Z"
}
```

**Test Cases:**
- TC-CART-01: Add single item to cart
- TC-CART-02: Add multiple quantities
- TC-CART-03: Add item with variant
- TC-CART-04: Add duplicate item (should update quantity)
- TC-CART-05: Add out-of-stock item (400 Bad Request)
- TC-CART-06: Invalid productId (404 Not Found)
- TC-CART-07: Quantity zero (400 Bad Request)
- TC-CART-08: Negative quantity (400 Bad Request)
- TC-CART-09: Without auth token (401 Unauthorized)
- TC-CART-10: Exceed max quantity per item

**Postman Test Script:**
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Cart item is added", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.items).to.be.an('array').that.is.not.empty;
    pm.expect(jsonData.itemCount).to.be.above(0);
});

pm.test("Total price calculation is correct", function () {
    var jsonData = pm.response.json();
    var item = jsonData.items[0];
    var expectedTotal = item.unitPrice * item.quantity;
    pm.expect(item.totalPrice).to.eql(expectedTotal);
});

pm.test("Cart summary totals are correct", function () {
    var jsonData = pm.response.json();
    var summary = jsonData.summary;
    var expectedTotal = summary.subtotal + summary.tax + summary.shipping - summary.discount;
    pm.expect(summary.total).to.eql(expectedTotal);
});

pm.test("Cart ID is set", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("cartId");
    pm.environment.set("cartId", jsonData.cartId);
});
```

---

### 1.6 View Cart (GET)

**API Endpoint:** `GET /api/v1/cart`

**Request Headers:**
```
Authorization: Bearer {{accessToken}}
```

**Response Body (200 OK):** (Same structure as Add to Cart response)

**Test Cases:**
- TC-VIEW-01: View cart with items
- TC-VIEW-02: View empty cart (200 OK, empty items array)
- TC-VIEW-03: Verify cart totals calculation
- TC-VIEW-04: Without auth token (401)

---

### 1.7 Update Cart (PUT)

**API Endpoint:** `PUT /api/v1/cart/items/{cartItemId}`

**Request Body:**
```json
{
  "quantity": 3,
  "variant": {
    "color": "Midnight Black",
    "size": "Standard"
  }
}
```

**Response Body (200 OK):** Updated cart object

**Test Cases:**
- TC-UPD-01: Update quantity
- TC-UPD-02: Update variant
- TC-UPD-03: Set quantity to zero (remove item)
- TC-UPD-04: Update non-existent item (404)
- TC-UPD-05: Invalid quantity value

---

### 1.8 Checkout Process (POST Order)

**API Endpoint:** `POST /api/v1/orders`

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer {{accessToken}}
```

**Request Body:**
```json
{
  "shippingAddress": {
    "street": "42, MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "zipCode": "560001",
    "country": "India",
    "phone": "+91-9876543210"
  },
  "billingAddress": {
    "street": "42, MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "zipCode": "560001",
    "country": "India"
  },
  "paymentMethod": "CREDIT_CARD",
  "couponCode": "SUMMER2026",
  "notes": "Please deliver after 6 PM"
}
```

**Response Body (201 Created):**
```json
{
  "orderId": "ord_20260614_001",
  "userId": "usr_8f3a9b2c",
  "status": "PENDING_PAYMENT",
  "items": [
    {
      "productId": "prod_12345",
      "productName": "Wireless Headphones",
      "quantity": 3,
      "unitPrice": 2499.00,
      "totalPrice": 7497.00,
      "variant": {
        "color": "Midnight Black",
        "size": "Standard"
      }
    }
  ],
  "shippingAddress": {
    "street": "42, MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "zipCode": "560001",
    "country": "India",
    "phone": "+91-9876543210"
  },
  "billingAddress": {
    "street": "42, MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "zipCode": "560001",
    "country": "India"
  },
  "pricing": {
    "subtotal": 7497.00,
    "tax": 1349.46,
    "shipping": 0.00,
    "discount": 500.00,
    "total": 8346.46,
    "currency": "INR"
  },
  "payment": {
    "method": "CREDIT_CARD",
    "status": "PENDING",
    "transactionId": null
  },
  "createdAt": "2026-06-14T11:15:00Z",
  "estimatedDelivery": "2026-06-17T18:00:00Z"
}
```

**Test Cases:**
- TC-CHK-01: Successful checkout with valid cart
- TC-CHK-02: Checkout with empty cart (400 Bad Request)
- TC-CHK-03: Invalid coupon code
- TC-CHK-04: Invalid payment method
- TC-CHK-05: Missing shipping address
- TC-CHK-06: Verify discount calculation
- TC-CHK-07: Verify tax calculation
- TC-CHK-08: Without auth token (401)

**Postman Test Script:**
```javascript
pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});

pm.test("Order ID is generated", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("orderId");
    pm.environment.set("orderId", jsonData.orderId);
});

pm.test("Order status is PENDING_PAYMENT", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.status).to.eql("PENDING_PAYMENT");
});

pm.test("Payment status is PENDING", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.payment.status).to.eql("PENDING");
});

pm.test("Pricing totals are correct", function () {
    var jsonData = pm.response.json();
    var pricing = jsonData.pricing;
    var expectedTotal = pricing.subtotal + pricing.tax + pricing.shipping - pricing.discount;
    pm.expect(pricing.total).to.eql(expectedTotal);
});

pm.test("Delivery date is in future", function () {
    var jsonData = pm.response.json();
    var deliveryDate = new Date(jsonData.estimatedDelivery);
    var now = new Date();
    pm.expect(deliveryDate).to.be.above(now);
});
```

---

### 1.9 Order History (GET)

**API Endpoint:** `GET /api/v1/orders`

**Query Parameters:**
```
?status=ALL&page=1&limit=10&sort=created_desc
```

**Response Body (200 OK):**
```json
{
  "orders": [
    {
      "orderId": "ord_20260614_001",
      "status": "PENDING_PAYMENT",
      "total": 8346.46,
      "currency": "INR",
      "itemCount": 3,
      "createdAt": "2026-06-14T11:15:00Z"
    }
  ],
  "pagination": {
    "currentPage": 1,
    "totalPages": 1,
    "totalItems": 1,
    "itemsPerPage": 10
  }
}
```

**Test Cases:**
- TC-ORD-01: Get order history
- TC-ORD-02: Filter by status
- TC-ORD-03: Pagination
- TC-ORD-04: Sort by date
- TC-ORD-05: Get specific order by ID

---

### 1.10 Payment Simulation

**API Endpoint:** `POST /api/v1/payments/process`

**Request Body:**
```json
{
  "orderId": "ord_20260614_001",
  "paymentMethod": "CREDIT_CARD",
  "cardDetails": {
    "cardNumber": "4111111111111111",
    "cardHolder": "Rahul Sharma",
    "expiryMonth": "12",
    "expiryYear": "2028",
    "cvv": "123"
  },
  "amount": 8346.46,
  "currency": "INR"
}
```

**Response Body (200 OK):**
```json
{
  "transactionId": "txn_abc123xyz",
  "orderId": "ord_20260614_001",
  "status": "SUCCESS",
  "amount": 8346.46,
  "currency": "INR",
  "paymentMethod": "CREDIT_CARD",
  "timestamp": "2026-06-14T11:20:00Z",
  "receiptUrl": "https://api.example.com/receipts/txn_abc123xyz.pdf"
}
```

**Test Cases:**
- TC-PAY-01: Successful payment
- TC-PAY-02: Insufficient funds (402 Payment Required)
- TC-PAY-03: Invalid card number (400 Bad Request)
- TC-PAY-04: Expired card (400 Bad Request)
- TC-PAY-05: Invalid CVV (400 Bad Request)
- TC-PAY-06: Payment for already paid order (409 Conflict)
- TC-PAY-07: Amount mismatch (400 Bad Request)
- TC-PAY-08: Timeout simulation (504 Gateway Timeout)

**Postman Test Script:**
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Payment status is SUCCESS", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.status).to.eql("SUCCESS");
});

pm.test("Transaction ID is present", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("transactionId");
    pm.environment.set("transactionId", jsonData.transactionId);
});

pm.test("Amount matches order total", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.amount).to.eql(parseFloat(pm.environment.get("orderTotal")));
});

pm.test("Receipt URL is valid", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.receiptUrl).to.match(/^https:\/\/.+/);
});
```

---

### 1.11 Error Handling and Negative Testing

```
┌──────────────────────────────────────────────────────────────┐
│              NEGATIVE TESTING MATRIX FOR E-COMMERCE             │
├──────────────────────────────────────────────────────────────┤
│  Scenario              │  Expected Status  │  Error Code      │
├──────────────────────────────────────────────────────────────┤
│  Invalid JSON body      │  400 Bad Request  │  INVALID_JSON    │
│  Missing auth token     │  401 Unauthorized │  AUTH_REQUIRED   │
│  Invalid token format   │  401 Unauthorized │  INVALID_TOKEN   │
│  Expired token          │  401 Unauthorized │  TOKEN_EXPIRED   │
│  Insufficient role      │  403 Forbidden    │  FORBIDDEN       │
│  Resource not found     │  404 Not Found    │  NOT_FOUND       │
│  Method not allowed     │  405 Method Not   │  METHOD_NOT_ALLOWED│
│  Duplicate resource     │  409 Conflict     │  ALREADY_EXISTS  │
│  Rate limit exceeded    │  429 Too Many     │  RATE_LIMITED    │
│  Server error           │  500 Internal     │  INTERNAL_ERROR  │
│  Service unavailable    │  503 Service      │  SERVICE_DOWN    │
│  Gateway timeout        │  504 Gateway      │  TIMEOUT         │
└──────────────────────────────────────────────────────────────┘
```

**Error Response Structure:**
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "One or more fields failed validation",
    "details": [
      {
        "field": "email",
        "message": "Invalid email format",
        "rejectedValue": "invalid-email"
      },
      {
        "field": "password",
        "message": "Password must be at least 8 characters",
        "rejectedValue": "123"
      }
    ],
    "timestamp": "2026-06-14T11:25:00Z",
    "requestId": "req_9f8e7d6c"
  }
}
```

---

### 1.12 Data Flow and Memory Management in E-Commerce

```
┌──────────────────────────────────────────────────────────────┐
│           DATA FLOW IN E-COMMERCE TEST SUITE                    │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  Step 1: Registration                                          │
│  ├─ Input: UserRegistrationDTO (Heap)                          │
│  ├─ Output: UserResponseDTO (Heap)                              │
│  └─ Persisted: userId (Environment Variable)                    │
│                                                                 │
│  Step 2: Login                                                   │
│  ├─ Input: LoginRequestDTO (Heap)                              │
│  ├─ Output: AuthResponseDTO (Heap)                              │
│  └─ Persisted: accessToken, refreshToken (Environment)         │
│                                                                 │
│  Step 3: Product Catalog                                         │
│  ├─ Input: Pagination params (Stack)                           │
│  ├─ Output: ProductPageDTO (Heap)                              │
│  └─ Persisted: productId (Environment)                         │
│                                                                 │
│  Step 4: Add to Cart                                             │
│  ├─ Input: CartItemRequestDTO (Heap)                           │
│  ├─ Output: CartResponseDTO (Heap)                              │
│  └─ Persisted: cartId, cartItemId (Environment)                │
│                                                                 │
│  Step 5: Checkout                                                │
│  ├─ Input: OrderRequestDTO (Heap)                              │
│  ├─ Output: OrderResponseDTO (Heap)                            │
│  └─ Persisted: orderId (Environment)                           │
│                                                                 │
│  Step 6: Payment                                                 │
│  ├─ Input: PaymentRequestDTO (Heap)                            │
│  ├─ Output: PaymentResponseDTO (Heap)                          │
│  └─ Persisted: transactionId (Environment)                       │
│                                                                 │
│  Step 7: Order History                                           │
│  ├─ Input: Pagination params (Stack)                           │
│  └─ Output: OrderPageDTO (Heap)                                │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. Project 2: User Management API Testing

### 2.1 Overview

User Management systems handle CRUD operations for user accounts with roles, permissions, and search capabilities.

---

### 2.2 CRUD Operations

**Create User:**
```
POST /api/v1/users
```

```json
{
  "firstName": "Priya",
  "lastName": "Patel",
  "email": "priya.patel@example.com",
  "password": "SecurePass456!",
  "role": "ADMIN",
  "department": "Engineering",
  "isActive": true
}
```

**Read User:**
```
GET /api/v1/users/{userId}
```

**Update User:**
```
PUT /api/v1/users/{userId}
```

```json
{
  "firstName": "Priya",
  "lastName": "Patel",
  "email": "priya.patel@example.com",
  "role": "MANAGER",
  "department": "Product",
  "isActive": true
}
```

**Delete User:**
```
DELETE /api/v1/users/{userId}
```

**Test Cases for CRUD:**
- TC-CRUD-01: Create user with valid data
- TC-CRUD-02: Create user with duplicate email (409)
- TC-CRUD-03: Read existing user (200)
- TC-CRUD-04: Read non-existent user (404)
- TC-CRUD-05: Update user role
- TC-CRUD-06: Update user status
- TC-CRUD-07: Delete existing user (204)
- TC-CRUD-08: Delete already deleted user (404)
- TC-CRUD-09: Partial update with PATCH
- TC-CRUD-10: Update with invalid role (400)

---

### 2.3 Search and Filter

**API Endpoint:** `GET /api/v1/users/search`

**Query Parameters:**
```
?q=priya&role=ADMIN&department=Engineering&status=active&createdAfter=2026-01-01
```

**Response:**
```json
{
  "users": [
    {
      "userId": "usr_12345",
      "firstName": "Priya",
      "lastName": "Patel",
      "email": "priya.patel@example.com",
      "role": "ADMIN",
      "department": "Engineering",
      "isActive": true,
      "createdAt": "2026-03-15T10:00:00Z"
    }
  ],
  "totalResults": 1,
  "query": "priya",
  "filters": {
    "role": "ADMIN",
    "department": "Engineering",
    "status": "active"
  }
}
```

**Test Cases:**
- TC-SEARCH-01: Search by name
- TC-SEARCH-02: Filter by role
- TC-SEARCH-03: Filter by department
- TC-SEARCH-04: Combine search and filters
- TC-SEARCH-05: Empty search results
- TC-SEARCH-06: Case insensitive search
- TC-SEARCH-07: Wildcard search
- TC-SEARCH-08: Date range filter

---

### 2.4 Pagination Testing

**Query Parameters:**
```
?page=1&size=10&sort=createdAt,desc
```

**Response:**
```json
{
  "content": [ /* user objects */ ],
  "pageable": {
    "pageNumber": 1,
    "pageSize": 10,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    }
  },
  "totalElements": 150,
  "totalPages": 15,
  "last": false,
  "first": true,
  "number": 1,
  "size": 10,
  "numberOfElements": 10,
  "empty": false
}
```

**Pagination Test Cases:**
- TC-PAGE-01: First page
- TC-PAGE-02: Last page
- TC-PAGE-03: Middle page
- TC-PAGE-04: Page size boundary (1, 10, 50, 100)
- TC-PAGE-05: Page number beyond total (empty)
- TC-PAGE-06: Sort by different fields
- TC-PAGE-07: Sort direction ascending
- TC-PAGE-08: Sort direction descending

---

### 2.5 Bulk Operations

**Bulk Create:**
```
POST /api/v1/users/bulk
```

```json
{
  "users": [
    {
      "firstName": "User1",
      "lastName": "Test",
      "email": "user1@example.com",
      "role": "USER"
    },
    {
      "firstName": "User2",
      "lastName": "Test",
      "email": "user2@example.com",
      "role": "USER"
    }
  ]
}
```

**Response:**
```json
{
  "successful": 2,
  "failed": 0,
  "results": [
    {
      "email": "user1@example.com",
      "status": "CREATED",
      "userId": "usr_001"
    },
    {
      "email": "user2@example.com",
      "status": "CREATED",
      "userId": "usr_002"
    }
  ]
}
```

**Bulk Update:**
```
PUT /api/v1/users/bulk/status
```

```json
{
  "userIds": ["usr_001", "usr_002", "usr_003"],
  "status": "INACTIVE"
}
```

**Bulk Delete:**
```
DELETE /api/v1/users/bulk
```

```json
{
  "userIds": ["usr_001", "usr_002"]
}
```

---

### 2.6 Import/Export

**Export Users:**
```
GET /api/v1/users/export?format=csv
```

**Response Headers:**
```
Content-Type: text/csv
Content-Disposition: attachment; filename="users_2026-06-14.csv"
```

**Import Users:**
```
POST /api/v1/users/import
Content-Type: multipart/form-data
```

**Form Data:**
```
file: users.csv (multipart)
format: csv
skipErrors: true
```

**Response:**
```json
{
  "totalRecords": 100,
  "processed": 98,
  "failed": 2,
  "errors": [
    {
      "row": 45,
      "error": "Invalid email format",
      "data": { "email": "invalid-email" }
    },
    {
      "row": 67,
      "error": "Duplicate email",
      "data": { "email": "user@example.com" }
    }
  ]
}
```

---

## 3. Project 3: Social Media API Testing

### 3.1 Overview

Social Media APIs involve authentication, content creation, engagement, and real-time notifications.

---

### 3.2 Authentication (OAuth 2.0)

**Authorization Code Flow:**
```
Step 1: GET /oauth/authorize?client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=read,write
Step 2: User approves → Redirect to REDIRECT_URI?code=AUTH_CODE
Step 3: POST /oauth/token
        grant_type=authorization_code
        code=AUTH_CODE
        client_id=CLIENT_ID
        client_secret=CLIENT_SECRET
        redirect_uri=REDIRECT_URI
Step 4: Receive access_token and refresh_token
```

**Token Request:**
```
POST /api/v1/oauth/token
Content-Type: application/x-www-form-urlencoded
```

**Response:**
```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "expires_in": 3600,
  "refresh_token": "def50200...",
  "scope": "read write"
}
```

**Test Cases:**
- TC-OAUTH-01: Valid authorization code flow
- TC-OAUTH-02: Invalid client_id
- TC-OAUTH-03: Invalid client_secret
- TC-OAUTH-04: Expired authorization code
- TC-OAUTH-05: Invalid redirect_uri
- TC-OAUTH-06: Refresh token flow
- TC-OAUTH-07: Token revocation
- TC-OAUTH-08: Scope validation

---

### 3.3 Create Post

**API Endpoint:** `POST /api/v1/posts`

**Request Body:**
```json
{
  "content": "Just completed a marathon! Feeling amazing! #fitness #marathon",
  "media": [
    {
      "type": "image",
      "url": "https://cdn.example.com/media/marathon_1.jpg",
      "caption": "At the finish line"
    },
    {
      "type": "video",
      "url": "https://cdn.example.com/media/marathon_video.mp4",
      "duration": 120
    }
  ],
  "visibility": "PUBLIC",
  "tags": ["fitness", "marathon"],
  "location": {
    "name": "Marine Drive",
    "latitude": 18.9543,
    "longitude": 72.8114
  }
}
```

**Response (201 Created):**
```json
{
  "postId": "post_98765",
  "userId": "usr_12345",
  "content": "Just completed a marathon! Feeling amazing! #fitness #marathon",
  "media": [
    {
      "mediaId": "med_001",
      "type": "image",
      "url": "https://cdn.example.com/media/marathon_1.jpg",
      "caption": "At the finish line"
    }
  ],
  "visibility": "PUBLIC",
  "tags": ["fitness", "marathon"],
  "location": {
    "name": "Marine Drive",
    "latitude": 18.9543,
    "longitude": 72.8114
  },
  "likes": 0,
  "comments": 0,
  "shares": 0,
  "createdAt": "2026-06-14T12:00:00Z",
  "updatedAt": "2026-06-14T12:00:00Z"
}
```

**Test Cases:**
- TC-POST-01: Create text-only post
- TC-POST-02: Create post with media
- TC-POST-03: Create post with location
- TC-POST-04: Create post with tags
- TC-POST-05: Create post with visibility FRIENDS
- TC-POST-06: Create post with visibility PRIVATE
- TC-POST-07: Exceed character limit (400)
- TC-POST-08: Empty content (400)
- TC-POST-09: Invalid media URL (400)
- TC-POST-10: XSS attempt in content (sanitized)

---

### 3.4 Like/Comment

**Like Post:**
```
POST /api/v1/posts/{postId}/likes
```

**Response:**
```json
{
  "likeId": "like_001",
  "postId": "post_98765",
  "userId": "usr_67890",
  "createdAt": "2026-06-14T12:05:00Z"
}
```

**Unlike Post:**
```
DELETE /api/v1/posts/{postId}/likes
```

**Add Comment:**
```
POST /api/v1/posts/{postId}/comments
```

```json
{
  "content": "Congratulations! Amazing achievement!",
  "parentCommentId": null,
  "mentions": ["usr_12345"]
}
```

**Response:**
```json
{
  "commentId": "cmt_001",
  "postId": "post_98765",
  "userId": "usr_67890",
  "content": "Congratulations! Amazing achievement!",
  "mentions": ["usr_12345"],
  "likes": 0,
  "replies": 0,
  "createdAt": "2026-06-14T12:10:00Z",
  "updatedAt": "2026-06-14T12:10:00Z"
}
```

**Test Cases:**
- TC-LIKE-01: Like a post
- TC-LIKE-02: Unlike a post
- TC-LIKE-03: Like already liked post (409)
- TC-LIKE-04: Like own post
- TC-COM-01: Add comment
- TC-COM-02: Add nested reply
- TC-COM-03: Add comment with mention
- TC-COM-04: Delete own comment
- TC-COM-05: Delete others comment (403)
- TC-COM-06: Empty comment (400)

---

### 3.5 Feed Retrieval

**API Endpoint:** `GET /api/v1/feed`

**Query Parameters:**
```
?type=HOME&page=1&limit=20&sort=created_desc
```

**Response:**
```json
{
  "posts": [
    {
      "postId": "post_98765",
      "user": {
        "userId": "usr_12345",
        "username": "rahul_run",
        "displayName": "Rahul Sharma",
        "avatar": "https://cdn.example.com/avatars/rahul.jpg"
      },
      "content": "Just completed a marathon!",
      "media": [ /* media objects */ ],
      "engagement": {
        "likes": 245,
        "comments": 32,
        "shares": 12,
        "isLikedByMe": true,
        "isSharedByMe": false
      },
      "createdAt": "2026-06-14T12:00:00Z"
    }
  ],
  "pagination": {
    "cursor": "eyJpZCI6InBvc3RfOTg3NjUiLCJjcmVhdGVkQXQiOiIyMDI2LTA2LTE0VDEyOjAwOjAwWiJ9",
    "hasMore": true
  }
}
```

**Test Cases:**
- TC-FEED-01: Get home feed
- TC-FEED-02: Get user profile feed
- TC-FEED-03: Get trending feed
- TC-FEED-04: Pagination with cursor
- TC-FEED-05: Empty feed
- TC-FEED-06: Feed with blocked user content filtered

---

### 3.6 Notifications

**API Endpoint:** `GET /api/v1/notifications`

**Response:**
```json
{
  "notifications": [
    {
      "notificationId": "notif_001",
      "type": "LIKE",
      "message": "Priya Patel liked your post",
      "actor": {
        "userId": "usr_67890",
        "username": "priya_p",
        "avatar": "https://cdn.example.com/avatars/priya.jpg"
      },
      "target": {
        "type": "POST",
        "id": "post_98765"
      },
      "isRead": false,
      "createdAt": "2026-06-14T12:05:00Z"
    },
    {
      "notificationId": "notif_002",
      "type": "COMMENT",
      "message": "Amit Kumar commented on your post",
      "actor": {
        "userId": "usr_11111",
        "username": "amit_k",
        "avatar": "https://cdn.example.com/avatars/amit.jpg"
      },
      "target": {
        "type": "POST",
        "id": "post_98765"
      },
      "isRead": false,
      "createdAt": "2026-06-14T12:10:00Z"
    }
  ],
  "unreadCount": 2,
  "totalCount": 15
}
```

**Test Cases:**
- TC-NOTIF-01: Get notifications
- TC-NOTIF-02: Mark notification as read
- TC-NOTIF-03: Mark all as read
- TC-NOTIF-04: Delete notification
- TC-NOTIF-05: Real-time notification via WebSocket
- TC-NOTIF-06: Notification pagination

---

## 4. Project 4: Banking API Testing

### 4.1 Overview

Banking APIs require strict security, idempotency, and transaction integrity.

---

### 4.2 Account Creation

**API Endpoint:** `POST /api/v1/accounts`

**Request Body:**
```json
{
  "customerId": "cust_12345",
  "accountType": "SAVINGS",
  "branchCode": "BR001",
  "initialDeposit": 5000.00,
  "currency": "INR",
  "nominees": [
    {
      "name": "Sunita Sharma",
      "relationship": "SPOUSE",
      "percentage": 100
    }
  ],
  "kycDocuments": [
    {
      "type": "PAN_CARD",
      "documentNumber": "ABCDE1234F",
      "fileUrl": "https://docs.example.com/kyc/pan_123.pdf"
    },
    {
      "type": "AADHAAR",
      "documentNumber": "1234-5678-9012",
      "fileUrl": "https://docs.example.com/kyc/aadhaar_123.pdf"
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "accountId": "acc_9876543210",
  "accountNumber": "9876543210",
  "customerId": "cust_12345",
  "accountType": "SAVINGS",
  "branchCode": "BR001",
  "balance": 5000.00,
  "currency": "INR",
  "status": "ACTIVE",
  "ifscCode": "BANK0001",
  "micrCode": "560002001",
  "nominees": [
    {
      "nomineeId": "nom_001",
      "name": "Sunita Sharma",
      "relationship": "SPOUSE",
      "percentage": 100
    }
  ],
  "createdAt": "2026-06-14T09:00:00Z",
  "kycStatus": "VERIFIED"
}
```

**Test Cases:**
- TC-ACC-01: Create savings account
- TC-ACC-02: Create current account
- TC-ACC-03: Create account with zero deposit
- TC-ACC-04: Create account with invalid KYC (400)
- TC-ACC-05: Duplicate account for same customer (409)
- TC-ACC-06: Invalid account type (400)
- TC-ACC-07: Missing nominee for savings (400)

---

### 4.3 Balance Inquiry

**API Endpoint:** `GET /api/v1/accounts/{accountId}/balance`

**Response:**
```json
{
  "accountId": "acc_9876543210",
  "accountNumber": "9876543210",
  "balance": 5000.00,
  "availableBalance": 4950.00,
  "holdAmount": 50.00,
  "currency": "INR",
  "lastUpdated": "2026-06-14T09:30:00Z"
}
```

**Test Cases:**
- TC-BAL-01: Get balance for valid account
- TC-BAL-02: Get balance for invalid account (404)
- TC-BAL-03: Get balance for closed account (403)
- TC-BAL-04: Verify available balance = balance - hold
- TC-BAL-05: Without auth token (401)

---

### 4.4 Fund Transfer

**API Endpoint:** `POST /api/v1/transfers`

**Request Body:**
```json
{
  "sourceAccountId": "acc_9876543210",
  "destinationAccountId": "acc_1234567890",
  "amount": 1000.00,
  "currency": "INR",
  "transferType": "IMPS",
  "description": "Rent payment",
  "idempotencyKey": "idemp_20260614_001",
  "otp": "123456"
}
```

**Response (200 OK):**
```json
{
  "transactionId": "txn_9876543210",
  "sourceAccountId": "acc_9876543210",
  "destinationAccountId": "acc_1234567890",
  "amount": 1000.00,
  "currency": "INR",
  "transferType": "IMPS",
  "status": "SUCCESS",
  "description": "Rent payment",
  "idempotencyKey": "idemp_20260614_001",
  "transactionTimestamp": "2026-06-14T09:45:00Z",
  "sourceBalance": 4000.00,
  "destinationBalance": 6000.00
}
```

**Test Cases:**
- TC-TRANS-01: Successful IMPS transfer
- TC-TRANS-02: Successful NEFT transfer
- TC-TRANS-03: Successful RTGS transfer
- TC-TRANS-04: Transfer with insufficient funds (402)
- TC-TRANS-05: Transfer to invalid account (404)
- TC-TRANS-06: Transfer exceeding daily limit (400)
- TC-TRANS-07: Duplicate transfer with same idempotency key (200, same result)
- TC-TRANS-08: Invalid OTP (403)
- TC-TRANS-09: Transfer to same account (400)
- TC-TRANS-10: Negative amount (400)
- TC-TRANS-11: Transfer without OTP (403)
- TC-TRANS-12: Verify balance update after transfer

---

### 4.5 Transaction History

**API Endpoint:** `GET /api/v1/accounts/{accountId}/transactions`

**Query Parameters:**
```
?startDate=2026-06-01&endDate=2026-06-14&page=1&limit=20&sort=timestamp_desc
```

**Response:**
```json
{
  "accountId": "acc_9876543210",
  "transactions": [
    {
      "transactionId": "txn_9876543210",
      "type": "DEBIT",
      "amount": 1000.00,
      "currency": "INR",
      "description": "Rent payment",
      "counterparty": {
        "accountNumber": "1234567890",
        "name": "Amit Kumar"
      },
      "balanceAfter": 4000.00,
      "timestamp": "2026-06-14T09:45:00Z",
      "status": "COMPLETED"
    },
    {
      "transactionId": "txn_9876543209",
      "type": "CREDIT",
      "amount": 5000.00,
      "currency": "INR",
      "description": "Initial deposit",
      "counterparty": {
        "accountNumber": "CASH",
        "name": "Self"
      },
      "balanceAfter": 5000.00,
      "timestamp": "2026-06-14T09:00:00Z",
      "status": "COMPLETED"
    }
  ],
  "pagination": {
    "currentPage": 1,
    "totalPages": 1,
    "totalItems": 2,
    "itemsPerPage": 20
  },
  "summary": {
    "totalCredits": 5000.00,
    "totalDebits": 1000.00,
    "netChange": 4000.00
  }
}
```

**Test Cases:**
- TC-TRANS-13: Get transaction history
- TC-TRANS-14: Filter by date range
- TC-TRANS-15: Filter by transaction type
- TC-TRANS-16: Pagination
- TC-TRANS-17: Empty transaction history
- TC-TRANS-18: Verify balance consistency

---

### 4.6 Security Testing

**Security Test Matrix:**

```
┌──────────────────────────────────────────────────────────────┐
│              SECURITY TESTING FOR BANKING API                   │
├──────────────────────────────────────────────────────────────┤
│  Test                        │  Expected Result                │
├──────────────────────────────────────────────────────────────┤
│  SQL Injection in acc ID     │  400 Bad Request, sanitized     │
│  XSS in description          │  400 Bad Request, sanitized     │
│  CSRF without token          │  403 Forbidden                  │
│  Replay attack (same idemp)  │  200 OK, same result            │
│  Brute force OTP             │  429 Too Many Requests          │
│  Man-in-the-middle (HTTP)    │  400 Bad Request, HTTPS only    │
│  Rate limiting on transfers  │  429 Too Many Requests          │
│  Access other user's account │  403 Forbidden                  │
│  Tamper with amount          │  400 Bad Request, checksum fail │
│  Invalid JWT signature       │  401 Unauthorized               │
│  Expired JWT token           │  401 Unauthorized               │
│  Weak SSL/TLS version        │  Handshake failure                │
│  Missing Content-Type        │  415 Unsupported Media Type     │
│  Large payload (DoS)         │  413 Payload Too Large          │
│  Path traversal in file URL  │  400 Bad Request                │
└──────────────────────────────────────────────────────────────┘
```

**Test Cases:**
- TC-SEC-01: Verify HTTPS enforcement
- TC-SEC-02: Test JWT token validation
- TC-SEC-03: Test rate limiting
- TC-SEC-04: Test input sanitization
- TC-SEC-05: Test CSRF protection
- TC-SEC-06: Test authorization boundaries
- TC-SEC-07: Test idempotency enforcement
- TC-SEC-08: Test audit logging

---

## 5. Data Flow and Memory Management in Projects

### 5.1 How JSON Data Flows Between Requests

```
┌──────────────────────────────────────────────────────────────┐
│           JSON DATA FLOW IN API TESTING SUITE                   │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌─────────────┐     ┌─────────────┐     ┌─────────────┐      │
│  │  Request 1  │────→│  Response 1 │────→│  Persist    │      │
│  │  (Login)    │     │  (Token)    │     │  Variable   │      │
│  └─────────────┘     └─────────────┘     └─────────────┘      │
│         │                                               │      │
│         │         ┌──────────────────────┐                │      │
│         └────────→│  Environment/        │←─────────────┘      │
│                   │  Collection Variable   │                      │
│                   │  (String Pool / Heap)  │                      │
│                   └──────────────────────┘                      │
│                          │                                      │
│  ┌─────────────┐     ┌──┴────────┐     ┌─────────────┐       │
│  │  Request 2  │←────│  Read     │     │  Response 2 │       │
│  │  (GET)      │     │  Variable │     │  (Data)     │       │
│  └─────────────┘     └───────────┘     └─────────────┘       │
│                                                                 │
│  Data Flow Pattern:                                            │
│  1. Request sends JSON payload (serialized to bytes)            │
│  2. Server processes and returns JSON response                 │
│  3. Test script extracts values                                │
│  4. Values stored in environment variables                     │
│  5. Subsequent requests read variables and substitute            │
│  6. Cycle repeats for entire test suite                        │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

### 5.2 Memory Allocation for Each Step

```
┌──────────────────────────────────────────────────────────────┐
│              MEMORY ALLOCATION PER REQUEST STEP                   │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  Request Preparation Phase:                                      │
│  ├─ JSON Payload String → Heap (String Pool if interned)       │
│  ├─ Headers Map → Heap                                          │
│  ├─ URL String → Heap                                          │
│  ├─ Method Enum → Metaspace (class metadata)                   │
│  └─ Total: ~2-5 KB per request                                  │
│                                                                 │
│  Request Execution Phase:                                        │
│  ├─ HTTP Connection Object → Heap                              │
│  ├─ Request Body Stream → Heap (buffer)                        │
│  ├─ SSL Context (if HTTPS) → Heap                              │
│  ├─ Response Stream → Heap (buffer)                            │
│  └─ Total: ~10-50 KB per request                               │
│                                                                 │
│  Response Processing Phase:                                      │
│  ├─ Response Body String → Heap                                │
│  ├─ Parsed JSON Object (JSONObject/Map) → Heap                 │
│  ├─ Extracted Values → Heap (then stored to variables)          │
│  ├─ Test Results Array → Heap                                  │
│  └─ Total: ~5-20 KB per request                                 │
│                                                                 │
│  Cleanup Phase:                                                  │
│  ├─ Request/Response objects → Eligible for GC                   │
│  ├─ Buffers → Eligible for GC                                   │
│  ├─ Parsed JSON → Eligible for GC                               │
│  └─ Environment variables → Persisted (String Pool/Heap)       │
│                                                                 │
│  ┌────────────────────────────────────────────────────────┐    │
│  │  Typical Memory per Test Suite (100 requests):       │    │
│  │  ├─ Peak Heap: ~5-10 MB                                │    │
│  │  ├─ Metaspace: ~50 MB (constant)                       │    │
│  │  ├─ String Pool: ~1-2 MB (accumulated variables)      │    │
│  │  └─ Native Memory: ~20-50 MB (network buffers)         │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

### 5.3 Object Lifecycle During Test Execution

```
┌──────────────────────────────────────────────────────────────┐
│              OBJECT LIFECYCLE IN API TESTING                    │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  Stage 1: Object Creation                                      │
│  ├─ new RequestBuilder() → Eden Space                          │
│  ├─ new JSONObject() → Eden Space                              │
│  ├─ new Headers() → Eden Space                                 │
│  └─ State: Objects are alive and referenced                     │
│                                                                 │
│  Stage 2: Object Usage                                         │
│  ├─ RequestBuilder.build() → Request object                     │
│  ├─ JSONObject.parse() → Parsed response                       │
│  ├─ Extracted values → Stored in variables                     │
│  └─ State: Objects actively referenced                          │
│                                                                 │
│  Stage 3: Object Dereference                                   │
│  ├─ request = null → Reference removed                         │
│  ├─ response = null → Reference removed                        │
│  ├─ jsonData = null → Reference removed                        │
│  └─ State: Objects unreachable (eligible for GC)               │
│                                                                 │
│  Stage 4: Garbage Collection                                   │
│  ├─ Minor GC (Young Gen) → Frequent, fast                     │
│  ├─ Major GC (Old Gen) → Less frequent, slower                  │
│  ├─ Full GC → Stop-the-world, complete cleanup                 │
│  └─ State: Memory reclaimed, objects destroyed                  │
│                                                                 │
│  Stage 5: Environment Variable Persistence                     │
│  ├─ String values → Interned (String Pool) or Heap             │
│  ├─ Maps/Arrays → Not interned, remain in Heap                 │
│  └─ State: Persisted across requests until cleared             │
│                                                                 │
│  ┌────────────────────────────────────────────────────────┐    │
│  │  Best Practices:                                       │    │
│  │  ├─ Clear large variables after use                    │    │
│  │  ├─ Use streaming for large responses                  │    │
│  │  ├─ Avoid keeping full JSON in memory                  │    │
│  │  └─ Call System.gc() only when necessary (hint)        │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

### 5.4 Garbage Collection Patterns

```
┌──────────────────────────────────────────────────────────────┐
│            GARBAGE COLLECTION PATTERNS IN API TESTS             │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  Pattern 1: Request-Response Cycle                             │
│  ├─ Each request creates short-lived objects                    │
│  ├─ Minor GC cleans Eden Space after each batch                 │
│  ├─ Survivor spaces (S0, S1) hold partially aged objects         │
│  └─ Example: 100 requests → ~10-20 Minor GCs                    │
│                                                                 │
│  Pattern 2: Large Response Handling                              │
│  ├─ Large JSON responses (>1MB) allocated in Old Gen directly │
│  ├─ If promoted, may trigger Major GC                         │
│  ├─ Solution: Stream response, don't load fully               │
│  └─ Example: Bulk export API → streaming recommended           │
│                                                                 │
│  Pattern 3: Collection Variable Accumulation                     │
│  ├─ Variables stored across tests accumulate in Old Gen        │
│  ├─ Long test suites may increase heap usage                   │
│  ├─ Solution: Clear variables at end of collection              │
│  └─ Example: 1000 test runs → clear env at end                 │
│                                                                 │
│  Pattern 4: Connection Pooling                                  │
│  ├─ HTTP connections reused (pool in native memory)             │
│  ├─ Connection objects live longer than requests               │
│  ├─ Managed by connection pool, not GC                          │
│  └─ Example: 10 parallel threads → 10 connections in pool     │
│                                                                 │
│  Pattern 5: SSL/TLS Context                                     │
│  ├─ SSLContext created once per JVM                             │
│  ├─ Lives in native memory (off-heap)                          │
│  ├─ Not subject to GC, managed by SSL provider                 │
│  └─ Example: HTTPS tests → single context reused                │
│                                                                 │
│  ┌────────────────────────────────────────────────────────┐    │
│  │  GC Tuning for API Testing:                            │    │
│  │  ├─ -Xms512m -Xmx2g (heap size)                      │    │
│  │  ├─ -XX:+UseG1GC (low pause GC)                      │    │
│  │  ├─ -XX:MaxGCPauseMillis=200 (target pause)           │    │
│  │  └─ -XX:+PrintGCDetails (logging)                    │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

### 5.5 Optimization Strategies

```
┌──────────────────────────────────────────────────────────────┐
│           OPTIMIZATION STRATEGIES FOR API TESTING               │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  1. Response Size Optimization                                  │
│  ├─ Use field selection (?fields=id,name,price)                │
│  ├─ Enable compression (gzip/deflate)                          │
│  ├─ Paginate large datasets                                    │
│  └─ Memory savings: 50-80%                                     │
│                                                                 │
│  2. Connection Optimization                                     │
│  ├─ Enable HTTP/2 multiplexing                                 │
│  ├─ Reuse TCP connections (keep-alive)                         │
│  ├─ Connection pooling (max 10-20 per host)                   │
│  └─ Time savings: 30-50%                                       │
│                                                                 │
│  3. JSON Parsing Optimization                                   │
│  ├─ Use streaming parser (Jackson Streaming API)              │
│  ├─ Extract only needed fields (JSONPath)                      │
│  ├─ Avoid full object mapping for large responses              │
│  └─ Memory savings: 60-70%                                     │
│                                                                 │
│  4. Variable Storage Optimization                                 │
│  ├─ Store only needed values (not entire JSON)                │
│  ├─ Clear variables after test suite completion                │
│  ├─ Use collection variables (not global) for isolation        │
│  └─ Memory savings: 40-60%                                     │
│                                                                 │
│  5. Parallel Execution Optimization                             │
│  ├─ Run independent tests in parallel                          │
│  ├─ Limit thread count (CPU cores * 2)                         │
│  ├─ Use thread-local variables for isolation                   │
│  └─ Time savings: 60-80% (with 4-8 threads)                    │
│                                                                 │
│  6. JVM Memory Optimization                                     │
│  ├─ Set appropriate heap size (don't over-allocate)           │
│  ├─ Use G1GC for low pause times                               │
│  ├─ Monitor with VisualVM or JConsole                          │
│  └─ Stability improvement: 40-50%                              │
│                                                                 │
│  7. Test Data Optimization                                      │
│  ├─ Use small, representative test data                        │
│  ├─ Generate data programmatically (not hardcoded)             │
│  ├─ Clean up test data after execution                         │
│  └─ Maintenance reduction: 50-70%                                │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

## 6. Step-by-Step Implementation Guide

### 6.1 Setting Up the Project

**Step 1: Create Maven Project**
```xml
<!-- pom.xml -->
<project>
    <groupId>com.example</groupId>
    <artifactId>api-testing-realworld</artifactId>
    <version>1.0.0</version>
    
    <dependencies>
        <!-- REST Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.4.0</version>
        </dependency>
        
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.9.0</version>
        </dependency>
        
        <!-- Jackson -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.16.0</version>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
        </dependency>
        
        <!-- AssertJ -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.25.0</version>
        </dependency>
    </dependencies>
</project>
```

**Step 2: Configure TestNG Suite**
```xml
<!-- testng.xml -->
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="RealWorldAPITests" parallel="methods" thread-count="4">
    <test name="ECommerceTests">
        <classes>
            <class name="com.example.api.EcommerceApiTest"/>
        </classes>
    </test>
    <test name="UserManagementTests">
        <classes>
            <class name="com.example.api.UserManagementApiTest"/>
        </classes>
    </test>
    <test name="BankingTests">
        <classes>
            <class name="com.example.api.BankingApiTest"/>
        </classes>
    </test>
    <test name="SocialMediaTests">
        <classes>
            <class name="com.example.api.SocialMediaApiTest"/>
        </classes>
    </test>
</suite>
```

**Step 3: Create Base Test Class**
```java
public class BaseApiTest {
    protected static final String BASE_URL = "https://api.example.com";
    protected static final ObjectMapper objectMapper = new ObjectMapper();
    protected String authToken;
    
    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(
            new RequestLoggingFilter(),
            new ResponseLoggingFilter()
        );
    }
    
    protected RequestSpecification givenAuth() {
        return given()
            .header("Authorization", "Bearer " + authToken)
            .contentType(ContentType.JSON);
    }
}
```

---

### 6.2 Creating POJOs for Each Entity

**Example: User Registration POJO**
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Address address;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
    }
}
```

**Example: Auth Response POJO**
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;
    private UserInfo user;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private String userId;
        private String email;
        private String role;
    }
}
```

---

### 6.3 Building the Controller Layer

**Example: E-Commerce Controller**
```java
public class EcommerceController extends BaseApiTest {
    
    public Response registerUser(UserRegistrationRequest request) {
        return given()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/api/v1/users/register");
    }
    
    public Response login(String email, String password) {
        Map<String, String> credentials = Map.of(
            "email", email,
            "password", password
        );
        
        Response response = given()
            .contentType(ContentType.JSON)
            .body(credentials)
            .post("/api/v1/auth/login");
        
        if (response.statusCode() == 200) {
            authToken = response.jsonPath().getString("accessToken");
        }
        
        return response;
    }
    
    public Response getProducts(String category, int page, int limit) {
        return givenAuth()
            .queryParam("category", category)
            .queryParam("page", page)
            .queryParam("limit", limit)
            .get("/api/v1/products");
    }
    
    public Response addToCart(String productId, int quantity) {
        Map<String, Object> cartItem = Map.of(
            "productId", productId,
            "quantity", quantity
        );
        
        return givenAuth()
            .body(cartItem)
            .post("/api/v1/cart/items");
    }
    
    public Response checkout(OrderRequest request) {
        return givenAuth()
            .body(request)
            .post("/api/v1/orders");
    }
    
    public Response processPayment(PaymentRequest request) {
        return givenAuth()
            .body(request)
            .post("/api/v1/payments/process");
    }
}
```

---

### 6.4 Writing Test Cases

**Example: E-Commerce Test Class**
```java
public class EcommerceApiTest extends EcommerceController {
    
    private String userId;
    private String productId;
    private String orderId;
    private String cartId;
    
    @Test(priority = 1)
    public void testUserRegistration() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
            .firstName("Rahul")
            .lastName("Sharma")
            .email("rahul" + System.currentTimeMillis() + "@example.com")
            .password("SecurePass123!")
            .phone("+91-9876543210")
            .address(UserRegistrationRequest.Address.builder()
                .street("42, MG Road")
                .city("Bangalore")
                .state("Karnataka")
                .zipCode("560001")
                .country("India")
                .build())
            .build();
        
        Response response = registerUser(request);
        
        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.jsonPath().getString("firstName")).isEqualTo("Rahul");
        assertThat(response.jsonPath().getString("status")).isEqualTo("ACTIVE");
        
        userId = response.jsonPath().getString("userId");
    }
    
    @Test(priority = 2)
    public void testUserLogin() {
        Response response = login("rahul" + System.currentTimeMillis() + "@example.com", "SecurePass123!");
        
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("tokenType")).isEqualTo("Bearer");
        assertThat(response.jsonPath().getInt("expiresIn")).isEqualTo(3600);
    }
    
    @Test(priority = 3)
    public void testGetProducts() {
        Response response = getProducts("ELECTRONICS", 1, 10);
        
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("data")).isNotEmpty();
        
        productId = response.jsonPath().getString("data[0].productId");
    }
    
    @Test(priority = 4)
    public void testAddToCart() {
        Response response = addToCart(productId, 2);
        
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getInt("itemCount")).isEqualTo(2);
        
        cartId = response.jsonPath().getString("cartId");
    }
    
    @Test(priority = 5)
    public void testCheckout() {
        OrderRequest request = OrderRequest.builder()
            .shippingAddress(OrderRequest.Address.builder()
                .street("42, MG Road")
                .city("Bangalore")
                .state("Karnataka")
                .zipCode("560001")
                .country("India")
                .phone("+91-9876543210")
                .build())
            .paymentMethod("CREDIT_CARD")
            .build();
        
        Response response = checkout(request);
        
        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.jsonPath().getString("status")).isEqualTo("PENDING_PAYMENT");
        
        orderId = response.jsonPath().getString("orderId");
    }
    
    @Test(priority = 6)
    public void testPayment() {
        PaymentRequest request = PaymentRequest.builder()
            .orderId(orderId)
            .paymentMethod("CREDIT_CARD")
            .amount(8346.46)
            .currency("INR")
            .build();
        
        Response response = processPayment(request);
        
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("status")).isEqualTo("SUCCESS");
    }
    
    @Test(priority = 7)
    public void testDuplicateEmailRegistration() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
            .firstName("Rahul")
            .lastName("Sharma")
            .email("rahul@example.com")
            .password("SecurePass123!")
            .build();
        
        Response response = registerUser(request);
        assertThat(response.statusCode()).isEqualTo(409);
    }
    
    @Test(priority = 8)
    public void testInvalidLogin() {
        Response response = login("wrong@example.com", "wrongpassword");
        assertThat(response.statusCode()).isEqualTo(401);
    }
    
    @Test(priority = 9)
    public void testAddToCartWithoutAuth() {
        Response response = given()
            .contentType(ContentType.JSON)
            .body(Map.of("productId", productId, "quantity", 1))
            .post("/api/v1/cart/items");
        
        assertThat(response.statusCode()).isEqualTo(401);
    }
}
```

---

### 6.5 Running and Reporting

**Run with Maven:**
```bash
mvn clean test
```

**Run specific test class:**
```bash
mvn test -Dtest=EcommerceApiTest
```

**Generate Allure Report:**
```xml
<!-- Add Allure dependency -->
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>2.25.0</version>
</dependency>
```

```bash
mvn allure:serve
```

**Generate Extent Report:**
```java
@AfterSuite
public void generateReport() {
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("report.html");
    extent.attachReporter(spark);
    // ... add test results
    extent.flush();
}
```

---

## 7. JSON Data Structures for Each Project

### 7.1 E-Commerce JSON Structures

```json
// User Registration Request
{
  "firstName": "string",
  "lastName": "string",
  "email": "string (email format)",
  "password": "string (min 8 chars)",
  "phone": "string (E.164 format)",
  "address": {
    "street": "string",
    "city": "string",
    "state": "string",
    "zipCode": "string",
    "country": "string (ISO 3166-1 alpha-3)"
  }
}

// User Registration Response
{
  "userId": "string (UUID)",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "phone": "string",
  "address": {
    "street": "string",
    "city": "string",
    "state": "string",
    "zipCode": "string",
    "country": "string"
  },
  "createdAt": "string (ISO 8601)",
  "status": "string (enum: ACTIVE, INACTIVE, SUSPENDED)"
}

// Login Request
{
  "email": "string",
  "password": "string"
}

// Login Response
{
  "accessToken": "string (JWT)",
  "refreshToken": "string (JWT)",
  "tokenType": "string (Bearer)",
  "expiresIn": "integer (seconds)",
  "user": {
    "userId": "string",
    "email": "string",
    "role": "string (enum: CUSTOMER, ADMIN, MANAGER)"
  }
}

// Product
{
  "productId": "string",
  "name": "string",
  "description": "string",
  "category": "string (enum)",
  "price": "number (decimal)",
  "currency": "string (ISO 4217)",
  "stockQuantity": "integer",
  "rating": "number (0-5)",
  "images": ["string (URL)"],
  "specifications": {
    "key": "string"
  }
}

// Cart Item Request
{
  "productId": "string",
  "quantity": "integer (min 1)",
  "variant": {
    "color": "string",
    "size": "string"
  }
}

// Cart Response
{
  "cartId": "string",
  "userId": "string",
  "items": [
    {
      "cartItemId": "string",
      "productId": "string",
      "productName": "string",
      "quantity": "integer",
      "unitPrice": "number",
      "totalPrice": "number",
      "variant": {
        "color": "string",
        "size": "string"
      },
      "addedAt": "string (ISO 8601)"
    }
  ],
  "summary": {
    "subtotal": "number",
    "tax": "number",
    "shipping": "number",
    "discount": "number",
    "total": "number",
    "currency": "string"
  },
  "itemCount": "integer",
  "updatedAt": "string (ISO 8601)"
}

// Order Request
{
  "shippingAddress": {
    "street": "string",
    "city": "string",
    "state": "string",
    "zipCode": "string",
    "country": "string",
    "phone": "string"
  },
  "billingAddress": {
    "street": "string",
    "city": "string",
    "state": "string",
    "zipCode": "string",
    "country": "string"
  },
  "paymentMethod": "string (enum: CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING)",
  "couponCode": "string (optional)",
  "notes": "string (optional)"
}

// Order Response
{
  "orderId": "string",
  "userId": "string",
  "status": "string (enum: PENDING_PAYMENT, PAID, PROCESSING, SHIPPED, DELIVERED, CANCELLED)",
  "items": ["Product objects with quantity and price"],
  "shippingAddress": "Address object",
  "billingAddress": "Address object",
  "pricing": {
    "subtotal": "number",
    "tax": "number",
    "shipping": "number",
    "discount": "number",
    "total": "number",
    "currency": "string"
  },
  "payment": {
    "method": "string",
    "status": "string (enum: PENDING, SUCCESS, FAILED, REFUNDED)",
    "transactionId": "string (nullable)"
  },
  "createdAt": "string (ISO 8601)",
  "estimatedDelivery": "string (ISO 8601)"
}

// Payment Request
{
  "orderId": "string",
  "paymentMethod": "string",
  "cardDetails": {
    "cardNumber": "string (16 digits)",
    "cardHolder": "string",
    "expiryMonth": "string (MM)",
    "expiryYear": "string (YYYY)",
    "cvv": "string (3-4 digits)"
  },
  "amount": "number",
  "currency": "string"
}

// Payment Response
{
  "transactionId": "string",
  "orderId": "string",
  "status": "string (enum: SUCCESS, FAILED, PENDING)",
  "amount": "number",
  "currency": "string",
  "paymentMethod": "string",
  "timestamp": "string (ISO 8601)",
  "receiptUrl": "string (URL)"
}
```

---

### 7.2 User Management JSON Structures

```json
// User Request
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "role": "string (enum: USER, ADMIN, MANAGER, GUEST)",
  "department": "string",
  "isActive": "boolean"
}

// User Response
{
  "userId": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "role": "string",
  "department": "string",
  "isActive": "boolean",
  "createdAt": "string (ISO 8601)",
  "updatedAt": "string (ISO 8601)"
}

// Bulk Operation Request
{
  "users": ["User Request objects"],
  "operation": "string (enum: CREATE, UPDATE, DELETE)"
}

// Bulk Operation Response
{
  "successful": "integer",
  "failed": "integer",
  "results": [
    {
      "identifier": "string",
      "status": "string (enum: CREATED, UPDATED, DELETED, FAILED)",
      "error": "string (nullable)"
    }
  ]
}

// Import Response
{
  "totalRecords": "integer",
  "processed": "integer",
  "failed": "integer",
  "errors": [
    {
      "row": "integer",
      "error": "string",
      "data": "object"
    }
  ]
}
```

---

### 7.3 Social Media JSON Structures

```json
// Post Request
{
  "content": "string (max 280 chars for microblog, unlimited for blog)",
  "media": [
    {
      "type": "string (enum: image, video, audio, document)",
      "url": "string (URL)",
      "caption": "string (optional)",
      "duration": "integer (seconds, for video/audio)"
    }
  ],
  "visibility": "string (enum: PUBLIC, FRIENDS, PRIVATE, CUSTOM)",
  "tags": ["string"],
  "location": {
    "name": "string",
    "latitude": "number",
    "longitude": "number"
  }
}

// Post Response
{
  "postId": "string",
  "userId": "string",
  "content": "string",
  "media": ["Media objects"],
  "visibility": "string",
  "tags": ["string"],
  "location": "Location object",
  "likes": "integer",
  "comments": "integer",
  "shares": "integer",
  "createdAt": "string (ISO 8601)",
  "updatedAt": "string (ISO 8601)"
}

// Comment Request
{
  "content": "string",
  "parentCommentId": "string (nullable, for replies)",
  "mentions": ["string (user IDs)"]
}

// Notification Response
{
  "notificationId": "string",
  "type": "string (enum: LIKE, COMMENT, SHARE, FOLLOW, MENTION)",
  "message": "string",
  "actor": {
    "userId": "string",
    "username": "string",
    "avatar": "string (URL)"
  },
  "target": {
    "type": "string (enum: POST, COMMENT, USER)",
    "id": "string"
  },
  "isRead": "boolean",
  "createdAt": "string (ISO 8601)"
}
```

---

### 7.4 Banking JSON Structures

```json
// Account Creation Request
{
  "customerId": "string",
  "accountType": "string (enum: SAVINGS, CURRENT, FIXED_DEPOSIT, RECURRING_DEPOSIT)",
  "branchCode": "string",
  "initialDeposit": "number",
  "currency": "string (ISO 4217)",
  "nominees": [
    {
      "name": "string",
      "relationship": "string",
      "percentage": "number (0-100)"
    }
  ],
  "kycDocuments": [
    {
      "type": "string (enum: PAN_CARD, AADHAAR, PASSPORT, DRIVING_LICENSE)",
      "documentNumber": "string",
      "fileUrl": "string (URL)"
    }
  ]
}

// Account Response
{
  "accountId": "string",
  "accountNumber": "string",
  "customerId": "string",
  "accountType": "string",
  "branchCode": "string",
  "balance": "number",
  "currency": "string",
  "status": "string (enum: ACTIVE, INACTIVE, DORMANT, CLOSED)",
  "ifscCode": "string",
  "micrCode": "string",
  "nominees": ["Nominee objects"],
  "createdAt": "string (ISO 8601)",
  "kycStatus": "string (enum: PENDING, VERIFIED, REJECTED)"
}

// Transfer Request
{
  "sourceAccountId": "string",
  "destinationAccountId": "string",
  "amount": "number",
  "currency": "string",
  "transferType": "string (enum: IMPS, NEFT, RTGS, UPI)",
  "description": "string",
  "idempotencyKey": "string (UUID)",
  "otp": "string"
}

// Transfer Response
{
  "transactionId": "string",
  "sourceAccountId": "string",
  "destinationAccountId": "string",
  "amount": "number",
  "currency": "string",
  "transferType": "string",
  "status": "string (enum: SUCCESS, FAILED, PENDING, REVERSED)",
  "description": "string",
  "idempotencyKey": "string",
  "transactionTimestamp": "string (ISO 8601)",
  "sourceBalance": "number",
  "destinationBalance": "number"
}

// Transaction History Entry
{
  "transactionId": "string",
  "type": "string (enum: CREDIT, DEBIT)",
  "amount": "number",
  "currency": "string",
  "description": "string",
  "counterparty": {
    "accountNumber": "string",
    "name": "string"
  },
  "balanceAfter": "number",
  "timestamp": "string (ISO 8601)",
  "status": "string (enum: COMPLETED, PENDING, FAILED, REVERSED)"
}
```

---

## 8. Interview FAQs (15+ Questions)

### Q1: How do you handle authentication tokens in a Postman collection?

**Answer:**
- Extract the token from the login response using `pm.environment.set("token", jsonData.accessToken)`
- Use the token in subsequent requests via `{{token}}` in the Authorization header
- Set the token at the collection level using Pre-request Scripts
- Implement token refresh logic in collection tests
- Handle token expiry by checking response status and re-authenticating

```javascript
// Pre-request Script (Collection level)
if (!pm.environment.get("token")) {
    // Trigger login request
    pm.sendRequest({
        url: pm.environment.get("baseUrl") + "/auth/login",
        method: "POST",
        body: {
            mode: "raw",
            raw: JSON.stringify({
                email: pm.environment.get("email"),
                password: pm.environment.get("password")
            })
        }
    }, function(err, response) {
        var jsonData = response.json();
        pm.environment.set("token", jsonData.accessToken);
    });
}
```

---

### Q2: How do you test API workflows that span multiple requests?

**Answer:**
- Use Postman Collections with folder organization
- Set environment variables to pass data between requests
- Use `pm.sendRequest()` for dependent calls in pre-request scripts
- Implement test scripts to validate state transitions
- Use Newman for CI/CD execution of complete workflows
- Chain tests in TestNG with `@Test(dependsOnMethods = {...})`
- Use data-driven testing with CSV/JSON files for multiple scenarios

---

### Q3: What is idempotency in API testing and how do you test it?

**Answer:**
Idempotency means multiple identical requests produce the same result.

**Testing approach:**
- Send the same request with an idempotency key twice
- Verify both responses have status 200 and identical data
- Check that no duplicate records are created
- Verify the same transaction ID is returned
- Test with network interruption between retries

```java
@Test
public void testIdempotency() {
    String idempotencyKey = UUID.randomUUID().toString();
    TransferRequest request = createTransferRequest(idempotencyKey);
    
    Response response1 = transfer(request);
    Response response2 = transfer(request);
    
    assertThat(response1.statusCode()).isEqualTo(200);
    assertThat(response2.statusCode()).isEqualTo(200);
    assertThat(response1.jsonPath().getString("transactionId"))
        .isEqualTo(response2.jsonPath().getString("transactionId"));
}
```

---

### Q4: How do you handle dynamic data in API tests?

**Answer:**
- Use timestamps or UUIDs for unique fields (email, username)
- Use faker libraries for generating realistic test data
- Implement data factories for complex objects
- Use pre-request scripts to generate random values
- Store generated data in environment variables for cleanup

```java
public class TestDataFactory {
    private static final Faker faker = new Faker();
    
    public static UserRegistrationRequest createUser() {
        return UserRegistrationRequest.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .email(faker.internet().emailAddress())
            .password("TestPass123!")
            .build();
    }
}
```

---

### Q5: How do you test API rate limiting?

**Answer:**
- Send requests in a loop exceeding the rate limit
- Verify 429 status code is returned
- Check the `Retry-After` header
- Test with concurrent requests from multiple threads
- Verify rate limit resets after the window

```java
@Test
public void testRateLimiting() {
    for (int i = 0; i < 110; i++) {
        given().get("/api/v1/products");
    }
    
    Response response = given().get("/api/v1/products");
    assertThat(response.statusCode()).isEqualTo(429);
    assertThat(response.header("Retry-After")).isNotNull();
}
```

---

### Q6: How do you test file upload/download APIs?

**Answer:**
- Use `multipart/form-data` for uploads
- Verify file size, type, and content validation
- Test with invalid file types (400)
- Test with oversized files (413)
- For downloads: verify Content-Type, Content-Length, and Content-Disposition headers
- Verify checksum integrity for downloaded files

```java
@Test
public void testFileUpload() {
    File file = new File("src/test/resources/test.csv");
    
    Response response = given()
        .multiPart("file", file)
        .multiPart("format", "csv")
        .post("/api/v1/users/import");
    
    assertThat(response.statusCode()).isEqualTo(200);
    assertThat(response.jsonPath().getInt("processed")).isGreaterThan(0);
}
```

---

### Q7: How do you test API pagination?

**Answer:**
- Test first page, last page, and middle pages
- Verify page size boundaries (1, default, max)
- Test invalid page numbers (0, negative, beyond total)
- Verify total count and total pages accuracy
- Test sorting on different fields and directions
- Verify cursor-based pagination for large datasets

---

### Q8: How do you test API security (SQL injection, XSS)?

**Answer:**
- SQL Injection: Send `' OR '1'='1`, `1; DROP TABLE users--` in input fields
- XSS: Send `<script>alert('xss')</script>` in text fields
- Verify input is sanitized and rejected with 400
- Check response headers for security policies (CSP, X-XSS-Protection)
- Verify parameterized queries prevent SQL injection
- Test for path traversal in file path parameters

---

### Q9: What is the difference between REST Assured and Postman?

**Answer:**

```
┌────────────────────┬────────────────────┬────────────────────┐
│ Feature            │ REST Assured       │ Postman            │
├────────────────────┼────────────────────┼────────────────────┤
│ Type               │ Java library       │ GUI tool + CLI     │
│ Code-based         │ Yes (Java)         │ JavaScript (tests) │
│ CI/CD integration  │ Excellent (Maven)  │ Good (Newman)      │
│ Version control    │ Full (Git)         │ Collection export  │
│ Team collaboration │ Good (code review) │ Excellent (cloud)  │
│ Reporting          │ Extent/Allure      │ Built-in/Newman    │
│ Debugging          │ IDE debugger       │ Visual interface   │
│ Performance        │ Fast (no GUI)      │ Slower (GUI)       │
│ Reusability        │ High (OOP)         │ Medium (variables) │
└────────────────────┴────────────────────┴────────────────────┘
```

---

### Q10: How do you test OAuth 2.0 flows?

**Answer:**
- Test authorization code flow: verify code exchange for token
- Test client credentials flow for service-to-service
- Test implicit flow (deprecated but still used)
- Test PKCE extension for mobile apps
- Verify token expiry and refresh token flow
- Test with invalid scopes (403)
- Test token revocation endpoint
- Verify state parameter prevents CSRF

---

### Q11: How do you handle API versioning in tests?

**Answer:**
- Use version in URL path: `/api/v1/users`
- Use version in header: `Accept: application/vnd.api.v1+json`
- Test backward compatibility between versions
- Verify deprecation headers: `Deprecation: true`, `Sunset: date`
- Test that v1 clients still work with v2 endpoints
- Maintain separate test suites for each version
- Use environment variables for version switching

---

### Q12: How do you test API response time and performance?

**Answer:**
- Use `pm.expect(pm.response.responseTime).to.be.below(2000)` in Postman
- In REST Assured: `response.then().time(lessThan(2000L))`
- Use JMeter or Gatling for load testing
- Test with concurrent users (10, 100, 1000)
- Measure percentiles (p50, p95, p99)
- Test with large payloads (10MB, 100MB)
- Monitor server CPU and memory during tests

---

### Q13: How do you test error handling in APIs?

**Answer:**
- Test each HTTP status code (400, 401, 403, 404, 409, 422, 500, 503)
- Verify error response structure (code, message, details)
- Test with invalid JSON body (malformed JSON)
- Test with missing required fields
- Test with invalid enum values
- Test with data type mismatches (string instead of number)
- Verify error messages don't expose internal details (security)
- Test with null values in non-nullable fields

---

### Q14: How do you test WebSocket or real-time APIs?

**Answer:**
- Use Postman WebSocket feature for manual testing
- Use Java libraries (Java-WebSocket, Tyrus) for automated tests
- Test connection establishment and closure
- Verify message format (JSON, binary)
- Test reconnection logic
- Test with multiple concurrent connections
- Verify heartbeat/ping-pong mechanism
- Test authentication over WebSocket

---

### Q15: How do you test API contract validation?

**Answer:**
- Use JSON Schema validation: `matchesJsonSchemaInClasspath("schema.json")`
- Use OpenAPI/Swagger specification for contract testing
- Verify all required fields are present
- Verify data types match specification
- Test field constraints (min, max, pattern, enum)
- Use Pact for consumer-driven contract testing
- Verify response headers match contract
- Test that no extra fields are present (strict validation)

```java
@Test
public void testSchemaValidation() {
    given()
        .get("/api/v1/users/123")
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("user-schema.json"));
}
```

---

### Q16: How do you manage test data in API testing?

**Answer:**
- **Setup:** Create test data in `@BeforeClass` or `@BeforeMethod`
- **Cleanup:** Delete test data in `@AfterClass` or `@AfterMethod`
- **Isolation:** Use unique identifiers per test run
- **Seeding:** Use SQL scripts or API calls to seed database
- **Factories:** Implement data factories for consistent object creation
- **Mocking:** Use WireMock or Mockito for external dependencies
- **Fixtures:** Use JSON files for static test data

---

### Q17: How do you test API idempotency keys and duplicate prevention?

**Answer:**
- Generate unique idempotency keys per request
- Replay the same request with the same key
- Verify identical response (status, body, headers)
- Verify no duplicate database records
- Test with different keys for same payload (should create new)
- Verify key expiration (old key should create new after expiry)
- Test concurrent requests with same key (only one should succeed)

---

### Q18: How do you handle API dependencies in a microservices architecture?

**Answer:**
- Use WireMock to mock dependent services
- Implement contract testing with Pact
- Test service integration with Docker Compose
- Use service virtualization for unavailable services
- Implement circuit breaker testing (fallback responses)
- Test eventual consistency with retries and polling
- Use distributed tracing headers (X-Request-ID) for debugging

---

### Q19: How do you test API backward compatibility?

**Answer:**
- Maintain a baseline response snapshot for comparison
- Add new fields as optional (not required)
- Don't remove or rename existing fields
- Test old clients with new API versions
- Use semantic versioning (MAJOR.MINOR.PATCH)
- Verify deprecation headers in responses
- Test graceful degradation when new features are unavailable

---

### Q20: How do you test API concurrency and thread safety?

**Answer:**
- Use TestNG parallel execution: `parallel="methods" thread-count="10"`
- Test with multiple threads accessing the same resource
- Verify no race conditions in balance updates (banking)
- Test optimistic locking with version numbers
- Verify atomic operations (all-or-nothing)
- Test with concurrent cart modifications
- Verify database isolation levels (READ_COMMITTED, SERIALIZABLE)
- Use synchronization tests for inventory updates

```java
@Test(threadPoolSize = 10, invocationCount = 100)
public void testConcurrentTransfer() {
    // All threads transfer from same account
    // Verify final balance is correct (no race conditions)
}
```

---

## Key Takeaways

```
┌──────────────────────────────────────────────────────────────┐
│                    KEY TAKEAWAYS                                │
├──────────────────────────────────────────────────────────────┤
│                                                                 │
│  1. Real-world API testing requires complete workflow coverage │
│     — from authentication to final state verification.        │
│                                                                 │
│  2. Data flows between requests via environment variables     │
│     or test context, requiring careful lifecycle management.    │
│                                                                 │
│  3. Memory management matters: clean up large objects,         │
│     use streaming, and monitor heap usage.                    │
│                                                                 │
│  4. Security testing is critical: test SQL injection, XSS,    │
│     CSRF, rate limiting, and authentication bypass.           │
│                                                                 │
│  5. Idempotency and concurrency are production concerns       │
│     that must be tested with realistic scenarios.             │
│                                                                 │
│  6. Use both Postman (manual/exploratory) and REST Assured    │
│     (automated/CI) for comprehensive coverage.                │
│                                                                 │
│  7. POJOs and controllers provide maintainable, type-safe     │
│     test code that matches application architecture.          │
│                                                                 │
│  8. JSON structures must be validated against schemas         │
│     to ensure contract compliance.                            │
│                                                                 │
│  9. Error handling and negative testing are as important     │
│     as positive testing.                                        │
│                                                                 │
│  10. Performance and memory optimization should be part of    │
│      every test suite design.                                   │
│                                                                 │
└──────────────────────────────────────────────────────────────┘
```

---

**Happy Testing! 🧪**

*Master real-world API testing, and you master the art of quality assurance.*

---

**Last Updated:** June 2026
