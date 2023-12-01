package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LeaveDetailsPage {

    private final SelenideElement employeeName = $x("//div[div/label[text()='Employee Name']]//p");
    private final SelenideElement leaveDates = $x("//div[div/label[text()='Leave requested for']]//p");

    public Boolean validateLeave(String fromDate, String toDate, String name) {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String leaveEmployeeName = employeeName.should(Condition.exist).shouldBe(Condition.visible).getText();
        String leaveRequestedDates = leaveDates.should(Condition.exist).shouldBe(Condition.visible).getText();

        return leaveEmployeeName.equals(name)
                && leaveRequestedDates.startsWith(fromDate)
                && leaveRequestedDates.endsWith(toDate);
    }
}
