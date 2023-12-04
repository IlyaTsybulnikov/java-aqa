package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.DashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.page;

public class DashboardElementsTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Make sure that all dashboard page elements exist")
    public void dashboardElementsExistTest() {
        List<String> elementNames = page(DashboardPage.class)
                .getGridElements()
                .shouldHave(CollectionCondition.size(7))
                .stream()
                .map(pageElement ->
                        page(DashboardPage.class)
                                .getPageElementName(pageElement
                                        .should(Condition.exist)
                                        .shouldBe(Condition.visible)
                                )
                ).collect(Collectors.toList());

        Assertions.assertEquals(Constants.TEST_DASHBOARD_ELEMENT_NAMES, elementNames);
    }
}