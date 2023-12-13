package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.pages.DashboardPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Dashboard Elements Test")
public class DashboardElementsTest extends BaseTest {

    @Test
    @Description("Make sure that all dashboard page elements exist")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Make Sure that Dashboard Elements Exist")
    public void dashboardElementsExistTest() {
        Boolean doDashboardElementsExist = page(DashboardPage.class).validateGridElementsTitles();

        Assertions.assertTrue(doDashboardElementsExist);
    }
}