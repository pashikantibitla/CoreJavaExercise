# Chapter 25 — Development

> **Topics:** JDK vs JRE vs JVM, JAR vs WAR vs EAR, Classpath, Java CLI Tools, Build Tools, Interview FAQs

---

## Table of Contents

1. [JDK vs JRE vs JVM](#1-jdk-vs-jre-vs-jvm)
2. [JAR vs WAR vs EAR](#2-jar-vs-war-vs-ear)
3. [Classpath and PATH](#3-classpath-and-path)
4. [Java Command Line Tools](#4-java-command-line-tools)
5. [Build Tools](#5-build-tools)
6. [Build Lifecycle](#6-build-lifecycle)
7. [Interview Questions](#7-interview-questions)
8. [Key Takeaways](#8-key-takeaways)

---

## 1. JDK vs JRE vs JVM

**File:** `25_Development.md`  
**Topics:** Architecture, comparison, components

- **JVM:** Java Virtual Machine — executes bytecode, provides platform independence.
- **JRE:** Java Runtime Environment — JVM + core libraries + runtime files. Minimum to run Java.
- **JDK:** Java Development Kit — JRE + development tools (javac, jar, javadoc, javap, jdb, etc.).

```
JDK = JRE + Development Tools
JRE = JVM + Core Libraries + Runtime Files
JVM = Bytecode execution engine
```

---

## 2. JAR vs WAR vs EAR

**File:** `25_Development.md`  
**Topics:** Packaging formats, deployment, structure

- **JAR:** Java Archive — classes, resources, manifest. For libraries and standalone apps.
- **WAR:** Web Application Archive — web apps, servlets, JSPs, static files. For Tomcat/Jetty.
- **EAR:** Enterprise Archive — multi-module enterprise apps. For Java EE app servers.

---

## 3. Classpath and PATH

**File:** `25_Development.md`  
**Topics:** Environment variables, differences, setting classpath

- **PATH:** OS variable to find executables (javac, java, etc.).
- **CLASSPATH:** JVM variable to find classes, JARs, and resources.
- Set via `-cp`, `-classpath`, or environment variable.

---

## 4. Java Command Line Tools

**File:** `25_Development.md`  
**Topics:** javac, java, jar, javadoc, javap, jdb, jconsole

- `javac` — compile source code
- `java` — run application
- `jar` — create and manage JAR files
- `javadoc` — generate HTML documentation
- `javap` — disassemble bytecode
- `jdb` — debugger
- `jshell` — interactive Java REPL (Java 9+)

---

## 5. Build Tools

**File:** `25_Development.md`  
**Topics:** Ant, Maven, Gradle

- **Ant:** Procedural, XML-based, manual dependency management.
- **Maven:** Declarative, POM-based, convention over configuration, strong dependency management.
- **Gradle:** Hybrid, Groovy/Kotlin DSL, fast incremental builds, build cache.

---

## 6. Build Lifecycle

**File:** `25_Development.md`  
**Topics:** Maven lifecycle, Gradle tasks, phases

- **Maven Default Lifecycle:** validate → compile → test → package → verify → install → deploy
- **Gradle:** Initialization → Configuration → Execution. Tasks form a DAG.
- **Maven Clean Lifecycle:** pre-clean → clean → post-clean

---

## 📁 Additional Files

- **25_Development.md** — Comprehensive theory, code snippets, and interview FAQs
- **java_programs/** — Individual `.java` files demonstrating development concepts

---

## 🎯 Learning Path

```
Start Here
    │
    ├── JDK vs JRE vs JVM ──→ Understand the Java runtime hierarchy
    │
    ├── JAR vs WAR vs EAR ──→ Packaging formats and deployment
    │
    ├── Classpath and PATH ──→ Environment configuration
    │
    ├── Java CLI Tools ──→ javac, java, jar, javadoc, javap
    │
    ├── Build Tools ──→ Ant, Maven, Gradle basics
    │
    └── Build Lifecycle ──→ Maven phases, Gradle tasks
```

---

## 📋 Key Takeaways

1. **JDK = JRE + Dev Tools; JRE = JVM + Libraries; JVM = Bytecode execution engine.**
2. **JAR** for libraries/standalone, **WAR** for web apps, **EAR** for enterprise apps.
3. **PATH** for OS executables; **CLASSPATH** for Java classes and resources.
4. `javac` compiles, `java` runs, `jar` packages, `javadoc` documents, `javap` disassembles.
5. **Ant** is procedural XML; **Maven** is declarative POM-based; **Gradle** is flexible DSL-based.
6. **Maven lifecycle:** validate → compile → test → package → verify → install → deploy.
7. **Gradle** uses a task-based DAG model with build cache and incremental builds.
8. **Maven coordinates** `groupId:artifactId:version` uniquely identify dependencies.
9. **Maven local repository** (`~/.m2`) caches dependencies across projects.
10. **Gradle** is generally faster than Maven due to incremental builds and build cache.

---

**Happy coding! 🚀**

*Master the tools, and you master the craft of Java development.*
