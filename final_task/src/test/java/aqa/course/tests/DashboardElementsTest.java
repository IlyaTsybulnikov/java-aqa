package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.DashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.collections.ExactTexts;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class DashboardElementsTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Make sure that all dashboard page elements exist")
    public void dashboardElementsExistTest() {
        page(DashboardPage.class)
                .getGridElementsTitles()
                .shouldHave(CollectionCondition.size(7))
                .shouldHave(ExactTexts.exactTexts(Constants.TEST_DASHBOARD_ELEMENT_NAMES));
    }
}