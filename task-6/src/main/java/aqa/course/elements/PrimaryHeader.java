package aqa.course.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aqa.course.pages.CartPage;

public class PrimaryHeader {

    private final CartPage cartPage;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement openCartButton;

    public PrimaryHeader(WebDriver driver){
        cartPage = new CartPage(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage clickGoToCart() {
        openCartButton.click();

        return cartPage;
    }
}
