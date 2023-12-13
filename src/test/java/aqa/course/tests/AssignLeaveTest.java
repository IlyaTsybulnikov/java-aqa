package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.constants.Constants;
import aqa.course.elements.SiteNavigationSidePanel;
import aqa.course.pages.LeavePage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Assign Leave Test")
public class AssignLeaveTest extends BaseTest {

    @Test
    @Description("Create Assign Leave and validate it's info")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Create and Validate Assign Leave")
    public void createAndValidateAssignLeaveTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        LeavePage leavePage = page(LeavePage.class);

        String newEmployeeName =
                 page(SiteNavigationSidePanel.class)
                .clickOpenLeavePage()
                .clickAssignLeaveButton()
                .setLeaveType(Constants.TEST_LEAVE_TYPE)
                .setFromDate(Constants.TEST_LEAVE_FROM_DATE)
                .setToDate(Constants.TEST_LEAVE_TO_DATE)
                .setEmployeeName(currentUsername);

        leavePage
                .clickAssignButton()
                .clickOkModalButton()
                .clickMyLeaveButton()
                .removeLeaveStatusFilter()
                .filterMyLeaves(
                        Constants.TEST_LEAVE_FROM_DATE,
                        Constants.TEST_LEAVE_TO_DATE,
                        Constants.TEST_LEAVE_SCHEDULED_TYPE
                );

        softAssertions.assertThat(leavePage.checkIfLeaveCreated().equals(true));

        leavePage.openLeaveDetails();

        Boolean isLeaveInfoValid = leavePage.checkIfLeaveIsValid(
                Constants.TEST_LEAVE_FROM_DATE,
                Constants.TEST_LEAVE_TO_DATE,
                newEmployeeName
        );

        softAssertions.assertThat(isLeaveInfoValid.equals(true));
        softAssertions.assertAll();
    }
}