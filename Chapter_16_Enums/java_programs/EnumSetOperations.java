import java.util.EnumSet;

public class EnumSetOperations {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        EnumSet<Day> all = EnumSet.allOf(Day.class);
        EnumSet<Day> none = EnumSet.noneOf(Day.class);
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        EnumSet<Day> workdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);

        System.out.println("All: " + all);
        System.out.println("None: " + none);
        System.out.println("Weekend: " + weekend);
        System.out.println("Workdays: " + workdays);

        EnumSet<Day> complement = EnumSet.complementOf(workdays);
        System.out.println("Complement of workdays: " + complement);

        // Set operations
        EnumSet<Day> union = EnumSet.copyOf(workdays);
        union.addAll(weekend);
        System.out.println("Union: " + union);

        EnumSet<Day> intersection = EnumSet.copyOf(workdays);
        intersection.retainAll(weekend);
        System.out.println("Intersection: " + intersection);
    }
}
