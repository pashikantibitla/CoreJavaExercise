# Chapter 15 — JVM Architecture

> **Topics:** JVM Internals, Class Loading Subsystem, Runtime Data Areas, Execution Engine, Class Loaders, Delegation Model, JVM Tuning, Interview FAQs

---

## Table of Contents

1. [JVM Architecture Overview](#1-jvm-architecture-overview)
2. [Class Loading Subsystem](#2-class-loading-subsystem)
3. [Linking](#3-linking)
4. [Initialization](#4-initialization)
5. [Runtime Data Areas](#5-runtime-data-areas)
6. [Execution Engine](#6-execution-engine)
7. [Class Loader Types](#7-class-loader-types)
8. [Types of Class Loaders](#8-types-of-class-loaders)
9. [How Java Class Loader Works](#9-how-java-class-loader-works)
10. [JVM Options and Tuning](#10-jvm-options-and-tuning)
11. [Interview FAQs](#11-interview-faqs)

---

## 📁 Files

- **15_JVM_Architecture.md** — Comprehensive theory and notes
- **java_programs/** — Java programs demonstrating class loading, memory monitoring, and interview problems

---

## 🎯 Key Takeaways

1. **JVM** provides the runtime environment for Java bytecode.
2. **Class Loader Subsystem** loads, links, and initializes class files.
3. **Runtime Data Areas** include Method Area, Heap, Stack, PC Register, and Native Method Stack.
4. **Execution Engine** executes bytecode using an interpreter and JIT compiler.
5. **Garbage Collector** reclaims memory automatically from the heap.
6. **Delegation Model** ensures class loading security and avoids duplicate loading.
7. **JVM Tuning** involves heap size, stack size, and GC selection for performance.

---

**Happy coding! 🚀**
