public class BasicEnum {
    enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public static void main(String[] args) {
        Day today = Day.MONDAY;
        System.out.println("Today: " + today);
        System.out.println("Name: " + today.name());
        System.out.println("Ordinal: " + today.ordinal());

        System.out.println("\nAll days:");
        for (Day d : Day.values()) {
            System.out.println(d.name() + " (ordinal " + d.ordinal() + ")");
        }

        Day parsed = Day.valueOf("FRIDAY");
        System.out.println("\nParsed from string: " + parsed);
    }
}
