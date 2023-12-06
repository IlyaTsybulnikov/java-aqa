package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.elements.SiteNavigationSidePanel;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("PIM Elements Test")
public class PIMElementsTest extends BaseTest {

    @Test
    @Description("Make sure that all pim page elements exist")
    @Owner("Ilya Tsybulnikov")
    @DisplayName("Make Sure that PIM Page Elements Exist")
    public void pimElementsExistTest() {
        page(SiteNavigationSidePanel.class)
                .clickOpenPIMPage()
                .verifyTableFilterButtons()
                .verifyTopBarMenuButtons()
                .verifyTableFilterFields()
                .verifyRecordsTableColumnHeaders()
                .verifyTableAddButton();
    }
}