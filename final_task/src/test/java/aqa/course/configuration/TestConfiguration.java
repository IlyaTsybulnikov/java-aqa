package aqa.course.configuration;

import aqa.course.constants.Constants;
import aqa.course.pages.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestConfiguration {

    @BeforeAll
    static void loginToSite() {
        open(Constants.LOGIN_PAGE_URL);

        if (!WebDriverRunner.getWebDriver().getCurrentUrl().contains("login")) return;

        List<String> credentials = Arrays.asList(Constants.LOGIN_USERNAME, Constants.LOGIN_PASSWORD);

        page(LoginPage.class)
                .enterUsername(credentials.get(0))
                .enterPassword(credentials.get(1))
                .clickLoginButton();
    }
}