package aqa.course.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import aqa.course.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Feature;

public class NumberOfProductsSteps {

    private final HomePage homePage = new HomePage();
    private ElementsCollection allProducts;

    @When("^I get all products on the home page$")
    @Feature("Check number of products")
    public void getAllProducts() {
        this.allProducts = homePage.getAllProducts();
    }

    @Then("^check if number of products equals six$")
    @Feature("Check number of products")
    public void checkProductNumber() {
        this.allProducts.shouldHave(CollectionCondition.size(6));
    }
}