package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Properties;

import java.util.Random;

public class AdvancedSearch extends Base {
    /* Private Fields */
    private String items;
    private String stores;
    private String[] categories;
    private String[] filters;
    private String[] resultViews;
    private String[] resultsPerPage;

    /* Navigate to Advanced Search Webpage through Construction of AdvancedSearch class */
    public AdvancedSearch(Base base, Properties prop) {
        super(base, prop);
        /* Navigates to Advanced Search page */
        this.retrieve(0, "gh-as-a").click();
        /* Retrieve All Options for each of the following Items (depicted as Strings) from the Advanced Search Webpage */
        retrieveAll(new String[]{"Categories", "Filters", "Result Views", "Results Per Page"});
    }

    /* Loop through to RetrieveAll Items send as String array through the Method Argument */
    public void retrieveAll(String[] all) { for(String it: all)retrieveAll(it); }

    /* Getter Setter Methods */
    public String[] getCategories() { return this.categories; }
    public void setCategories(String[] categories) { this.categories = categories; }
    public String[] getFilters() { return this.filters; }
    public void setFilters(String[] filters) { this.filters = filters; }
    public int totalCategories() { return this.categories.length; }

    /* Find Items by Category */
    public void findItems(String category) throws InterruptedException { super.retrieve(0, "e1-1").sendKeys(category); }

    /* Find Item by Keyword for specific Category */
    public void findItem(String category, String keyword) {
        /* JDBC Required: Perform validation of category with match from relevant category in database */
        if(category.equals(""))
            category = this.categories[(new Random()).nextInt(this.categories.length)];

        super.retrieve(0, "e1-1").sendKeys(category);
        findItem(keyword);
    }

    /* Find Item by Keyword */
    public void findItem(String keyword) { super.retrieve(0, "_nkw").sendKeys(keyword); }
    /* Clear Search Field */
    public void clearSearch() { super.retrieve(0, "_nkw").clear(); }
    /* Find Item by Random Category */
    public void findItems() throws InterruptedException { findItems(this.categories[(new Random()).nextInt(this.categories.length)]); }

    /* Include Specific Information to Appear with Search */
    public void searchIncluding(String inclusion) {
        String include = "LH_";
        switch(inclusion) {
            case "Title & Description": include += "TitleDesc"; break;
            case "Completed Listings": include += "Complete"; break;
            case "Sold Listings": include += "Sold"; break;

            default: System.out.println("Sorry! The item you are looking for does not exist in this webpage (Advanced Search). ");
        }
        WebElement element = super.retrieve(0, include);
        if(!element.isSelected())
            element.click();
    }

    /* Specify Price Range for Item Search */
    public boolean priceRange(double minPrice, double maxPrice) {
        /* Discontinue function if parameters do not satisfy precondition; 0 < minPrice < maxPrice */
        if(minPrice > maxPrice || minPrice < 0)
            return false;

        /* Select the checkbox to Manually Set your Price Range */
        WebElement element = super.retrieve(0, prop.getProperty("priceRangeCheckbox"));
        if(!element.isSelected())
            element.click();

        /* Set the Minimum Price */
        element = super.retrieve(2, prop.getProperty("priceLowerBound"));
        if(!element.getText().equals(""))
            element.sendKeys(Double.toString(minPrice));

        /* Set the Maximum Price */
        element = super.retrieve(2, prop.getProperty("priceUpperBound"));
        if(!element.getText().equals(""))
            element.sendKeys(Double.toString(maxPrice));

        return true;
    }

    /* Format Specific Purchase Types to Appear with Search */
    public void formatBuy(String buyFormat) {
        String format = "LH_";
        switch(buyFormat) {
            case "Auction": format += buyFormat; break;
            case "Buy It Now": format += "BIN"; break;
            case "Classified Ads": format += "CAds"; break;
            default: System.out.println("Sorry! The item you are looking for does not exist in this page (Advanced Search). ");
        }
        WebElement element = super.retrieve(0, format);
        if(!element.isSelected())
            element.click();
    }

    /* Specify the condition of the Products for which Search */
    public void setConditions(String condition) {
        condition = (condition == "Not Specified" ? "NS": condition);

        WebElement element = super.retrieve(0, "LH_ItemCondition" + condition);

        if(!element.isSelected())
            element.click();
    }

    /* Search Customization Options */
    public void sortBy(String filter) { super.retrieve(0, prop.getProperty("ebayFilters")).sendKeys(filter); }
    public void viewResults(String resultFormat) { super.retrieve(0, prop.getProperty("ebayResultViews")).sendKeys(resultFormat); }
    public void resultsPerPage(int numOfResults) { super.retrieve(0, prop.getProperty("ebayResultsPerPage")).sendKeys(Integer.toString(numOfResults)); }
    public void clearOptions() { super.retrieve(2, prop.getProperty("clearOptionsClick")).click(); }

    public void retrieveAll(String itemo) {
        String key = prop.getProperty("ebay" + itemo.replaceAll("\\s", ""));
//        switch(itemo) {
//            case "Categories": key = prop.getProperty("ebay" + itemo);
//            case "Filters": key = "LH_SORT_BY"; break;
//            case "Result Views": key = "LH_VIEW_RESULTS_AS"; break;
//            case "Results Per Page": key = "LH_IPP"; break;
//
//            default: System.out.println("Sorry! The item you are looking for does not exist in this Advanced Search Page. ");
//        }

        System.out.println(key);
        WebElement element = this.driver.findElement(By.id(key));
        Select select = new Select(element);
        List<WebElement> all = select.getOptions();

        String[] items = new String[all.size()];
        int i = 0;
        for(WebElement item: all)
            items[i++] = item.getText();

        switch(itemo) {
            case "Categories": this.categories = items; break;
            case "Filters": this.filters = items; break;
            case "Result Views": this.resultViews = items; break;
            case "Results Per Page": this.resultsPerPage = items; break;

            default: System.out.println("Sorry! THe item you are looking to retrieve from this webpage does not exist. ");
        }
    }

    /* Click Super Advanced Search (Bottom of Page Search Button) */
    public void clickSearch(boolean click) {
        super.retrieve(click ? 1 : 0, prop.getProperty(click ? "upper" : "lower") + "AdvancedSearchButton");
//        if(click) /* Upper Search Button */
//            super.retrieve(1,prop.getProperty("upperAdvancedSearchButton")).click();
//        else /* Lower Search Button */
//            super.retrieve(0, prop.getProperty("lowerAdvancedSearchButton")).click();
    }
}
