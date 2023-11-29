package aqa.course.pages;

import aqa.course.elements.SiteNavigationSidePanel;

public class DashboardPage {

    private final SiteNavigationSidePanel siteNavigationSidePanel = new SiteNavigationSidePanel();

    public void goToAdminPage() {
        this.siteNavigationSidePanel.clickGoToAdminPage();
    }

    public void goToRecruitmentPage() {
        this.siteNavigationSidePanel.clickGoToRecruitmentPage();
    }
}
