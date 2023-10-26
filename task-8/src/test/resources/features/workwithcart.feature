@SolenoidTest
Feature: WorkWithCartSteps
  Test if products correctly can be successfully added and removed from cart

  Background:
    Given I am logged in to site

  Scenario: Check cart addition and removal
    When I add three products to cart
    And I click cart icon
    Then check if cart contains selected products
    When I click "remove" button on the third cart item
    Then check if cart no longer contains third item
