package ru.sooslick.qa.pagemodel.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Alternative GET_TEXT implementation for elements with text inside ::before and ::after pseudo-elements
 */
public class GetTextWithPseudoElementsAction implements ActionPerformer<String> {

    @Override
    public String perform(WebDriver driver, WebElement element) {
        if (driver instanceof JavascriptExecutor jsExecutor) {
            String before = extractText(jsExecutor,
                    "return window.getComputedStyle(arguments[0], '::before').content;",
                    element);
            String after = extractText(jsExecutor,
                    "return window.getComputedStyle(arguments[0], '::after').content;",
                    element);
            return (before + " " + element.getText() + " " + after).trim();
        }
        throw new UnsupportedOperationException("Can't extract text from ::before and ::after pseudo-elements");
    }

    private String extractText(JavascriptExecutor jsExecutor, String cmd, Object... args) {
        String commandResult = (String) jsExecutor.executeScript(cmd, args);
        if (commandResult.startsWith("\""))
            return commandResult.substring(1, commandResult.length() - 1);
        return "";
    }
}
