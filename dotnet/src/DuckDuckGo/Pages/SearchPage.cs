using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;

namespace DuckDuckGo.Pages
{
    public class SearchPage
    {
        private readonly IWebDriver _driver;
        private readonly WebDriverWait _wait;

        public SearchPage(IWebDriver driver)
        {
            _driver = driver;
            _wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(10));
        }

        public void NavigateToUrl()
        {
            const string url = "https://duckduckgo.com/";
            _driver.Navigate().GoToUrl(url);
            _wait.Until(driver => driver.Url.Contains(url));
        }

        private IWebElement SearchField
        {
            get { return _driver.FindElement(By.Id("search_form_input_homepage")); }
        }

        private IWebElement SearchButton
        {
            get { return _driver.FindElement(By.Id("search_button_homepage")); }
        }

        private IWebElement PageFooter
        {
            get { return _driver.FindElement(By.Id("footer_homepage")); }
        }

        private IEnumerable<IWebElement> SearchResults
        {
            get { return _driver.FindElements(By.ClassName("result__a")); }
        }

        public void PageHasLoaded()
        {
            _wait.IgnoreExceptionTypes(typeof(StaleElementReferenceException));
            _wait.PollingInterval = TimeSpan.FromSeconds(2);
            _wait.Until(driver => PageFooter);
        }

        public void SearchForKeyword()
        {
            _wait.Until(driver => SearchField.Displayed);
            SearchField.SendKeys("OpenBSD");
            SearchButton.Click();
        }

        public IEnumerable<IWebElement> VerifyResults()
        {
            var searchResults = SearchResults
                .Where(element => element.Text.Contains("OpenBSD"));
            return searchResults;
        }

        public string GetTitle
        {
            get { return _driver.Title; }
        }
    }
}