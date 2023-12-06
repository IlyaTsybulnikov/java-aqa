package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.constants.Constants;
import aqa.course.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Login Test")
public class LoginTest extends BaseTest {

    @Test
    @Description("Check login process")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Login")
    public void loginTest() {
        page(LoginPage.class)
                .openLoginPage()
                .enterUsername(Constants.LOGIN_USERNAME)
                .enterPassword(Constants.LOGIN_PASSWORD)
                .clickLoginButton()
                .verifyNavPanelIsEnabled();
    }
}