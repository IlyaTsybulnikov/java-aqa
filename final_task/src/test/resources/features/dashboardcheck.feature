@SolenoidTest
Feature: DashboardCheckSteps
  Test all dashboard elements

  Background:
    Given I am logged in to site

  Scenario: Check if all dashboard elements are visible
    When I get all dashboard page elements
    Then all dashboard page elements exist and are visible