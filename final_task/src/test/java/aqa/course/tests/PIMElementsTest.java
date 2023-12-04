package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.DashboardPage;
import aqa.course.pages.PIMPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.page;

public class PIMElementsTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Make sure that all pim page elements exist")
    public void pimElementsExistTest() {
        page(DashboardPage.class)
                .goToPIMPage();

        checkHeaderButtons();
        checkTableFilterButtons();
        checkTableFilterFields();

        checkRecordsTable();
        checkTableFilter();
        checkTableAddButton();
    }

    private void checkHeaderButtons() {
        List<String> headerButtonsNames =
                page(PIMPage.class)
                        .getHeaderButtons()
                        .shouldHave(CollectionCondition.sizeGreaterThan(0))
                        .stream().map(headerButton ->
                                page(PIMPage.class).getElementName(headerButton
                                        .should(Condition.exist)
                                        .shouldBe(Condition.visible)))
                        .collect(Collectors.toList());

        Assertions.assertEquals(Constants.TEST_PIM_HEADER_BUTTONS_NAMES, headerButtonsNames);
    }

    private void checkTableFilterButtons() {
        List<String> tableFilterButtonsNames =
                page(PIMPage.class)
                        .getTableFilterButtons()
                        .shouldHave(CollectionCondition.sizeGreaterThan(0))
                        .stream().map(tableFilterButton ->
                                page(PIMPage.class).getElementName(tableFilterButton
                                        .should(Condition.exist)
                                        .shouldBe(Condition.visible)))
                        .collect(Collectors.toList());

        Assertions.assertEquals(Constants.TEST_PIM_TABLE_FILTER_BUTTONS_NAMES, tableFilterButtonsNames);
    }

    private void checkTableFilterFields() {
        List<String> tableFilterFieldsNames =
                page(PIMPage.class)
                        .getTableFilterFields()
                        .shouldHave(CollectionCondition.sizeGreaterThan(0))
                        .stream().map(tableFilterField ->
                                page(PIMPage.class).getElementLabelText(tableFilterField
                                        .should(Condition.exist)
                                        .shouldBe(Condition.visible)))
                        .collect(Collectors.toList());

        Assertions.assertEquals(Constants.TEST_PIM_TABLE_FILTER_FIELDS_NAMES, tableFilterFieldsNames);
    }

    private void checkRecordsTable() {
        page(PIMPage.class)
                .getRecordsTable()
                .should(Condition.exist)
                .shouldBe(Condition.visible);
    }

    private void checkTableFilter() {
        page(PIMPage.class)
                .getTableFilter()
                .should(Condition.exist)
                .shouldBe(Condition.visible);
    }

    private void checkTableAddButton() {
        page(PIMPage.class)
                .getTableAddButton()
                .should(Condition.exist)
                .shouldBe(Condition.visible);
    }
}