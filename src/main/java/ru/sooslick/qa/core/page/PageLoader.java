package ru.sooslick.qa.core.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.sooslick.qa.pagemodel.Page;

import java.util.HashMap;
import java.util.Map;

public class PageLoader {
    private static final Map<String, Page> cachedPages = new HashMap<>();

    private static PageLoader INSTANCE;

    public static PageLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageLoader();
        }
        return INSTANCE;
    }

    @SneakyThrows
    public Page loadPage(WebDriver webDriver, String name) {
        Page cachedPage = cachedPages.get(name);
        if (cachedPage != null)
            return cachedPage;
        Class<? extends Page> pageClass = PageNameResolver.getPageClass(name);
        Page page = pageClass.getDeclaredConstructor().newInstance();
        // todo i dont like these redundant constructor parameters. can I use Page as parent?
        PageFactory.initElements(new PageFieldDecorator(webDriver, webDriver, page), page);
        cachedPages.put(name, page);
        return page;
    }

}
