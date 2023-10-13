package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import aqa.course.hooks.Configuration;

public class SortProductsTest extends Configuration {

    private final By sortProductsButton = By.xpath("//select[@data-test='product_sort_container']");
    private final By firstInventoryItem = By.xpath("//div[@class='inventory_item']");
    private final By lastInventoryItem = By.xpath("//div[@class='inventory_item'][last()]");

    @Test
    @DisplayName("Sort products and make sure they moved ")
    void productsSortingTest() {
        Select sortSelect = new Select(driver.findElement(sortProductsButton));

        sortSelect.selectByValue("az");
        WebElement firstElemBeforeSort = driver.findElement(firstInventoryItem);

        sortSelect.selectByValue("za");
        WebElement lastElemAfterSort = driver.findElement(lastInventoryItem);

        assertEquals(firstElemBeforeSort, lastElemAfterSort);

        sortSelect = new Select(driver.findElement(sortProductsButton));
        sortSelect.selectByValue("hilo");

        firstElemBeforeSort = driver.findElement(firstInventoryItem);

        sortSelect = new Select(driver.findElement(sortProductsButton));
        sortSelect.selectByValue("lohi");

        lastElemAfterSort = driver.findElement(lastInventoryItem);

        assertEquals(firstElemBeforeSort, lastElemAfterSort);
    }
}
