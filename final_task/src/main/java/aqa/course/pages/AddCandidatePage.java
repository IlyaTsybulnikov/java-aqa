package aqa.course.pages;

import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class AddCandidatePage {

    private final SiteNavigationSidePanel navigationSidePanel = new SiteNavigationSidePanel();

    private final SelenideElement firstNameField = $x("//input[@name='firstName']");
    private final SelenideElement middleNameField = $x("//input[@name='middleName']");
    private final SelenideElement lastNameField = $x("//input[@name='lastName']");
    private final SelenideElement vacancyInputField = $x("//div[div[label[text()='Vacancy']]]" +
            "//div[@class='oxd-select-text-input']");
    private final SelenideElement vacancyOptions = $x("//div[div[label[text()='Vacancy']]]" +
            "//div[@role='listbox']");
    private final SelenideElement emailField = $x("//div[div[label[text()='Email']]]//input");
    private final SelenideElement contactNumberField = $x("//div[div[label" +
            "[text()='Contact Number']]]//input");
    private final SelenideElement applicationDateField = $x("//div[div[label" +
            "[text()='Date of Application']]]//input");
    private final SelenideElement notesField = $x("//div[div[label[text()='Notes']]]//textarea");
    private final SelenideElement saveButton = $x("//button[@type='submit'][text()=' Save ']");


    public void setFirstName(String firstName) {
        firstNameField.setValue(firstName);
    }
    public void setMiddleName(String middleName) {
        middleNameField.setValue(middleName);
    }

    public void setLastName(String lastName) {
        lastNameField.setValue(lastName);
    }

    public void setVacancy() {
        vacancyInputField.click();

        vacancyOptions.$x(".//span[1]").click();
    }

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setContactNumber(String contactNumber) {
        contactNumberField.setValue(contactNumber);
    }

    public void setApplicationDate(String applicationDate) {
        applicationDateField.sendKeys(Keys.CONTROL + "A");
        applicationDateField.sendKeys(Keys.BACK_SPACE);

        applicationDateField.setValue(applicationDate);
    }

    public void setNotes(String notes) {
        notesField.setValue(notes);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void goToCandidatesList() {
        this.navigationSidePanel.clickGoToRecruitmentPage();
    }
}