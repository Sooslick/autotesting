package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.Actions;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocator;
import ru.sooslick.qa.pagemodel.annotations.ComponentLocators;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;
import ru.sooslick.qa.pagemodel.page.Page;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Helper class for page model annotations
 */
@UtilityClass
public class PageAnnotationsHelper {

    /**
     * Returns normalized element name from {@link ElementName} annotation.
     * If annotation is not presented, field's name will be used as element name.
     *
     * @param field annotated field.
     * @return element's name.
     */
    public String getElementName(Field field) {
        return Optional.ofNullable(field.getAnnotation(ElementName.class))
                .map(ElementName::value)
                .map(String::trim)
                .orElse(field.getName());
    }

    /**
     * Returns element's locator from {@link FindBy} annotation.
     * If annotation is not presented, method will create a new locator, looking for element with same id as field's name.
     *
     * @param field annotated field.
     * @return locator for element.
     */
    public By getElementLocator(Field field) {
        return Optional.ofNullable(field.getAnnotation(FindBy.class))
                .map(PageAnnotationsHelper::createLocator)
                .orElse(By.id(field.getName()));
    }

    /**
     * @param field annotated field.
     * @return true if field has {@link Required} annotation.
     */
    public boolean getRequired(Field field) {
        return field.getAnnotation(Required.class) != null;
    }

    /**
     * Converts repeatable {@link Actions} annotation to collection of {@link Action}.
     *
     * @param field annotated field.
     * @return collection of Actions annotations.
     */
    public Collection<Action> getActions(Field field) {
        if (field.getAnnotation(Actions.class) != null)
            return Arrays.asList(field.getAnnotation(Actions.class).value());
        else if (field.getAnnotation(Action.class) != null)
            return Collections.singleton(field.getAnnotation(Action.class));
        else
            return Collections.emptyList();
    }

    /**
     * Converts repeatable {@link ComponentLocators} annotation to collection of {@link ComponentLocator}.
     *
     * @param field annotated field.
     * @return collection of Component Locator annotations.
     */
    public Collection<ComponentLocator> getComponentLocators(Field field) {
        if (field.getAnnotation(ComponentLocators.class) != null)
            return Arrays.asList(field.getAnnotation(ComponentLocators.class).value());
        else if (field.getAnnotation(ComponentLocator.class) != null)
            return Collections.singleton(field.getAnnotation(ComponentLocator.class));
        else
            return Collections.emptyList();
    }

    /**
     * Returns normalized page name from {@link PageName} annotation.
     * If annotation is not presented, class' name will be used as page name.
     *
     * @param pageClass annotated class.
     * @return page name.
     */
    public String getPageName(Class<? extends Page> pageClass) {
        return Optional.ofNullable(pageClass.getAnnotation(PageName.class))
                .map(PageName::value)
                .orElse(pageClass.getSimpleName())
                .trim()
                .toLowerCase();
    }

    /**
     * Returns locator, created from {@link FindBy} annotation using Selenium tools.
     *
     * @param findBy annotation.
     * @return locator, created from annotation.
     */
    public By createLocator(FindBy findBy) {
        return new FindBy.FindByBuilder()
                .buildIt(findBy, null);
    }
}
