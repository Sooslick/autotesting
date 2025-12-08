package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.opentest4j.MultipleFailuresError;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Helper class for various exceptions.
 */
@UtilityClass
public class ExceptionsHelper {

    /**
     * Converts list of throwable to single exception.
     * 1) If given list is empty, method will throw IllegalStateException;
     * 2) If given list contains only one throwable, method will return throwable from the list;
     * 3) Otherwise, list of throwable will be converted to MultipleFailureException.
     *
     * @param throwableList source list of throwable.
     * @param summary       message for resulting exception
     * @return converted throwable.
     */
    public Throwable convertExceptionList(List<Throwable> throwableList, String summary) {
        if (throwableList.isEmpty())
            return new IllegalStateException(summary);
        if (throwableList.size() == 1)
            return throwableList.get(0);
        return new MultipleFailuresError(summary, throwableList);
    }

    /**
     * Converts list of throwable to single exception.
     * 1) If given list is empty, method will do nothing;
     * 2) If given list contains only one throwable, method will throw that throwable from the list;
     * 3) Otherwise, list of throwable will be converted to MultipleFailureException.
     *
     * @param throwableList source list of throwable.
     * @param summary       message for resulting exception
     */
    @SneakyThrows
    public void convertAndThrowExceptionList(List<Throwable> throwableList, String summary) {
        if (throwableList.isEmpty())
            return;
        if (throwableList.size() == 1)
            throw throwableList.get(0);
        throw new MultipleFailuresError(summary, throwableList);
    }

    /**
     * Checks if exceptions have same class and messages.
     *
     * @param left  first exception.
     * @param right second exception.
     * @return true if exceptions are instances of same class and have same messages.
     */
    public boolean isSameException(Throwable left, Throwable right) {
        if (left.getClass().equals(right.getClass())) {
            return left.getMessage().equals(right.getMessage());
        }
        return false;
    }

    /**
     * Processes list of throwable and removes repeating exceptions using {@link ExceptionsHelper#isSameException} method.
     *
     * @param throwableList source list of throwable.
     * @return processed list without repeating exceptions.
     */
    public List<Throwable> reduceDuplications(List<Throwable> throwableList) {
        if (throwableList.isEmpty())
            return Collections.emptyList();

        List<Throwable> reducedList = new LinkedList<>();
        Throwable lastCheckedError = throwableList.get(0);
        reducedList.add(lastCheckedError);

        for (Throwable currentError : throwableList) {
            if (!ExceptionsHelper.isSameException(currentError, lastCheckedError)) {
                lastCheckedError = currentError;
                reducedList.add(lastCheckedError);
            }
        }
        return reducedList;
    }
}
