package webpages;

/* Selenium Web Automation Packages & Libraries */
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.ArrayList;
import java.util.Properties;

public class Base {
    /* Protected Data Fields Accessible Within the Webpages Package */
    protected Properties prop;
    protected WebDriver driver;
    protected String browser;
    protected String website;
    /* Overloaded Constructors */
    public Base() {
        this("", new Properties());
    }
    /* Basic Constructor#1 */
    public Base(String website, Properties prop) {
        this.prop = prop;
        this.browser = "chrome";
        this.website = website;

        System.setProperty("webdriver." + browser + ".driver", "/Users/naimul7/Downloads/" + browser + "driver");

        this.driver = new ChromeDriver();
        this.driver.get(website);
    }
    /* Basic Constructor#2 */
    public Base(String browser, String website) {
        this.browser = browser;
        this.website = website;

        this.driver = selectBrowserDriver();
    }
    /* Super Constructor Usage */
    public Base(Base base, Properties prop) {
        this.driver = base.getDriver();
        this.browser = base.getBrowser();
        this.website = base.getWebsite();
        this.prop = prop;
    }
    /* Getter Methods */
    public WebDriver getDriver() { return driver; }
    public String getBrowser() { return browser; }
    public String getWebsite() { return website; }
    /* Setter Methods */
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setBrowser(String browser) { this.browser = browser; }
    public void setDriver(WebDriver driver) { this.driver = driver; }

    /* Set the WebDriver according to selected browser */
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

    /* Web Functionalities */
    public void dailyDeals() { this.driver.findElement(By.name(prop.getProperty("dailyDeals"))).click(); }
    public void sell() { this.driver.findElement(By.name(prop.getProperty("sell"))).click();  }
    public void helpAndContact() { this.driver.findElement(By.name(prop.getProperty("helpAndContact"))).click(); }
    public void brandOutlet() { this.driver.findElement(By.name(prop.getProperty("brandOutlet"))).click(); }
    public void wishList() { this.driver.findElement(By.name(prop.getProperty("wishList"))).click(); }
    public void myEbay() { this.driver.findElement(By.name(prop.getProperty("myEbay"))).click(); }
    public void notificationCart() { this.driver.findElement(By.name(prop.getProperty("notificationCart"))).click(); }

    /* Footer Methods: Web Functionalities */
    public void aboutEbay() { this.driver.findElement(By.name(prop.getProperty("aboutEbay"))).click(); }
    public void announcements() { this.driver.findElement(By.name(prop.getProperty("announcements"))).click(); }
    public void community() { this.driver.findElement(By.name(prop.getProperty("community"))).click(); }
    public void verifyCopyrightText() { /* What is this? */ }

    /* Scrolling Functionality */
    public void scroll(int scrollValue) { ((JavascriptExecutor)driver).executeScript("scroll(0, " + scrollValue + ");"); }

    /* Successfully End Web Automation Session */
    public void destroy() { this.driver.close(); this.driver.quit(); }

    /* Simplification Method for Retrieving Web Elements by id, name, or xpath*/
    public WebElement retrieve(int lock, String key) { return (lock == 0 ? this.driver.findElement(By.id(key)) : (lock == 1 ? this.driver.findElement(By.xpath(key)) : this.driver.findElement(By.name(key)))); }

    /* Basic Page Navigations */
    public void reverse() { this.driver.navigate().back(); }
    public void forward() { this.driver.navigate().forward(); }
    public void refresh() { this.driver.navigate().refresh(); }

    /* Window Handle Management */
    public void newTab() { driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t"); }
    public void switchTab(int tabIndex) { driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(tabIndex)); }
    public void delete() { this.driver.quit(); }
    public void closeTab() { this.driver.close(); }
}


