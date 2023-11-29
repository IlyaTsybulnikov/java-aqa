package aqa.course.hooks;

import aqa.course.constants.Constants;
import aqa.course.pages.DashboardPage;
import aqa.course.pages.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class BeforeSteps {

    @Given("I am logged in to site")
    @Step("Open Login page")
    public void openLoginPage() {
        open(Constants.LOGIN_PAGE_URL);

        if(!WebDriverRunner.getWebDriver().getCurrentUrl().contains("login")) {
            return;
        }

        List<String> credentials = Arrays.asList(Constants.LOGIN_USERNAME, Constants.LOGIN_PASSWORD);

        new LoginPage().logIn(credentials.get(0), credentials.get(1));
    }

    @Before(value = "@SolenoidTest")
    public static void setupAllureReports() {
        Configuration.browser = Constants.BROWSER_CHROME;
        Configuration.browserSize = Constants.BROWSER_SIZE_FULL_HD;
        Configuration.timeout = 6000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @And("^I go to admin page$")
    public void goToAdminPage() {
        new DashboardPage().goToAdminPage();
    }

    @And("^I go to recruitment page$")
    public void goToRecruitmentPage() {
        new DashboardPage().goToRecruitmentPage();
    }
}
