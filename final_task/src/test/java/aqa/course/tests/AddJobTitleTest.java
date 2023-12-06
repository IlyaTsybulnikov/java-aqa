package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.elements.SiteNavigationSidePanel;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
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
        page(SiteNavigationSidePanel.class)
                .clickOpenAdminPage()
                .goToJobTitleList()
                .clickAddJobTitleButton()
                .enterRecordData(title, description, note)
                .clickSaveButton()
                .verifyJobTitleExist(title)
                .deleteJobTitleByTitle(title)
                .verifyJobTitleDoNotExist(title);
    }
}