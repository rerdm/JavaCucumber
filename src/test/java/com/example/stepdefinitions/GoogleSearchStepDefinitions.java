package com.example.stepdefinitions;

import com.example.pages.GoogleSearchPage;
import com.example.utils.DriverManager;
import com.example.utils.TestDataLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

/**
 * Step Definitions für Google Search Tests
 */
public class GoogleSearchStepDefinitions {
    
    private GoogleSearchPage googleSearchPage;
    private String currentSearchTerm;
    private String currentUrl;
    
    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        currentSearchTerm = searchTerm;
        googleSearchPage = new GoogleSearchPage(DriverManager.getDriver());
        googleSearchPage.performSearch(searchTerm);
        System.out.println("Suche durchgeführt für: " + searchTerm);
    }
    
    @When("I search for the term from test data {string}")
    public void iSearchForTheTermFromTestData(String website) {
        currentSearchTerm = TestDataLoader.getSearchTerm(website);
        googleSearchPage = new GoogleSearchPage(DriverManager.getDriver());
        googleSearchPage.performSearch(currentSearchTerm);
        System.out.println("Suche mit Testdaten durchgeführt für: " + currentSearchTerm);
    }
    
    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        Assertions.assertTrue(googleSearchPage.areSearchResultsDisplayed(),
            "Suchergebnisse sollten angezeigt werden!");
        
        int resultCount = googleSearchPage.getNumberOfResults();
        System.out.println("Anzahl der Suchergebnisse: " + resultCount);
        
        Assertions.assertTrue(resultCount > 0,
            "Es sollten Suchergebnisse vorhanden sein!");
    }
    
    @And("the results should contain {string}")
    public void theResultsShouldContain(String expectedTerm) {
        boolean found = googleSearchPage.isSearchTermInResults(expectedTerm);
        Assertions.assertTrue(found,
            "Die Suchergebnisse sollten '" + expectedTerm + "' enthalten!");
        System.out.println("Begriff '" + expectedTerm + "' in Suchergebnissen gefunden");
    }
    
    @And("the results should contain the expected result for {string}")
    public void theResultsShouldContainTheExpectedResultFor(String website) {
        String expectedResult = TestDataLoader.getExpectedResult(website);
        boolean found = googleSearchPage.isSearchTermInResults(expectedResult);
        Assertions.assertTrue(found,
            "Die Suchergebnisse sollten '" + expectedResult + "' enthalten!");
        System.out.println("Erwarteter Begriff '" + expectedResult + "' in Suchergebnissen gefunden");
    }
    
    @And("there should be at least {int} search results")
    public void thereShouldBeAtLeastSearchResults(int minResults) {
        int actualResults = googleSearchPage.getNumberOfResults();
        Assertions.assertTrue(actualResults >= minResults,
            "Es sollten mindestens " + minResults + " Suchergebnisse vorhanden sein. Gefunden: " + actualResults);
        System.out.println("Mindestanzahl von " + minResults + " Suchergebnissen erfüllt: " + actualResults);
    }
    
    @And("result statistics should be displayed")
    public void resultStatisticsShouldBeDisplayed() {
        String stats = googleSearchPage.getResultStats();
        Assertions.assertFalse(stats.isEmpty(),
            "Suchstatistiken sollten angezeigt werden!");
        System.out.println("Suchstatistiken: " + stats);
    }
    
    @And("I click on the first search result")
    public void iClickOnTheFirstSearchResult() {
        currentUrl = DriverManager.getDriver().getCurrentUrl();
        String firstResultTitle = googleSearchPage.getFirstResultTitle();
        System.out.println("Klicke auf erstes Suchergebnis: " + firstResultTitle);
        googleSearchPage.clickFirstResult();
    }
    
    @Then("I should be navigated to a new page")
    public void iShouldBeNavigatedToANewPage() {
        String newUrl = DriverManager.getDriver().getCurrentUrl();
        Assertions.assertNotEquals(currentUrl, newUrl,
            "Die URL sollte sich nach dem Klick geändert haben!");
        System.out.println("Navigation erfolgreich von: " + currentUrl + " zu: " + newUrl);
    }
}
