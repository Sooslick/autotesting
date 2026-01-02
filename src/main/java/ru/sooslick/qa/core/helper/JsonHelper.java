package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import tools.jackson.databind.ObjectMapper;

@UtilityClass
public class JsonHelper {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
