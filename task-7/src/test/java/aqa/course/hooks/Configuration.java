package aqa.course.hooks;

import static com.codeborne.selenide.Selenide.open;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;

public class Configuration {

    protected static HomePage homePage;

    @BeforeAll
    public static void beforeAll() {
        open("https://www.saucedemo.com/");

        List<String> credentials = getCredentials();

        homePage = new LoginPage().logIn(credentials.get(0), credentials.get(1));
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
