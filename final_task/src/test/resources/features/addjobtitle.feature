@SolenoidTest
Feature: AddJobTitleSteps
  Test job title addition

  Background:
    Given I am logged in to site
    And I go to admin page

  Scenario Outline: Add and then delete 3 job titles
    When I click 'Job' dropdown and 'Job Titles' option
    And I click 'Add' button to add job title
    And I enter <title>, <description> and <note> into specified fields
    And I click 'Save' button to save job title
    Then job record with title "<title>" is created
    When I click delete button next to record with "<title>" title
    Then job record with title "<title>" is deleted

    Examples:
    | title | description | note |
    | testTitle1 | some description for the first job title | first note |
    | testTitle2 | some description for the second job title | second note |
    | testTitle3 | some description for the third job title | third note |