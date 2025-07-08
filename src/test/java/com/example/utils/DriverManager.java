
package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * The DriverManager class provides a singleton-style management of the Selenium WebDriver instance.
 * It ensures that only one WebDriver is created and reused throughout the test execution.
 * The class handles the setup and configuration of the ChromeDriver using WebDriverManager,
 * applies common ChromeOptions, and provides utility methods to get, close, or quit the driver.
 * This helps to centralize WebDriver lifecycle management and configuration for browser-based tests.
 */

public class DriverManager {
    private static WebDriver driver;
    
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    
    private static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        
        // Für Headless-Modus (optional)
        // options.addArguments("--headless");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}
