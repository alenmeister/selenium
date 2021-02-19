package com.sqs.selenium.playground.steps;

import com.sqs.selenium.playground.infrastructure.TestContext;
import com.sqs.selenium.playground.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private SearchPage searchPage;

    public SearchSteps() {
        TestContext testContext = TestContext.getInstance();
        WebDriver driver = testContext.getDriver();
        searchPage = new SearchPage(driver, 5);
    }

    @Given("that the user is on the DuckDuckGo page")
    public void userIsOnTheGooglePage() throws Throwable {
        searchPage.navigateToUrl();
    }

    @When("the search phrase {string} is entered")
    public void searchPhraseIsEntered(String phrase) throws Throwable {
        searchPage.searchForPhrase(phrase);
    }

    @Then("results for {string} are shown")
    public void resultsAreShown(String title) throws Throwable {
        assertTrue(searchPage.pageTitleContains(title));
    }
}