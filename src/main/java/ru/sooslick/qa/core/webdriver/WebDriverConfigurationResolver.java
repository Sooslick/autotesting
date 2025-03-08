package ru.sooslick.qa.core.webdriver;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.NoSuchDriverException;
import ru.sooslick.qa.core.RunnerProperties;

/**
 * Utility class for working with WebDriver presets.
 */
@UtilityClass
public class WebDriverConfigurationResolver {

    /**
     * Instantiate WebDriver from preset, specified in runner configuration.
     *
     * @param emulatedDevice if not null, then name of known device, that can be emulated by browser
     * @return started WebDriver.
     */
    public WebDriver getWebDriver(@Nullable String emulatedDevice) {
        WebDriverConfig config = resolveConfig(RunnerProperties.WEBDRIVER_CONFIGURATION);
        if (emulatedDevice != null)
            config.applyEmulation(emulatedDevice);
        return config.getDriver();
    }

    @SneakyThrows
    private WebDriverConfig resolveConfig(String className) {
        Class<?> aClass = ReflectionUtils.tryToLoadClass(className).get();
        if (WebDriverConfig.class.isAssignableFrom(aClass))
            return (WebDriverConfig) aClass.getDeclaredConstructor().newInstance();
        throw new NoSuchDriverException("Unable to resolve WebDriverConfig class " + className);
    }
}
