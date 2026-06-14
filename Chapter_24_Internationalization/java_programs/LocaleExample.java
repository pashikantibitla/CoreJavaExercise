import java.util.Locale;

public class LocaleExample {
    public static void main(String[] args) {
        // Different ways to create Locale
        
        // 1. Constructor
        Locale locale1 = new Locale("en", "US");
        System.out.println("Constructor: " + locale1);
        
        // 2. Builder
        Locale locale2 = new Locale.Builder()
            .setLanguage("hi")
            .setRegion("IN")
            .build();
        System.out.println("Builder: " + locale2);
        
        // 3. Constants
        Locale locale3 = Locale.FRANCE;
        Locale locale4 = Locale.JAPAN;
        System.out.println("Constants: " + locale3 + ", " + locale4);
        
        // 4. Language tag
        Locale locale5 = Locale.forLanguageTag("de-DE");
        System.out.println("Language tag: " + locale5);
        
        // 5. Default locale
        Locale defaultLocale = Locale.getDefault();
        System.out.println("Default: " + defaultLocale);
        
        // Locale methods
        System.out.println("\n--- Locale Methods ---");
        System.out.println("Language: " + locale1.getLanguage());
        System.out.println("Country: " + locale1.getCountry());
        System.out.println("Display Name: " + locale1.getDisplayName());
        System.out.println("Display Language: " + locale1.getDisplayLanguage());
        System.out.println("Display Country: " + locale1.getDisplayCountry());
        
        // Display in another locale
        System.out.println("French in Hindi: " + Locale.FRANCE.getDisplayName(new Locale("hi", "IN")));
        
        // Available locales
        System.out.println("\nTotal available locales: " + Locale.getAvailableLocales().length);
    }
}
