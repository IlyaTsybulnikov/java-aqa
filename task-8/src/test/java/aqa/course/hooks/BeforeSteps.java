package aqa.course.hooks;

import static com.codeborne.selenide.Selenide.open;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aqa.course.databaseConnection.JDBCConnection;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import aqa.course.pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;

public class BeforeSteps {

    @Given("I am logged in to site")
    @Step("Open Login page")
    public void openLoginPage() {
        open("https://www.saucedemo.com/");

        List<String> credentials = getCredentials();

        new LoginPage().logIn(credentials.get(0), credentials.get(1));
    }

    @Given("I am connected to mssql server")
    @Step("Connect to mssql server")
    public void connectToServer() {
        JDBCConnection.connectToDB();
    }

    @Before(value = "@SolenoidTest")
    public static void setupAllureReports() {
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", selenoidOptions);

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    private static List<String> getCredentials() {
        List<String> loginCredentials = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader("src/test/resources/loginCredentials.csv"))
        ) {
            loginCredentials = Arrays.asList(br.readLine().split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return loginCredentials;
    }
}
