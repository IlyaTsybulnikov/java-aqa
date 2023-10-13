package aqa.course.tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import aqa.course.hooks.Configuration;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class WorkWithCartTest extends Configuration {

    private final By firstProductButton = By.xpath("//div[@class='inventory_item'][1]//button");
    private final By secondProductButton = By.xpath("//div[@class='inventory_item'][4]//button");
    private final By thirdProductButton = By.xpath("//div[@class='inventory_item'][5]//button");
    private final By firstProductTitle = By.xpath("//div[@class='inventory_item'][1]/div[2]//a");
    private final By secondProductTitle = By.xpath("//div[@class='inventory_item'][4]/div[2]//a");
    private final By thirdProductTitle = By.xpath("//div[@class='inventory_item'][5]/div[2]//a");
    private final By openCartButton = By.xpath("//a[@class='shopping_cart_link']");
    private final By cartItemTitles = By.xpath("//div[@class='cart_item']//a");
    private final By thirdCartItemRemoveButton = By.xpath("//div[@class='cart_item'][3]//button");

    @Test
    @DisplayName("Checking if adding to cart and removing from cart works well")
    void addAndRemoveFromCartTest() {
        driver.findElement(firstProductButton).click();
        driver.findElement(secondProductButton).click();
        driver.findElement(thirdProductButton).click();

        WebElement firstProductName = driver.findElement(firstProductTitle);
        WebElement secondProductName = driver.findElement(secondProductTitle);
        WebElement thirdProductName = driver.findElement(thirdProductTitle);

        List<String> productNames = Arrays.asList(
                firstProductName.getAttribute("id"),
                secondProductName.getAttribute("id"),
                thirdProductName.getAttribute("id")
        );

        driver.findElement(openCartButton).click();

        List<String> allCartItemNames = driver.findElements(cartItemTitles).stream()
                .map(cartItemTitle -> cartItemTitle.getAttribute("id"))
                .collect(Collectors.toList());

        assertEquals(
                productNames,
                allCartItemNames
        );

        driver.findElement(thirdCartItemRemoveButton).click();

        allCartItemNames = driver.findElements(cartItemTitles).stream()
                .map(cartItemTitle -> cartItemTitle.getAttribute("id"))
                .collect(Collectors.toList());

        assertEquals(2, allCartItemNames.size());
        assertTrue(allCartItemNames.contains(productNames.get(0)));
        assertTrue(allCartItemNames.contains(productNames.get(1)));
        assertFalse(allCartItemNames.contains(productNames.get(2)));
    }
}