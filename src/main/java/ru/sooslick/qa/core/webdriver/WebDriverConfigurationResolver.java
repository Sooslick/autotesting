package ru.sooslick.qa.core.webdriver;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.NoSuchDriverException;
import ru.sooslick.qa.core.RunnerProperties;

@UtilityClass
public class WebDriverConfigurationResolver {

    public WebDriver getWebDriver() {
        WebDriverConfig config = resolveConfig(RunnerProperties.WEBDRIVER_CONFIGURATION);
        return config.getDriver(RunnerProperties.WEBDRIVER_PATH);
    }

    @SneakyThrows
    private WebDriverConfig resolveConfig(String className) {
        Class<?> aClass = ReflectionUtils.tryToLoadClass(className).get();
        if (WebDriverConfig.class.isAssignableFrom(aClass))
            return (WebDriverConfig) aClass.getDeclaredConstructor().newInstance();
        throw new NoSuchDriverException("Unable to resolve WebDriverConfig class " + className);
    }
}
