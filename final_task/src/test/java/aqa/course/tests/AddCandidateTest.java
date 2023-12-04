package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.DashboardPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@Feature("Add recruitment candidate")
public class AddCandidateTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Creation of new candidate")
    public void createNewCandidate() {
        String fullName = Constants.TEST_FIRST_NAME
                + " " + Constants.TEST_MIDDLE_NAME
                + " " + Constants.TEST_LAST_NAME;

        page(DashboardPage.class)
                .goToRecruitmentPage()
                .clickAddCandidateButton()
                .setFirstName(Constants.TEST_FIRST_NAME)
                .setMiddleName(Constants.TEST_MIDDLE_NAME)
                .setLastName(Constants.TEST_LAST_NAME)
                .setVacancy()
                .setEmail(Constants.TEST_EMAIL)
                .setContactNumber(Constants.TEST_CONTACT_NUMBER)
                .setApplicationDate(Constants.TEST_APPLICATION_DATE)
                .setNotes(Constants.TEST_NOTES)
                .clickSaveButton()
                .goToCandidatesList()
                .filterCandidatesList(fullName)
                .getCandidateByName(fullName)
                .shouldHave(Condition.text(fullName));
    }
}