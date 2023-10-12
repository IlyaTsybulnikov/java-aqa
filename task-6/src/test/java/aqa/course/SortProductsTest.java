package aqa.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;

class SortProductsTest {

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
    @DisplayName("Sort products and make sure they moved ")
    void productsSortingTest() {
        HomePage homePage = new HomePage(driver);

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
