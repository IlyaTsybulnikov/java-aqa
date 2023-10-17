package aqa.course.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import aqa.course.hooks.Configuration;

class SortProductsTest extends Configuration {

    @Test
    @DisplayName("Sort products and make sure they moved ")
    void productsSortingTest() {
        homePage.getSortSelect().selectOptionByValue("az");
        String firstElemBeforeSort = homePage.getProductName(homePage.getFirstProduct()).getText();

        homePage.getSortSelect().selectOptionByValue("za");
        homePage.getProductName(homePage.getLastProduct())
                .shouldHave(Condition.text(firstElemBeforeSort));

        homePage.getSortSelect().selectOptionByValue("hilo");
        firstElemBeforeSort = homePage.getProductName(homePage.getFirstProduct()).getText();

        homePage.getSortSelect().selectOptionByValue("lohi");
        homePage.getProductName(homePage.getLastProduct())
                .shouldHave(Condition.text(firstElemBeforeSort));
    }
}
