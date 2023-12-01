package aqa.course.steps;

import aqa.course.constants.Constants;
import aqa.course.pages.PIMPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class PIMCheckSteps {

    private PIMPage pimPage = new PIMPage();

    private ElementsCollection headerButtons;
    private ElementsCollection tableFilterButtons;
    private ElementsCollection tableFilterFields;
    private SelenideElement recordsTable;
    private SelenideElement tableFilter;
    private SelenideElement tableAddButton;

    @When("^I get all pim page elements$")
    public void getPageElements() {
        headerButtons = pimPage.getHeaderButtons();
        tableFilterButtons = pimPage.getTableFilterButtons();
        tableFilterFields = pimPage.getTableFilterFields();
        recordsTable = pimPage.getRecordsTable();
        tableFilter = pimPage.getTableFilter();
        tableAddButton = pimPage.getTableAddButton();
    }

    @Then("^all pim page elements exist and are visible$")
    public void checkPageElements() {
        try {
            Thread.sleep(600);          //          никакие should не помогают :(
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> headerButtonsNames = headerButtons
                .stream()
                .map(headerButton ->
                        pimPage.getElementName(headerButton
                                .should(Condition.exist)
                                .shouldBe(Condition.visible)))
                .collect(Collectors.toList());

        List<String> tableFilterButtonsNames = tableFilterButtons
                .stream()
                .map(tableFilterButton ->
                        pimPage.getElementName(tableFilterButton
                                .should(Condition.exist)
                                .shouldBe(Condition.visible)))
                .collect(Collectors.toList());

        List<String> tableFilterFieldsNames = tableFilterFields
                .stream()
                .map(tableFilterField ->
                        pimPage.getElementLabelText(tableFilterField
                                .should(Condition.exist)
                                .shouldBe(Condition.visible)))
                .collect(Collectors.toList());

        Assertions.assertEquals(Constants.TEST_PIM_HEADER_BUTTONS_NAMES, headerButtonsNames);
        Assertions.assertEquals(Constants.TEST_PIM_TABLE_FILTER_BUTTONS_NAMES, tableFilterButtonsNames);
        Assertions.assertEquals(Constants.TEST_PIM_TABLE_FILTER_FIELDS_NAMES, tableFilterFieldsNames);

        Assertions.assertTrue(recordsTable.exists());
        Assertions.assertTrue(recordsTable.isDisplayed());
        Assertions.assertTrue(recordsTable.isEnabled());

        Assertions.assertTrue(tableFilter.exists());
        Assertions.assertTrue(tableFilter.isDisplayed());
        Assertions.assertTrue(tableFilter.isEnabled());

        Assertions.assertTrue(tableAddButton.exists());
        Assertions.assertTrue(tableAddButton.isDisplayed());
        Assertions.assertTrue(tableAddButton.isEnabled());
    }
}