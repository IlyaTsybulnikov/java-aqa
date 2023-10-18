Feature: Logging to site and checking products

  Background:
    Given I am logged in to site

  Scenario: Check the number of products
    When I get all products on the home page
    Then check if number of products equals six