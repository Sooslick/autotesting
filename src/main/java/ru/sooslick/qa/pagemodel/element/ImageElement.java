package ru.sooslick.qa.pagemodel.element;

import ru.sooslick.qa.core.helper.WebDriverHelper;

public class ImageElement extends HtmlElement {

    public boolean isValid() {
        refreshIfStale();
        Object result = WebDriverHelper.runJs(driver,
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                cachedElement);
        return (result instanceof Boolean && (boolean) result);
    }
}
