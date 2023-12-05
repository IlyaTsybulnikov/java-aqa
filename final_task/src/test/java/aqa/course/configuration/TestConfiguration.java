package aqa.course.configuration;

import aqa.course.constants.Constants;
import aqa.course.pages.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestConfiguration {

    @BeforeAll
    static void loginToSite() {
        open(Constants.LOGIN_PAGE_URL);

        if (!WebDriverRunner.getWebDriver().getCurrentUrl().contains("login")) return;

        page(LoginPage.class)
                .enterUsername(Constants.LOGIN_USERNAME)
                .enterPassword(Constants.LOGIN_PASSWORD)
                .clickLoginButton();
    }
}