package ru.sooslick.qa.core.helper;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sooslick.qa.pagemodel.ActionType;
import ru.sooslick.qa.pagemodel.actions.ActionPerformer;
import ru.sooslick.qa.pagemodel.annotations.Action;
import ru.sooslick.qa.pagemodel.annotations.Actions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ActionsHelper {
    private final Map<Class<? extends ActionPerformer<?>>, ActionPerformer<?>> savedPerformers = new HashMap<>();

    public Map<ActionType, ActionPerformer<?>> createMapFromAnnotation(Field field) {
        // todo this method is a mess a bit, plz refactor
        Action[] actionsArray = null;
        Actions actions = field.getAnnotation(Actions.class);
        if (actions != null)
            actionsArray = actions.value();
        else {
            Action action = field.getAnnotation(Action.class);
            if (action != null)
                actionsArray = new Action[]{action};
        }
        Map<ActionType, ActionPerformer<?>> result = actionsArray == null ? new HashMap<>() :
                Arrays.stream(actionsArray)
                        .collect(Collectors.toMap(
                                Action::type,
                                action -> getPerformer(action.performer())));
        Arrays.stream(ActionType.values())
                .forEach(actionType -> {
                    if (result.get(actionType) == null)
                        result.put(actionType, createPerformer(actionType.getDefaultPerformer()));
                });
        return result;
    }

    private ActionPerformer<?> getPerformer(Class<? extends ActionPerformer<?>> performerClass) {
        return savedPerformers.computeIfAbsent(performerClass, ActionsHelper::createPerformer);
    }

    @SneakyThrows
    private ActionPerformer<?> createPerformer(Class<? extends ActionPerformer<?>> performerClass) {
        return performerClass.getDeclaredConstructor().newInstance();
    }
}
