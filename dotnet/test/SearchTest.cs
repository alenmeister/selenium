using DuckDuckGo.Infrastructure;
using DuckDuckGo.Pages;
using NUnit.Framework;
using OpenQA.Selenium;

namespace DuckDuckGo
{
    [TestFixture]
    public class SearchTest
    {
        private SearchPage _searchPage;

        [SetUp]
        public void Setup()
        {
            var driver = WebDriverFactory.CreateWebDriver(Browser.Chrome);
            _searchPage = new SearchPage(driver);
            _searchPage.NavigateToUrl();
        }

        [Test(Description = "Check that the current page matches expected title")]
        public void CheckPageTitle()
        {
            var title = _searchPage.GetTitle;
            Assert.That(title, Does.StartWith("DuckDuckGo"));
        }

        [Test(Description = "Search for OpenBSD websites on DuckDuckGo")]
        public void VerifySearchResults()
        {
            _searchPage.PageHasLoaded();
            _searchPage.SearchForKeyword();
            CollectionAssert.IsNotEmpty(_searchPage.VerifyResults());
        }

        [TearDown]
        public void Cleanup()
        {
            WebDriverFactory.DestroyWebDriver();
        }
    }
}