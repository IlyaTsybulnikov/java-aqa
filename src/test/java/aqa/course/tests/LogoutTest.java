package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.elements.SiteHeader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Logout Test")
public class LogoutTest extends BaseTest {

    @Test
    @Description("Check logout process")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Logout")
    public void logoutTest() {
        Boolean isLogoutSuccessfull = page(SiteHeader.class)
                .clickLogout()
                .verifyPageTitleIsVisible();

        Assertions.assertTrue(isLogoutSuccessfull);
    }
}