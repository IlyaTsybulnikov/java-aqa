package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import aqa.course.hooks.Configuration;

class SortProductsTest extends Configuration {

    @Test
    @DisplayName("Sort products and make sure they moved ")
    void productsSortingTest() {
        Select sortSelect = new Select(homePage.getSortSelect());

        sortSelect.selectByValue("az");
        WebElement firstElemBeforeSort = homePage.getFirstProduct();

        sortSelect.selectByValue("za");
        WebElement lastElemAfterSort = homePage.getLastProduct();

        assertEquals(firstElemBeforeSort, lastElemAfterSort);

        sortSelect = new Select(homePage.getSortSelect());
        sortSelect.selectByValue("hilo");

        firstElemBeforeSort = homePage.getFirstProduct();

        sortSelect = new Select(homePage.getSortSelect());
        sortSelect.selectByValue("lohi");

        lastElemAfterSort = homePage.getLastProduct();

        assertEquals(firstElemBeforeSort, lastElemAfterSort);
    }
}
