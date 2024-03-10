package ru.sooslick.qa;

import io.cucumber.core.options.Constants;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.EngineFilter;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import ru.sooslick.qa.core.RunnerProperties;

import java.io.IOException;

public class YamlRunner {
    public static void main(String[] args) throws IOException {
        Launcher launcher = LauncherFactory.create();
        LauncherDiscoveryRequest request = createDiscovery();
//        TestPlan testPlan = launcher.discover(request);
        launcher.execute(request);
    }

    private static LauncherDiscoveryRequest createDiscovery() {
        LauncherDiscoveryRequestBuilder requestBuilder = LauncherDiscoveryRequestBuilder.request()
                .configurationParameter(Constants.GLUE_PROPERTY_NAME, String.join(",", RunnerProperties.STEPS_PACKAGES))
                .configurationParameter(Constants.OBJECT_FACTORY_PROPERTY_NAME, RunnerProperties.CUCUMBER_OBJECT_FACTORY)
                .filters(EngineFilter.includeEngines("cucumber"))
                .filters(TagFilter.includeTags(RunnerProperties.LAUNCH_TEST_TAGS));
        RunnerProperties.SCENARIOS_DIRECTORIES.forEach(entry ->
                requestBuilder.selectors(DiscoverySelectors.selectClasspathResource(entry)));
        return requestBuilder.build();
    }
}
