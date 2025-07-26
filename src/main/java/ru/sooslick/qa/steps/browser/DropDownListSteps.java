package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class DropDownListSteps {

    @Context
    private ScenarioContext context;

    @Then("Drop-down list {element} has selected text {dataGenerator}")
    public void checkSelectedText(HtmlElement dropDown, String expectedText) {
        StringVerifier verifier = new StringVerifier(expectedText);
        Repeat.untilSuccess(() -> {
            HtmlElement selectedText = HtmlElementHelper.wrapElement(dropDown, Component.DROP_DOWN_SELECTED_TEXT);
            verifier.test(selectedText.getText());
        });
    }
}
