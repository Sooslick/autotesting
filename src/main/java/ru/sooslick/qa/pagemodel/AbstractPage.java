package ru.sooslick.qa.pagemodel;

import ru.sooslick.qa.pagemodel.annotations.PageName;

import java.util.Optional;

abstract public class AbstractPage extends HtmlElement implements Page {

    @Override
    public String getName() {
        Class<? extends Page> pageClass = this.getClass();
        return Optional.ofNullable(pageClass.getAnnotation(PageName.class))
                .map(PageName::value)
                .orElse(pageClass.getSimpleName());
    }
}
