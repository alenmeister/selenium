package com.sqs.selenium.playground.infrastructure;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener implements WebDriverEventListener {

    private static Logger log = LoggerFactory.getLogger(EventListener.class);

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        log.info("beforeAlertAccept {}", driver);
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        log.info("afterAlertAccept {}", driver);
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        log.info("beforeAlertDismiss {}", driver);
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        log.info("afterAlertDismiss {}", driver);
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("beforeNavigateTo {}", url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        log.info("afterNavigateTo {}", url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        log.info("beforeNavigateBack {}", driver);
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        log.info("afterNavigateBack {}", driver);
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        log.info("beforeNavigateForward {}", driver);
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        log.info("afterNavigateForward {}", driver);
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        log.info("beforeNavigateRefresh {}", driver);
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        log.info("afterNavigateRefresh {}", driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.info("beforeFindBy {} {}", by, element);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.info("afterFindBy {} {}", by, element);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.info("beforeClickOn {}", element);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        log.info("afterClickOn {}", element);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.info("beforeChangeValueOf {} {}", element, keysToSend);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.info("afterChangeValueOf {} {}", element, keysToSend);
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        log.info("beforeScript {}", script);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        log.info("afterScript {}", script);
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        log.info("afterSwitchToWindow {}", windowName);
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        log.info("beforeSwitchToWindow {}", windowName);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        log.info("onException", throwable);
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        log.info("beforeGetScreenshotAs {}", target);
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        log.info("afterGetScreenshotAs {} {}", target, screenshot);
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        log.info("beforeGetText {}", element);
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        log.info("afterGetText {} {}", element, text);
    }
}