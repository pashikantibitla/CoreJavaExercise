# Chapter 04 — Postman Beginner Tutorial

> **Source:** YouTube Playlist — [Postman Beginner Tutorial by Raghav Pal](https://www.youtube.com/playlist?list=PLhW3qG5bs-L-oT0GenwPLcJAPD_SiFK3C)  
> **Channel:** Automation Step by Step  
> **Total Videos:** 26  
> **Topics:** API Testing, Postman Fundamentals, Collections, Variables, Scripting, Newman, Jenkins, Data-Driven Testing, Authorization, Mini-Projects

---

## Table of Contents

### Core Topics (Videos 1-26)
1. [Postman Introduction and UI Components](#1-postman-introduction-and-ui-components)
2. [Creating API Requests](#2-creating-api-requests)
3. [Collections and Folder Organization](#3-collections-and-folder-organization)
4. [Collection Runner](#4-collection-runner)
5. [Variables](#5-variables)
6. [Scripting](#6-scripting)
7. [Environments](#7-environments)
8. [Writing Tests](#8-writing-tests)
9. [Debugging](#9-debugging)
10. [Newman](#10-newman)
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

---

## 1. Postman Introduction and UI Components

**File:** `04_Postman_Tutorial.md` — Section 1  
**Videos:** 1-2

- What is Postman and its capabilities
- Postman UI components: Sidebar, Request Builder, Response Viewer
- Top navigation, left sidebar, status bar
- Step-by-step UI walkthrough

---

## 2. Creating API Requests

**File:** `04_Postman_Tutorial.md` — Section 2  
**Videos:** 3

- HTTP Methods: GET, POST, PUT, DELETE, PATCH
- Creating first GET request
- Creating POST, PUT, DELETE requests
- Adding Query Parameters
- Adding Headers
- Request body types: raw, form-data, x-www-form-urlencoded

---

## 3. Collections and Folder Organization

**File:** `04_Postman_Tutorial.md` — Section 3  
**Videos:** 4

- What is a Collection
- Collection structure and folder hierarchy
- Creating collections, folders, and requests
- Collection options: Edit, Duplicate, Export, Share, Run

---

## 4. Collection Runner

**File:** `04_Postman_Tutorial.md` — Section 4  
**Videos:** 5

- What is Collection Runner
- Runner configuration: Environment, Iterations, Delay, Data
- Running collections and folders
- Iteration data and summary reports
- Exporting results

---

## 5. Variables

**File:** `04_Postman_Tutorial.md` — Section 5  
**Videos:** 6

- Variable types: Global, Collection, Environment, Data, Local
- Variable scope and precedence
- Creating and using variables
- Dynamic variables: `{{$guid}}`, `{{$timestamp}}`, `{{$randomInt}}`
- Setting and getting variables in scripts

---

## 6. Scripting

**File:** `04_Postman_Tutorial.md` — Section 6  
**Videos:** 7, 9

- Pre-request Scripts vs Test Scripts
- Script execution flow
- `pm` object reference
- Common scripting patterns
- Conditional logic in scripts

---

## 7. Environments

**File:** `04_Postman_Tutorial.md` — Section 7  
**Videos:** 8

- What are Environments
- Creating multiple environments (Dev, Staging, Prod)
- Switching environments
- Environment Quick Look
- Using environment variables in requests and scripts

---

## 8. Writing Tests

**File:** `04_Postman_Tutorial.md` — Section 8  
**Videos:** 10

- `pm.test()` structure
- Status code validation
- Response time validation
- JSON body validation
- Header and cookie validation
- Chai assertion library
- Multiple tests and conditional tests

---

## 9. Debugging

**File:** `04_Postman_Tutorial.md` — Section 9  
**Videos:** 11

- Postman Console
- `console.log()` usage
- Debugging request/response
- Network information inspection
- Common debugging scenarios

---

## 10. Newman

**File:** `04_Postman_Tutorial.md` — Section 10  
**Videos:** 12

- What is Newman
- Installing Newman (Node.js required)
- Basic commands: run, environment, data file
- CLI options reference
- HTML reports with `newman-reporter-htmlextra`
- Running Newman via Node.js script

---

## 11. Jenkins Integration

**File:** `04_Postman_Tutorial.md` — Section 11  
**Videos:** 13

- Why Jenkins for API Testing
- Prerequisites and setup
- Creating a Jenkins job (Freestyle)
- Configuring Node.js plugin
- Build steps with batch/shell commands
- Post-build actions: HTML reports, artifacts
- Jenkins Pipeline (Jenkinsfile)
- Pipeline with parameters

---

## 12. Workspaces

**File:** `04_Postman_Tutorial.md` — Section 12  
**Videos:** 14

- Workspace types: Personal, Team, Public, Partner, Private
- Creating workspaces
- Managing workspace members and roles
- Moving collections between workspaces
- Workspace use cases

---

## 13. Data-Driven Testing

**File:** `04_Postman_Tutorial.md` — Section 13  
**Videos:** 15

- What is Data-Driven Testing
- CSV and JSON data file formats
- Using data variables in requests
- Using `pm.iterationData.get()` in scripts
- Running in Collection Runner with data files
- Running with Newman `-d` flag
- Advanced data-driven patterns

---

## 14. Running Collections Remotely

**File:** `04_Postman_Tutorial.md` — Section 14  
**Videos:** 16

- Postman API for programmatic access
- Getting API keys
- Running collections via Postman API
- Postman Monitors: scheduled cloud runs
- Sharing collection run links
- Running from URL with Newman

---

## 15. SOAP Requests

**File:** `04_Postman_Tutorial.md` — Section 15  
**Videos:** 17

- SOAP vs REST comparison
- Creating SOAP requests in Postman
- XML body and SOAPAction header
- WSDL import
- Testing SOAP responses
- Common SOAP headers

---

## 16. Response Extraction and Chaining

**File:** `04_Postman_Tutorial.md` — Section 16  
**Videos:** 18

- Extracting JSON values
- Extracting from arrays
- Extracting from headers and cookies
- Using extracted values in next requests
- Chaining multiple requests
- Handling nested JSON
- Using lodash for advanced extraction

---

## 17. API Authorization

**File:** `04_Postman_Tutorial.md` — Section 17  
**Videos:** 19

- Authorization types: Basic, Bearer, API Key, OAuth 1.0/2.0, AWS
- Basic Auth step-by-step
- Bearer Token setup
- API Key in header/query
- OAuth 2.0 flow
- Collection-level authorization
- Auth handling in test scripts

---

## 18. Crash Course Summary

**File:** `04_Postman_Tutorial.md` — Section 18  
**Videos:** 20-23

- Complete workflow overview
- Quick setup checklist
- Common patterns: CRUD, Auth, E-Commerce
- All concepts combined

---

## 19. Mini-Project 1: Login API Flow

**File:** `04_Postman_Tutorial.md` — Section 19  
**Videos:** 24

- Project overview: Login → Token → Protected Endpoints
- Collection structure
- Login request (valid and invalid credentials)
- Token extraction and environment storage
- Protected endpoint access with Bearer token
- Logout and cleanup
- Data-driven login test with CSV

---

## 20. Mini-Project 2: E-Commerce Add to Cart

**File:** `04_Postman_Tutorial.md` — Section 20  
**Videos:** 25

- Project overview: Login → Products → Cart → Checkout
- Collection structure
- Authentication setup
- Product catalog requests
- Cart creation and item addition
- Cart viewing and validation
- Checkout flow
- Order verification
- Data-driven cart test with CSV

---

## 21. Monitoring and Mocking

**File:** `04_Postman_Tutorial.md` — Section 21

- Postman Monitors: creation, scheduling, alerts
- Monitor configuration options
- Viewing monitor results
- Mock servers: creation, configuration
- Adding mock responses and examples
- Mocking error scenarios
- Using mock servers in development

---

## 22. API Documentation

**File:** `04_Postman_Tutorial.md` — Section 22

- Auto-generated documentation
- Adding descriptions with Markdown
- Adding examples to requests
- Publishing public documentation
- Private documentation
- Code snippets in 20+ languages
- Custom branding

---

## 23. Postman Flows

**File:** `04_Postman_Tutorial.md` — Section 23

- What are Postman Flows
- Visual workflow design
- Flow blocks: Send Request, If, For Each, Select, Assign, Delay, Function
- Creating and running flows
- E-Commerce flow example

---

## 24. Interview FAQs

**File:** `04_Postman_Tutorial.md` — Section 24  
**Videos:** All concepts

- 35+ interview questions and answers
- Categories: Basics, Variables, Scripting, Collections, Newman, CI/CD, Advanced

---

## 📁 Additional Files

- **`postman_collections/sample_pre_request_script.js`** — Pre-request script examples
- **`postman_collections/sample_test_script.js`** — Test script examples
- **`postman_collections/sample_data_driven.csv`** — Sample CSV for data-driven testing
- **`postman_collections/sample_data_driven.json`** — Sample JSON for data-driven testing
- **`postman_collections/newman_commands.md`** — Common Newman commands reference

---

## 🎯 Learning Path

```
Phase 1: Foundation
    │
    ├── Postman Introduction & UI ──→ Understand the interface
    │
    ├── Creating API Requests ──→ GET, POST, PUT, DELETE
    │
    ├── Collections & Folders ──→ Organize requests
    │
    └── Collection Runner ──→ Execute multiple requests

Phase 2: Variables & Scripting
    │
    ├── Variables ──→ Global, Collection, Environment, Data, Local
    │
    ├── Environments ──→ Dev, Staging, Production
    │
    ├── Pre-request Scripts ──→ Setup and dynamic data
    │
    └── Test Scripts ──→ Validation and extraction

Phase 3: Automation & CI/CD
    │
    ├── Debugging ──→ Console, logs, network inspection
    │
    ├── Newman ──→ Command-line execution
    │
    ├── Jenkins Integration ──→ CI/CD pipelines
    │
    └── Data-Driven Testing ──→ CSV/JSON data files

Phase 4: Advanced Topics
    │
    ├── Workspaces ──→ Collaboration
    │
    ├── Remote Runs ──→ Monitors, Postman API
    │
    ├── SOAP Requests ──→ XML-based APIs
    │
    ├── Response Chaining ──→ Extract and reuse data
    │
    └── API Authorization ──→ Auth types and OAuth 2.0

Phase 5: Real-World Projects
    │
    ├── Mini-Project 1: Login API Flow ──→ Auth, Token, Protected APIs
    │
    ├── Mini-Project 2: E-Commerce Flow ──→ Cart, Checkout
    │
    ├── Monitoring & Mocking ──→ Scheduled runs, mock servers
    │
    ├── API Documentation ──→ Publish docs
    │
    └── Postman Flows ──→ Visual automation
```

---

## 📋 Key Takeaways

1. **Postman is an API Platform** — Complete solution for API development, testing, documentation, and collaboration.
2. **Collections Organize Work** — Group requests into folders for maintainability.
3. **Variables Enable Reusability** — Global, collection, environment, data, and local variables with scope precedence.
4. **Scripts Add Power** — Pre-request scripts prepare; test scripts validate. JavaScript in Postman Sandbox.
5. **Environments Separate Configs** — Dev, Staging, and Production without duplicating requests.
6. **Chai Assertions** — `pm.test()` and `pm.expect()` for readable, maintainable tests.
7. **Response Extraction** — Save values for chaining in subsequent requests.
8. **Data-Driven Testing** — CSV/JSON files with Collection Runner for multiple scenarios.
9. **Newman Enables CI/CD** — Command-line runner for Jenkins, GitHub Actions, Azure DevOps.
10. **Jenkins Automates Execution** — Pipelines to run tests on every commit with HTML reporting.
11. **Monitors Watch Production** — Scheduled cloud runs with alerts on failures.
12. **Mock Servers Speed Development** — Simulate APIs before they are built.
13. **Documentation Drives Adoption** — Auto-generate and publish API docs from collections.
14. **Authorization is Critical** — Basic, Bearer, API Key, OAuth 2.0, custom schemes.
15. **SOAP is Supported** — XML-based APIs with proper headers and body.
16. **Debugging with Console** — `console.log()` and Console for troubleshooting.
17. **Flows Provide Visual Automation** — No-code workflows for chaining requests.
18. **Workspaces Enable Collaboration** — Personal, team, and public workspaces.
19. **Scope Precedence** — Data > Local > Environment > Collection > Global.
20. **Practice with Mini-Projects** — Real-world flows like Login + Token + E-Commerce.

---

**Happy API Testing!**

*Master Postman, and you master the art of API Quality Assurance.*
