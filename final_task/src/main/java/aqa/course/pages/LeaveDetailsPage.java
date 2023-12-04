package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class LeaveDetailsPage {

    private final SelenideElement employeeName = $x("(//p)[2]");
    private final SelenideElement leaveDates = $x("(//p)[3]");

    public Boolean validateLeave(String fromDate, String toDate, String name) {
        List<String> nameParts = Arrays.asList(name.split(" "));

        String leaveEmployeeName = employeeName
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .should(Condition.appear)
                .shouldNotHave(Condition.exactText(""))
                .getText();

        String leaveRequestedDates = leaveDates
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .should(Condition.appear)
                .shouldNotHave(Condition.exactText(""))
                .getText();

        List<String> leaveEmployeeNameParts = Arrays.asList(leaveEmployeeName.split(" "));

        return nameParts.stream().allMatch(leaveEmployeeNameParts::contains)
                && leaveRequestedDates.startsWith(fromDate)
                && leaveRequestedDates.endsWith(toDate);
    }
}
