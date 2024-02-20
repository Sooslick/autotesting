package ru.sooslick.qa.core.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PageModelException extends RuntimeException {

    private final Class<?>[] troubleshooters;
    private final String message;

    public PageModelException(String message) {
        this.message = message;
        this.troubleshooters = null;
    }

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
