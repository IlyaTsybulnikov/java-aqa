package aqa.course.elements;

import aqa.course.pages.AdminPage;
import aqa.course.pages.LeavePage;
import aqa.course.pages.PIMPage;
import aqa.course.pages.RecruitmentPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class SiteNavigationSidePanel {

    private final SelenideElement adminButton = $x("//span[text()='Admin']");
    private final SelenideElement recruitmentButton = $x("//span[text()='Recruitment']");
    private final SelenideElement leaveButton = $x("//span[text()='Leave']");
    private final SelenideElement pimButton = $x("//span[text()='PIM']");
    private final SelenideElement navigationPanel = $x("//nav[@class='oxd-navbar-nav']");

    @Step("Click open admin page button")
    public AdminPage clickOpenAdminPage() {
        adminButton.click();

        return page(AdminPage.class);
    }

    @Step("Click go to recruitment tab")
    public RecruitmentPage clickOpenRecruitmentPage() {
        recruitmentButton.click();

        return page(RecruitmentPage.class);
    }

    @Step("Click go to leave tab")
    public LeavePage clickOpenLeavePage() {
        leaveButton.click();

        return page(LeavePage.class);
    }

    @Step("Click go to pim tab")
    public PIMPage clickOpenPIMPage() {
        pimButton.click();

        return page(PIMPage.class);
    }

    @Step("Verify that navigation panel is enabled")
    public void verifyNavPanelIsEnabled() {
        navigationPanel.shouldBe(Condition.enabled);
    }
}