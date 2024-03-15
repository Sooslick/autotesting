package ru.sooslick.qa;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("scenarios")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ru.sooslick.qa.steps")
@ConfigurationParameter(key = Constants.OBJECT_FACTORY_PROPERTY_NAME, value = "ru.sooslick.qa.core.StepsFactory")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@IncludeTags("SooslickArtMain")
public class DebugRunner {
}
