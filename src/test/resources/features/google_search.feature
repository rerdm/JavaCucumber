Feature: Google Search functionality
  As a user
  I want to search open the coffeeshop
  So that I he can enter the Shop  find relevant information

  Scenario: Perform a basic Google search
    Given I open the website "google"
    When I search for "Java Programming"
    Then search results should be displayed
    And the results should contain "Programming"

