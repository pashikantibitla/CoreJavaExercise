# 01 — API Testing Fundamentals

> **Topics:** API Testing Basics, REST/SOAP/GraphQL, HTTP Methods, Status Codes, Authentication, REST Principles, Interview FAQs
> **Target:** Beginner to Intermediate Testers and Developers

---

## Table of Contents

1. [What is API Testing](#1-what-is-api-testing)
2. [Types of APIs](#2-types-of-apis)
3. [HTTP Methods](#3-http-methods)
4. [HTTP Status Codes](#4-http-status-codes)
5. [Request and Response Structure](#5-request-and-response-structure)
6. [Authentication Methods](#6-authentication-methods)
7. [Content Types](#7-content-types)
8. [REST Principles](#8-rest-principles)
9. [RESTful Design Patterns](#9-restful-design-patterns)
10. [Path vs Query vs Body Parameters](#10-path-vs-query-vs-body-parameters)
11. [JSON vs XML](#11-json-vs-xml)
12. [Code Examples](#12-code-examples)
13. [Interview Questions](#13-interview-questions)
14. [Quick Reference](#14-quick-reference)
15. [Key Takeaways](#15-key-takeaways)

---

## 1. What is API Testing

### Definition

API Testing is a type of software testing that involves testing application programming interfaces (APIs) directly to validate functionality, reliability, performance, and security.

Unlike UI testing, API testing focuses on the **business logic layer** of the software architecture.

```
┌─────────────────────────────────────────────────────────────┐
│                    SOFTWARE ARCHITECTURE                       │
│                                                               │
│   ┌─────────────────────────────────────────────────────┐    │
│   │  Presentation Layer (UI)                           │    │
│   │  - Web pages, Mobile apps, Desktop apps            │    │
│   └──────────────────┬──────────────────────────────────┘    │
│                      │ calls                                │
│   ┌──────────────────▼──────────────────────────────────┐    │
│   │  Business Logic Layer (API)    ★ API Testing Here ★ │    │
│   │  - REST APIs, SOAP services, GraphQL                │    │
│   └──────────────────┬──────────────────────────────────┘    │
│                      │ queries                                │
│   ┌──────────────────▼──────────────────────────────────┐    │
│   │  Data Layer (Database)                               │    │
│   │  - MySQL, PostgreSQL, MongoDB, Redis                 │    │
│   └─────────────────────────────────────────────────────┘    │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

### Why API Testing is Important

```
┌─────────────────────────────────────────────────────────────┐
│              IMPORTANCE OF API TESTING                        │
├─────────────────────────────────────────────────────────────┤
│ 1. Early Testing        │ Test before UI is ready            │
│ 2. Time Efficiency      │ Faster than UI automation        │
│ 3. Language Independent │ Test Java API with Python scripts  │
│ 4. Better Coverage      │ Test error cases UI cannot reach   │
│ 5. Security             │ Expose vulnerabilities directly    │
│ 6. Integration          │ Verify microservices communicate   │
│ 7. Cost Reduction       │ Catch bugs early, cheaper to fix │
│ 8. Reusability          │ Same tests work across platforms   │
│ 9. Performance          │ Load test APIs directly            │
│ 10. Automation Ready    │ Easy to integrate with CI/CD       │
└─────────────────────────────────────────────────────────────┘
```

### What to Test in APIs

```
┌─────────────────────────────────────────────────────────────┐
│                   API TESTING CHECKLIST                       │
├─────────────────────────────────────────────────────────────┤
│ ✅ Functional Testing                                        │
│    • Verify correct output for valid input                   │
│    • Verify error handling for invalid input                 │
│    • Verify boundary conditions                             │
│                                                              │
│ ✅ Reliability Testing                                       │
│    • Test consistent behavior under load                     │
│    • Test timeout and retry scenarios                         │
│                                                              │
│ ✅ Load Testing                                              │
│    • Test performance under expected load                    │
│    • Test behavior under peak load                           │
│                                                              │
│ ✅ Security Testing                                          │
│    • Test authentication and authorization                    │
│    • Test SQL injection, XSS, CSRF                           │
│    • Test input validation                                   │
│                                                              │
│ ✅ Negative Testing                                          │
│    • Test with invalid data types                            │
│    • Test with missing required fields                       │
│    • Test with malformed requests                            │
│                                                              │
│ ✅ Integration Testing                                       │
│    • Test API-to-API communication                           │
│    • Test database interaction                               │
│    • Test third-party service integration                    │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. Types of APIs

### Overview

APIs can be categorized based on their architecture and communication protocols.

```
┌─────────────────────────────────────────────────────────────┐
│                    TYPES OF APIS                               │
│                                                                │
│    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐      │
│    │    REST     │    │    SOAP     │    │   GraphQL   │      │
│    │             │    │             │    │             │      │
│    │  • HTTP     │    │  • XML      │    │  • Query    │      │
│    │  • JSON     │    │  • WSDL     │    │  • Single   │      │
│    │  • Stateless│    │  • Envelope │    │    Endpoint │      │
│    │  • Resource │    │  • Protocol │    │  • Flexible │      │
│    └─────────────┘    └─────────────┘    └─────────────┘      │
│                                                                │
│    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐      │
│    │    RPC      │    │  gRPC       │    │  WebSocket  │      │
│    │             │    │             │    │             │      │
│    │  • Function │    │  • HTTP/2   │    │  • Full     │      │
│    │    calls    │    │  • Protobuf │    │    Duplex   │      │
│    │  • XML/JSON │    │  • Streaming│    │  • Realtime │      │
│    └─────────────┘    └─────────────┘    └─────────────┘      │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Detailed Comparison

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        REST vs SOAP vs GraphQL                                │
├──────────────────┬─────────────────┬─────────────────┬────────────────────┤
│ Feature          │ REST            │ SOAP            │ GraphQL            │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Protocol         │ HTTP            │ HTTP, SMTP,     │ HTTP               │
│                  │                 │ TCP, etc.       │                    │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Data Format      │ JSON, XML,      │ XML only        │ JSON               │
│                  │ HTML, Plain     │                 │                    │
│                  │ Text            │                 │                    │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Architecture     │ Resource-based  │ Service/        │ Query-based        │
│                  │                 │ Operation-based │                    │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Transport        │ Stateless       │ Can be          │ Stateless          │
│                  │                 │ stateful        │                    │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Security         │ HTTPS, OAuth,   │ WS-Security,    │ HTTPS, OAuth,      │
│                  │ JWT, API Keys   │ SSL, SAML       │ JWT                │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Performance      │ Lightweight,    │ Heavy XML       │ Efficient,         │
│                  │ fast            │ parsing, slower │ precise data       │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Caching          │ Easy (HTTP      │ Difficult       │ Requires custom    │
│                  │ cache headers)  │                 │ implementation     │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Flexibility      │ High            │ Low (strict     │ Very High          │
│                  │                 │ contract)       │                    │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Error Handling   │ HTTP Status     │ Built-in        │ Custom errors      │
│                  │ Codes           │ fault element   │ in response        │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Tooling          │ Postman,        │ SoapUI,         │ GraphiQL,          │
│                  │ curl, Swagger   │ XMLSpy          │ Apollo, Playground │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ Use Cases        │ Web services,   │ Enterprise,     │ Mobile apps,       │
│                  │ Mobile apps,    │ Banking,        │ Complex data       │
│                  │ Microservices   │ Payment systems │ requirements       │
├──────────────────┼─────────────────┼─────────────────┼────────────────────┤
│ When to Use      │ Public APIs,    │ Enterprise      │ Complex            │
│                  │ Microservices,  │ integrations,   │ frontends,         │
│                  │ Simple CRUD     │ ACID transactions│ social networks   │
└──────────────────┴─────────────────┴─────────────────┴────────────────────┘
```

### REST (Representational State Transfer)

REST is an architectural style, not a protocol. It uses standard HTTP methods and is resource-oriented.

```
┌─────────────────────────────────────────────────────────────┐
│                    REST ARCHITECTURE                             │
│                                                                │
│   Client                          Server                         │
│   ┌─────────────┐               ┌─────────────────────┐       │
│   │  Browser    │─── GET ──────▶│  /users             │       │
│   │  Mobile App │◀── 200 OK ────│  /users/123         │       │
│   │  Service    │               │  /orders            │       │
│   └─────────────┘─── POST ─────▶│  /orders/456/items  │       │
│                 │◀── 201 Created│                     │       │
│                 │               │  Resources expose  │       │
│                 │─── PUT ──────▶│  state via         │       │
│                 │◀── 200 OK ────│  representations   │       │
│                 │               │                     │       │
│                 │─── DELETE ───▶│  (JSON/XML/HTML)    │       │
│                 │◀── 204 NoContent                    │       │
│                 │               └─────────────────────┘       │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### SOAP (Simple Object Access Protocol)

SOAP is a protocol with strict standards. It uses XML for messages and typically operates over HTTP or SMTP.

```xml
<!-- SOAP Request Example -->
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <soap:Header>
        <auth:Credentials xmlns:auth="http://example.com/auth">
            <auth:Username>admin</auth:Username>
            <auth:Password>secret</auth:Password>
        </auth:Credentials>
    </soap:Header>
    <soap:Body>
        <GetUserDetails xmlns="http://example.com/users">
            <UserId>123</UserId>
        </GetUserDetails>
    </soap:Body>
</soap:Envelope>
```

```
┌─────────────────────────────────────────────────────────────┐
│                    SOAP MESSAGE STRUCTURE                      │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │  Envelope (mandatory)                                    │ │
│   │  ┌─────────────────────────────────────────────────────┐ │ │
│   │  │  Header (optional) — Auth, routing, etc.          │ │ │
│   │  └─────────────────────────────────────────────────────┘ │ │
│   │  ┌─────────────────────────────────────────────────────┐ │ │
│   │  │  Body (mandatory) — Actual request/response data    │ │ │
│   │  │  ┌─────────────────────────────────────────────┐   │ │ │
│   │  │  │  Fault (optional) — Error information        │   │ │ │
│   │  │  └─────────────────────────────────────────────┘   │ │ │
│   │  └─────────────────────────────────────────────────────┘ │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### GraphQL

GraphQL is a query language for APIs that allows clients to request exactly the data they need.

```
┌─────────────────────────────────────────────────────────────┐
│                    GRAPHQL ARCHITECTURE                        │
│                                                                │
│   Traditional REST:                    GraphQL:                │
│   ┌─────────┐    ┌─────────┐          ┌─────────┐             │
│   │ /user   │───▶│ Name    │          │ /graphql│             │
│   │ /posts  │───▶│ Posts   │          │         │             │
│   │ /friends│───▶│ Friends │          │  query {             │
│   │ /comments│───▶│ Comments│          │    user(id: 1) {     │
│   │         │    │         │          │      name            │
│   │ 3+ calls│    │         │          │      posts { title } │
│   └─────────┘    └─────────┘          │      friends { name }│
│                                        │    }                 │
│   Over-fetching!                       │  }                   │
│                                        │          │          │
│                                        │  Single call!        │
│                                        │  No over-fetching!   │
│                                        └─────────┘             │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

```graphql
# GraphQL Query Example
query GetUserWithPosts {
    user(id: "123") {
        id
        name
        email
        posts {
            id
            title
            comments {
                id
                text
            }
        }
    }
}

# GraphQL Mutation Example
mutation CreateUser {
    createUser(input: { name: "John", email: "john@example.com" }) {
        id
        name
        email
    }
}
```

---

## 3. HTTP Methods

### Overview

HTTP methods (also called verbs) indicate the desired action to be performed on a resource.

```
┌─────────────────────────────────────────────────────────────┐
│                    HTTP METHODS                                │
│                                                                │
│   ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐       │
│   │   GET   │  │  POST   │  │   PUT   │  │ DELETE  │       │
│   │         │  │         │  │         │  │         │       │
│   │ Retrieve│  │ Create  │  │ Update  │  │ Remove  │       │
│   │ Read    │  │         │  │ Replace │  │         │       │
│   └─────────┘  └─────────┘  └─────────┘  └─────────┘       │
│                                                                │
│   ┌─────────┐  ┌─────────┐  ┌─────────┐                    │
│   │  PATCH  │  │  HEAD   │  │ OPTIONS │                    │
│   │         │  │         │  │         │                    │
│   │ Partial │  │ Headers │  │ Check   │                    │
│   │ Update  │  │ Only    │  │ Methods │                    │
│   └─────────┘  └─────────┘  └─────────┘                    │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Comprehensive HTTP Methods Table

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        HTTP METHODS COMPARISON                                │
├─────────┬─────────────┬────────────┬────────────┬─────────────┬──────────────┤
│ Method  │ Purpose     │ Idempotent │ Safe       │ Cacheable   │ Body Allowed │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ GET     │ Retrieve    │ Yes        │ Yes        │ Yes         │ Should avoid │
│         │ resource    │            │            │             │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ POST    │ Create      │ No         │ No         │ Yes         │ Yes          │
│         │ resource    │            │            │ (sometimes) │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ PUT     │ Full update │ Yes        │ No         │ No          │ Yes          │
│         │ / Replace   │            │            │             │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ DELETE  │ Remove      │ Yes        │ No         │ No          │ Yes          │
│         │ resource    │            │            │             │ (sometimes)  │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ PATCH   │ Partial     │ No         │ No         │ No          │ Yes          │
│         │ update      │            │            │             │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ HEAD    │ Same as GET │ Yes        │ Yes        │ Yes         │ No           │
│         │ but no body │            │            │             │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ OPTIONS │ Get         │ Yes        │ Yes        │ No          │ Yes          │
│         │ supported   │            │            │             │              │
│         │ methods     │            │            │             │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ TRACE   │ Echo back   │ Yes        │ Yes        │ No          │ No           │
│         │ request     │            │            │             │              │
├─────────┼─────────────┼────────────┼────────────┼─────────────┼──────────────┤
│ CONNECT │ Establish   │ No         │ No         │ No          │ Yes          │
│         │ tunnel      │            │            │             │              │
└─────────┴─────────────┴────────────┴────────────┴─────────────┴──────────────┘
```

### Definitions

**Idempotent:** Multiple identical requests produce the same result as a single request.
- `GET`, `PUT`, `DELETE`, `HEAD`, `OPTIONS`, `TRACE` are idempotent.
- `POST`, `PATCH` are NOT idempotent.

**Safe:** The method does not modify the server state.
- `GET`, `HEAD`, `OPTIONS`, `TRACE` are safe.
- `POST`, `PUT`, `DELETE`, `PATCH` are NOT safe.

**Cacheable:** The response can be stored by a cache for reuse.
- `GET` and `HEAD` are cacheable by default.
- `POST` can be cacheable if explicit cache headers are present.
- `PUT`, `DELETE`, `PATCH` are generally not cacheable.

### Method Examples

#### GET
```http
GET /users/123 HTTP/1.1
Host: api.example.com
Accept: application/json
```

#### POST
```http
POST /users HTTP/1.1
Host: api.example.com
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com"
}
```

#### PUT
```http
PUT /users/123 HTTP/1.1
Host: api.example.com
Content-Type: application/json

{
    "id": 123,
    "name": "John Updated",
    "email": "john.updated@example.com"
}
```

#### DELETE
```http
DELETE /users/123 HTTP/1.1
Host: api.example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
```

#### PATCH
```http
PATCH /users/123 HTTP/1.1
Host: api.example.com
Content-Type: application/json

{
    "email": "new.email@example.com"
}
```

#### HEAD
```http
HEAD /users/123 HTTP/1.1
Host: api.example.com
```
Response returns only headers, no body.

#### OPTIONS
```http
OPTIONS /users HTTP/1.1
Host: api.example.com
```
Response:
```http
HTTP/1.1 200 OK
Allow: GET, POST, HEAD, OPTIONS
```

---

## 4. HTTP Status Codes

### Overview

HTTP status codes are three-digit numbers returned by the server to indicate the result of a request.

```
┌─────────────────────────────────────────────────────────────┐
│                    HTTP STATUS CODE CLASSES                     │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 1xx — Informational   │ Request received, continuing    │ │
│   ├─────────────────────────────────────────────────────────┤ │
│   │ 2xx — Success          │ Request successfully completed  │ │
│   ├─────────────────────────────────────────────────────────┤ │
│   │ 3xx — Redirection      │ Further action needed           │ │
│   ├─────────────────────────────────────────────────────────┤ │
│   │ 4xx — Client Error     │ Request has errors              │ │
│   ├─────────────────────────────────────────────────────────┤ │
│   │ 5xx — Server Error     │ Server failed to process        │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Detailed Status Codes

#### 1xx Informational

```
┌─────────────────────────────────────────────────────────────┐
│                    1xx INFORMATIONAL CODES                      │
├─────────┬─────────────────────────────────────────────────────┤
│ Code    │ Meaning                                             │
├─────────┼─────────────────────────────────────────────────────┤
│ 100     │ Continue — Client should continue with request     │
│ 101     │ Switching Protocols — Server agrees to upgrade     │
│ 102     │ Processing — WebDAV; request is being processed    │
│ 103     │ Early Hints — Preload resources while preparing    │
└─────────┴─────────────────────────────────────────────────────┘
```

#### 2xx Success

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                            2xx SUCCESS CODES                                  │
├─────────┬─────────────────────────────────────┬───────────────────────────────┤
│ Code    │ Meaning                             │ Example                       │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 200     │ OK — Request succeeded              │ GET /users/123 returns user   │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 201     │ Created — Resource created          │ POST /users returns new user  │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 202     │ Accepted — Request accepted for     │ Async processing queued       │
│         │ async processing                    │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 204     │ No Content — Success, no body         │ DELETE /users/123 succeeded │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 206     │ Partial Content — Range request       │ Downloading file in chunks    │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 200     │ OK — Request succeeded              │ GET /users/123 returns user   │
└─────────┴─────────────────────────────────────┴───────────────────────────────┘
```

#### 3xx Redirection

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          3xx REDIRECTION CODES                                │
├─────────┬─────────────────────────────────────┬───────────────────────────────┤
│ Code    │ Meaning                             │ Example                       │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 301     │ Moved Permanently — Resource        │ URL permanently changed       │
│         │ moved to new URL                    │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 302     │ Found — Temporary redirect            │ Login redirect to home page   │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 304     │ Not Modified — Cached version is      │ Browser uses cached data      │
│         │ still valid                         │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 307     │ Temporary Redirect — Same method      │ Retry with same method        │
│         │ must be used                        │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 308     │ Permanent Redirect — Same method      │ Like 301 but method preserved │
└─────────┴─────────────────────────────────────┴───────────────────────────────┘
```

#### 4xx Client Errors

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          4xx CLIENT ERROR CODES                               │
├─────────┬─────────────────────────────────────┬───────────────────────────────┤
│ Code    │ Meaning                             │ Example                       │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 400     │ Bad Request — Invalid syntax or     │ Missing required field        │
│         │ malformed request                   │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 401     │ Unauthorized — Authentication       │ Missing or invalid token      │
│         │ required                            │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 403     │ Forbidden — Authenticated but not   │ User cannot access admin      │
│         │ authorized                          │ resources                     │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 404     │ Not Found — Resource does not exist │ /users/999 does not exist    │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 405     │ Method Not Allowed — HTTP method    │ POST to /users/123 (read)    │
│         │ not supported on resource           │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 406     │ Not Acceptable — Cannot satisfy     │ Requesting XML but server     │
│         │ Accept header                       │ only supports JSON            │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 408     │ Request Timeout — Server gave up    │ Slow network connection       │
│         │ waiting for client                  │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 409     │ Conflict — Request conflicts with   │ Duplicate email on create     │
│         │ current state                       │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 410     │ Gone — Resource permanently         │ Deleted product page          │
│         │ removed                             │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 422     │ Unprocessable Entity — Semantic     │ Valid JSON but invalid        │
│         │ errors in request body              │ business logic                │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 429     │ Too Many Requests — Rate limit      │ API quota exceeded            │
│         │ exceeded                            │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 451     │ Unavailable For Legal Reasons —     │ Censored content              │
│         │ Legal restriction                   │                               │
└─────────┴─────────────────────────────────────┴───────────────────────────────┘
```

#### 5xx Server Errors

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          5xx SERVER ERROR CODES                               │
├─────────┬─────────────────────────────────────┬───────────────────────────────┤
│ Code    │ Meaning                             │ Example                       │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 500     │ Internal Server Error — Unexpected  │ NullPointerException in       │
│         │ server condition                    │ backend code                  │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 501     │ Not Implemented — Server does not   │ HTTP method not supported     │
│         │ support the functionality           │ by server                     │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 502     │ Bad Gateway — Invalid response from │ Upstream server error         │
│         │ upstream server                     │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 503     │ Service Unavailable — Server        │ Maintenance mode, overloaded  │
│         │ temporarily overloaded or down      │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 504     │ Gateway Timeout — Upstream server   │ Database connection timeout   │
│         │ did not respond in time             │                               │
├─────────┼─────────────────────────────────────┼───────────────────────────────┤
│ 505     │ HTTP Version Not Supported          │ Server does not support       │
│         │                                     │ HTTP/2                        │
└─────────┴─────────────────────────────────────┴───────────────────────────────┘
```

---

## 5. Request and Response Structure

### HTTP Request Structure

```
┌─────────────────────────────────────────────────────────────┐
│                    HTTP REQUEST STRUCTURE                      │
│                                                                │
│   POST /api/v1/users HTTP/1.1        ← Request Line          │
│   Host: api.example.com              ← Headers               │
│   Content-Type: application/json                               │
│   Authorization: Bearer eyJhbG...                              │
│   Accept: application/json                                     │
│   User-Agent: PostmanRuntime/7.32.1                            │
│   Content-Length: 56                                           │
│                                                                │
│   {                                  ← Body (Optional)        │
│       "name": "John",                                          │
│       "email": "john@example.com"                              │
│   }                                                            │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### HTTP Response Structure

```
┌─────────────────────────────────────────────────────────────┐
│                    HTTP RESPONSE STRUCTURE                   │
│                                                                │
│   HTTP/1.1 200 OK                    ← Status Line             │
│   Content-Type: application/json       ← Headers               │
│   Content-Length: 234                                          │
│   Date: Sun, 14 Jun 2026 10:30:00 GMT                          │
│   Server: nginx/1.18.0                                         │
│   X-Request-Id: abc-123-def-456                                │
│   Cache-Control: no-cache                                      │
│                                                                │
│   {                                  ← Body (Optional)        │
│       "id": 123,                                               │
│       "name": "John",                                          │
│       "email": "john@example.com"                              │
│   }                                                            │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Common Request Headers

```
┌─────────────────────────────────────────────────────────────┐
│                    COMMON REQUEST HEADERS                      │
├──────────────────┬──────────────────────────────────────────┤
│ Header           │ Purpose                                  │
├──────────────────┼──────────────────────────────────────────┤
│ Host             │ Target server domain                     │
│ Content-Type     │ Format of request body                   │
│ Content-Length   │ Size of request body in bytes            │
│ Authorization    │ Authentication credentials               │
│ Accept           │ Preferred response format (MIME type)    │
│ Accept-Encoding  │ Preferred compression (gzip, deflate)    │
│ User-Agent       │ Client software identity                 │
│ Cookie           │ Session cookies from server              │
│ X-Request-Id     │ Unique request identifier (traceability) │
│ If-None-Match    │ Conditional request with ETag            │
│ Cache-Control    │ Directives for caching mechanisms        │
│ Origin           │ CORS origin of the request               │
│ Referer          │ Previous page URL                        │
└──────────────────┴──────────────────────────────────────────┘
```

### Common Response Headers

```
┌─────────────────────────────────────────────────────────────┐
│                    COMMON RESPONSE HEADERS                     │
├──────────────────┬──────────────────────────────────────────┤
│ Header           │ Purpose                                  │
├──────────────────┼──────────────────────────────────────────┤
│ Content-Type     │ Format of response body                  │
│ Content-Length   │ Size of response body in bytes           │
│ Date             │ Server response timestamp                │
│ Server           │ Server software identity                 │
│ Set-Cookie       │ Session cookie to store                  │
│ Location         │ Redirect URL (for 201, 301, 302)         │
│ ETag             │ Entity tag for caching                   │
│ Last-Modified    │ Last modification timestamp              │
│ Cache-Control    │ Caching directives                       │
│ X-Request-Id     │ Echoes request ID for tracing            │
│ Access-Control-  │ CORS allowed origins                     │
│   Allow-Origin   │                                          │
│ WWW-Authenticate │ Authentication scheme (for 401)          │
│ Retry-After      │ Seconds to wait before retry (503, 429)  │
└──────────────────┴──────────────────────────────────────────┘
```

### Parameters

```
┌─────────────────────────────────────────────────────────────┐
│                    PARAMETER TYPES                             │
│                                                                │
│   1. PATH PARAMETERS                                           │
│      URL: /users/{id}                                          │
│      Example: /users/123                                       │
│      Purpose: Identify a specific resource                   │
│                                                                │
│   2. QUERY PARAMETERS (Query String)                         │
│      URL: /users?page=1&limit=10&sort=asc                     │
│      Purpose: Filter, sort, paginate, search                 │
│                                                                │
│   3. HEADER PARAMETERS                                         │
│      X-Api-Key: abc123                                         │
│      Purpose: Authentication, metadata, versioning           │
│                                                                │
│   4. COOKIE PARAMETERS                                       │
│      Cookie: sessionId=xyz789                                  │
│      Purpose: Session management, tracking                   │
│                                                                │
│   5. REQUEST BODY                                              │
│      JSON/XML payload in POST/PUT/PATCH                      │
│      Purpose: Create or update resource data                 │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 6. Authentication Methods

### Overview

API authentication verifies the identity of the client making the request.

```
┌─────────────────────────────────────────────────────────────┐
│                    AUTHENTICATION METHODS                      │
│                                                                │
│   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐      │
│   │ Basic Auth  │    │ Bearer Token│    │    OAuth    │      │
│   │             │    │             │    │             │      │
│   │ Base64      │    │ JWT / API   │    │ Delegated   │      │
│   │ Username:   │    │ Key         │    │ Access via  │      │
│   │ Password    │    │             │    │ Provider    │      │
│   └─────────────┘    └─────────────┘    └─────────────┘      │
│                                                                │
│   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐      │
│   │   API Key   │    │     JWT     │    │  OAuth 2.0  │      │
│   │             │    │             │    │             │      │
│   │ Simple Key  │    │ Signed      │    │ Authorization│     │
│   │ in Header   │    │ Token       │    │ Code Flow   │      │
│   └─────────────┘    └─────────────┘    └─────────────┘      │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Basic Authentication

```
┌─────────────────────────────────────────────────────────────┐
│                    BASIC AUTHENTICATION FLOW                   │
│                                                                │
│   Client                              Server                   │
│   ┌─────────┐                         ┌─────────┐             │
│   │         │─── 1. Request ────────▶│         │             │
│   │         │    (no credentials)     │         │             │
│   │         │                         │         │             │
│   │         │◀── 2. 401 Unauthorized ─│         │             │
│   │         │    WWW-Authenticate:    │         │             │
│   │         │    Basic realm="API"    │         │             │
│   │         │                         │         │             │
│   │         │─── 3. Request ────────▶│         │             │
│   │         │    Authorization:       │         │             │
│   │         │    Basic base64(user:pass)                        │
│   │         │                         │         │             │
│   │         │◀── 4. 200 OK ─────────│         │             │
│   │         │                         │         │             │
│   └─────────┘                         └─────────┘             │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

```http
# Basic Auth Request
GET /api/protected HTTP/1.1
Host: api.example.com
Authorization: Basic dXNlcjpwYXNzd29yZA==
# base64("user:password") = "dXNlcjpwYXNzd29yZA=="
```

### Bearer Token / API Key

```
┌─────────────────────────────────────────────────────────────┐
│                    BEARER TOKEN FLOW                           │
│                                                                │
│   Client                              Server                   │
│   ┌─────────┐                         ┌─────────┐             │
│   │         │─── 1. Login/Register ──▶│         │             │
│   │         │    (credentials)          │         │             │
│   │         │                         │         │             │
│   │         │◀── 2. Token ────────────│         │             │
│   │         │    { "token": "abc123" }│         │             │
│   │         │                         │         │             │
│   │         │─── 3. API Request ────▶│         │             │
│   │         │    Authorization:       │         │             │
│   │         │    Bearer abc123        │         │             │
│   │         │                         │         │             │
│   │         │◀── 4. 200 OK ─────────│         │             │
│   │         │                         │         │             │
│   └─────────┘                         └─────────┘             │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

```http
# Bearer Token Request
GET /api/users HTTP/1.1
Host: api.example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

```http
# API Key in Header
GET /api/users HTTP/1.1
Host: api.example.com
X-API-Key: abc123def456ghi789
```

### OAuth 2.0 Authorization Code Flow

```
┌─────────────────────────────────────────────────────────────┐
│              OAUTH 2.0 AUTHORIZATION CODE FLOW                │
│                                                                │
│   ┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐│
│   │  User   │    │  Client │    │  Auth   │    │Resource ││
│   │ (Browser)│    │ (App)   │    │ Server  │    │ Server  ││
│   └────┬────┘    └────┬────┘    └────┬────┘    └────┬────┘│
│        │              │              │              │       │
│        │ 1. Click Login│              │              │       │
│        │─────────────▶│              │              │       │
│        │              │              │              │       │
│        │ 2. Redirect to│              │              │       │
│        │   Auth Server │              │              │       │
│        │◀─────────────│              │              │       │
│        │              │              │              │       │
│        │ 3. Login &    │              │              │       │
│        │   Consent     │              │              │       │
│        │─────────────▶│              │              │       │
│        │              │              │              │       │
│        │ 4. Auth Code  │              │              │       │
│        │◀─────────────│              │              │       │
│        │              │              │              │       │
│        │              │ 5. Send Code │              │       │
│        │              │ + Client Secret              │       │
│        │              │─────────────▶│              │       │
│        │              │              │              │       │
│        │              │ 6. Access Token              │       │
│        │              │◀─────────────│              │       │
│        │              │              │              │       │
│        │              │ 7. API Call  │              │       │
│        │              │  Bearer Token              │       │
│        │              │────────────────────────────▶│       │
│        │              │              │              │       │
│        │              │ 8. Response  │              │       │
│        │              │◀────────────────────────────│       │
│        │              │              │              │       │
└─────────────────────────────────────────────────────────────┘
```

### OAuth 2.0 Grant Types

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         OAUTH 2.0 GRANT TYPES                                 │
├─────────────────────────────┬───────────────────────────────────────────────┤
│ Grant Type                  │ Use Case                                      │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ Authorization Code          │ Web apps, mobile apps (most secure)           │
│                             │ • User authenticates with provider            │
│                             │ • Authorization code exchanged for token        │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ Implicit (Deprecated)       │ Single-page apps (legacy)                     │
│                             │ • Token returned directly in redirect         │
│                             │ • Less secure, now replaced by PKCE           │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ Password Credentials        │ Trusted first-party apps only                 │
│                             │ • User gives credentials directly to app      │
│                             │ • App exchanges for token                     │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ Client Credentials          │ Server-to-server communication                │
│                             │ • No user involved                            │
│                             │ • App uses its own credentials                │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ Device Code                 │ Input-constrained devices (TV, IoT)           │
│                             │ • User completes auth on secondary device     │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ Refresh Token               │ Renew access without re-login                 │
│                             │ • Exchange refresh token for new access token │
└─────────────────────────────┴───────────────────────────────────────────────┘
```

### JWT (JSON Web Token)

```
┌─────────────────────────────────────────────────────────────┐
│                    JWT STRUCTURE                               │
│                                                                │
│   eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.                      │
│   eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.  │
│   SflKxwRJSMeKKF2QT4fwpMe...                                   │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │  HEADER (Base64Url)                                      │ │
│   │  {                                                      │ │
│   │      "alg": "HS256",   ← Algorithm                     │ │
│   │      "typ": "JWT"      ← Type                          │ │
│   │  }                                                      │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │  PAYLOAD / CLAIMS (Base64Url)                            │ │
│   │  {                                                      │ │
│   │      "sub": "1234567890",  ← Subject (user ID)         │ │
│   │      "name": "John Doe",                                 │ │
│   │      "iat": 1516239022,    ← Issued at                 │ │
│   │      "exp": 1516242622,    ← Expiration                │ │
│   │      "role": "admin"       ← Custom claim                │ │
│   │  }                                                      │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │  SIGNATURE (Base64Url)                                   │ │
│   │  HMACSHA256(                                            │ │
│   │      base64Url(header) + "." + base64Url(payload),     │ │
│   │      secret                                             │ │
│   │  )                                                      │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 7. Content Types

### MIME Types

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         COMMON API CONTENT TYPES                              │
├─────────────────────────────┬───────────────────────────────────────────────┤
│ Content-Type                │ Description                                   │
├─────────────────────────────┼───────────────────────────────────────────────┤
│ application/json            │ JSON format (most common for REST APIs)       │
│ application/xml             │ XML format (common for SOAP APIs)             │
│ text/plain                  │ Plain text                                    │
│ text/html                   │ HTML content                                  │
│ application/x-www-form-urlencoded│ HTML form submission (key=value pairs)   │
│ multipart/form-data         │ Form data with file uploads                   │
│ application/octet-stream    │ Binary data, file downloads                   │
│ application/pdf             │ PDF documents                                 │
│ image/png, image/jpeg       │ Image files                                   │
│ text/csv                    │ CSV data                                      │
│ application/graphql         │ GraphQL queries                               │
│ application/ld+json         │ JSON-LD (Linked Data)                         │
└─────────────────────────────┴───────────────────────────────────────────────┘
```

### JSON Example

```json
{
    "id": 123,
    "name": "John Doe",
    "email": "john@example.com",
    "isActive": true,
    "roles": ["admin", "user"],
    "profile": {
        "age": 30,
        "city": "New York"
    },
    "createdAt": "2026-06-14T10:30:00Z"
}
```

### XML Example

```xml
<?xml version="1.0" encoding="UTF-8"?>
<user>
    <id>123</id>
    <name>John Doe</name>
    <email>john@example.com</email>
    <isActive>true</isActive>
    <roles>
        <role>admin</role>
        <role>user</role>
    </roles>
    <profile>
        <age>30</age>
        <city>New York</city>
    </profile>
    <createdAt>2026-06-14T10:30:00Z</createdAt>
</user>
```

### Form Data

```http
POST /api/upload HTTP/1.1
Host: api.example.com
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary

------WebKitFormBoundary
Content-Disposition: form-data; name="file"; filename="report.pdf"
Content-Type: application/pdf

<binary data>
------WebKitFormBoundary
Content-Disposition: form-data; name="description"

Monthly sales report
------WebKitFormBoundary--
```

---

## 8. REST Principles

### The Six Constraints of REST

```
┌─────────────────────────────────────────────────────────────┐
│                    REST ARCHITECTURAL CONSTRAINTS               │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 1. UNIFORM INTERFACE                                     │ │
│   │    • Resource identification (URI)                      │ │
│   │    • Resource manipulation through representations      │ │
│   │    • Self-descriptive messages                          │ │
│   │    • Hypermedia as the engine of application state      │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 2. STATELESSNESS                                         │ │
│   │    • Each request contains all information needed       │ │
│   │    • No server-side session storage                     │ │
│   │    • Every request is independent                       │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 3. CACHEABILITY                                          │ │
│   │    • Responses must define themselves as cacheable       │ │
│   │    • Improves scalability and performance               │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 4. CLIENT-SERVER ARCHITECTURE                            │ │
│   │    • Separation of concerns                             │ │
│   │    • Client handles UI, Server handles data              │ │
│   │    • Independent evolution of both layers               │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 5. LAYERED SYSTEM                                        │ │
│   │    • Client cannot tell if connected directly to         │ │
│   │      end server or to an intermediary                   │ │
│   │    • Load balancers, caches, proxies, gateways          │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ 6. CODE ON DEMAND (Optional)                             │ │
│   │    • Server can extend client functionality by           │ │
│   │      sending executable code (e.g., JavaScript)         │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Statelessness Explained

```
┌─────────────────────────────────────────────────────────────┐
│                    STATELESS vs STATEFUL                        │
│                                                                │
│   STATEFUL (Session-based):                                    │
│   ┌─────────┐         ┌─────────┐         ┌─────────┐       │
│   │ Client  │──Req1──▶│ Server  │──Store──▶│ Session │       │
│   │         │         │         │   Data   │  DB     │       │
│   │         │──Req2──▶│ Server  │──Read──▶│ Session │       │
│   │         │         │         │   Data   │  DB     │       │
│   │         │──Req3──▶│ Server  │──Read──▶│ Session │       │
│   │         │         │         │   Data   │  DB     │       │
│   └─────────┘         └─────────┘         └─────────┘       │
│                                                                │
│   STATELESS (Token-based):                                   │
│   ┌─────────┐         ┌─────────┐                            │
│   │ Client  │──Req1──▶│ Server  │                            │
│   │         │         │         │                            │
│   │         │──Req2──▶│ Server  │  Each request has full    │
│   │         │         │         │  context (Token + Headers) │
│   │         │──Req3──▶│ Server  │                            │
│   │         │         │         │                            │
│   └─────────┘         └─────────┘                            │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 9. RESTful Design Patterns

### URL Naming Conventions

```
┌─────────────────────────────────────────────────────────────┐
│                    REST URL NAMING CONVENTIONS                 │
│                                                                │
│   ✅ CORRECT                      ❌ INCORRECT                │
│   ─────────────────────────────────────────────────────────    │
│   /users                          /getUsers                    │
│   /users/123                      /user/123                  │
│   /users/123/orders               /getUserOrders/123           │
│   /orders?status=pending          /orders/pending              │
│   /users/123/posts                /getPostsByUser/123        │
│   /products?category=electronics  /products/electronics        │
│                                                                │
│   RULES:                                                       │
│   • Use nouns, not verbs                                       │
│   • Use plural nouns                                           │
│   • Use lowercase letters                                      │
│   • Use hyphens for multi-word resources (e.g., /user-profiles) │
│   • Use nested structure for relationships                     │
│   • Use query parameters for filtering, sorting, pagination    │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### HTTP Method + URL Mapping

```
┌─────────────────────────────────────────────────────────────┐
│                    REST RESOURCE MAPPING                       │
│                                                                │
│   Resource: /users                                             │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ GET    /users         → List all users                  │ │
│   │ POST   /users         → Create new user                 │ │
│   │ GET    /users/123     → Get user with ID 123            │ │
│   │ PUT    /users/123     → Update user 123 (full)          │ │
│   │ PATCH  /users/123     → Partial update user 123         │ │
│   │ DELETE /users/123     → Delete user 123                 │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
│   Resource: /users/123/orders                                  │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │ GET    /users/123/orders     → List orders of user 123 │ │
│   │ POST   /users/123/orders     → Create order for user 123│ │
│   │ GET    /users/123/orders/456 → Get order 456 of user 123│ │
│   │ PUT    /users/123/orders/456 → Update order 456         │ │
│   │ DELETE /users/123/orders/456 → Delete order 456         │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Pagination, Filtering, Sorting

```
┌─────────────────────────────────────────────────────────────┐
│                    QUERY PARAMETER PATTERNS                    │
│                                                                │
│   PAGINATION:                                                  │
│   GET /users?page=1&limit=10                                  │
│   GET /users?offset=0&limit=10                                  │
│   GET /users?cursor=abc123&limit=10  (Cursor-based)           │
│                                                                │
│   FILTERING:                                                   │
│   GET /users?status=active&role=admin                         │
│   GET /orders?date_from=2026-01-01&date_to=2026-06-30         │
│   GET /products?price_min=100&price_max=500                   │
│                                                                │
│   SORTING:                                                     │
│   GET /users?sort=name                                          │
│   GET /users?sort=-created_at  (Descending)                   │
│   GET /users?sort=age&sort=-name  (Multi-field)              │
│                                                                │
│   SEARCHING:                                                   │
│   GET /users?q=john                                             │
│   GET /products?search=laptop&category=electronics            │
│                                                                │
│   FIELD SELECTION:                                             │
│   GET /users?fields=id,name,email                              │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 10. Path vs Query vs Body Parameters

### Comparison

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                    PARAMETER TYPE COMPARISON                                  │
├──────────────────┬──────────────────┬──────────────────┬──────────────────────┤
│ Aspect           │ Path Parameters  │ Query Parameters │ Request Body         │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Location         │ URL path         │ URL after ?      │ HTTP message body    │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Syntax           │ /users/{id}      │ ?key=value&key2=v│ JSON, XML, form-data │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Visibility       │ Visible in URL   │ Visible in URL   │ Not visible in URL   │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Use for          │ Resource ID      │ Filtering, sort, │ Complex data, create │
│                  │                  │ pagination, search│ update payloads     │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ HTTP Methods     │ GET, PUT, DELETE │ GET              │ POST, PUT, PATCH     │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Required?        │ Usually required │ Optional         │ Required for writes  │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Security         │ Can expose IDs   │ Can expose data  │ More secure (HTTPS)  │
├──────────────────┼──────────────────┼──────────────────┼──────────────────────┤
│ Example          │ /users/123       │ /users?active=1  │ { "name": "John" }   │
└──────────────────┴──────────────────┴──────────────────┴──────────────────────┘
```

### Example Request with All Parameter Types

```http
POST /api/v1/users/123/orders?priority=high&notify=true HTTP/1.1
Host: api.example.com
Content-Type: application/json
Authorization: Bearer token123
X-Request-Id: req-abc-456

{
    "productId": 789,
    "quantity": 2,
    "shippingAddress": {
        "street": "123 Main St",
        "city": "New York",
        "zip": "10001"
    }
}
```

```
┌─────────────────────────────────────────────────────────────┐
│                    PARAMETER BREAKDOWN                         │
│                                                                │
│   Path Parameters:                                             │
│   • /users/123       → userId = 123                          │
│   • /orders          → resource = orders                     │
│                                                                │
│   Query Parameters:                                            │
│   • priority=high    → filter by priority                    │
│   • notify=true      → flag to send notification             │
│                                                                │
│   Header Parameters:                                           │
│   • Authorization    → Bearer token for auth               │
│   • X-Request-Id     → Traceability ID                       │
│   • Content-Type     → Body format is JSON                   │
│                                                                │
│   Request Body:                                                │
│   • productId, quantity, shippingAddress                   │
│   • Complex nested object for order creation                 │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 11. JSON vs XML

### Comparison

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                            JSON vs XML                                        │
├──────────────────────┬──────────────────────────┬─────────────────────────────┤
│ Feature              │ JSON                     │ XML                         │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Format               │ Key-value pairs          │ Tag-based markup            │
│ Readability          │ Easy, concise            │ Verbose, more structured    │
│ Data Types           │ String, Number, Boolean, │ All data is text, requires  │
│                      │ Array, Object, null      │ schema for type validation  │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Parsing              │ Native in JavaScript,    │ Requires XML parser (DOM,   │
│                      │ lightweight parsers      │ SAX, StAX)                  │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ File Size            │ Smaller (less verbose)   │ Larger (opening+closing     │
│                      │                          │ tags)                       │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Schema Validation    │ JSON Schema (optional)   │ XML Schema (XSD) - strong   │
│                      │                          │ validation                  │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Comments             │ Not supported            │ Supported <!-- comment -->  │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Namespaces           │ Not supported            │ Supported (essential for    │
│                      │                          │ SOAP)                       │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Extensibility        │ Less extensible          │ Highly extensible           │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Tooling              │ Native browser support,  │ Mature enterprise tools       │
│                      │ Postman, curl            │ (XMLSpy, SoapUI)            │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ Use Cases            │ REST APIs, Web apps,     │ SOAP services, Enterprise   │
│                      │ Mobile apps, Config files│ integration, Config files   │
├──────────────────────┼──────────────────────────┼─────────────────────────────┤
│ When to Use          │ Modern APIs, JavaScript  │ Enterprise systems, strict  │
│                      │ ecosystems, microservices│ contracts, legacy systems   │
└──────────────────────┴──────────────────────────┴─────────────────────────────┘
```

---

## 12. Code Examples

### JavaScript (Node.js / Fetch API)

```javascript
// GET Request with Fetch API
async function getUser(userId) {
    const response = await fetch(`https://api.example.com/users/${userId}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        }
    });
    
    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    
    return await response.json();
}

// POST Request with JSON Body
async function createUser(userData) {
    const response = await fetch('https://api.example.com/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        },
        body: JSON.stringify(userData)
    });
    
    return await response.json();
}

// PUT Request
async function updateUser(userId, userData) {
    const response = await fetch(`https://api.example.com/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        },
        body: JSON.stringify(userData)
    });
    
    return await response.json();
}

// DELETE Request
async function deleteUser(userId) {
    const response = await fetch(`https://api.example.com/users/${userId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + getToken()
        }
    });
    
    return response.status === 204;
}

// PATCH Request
async function patchUser(userId, partialData) {
    const response = await fetch(`https://api.example.com/users/${userId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        },
        body: JSON.stringify(partialData)
    });
    
    return await response.json();
}

// Form Data Upload
async function uploadFile(file, description) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('description', description);
    
    const response = await fetch('https://api.example.com/upload', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + getToken()
        },
        body: formData
    });
    
    return await response.json();
}
```

### Java (HttpClient)

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {
    
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    // GET Request
    public static String getUser(String userId, String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/users/" + userId))
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }
        
        return response.body();
    }
    
    // POST Request with JSON
    public static String createUser(String jsonBody, String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/users"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
    
    // PUT Request
    public static String updateUser(String userId, String jsonBody, String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/users/" + userId))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
    
    // DELETE Request
    public static boolean deleteUser(String userId, String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/users/" + userId))
                .header("Authorization", "Bearer " + token)
                .DELETE()
                .build();
        
        HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 204;
    }
}
```

### Java (with RestAssured)

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {
    
    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.example.com";
        
        // GET with validation
        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/users/123")
        .then()
            .statusCode(200)
            .body("id", equalTo(123))
            .body("name", notNullValue())
            .body("email", containsString("@"));
        
        // POST with JSON body
        String requestBody = "{\"name\":\"John\",\"email\":\"john@example.com\"}";
        
        Response response = given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .extract()
            .response();
        
        System.out.println("Created user ID: " + response.jsonPath().getString("id"));
        
        // PUT
        given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body("{\"name\":\"John Updated\"}")
        .when()
            .put("/users/123")
        .then()
            .statusCode(200);
        
        // DELETE
        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .delete("/users/123")
        .then()
            .statusCode(204);
    }
}
```

### JavaScript (Axios)

```javascript
const axios = require('axios');

const api = axios.create({
    baseURL: 'https://api.example.com',
    timeout: 10000,
    headers: {
        'Accept': 'application/json'
    }
});

// Add request interceptor for auth token
api.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

// GET
async function getUser(userId) {
    try {
        const response = await api.get(`/users/${userId}`);
        return response.data;
    } catch (error) {
        console.error('GET failed:', error.response?.status, error.message);
        throw error;
    }
}

// POST
async function createUser(userData) {
    const response = await api.post('/users', userData);
    return response.data;
}

// PUT
async function updateUser(userId, userData) {
    const response = await api.put(`/users/${userId}`, userData);
    return response.data;
}

// DELETE
async function deleteUser(userId) {
    const response = await api.delete(`/users/${userId}`);
    return response.status === 204;
}

// PATCH
async function patchUser(userId, partialData) {
    const response = await api.patch(`/users/${userId}`, partialData);
    return response.data;
}
```

---

## 13. Interview Questions

### Basic Level

```
┌─────────────────────────────────────────────────────────────┐
│                    BASIC LEVEL QUESTIONS                      │
│                                                                │
│  Q1. What is an API?                                           │
│  A. An API (Application Programming Interface) is a set of     │
│     rules and protocols that allows different software         │
│     applications to communicate with each other.             │
│                                                                │
│  Q2. What is API Testing?                                     │
│  A. API Testing is software testing that validates APIs       │
│     directly — checking functionality, reliability,             │
│     performance, and security without using the UI.          │
│                                                                │
│  Q3. What are the types of APIs?                              │
│  A. REST, SOAP, GraphQL, RPC, gRPC, and WebSocket.            │
│                                                                │
│  Q4. What is the difference between REST and SOAP?            │
│  A. REST is an architectural style using HTTP/JSON,           │
│     lightweight and flexible. SOAP is a protocol using        │
│     XML with strict standards and built-in security.          │
│                                                                │
│  Q5. What are HTTP methods and their purposes?                │
│  A. GET (read), POST (create), PUT (update/replace),        │
│     DELETE (remove), PATCH (partial update), HEAD (headers    │
│     only), OPTIONS (supported methods).                       │
│                                                                │
│  Q6. What is idempotence? Which methods are idempotent?     │
│  A. An idempotent operation produces the same result           │
│     regardless of how many times it is called. GET, PUT,      │
│     DELETE, HEAD, OPTIONS, TRACE are idempotent. POST and    │
│     PATCH are NOT idempotent.                                 │
│                                                                │
│  Q7. What are safe HTTP methods?                              │
│  A. Safe methods do not modify server state. GET, HEAD,     │
│     OPTIONS, TRACE are safe.                                  │
│                                                                │
│  Q8. What are the main parts of an HTTP request?            │
│  A. Request line (method + URL + version), Headers, and       │
│     optional Body.                                            │
│                                                                │
│  Q9. What are the main parts of an HTTP response?           │
│  A. Status line (version + status code + message), Headers,   │
│     and optional Body.                                        │
│                                                                │
│  Q10. What is the difference between 401 and 403?             │
│  A. 401 Unauthorized means authentication is required or    │
│     failed. 403 Forbidden means you are authenticated but    │
│     not authorized to access the resource.                   │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Intermediate Level

```
┌─────────────────────────────────────────────────────────────┐
│                  INTERMEDIATE LEVEL QUESTIONS                   │
│                                                                │
│  Q11. What is the difference between PUT and PATCH?          │
│  A. PUT replaces the entire resource with the request body.   │
│     PATCH applies partial modifications to the resource.    │
│                                                                │
│  Q12. What is a RESTful API?                                │
│  A. An API that follows REST architectural constraints:       │
│     statelessness, client-server, cacheability, uniform       │
│     interface, layered system, and optionally code on demand.│
│                                                                │
│  Q13. What are REST principles? Explain statelessness.      │
│  A. REST has six constraints: Uniform Interface, Statelessness,│
│     Cacheability, Client-Server, Layered System, and         │
│     Code on Demand. Statelessness means each request from     │
│     the client must contain all the information needed to     │
│     understand and process it. The server does not store any   │
│     client context between requests.                         │
│                                                                │
│  Q14. What is the difference between Path and Query           │
│      parameters?                                              │
│  A. Path parameters are part of the URL path and identify    │
│     a specific resource (/users/123). Query parameters are   │
│     used for filtering, sorting, and pagination               │
│     (/users?page=1&limit=10).                                │
│                                                                │
│  Q15. What authentication methods are used in APIs?         │
│  A. Basic Auth, Bearer Token, API Key, OAuth 1.0/2.0,       │
│     JWT, and SAML.                                            │
│                                                                │
│  Q16. What is JWT? How does it work?                        │
│  A. JWT (JSON Web Token) is a compact, self-contained way to  │
│     transmit information between parties as a JSON object.   │
│     It consists of three parts: Header (algorithm and type),  │
│     Payload (claims/data), and Signature (verification).       │
│                                                                │
│  Q17. What is OAuth 2.0? Explain the Authorization Code flow. │
│  A. OAuth 2.0 is an authorization framework that enables    │
│     third-party applications to obtain limited access to a   │
│     user's resources. Authorization Code flow:                │
│     1. Client redirects user to authorization server         │
│     2. User logs in and grants permission                     │
│     3. Authorization server redirects back with auth code     │
│     4. Client exchanges auth code + client secret for token  │
│     5. Client uses access token to call resource server       │
│                                                                │
│  Q18. What is the difference between 200 and 201?            │
│  A. 200 OK means the request succeeded. 201 Created means     │
│     the request succeeded and a new resource was created.    │
│                                                                │
│  Q19. What is the difference between 302 and 307?            │
│  A. Both are temporary redirects. 302 allows the method to    │
│     change to GET on redirect. 307 requires the same method   │
│     to be used.                                               │
│                                                                │
│  Q20. What is caching and which HTTP headers control it?    │
│  A. Caching stores copies of responses to reduce server load.│
│     Headers: Cache-Control, Expires, ETag, Last-Modified,     │
│     If-None-Match, If-Modified-Since.                        │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

### Advanced Level

```
┌─────────────────────────────────────────────────────────────┐
│                    ADVANCED LEVEL QUESTIONS                     │
│                                                                │
│  Q21. What is HATEOAS in REST?                                │
│  A. HATEOAS (Hypermedia as the Engine of Application State)   │
│     means the API response includes links to related resources  │
│     and possible actions, allowing the client to navigate     │
│     the API dynamically without hardcoding URLs.             │
│                                                                │
│  Q22. What is the difference between REST and RESTful?        │
│  A. REST is an architectural style/standard. RESTful means    │
│     an API that follows and implements REST principles.       │
│                                                                │
│  Q23. How do you handle pagination in REST APIs?              │
│  A. Common approaches: Offset/Limit (page=1&limit=10),       │
│     Cursor-based (cursor=abc123), and Keyset pagination.    │
│     Include metadata in response: total count, next/prev     │
│     links, current page.                                      │
│                                                                │
│  Q24. What is idempotence and why is it important?          │
│  A. An operation is idempotent if multiple identical         │
│     requests produce the same result. It is important for    │
│     network reliability — if a request fails due to network  │
│     issues, it can safely be retried without side effects.   │
│                                                                │
│  Q25. What is the difference between authentication and       │
│      authorization?                                           │
│  A. Authentication verifies WHO you are (identity).         │
│     Authorization verifies WHAT you are allowed to do        │
│     (permissions).                                            │
│                                                                │
│  Q26. What are Webhooks? How do they differ from APIs?      │
│  A. Webhooks are event-driven HTTP callbacks. Unlike APIs    │
│     where the client polls the server, webhooks push data to │
│     the client when an event occurs.                         │
│                                                                │
│  Q27. What is API versioning and what strategies exist?     │
│  A. API versioning ensures backward compatibility. Strategies: │
│     • URL versioning (/v1/users, /v2/users)                   │
│     • Header versioning (Accept: application/vnd.api.v2+json)│
│     • Query parameter versioning (/users?version=2)          │
│     • Domain versioning (v1.api.example.com)                  │
│                                                                │
│  Q28. What is a rate limit and how is it implemented?       │
│  A. Rate limiting controls how many requests a client can   │
│     make in a time window. Implemented via:                  │
│     • Fixed Window — fixed time buckets                       │
│     • Sliding Window — continuous time window                 │
│     • Token Bucket — tokens added at fixed rate               │
│     • Leaky Bucket — requests processed at fixed rate        │
│     Headers: X-RateLimit-Limit, X-RateLimit-Remaining,       │
│     X-RateLimit-Reset, Retry-After.                          │
│                                                                │
│  Q29. What is the difference between JSON and XML?          │
│  A. JSON is lightweight, key-value based, native to          │
│     JavaScript, easier to parse. XML is verbose, tag-based,    │
│     supports schemas and namespaces, better for enterprise.   │
│                                                                │
│  Q30. What is GraphQL and what are its advantages?          │
│  A. GraphQL is a query language for APIs. Advantages:        │
│     • Clients request exactly what they need (no over-fetching)│
│     • Single endpoint for multiple resources                  │
│     • Strong typing with schema                               │
│     • Introspection for documentation                         │
│     • Disadvantages: Caching is harder, complex queries can  │
│       impact performance.                                     │
│                                                                │
│  Q31. What is CORS? Why does it matter for APIs?            │
│  A. CORS (Cross-Origin Resource Sharing) is a browser        │
│     security mechanism that restricts web pages from making  │
│     requests to a different domain than the one that served   │
│     the web page. APIs must set appropriate Access-Control-   │
│     headers to allow cross-origin requests.                  │
│                                                                │
│  Q32. What are common API security vulnerabilities?         │
│  A. • Injection attacks (SQL, NoSQL, Command)                  │
│     • Broken authentication                                    │
│     • Sensitive data exposure                                  │
│     • XML External Entities (XXE)                            │
│     • Broken access control                                    │
│     • Security misconfiguration                              │
│     • Cross-Site Scripting (XSS)                             │
│     • Insecure deserialization                                 │
│     • Insufficient logging and monitoring                    │
│                                                                │
│  Q33. What is the difference between gRPC and REST?         │
│  A. gRPC uses HTTP/2 and Protocol Buffers for binary,        │
│     high-performance communication. REST uses HTTP/1.1 and   │
│     JSON/text, is human-readable, and has better tooling.    │
│                                                                │
│  Q34. What are OpenAPI and Swagger?                          │
│  A. OpenAPI Specification (formerly Swagger) is a standard     │
│     format for describing REST APIs. It enables automated    │
│     documentation, code generation, and testing.             │
│                                                                │
│  Q35. What is API contract testing?                           │
│  A. Contract testing verifies that the API provider and        │
│     consumer adhere to a shared contract (e.g., OpenAPI       │
│     spec, Pact). Tools: Pact, Spring Cloud Contract.         │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 14. Quick Reference

```
┌─────────────────────────────────────────────────────────────┐
│                    API TESTING QUICK REFERENCE                 │
├─────────────────────────────────────────────────────────────┤
│                                                                │
│  HTTP Methods:                                                 │
│  GET → Retrieve    | POST → Create    | PUT → Replace       │
│  PATCH → Partial   | DELETE → Remove  | HEAD → Headers only │
│                                                                │
│  Status Codes:                                                 │
│  200 OK | 201 Created | 204 No Content | 400 Bad Request      │
│  401 Unauthorized | 403 Forbidden | 404 Not Found | 500 Error│
│                                                                │
│  Auth:                                                         │
│  Basic → Base64(user:pass) | Bearer → Token in header        │
│  OAuth2 → Delegated access | JWT → Self-contained claims      │
│                                                                │
│  Content-Type:                                                 │
│  JSON → application/json | XML → application/xml              │
│  Form → multipart/form-data | URL-encoded → application/...   │
│                                                                │
│  REST Rules:                                                   │
│  • Use nouns in URLs, not verbs                              │
│  • Use plural nouns (/users, not /user)                      │
│  • Use HTTPS for security                                     │
│  • Version your APIs (/v1/, /v2/)                            │
│  • Use proper HTTP status codes                             │
│  • Support filtering, sorting, pagination via query params    │
│                                                                │
│  Parameter Usage:                                              │
│  Path → Resource ID        | Query → Filter/Sort/Search        │
│  Header → Auth/Metadata    | Body → Create/Update data        │
│                                                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 15. Key Takeaways

1. **API Testing validates the business logic layer** without relying on the UI.
2. **REST is the most popular API style** due to its simplicity and use of HTTP standards.
3. **HTTP Methods have specific meanings** — GET (read), POST (create), PUT (replace), DELETE (remove), PATCH (partial update).
4. **Idempotence matters** — GET, PUT, DELETE are idempotent; POST and PATCH are not.
5. **Status codes communicate result** — 2xx success, 3xx redirect, 4xx client error, 5xx server error.
6. **Authentication is critical** — Use HTTPS, prefer OAuth 2.0 or JWT over Basic Auth.
7. **RESTful design uses nouns and plural forms** — `/users` not `/getUser`, `/users/123/orders` for nested resources.
8. **JSON is the dominant format** for modern REST APIs due to its lightweight nature.
9. **Statelessness enables scalability** — no server-side session means any server can handle any request.
10. **Path parameters identify resources**; Query parameters filter, sort, and paginate; Request body carries complex data.
11. **GraphQL solves over-fetching** by allowing clients to request exactly the fields they need.
12. **Always version your APIs** to maintain backward compatibility for existing clients.
13. **API security** requires input validation, proper authentication, rate limiting, and HTTPS.
14. **Test for functional, reliability, load, security, and negative scenarios** for comprehensive coverage.
15. **Use Postman, RestAssured, or HttpClient** to automate API testing and integrate with CI/CD.

---

**Happy Testing! 🚀**

*Master API Testing, and you master the foundation of modern software integration.*

---

**Last Updated:** June 2026
