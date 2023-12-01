package aqa.course.pages;

import aqa.course.elements.LeaveTopBarMenu;

public class LeavePage {

    private final LeaveTopBarMenu leaveTopBarMenu = new LeaveTopBarMenu();

    public AssignLeavePage clickAssignLeaveButton() {
        return this.leaveTopBarMenu.clickAssignLeaveButton();
    }

    public MyLeavePage clickMyLeaveButton() {
        return this.leaveTopBarMenu.clickMyLeaveButton();
    }
}
