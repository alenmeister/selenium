package com.sqs.selenium.playground.pages;

import com.sqs.selenium.playground.common.WaitConditions;
import com.sqs.selenium.playground.infrastructure.UrlBuilder;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchPage extends AbstractPage {

    @FindBy(how = How.NAME, using = "q")
    private WebElement txtSearch;

    public SearchPage(WebDriver driver, int timeoutInSeconds) {
       super(driver, timeoutInSeconds);
    }

    public void navigateToUrl() throws MalformedURLException {
        URL builder = UrlBuilder.empty()
                .withProtocol("https")
                .withHost("duckduckgo")
                .build();
        String baseUrl = builder.toString();
        driver.navigate().to(baseUrl);
        waitFor(WaitConditions.urlContains(baseUrl));
    }

    public void searchForPhrase(String phrase) {
        wait.until(ExpectedConditions.elementToBeClickable(txtSearch));
        txtSearch.sendKeys(phrase);
        txtSearch.submit();
    }

    public boolean pageTitleContains(String phrase) {
        try {
            return wait.until(ExpectedConditions.titleContains(phrase));
        } catch (TimeoutException e) {
            return false;
        }
    }
}