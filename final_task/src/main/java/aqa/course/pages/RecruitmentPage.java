package aqa.course.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class RecruitmentPage {

    private final SelenideElement addCandidateButton = $x("//button[text()=' Add ']");
    private final SelenideElement candidateNameFilter = $x("(//input)[2]");

    public AddCandidatePage clickAddCandidateButton() {
        addCandidateButton.click();

        return page(AddCandidatePage.class);
    }

    public SelenideElement getCandidateByName(String name) {

        return $x("//div[text() = '" + name + "']")
                .should(Condition.exist)
                .shouldBe(Condition.visible);
    }

    public RecruitmentPage filterCandidatesList(String candidateName) {
        candidateNameFilter.setValue(candidateName);

        return this;
    }
}