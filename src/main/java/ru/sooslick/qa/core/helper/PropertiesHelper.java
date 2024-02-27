package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@UtilityClass
public class PropertiesHelper {
    private final Map<String, Properties> cachedProperties = new HashMap<>();

    public @Nullable String getProperty(String property) {
        Properties properties = cachedProperties.computeIfAbsent("connections.properties", PropertiesHelper::createProperties);
        return properties.getProperty(property);
    }

    @SneakyThrows
    private Properties createProperties(String file) {
        try (InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("properties/" + file)) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        }
    }
}
