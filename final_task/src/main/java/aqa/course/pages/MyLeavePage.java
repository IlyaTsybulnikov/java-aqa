package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class MyLeavePage {

    private final SelenideElement fromDateInput = $x("//div[div/label[text()='From Date']]//input");
    private final SelenideElement toDateInput = $x("//div[div/label[text()='To Date']]//input");
    private final SelenideElement leaveStatusField = $x("//div[div/label" +
            "[text()='Show Leave with Status']]//div[@class='oxd-select-text-input']");
    private final SelenideElement leaveStatusOptions = $x("//div[div/label" +
            "[text()='Show Leave with Status']]//div[@role='listbox']");
    private final SelenideElement searchButton = $x("//button[@type='submit'][text()=' Search ']");
    private final SelenideElement leaveDetailsButton = $x("//div[@class='oxd-table-card']/div" +
            "//button[@class='oxd-icon-button']");
    private final SelenideElement viewLeaveDetailsOption = $x("//ul/li[p[text()='View Leave Details']]");

    public void filterMyLeaves(String fromDate, String toDate, String status) {
        fromDateInput.setValue(fromDate);

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        toDateInput.sendKeys(Keys.CONTROL + "A");
        toDateInput.sendKeys(Keys.BACK_SPACE);

        toDateInput.setValue(toDate);

        leaveStatusField.click();
        leaveStatusOptions.$x(".//span[text()='" + status + "']").click();

        searchButton.click();
    }

    public Boolean checkIfLeaveCreated() {
        leaveDetailsButton.should(Condition.exist);

        return leaveDetailsButton.exists();
    }

    public LeaveDetailsPage openLeaveDetails() {
        leaveDetailsButton.click();
        viewLeaveDetailsOption.click();

        return page(LeaveDetailsPage.class);
    }
}
