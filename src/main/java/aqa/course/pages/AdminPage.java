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
    private final SelenideElement localizationOption = $x("//a[text()='Localization']");
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

    //'Localization' page elements
    private final SelenideElement dateFormatField = $x("(//div[@class='oxd-select-text-input'])[2]");
    private final SelenideElement dateFormatOption = $x("//div[@role='listbox']//span");
    private final SelenideElement saveLocalizationButton = $x("//button[@type='submit']");

    private final String editUserButtonLocator = "//div[div/div[text()='%s']]" +
            "//button[i[@class='oxd-icon bi-pencil-fill']]";
    private final String jobTopBarButtonLocator = ".//span[@class='oxd-topbar-body-nav-tab-item'][text()='Job ']";
    private final String optionLocator = ".//span[text()='%s']";
    private final String jobTitleColumnValueLocator = "//div[text()='%s']";
    private final String jobTitleDeleteButtonLocator = "//div[div/div[text()='%s']]//button[i[@class='oxd-icon bi-trash']]";

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

    @Step("Click edit button on {0} user table row")
    public AdminPage openEditNewUserPage(String username) {
        $x(String.format(editUserButtonLocator, username)).click();

        return this;
    }

    @Step("Go to job title list")
    public AdminPage goToJobTitleList() {
        topbarMenu.$x(jobTopBarButtonLocator).click();
        jobTitlesOption.click();

        return this;
    }

    @Step("Enter user's role as {0}")
    public AdminPage enterUserRole(String role) {
        userRoleField.click();
        userRoleOptions.$x(String.format(optionLocator, role)).click();

        return this;
    }

    @Step("Enter user's employee name as {0}")
    public String enterEmployeeName(String username) {
        employeeNameField.setValue(username);
        employeeNameRoleFirstOption.click();

        return employeeNameField.getValue();
    }

    @Step("Enter user's status as {0}")
    public AdminPage enterStatus(String status) {
        statusField.click();
        statusFieldOptions.$x(String.format(optionLocator, status)).click();

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

    @Step("Check if user info is valid")
    public Boolean checkIfUserInfoIsValid(String uniqueUsername, String newEmployeeName) {
        userRoleField.shouldHave(Condition.exactText(Constants.USER_ROLE_ADMIN));

        employeeNameField.shouldHave(Condition.exactValue(newEmployeeName));

        statusField.shouldHave(Condition.exactText(Constants.USER_STATUS_ENABLED));

        usernameField.shouldHave(Condition.exactValue(uniqueUsername));

        return userRoleField.getText().equals(Constants.USER_ROLE_ADMIN)
                && employeeNameField.getValue().equals(newEmployeeName)
                && statusField.getText().equals(Constants.USER_STATUS_ENABLED)
                && usernameField.getValue().equals(uniqueUsername);
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

    @Step("Check if job title exist")
    public Boolean checkIfJobTitleExist(String jobTitle) {
        return $x(String.format(jobTitleColumnValueLocator, jobTitle)).exists();
    }

    @Step("Delete '{0}' job title")
    public AdminPage deleteJobTitleByTitle(String jobTitle) {
        $x(String.format(jobTitleDeleteButtonLocator, jobTitle)).click();

        confirmDeleteButton.click();

        return this;
    }

    @Step("Go to localization settings")
    public AdminPage goToLocalization() {
        topbarMenu.$x(String.format(optionLocator, "Configuration ")).click();
        localizationOption.click();

        return this;
    }

    @Step("Set default date format")
    public void setDefaultDateFormat() {
        if (!dateFormatField
                .shouldNotHave(Condition.exactText("-- Select --"))
                .getText()
                .startsWith(Constants.DEFAULT_DATE_FORMAT)
        ) {
            dateFormatField.click();
            dateFormatOption.click();

            saveLocalizationButton.click();
        }
    }
}