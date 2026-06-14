# 17 — File I/O

> **Topics:** File Class, FileWriter, FileReader, BufferedWriter, BufferedReader, PrintWriter, Streams, Serialization, FileInputStream, FileOutputStream, NIO.2, Interview FAQs

---

## Table of Contents

1. [Introduction to File I/O](#1-introduction-to-file-io)
2. [File Class](#2-file-class)
3. [FileWriter and FileReader](#3-filewriter-and-filereader)
4. [BufferedWriter and BufferedReader](#4-bufferedwriter-and-bufferedreader)
5. [PrintWriter](#5-printwriter)
6. [InputStream and OutputStream](#6-inputstream-and-outputstream)
7. [Object Serialization](#7-object-serialization)
8. [FileInputStream and FileOutputStream](#8-fileinputstream-and-fileoutputstream)
9. [NIO.2 Path and Files](#9-nio2-path-and-files)
10. [Interview FAQs](#10-interview-faqs)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. Introduction to File I/O

### What is File I/O?

**File I/O** (Input/Output) refers to reading data from and writing data to files on a storage device. Java provides a rich set of classes for file operations in the `java.io` and `java.nio` packages.

```
┌─────────────────────────────────────────────────────────────────────┐
│                    JAVA I/O CLASSIFICATION                            │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │  Based on Data Type                                            │  │
│  │  ├─ Character Streams (Reader/Writer) → 16-bit Unicode        │  │
│  │  └─ Byte Streams (InputStream/OutputStream) → 8-bit raw bytes│  │
│  │                                                                │  │
│  │  Based on Functionality                                        │  │
│  │  ├─ Node Streams (directly connect to source)                 │  │
│  │  └─ Processing Streams (wrap node streams)                  │  │
│  │                                                                │  │
│  │  NIO.2 (Java 7+)                                               │  │
│  │  └─ Path, Files, Paths → modern file operations               │  │
│  └─────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 2. File Class

### What is the File Class?

The `File` class is an abstract representation of file and directory pathnames. It provides methods to create, delete, rename, and query file properties.

```java
import java.io.File;

public class FileClassDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("test.txt");
        
        // Create new file
        boolean created = file.createNewFile();
        System.out.println("Created: " + created);
        
        // Check properties
        System.out.println("Exists: " + file.exists());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Directory: " + file.isDirectory());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Name: " + file.getName());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Length: " + file.length());
        System.out.println("Last Modified: " + file.lastModified());
        System.out.println("Can Read: " + file.canRead());
        System.out.println("Can Write: " + file.canWrite());
        
        // Delete file
        boolean deleted = file.delete();
        System.out.println("Deleted: " + deleted);
    }
}
```

### File Class Important Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    FILE CLASS METHODS                                 │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Method             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ createNewFile()    │ Creates a new empty file                 │  │
│  │ delete()           │ Deletes the file or directory            │  │
│  │ exists()           │ Tests whether the file exists            │  │
│  │ isFile()           │ Tests if pathname is a file              │  │
│  │ isDirectory()      │ Tests if pathname is a directory         │  │
│  │ getName()          │ Returns the file name                    │  │
│  │ getAbsolutePath()  │ Returns absolute pathname                │  │
│  │ getParent()        │ Returns parent directory path            │  │
│  │ length()           │ Returns file size in bytes               │  │
│  │ lastModified()     │ Returns last modified timestamp          │  │
│  │ canRead()          │ Tests if file is readable                │  │
│  │ canWrite()         │ Tests if file is writable                │  │
│  │ mkdir()            │ Creates a directory                      │  │
│  │ mkdirs()           │ Creates directories including parents    │  │
│  │ list()             │ Returns list of files in directory       │  │
│  │ listFiles()        │ Returns File array of directory contents │  │
│  │ renameTo(File)     │ Renames the file                         │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 3. FileWriter and FileReader

### FileWriter

`FileWriter` is a convenience class for writing character files. It extends `OutputStreamWriter`.

```java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
    public static void main(String[] args) {
        // FileWriter writes characters to a file
        // If file does not exist, it is created
        // If append is true, data is appended; otherwise overwritten
        try (FileWriter writer = new FileWriter("output.txt", true)) {
            writer.write("Hello, FileWriter!\n");
            writer.write("Line 2\n");
            writer.write(65); // writes character 'A'
            writer.write(new char[] {'B', 'C', 'D'});
            writer.write("\nEnd of file.\n");
            // flush is automatic with try-with-resources on close
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### FileWriter Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    FILEWRITER METHODS                                 │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Method             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ write(int c)       │ Writes a single character                │  │
│  │ write(char[] cbuf) │ Writes character array                   │  │
│  │ write(String str)  │ Writes a string                          │  │
│  │ write(String, off,│ Writes part of a string                  │  │
│  │   len)             │                                          │  │
│  │ flush()            │ Flushes the stream                       │  │
│  │ close()            │ Closes the stream and releases resources │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### FileReader

`FileReader` is a convenience class for reading character files. It extends `InputStreamReader`.

```java
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("output.txt")) {
            int ch;
            // read() returns -1 at end of file
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### FileReader Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    FILEREADER METHODS                                 │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Method             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ read()             │ Reads a single character (returns int)   │  │
│  │ read(char[] cbuf)  │ Reads characters into array              │  │
│  │ read(char[], off,  │ Reads characters into part of array      │  │
│  │   len)             │                                          │  │
│  │ close()            │ Closes the stream                        │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 4. BufferedWriter and BufferedReader

### BufferedWriter

`BufferedWriter` writes text to a character-output stream, buffering characters for efficient writing.

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered.txt"))) {
            bw.write("First line");
            bw.newLine(); // platform-independent newline
            bw.write("Second line");
            bw.newLine();
            bw.write("Third line");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### BufferedReader

`BufferedReader` reads text from a character-input stream, buffering characters for efficient reading.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("buffered.txt"))) {
            String line;
            // readLine() returns null at end of file
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Buffered Classes Comparison

```
┌─────────────────────────────────────────────────────────────────────┐
│                    BUFFERED vs UNBUFFERED                             │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ Buffered           │ Unbuffered         │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Performance        │ Faster (fewer I/O) │ Slower (many I/O)  │  │
│  │ Memory             │ Uses buffer        │ No buffer          │  │
│  │ readLine()         │ Yes (BufferedReader│ No (FileReader)    │  │
│  │ newLine()          │ Yes (BufferedWriter│ No (FileWriter)    │  │
│  │ Use Case           │ Large files        │ Small files        │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 5. PrintWriter

### PrintWriter

`PrintWriter` prints formatted representations of objects to a text-output stream. It never throws `IOException` (uses `checkError()` instead).

```java
import java.io.PrintWriter;
import java.io.IOException;

public class PrintWriterDemo {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter("print.txt")) {
            pw.println("Hello, PrintWriter!");
            pw.print("Number: ");
            pw.println(42);
            pw.printf("Formatted: %.2f%n", 3.14159);
            pw.write("Raw write\n");
            
            if (pw.checkError()) {
                System.out.println("An error occurred");
            }
        }
    }
}
```

### PrintWriter Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    PRINTWRITER METHODS                                │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Method             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ print()            │ Prints text (no newline)                 │  │
│  │ println()          │ Prints text with newline                 │  │
│  │ printf()           │ Prints formatted text                    │  │
│  │ write()            │ Writes character or string               │  │
│  │ flush()            │ Flushes the stream                       │  │
│  │ close()            │ Closes the stream                        │  │
│  │ checkError()       │ Returns true if an error occurred        │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 6. InputStream and OutputStream

### InputStream

`InputStream` is the abstract base class for all byte input streams. It reads bytes from a source.

```java
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamDemo {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("output.txt")) {
            int data;
            while ((data = is.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### OutputStream

`OutputStream` is the abstract base class for all byte output streams. It writes bytes to a destination.

```java
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamDemo {
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("bytes.bin")) {
            byte[] data = { 65, 66, 67, 68, 69 }; // A B C D E
            os.write(data);
            os.write(70); // F
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Key Methods

```
┌─────────────────────────────────────────────────────────────────────┐
│                    INPUTSTREAM / OUTPUTSTREAM METHODS                   │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Method             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ read()             │ Reads one byte (returns int, -1 = EOF)   │  │
│  │ read(byte[] b)     │ Reads bytes into array                   │  │
│  │ write(int b)       │ Writes one byte                          │  │
│  │ write(byte[] b)    │ Writes bytes from array                  │  │
│  │ flush()            │ Flushes buffered bytes                   │  │
│  │ close()            │ Closes the stream                        │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 7. Object Serialization

### What is Serialization?

**Serialization** is the process of converting an object into a byte stream. **Deserialization** reconstructs the object from the byte stream. Java provides `ObjectOutputStream` and `ObjectInputStream`.

```java
import java.io.*;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private transient String password; // transient field is not serialized
    
    public Employee(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', password='" + password + "'}";
    }
}
```

```java
import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        String file = "employee.ser";
        
        // Serialize
        Employee emp = new Employee(101, "Alice", "secret123");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(emp);
            System.out.println("Serialized: " + emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Employee restored = (Employee) ois.readObject();
            System.out.println("Deserialized: " + restored);
            // Note: password will be null because it is transient
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

### Serialization Rules

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SERIALIZATION RULES                                  │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Rule               │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Serializable       │ Class must implement Serializable        │  │
│  │ serialVersionUID     │ Recommended for version compatibility    │  │
│  │ transient           │ Field is skipped during serialization  │  │
│  │ static              │ Static fields are not serialized         │  │
│  │ Constructor         │ No-arg constructor not required for      │  │
│  │                     │ deserialization                          │  │
│  │ Super class         │ Must be serializable or have no-arg    │  │
│  │                     │ constructor                              │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 8. FileInputStream and FileOutputStream

### FileInputStream

`FileInputStream` reads raw bytes from a file.

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("bytes.bin")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### FileOutputStream

`FileOutputStream` writes raw bytes to a file.

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("copy.bin")) {
            String text = "Hello, FileOutputStream!";
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 9. NIO.2 Path and Files

### NIO.2 Overview

Java 7 introduced the **NIO.2** package (`java.nio.file`) with `Path`, `Paths`, and `Files` classes for modern file operations.

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class NIO2Demo {
    public static void main(String[] args) throws IOException {
        // Path represents a file or directory path
        Path path = Paths.get("nio2.txt");
        
        // Create file
        if (!Files.exists(path)) {
            Files.createFile(path);
            System.out.println("Created: " + path);
        }
        
        // Write lines
        List<String> lines = List.of("Line 1", "Line 2", "Line 3");
        Files.write(path, lines);
        
        // Read all lines
        List<String> readLines = Files.readAllLines(path);
        System.out.println("Contents:");
        readLines.forEach(System.out::println);
        
        // Copy file
        Path copy = Paths.get("nio2_copy.txt");
        Files.copy(path, copy, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Copied to: " + copy);
        
        // Move file
        Path moved = Paths.get("nio2_moved.txt");
        Files.move(copy, moved, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Moved to: " + moved);
        
        // Delete file
        Files.deleteIfExists(moved);
        System.out.println("Deleted: " + moved);
        
        // File attributes
        System.out.println("Size: " + Files.size(path));
        System.out.println("Is readable: " + Files.isReadable(path));
        System.out.println("Is writable: " + Files.isWritable(path));
        
        // Walk directory tree
        Path root = Paths.get(".");
        System.out.println("\nDirectory tree (max 2 levels):");
        Files.walk(root, 2).forEach(System.out::println);
    }
}
```

### NIO.2 Key Classes

```
┌─────────────────────────────────────────────────────────────────────┐
│                    NIO.2 KEY CLASSES                                  │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Class/Method       │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ Path               │ Represents file/directory path           │  │
│  │ Paths.get()        │ Creates a Path instance                  │  │
│  │ Files.exists()     │ Checks if path exists                    │  │
│  │ Files.createFile() │ Creates a new file                       │  │
│  │ Files.createDir()  │ Creates a new directory                  │  │
│  │ Files.readAllLines()│ Reads all lines into a List               │  │
│  │ Files.write()      │ Writes lines to file                     │  │
│  │ Files.copy()       │ Copies a file                            │  │
│  │ Files.move()       │ Moves/renames a file                     │  │
│  │ Files.delete()     │ Deletes a file                           │  │
│  │ Files.walk()       │ Walks directory tree                     │  │
│  │ Files.size()       │ Returns file size                        │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 10. Interview FAQs

### Q1. What is the difference between FileInputStream and FileReader?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    FILEINPUTSTREAM vs FILEREADER                      │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ FileInputStream    │ FileReader         │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Data Type          │ Raw bytes (8-bit)  │ Characters (16-bit)│  │
│  │ Base Class         │ InputStream        │ Reader             │  │
│  │ Use Case           │ Binary files       │ Text files         │  │
│  │ Encoding           │ Not handled        │ Platform default   │  │
│  │ Buffered version   │ BufferedInputStream│ BufferedReader     │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q2. What is the difference between BufferedReader and Scanner?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    BUFFEREDREADER vs SCANNER                          │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ BufferedReader     │ Scanner            │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Speed              │ Faster (buffered)  │ Slower (tokenized) │  │
│  │ Parsing            │ Manual parsing     │ Built-in parsing   │  │
│  │ Memory             │ Fixed buffer       │ Token-based        │  │
│  │ readLine()         │ Yes                │ nextLine()         │  │
│  │ Regex support      │ No                 │ Yes                │  │
│  │ Use Case           │ Large text files   │ Interactive input  │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q3. What is the difference between Serializable and Externalizable?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SERIALIZABLE vs EXTERNALIZABLE                     │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ Serializable       │ Externalizable     │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Interface          │ java.io.Serializable│ java.io.Externalizable│  │
│  │ Control            │ JVM handles         │ Programmer handles │  │
│  │ Methods            │ None required       │ writeExternal,    │  │
│  │                    │                     │ readExternal       │  │
│  │ Performance        │ Slower (reflection) │ Faster (manual)    │  │
│  │ Flexibility        │ Less                │ More               │  │
│  │ serialVersionUID   │ Recommended         │ Optional           │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q4. What is the purpose of the `transient` keyword?

```java
// transient fields are NOT serialized.
// Use cases: passwords, sensitive data, temporary/cache values.

public class User implements Serializable {
    private String username;
    private transient String password; // skipped during serialization
    private transient int age;       // skipped, deserialized as 0
}
```

### Q5. What is the difference between File and Path?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    FILE vs PATH                                         │
│  ┌────────────────────┬────────────────────┬────────────────────┐  │
│  │ Feature            │ java.io.File       │ java.nio.file.Path │  │
│  ├────────────────────┼────────────────────┼────────────────────┤  │
│  │ Introduced         │ Java 1.0            │ Java 7 (NIO.2)     │  │
│  │ API Style          │ Older, methods on │ Modern, utility    │  │
│  │                    │ File object       │ class Files        │  │
│  │ Symbolic Links     │ Limited support   │ Full support       │  │
│  │ Immutability       │ Mutable           │ Immutable          │  │
│  │ Performance        │ Slower            │ Faster             │  │
│  │ Use Case           │ Legacy code       │ Modern Java code   │  │
│  └────────────────────┴────────────────────┴────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q6. What is the difference between `flush()` and `close()`?

```java
// flush() — writes buffered data to the underlying stream without closing
// close() — flushes and then closes the stream, releasing resources

// Always use try-with-resources or close() to prevent resource leaks.
```

### Q7. How do you copy a file in Java?

```java
// Using NIO.2 (recommended):
Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

// Using streams:
try (InputStream in = new FileInputStream("source.txt");
     OutputStream out = new FileOutputStream("target.txt")) {
    byte[] buffer = new byte[1024];
    int len;
    while ((len = in.read(buffer)) > 0) {
        out.write(buffer, 0, len);
    }
}
```

### Q8. What is `serialVersionUID` and why is it important?

```java
// serialVersionUID is a unique identifier for a Serializable class.
// It is used during deserialization to verify that sender and receiver
// have compatible class versions.

// If the UID does not match, InvalidClassException is thrown.

private static final long serialVersionUID = 1L;

// Best practice: always declare it explicitly to avoid
// auto-generated UID mismatches during class changes.
```

### Q9. What is the difference between `System.out`, `System.err`, and `System.in`?

```
┌─────────────────────────────────────────────────────────────────────┐
│                    SYSTEM STREAMS                                       │
│  ┌────────────────────┬──────────────────────────────────────────┐  │
│  │ Stream             │ Description                              │  │
│  ├────────────────────┼──────────────────────────────────────────┤  │
│  │ System.out         │ Standard output stream (PrintStream)     │  │
│  │ System.err         │ Standard error stream (PrintStream)      │  │
│  │ System.in          │ Standard input stream (InputStream)      │  │
│  │                    │ typically keyboard input                 │  │
│  └────────────────────┴──────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

### Q10. Explain `try-with-resources` in Java I/O.

```java
// try-with-resources automatically closes resources that implement AutoCloseable.

// Example:
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
// br is automatically closed here, even if an exception occurs.

// Multiple resources:
try (BufferedReader br = new BufferedReader(new FileReader("in.txt"));
     PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))) {
    pw.println(br.readLine());
}
```

---

## 11. Key Takeaways

1. **File** class provides metadata and file system operations (create, delete, check).
2. **FileWriter/FileReader** are simple character-based I/O classes.
3. **BufferedWriter/BufferedReader** improve performance and provide `newLine()` and `readLine()`.
4. **PrintWriter** supports formatted output (`print`, `println`, `printf`).
5. **InputStream/OutputStream** are abstract byte-level I/O classes.
6. **ObjectInputStream/ObjectOutputStream** handle Java object serialization.
7. **FileInputStream/FileOutputStream** read and write raw binary data.
8. **NIO.2** (`Path`, `Files`, `Paths`) offers modern, efficient file I/O.
9. **try-with-resources** ensures streams are closed automatically.
10. **transient** fields are skipped during serialization.

---

**Happy coding! 🚀**
