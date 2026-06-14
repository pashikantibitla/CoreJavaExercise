# 03 — POJO Classes and JSON

> **Topics:** POJO Fundamentals, JavaBean Conventions, Serialization/Deserialization, Jackson Library, Gson Library, Lombok Annotations, Nested POJOs, Memory Allocation, GC Impact, JSON Structure, JSON Parsing, Tree vs Streaming Model, JSON Schema Validation, Interview FAQs

---

## Contents

| # | File | Description |
|---|------|-------------|
| 1 | `03_POJO_Classes_and_JSON.md` | Comprehensive POJO and JSON theory guide |
| 2 | `../java_programs/EmployeePOJO.java` | Basic POJO with all standard methods |
| 3 | `../java_programs/EmployeeLombok.java` | Lombok version of Employee POJO |
| 4 | `../java_programs/EmployeeJacksonSerialization.java` | Jackson serialization/deserialization |
| 5 | `../java_programs/EmployeeGsonSerialization.java` | Gson serialization/deserialization |
| 6 | `../java_programs/NestedPOJOExample.java` | Nested POJO structure |
| 7 | `../java_programs/MemoryAllocationDemo.java` | Demonstrating heap vs stack allocation |
| 8 | `../java_programs/JsonTreeModel.java` | Tree model parsing example |
| 9 | `../java_programs/JsonStreamingModel.java` | Streaming model parsing example |
| 10 | `../java_programs/JsonSchemaValidation.java` | JSON Schema validation example |

---

## How to Use This Folder

1. Read `03_POJO_Classes_and_JSON.md` from top to bottom.
2. Compile and run each Java program in `java_programs/` to see the concepts in action.
3. Practice creating your own POJOs for real-world API responses.
4. Review interview questions to prepare for technical discussions.

---

## Compilation Notes

Some programs require external dependencies. For compilation with Maven, add the following to your `pom.xml`:

```xml
<dependencies>
    <!-- Jackson -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
    
    <!-- Gson -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.28</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- JSON Schema Validator -->
    <dependency>
        <groupId>com.networknt</groupId>
        <artifactId>json-schema-validator</artifactId>
        <version>1.0.86</version>
    </dependency>
</dependencies>
```

---

**Happy coding!**
