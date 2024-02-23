package ru.sooslick.qa.core.page;

import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.core.exception.PageModelException;
import ru.sooslick.qa.core.helper.PageAnnotationsHelper;
import ru.sooslick.qa.pagemodel.page.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UtilityClass
public class PageNameResolver {
    private final Map<String, Class<? extends Page>> registeredPages = new HashMap<>();

    static {
        // todo load classes from config
        ReflectionUtils.streamAllClassesInPackage("ru.sooslick.qa.pageobjects",
                        ClassFilter.of(Page.class::isAssignableFrom))
                .map(aClass -> (Class<? extends Page>) aClass)
                .forEach(pageClass -> {
                    String name = PageAnnotationsHelper.getPageName(pageClass);
                    if (registeredPages.containsKey(name))
                        throw new PageModelException("Page duplicate: " + name, pageClass, registeredPages.get(name));
                    registeredPages.put(name, pageClass);
                });
    }

    public Class<? extends Page> getPageClass(String name) {
        return Optional.ofNullable(registeredPages.get(name.trim()))
                .orElseThrow(() -> new PageModelException("Unknown page: " + name));
    }
}
