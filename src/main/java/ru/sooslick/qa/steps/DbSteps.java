package ru.sooslick.qa.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.DataGeneratorsHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Steps class for database-related actions and queries
 */
@Slf4j
public class DbSteps {

    @Context
    private ScenarioContext context;

    private final Map<String, JdbcTemplate> connections = new HashMap<>();

    @Given("A user establishes a database connection {string}, using following connection string")
    @SneakyThrows
    public void openConnection(String name, List<String> connectionStrings) {
        if (connectionStrings.isEmpty())
            throw new IllegalArgumentException("No jdbc connection string provided");
        if (connections.containsKey(name))
            throw new IllegalArgumentException("Variable " + name + " already defined in test context!");

        String connectionString = DataGeneratorsHelper.processString(String.join("", connectionStrings), context);
        Connection dbConnection = DriverManager.getConnection(connectionString);
        connections.put(name, new JdbcTemplate(new SingleConnectionDataSource(dbConnection, false)));
    }

    @Given("A user executes following SQL using the connection {string} and saves the result as table {string}")
    public void selectTable(String connection, String variable, List<String> sqlDraft) {
        select(connection, sqlDraft, variable, JdbcTemplate::queryForList);
    }

    @Given("A user executes following SQL using the connection {string} and saves the result as variable {string}")
    public void selectValue(String connection, String variable, List<String> sqlDraft) {
        select(connection, sqlDraft, variable, (dbc, sql) -> dbc.queryForObject(sql, String.class));
    }

    @After
    public void rollback() {
        connections.values().forEach(jdbcTemplate -> {
            try {
                //noinspection ConstantConditions
                jdbcTemplate.getDataSource().getConnection().close();
            } catch (Exception e) {
                log.warn("Unable to close db connection properly? " + e.getMessage());
            }
        });
    }

    private @NotNull JdbcTemplate getConnection(String name) {
        JdbcTemplate jdbcTemplate = connections.get(name);
        if (jdbcTemplate == null)
            throw new IllegalArgumentException("Unknown database connection");
        return jdbcTemplate;
    }

    private String formatSql(List<String> parts) {
        if (parts == null || parts.isEmpty())
            throw new IllegalArgumentException("Empty SQL");
        return parts.stream()
                .map(part -> DataGeneratorsHelper.processString(part, context))
                .collect(Collectors.joining(" \n"));
    }

    private <T> void select(String connection, List<String> sqlDraft, String variable, BiFunction<JdbcTemplate, String, T> query) {
        JdbcTemplate jdbcTemplate = getConnection(connection);
        String sql = formatSql(sqlDraft);
        T value = query.apply(jdbcTemplate, sql);
        context.saveVariable(variable, value);
    }
}
