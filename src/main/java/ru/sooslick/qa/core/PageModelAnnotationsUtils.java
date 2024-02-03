package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.annotations.ElementName;
import ru.sooslick.qa.pagemodel.annotations.Required;

import java.lang.reflect.Field;
import java.util.Optional;

@UtilityClass
public class PageModelAnnotationsUtils {

    public String getElementName(Field field) {
        return Optional.ofNullable(field.getAnnotation(ElementName.class))
                .map(ElementName::value)
                .orElse(field.getName());
    }

    public By getElementLocator(Field field) {
        return Optional.ofNullable(field.getAnnotation(FindBy.class))
                .map(PageModelAnnotationsUtils::createLocator)
                .orElse(By.id(field.getName()));
    }

    public boolean getRequired(Field field) {
        return field.getAnnotation(Required.class) != null;
    }

    private By createLocator(FindBy findBy) {
        return new FindBy.FindByBuilder()
                .buildIt(findBy, null);
    }
}
