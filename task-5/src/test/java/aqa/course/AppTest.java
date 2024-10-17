package aqa.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @AfterEach
    public void closeDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    public void itemsAmountTest() {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_list']/div"));

        assertEquals(6, products.size());
    }

    /*@Test
    public void addAndRemoveFromCartTest() {
        WebElement backpack = driver.findElement(By.xpath("//div[@class='inventory_list']/div[1]"));
        WebElement jacket = driver.findElement(By.xpath("//div[@class='inventory_list']/div[4]"));
        WebElement onesie = driver.findElement(By.xpath("//div[@class='inventory_list']/div[5]"));

        backpack.findElement(By.xpath(".//div[@class='pricebar']/button")).click();
        jacket.findElement(By.xpath(".//div[@class='pricebar']/button")).click();
        onesie.findElement(By.xpath(".//div[@class='pricebar']/button")).click();

        String backpackName = backpack.findElement(
                By.xpath(".//div[@class='inventory_item_name']")).getText();
        String jacketName = jacket.findElement(
                By.xpath(".//div[@class='inventory_item_name']")).getText();
        String onesieName = onesie.findElement(
                By.xpath(".//div[@class='inventory_item_name']")).getText();

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        WebElement firstItem = driver.findElement(
                By.xpath("//div[@class='cart_list']/div[@class='cart_item'][1]"));
        WebElement secondItem = driver.findElement(
                By.xpath("//div[@class='cart_list']/div[@class='cart_item'][2]"));
        WebElement thirdItem = driver.findElement(
                By.xpath("//div[@class='cart_list']/div[@class='cart_item'][3]"));

        String firstItemName = firstItem.findElement(
                By.xpath(".//div[@class='inventory_item_name']")).getText();
        String secondItemName = secondItem.findElement(
                By.xpath(".//div[@class='inventory_item_name']")).getText();
        String thirdItemName = thirdItem.findElement(
                By.xpath(".//div[@class='inventory_item_name']")).getText();

        assertEquals(backpackName, firstItemName);
        assertEquals(jacketName, secondItemName);
        assertEquals(onesieName, thirdItemName);

        thirdItem.findElement(By.xpath(".//div[@class='item_pricebar']/button")).click();

        WebElement lastCartItem = driver.findElement(
                By.xpath("//div[@class='cart_list']/div[@class='cart_item'][last()]"));

        assertNotEquals(thirdItem, lastCartItem);
        assertEquals(secondItem, lastCartItem);
    }

    @Test
    public void productsSortingTest() {
        Select sortSelect = new Select(driver.findElement(
                By.xpath("//select[@data-test='product_sort_container']")));

        sortSelect.selectByValue("az");
        WebElement firstElemBeforeSort = driver.findElement(By.xpath("//div[@class='inventory_list']" +
                "/div[@class='inventory_item']"));

        sortSelect.selectByValue("za");
        WebElement lastElemAfterSort = driver.findElement(By.xpath("//div[@class='inventory_list']" +
                "/div[@class='inventory_item'][last()]"));

        assertEquals(firstElemBeforeSort, lastElemAfterSort);

        sortSelect = new Select(driver.findElement(
                By.xpath("//select[@data-test='product_sort_container']")));
        sortSelect.selectByValue("hilo");

        firstElemBeforeSort = driver.findElement(By.xpath("//div[@class='inventory_list']" +
                "/div[@class='inventory_item']"));

        sortSelect = new Select(driver.findElement(
                By.xpath("//select[@data-test='product_sort_container']")));
        sortSelect.selectByValue("lohi");

        lastElemAfterSort = driver.findElement(By.xpath("//div[@class='inventory_list']" +
                "/div[@class='inventory_item'][last()]"));

        assertEquals(firstElemBeforeSort, lastElemAfterSort);
    }*/
}
