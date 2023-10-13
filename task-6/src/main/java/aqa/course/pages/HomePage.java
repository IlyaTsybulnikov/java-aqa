package aqa.course.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aqa.course.elements.PrimaryHeader;

public class HomePage {

    private final PrimaryHeader primaryHeader;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> inventoryListProducts;
    @FindBy(xpath = "//div[@class='inventory_item'][last()]")
    private WebElement lastInventoryListProduct;
    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    private WebElement sortProductsButton;

    @FindBy(xpath = "//div[@class='inventory_item'][1]//button")
    private WebElement firstProductButton;
    @FindBy(xpath = "//div[@class='inventory_item'][4]//button")
    private WebElement secondProductButton;
    @FindBy(xpath = "//div[@class='inventory_item'][5]//button")
    private WebElement thirdProductButton;

    @FindBy(xpath = "//div[@class='inventory_item'][1]/div[2]//a")
    private WebElement firstProductTitle;
    @FindBy(xpath = "//div[@class='inventory_item'][4]/div[2]//a")
    private WebElement secondProductTitle;
    @FindBy(xpath = "//div[@class='inventory_item'][5]/div[2]//a")
    private WebElement thirdProductTitle;

    public HomePage(WebDriver driver) {
        this.primaryHeader = new PrimaryHeader(driver);

        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllProducts() {
        return inventoryListProducts;
    }

    public WebElement getFirstProduct() {
        return inventoryListProducts.get(0);
    }

    public WebElement getLastProduct() {
        return lastInventoryListProduct;
    }

    public void addProductsToCart() {
        firstProductButton.click();
        secondProductButton.click();
        thirdProductButton.click();
    }

    public List<String> getProductNames() {
        return Arrays.asList(
                firstProductTitle.getText(),
                secondProductTitle.getText(),
                thirdProductTitle.getText()
        );
    }

    public WebElement getSortSelect() {
        return sortProductsButton;
    }

    public CartPage clickCartButton() {
        return primaryHeader.clickGoToCart();
    }
}
