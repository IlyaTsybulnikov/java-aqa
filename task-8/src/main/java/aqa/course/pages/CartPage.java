package aqa.course.pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class CartPage {

    private final ElementsCollection cartItemNames = $$x("//div[@class='cart_item']//a");
    private final SelenideElement thirdCartItemRemoveButton = $x("//div[@class='cart_item'][3]//button");

    @Step("Get Cart Item Names")
    public ElementsCollection getCartItemNames() {
        return cartItemNames;
    }

    @Step("Click 'Remove' button on the third item")
    public void clickRemoveThirdCartItem() {
        thirdCartItemRemoveButton.click();
    }
}
