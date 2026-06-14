# Chapter 25 — Development

> **Topics:** JDK vs JRE vs JVM, JAR vs WAR vs EAR, Classpath, Java CLI Tools, Build Tools, Interview FAQs

---

## Table of Contents

1. [Introduction to Java Development](#1-introduction-to-java-development)
2. [JDK vs JRE vs JVM](#2-jdk-vs-jre-vs-jvm)
3. [JAR vs WAR vs EAR](#3-jar-vs-war-vs-ear)
4. [Classpath and PATH](#4-classpath-and-path)
5. [Java Command Line Tools](#5-java-command-line-tools)
6. [Build Tools Basics](#6-build-tools-basics)
7. [Build Lifecycle](#7-build-lifecycle)
8. [Interview FAQs](#8-interview-faqs)
9. [Key Takeaways](#9-key-takeaways)

---

## 1. Introduction to Java Development

### What is Java Development?

Java development encompasses the entire process of writing, compiling, packaging, and deploying Java applications. It involves understanding the Java ecosystem, tools, and best practices for building robust software.

```
┌──────────────────────────────────────────────────────────────┐
│              JAVA DEVELOPMENT WORKFLOW                         │
│                                                               │
│   ┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐   │
│   │  Write  │ → │ Compile │ → │ Package │ → │  Deploy │   │
│   │  .java  │    │  .class │    │  .jar   │    │  Server │   │
│   └─────────┘    └─────────┘    └─────────┘    └─────────┘   │
│        │              │              │              │          │
│        ▼              ▼              ▼              ▼          │
│     Editor        javac          jar/jar        java/app      │
│     IDE           compiler       tool           server        │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. JDK vs JRE vs JVM

### What is JVM?

**JVM (Java Virtual Machine)** is an abstract computing machine that enables a computer to run Java programs. It provides platform independence by translating bytecode into native machine code.

```
┌──────────────────────────────────────────────────────────────┐
│              JVM ARCHITECTURE                                 │
│                                                               │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │                    JVM Components                       │ │
│   │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐   │ │
│   │  │ ClassLoader │  │  Runtime    │  │  Execution  │   │ │
│   │  │ Subsystem   │  │  Data Areas │  │  Engine     │   │ │
│   │  │             │  │             │  │             │   │ │
│   │  │ - Loading   │  │ - Method    │  │ - Interpreter│  │ │
│   │  │ - Linking   │  │   Area      │  │ - JIT Compiler│  │ │
│   │  │ - Initialization│ - Heap    │  │ - GC Threads │  │ │
│   │  │             │  │ - Stack     │  │             │   │ │
│   │  │             │  │ - PC Register│  │             │   │ │
│   │  │             │  │ - Native    │  │             │   │ │
│   │  │             │  │   Method    │  │             │   │ │
│   │  └─────────────┘  └─────────────┘  └─────────────┘   │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### What is JRE?

**JRE (Java Runtime Environment)** is the minimum environment required to run Java applications. It includes:
- JVM
- Core libraries (`rt.jar` / `lib/modules` in Java 9+)
- Supporting files (properties, fonts, etc.)
- **Does NOT include** development tools (javac, javadoc, etc.)

### What is JDK?

**JDK (Java Development Kit)** is the full development environment. It includes:
- JRE (everything needed to run Java)
- Development tools (javac, java, jar, javadoc, javap, etc.)
- Debuggers (jdb)
- Additional utilities (javah, keytool, etc.)

```
┌──────────────────────────────────────────────────────────────┐
│              JDK vs JRE vs JVM HIERARCHY                     │
│                                                               │
│          ┌─────────────────────────────────┐                  │
│          │              JDK                  │                  │
│          │  ┌─────────────────────────┐    │                  │
│          │  │          JRE            │    │                  │
│          │  │  ┌─────────────────┐  │    │                  │
│          │  │  │       JVM       │  │    │                  │
│          │  │  │  ┌───────────┐  │  │    │                  │
│          │  │  │  │  Native   │  │  │    │                  │
│          │  │  │  │  Method   │  │  │    │                  │
│          │  │  │  │  Library  │  │  │    │                  │
│          │  │  │  └───────────┘  │  │    │                  │
│          │  │  └─────────────────┘  │    │                  │
│          │  │  + Core Libraries     │    │                  │
│          │  └─────────────────────────┘    │                  │
│          │  + Dev Tools (javac, jar, etc.) │                  │
│          └─────────────────────────────────┘                  │
│                                                               │
│   JDK = JRE + Development Tools                               │
│   JRE = JVM + Core Libraries + Runtime Files                  │
│   JVM = Abstract machine that executes bytecode               │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Comparison Table

```
┌──────────────────────────────────────────────────────────────┐
│              JDK vs JRE vs JVM COMPARISON                     │
│  ┌────────────────┬────────────────┬────────────────────────┐ │
│  │ Feature        │ JDK │ JRE │ JVM │ Description            │ │
│  ├────────────────┼─────┼─────┼─────┼────────────────────────┤ │
│  │ Compiler       │ ✅ │ ❌ │ ❌ │ javac                   │ │
│  │ Debugger       │ ✅ │ ❌ │ ❌ │ jdb                     │ │
│  │ Archiver       │ ✅ │ ❌ │ ❌ │ jar                     │ │
│  │ Documentation  │ ✅ │ ❌ │ ❌ │ javadoc                 │ │
│  │ Disassembler   │ ✅ │ ❌ │ ❌ │ javap                   │ │
│  │ Run Java       │ ✅ │ ✅ │ ❌ │ java command            │ │
│  │ Core Libraries │ ✅ │ ✅ │ ❌ │ rt.jar / lib/modules    │ │
│  │ JVM            │ ✅ │ ✅ │ ✅ │ Runtime engine          │ │
│  │ Execute Bytecode│ ✅ │ ✅ │ ✅ │ Platform independence   │ │
│  └────────────────┴─────┴─────┴─────┴────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 3. JAR vs WAR vs EAR

### JAR (Java Archive)

- **Purpose:** Package Java classes, metadata, and resources into a single file.
- **Extension:** `.jar`
- **Used for:** Libraries, standalone applications, utility classes.
- **Structure:** `META-INF/MANIFEST.MF` + class files + resources.
- **Main class:** Can specify `Main-Class` in manifest for executable JAR.

```
myapp.jar
├── META-INF/
│   └── MANIFEST.MF
├── com/
│   └── example/
│       ├── Main.class
│       └── Utils.class
└── resources/
    └── config.properties
```

### WAR (Web Application Archive)

- **Purpose:** Package web applications for deployment on a servlet container.
- **Extension:** `.war`
- **Used for:** Web applications (Servlets, JSPs, HTML, JS, CSS).
- **Container:** Tomcat, Jetty, WildFly, etc.
- **Structure:** `WEB-INF/web.xml`, `WEB-INF/classes/`, `WEB-INF/lib/`, static resources.

```
mywebapp.war
├── META-INF/
│   └── MANIFEST.MF
├── WEB-INF/
│   ├── web.xml
│   ├── classes/
│   │   └── com/example/servlet/MyServlet.class
│   └── lib/
│       ├── servlet-api.jar
│       └── dependency.jar
├── index.html
├── css/
│   └── style.css
└── js/
    └── app.js
```

### EAR (Enterprise Archive)

- **Purpose:** Package enterprise applications for deployment on a Java EE / Jakarta EE application server.
- **Extension:** `.ear`
- **Used for:** Multi-module enterprise applications (EJB, web, resource adapter).
- **Container:** WildFly, WebLogic, WebSphere, GlassFish.
- **Structure:** `META-INF/application.xml`, JAR modules, WAR modules, RAR modules.

```
myenterprise.ear
├── META-INF/
│   ├── MANIFEST.MF
│   └── application.xml
├── ejb-module.jar
├── web-module.war
├── resource-adapter.rar
└── lib/
    └── shared-libraries.jar
```

### Comparison Table

```
┌──────────────────────────────────────────────────────────────┐
│              JAR vs WAR vs EAR COMPARISON                     │
│  ┌────────────────┬─────────┬─────────┬────────────────────┐ │
│  │ Feature        │ JAR     │ WAR     │ EAR                │ │
│  ├────────────────┼─────────┼─────────┼────────────────────┤ │
│  │ Full Form      │ Java    │ Web App │ Enterprise Archive │ │
│  │                │ Archive │ Archive │                    │ │
│  ├────────────────┼─────────┼─────────┼────────────────────┤ │
│  │ Container      │ JRE/JVM │ Servlet │ Java EE / Jakarta  │ │
│  │                │         │ Container│ EE App Server     │ │
│  ├────────────────┼─────────┼─────────┼────────────────────┤ │
│  │ Contains       │ Classes │ Web     │ JAR + WAR + EJB    │ │
│  │                │ + libs  │ resources│ + RAR modules      │ │
│  ├────────────────┼─────────┼─────────┼────────────────────┤ │
│  │ Deployment     │ java -jar│ Drop to │ Deploy to app     │ │
│  │                │         │ webapp/ │ server            │ │
│  ├────────────────┼─────────┼─────────┼────────────────────┤ │
│  │ Web support    │ ❌     │ ✅     │ ✅ (via WAR)      │ │
│  │ EJB support    │ ❌     │ ❌     │ ✅ (via EJB JAR)  │ │
│  │ Main class     │ ✅     │ ❌     │ ❌                 │ │
│  └────────────────┴─────────┴─────────┴────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 4. Classpath and PATH

### What is PATH?

**PATH** is an environment variable that tells the operating system where to find executable programs. For Java, it should include the `bin` directory of the JDK so commands like `javac`, `java`, `jar` are accessible from any directory.

```
Windows Example:
PATH = C:\Program Files\Java\jdk-17\bin;C:\Windows\System32;...

Linux/macOS Example:
PATH = /usr/lib/jvm/java-17-openjdk/bin:/usr/local/bin:/usr/bin
```

### What is Classpath?

**Classpath** is a parameter that tells the JVM where to look for classes and packages. It can include directories, JAR files, and ZIP files.

```
Windows Example:
CLASSPATH = .;C:\myproject\classes;C:\myproject\lib\mysql-connector.jar

Linux/macOS Example:
CLASSPATH = .:/home/user/myproject/classes:/home/user/lib/mysql-connector.jar
```

### Classpath vs PATH

```
┌──────────────────────────────────────────────────────────────┐
│              CLASSPATH vs PATH                                 │
│  ┌────────────────┬──────────────────────────────────────┐  │
│  │ Aspect         │ PATH          │ CLASSPATH              │  │
│  ├────────────────┼────────────────┼──────────────────────┤  │
│  │ Purpose        │ Find OS        │ Find Java classes      │  │
│  │                │ executables    │ and resources          │  │
│  ├────────────────┼────────────────┼──────────────────────┤  │
│  │ Used by        │ OS shell       │ JVM / java compiler    │  │
│  ├────────────────┼────────────────┼──────────────────────┤  │
│  │ Java tools     │ javac, java,   │ .class files, .jar,    │  │
│  │                │ jar, javadoc   │ .zip files             │  │
│  ├────────────────┼────────────────┼──────────────────────┤  │
│  │ Default value  │ System defined │ Current directory (.)  │  │
│  │                │                │ if not set             │  │
│  ├────────────────┼────────────────┼──────────────────────┤  │
│  │ Set via        │ Environment    │ -cp, -classpath,        │  │
│  │                │ variable       │ CLASSPATH env var      │  │
│  └────────────────┴────────────────┴──────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Setting Classpath in Java Commands

```bash
# Using -cp or -classpath
javac -cp "lib/*;classes" src/Main.java        # Windows
javac -cp "lib/*:classes" src/Main.java          # Linux/macOS

java -cp "lib/*:classes" com.example.Main

# Using -jar (classpath in manifest)
java -jar myapp.jar

# Using module path (Java 9+)
java --module-path mods -m com.example/com.example.Main
```

---

## 5. Java Command Line Tools

### javac (Java Compiler)

Compiles `.java` source files into `.class` bytecode files.

```bash
# Basic compilation
javac HelloWorld.java

# Compile with classpath
javac -cp "lib/*" HelloWorld.java

# Compile with destination directory
javac -d classes HelloWorld.java

# Compile with source and target version
javac -source 11 -target 11 HelloWorld.java

# Compile multiple files
javac -d classes src/*.java

# Compile with encoding
javac -encoding UTF-8 HelloWorld.java

# Compile with module path
javac --module-path mods -d classes HelloWorld.java
```

### java (Java Launcher)

Runs compiled Java applications.

```bash
# Run a class
java com.example.Main

# Run with classpath
java -cp "classes;lib/*" com.example.Main

# Run with system properties
java -Dapp.env=production -Dapp.port=8080 com.example.Main

# Run with JVM options
java -Xmx512m -Xms256m com.example.Main

# Run a JAR file
java -jar myapp.jar

# Run with module system
java --module-path mods -m com.example/com.example.Main
```

### jar (Java Archive Tool)

Creates and manages JAR files.

```bash
# Create a JAR file
jar cvf myapp.jar -C classes .

# Create JAR with manifest
jar cvfm myapp.jar manifest.txt -C classes .

# List contents of JAR
jar tf myapp.jar

# Extract JAR contents
jar xf myapp.jar

# Update JAR contents
jar uf myapp.jar com/example/NewClass.class

# Create executable JAR
jar cvfe myapp.jar com.example.Main -C classes .
```

### javadoc (Documentation Generator)

Generates HTML documentation from Java source code comments.

```bash
# Generate docs for a package
javadoc -d docs src/com/example/*.java

# Generate docs with classpath
javadoc -cp "lib/*" -d docs src/com/example/*.java

# Generate docs for specific access level
javadoc -private -d docs src/com/example/*.java
```

### javap (Class Disassembler)

Disassembles compiled Java classes to show bytecode, methods, fields, etc.

```bash
# Basic disassembly
javap com.example.Main

# Show all methods (including private)
javap -p com.example.Main

# Show verbose output
javap -v com.example.Main

# Show bytecode
javap -c com.example.Main

# Show constant pool
javap -v -p com.example.Main
```

### Other Important Tools

```
┌──────────────────────────────────────────────────────────────┐
│              JAVA COMMAND LINE TOOLS SUMMARY                   │
│  ┌────────────────┬──────────────────────────────────────┐  │
│  │ Tool           │ Purpose                              │  │
│  ├────────────────┼──────────────────────────────────────┤  │
│  │ javac          │ Compile .java to .class              │  │
│  │ java           │ Run Java application                 │  │
│  │ jar            │ Create/manage JAR files              │  │
│  │ javadoc        │ Generate API documentation           │  │
│  │ javap          │ Disassemble .class files             │  │
│  │ jdb            │ Java debugger                        │  │
│  │ jconsole       │ JVM monitoring GUI                   │  │
│  │ jvisualvm      │ Visual profiling tool                │  │
│  │ jstat          │ JVM statistics                       │  │
│  │ jstack         │ Thread dump                          │  │
│  │ jmap           │ Memory map                           │  │
│  │ jcmd           │ JVM diagnostic command               │  │
│  │ keytool        │ Key and certificate management       │  │
│  │ jarsigner      │ Sign and verify JAR files            │  │
│  │ jshell         │ Interactive Java REPL (Java 9+)      │  │
│  └────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

---

## 6. Build Tools Basics

### Why Build Tools?

Build tools automate the process of compiling, testing, packaging, and deploying Java applications. They manage dependencies, run tests, and ensure consistent builds.

### Ant (Apache Ant)

- **Released:** 2000
- **Approach:** Procedural (XML-based scripts)
- **Build file:** `build.xml`
- **Key concepts:** Targets, tasks, dependencies.
- **Pros:** Flexible, simple, no conventions.
- **Cons:** Verbose, no dependency management natively.

```xml
<!-- build.xml -->
<project name="MyApp" default="compile">
    <property name="src" value="src"/>
    <property name="build" value="build"/>
    <property name="dist" value="dist"/>

    <target name="clean">
        <delete dir="${build}"/>
        <delete dir="${dist}"/>
    </target>

    <target name="compile" depends="clean">
        <mkdir dir="${build}"/>
        <javac srcdir="${src}" destdir="${build}"/>
    </target>

    <target name="jar" depends="compile">
        <mkdir dir="${dist}"/>
        <jar destfile="${dist}/myapp.jar" basedir="${build}">
            <manifest>
                <attribute name="Main-Class" value="com.example.Main"/>
            </manifest>
        </jar>
    </target>
</project>
```

```bash
# Ant commands
ant compile    # Compile the project
ant jar        # Create JAR
ant clean      # Clean build artifacts
ant            # Run default target
```

### Maven

- **Released:** 2004
- **Approach:** Declarative (convention over configuration)
- **Build file:** `pom.xml` (Project Object Model)
- **Key concepts:** Coordinates (groupId, artifactId, version), lifecycle, plugins, dependencies, repositories.
- **Pros:** Standardized, excellent dependency management, large plugin ecosystem.
- **Cons:** XML can be verbose, steep learning curve.

```xml
<!-- pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>myapp</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

```bash
# Maven commands
mvn compile       # Compile source code
mvn test          # Run tests
mvn package       # Package into JAR/WAR
mvn install       # Install to local repository
mvn clean         # Clean build directory
mvn clean install # Full rebuild and install
mvn dependency:tree # Show dependency tree
```

### Gradle

- **Released:** 2008
- **Approach:** Hybrid (convention + scripting)
- **Build file:** `build.gradle` (Groovy) or `build.gradle.kts` (Kotlin)
- **Key concepts:** Tasks, plugins, dependencies, closures.
- **Pros:** Flexible, concise DSL, fast (incremental builds, build cache), great Android support.
- **Cons:** Groovy/Kotlin knowledge needed, dynamic scripts can be complex.

```groovy
// build.gradle
plugins {
    id 'java'
}

group = 'com.example'
version = '1.0.0'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.google.guava:guava:31.1-jre'
    testImplementation 'junit:junit:4.13.2'
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

jar {
    manifest {
        attributes 'Main-Class': 'com.example.Main'
    }
}
```

```bash
# Gradle commands
gradle compileJava      # Compile Java source
gradle test           # Run tests
gradle build          # Full build (compile + test + package)
gradle clean          # Clean build directory
gradle jar            # Create JAR
gradle dependencies   # Show dependency tree
gradle wrapper        # Generate wrapper files
```

### Build Tools Comparison

```
┌──────────────────────────────────────────────────────────────┐
│              ANT vs MAVEN vs GRADLE COMPARISON               │
│  ┌────────────────┬──────────┬──────────┬────────────────┐  │
│  │ Feature        │ Ant      │ Maven    │ Gradle         │  │
│  ├────────────────┼──────────┼──────────┼────────────────┤  │
│  │ Configuration  │ XML      │ XML      │ Groovy/Kotlin  │  │
│  │ Style          │ Procedural│ Declarative│ Hybrid       │  │
│  │ Conventions    │ None     │ Strong   │ Moderate       │  │
│  │ Dependencies   │ Manual   │ Central  │ Central        │  │
│  │ Learning Curve │ Low      │ Medium   │ Medium         │  │
│  │ Flexibility    │ High     │ Medium   │ High           │  │
│  │ Performance    │ Medium   │ Medium   │ Fast           │  │
│  │ Build Cache    │ ❌       │ ❌       │ ✅             │  │
│  │ Incremental    │ Manual   │ Limited  │ ✅             │  │
│  │ Android        │ ❌       │ ❌       │ ✅ (primary)   │  │
│  │ Community      │ Old      │ Large    │ Growing        │  │
│  └────────────────┴──────────┴──────────┴────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

---

## 7. Build Lifecycle

### Maven Build Lifecycle

Maven has three built-in lifecycles:

1. **Default:** Handles project deployment
2. **Clean:** Handles project cleaning
3. **Site:** Handles project site documentation

```
┌──────────────────────────────────────────────────────────────┐
│              MAVEN DEFAULT LIFECYCLE PHASES                    │
│                                                               │
│   validate → compile → test → package → verify → install → deploy│
│                                                               │
│   ┌─────────────┬──────────────────────────────────────────┐ │
│   │ Phase       │ Description                                │ │
│   ├─────────────┼──────────────────────────────────────────┤ │
│   │ validate    │ Validate project structure and info      │ │
│   │ compile     │ Compile source code                      │ │
│   │ test        │ Run unit tests using testing framework    │ │
│   │ package     │ Package compiled code into distributable   │ │
│   │             │ format (JAR, WAR)                        │ │
│   │ verify      │ Run integration tests                    │ │
│   │ install     │ Install package into local repository    │ │
│   │ deploy      │ Deploy to remote repository               │ │
│   └─────────────┴──────────────────────────────────────────┘ │
│                                                               │
│   CLEAN LIFECYCLE: pre-clean → clean → post-clean            │
│   SITE LIFECYCLE: pre-site → site → post-site → site-deploy │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Gradle Build Lifecycle

Gradle uses a **task-based** model rather than phases.

```
┌──────────────────────────────────────────────────────────────┐
│              GRADLE BUILD LIFECYCLE                            │
│                                                               │
│   Initialization → Configuration → Execution                   │
│                                                               │
│   ┌────────────────┬────────────────────────────────────────┐ │
│   │ Phase          │ Description                            │ │
│   ├────────────────┼────────────────────────────────────────┤ │
│   │ Initialization │ Determine projects, create settings  │ │
│   │ Configuration  │ Configure project and tasks, build DAG │ │
│   │ Execution      │ Execute selected tasks                 │ │
│   └────────────────┴────────────────────────────────────────┘ │
│                                                               │
│   Java Plugin Tasks:                                           │
│   compileJava → processResources → classes → compileTestJava   │
│   → test → jar → assemble → check → build                      │
│                                                               │
│   Task Dependencies:                                           │
│   build dependsOn: assemble, check                             │
│   check dependsOn: test                                        │
│   assemble dependsOn: jar                                      │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Common Build Lifecycle Commands

```bash
# Maven
mvn clean          # Remove target/ directory
mvn compile        # Compile src/main/java
mvn test-compile   # Compile src/test/java
mvn test           # Run tests
mvn package        # Create JAR/WAR in target/
mvn install        # Package + install to ~/.m2/repository
mvn deploy         # Deploy to remote repository
mvn clean install  # Full clean build

# Gradle
gradle clean       # Remove build/ directory
gradle compileJava # Compile main source
gradle compileTestJava # Compile test source
gradle test        # Run tests
gradle jar         # Create JAR
gradle build       # Compile, test, package
gradle assemble    # Package without tests
gradle check       # Run tests and checks
```

---

## 8. Interview FAQs

### Q1. What is the difference between JDK, JRE, and JVM?

**Answer:**
- **JVM (Java Virtual Machine):** The runtime engine that executes Java bytecode. It provides platform independence.
- **JRE (Java Runtime Environment):** Contains JVM + core libraries + runtime files. It is the minimum environment needed to run Java applications.
- **JDK (Java Development Kit):** Contains JRE + development tools (javac, jar, javadoc, javap, debugger, etc.). It is needed to develop Java applications.

Relationship: `JDK ⊃ JRE ⊃ JVM`

### Q2. What is the difference between JAR, WAR, and EAR?

**Answer:**
- **JAR:** Package for Java classes, libraries, and standalone applications. Executed with `java -jar`.
- **WAR:** Package for web applications. Contains servlets, JSPs, HTML, JS, CSS. Deployed to servlet containers like Tomcat.
- **EAR:** Package for enterprise applications. Contains JAR, WAR, and EJB modules. Deployed to Java EE application servers like WebLogic, WildFly.

### Q3. What is the difference between PATH and CLASSPATH?

**Answer:**
- **PATH:** Used by the operating system to find executable programs (e.g., `javac`, `java`). It points to the JDK's `bin` directory.
- **CLASSPATH:** Used by the JVM to find compiled Java classes and resources. It points to directories, JAR files, or ZIP files containing `.class` files.

### Q4. What is the purpose of the `javac` command?

**Answer:** `javac` is the Java compiler. It compiles Java source files (`.java`) into bytecode files (`.class`). It performs syntax checking, type checking, and generates platform-independent bytecode.

### Q5. What does the `javap` command do?

**Answer:** `javap` (Java disassembler) displays information about compiled Java classes. It shows the class's methods, fields, constructors, bytecode, and constant pool. It is useful for understanding compiled code and verifying compiler behavior.

### Q6. What is the difference between Ant, Maven, and Gradle?

**Answer:**
- **Ant:** Procedural, XML-based, no built-in dependency management, flexible but verbose.
- **Maven:** Declarative, convention over configuration, POM-based, strong dependency management, standardized lifecycle.
- **Gradle:** Hybrid, Groovy/Kotlin DSL, combines convention and flexibility, fast incremental builds, build cache, great for Android.

### Q7. What are Maven coordinates (groupId, artifactId, version)?

**Answer:** Maven coordinates uniquely identify a project or dependency:
- **groupId:** Organization or group (e.g., `com.example`).
- **artifactId:** Project name (e.g., `myapp`).
- **version:** Release version (e.g., `1.0.0`, `1.0.0-SNAPSHOT`).

Format: `groupId:artifactId:version` (e.g., `com.example:myapp:1.0.0`)

### Q8. What is the Maven local repository?

**Answer:** The Maven local repository is a directory on the developer's machine (`~/.m2/repository` on Linux/macOS, `%USERPROFILE%\.m2\repository` on Windows) where Maven stores downloaded dependencies and locally installed artifacts. It prevents re-downloading dependencies and allows sharing artifacts across projects.

### Q9. What is the difference between `compile` and `test` scope in Maven?

**Answer:**
- **compile scope:** Dependency is available in all classpaths (main, test, runtime). This is the default scope.
- **test scope:** Dependency is only available in the test classpath. It is not included in the final package or available at runtime.

### Q10. What is Gradle's build cache?

**Answer:** Gradle's build cache stores previous build outputs. When a task's inputs haven't changed, Gradle reuses the cached output instead of re-running the task. This significantly speeds up builds, especially in CI/CD environments and when switching branches. It can be local or remote (shared across teams).

---

## 9. Key Takeaways

1. **JDK** includes JRE + development tools. **JRE** includes JVM + libraries. **JVM** executes bytecode.
2. **JAR** packages classes/libraries. **WAR** packages web apps. **EAR** packages enterprise apps.
3. **PATH** points to OS executables. **CLASSPATH** points to Java classes and resources.
4. `javac` compiles `.java` to `.class`. `java` runs the application. `jar` creates archives. `javadoc` generates docs. `javap` disassembles classes.
5. **Ant** is procedural XML-based. **Maven** is declarative with POM and lifecycle. **Gradle** is hybrid DSL-based with fast incremental builds.
6. **Maven Default Lifecycle:** validate → compile → test → package → verify → install → deploy.
7. **Gradle Lifecycle:** Initialization → Configuration → Execution. Tasks form a Directed Acyclic Graph (DAG).
8. **Maven coordinates** (`groupId:artifactId:version`) uniquely identify artifacts.
9. **Dependency scopes** in Maven (compile, test, provided, runtime) control classpath availability.
10. **Gradle build cache** and **incremental builds** make Gradle significantly faster than Maven for large projects.

---

**Happy coding! 🚀**

*Master the Java development ecosystem, and you master the art of building software at scale.*
