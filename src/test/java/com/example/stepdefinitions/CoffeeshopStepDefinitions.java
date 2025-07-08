package com.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import com.example.utils.DriverManager;
import com.example.utils.TestDataLoader;

/**
 * Step Definitions for Coffeeshop Tests
 */
public class CoffeeshopStepDefinitions {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public CoffeeshopStepDefinitions() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @Given("the user is on the coffeeshop homepage")
    public void theUserIsOnTheCoffeeshopHomepage() {
        // Load the URL from urls.properties
        String coffeeshopUrl = TestDataLoader.getUrl("coffeeshop");
        driver.get(coffeeshopUrl);
        
        // Wait until the page is loaded - check for the actual title
        wait.until(ExpectedConditions.titleContains("coffeeshop"));
        
        System.out.println("Navigated to Coffeeshop Homepage: " + coffeeshopUrl);
        System.out.println("Current page title: " + driver.getTitle());

        String expectedUrl = TestDataLoader.getUrl("coffeeshop");
        String actualUrl = driver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl, "The current URL (" + actualUrl + ") does not match the expected URL (" + expectedUrl + ").");
    }
    
    @When("the user clicks the button Shop to enter the shop")
    public void theUserClicksTheShopButton() {
        try {
            // Load the XPath locator from properties
            String buttonXpath = TestDataLoader.getCoffeeshopData("button_enter_shop_xpath_locator");
            
            // Wait for the element and click it
            WebElement enterShopButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath))
            );

            // Click the button
            enterShopButton.click();
            System.out.println("Shop button pressed successfully.");

            
        } catch (Exception e) {
            System.err.println("Error clicking the button: " + e.getMessage());
            throw new RuntimeException("Button 'Shop' could not be clicked", e);
        }
    }
    
    @Then("the user should be taken to the shop page")
    public void theUserShouldBeTakenToTheShopPage() {
        try {

            // Wait until the URL contains the expected shop URL
            String actualUrl = driver.getCurrentUrl();
            wait.until(ExpectedConditions.urlToBe(actualUrl));

            String expectedUrl = TestDataLoader.getUrl("coffeeshop_shop");

            System.out.println("Actual page: " + actualUrl + " Matches the expected page " + expectedUrl);

            assertEquals(expectedUrl, actualUrl, "The current URL (" + actualUrl + ") does not match the expected URL (" + expectedUrl + ").");
            
        } catch (Exception e) {
            System.err.println("Error verifying the shop page: " + e.getMessage());
            throw new RuntimeException("Navigation to shop page failed", e);
        }
    }
}