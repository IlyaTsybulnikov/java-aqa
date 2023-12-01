@SolenoidTest
Feature: AssignLeaveSteps
  Test leave assignment

  Background:
    Given I am logged in to site
    And I go to "Leave" page

  Scenario: Assign leave and check form
    When I click 'Assign Leave' button to in header
    And I enter data to all leave fields
    And I click 'Assign' button to assign leave
    And I click 'Ok' on the confirmation window
    And I go to My Leave list
    Then leave assign is created
    When I view leave details
    Then leave details are correct