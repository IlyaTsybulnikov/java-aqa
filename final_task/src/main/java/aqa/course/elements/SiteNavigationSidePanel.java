package aqa.course.elements;

import aqa.course.pages.AdminPage;
import aqa.course.pages.RecruitmentPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class SiteNavigationSidePanel {

    private final SelenideElement adminButton = $x("//li[a/span[text()='Admin']]");
    private final SelenideElement recruitmentButton = $x("//li[a/span[text()='Recruitment']]");

    @Step("Click go to admin tab")
    public AdminPage clickGoToAdminPage() {
        adminButton.click();

        return page(AdminPage.class);
    }

    @Step("Click go to recruitment tab")
    public RecruitmentPage clickGoToRecruitmentPage() {
        recruitmentButton.click();

        return page(RecruitmentPage.class);
    }
}