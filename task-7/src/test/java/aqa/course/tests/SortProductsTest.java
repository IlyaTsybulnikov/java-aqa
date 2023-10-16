package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;

import com.codeborne.selenide.ElementsCollection;

import aqa.course.hooks.Configuration;

class SortProductsTest extends Configuration {

    @Test
    @DisplayName("Sort products and make sure they moved ")
    void productsSortingTest() {
        ElementsCollection allProducts;
        Select sortSelect = new Select(homePage.getSortSelect());

        sortSelect.selectByValue("az");
        allProducts = homePage.getAllProducts();
        String firstElemBeforeSort = homePage.getProductName(allProducts.first()).getText();

        sortSelect.selectByValue("za");
        allProducts = homePage.getAllProducts();
        String lastElemAfterSort = homePage.getProductName(allProducts.last()).getText();

        assertEquals(firstElemBeforeSort, lastElemAfterSort);

        sortSelect = new Select(homePage.getSortSelect());
        sortSelect.selectByValue("hilo");
        allProducts = homePage.getAllProducts();
        firstElemBeforeSort = homePage.getProductName(allProducts.first()).getText();

        sortSelect = new Select(homePage.getSortSelect());
        sortSelect.selectByValue("lohi");
        allProducts = homePage.getAllProducts();
        lastElemAfterSort = homePage.getProductName(allProducts.last()).getText();

        assertEquals(firstElemBeforeSort, lastElemAfterSort);
    }
}
