package aqa.course.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SiteHeader {

    private final SelenideElement userDropdown = $x("//span[@class='oxd-userdropdown-tab']");

    public String getCurrentUserName() {
        return userDropdown.getText();
    }
}
