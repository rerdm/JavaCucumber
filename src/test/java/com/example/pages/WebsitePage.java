package com.example.pages;

import org.openqa.selenium.WebDriver;

/**
 * Page Object für Website-Navigation
 */
public class WebsitePage extends BasePage {
    
    public WebsitePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToUrl(String url) {
        driver.get(url);
        waitForPageLoad();
    }
    
    public boolean isPageLoaded() {
        return driver.getTitle() != null && !driver.getTitle().isEmpty();
    }
    
    public boolean isUrlCorrect(String expectedUrl) {
        String currentUrl = getCurrentUrl();
        return currentUrl.contains(expectedUrl) || expectedUrl.contains(currentUrl);
    }
}
