# Chapter 01 — API Testing Basics

> **Source:** API Testing Using Postman & REST Assured Study Guide  
> **Topics:** API Testing Fundamentals, REST/SOAP/GraphQL, HTTP Methods, Status Codes, Authentication, REST Principles  

---

## Table of Contents

### Core Topics
1. [API Testing Fundamentals](#1-api-testing-fundamentals)

### Extended Topics
- [SOAP UI Concepts](../02_SOAP_UI_Concepts)
- [POJO Classes and JSON](../03_POJO_Classes_and_JSON)
- [Postman Beginner Tutorial](../04_Postman_Beginner_Tutorial)
- [Automation with Java](../05_Automation_with_Java)
- [Real World Projects](../06_Real_World_Projects)

---

## 1. API Testing Fundamentals

**File:** `01_API_Testing_Fundamentals.md`

- What is API Testing and why it's important
- Types of APIs (REST, SOAP, GraphQL) with detailed comparison
- HTTP Methods (GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS)
- HTTP Status Codes (1xx, 2xx, 3xx, 4xx, 5xx)
- Request and Response Structure (headers, body, parameters)
- Authentication methods (Basic Auth, Bearer Token, OAuth 1.0/2.0, API Keys, JWT)
- Content Types (JSON, XML, form-data, raw)
- REST Principles (statelessness, cacheability, uniform interface, layered system)
- RESTful Design Patterns and URL naming conventions
- Path Parameters vs Query Parameters vs Request Body
- JSON vs XML comparison
- Code examples in JavaScript and Java
- Interview FAQs (35+ questions from basic to advanced)
- ASCII diagrams for architecture

---

## 📁 Additional Files

- **interview.md** — Comprehensive interview questions from low to high/severe levels
- **coding.md** — Programming problems with logic explanations from low to high/severe levels
- **java_programs/** — Individual .java files for each coding problem

---

## 🎯 Learning Path

```
Phase 1: API Testing Foundations
    │
    ├── API Testing Fundamentals ──→ What is API, why test, architecture
    │
    ├── Types of APIs ──→ REST, SOAP, GraphQL, RPC, gRPC, WebSocket
    │
    ├── HTTP Methods ──→ GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS
    │
    ├── HTTP Status Codes ──→ 1xx, 2xx, 3xx, 4xx, 5xx with examples
    │
    ├── Request/Response Structure ──→ Headers, body, parameters
    │
    └── Authentication ──→ Basic, Bearer, OAuth, JWT, API Keys

Phase 2: REST Design & Patterns
    │
    ├── REST Principles ──→ Statelessness, cacheability, uniform interface
    │
    ├── RESTful Design ──→ URL naming, resource mapping, pagination
    │
    ├── Parameter Types ──→ Path, query, body, header, cookie
    │
    └── JSON vs XML ──→ Format comparison, when to use which

Phase 3: Practical Implementation
    │
    ├── Code Examples ──→ JavaScript (Fetch, Axios), Java (HttpClient, RestAssured)
    │
    ├── Interview Preparation ──→ 35+ questions from basic to advanced
    │
    └── Quick Reference ──→ Cheat sheet for daily use
```

---

## 📋 Key Takeaways

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
