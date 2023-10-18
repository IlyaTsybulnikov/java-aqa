package aqa.course.tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/login.feature",
        glue = "aqa.course.steps")
public class LoginTest {
}
