package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import ru.sooslick.qa.core.RunnerProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Helper class for properties files.
 */
@UtilityClass
@Slf4j
public class PropertiesHelper {
    private Map<String, String> cachedProperties;

    /**
     * Returns property value by property key, searching in all registered properties files by runner config.
     *
     * @param property property key.
     * @return property value or null if property with given key does not exist.
     */
    public @Nullable String getProperty(String property) {
        if (cachedProperties == null)
            lazyInit();
        return cachedProperties.get(property);
    }

    /**
     * Return InoutStream for resource with given path.
     *
     * @param fname path to resource.
     * @return InputStream for given resource.
     * @throws IOException if resource inacccessible or does not exist.
     */
    // todo probably I should move this method to anywhere else
    public InputStream getResourceInputStream(String fname) throws IOException {
        InputStream is;
        try {
            is = new FileInputStream(fname);
        } catch (FileNotFoundException e) {
            log.warn("Properties file '{}' does not exist, trying to load from classpath resources", fname);
            is = PropertiesHelper.class.getResourceAsStream(fname);
        }
        if (is == null)
            throw new IOException("Failed to load resource " + fname);
        return is;
    }

    private void lazyInit() {
        cachedProperties = new HashMap<>();
        RunnerProperties.PROPERTIES_FILES.stream()
                .map(PropertiesHelper::createProperties)
                .forEach(props -> props.forEach((key, value) ->
                        cachedProperties.put(key.toString(), value.toString())));
    }

    private Properties createProperties(String file) {
        Properties properties = new Properties();
        try (InputStream is = getResourceInputStream(file)) {
            properties.load(is);
        } catch (IOException e) {
            log.warn("Unable to read '{}' file from runner config", file, e);
        }
        return properties;
    }
}
