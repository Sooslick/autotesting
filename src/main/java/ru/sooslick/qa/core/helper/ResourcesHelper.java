package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility methods for resource files
 */
@UtilityClass
@Slf4j
public class ResourcesHelper {

    /**
     * Return InoutStream for resource with given path.
     *
     * @param fname path to resource.
     * @return InputStream for given resource.
     * @throws IOException if resource inacccessible or does not exist.
     */
    public InputStream getResourceInputStream(String fname) throws IOException {
        InputStream is;
        try {
            is = new FileInputStream(fname);
        } catch (FileNotFoundException e) {
            log.warn("File '{}' does not exist, trying to load from classpath resources", fname);
            is = PropertiesHelper.class.getResourceAsStream(fname);
        }
        if (is == null)
            throw new IOException("Failed to load resource " + fname);
        return is;
    }
}
