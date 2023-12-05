package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.AssignLeavePage;
import aqa.course.pages.DashboardPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.page;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class AssignLeaveTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Create Assign Leave and validate it's info")
    public void createAndValidateAssignLeaveTest() {
        String currentUserName = page(AssignLeavePage.class).getCurrentUserName();
        LocalDate lastDayOfYear = LocalDate.now().plusYears(1).with(lastDayOfYear());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDate = dtf.format(lastDayOfYear.minusDays(30));
        String toDate = dtf.format(lastDayOfYear);

        String newEmployeeName =
                 page(DashboardPage.class)
                .goToLeavePage()
                .clickAssignLeaveButton()
                .setLeaveType(Constants.TEST_LEAVE_TYPE)
                .setFromDate(fromDate)
                .setToDate(toDate)
                .setEmployeeName(currentUserName);

        page(AssignLeavePage.class)
                .clickAssignButton()
                .clickOkModalButton()
                .clickMyLeaveButton()
                .removeLeaveStatusFilter()
                .filterMyLeaves(fromDate, toDate, Constants.TEST_LEAVE_SCHEDULED_TYPE)
                .checkIfLeaveCreated()
                .openLeaveDetails()
                .validateLeave(fromDate, toDate, newEmployeeName);
    }
}