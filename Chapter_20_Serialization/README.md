# Chapter 20 — Serialization

> **Topics:** Introduction, ObjectOutputStream, ObjectInputStream, Serializable, transient, serialVersionUID, Custom Serialization, Externalizable, Inheritance, Interview FAQs

---

## Table of Contents

1. [Introduction to Serialization](#1-introduction-to-serialization)
2. [ObjectOutputStream and ObjectInputStream](#2-objectoutputstream-and-objectinputstream)
3. [Serializable Interface](#3-serializable-interface)
4. [The transient Keyword](#4-the-transient-keyword)
5. [serialVersionUID](#5-serialversionuid)
6. [Custom Serialization](#6-custom-serialization)
7. [Externalizable Interface](#7-externalizable-interface)
8. [Serialization with Inheritance](#8-serialization-with-inheritance)
9. [Interview FAQs](#9-interview-faqs)

---

## 1. Introduction to Serialization

**File:** `20_Serialization.md`  
**Topics:** Introduction

- What is serialization?
- Why use serialization?
- Serializable interface
- ObjectOutputStream and ObjectInputStream

---

## 2. ObjectOutputStream and ObjectInputStream

**File:** `20_Serialization.md`  
**Topics:** Core classes

- `ObjectOutputStream.writeObject()`
- `ObjectInputStream.readObject()`
- Serialization and deserialization workflow
- Exception handling

---

## 3. Serializable Interface

**File:** `20_Serialization.md`  
**Topics:** Marker interface

- Marker interface concept
- Object graph serialization
- Rules for serializable classes
- Handling non-serializable fields

---

## 4. The transient Keyword

**File:** `20_Serialization.md`  
**Topics:** Excluding fields

- What is transient?
- Use cases for transient
- Default values after deserialization
- Security considerations

---

## 5. serialVersionUID

**File:** `20_Serialization.md`  
**Topics:** Version control

- What is serialVersionUID?
- Why declare it explicitly?
- Compatible vs incompatible changes
- InvalidClassException

---

## 6. Custom Serialization

**File:** `20_Serialization.md`  
**Topics:** writeObject and readObject

- `writeObject(ObjectOutputStream)`
- `readObject(ObjectInputStream)`
- `writeReplace()` and `readResolve()`
- Encryption and sensitive data

---

## 7. Externalizable Interface

**File:** `20_Serialization.md`  
**Topics:** Full control

- Externalizable vs Serializable
- `writeExternal()` and `readExternal()`
- Performance benefits
- Required no-arg constructor

---

## 8. Serialization with Inheritance

**File:** `20_Serialization.md`  
**Topics:** Class hierarchies

- Serializable parent
- Non-serializable parent
- Handling parent fields
- Constructor behavior during deserialization

---

## 9. Interview FAQs

**File:** `20_Serialization.md`  
**Topics:** Common questions

- What is serialization?
- Serializable vs Externalizable
- Purpose of transient
- Importance of serialVersionUID
- Custom serialization
- Static fields and serialization
- Inheritance and serialization
- Common exceptions

---

## 📁 Additional Files

- **java_programs/** — Individual .java files demonstrating:
  - Basic serialization/deserialization
  - transient fields
  - serialVersionUID usage
  - Custom serialization
  - Externalizable interface
  - Interview-level problems

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Introduction to Serialization ──→ What and why
    │
    ├── ObjectOutputStream / ObjectInputStream ──→ Core classes
    │
    ├── Serializable Interface ──→ Marker interface and rules
    │
    ├── transient Keyword ──→ Excluding fields
    │
    ├── serialVersionUID ──→ Version compatibility
    │
    ├── Custom Serialization ──→ writeObject and readObject
    │
    ├── Externalizable Interface ──→ Full control
    │
    ├── Serialization with Inheritance ──→ Class hierarchies
    │
    └── Interview FAQs ──→ Common questions and answers
```

---

## 📋 Key Takeaways

1. **Serialization converts objects to byte streams** for persistence or network transfer.
2. **Serializable** is a marker interface — no methods to implement.
3. **ObjectOutputStream** serializes; **ObjectInputStream** deserializes.
4. **transient** fields are skipped during serialization.
5. **serialVersionUID** ensures version compatibility — always declare it.
6. **Custom serialization** via `writeObject`/`readObject` for special handling.
7. **Externalizable** gives full control over serialization logic.
8. **Non-serializable parent** requires manual handling or a no-arg constructor.
9. **Static fields** are not serialized (they belong to the class, not instance).
10. **Object graphs** are handled automatically — circular references are safe.

---

**Happy coding!**

*Serialization is the bridge between objects and bytes — master it for robust persistence and communication!*
