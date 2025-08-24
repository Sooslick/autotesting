package ru.sooslick.qa.core.repeaters;

import ru.sooslick.qa.core.helper.ExceptionsHelper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Repeatable implementation to run steps for each element of various collections.
 *
 * @param <Entity> type of collection element.
 */
public class RepeatableGroup<Entity> extends AbstractRepeatable<Void> {
    private final LinkedList<Entity> group;
    private final Consumer<Entity> steps;

    public RepeatableGroup(Collection<Entity> group, Consumer<Entity> steps) {
        this.group = new LinkedList<>(group);
        this.steps = steps;
    }

    @Override
    public Void doAction() throws Throwable {
        LinkedList<Entity> succeed = new LinkedList<>();
        LinkedList<Throwable> failures = new LinkedList<>();
        for (Entity e : group) {
            try {
                steps.accept(e);
                succeed.add(e);
            } catch (Throwable t) {
                failures.add(t);
            }
        }
        group.removeAll(succeed);
        if (group.size() > 0)
            throw ExceptionsHelper.convertExceptionList(failures, "Check failed for " + failures.size() + " elements");
        return null;
    }
}
