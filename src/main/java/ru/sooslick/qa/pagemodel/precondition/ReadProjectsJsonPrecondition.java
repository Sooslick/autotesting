package ru.sooslick.qa.pagemodel.precondition;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.core.helper.PropertiesHelper;
import ru.sooslick.qa.pagemodel.annotations.Context;
import ru.sooslick.qa.pagemodel.annotations.PreconditionName;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// todo move precondition out of framework
@PreconditionName("download projects.json and read data to test context")
public class ReadProjectsJsonPrecondition implements Precondition {

    @Context
    private ScenarioContext context;

    private String sessionVariable = "ssh session";

    @Override
    @SneakyThrows
    public void complete() {
        Session session = (Session) context.getVariable(sessionVariable);

        var channel = session.openChannel("sftp");
        channel.connect();
        var channelSftp = (ChannelSftp) channel;
        new File("output").mkdirs();

        String projectsJsonLocation = PropertiesHelper.getProperty("path.projects.json");
        channelSftp.get(projectsJsonLocation, "output/projects.json");
        channelSftp.disconnect();
        session.disconnect();

        ObjectMapper om = new ObjectMapper();
        var node = om.readTree(Files.readAllBytes(Paths.get("output/projects.json")));
        List<SooslickArtProject> projects = new LinkedList<>();
        node.forEach(prjNode -> projects.add(
                om.convertValue(prjNode, SooslickArtProject.class)));
        var sortedProjects = projects.stream()
                .peek(prj -> {
                    // todo should i use properties to extract domain?
                    if (prj.link.startsWith("/"))
                        prj.link = "https://sooslick.art" + prj.link;
                    if (prj.banner.startsWith("/"))
                        prj.banner = "https://sooslick.art" + prj.banner;
                    if (prj.big != null && prj.big.startsWith("/"))
                        prj.big = "https://sooslick.art" + prj.big;
                })
                .sorted(Comparator.comparingInt(o -> o.order))
                .collect(Collectors.toList());

        var featuredProject = sortedProjects.stream()
                .filter(SooslickArtProject::isFeatured)
                .findFirst()
                .orElse(null);

        var visibleProjectsNormal = sortedProjects.stream()
                .filter(prj -> prj.visibility == 100)
                .collect(Collectors.toList());

        var visibleProjectsAll = sortedProjects.stream()
                .filter(prj -> prj.visibility > 0)
                .collect(Collectors.toList());

        var hiddenProjects = sortedProjects.stream()
                .filter(prj -> prj.visibility > 0 && prj.visibility < 100)
                .collect(Collectors.toList());

        var showcaseProjects = sortedProjects.stream()
                .filter(SooslickArtProject::isShowcase)
                .collect(Collectors.toList());

        var techProjects = sortedProjects.stream()
                .filter(prj -> prj.visibility == 0)
                .collect(Collectors.toList());

        context.saveVariable("all projects", projects);
        context.saveVariable("featured project", featuredProject);
        context.saveVariable("displayed projects", visibleProjectsNormal);
        context.saveVariable("displayed projects including hidden", visibleProjectsAll);
        context.saveVariable("hidden projects", hiddenProjects);
        context.saveVariable("showcase projects", showcaseProjects);
        context.saveVariable("service projects", techProjects);
    }

    @Override
    public void withParameters(Map<String, String> params) {
        sessionVariable = params.getOrDefault("session variable", sessionVariable);
    }

    @Override
    public void rollback() {
        File f = new File("output/projects.json");
        if (f.exists())
            f.delete();
    }

    @Data
    public static class SooslickArtProject {
        String id;
        String name;
        String banner;
        String big;
        String link;
        int visibility;
        int order;
        boolean featured;
        boolean showcase;
        List<String> details;

        @Override
        public String toString() {
            return name;
        }
    }
}
