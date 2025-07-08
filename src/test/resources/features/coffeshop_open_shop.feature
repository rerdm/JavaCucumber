Feature: Open Coffeeshop Website

    As a user
    I want to open the coffeeshop website
    So that I can enter the shop via a button

    Scenario: User enters the via chrome webbrowser
        Given the user is on the coffeeshop homepage
        When the user clicks the button Shop to enter the shop
        Then the user should be taken to the shop page