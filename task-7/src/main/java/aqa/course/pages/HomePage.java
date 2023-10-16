package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import aqa.course.elements.PrimaryHeader;

public class HomePage {

    private final ElementsCollection inventoryListProducts = $$x("//div[@class='inventory_item']");
    private final SelenideElement sortProductsButton = $x("//select[@data-test='product_sort_container']");


    private final PrimaryHeader primaryHeader;

    public HomePage() {
        this.primaryHeader = new PrimaryHeader();
    }

    public ElementsCollection getAllProducts() {
        return inventoryListProducts;
    }

    public SelenideElement getFirstProducts() {
        return getAllProducts().first();
    }

    public SelenideElement getLastProducts() {
        return getAllProducts().last();
    }

    public SelenideElement getProductName(SelenideElement product) {
        return product.$x("./div[2]//a");
    }

    public SelenideElement getSortSelect() {
        return sortProductsButton;
    }

    public void clickAddToCartButton(SelenideElement product) {
        product.find(By.xpath(".//button")).click();
    }

    public CartPage clickCartButton() {
        return this.primaryHeader.clickGoToCart();
    }
}
