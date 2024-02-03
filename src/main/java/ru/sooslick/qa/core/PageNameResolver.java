package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;
import org.reflections.Reflections;
import ru.sooslick.qa.pagemodel.Page;
import ru.sooslick.qa.pagemodel.annotations.PageName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@UtilityClass
public class PageNameResolver {
    private final Map<String, Class<? extends Page>> registeredPages = new HashMap<>();

    static {
        // todo load classes from config
        String pagesPath = "ru.sooslick.qa.pages";
        Set<Class<? extends Page>> pageClasses = new Reflections(pagesPath)
                .getSubTypesOf(Page.class);
        pageClasses.forEach(pageClass -> {
            String name = Optional.ofNullable(pageClass.getAnnotation(PageName.class))
                    .map(PageName::value)
                    .orElse(pageClass.getSimpleName());
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
