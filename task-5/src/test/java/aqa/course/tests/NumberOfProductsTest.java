package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import aqa.course.hooks.Configuration;

public class NumberOfProductsTest extends Configuration {

    private static final By inventoryListItem = By.xpath("//div[@class='inventory_list']/div");

    @Test
    @DisplayName("Check the number of products")
    void itemsAmountTest() {
        List<WebElement> products = driver.findElements(inventoryListItem);

        assertEquals(6, products.size());
    }
}
