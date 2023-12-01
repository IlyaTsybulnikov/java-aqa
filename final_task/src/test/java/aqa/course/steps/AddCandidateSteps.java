package aqa.course.steps;

import aqa.course.constants.Constants;
import aqa.course.pages.AddCandidatePage;
import aqa.course.pages.RecruitmentPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddCandidateSteps {
    private final RecruitmentPage recruitmentPage = new RecruitmentPage();
    private AddCandidatePage addCandidatePage;

    @When("^I click 'Add' button to add candidate$")
    public void clickAddCandidateButton() {
        addCandidatePage = recruitmentPage.clickAddCandidateButton();
    }

    @And("^I enter data to all candidate fields$")
    public void enterDataToAllFields() {
        addCandidatePage.setFirstName(Constants.TEST_FIRST_NAME);
        addCandidatePage.setMiddleName(Constants.TEST_MIDDLE_NAME);
        addCandidatePage.setLastName(Constants.TEST_LAST_NAME);
        addCandidatePage.setVacancy();
        addCandidatePage.setEmail(Constants.TEST_EMAIL);
        addCandidatePage.setContactNumber(Constants.TEST_CONTACT_NUMBER);
        addCandidatePage.setApplicationDate(Constants.TEST_APPLICATION_DATE);
        addCandidatePage.setNotes(Constants.TEST_NOTES);
    }

    @And("^I click 'Save' button to save candidate$")
    public void saveCandidate() {
        addCandidatePage.clickSaveButton();
    }

    @And("^I go back to candidates list$")
    public void goToCandidatesList() {
        addCandidatePage.goToCandidatesList();
    }

    @Then("^candidate is created$")
    public void checkIfCandidateCreated() {
        String fullName = Constants.TEST_FIRST_NAME
                + " " + Constants.TEST_MIDDLE_NAME
                + " " + Constants.TEST_LAST_NAME;

        recruitmentPage.filterCandidatesList(fullName);
        SelenideElement candidateName = recruitmentPage.getCandidateByName(fullName);

        candidateName.shouldHave(Condition.text(fullName));
    }
}