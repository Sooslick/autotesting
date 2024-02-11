package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ActionsHelper {
    private final Map<Class<? extends ActionPerformer<?>>, ActionPerformer<?>> savedPerformers = new HashMap<>();

    public ActionPerformer<?> getPerformer(Class<? extends ActionPerformer<?>> performerClass) {
        return savedPerformers.computeIfAbsent(performerClass, ActionsHelper::createPerformer);
    }

    @SneakyThrows
    private ActionPerformer<?> createPerformer(Class<? extends ActionPerformer<?>> performerClass) {
        return performerClass.getDeclaredConstructor().newInstance();
    }
}
