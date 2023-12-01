package aqa.course.steps;

import aqa.course.constants.Constants;
import aqa.course.pages.AssignLeavePage;
import aqa.course.pages.LeaveDetailsPage;
import aqa.course.pages.LeavePage;
import aqa.course.pages.MyLeavePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AssignLeaveSteps {

    private LeavePage leavePage = new LeavePage();
    private AssignLeavePage assignLeavePage;
    private MyLeavePage myLeavePage;
    private LeaveDetailsPage leaveDetailsPage;
    private String currentUserName;
    private String fromDate;
    private String toDate;

    @When("^I click 'Assign Leave' button to in header$")
    public void clickAssignLeaveButton() {
        assignLeavePage = leavePage.clickAssignLeaveButton();
    }

    @And("^I enter data to all leave fields$")
    public void enterLeaveData() {
        currentUserName = assignLeavePage.getCurrentUserName();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fromDate = dtf.format(LocalDateTime.now().plusYears(1));
        toDate = dtf.format(LocalDateTime.now().plusYears(1).plusDays(30));

        assignLeavePage.setEmployeeName(currentUserName);
        assignLeavePage.setLeaveType(Constants.TEST_LEAVE_TYPE);
        assignLeavePage.setFromDate(fromDate);
        assignLeavePage.setToDate(toDate);
    }

    @And("^I click 'Assign' button to assign leave$")
    public void clickAssignButton() {
        assignLeavePage.clickAssignButton();
    }

    @And("^I click 'Ok' on the confirmation window$")
    public void confirmAssign() {
        leavePage = assignLeavePage.clickOkModalButton();
    }

    @And("^I go to My Leave list$")
    public void goToMyLeave() {
        myLeavePage = leavePage.clickMyLeaveButton();
    }

    @Then("^leave assign is created$")
    public void checkIfLeaveCreated() {
        myLeavePage.filterMyLeaves(fromDate, toDate, Constants.TEST_LEAVE_SCHEDULED_TYPE);

        Assertions.assertTrue(myLeavePage.checkIfLeaveCreated());
    }

    @When("^I view leave details$")
    public void openLeaveDetails() {
        leaveDetailsPage = myLeavePage.openLeaveDetails();
    }

    @Then("^leave details are correct$")
    public void validateLeaveDetails() {
        Boolean leaveIsValid = leaveDetailsPage.validateLeave(fromDate, toDate, currentUserName);

        Assertions.assertTrue(leaveIsValid);
    }
}
