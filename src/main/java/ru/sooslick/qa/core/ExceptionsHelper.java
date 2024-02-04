package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;
import org.opentest4j.MultipleFailuresError;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class ExceptionsHelper {

    public @Nullable Throwable convertExceptionList(List<Throwable> throwableList) {
        if (throwableList.isEmpty())
            return null;
        if (throwableList.size() == 1)
            return throwableList.get(0);
        return new MultipleFailuresError(null, throwableList);
    }

    public boolean isSameException(Throwable left, Throwable right) {
        if (left.getClass().equals(right.getClass())) {
            return left.getMessage().equals(right.getMessage());
        }
        return false;
    }

    public List<Throwable> reduceDuplications(List<Throwable> throwableList) {
        if (throwableList.isEmpty())
            return Collections.emptyList();

        List<Throwable> reducedList = new LinkedList<>();
        Throwable lastCheckedError = throwableList.get(0);
        reducedList.add(lastCheckedError);

        for (Throwable currentError : throwableList) {
            if (ExceptionsHelper.isSameException(currentError, lastCheckedError)) {
                lastCheckedError = currentError;
                reducedList.add(lastCheckedError);
            }
        }
        return reducedList;
    }
}
