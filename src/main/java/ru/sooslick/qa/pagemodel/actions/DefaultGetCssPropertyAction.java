package ru.sooslick.qa.pagemodel.actions;

import org.junit.platform.commons.util.StringUtils;
import ru.sooslick.qa.core.exception.ElementActionException;
import ru.sooslick.qa.pagemodel.HtmlElement;

public class DefaultGetCssPropertyAction implements ActionPerformer<String> {
    String cssProperty;

    @Override
    public String perform(HtmlElement element) {
        return element.getCssValue(cssProperty);
    }

    @Override
    public ActionPerformer<String> withParameters(String... parameters) {
        if (parameters.length == 0 || StringUtils.isBlank(parameters[0]))
            throw new ElementActionException("You must specify css-property in order to get property value");
        this.cssProperty = parameters[0].trim();
        return this;
    }
}
