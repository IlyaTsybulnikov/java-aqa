package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LeaveDetailsPage {

    private final SelenideElement employeeName = $x("(//p)[2]");
    private final SelenideElement leaveDates = $x("(//p)[3]");

    @Step("Validate leave details")
    public void validateLeave(String fromDate, String toDate, String name) {
        employeeName.shouldHave(Condition.exactText(name));

        leaveDates
                .shouldHave(Condition.partialText(fromDate))
                .shouldHave(Condition.partialText(toDate));
    }
}