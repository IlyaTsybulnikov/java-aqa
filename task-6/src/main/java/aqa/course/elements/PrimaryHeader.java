package aqa.course.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aqa.course.pages.CartPage;

public class PrimaryHeader {

    private final WebDriver driver;

    @FindBy(id = "shopping_cart_container")
    private WebElement goToCartButton;

    public PrimaryHeader(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage clickGoToCart() {
        goToCartButton.click();

        return new CartPage(driver);
    }
}
