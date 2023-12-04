package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;

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
    private final SelenideElement cancelButton = $x("//button[@type='button'][text()=' Cancel ']");
    private final SelenideElement spinner = $x("//div[@class='oxd-loading-spinner-container']");

    private final SiteHeader siteHeader = new SiteHeader();

    public CreateUserPage enterUserRole(String role) {
        userRoleField.click();
        userRoleOptions.$x(".//span[text()='" + role + "']").click();

        return this;
    }

    public CreateUserPage enterEmployeeName(String name) {
        employeeNameField.setValue(name);
        employeeNameRoleFirstOption.click();

        return this;
    }

    public CreateUserPage enterStatus(String status) {
        statusField.click();
        statusFieldOptions.$x(".//span[text()='" + status + "']").click();

        return this;
    }

    public CreateUserPage enterUsername(String username) {
        usernameField.setValue(username);

        return this;
    }

    public CreateUserPage enterPassword(String password) {
        passwordField.setValue(password);

        return this;
    }

    public CreateUserPage confirmPassword(String password) {
        confirmPasswordField.setValue(password);

        return this;
    }

    public AdminPage clickSaveUser() {
        saveButton.click();

        return page(AdminPage.class);
    }

    public String getCurrentUserName() {
        return this.siteHeader.getCurrentUserName();
    }

    public Boolean validateUserInfo(String uniqueUsername) {
        String userRole = userRoleField.shouldNotHave(Condition.text(Constants.PLACEHOLDER_SELECT)).getText();
        String employeeName = employeeNameField.getValue();
        String status = statusField.shouldNotHave(Condition.text(Constants.PLACEHOLDER_SELECT)).getText();
        String username = usernameField.getValue();

        if(employeeName == null || username == null) {
            return false;
        }

        boolean isEmployeeNameValid = Arrays.stream(getCurrentUserName().split(" ")).allMatch(employeeName::contains);

        return userRole.equals(Constants.USER_ROLE_ADMIN)
                && isEmployeeNameValid
                && status.equals(Constants.USER_STATUS_ENABLED)
                && username.equals(uniqueUsername);
    }

    public AdminPage cancelUserEdit() {
        cancelButton.click();

        spinner
                .shouldNot(Condition.exist)
                .shouldNotBe(Condition.visible);

        this.siteHeader.getPageName()
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.ownText("Admin"));

        return page(AdminPage.class);
    }
}