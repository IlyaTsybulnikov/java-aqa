@SolenoidTest
Feature: AddCandidateSteps
  Test candidate addition

  Background:
    Given I am logged in to site
    And I go to recruitment page

  Scenario: Create candidate
    When I click 'Add' button to add candidate
    And I enter data to all candidate fields
    And I click 'Save' button to save candidate
    And I go back to candidates list
    Then candidate is created