package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CreateUserPage {

    private final SelenideElement userRoleField = $x("//div[div/label[text()='User Role']]" +
            "//div[@class='oxd-select-text oxd-select-text--active']");
    private final SelenideElement userRoleOptions = $x("//div[div/label[text()='User Role']]" +
            "//div[@role='listbox']");

    private final SelenideElement statusField = $x("//div[div/label[text()='Status']]" +
            "//div[@class='oxd-select-text oxd-select-text--active']/div[1]");
    private final SelenideElement statusFieldOptions = $x("//div[div/label[text()='Status']]" +
            "//div[@role='listbox']");

    private final SelenideElement employeeNameField = $x("//div[div/label[text()='Employee Name']]" +
            "//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input");
    private final SelenideElement employeeNameRoleFirstOption = $x("//div[div/label" +
            "[text()='Employee Name']]//div[@role='listbox']//span[1]");

    private final SelenideElement usernameField = $x("//div[div/label[text()='Username']]" +
            "//input[@class='oxd-input oxd-input--active']");
    private final SelenideElement passwordField = $x("//div[div/label[text()='Password']]" +
            "//input[@type='password']");
    private final SelenideElement confirmPasswordField = $x("//div[div/label[text()='Confirm Password']]" +
            "//input[@type='password']");
    private final SelenideElement saveButton = $x("//button[@type='submit'][text() = ' Save ']");
    private final SelenideElement cancelButton = $x("//button[@type='button'][text() = ' Cancel ']");

    private final SiteHeader siteHeader = new SiteHeader();

    public void enterUserRole(String role) {
        userRoleField.click();
        userRoleOptions.$x(".//span[text()='" + role + "']").click();
    }

    public void enterEmployeeName(String name) {
        employeeNameField.setValue(name);
        employeeNameRoleFirstOption.click();
    }

    public void enterStatus(String status) {
        statusField.click();
        statusFieldOptions.$x(".//span[text()='" + status + "']").click();
    }

    public void enterUsername(String username) {
        usernameField.setValue(username);
    }

    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    public void confirmPassword(String password) {
        confirmPasswordField.setValue(password);
    }

    public AdminPage clickSaveUser() {
        saveButton.click();

        return page(AdminPage.class);
    }

    public String getCurrentUserName() {
        return this.siteHeader.getCurrentUserName();
    }

    public Boolean validateUserInfo(String uniqueUsername) {
        userRoleField.should(Condition.exist).shouldBe(Condition.visible).shouldBe(Condition.interactable);
        employeeNameField.should(Condition.exist).shouldBe(Condition.visible).shouldBe(Condition.interactable);
        statusField.should(Condition.exist).shouldBe(Condition.visible).shouldBe(Condition.interactable);
        usernameField.should(Condition.exist).shouldBe(Condition.visible).shouldBe(Condition.interactable);

        String userRole = userRoleField.getText();
        String employeeName = employeeNameField.getValue();
        String status = statusField.getText();
        String username = usernameField.getValue();

        Boolean isEmployeeNameValid = Arrays.stream(getCurrentUserName().split(" ")).allMatch(employeeName::contains);

        return userRole.equals(Constants.USER_ROLE_ADMIN)
                && isEmployeeNameValid
                && status.equals(Constants.USER_STATUS_ENABLED)
                && username.equals(uniqueUsername);
    }

    public void cancelUserEdit() {
        cancelButton.click();
    }
}