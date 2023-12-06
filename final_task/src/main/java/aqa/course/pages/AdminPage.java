package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AdminPage {

    // 'Admin' page elements
    private final SelenideElement addButton = $x("//button[text()=' Add ']");
    private final SelenideElement topbarMenu = $x("//nav[@class='oxd-topbar-body-nav']");
    private final SelenideElement jobTitlesOption = $x("//li[a[text()='Job Titles']]");
    private final SelenideElement usernameFilter = $x("(//input" +
            "[@class='oxd-input oxd-input--active'])[2]");
    private final SelenideElement searchButton = $x("//button[@type='submit'][text()=' Search ']");

    // 'Create User' form elements
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

    // 'Job Title' page elements
    private final SelenideElement jobTitleInputField = $x("(//input)[2]");
    private final SelenideElement jobDescriptionTextArea = $x("//textarea");
    private final SelenideElement noteTextArea = $x("(//textarea)[2]");
    private final SelenideElement addJobTitleButton = $x("//button[@type='button'][text()=' Add ']");
    private final SelenideElement confirmDeleteButton = $x("//button[text()=' Yes, Delete ']");

    @Step("Click add user")
    public AdminPage clickAddButton() {
        addButton.click();

        return this;
    }

    @Step("Filter users by '{0}' username")
    public AdminPage filterUserList(String username) {
        page(SiteHeader.class).getPageName().shouldHave(Condition.ownText("User Management"));

        usernameFilter.shouldBe(Condition.editable).setValue(username);

        searchButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("CLick edit button on {0} user table row")
    public AdminPage openEditNewUserPage(String username) {
        $x("//div[div/div[text()='" + username + "']]" +
                "//button[i[@class='oxd-icon bi-pencil-fill']]").click();

        return this;
    }

    @Step("Go to job title list")
    public AdminPage goToJobTitleList() {
        topbarMenu.$x(".//span[@class='oxd-topbar-body-nav-tab-item'][text()='Job ']").click();
        jobTitlesOption.click();

        return this;
    }

    @Step("Enter user's role as {0}")
    public AdminPage enterUserRole(String role) {
        userRoleField.click();
        userRoleOptions.$x(".//span[text()='" + role + "']").click();

        return this;
    }

    @Step("Enter user's employee name as current username")
    public String enterEmployeeNameAsCurrentUsername() {
        employeeNameField.setValue(page(SiteHeader.class).getCurrentUserName());
        employeeNameRoleFirstOption.click();

        return employeeNameField.getValue();
    }

    @Step("Enter user's status as {0}")
    public AdminPage enterStatus(String status) {
        statusField.click();
        statusFieldOptions.$x(".//span[text()='" + status + "']").click();

        return this;
    }

    @Step("Enter user's username as {0}")
    public AdminPage enterUsername(String username) {
        usernameField.setValue(username);

        return this;
    }

    @Step("Enter user's password as {0}")
    public AdminPage enterPassword(String password) {
        passwordField.setValue(password);

        return this;
    }

    @Step("Confirm user's password as {0}")
    public AdminPage confirmPassword(String password) {
        confirmPasswordField.setValue(password);

        return this;
    }

    @Step("Click save user button")
    public AdminPage clickSaveUser() {
        saveButton.click();

        return this;
    }

    @Step("Validate '{0}' user's info")
    public void validateUserInfo(String uniqueUsername, String newEmployeeName) {
        userRoleField.shouldHave(Condition.exactText(Constants.USER_ROLE_ADMIN));

        employeeNameField.shouldHave(Condition.exactValue(newEmployeeName));

        statusField.shouldHave(Condition.exactText(Constants.USER_STATUS_ENABLED));

        usernameField.shouldHave(Condition.exactValue(uniqueUsername));
    }

    @Step("Enter job title data")
    public AdminPage enterRecordData(String jobTitle, String jobDescription, String jobNote) {
        jobTitleInputField.setValue(jobTitle);
        jobDescriptionTextArea.setValue(jobDescription);
        noteTextArea.setValue(jobNote);

        return this;
    }

    @Step("Click save job title")
    public AdminPage clickSaveButton() {
        saveButton.click();

        return this;
    }

    @Step("Click add job title button")
    public AdminPage clickAddJobTitleButton() {
        addJobTitleButton.click();

        return this;
    }

    @Step("Verify '{0}' job title exist")
    public AdminPage verifyJobTitleExist(String jobTitle) {
        $x("//div[text()='" + jobTitle + "']").shouldHave(Condition.text(jobTitle));

        return this;
    }

    @Step("Verify '{0}' job title do not exist")
    public void verifyJobTitleDoNotExist(String jobTitle) {
        $x("//div[text()='" + jobTitle + "']").shouldNot(Condition.exist);
    }

    @Step("Delete '{0}' job title")
    public AdminPage deleteJobTitleByTitle(String jobTitle) {
        $x("//div[div/div[text()='" + jobTitle + "']]" +
                "//button[i[@class='oxd-icon bi-trash']]").click();

        confirmDeleteButton.click();

        return this;
    }
}