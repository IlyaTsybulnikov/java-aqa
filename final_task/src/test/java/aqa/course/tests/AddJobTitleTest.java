package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.pages.DashboardPage;
import aqa.course.pages.JobTitleListPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.page;

public class AddJobTitleTest
        extends BaseTest
        implements TestConfiguration {

    @ParameterizedTest
    @Description("Add job title")
    @CsvFileSource(resources = "/jobTitlesInfo.csv", numLinesToSkip = 1)
    public void addJobTitleTest(String title, String description, String note) {
        page(DashboardPage.class)
                .goToAdminPage()
                .goToJobTitleList()
                .clickAddJobTitleButton()
                .enterRecordData(title, description, note)
                .clickSaveButton()
                .getJobTitleElement(title)
                .shouldHave(Condition.text(title));

        page(JobTitleListPage.class)
                .deleteJobTitleByTitle(title)
                .getJobTitleElement(title)
                .shouldNot(Condition.exist);
    }
}