package ru.sooslick.qa.pagemodel.actions;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.sooslick.qa.pagemodel.element.HtmlElement;

public class GetTextWithPseudoElementsAction implements ActionPerformer<String> {

    @Override
    public String perform(HtmlElement element) {
        WebDriver driver = element.getWrappedDriver();
        if (driver instanceof JavascriptExecutor jsExecutor) {
            element.isDisplayed();  // todo refreshIfStale is private but I need fresh cachedElement object
            String before = extractText(jsExecutor,
                    "return window.getComputedStyle(arguments[0], '::before').content;",
                    element.getCachedElement());
            String after = extractText(jsExecutor,
                    "return window.getComputedStyle(arguments[0], '::after').content;",
                    element.getCachedElement());
            return (before + " " + element.getText() + " " + after).trim();
        }
        throw new UnsupportedOperationException("Can't extract text from ::before and ::after pseudo-elements");
    }

    private String extractText(JavascriptExecutor jsExecutor, String cmd, Object... args) {
        String commandResult = (String) jsExecutor.executeScript(cmd, args);
        if (StringUtils.isQuoted(commandResult))
            return commandResult.substring(1, commandResult.length() - 1);
        return "";
    }
}
