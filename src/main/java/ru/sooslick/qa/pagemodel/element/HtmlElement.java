package ru.sooslick.qa.pagemodel.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
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
import ru.sooslick.qa.pagemodel.components.ComputedComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private WebElement cachedElement;

    @Override
    public @NotNull HtmlElement getChildElementByName(String name) {
        return Optional.ofNullable(innerElements.get(name))
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "Page %s has no known element with name '%s'\nCheck your @ElementName has no typos and your elements are HtmlElement (or it's subclass)",
                        this.name, name)));
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
    public Object triggerAction(ActionType type) {
        ActionPerformer<?> performer = actions.get(type);
        if (performer == null)
            performer = type.getDefaultPerformerImpl();
        return performer.perform(this.getWrappedDriver(), this.getCachedElement());
    }

    public ComputedComponent getComponent(Component component) {
        By locator = componentLocators.getOrDefault(component, component.getDefaultLocator());
        Class<? extends HtmlElement> containerType = componentTypes.getOrDefault(component, component.getContainerType());
        return new ComputedComponent(component, locator, containerType);
    }

    // TODO: do not use, rewrite method for new components
    public List<WebElement> findComponentElements(Component component) {
        return findElements(getComponent(component).locator());
    }

    @Override
    public void click() {
        triggerAction(ActionType.CLICK);
    }

    @Override
    public void submit() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        // TODO - action-based type
        getCachedElement().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        triggerAction(ActionType.CLEAR);
    }

    @Override
    public String getTagName() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getAttribute(String name) {
        // TODO deprecated get attribute
        return getCachedElement().getAttribute(name);
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
        return (String) triggerAction(ActionType.GET_TEXT);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getCachedElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getCachedElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        try {
            return (boolean) triggerAction(ActionType.GET_ELEMENT_VISIBILITY);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return getCachedElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getCachedElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getCssValue(String propertyName) {
        return getCachedElement().getCssValue(propertyName);
    }

    @Override
    public WebDriver getWrappedDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return name + " " + locator.toString();
    }

    protected WebElement getCachedElement() {
        if (cachedElement == null) {
            cachedElement = parent.findElement(locator);
            return cachedElement;
        }
        try {
            // quick check if element still presented in DOM (result of isDisplayed does not matter)
            // if we catch StaleElementReferenceException then we should update cached element
            cachedElement.isDisplayed();
        } catch (StaleElementReferenceException e) {
            cachedElement = parent.findElement(locator);
        }
        return cachedElement;
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
