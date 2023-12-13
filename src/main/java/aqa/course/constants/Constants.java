package aqa.course.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String LOGIN_PAGE_URL = "https://opensource-demo.orangehrmlive.com";
    public static final String LOGIN_USERNAME = "Admin";
    public static final String LOGIN_PASSWORD = "admin123";
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_SIZE_FULL_HD = "1920x1080";
    public static final String USER_ROLE_ADMIN = "Admin";
    public static final String USER_STATUS_ENABLED = "Enabled";
    public static final String TEST_USERNAME = "testtest";
    public static final String TEST_PASSWORD = "testtest123";
    public static final String TEST_FIRST_NAME = "testFN";
    public static final String TEST_MIDDLE_NAME = "testMN";
    public static final String TEST_LAST_NAME = "testLN";
    public static final String TEST_FULL_NAME = TEST_FIRST_NAME + " " + TEST_MIDDLE_NAME + " " + TEST_LAST_NAME;
    public static final String TEST_EMAIL = "testemail@mail.com";
    public static final String TEST_CONTACT_NUMBER = "+123456789";
    public static final String TEST_APPLICATION_DATE = "2020-11-11";
    public static final String TEST_NOTES = "test notes for test candidate";
    public static final String TEST_LEAVE_TYPE = "CAN - Bereavement";
    public static final String TEST_LEAVE_SCHEDULED_TYPE = "Scheduled";
    public static final String TEST_LEAVE_FROM_DATE = "2024-12-01";
    public static final String TEST_LEAVE_TO_DATE = "2024-12-31";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-mm-dd";

    public static final List<String> TEST_PIM_HEADER_BUTTONS_NAMES = Arrays.asList(
            "Configuration",
            "Employee List",
            "Add Employee",
            "Reports"
    );
    public static final List<String> TEST_PIM_TABLE_FILTER_BUTTONS_NAMES = Arrays.asList(
            "Reset",
            "Search"
    );
    public static final List<String> TEST_PIM_TABLE_FILTER_FIELDS_NAMES = Arrays.asList(
            "Employee Name",
            "Employee Id",
            "Employment Status",
            "Include",
            "Supervisor Name",
            "Job Title",
            "Sub Unit"
    );
}