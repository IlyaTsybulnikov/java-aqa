package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class RecruitmentPage {

    private final SelenideElement addCandidateButton = $x("//button[text()=' Add ']");
    private final SelenideElement candidateNameFilter = $x("(//input)[2]");

    @Step("Click add candidate button")
    public AddCandidatePage clickAddCandidateButton() {
        addCandidateButton.shouldBe(Condition.enabled).click();

        return page(AddCandidatePage.class);
    }

    @Step("Get candidate by '{0}' name")
    public SelenideElement getCandidateByName(String name) {

        return $x("//div[text() = '" + name + "']").shouldBe(Condition.visible);
    }

    @Step("Filter candidates by '{0}' name")
    public RecruitmentPage filterCandidatesList(String candidateName) {
        candidateNameFilter.shouldBe(Condition.editable).setValue(candidateName);

        return this;
    }
}