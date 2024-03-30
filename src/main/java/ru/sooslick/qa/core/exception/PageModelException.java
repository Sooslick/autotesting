package ru.sooslick.qa.core.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Exception class for errors related to page model and associated reflective operations.
 */
public class PageModelException extends RuntimeException {

    private final Class<?>[] troubleshooters;
    private final String message;

    /**
     * Default constructor with detail message.
     *
     * @param message detail message.
     */
    public PageModelException(String message) {
        this.message = message;
        this.troubleshooters = null;
    }

    /**
     * Default constructor with detail message and list of classes causing errors.
     *
     * @param message         detail message.
     * @param troubleshooters list of classes that will be mentioned in detail message.
     */
    public PageModelException(String message, Class<?>... troubleshooters) {
        this.message = message;
        this.troubleshooters = troubleshooters;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder(this.message);
        if (troubleshooters != null) {
            sb.append(". Page classes: ");
            sb.append(Arrays.stream(troubleshooters)
                    .map(Class::getName)
                    .collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }
}
