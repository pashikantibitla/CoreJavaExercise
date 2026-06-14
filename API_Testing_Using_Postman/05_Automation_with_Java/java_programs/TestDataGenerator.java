import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * TestDataGenerator.java
 *
 * Utility class for generating test data dynamically.
 * Uses JavaFaker library to create realistic fake data.
 *
 * This class provides methods to generate:
 * - Personal information (names, emails, addresses)
 * - Business information (company names, job titles)
 * - Technical data (UUIDs, random strings, numbers)
 * - Date-related data (past dates, future dates)
 * - Complete POJOs for API requests
 *
 * Prerequisites:
 * - com.github.javafaker:javafaker
 */
public class TestDataGenerator {

    // Shared Faker instance for generating fake data
    private static final Faker faker = new Faker();

    // ==================== PERSONAL DATA ====================

    /**
     * Generate a random full name.
     *
     * @return a full name (e.g., "John Smith")
     */
    public static String generateRandomName() {
        return faker.name().fullName();
    }

    /**
     * Generate a random first name.
     *
     * @return a first name
     */
    public static String generateRandomFirstName() {
        return faker.name().firstName();
    }

    /**
     * Generate a random last name.
     *
     * @return a last name
     */
    public static String generateRandomLastName() {
        return faker.name().lastName();
    }

    /**
     * Generate a random email address.
     *
     * @return an email address (e.g., "john.smith@example.com")
     */
    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generate a random email address with a specific domain.
     *
     * @param domain the email domain (e.g., "test.com")
     * @return a custom domain email
     */
    public static String generateRandomEmail(String domain) {
        String username = faker.internet().slug().replace("-", "");
        return username + "@" + domain;
    }

    /**
     * Generate a random password.
     *
     * @param minLength minimum password length
     * @param maxLength maximum password length
     * @return a random password
     */
    public static String generateRandomPassword(int minLength, int maxLength) {
        return faker.internet().password(minLength, maxLength, true, true);
    }

    /**
     * Generate a random phone number.
     *
     * @return a phone number
     */
    public static String generateRandomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    /**
     * Generate a random address.
     *
     * @return a full address
     */
    public static String generateRandomAddress() {
        return faker.address().fullAddress();
    }

    /**
     * Generate a random city.
     *
     * @return a city name
     */
    public static String generateRandomCity() {
        return faker.address().city();
    }

    /**
     * Generate a random country.
     *
     * @return a country name
     */
    public static String generateRandomCountry() {
        return faker.address().country();
    }

    // ==================== BUSINESS DATA ====================

    /**
     * Generate a random job title.
     *
     * @return a job title (e.g., "Software Engineer")
     */
    public static String generateRandomJobTitle() {
        return faker.job().title();
    }

    /**
     * Generate a random company name.
     *
     * @return a company name
     */
    public static String generateRandomCompanyName() {
        return faker.company().name();
    }

    /**
     * Generate a random department name.
     *
     * @return a department name
     */
    public static String generateRandomDepartment() {
        return faker.commerce().department();
    }

    // ==================== TECHNICAL DATA ====================

    /**
     * Generate a random UUID.
     *
     * @return a UUID string
     */
    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a random string of specified length.
     *
     * @param length the desired length
     * @return a random string
     */
    public static String generateRandomString(int length) {
        return faker.lorem().characters(length);
    }

    /**
     * Generate a random alphanumeric string.
     *
     * @param length the desired length
     * @return a random alphanumeric string
     */
    public static String generateRandomAlphanumeric(int length) {
        return faker.lorem().characters(length, true, true);
    }

    /**
     * Generate a random number within a range.
     *
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return a random integer
     */
    public static int generateRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    /**
     * Generate a random double number.
     *
     * @param min minimum value
     * @param max maximum value
     * @param decimalPlaces number of decimal places
     * @return a random double
     */
    public static double generateRandomDouble(int min, int max, int decimalPlaces) {
        return faker.number().randomDouble(decimalPlaces, min, max);
    }

    // ==================== DATE DATA ====================

    /**
     * Generate a future date as a string.
     *
     * @param daysInFuture number of days in the future
     * @return date string in ISO format (yyyy-MM-dd)
     */
    public static String generateFutureDate(int daysInFuture) {
        LocalDate futureDate = LocalDate.now().plusDays(daysInFuture);
        return futureDate.toString();
    }

