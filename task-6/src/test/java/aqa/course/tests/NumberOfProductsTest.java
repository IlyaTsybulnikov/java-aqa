package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import aqa.course.hooks.Configuration;

class NumberOfProductsTest extends Configuration {

    @Test
    @DisplayName("Check the number of products")
    void itemsAmountTest() {
        List<WebElement> products = homePage.getAllProducts();

        assertEquals(6, products.size());
    }
}
