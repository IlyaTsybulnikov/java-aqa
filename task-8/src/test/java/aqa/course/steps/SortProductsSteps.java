package aqa.course.steps;

import com.codeborne.selenide.Condition;

import aqa.course.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortProductsSteps {

    private final HomePage homePage = new HomePage();
    private String firstElemBeforeSort;

    @When("I select \"(.*)\" sort option")
    public void selectSortOption(String sortOption) {
        homePage.getSortSelect().selectOptionByValue(sortOption);
    }

    @When("I take first product")
    public void getFirstProduct() {
        firstElemBeforeSort = homePage.getProductName(homePage.getFirstProduct()).getText();
    }

    @Then("check if first product became last")
    public void compareFirstAndLastProducts() {
        homePage.getProductName(homePage.getLastProduct())
                .shouldHave(Condition.text(firstElemBeforeSort));
    }
}
