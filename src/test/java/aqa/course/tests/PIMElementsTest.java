package aqa.course.tests;

import aqa.course.configuration.BaseTest;
import aqa.course.elements.SiteNavigationSidePanel;
import aqa.course.pages.PIMPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
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
        SoftAssertions softAssertions = new SoftAssertions();
        PIMPage pimPage = page(PIMPage.class);

        page(SiteNavigationSidePanel.class)
                .clickOpenPIMPage();

        softAssertions.assertThat(pimPage.verifyTableFilterButtons().equals(true));
        softAssertions.assertThat(pimPage.verifyTopBarMenuButtons().equals(true));
        softAssertions.assertThat(pimPage.verifyTableFilterFields().equals(true));
        softAssertions.assertThat(pimPage.verifyRecordsTableColumnHeaders().equals(true));
        softAssertions.assertThat(pimPage.verifyTableAddButton().equals(true));

        softAssertions.assertAll();
    }
}