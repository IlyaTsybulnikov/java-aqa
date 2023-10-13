package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aqa.course.hooks.Configuration;
import aqa.course.pages.CartPage;
import aqa.course.pages.HomePage;

class WorkWithCartTest extends Configuration {

    @Test
    @DisplayName("Checking if adding to cart and removing from cart works well")
    void addAndRemoveFromCartTest() {
        HomePage homePage = new HomePage(driver);

        homePage.addProductsToCart();
        List<String> productNames = homePage.getProductNames();

        CartPage cartPage = homePage.clickCartButton();
        List<String> cartItemNames = cartPage.getCartItemNames();

        assertEquals(productNames, cartItemNames);

        cartPage.clickRemoveThirdCartItem();
        cartItemNames = cartPage.getCartItemNames();

        assertEquals(2, cartItemNames.size());
        assertTrue(cartItemNames.contains(productNames.get(0)));
        assertTrue(cartItemNames.contains(productNames.get(1)));
        assertFalse(cartItemNames.contains(productNames.get(2)));
    }
}
