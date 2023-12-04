package aqa.course.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CreateJobTitlePage {

    private final SelenideElement jobTitleInputField = $x("(//input)[2]");
    private final SelenideElement jobDescriptionTextArea = $x("//textarea");
    private final SelenideElement noteTextArea = $x("(//textarea)[2]");
    private final SelenideElement saveButton = $x("//button[@type='submit'][text()=' Save ']");

    public CreateJobTitlePage enterRecordData(String jobTitle, String jobDescription, String jobNote) {
        jobTitleInputField.setValue(jobTitle);
        jobDescriptionTextArea.setValue(jobDescription);
        noteTextArea.setValue(jobNote);

        return this;
    }

    public JobTitleListPage clickSaveButton() {
        saveButton.click();

        return page(JobTitleListPage.class);
    }
}