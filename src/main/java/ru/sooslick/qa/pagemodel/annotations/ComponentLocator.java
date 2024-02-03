package ru.sooslick.qa.pagemodel.annotations;

import org.openqa.selenium.support.FindBy;
import ru.sooslick.qa.pagemodel.Component;

public @interface ComponentLocator {
    Component component();
    FindBy findBy();
}
