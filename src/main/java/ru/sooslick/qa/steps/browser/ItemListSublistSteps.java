package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.HtmlElementHelper;
import ru.sooslick.qa.core.helper.ItemListHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.List;

public class ItemListSublistSteps {

    @Context
    private ScenarioContext context;

    // todo I don't like sublist idea personally, but I don't have suitable ideas for these steps yet
    //  so i'd implemented steps for Portfolio Details in this TEMP way (also chk SooslickArtCustomSteps)
    @Then("Each sublist {string} in list {element} has the following elements")
    public void checkSublistStricture(String sublistName, HtmlElement listElement, List<String> elementNames) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            listItems.forEach(li -> {
                HtmlElement sublistElement = HtmlElementHelper.findElementByName(li, sublistName);
                List<HtmlElement> sublistItems = ItemListHelper.getListItems(sublistElement);
                sublistItems.forEach(subLi -> elementNames.forEach(name -> {
                    // todo code duplication: find inner element + assert visible
                    HtmlElement targetElement = HtmlElementHelper.findElementByName(subLi, name);
                    Assertions.assertTrue(targetElement.isDisplayed(), "Element '" + targetElement.getName() + "' is not visible");
                }));
            });
        });
    }
}
