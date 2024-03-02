package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;

@UtilityClass
public class ReflectionsHelper {

    public Object reflectiveGet(String propertyName, Object source) throws Exception {
        Field f = source.getClass().getDeclaredField(propertyName);
        return ReflectionUtils.tryToReadFieldValue(f, source).get();
    }
}
