Feature: LoginSteps
    As a user
    I want to login to a site

    Scenario Outline: Check login for different users
    Given that login page is opened
    When I enter username as <username>
    And I enter password as <password>
    And I click the "Login" button
    Then check if home page is open

    Examples:
    |username|password|
    |standard_user|secret_sauce|
    |problem_user|secret_sauce|
    |error_user|secret_sauce|