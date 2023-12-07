package aqa.course.pages;

import aqa.course.constants.Constants;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.collections.ExactTexts;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class DashboardPage {

    private final ElementsCollection dashboardElementsTitles = $$x("//div" +
            "[@class='orangehrm-dashboard-widget-name']//p");

    @Step("Get Dashboard page elements")
    public void getGridElementsTitles() {
        dashboardElementsTitles.shouldHave(
                CollectionCondition.size(7),
                ExactTexts.exactTexts(Constants.TEST_DASHBOARD_ELEMENT_NAMES)
        );
    }
}
