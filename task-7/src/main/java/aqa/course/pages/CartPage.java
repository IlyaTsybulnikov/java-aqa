package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CartPage {

    private final ElementsCollection cartItemNames = $$x("//div[@class='cart_item']//a");
    private final SelenideElement thirdCartItemRemoveButton = $x("//div[@class='cart_item'][3]//button");

    public ElementsCollection getCartItemNames() {
        return cartItemNames;
    }

    public void clickRemoveThirdCartItem() {
        thirdCartItemRemoveButton.click();
    }
}
