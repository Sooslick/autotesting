package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

@UtilityClass
public class ReflectionsHelper {

    public <T> Collection<Class<? extends T>> getPackageClasses(String packageName, Class<T> baseClass) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream is = classLoader.getResourceAsStream(packageName.replaceAll("\\.", "/"));
        return new BufferedReader(new InputStreamReader(is)).lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> createClassName(packageName, line))
                .filter(baseClass::isAssignableFrom)
                .map(aClass -> (Class<? extends T>) aClass)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private Class<?> createClassName(String packageName, String classFileName) {
        String className = classFileName.substring(0, classFileName.lastIndexOf("."));
        String fullyQualifiedName = packageName + "." + className;
        return Class.forName(fullyQualifiedName);
    }
}
