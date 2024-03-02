package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.AssertionFailureBuilder;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.ItemListHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.List;

public class GridSteps {

    @Context
    private ScenarioContext context;

    @Then("List {element} items are in lines of {int} elements per line")
    public void checkListItemsRows(HtmlElement listElement, int itemsPerLine) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            if (listItems.isEmpty())
                return;
            int savedY = listItems.get(0).getLocation().getY();
            int currentRow = 1;
            int currentItem = 0;
            for (HtmlElement li : listItems) {
                currentItem++;
                int currentY = li.getLocation().getY();
                // if we expect more items in row, then we assert that every item in row have the same Y
                if (currentItem <= itemsPerLine) {
                    if (savedY != currentY)
                        AssertionFailureBuilder.assertionFailure()
                                .message("List structure mismatch at row " + currentRow + ", comparing element #" + currentItem + " Y-coordinate with row baseline")
                                .expected(savedY)
                                .actual(currentY)
                                .buildAndThrow();
                }
                // otherwise, we assert that next row has greater Y and save it
                else {
                    currentItem = 1;
                    currentRow++;
                    if (currentY <= savedY)
                        AssertionFailureBuilder.assertionFailure()
                                .message("List structure mismatch at row " + currentRow + ", expecting element #" + currentItem + " will have greater Y-coordinate")
                                .expected(savedY)
                                .actual(currentY)
                                .buildAndThrow();
                    savedY = currentY;
                }
            }
        });
    }
}
