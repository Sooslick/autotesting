package ru.sooslick.qa.pagemodel.element;

import ru.sooslick.qa.core.helper.WebDriverHelper;

/**
 * Child class of HtmlElement with some functions, related to <img> elements.
 */
public class ImageElement extends HtmlElement {

    /**
     * @return true if <img> element has valid 'src' attribute, and this source is accessible from the page.
     */
    public boolean isValid() {
        refreshIfStale();
        Object result = WebDriverHelper.runJs(driver,
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                cachedElement);
        return (result instanceof Boolean && (boolean) result);
    }
}
