package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@id='user-name']");
    private final SelenideElement passwordField = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//input[@id='login-button']");

    public HomePage logIn(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();

        return page(HomePage.class);
    }
}