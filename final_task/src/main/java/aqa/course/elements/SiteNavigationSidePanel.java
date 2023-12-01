package aqa.course.elements;

import aqa.course.pages.AdminPage;
import aqa.course.pages.LeavePage;
import aqa.course.pages.PIMPage;
import aqa.course.pages.RecruitmentPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class SiteNavigationSidePanel {

    private final SelenideElement adminButton = $x("//li[a/span[text()='Admin']]");
    private final SelenideElement recruitmentButton = $x("//li[a/span[text()='Recruitment']]");
    private final SelenideElement leaveButton = $x("//li[a/span[text()='Leave']]");
    private final SelenideElement pimButton = $x("//li[a/span[text()='PIM']]");

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

    @Step("Click go to leave tab")
    public LeavePage clickGoToLeavePage() {
        leaveButton.click();

        return page(LeavePage.class);
    }

    @Step("Click go to pim tab")
    public PIMPage clickGoToPIMPage() {
        pimButton.click();

        return page(PIMPage.class);
    }
}