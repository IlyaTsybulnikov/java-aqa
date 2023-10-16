package aqa.course.tests;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;

public class LoginTest {

    @BeforeEach
    public void driverSetUp() {
        open("https://www.saucedemo.com/");
    }

    @ParameterizedTest(name = "Logging in as {0}")
    @CsvFileSource(resources = "/loginCredentials.csv")
    @DisplayName("Checking login for different users")
    void SuccessfulLoginTest(String username, String password) {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.logIn(username, password);

        assertFalse(homePage.getAllProducts().isEmpty());
    }
}
