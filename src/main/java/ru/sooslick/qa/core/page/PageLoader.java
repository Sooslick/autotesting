package ru.sooslick.qa.core.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.sooslick.qa.pagemodel.page.Page;

public class PageLoader {
    private static PageLoader INSTANCE;

    public static PageLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageLoader();
        }
        return INSTANCE;
    }

    @SneakyThrows
    public Page loadPage(WebDriver webDriver, String name) {
        Class<? extends Page> pageClass = PageNameResolver.getPageClass(name);
        Page page = pageClass.getDeclaredConstructor().newInstance();
        // todo i dont like these redundant constructor parameters. can I use Page as parent?
        PageFactory.initElements(new PageFieldDecorator(webDriver, webDriver, page), page);
        return page;
    }
}
