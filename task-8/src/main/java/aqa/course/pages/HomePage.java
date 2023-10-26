package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import aqa.course.elements.PrimaryHeader;
import io.qameta.allure.Step;

public class HomePage {

    private final ElementsCollection inventoryListProducts = $$x("//div[@class='inventory_item']");
    private final SelenideElement sortProductsButton = $x("//select[@data-test='product_sort_container']");


    private final PrimaryHeader primaryHeader = new PrimaryHeader();

    @Step("Get all products")
    public ElementsCollection getAllProducts() {
        return inventoryListProducts;
    }

    @Step("Get first product")
    public SelenideElement getFirstProduct() {
        return inventoryListProducts.first();
    }

    @Step("Get last product")
    public SelenideElement getLastProduct() {
        return inventoryListProducts.last();
    }

    @Step("Get product title")
    public SelenideElement getProductName(SelenideElement product) {
        return product.$x("./div[2]//a");
    }

    @Step("Get sort options menu")
    public SelenideElement getSortSelect() {
        return sortProductsButton;
    }

    @Step("Click 'Add to cart' button")
    public void clickAddToCartButton(SelenideElement product) {
        product.$x(".//button").click();
    }

    @Step("Click cart button")
    public CartPage clickCartButton() {
        return this.primaryHeader.clickGoToCart();
    }
}
