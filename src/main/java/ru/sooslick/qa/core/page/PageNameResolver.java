package ru.sooslick.qa.core.page;

import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;
import ru.sooslick.qa.core.RunnerProperties;
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
        //noinspection unchecked
        RunnerProperties.PAGE_OBJECTS_PACKAGES.forEach(pkg ->
                ReflectionUtils.streamAllClassesInPackage(pkg, ClassFilter.of(Page.class::isAssignableFrom))
                        .map(aClass -> (Class<? extends Page>) aClass)
                        .forEach(pageClass -> {
                            String normalizedName = PageAnnotationsHelper.getPageName(pageClass);
                            if (registeredPages.containsKey(normalizedName))
                                throw new PageModelException("Page duplicate: " + normalizedName, pageClass, registeredPages.get(normalizedName));
                            registeredPages.put(normalizedName, pageClass);
                        }));
    }

    public Class<? extends Page> getPageClass(String name) {
        String normalizedName = name.trim().toLowerCase();
        return Optional.ofNullable(registeredPages.get(normalizedName))
                .orElseThrow(() -> new PageModelException("Unknown page: " + name));
    }
}
