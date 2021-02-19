package com.sqs.selenium.playground.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractPage(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeoutInSeconds);
        PageFactory.initElements(driver, this);
    }

    public boolean waitForJS() {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return document.readyState")
                .equals("complete");
    }

    public boolean waitForjQuery() {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return jQuery.active == 0");
    }

    public <V> V waitFor(ExpectedCondition<V> condition) {
        try {
            return wait.ignoring(StaleElementReferenceException.class)
                    .until(condition);
        } catch (TimeoutException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}