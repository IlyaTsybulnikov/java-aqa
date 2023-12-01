package aqa.course.steps;

import aqa.course.constants.Constants;
import aqa.course.pages.DashboardPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardCheckSteps {

    private DashboardPage dashboardPage = new DashboardPage();
    private ElementsCollection pageElements;

    @When("^I get all dashboard page elements$")
    public void getPageElements() {
        pageElements = dashboardPage.getGridElements();
    }

    @Then("^all dashboard page elements exist and are visible$")
    public void checkPageElements() {
        List<String> pageElementNames = pageElements.stream()
                .map(pageElement -> dashboardPage.getPageElementName(pageElement
                        .should(Condition.exist)
                        .shouldBe(Condition.visible)))
                .collect(Collectors.toList());

        Assertions.assertEquals(Constants.TEST_DASHBOARD_ELEMENT_NAMES, pageElementNames);
        Assertions.assertEquals(7, pageElementNames.size());
    }
}
