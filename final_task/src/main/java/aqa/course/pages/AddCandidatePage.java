package aqa.course.pages;

import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AddCandidatePage {

    private final SiteNavigationSidePanel navigationSidePanel = new SiteNavigationSidePanel();

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

    @Step("Set candidate's first name as {0}")
    public AddCandidatePage setFirstName(String firstName) {
        firstNameField.shouldBe(Condition.editable).setValue(firstName);

        return this;
    }

    @Step("Set candidate's middle name as {0}")
    public AddCandidatePage setMiddleName(String middleName) {
        middleNameField.shouldBe(Condition.editable).setValue(middleName);

        return this;
    }

    @Step("Set candidate's last name as {0}")
    public AddCandidatePage setLastName(String lastName) {
        lastNameField.shouldBe(Condition.editable).setValue(lastName);

        return this;
    }

    @Step("Set candidate's vacancy")
    public AddCandidatePage setVacancy() {
        vacancyInputField.shouldBe(Condition.enabled).click();
        vacancyOptions.$x(".//span[1]").click();

        return this;
    }

    @Step("Set candidate's email as {0}")
    public AddCandidatePage setEmail(String email) {
        emailField.shouldBe(Condition.editable).setValue(email);

        return this;
    }

    @Step("Set candidate's contact number as {0}")
    public AddCandidatePage setContactNumber(String contactNumber) {
        contactNumberField.shouldBe(Condition.editable).setValue(contactNumber);

        return this;
    }

    @Step("Set candidate's application date as {0}")
    public AddCandidatePage setApplicationDate(String applicationDate) {
        applicationDateField.shouldBe(Condition.editable).sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        applicationDateField.setValue(applicationDate);

        return this;
    }

    @Step("Set candidate's notes")
    public AddCandidatePage setNotes(String notes) {
        notesField.shouldBe(Condition.editable).setValue(notes);

        return this;
    }

    @Step("Click save candidate")
    public AddCandidatePage clickSaveButton() {
        saveButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("Go to candidates list")
    public RecruitmentPage goToCandidatesList() {
        navigationSidePanel.clickGoToRecruitmentPage();

        return page(RecruitmentPage.class);
    }
}