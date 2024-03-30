package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to make easy conversions Strings to numbers.
 */
@UtilityClass
public class NumberHelper {

    /**
     * Number regexp.
     */
    public final Pattern NUMBER_PATTERN = Pattern.compile("([-]?[0-9]+([.,][0-9]+)?)");

    /**
     * Checks if source string contains any number and returns substring that contains this number.
     *
     * @param smth source string with number.
     * @return substring that contains number from parameter, or null if source string does not match the {@link NumberHelper#NUMBER_PATTERN}.
     */
    public @Nullable String extractNumber(String smth) {
        Matcher m = NUMBER_PATTERN.matcher(smth);
        if (m.find())
            return m.group(0);
        return null;
    }

    /**
     * Returns string parameter as int with default fallback for unsuccessful conversions.
     *
     * @param smth source string to convert using Integer.parseInt.
     * @param dflt default value for unsuccessful conversions.
     * @return integer parsed from source string, or default value if parseInt failed.
     */
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
