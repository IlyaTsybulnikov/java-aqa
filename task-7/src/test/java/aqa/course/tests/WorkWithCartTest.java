package aqa.course.tests;

import aqa.course.hooks.Configuration;
import aqa.course.pages.CartPage;
import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class WorkWithCartTest extends Configuration {

    @Test
    @DisplayName("Checking if adding to cart and removing from cart works well")
    void addAndRemoveFromCartTest() {
        List<String> addedProductNames = homePage.getAllProducts().first(3)
                .asDynamicIterable().stream()
                .peek(product -> homePage.clickAddToCartButton(product))
                .map(product -> homePage.getProductName(product).getText())
                .collect(Collectors.toList());

        CartPage cartPage = homePage.clickCartButton();

        cartPage.getCartItemNames().shouldHave(
                CollectionCondition.size(3),
                CollectionCondition.texts(addedProductNames)
        );

        cartPage.clickRemoveThirdCartItem();

        cartPage.getCartItemNames().shouldHave(
                CollectionCondition.size(2),
                CollectionCondition.itemWithText(addedProductNames.get(0)),
                CollectionCondition.itemWithText(addedProductNames.get(1))
        );
    }
}
