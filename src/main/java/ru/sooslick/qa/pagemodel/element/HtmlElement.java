package ru.sooslick.qa.pagemodel.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
import ru.sooslick.qa.pagemodel.ElementsContainer;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.actions.ActionType;
import ru.sooslick.qa.pagemodel.components.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for Selenium WebElement.
 * HtmlElement adds some customization for various actions and contains some metadata
 * for referring in cucumber scenarios.
 */
// todo review methods and javadocs
@NoArgsConstructor
public class HtmlElement implements ElementsContainer, WebElement, Locatable, WrapsDriver {
    private final Map<String, HtmlElement> innerElements = new HashMap<>();

    protected WebDriver driver;
    private SearchContext parent;
    @Getter
    private String name;
    private By locator;
    @Getter
    private boolean required;
    private Map<ActionType, ActionPerformer<?>> actions;
    private Map<Component, By> componentLocators;
    private Map<Component, Class<? extends HtmlElement>> componentTypes;
    @Getter
    protected WebElement cachedElement;   // todo temp workaround, i can't access protected webdriver methods

    @Override
    public @Nullable HtmlElement getChildElementByName(String name) {
        return innerElements.get(name);
    }

    @Override
    public @NotNull Collection<HtmlElement> getChildElements() {
        return innerElements.values();
    }

    @Override
    public void addChildElement(@NotNull HtmlElement element) {
        innerElements.put(element.getName(), element);
    }

    // todo I should create own interface for these methods
    // todo I should refactor my approach to triggering actions bcs i messed up the whole idea of actions
    public Object triggerAction(ActionType type) {
        ActionPerformer<?> performer = actions.get(type);
        if (performer == null)
            performer = type.getDefaultPerformerImpl();
        return performer.perform(this);
    }

    public By getComponentLocator(Component component) {
        return componentLocators.get(component);
    }

    public Class<? extends HtmlElement> getComponentType(Component component) {
        return componentTypes.get(component);
    }

    @Override
    public void click() {
        refreshIfStale();
        cachedElement.click();
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
        refreshIfStale();
        return cachedElement.getAttribute(name);
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
        refreshIfStale();
        return cachedElement.findElements(by);
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
        refreshIfStale();
        return cachedElement.getLocation();
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

    protected void refreshIfStale() {
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
