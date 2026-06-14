# Chapter 02 — SOAP UI Concepts

> **Topics:** What is SOAP, REST vs SOAP, WSDL Structure, SOAP Message Structure, SOAP UI Tool Basics, Creating SOAP Requests, Assertions, SOAP Interview FAQs

---

## Table of Contents

1. [What is SOAP](#1-what-is-soap)
2. [SOAP vs REST — Detailed Comparison](#2-soap-vs-rest--detailed-comparison)
3. [WSDL Structure and Components](#3-wsdl-structure-and-components)
4. [SOAP Message Structure](#4-soap-message-structure)
5. [SOAP UI Tool Basics](#5-soap-ui-tool-basics)
6. [Creating SOAP Requests in SOAP UI](#6-creating-soap-requests-in-soap-ui)
7. [Assertions in SOAP UI](#7-assertions-in-soap-ui)
8. [SOAP Interview Questions](#8-soap-interview-questions)
9. [Key Takeaways](#9-key-takeaways)

---

## 1. What is SOAP

### Definition

**SOAP** (Simple Object Access Protocol) is a protocol for exchanging structured information in the implementation of web services. It uses XML for its message format and relies on application layer protocols, primarily HTTP or HTTPS, for message transmission.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SOAP PROTOCOL OVERVIEW                              │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ SOAP Characteristics:                                       │   │
│  │ 1. Protocol-based (not architectural style)              │   │
│  │ 2. XML message format only                                │   │
│  │ 3. Uses WSDL for service description                       │   │
│  │ 4. Supports multiple transport protocols (HTTP, SMTP, JMS)  │   │
│  │ 5. Built-in error handling (Fault element)               │   │
│  │ 6. Strict standards and formal contracts                  │   │
│  │ 7. Statelessness (like HTTP)                               │   │
│  │ 8. Supports WS-* standards (security, transactions, etc.)   │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### Why SOAP Exists

SOAP was designed to provide a formal, standards-based way to communicate between applications over a network. Before REST became popular, SOAP was the dominant approach for web services, especially in enterprise environments where formal contracts, security, and transactional reliability were required.

```java
// SOAP is NOT a Java library or framework
// It is a protocol specification maintained by W3C
// Java implementations: JAX-WS (Java API for XML Web Services)
// Popular tools: SOAP UI, Apache CXF, Axis2
```

### SOAP Transport Protocols

```java
// SOAP can be transported over multiple protocols:
// 1. HTTP/HTTPS   — Most common (port 80/443)
// 2. SMTP         — Email-based messaging
// 3. JMS          — Java Message Service (enterprise messaging)
// 4. FTP          — File transfer (rare)
// 5. TCP          — Raw TCP sockets

// Example: SOAP over HTTP POST request
// POST /calculator HTTP/1.1
// Host: www.example.com
// Content-Type: text/xml; charset=utf-8
// SOAPAction: "http://example.com/Add"
// 
// <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
//   <soap:Body>
//     <Add xmlns="http://example.com/">
//       <a>5</a>
//       <b>3</b>
//     </Add>
//   </soap:Body>
// </soap:Envelope>
```

---

## 2. SOAP vs REST — Detailed Comparison

### High-Level Differences

```
┌─────────────────────────────────────────────────────────────────────┐
│              SOAP vs REST — 10+ DIMENSION COMPARISON                 │
│  ┌────────────────┬────────────────────────┬────────────────────┐  │
│  │ Dimension      │ SOAP                   │ REST               │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Type           │ Protocol               │ Architectural Style  │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Message Format │ XML only               │ XML, JSON, YAML, etc.│  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Service Desc.  │ WSDL (formal contract) │ No formal contract   │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Transport      │ HTTP, SMTP, JMS, etc.  │ HTTP only            │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Security       │ WS-Security, SSL       │ HTTPS, OAuth, JWT    │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Performance    │ Slower (XML parsing)   │ Faster (lightweight) │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Caching        │ Not supported natively │ Supports HTTP caching│  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Learning Curve │ Steep (WSDL, XML, etc.)│ Gentle (HTTP methods)│  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Error Handling │ Built-in Fault element │ HTTP status codes    │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ State          │ Stateless              │ Stateless            │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Use Case       │ Enterprise, banking,   │ Mobile, web, cloud,  │  │
│  │                │ transactions, legacy   │ microservices, IoT   │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Flexibility    │ Rigid (formal contract)│ Flexible (no contract) │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Tooling        │ SOAP UI, WSDL parsers  │ Postman, cURL, any   │  │
│  │                │                        │ HTTP client          │  │
│  ├────────────────┼────────────────────────┼────────────────────┤  │
│  │ Transactions   │ ACID via WS-Atomic     │ No built-in support  │  │
│  │                │ Transaction            │ (implemented manually)│  │
│  └────────────────┴────────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### When to Choose SOAP vs REST

```java
// Choose SOAP when:
// 1. You need formal contracts and strict standards
// 2. Security requirements are high (WS-Security)
// 3. You need ACID transactions
// 4. You are integrating with legacy enterprise systems
// 5. You need reliable messaging (WS-ReliableMessaging)
// 6. You need built-in error handling with formal fault codes

// Choose REST when:
// 1. You need lightweight, fast communication
// 2. You want to support multiple formats (JSON, XML, etc.)
// 3. You are building public APIs for web/mobile
// 4. You need to leverage HTTP caching
// 5. You want a simpler, more flexible architecture
// 6. You are building microservices or cloud-native apps
```

### Protocol vs Architectural Style

```java
// SOAP is a PROTOCOL:
// - Has strict rules for message format
// - Has formal error handling
// - Has security standards
// - Uses WSDL to describe operations

// REST is an ARCHITECTURAL STYLE:
// - Uses HTTP methods (GET, POST, PUT, DELETE)
// - Uses HTTP status codes (200, 404, 500, etc.)
// - Uses URIs to identify resources
// - No formal contract required
// - Representations can be JSON, XML, HTML, etc.
```

---

## 3. WSDL Structure and Components

### What is WSDL?

**WSDL** (Web Services Description Language) is an XML-based language used to describe the functionality offered by a web service. It specifies the location of the service and the operations (methods) that the service exposes.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    WSDL STRUCTURE OVERVIEW                           │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ <definitions>                                                │   │
│  │   ├── <types>        — XML Schema data types                │   │
│  │   ├── <message>      — Request/response messages              │   │
│  │   ├── <portType>     — Abstract operations interface          │   │
│  │   ├── <binding>      — Concrete protocol & data format          │   │
│  │   └── <service>      — Service endpoint address (URL)        │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### WSDL Components in Detail

```xml
<!-- Example WSDL for a Calculator Service -->
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://schemas.xmlsoap.org/wsdl/"
             xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
             xmlns:tns="http://example.com/calculator"
             targetNamespace="http://example.com/calculator">

  <!-- 1. TYPES: XML Schema definitions for data types -->
  <types>
    <schema xmlns="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://example.com/calculator">
      
      <!-- Request element: Add operation -->
      <element name="AddRequest">
        <complexType>
          <sequence>
            <element name="a" type="int"/>
            <element name="b" type="int"/>
          </sequence>
        </complexType>
      </element>
      
      <!-- Response element: Add result -->
      <element name="AddResponse">
        <complexType>
          <sequence>
            <element name="result" type="int"/>
          </sequence>
        </complexType>
      </element>
      
    </schema>
  </types>

  <!-- 2. MESSAGE: Abstract message definitions -->
  <message name="AddRequestMessage">
    <part name="parameters" element="tns:AddRequest"/>
  </message>
  
  <message name="AddResponseMessage">
    <part name="parameters" element="tns:AddResponse"/>
  </message>

  <!-- 3. PORTTYPE: Abstract interface (operations) -->
  <portType name="CalculatorPortType">
    <operation name="Add">
      <input message="tns:AddRequestMessage"/>
      <output message="tns:AddResponseMessage"/>
    </operation>
  </portType>

  <!-- 4. BINDING: Concrete protocol binding -->
  <binding name="CalculatorBinding" type="tns:CalculatorPortType">
    <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
    <operation name="Add">
      <soap:operation soapAction="http://example.com/Add"/>
      <input>
        <soap:body use="literal"/>
      </input>
      <output>
        <soap:body use="literal"/>
      </output>
    </operation>
  </binding>

  <!-- 5. SERVICE: Service endpoint -->
  <service name="CalculatorService">
    <port name="CalculatorPort" binding="tns:CalculatorBinding">
      <soap:address location="http://example.com/calculator"/>
    </port>
  </service>

</definitions>
```

### WSDL Component Summary

```java
// WSDL Components explained:
// 
// 1. <types>
//    - Defines XML Schema data types used in messages
//    - Maps Java types to XML types
//    - Example: int, string, complex types, arrays
//
// 2. <message>
//    - Defines abstract message format
//    - Each message has <part> elements
//    - Parts reference types defined in <types>
//
// 3. <portType>
//    - Defines abstract operations (interface)
//    - Like a Java interface: declares methods but doesn't implement them
//    - Each operation has <input> and <output> messages
//
// 4. <binding>
//    - Binds abstract portType to concrete protocol
//    - Specifies SOAP style (document or rpc)
//    - Specifies transport (HTTP, JMS, etc.)
//    - Specifies encoding (literal or encoded)
//
// 5. <service>
//    - Defines service endpoint
//    - Contains <port> with actual URL
//    - URL where the SOAP service is hosted
```

### SOAP Styles

```java
// SOAP has two message styles:
//
// 1. Document Style (most common today)
//    - Message body contains an XML document
//    - Loose coupling, flexible schema
//    - Message: <soap:Body><AddRequest>...</AddRequest></soap:Body>
//    - WSDL: <soap:binding style="document"/>
//
// 2. RPC Style (legacy, less common)
//    - Message body contains method name and parameters
//    - Tight coupling to operations
//    - Message: <soap:Body><Add><a>5</a><b>3</b></Add></soap:Body>
//    - WSDL: <soap:binding style="rpc"/>
//
// SOAP has two encoding types:
//
// 1. Literal Encoding
//    - XML is literal, no additional encoding
//    - Better interoperability
//    - WSDL: <soap:body use="literal"/>
//
// 2. SOAP Encoding (encoded)
//    - Uses SOAP encoding rules (xsi:type attributes)
//    - More verbose, less interoperable
//    - WSDL: <soap:body use="encoded"/>
```

---

## 4. SOAP Message Structure

### SOAP Envelope

Every SOAP message is wrapped in an **Envelope** element. It is the root element and defines the XML document as a SOAP message.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SOAP MESSAGE STRUCTURE                              │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ <soap:Envelope>                                              │   │
│  │   xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"    │   │
│  │                                                              │   │
│  │   ┌─────────────────────────────────────────────────────┐   │   │
│  │   │ <soap:Header>  — Optional metadata                  │   │   │
│  │   │   (authentication, routing, transactions)           │   │   │
│  │   └─────────────────────────────────────────────────────┘   │   │
│  │                                                              │   │
│  │   ┌─────────────────────────────────────────────────────┐   │   │
│  │   │ <soap:Body>      — Required payload                 │   │   │
│  │   │   (actual request/response data)                    │   │   │
│  │   └─────────────────────────────────────────────────────┘   │   │
│  │                                                              │   │
│  │   ┌─────────────────────────────────────────────────────┐   │   │
│  │   │ <soap:Fault>     — Optional error information       │   │   │
│  │   │   (appears inside Body on error)                    │   │   │
│  │   └─────────────────────────────────────────────────────┘   │   │
│  │                                                              │   │
│  │ </soap:Envelope>                                             │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### SOAP Header

The Header element contains application-specific information (like authentication, transaction IDs, or routing information) about the SOAP message.

```xml
<!-- SOAP Header Example -->
<soap:Header>
  <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
    <wsse:UsernameToken>
      <wsse:Username>admin</wsse:Username>
      <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">secret123</wsse:Password>
    </wsse:UsernameToken>
  </wsse:Security>
  
  <tns:TransactionID xmlns:tns="http://example.com/">
    TXN-12345-67890
  </tns:TransactionID>
</soap:Header>
```

### SOAP Body

The Body element contains the actual SOAP message intended for the ultimate endpoint of the message.

```xml
<!-- SOAP Body Example — Request -->
<soap:Body>
  <Add xmlns="http://example.com/calculator">
    <a>10</a>
    <b>20</b>
  </Add>
</soap:Body>

<!-- SOAP Body Example — Response -->
<soap:Body>
  <AddResponse xmlns="http://example.com/calculator">
    <result>30</result>
  </AddResponse>
</soap:Body>
```

### SOAP Fault

The Fault element holds errors and status information from a specific SOAP message. It appears inside the Body element.

```xml
<!-- SOAP Fault Example -->
<soap:Body>
  <soap:Fault>
    <faultcode>soap:Server</faultcode>
    <faultstring>Internal Server Error</faultstring>
    <faultactor>http://example.com/calculator</faultactor>
    <detail>
      <tns:CalculatorError xmlns:tns="http://example.com/calculator">
        <errorCode>CALC-001</errorCode>
        <errorMessage>Division by zero is not allowed</errorMessage>
      </tns:CalculatorError>
    </detail>
  </soap:Fault>
</soap:Body>
```

### Fault Element Components

```java
// SOAP Fault sub-elements:
//
// 1. <faultcode>
//    - Machine-readable error code
//    - Values: VersionMismatch, MustUnderstand, Client, Server
//    - Namespace-qualified: soap:Client, soap:Server
//
// 2. <faultstring>
//    - Human-readable error description
//    - Explains what went wrong
//
// 3. <faultactor>
//    - Optional: indicates which intermediary caused the fault
//    - URI of the service that generated the fault
//
// 4. <detail>
//    - Optional: application-specific error details
//    - Contains custom error information
//    - Must be present if fault is caused by Body content
```

### Complete SOAP Message Example

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:xsd="http://www.w3.org/2001/XMLSchema"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

  <soap:Header>
    <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
      <wsse:UsernameToken>
        <wsse:Username>testuser</wsse:Username>
        <wsse:Password>testpass</wsse:Password>
      </wsse:UsernameToken>
    </wsse:Security>
  </soap:Header>

  <soap:Body>
    <GetWeather xmlns="http://www.webservice.com/">
      <CityName>New York</CityName>
      <CountryCode>US</CountryCode>
    </GetWeather>
  </soap:Body>

</soap:Envelope>
```

### SOAP Message Rules

```java
// SOAP Message Rules:
// 1. Must use XML encoding
// 2. Must use SOAP Envelope namespace
// 3. Must not contain a DTD reference
// 4. Must not contain XML Processing Instructions
// 5. Must use UTF-8 or UTF-16 encoding (default)
// 6. Envelope is the root element
// 7. Body is mandatory; Header and Fault are optional
// 8. Fault must appear inside Body
// 9. Must not use XML comments inside Envelope
```

---

## 5. SOAP UI Tool Basics

### What is SOAP UI?

**SOAP UI** is an open-source testing tool for SOAP and REST APIs. It allows testers to create, execute, and automate functional, regression, compliance, and load tests for web services.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SOAP UI TOOL OVERVIEW                               │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ SOAP UI Capabilities:                                       │   │
│  │ 1. Create and send SOAP requests                           │   │
│  │ 2. Create and send REST requests                           │   │
│  │ 3. Validate responses with assertions                      │   │
│  │ 4. Mock services (simulate server responses)               │   │
│  │ 5. Load testing (performance testing)                      │   │
│  │ 6. Security testing (penetration testing)                  │   │
│  │ 7. Data-driven testing (property transfers)                │   │
│  │ 8. Automate tests with Groovy scripts                      │   │
│  │ 9. Generate WSDL documentation                             │   │
│  │ 10. Test compliance with WS-I standards                    │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### SOAP UI Project Structure

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SOAP UI PROJECT STRUCTURE                           │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ SOAP UI Project                                               │   │
│  │   ├── Workspace                                               │   │
│  │   │   └── Project                                            │   │
│  │   │       ├── Interface (WSDL / REST URL)                     │   │
│  │   │       │   └── Operation (SOAPAction / REST Method)      │   │
│  │   │       │       └── Request (test request)                │   │
│  │   │       │                                                  │   │
│  │   │       ├── TestSuite                                       │   │
│  │   │       │   └── TestCase                                   │   │
│  │   │       │       ├── Test Step (SOAP/REST/Mock)           │   │
│  │   │       │       │   └── Assertion (validation)           │   │
│  │   │       │       └── Test Step (Groovy Script)             │   │
│  │   │       │                                                  │   │
│  │   │       └── MockService                                     │   │
│  │   │           └── MockResponse                                │   │
│  │   │                                                          │   │
│  │   └── Properties (global variables)                           │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### SOAP UI Testing Approach

```java
// SOAP UI Testing Steps:
//
// Step 1: Create a New Project
//   - File → New SOAP Project
//   - Enter WSDL URL or browse local WSDL file
//   - SOAP UI imports all operations from WSDL
//
// Step 2: Create Test Suite
//   - Right-click on Project → New TestSuite
//   - Name the test suite (e.g., "CalculatorTests")
//
// Step 3: Create Test Case
//   - Right-click on TestSuite → New TestCase
//   - Name the test case (e.g., "TestAddOperation")
//
// Step 4: Add Test Steps
//   - Right-click on TestCase → Add Step → SOAP Request
//   - Select the operation from WSDL
//   - SOAP UI generates the request template
//
// Step 5: Configure Request
//   - Edit the request XML (add values to parameters)
//   - Add headers if needed (e.g., SOAPAction, Content-Type)
//   - Add authentication if needed (Basic, WS-Security, etc.)
//
// Step 6: Add Assertions
//   - Click on "Assertions" tab
//   - Add assertions to validate response
//   - XPath Match, Schema Compliance, Contains, SLA, etc.
//
// Step 7: Run the Test
//   - Click the green "Run" button
//   - View response in the "Raw" or "XML" tab
//   - Check assertion results in the "Assertions" tab
//
// Step 8: Automate / Parameterize
//   - Use properties for dynamic values
//   - Use Groovy scripts for complex logic
//   - Create data-driven tests with DataSource
```

---

## 6. Creating SOAP Requests in SOAP UI

### Step-by-Step Process

```java
// Creating a SOAP Request in SOAP UI:
//
// 1. Create New SOAP Project
//    File → New SOAP Project
//    Project Name: CalculatorService
//    Initial WSDL: http://www.dneonline.com/calculator.asmx?WSDL
//
// 2. Explore Imported Operations
//    SOAP UI automatically imports all operations from WSDL:
//    - Add
//    - Subtract
//    - Multiply
//    - Divide
//
// 3. Open Request Template
//    Double-click on any operation (e.g., "Add")
//    SOAP UI generates a request template with placeholders:
//
//    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
//                      xmlns:tem="http://tempuri.org/">
//       <soapenv:Header/>
//       <soapenv:Body>
//          <tem:Add>
//             <tem:intA>?</tem:intA>
//             <tem:intB>?</tem:intB>
//          </tem:Add>
//       </soapenv:Body>
//    </soapenv:Envelope>
//
// 4. Fill in Values
//    Replace ? with actual values:
//    <tem:intA>5</tem:intA>
//    <tem:intB>3</tem:intB>
//
// 5. Send Request
//    Click the green "Run" arrow (top-left of request editor)
//    Or press Ctrl+Enter
//
// 6. View Response
//    Response appears in the right panel:
//    <soap:Body>
//       <AddResponse xmlns="http://tempuri.org/">
//          <AddResult>8</AddResult>
//       </AddResponse>
//    </soap:Body>
```

### Request Configuration

```java
// SOAP UI Request Configuration:
//
// Endpoint URL:
//   - The URL where the SOAP service is hosted
//   - Example: http://www.dneonline.com/calculator.asmx
//   - Can be overridden per request or globally
//
// SOAP Action:
//   - Identifies the operation to be performed
//   - Usually found in the WSDL binding section
//   - Example: "http://tempuri.org/Add"
//   - Sent in HTTP header: SOAPAction
//
// HTTP Headers:
//   - Content-Type: text/xml; charset=utf-8
//   - SOAPAction: "http://tempuri.org/Add"
//   - Authorization: Basic dXNlcjpwYXNzd29yZA==
//
// Authentication:
//   - Basic Auth: Username + Password
//   - WS-Security: UsernameToken, Timestamp, Signature
//   - OAuth 2.0: Bearer token
//   - NTLM: Windows authentication
//
// Attachments:
//   - MTOM: Message Transmission Optimization Mechanism
//   - SwA: SOAP with Attachments
//   - Used for sending binary data (images, files)
```

### Property Transfer in SOAP UI

```java
// Property Transfer in SOAP UI:
//
// Purpose: Pass data from one test step to another
// Example: Extract a value from response and use in next request
//
// Step 1: Create Property Transfer test step
//   - Add Step → Property Transfer
//   - Name: "TransferSessionID"
//
// Step 2: Configure Source
//   - Source: Previous Test Step (e.g., "Login Request")
//   - Property: Response
//   - XPath: //sessionId/text()
//
// Step 3: Configure Target
//   - Target: Next Test Step (e.g., "GetData Request")
//   - Property: Request
//   - XPath: //sessionId/text()
//
// Step 4: Run Test
//   - Login request returns: <sessionId>ABC123</sessionId>
//   - Property transfer extracts "ABC123"
//   - GetData request uses: <sessionId>ABC123</sessionId>
```

---

## 7. Assertions in SOAP UI

### Types of Assertions

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SOAP UI ASSERTIONS OVERVIEW                         │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. XPath Match          — Validate value using XPath          │   │
│  │ 2. Schema Compliance    — Validate against WSDL/XSD schema  │   │
│  │ 3. SLA (Response Time) — Validate response time < threshold  │   │
│  │ 4. Contains             — Check if response contains string   │   │
│  │ 5. Not Contains         — Check if response does NOT contain  │   │
│  │ 6. SOAP Fault           — Validate fault presence/absence     │   │
│  │ 7. Response Status      — Validate HTTP status code         │   │
│  │ 8. SOAP Response        — Validate response is valid SOAP     │   │
│  │ 9. JMS Timeout          — Validate JMS response time         │   │
│  │ 10. WS-Security Status   — Validate WS-Security processing  │   │
│  │ 11. Script Assertion     — Groovy script for custom logic   │   │
│  │ 12. JsonPath Match      — For REST responses (JSON)          │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### XPath Assertion

```java
// XPath Assertion in SOAP UI:
//
// Purpose: Validate specific value in XML response using XPath expression
//
// Example: Validate that AddResult equals 8
//
// XPath Expression:
//   //AddResult
//
// Expected Value:
//   8
//
// Example: Validate that sessionId is not empty
//
// XPath Expression:
//   //sessionId
//
// Expected Value:
//   * (wildcard — any non-empty value)
//
// Advanced XPath:
//   //ns:AddResult[text()='8']
//   (declare namespace prefix in assertion config)
//
// Common XPath Assertion Use Cases:
//   1. Check exact value: //result = "success"
//   2. Check existence: count(//error) = 0
//   3. Check length: string-length(//name) > 0
//   4. Check numeric range: //price > 0 and //price < 1000
```

### Schema Compliance Assertion

```java
// Schema Compliance Assertion in SOAP UI:
//
// Purpose: Validate that the response XML conforms to the WSDL/XSD schema
//
// How it works:
//   1. SOAP UI extracts the schema from the WSDL
//   2. Validates the response against the schema
//   3. Reports any validation errors (missing elements, wrong types, etc.)
//
// Configuration:
//   - Add Assertion → Schema Compliance
//   - Usually automatically configured from WSDL
//   - Can specify custom schema file if needed
//
// Common Errors Detected:
//   - Element not defined in schema
//   - Wrong data type (string instead of int)
//   - Missing required element
//   - Extra element not allowed in schema
//   - Invalid date format
//
// Example:
//   Response: <age>twenty-five</age>
//   Schema: <element name="age" type="int"/>
//   Result: FAIL — "twenty-five" is not a valid integer
```

### SLA Assertion (Response Time)

```java
// SLA Assertion in SOAP UI:
//
// Purpose: Validate that the response time is within acceptable limits
//
// Configuration:
//   - Add Assertion → SLA
//   - Maximum Expected Time: 2000 (ms)
//   - Or: 500 (ms) for fast APIs
//
// Example:
//   - Set SLA: 2000 ms
//   - Actual response time: 3500 ms
//   - Result: FAIL — Response time exceeded SLA
//
// Best Practices:
//   - Set realistic SLA based on production requirements
//   - Consider network latency in SLA calculations
//   - Use different SLAs for different environments
//   - Load testing often reveals SLA violations
//
// Performance Benchmarks:
//   - Simple SOAP request: < 500 ms
//   - Complex query: < 2000 ms
//   - Bulk operations: < 5000 ms
//   - Report generation: < 10000 ms
```

### Contains and Not Contains Assertions

```java
// Contains Assertion in SOAP UI:
//
// Purpose: Check if response contains a specific string or substring
//
// Example:
//   Search Text: "success"
//   Response: <result>operation completed successfully</result>
//   Result: PASS
//
// Use Cases:
//   - Check for success keywords in response
//   - Verify specific error messages
//   - Check for expected elements
//
// Not Contains Assertion:
//
// Purpose: Check that response does NOT contain a specific string
//
// Example:
//   Search Text: "error"
//   Response: <result>success</result>
//   Result: PASS
//
// Use Cases:
//   - Ensure no error keywords in response
//   - Verify sensitive data is not exposed
//   - Check for unexpected elements
```

### Script Assertion (Groovy)

```java
// Script Assertion in SOAP UI:
//
// Purpose: Write custom Groovy scripts for complex validation logic
//
// Example: Validate response status and body content
//
// Groovy Script:
//   def response = context.expand('${GetWeatherRequest#Response}')
//   def httpStatus = messageExchange.getResponseHeaders().get("HTTP-Status")
//   
//   // Check HTTP status
//   assert httpStatus == "200" : "Expected HTTP 200, got " + httpStatus
//   
//   // Check response contains data
//   assert response.contains("<temperature>") : "Temperature element missing"
//   
//   // Parse XML and validate value
//   def xml = new XmlSlurper().parseText(response)
//   def temp = xml.Body.GetWeatherResponse.temperature.toInteger()
//   assert temp > -50 && temp < 60 : "Temperature out of range: " + temp
//
// Common Groovy Script Patterns:
//   1. Parse XML: new XmlSlurper().parseText(response)
//   2. Parse JSON: new groovy.json.JsonSlurper().parseText(response)
//   3. Access properties: context.expand('${PropertyName}')
//   4. Log messages: log.info("Message: " + value)
//   5. Assert conditions: assert condition : "Error message"
```

---

## 8. SOAP Interview Questions

### Q1. What is SOAP and how does it differ from REST?

**Answer:** SOAP is a protocol that uses XML for message formatting and supports multiple transport protocols. REST is an architectural style that uses HTTP methods and can use JSON, XML, or other formats. SOAP is more rigid, formal, and enterprise-oriented, while REST is lightweight, flexible, and widely used for web and mobile APIs.

### Q2. What is WSDL and why is it important?

**Answer:** WSDL is the Web Services Description Language. It is an XML document that describes the operations, messages, data types, and endpoint URL of a SOAP web service. It acts as a formal contract between the service provider and consumer. Tools like SOAP UI can generate requests directly from a WSDL.

### Q3. What are the main parts of a SOAP message?

**Answer:** A SOAP message consists of:
- **Envelope**: The root element that wraps the entire message.
- **Header**: Optional element containing metadata (authentication, routing, transactions).
- **Body**: Required element containing the actual payload (request/response data).
- **Fault**: Optional element inside the Body that contains error information.

### Q4. What is the SOAP Fault element?

**Answer:** The Fault element is a sub-element of the Body that appears when an error occurs. It contains:
- **faultcode**: Machine-readable error code (VersionMismatch, MustUnderstand, Client, Server).
- **faultstring**: Human-readable error description.
- **faultactor**: The service that caused the error.
- **detail**: Application-specific error details.

### Q5. What are the different SOAP styles?

**Answer:** SOAP supports two message styles:
- **Document Style**: The Body contains an XML document. Loose coupling, flexible, most common today.
- **RPC Style**: The Body contains a method name and parameters. Tight coupling, legacy usage.

Additionally, two encoding types:
- **Literal**: XML is used as-is, no extra encoding.
- **Encoded**: Uses SOAP encoding rules (xsi:type), more verbose.

### Q6. What is the difference between SOAP 1.1 and SOAP 1.2?

**Answer:**
- SOAP 1.1 uses `http://schemas.xmlsoap.org/soap/envelope/` namespace and sends `SOAPAction` as a separate HTTP header.
- SOAP 1.2 uses `http://www.w3.org/2003/05/soap-envelope` namespace and includes `SOAPAction` as a parameter in the `Content-Type` header.
- SOAP 1.2 added additional fault codes and better error handling.
- SOAP 1.2 is more aligned with REST/HTTP standards.

### Q7. How does SOAP UI help in API testing?

**Answer:** SOAP UI is a tool that:
- Imports WSDL and auto-generates request templates.
- Sends SOAP/REST requests and displays responses.
- Validates responses with built-in assertions (XPath, Schema, SLA, Contains).
- Supports mock services for testing without a real server.
- Supports load testing and security testing.
- Allows automation with Groovy scripts and property transfers.
- Supports data-driven testing with external data sources.

### Q8. What are the most commonly used assertions in SOAP UI?

**Answer:**
- **XPath Match**: Validate specific XML values using XPath.
- **Schema Compliance**: Validate response against WSDL/XSD.
- **SLA**: Validate response time is within threshold.
- **Contains**: Check if response contains a string.
- **Not Contains**: Check if response does NOT contain a string.
- **SOAP Fault**: Check for presence or absence of faults.
- **Script Assertion**: Custom Groovy validation logic.

### Q9. What is a SOAPAction header?

**Answer:** The SOAPAction header is an HTTP header that tells the server which operation should be invoked. It is defined in the WSDL binding section. In SOAP 1.2, it is included in the `Content-Type` header as `action="..."`. It helps the server route the request to the correct handler without parsing the XML body.

### Q10. What are WS-* standards in SOAP?

**Answer:** WS-* standards are a set of specifications that extend SOAP for enterprise needs:
- **WS-Security**: Authentication, encryption, digital signatures.
- **WS-ReliableMessaging**: Guaranteed message delivery.
- **WS-AtomicTransaction**: ACID transactions across services.
- **WS-Addressing**: Message routing and addressing.
- **WS-Policy**: Service policies and requirements.
- **WS-Trust**: Trust relationships and token issuance.

### Q11. Can SOAP use JSON?

**Answer:** No, by definition SOAP uses XML for message formatting. However, SOAP UI can test REST APIs that use JSON. If you need JSON, you should use REST services instead of SOAP.

### Q12. What is MTOM in SOAP?

**Answer:** MTOM (Message Transmission Optimization Mechanism) is a method for sending binary data (images, files) efficiently with SOAP messages. Instead of base64-encoding the data inside XML, MTOM sends the binary data as MIME attachments, reducing message size and improving performance.

### Q13. What are the advantages of SOAP over REST?

**Answer:**
- Formal contract via WSDL.
- Built-in error handling (Fault element).
- Built-in security standards (WS-Security).
- Supports ACID transactions (WS-AtomicTransaction).
- Supports multiple transport protocols (HTTP, SMTP, JMS).
- Better tooling for enterprise environments.
- Reliable messaging (WS-ReliableMessaging).

### Q14. What are the disadvantages of SOAP?

**Answer:**
- Verbose XML format increases message size.
- Slower parsing compared to JSON.
- Steep learning curve (WSDL, XML, WS-* standards).
- No native support for caching.
- Tight coupling in RPC style.
- More complex setup and configuration.
- Less suitable for mobile and IoT devices.

### Q15. How do you handle authentication in SOAP UI?

**Answer:** SOAP UI supports multiple authentication methods:
- **Basic Authentication**: Username and password sent in HTTP header.
- **WS-Security**: UsernameToken, X.509 certificate, SAML tokens.
- **OAuth 2.0**: Bearer token for modern APIs.
- **NTLM**: Windows authentication.
- **Kerberos**: Enterprise single sign-on.

---

## 9. Key Takeaways

1. **SOAP** is a protocol for structured XML-based message exchange over a network.
2. **WSDL** is the formal contract that describes a SOAP service's operations, messages, and endpoint.
3. **SOAP Message** consists of Envelope, Header (optional), Body (required), and Fault (error).
4. **SOAP vs REST**: SOAP is protocol-based, rigid, XML-only, enterprise-oriented; REST is architectural style, flexible, multi-format, lightweight.
5. **SOAP UI** is a powerful tool for creating, testing, and automating SOAP and REST API tests.
6. **Assertions** in SOAP UI include XPath Match, Schema Compliance, SLA, Contains, Not Contains, and Groovy Script.
7. **SOAP Fault** provides built-in error handling with faultcode, faultstring, faultactor, and detail.
8. **WS-*** standards extend SOAP for security, transactions, reliable messaging, and addressing.
9. **SOAP Styles**: Document (most common) and RPC (legacy). Encoding: Literal and Encoded.
10. **SOAP Transport**: Primarily HTTP/HTTPS, but also supports SMTP, JMS, and others.
11. **MTOM** optimizes binary data transmission in SOAP messages.
12. **Property Transfer** in SOAP UI enables data passing between test steps.
13. **SOAPAction** header identifies the operation to invoke on the server.
14. **Schema Compliance** assertion ensures the response XML conforms to the WSDL/XSD schema.
15. **SLA Assertion** validates that API response times meet performance requirements.

---

**Happy coding!**

*Master SOAP UI and WSDL concepts to become a robust API tester — enterprise systems still rely heavily on SOAP services!*
