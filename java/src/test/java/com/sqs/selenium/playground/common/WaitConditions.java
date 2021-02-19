package com.sqs.selenium.playground.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitConditions {

    public static ExpectedCondition<Boolean> urlContains(final String phrase) {
        return new ExpectedCondition<Boolean>() {

            private String currentUrl;

            @Override
            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return currentUrl.contains(phrase);
            }

            @Override
            public String toString() {
                return String.format("URL should contain '%s'. Current URL: '%s'", phrase, currentUrl);
            }
        };
    }

    public static ExpectedCondition<Boolean> pageContainsText(final String text) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getPageSource().contains(text);
            }

            @Override
            public String toString() {
                return String.format("Page to contain '%s'", text);
            }
        };
    }
}