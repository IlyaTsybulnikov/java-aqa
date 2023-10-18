Feature: SortProductsSteps

  Background:
    Given I am logged in to site

  Scenario: Sort by name
    When I select "az" sort option
    And I take first product
    And I select "za" sort option
    Then check if first product became last

  Scenario: Sort by price
    When I select "hilo" sort option
    And I take first product
    And I select "lohi" sort option
    Then check if first product became last