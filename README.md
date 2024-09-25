# Trendyol Automation Testing Framework

## Overview

This project is an automated testing framework for Trendyol, an e-commerce website. It uses Selenium WebDriver for browser automation, Cucumber for behavior-driven development (BDD), and JUnit for test execution. The framework is built using Java and follows the Page Object Model design pattern.

## Prerequisites

- Java JDK 11 or higher
- Maven
- Microsoft Edge browser (EdgeDriver is used by default)

## Project Structure

- `src/test/java/com/ybydev/`
  - `CucumberTest.java`: Test runner class
  - `StepDefinitions.java`: Cucumber step definitions
  - `pages/`: Page Object Model classes
    - `HomePage.java`
    - `LoginPage.java`
    - `ProductPage.java`
    - `CartPage.java`
    - `SearchResultPage.java`
- `src/test/resources/`
  - `features/`: Cucumber feature files
    - `trendyol.feature`
  - `log4j2.xml`: Logging configuration

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/trendyol-automation.git
   ```
2. Navigate to the project directory:
   ```bash
   cd trendyol-automation
   ```
3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

To run the tests, use the following command:

```
mvn test
```

This will execute the Cucumber scenarios defined in the feature files.

## Configuration

- Browser settings and test data are currently hardcoded. Future improvements may include moving these to a configuration file.
- Logging is configured in `src/test/resources/log4j2.xml`.

## Features

- Login functionality
- Product search
- Adding products to cart
- Removing products from cart
- Empty cart verification

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
