# JavaCucumber Test Framework

A Cucumber-based test framework for Java with Selenium WebDriver and Page Object Model.

## Prerequisites

### Java Installation
1. **Install Java JDK 11 or higher**:
   - Download Java from: https://adoptopenjdk.net/ or https://www.oracle.com/java/technologies/downloads/
   - Install the JDK
   - Set the `JAVA_HOME` environment variable to the installation path
   - Add `%JAVA_HOME%\bin` to your `PATH` environment variable

2. **Verify Java installation**:
   ```powershell
   java -version
   javac -version
   ```

### Maven Installation
1. **Install Apache Maven**:
   - Download Maven from: https://maven.apache.org/download.cgi
   - Extract the archive to a folder (e.g., `C:\apache-maven`)
   - Add `C:\apache-maven\bin` to your `PATH` environment variable

2. **Verify Maven installation**:
   ```powershell
   mvn -version
   ```

### Chrome Browser
- **Install Google Chrome** (latest version recommended)
- ChromeDriver will be automatically downloaded by WebDriverManager

## Project Structure

```
JavaCucumber/
├── src/test/
│   ├── java/com/example/
│   │   ├── pages/          # Page Object Model classes
│   │   ├── stepdefinitions/ # Cucumber Step Definitions
│   │   ├── runner/         # Test Runner
│   │   └── utils/          # Utility classes
│   └── resources/
│       ├── features/       # Cucumber Feature files
│       └── testdata/       # Test data (URLs, etc.)
├── pom.xml                 # Maven configuration
└── README.md
```

## Framework Architecture & Class Interactions

This section shows how the different classes interact with each other in the test execution flow:

### 🔄 Test Execution Flow

```
┌─────────────────┐
│   TestRunner    │ ◄── Maven Test Command
│  (JUnit Suite)  │
└─────────────────┘
         │
         ▼
┌─────────────────┐
│ Feature Files   │ ◄── Gherkin scenarios
│ (.feature)      │     (Given, When, Then)
└─────────────────┘
         │
         ▼
┌─────────────────┐     ┌─────────────────┐
│ Step            │────▶│     Hooks       │
│ Definitions     │     │ (Before/After)  │
│                 │     └─────────────────┘
└─────────────────┘              │
         │                       ▼
         ▼                ┌─────────────────┐
┌─────────────────┐       │ DriverManager   │
│  Page Objects   │◄──────│                 │
│                 │       │ • getDriver()   │
│ • BasePage      │       │ • quitDriver()  │
│ • WebsitePage   │       └─────────────────┘
│ • GooglePage    │                │
│ • GitHubPage    │                ▼
│ • CoffeeshopPage│         ┌─────────────────┐
└─────────────────┘         │   WebDriver     │
         │                  │   (Selenium)    │
         ▼                  └─────────────────┘
┌─────────────────┐                │
│ TestDataLoader  │                ▼
│                 │         ┌─────────────────┐
│ • getUrl()      │         │    Browser      │
│ • getSearchData()│        │   (Chrome)      │
│ • getCoffeeData()│        └─────────────────┘
└─────────────────┘
         ▲
         │
┌─────────────────┐
│ Properties      │
│ Files           │
│ • urls.properties
│ • search_data.properties
│ • coffeeshop_data.properties
└─────────────────┘
```

### 🎯 Class Responsibility Overview

| **Class Type** | **Classes** | **Responsibilities** | **Called By** | **Calls** |
|----------------|-------------|---------------------|---------------|-----------|
| **🏃 Runner** | `TestRunner` | • Execute test suites<br>• Configure Cucumber | Maven | Cucumber Engine |
| **📋 Features** | `*.feature` | • Define test scenarios<br>• Business readable tests | Cucumber Engine | Step Definitions |
| **⚡ Step Definitions** | `WebsiteStepDefinitions`<br>`GoogleSearchStepDefinitions`<br>`GitHubStepDefinitions`<br>`CoffeeshopStepDefinitions` | • Implement test steps<br>• Bridge Gherkin ↔ Code<br>• Test assertions | Cucumber Engine | Page Objects<br>TestDataLoader<br>DriverManager |
| **🔧 Hooks** | `Hooks` | • Setup/Teardown<br>• Browser lifecycle | Cucumber Engine | DriverManager |
| **📄 Page Objects** | `BasePage`<br>`WebsitePage`<br>`GoogleSearchPage`<br>`GitHubPage`<br>`CoffeeshopPage` | • Encapsulate web elements<br>• Page-specific actions<br>• Element interactions | Step Definitions | WebDriver<br>WebDriverWait |
| **🛠️ Utilities** | `DriverManager`<br>`TestDataLoader`<br>`TestDataGenerator` | • WebDriver management<br>• Test data access<br>• Dynamic data generation | Step Definitions<br>Page Objects<br>Hooks | WebDriver<br>Properties Files |
| **📊 Data** | Properties Files | • Store test data<br>• Configuration values | TestDataLoader | None |

