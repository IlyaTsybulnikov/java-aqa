package aqa.course.configuration;

import aqa.course.constants.Constants;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void baseTestConfig() {
        Configuration.browser = Constants.BROWSER_CHROME;
        Configuration.browserSize = Constants.BROWSER_SIZE_FULL_HD;
        Configuration.timeout = 6000;
//        Configuration.headless = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}