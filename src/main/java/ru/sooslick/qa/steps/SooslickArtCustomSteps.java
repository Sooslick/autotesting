package ru.sooslick.qa.steps;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.ItemListHelper;
import ru.sooslick.qa.core.repeaters.Repeat;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.element.HtmlElement;
import ru.sooslick.qa.pagemodel.precondition.ReadProjectsJsonPrecondition;

import java.util.Collection;
import java.util.stream.Collectors;

public class SooslickArtCustomSteps {

    @Context
    ScenarioContext context;

    @Then("List {element} has description lines, corresponding to list variable {listVariable}")
    public void verifyShowcaseDesc(HtmlElement list, Collection<?> projects) {
        var mappedProjects = projects.stream()
                .peek(p -> Assertions.assertTrue(p instanceof ReadProjectsJsonPrecondition.SooslickArtProject))
                .map(p -> (ReadProjectsJsonPrecondition.SooslickArtProject) p)
                .collect(Collectors.toList());

        Repeat.untilSuccess(() -> {
            var items = ItemListHelper.getListItems(list);
            items.forEach(i -> {
                var id = i.getChildElementByName("Project Link").getAttribute("id");
                var prj = mappedProjects.stream()
                        .filter(p -> p.getId().equals(id))
                        .findAny()
                        .orElseThrow(() -> new AssertionError("Unknown project id: " + id));
                var expectedTexts = prj.getDetails().stream()
                        .map(t -> t.replaceAll("<[^>]*>", ""))
                        .collect(Collectors.toList());
                var detailsList = i.getChildElementByName("Project descriptions List");
                var actualTexts = ItemListHelper.getListItems(detailsList)
                        .stream()
                        .map(HtmlElement::getText)
                        .collect(Collectors.toList());
                Assertions.assertIterableEquals(expectedTexts, actualTexts);
            });
        });
    }
}
