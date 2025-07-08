package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

/**
 * Page Object für GitHub
 */
public class GitHubPage extends BasePage {
    
    private WebDriverWait wait;
    
    // GitHub Locatoren
    @FindBy(css = "[data-target='qbsearch-input.inputButton']")
    private WebElement searchBox;
    
    @FindBy(css = "[data-testid='search-input']")
    private WebElement searchInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;
    
    @FindBy(css = "[data-testid='results-list'] > div")
    private List<WebElement> searchResults;
    
    @FindBy(css = "h3 a")
    private List<WebElement> repositoryLinks;
    
    @FindBy(css = ".header-search-input")
    private WebElement headerSearchInput;
    
    @FindBy(css = ".btn-primary")
    private WebElement signUpButton;
    
    @FindBy(linkText = "Sign in")
    
    private WebElement signInLink;
    
    public GitHubPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void clickSearchBox() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            searchBox.click();
        } catch (Exception e) {
            // Fallback
            if (headerSearchInput.isDisplayed()) {
                headerSearchInput.click();
            }
        }
        System.out.println("Search Box geklickt");
    }
    
    public void enterSearchTerm(String searchTerm) {
        clickSearchBox();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchInput));
            searchInput.clear();
            searchInput.sendKeys(searchTerm);
        } catch (Exception e) {
            headerSearchInput.clear();
            headerSearchInput.sendKeys(searchTerm);
        }
        System.out.println("Suchbegriff eingegeben: " + searchTerm);
    }
    
    public void performSearch(String searchTerm) {
        enterSearchTerm(searchTerm);
        searchInput.submit(); // Enter drücken
        waitForSearchResults();
    }
    
    public void waitForSearchResults() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("[data-testid='results-list']")));
        } catch (Exception e) {
            System.out.println("Search results locator nicht gefunden, warte generell...");
            waitForPageLoad();
        }
    }
    
    public boolean areSearchResultsDisplayed() {
        try {
            return searchResults.size() > 0 || repositoryLinks.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getNumberOfResults() {
        try {
            return Math.max(searchResults.size(), repositoryLinks.size());
        } catch (Exception e) {
            return 0;
        }
    }
    
    public boolean isSignUpButtonVisible() {
        try {
            return signUpButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSignInLinkVisible() {
        try {
            return signInLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickFirstRepository() {
        if (repositoryLinks.size() > 0) {
            repositoryLinks.get(0).click();
            waitForPageLoad();
        }
    }
}
