package ru.sooslick.qa.core.page;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.sooslick.qa.pagemodel.annotations.PageName;
import ru.sooslick.qa.pagemodel.page.Page;

/**
 * Utility class for loading pages and filling them with elements.
 */
@UtilityClass
public class PageLoader {

    /**
     * Finds Page class and instantiates it, and creates associated elements.
     *
     * @param webDriver driver that opens the page
     * @param name      name of Page class, found by {@link PageName} annotation
     * @return loaded page.
     */
    @SneakyThrows
    public Page loadPage(WebDriver webDriver, String name) {
        Class<? extends Page> pageClass = PageNameResolver.getPageClass(name);
        Page page = pageClass.getDeclaredConstructor().newInstance();
        // todo i dont like these redundant constructor parameters. can I use Page as parent?
        PageFactory.initElements(new PageFieldDecorator(webDriver, webDriver, page), page);
        return page;
    }
}
