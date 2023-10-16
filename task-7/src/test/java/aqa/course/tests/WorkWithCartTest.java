package aqa.course.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import aqa.course.hooks.Configuration;
import aqa.course.pages.CartPage;

class WorkWithCartTest extends Configuration {

    @Test
    @DisplayName("Checking if adding to cart and removing from cart works well")
    void addAndRemoveFromCartTest() {
        ElementsCollection productsToAddToCart = homePage.getAllProducts().first(3);

        List<String> addedProductNames = productsToAddToCart.asDynamicIterable().stream()
                .map(product -> homePage.getProductName(product).getText()).collect(Collectors.toList());

        productsToAddToCart.asDynamicIterable().forEach(product -> homePage.clickAddToCartButton(product));

        CartPage cartPage = homePage.clickCartButton();
        ElementsCollection cartItems = cartPage.getCartItemNames();

        cartItems.shouldHave(CollectionCondition.texts(addedProductNames));

        cartPage.clickRemoveThirdCartItem();
        cartItems = cartPage.getCartItemNames();

        cartItems.shouldHave(CollectionCondition.size(2));
        cartItems.shouldHave(CollectionCondition.itemWithText(addedProductNames.get(0)));
        cartItems.shouldHave(CollectionCondition.itemWithText(addedProductNames.get(1)));
    }
}
