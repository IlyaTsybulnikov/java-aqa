package aqa.course.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class RecruitmentPage {

    private final SelenideElement addCandidateButton = $x("//button[text()=' Add ']");

    public AddCandidatePage clickAddCandidateButton() {
        addCandidateButton.click();

        return page(AddCandidatePage.class);
    }

    public SelenideElement getCandidateByName(String name) {

        return $x("//div[text() = '" + name + "']");
    }
}