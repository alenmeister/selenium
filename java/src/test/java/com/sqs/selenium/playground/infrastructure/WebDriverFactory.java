package com.sqs.selenium.playground.infrastructure;

import com.sqs.selenium.playground.infrastructure.exceptions.UnsupportedBrowserException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static WebDriver driver;
    private static URL remote;

    public enum Browser {
        HEADLESS,
        CHROME,
        FIREFOX
    }

    /**
     * Factory method that returns a WebDriver instance
     * based on the given browser
     *
     * @param browser enum representing the local browser
     * @return WebDriver instance
     */

    public static WebDriver getInstance(Browser browser) {

        if (browser == null) {
            browser = Browser.CHROME;
        }

        try {
            remote = UrlBuilder.empty()
                    .withProtocol("http")
                    .withHost("localhost:4444")
                    .withPath("/hub")
                    .build();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Obviosuly not a valid Grid URL!");
        }

        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case HEADLESS:
                final ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setBrowserName("Firefox");
                driver = new RemoteWebDriver(remote, capabilities);
            default:
                throw new UnsupportedBrowserException("Unsupported browser," + browser);
        }
        driver.manage().deleteAllCookies();
        return driver;
    }
}