package aqa.course.hooks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Configuration {

    protected static WebDriver driver;

    private static final By usernameInput = By.xpath("//input[@id='user-name']");
    private static final By passwordInput = By.xpath("//input[@id='password']");
    private static final By loginButton = By.xpath("//input[@id='login-button']");

    @BeforeAll
    public static void beforeAll() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");

        login();
    }

    @AfterAll
    public static void afterAll() {
        driver.close();
        driver.quit();
    }

    private static void login() {
        driver.findElement(usernameInput).sendKeys("standard_user");
        driver.findElement(passwordInput).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();
    }
}
