package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.CreateUserPage;
import aqa.course.pages.DashboardPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class AddUserTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Create user and validate his fields")
    public void createUserTest() {
        String uniqueUsername = Constants.TEST_USERNAME + System.currentTimeMillis();

        String newEmployeeName = page(DashboardPage.class)
                .goToAdminPage()
                .clickAddButton()
                .enterUserRole(Constants.USER_ROLE_ADMIN)
                .enterStatus(Constants.USER_STATUS_ENABLED)
                .enterUsername(uniqueUsername)
                .enterPassword(Constants.TEST_PASSWORD)
                .confirmPassword(Constants.TEST_PASSWORD)
                .enterEmployeeNameAsCurrentUsername();

        page(CreateUserPage.class)
                .clickSaveUser()
                .filterUserList(uniqueUsername)
                .openEditNewUserPage(uniqueUsername)
                .validateUserInfo(uniqueUsername, newEmployeeName);
    }
}