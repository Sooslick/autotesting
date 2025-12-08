package ru.sooslick.qa.steps.browser;

import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AssertionFailureBuilder;
import org.opentest4j.AssertionFailedError;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.ExceptionsHelper;
import ru.sooslick.qa.core.helper.ItemListHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class GridSteps {

    @Context
    private ScenarioContext context;

    @Then("List {element} items are in lines of {int} elements per line")
    public void checkListItemsRows(HtmlElement listElement, int itemsPerLine) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            if (listItems.isEmpty()) {
                log.warn("checkListItemsRow check: list {} has no items, assuming check is successfully completed", listElement.getName());
                return;
            }
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

    @Then("List {element} items are arranged in {int} columns of equal width")
    public void checkListItemsEqualWidthColumns(HtmlElement listElement, int itemsPerLine) {
        Repeat.untilSuccess(() -> {
            List<HtmlElement> listItems = ItemListHelper.getListItems(listElement);
            if (listItems.isEmpty()) {
                log.warn("checkListItemsRow check: list {} has no items, assuming check is successfully completed", listElement.getName());
                return;
            }

            List<Throwable> assertionErrors = new LinkedList<>();
            int etalonWidth = listItems.get(0).getRect().width;
            int[] savedXPositions = new int[itemsPerLine];
            int index = 0;
            for (HtmlElement li : listItems) {
                // Compare width
                if (li.getRect().width != etalonWidth)
                    assertionErrors.add(buildAssertionFailure(etalonWidth, li.getRect().width, "List item #" + (index + 1) + " has different width"));
                int currentX = li.getLocation().getX();
                if (index < itemsPerLine)
                    savedXPositions[index] = currentX;
                else if (savedXPositions[index % itemsPerLine] != currentX)
                    assertionErrors.add(buildAssertionFailure(savedXPositions[index % itemsPerLine], currentX, "List item #" + (index + 1) + " is misaligned relative to column X coordinate"));
                index++;
            }
            ExceptionsHelper.convertAndThrowExceptionList(assertionErrors, "Items are not arranged in proper way!");
        });
    }

    private AssertionFailedError buildAssertionFailure(Object expected, Object actual, String message) {
        return AssertionFailureBuilder.assertionFailure()
                .expected(expected)
                .actual(actual)
                .message(message)
                .build();
    }
}
