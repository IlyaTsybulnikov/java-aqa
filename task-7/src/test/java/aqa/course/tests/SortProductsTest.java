package aqa.course.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import aqa.course.hooks.Configuration;

class SortProductsTest extends Configuration {

    @Test
    @DisplayName("Sort products and make sure they moved ")
    void productsSortingTest() {
        homePage.getSortSelect().selectOptionByValue("az");
        String firstElemBeforeSort = homePage.getProductName(homePage.getFirstProducts()).getText();

        homePage.getSortSelect().selectOptionByValue("za");
        SelenideElement lastElemAfterSort = homePage.getProductName(homePage.getLastProducts());

        lastElemAfterSort.shouldHave(Condition.text(firstElemBeforeSort));

        homePage.getSortSelect().selectOptionByValue("hilo");
        firstElemBeforeSort = homePage.getProductName(homePage.getFirstProducts()).getText();

        homePage.getSortSelect().selectOptionByValue("lohi");
        lastElemAfterSort = homePage.getProductName(homePage.getLastProducts());


        lastElemAfterSort.shouldHave(Condition.text(firstElemBeforeSort));
    }
}
