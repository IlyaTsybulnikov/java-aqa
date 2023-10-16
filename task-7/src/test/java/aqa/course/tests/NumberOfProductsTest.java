package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.ElementsCollection;

import aqa.course.hooks.Configuration;

class NumberOfProductsTest extends Configuration {

    @Test
    @DisplayName("Check the number of products")
    void itemsAmountTest() {
        ElementsCollection products = homePage.getAllProducts();

        assertEquals(6, products.size());
    }
}
