package aqa.course.hooks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aqa.course.pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import aqa.course.pages.LoginPage;

public class Configuration {

    protected static HomePage homePage;
    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");

        List<String> credentials = getCredentials();

        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.logIn(credentials.get(0), credentials.get(1));
    }

    @AfterAll
    public static void afterAll() {
        driver.close();
        driver.quit();
    }

    private static List<String> getCredentials() {
        List<String> loginCredentials = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader("src/test/resources/loginCredentials.csv"))
        ) {
            String line = br.readLine();
            loginCredentials = Arrays.asList(line.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return loginCredentials;
    }
}