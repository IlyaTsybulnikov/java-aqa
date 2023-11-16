@SolenoidTest
Feature: JDBCConnectionSteps
  Test if database requests work properly

  Background:
    Given I am connected to mssql server

  Scenario: Check database requests
    When I send insert request
    Then check if record was created
    When I send update request
    Then check if changes were saved to database
    When I send delete request
    Then check if record was deleted