using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using OpenQA.Selenium.Remote;

namespace DuckDuckGo.Infrastructure
{
    internal static class WebDriverFactory
    {
        private static readonly NLog.Logger Logger = NLog.LogManager.GetCurrentClassLogger();

        private static IWebDriver _driver;
        private static readonly Uri RemoteUrl = new Uri(@"https://localhost:4444/hub");

        internal static IWebDriver CreateWebDriver(Browser browser, bool headless = false)
        {
            switch (browser)
            {
                case Browser.Chrome:
                    _driver = CreateDefaultChromeDriver(headless);
                    break;

                case Browser.Edge:
                    _driver = new EdgeDriver();
                    break;

                case Browser.InternetExplorer:
                    _driver = new InternetExplorerDriver();
                    break;

                case Browser.Remote:
                    var options = new FirefoxOptions();
                    options.PlatformName = "Linux";
                    _driver = new RemoteWebDriver(RemoteUrl, options.ToCapabilities());
                    break;

                default:
                    throw new ArgumentException("Browser is not supported or valid!");
            }

            LogBrowserVersion(_driver);
            _driver.Manage().Cookies.DeleteAllCookies();
            return _driver;
        }

        private static IWebDriver CreateDefaultChromeDriver(bool headless)
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.HideCommandPromptWindow = true;
            service.EnableVerboseLogging = false;
            service.SuppressInitialDiagnosticInformation = true;
            service.Start();

            var options = new ChromeOptions();
            options.AddArgument("start-maximized");

            if (headless)
            {
                options.AddArgument("headless");
                options.AddArgument("disable-gpu");
            }

            _driver = new ChromeDriver(service, options);
            return _driver;
        }

        internal static void DestroyWebDriver()
        {
            if (_driver != null)
            {
                _driver.Quit();
                _driver.Dispose();
            }

            _driver = null;
        }

        private static void LogBrowserVersion(IWebDriver driver)
        {
            if (driver is IHasCapabilities hasCapabilities)
            {
                var capabilities = hasCapabilities.Capabilities;
                Logger.Debug("Browser=" + capabilities.GetCapability("browserName") +
                             "Version=" + capabilities.GetCapability("browserVersion") +
                             "Platform=" + capabilities.GetCapability("platformName"));
            }
        }
    }
}