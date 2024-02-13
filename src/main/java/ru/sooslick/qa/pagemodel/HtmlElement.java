package ru.sooslick.qa.pagemodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class HtmlElement implements ElementsContainer, WebElement, Locatable, WrapsDriver {
    private final Map<String, HtmlElement> innerElements = new HashMap<>();

    private WebDriver driver;
    private SearchContext parent;
    @Getter
    private String name;
    private By locator;
    @Getter
    private boolean required;
    private Map<ActionType, ActionPerformer<?>> actions;
    @Getter
    private WebElement cachedElement;   // todo temp workaround, i can't access protected webdriver methods

    @Override
    public HtmlElement getChildElementByName(String name) {
        return innerElements.get(name);
    }

    @Override
    public Collection<HtmlElement> getChildElements() {
        return innerElements.values();
    }

    @Override
    public void addChildElement(HtmlElement element) {
        innerElements.put(element.getName(), element);
    }

    // todo I should create own interface for these methods
    public Object triggerAction(ActionType type) {
        return actions.get(type).perform(this);
    }

    @Override
    public void click() {
        // todo get rid of "not implemented" methods
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void submit() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void clear() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getTagName() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getAttribute(String name) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean isSelected() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean isEnabled() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getText() {
        refreshIfStale();
        return cachedElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public WebElement findElement(By by) {
        refreshIfStale();
        return cachedElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        refreshIfStale();
        return cachedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Dimension getSize() {
        refreshIfStale();
        return cachedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getCssValue(String propertyName) {
        refreshIfStale();
        return cachedElement.getCssValue(propertyName);
    }

    @Override
    public WebDriver getWrappedDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return name + " " + locator.toString();
    }

    private void refreshIfStale() {
        if (cachedElement == null) {
            cachedElement = parent.findElement(locator);
            return;
        }
        try {
            cachedElement.isDisplayed();
        } catch (StaleElementReferenceException e) {
            cachedElement = parent.findElement(locator);
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Coordinates getCoordinates() {
        throw new RuntimeException("Not implemented");
    }
}
