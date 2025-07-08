Feature: Website Navigation
  As a user
  I want to be able to open different websites
  So that I can verify that navigation works

  Scenario: Open Google website
    Given I open the website "google"
    Then the website should load successfully

  Scenario: Open GitHub website
    Given I open the website "github"
    Then the website should load successfully

  Scenario: Open Stack Overflow website
    Given I open the website "stackoverflow"
    Then the website should load successfully
