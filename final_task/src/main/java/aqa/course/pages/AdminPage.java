package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AdminPage {

    private final SelenideElement addButton = $x("//button[text()=' Add ']");
    private final SelenideElement topbarMenu = $x("//nav[@class='oxd-topbar-body-nav']");
    private final SelenideElement jobTitlesOption = $x("//li[a[text()='Job Titles']]");
    private final SelenideElement usernameFilter = $x("(//input" +
            "[@class='oxd-input oxd-input--active'])[2]");
    private final SelenideElement searchButton = $x("//button[@type='submit'][text()=' Search ']");
    private final SelenideElement spinner = $x("//div[@class='oxd-loading-spinner-container']");

    private final SiteHeader siteHeader = new SiteHeader();

    public CreateUserPage clickAddButton() {
        addButton.click();

        return page(CreateUserPage.class);
    }

    public SelenideElement getUserRowByUsername(String username) {

        return $x("//div[text() = '" + username + "']");
    }

    public AdminPage filterUserList(String username) {
        usernameFilter
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(username);

        searchButton
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .click();

        return this;
    }

    public CreateUserPage openEditUserPage(String username) {
        $x("//div[div/div[text()='" + username + "']]" +
                "//button[i[@class='oxd-icon bi-pencil-fill']]").click();

        return page(CreateUserPage.class);
    }

    public JobTitleListPage goToJobTitleList() {
        topbarMenu.$x(".//span[@class='oxd-topbar-body-nav-tab-item'][text()='Job ']").click();
        jobTitlesOption.click();

        return page(JobTitleListPage.class);
    }

    public AdminPage waitForSpinnerToDisappear() {
        spinner
                .shouldNot(Condition.exist)
                .shouldNotBe(Condition.visible);

        this.siteHeader.getPageName()
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldHave(Condition.ownText("User Management"));

        return this;
    }
}