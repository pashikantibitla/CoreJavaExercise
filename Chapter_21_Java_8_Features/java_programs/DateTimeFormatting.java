import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatting {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 6, 14);
        LocalTime time = LocalTime.of(14, 30, 0);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // Custom pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        System.out.println("Formatted date: " + formattedDate);

        // Parse
        LocalDate parsed = LocalDate.parse("15/01/2024", formatter);
        System.out.println("Parsed date: " + parsed);

        // ISO format
        System.out.println("ISO date: " + date.format(DateTimeFormatter.ISO_DATE));
        System.out.println("ISO dateTime: " + dateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        // Localized format
        DateTimeFormatter localized = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("Full format: " + date.format(localized));

        // Time formatting
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Formatted time: " + time.format(timeFormatter));
    }
}
