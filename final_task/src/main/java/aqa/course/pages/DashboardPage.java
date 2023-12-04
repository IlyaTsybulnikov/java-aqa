package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement dashboardGrid = $x("//div[@class='oxd-grid-3 " +
            "orangehrm-dashboard-grid']");

    private final SiteNavigationSidePanel siteNavigationSidePanel = new SiteNavigationSidePanel();
    private final SiteHeader siteHeader = new SiteHeader();

    public AdminPage goToAdminPage() {
        return this.siteNavigationSidePanel.clickGoToAdminPage();
    }

    public RecruitmentPage goToRecruitmentPage() {
        return this.siteNavigationSidePanel.clickGoToRecruitmentPage();
    }

    public LeavePage goToLeavePage() {
        return this.siteNavigationSidePanel.clickGoToLeavePage();
    }

    public PIMPage goToPIMPage() {
        return this.siteNavigationSidePanel.clickGoToPIMPage();
    }

    public ElementsCollection getGridElements() {
        return dashboardGrid
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .$$x(".//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']");
    }

    public String getPageElementName(SelenideElement pageElement) {
        return pageElement
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .$x(".//p").getText();
    }

    public String getCurrentUserName() {
        return this.siteHeader.getCurrentUserName();
    }

    public LoginPage clickLogout() {
        return this.siteHeader.clickLogout();
    }

    public LoginPage logout() {
        return this.siteHeader.clickLogout();
    }
}
