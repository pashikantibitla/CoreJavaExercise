import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader.java
 *
 * Configuration management utility for reading properties files.
 * Supports loading from classpath, default values, and
 * environment variable overrides.
 *
 * Usage:
 * String baseUri = ConfigReader.getProperty("base.uri");
 * String timeout = ConfigReader.getProperty("timeout.ms", "5000");
 */
public class ConfigReader {

    // Default properties file name on the classpath
    private static final String DEFAULT_CONFIG_FILE = "config.properties";

    // Properties storage
    private static Properties properties;

    static {
        // Load properties when class is initialized
        loadProperties(DEFAULT_CONFIG_FILE);
    }

    /**
     * Load properties from a file on the classpath.
     *
     * @param fileName the properties file name
     */
    public static void loadProperties(String fileName) {
        properties = new Properties();

        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (inputStream != null) {
                properties.load(inputStream);
                System.out.println("ConfigReader: Loaded properties from " + fileName);
            } else {
                System.out.println("ConfigReader: Warning - Could not find " + fileName + " on classpath");
            }
        } catch (IOException e) {
            System.err.println("ConfigReader: Error loading properties file: " + e.getMessage());
        }

        // Override with environment variables if present
        overrideWithEnvironmentVariables();
    }

    /**
     * Override properties with environment variables.
     * Environment variables take precedence over file properties.
     */
    private static void overrideWithEnvironmentVariables() {
        for (String envVarName : System.getenv().keySet()) {
            String envVarValue = System.getenv(envVarName);

            // Convert environment variable name to property key format
            // Example: BASE_URI -> base.uri
            String propertyKey = envVarName.toLowerCase().replace("_", ".");

            if (properties.containsKey(propertyKey)) {
                properties.setProperty(propertyKey, envVarValue);
                System.out.println("ConfigReader: Overrode property '" + propertyKey + "' with environment variable");
            }
        }
    }

    /**
     * Get a property value by key.
     *
     * @param key the property key
     * @return the property value, or null if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get a property value by key with a default fallback.
     *
     * @param key the property key
     * @param defaultValue the default value if key not found
     * @return the property value, or defaultValue if not found
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get an integer property value.
     *
     * @param key the property key
     * @return the integer value, or -1 if not found or invalid
     */
    public static int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            return -1;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("ConfigReader: Invalid integer value for key '" + key + "': " + value);
            return -1;
        }
    }

    /**
     * Get an integer property value with a default fallback.
     *
     * @param key the property key
     * @param defaultValue the default value
     * @return the integer value, or defaultValue if not found or invalid
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get a boolean property value.
     *
     * @param key the property key
     * @return true if the value is "true" (case-insensitive), false otherwise
     */
    public static boolean getBooleanProperty(String key) {
        String value = properties.getProperty(key);
        return value != null && value.equalsIgnoreCase("true");
    }

    /**
     * Get a boolean property value with a default fallback.
     *
     * @param key the property key
     * @param defaultValue the default value
     * @return the boolean value, or defaultValue if not found
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return value.equalsIgnoreCase("true");
    }

    /**
     * Check if a property exists.
     *
     * @param key the property key
     * @return true if the property exists
     */
    public static boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    /**
     * Set a property value at runtime.
     * Useful for dynamic configuration in tests.
     *
     * @param key the property key
     * @param value the property value
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        System.out.println("ConfigReader: Set property '" + key + "' = '" + value + "'");
    }

    /**
     * Reload properties from the default file.
     * Useful for resetting configuration during tests.
     */
    public static void reload() {
        loadProperties(DEFAULT_CONFIG_FILE);
    }

    /**
     * Reload properties from a specific file.
     *
     * @param fileName the properties file name
     */
    public static void reload(String fileName) {
        loadProperties(fileName);
    }

    /**
     * Print all loaded properties for debugging.
     */
    public static void printAllProperties() {
        System.out.println("=== Loaded Properties ===");
        for (String key : properties.stringPropertyNames()) {
            System.out.println(key + " = " + properties.getProperty(key));
        }
        System.out.println("========================");
    }
}
