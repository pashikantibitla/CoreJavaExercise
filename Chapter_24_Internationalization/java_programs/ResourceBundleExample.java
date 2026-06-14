import java.util.ResourceBundle;
import java.util.Locale;

public class ResourceBundleExample {
    public static void main(String[] args) {
        // Demonstrates ResourceBundle with different locales
        // Note: Requires Messages.properties, Messages_fr.properties, Messages_de.properties, Messages_hi.properties
        // in the classpath
        
        Locale[] locales = {
            Locale.getDefault(),
            new Locale("fr", "FR"),
            new Locale("de", "DE"),
            new Locale("hi", "IN")
        };
        
        String[] localeNames = {"Default", "French", "German", "Hindi"};
        
        for (int i = 0; i < locales.length; i++) {
            System.out.println("\n=== " + localeNames[i] + " (" + locales[i] + ") ===");
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("Messages", locales[i]);
                System.out.println("Greeting: " + bundle.getString("greeting"));
                System.out.println("Farewell: " + bundle.getString("farewell"));
                System.out.println("Welcome: " + bundle.getString("welcome"));
            } catch (Exception e) {
                System.out.println("ResourceBundle not found: " + e.getMessage());
            }
        }
        
        // Fallback demonstration: if Messages_fr_FR.properties doesn't exist,
        // it will fall back to Messages_fr.properties, then Messages.properties
        System.out.println("\n=== Fallback Demonstration ===");
        Locale swissFrench = new Locale("fr", "CH");
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Messages", swissFrench);
            System.out.println("Swiss French greeting (fallback): " + bundle.getString("greeting"));
        } catch (Exception e) {
            System.out.println("Fallback failed: " + e.getMessage());
        }
    }
}
