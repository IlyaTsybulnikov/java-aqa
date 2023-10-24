package aqa.course.hooks;

import static com.codeborne.selenide.Selenide.open;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aqa.course.pages.LoginPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.qameta.allure.selenide.AllureSelenide;

public class BeforeSteps {

    @Given("I am logged in to site")
    public void openLoginPage() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        open("https://www.saucedemo.com/");

        List<String> credentials = getCredentials();

        new LoginPage().logIn(credentials.get(0), credentials.get(1));
    }

    @BeforeAll
    static void setupAllureReports() {
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
