import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportManager.java
 *
 * Extent Reports setup and configuration utility.
 * Provides a singleton ExtentReports instance for generating
 * rich HTML test reports with dashboard, charts, and detailed logs.
 *
 * Usage:
 * ExtentReports reports = ExtentReportManager.getInstance();
 * ExtentTest test = reports.createTest("Test Name");
 * test.pass("Test passed");
 * ExtentReportManager.flushReports();
 *
 * Prerequisites:
 * - com.aventstack:extentreports
 */
public class ExtentReportManager {

    // Singleton instance of ExtentReports
    private static ExtentReports extentReports;

    // Default report output path
    private static final String REPORT_PATH = "reports/extent-report.html";

    // Report metadata
    private static final String REPORT_TITLE = "API Test Automation Report";
    private static final String DOCUMENT_TITLE = "REST API Test Execution";

    /**
     * Get the singleton ExtentReports instance.
     * Creates the instance if it does not exist.
     *
     * @return the ExtentReports instance
     */
    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            createInstance();
        }
        return extentReports;
    }

    /**
     * Create and configure the ExtentReports instance.
     */
    private static void createInstance() {
        // Create the Spark HTML reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);

        // Configure report appearance
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle(DOCUMENT_TITLE);
        sparkReporter.config().setReportName(REPORT_TITLE);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

        // Optional: Add custom CSS for styling
        sparkReporter.config().setCss(
            ".badge { font-size: 100%; } " +
            ".test-name { font-weight: bold; } " +
            ".test-status { font-size: 90%; }"
        );

        // Initialize ExtentReports and attach the reporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Add system/environment information to the report
        addSystemInfo();

        // Set analysis strategy (by test or by method)
        extentReports.setAnalysisStrategy(com.aventstack.extentreports.reporter.configuration.ViewName.TEST);

        System.out.println("ExtentReportManager: ExtentReports initialized. Report will be saved to: " + REPORT_PATH);
    }

    /**
     * Add system information to the report dashboard.
     */
    private static void addSystemInfo() {
        extentReports.setSystemInfo("Environment", getEnvironment());
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
        extentReports.setSystemInfo("Base URI", getBaseUri());
        extentReports.setSystemInfo("Time Zone", java.util.TimeZone.getDefault().getID());
    }

    /**
     * Create a new test entry in the report.
     *
     * @param testName the name of the test
     * @return the ExtentTest instance
     */
    public static ExtentTest createTest(String testName) {
        return getInstance().createTest(testName);
    }

    /**
     * Create a new test entry with description.
     *
     * @param testName the name of the test
     * @param description the test description
     * @return the ExtentTest instance
     */
    public static ExtentTest createTest(String testName, String description) {
        return getInstance().createTest(testName, description);
    }

    /**
     * Create a new test node (child) under an existing test.
     *
     * @param parentTest the parent ExtentTest
     * @param nodeName the name of the child node
     * @return the child ExtentTest instance
     */
    public static ExtentTest createNode(ExtentTest parentTest, String nodeName) {
        return parentTest.createNode(nodeName);
    }

    /**
     * Log a passed step to the report.
     *
     * @param test the ExtentTest instance
     * @param message the log message
     */
    public static void logPass(ExtentTest test, String message) {
        test.log(Status.PASS, message);
    }

    /**
     * Log a failed step to the report.
     *
     * @param test the ExtentTest instance
     * @param message the log message
     */
    public static void logFail(ExtentTest test, String message) {
        test.log(Status.FAIL, message);
    }

    /**
     * Log a failed step with exception details to the report.
     *
     * @param test the ExtentTest instance
     * @param message the log message
     * @param throwable the exception/throwable
     */
    public static void logFail(ExtentTest test, String message, Throwable throwable) {
        test.log(Status.FAIL, message);
        test.fail(throwable);
    }

    /**
     * Log an informational message to the report.
     *
     * @param test the ExtentTest instance
     * @param message the log message
     */
    public static void logInfo(ExtentTest test, String message) {
        test.log(Status.INFO, message);
    }

    /**
     * Log a warning message to the report.
     *
     * @param test the ExtentTest instance
     * @param message the log message
     */
    public static void logWarning(ExtentTest test, String message) {
        test.log(Status.WARNING, message);
    }

    /**
     * Attach a screenshot or file to the report.
     * Note: This is a placeholder for screenshot attachment.
     * For API tests, you typically attach request/response logs.
     *
     * @param test the ExtentTest instance
     * @param description the attachment description
     * @param data the data to attach
     */
    public static void attachRequestResponse(ExtentTest test, String description, String data) {
        test.info("<pre>" + description + ":\n" + data + "</pre>");
    }

    /**
     * Flush all reports to disk.
     * Call this at the end of the test suite.
     */
    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
            System.out.println("ExtentReportManager: Reports flushed to " + REPORT_PATH);
        }
    }

    /**
     * Get the environment from system properties or default to "QA".
     *
     * @return the environment name
     */
    private static String getEnvironment() {
        String env = System.getProperty("env");
        if (env == null || env.isEmpty()) {
            env = System.getenv("ENVIRONMENT");
        }
        return env != null ? env : "QA";
    }

    /**
     * Get the base URI from system properties or default.
     *
     * @return the base URI
     */
    private static String getBaseUri() {
        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            baseUri = System.getenv("BASE_URI");
        }
        return baseUri != null ? baseUri : "https://reqres.in";
    }

    /**
     * Reset the ExtentReports instance.
     * Useful for testing or re-initializing the reporter.
     */
    public static void reset() {
        if (extentReports != null) {
            extentReports.flush();
        }
        extentReports = null;
    }

    /**
     * Main method for quick demonstration.
     * Generates a sample report with test entries.
     */
    public static void main(String[] args) {
        ExtentReports reports = getInstance();

        ExtentTest test1 = reports.createTest("Test: Create User");
        test1.log(Status.INFO, "Sending POST request to /api/users");
        test1.log(Status.INFO, "Request body: {\"name\":\"John\",\"job\":\"Developer\"}");
        test1.log(Status.PASS, "User created successfully with ID: 123");

        ExtentTest test2 = reports.createTest("Test: Get User");
        test2.log(Status.INFO, "Sending GET request to /api/users/2");
        test2.log(Status.PASS, "User retrieved successfully");

        ExtentTest test3 = reports.createTest("Test: Update User");
        test3.log(Status.INFO, "Sending PUT request to /api/users/2");
        test3.log(Status.FAIL, "Update failed: 500 Internal Server Error");
        test3.log(Status.INFO, "Response: {\"error\":\"Internal Server Error\"}");

        ExtentTest test4 = reports.createTest("Test: Delete User");
        test4.log(Status.INFO, "Sending DELETE request to /api/users/2");
        test4.log(Status.PASS, "User deleted successfully");

        // Flush and generate the report
        flushReports();

        System.out.println("Sample report generated at: " + REPORT_PATH);
    }
}
