package aqa.course.tests;

import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.codeborne.selenide.CollectionCondition;

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
        new LoginPage().logIn(username, password).getAllProducts()
                .shouldHave(CollectionCondition.sizeNotEqual(0));
    }
}
