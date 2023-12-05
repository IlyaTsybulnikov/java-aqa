package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AssignLeavePage {

    private final SelenideElement employeeNameInput = $x("(//input)[2]");
    private final SelenideElement employeeNameFirstOption = $x("//div[@role='listbox']//span");
    private final SelenideElement leaveTypeField = $x("//div[@class='oxd-select-text-input']");
    private final SelenideElement leaveTypeOptions = $x("//div[@role='listbox']");
    private final SelenideElement fromDateInput = $x("(//input)[3]");
    private final SelenideElement toDateInput = $x("(//input)[4]");
    private final SelenideElement assignButton = $x("//button[@type='submit'][text()=' Assign ']");
    private final SelenideElement okModalButton = $x("//button[@type='button'][text()=' Ok ']");
    private final SelenideElement spinner = $x("//div[@class='oxd-loading-spinner-container']");

    private final SiteHeader siteHeader = new SiteHeader();

    @Step("Get current user name")
    public String getCurrentUserName() {
        return siteHeader.getCurrentUserName();
    }

    @Step("Set leave's employee name as {0}")
    public String setEmployeeName(String name) {
        employeeNameInput.setValue(name);
        employeeNameFirstOption.click();

        return employeeNameInput.getValue();
    }

    @Step("Set leave's leave type as {0}")
    public AssignLeavePage setLeaveType(String leaveType) {
        leaveTypeField.click();
        leaveTypeOptions.$x(".//span[text()='" + leaveType + "']").click();

        return this;
    }

    @Step("Set leave's fromDate to {0}")
    public AssignLeavePage setFromDate(String fromDate) {
        fromDateInput.shouldBe(Condition.visible).sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        fromDateInput.setValue(fromDate);

        return this;
    }

    @Step("Set leave's toDate to {0}")
    public AssignLeavePage setToDate(String toDate) {
        toDateInput.sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        toDateInput.setValue(toDate);

        return this;
    }

    @Step("Click assign leave button")
    public AssignLeavePage clickAssignButton() {
        assignButton.click();

        spinner.should(Condition.appear);

        return this;
    }

    @Step("Click 'Ok' on modal window")
    public LeavePage clickOkModalButton() {
        okModalButton.click();

        return page(LeavePage.class);
    }
}
