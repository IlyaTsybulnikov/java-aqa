package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@name='username']");
    private final SelenideElement passwordField = $x("//input[@name='password']");
    private final SelenideElement loginButton = $x("//button[@type='submit']");
    private final SelenideElement pageTitle = $x("//h5");

    @Step("Open login page")
    public LoginPage openLoginPage() {
        open(Constants.LOGIN_PAGE_URL);

        return this;
    }

    @Step("Enter username as {0}")
    public LoginPage enterUsername(String username) {
        usernameField.shouldBe(Condition.editable).setValue(username);

        return this;
    }

    @Step("Enter password as {0}")
    public LoginPage enterPassword(String password) {
        passwordField.shouldBe(Condition.editable).setValue(password);

        return this;
    }

    @Step("Click Login button")
    public SiteNavigationSidePanel clickLoginButton() {
        loginButton.shouldBe(Condition.enabled).click();

        return page(SiteNavigationSidePanel.class);
    }

    @Step("Verify that login page title is visible")
    public Boolean verifyPageTitleIsVisible() {
        pageTitle.shouldBe(Condition.visible).shouldHave(Condition.text("Login"));

        return pageTitle.getText().equals("Login");
    }

    @Step("Login to site")
    public void login(String username, String password) {
        open(Constants.LOGIN_PAGE_URL);

        usernameField.shouldBe(Condition.editable).setValue(username);
        passwordField.shouldBe(Condition.editable).setValue(password);
        loginButton.shouldBe(Condition.enabled).click();
    }
}