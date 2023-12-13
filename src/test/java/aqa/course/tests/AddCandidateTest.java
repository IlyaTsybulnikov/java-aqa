package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.constants.Constants;
import aqa.course.elements.SiteNavigationSidePanel;
import aqa.course.pages.RecruitmentPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Add Candidate Test")
public class AddCandidateTest extends BaseTest {

    @Test
    @Description("Creation of new candidate")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Create New Candidate")
    public void createNewCandidate() {
        page(SiteNavigationSidePanel.class)
                .clickOpenRecruitmentPage()
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
                .filterCandidatesList(Constants.TEST_FIRST_NAME);

        Assertions.assertEquals(page(RecruitmentPage.class).getFilteredCandidateName(), Constants.TEST_FULL_NAME);
    }
}