package aqa.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;

class NumberOfProductsTest {

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
    @DisplayName("Check the number of products")
    void itemsAmountTest() {
        HomePage homePage = new HomePage(driver);

        List<WebElement> products = homePage.getAllProducts();

        assertEquals(6, products.size());
    }
}
