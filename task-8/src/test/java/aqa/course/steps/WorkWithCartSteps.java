package aqa.course.steps;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.CollectionCondition;

import aqa.course.pages.CartPage;
import aqa.course.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WorkWithCartSteps {

    private final HomePage homePage = new HomePage();
    private List<String> addedProductNames;
    private CartPage cartPage;

    @When("I add three products to cart")
    public void addToCart() {
        this.addedProductNames = homePage.getAllProducts().first(3)
                .asDynamicIterable().stream()
                .peek(homePage::clickAddToCartButton)
                .map(product -> homePage.getProductName(product).getText())
                .collect(Collectors.toList());
    }

    @When("I click cart icon")
    public void goToCart() {
         this.cartPage = homePage.clickCartButton();
    }

    @Then("check if cart contains selected products")
    public void checkCartProducts() {
        cartPage.getCartItemNames().shouldHave(
                CollectionCondition.size(3),
                CollectionCondition.texts(addedProductNames)
        );
    }

    @When("I click \"remove\" button on the third cart item")
    public void removeThirdCartItem() {
        cartPage.clickRemoveThirdCartItem();
    }

    @Then("check if cart no longer contains third item")
    public void checkThirdItemRemoved() {
        cartPage.getCartItemNames().shouldHave(
                CollectionCondition.size(2),
                CollectionCondition.itemWithText(addedProductNames.get(0)),
                CollectionCondition.itemWithText(addedProductNames.get(1))
        );
    }
}
