package aqa.course.elements;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;

import aqa.course.pages.CartPage;

public class PrimaryHeader {

    private final SelenideElement openCartButton = $x("//a[@class='shopping_cart_link']");

    public CartPage clickGoToCart() {
        openCartButton.click();

        return page(CartPage.class);
    }
}
