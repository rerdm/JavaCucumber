Feature: Extended Coffeeshop functionality
  As a customer
  I want to interact with the coffeeshop website
  So that I can browse and purchase coffee products

  Scenario: User enters shop and sees products
    Given the user is on the coffeeshop homepage
    When the user clicks the "Enter Shop" button
    Then the user should be taken to the shop page
    And the shop page should display products
    And the shopping cart should be visible

  Scenario: Verify shop page elements
    Given the user is on the coffeeshop homepage
    When the user clicks the "Enter Shop" button
    Then the user should be taken to the shop page
    And the shop title should be "Coffee Cart"

  Background:
    Given the user is on the coffeeshop homepage
