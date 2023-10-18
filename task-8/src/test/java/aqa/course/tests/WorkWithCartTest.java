package aqa.course.tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/workwithcart.feature"},
        glue = {"aqa.course.steps", "aqa.course.hooks"})
public class WorkWithCartTest {
}
