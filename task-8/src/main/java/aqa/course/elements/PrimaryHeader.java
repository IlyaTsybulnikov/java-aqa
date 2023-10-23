package aqa.course.elements;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;

import aqa.course.pages.CartPage;
import io.qameta.allure.Step;

public class PrimaryHeader {

    private final SelenideElement openCartButton = $x("//a[@class='shopping_cart_link']");

    @Step("Click the cart icon on header")
    public CartPage clickGoToCart() {
        openCartButton.click();

        return page(CartPage.class);
    }
}
