package ru.sooslick.qa.core.page;

import lombok.experimental.UtilityClass;
import ru.sooslick.qa.core.exception.PageModelException;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.core.helper.ReflectionsHelper;
import ru.sooslick.qa.pagemodel.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UtilityClass
public class PageNameResolver {
    private final Map<String, Class<? extends Page>> registeredPages = new HashMap<>();

    static {
        // todo load classes from config
        String pagesPath = "ru.sooslick.qa.pages";
        ReflectionsHelper.getPackageClasses(pagesPath, Page.class)
                .forEach(pageClass -> {
                    String name = PageAnnotationsHelper.getPageName(pageClass);
                    if (registeredPages.containsKey(name))
                        throw new PageModelException("Page duplicate: " + name, pageClass, registeredPages.get(name));
                    registeredPages.put(name, pageClass);
                });
    }

    public Class<? extends Page> getPageClass(String name) {
        return Optional.ofNullable(registeredPages.get(name))
                .orElseThrow(() -> new PageModelException("Unknown page: " + name));
    }
}
