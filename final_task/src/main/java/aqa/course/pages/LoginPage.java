package aqa.course.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@name='username']");
    private final SelenideElement passwordField = $x("//input[@name='password']");
    private final SelenideElement loginButton = $x("//button[@type='submit']");

    @Step("Login to site")
    @Description("Enter username and password, and click 'Log In' button")
    public DashboardPage logIn(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();

        return page(DashboardPage.class);
    }
}
