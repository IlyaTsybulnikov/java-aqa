package aqa.course.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;

public class LoginTest {

    public WebDriver driver;

    @BeforeEach
    public void driverSetUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void closeDriver() {
        driver.close();
        driver.quit();
    }


    @ParameterizedTest(name = "Logging in as {0}")
    @CsvFileSource(resources = "/loginCredentials.csv")
    @DisplayName("Checking login for different users")
    void SuccessfulLoginTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.logIn(username, password);

        assertFalse(homePage.getAllProducts().isEmpty());
    }
}
