package ru.sooslick.qa.pagemodel.precondition;

import lombok.SneakyThrows;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.annotations.PreconditionName;

import java.util.Map;

// todo move precondition out of framework
@PreconditionName("Create Templates and Tickets for Bingo App")
public class BingoInsertDataPrecondition implements Precondition {

    @Context
    private ScenarioContext context;

    @Override
    @SneakyThrows
    public void complete() {
        // todo current testcases assume that templates & tickets exist, so no insert is required at this point
        //  but i should add it for dev environment in future, after app release & updating testcases
    }

    @Override
    public void withParameters(Map<String, String> params) {
        // todo add tickets and templates info
    }

    @Override
    public void rollback() {
        // todo no data inserted for rollback
    }
}
