package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AdminPage {

    private final SelenideElement addButton = $x("//button[text()=' Add ']");
    private final SelenideElement topbarMenu = $x("//nav[@class='oxd-topbar-body-nav']");
    private final SelenideElement jobTitlesOption = $x("//li[a[text()='Job Titles']]");
    private final SelenideElement usernameFilter = $x("(//input" +
            "[@class='oxd-input oxd-input--active'])[2]");
    private final SelenideElement searchButton = $x("//button[@type='submit'][text()=' Search ']");

    private final SiteHeader siteHeader = new SiteHeader();

    @Step("Click add user")
    public CreateUserPage clickAddButton() {
        addButton.click();

        return page(CreateUserPage.class);
    }

    @Step("Filter users by '{0}' username")
    public AdminPage filterUserList(String username) {
        siteHeader.getPageName().shouldHave(Condition.ownText("User Management"));

        usernameFilter.shouldBe(Condition.editable).setValue(username);

        searchButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("CLick edit button on {0} user table row")
    public CreateUserPage openEditNewUserPage(String username) {
        $x("//div[div/div[text()='" + username + "']]" +
                "//button[i[@class='oxd-icon bi-pencil-fill']]").click();

        return page(CreateUserPage.class);
    }

    @Step("Go to job title list")
    public JobTitleListPage goToJobTitleList() {
        topbarMenu.$x(".//span[@class='oxd-topbar-body-nav-tab-item'][text()='Job ']").click();
        jobTitlesOption.click();

        return page(JobTitleListPage.class);
    }
}