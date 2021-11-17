package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class Homepage extends Base {

    private String[] hometabs;

    /* Overloaded Constructors */
    public Homepage() { this(null); }
    public Homepage(Base base) { this(base, null); }
    public Homepage(Base base, Properties prop) { this(base, null, prop); }
    public Homepage(Base base, String[] hometabs, Properties prop) {
        super(base, prop);

        this.hometabs = hometabs;
    }

    /* Getter method for hometabs */
    public String[] getHometabs() { return this.hometabs; }
    /* Navigate through a specific hometab according to specified index */
    public WebElement navigateHometab(int tabIndex) { return super.driver.findElement(By.xpath(prop.getProperty("ebayHometabPrefix") + (tabIndex == 1 ? "span": "a"))); }
    /* Getter Method for total hometabs */
    public int getTotalHometabs() { return this.hometabs.length; }

    public void navigateHometabs() {
        /* If tabIndex is less than 0 or more than the number of tabs on the homepage of the website*/
        for(int tabIndex = 0; tabIndex < Integer.parseInt(super.prop.getProperty("ebayHometabsFunctioning")); tabIndex++)
            navigateHometab(tabIndex).click();
    }

    public String selectRandomCategory() {
        return null;
    }

    /* Getter Setter Methods */
    public String[] getHomeTabs() { return this.hometabs; }
    public void setHomeTabs(String[] homeTabs) { this.hometabs = homeTabs; }

    /* Navigate to any website or specific webpage */
    public void navigate(String destination) { driver.get(destination); }
    /* Navigate to a tab by its index in the home tabs */
    public boolean navigateTo(int tabIndex) {
        if(tabIndex < 1 || tabIndex > 12)
            return false;

        this.driver.findElement(By.xpath(prop.getProperty("ebayHometabPrefix") + Integer.toString(tabIndex) + "]/" +  (tabIndex == 1 ? "span": "a"))).click();

        return true;
    }

    /* Navigate to any specific tab */
    public boolean navigateTo(String tab) {
        int i = 0;
        for(String homeTab: hometabs) {
            if(tab.equals(homeTab))
                return navigateTo(i);

            i++;
        }
        return false;
    }

    /* Search Keyword under a Specific Category */

    public void search(String keyword) {
        this.driver.findElement(By.id(super.prop.getProperty("ebaySearchField"))).sendKeys(keyword);
        this.driver.findElement(By.id(super.prop.getProperty("ebaySearchButton"))).click();
    }
    /* Search Keyword */
    public void search(String keyword, String category) {

        if(category.equals("random"))
            category = selectRandomCategory();

        selectCategory(category);

        search(keyword);
    }

    /* Select category if specified */
    public void selectCategory(String category) { this.driver.findElement(By.id(super.prop.getProperty("ebayCategorySelector"))).sendKeys(category); }

    /* Parent class function inherited */
    public void dealToday() { super.dailyDeals(); }
}
