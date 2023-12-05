package aqa.course.pages;

import aqa.course.elements.LeaveTopBarMenu;
import io.qameta.allure.Step;

public class LeavePage {

    private final LeaveTopBarMenu leaveTopBarMenu = new LeaveTopBarMenu();

    @Step("Click Assign Leave button")
    public AssignLeavePage clickAssignLeaveButton() {
        return leaveTopBarMenu.clickAssignLeaveButton();
    }

    @Step("Click My Leave button")
    public MyLeavePage clickMyLeaveButton() {
        return leaveTopBarMenu.clickMyLeaveButton();
    }
}