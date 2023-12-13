package aqa.course.elements;

import aqa.course.pages.LoginPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class SiteHeader {

    private final SelenideElement userDetailsDropdown = $x("//span[@class='oxd-userdropdown-tab']");
    private final SelenideElement currentPageHeader = $x("(//span//h6)[last()]");
    private final SelenideElement logoutButton = $x("//a[text()='Logout']");

    @Step("Get current username")
    public String getCurrentUserName() {
        return userDetailsDropdown.getText();
    }

    @Step("Click logout")
    public LoginPage clickLogout() {
        userDetailsDropdown.click();
        logoutButton.click();

        return page(LoginPage.class);
    }

    @Step("Get page title")
    public SelenideElement getPageName() {
        return currentPageHeader;
    }
}
