package aqa.course.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class JobTitleListPage {

    private final SelenideElement addJobTitleButton = $x("//button[@type='button'][text()=' Add ']");
    private final SelenideElement confirmDeleteButton = $x("//button[text()=' Yes, Delete ']");

    @Step("Click add job title button")
    public CreateJobTitlePage clickAddJobTitleButton() {
        addJobTitleButton.click();

        return page(CreateJobTitlePage.class);
    }

    @Step("Get '{0}' job title element")
    public SelenideElement getJobTitleElement(String jobTitle) {
        return $x("//div[text()='" + jobTitle + "']");
    }

    @Step("Delete '{0}' job title")
    public JobTitleListPage deleteJobTitleByTitle(String jobTitle) {
        $x("//div[div/div[text()='" + jobTitle + "']]" +
                "//button[i[@class='oxd-icon bi-trash']]").click();

        confirmDeleteButton.click();

        return this;
    }
}