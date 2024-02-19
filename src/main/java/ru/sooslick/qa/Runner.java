package ru.sooslick.qa;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

// todo resolve config (steps, pages, scenarios, current suite)
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("scenarios")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ru.sooslick.qa.steps")
@ConfigurationParameter(key = Constants.OBJECT_FACTORY_PROPERTY_NAME, value = "io.cucumber.core.backend.ObjectFactory")
@IncludeTags("SooslickArtMain")
public class Runner {
}
