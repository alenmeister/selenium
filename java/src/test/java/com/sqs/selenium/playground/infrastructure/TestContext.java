package com.sqs.selenium.playground.infrastructure;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private static TestContext context;
    private WebDriver driver;

    private TestContext() {
        String browserStr = System.getProperty("browser", "chrome");
        WebDriverFactory.Browser browser = WebDriverFactory.Browser.valueOf(browserStr.toUpperCase());
        driver = WebDriverFactory.getInstance(browser);
    }

    public static TestContext getInstance() {
        synchronized (TestContext.class) {
            if (context == null) {
                context = new TestContext();
            }
        }
        return context;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Webdriver has not been initialized!");
        }
        return driver;
    }
}