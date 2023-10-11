package aqa.course.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item'][1]//div[@class='inventory_item_name']")
    private WebElement firstCartItemName;
    @FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item'][2]//div[@class='inventory_item_name']")
    private WebElement secondCartItemName;
    @FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item'][3]//div[@class='inventory_item_name']")
    private WebElement thirdCartItemName;
    @FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item'][last()]//div[@class='inventory_item_name']")
    private WebElement lastCartItemName;
    @FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item'][3]//div[@class='item_pricebar']/button")
    private WebElement lastCartItemRemoveButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getCartItemNames() {
        return Arrays.asList(
                firstCartItemName.getText(),
                secondCartItemName.getText(),
                thirdCartItemName.getText()
        );
    }

    public void clickRemoveLastCartItem() {
        lastCartItemRemoveButton.click();
    }

    public String getLastCartItemName() {
        return lastCartItemName.getText();
    }
}
