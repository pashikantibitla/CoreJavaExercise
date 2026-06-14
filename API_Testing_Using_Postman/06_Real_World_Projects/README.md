# 06 — Real-World Projects

> **Real-world API testing projects** covering E-Commerce, User Management, Social Media, and Banking domains.

---

## Contents

| File | Description |
|------|-------------|
| `06_Real_World_Projects.md` | Comprehensive theory guide (1000+ lines) covering 4 projects |
| `README.md` | This file — overview and navigation |
| `java_programs/` | Complete Java source files for hands-on practice |

---

## Projects Overview

### 1. E-Commerce API Testing
Complete user journey from registration to payment:
- User Registration & Login
- Product Catalog & Cart Management
- Checkout & Payment Processing
- Order History

**Java Files:** `EcommerceApiTest.java`, `EcommercePojos.java`, `EcommerceController.java`

### 2. User Management API Testing
- CRUD Operations
- Search & Filter
- Pagination Testing
- Bulk Operations
- Import/Export

**Java File:** `UserManagementApiTest.java`

### 3. Social Media API Testing
- OAuth 2.0 Authentication
- Create Post & Media Upload
- Like, Comment & Share
- Feed Retrieval
- Notifications

**Java File:** `SocialMediaApiTest.java`

### 4. Banking API Testing
- Account Creation & KYC
- Balance Inquiry
- Fund Transfer (IMPS/NEFT/RTGS)
- Transaction History
- Security Testing

**Java File:** `BankingApiTest.java`

### 5. Data Flow & Memory Management
Demonstrates how JSON data flows between requests and JVM memory allocation patterns.

**Java File:** `DataFlowDemo.java`

---

## How to Compile and Run

### Prerequisites
- Java 8 or higher
- Maven 3.6+ (optional, for dependencies)

### Compilation
```bash
# Compile all Java files
javac java_programs/*.java

# Run a specific test
java java_programs.EcommerceApiTest
java java_programs.DataFlowDemo
```

### With Maven (Full Dependencies)
```bash
# Add dependencies to pom.xml (see 06_Real_World_Projects.md Section 6.1)
mvn clean test
```

---

## Learning Path

```
Phase 1: Read 06_Real_World_Projects.md theory
Phase 2: Study the Java POJOs (EcommercePojos.java)
Phase 3: Study the Controller layer (EcommerceController.java)
Phase 4: Run the test classes (EcommerceApiTest.java, etc.)
Phase 5: Review DataFlowDemo.java for memory concepts
Phase 6: Answer the Interview FAQs (Section 8)
```

---

## Key Concepts Covered

- Authentication & Token Management
- CRUD Operations
- Search, Filter & Pagination
- Bulk Operations
- OAuth 2.0 Flows
- Security Testing (SQL Injection, XSS, Rate Limiting)
- Idempotency & Concurrency
- JSON Schema Validation
- Data Flow & Memory Management
- Error Handling & Negative Testing

---

## Interview Ready

- 20+ Interview FAQs covering real-world scenarios
- Code examples for REST Assured, Postman, and TestNG
- Best practices for CI/CD integration

---

**Happy Testing! 🧪**
