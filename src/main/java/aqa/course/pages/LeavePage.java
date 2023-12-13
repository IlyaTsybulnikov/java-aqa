package aqa.course.pages;

import aqa.course.constants.Constants;
import aqa.course.elements.SiteNavigationSidePanel;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class LeavePage {

    // Leave page top bar menu buttons
    private final SelenideElement assignLeaveButton = $x("//a[text()='Assign Leave']");
    private final SelenideElement myLeaveButton = $x("//a[text()='My Leave']");

    // 'Assign Leave' form elements
    private final SelenideElement employeeNameInput = $x("(//input)[2]");
    private final SelenideElement employeeNameFirstOption = $x("//div[@role='listbox']//span");
    private final SelenideElement leaveTypeField = $x("//div[@class='oxd-select-text-input']");
    private final SelenideElement leaveTypeOptions = $x("//div[@role='listbox']");
    private final SelenideElement fromDateInput = $x("(//input)[3]");
    private final SelenideElement toDateInput = $x("(//input)[4]");
    private final SelenideElement assignButton = $x("//button[@type='submit'][text()=' Assign ']");
    private final SelenideElement okModalButton = $x("//button[@type='button'][text()=' Ok ']");
    private final SelenideElement spinner = $x("//div[@class='oxd-loading-spinner-container']");

    // Leave Details fields
    private final SelenideElement employeeName = $x("(//p)[2]");
    private final SelenideElement leaveDates = $x("(//p)[3]");

    // 'My Leave' page elements
    private final SelenideElement fromDateFilterInput = $x("(//input)[2]");
    private final SelenideElement toDateFilterInput = $x("(//input)[3]");
    private final SelenideElement leaveStatusField = $x("//div[@class='oxd-select-text-input']");
    private final SelenideElement leaveStatusOptions = $x("//div[@role='listbox']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");
    private final SelenideElement leaveDetailsButton = $x("//div[@class='oxd-table-card']" +
            "//button[@class='oxd-icon-button']");
    private final SelenideElement viewLeaveDetailsOption = $x("//p[text()='View Leave Details']");
    private final ElementsCollection leaveStatusFilterRemoveButtons = $$x("//i" +
            "[@class='oxd-icon bi-x --clear']");

    private final String optionLocator = ".//span[text()='%s']";
    private final String leaveTableRowLocator = "//div[@class='oxd-table-card']";

    @Step("Click Assign Leave button")
    public LeavePage clickAssignLeaveButton() {
        assignLeaveButton.click();

        return this;
    }

    @Step("Click My Leave button")
    public LeavePage clickMyLeaveButton() {
        myLeaveButton.click();

        return this;
    }

    @Step("Set leave's employee name as {0}")
    public String setEmployeeName(String username) {
        employeeNameInput.setValue(username);
        employeeNameFirstOption.click();

        return employeeNameInput.getValue();
    }

    @Step("Set leave's leave type as {0}")
    public LeavePage setLeaveType(String leaveType) {
        leaveTypeField.click();
        leaveTypeOptions.$x(String.format(optionLocator, leaveType)).click();

        return this;
    }

    @Step("Set leave's fromDate to {0}")
    public LeavePage setFromDate(String fromDate) {
        fromDateInput.shouldBe(Condition.editable).setValue(fromDate);

        return this;
    }

    @Step("Set leave's toDate to {0}")
    public LeavePage setToDate(String toDate) {
        toDateInput.shouldBe(Condition.enabled).click();
        toDateInput.shouldNotHave(Condition.exactValue("")).sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        toDateInput.setValue(toDate);

        return this;
    }

    @Step("Click assign leave button")
    public LeavePage clickAssignButton() {
        assignButton.click();

        spinner.should(Condition.appear);

        return this;
    }

    @Step("Click 'Ok' on modal window")
    public LeavePage clickOkModalButton() {
        okModalButton.click();

        return this;
    }

    @Step("Check if leave info is valid")
    public Boolean checkIfLeaveIsValid(String fromDate, String toDate, String name) {
        if (!leaveDates.getText().startsWith(fromDate)) {
            checkDateFormat();
        }

        employeeName.shouldHave(Condition.exactText(name));
        leaveDates.shouldHave(Condition.partialText(fromDate), Condition.partialText(toDate));

        return employeeName.getText().equals(name)
                && leaveDates.getText().contains(fromDate)
                && leaveDates.getText().contains(toDate);
    }

    @Step("Remove Leave status filter")
    public LeavePage removeLeaveStatusFilter() {
        leaveStatusFilterRemoveButtons
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asFixedIterable()
                .forEach(e -> leaveStatusFilterRemoveButtons.get(0).click());

        return this;
    }

    @Step("Filter My leaves")
    public LeavePage filterMyLeaves(String fromDate, String toDate, String status) {
        fromDateFilterInput
                .shouldNotHave(Condition.exactValue(""))
                .sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        fromDateFilterInput.setValue(fromDate);

        toDateFilterInput
                .shouldNotHave(Condition.exactValue(""))
                .sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        toDateFilterInput.setValue(toDate);

        leaveStatusField.shouldBe(Condition.enabled).click();
        leaveStatusOptions.$x(String.format(optionLocator, status)).click();

        searchButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("Check if leave is created")
    public LeavePage checkIfLeaveCreated() {
        $$x(leaveTableRowLocator).shouldHave(CollectionCondition.size(1));

        leaveDetailsButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Open leave details")
    public LeavePage openLeaveDetails() {
        leaveDetailsButton.click();
        viewLeaveDetailsOption.click();

        return this;
    }

    @Step("Check if Date Format is default")
    private void checkDateFormat() {
        page(SiteNavigationSidePanel.class)
                .clickOpenAdminPage()
                .goToLocalization()
                .setDefaultDateFormat();

        page(SiteNavigationSidePanel.class)
                .clickOpenLeavePage()
                .clickMyLeaveButton()
                .removeLeaveStatusFilter()
                .filterMyLeaves(
                        Constants.TEST_LEAVE_FROM_DATE,
                        Constants.TEST_LEAVE_TO_DATE,
                        Constants.TEST_LEAVE_SCHEDULED_TYPE
                )
                .checkIfLeaveCreated()
                .openLeaveDetails();
    }
}