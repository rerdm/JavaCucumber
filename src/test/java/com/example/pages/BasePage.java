/**
 * BasePage.java
 *
 * This abstract class serves as the foundational base for all Page Object classes
 * in the test automation framework. It provides common functionality and utilities
 * such as initializing web elements, retrieving the page title and current URL,
 * and a simple method to wait for the page to load.
 *
 * Purpose:
 * - To encapsulate shared behaviors and properties for all page objects.
 * - To promote code reuse and maintainability across different page classes.
 *
 * Usage:
 * - All specific page classes (e.g., LoginPage, HomePage) should extend this BasePage.
 * - Used throughout the test suite wherever a page object is instantiated.
 */
package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Basis-Page-Klasse für alle Page Objects
 */
public abstract class BasePage {
    protected WebDriver driver;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public void waitForPageLoad() {
        // Einfaches Warten auf Seitenladevorgang
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
