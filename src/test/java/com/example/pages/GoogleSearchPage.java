package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

/**
 * Page Object für Google Search
 */
public class GoogleSearchPage extends BasePage {
    
    private WebDriverWait wait;
    
    // Locatoren für Google Search
    @FindBy(name = "q")
    private WebElement searchBox;
    
    @FindBy(name = "btnK")
    private WebElement searchButton;
    
    @FindBy(xpath = "//input[@value='Google Search']")
    private WebElement googleSearchBtn;
    
    @FindBy(xpath = "//input[@value=\"I'm Feeling Lucky\"]")
    private WebElement luckyButton;
    
    @FindBy(css = "h3")
    private List<WebElement> searchResults;
    
    @FindBy(id = "result-stats")
    private WebElement resultStats;
    
    @FindBy(xpath = "//div[@id='search']//h3/parent::a")
    private List<WebElement> resultLinks;
    
    @FindBy(css = "[data-ved]")
    private List<WebElement> searchResultItems;
    
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void enterSearchTerm(String searchTerm) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        System.out.println("Suchbegriff eingegeben: " + searchTerm);
    }
    
    public void clickSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchButton.click();
        } catch (Exception e) {
            // Fallback: versuche Google Search Button
            googleSearchBtn.click();
        }
        System.out.println("Search Button geklickt");
    }
    
    public void performSearch(String searchTerm) {
        enterSearchTerm(searchTerm);
        clickSearchButton();
        waitForSearchResults();
    }
    
    public void waitForSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.css("h3")));
        waitForPageLoad();
    }
    
    public boolean areSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.css("h3")));
            return searchResults.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getNumberOfResults() {
        return searchResults.size();
    }
    
    public String getFirstResultTitle() {
        if (searchResults.size() > 0) {
            return searchResults.get(0).getText();
        }
        return "";
    }
    
    public boolean isSearchTermInResults(String expectedTerm) {
        for (WebElement result : searchResults) {
            if (result.getText().toLowerCase().contains(expectedTerm.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> getAllResultTitles() {
        return searchResults.stream()
                .map(WebElement::getText)
                .toList();
    }
    
    public String getResultStats() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.id("result-stats")));
            return resultStats.getText();
        } catch (Exception e) {
            return "Keine Statistiken gefunden";
        }
    }
    
    public void clickFirstResult() {
        if (resultLinks.size() > 0) {
            resultLinks.get(0).click();
            waitForPageLoad();
        }
    }
}
