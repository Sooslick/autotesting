package ru.sooslick.qa.core.page;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.core.helper.ActionsHelper;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.Component;
import ru.sooslick.qa.pagemodel.HtmlElement;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.annotations.Action;

import java.lang.reflect.Field;
import java.util.Arrays;
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
    private Map<Component, By> components = new HashMap<>();

    public HtmlElementBuilder(Field field) {
        this.type = field.getType();
        fromAnnotations(field);
    }

    public HtmlElementBuilder fromAnnotations(Field source) {
        return this
                .name(PageAnnotationsHelper.getElementName(source))
                .locator(PageAnnotationsHelper.getElementLocator(source))
                .required(PageAnnotationsHelper.getRequired(source))
                .actions(PageAnnotationsHelper.getActions(source));
//        components(source);   // todo
    }

    public HtmlElementBuilder actions(Collection<Action> actions) {
        actions.forEach(this::action);
        return this;
    }

    public HtmlElementBuilder action(Action action) {
        actions.put(action.type(), ActionsHelper.getPerformer(action.performer()));
        return this;
    }

    public HtmlElement build() throws ReflectiveOperationException {
        HtmlElement element = (HtmlElement) type.getDeclaredConstructor().newInstance();

        completeActions();

        set(element, "driver", webDriver);
        set(element, "parent", parent);
        set(element, "name", name);
        set(element, "locator", locator);
        set(element, "required", required);
        set(element, "actions", actions);
        // todo components
        return element;
    }

    private void set(HtmlElement object, String property, Object value) throws ReflectiveOperationException {
        Field f = HtmlElement.class.getDeclaredField(property);
        f.setAccessible(true);
        f.set(object, value);
    }

    private void completeActions() {
        Arrays.stream(ActionType.values())
                .forEach(actionType -> actions.computeIfAbsent(actionType,
                        (at) -> ActionsHelper.getPerformer(at.getDefaultPerformer())));
    }
}
