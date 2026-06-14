import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExample {
    public static void main(String[] args) {
        double number = 1234567.891;
        double percentage = 0.8575;
        
        Locale[] locales = {
            Locale.US,
            Locale.UK,
            Locale.GERMANY,
            Locale.FRANCE,
            new Locale("en", "IN"),
            Locale.JAPAN
        };
        
        String[] names = {"US", "UK", "Germany", "France", "India", "Japan"};
        
        System.out.println("=== NUMBER FORMATTING ===");
        for (int i = 0; i < locales.length; i++) {
            NumberFormat nf = NumberFormat.getInstance(locales[i]);
            System.out.println(names[i] + ": " + nf.format(number));
        }
        
        System.out.println("\n=== CURRENCY FORMATTING ===");
        for (int i = 0; i < locales.length; i++) {
            NumberFormat cf = NumberFormat.getCurrencyInstance(locales[i]);
            System.out.println(names[i] + ": " + cf.format(number));
        }
        
        System.out.println("\n=== PERCENTAGE FORMATTING ===");
        for (int i = 0; i < locales.length; i++) {
            NumberFormat pf = NumberFormat.getPercentInstance(locales[i]);
            System.out.println(names[i] + ": " + pf.format(percentage));
        }
        
        // Custom number formatting
        System.out.println("\n=== CUSTOM FORMAT ===");
        NumberFormat custom = NumberFormat.getInstance();
        custom.setMaximumFractionDigits(1);
        custom.setMinimumFractionDigits(1);
        System.out.println("Custom: " + custom.format(number));
        
        // Integer formatting
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        System.out.println("Integer: " + integerFormat.format(number));
        
        // Parsing
        System.out.println("\n=== PARSING ===");
        try {
            NumberFormat usParser = NumberFormat.getInstance(Locale.US);
            Number parsed = usParser.parse("1,234,567.89");
            System.out.println("Parsed: " + parsed.doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
