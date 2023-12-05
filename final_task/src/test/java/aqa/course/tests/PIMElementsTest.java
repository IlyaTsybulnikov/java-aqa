package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.pages.DashboardPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class PIMElementsTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Make sure that all pim page elements exist")
    public void pimElementsExistTest() {
        page(DashboardPage.class)
                .goToPIMPage()
                .verifyTableFilterButtons()
                .verifyTopBarMenuButtons()
                .verifyTableFilterFields()
                .verifyRecordsTableColumnHeaders()
                .verifyTableAddButton();
    }
}