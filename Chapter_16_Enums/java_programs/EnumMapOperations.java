import java.util.EnumMap;

public class EnumMapOperations {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);

        schedule.put(Day.MONDAY, "Team Meeting");
        schedule.put(Day.TUESDAY, "Code Review");
        schedule.put(Day.WEDNESDAY, "Development");
        schedule.put(Day.THURSDAY, "Testing");
        schedule.put(Day.FRIDAY, "Deployment");

        System.out.println("Schedule: " + schedule);
        System.out.println("Monday task: " + schedule.get(Day.MONDAY));

        System.out.println("\nIterating over keys:");
        for (Day day : schedule.keySet()) {
            System.out.println(day + " -> " + schedule.get(day));
        }

        System.out.println("Contains Saturday? " + schedule.containsKey(Day.SATURDAY));
        System.out.println("Contains Tuesday? " + schedule.containsKey(Day.TUESDAY));
    }
}
