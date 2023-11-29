package aqa.course.steps;

import aqa.course.constants.Constants;
import aqa.course.pages.AdminPage;
import aqa.course.pages.CreateUserPage;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;

public class AddUserSteps {

    private AdminPage adminPage = new AdminPage();
    private CreateUserPage createUserPage;
    private String uniqueUsername;

    @When("^I click 'Add' button to add user$")
    public void clickAddButton() {
        createUserPage = adminPage.clickAddButton();
    }

    @And("^I enter data to all user fields$")
    public void enterUserData() {
        String currentUserName = createUserPage.getCurrentUserName();
        uniqueUsername = Constants.TEST_USERNAME + System.currentTimeMillis();

        createUserPage.enterUserRole(Constants.USER_ROLE_ADMIN);
        createUserPage.enterEmployeeName(currentUserName);
        createUserPage.enterStatus(Constants.USER_STATUS_ENABLED);
        createUserPage.enterUsername(uniqueUsername);
        createUserPage.enterPassword(Constants.TEST_PASSWORD);
        createUserPage.confirmPassword(Constants.TEST_PASSWORD);
    }

    @And("^I click 'Save' button to save user$")
    public void clickSaveUser() {
        adminPage = createUserPage.clickSaveUser();
    }

    @And("^I validate all user fields$")
    public void validateEnteredFields() {
        createUserPage = adminPage.openEditUserPage(uniqueUsername);
        Boolean areFieldsValid = createUserPage.validateUserInfo(uniqueUsername);

        createUserPage.cancelUserEdit();

        Assertions.assertTrue(areFieldsValid);
    }

    @Then("^user is created$")
    public void checkIfUserIsCreated() {
        SelenideElement userRow = adminPage.getUserRowByUsername(uniqueUsername);

        userRow.shouldHave(Condition.ownText(uniqueUsername));
    }
}