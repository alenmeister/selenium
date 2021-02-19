package com.sqs.selenium.playground.infrastructure;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private final TestContext testContext = TestContext.getInstance();
    private static boolean runBeforeAll = true;

    @Before
    public void beforeEach() {
        if (runBeforeAll) {
            beforeAll();
        }
    }

    private void beforeAll() {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                testContext.getDriver().quit()));
        runBeforeAll = false;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            embedScreenshot(scenario);
            String path = String.format("target/screenshots/%s_%s.png",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy_HH.mm")),
                    scenario.getId());
            saveScreenshot(path);
        }
    }

    private void embedScreenshot(Scenario scenario) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) testContext.getDriver();
        scenario.embed(takesScreenshot.getScreenshotAs(OutputType.BYTES), "image/png");
    }

    private void saveScreenshot(String path) {
        try {
            FileUtils.copyFile(((TakesScreenshot) testContext.getDriver())
                    .getScreenshotAs(OutputType.FILE), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}