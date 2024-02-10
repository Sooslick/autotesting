package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.Page;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.annotations.Required;

import java.lang.reflect.Field;
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

    public String getPageName(Class<? extends Page> pageClass) {
        return Optional.ofNullable(pageClass.getAnnotation(PageName.class))
                .map(PageName::value)
                .orElse(pageClass.getSimpleName());
    }

    private By createLocator(FindBy findBy) {
        return new FindBy.FindByBuilder()
                .buildIt(findBy, null);
    }
}
