package aqa.course.elements;

import aqa.course.pages.LoginPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class SiteHeader {

    private final SelenideElement userDetailsDropdown = $x("//span[@class='oxd-userdropdown-tab']");
    private final SelenideElement currentPageHeader = $x("(//span//h6)[last()]");
    private final SelenideElement logoutButton = $x("//a[text()='Logout']");

    public String getCurrentUserName() {
        return userDetailsDropdown.getText();
    }

    public LoginPage clickLogout() {
        userDetailsDropdown.click();
        logoutButton.click();

        return page(LoginPage.class);
    }

    public SelenideElement getPageName() {
        return currentPageHeader;
    }
}
