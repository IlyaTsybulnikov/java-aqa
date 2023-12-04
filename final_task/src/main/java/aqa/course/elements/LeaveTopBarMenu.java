package aqa.course.elements;

import aqa.course.pages.AssignLeavePage;
import aqa.course.pages.MyLeavePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class LeaveTopBarMenu {

    private final SelenideElement assignLeaveButton = $x("//a[text()='Assign Leave']");
    private final SelenideElement myLeaveButton = $x("//a[text()='My Leave']");

    public AssignLeavePage clickAssignLeaveButton() {
        assignLeaveButton.click();

        return page(AssignLeavePage.class);
    }

    public MyLeavePage clickMyLeaveButton() {
        myLeaveButton.click();

        return page(MyLeavePage.class);
    }
}