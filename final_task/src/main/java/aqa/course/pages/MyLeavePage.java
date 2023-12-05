package aqa.course.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Remove Leave status filter")
    public MyLeavePage removeLeaveStatusFilter() {
        /*int numberOfFilters = leaveStatusFilterRemoveButtons
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .size();

        System.out.println(numberOfFilters);
        for (int i = numberOfFilters - 1; i >= 0; i--) {
            leaveStatusFilterRemoveButtons
                    .get(i)
                    .should(Condition.exist)
                    .shouldBe(Condition.visible)
                    .shouldBe(Condition.interactable)
                    .click();
        }*/

        leaveStatusFilterRemoveButtons
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable().forEach(button -> {
                    System.out.println(button);
                    System.out.println(leaveStatusFilterRemoveButtons.indexOf(button));
                    System.out.println(button.$x("./parent::span").getText());
                    button.click();
                });
        // .asDynamicIterable().forEach(button -> button.shouldBe(Condition.enabled))

        System.out.println( $$x("//i[@class='oxd-icon bi-x --clear']").size() );
        return this;
    }

    @Step("Filter My leaves")
    public MyLeavePage filterMyLeaves(String fromDate, String toDate, String status) {
        fromDateInput.shouldBe(Condition.interactable).sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        fromDateInput.setValue(fromDate);

        toDateInput.shouldBe(Condition.interactable).sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        toDateInput.setValue(toDate);

        leaveStatusField.shouldBe(Condition.enabled).click();
        leaveStatusOptions.$x(".//span[text()='" + status + "']").click();

        searchButton.shouldBe(Condition.enabled).click();

        return this;
    }

    @Step("Check if leave is created")
    public MyLeavePage checkIfLeaveCreated() {
        $$x("//div[@class='oxd-table-card']")
                .shouldHave(CollectionCondition.size(1));

        leaveDetailsButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Open leave details")
    public LeaveDetailsPage openLeaveDetails() {
        leaveDetailsButton.click();
        viewLeaveDetailsOption.click();

        return page(LeaveDetailsPage.class);
    }
}
