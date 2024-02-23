package ru.sooslick.qa.core.page;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.helper.ActionsHelper;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
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

    public HtmlElementBuilder(Field field) {
        this.type = field.getType();
        fromAnnotations(field);
    }

    public HtmlElementBuilder(Class<? extends HtmlElement> type) {
        this.type = type;
    }

    public HtmlElementBuilder fromAnnotations(Field source) {
        return this
                .name(PageAnnotationsHelper.getElementName(source))
                .locator(PageAnnotationsHelper.getElementLocator(source))
                .required(PageAnnotationsHelper.getRequired(source))
                .actions(PageAnnotationsHelper.getActions(source))
                .components(PageAnnotationsHelper.getComponentLocators(source));
    }

    public HtmlElementBuilder actions(Collection<Action> actions) {
        actions.forEach(this::action);
        return this;
    }

    public HtmlElementBuilder action(Action action) {
        actions.put(action.type(), ActionsHelper.getPerformer(action.performer()));
        return this;
    }

    public HtmlElementBuilder components(Collection<ComponentLocator> components) {
        components.forEach(this::component);
        return this;
    }

    public HtmlElementBuilder component(ComponentLocator component) {
        componentLocators.put(component.component(), PageAnnotationsHelper.createLocator(component.locator()));
        componentTypes.put(component.component(), component.type());
        return this;
    }

    public HtmlElement build() throws ReflectiveOperationException {
        HtmlElement element = (HtmlElement) type.getDeclaredConstructor().newInstance();

        set(element, "driver", webDriver);
        set(element, "parent", parent);
        set(element, "name", name);
        set(element, "locator", locator);
        set(element, "required", required);
        set(element, "actions", actions);
        set(element, "componentLocators", componentLocators);
        set(element, "componentTypes", componentTypes);
        return element;
    }

    private void set(HtmlElement object, String property, Object value) throws ReflectiveOperationException {
        Field f = HtmlElement.class.getDeclaredField(property);
        f.setAccessible(true);
        f.set(object, value);
    }
}
