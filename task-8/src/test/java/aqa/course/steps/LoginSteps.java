package aqa.course.steps;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.CollectionCondition;

import aqa.course.pages.HomePage;
import aqa.course.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Feature;

public class LoginSteps {

    private String username;
    private String password;
    private HomePage homePage;

    @Given("^that login page is opened$")
    @Feature("Login to site")
    public void openLoginPage() {
        open("https://www.saucedemo.com/");
    }

    @When("^I enter username as (.*)$")
    @Feature("Login to site")
    public void setUsername(String username) {
        this.username = username;
    }

    @When("^I enter password as (.*)$")
    @Feature("Login to site")
    public void setPassword(String password) {
        this.password = password;
    }

    @When("^I click the \"Login\" button$")
    @Feature("Login to site")
    public void login() {
        this.homePage = new LoginPage().logIn(this.username, this.password);
    }

    @Then("^check if home page is open$")
    @Feature("Login to site")
    public void checkHomePageIsOpen() {
        this.homePage.getAllProducts()
                .shouldHave(CollectionCondition.sizeNotEqual(0));
    }

}