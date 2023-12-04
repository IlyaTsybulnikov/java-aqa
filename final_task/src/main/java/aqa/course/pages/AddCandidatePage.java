package aqa.course.pages;

import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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


    public AddCandidatePage setFirstName(String firstName) {
        firstNameField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(firstName);

        return this;
    }

    public AddCandidatePage setMiddleName(String middleName) {
        middleNameField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(middleName);

        return this;
    }

    public AddCandidatePage setLastName(String lastName) {
        lastNameField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(lastName);

        return this;
    }

    public AddCandidatePage setVacancy() {
        vacancyInputField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .click();

        vacancyOptions.$x(".//span[1]").click();

        return this;
    }

    public AddCandidatePage setEmail(String email) {
        emailField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(email);

        return this;
    }

    public AddCandidatePage setContactNumber(String contactNumber) {
        contactNumberField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(contactNumber);

        return this;
    }

    public AddCandidatePage setApplicationDate(String applicationDate) {
        applicationDateField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .sendKeys(Keys.CONTROL + "A");
        applicationDateField.sendKeys(Keys.BACK_SPACE);

        applicationDateField.setValue(applicationDate);

        return this;
    }

    public AddCandidatePage setNotes(String notes) {
        notesField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(notes);

        return this;
    }

    public AddCandidatePage clickSaveButton() {
        saveButton
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .click();

        return this;
    }

    public RecruitmentPage goToCandidatesList() {
        this.navigationSidePanel.clickGoToRecruitmentPage();

        return page(RecruitmentPage.class);
    }
}