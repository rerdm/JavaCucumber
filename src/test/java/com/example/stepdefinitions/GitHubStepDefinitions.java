package com.example.stepdefinitions;

import com.example.pages.GitHubPage;
import com.example.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

/**
 * Step Definitions für GitHub Tests
 */
public class GitHubStepDefinitions {
    
    private GitHubPage gitHubPage;
    private String currentUrl;
    
    @When("I search for repositories with {string}")
    public void iSearchForRepositoriesWith(String searchTerm) {
        gitHubPage = new GitHubPage(DriverManager.getDriver());
        gitHubPage.performSearch(searchTerm);
        System.out.println("Repository-Suche durchgeführt für: " + searchTerm);
    }
    
    @And("sign up button should be visible")
    public void signUpButtonShouldBeVisible() {
        gitHubPage = new GitHubPage(DriverManager.getDriver());
        boolean isVisible = gitHubPage.isSignUpButtonVisible();
        Assertions.assertTrue(isVisible,
            "Sign Up Button sollte sichtbar sein!");
        System.out.println("Sign Up Button ist sichtbar");
    }
    
    @And("sign in link should be visible")
    public void signInLinkShouldBeVisible() {
        gitHubPage = new GitHubPage(DriverManager.getDriver());
        boolean isVisible = gitHubPage.isSignInLinkVisible();
        Assertions.assertTrue(isVisible,
            "Sign In Link sollte sichtbar sein!");
        System.out.println("Sign In Link ist sichtbar");
    }
    
    @And("I click on the first repository")
    public void iClickOnTheFirstRepository() {
        currentUrl = DriverManager.getDriver().getCurrentUrl();
        gitHubPage.clickFirstRepository();
        System.out.println("Erstes Repository geklickt");
    }
}
