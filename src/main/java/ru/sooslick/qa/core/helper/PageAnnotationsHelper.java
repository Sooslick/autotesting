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

@UtilityClass
public class PageAnnotationsHelper {

    public String getElementName(Field field) {
        return Optional.ofNullable(field.getAnnotation(ElementName.class))
                .map(ElementName::value)
                .map(String::trim)
                .orElse(field.getName());
    }

    public By getElementLocator(Field field) {
        return Optional.ofNullable(field.getAnnotation(FindBy.class))
                .map(PageAnnotationsHelper::createLocator)
                .orElse(By.id(field.getName()));
    }

    public boolean getRequired(Field field) {
        return field.getAnnotation(Required.class) != null;
    }

    public Collection<Action> getActions(Field field) {
        if (field.getAnnotation(Actions.class) != null)
            return Arrays.asList(field.getAnnotation(Actions.class).value());
        else if (field.getAnnotation(Action.class) != null)
            return Collections.singleton(field.getAnnotation(Action.class));
        else
            return Collections.emptyList();
    }

    public Collection<ComponentLocator> getComponentLocators(Field field) {
        if (field.getAnnotation(ComponentLocators.class) != null)
            return Arrays.asList(field.getAnnotation(ComponentLocators.class).value());
        else if (field.getAnnotation(ComponentLocator.class) != null)
            return Collections.singleton(field.getAnnotation(ComponentLocator.class));
        else
            return Collections.emptyList();
    }

    public String getPageName(Class<? extends Page> pageClass) {
        return Optional.ofNullable(pageClass.getAnnotation(PageName.class))
                .map(PageName::value)
                .orElse(pageClass.getSimpleName());
    }

    public By createLocator(FindBy findBy) {
        return new FindBy.FindByBuilder()
                .buildIt(findBy, null);
    }
}
