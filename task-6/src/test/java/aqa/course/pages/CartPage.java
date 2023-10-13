package aqa.course.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    @FindBy(xpath = "//div[@class='cart_item']//a")
    private List<WebElement> cartItemTitles;
    @FindBy(xpath = "//div[@class='cart_item'][3]//button")
    private WebElement thirdCartItemRemoveButton;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getCartItemNames() {
        return cartItemTitles.stream()
                .map(cartItemTitle -> cartItemTitle.getAttribute("id"))
                .collect(Collectors.toList());
    }

    public void clickRemoveThirdCartItem() {
        thirdCartItemRemoveButton.click();
    }
}
