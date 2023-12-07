package aqa.course.configuration;

import aqa.course.constants.Constants;
import aqa.course.pages.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.Selenide.page;

public class BaseTest {

    @BeforeAll
    static void baseTestConfig(TestInfo info) {
        Configuration.browser = Constants.BROWSER_CHROME;
        Configuration.browserSize = Constants.BROWSER_SIZE_FULL_HD;
        Configuration.timeout = 5000;
        Configuration.headless = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        if (info.getDisplayName().equals("Login Test")) return;

        page(LoginPage.class)
                .openLoginPage()
                .enterUsername(Constants.LOGIN_USERNAME)
                .enterPassword(Constants.LOGIN_PASSWORD)
                .clickLoginButton();
    }

    @AfterAll
    @DisplayName("Complete a test and close driver")
    static void afterAllTests() {
        Selenide.closeWebDriver();
    }
}