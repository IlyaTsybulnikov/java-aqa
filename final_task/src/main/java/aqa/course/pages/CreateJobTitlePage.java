package aqa.course.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CreateJobTitlePage {

    private final SelenideElement jobTitleInputField = $x("//div[div/label[text()='Job Title']]//input");
    private final SelenideElement jobDescriptionTextArea = $x("//div[div/label[text()='Job Description']]" +
            "//textarea");
    private final SelenideElement noteTextArea = $x("//div[div/label[text()='Note']]//textarea");
    private final SelenideElement saveButton = $x("//button[@type='submit'][text()=' Save ']");

    public void enterRecordData(String jobTitle, String jobDescription, String jobNote) {
        jobTitleInputField.setValue(jobTitle);
        jobDescriptionTextArea.setValue(jobDescription);
        noteTextArea.setValue(jobNote);
    }

    public JobTitleListPage clickSaveButton() {
        saveButton.click();

        return page(JobTitleListPage.class);
    }
}