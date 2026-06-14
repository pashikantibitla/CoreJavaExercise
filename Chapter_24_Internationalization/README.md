# Chapter 24 — Internationalization (I18N)

> **Topics:** Locale, ResourceBundle, NumberFormat, DateFormat, MessageFormat, Interview FAQs

---

## Table of Contents

1. [Introduction to Internationalization](#1-introduction-to-internationalization)
2. [Locale Class](#2-locale-class)
3. [ResourceBundle](#3-resourcebundle)
4. [NumberFormat Class](#4-numberformat-class)
5. [DateFormat Class](#5-dateformat-class)
6. [MessageFormat Class](#6-messageformat-class)
7. [Interview Questions](#7-interview-questions)
8. [Key Takeaways](#8-key-takeaways)

---

## 1. Introduction to Internationalization

**File:** `24_Internationalization.md`  
**Topics:** What is I18N, why it matters, architecture

- **Internationalization (I18N):** The process of designing an application so that it can be adapted to various languages and regions without engineering changes.
- **Localization (L10N):** The process of adapting an internationalized application for a specific region or language by translating content and adding locale-specific components.
- **Why I18N?** Applications can reach global markets, support multiple languages, currencies, date formats, and cultural conventions.

```
┌──────────────────────────────────────────────────────────────┐
│              INTERNATIONALIZATION vs LOCALIZATION              │
│  ┌────────────────────┬────────────────────────────────────┐ │
│  │ I18N               │ Designing app to support multiple  │ │
│  │ (Internationalize) │ languages/regions without code change│ │
│  ├────────────────────┼────────────────────────────────────┤ │
│  │ L10N               │ Adapting I18N app for a specific   │ │
│  │ (Localization)     │ locale by translating content        │ │
│  └────────────────────┴────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. Locale Class

**File:** `24_Internationalization.md`  
**Topics:** Creating Locale, country codes, language codes, predefined locales

- `java.util.Locale` represents a specific geographical, political, or cultural region.
- A Locale consists of: **language code**, **country code**, and optionally **variant**.
- Language codes: ISO 639 (e.g., `en`, `hi`, `fr`, `de`, `ja`)
- Country codes: ISO 3166 (e.g., `US`, `IN`, `FR`, `DE`, `JP`)

```
┌──────────────────────────────────────────────────────────────┐
│                    COMMON LOCALE EXAMPLES                       │
│  ┌────────────────────┬────────────────────────────────────┐ │
│  │ Locale               │ Code                               │ │
│  ├────────────────────┼────────────────────────────────────┤ │
│  │ English (US)         │ en_US                              │ │
│  │ English (UK)         │ en_GB                              │ │
│  │ Hindi (India)        │ hi_IN                              │ │
│  │ French (France)      │ fr_FR                              │ │
│  │ German (Germany)     │ de_DE                              │ │
│  │ Japanese (Japan)     │ ja_JP                              │ │
│  │ Chinese (China)      │ zh_CN                              │ │
│  │ Spanish (Spain)      │ es_ES                              │ │
│  └────────────────────┴────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 3. ResourceBundle

**File:** `24_Internationalization.md`  
**Topics:** Properties files, `getBundle`, `ResourceBundle` hierarchy

- `java.util.ResourceBundle` is used to store locale-specific resources (messages, labels, etc.) outside the source code.
- Two types: `PropertyResourceBundle` (`.properties` files) and `ListResourceBundle` (Java classes).
- Naming convention: `BaseName_locale.properties` (e.g., `Messages_en_US.properties`, `Messages_hi_IN.properties`).
- `ResourceBundle.getBundle("BaseName", locale)` loads the appropriate bundle.

```
┌──────────────────────────────────────────────────────────────┐
│              RESOURCEBUNDLE FILE HIERARCHY                     │
│                                                                │
│  Messages.properties         ← Default fallback                │
│  Messages_en.properties      ← English default                 │
│  Messages_en_US.properties   ← English (US)                    │
│  Messages_en_GB.properties   ← English (UK)                    │
│  Messages_hi_IN.properties   ← Hindi (India)                   │
│                                                                │
│  Fallback order:                                               │
│  Messages_en_US → Messages_en → Messages                       │
└──────────────────────────────────────────────────────────────┘
```

---

## 4. NumberFormat Class

**File:** `24_Internationalization.md`  
**Topics:** Formatting numbers, currencies, percentages

- `java.text.NumberFormat` is an abstract class for formatting and parsing numbers.
- Factory methods: `getNumberInstance()`, `getCurrencyInstance()`, `getPercentInstance()`.
- Locale-specific formatting: commas vs periods for decimal separators, currency symbols, etc.

```
┌──────────────────────────────────────────────────────────────┐
│              NUMBERFORMAT FACTORY METHODS                       │
│  ┌────────────────────────┬────────────────────────────────┐ │
│  │ Method                 │ Purpose                        │ │
│  ├────────────────────────┼────────────────────────────────┤ │
│  │ getNumberInstance()    │ General number formatting      │ │
│  │ getCurrencyInstance()  │ Currency formatting            │ │
│  │ getPercentInstance()   │ Percentage formatting          │ │
│  │ getIntegerInstance()   │ Integer formatting             │ │
│  └────────────────────────┴────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 5. DateFormat Class

**File:** `24_Internationalization.md`  
**Topics:** Formatting dates, date styles, time zones

- `java.text.DateFormat` is an abstract class for formatting and parsing dates/times.
- Styles: `SHORT`, `MEDIUM`, `LONG`, `FULL`.
- Factory methods: `getDateInstance()`, `getTimeInstance()`, `getDateTimeInstance()`.
- Locale-specific formatting: day/month order, timezone names, 12-hour vs 24-hour.

```
┌──────────────────────────────────────────────────────────────┐
│              DATEFORMAT STYLES & OUTPUTS                        │
│  ┌────────────────────────┬────────────────────────────────┐ │
│  │ Style                  │ Example (US Locale)            │ │
│  ├────────────────────────┼────────────────────────────────┤ │
│  │ SHORT                  │ 6/15/25                        │ │
│  │ MEDIUM                 │ Jun 15, 2025                   │ │
│  │ LONG                   │ June 15, 2025                  │ │
│  │ FULL                   │ Sunday, June 15, 2025          │ │
│  └────────────────────────┴────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 6. MessageFormat Class

**File:** `24_Internationalization.md`  
**Topics:** Parameterized messages, choice format, pluralization

- `java.text.MessageFormat` provides a way to construct locale-dependent messages with placeholders.
- Placeholders: `{0}`, `{1}`, `{2}`, etc. can be formatted with type and style: `{0,number,currency}`.
- Supports nested formats: `ChoiceFormat`, `DateFormat`, `NumberFormat`.

---

## 📁 Additional Files

- **24_Internationalization.md** — Comprehensive theory, code snippets, and interview FAQs
- **java_programs/** — Individual `.java` files demonstrating I18N concepts

---

## 🎯 Learning Path

```
Start Here
    │
    ├── Introduction to I18N ──→ I18N vs L10N, architecture
    │
    ├── Locale Class ──→ Language codes, country codes, creating Locale
    │
    ├── ResourceBundle ──→ Properties files, getBundle, fallback hierarchy
    │
    ├── NumberFormat ──→ Numbers, currency, percentage formatting
    │
    ├── DateFormat ──→ Date, time, datetime styles, locales
    │
    └── MessageFormat ──→ Parameterized messages, placeholders, nested formats
```

---

## 📋 Key Takeaways

1. **I18N** = designing apps for multiple languages; **L10N** = adapting for a specific locale.
2. **Locale** = language + country + variant (e.g., `en_US`, `hi_IN`).
3. **ResourceBundle** externalizes locale-specific strings into `.properties` files.
4. **NumberFormat** formats numbers, currencies, and percentages according to locale.
5. **DateFormat** formats dates and times with locale-aware styles (SHORT, MEDIUM, LONG, FULL).
6. **MessageFormat** builds parameterized locale-aware messages with `{0}`, `{1}`, etc.
7. The default locale is the JVM's default; always specify a locale for predictable behavior.
8. Use `Locale.getAvailableLocales()` to see supported locales.
9. ResourceBundle fallback: `locale-specific → language-specific → default bundle`.
10. **Interview tip:** Know the difference between `DateFormat` (deprecated pattern-based) and `java.time.format.DateTimeFormatter` (Java 8+).

---

**Happy coding! 🚀**

*Internationalization is what turns a local application into a global product.*
