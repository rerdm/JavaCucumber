Feature: GitHub website interaction
  As a developer
  I want to interact with GitHub
  So that I can find repositories and navigate the site

  Scenario: Verify GitHub homepage loads
    Given I open the website "github"
    Then the website should load successfully
    And sign up button should be visible
    And sign in link should be visible