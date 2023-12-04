package aqa.course.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class MyLeavePage {

    private final SelenideElement fromDateInput = $x("(//input)[2]");
    private final SelenideElement toDateInput = $x("(//input)[3]");
    private final SelenideElement leaveStatusField = $x("//div[@class='oxd-select-text-input']");
    private final SelenideElement leaveStatusOptions = $x("//div[@role='listbox']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");
    private final SelenideElement leaveDetailsButton = $x("//div[@class='oxd-table-card']" +
            "//button[@class='oxd-icon-button']");
    private final SelenideElement viewLeaveDetailsOption = $x("//p[text()='View Leave Details']");
    private final ElementsCollection leaveStatusFilterRemoveButtons = $$x("//i" +
            "[@class='oxd-icon bi-x --clear']");

    public MyLeavePage removeLeaveStatusFilter() {
        int numberOfFilters = leaveStatusFilterRemoveButtons
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .size();

        for (int i = numberOfFilters - 1; i >= 0; i--) {
            leaveStatusFilterRemoveButtons
                    .get(i)
                    .should(Condition.exist)
                    .shouldBe(Condition.visible)
                    .shouldBe(Condition.interactable)
                    .click();
        }

        return this;
    }

    public MyLeavePage filterMyLeaves(String fromDate, String toDate, String status) {
        fromDateInput
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.interactable)
                .sendKeys(Keys.CONTROL + "A");
        fromDateInput.sendKeys(Keys.BACK_SPACE);

        fromDateInput
                .shouldHave(Condition.exactText(""))
                .setValue(fromDate);

        toDateInput
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.interactable)
                .sendKeys(Keys.CONTROL + "A");
        toDateInput.sendKeys(Keys.BACK_SPACE);

        toDateInput
                .shouldHave(Condition.exactText(""))
                .setValue(toDate);

        leaveStatusField
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.interactable)
                .click();
        leaveStatusOptions.$x(".//span[text()='" + status + "']").click();

        searchButton
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.interactable)
                .click();

        return this;
    }

    public MyLeavePage checkIfLeaveCreated() {
        $$x("//div[@class='oxd-table-card']")
                .shouldHave(CollectionCondition.size(1));

        leaveDetailsButton
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.interactable);

        return this;
    }

    public LeaveDetailsPage openLeaveDetails() {
        leaveDetailsButton.click();
        viewLeaveDetailsOption.click();

        return page(LeaveDetailsPage.class);
    }
}
