package aqa.course.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class DashboardPage {

    private final ElementsCollection dashboardElementsTitles = $$x("//div" +
            "[@class='orangehrm-dashboard-widget-name']//p");

    @Step("Check if grid elements are valid")
    public Boolean validateGridElementsTitles() {
        dashboardElementsTitles.shouldHave(CollectionCondition.size(7));

        return dashboardElementsTitles.size() == 7;
    }
}
