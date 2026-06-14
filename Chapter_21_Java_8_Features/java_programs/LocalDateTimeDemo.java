import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;
import java.time.Instant;

public class LocalDateTimeDemo {
    public static void main(String[] args) {
        // LocalDate
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);

        LocalDate specific = LocalDate.of(2024, 1, 15);
        System.out.println("Specific: " + specific);

        LocalDate tomorrow = today.plusDays(1);
        System.out.println("Tomorrow: " + tomorrow);

        LocalDate lastMonth = today.minusMonths(1);
        System.out.println("Last month: " + lastMonth);

        // LocalTime
        LocalTime now = LocalTime.now();
        System.out.println("Now: " + now);

        LocalTime specificTime = LocalTime.of(14, 30, 0);
        System.out.println("Specific time: " + specificTime);

        // LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("DateTime: " + dateTime);

        LocalDateTime specificDateTime = LocalDateTime.of(2024, 12, 25, 10, 0);
        System.out.println("Specific DateTime: " + specificDateTime);

        // Period
        Period period = Period.between(today, tomorrow);
        System.out.println("Period: " + period);

        Period p = Period.of(1, 2, 3);
        System.out.println("Custom period: " + p);

        // Duration
        Duration duration = Duration.between(specificTime, now);
        System.out.println("Duration: " + duration);

        // Instant
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);
    }
}
