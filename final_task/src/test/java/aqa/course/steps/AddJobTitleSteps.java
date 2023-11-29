package aqa.course.steps;

import aqa.course.pages.AdminPage;
import aqa.course.pages.CreateJobTitlePage;
import aqa.course.pages.JobTitleListPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddJobTitleSteps {

    private AdminPage adminPage = new AdminPage();
    private JobTitleListPage jobTitleListPage;
    private CreateJobTitlePage createJobTitlePage;

    @When("^I click 'Job' dropdown and 'Job Titles' option$")
    public void openJobTitleList() {
        jobTitleListPage = adminPage.goToJobTitleList();
    }

    @And("^I click 'Add' button to add job title$")
    public void clickAddButton() {
        createJobTitlePage =  jobTitleListPage.clickAddJobTitleButton();
    }

    @And("^I enter (.*), (.*) and (.*) into specified fields$")
    public void enterJobTitleData(String jobTitle, String jobDescription, String jobNote) {
        createJobTitlePage.enterRecordData(jobTitle, jobDescription, jobNote);
    }

    @And("^I click 'Save' button to save job title$")
    public void clickSave() {
        jobTitleListPage = createJobTitlePage.clickSaveButton();
    }

    @Then("^job record with title \"(.*)\" is created$")
    public void checkCreatedJobTitle(String jobTitle) {
        SelenideElement jobTitleElement = jobTitleListPage.getJobTitleElement(jobTitle);

        jobTitleElement.shouldHave(Condition.text(jobTitleElement.getText()));
    }

    @When("^I click delete button next to record with \"(.*)\" title$")
    public void deleteRecord(String jobTitle) {
        jobTitleListPage.deleteJobTitleByTitle(jobTitle);
    }

    @Then("^job record with title \"(.*)\" is deleted$")
    public void checkDeletedJobTitle(String jobTitle) {
        SelenideElement jobTitleElement = jobTitleListPage.getJobTitleElement(jobTitle);

        jobTitleElement.shouldNot(Condition.exist);
    }
}