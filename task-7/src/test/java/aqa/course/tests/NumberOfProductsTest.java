package aqa.course.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.CollectionCondition;

import aqa.course.hooks.Configuration;

class NumberOfProductsTest extends Configuration {

    @Test
    @DisplayName("Check the number of products")
    void itemsAmountTest() {
        homePage.getAllProducts()
                .shouldHave(CollectionCondition.size(6));
    }
}
