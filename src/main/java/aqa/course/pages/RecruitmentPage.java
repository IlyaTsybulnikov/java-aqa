package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class RecruitmentPage {

    // 'Create Candidate' form elements
    private final SelenideElement firstNameField = $x("//input[@name='firstName']");
    private final SelenideElement middleNameField = $x("//input[@name='middleName']");
    private final SelenideElement lastNameField = $x("//input[@name='lastName']");
    private final SelenideElement vacancyInputField = $x("//div[@class='oxd-select-text-input']");
    private final SelenideElement vacancyOptions = $x("//div[@role='listbox']");
    private final SelenideElement emailField = $x("(//input)[5]");
    private final SelenideElement contactNumberField = $x("(//input)[6]");
    private final SelenideElement applicationDateField = $x("(//input)[9]");
    private final SelenideElement notesField = $x("//textarea");
    private final SelenideElement saveButton = $x("//button[@type='submit'][text()=' Save ']");

    // 'Recruitment' page elements
    private final SelenideElement addCandidateButton = $x("//button[text()=' Add ']");
    private final SelenideElement candidateNameFilter = $x("(//input)[2]");
    private final SelenideElement candidateNameOptions = $x("//div[@role='listbox']");
    private final SelenideElement searchCandidateButton = $x("//button[text()=' Search ']");
    private final SelenideElement candidatesTopBarButton = $x("//a[text()='Candidates']");
    private final SelenideElement tableRowCandidateField = $x("(//div" +
            "[@class='oxd-table-cell oxd-padding-cell'])[3]/div");

    private final String firstOptionLocator = ".//span[1]";

    @Step("Click add candidate button")
    public RecruitmentPage clickAddCandidateButton() {
        addCandidateButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("Get filtered candidate name")
    public String getFilteredCandidateName() {
        return tableRowCandidateField.shouldBe(Condition.visible).getText();
    }

    @Step("Filter candidates by '{0}' name")
    public RecruitmentPage filterCandidatesList(String candidateName) {
        candidateNameFilter.shouldBe(Condition.editable).setValue(candidateName);
        candidateNameOptions.$x(firstOptionLocator).click();

        searchCandidateButton.click();

        return this;
    }

    @Step("Set candidate's first name as {0}")
    public RecruitmentPage setFirstName(String firstName) {
        firstNameField.shouldBe(Condition.editable).setValue(firstName);

        return this;
    }

    @Step("Set candidate's middle name as {0}")
    public RecruitmentPage setMiddleName(String middleName) {
        middleNameField.shouldBe(Condition.editable).setValue(middleName);

        return this;
    }

    @Step("Set candidate's last name as {0}")
    public RecruitmentPage setLastName(String lastName) {
        lastNameField.shouldBe(Condition.editable).setValue(lastName);

        return this;
    }

    @Step("Set candidate's vacancy")
    public RecruitmentPage setVacancy() {
        vacancyInputField.shouldBe(Condition.enabled).click();
        vacancyOptions.$x(firstOptionLocator).click();

        return this;
    }

    @Step("Set candidate's email as {0}")
    public RecruitmentPage setEmail(String email) {
        emailField.shouldBe(Condition.editable).setValue(email);

        return this;
    }

    @Step("Set candidate's contact number as {0}")
    public RecruitmentPage setContactNumber(String contactNumber) {
        contactNumberField.shouldBe(Condition.editable).setValue(contactNumber);

        return this;
    }

    @Step("Set candidate's application date as {0}")
    public RecruitmentPage setApplicationDate(String applicationDate) {
        applicationDateField.shouldBe(Condition.editable).sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        applicationDateField.setValue(applicationDate);

        return this;
    }

    @Step("Set candidate's notes")
    public RecruitmentPage setNotes(String notes) {
        notesField.shouldBe(Condition.editable).setValue(notes);

        return this;
    }

    @Step("Click save candidate")
    public RecruitmentPage clickSaveButton() {
        saveButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("Go to candidates list")
    public RecruitmentPage goToCandidatesList() {
        candidatesTopBarButton.click();

        return this;
    }
}