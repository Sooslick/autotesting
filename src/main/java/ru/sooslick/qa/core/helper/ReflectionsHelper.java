package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Helper class for reflective operations.
 */
@UtilityClass
public class ReflectionsHelper {

    /**
     * Returns value of desired field using reflective access.
     *
     * @param propertyName field name.
     * @param source       target object that contains desired field.
     * @return value of field from given object.
     * @throws Exception if field does not exist in given object, or we can't access it.
     */
    public Object reflectiveGet(String propertyName, Object source) throws Exception {
        Field f = source.getClass().getDeclaredField(propertyName);
        return ReflectionUtils.tryToReadFieldValue(f, source).get();
    }

    /**
     * Sets value of desired field using reflective access.
     *
     * @param object   object to mutate.
     * @param property name of the desired field.
     * @param value    value to set.
     * @throws ReflectiveOperationException if field does not exist in given object, or we can't access it.
     */
    public void reflectiveSet(Object object, String property, Object value) throws ReflectiveOperationException {
        Field f = object.getClass().getDeclaredField(property);
        reflectiveSet(object, f, value);
    }

    /**
     * Sets value of desired field using reflective access.
     *
     * @param object object to mutate.
     * @param field  desired field.
     * @param value  value to set.
     * @throws ReflectiveOperationException if field does not exist in given object, or we can't access it.
     */
    public void reflectiveSet(Object object, Field field, Object value) throws ReflectiveOperationException {
        field.setAccessible(true);
        field.set(object, value);
    }
}
