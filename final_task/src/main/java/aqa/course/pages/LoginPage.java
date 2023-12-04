package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@name='username']");
    private final SelenideElement passwordField = $x("//input[@name='password']");
    private final SelenideElement loginButton = $x("//button[@type='submit']");
    private final SiteHeader siteHeader = new SiteHeader();

    public LoginPage openLoginPage() {
        open(Constants.LOGIN_PAGE_URL);

        if (this.siteHeader.getPageName().getText().equals("Dashboard")) logout();

        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(username);

        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(password);

        return this;
    }

    public DashboardPage clickLoginButton() {
        loginButton
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.interactable)
                .click();

        return page(DashboardPage.class);
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public LoginPage logout() {
        return this.siteHeader.clickLogout();
    }
}
