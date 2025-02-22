package ru.sooslick.qa.core.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonHelper {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
