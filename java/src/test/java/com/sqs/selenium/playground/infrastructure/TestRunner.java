package com.sqs.selenium.playground.infrastructure;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber/cucumber.json"},
        features = "src/test/resources/features",
        glue = {"com.sqs.selenium.playground.steps", "com.sqs.selenium.playground.infrastructure"})
public class TestRunner {}