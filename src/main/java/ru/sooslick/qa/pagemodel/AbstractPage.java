package ru.sooslick.qa.pagemodel;

import ru.sooslick.qa.core.PageModelAnnotationsUtils;

abstract public class AbstractPage extends HtmlElement implements Page {

    @Override
    public String getName() {
        return PageModelAnnotationsUtils.getPageName(this.getClass());
    }
}
