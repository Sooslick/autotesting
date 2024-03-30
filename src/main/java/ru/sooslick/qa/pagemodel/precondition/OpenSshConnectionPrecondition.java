package ru.sooslick.qa.pagemodel.precondition;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.annotations.PreconditionName;

import java.util.Map;

// todo generify or move out of framework. +javadoc
@PreconditionName("establish an ssh connection with given parameters")
public class OpenSshConnectionPrecondition implements Precondition {

    @Context
    private ScenarioContext context;

    private Session session;

    private String username = "root";
    private String host = "localhost";
    private int port = 22;
    private String password;
    private String contextVariable = "ssh session";

    @Override
    @SneakyThrows
    public void complete() {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        context.saveVariable(contextVariable, session);
    }

    @Override
    public void withParameters(Map<String, String> parameters) {
        username = parameters.getOrDefault("username", username);
        host = parameters.getOrDefault("host", host);
        port = Integer.parseInt(parameters.getOrDefault("port", "22"));
        password = parameters.getOrDefault("password", password);
        contextVariable = parameters.getOrDefault("variable", contextVariable);
    }

    @Override
    public void rollback() {
        session.disconnect();
    }
}
