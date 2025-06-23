package ru.sooslick.qa.core.webdriver;

import lombok.SneakyThrows;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import ru.sooslick.qa.core.helper.PropertiesHelper;

import java.net.URL;
import java.util.Objects;

/**
 * Default Chrome config for web testing, using Selenium Grid
 */
public class SeleniumGridChromeConfiguration extends DefaultChromeConfiguration {

    @Override
    @SneakyThrows
    public WebDriver getDriver() {
        String url = Objects.requireNonNull(PropertiesHelper.getProperty("selenium.grid.url"));
        String user = PropertiesHelper.getProperty("selenium.grid.username");
        String pass = PropertiesHelper.getProperty("selenium.grid.password");

        ClientConfig clientConfig = ClientConfig.defaultConfig()
                .baseUrl(new URL(url));
        if (user != null && pass != null)
            clientConfig = clientConfig.authenticateAs(new UsernameAndPassword(user, pass));
        HttpCommandExecutor executor = new HttpCommandExecutor(clientConfig);
        RemoteWebDriver webDriver = new RemoteWebDriver(executor, getChromeOptions());
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
