@SolenoidTest
Feature: AddUserSteps
  Test user addition

  Background:
    Given I am logged in to site
    And I go to admin page

  Scenario: Add user with form check
    When I click 'Add' button to add user
    And I enter data to all user fields
    And I click 'Save' button to save user
    And I validate all user fields
    Then user is created