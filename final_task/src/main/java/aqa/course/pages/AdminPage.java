package aqa.course.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AdminPage {

    private final SelenideElement addButton = $x("//button[text()=' Add ']");
    private final SelenideElement topbarMenu = $x("//nav[@class='oxd-topbar-body-nav']");
    private final SelenideElement jobTitlesOption = $x("//li[a[text()='Job Titles']]");
    private final SelenideElement usernameFilter = $x("//div[@class='oxd-table-filter']" +
            "[div/div/h5[text()='System Users']]//div[div/label[text()='Username']]//input");
    private final SelenideElement searchButton = $x("//button[@type='submit'][text()=' Search ']");

    public CreateUserPage clickAddButton() {
        addButton.click();

        return page(CreateUserPage.class);
    }

    public SelenideElement getUserRowByUsername(String username) {

        return $x("//div[text() = '" + username + "']");
    }

    public void filterUserList(String username) {
        usernameFilter.setValue(username);

        searchButton.click();
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
}