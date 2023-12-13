package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.elements.SiteNavigationSidePanel;
import aqa.course.pages.AdminPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Add Job Title Test")
public class AddJobTitleTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/jobTitlesInfo.csv", numLinesToSkip = 1)
    @Description("Add job title")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Add Job Title")
    public void addJobTitleTest(String title, String description, String note) {
        SoftAssertions softAssertions = new SoftAssertions();
        AdminPage adminPage = page(AdminPage.class);

        page(SiteNavigationSidePanel.class)
                .clickOpenAdminPage()
                .goToJobTitleList()
                .clickAddJobTitleButton()
                .enterRecordData(title, description, note)
                .clickSaveButton();

        softAssertions.assertThat(adminPage.checkIfJobTitleExist(title).equals(true));

        adminPage.deleteJobTitleByTitle(title);

        softAssertions.assertThat(adminPage.checkIfJobTitleExist(title).equals(false));

        softAssertions.assertAll();
    }
}