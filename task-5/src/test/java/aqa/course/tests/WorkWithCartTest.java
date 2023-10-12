package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import aqa.course.hooks.Configuration;

public class WorkWithCartTest extends Configuration {

    private final By firstProductButton = By.xpath("//div[@class='inventory_list']/div[1]//button");
    private final By secondProductButton = By.xpath("//div[@class='inventory_list']/div[4]//button");
    private final By thirdProductButton = By.xpath("//div[@class='inventory_list']/div[5]//button");
    private final By firstProductTitle = By.xpath("//div[@class='inventory_list']/div[1]" +
            "//div[@class='inventory_item_name']");
    private final By secondProductTitle = By.xpath("//div[@class='inventory_list']/div[4]" +
            "//div[@class='inventory_item_name']");
    private final By thirdProductTitle = By.xpath("//div[@class='inventory_list']/div[5]" +
            "//div[@class='inventory_item_name']");
    private final By goToCartButton = By.xpath("//a[@class='shopping_cart_link']");
    private final By firstCartItemTitle = By.xpath("//div[@class='cart_item'][1]" +
            "//div[@class='inventory_item_name']");
    private final By secondCartItemTitle = By.xpath("//div[@class='cart_item'][2]" +
            "//div[@class='inventory_item_name']");
    private final By thirdCartItemTitle = By.xpath("//div[@class='cart_item'][3]" +
            "//div[@class='inventory_item_name']");
    private final By thirdCartItemRemoveButton = By.xpath("//div[@class='cart_item'][3]//button");
    private final By lastCartItemTitle = By.xpath("//div[@class='cart_item'][last()]" +
            "//div[@class='inventory_item_name']");

    @Test
    @DisplayName("Checking if adding to cart and removing from cart works well")
    void addAndRemoveFromCartTest() {
        driver.findElement(firstProductButton).click();
        driver.findElement(secondProductButton).click();
        driver.findElement(thirdProductButton).click();

        String firstProductName = driver.findElement(firstProductTitle).getText();
        String secondProductName = driver.findElement(secondProductTitle).getText();
        String thirdProductName = driver.findElement(thirdProductTitle).getText();

        driver.findElement(goToCartButton).click();

        String firstCartItemName = driver.findElement(firstCartItemTitle).getText();
        String secondCartItemName = driver.findElement(secondCartItemTitle).getText();
        String thirdCartItemName = driver.findElement(thirdCartItemTitle).getText();

        driver.findElement(thirdCartItemRemoveButton).click();

        String lastCartItemName = driver.findElement(lastCartItemTitle).getText();

        assertEquals(
                Arrays.asList(firstProductName, secondProductName, thirdProductName),
                Arrays.asList(firstCartItemName, secondCartItemName, thirdCartItemName)
        );
        assertNotEquals(thirdProductName, lastCartItemName);
        assertEquals(secondCartItemName, lastCartItemName);
    }
}
