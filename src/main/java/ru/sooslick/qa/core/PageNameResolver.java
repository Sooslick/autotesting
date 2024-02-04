package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;
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
        ReflectionsUtils.getPackageClasses(pagesPath, Page.class)
                .forEach(pageClass -> {
                    String name = PageModelAnnotationsUtils.getPageName(pageClass);
                    if (registeredPages.containsKey(name))
                        throw new RuntimeException("Page duplicate: " + name);   // todo proper exception
                    registeredPages.put(name, pageClass);
                });
    }

    public Class<? extends Page> getPageClass(String name) {
        return Optional.ofNullable(registeredPages.get(name))
                .orElseThrow(() -> new RuntimeException("Unknown page: " + name));  // todo proper exception
    }
}
