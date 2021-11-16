package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class Homepage extends Base {

    private String[] hometabs;

    /* Overloaded Constructors */
    public Homepage() { this(null); }
    public Homepage(Base base) { this(base, null); }

    public Homepage(Base base, Properties prop) {
        super(base, prop);

        this.hometabs = retrieveHometabs();
    }

    public String[] retrieveHometabs() {
        return null;
    }

    public WebElement navigateHometab(int tabIndex) {
        return super.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/ul/li[))" + (tabIndex == 1 ? "span": "a")));
    }

    public void navigateHometabs() {
        /* If tabIndex is less than 0 or more than the number of tabs on the homepage of the website*/
        for(int tabIndex = 0; tabIndex < Integer.parseInt(super.prop.getProperty("ebayHometabFunctioning")); tabIndex++)
            navigateHometab(tabIndex).click();
    }

    public void search(String keyword) {
        this.driver.findElement(By.id(super.prop.getProperty("ebaySearchField"))).sendKeys(keyword);
        this.driver.findElement(By.id(super.prop.getProperty("ebaySearchButton"))).click();
    }

    public void search(String keyword, String category) {

        if(category.equals("random"))
            category = selectRandomCategory();

        this.driver.findElement(By.id(super.prop.getProperty("ebayCategorySelector"))).sendKeys(category);

        search(keyword);
    }

    public String selectRandomCategory() {
        return null;
    }
}
