# 05 — Automation with Java

> **Comprehensive Java Automation for API Testing**
> 
> Covers: REST Assured, HttpClient, OkHttp, POJOs, Data-Driven Testing, Framework Architecture, Reporting, CI/CD, Interview FAQs

---

## Table of Contents

1. [Overview](#overview)
2. [Files in This Folder](#files-in-this-folder)
3. [Java Programs](#java-programs)
4. [How to Use](#how-to-use)
5. [Prerequisites](#prerequisites)
6. [Maven Setup](#maven-setup)

---

## Overview

This folder contains comprehensive Java automation content for API testing. It covers the complete spectrum from basic HTTP operations using different libraries to building a production-ready automation framework.

### Topics Covered

- **API Testing Libraries**: REST Assured, HttpClient, OkHttp, JSONPath
- **REST Assured Deep Dive**: BDD syntax, request/response handling, authentication, assertions
- **POJO-based Automation**: Serialization, deserialization, nested POJOs, Lombok
- **Data-Driven Testing**: TestNG DataProvider, CSV, JSON, Excel data sources
- **Framework Architecture**: Layered design, BaseTest, configuration management, utilities
- **Reporting**: Extent Reports, Allure Reports, TestNG Reports
- **Maven Configuration**: Complete pom.xml with all dependencies
- **CI/CD Integration**: Jenkins pipeline, GitHub Actions workflow
- **Interview FAQs**: 30+ questions from beginner to advanced level

---

## Files in This Folder

| File | Description | Lines |
|------|-------------|-------|
| `05_Automation_with_Java.md` | Comprehensive theory, concepts, and examples | 1500+ |
| `README.md` | This file — overview and navigation | 100+ |

---

## Java Programs

All programs are located in the `java_programs/` subdirectory.

| # | Program | Description |
|---|---------|-------------|
| 1 | `RestAssuredBasicTest.java` | Basic GET, POST, PUT, DELETE operations |
| 2 | `RestAssuredAuthTest.java` | Basic Auth, Bearer Token, OAuth examples |
| 3 | `RestAssuredPojoTest.java` | Using POJOs for request/response handling |
| 4 | `RestAssuredDataDrivenTest.java` | TestNG DataProvider with CSV data |
| 5 | `RestAssuredChainingTest.java` | Response extraction and request chaining |
| 6 | `RestAssuredJsonPathTest.java` | JSONPath assertions and filtering |
| 7 | `HttpClientTest.java` | Java 11 HttpClient synchronous example |
| 8 | `OkHttpTest.java` | OkHttp library GET/POST example |
| 9 | `ApiFrameworkBaseTest.java` | Framework base test class with setup |
| 10 | `UserController.java` | Controller pattern for User API |
| 11 | `UserService.java` | Service pattern for business logic |
| 12 | `ConfigReader.java` | Configuration management utility |
| 13 | `JsonUtils.java` | JSON manipulation and parsing utility |
| 14 | `TestDataGenerator.java` | Test data generation with Faker |
| 15 | `ExtentReportManager.java` | Extent Reports setup and configuration |

---

## How to Use

### 1. Read the Theory First

Start with `05_Automation_with_Java.md` to understand the concepts, syntax, and best practices.

### 2. Study the Programs

Navigate to `java_programs/` and study each program. Each file contains:
- Detailed comments explaining the code
- Import statements showing required dependencies
- Test methods demonstrating specific features
- Example usage patterns

### 3. Compile and Run

```bash
# Navigate to the java_programs directory
cd java_programs

# Compile a single program (requires dependencies in classpath)
javac -cp "path/to/rest-assured.jar;path/to/testng.jar" RestAssuredBasicTest.java

# Run TestNG tests
mvn clean test
```

### 4. Practice with Real APIs

The programs use `https://reqres.in` (a free fake API for testing) as the default endpoint. You can replace it with your own API endpoints.

---

## Prerequisites

- **Java 11 or higher** (Java 11+ recommended for HttpClient examples)
- **Maven 3.6+** (for dependency management and test execution)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)
- **TestNG** (included via Maven dependencies)
- **REST Assured** (included via Maven dependencies)

---

## Maven Setup

All Java programs are designed to work with a Maven project. The `pom.xml` configuration is included in the detailed markdown file (`05_Automation_with_Java.md`).

### Quick Setup

1. Create a new Maven project
2. Add dependencies from the pom.xml section in the markdown file
3. Copy the Java programs into `src/test/java/`
4. Run `mvn clean test`

### Key Dependencies

- `io.rest-assured:rest-assured` — REST API testing
- `org.testng:testng` — Test framework
- `com.fasterxml.jackson.core:jackson-databind` — JSON serialization
- `org.projectlombok:lombok` — Boilerplate reduction
- `com.aventstack:extentreports` — HTML reporting
- `io.qameta.allure:allure-testng` — Allure reporting
- `com.github.javafaker:javafaker` — Test data generation
- `com.opencsv:opencsv` — CSV processing
- `org.apache.poi:poi-ooxml` — Excel processing

---

## Learning Path

```
Phase 1: Basics
├── Read "API Testing Libraries Overview"
├── Study RestAssuredBasicTest.java
├── Study HttpClientTest.java
└── Study OkHttpTest.java

Phase 2: Advanced REST Assured
├── Read "RestAssured Deep Dive"
├── Study RestAssuredAuthTest.java
├── Study RestAssuredJsonPathTest.java
└── Study RestAssuredChainingTest.java

Phase 3: POJOs and Data
├── Read "POJO-based Automation"
├── Study RestAssuredPojoTest.java
├── Read "Data-Driven Testing"
└── Study RestAssuredDataDrivenTest.java

Phase 4: Framework
├── Read "Framework Architecture"
├── Study ApiFrameworkBaseTest.java
├── Study UserController.java
├── Study UserService.java
├── Study ConfigReader.java
├── Study JsonUtils.java
└── Study TestDataGenerator.java

Phase 5: Reporting & CI/CD
├── Read "Reporting" section
├── Study ExtentReportManager.java
├── Read "Maven Configuration"
└── Read "CI/CD Integration"

Phase 6: Interview Preparation
└── Read "Interview FAQs" (30+ questions)
```

---

## Key Features

1. **Comprehensive Coverage** — Every major API testing concept in Java
2. **Multiple Libraries** — REST Assured, HttpClient, OkHttp, JSONPath
3. **Real Examples** — All programs compile and use real API patterns
4. **Framework Ready** — Complete architecture for production use
5. **Interview Ready** — 30+ questions from beginner to advanced
6. **CI/CD Ready** — Jenkins and GitHub Actions configurations
7. **Best Practices** — Follows industry standards and patterns

---

## Notes

- All programs use `https://reqres.in` for demonstration purposes. Replace with your actual API endpoints.
- For authentication examples, replace placeholder tokens with actual credentials.
- Lombok requires annotation processing enabled in your IDE.
- Allure reports require the Allure command-line tool installed.

---

**Happy API Testing!**

---

**Last Updated:** June 2026