### 🔗 Detailed Interaction Flow

#### 1. **Test Initialization**
```
Maven → TestRunner → Cucumber Engine → Hooks.setUp() → DriverManager.getDriver()
```

#### 2. **Step Execution**
```
Feature File → Step Definition → Page Object → WebDriver → Browser
                    ↓
              TestDataLoader ← Properties Files
```

#### 3. **Test Cleanup**
```
Hooks.tearDown() → DriverManager.quitDriver() → WebDriver.quit()
```

### 📝 Key Design Patterns

- **🏗️ Page Object Model**: Encapsulates web page elements and actions
- **🏭 Factory Pattern**: DriverManager creates and manages WebDriver instances  
- **📂 Data Access Object**: TestDataLoader abstracts test data access
- **🔌 Dependency Injection**: Cucumber injects dependencies into Step Definitions
- **🎣 Hook Pattern**: Setup and teardown through Cucumber hooks

### 🌟 Benefits of This Architecture

- **🔍 Separation of Concerns**: Each class has a single responsibility
- **🔧 Maintainability**: Changes in UI only affect Page Objects
- **♻️ Reusability**: Page Objects and utilities can be reused across tests
- **📖 Readability**: Feature files are business-readable
- **🧪 Testability**: Easy to unit test individual components
- **📈 Scalability**: Easy to add new pages and test scenarios

## Installation

1. **Clone or download the project**

2. **Install dependencies**:
   ```powershell
   cd JavaCucumber
   mvn clean install
   ```

## Running Tests

### Run all tests
```powershell
mvn test
```

### Run specific feature file
```powershell
mvn test -Dcucumber.filter.tags="@website"
```

### Tests with different browsers
The framework is configured for Chrome by default. To use other browsers, you need to modify the `DriverManager` class accordingly.

## Managing Test Data

### Adding URLs
Edit the file `src/test/resources/testdata/urls.properties`:
```properties
google=https://www.google.com
github=https://github.com
stackoverflow=https://stackoverflow.com
new_website=https://example.com
```

### Creating new tests
1. Add new scenarios to `src/test/resources/features/website_navigation.feature`
2. Implement corresponding Step Definitions in `src/test/java/com/example/stepdefinitions/`

## Minimal Test

The minimal test opens various websites and verifies they load successfully:

```gherkin
Scenario: Open Google website
  Given I open the website "google"
  Then the website should load successfully
```

## Features

- ✅ **Page Object Model**: Clean separation of test code and page elements
- ✅ **Test Data Management**: External configuration of URLs and test data
- ✅ **Automatic WebDriver Management**: No manual ChromeDriver installation required
- ✅ **Cucumber BDD**: Tests in natural language (German)
- ✅ **Maven Integration**: Easy dependency management
- ✅ **JUnit 5**: Modern test infrastructure

## Troubleshooting

### Common Issues

1. **Java not found**:
   - Check your `JAVA_HOME` and `PATH` environment variables

2. **Maven not found**:
   - Check Maven installation and `PATH` variable

3. **Chrome not found**:
   - Install Google Chrome
   - WebDriverManager automatically downloads the appropriate ChromeDriver

4. **Tests fail**:
   - Check internet connection
   - Ensure URLs in `urls.properties` are accessible

## Extension

The framework can be easily extended:
- New Page Objects for more complex websites
- Additional Step Definitions for various actions
- Integration of additional browsers
- Reporting features with Cucumber Reports
