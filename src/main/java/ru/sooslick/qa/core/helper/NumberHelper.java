package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class NumberHelper {

    private final Pattern NUMBER_PATTERN = Pattern.compile("([-]?[0-9]+([.,][0-9]+)?)");

    public @Nullable String extractNumber(String smth) {
        Matcher m = NUMBER_PATTERN.matcher(smth);
        if (m.find())
            return m.group(0);
        return null;
    }

    public int tryParseInteger(@Nullable String smth, int dflt) {
        if (smth == null)
            return dflt;
        try {
            return Integer.parseInt(smth);
        } catch (NumberFormatException e) {
            return dflt;
        }
    }
}
