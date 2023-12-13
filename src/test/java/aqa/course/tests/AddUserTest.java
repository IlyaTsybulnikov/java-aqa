package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.constants.Constants;
import aqa.course.elements.SiteNavigationSidePanel;
import aqa.course.pages.AdminPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Add User Test")
public class AddUserTest extends BaseTest {

    @Test
    @Description("Create user and validate his fields")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Create User")
    public void createUserTest() {
        String uniqueUsername = Constants.TEST_USERNAME + System.currentTimeMillis();

        String newEmployeeName = page(SiteNavigationSidePanel.class)
                .clickOpenAdminPage()
                .clickAddButton()
                .enterUserRole(Constants.USER_ROLE_ADMIN)
                .enterStatus(Constants.USER_STATUS_ENABLED)
                .enterUsername(uniqueUsername)
                .enterPassword(Constants.TEST_PASSWORD)
                .confirmPassword(Constants.TEST_PASSWORD)
                .enterEmployeeName(currentUsername);

        Boolean isUserInfoValid = page(AdminPage.class)
                .clickSaveUser()
                .filterUserList(uniqueUsername)
                .openEditNewUserPage(uniqueUsername)
                .checkIfUserInfoIsValid(uniqueUsername, newEmployeeName);

        Assertions.assertTrue(isUserInfoValid);
    }
}