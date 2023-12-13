package aqa.course.pages;

import aqa.course.constants.Constants;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.collections.ExactTexts;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

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
    public Boolean verifyTopBarMenuButtons() {
        topBarMenuButtons
                .shouldHave(ExactTexts.exactTexts(Constants.TEST_PIM_HEADER_BUTTONS_NAMES))
                .asDynamicIterable().forEach(button -> button.shouldBe(Condition.enabled));

        List<String> pimHeaderButtonsNames = topBarMenuButtons
                .stream()
                .map(button -> button.getText())
                .collect(Collectors.toList());

        return pimHeaderButtonsNames.equals(Constants.TEST_PIM_HEADER_BUTTONS_NAMES);
    }

    @Step("Verify table filter buttons are enabled")
    public Boolean verifyTableFilterButtons() {
        tableFilterButtons
                .shouldHave(ExactTexts.exactTexts(Constants.TEST_PIM_TABLE_FILTER_BUTTONS_NAMES))
                .asDynamicIterable().forEach(button -> button.shouldBe(Condition.enabled));

        List<String> pimTableFilterButtonsNames = tableFilterButtons
                .stream()
                .map(button -> button.getText())
                .collect(Collectors.toList());

        return pimTableFilterButtonsNames.equals(Constants.TEST_PIM_TABLE_FILTER_BUTTONS_NAMES);
    }

    @Step("Verify TableFilter Fields")
    public Boolean verifyTableFilterFields() {
        tableFilterFieldsTitles.shouldHave(ExactTexts.exactTexts(Constants.TEST_PIM_TABLE_FILTER_FIELDS_NAMES));

        List<String> pimFilterFieldsTitles = tableFilterFieldsTitles
                .stream()
                .map(title -> title.getText())
                .collect(Collectors.toList());

        return pimFilterFieldsTitles.equals(Constants.TEST_PIM_TABLE_FILTER_FIELDS_NAMES);
    }

    @Step("Verify records table column headers exist")
    public Boolean verifyRecordsTableColumnHeaders() {
        tableColumnHeaders
                .shouldHave(CollectionCondition.size(9))
                .asDynamicIterable().forEach(header -> header.should(Condition.exist));

        return tableColumnHeaders.size() == 9;
    }

    @Step("Verify table 'Add' button")
    public Boolean verifyTableAddButton() {
        tableAddButton.should(Condition.enabled);

        return tableAddButton.isEnabled();
    }
}