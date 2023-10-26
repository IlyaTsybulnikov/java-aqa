package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@id='user-name']");
    private final SelenideElement passwordField = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//input[@id='login-button']");

    @Step("Login to site")
    @Description("Enter username and password, and click 'Log In' button")
    public HomePage logIn(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();

        return page(HomePage.class);
    }
}