package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import aqa.course.elements.PrimaryHeader;

public class HomePage {

    private final ElementsCollection inventoryListProducts = $$x("//div[@class='inventory_item']");
    private final SelenideElement sortProductsButton = $x("//select[@data-test='product_sort_container']");


    private final PrimaryHeader primaryHeader = new PrimaryHeader();

    public ElementsCollection getAllProducts() {
        return inventoryListProducts;
    }

    public SelenideElement getFirstProduct() {
        return inventoryListProducts.first();
    }

    public SelenideElement getLastProduct() {
        return inventoryListProducts.last();
    }

    public SelenideElement getProductName(SelenideElement product) {
        return product.$x("./div[2]//a");
    }

    public SelenideElement getSortSelect() {
        return sortProductsButton;
    }

    public void clickAddToCartButton(SelenideElement product) {
        product.$x(".//button").click();
    }

    public CartPage clickCartButton() {
        return this.primaryHeader.clickGoToCart();
    }
}