    /**
     * Generate a past date as a string.
     *
     * @param daysInPast number of days in the past
     * @return date string in ISO format (yyyy-MM-dd)
     */
    public static String generatePastDate(int daysInPast) {
        LocalDate pastDate = LocalDate.now().minusDays(daysInPast);
        return pastDate.toString();
    }

    /**
     * Generate a random birthdate.
     *
     * @param minAge minimum age
     * @param maxAge maximum age
     * @return date string in ISO format
     */
    public static String generateRandomBirthDate(int minAge, int maxAge) {
        LocalDate now = LocalDate.now();
        int years = faker.number().numberBetween(minAge, maxAge);
        LocalDate birthDate = now.minusYears(years);
        return birthDate.toString();
    }

    // ==================== LIST DATA ====================

    /**
     * Generate a list of random names.
     *
     * @param count the number of names
     * @return list of names
     */
    public static List<String> generateRandomNames(int count) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            names.add(generateRandomName());
        }
        return names;
    }

    /**
     * Generate a list of random emails.
     *
     * @param count the number of emails
     * @return list of emails
     */
    public static List<String> generateRandomEmails(int count) {
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            emails.add(generateRandomEmail());
        }
        return emails;
    }

    /**
     * Generate a list of random skills.
     *
     * @param count the number of skills
     * @return list of skills
     */
    public static List<String> generateRandomSkills(int count) {
        List<String> skills = new ArrayList<>();
        List<String> possibleSkills = List.of(
            "Java", "Python", "JavaScript", "C#", "Go", "Ruby",
            "Selenium", "REST Assured", "Postman", "JMeter",
            "AWS", "Azure", "Docker", "Kubernetes", "Jenkins",
            "SQL", "MongoDB", "PostgreSQL", "Redis",
            "React", "Angular", "Vue", "Spring Boot", "Node.js"
        );

        for (int i = 0; i < count; i++) {
            skills.add(possibleSkills.get(faker.number().numberBetween(0, possibleSkills.size())));
        }
        return skills;
    }

    // ==================== COMPOSITE DATA ====================

    /**
     * Generate a UserRequest object for API testing.
     * This is a simple map-based representation; in a real framework,
     * you would return a POJO.
     *
     * @return a JSON string representing a user request
     */
    public static String generateRandomUserRequest() {
        String name = generateRandomName();
        String job = generateRandomJobTitle();
        String email = generateRandomEmail();
        List<String> skills = generateRandomSkills(3);

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"name\":\"").append(name).append("\",");
        json.append("\"job\":\"").append(job).append("\",");
        json.append("\"email\":\"").append(email).append("\",");
        json.append("\"skills\":[");
        for (int i = 0; i < skills.size(); i++) {
            json.append("\"").append(skills.get(i)).append("\"");
            if (i < skills.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        json.append("}");

        return json.toString();
    }

    /**
     * Generate a unique identifier with a prefix.
     *
     * @param prefix the prefix to add
     * @return a unique identifier string
     */
    public static String generateUniqueId(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "_" + faker.number().randomNumber(4, true);
    }

    /**
     * Generate a random IP address.
     *
     * @return a random IP address
     */
    public static String generateRandomIpAddress() {
        return faker.internet().ipV4Address();
    }

    /**
     * Generate a random MAC address.
     *
     * @return a random MAC address
     */
    public static String generateRandomMacAddress() {
        return faker.internet().macAddress();
    }

    /**
     * Generate a random URL.
     *
     * @return a random URL
     */
    public static String generateRandomUrl() {
        return faker.internet().url();
    }

    /**
     * Generate a random color.
     *
     * @return a color name
     */
    public static String generateRandomColor() {
        return faker.color().name();
    }

    /**
     * Generate a random animal name.
     *
     * @return an animal name
     */
    public static String generateRandomAnimal() {
        return faker.animal().name();
    }

    /**
     * Generate a random book title.
     *
     * @return a book title
     */
    public static String generateRandomBookTitle() {
        return faker.book().title();
    }

    /**
     * Generate a random lorem text (paragraph).
     *
     * @return a paragraph of text
     */
    public static String generateRandomLoremText() {
        return faker.lorem().paragraph();
    }

    /**
     * Generate a random boolean value.
     *
     * @return true or false
     */
    public static boolean generateRandomBoolean() {
        return faker.bool().bool();
    }

    /**
     * Generate a random percentage value.
     *
     * @return a percentage between 0 and 100
     */
    public static int generateRandomPercentage() {
        return faker.number().numberBetween(0, 100);
    }
}
