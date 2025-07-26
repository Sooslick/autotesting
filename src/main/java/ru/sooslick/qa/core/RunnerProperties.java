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
     * List of packages that contains Verifier methods.
     */
    public List<String> VERIFIERS_PACKAGES;

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

    /**
     * Path to directory with files downloaded during webdriver session.
     */
    public String WEBDRIVER_DOWNLOADS_DIRECTORY;

    /**
     * Max allowable inaccuracy for floating point math comparisons.
     */
    public double DELTA;

    /**
     * Min amount of attempts to run group of steps.
     */
    public int REPEAT_MIN_ATTEMPTS;

    /**
     * Min duration of reattempting to run steps in milliseconds.
     */
    public long REPEAT_DURATION;

    static {
        String runnerYamlName = System.getenv("RUNNER_PROPERTIES");
        runnerYamlName = runnerYamlName == null ? RUNNER_YAML : runnerYamlName;
        try {
            InputStream is = PropertiesHelper.getResourceInputStream(runnerYamlName);
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            var yaml = om.readTree(is);

            SCENARIOS_DIRECTORIES = readYamlList(yaml, "/resources/scenarios");
            STEPS_PACKAGES = readYamlList(yaml, "/resources/steps");
            PAGE_OBJECTS_PACKAGES = readYamlList(yaml, "/resources/page-objects");
            PRECONDITIONS_PACKAGES = readYamlList(yaml, "/resources/preconditions");
            DATA_GENERATORS_PACKAGES = readYamlList(yaml, "/resources/data-generators");
            VERIFIERS_PACKAGES = readYamlList(yaml, "/resources/verifiers");
            PROPERTIES_FILES = readYamlList(yaml, "/resources/properties");

            LAUNCH_TEST_TAGS = readYamlList(yaml, "/test-run/launch-tags");
            WEBDRIVER_CONFIGURATION = yaml.at("/test-run/web-driver/configuration-class").asText();
            WEBDRIVER_PATH = yaml.at("/test-run/web-driver/path").asText();
            BROWSER_BINARY_PATH = yaml.at("/test-run/web-driver/binary-path").asText();
            WEBDRIVER_DOWNLOADS_DIRECTORY = yaml.at("/test-run/web-driver/downloads-directory").asText();

            DELTA = yaml.at("/test-run/assertions/float-numbers-equation-delta").asDouble();
            REPEAT_MIN_ATTEMPTS = yaml.at("/test-run/assertions/repeatable-min-attempts").asInt();
            REPEAT_DURATION = yaml.at("/test-run/assertions/repeatable-duration-ms").asLong();
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
        PAGE_OBJECTS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pageobjects");
        PRECONDITIONS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pagemodel.precondition");
        DATA_GENERATORS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pagemodel.generator");
        VERIFIERS_PACKAGES = Collections.singletonList("ru.sooslick.qa.pagemodel.verifier");
        PROPERTIES_FILES = Collections.singletonList("properties/connections.properties");

        LAUNCH_TEST_TAGS = Collections.emptyList();
        WEBDRIVER_CONFIGURATION = "ru.sooslick.qa.core.webdriver.DefaultChromeConfiguration";
        WEBDRIVER_PATH = "chromedriver";
        BROWSER_BINARY_PATH = "";
        WEBDRIVER_DOWNLOADS_DIRECTORY = "downloads";

        DELTA = 0.001d;
        REPEAT_MIN_ATTEMPTS = 2;
        REPEAT_DURATION = 5000;
    }
}
