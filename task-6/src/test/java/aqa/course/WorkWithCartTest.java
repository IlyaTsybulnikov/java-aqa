package aqa.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import aqa.course.pages.CartPage;
import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;

class WorkWithCartTest {

    private static WebDriver driver;

    private static final String username = "standard_user";
    private static final String password = "secret_sauce";


    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(username, password);
    }

    @AfterAll
    public static void closeDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    @DisplayName("Checking if adding to cart and removing from cart works well")
    void addAndRemoveFromCartTest() {
        HomePage homePage = new HomePage(driver);

        homePage.addProductsToCart();
        List<String> productNames = homePage.getProductNames();

        CartPage cartPage = homePage.clickCartButton();
        List<String> cartItemNames = cartPage.getCartItemNames();

        cartPage.clickRemoveLastCartItem();
        String lastCartItemName = cartPage.getLastCartItemName();

        assertEquals(productNames, cartItemNames);
        assertNotEquals(productNames.get(2), lastCartItemName);
        assertEquals(productNames.get(1), lastCartItemName);
    }
}
