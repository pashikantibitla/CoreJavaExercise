import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Locale;

public class I18NInterviewQuestions {
    
    // Q1: Demonstrate I18N message formatting
    public static void demonstrateMessageFormat() {
        String pattern = "Hello {0}, you have {1,number,integer} unread messages as of {2,date,medium}.";
        String result = MessageFormat.format(pattern, "Alice", 42, new Date());
        System.out.println("MessageFormat: " + result);
    }
    
    // Q2: Demonstrate I18N with currency and locale
    public static void demonstrateCurrencyI18N() {
        java.text.NumberFormat us = java.text.NumberFormat.getCurrencyInstance(Locale.US);
        java.text.NumberFormat india = java.text.NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        java.text.NumberFormat japan = java.text.NumberFormat.getCurrencyInstance(Locale.JAPAN);
        
        double amount = 1234567.89;
        System.out.println("US: " + us.format(amount));
        System.out.println("India: " + india.format(amount));
        System.out.println("Japan: " + japan.format(amount));
    }
    
    // Q3: Demonstrate fallback in ResourceBundle
    public static void demonstrateResourceBundleFallback() {
        // Create a non-existent locale to force fallback
        Locale fantasyLocale = new Locale("xx", "YY");
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Messages", fantasyLocale);
            System.out.println("Fallback greeting: " + bundle.getString("greeting"));
        } catch (Exception e) {
            System.out.println("No fallback available: " + e.getMessage());
        }
    }
    
    // Q4: Demonstrate custom DecimalFormat pattern
    public static void demonstrateCustomDecimalFormat() {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00 'INR'");
        System.out.println("Custom DecimalFormat: " + df.format(9876543.21));
    }
    
    // Q5: Demonstrate date formatting across multiple locales
    public static void demonstrateDateLocales() {
        Date now = new Date();
        Locale[] locales = {Locale.US, Locale.UK, Locale.GERMANY, Locale.FRANCE, Locale.JAPAN, new Locale("hi", "IN")};
        String[] names = {"US", "UK", "Germany", "France", "Japan", "India"};
        
        for (int i = 0; i < locales.length; i++) {
            java.text.DateFormat df = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, locales[i]);
            System.out.println(names[i] + " full date: " + df.format(now));
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== I18N Interview-Level Demonstrations ===\n");
        
        demonstrateMessageFormat();
        System.out.println();
        
        demonstrateCurrencyI18N();
        System.out.println();
        
        demonstrateResourceBundleFallback();
        System.out.println();
        
        demonstrateCustomDecimalFormat();
        System.out.println();
        
        demonstrateDateLocales();
    }
}
