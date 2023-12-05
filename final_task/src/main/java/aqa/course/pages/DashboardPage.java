package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class DashboardPage {

    private final ElementsCollection dashboardElementsTitles = $$x("//div" +
            "[@class='orangehrm-dashboard-widget-name']//p");

    private final SiteNavigationSidePanel siteNavigationSidePanel = new SiteNavigationSidePanel();
    private final SiteHeader siteHeader = new SiteHeader();

    @Step("Go to admin page")
    public AdminPage goToAdminPage() {
        return siteNavigationSidePanel.clickOpenAdminPage();
    }

    @Step("Go to recruitment page")
    public RecruitmentPage goToRecruitmentPage() {
        return siteNavigationSidePanel.clickGoToRecruitmentPage();
    }

    @Step("Go to leave page")
    public LeavePage goToLeavePage() {
        return siteNavigationSidePanel.clickGoToLeavePage();
    }

    @Step("Go to PIM page")
    public PIMPage goToPIMPage() {
        return siteNavigationSidePanel.clickGoToPIMPage();
    }

    @Step("Get Dashboard page elements")
    public ElementsCollection getGridElementsTitles() {
        return dashboardElementsTitles;
    }

    @Step("Logout")
    public LoginPage logout() {
        return siteHeader.clickLogout();
    }
}
