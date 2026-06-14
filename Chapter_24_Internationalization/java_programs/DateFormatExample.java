import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatExample {
    public static void main(String[] args) {
        Date now = new Date();
        
        // Date styles
        System.out.println("=== DATE FORMATS ===");
        int[] styles = {DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL};
        String[] styleNames = {"SHORT", "MEDIUM", "LONG", "FULL"};
        
        for (int i = 0; i < styles.length; i++) {
            DateFormat df = DateFormat.getDateInstance(styles[i], Locale.US);
            System.out.println(styleNames[i] + " (US): " + df.format(now));
        }
        
        // Time styles
        System.out.println("\n=== TIME FORMATS ===");
        for (int i = 0; i < styles.length; i++) {
            DateFormat tf = DateFormat.getTimeInstance(styles[i], Locale.US);
            System.out.println(styleNames[i] + " (US): " + tf.format(now));
        }
        
        // DateTime combined
        System.out.println("\n=== DATE + TIME ===");
        DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.US);
        System.out.println("MEDIUM date + SHORT time (US): " + dtf.format(now));
        
        // Locale-specific dates
        System.out.println("\n=== LOCALE-SPECIFIC DATES ===");
        Locale[] locales = {Locale.US, Locale.UK, Locale.GERMANY, Locale.FRANCE, Locale.JAPAN};
        String[] names = {"US", "UK", "Germany", "France", "Japan"};
        
        for (int i = 0; i < locales.length; i++) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locales[i]);
            System.out.println(names[i] + ": " + df.format(now));
        }
        
        // SimpleDateFormat custom patterns
        System.out.println("\n=== CUSTOM PATTERNS ===");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEEE, MMMM dd, yyyy hh:mm a");
        
        System.out.println("dd/MM/yyyy: " + sdf1.format(now));
        System.out.println("yyyy-MM-dd HH:mm:ss: " + sdf2.format(now));
        System.out.println("EEEE, MMMM dd, yyyy hh:mm a: " + sdf3.format(now));
        
        // Parsing
        System.out.println("\n=== PARSING ===");
        try {
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = parser.parse("25/12/2023");
            System.out.println("Parsed date: " + parsed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
