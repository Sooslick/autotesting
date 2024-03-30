package ru.sooslick.qa.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.sooslick.qa.core.helper.PropertiesHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility class that stores global runner properties.
 * These properties are linked to runner.yaml resource or external yaml file,
 * specified in RUNNER_PROPERTIES environment variable.
 * <p>
 * Most of the framework features works with these properties since these properties contains
 * paths, packages, etc. important runner settings.
 */
@UtilityClass
@Slf4j
public class RunnerProperties {
    private final String RUNNER_YAML = "/properties/runner.yaml";

    /**
     * List of paths to directories that contains cucumber .scenario files.
     */
    public List<String> SCENARIOS_DIRECTORIES;

    /**
     * List of packages that contains cucumber steps classes and used as "cucumber.glue" cucumber property.
     */
    public List<String> STEPS_PACKAGES;

    // todo remove soon
    public String CUCUMBER_OBJECT_FACTORY;

    /**
     * List of packages that contains Pages and associated Page Model classes.
     */
    public List<String> PAGE_OBJECTS_PACKAGES;

    /**
     * List of packages that contains Preconditions implementations.
     */
    public List<String> PRECONDITIONS_PACKAGES;

    /**
     * List of packages that contains DataGenerator implementations.
     */
    public List<String> DATA_GENERATORS_PACKAGES;

    /**
     * List of paths to properties files
     */
    public List<String> PROPERTIES_FILES;

    /**
     * Test tags to launch.
     */
    public List<String> LAUNCH_TEST_TAGS;

    /**
     * WebDriver preset that will be used in test run.
     */
    public String WEBDRIVER_CONFIGURATION;

    /**
     * Path to WebDriver executable.
     */
    public String WEBDRIVER_PATH;

    /**
     * Path to browser main executable.
     */
    public String BROWSER_BINARY_PATH;

    static {
        String runnerYamlName = System.getenv("RUNNER_PROPERTIES");
        runnerYamlName = runnerYamlName == null ? RUNNER_YAML : runnerYamlName;
        try {
            InputStream is = PropertiesHelper.getResourceInputStream(runnerYamlName);
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            var yaml = om.readTree(is);

            SCENARIOS_DIRECTORIES = readYamlList(yaml, "/resources/scenarios");
            STEPS_PACKAGES = readYamlList(yaml, "/resources/steps");
            CUCUMBER_OBJECT_FACTORY = yaml.at("/resources/objects-factory").asText();
            PAGE_OBJECTS_PACKAGES = readYamlList(yaml, "/resources/page-objects");
            PRECONDITIONS_PACKAGES = readYamlList(yaml, "/resources/preconditions");
            DATA_GENERATORS_PACKAGES = readYamlList(yaml, "/resources/data-generators");
            PROPERTIES_FILES = readYamlList(yaml, "/resources/properties");

            LAUNCH_TEST_TAGS = readYamlList(yaml, "/test-run/launch-tags");
            WEBDRIVER_CONFIGURATION = yaml.at("/test-run/web-driver/configuration-class").asText();
            WEBDRIVER_PATH = yaml.at("/test-run/web-driver/path").asText();
            BROWSER_BINARY_PATH = yaml.at("/test-run/web-driver/binary-path").asText();
        } catch (IOException e) {
            log.error("Failed to read runner properties file!", e);
            setDefaults();
            log.warn("Loaded default runner properties");
        }
    }

    private List<String> readYamlList(JsonNode fromNode, String path) {
        JsonNode targetNode = fromNode.at(path);
        if (targetNode.isArray()) {
            List<String> result = new LinkedList<>();
            for (JsonNode arrayElement : targetNode)
                result.add(arrayElement.asText());
            return result;
        } else
            return Collections.singletonList(targetNode.asText());
    }

    private void setDefaults() {
        SCENARIOS_DIRECTORIES = Collections.singletonList("scenarios");
        STEPS_PACKAGES = Collections.singletonList("ru.sooslick.qa.steps");
        CUCUMBER_OBJECT_FACTORY = "ru.sooslick.qa.core.StepsFactory";
        PAGE_OBJECTS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pageobjects");
        PRECONDITIONS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pagemodel.precondition");
        DATA_GENERATORS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pagemodel.generator");
        PROPERTIES_FILES = Collections.singletonList("properties/connections.properties");
        LAUNCH_TEST_TAGS = Collections.emptyList();
        WEBDRIVER_CONFIGURATION = "ru.sooslick.qa.core.webdriver.DefaultChromeConfiguration";
        WEBDRIVER_PATH = "chromedriver";
        BROWSER_BINARY_PATH = "";
    }
}
