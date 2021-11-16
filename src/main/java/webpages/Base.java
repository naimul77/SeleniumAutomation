package webpages;

/* Selenium Web Automation Packages & Libraries */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Properties;

public class Base {
    protected Properties prop;
    protected WebDriver driver;
    protected String browser;
    protected String website;

    /* Overloaded Constructors */
    public Base() {
        this("", new Properties());
    }

    public Base(String website, Properties prop) {
        this.prop = prop;
        this.browser = "chrome";
        this.website = website;

        this.driver = new ChromeDriver();
        this.driver.get(website);
    }

    public Base(Base base, Properties prop) {
        this.driver = base.getDriver();
        this.browser = base.getBrowser();
        this.website = base.getWebsite();
        this.prop = prop;
    }


    public WebDriver getDriver() {
        return driver;
    }

    public String getBrowser() {
        return browser;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Base(String browser, String website) {
        this.browser = browser;
        this.website = website;

        this.driver = selectBrowserDriver();
    }

    public WebDriver selectBrowserDriver() {
        System.setProperty("webdriver." + this.browser + ".driver", "/Users/naimul7/Downloads/" + this.browser + "driver");
        /* Create corresponding web driver according to specific browser indicated as method argument */
        switch(browser.toLowerCase()) {
            case "ie": return new InternetExplorerDriver();
            case "edge": return new EdgeDriver();
            case "chrome": return new ChromeDriver();
            case "gecko": return new FirefoxDriver();
            case "safari": return new SafariDriver();
            default: return null;
        }
    }
}
