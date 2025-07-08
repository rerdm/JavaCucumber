Feature: Open Coffeeshop Website

    As a user
    I want to open the coffeeshop website
    So that I can enter the shop via a button

    Scenario: User enters the shop via the enter button
        Given the user is on the coffeeshop homepage
        When the user clicks the "Enter Shop" button
        Then the user should be taken to the shop page