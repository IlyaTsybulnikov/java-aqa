package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.configuration.TestConfiguration;
import aqa.course.constants.Constants;
import aqa.course.pages.CreateUserPage;
import aqa.course.pages.DashboardPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class AddUserTest
        extends BaseTest
        implements TestConfiguration {

    @Test
    @Description("Create user and validate his fields")
    public void createUserTest() {
        String currentUserName = page(CreateUserPage.class).getCurrentUserName();
        String uniqueUsername = Constants.TEST_USERNAME + System.currentTimeMillis();

        Boolean areUserFieldsValid = page(DashboardPage.class)
                .goToAdminPage()
                .clickAddButton()
                .enterUserRole(Constants.USER_ROLE_ADMIN)
                .enterEmployeeName(currentUserName)
                .enterStatus(Constants.USER_STATUS_ENABLED)
                .enterUsername(uniqueUsername)
                .enterPassword(Constants.TEST_PASSWORD)
                .confirmPassword(Constants.TEST_PASSWORD)
                .clickSaveUser()
                .waitForSpinnerToDisappear()
                .filterUserList(uniqueUsername)
                .openEditUserPage(uniqueUsername)
                .validateUserInfo(uniqueUsername);

        Assertions.assertTrue(areUserFieldsValid);

        page(CreateUserPage.class)
                .cancelUserEdit()
                .filterUserList(uniqueUsername)
                .getUserRowByUsername(uniqueUsername)
                .shouldHave(Condition.ownText(uniqueUsername));
    }
}