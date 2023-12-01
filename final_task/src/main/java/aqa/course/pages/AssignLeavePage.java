package aqa.course.pages;

import aqa.course.elements.SiteHeader;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AssignLeavePage {

    private final SelenideElement employeeNameInput = $x("//div[div/label[text()='Employee Name']]" +
            "//input");
    private final SelenideElement employeeNameFirstOption = $x("//div[div/label" +
            "[text()='Employee Name']]//div[@role='listbox']//span[1]");
    private final SelenideElement leaveTypeField = $x("//div[div/label[text()='Leave Type']]" +
            "//div[@class='oxd-select-text-input']");
    private final SelenideElement leaveTypeOptions = $x("//div[div/label[text()='Leave Type']]" +
            "//div[@role='listbox']");
    private final SelenideElement fromDateInput = $x("//div[div/label[text()='From Date']]//input");
    private final SelenideElement toDateInput = $x("//div[div/label[text()='To Date']]//input");
    private final SelenideElement assignButton = $x("//button[@type='submit'][text()=' Assign ']");
    private final SelenideElement okModalButton = $x("//button[@type='button'][text()=' Ok ']");

    private final SiteHeader siteHeader = new SiteHeader();

    public String getCurrentUserName() {
        return this.siteHeader.getCurrentUserName();
    }

    public void setEmployeeName(String name) {
        employeeNameInput.setValue(name);
        employeeNameFirstOption.click();
    }

    public void setLeaveType(String leaveType) {
        leaveTypeField.click();
        leaveTypeOptions.$x(".//span[text()='" + leaveType + "']").click();
    }

    public void setFromDate(String fromDate) {
        fromDateInput.setValue(fromDate);
    }

    public void setToDate(String toDate) {
        toDateInput.sendKeys(Keys.CONTROL + "A");
        toDateInput.sendKeys(Keys.BACK_SPACE);

        toDateInput.setValue(toDate);
    }

    public void clickAssignButton() {
        assignButton.click();
    }

    public LeavePage clickOkModalButton() {
        okModalButton.click();

        return page(LeavePage.class);
    }
}
