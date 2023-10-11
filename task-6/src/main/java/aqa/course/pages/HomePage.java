package aqa.course.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aqa.course.elements.PrimaryHeader;

public class HomePage {

    private final WebDriver driver;
    private final PrimaryHeader primaryHeader;

    @FindBy(xpath = "//div[@class='inventory_list']/div[@class='inventory_item']")
    private List<WebElement> inventoryListProducts;
    @FindBy(xpath = "//div[@class='inventory_list']/div[@class='inventory_item'][last()]")
    private WebElement lastInventoryListProduct;
    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    private WebElement sortSelect;

    @FindBy(xpath = "//div[@class='inventory_list']/div[1]//div[@class='pricebar']/button")
    private WebElement firstProductButton;
    @FindBy(xpath = "//div[@class='inventory_list']/div[4]//div[@class='pricebar']/button")
    private WebElement secondProductButton;
    @FindBy(xpath = "//div[@class='inventory_list']/div[5]//div[@class='pricebar']/button")
    private WebElement thirdProductButton;

    @FindBy(xpath = "//div[@class='inventory_list']/div[1]//div[@class='inventory_item_name']")
    private WebElement firstProductName;
    @FindBy(xpath = "//div[@class='inventory_list']/div[4]//div[@class='inventory_item_name']")
    private WebElement secondProductName;
    @FindBy(xpath = "//div[@class='inventory_list']/div[5]//div[@class='inventory_item_name']")
    private WebElement thirdProductName;

    public HomePage(WebDriver driver) {
        this.driver = driver;
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
                firstProductName.getText(),
                secondProductName.getText(),
                thirdProductName.getText()
        );
    }

    public WebElement getSortSelect() {
        return sortSelect;
    }

    public CartPage clickCartButton() {
        return primaryHeader.clickGoToCart();
    }
}
