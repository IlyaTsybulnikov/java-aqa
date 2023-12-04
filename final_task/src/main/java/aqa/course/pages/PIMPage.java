package aqa.course.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class PIMPage {

    private final ElementsCollection headerButtons = $$x("//nav[@aria-label='Topbar Menu']//li");
    private final ElementsCollection tableFilterButtons = $$x("//div[@class='oxd-table-filter-area']//button");
    private final ElementsCollection tableFilterFields = $$x("//div" +
            "[@class='oxd-grid-item oxd-grid-item--gutters']");
    private final SelenideElement recordsTable = $x("//div[@class='oxd-table orangehrm-employee-list']");
    private final SelenideElement tableFilter = $x("//div[@class='oxd-table-filter']");
    private final SelenideElement tableAddButton = $x("//div[@class='orangehrm-header-container']/button");

    public ElementsCollection getHeaderButtons() {
        return headerButtons;
    }

    public ElementsCollection getTableFilterButtons() {
        return tableFilterButtons;
    }

    public ElementsCollection getTableFilterFields() {
        return tableFilterFields;
    }

    public SelenideElement getRecordsTable() {
        return recordsTable;
    }

    public SelenideElement getTableFilter() {
        return tableFilter;
    }

    public SelenideElement getTableAddButton() {
        return tableAddButton;
    }

    public String getElementName(SelenideElement pageElement) {
        return pageElement.getText();
    }

    public String getElementLabelText(SelenideElement pageElement) {
        return pageElement.$x(".//label").getText();
    }
}