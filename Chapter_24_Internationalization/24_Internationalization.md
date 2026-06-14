# Chapter 24 — Internationalization (I18N)

> **Topics:** Locale, ResourceBundle, NumberFormat, DateFormat, MessageFormat, Currency, Percentage

---

## Table of Contents

1. [Introduction to Internationalization](#1-introduction-to-internationalization)
2. [Locale Class](#2-locale-class)
3. [ResourceBundle](#3-resourcebundle)
4. [NumberFormat Class](#4-numberformat-class)
5. [DateFormat Class](#5-dateformat-class)
6. [MessageFormat Class](#6-messageformat-class)
7. [Interview FAQs](#7-interview-faqs)
8. [Key Takeaways](#8-key-takeaways)

---

## 1. Introduction to Internationalization

### What is Internationalization (I18N)?

Internationalization is the process of designing an application so that it can be adapted to various languages and regions **without engineering changes**.

```
┌──────────────────────────────────────────────────────────────┐
│                    I18N vs L10N                               │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Term               │ Meaning                              │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ I18N               │ Internationalization (18 letters)   │  │
│  │ L10N               │ Localization (10 letters)             │  │
│  │ I18N               │ Design once, adapt everywhere         │  │
│  │ L10N               │ Translation + region-specific content │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

### Why I18N is Important?

- **Global Reach:** Single application serves multiple countries.
- **Maintainability:** No code changes needed for new languages.
- **User Experience:** Users see content in their native language and format.
- **Business Value:** Supports markets worldwide with minimal effort.

### Core Components of Java I18N

```
┌──────────────────────────────────────────────────────────────┐
│              JAVA I18N CORE COMPONENTS                        │
│                                                               │
│   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐     │
│   │   Locale    │    │ ResourceBundle│   │  Format     │     │
│   │  (Who?)     │    │   (What?)     │   │  (How?)     │     │
│   └─────────────┘    └─────────────┘    └─────────────┘     │
│                                                               │
│   - Language code    - Properties files    - NumberFormat    │
│   - Country code     - Key-value pairs     - DateFormat     │
│   - Variant          - getBundle()         - MessageFormat  │
│   - Script           - BaseName + Locale   - ChoiceFormat   │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

---

## 2. Locale Class

### What is a Locale?

A `Locale` object represents a specific geographical, political, or cultural region. It is used to locale-sensitive operations like formatting dates, numbers, and currencies.

```
┌──────────────────────────────────────────────────────────────┐
│                    LOCALE STRUCTURE                            │
│                                                               │
│   Locale = language + country + variant                        │
│                                                               │
│   ┌─────────────────────────────────────────────────────────┐ │
│   │  language:  ISO 639 code (lowercase, 2-3 letters)      │ │
│   │  country:   ISO 3166 code (uppercase, 2 letters)       │ │
│   │  variant:   vendor/browser-specific (e.g., WIN, MAC)   │ │
│   └─────────────────────────────────────────────────────────┘ │
│                                                               │
│   Examples:                                                  │
│   - en         → English language                            │
│   - en_US      → English (United States)                     │
│   - en_GB      → English (United Kingdom)                    │
│   - fr_FR      → French (France)                             │
│   - de_DE      → German (Germany)                            │
│   - hi_IN      → Hindi (India)                               │
│   - ja_JP      → Japanese (Japan)                            │
│   - zh_CN      → Chinese (China)                             │
│   - es_ES      → Spanish (Spain)                             │
│   - pt_BR      → Portuguese (Brazil)                         │
│   - ru_RU      → Russian (Russia)                            │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Creating Locale Objects

```java
import java.util.Locale;

public class LocaleDemo {
    public static void main(String[] args) {
        // 1. Using constructor (deprecated in Java 19, still widely used)
        Locale locale1 = new Locale("en", "US");
        Locale locale2 = new Locale("fr", "FR");
        
        // 2. Using Locale.Builder (recommended)
        Locale locale3 = new Locale.Builder()
            .setLanguage("de")
            .setRegion("DE")
            .build();
        
        // 3. Using Locale constants
        Locale locale4 = Locale.ENGLISH;
        Locale locale5 = Locale.US;
        Locale locale6 = Locale.UK;
        Locale locale7 = Locale.FRANCE;
        Locale locale8 = Locale.JAPAN;
        Locale locale9 = Locale.CANADA;
        
        // 4. Using Locale.forLanguageTag()
        Locale locale10 = Locale.forLanguageTag("en-US");
        Locale locale11 = Locale.forLanguageTag("hi-IN");
        
        // 5. Default locale of the system
        Locale defaultLocale = Locale.getDefault();
        
        System.out.println("Locale 1: " + locale1);        // en_US
        System.out.println("Locale 2: " + locale2);        // fr_FR
        System.out.println("Locale 3: " + locale3);        // de_DE
        System.out.println("Locale 4: " + locale4);        // en
        System.out.println("Locale 5: " + locale5);        // en_US
        System.out.println("Default : " + defaultLocale);  // en_IN (or your system's)
        
        // Locale methods
        System.out.println("Language: " + locale1.getLanguage());  // en
        System.out.println("Country:  " + locale1.getCountry());   // US
        System.out.println("Display:  " + locale1.getDisplayName()); // English (United States)
        System.out.println("Display Language: " + locale1.getDisplayLanguage()); // English
        System.out.println("Display Country:  " + locale1.getDisplayCountry());  // United States
    }
}
```

### Common Language and Country Codes

```
┌──────────────────────────────────────────────────────────────┐
│              COMMON LANGUAGE CODES (ISO 639)                 │
│  ┌────────┬──────────┐    ┌────────┬──────────┐             │
│  │ Code   │ Language │    │ Code   │ Language │             │
│  ├────────┼──────────┤    ├────────┼──────────┤             │
│  │ en     │ English  │    │ hi     │ Hindi    │             │
│  │ fr     │ French   │    │ ta     │ Tamil    │             │
│  │ de     │ German   │    │ te     │ Telugu   │             │
│  │ es     │ Spanish  │    │ mr     │ Marathi  │             │
│  │ ja     │ Japanese │    │ gu     │ Gujarati │             │
│  │ zh     │ Chinese  │    │ kn     │ Kannada  │             │
│  │ ru     │ Russian  │    │ pa     │ Punjabi  │             │
│  │ ar     │ Arabic   │    │ ml     │ Malayalam│             │
│  │ pt     │ Portuguese│   │ bn     │ Bengali  │             │
│  │ ko     │ Korean   │    │ it     │ Italian  │             │
│  └────────┴──────────┘    └────────┴──────────┘             │
│                                                               │
│              COMMON COUNTRY CODES (ISO 3166)                 │
│  ┌────────┬──────────────┐  ┌────────┬──────────────┐      │
│  │ Code   │ Country      │  │ Code   │ Country      │      │
│  ├────────┼──────────────┤  ├────────┼──────────────┤      │
│  │ US     │ United States│  │ IN     │ India        │      │
│  │ GB     │ Great Britain│  │ CN     │ China        │      │
│  │ FR     │ France       │  │ JP     │ Japan        │      │
│  │ DE     │ Germany      │  │ CA     │ Canada       │      │
│  │ BR     │ Brazil       │  │ AU     │ Australia    │      │
│  │ RU     │ Russia       │  │ MX     │ Mexico       │      │
│  └────────┴──────────────┘  └────────┴──────────────┘      │
└──────────────────────────────────────────────────────────────┘
```

### Setting Default Locale

```java
public class SetDefaultLocale {
    public static void main(String[] args) {
        System.out.println("Before: " + Locale.getDefault()); // en_US
        
        Locale.setDefault(Locale.FRANCE);
        
        System.out.println("After:  " + Locale.getDefault()); // fr_FR
        
        // Reset to system default
        Locale.setDefault(Locale.Category.DISPLAY, Locale.US);
    }
}
```

---

## 3. ResourceBundle

### What is ResourceBundle?

`ResourceBundle` is a Java class used to store **locale-specific resources** (text, messages, labels) in key-value pairs. It allows applications to load the appropriate resource file based on the current `Locale`.

```
┌──────────────────────────────────────────────────────────────┐
│              RESOURCEBUNDLE ARCHITECTURE                     │
│                                                               │
│   BaseName.properties         (default/fallback)              │
│   BaseName_en.properties      (English)                      │
│   BaseName_en_US.properties   (English - US)                 │
│   BaseName_fr.properties      (French)                       │
│   BaseName_fr_FR.properties   (French - France)              │
│   BaseName_de.properties      (German)                       │
│   BaseName_hi.properties      (Hindi)                        │
│   BaseName_hi_IN.properties   (Hindi - India)                │
│                                                               │
│   Search Order (from most specific to least specific):       │
│   BaseName_lang_country → BaseName_lang → BaseName           │
│                                                               │
│   Example for Locale("fr", "FR"):                            │
│   1. Messages_fr_FR.properties                               │
│   2. Messages_fr.properties                                    │
│   3. Messages.properties   (fallback)                        │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Properties File Format

```properties
# Messages.properties (default - English)
greeting=Hello
farewell=Goodbye
welcome=Welcome to our application
message=Your balance is {0}
```

```properties
# Messages_fr.properties (French)
greeting=Bonjour
farewell=Au revoir
welcome=Bienvenue dans notre application
message=Votre solde est {0}
```

```properties
# Messages_de.properties (German)
greeting=Hallo
farewell=Auf Wiedersehen
welcome=Willkommen in unserer Anwendung
message=Ihr Guthaben beträgt {0}
```

```properties
# Messages_hi.properties (Hindi)
greeting=नमस्ते
farewell=अलविदा
welcome=हमारे एप्लिकेशन में आपका स्वागत है
message=आपका बैलेंस {0} है
```

### Loading ResourceBundle

```java
import java.util.ResourceBundle;
import java.util.Locale;

public class ResourceBundleDemo {
    public static void main(String[] args) {
        // Load default bundle
        ResourceBundle bundle = ResourceBundle.getBundle("Messages");
        System.out.println("Default: " + bundle.getString("greeting"));
        
        // Load French bundle
        Locale french = new Locale("fr", "FR");
        ResourceBundle frenchBundle = ResourceBundle.getBundle("Messages", french);
        System.out.println("French:  " + frenchBundle.getString("greeting"));
        
        // Load German bundle
        Locale german = new Locale("de", "DE");
        ResourceBundle germanBundle = ResourceBundle.getBundle("Messages", german);
        System.out.println("German:  " + germanBundle.getString("greeting"));
        
        // Load Hindi bundle
        Locale hindi = new Locale("hi", "IN");
        ResourceBundle hindiBundle = ResourceBundle.getBundle("Messages", hindi);
        System.out.println("Hindi:   " + hindiBundle.getString("greeting"));
        
        // Load with ClassLoader
        ResourceBundle bundle2 = ResourceBundle.getBundle("Messages", Locale.US, 
            ResourceBundleDemo.class.getClassLoader());
        System.out.println("US:      " + bundle2.getString("greeting"));
    }
}
```

### ResourceBundle with Class (Java Class Approach)

Instead of `.properties` files, you can use Java classes that extend `ListResourceBundle`.

```java
import java.util.ListResourceBundle;

public class Messages_fr extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            { "greeting", "Bonjour" },
            { "farewell", "Au revoir" },
            { "welcome", "Bienvenue" }
        };
    }
}
```

```java
public class ResourceBundleClassDemo {
    public static void main(String[] args) {
        Locale french = new Locale("fr", "FR");
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", french);
        System.out.println(bundle.getString("greeting")); // Bonjour
    }
}
```

### Key Points about ResourceBundle

```
┌──────────────────────────────────────────────────────────────┐
│              RESOURCEBUNDLE KEY POINTS                       │
│  ┌────────────────────┬──────────────────────────────────────┐  │
│  │ Point              │ Explanation                          │  │
│  ├────────────────────┼──────────────────────────────────────┤  │
│  │ File naming        │ BaseName_lang_country.properties     │  │
│  │ Encoding           │ ISO-8859-1 (use Unicode escapes for   │  │
│  │                    │ non-ASCII, or use UTF-8 with -encoding│  │
│  │ Fallback           │ If locale-specific not found, uses   │  │
│  │                    │ BaseName.properties as fallback      │  │
│  │ Key existence      │ MissingKeyException if key not found │  │
│  │ getObject()        │ Returns Object (supports arrays, etc)│  │
│  │ getString()        │ Returns String (most common)           │  │
│  │ getStringArray()   │ Returns String[]                     │  │
│  └────────────────────┴──────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────────┘
```

---

## 4. NumberFormat Class

### What is NumberFormat?

`NumberFormat` is an abstract class in `java.text` package used to format and parse numbers in a locale-sensitive manner.

```
┌──────────────────────────────────────────────────────────────┐
│              NUMBERFORMAT HIERARCHY                          │
│                                                               │
│   java.text.Format (abstract)                                │
│        │                                                       │
│        └── java.text.NumberFormat (abstract)                  │
│                  │                                             │
│                  ├── DecimalFormat (concrete)                 │
│                  │                                             │
│                  Factory Methods:                              │
│                  ├── getInstance() / getNumberInstance()      │
│                  ├── getCurrencyInstance()                    │
│                  ├── getPercentInstance()                     │
│                  ├── getIntegerInstance()                     │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Formatting Numbers

```java
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatDemo {
    public static void main(String[] args) {
        double number = 1234567.89;
        
        // Default locale
        NumberFormat defaultFormat = NumberFormat.getInstance();
        System.out.println("Default: " + defaultFormat.format(number)); // 1,234,567.89
        
        // US locale
        NumberFormat usFormat = NumberFormat.getInstance(Locale.US);
        System.out.println("US:      " + usFormat.format(number));      // 1,234,567.89
        
        // German locale (uses . for thousands, comma for decimal)
        NumberFormat germanFormat = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("German:  " + germanFormat.format(number));    // 1.234.567,89
        
        // French locale
        NumberFormat frenchFormat = NumberFormat.getInstance(Locale.FRANCE);
        System.out.println("French:  " + frenchFormat.format(number));    // 1 234 567,89
        
        // Indian locale
        NumberFormat indianFormat = NumberFormat.getInstance(new Locale("en", "IN"));
        System.out.println("India:   " + indianFormat.format(number));    // 12,34,567.89
        
        // Number formatting options
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumIntegerDigits(10);
        nf.setMinimumIntegerDigits(1);
        nf.setGroupingUsed(true);
        
        System.out.println("Custom:  " + nf.format(123.456)); // 123.46
        
        // Parsing
        try {
            Number parsed = nf.parse("1,234.56");
            System.out.println("Parsed:  " + parsed.doubleValue()); // 1234.56
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Formatting Currency

```java
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatDemo {
    public static void main(String[] args) {
        double amount = 1234567.89;
        
        // US Dollar
        NumberFormat usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("US:      " + usCurrency.format(amount)); // $1,234,567.89
        
        // UK Pound
        NumberFormat ukCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println("UK:      " + ukCurrency.format(amount)); // £1,234,567.89
        
        // Euro (Germany)
        NumberFormat euroCurrency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println("Euro:    " + euroCurrency.format(amount)); // 1.234.567,89 €
        
        // Japanese Yen
        NumberFormat japanCurrency = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        System.out.println("Japan:   " + japanCurrency.format(amount)); // ￥1,234,568
        
        // Indian Rupee
        NumberFormat indiaCurrency = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        System.out.println("India:   " + indiaCurrency.format(amount)); // ₹12,34,567.89
        
        // Currency without symbol (just number format)
        NumberFormat plain = NumberFormat.getInstance();
        System.out.println("Plain:   " + plain.format(amount));
        
        // Custom currency formatting with DecimalFormat
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00 'USD'");
        System.out.println("Custom:  " + df.format(amount));
    }
}
```

### Formatting Percentages

```java
import java.text.NumberFormat;
import java.util.Locale;

public class PercentFormatDemo {
    public static void main(String[] args) {
        double value = 0.7567;
        
        // Default percentage
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        System.out.println("Default: " + percentFormat.format(value)); // 76%
        
        // US percentage
        NumberFormat usPercent = NumberFormat.getPercentInstance(Locale.US);
        System.out.println("US:      " + usPercent.format(value)); // 76%
        
        // German percentage
        NumberFormat germanPercent = NumberFormat.getPercentInstance(Locale.GERMANY);
        System.out.println("German:  " + germanPercent.format(value)); // 76 %
        
        // French percentage
        NumberFormat frenchPercent = NumberFormat.getPercentInstance(Locale.FRANCE);
        System.out.println("French:  " + frenchPercent.format(value)); // 76 %
        
        // Custom precision
        NumberFormat custom = NumberFormat.getPercentInstance();
        custom.setMinimumFractionDigits(2);
        custom.setMaximumFractionDigits(2);
        System.out.println("Custom:  " + custom.format(value)); // 75.67%
        
        // Integer percentage
        NumberFormat integerPercent = NumberFormat.getPercentInstance();
        integerPercent.setMaximumFractionDigits(0);
        System.out.println("Integer: " + integerPercent.format(value)); // 76%
        
        // Parsing percentage
        try {
            Number parsed = percentFormat.parse("75.67%");
            System.out.println("Parsed:  " + parsed.doubleValue()); // 0.7567
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### DecimalFormat for Custom Patterns

```java
import java.text.DecimalFormat;

public class DecimalFormatDemo {
    public static void main(String[] args) {
        double number = 1234567.8912;
        
        // Pattern: # - digit, 0 - zero-padding, , - grouping, . - decimal
        DecimalFormat df1 = new DecimalFormat("#,###.##");
        System.out.println(df1.format(number)); // 1,234,567.89
        
        DecimalFormat df2 = new DecimalFormat("#,###.00");
        System.out.println(df2.format(number)); // 1,234,567.89
        
        DecimalFormat df3 = new DecimalFormat("0000000000.00");
        System.out.println(df3.format(123.4)); // 0000000123.40
        
        DecimalFormat df4 = new DecimalFormat("$#,##0.00");
        System.out.println(df4.format(number)); // $1,234,567.89
        
        DecimalFormat df5 = new DecimalFormat("#,##0.00 'INR'");
        System.out.println(df5.format(number)); // 1,234,567.89 INR
        
        // Scientific notation
        DecimalFormat df6 = new DecimalFormat("0.###E0");
        System.out.println(df6.format(number)); // 1.235E6
        
        // Percentage
        DecimalFormat df7 = new DecimalFormat("0.00%");
        System.out.println(df7.format(0.7567)); // 75.67%
        
        // Negative numbers
        DecimalFormat df8 = new DecimalFormat("#,##0.00;(#,##0.00)");
        System.out.println(df8.format(-1234.56)); // (1,234.56)
    }
}
```

---

## 5. DateFormat Class

### What is DateFormat?

`DateFormat` is an abstract class in `java.text` package used to format and parse dates in a locale-sensitive and style-sensitive manner.

```
┌──────────────────────────────────────────────────────────────┐
│              DATEFORMAT HIERARCHY                            │
│                                                               │
│   java.text.Format (abstract)                                │
│        │                                                       │
│        └── java.text.DateFormat (abstract)                    │
│                  │                                             │
│                  └── java.text.SimpleDateFormat (concrete)    │
│                                                               │
│   Factory Methods:                                            │
│   ├── getDateInstance()        → Date only                   │
│   ├── getTimeInstance()        → Time only                   │
│   ├── getDateTimeInstance()    → Date + Time                 │
│                                                               │
│   Styles:                                                     │
│   ├── DateFormat.SHORT        → 12/31/23                     │
│   ├── DateFormat.MEDIUM       → Dec 31, 2023               │
│   ├── DateFormat.LONG         → December 31, 2023            │
│   ├── DateFormat.FULL         → Sunday, December 31, 2023    │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### Formatting Dates

```java
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatDemo {
    public static void main(String[] args) {
        Date now = new Date();
        
        // Date only - different styles
        System.out.println("=== DATE ONLY ===");
        DateFormat shortDate = DateFormat.getDateInstance(DateFormat.SHORT);
        System.out.println("SHORT:   " + shortDate.format(now)); // 12/31/23
        
        DateFormat mediumDate = DateFormat.getDateInstance(DateFormat.MEDIUM);
        System.out.println("MEDIUM:  " + mediumDate.format(now)); // Dec 31, 2023
        
        DateFormat longDate = DateFormat.getDateInstance(DateFormat.LONG);
        System.out.println("LONG:    " + longDate.format(now)); // December 31, 2023
        
        DateFormat fullDate = DateFormat.getDateInstance(DateFormat.FULL);
        System.out.println("FULL:    " + fullDate.format(now)); // Sunday, December 31, 2023
        
        // Time only - different styles
        System.out.println("\n=== TIME ONLY ===");
        DateFormat shortTime = DateFormat.getTimeInstance(DateFormat.SHORT);
        System.out.println("SHORT:   " + shortTime.format(now)); // 5:30 PM
        
        DateFormat mediumTime = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        System.out.println("MEDIUM:  " + mediumTime.format(now)); // 5:30:45 PM
        
        DateFormat longTime = DateFormat.getTimeInstance(DateFormat.LONG);
        System.out.println("LONG:    " + longTime.format(now)); // 5:30:45 PM IST
        
        DateFormat fullTime = DateFormat.getTimeInstance(DateFormat.FULL);
        System.out.println("FULL:    " + fullTime.format(now)); // 5:30:45 PM India Standard Time
        
        // Date and Time combined
        System.out.println("\n=== DATE + TIME ===");
        DateFormat shortDT = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        System.out.println("SHORT:   " + shortDT.format(now)); // 12/31/23, 5:30 PM
        
        DateFormat mediumDT = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        System.out.println("MEDIUM:  " + mediumDT.format(now)); // Dec 31, 2023, 5:30:45 PM
        
        DateFormat fullDT = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        System.out.println("FULL:    " + fullDT.format(now)); // Sunday, December 31, 2023 at 5:30:45 PM India Standard Time
    }
}
```

### Locale-Specific Date Formatting

```java
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleDateFormatDemo {
    public static void main(String[] args) {
        Date now = new Date();
        
        // US format
        DateFormat usFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
        System.out.println("US:      " + usFormat.format(now));
        
        // UK format
        DateFormat ukFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.UK);
        System.out.println("UK:      " + ukFormat.format(now));
        
        // German format
        DateFormat germanFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
        System.out.println("German:  " + germanFormat.format(now));
        
        // French format
        DateFormat frenchFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE);
        System.out.println("French:  " + frenchFormat.format(now));
        
        // Japanese format
        DateFormat japanFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.JAPAN);
        System.out.println("Japan:   " + japanFormat.format(now));
        
        // DateTime with locale
        DateFormat dtFormat = DateFormat.getDateTimeInstance(
            DateFormat.LONG, DateFormat.SHORT, Locale.FRANCE);
        System.out.println("FR DateTime: " + dtFormat.format(now));
    }
}
```

### SimpleDateFormat for Custom Patterns

```java
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {
    public static void main(String[] args) {
        Date now = new Date();
        
        // Common patterns
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("dd/MM/yyyy:       " + sdf1.format(now)); // 31/12/2023
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("yyyy-MM-dd:       " + sdf2.format(now)); // 2023-12-31
        
        SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println("MMM dd, yyyy:     " + sdf3.format(now)); // Dec 31, 2023
        
        SimpleDateFormat sdf4 = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        System.out.println("EEEE, MMMM...:    " + sdf4.format(now)); // Sunday, December 31, 2023
        
        SimpleDateFormat sdf5 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("dd/MM/yyyy HH:mm: " + sdf5.format(now)); // 31/12/2023 17:30:45
        
        SimpleDateFormat sdf6 = new SimpleDateFormat("hh:mm:ss a");
        System.out.println("hh:mm:ss a:       " + sdf6.format(now)); // 05:30:45 PM
        
        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        System.out.println("ISO-like:         " + sdf7.format(now)); // 2023-12-31T17:30:45.123+0530
        
        // Parsing
        try {
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = parser.parse("25/12/2023");
            System.out.println("Parsed: " + parsed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### SimpleDateFormat Pattern Letters

```
┌──────────────────────────────────────────────────────────────┐
│              SIMPLEDATEFORMAT PATTERN LETTERS                  │
│  ┌────────┬────────────────────────────────────────────────┐ │
│  │ Letter │ Component            │ Example                 │ │
│  ├────────┼──────────────────────┼─────────────────────────┤ │
│  │ y      │ Year                 │ 2023, 23                │ │
│  │ M      │ Month                │ 12, Dec, December       │ │
│  │ d      │ Day of month         │ 31                      │ │
│  │ E      │ Day of week          │ Sun, Sunday             │ │
│  │ H      │ Hour (0-23)          │ 17                      │ │
│  │ h      │ Hour (1-12)          │ 05                      │ │
│  │ m      │ Minute               │ 30                      │ │
│  │ s      │ Second               │ 45                      │ │
│  │ S      │ Millisecond          │ 123                     │ │
│  │ a      │ AM/PM marker         │ PM                      │ │
│  │ z      │ Time zone name       │ IST, PST                │ │
│  │ Z      │ Time zone offset     │ +0530, -0800            │ │
│  │ X      │ Time zone ISO        │ +05, +05:30, +05:30:00  │ │
│  │ '      │ Escape for text      │ 'of' → of              │ │
│  │ ''     │ Single quote         │ 'o''clock' → o'clock   │ │
│  └────────┴──────────────────────┴─────────────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

---

## 6. MessageFormat Class

### What is MessageFormat?

`MessageFormat` provides a mechanism to create concatenated messages in a language-neutral way. It extends `Format` and allows you to insert dynamic values into a message string using placeholders `{0}`, `{1}`, `{2}`, etc.

```
┌──────────────────────────────────────────────────────────────┐
│              MESSAGEFORMAT USAGE                               │
│                                                               │
│   Pattern: "Hello {0}, you have {1} messages on {2}."      │
│   Arguments: ["Alice", 5, new Date()]                        │
│   Result: "Hello Alice, you have 5 messages on 12/31/23."    │
│                                                               │
│   Format Types:                                              │
│   - {0}           → default (string)                         │
│   - {0,number}    → NumberFormat                            │
│   - {0,date}      → DateFormat                              │
│   - {0,time}      → TimeFormat                              │
│   - {0,choice}    → ChoiceFormat                            │
│                                                               │
│   Format Styles:                                             │
│   - {0,date,short}    → 12/31/23                            │
│   - {0,date,medium}   → Dec 31, 2023                        │
│   - {0,date,long}     → December 31, 2023                   │
│   - {0,number,currency} → $1,234.56                         │
│   - {0,number,percent} → 75%                                │
│   - {0,number,integer} → 1234                               │
│                                                               │
└──────────────────────────────────────────────────────────────┘
```

### MessageFormat Examples

```java
import java.text.MessageFormat;
import java.util.Date;

public class MessageFormatDemo {
    public static void main(String[] args) {
        // Basic usage
        String pattern = "Hello {0}, welcome to {1}!";
        String result = MessageFormat.format(pattern, "Alice", "Java");
        System.out.println(result); // Hello Alice, welcome to Java!
        
        // Multiple arguments with different types
        String pattern2 = "User {0} has {1,number,integer} messages " +
                         "and balance {2,number,currency} as of {3,date,long}.";
        String result2 = MessageFormat.format(pattern2, 
            "Bob", 42, 1234.56, new Date());
        System.out.println(result2);
        // User Bob has 42 messages and balance $1,234.56 as of December 31, 2023.
        
        // Using MessageFormat object for reuse
        MessageFormat mf = new MessageFormat("Order #{0} placed on {1,date,medium} " +
                                            "for {2,number,currency}");
        Object[] args1 = {1001, new Date(), 499.99};
        System.out.println(mf.format(args1));
        
        // Reusing with different arguments
        Object[] args2 = {1002, new Date(), 1299.50};
        System.out.println(mf.format(args2));
        
        // Number formatting within message
        String numberPattern = "Progress: {0,number,percent} complete ({1,number,integer} of {2,number,integer} tasks)";
        String progress = MessageFormat.format(numberPattern, 0.75, 15, 20);
        System.out.println(progress); // Progress: 75% complete (15 of 20 tasks)
        
        // Time formatting
        String timePattern = "Meeting scheduled at {0,time,short} on {0,date,full}";
        String meeting = MessageFormat.format(timePattern, new Date());
        System.out.println(meeting);
    }
}
```

### ChoiceFormat with MessageFormat

```java
import java.text.ChoiceFormat;
import java.text.MessageFormat;

public class ChoiceFormatDemo {
    public static void main(String[] args) {
        // ChoiceFormat pattern: limit#value|limit#value|...
        // 0#no files|1#one file|1<many files
        
        String choicePattern = "There {0,choice,0#are no files|1#is one file|1<are {0,number,integer} files}.";
        
        System.out.println(MessageFormat.format(choicePattern, 0));   // There are no files.
        System.out.println(MessageFormat.format(choicePattern, 1));   // There is one file.
        System.out.println(MessageFormat.format(choicePattern, 5));   // There are 5 files.
        System.out.println(MessageFormat.format(choicePattern, 100)); // There are 100 files.
        
        // File count example with explicit ChoiceFormat
        double[] limits = {0, 1, 2};
        String[] formats = {"no files", "one file", "{0,number,integer} files"};
        ChoiceFormat cf = new ChoiceFormat(limits, formats);
        
        MessageFormat mf = new MessageFormat("You have {0}.");
        mf.setFormatByArgumentIndex(0, cf);
        
        System.out.println(mf.format(new Object[]{0}));   // You have no files.
        System.out.println(mf.format(new Object[]{1}));   // You have one file.
        System.out.println(mf.format(new Object[]{10}));  // You have 10 files.
    }
}
```

---

## 7. Interview FAQs

### Q1. What is the difference between I18N and L10N?

```
┌──────────────────────────────────────────────────────────────┐
│  I18N (Internationalization)    │  L10N (Localization)        │
├──────────────────────────────────┼───────────────────────────┤
│  Process of designing app       │  Process of adapting app  │
│  to support multiple languages    │  for a specific locale    │
│  without code changes             │  (translation, formats)   │
├──────────────────────────────────┼───────────────────────────┤
│  Happens during development       │  Happens after development│
├──────────────────────────────────┼───────────────────────────┤
│  One-time effort                  │  Repeated for each locale │
├──────────────────────────────────┼───────────────────────────┤
│  Example: Using ResourceBundle,   │  Example: Creating French │
│  Locale, Format classes           │  .properties files        │
└──────────────────────────────────────────────────────────────┘
```

### Q2. How does ResourceBundle search for locale-specific files?

**Answer:** ResourceBundle uses the following search order (most specific to least specific):

1. `BaseName_language_country_variant.properties`
2. `BaseName_language_country.properties`
3. `BaseName_language.properties`
4. `BaseName.properties` (default/fallback)

If none of the locale-specific files are found, it falls back to the default properties file.

### Q3. What is the difference between `getBundle()` and `getBundle()` with ClassLoader?

**Answer:** The two-argument `getBundle(String baseName, Locale locale)` uses the system class loader. The three-argument `getBundle(String baseName, Locale locale, ClassLoader loader)` allows you to specify a custom class loader, which is useful in modular systems, web applications, or when resources are loaded from non-standard locations.

### Q4. What is the difference between `DateFormat` and `SimpleDateFormat`?

**Answer:**
- `DateFormat` is an abstract class with factory methods (`getDateInstance`, `getTimeInstance`, `getDateTimeInstance`). It provides standard date/time formatting for different locales.
- `SimpleDateFormat` is a concrete subclass that allows custom date/time patterns using format letters (`yyyy`, `MM`, `dd`, etc.). It gives more flexibility but is not synchronized (not thread-safe).

### Q5. Is `SimpleDateFormat` thread-safe?

**Answer:** No, `SimpleDateFormat` is **NOT thread-safe**. Multiple threads should not share a single `SimpleDateFormat` instance. Solutions:
- Use `ThreadLocal<SimpleDateFormat>` to create one per thread.
- Use `java.time.format.DateTimeFormatter` (Java 8+) which is thread-safe.
- Synchronize access manually (slower).

### Q6. How does `NumberFormat.getCurrencyInstance()` differ from `NumberFormat.getInstance()`?

**Answer:**
- `getCurrencyInstance()` formats numbers with the locale's currency symbol, decimal places, and currency-specific rules.
- `getInstance()` (or `getNumberInstance()`) formats plain numbers with locale-specific grouping and decimal separators.

Example:
```java
NumberFormat.getCurrencyInstance(Locale.US).format(1234.56); // $1,234.56
NumberFormat.getInstance(Locale.US).format(1234.56);       // 1,234.56
```

### Q7. What is `MessageFormat` and why is it useful?

**Answer:** `MessageFormat` allows creating composite messages with placeholders that are filled at runtime. It supports formatting numbers, dates, and times within the message. It is useful for I18N because:
- The message pattern can be stored in a `ResourceBundle`.
- Different languages can have different word orders (e.g., "{0} has {1} messages" vs "There are {1} messages for {0}").
- The placeholders are independent of language structure.

### Q8. What is the difference between `Locale.getDefault()` and `Locale.getDefault(Locale.Category)`?

**Answer:**
- `Locale.getDefault()` returns the default locale for all locale-sensitive operations.
- `Locale.getDefault(Locale.Category.DISPLAY)` returns the default locale for UI display (menus, dialogs).
- `Locale.getDefault(Locale.Category.FORMAT)` returns the default locale for formatting (dates, numbers, currencies).

### Q9. How do you format a date in a specific time zone?

**Answer:** Use `SimpleDateFormat` and call `setTimeZone()`:
```java
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
System.out.println(sdf.format(new Date()));
```

### Q10. What is the Java 8+ alternative to `DateFormat` and `SimpleDateFormat`?

**Answer:** Java 8 introduced `java.time.format.DateTimeFormatter` as part of the new Date/Time API (`java.time`). It is **immutable and thread-safe**, unlike `SimpleDateFormat`. Example:
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
String formatted = LocalDateTime.now().format(formatter);
```

---

## 8. Key Takeaways

1. **Internationalization (I18N)** is the design process to make applications adaptable to multiple languages and regions without code changes.
2. **Locale** represents a language-country combination (`en_US`, `fr_FR`, `hi_IN`) and is the foundation of all I18N operations.
3. **ResourceBundle** loads locale-specific key-value pairs from `.properties` files or Java classes. It uses a fallback chain from most specific to least specific.
4. **NumberFormat** formats numbers, currencies, and percentages in a locale-sensitive manner. Use `getInstance()`, `getCurrencyInstance()`, `getPercentInstance()`.
5. **DecimalFormat** allows custom number patterns using `#`, `0`, `,`, `.`, `E`, `%`, etc.
6. **DateFormat** formats dates and times with predefined styles: `SHORT`, `MEDIUM`, `LONG`, `FULL`. Use `getDateInstance()`, `getTimeInstance()`, `getDateTimeInstance()`.
7. **SimpleDateFormat** supports custom date/time patterns but is **not thread-safe**. Use `ThreadLocal` or Java 8's `DateTimeFormatter` for thread safety.
8. **MessageFormat** enables composite messages with dynamic placeholders (`{0}`, `{1}`) that can be formatted as numbers, dates, or times. Ideal for I18N message strings.
9. **ChoiceFormat** handles pluralization and conditional formatting within messages.
10. **Java 8+ `java.time`** package provides modern, immutable, thread-safe alternatives to older I18N date/time classes.

---

**Happy coding! 🚀**

*Internationalize once, localize everywhere — build global-ready Java applications.*
