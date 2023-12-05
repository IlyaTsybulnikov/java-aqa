package aqa.course.pages;

import aqa.course.constants.Constants;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.collections.ExactTexts;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class PIMPage {

    private final ElementsCollection topBarMenuButtons = $$x("//nav[@aria-label='Topbar Menu']//li");
    private final ElementsCollection tableFilterButtons = $$x("//div[@class='oxd-table-filter-area']" +
            "//button");
    private final ElementsCollection tableFilterFieldsTitles = $$x("//div" +
            "[@class='oxd-input-group__label-wrapper']");
    private final ElementsCollection tableColumnHeaders = $$x("//div[@role='columnheader']");
    private final SelenideElement tableAddButton = $x("//div[@class='orangehrm-header-container']/button");

    @Step("Verify Top Bar menu buttons are enabled")
    public PIMPage verifyTopBarMenuButtons() {
        topBarMenuButtons
                .shouldHave(ExactTexts.exactTexts(Constants.TEST_PIM_HEADER_BUTTONS_NAMES))
                .asDynamicIterable().forEach(button -> button.shouldBe(Condition.enabled));

        return this;
    }

    @Step("Verify table filter buttons are enabled")
    public PIMPage verifyTableFilterButtons() {
        tableFilterButtons
                .shouldHave(ExactTexts.exactTexts(Constants.TEST_PIM_TABLE_FILTER_BUTTONS_NAMES))
                .asDynamicIterable().forEach(button -> button.shouldBe(Condition.enabled));

        return this;
    }

    @Step("Verify TableFilter Fields")
    public PIMPage verifyTableFilterFields() {
        tableFilterFieldsTitles.shouldHave(ExactTexts.exactTexts(Constants.TEST_PIM_TABLE_FILTER_FIELDS_NAMES));

        return this;
    }

    @Step("Verify records table column headers exist")
    public PIMPage verifyRecordsTableColumnHeaders() {
        tableColumnHeaders
                .shouldHave(CollectionCondition.size(9))
                .asDynamicIterable().forEach(header -> header.should(Condition.exist));

        return this;
    }

    @Step("Verify table 'Add' button")
    public void verifyTableAddButton() {
        tableAddButton.should(Condition.enabled);
    }
}