package aqa.course.elements;

import aqa.course.pages.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrimaryHeader {

    private WebDriver driver;

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
