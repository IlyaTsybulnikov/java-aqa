package aqa.course.tests;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.selenide.AllureSelenide;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        glue = {"aqa.course.steps", "aqa.course.hooks"})
public class RunSwagLabsTests {
    @BeforeClass
    public static void setupAllureReports() {
        Configuration.headless = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}