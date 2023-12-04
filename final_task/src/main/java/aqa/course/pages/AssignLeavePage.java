package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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

    public String getCurrentUserName() {
        return this.siteHeader.getCurrentUserName();
    }

    public AssignLeavePage setEmployeeName(String name) {
        employeeNameInput.setValue(name);
        employeeNameFirstOption.click();

        return this;
    }

    public AssignLeavePage setLeaveType(String leaveType) {
        leaveTypeField.click();
        leaveTypeOptions.$x(".//span[text()='" + leaveType + "']").click();

        return this;
    }

    public AssignLeavePage setFromDate(String fromDate) {
        fromDateInput
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .sendKeys(Keys.CONTROL + "A");
        fromDateInput.sendKeys(Keys.BACK_SPACE);

        fromDateInput.setValue(fromDate);

        return this;
    }

    public AssignLeavePage setToDate(String toDate) {
        toDateInput.sendKeys(Keys.CONTROL + "A");
        toDateInput.sendKeys(Keys.BACK_SPACE);

        toDateInput.setValue(toDate);

        return this;
    }

    public AssignLeavePage clickAssignButton() {
        assignButton.click();

        spinner.should(Condition.exist).shouldBe(Condition.visible).should(Condition.appear);

        return this;
    }

    public LeavePage clickOkModalButton() {
        okModalButton.click();

        return page(LeavePage.class);
    }
}
