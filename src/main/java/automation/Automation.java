package automation;

import java.util.HashMap;
import java.util.Properties;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import webpages.Base;
import webpages.Homepage;
import webpages.AdvancedSearch;
import webpages.Products;

public class Automation {

    protected static Properties prop = new Properties();
    protected static Base base;

    public static void main(String[] args) {

        /* May need to change the path of the filename for the config.properties file under the project */
        String filename = "/Users/naimul7/JavaProjects/SeleniumAutomation/src/main/java/automation/config.properties";
        try{
            InputStream input = new FileInputStream(filename);
            prop.load(input);
            base = new Base("https://" + prop.getProperty("website"), prop);
            automateHomepage();
            input.close();
        } catch(FileNotFoundException fnfex) { fnfex.printStackTrace();
        } catch(IOException ioex) { ioex.printStackTrace();
        } catch(InterruptedException iex) { iex.printStackTrace();
        } finally { System.out.println("Webpage Automation is Successful!\nThank you for running this program!"); }
    }

    public static void automateHomepage() throws InterruptedException {
        /* Create Homepage class with specified home tabs to perform basic search through specific website page */
        Homepage homepage = new Homepage(base, new String[]{"Home", "Saved", "Motors", "Fashion", "Electronics", "Collectibles & Art", "Home & Garden", "Sporting Goods", "Toys", "Business & Industrial", "Music", "eBay Refurbished"}, prop);

        /* Test#1: Navigate All Home Page Tabs */
        System.out.println(homepage.getTotalHometabs());

        for(int i = 2; i <= homepage.getTotalHometabs() / 3; i++) {
            homepage.navigateTo(i);
            Thread.sleep(3000);
            homepage.reverse();
        }

        for(int testCase = 2; testCase < 4; testCase++) {
            if (testCase == 2)  /* Test#2: Basic Search by Keyword, no Categories */
                homepage.search("Pokemon Brilliant Diamond");
            else  /* Test#3: Basic Search by Keyword & Category */
                homepage.search("Playstation 5 Accessories", "PS5 Controller");

            /* Scroll Up */
            homepage.scroll(250);
            /* Scroll Down */
            homepage.scroll(-250);
            /* Go back to homepage */
            homepage.reverse();
        }

        System.out.println("Homepage automation completed. ");

        /* Begin Advanced Search Webpage Automation */
        automateAdvancedSearch();
    }

    public static void automateAdvancedSearch() throws InterruptedException {
        /* Create an Instance of Advanced Search Page: Navigates to Advanced Search Page */
        AdvancedSearch advancedSearch = new AdvancedSearch(base, prop);

        /* Test#1: Advanced Search / Find Items by Keyword */
        advancedSearch.findItem("iPhone");

        /* Precondition to Test#2: Retrieve all categories for search and Initialize it in the AdvancedSearch class instance */
        /* Test#2: Run Advanced Search for each Cateogry */
        int i = 0;
        for(String category: advancedSearch.getCategories()) {
            if(i++ > 5)
                break;

            advancedSearch.findItems(category);
            advancedSearch.clickSearch(true);
            Thread.sleep(2500);
            advancedSearch.reverse();
        }

        /* Precondition to Test#3: Create Hashmap of Random Categories and Keywords for Advanced Search */
        HashMap<String, String> categoryKeys = new HashMap<String, String>();
        categoryKeys.put("Cell Phones & Accessories", "iPhone");
        categoryKeys.put("Computers Tablets & Networking", "MacBook Pro M1");
        categoryKeys.put("Video Games & Consoles", "PlayStation 5");
        /* Test#3: Run Multiple Advanced Searches for Random Category with Keyword */
        for(HashMap.Entry<String, String> categoryKey: categoryKeys.entrySet()) {
            advancedSearch.clearSearch();
            advancedSearch.findItem(categoryKey.getKey(), categoryKey.getValue());
            advancedSearch.clickSearch(true);
            Thread.sleep(5000);
            advancedSearch.reverse();
        }

        /*
            Test#4-9: Customized Searches
        */
        /* Test#4: Search Including All Options: Title & Description, Completed Listings & Sold Listings */
        for(String include: new String[]{"Title & Description", "Completed Listings", "Sold Listings"}) {
            advancedSearch.searchIncluding(include);
            advancedSearch.clickSearch(false);
            Thread.sleep(5000);
            advancedSearch.reverse();
        }

        /* Test#5: Search with Specified Price Range */
        advancedSearch.priceRange(400.00, 600.00);
        advancedSearch.clickSearch(false);
        Thread.sleep(5000);
        advancedSearch.reverse();

        /* Test#6: Search with Specific Buying Formats */ // Auction, Buy It Now, Auction & Buy It Now, Classified Ads
        for(String format: new String[]{"Auction", "Buy It Now", "Classified Ads"}) {
            advancedSearch.formatBuy(format);
            advancedSearch.clickSearch(false);
            Thread.sleep(5000);
            advancedSearch.reverse();
        }

        /* Test#7: Search for products with only Specified Condition */
        for(String format: new String[]{"Used", "New", "Not Specified"}) {
            advancedSearch.setConditions(format);
            advancedSearch.clickSearch(false);
            Thread.sleep(5000);
            advancedSearch.reverse();
        }

        /* Test#8: Search for Products with Custom Options for Web Results Generation */
        automateProducts();
    }

    public static void automateProducts() {
        Products products = new Products(base, prop);

        products.acceptOffers();
        products.auction();
        products.buyItNow();
        products.checkProductDescription();
        /* Switch to new tab opened for checking product description */
        products.switchTab(1);
        /* View the new tab and close the currently opened new tab for product description */
        products.closeTab();
        /* Switch tab to original opened tab */
        products.switchTab(0);
        products.allListings();

        /* Product Price Check does not require any Button Click */
        //products.checkProductPrice();
        /* Close Current Window Tab */
        products.closeTab();

        products.delete();
    }

    public static void WritePropertiesFile(String config) {
        /* Configure this method to create or update properties inside the config.properties file */
        try{
            OutputStream output = new FileOutputStream(config);
            /* Write code to write any new properties into the config.properties file */
        } catch (FileNotFoundException fnfex) { fnfex.printStackTrace();
        } finally { System.out.println("Write to " + config + ".txt" + " is successful.\nThank you !"); }
    }

    public static void ReadPropertiesFile() {/* Configure this method to read values of various properties by their key */}
}
