package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CreateUserPage {

    private final SelenideElement userRoleField = $x("//div[@class='oxd-select-text-input']");
    private final SelenideElement userRoleOptions = $x("//div[@role='listbox']");

    private final SelenideElement statusField = $x("(//div[@class='oxd-select-text-input'])[2]");
    private final SelenideElement statusFieldOptions = $x("//div[@role='listbox']");

    private final SelenideElement employeeNameField = $x("(//input)[2]");
    private final SelenideElement employeeNameRoleFirstOption = $x("//div[@role='listbox']//span");

    private final SelenideElement usernameField = $x("(//input)[3]");
    private final SelenideElement passwordField = $x("//input[@type='password']");
    private final SelenideElement confirmPasswordField = $x("(//input[@type='password'])[2]");
    private final SelenideElement saveButton = $x("//button[@type='submit'][text()=' Save ']");

    private final SiteHeader siteHeader = new SiteHeader();

    @Step("Enter user's role as {0}")
    public CreateUserPage enterUserRole(String role) {
        userRoleField.click();
        userRoleOptions.$x(".//span[text()='" + role + "']").click();

        return this;
    }

    @Step("Enter user's employee name as current username")
    public String enterEmployeeNameAsCurrentUsername() {
        employeeNameField.setValue(siteHeader.getCurrentUserName());
        employeeNameRoleFirstOption.click();

        return employeeNameField.getValue();
    }

    @Step("Enter user's status as {0}")
    public CreateUserPage enterStatus(String status) {
        statusField.click();
        statusFieldOptions.$x(".//span[text()='" + status + "']").click();

        return this;
    }

    @Step("Enter user's username as {0}")
    public CreateUserPage enterUsername(String username) {
        usernameField.setValue(username);

        return this;
    }

    @Step("Enter user's password as {0}")
    public CreateUserPage enterPassword(String password) {
        passwordField.setValue(password);

        return this;
    }

    @Step("Confirm user's password as {0}")
    public CreateUserPage confirmPassword(String password) {
        confirmPasswordField.setValue(password);

        return this;
    }

    @Step("Click save user button")
    public AdminPage clickSaveUser() {
        saveButton.click();

        return page(AdminPage.class);
    }

    @Step("Validate '{0}' user's info")
    public void validateUserInfo(String uniqueUsername, String newEmployeeName) {
        userRoleField.shouldHave(Condition.exactText(Constants.USER_ROLE_ADMIN));

        employeeNameField.shouldHave(Condition.exactValue(newEmployeeName));

        statusField.shouldHave(Condition.exactText(Constants.USER_STATUS_ENABLED));

        usernameField.shouldHave(Condition.exactValue(uniqueUsername));
    }
}