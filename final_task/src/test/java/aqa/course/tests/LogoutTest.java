package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.pages.DashboardPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class LogoutTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Check logout process")
    public void logoutTest() {
        page(DashboardPage.class)
                .logout()
                .getLoginButton()
                .should(Condition.exist)
                .shouldBe(Condition.visible);
    }
}