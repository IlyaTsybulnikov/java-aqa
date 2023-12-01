@SolenoidTest
Feature: PIMCheckSteps
  Test all pim page elements

  Background:
    Given I am logged in to site
    And I go to pim page

  Scenario: Check if all pim page elements are visible
    When I get all pim page elements
    Then all pim page elements exist and are visible