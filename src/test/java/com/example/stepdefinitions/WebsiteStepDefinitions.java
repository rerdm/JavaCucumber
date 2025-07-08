package com.example.stepdefinitions;

import com.example.pages.WebsitePage;
import com.example.utils.DriverManager;
import com.example.utils.TestDataLoader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

/**
 * Step Definitions für Website-Navigation Tests
 */
public class WebsiteStepDefinitions {
    
    private WebsitePage websitePage;
    private String currentWebsiteName;
    
    @Given("I open the website {string}")
    public void iOpenTheWebsite(String websiteName) {
        currentWebsiteName = websiteName;
        String url = TestDataLoader.getUrl(websiteName);
        
        websitePage = new WebsitePage(DriverManager.getDriver());
        websitePage.navigateToUrl(url);
        
        System.out.println("Website '" + websiteName + "' geöffnet: " + url);
    }
    
    @Then("the website should load successfully")
    public void theWebsiteShouldLoadSuccessfully() {
        Assertions.assertTrue(websitePage.isPageLoaded(), 
            "Die Website '" + currentWebsiteName + "' wurde nicht erfolgreich geladen!");
        
        String pageTitle = websitePage.getPageTitle();
        System.out.println("Seiten-Titel: " + pageTitle);
        
        Assertions.assertFalse(pageTitle.isEmpty(), 
            "Der Seiten-Titel sollte nicht leer sein!");
    }
}
