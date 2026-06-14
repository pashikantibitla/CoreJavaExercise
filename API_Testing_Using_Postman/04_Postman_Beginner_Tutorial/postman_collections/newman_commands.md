# Newman Commands Reference

# This file contains common Newman CLI commands for running Postman collections.
# Copy these commands as needed for your CI/CD pipelines and automation.

# ─────────────────────────────────────────────────────────────────────────────
# 1. BASIC RUN
# ─────────────────────────────────────────────────────────────────────────────

# Run a collection from a local file
newman run MyCollection.postman_collection.json

# Run a collection from URL
newman run https://www.getpostman.com/collections/your-collection-id

# ─────────────────────────────────────────────────────────────────────────────
# 2. WITH ENVIRONMENT
# ─────────────────────────────────────────────────────────────────────────────

# Run with environment variables
newman run MyCollection.json -e Development.postman_environment.json

# Run with globals
newman run MyCollection.json -g globals.json

# ─────────────────────────────────────────────────────────────────────────────
# 3. WITH DATA FILES (DATA-DRIVEN TESTING)
# ─────────────────────────────────────────────────────────────────────────────

# Run with CSV data file
newman run MyCollection.json -d data.csv

# Run with JSON data file
newman run MyCollection.json -d data.json

# Run with specific number of iterations
newman run MyCollection.json -d data.csv -n 10

# ─────────────────────────────────────────────────────────────────────────────
# 4. DELAYS AND TIMEOUTS
# ─────────────────────────────────────────────────────────────────────────────

# Add delay between requests (milliseconds)
newman run MyCollection.json --delay-request 500

# Set request timeout (milliseconds)
newman run MyCollection.json --timeout-request 30000

# Set global timeout (entire run)
newman run MyCollection.json --timeout 120000

# ─────────────────────────────────────────────────────────────────────────────
# 5. REPORTERS
# ─────────────────────────────────────────────────────────────────────────────

# Run with CLI reporter (default)
newman run MyCollection.json -r cli

# Run with JSON reporter
newman run MyCollection.json -r json --reporter-json-export report.json

# Run with HTML reporter (requires newman-reporter-html)
newman run MyCollection.json -r html --reporter-html-export report.html

# Run with HTML Extra reporter (recommended, requires newman-reporter-htmlextra)
newman run MyCollection.json -r htmlextra --reporter-htmlextra-export report.html

# Run with multiple reporters
newman run MyCollection.json -r cli,json,htmlextra

# Run with JUnit reporter (for Jenkins integration)
newman run MyCollection.json -r junit --reporter-junit-export junit-report.xml

# Run with all major reporters
newman run MyCollection.json -r cli,json,htmlextra,junit \
  --reporter-json-export report.json \
  --reporter-htmlextra-export report.html \
  --reporter-junit-export junit-report.xml

# ─────────────────────────────────────────────────────────────────────────────
# 6. ERROR HANDLING
# ─────────────────────────────────────────────────────────────────────────────

# Stop on first error (bail)
newman run MyCollection.json --bail

# Stop on first test failure
newman run MyCollection.json --bail failure

# Continue on error (default)
newman run MyCollection.json --bail false

# Suppress exit code (always return 0)
newman run MyCollection.json --suppress-exit-code

# ─────────────────────────────────────────────────────────────────────────────
# 7. VERBOSE OUTPUT
# ─────────────────────────────────────────────────────────────────────────────

# Verbose mode (show detailed request/response info)
newman run MyCollection.json --verbose

# Disable colored output
newman run MyCollection.json --no-color

# ─────────────────────────────────────────────────────────────────────────────
# 8. FULL EXAMPLE WITH ALL OPTIONS
# ─────────────────────────────────────────────────────────────────────────────

newman run "E-Commerce API Tests.postman_collection.json" \
  -e "Development.postman_environment.json" \
  -g "globals.json" \
  -d "test_data.csv" \
  -n 5 \
  --delay-request 1000 \
  --timeout-request 30000 \
  --bail false \
  -r cli,htmlextra,json,junit \
  --reporter-htmlextra-export "newman-report.html" \
  --reporter-json-export "newman-report.json" \
  --reporter-junit-export "newman-junit.xml" \
  --verbose

# ─────────────────────────────────────────────────────────────────────────────
# 9. RUNNING FROM URL WITH ENVIRONMENT
# ─────────────────────────────────────────────────────────────────────────────

newman run "https://api.getpostman.com/collections/{collection_uid}?apikey={your_api_key}" \
  -e "https://api.getpostman.com/environments/{env_uid}?apikey={your_api_key}"

# ─────────────────────────────────────────────────────────────────────────────
# 10. NODE.JS SCRIPT EXECUTION
# ─────────────────────────────────────────────────────────────────────────────

# Save as run-newman.js and execute: node run-newman.js

# const newman = require('newman');
# 
# newman.run({
#     collection: require('./MyCollection.postman_collection.json'),
#     environment: require('./Development.postman_environment.json'),
#     reporters: ['cli', 'htmlextra', 'json'],
#     reporter: {
#         htmlextra: { export: './reports/report.html' },
#         json: { export: './reports/report.json' }
#     },
#     delayRequest: 500,
#     timeoutRequest: 30000,
#     bail: false
# }, function (err, summary) {
#     if (err) { console.error('Error:', err); }
#     console.log('Requests:', summary.run.stats.requests.total);
#     console.log('Failures:', summary.run.failures.length);
#     if (summary.run.failures.length > 0) { process.exit(1); }
# });

# ─────────────────────────────────────────────────────────────────────────────
# 11. INSTALLATION COMMANDS
# ─────────────────────────────────────────────────────────────────────────────

# Install Newman globally
npm install -g newman

# Install HTML reporter
npm install -g newman-reporter-html

# Install HTML Extra reporter (recommended)
npm install -g newman-reporter-htmlextra

# Install JUnit reporter
npm install -g newman-reporter-junit

# Verify installation
newman --version

# ─────────────────────────────────────────────────────────────────────────────
# 12. WINDOWS BATCH EXAMPLE
# ─────────────────────────────────────────────────────────────────────────────

# For Jenkins on Windows:
# npm install -g newman
# npm install -g newman-reporter-htmlextra
# 
# newman run collections\E-Commerce_API_Tests.postman_collection.json ^
#   -e environments\Development.postman_environment.json ^
#   -r cli,htmlextra,json ^
#   --reporter-htmlextra-export "newman-report.html" ^
#   --reporter-json-export "newman-report.json" ^
#   --verbose

# ─────────────────────────────────────────────────────────────────────────────
# 13. LINUX/MAC SHELL EXAMPLE
# ─────────────────────────────────────────────────────────────────────────────

# For Jenkins on Linux/Mac:
# npm install -g newman
# npm install -g newman-reporter-htmlextra
#
# newman run collections/E-Commerce_API_Tests.postman_collection.json \
#   -e environments/Development.postman_environment.json \
#   -r cli,htmlextra,json \
#   --reporter-htmlextra-export "newman-report.html" \
#   --reporter-json-export "newman-report.json" \
#   --verbose

# ─────────────────────────────────────────────────────────────────────────────
# End of Newman Commands Reference
# ─────────────────────────────────────────────────────────────────────────────
