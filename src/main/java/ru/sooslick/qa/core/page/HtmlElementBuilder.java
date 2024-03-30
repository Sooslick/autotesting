package ru.sooslick.qa.core.page;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.helper.ActionsHelper;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.core.helper.ReflectionsHelper;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.components.Component;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder class for HtmlElements.
 */
@Accessors(fluent = true)
public class HtmlElementBuilder {
    private final Class<?> type;

    @Setter
    private WebDriver webDriver;
    @Setter
    private SearchContext parent;
    @Setter
    private String name;
    @Setter
    private By locator;
    @Setter
    private boolean required;
    private Map<ActionType, ActionPerformer<?>> actions = new HashMap<>();
    private Map<Component, By> componentLocators = new HashMap<>();
    private Map<Component, Class<? extends HtmlElement>> componentTypes = new HashMap<>();

    /**
     * Constructor with field's annotations preset.
     *
     * @param field field with page model annotations.
     */
    public HtmlElementBuilder(Field field) {
        this.type = field.getType();
        fromAnnotations(field);
    }

    /**
     * Constructor with no preset data.
     *
     * @param type java type of desired HtmlElement.
     */
    public HtmlElementBuilder(Class<? extends HtmlElement> type) {
        this.type = type;
    }

    /**
     * Stores HtmlElement data from annotations' values.
     *
     * @param source field with page model annotations.
     * @return this.
     */
    public HtmlElementBuilder fromAnnotations(Field source) {
        return this
                .name(PageAnnotationsHelper.getElementName(source))
                .locator(PageAnnotationsHelper.getElementLocator(source))
                .required(PageAnnotationsHelper.getRequired(source))
                .actions(PageAnnotationsHelper.getActions(source))
                .components(PageAnnotationsHelper.getComponentLocators(source));
    }

    /**
     * Stores actions list from annotations.
     *
     * @param actions annotations.
     * @return this.
     */
    public HtmlElementBuilder actions(Collection<Action> actions) {
        actions.forEach(this::action);
        return this;
    }

    /**
     * Stores action from annotation.
     *
     * @param action annotation.
     * @return this.
     */
    public HtmlElementBuilder action(Action action) {
        actions.put(action.type(), ActionsHelper.getPerformer(action.performer()));
        return this;
    }

    /**
     * Stores components list from annotations.
     *
     * @param components annotations.
     * @return this.
     */
    public HtmlElementBuilder components(Collection<ComponentLocator> components) {
        components.forEach(this::component);
        return this;
    }

    /**
     * Stores component from annotation.
     *
     * @param component annotation.
     * @return this.
     */
    public HtmlElementBuilder component(ComponentLocator component) {
        componentLocators.put(component.component(), PageAnnotationsHelper.createLocator(component.locator()));
        componentTypes.put(component.component(), component.type());
        return this;
    }

    /**
     * @return built HtmlElement with stored data.
     * @throws ReflectiveOperationException when error occurred while attempting fill HtmlElement with data
     */
    public HtmlElement build() throws ReflectiveOperationException {
        HtmlElement element = (HtmlElement) type.getDeclaredConstructor().newInstance();

        ReflectionsHelper.reflectiveSet(element, "driver", webDriver);
        ReflectionsHelper.reflectiveSet(element, "parent", parent);
        ReflectionsHelper.reflectiveSet(element, "name", name);
        ReflectionsHelper.reflectiveSet(element, "locator", locator);
        ReflectionsHelper.reflectiveSet(element, "required", required);
        ReflectionsHelper.reflectiveSet(element, "actions", actions);
        ReflectionsHelper.reflectiveSet(element, "componentLocators", componentLocators);
        ReflectionsHelper.reflectiveSet(element, "componentTypes", componentTypes);
        return element;
    }
}
